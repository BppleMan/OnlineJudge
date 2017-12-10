package com.bppleman.affair.submit;

import com.bppleman.delegate.CodeAffairDelegate;
import com.bppleman.entity.ProblemRatio;
import com.bppleman.entity.Status;
import com.bppleman.entity.UserSolve;
import com.bppleman.service.ProblemRatioService;
import com.bppleman.service.StatusService;
import com.bppleman.service.UserSolveService;

import javax.annotation.Resource;
import java.util.List;

public class CodeAffair implements CodeAffairDelegate {

    @Resource
    private StatusService statusService = null;

    @Resource
    private ProblemRatioService problemRatioService = null;

    @Resource
    private UserSolveService userSolveService = null;

    public void run () {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        thread.start();
    }

    @Override
    public void shouldUpdateProblemRatio(Status status) {
        List<ProblemRatio> problemRatios = problemRatioService.getProblemRatio(status.getIdParam());
        if (problemRatios.size() > 1) {
            try {
                throw new Exception(String.format("此处应取到的ProblemRatio数量为1，但是取到了%d个", problemRatios.size()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ProblemRatio problemRatio = problemRatios.get(0);
        problemRatio.setSubmitTime(problemRatio.getSubmitTime() + 1);
        if (status.getStatusValue().equals(Status.Accepted))
            problemRatio.setAcTime(problemRatio.getAcTime() + 1);
        problemRatio.setRatioValue((double)problemRatio.getAcTime() / (double)problemRatio.getSubmitTime() * 100);
        problemRatioService.updateProblemRatio(problemRatio);
    }

    @Override
    public void shouldUpdateStatus(Status status) {
        statusService.updateStatus(status);
    }

    @Override
    public void shouldUpdateUserSolve(Status status) {
        UserSolve userSolve  = new UserSolve(status.getIdParam());
        if (status.getStatusValue().equals(Status.Accepted))
            userSolve.setSolveState(UserSolve.SOLVED);
        else userSolve.setSolveState(UserSolve.UN_SOLVED);
        userSolveService.insertUserSolve(userSolve);
    }

    @Override
    public void shouldUpdateRank(Status status) {

    }
}
