package com.bppleman.dao;

import com.bppleman.entity.IDParam;
import com.bppleman.entity.UserSolve;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContestProblemUserSolveDao {
    List<UserSolve> getUserSolve(Integer userId, Integer contestId);
    UserSolve getUserSolveByParam(Integer userId, Integer problemId, Integer contestId);
    int insertUserSolve(UserSolve userSolve);
    int updateUserSolve(UserSolve userSolve);
    int deleteUserSolve(Integer userId, Integer problemId, Integer contestId);
}
