package com.bppleman.controller;

import com.bppleman.entity.*;
import com.bppleman.judge.JudgeThreadProxy;
import com.bppleman.service.*;
import com.bppleman.tool.TokenTool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    private ProblemService problemService;

    @Resource
    private StatusService statusService;

    @Resource
    private CodeService codeService;

    @Resource
    private ProblemUserSolveService problemUserSolveService;

    @Resource
    private ProblemRatioService problemRatioService;

    @Resource
    private LabelService labelService;

    @Resource
    private JudgeThreadProxy judgeThreadProxy;

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
                                            @RequestParam(value = "cp", required = false) Integer countPerPage,
                                            HttpServletRequest request, HttpSession session) {
        request.setAttribute("typeMap", typeMap);
        if (countPerPage == null)
            countPerPage = 50;
        List<Problem> problems = problemService.getProblemsWithPage(type, keyWord, pageNumber, countPerPage);
        request.setAttribute("problems", problems);
//        这里用于设置关于分页的参数
        setPagination(type, keyWord, countPerPage, pageNumber, request);
        User user = (User) session.getAttribute("user");
        if (user != null) {
            Map<Integer, String> userSolveMap = problemUserSolveService.getUserSolveMap(user.getId());
            request.setAttribute("userSolveMap", userSolveMap);
        }
        List<Label> labels = labelService.getLabels();
        request.setAttribute("labels", labels);
        return prefix + listProblem;
    }

    @RequestMapping("/show_problem")
    public String showProblem(Integer problemId, HttpServletRequest request) {
        Problem problem = problemService.getProblemByProblemId(problemId);
        request.setAttribute("problem", problem);
        request.setAttribute("codeProblemPath", "/problem/show_problem/code_problem");
        request.setAttribute("statusPath", "/list_status/with_problem/1");
        return prefix + showProblem;
    }

    @RequestMapping("/show_problem/code_problem")
    public String codeProblem(Integer problemId, HttpSession session, HttpServletRequest request) {
        String token = TokenTool.getInstance().makeToken();
        session.setAttribute("code_problem_token", token);
        request.setAttribute("problemId", problemId);
        request.setAttribute("submitPath", "/problem/show_problem/code_problem/submit");
        return prefix + codeProblem;
    }

    @RequestMapping("/show_problem/code_problem/submit")
    public String codeProblemSubmit(Code code, String token, Integer problemId, HttpSession session, RedirectAttributes redirectAttributes) {
        String serverToken = (String) session.getAttribute("code_problem_token");
//        符合令牌的才可以插入
        User user = (User) session.getAttribute("user");
        IDParam idParam = new IDParam(user.getId(), problemId, null, null);
        if (serverToken != null && token.equals(serverToken)) {
            session.removeAttribute("token");
            code.setIdParam(idParam);
            code.setLength(code.getCodeValue().length());
            codeService.insertCode(code);

            Status status = new Status();
            status.setIdParam(idParam);
            status.setCode(code);
            status.setStatusValue(Status.Judging);
            status.setDate(new Timestamp(System.currentTimeMillis()));
            statusService.insertStatus(status);

            judgeThreadProxy.setStatusService(statusService);
            judgeThreadProxy.setStatus(status);
            judgeThreadProxy.run();
        }
        redirectAttributes.addAttribute("page", 1);
        redirectAttributes.addAttribute("userId", idParam.getUserId());
        redirectAttributes.addAttribute("problemId", idParam.getProblemId());
        return "redirect:/status/list_status";
    }

    private void setPagination(String type, String keyWord, Integer countPerPage, Integer pageNumber,
                               HttpServletRequest request) {
        if ((type != null && !"".equals(type.trim())) && (keyWord != null && !"".equals(type.trim()))) {
            request.setAttribute("search", true);
            request.setAttribute("type", type);
            request.setAttribute("keyWord", keyWord);
        }
        Integer tableCount = problemService.getCount(type, keyWord);
        Integer pageCount = (int) Math.ceil((double) tableCount / (double) countPerPage);
        request.setAttribute("pageCount",pageCount);
        request.setAttribute("pageNumber", pageNumber);
    }
}
