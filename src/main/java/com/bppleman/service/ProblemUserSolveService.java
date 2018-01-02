package com.bppleman.service;

import com.bppleman.entity.Problem;
import com.bppleman.entity.UserSolve;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProblemUserSolveService {
    Map<Integer, String> getUserSolveMap(Integer userId);
    UserSolve getUserSolveByParam(Integer userId, Integer problemId);
    List<Problem> getProblemByUserSolved(Integer page, Integer length, Integer userId);
    List<Problem> getProblemByUserUnSolved(Integer page, Integer length, Integer userId);
    Map<String, List<Problem>> getUserSolveProblemsMap(Map<String, Integer> userSolveCurrentPageMap, Integer length, Integer userId);
    Integer getUserSolvedTotalPage(Integer userId, Double countPerPage);
    Integer getUserUnSolvedTotalPage(Integer userId, Double countPerPage);
    Map<String, Integer> getUserSolveTotalPageMap(Integer userId, Double countPerPage);
    List<Integer> getUserSubmittedProblemIds(Integer userId);
    boolean insertUserSolve(UserSolve userSolve);
    boolean updateUserSolve(UserSolve userSolve);
    boolean deleteUserSolve(Integer userId, Integer problemId);
}
