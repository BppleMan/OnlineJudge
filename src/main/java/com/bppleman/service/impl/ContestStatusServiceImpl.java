package com.bppleman.service.impl;

import com.bppleman.dao.StatusDao;
import com.bppleman.entity.IDParam;
import com.bppleman.entity.ProblemRatio;
import com.bppleman.entity.Status;
import com.bppleman.entity.UserSolve;
import com.bppleman.service.*;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("contestStatusService")
public class ContestStatusServiceImpl implements StatusService {

    @Resource
    private StatusDao statusDao;

    @Resource
    private CodeService codeService;

    @Resource
    private ProblemRatioService contestProblemRatioService;

    @Resource
    private ContestProblemUserSolveService contestProblemUserSolveService;

    @Override
    public List<Status> getStatus(IDParam param) {
        return statusDao.getStatus(param);
    }

    @Override
    public List<Status> getStatusByIDs(List<Integer> ids) {
        return statusDao.getStatusByIDs(ids);
    }

    @Override
    public Integer getProblemXXTimesByUserId(IDParam param, String statusValue) {
        return null;
    }

    @Override
    public Status getTheLastInsertStatus() {
        return statusDao.getTheLastInsertStatus();
    }

    @Override
    public boolean insertStatus(Status status) {
        boolean result = true;
        status.getCode().setLength(status.getCode().getCodeValue().length());
        status.getCode().setIdParam(status.getIdParam());
        result = codeService.insertCode(status.getCode());
        if (result == true) {
            status.setCodeId(status.getCode().getId());
            if (statusDao.insertStatus(status) != 1)
                result = false;
        }
        if (result == true) {
            Logger.getRootLogger().setLevel(Level.DEBUG);
            ProblemRatio problemRatio = contestProblemRatioService.getProblemRatioByProblemId(status.getIdParam());
            if (problemRatio == null) {
                problemRatio = new ProblemRatio(status.getIdParam());
                problemRatio.setSubmitTime(1);
                problemRatio.setAcTime(0);
                problemRatio.setRatioValue(0.0);
                result = contestProblemRatioService.insertProblemRatio(problemRatio);
            } else {
                problemRatio.setSubmitTime(problemRatio.getSubmitTime() + 1);
                problemRatio.setRatioValue((double) problemRatio.getAcTime() / (double) problemRatio.getSubmitTime() * 100);
                result = contestProblemRatioService.updateProblemRatio(problemRatio);
            }
            Logger.getRootLogger().setLevel(Level.ERROR);
        }
        if (result == true) {
            UserSolve userSolve  = new UserSolve(status.getIdParam());
            if (status.getStatusValue().equals(Status.Accepted))
                userSolve.setSolveState(UserSolve.SOLVED);
            else userSolve.setSolveState(UserSolve.UN_SOLVED);
            result = contestProblemUserSolveService.insertUserSolve(userSolve);
        }
        return result;
    }

    @Override
    public boolean updateStatus(Status status) {
        boolean result = true;
        if (statusDao.updateStatus(status) != 1)
            result = false;
        if (result == true) {
            ProblemRatio problemRatio = contestProblemRatioService.getProblemRatioByProblemId(status.getIdParam());
            if (status.getStatusValue().equals(Status.Accepted))
                problemRatio.setAcTime(problemRatio.getAcTime() + 1);
            problemRatio.setRatioValue((double)problemRatio.getAcTime() / (double)problemRatio.getSubmitTime() * 100);
            result = contestProblemRatioService.updateProblemRatio(problemRatio);
        }
        if (result == true) {
            UserSolve userSolve  = contestProblemUserSolveService.getUserSolveByParam(status.getIdParam());
            if (status.getStatusValue().equals(Status.Accepted))
                userSolve.setSolveState(UserSolve.SOLVED);
            else userSolve.setSolveState(UserSolve.UN_SOLVED);
            result = contestProblemUserSolveService.updateUserSolve(userSolve);
        }
        return result;
    }
}
