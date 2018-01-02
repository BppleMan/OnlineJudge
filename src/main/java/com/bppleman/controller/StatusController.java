package com.bppleman.controller;

import com.bppleman.entity.IDParam;
import com.bppleman.entity.Status;
import com.bppleman.service.ContestService;
import com.bppleman.service.ProblemService;
import com.bppleman.service.StatusService;
import com.bppleman.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by BppleMan on 2017/11/20.
 */
@Controller
@RequestMapping("/status")
public class StatusController {

    private String prefix = "/status";
    private String listStatus = "/list_status";

    private double statusCountPerPage = 20.0;

    @Resource
    private StatusService statusService = null;

    @Resource
    private ContestService contestService = null;

    @Resource
    private UserService userService = null;

    @Resource
    private ProblemService problemService = null;

    @RequestMapping("/list_status")
    public String listStatus(Integer page, IDParam idParam, HttpServletRequest request) {
        List<Status> statuses = statusService.getStatus(idParam);
        setPagination(statuses, idParam, page, request);
        return prefix + listStatus;
    }

    @RequestMapping("/list_contest_status")
    public String listContestStatus(Integer page, IDParam idParam, HttpServletRequest request) {
        List<Status> statuses = statusService.getStatus(idParam);
        setPagination(statuses, idParam, page, request);
        request.setAttribute("isContestStatus", true);
        return prefix + listStatus;
    }

    public void setPagination(List<Status> statuses, IDParam idParam, Integer pageNumber, HttpServletRequest request) {
        request.setAttribute("statuses", statuses);
        Integer pageCount = (int) Math.ceil((double) statuses.size() / statusCountPerPage);
        request.setAttribute("pageCount", pageCount);
        request.setAttribute("pageNumber", pageNumber);
        request.setAttribute("statusCountPerPage", statusCountPerPage);
        request.setAttribute("idParam", idParam);
    }

    @RequestMapping("/getUsername")
    @ResponseBody
    public Map<Long, String> getUsername(@RequestBody List<Integer> userIds) {
        Map<Long, String> idNameMap = userService.getIDUsernameMapByIDs(userIds);
        return idNameMap;
    }

    @RequestMapping("/getProblemTitle")
    @ResponseBody
    public Map<Long, String> getProblemTitle(@RequestBody List<Integer> problemIds) {
        Map<Long, String> idNameMap = problemService.getProblemIdToTitleMapByProblemIds(problemIds);
        return idNameMap;
    }
}
