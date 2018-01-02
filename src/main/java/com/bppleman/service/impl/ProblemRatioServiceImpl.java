package com.bppleman.service.impl;

import com.bppleman.dao.ProblemRatioDao;
import com.bppleman.entity.IDParam;
import com.bppleman.entity.ProblemRatio;
import com.bppleman.service.ProblemRatioService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("problemRatioService")
public class ProblemRatioServiceImpl implements ProblemRatioService {

    @Resource
    private ProblemRatioDao problemRatioDao = null;

    public List<ProblemRatio> getProblemRatio() {
        return problemRatioDao.getProblemRatio();
    }

    @Override
    public Map<Integer, ProblemRatio> getProblemRatioMap(IDParam param) {
        Map<Integer, ProblemRatio> problemRatioMap = new HashMap<>();
        List<ProblemRatio>  problemRatios = getProblemRatio();
        for (ProblemRatio pr : problemRatios) {
            problemRatioMap.put(pr.getProblemId(), pr);
        }
        return problemRatioMap;
    }

    @Override
    public ProblemRatio getProblemRatioByProblemId(IDParam param) {
        return problemRatioDao.getProblemRatioByProblemId(param.getProblemId());
    }

    @Override
    public boolean insertProblemRatio(ProblemRatio problemRatio) {
        boolean result = true;
        if (problemRatioDao.insertProblemRatio(problemRatio) != 1)
            result = false;
        return result;
    }

    @Override
    public boolean updateProblemRatio(ProblemRatio problemRatio) {
        boolean result = true;
        if (problemRatioDao.updateProblemRatio(problemRatio) == 0)
            result = false;
        return result;
    }

    @Override
    public boolean deleteProblemRatio(IDParam param) {
        boolean result = true;
        if (problemRatioDao.deleteProblemRatio(param.getProblemId()) != 1)
            result = false;
        return result;
    }
}
