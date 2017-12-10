package com.bppleman.controller;

import com.bppleman.entity.*;
import com.bppleman.judge.JudgeThreadProxy;
import com.bppleman.service.*;
import com.bppleman.tool.TokenTool;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
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
    private String contest_status = "/status/user_problem_status/1";
    private String showProblem = "/problem/show_problem";
    private String codeProblem = "/problem/code_problem";

    public static final String NeedLogin = "NeedLogin";
    public static final String CanCreate = "CanCreate";
    public static final String CanNotCreate = "CanNotCreate";

    @Resource
    private ContestService contestService = null;

    @Resource
    private LabelService labelService = null;

    @Resource
    private ProblemService problemService = null;

    @Resource
    private StatusService statusService = null;

    @Resource
    private UserSolveService userSolveService = null;

    @Resource
    private CodeService codeService = null;

    @Resource
    private JudgeThreadProxy judgeThreadProxy = null;


    @RequestMapping("/create_contest")
    public String create_contest(String note, HttpSession session) {
        String create_contest_token = TokenTool.getInstance().makeToken();
        session.setAttribute("create_contest_token", create_contest_token);
        if (note.equals(Contest.Note.TEACHER)) {
            User user = (User)session.getAttribute("user");
//            当点击Teacher下的CreateContest的时候
//            需要判断登录的账号是否是管理员
//            管理员才能创建teacher级别的contest
//            否则直接强制创建diy级别的contest
            if (user.getType().equals(User.Admin)) {
                session.setAttribute("note", note);
            } else {
                session.setAttribute("note", Contest.Note.DIY);
            }
        } else {
//            如果点击的是DIY下的CreateContest
//            则任何账户都可以直接创建
            session.setAttribute("note", note);
        }
        return prefix + create_contest;
    }

    @RequestMapping("/submit_contest")
    public String submit_contest(Contest contest,
                                 int day, int hour, int minute, int second,
                                 String token, HttpSession session) {
        String create_contest_token = (String) session.getAttribute("create_contest_token");
        if (token.equals(create_contest_token)) {
            long duration = (day * 24 * 3600 + hour * 3600 + minute * 60 + second) * 1000;
            Timestamp endTime = new Timestamp(contest.getStartTime().getTime() + duration);
            contest.setEndTime(endTime);
            contest.setDuration(duration);
//            如果开始时间比当前时间推后60秒
//            即在60秒内创建的contest
//            都将直接视为running
//            否则为ready
            if ((contest.getStartTime().getTime() - System.currentTimeMillis()) <= 60000) {
                contest.setStatus(Contest.Status.READY);
            } else {
                contest.setStatus(Contest.Status.RUNNING);
            }
            User user = (User)session.getAttribute("user");
            contest.setAuthor(user.getUsername());
            String note = (String) session.getAttribute("note");
//            这里就是正式设置Note的地方
//            两个判断条件：
//            1、账户必须是管理员
//            2、点击的必须是Teacher下的CreateContest
//            如果都满足那么将contest设置为Contest.Note.TEACHER
            if (user.getType().equals(User.Admin) && note.equals(Contest.Note.TEACHER)) {
                contest.setNote(Contest.Note.TEACHER);
            } else {
                contest.setNote(Contest.Note.DIY);
            }

            if (contestService.insertContest(contest)) {
                return "redirect:" + prefix + editContestProblem + "?contestId=" + contest.getId();
            }
        }
        return "redirect:/home";
    }

    @RequestMapping("/edit_contest_problem")
    public String edit_contest_problem(int contestId, HttpServletRequest request, HttpSession session) {
        Contest contest = contestService.getContestById(contestId);
        if (contest != null) {
            List<String> types = labelService.getAllTypes();
            Map<String, List<Label>> labelsMap = new HashMap<>();
            for (String type : types) {
                List<Label> labels = labelService.getLabelsByType(type);
                labelsMap.put(type, labels);
            }
            request.setAttribute("types", types);
            request.setAttribute("labelsMap", labelsMap);
            request.setAttribute("contest", contest);
            String token = TokenTool.getInstance().makeToken();
            session.setAttribute("contest_problem_token", token);
            return prefix + editContestProblem;
        }
        String note = (String) session.getAttribute("note");
        return "redirect:" + prefix + listContest + "?note=" + note;
    }

    @RequestMapping("/submit_contest_problem")
    public String submit_contest_problem(@RequestParam(required = false) List<Integer> selected,int contestId, String token, HttpSession session){
        String contest_problem_token = (String)session.getAttribute("contest_problem_token");
        if (token.equals(contest_problem_token)) {
            if (selected != null) {
                List<Integer> problemIds = new ArrayList<>();
                Contest contest = contestService.getContestById(contestId);
                for (Integer id : selected) {
                    if (!problemIds.contains(id))
                        problemIds.add(id);
                }
                contestService.updateContestProblem(contestId, problemIds);
                List<IDParam> idParams = new ArrayList<>();
                for (Problem problem : contest.getProblems()) {
                    if (!problemIds.contains(problem.getId())) {
                        idParams.add(new IDParam(null, problem.getId(), contestId, null));
                    }
                }
                if (idParams.size() != 0) {
                    userSolveService.deleteUserSolve(idParams);
                }
            }
        }
        String note = (String) session.getAttribute("note");
        return "redirect:" + prefix + listContest + "?note=" + note;
    }

    @RequestMapping("/list_contest")
    public String listContest(String note, HttpSession session) {
        session.setAttribute("note", note);
        return "redirect:" + prefix + listContest + "/1";
    }

    @RequestMapping("/list_contest/{pageNumber}")
    public String listContestWithPageNumber(@PathVariable int pageNumber, HttpServletRequest request, HttpSession session) {
        String note = (String) session.getAttribute("note");
        List<Contest> contests = contestService.getContestsByNote(note);
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        for (Contest contest : contests) {
            if (currentTime.getTime() >= contest.getStartTime().getTime()) {
                contest.setStatus(Contest.Status.RUNNING);
            }
            if (currentTime.getTime() > contest.getEndTime().getTime()) {
                contest.setStatus(Contest.Status.END);
            }
        }
        session.setAttribute("contests", contests);

        double countPerPage = 20.0;
        int pageCount = (int) Math.ceil((double)contests.size() / countPerPage);
        //        总页数
        session.setAttribute("pageCount",pageCount);
        //        当前页
        session.setAttribute("pageNumber", pageNumber);
        //        每页的problem数量
        session.setAttribute("countPerPage", countPerPage);
        request.setAttribute("h1", "竞赛列表");
        return prefix + listContest;
    }

    @RequestMapping("/show_contest")
    public String showContest(int contestId, HttpServletRequest request, HttpSession session) {
        Contest contest = contestService.getContestById(contestId);
        if (contest != null) {
            session.setAttribute("contest", contest);
            User user = (User) session.getAttribute("user");
            IDParam idParam = new IDParam(user.getId(), null, contestId, null);
            Map<Integer, String> userSolveMap = userSolveService.getUserSolveMap(idParam);
            request.setAttribute("userSolveMap",  userSolveMap);
        }
        return prefix + showContest;
    }

    @RequestMapping("/show_problem")
    public String showProblem(int problemId, HttpServletRequest request, HttpSession session) {
        Problem problem = problemService.getProblemByID(problemId);
        request.setAttribute("problem", problem);
        request.setAttribute("codePath", "/contest/show_problem/code_problem");
        request.setAttribute("statusPath", "/list_status/contest/with_problem/1");
        Contest contest = (Contest) session.getAttribute("contest");
        request.setAttribute("contestId", contest.getId());
        return showProblem;
    }

    @RequestMapping("/show_problem/code_problem")
    public String codeProblem(int problemId, HttpSession session, HttpServletRequest request) {
        String token = TokenTool.getInstance().makeToken();
        session.setAttribute("code_problem_token", token);
        request.setAttribute("problemId", problemId);
        request.setAttribute("submitPath", "/contest/show_problem/code_problem/submit");
        return codeProblem;
    }

    @RequestMapping("/show_problem/code_problem/submit")
    public String codeProblemSubmit(Code code, String token, int problemId, HttpSession session, HttpServletRequest request) {
        String serverToken = (String) session.getAttribute("code_problem_token");
        Contest contest = (Contest) session.getAttribute("contest");
//        符合令牌的才可以插入
        if (serverToken != null && token.equals(serverToken) && contest != null) {
            session.removeAttribute("token");

            User user = (User) session.getAttribute("user");
            code.getIdParam().setUserId(user.getId());
            code.getIdParam().setProblemId(problemId);
            code.getIdParam().setContestId(contest.getId());
            code.setLength(code.getCodeValue().length());
            codeService.insertCode(code);
            Status status = new Status();

            status.setStatusValue(Status.Judging);
            status.setDate(new Timestamp(System.currentTimeMillis()));
            status.getIdParam().setUserId(user.getId());
            status.getIdParam().setProblemId(problemId);
            status.getIdParam().setContestId(contest.getId());
            statusService.insertStatus(status);

            judgeThreadProxy.setStatus(status);
            judgeThreadProxy.run();
        }
        return "redirect:/status/list_status/contest/with_user_problem/1" + "?contestId=" + contest.getId() + "&userId=" + code.getIdParam().getUserId() + "&problemId=" + code.getIdParam().getProblemId();
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

    @RequestMapping("/check_contest_password")
    @ResponseBody
    public boolean checkContestPassword(int contestId, String password) {
        Contest contest = contestService.getContestById(contestId);
        if (contest.getPassword().equals(password))
            return true;
        else return false;
    }

    @RequestMapping("/load_selected_problem")
    @ResponseBody
    public List<Problem> loadSelectedProblem(Integer contestId) {
        Contest contest = contestService.getContestById(contestId);
        return contest.getProblems();
    }

    @RequestMapping("/load_problem")
    @ResponseBody
    public List<Problem> loadProblem(String type, String label) {
        List<Problem> problems = contestService.getProblemsByLabel(label);
        return problems;
    }

    @RequestMapping("/search")
    @ResponseBody
    public List<Contest> search(String keyWord) {
        if (keyWord == null || "".equals(keyWord.trim())) {
            return null;
        }
        List<Contest> searchContests = contestService.getProblemsByKeyWord(keyWord);
        return searchContests;
    }
}
