package com.bppleman.controller;

import com.bppleman.entity.*;
import com.bppleman.judge.JudgeThreadProxy;
import com.bppleman.service.*;
import com.bppleman.tool.TokenTool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BppleMan on 17/6/15.
 */
@Controller
@RequestMapping("/contest")
public class ContestController {
    private String prefix = "/contest";
    private String listContest = "/list_contest";
    private String editContestProblem = "/edit_contest_problem";
    private String create_contest = "/create_contest";
    private String showContest = "/show_contest";
    private String showProblem = "/problem/show_problem";
    private String codeProblem = "/problem/code_problem";
    private String inputPassword = "/input_password";

    public static final String NeedLogin = "NeedLogin";
    public static final String CanCreate = "CanCreate";
    public static final String CanNotCreate = "CanNotCreate";

    @Resource
    private ContestService contestService;

    @Resource
    private LabelService labelService;

    @Resource
    private ProblemService problemService;

    @Resource
    private ContestProblemUserSolveService contestProblemUserSolveService;

    @Resource
    private ProblemRatioService contestProblemRatioService;

    @Resource
    private ProblemLabelService problemLabelService;

    @Resource
    private StatusService contestStatusService;

    @Resource
    private JudgeThreadProxy judgeThreadProxy;


    private Map<String, String> typeMap;
    public static class Type {
        //        属性值对应的是表中的字段名
        public static final String USERNAME = "username";
        public static final String USER_ID = "user_id";
        public static final String TYPE = "type";
        public static final String STATUS = "status";
        public static final String NAME = "name";
        public static final String PROBLEM = "contest_id";
    }

    public ContestController() {
        typeMap = new HashMap<>();
        typeMap.put(Type.USERNAME, "竞赛作者");
        typeMap.put(Type.TYPE, "竞赛类型");
        typeMap.put(Type.STATUS, "竞赛状态");
        typeMap.put(Type.NAME, "竞赛名称");
    }

    @RequestMapping("/create_contest")
    public String create_contest(String note, HttpSession session) {
        String create_contest_token = TokenTool.getInstance().makeToken();
        session.setAttribute("create_contest_token", create_contest_token);
        return prefix + create_contest;
    }

    @RequestMapping("/submit_contest")
    public String submit_contest(Contest contest, String token, HttpSession session) {
        String create_contest_token = (String) session.getAttribute("create_contest_token");
        if (token.equals(create_contest_token)) {

            User user = (User)session.getAttribute("user");
            contest.setUserId(user.getId());
            contest.setUsername(user.getUsername());

            if (contestService.insertContest(contest)) {
                return "redirect:" + prefix + editContestProblem + "?contestId=" + contest.getId();
            }
        }
        return "redirect:/home";
    }

