package com.bppleman.service.impl;

import com.bppleman.controller.ContestController;
import com.bppleman.dao.SmartProblemDao;
import com.bppleman.entity.Contest;
import com.bppleman.entity.Problem;
import com.bppleman.entity.SmartProblem;
import com.bppleman.service.ContestService;
import com.bppleman.service.SmartProblemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class SmartProblemServiceImpl implements SmartProblemService {

    @Resource
    private SmartProblemDao smartProblemDao;

    @Resource
    private ContestService contestService;

    @Override
    public List<Problem> getProblemsBySmartProblem(SmartProblem smartProblem, Integer userId) {
//        这是查询得到的problem集合
        List<Problem> queryResult = smartProblemDao.getProblemsBySmartProblem(smartProblem);
//        这是备选题目列表
        List<Problem> canSelectProblem = new ArrayList<>();
//        这是该User之前已经出过的problemId的集合
        List<Integer> alreadyExistProblemIds = getAlreadyExistProblemIds(userId);
//        这是最终problem集合
        List<Problem> smartProblems = new ArrayList<>();
//        需要先将之前出过的题剔除
        for (Problem problem : queryResult) {
            boolean selected = false;
            for (Integer alreadyExistProblemId : alreadyExistProblemIds) {
                if (problem.getId().equals(alreadyExistProblemId)) {
                    selected = true;
                    break;
                }
            }
            if (!selected) {
                canSelectProblem.add(problem);
            }
        }
//        随机选择n个problem加入到smartProblems中
//        需要判断随机数是否重复
        Random random = new Random();
        for (int i = 0; i < smartProblem.getProblemCount(); i++) {
            boolean contains = false;
            Problem problem = null;
            while (true) {
                problem = canSelectProblem.get(random.nextInt(canSelectProblem.size()));
                if (!smartProblems.contains(problem))
                    break;
            }
            smartProblems.add(problem);
        }
        return smartProblems;
    }

    List<Integer> getAlreadyExistProblemIds(Integer userId) {
        List<Integer> alreadyExistProblemIds = new ArrayList<>();
        List<Contest> contests = contestService.getContestsWithPage(ContestController.Type.USER_ID, String.valueOf(userId), null, null);
        for (Contest contest : contests) {
            alreadyExistProblemIds.addAll(contest.getProblemIds());
        }
        return alreadyExistProblemIds;
    }
}
