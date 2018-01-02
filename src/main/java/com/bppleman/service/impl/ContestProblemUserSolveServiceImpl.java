package com.bppleman.service.impl;

import com.bppleman.dao.ContestProblemUserSolveDao;
import com.bppleman.dao.ProblemUserSolveDao;
import com.bppleman.entity.IDParam;
import com.bppleman.entity.UserSolve;
import com.bppleman.service.ContestProblemUserSolveService;
import com.bppleman.service.ProblemUserSolveService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("contestProblemUserSolveService")
public class ContestProblemUserSolveServiceImpl implements ContestProblemUserSolveService {

    @Resource
    private ContestProblemUserSolveDao contestProblemUserSolveDao;

    public List<UserSolve> getUserSolve(IDParam param) {
        return contestProblemUserSolveDao.getUserSolve(param.getUserId(), param.getContestId());
    }

    @Override
    public Map<Integer, String> getUserSolveMap(IDParam idParam) {
        List<UserSolve> userSolves = getUserSolve(idParam);
        Map<Integer, String> result = new HashMap<>();
        for (UserSolve us: userSolves) {
            result.put(us.getIdParam().getProblemId(), us.getSolveState());
        }
        return result;
    }

    @Override
    public UserSolve getUserSolveByParam(IDParam param) {
        return contestProblemUserSolveDao.getUserSolveByParam(param.getUserId(), param.getProblemId(), param.getContestId());
    }

    @Override
    @Transactional
    public boolean insertUserSolve(UserSolve userSolve) {
        boolean result = true;
        if (contestProblemUserSolveDao.insertUserSolve(userSolve) != 1)
            result = false;
        return result;
    }

    @Override
    @Transactional
    public boolean updateUserSolve(UserSolve userSolve) {
        boolean result = true;
        if (contestProblemUserSolveDao.updateUserSolve(userSolve) != 1)
            result = false;
        return result;
    }

    @Override
    @Transactional
    public boolean deleteUserSolve(IDParam param) {
        boolean result = true;
        if (contestProblemUserSolveDao.deleteUserSolve(param.getUserId(), param.getProblemId(), param.getContestId()) == 0)
            result = false;
        return result;
    }
}
