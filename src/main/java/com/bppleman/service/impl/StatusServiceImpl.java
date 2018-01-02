package com.bppleman.service.impl;

import com.bppleman.dao.CodeDao;
import com.bppleman.dao.StatusDao;
import com.bppleman.entity.IDParam;
import com.bppleman.entity.ProblemRatio;
import com.bppleman.entity.Status;
import com.bppleman.entity.UserSolve;
import com.bppleman.service.CodeService;
import com.bppleman.service.ProblemRatioService;
import com.bppleman.service.ProblemUserSolveService;
import com.bppleman.service.StatusService;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by BppleMan on 2017/11/20.
 */
@Service("statusService")
public class StatusServiceImpl implements StatusService {

    @Resource
    private StatusDao statusDao;

    @Resource
    private CodeService codeService;

    @Resource
    private ProblemRatioService problemRatioService;

    @Resource
    private ProblemUserSolveService problemUserSolveService;

    @Override
    public List<Status> getStatus(IDParam idParam) {
        return statusDao.getStatus(idParam);
    }

    @Override
    public List<Status> getStatusByIDs(List<Integer> ids) {
        return statusDao.getStatusByIDs(ids);
    }

    @Override
    public Status getTheLastInsertStatus() {
        return statusDao.getTheLastInsertStatus();
    }

    @Override
    public Integer getProblemXXTimesByUserId(IDParam param, String statusValue) {
        return statusDao.getProblemXXTimesByUserId(param, statusValue);
    }

    @Override
    @Transactional
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
            ProblemRatio problemRatio = problemRatioService.getProblemRatioByProblemId(status.getIdParam());
            if (problemRatio == null) {
                problemRatio = new ProblemRatio(status.getIdParam());
                problemRatio.setSubmitTime(1);
                problemRatio.setAcTime(0);
                problemRatio.setRatioValue(0.0);
                result = problemRatioService.insertProblemRatio(problemRatio);
            } else {
                problemRatio.setSubmitTime(problemRatio.getSubmitTime() + 1);
                problemRatio.setRatioValue((double) problemRatio.getAcTime() / (double) problemRatio.getSubmitTime() * 100);
                result = problemRatioService.updateProblemRatio(problemRatio);
            }
            Logger.getRootLogger().setLevel(Level.ERROR);
        }
        if (result == true) {
            UserSolve userSolve  = new UserSolve(status.getIdParam());
            if (status.getStatusValue().equals(Status.Accepted))
                userSolve.setSolveState(UserSolve.SOLVED);
            else userSolve.setSolveState(UserSolve.UN_SOLVED);
            result = problemUserSolveService.insertUserSolve(userSolve);
        }
        return result;
    }

    @Override
    @Transactional
    public boolean updateStatus(Status status) {
        boolean result = true;
        if (statusDao.updateStatus(status) != 1)
            result = false;
        if (result == true) {
            ProblemRatio problemRatio = problemRatioService.getProblemRatioByProblemId(status.getIdParam());
            if (status.getStatusValue().equals(Status.Accepted))
                problemRatio.setAcTime(problemRatio.getAcTime() + 1);
            problemRatio.setRatioValue((double)problemRatio.getAcTime() / (double)problemRatio.getSubmitTime() * 100);
            result = problemRatioService.updateProblemRatio(problemRatio);
        }
        if (result == true) {
            UserSolve userSolve  = problemUserSolveService.getUserSolveByParam(status.getIdParam().getUserId(), status.getIdParam().getProblemId());
            if (status.getStatusValue().equals(Status.Accepted))
                userSolve.setSolveState(UserSolve.SOLVED);
            else userSolve.setSolveState(UserSolve.UN_SOLVED);
            result = problemUserSolveService.updateUserSolve(userSolve);
        }
        return result;
    }
}
