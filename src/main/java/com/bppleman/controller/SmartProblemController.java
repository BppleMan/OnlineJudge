package com.bppleman.controller;

import com.bppleman.entity.Problem;
import com.bppleman.entity.SmartProblem;
import com.bppleman.entity.User;
import com.bppleman.service.SmartProblemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/smartProblem")
public class SmartProblemController {

    @Resource
    private SmartProblemService smartProblemService;

//    @RequestMapping("/generateProblem")
//    @ResponseBody
//    public Map<String ,SmartProblem> generateProblem(@RequestBody List<SmartProblem> smartProblems) {
//        List<Problem> result = new ArrayList<>();
//        System.out.println(smartProblems);
//        Map<String, SmartProblem> smartProblemsMap = new HashMap<>();
//        for (SmartProblem smartProblem : smartProblems) {
//            smartProblemsMap.put(smartProblem.getTrId(), smartProblem);
//        }
//        return smartProblemsMap;
//    }

    @RequestMapping("/generateProblem")
    @ResponseBody
    public Map<String, List<Problem>> generateProblem(@RequestBody Map<String ,SmartProblem> smartProblemsMap,
                                                      HttpSession session) {
        User user = (User) session.getAttribute("user");
        Map<String, List<Problem>> generatedSmartProblemsMap = new HashMap<>();
        for (String key : smartProblemsMap.keySet()) {
            List<Problem> generatedSmartProblems = smartProblemService.getProblemsBySmartProblem(smartProblemsMap.get(key), user.getId());
            generatedSmartProblemsMap.put(key,generatedSmartProblems);
        }
        System.out.println(generatedSmartProblemsMap);
        return generatedSmartProblemsMap;
    }
}
