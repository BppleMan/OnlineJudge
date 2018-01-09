package com.bppleman.service;

import com.bppleman.dao.ProblemDao;
import com.bppleman.entity.Problem;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by BppleMan on 2017/11/8.
 */
@Repository
public interface ProblemService {
    List<Problem> getAllProblems();
    List<Problem> getProblemsWithPage(String type, String keyWord, Integer page, Integer length);
    Integer getCount(String type, String keyWord);
    Problem getProblemByProblemId(Integer problemId);
    Map<Long, String> getProblemIdToTitleMapByProblemIds(List<Integer> ids);
    boolean insertProblem(Problem problem, String[] labels);
    boolean updateProblem(Problem newProblem, String[] labels);
    boolean deleteProblemByProblemId(Integer problemId);
}
