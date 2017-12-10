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
    List<Problem> getProblemsWithPage(String type, String keyWord, int page, int length);
    int getCount(String type, String keyWord);
    Problem getProblemByID(int problemId);
    Map<Long, String> getIDTitleMapByIDs(List<Integer> ids);
    boolean insertProblem(Problem problem);
    boolean deleteProblemByID(int problemId);
}
