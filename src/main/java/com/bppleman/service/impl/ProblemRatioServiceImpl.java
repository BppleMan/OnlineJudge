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

    @Override
    public List<ProblemRatio> getProblemRatio(IDParam idParam) {
        return problemRatioDao.getProblemRatio(idParam);
    }

    @Override
    public boolean insertProblemRatio(ProblemRatio problemRatio) {
        if (problemRatioDao.insertProblemRatio(problemRatio) == 1)
            return true;
        return false;
    }

    @Override
    public boolean updateProblemRatio(ProblemRatio problemRatio) {
        if (problemRatioDao.updateProblemRatio(problemRatio) > 0)
            return true;
        return false;
    }

    @Override
    public boolean deleteProblemRatio(List<IDParam> idParams) {
        if (problemRatioDao.deleteProblemRatio(idParams) == 1)
            return true;
        return false;
    }

    @Override
    public Map<Integer, ProblemRatio> getProblemRatioMap(IDParam idParam) {
        Map<Integer, ProblemRatio> problemRatioMap = new HashMap<>();
        List<ProblemRatio>  problemRatios = getProblemRatio(idParam);
        for (ProblemRatio pr : problemRatios) {
            problemRatioMap.put(pr.getIdParam().getProblemId(), pr);
        }
        return problemRatioMap;
    }
}
