package com.bppleman.service.impl;

import com.bppleman.dao.ContestProblemRatioDao;
import com.bppleman.entity.IDParam;
import com.bppleman.entity.ProblemRatio;
import com.bppleman.service.ProblemRatioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("contestProblemRatioService")
public class ContestProblemRatioServiceImpl implements ProblemRatioService {

    @Resource
    private ContestProblemRatioDao contestProblemRatioDao;

    @Override
    public Map<Integer, ProblemRatio> getProblemRatioMap(IDParam param) {
        Map<Integer, ProblemRatio> contestProblemRatiosMap = new HashMap<>();
        List<ProblemRatio> contestProblemRatios = contestProblemRatioDao.getContestProblemRatios(param.getContestId());
        for (ProblemRatio contestProblemRatio : contestProblemRatios) {
            contestProblemRatiosMap.put(contestProblemRatio.getProblemId(), contestProblemRatio);
        }
        return contestProblemRatiosMap;
    }

    @Override
    public ProblemRatio getProblemRatioByProblemId(IDParam param) {
        return contestProblemRatioDao.getContestProblemRatioByProblemId(param.getContestId(), param.getProblemId());
    }

    @Override
    @Transactional
    public boolean insertProblemRatio(ProblemRatio problemRatio) {
        boolean result = true;
        if (contestProblemRatioDao.insertContestProblemRatio(problemRatio) != 1)
            result = false;
        return result;
    }

    @Override
    @Transactional
    public boolean updateProblemRatio(ProblemRatio problemRatio) {
        boolean result = true;
        if (contestProblemRatioDao.updateContestProblemRatio(problemRatio) != 1)
            result = false;
        return result;
    }

    @Override
    @Transactional
    public boolean deleteProblemRatio(IDParam param) {
        boolean result = true;
        if (contestProblemRatioDao.deleteContestProblemRatio(param.getContestId(), param.getProblemId()) != 1) {
            result = false;
        }
        return result;
    }
}