    @RequestMapping("/edit_contest_problem")
    public String edit_contest_problem(Integer contestId,
                                       @RequestParam(value = "rt", required = false) Integer requestTime,
                                       @RequestParam(value = "lt", required = false) String labelType,
                                       @RequestParam(value = "lv", required = false) String labelValue,
                                       @RequestParam(value = "cpage", required = false) Integer collapsePage,
                                       HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes) {
//        System.out.println(request.get());
        Contest contest = contestService.getContestById(contestId);
        if (contest != null) {
//            contest中的problem
            List<Problem> contestProblems = contestService.getProblemsByContestId(contestId, 1, null);
            request.setAttribute("contestProblems", contestProblems);
//            所有的label
            List<Label> labels = labelService.getLabels();
            request.setAttribute("labels", labels);
//            每个label中problem的分页
//            每个label单页的problem数量是固定的
            Integer countPerPage = 10;
//            这个map很重要
//            它维护了一个labelValue--currentPage的键值对
//            也就是说，在前端每个手风琴页中都是一个不同的labelValue对应的problem集合
//            那么每个手风琴页在翻页时，页码并非是一致的
//            每一次翻页只会对一个手风琴页进行翻页
//            那么其它的手风琴页应该保持原来的页码
            Map<String, Integer> labelCurrentPage = null;
            if (requestTime != null) {
                labelCurrentPage = (Map<String, Integer>) session.getAttribute("labelCurrentPage");
                requestTime++;
            } else {
//                如果session中没有维护这个map
//                那么创建并维护这个map
//                并且将所有label对应的problem分页的当前页都初始化为1
                labelCurrentPage = new HashMap<>();
                for (Label label : labels) {
                    for (String value : label.getValues()) {
                        labelCurrentPage.put(value, 1);
                    }
                }
                session.setAttribute("labelCurrentPage", labelCurrentPage);
                requestTime = 1;
            }
            request.setAttribute("requestTime", requestTime);
            if (labelValue != null) {
//                当labelValue不为空的时候
//                应该更新labelValue对应的当前页码
                labelCurrentPage.put(labelValue, collapsePage);
                session.setAttribute("labelCurrentPage", labelCurrentPage);
            }
//            要获取不同label对应problem的不同分页
//            就应该传入：
//            1、labelCurrentPage参数
//            2、每页problem数量
            Map<String, List<Problem>> labelProblemsMap = problemLabelService.getLabelProblemsMapByLabelValueWithPage(labelCurrentPage, countPerPage);
            Map<String, Integer> labelPageCountMap = problemLabelService.getLabelPageCountsMap(labelProblemsMap.keySet(), countPerPage);
            Map<String, Map<String, Integer>> labelPageCountBeginEndMap = new HashMap<>();
            for (String label : labelPageCountMap.keySet()) {
                Map<String, Integer> beginEndMap = new HashMap<>();
                Integer begin = 1, end = 1;
                Integer pageCount = labelPageCountMap.get(label);
                Integer pageNumber =  labelCurrentPage.get(label);
                if (pageCount > 10) {
                    for (int i = 0; i < Math.ceil((double) pageCount / 10); i++) {
                        if (pageNumber > i * 10 && pageNumber <= (i + 1) * 10) {
                            begin = i * 10 + 1;
                            end = (i + 1) * 10;
                        }
                    }
                    if (end > pageCount)
                        end = pageCount;
                }
                beginEndMap.put("begin", begin);
                beginEndMap.put("end", end);
                labelPageCountBeginEndMap.put(label, beginEndMap);
            }

            request.setAttribute("labelPageCountBeginEndMap", labelPageCountBeginEndMap);
            request.setAttribute("labelProblemsMap", labelProblemsMap);
            request.setAttribute("labelPageCountMap", labelPageCountMap);
//            contest：向前端传值
            request.setAttribute("contest", contest);
            request.setAttribute("lv", labelValue);
            request.setAttribute("labelType", labelType);
//            token令牌
            String token = TokenTool.getInstance().makeToken();
            session.setAttribute("contest_problem_token", token);
            return prefix + editContestProblem;
        }
        return "redirect:" + prefix + listContest;
    }

    @RequestMapping("/submit_contest_problem")
    public String submit_contest_problem(@RequestParam(required = false) List<Integer> selected,
                                         Integer contestId, String token,
                                         HttpSession session){
        String contest_problem_token = (String)session.getAttribute("contest_problem_token");
        if (token.equals(contest_problem_token)) {
//            经过编辑contest之后，将会产生三部分problem
//            1、需要保留的problem
//            2、需要新增的problem
//            3、需要删除的problem
            contestService.updateContestProblem(contestId, selected);
        }
        return "redirect:" + prefix + listContest;
    }

    @RequestMapping("/list_contest")
    public String listContestWithPageNumber(@RequestParam(value = "tp", required = false) String type,
                                            @RequestParam(value = "kw", required = false) String keyWord,
                                            @RequestParam(value = "page", required = false) Integer pageNumber,
                                            @RequestParam(value = "cp", required = false) Integer countPerPage,
                                            HttpServletRequest request) {
        if (countPerPage == null)
            countPerPage = 20;
        if (pageNumber == null)
            pageNumber = 1;
        List<Contest> contests = contestService.getContestsWithPage(type, keyWord, pageNumber, countPerPage);
        request.setAttribute("contests", contests);
        setPagination(type, keyWord, countPerPage, pageNumber, request);
        request.setAttribute("h1", "竞赛列表");
        request.setAttribute("typeMap", typeMap);
        return prefix + listContest;
    }

    @RequestMapping("/show_contest")
    public String showContest(Integer contestId,
                              @RequestParam(value = "page") Integer pageNumber,
                              HttpServletRequest request, HttpSession session) {
        Contest contest = contestService.getContestById(contestId);
        User user = (User) session.getAttribute("user");
        if (contest != null) {
            Integer countPerPage = 5;
            List<Problem> problems = contestService.getProblemsByContestId(contestId, pageNumber, countPerPage);
            request.setAttribute("problems", problems);
            Map<Integer, ProblemRatio> contestProblemRatiosMap = contestProblemRatioService.getProblemRatioMap(new IDParam(-1, -1, contestId, -1));
            request.setAttribute("contestProblemRatiosMap", contestProblemRatiosMap);
            Map<Integer, String> contestProblemUserSolveMap = contestProblemUserSolveService.getUserSolveMap(new IDParam(user.getId(), null, contestId, null));
            request.setAttribute("contestProblemUserSolveMap", contestProblemUserSolveMap);
            setPagination(Type.PROBLEM, String.valueOf(contestId), countPerPage, pageNumber, request);
            if (user != null) {
                IDParam idParam = new IDParam(user.getId(), null, contestId, null);
                Map<Integer, String> userSolveMap = contestProblemUserSolveService.getUserSolveMap(idParam);
                request.setAttribute("userSolveMap",  userSolveMap);
            }
            request.setAttribute("contestId", contestId);
        }
        return prefix + showContest;
    }

