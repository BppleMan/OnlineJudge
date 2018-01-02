package com.bppleman.dao;

import com.bppleman.entity.IDParam;
import com.bppleman.entity.Problem;
import com.bppleman.entity.UserSolve;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProblemUserSolveDao {
    List<UserSolve> getUserSolve(Integer userId);
    UserSolve getUserSolveByParam(Integer userId, Integer problemId);
    List<Integer> getUserSubmittedProblemIds(Integer userId);
    List<Problem> getProblemByUserSolveInfo(String userSolveValue, Integer offset, Integer length, Integer userId);
    int insertUserSolve(UserSolve userSolve);
    int updateUserSolve(UserSolve userSolve);
    int deleteUserSolve(Integer userId, Integer problemId);
}
