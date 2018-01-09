package com.bppleman.controller.bg;

import com.bppleman.controller.ProblemController;
import com.bppleman.entity.Label;
import com.bppleman.entity.Problem;
import com.bppleman.entity.ProblemData;
import com.bppleman.entity.ProblemLabel;
import com.bppleman.service.LabelService;
import com.bppleman.service.ProblemDataService;
import com.bppleman.service.ProblemService;
import com.bppleman.tool.TokenTool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/bg/problem")
public class BGProblemController {
    private String prefix = "/bg/problem";
    private String listProblem = "/list_problem";
    private String editProblem = "/edit_problem";

    @Resource
    private ProblemService problemService;

    @Resource
    private LabelService labelService;

    @Resource
    private ProblemController problemController;

    @RequestMapping("/list_problem")
    public String listProblem(@RequestParam(value = "tp", required = false) String type,
                              @RequestParam(value = "kw", required = false) String keyWord,
                              @RequestParam(value = "page", required = false) Integer pageNumber,
                              HttpServletRequest request, HttpSession session) {
        if (pageNumber == null)
            pageNumber = 1;
        problemController.listProblemWithPageNumber(type, keyWord, pageNumber,25, request, session);
        Integer pageCount = (Integer) request.getAttribute("pageCount");
        Integer begin = null, end = null;
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
        request.setAttribute("begin", begin);
        request.setAttribute("end", end);
        return prefix + listProblem;
    }

    @RequestMapping("/create_problem")
    public String createProblem(HttpServletRequest request, HttpSession session) {
        List<Label> labels = labelService.getLabels();
        request.setAttribute("labels", labels);
        request.setAttribute("createProblem", true);
        session.setAttribute("createProblemToken", TokenTool.getInstance().makeToken());
        return prefix + editProblem;
    }

    @RequestMapping("/create_problem/submit")
    public String createProblemSubmit(Problem problem, String[] labels, String token,
                                    HttpSession session, HttpServletRequest request,
                                    RedirectAttributes redirectAttributes) {
        String createProblemToken = (String) session.getAttribute("createProblemToken");
        if (createProblemToken != null && createProblemToken.equals(token)) {
            session.removeAttribute("createProblemToken");
            problemService.insertProblem(problem, labels);
        }
        redirectAttributes.addAttribute("page", 1);
        return "redirect:" + prefix + listProblem;
    }

    @RequestMapping("/edit_problem")
    public String editProblem(@RequestParam(required = false) Integer problemId,
                              HttpServletRequest request, HttpSession session) {
        if (problemId != null) {
            Problem problem = problemService.getProblemByProblemId(problemId);
            request.setAttribute("problem", problem);
        }
        List<Label> labels = labelService.getLabels();
        String editProblemToken = TokenTool.getInstance().makeToken();
        session.setAttribute("editProblemToken", editProblemToken);
        request.setAttribute("editProblem", true);
        request.setAttribute("labels", labels);
        return prefix + editProblem;
    }

    @RequestMapping("/edit_problem/submit")
    public String editProblemSubmit(Problem problem, String[] labels, String token,
                                    HttpSession session, HttpServletRequest request,
                                    RedirectAttributes redirectAttributes) {
        String editProblemToken = (String) session.getAttribute("editProblemToken");
        if (editProblemToken != null && editProblemToken.equals(token)) {
            session.removeAttribute("editProblemToken");
            problemService.updateProblem(problem, labels);
        }
        redirectAttributes.addAttribute("page", 1);
        return "redirect:" + prefix + listProblem;
    }


    @RequestMapping("/getProblem")
    @ResponseBody
    public Problem getProblem(Integer problemId) {
        Problem problem = problemService.getProblemByProblemId(problemId);
        return problem;
    }

    private String editProblemData = "/edit_problem_data";

    @RequestMapping("/edit_problem_data")
    public String editProblemData(@RequestParam(required = false)Integer problemId,
                                  HttpServletRequest request, HttpSession session,
                                  RedirectAttributes redirectAttributes) {
        if (problemId == null) {
            redirectAttributes.addAttribute("page", 1);
            return "redirect:" + prefix + listProblem;
        }
        Problem problem = problemService.getProblemByProblemId(problemId);
        request.setAttribute("problem", problem);
        return prefix + editProblemData;
    }

    @RequestMapping("/edit_problem_data/submit")
    public String editProblemData(ProblemData problemData, HttpServletRequest request) {
        problemDataService.updateProblemData(problemData);
        return "redirect:" + prefix + editProblemData;
    }

    @Resource
    private ProblemDataService problemDataService;
    @RequestMapping("/getProblemData")
    @ResponseBody
    public ProblemData getProblemData(Integer problemId) {
        return problemDataService.getProblemDataByProblemId(problemId);
    }
}