    @RequestMapping("/show_problem")
    public String showProblem(Integer contestId, Integer problemId,
                              HttpServletRequest request, HttpSession session) {
        Problem problem = problemService.getProblemByProblemId(problemId);
        request.setAttribute("problem", problem);
        request.setAttribute("codeProblemPath", "/contest/show_problem/code_problem");
        request.setAttribute("isContestProblem", true);
        request.setAttribute("contestId", contestId);
        return showProblem;
    }

    @RequestMapping("/show_problem/code_problem")
    public String codeProblem(Integer problemId, Integer contestId, HttpSession session, HttpServletRequest request) {
        request.setAttribute("isContestProblem", true);
        String token = TokenTool.getInstance().makeToken();
        session.setAttribute("code_problem_token", token);
        request.setAttribute("problemId", problemId);
        request.setAttribute("contestId", contestId);
        request.setAttribute("submitPath", "/contest/show_problem/code_problem/submit");
        return codeProblem;
    }

    @RequestMapping("/show_problem/code_problem/submit")
    public String codeProblemSubmit(Status status, String token,
                                    HttpSession session, RedirectAttributes redirectAttributes) {
        String serverToken = (String) session.getAttribute("code_problem_token");
        User user = (User) session.getAttribute("user");
        IDParam param = status.getIdParam();
        param.setUserId(user.getId());
        Contest contest = contestService.getContestById(param.getContestId());
//        符合令牌的才可以插入
        if (serverToken != null && token.equals(serverToken) && contest != null) {
            session.removeAttribute("token");
            status.setStatusValue(Status.Judging);
            status.setDate(new Timestamp(System.currentTimeMillis()));
            contestStatusService.insertStatus(status);

            judgeThreadProxy.setStatusService(contestStatusService);
            judgeThreadProxy.setStatus(status);
            judgeThreadProxy.run();
        }

        redirectAttributes.addAttribute("page", 1);
        redirectAttributes.addAttribute("contestId", param.getContestId());
        redirectAttributes.addAttribute("userId", param.getUserId());
        redirectAttributes.addAttribute("problemId", param.getProblemId());
        return "redirect:/status/list_contest_status";
    }

    @RequestMapping("/can_create_contest")
    @ResponseBody
    public String canCreateContest(HttpSession session) {
        User user = (User)session.getAttribute("user");
        if (user == null) {
            return NeedLogin;
        } else if (contestService.canCreateContest(user)) {
            return CanCreate;
        } else
            return CanNotCreate;
    }

    @RequestMapping("/should_input_password")
    @ResponseBody
    public String shouldInputPassword(Integer contestId, HttpSession session) {
        String password = "password";
        String noPassword = "noPassword";
        String login = "login";
        User user = (User) session.getAttribute("user");
        if (user == null)
            return login;
        Contest contest = contestService.getContestById(contestId);
        if (contest.getType().equals(Contest.Type.PASSWORD))
            return password;
        else return noPassword;
    }

    @RequestMapping("/check_contest_password")
    @ResponseBody
    public boolean checkContestPassword(Integer contestId, String password) {
        Contest contest = contestService.getContestById(contestId);
        if (contest.getPassword().equals(password))
            return true;
        else return false;
    }

    @RequestMapping("/generateProblem")
    @ResponseBody
    public List<Problem> generateProblem(String typeValue, Integer levelStart, Integer levelEnd, Integer problemNumber) {
        System.out.println(typeValue);
        System.out.println(levelStart);
        System.out.println(levelEnd);
        System.out.println(problemNumber);
        return null;
    }


    private void setPagination(String type, String keyWord, Integer countPerPage, Integer pageNumber,
                               HttpServletRequest request) {
        if ((type != null && !"".equals(type.trim())) && (keyWord != null && !"".equals(type.trim()))) {
            request.setAttribute("search", true);
            request.setAttribute("type", type);
            request.setAttribute("keyWord", keyWord);
        }
        Integer tableCount = contestService.getCount(type, keyWord);
        Integer pageCount = (int) Math.ceil((double) tableCount / (double) countPerPage);
        request.setAttribute("pageCount",pageCount);
        request.setAttribute("pageNumber", pageNumber);
    }
}
