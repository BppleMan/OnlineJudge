package com.bppleman.controller;

import com.bppleman.affair.submit.CodeAffair;
import com.bppleman.entity.*;
import com.bppleman.judge.JudgeThreadProxy;
import com.bppleman.service.*;
import com.bppleman.tool.TokenTool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BppleMan on 2017/11/7.
 */
@Controller
@RequestMapping("/problem")
public class ProblemController {
    private String prefix = "/problem/";
    private String listProblem = "list_problem";
    private String showProblem = "show_problem";
    private String codeProblem = "code_problem";
    private String createProblem = "create_problem";
    private String user_problem_status = "/status/user_problem_status/1";

    private Map<String, String> typeMap;
    public static class Type {
        public static final String ID = "id";
        public static final String TITLE = "title";
        public static final String LABEL = "label";
        public static final String AUTHOR = "author";
    }


    @Resource
    private ProblemService problemService = null;

    @Resource
    private StatusService statusService = null;

    @Resource
    private CodeService codeService = null;

    @Resource
    private UserSolveService userSolveService = null;

    @Resource
    private ProblemRatioService problemRatioService = null;

    @Resource
    private TableCountService tableCountService = null;

    @Resource
    private JudgeThreadProxy judgeThreadProxy = null;

    @Resource
    private CodeAffair codeAffair = null;

    public ProblemController() {
        typeMap = new HashMap<>();
        typeMap.put(Type.ID, "题目编号");
        typeMap.put(Type.TITLE, "题目名称");
        typeMap.put(Type.LABEL, "题目标签");
        typeMap.put(Type.AUTHOR, "题目作者");
    }

    @RequestMapping("/list_problem")
    public String listProblemWithPageNumber(@RequestParam(value = "tp", required = false) String type,
                                            @RequestParam(value = "kw", required = false) String keyWord,
                                            @RequestParam(value = "page", required = true) Integer pageNumber,
                                            HttpServletRequest request, HttpSession session) {
        if ((type != null && !"".equals(type.trim())) && (keyWord != null && !"".equals(type.trim()))) {
            request.setAttribute("search", true);
            request.setAttribute("type", type);
            request.setAttribute("keyWord", keyWord);
        }
        request.setAttribute("typeMap", typeMap);
        int countPerPage = 50;
        List<Problem> problems = problemService.getProblemsWithPage(type, keyWord, pageNumber, countPerPage);
        request.setAttribute("problems", problems);
        int tableCount = problemService.getCount(type, keyWord);
        int pageCount = (int) Math.ceil((double) tableCount / (double) countPerPage);
        request.setAttribute("pageCount",pageCount);
        request.setAttribute("pageNumber", pageNumber);
        User user = (User) session.getAttribute("user");
        if (user != null) {
            IDParam userSolveParam = new IDParam(user.getId(), null, null, null);
            Map<Integer, String> userSolveMap = userSolveService.getUserSolveMap(userSolveParam);
            request.setAttribute("userSolveMap", userSolveMap);
        }
        IDParam problemRatioParam = new IDParam(null, null, null, null);
        Map<Integer, ProblemRatio> problemRatioMap = problemRatioService.getProblemRatioMap(problemRatioParam);
        request.setAttribute("problemRatioMap", problemRatioMap);
        return prefix + listProblem;
    }

    @RequestMapping("/show_problem")
    public String showProblem(int problemId, HttpServletRequest request) {
        Problem problem = problemService.getProblemByID(problemId);
        request.setAttribute("problem", problem);
        request.setAttribute("codePath", "/problem/show_problem/code_problem");
        request.setAttribute("statusPath", "/list_status/with_problem/1");
        return prefix + showProblem;
    }

    @RequestMapping("/show_problem/code_problem")
    public String codeProblem(int problemId, HttpSession session, HttpServletRequest request) {
        String token = TokenTool.getInstance().makeToken();
        session.setAttribute("code_problem_token", token);
        request.setAttribute("problemId", problemId);
        request.setAttribute("submitPath", "/problem/show_problem/code_problem/submit");
        return prefix + codeProblem;
    }

    @RequestMapping("/show_problem/code_problem/submit")
    public String codeProblemSubmit(Code code, String token, int problemId, HttpSession session, HttpServletRequest request) {
        String serverToken = (String) session.getAttribute("code_problem_token");
//        符合令牌的才可以插入
        if (serverToken == null || !token.equals(serverToken)) {
            return "redirect:" + user_problem_status + "?userId=" + code.getIdParam().getUserId() + "&problemId=" + code.getIdParam().getProblemId();
        }
        session.removeAttribute("token");

        User user = (User) session.getAttribute("user");
        IDParam idParam = new IDParam(user.getId(), problemId, null, null);
        code.setIdParam(idParam);
        code.setLength(code.getCodeValue().length());
        codeService.insertCode(code);

        Status status = new Status();
        status.setIdParam(idParam);
        status.setCode(code);
        status.setStatusValue(Status.Judging);
        status.setDate(new Timestamp(System.currentTimeMillis()));
        statusService.insertStatus(status);

        judgeThreadProxy.setDelegate(codeAffair);
        judgeThreadProxy.setStatus(status);
        judgeThreadProxy.run();

        return "redirect:/status/list_status/1?userId=" + idParam.getUserId() + "&problemId=" + idParam.getProblemId();
    }

    @RequestMapping("/create_problem")
    public String createProblem() {
        return prefix + createProblem;
    }

    @RequestMapping("/create_problem/submit")
    public String createProblemSubmit(Problem problem) {
        if (problemService.insertProblem(problem)) {
            IDParam idParam = new IDParam(null, problem.getId(), null, null);
            ProblemRatio problemRatio = new ProblemRatio();
            problemRatio.setIdParam(idParam);
            problemRatioService.insertProblemRatio(problemRatio);
            tableCountService.updateTableCount("t_problem", 1, "+");
        }
        return "redirect:" + prefix + listProblem + "/1";
    }
}
