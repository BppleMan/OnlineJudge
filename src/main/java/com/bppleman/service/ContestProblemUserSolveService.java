package com.bppleman.service;

import com.bppleman.entity.IDParam;
import com.bppleman.entity.UserSolve;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface ContestProblemUserSolveService {
    /**
     *
     * @param param param只能包括userId、contestId
     * @return
     */
    Map<Integer, String> getUserSolveMap(IDParam param);

    /**
     *
     * @param param param只能包括userId、problemId、contestId
     * @return
     */
    UserSolve getUserSolveByParam(IDParam param);
    boolean insertUserSolve(UserSolve userSolve);
    boolean updateUserSolve(UserSolve userSolve);

    /**
     *
     * @param param param只能包括userId、problemId、contestId
     * @return
     */
    boolean deleteUserSolve(IDParam param);
}
