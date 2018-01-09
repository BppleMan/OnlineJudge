package com.bppleman.controller.bg;

import com.bppleman.controller.ContestController;
import com.bppleman.entity.Admin;
import com.bppleman.entity.Contest;
import com.bppleman.service.ContestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bg/contest")
public class BGContestController {
    private String prefix = "/bg/contest";
    private String listContest = "/list_contest";

    @Resource
    private ContestService contestService;

    @Resource
    private  ContestController contestController;

    @RequestMapping("/list_contest")
    public String listContestWithPageNumber(@RequestParam(value = "tp", required = false) String type,
                                            @RequestParam(value = "kw", required = false) String keyWord,
                                            @RequestParam(value = "page", required = false) Integer pageNumber,
                                            HttpServletRequest request, HttpSession session) {
        if (pageNumber == null)
            pageNumber = 1;
        contestController.listContestWithPageNumber(type, keyWord, pageNumber, 25, request);
        Integer pageCount = (Integer) request.getAttribute("pageCount");
        Integer begin = 1, end = 1;
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
        return prefix + listContest;
    }

    @RequestMapping("/create_contest")
    public String createContest() {
        return "redirect:" + prefix + editContestDate;
    }

    private String editContestDate = "/edit_contest/date";
    private String editContestContent = "/edit_contest/content";

    @RequestMapping("/edit_contest")
    public String editContest(@RequestParam(required = false) Integer contestId,
                              HttpServletRequest request,
                              RedirectAttributes redirectAttributes) {
        if (contestId == null) {
            return "redirect:" + prefix + listContest;
        } else {
            redirectAttributes.addAttribute("contestId", contestId);
            return "redirect:" + prefix + editContestDate;
        }
    }

    @RequestMapping("/edit_contest/date")
    public String editContestDate(Integer contestId, HttpServletRequest request) {
        if (contestId != null) {
            Contest contest = contestService.getContestById(contestId);
            request.setAttribute("contest", contest);
        }
        Map<String, String> typesMap = new HashMap<>();
        typesMap.put(Contest.Type.PASSWORD, "需要密码");
        typesMap.put(Contest.Type.PUBLIC, "公开");
        typesMap.put(Contest.Type.CLASS, "只允许本班学生");
        request.setAttribute("typesMap", typesMap);
        return prefix + editContestDate;
    }

    @RequestMapping("/edit_contest/date/submit")
    public String editContestDateSubmit(Contest contest, Integer contestId,
                                        @RequestParam(required = false) Boolean isContinue,
                                        HttpServletRequest request, HttpSession session,
                                        RedirectAttributes redirectAttributes) {
        if (contestId != null) {
            contestService.updateContest(contest, contestId);
        } else {
            Admin admin = (Admin) session.getAttribute("admin");
            if (admin != null) {
                contest.setUserId(admin.getId());
                contest.setUsername(admin.getAdminName());
                contestService.insertContest(contest);
                contestId = contest.getId();
            }
        }
        if (isContinue != null && isContinue) {
            redirectAttributes.addAttribute("contestId", contestId);
            return "redirect:" + prefix + editContestContent;
        }
        return "redirect:" + prefix +  listContest;
    }

    @RequestMapping("/edit_contest/content")
    public String editContestContent(Integer contestId,
                                     @RequestParam(value = "rt", required = false) Integer requestTime,
                                     @RequestParam(value = "lt", required = false) String labelType,
                                     @RequestParam(value = "lv", required = false) String labelValue,
                                     @RequestParam(value = "cpage", required = false) Integer collapsePage,
                                     HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes) {
        contestController.edit_contest_problem(contestId, requestTime, labelType, labelValue, collapsePage, request,  session, redirectAttributes);
        return prefix + editContestContent;
    }

    @RequestMapping("/edit_contest/content/submit")
    public String getEditContestContent(@RequestParam(required = false) List<Integer> selected,
                                        Integer contestId, String token,
                                        HttpSession session, HttpServletRequest request) {
        contestController.submit_contest_problem(selected, contestId, token, session);
        return "redirect:" + prefix + listContest;
    }

    @RequestMapping("/delete_contest")
    public String deleteContest(Integer contestId) {
        contestService.deleteContest(contestId);
        return "redirect:" + prefix + listContest;
    }

    @RequestMapping("/clone_contest")
    public String cloneContest(Integer contestId,
                               HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes) {
        Contest contest = contestService.getContestById(contestId);
        try {
            Admin admin = (Admin) session.getAttribute("admin");
            Contest clone = contestService.cloneContest(contest, admin);
            redirectAttributes.addAttribute("contestId", clone.getId());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return "redirect:" + prefix + editContestDate;
    }
}
