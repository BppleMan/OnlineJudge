package com.bppleman.service.impl;

import com.bppleman.dao.ProblemDataDao;
import com.bppleman.entity.ProblemData;
import com.bppleman.service.ProblemDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by BppleMan on 2017/11/21.
 */
@Service("dataService")
public class ProblemDataServiceImpl implements ProblemDataService {

    @Resource
    private ProblemDataDao problemDataDao;

    @Override
    public ProblemData getProblemDataByProblemId(Integer problemId) {
        return problemDataDao.getProblemDataByProblemId(problemId);
    }

    @Override
    public ProblemData getProblemDataByProblemIdAndShellName(Integer problemId, String shellName) {
        return problemDataDao.getProblemDataByProblemIdAndShellName(problemId, shellName);
    }

    @Override
    public boolean insertProblemData(ProblemData problemData) {
        boolean result = true;
        if (problemDataDao.insertProblemData(problemData) != 1)
            result = false;
        return result;
    }

    @Override
    public boolean updateProblemData(ProblemData problemData) {
        boolean result = true;
        ProblemData pd = getProblemDataByProblemId(problemData.getProblemId());
        if (pd == null) {
            if (problemDataDao.insertProblemData(problemData) != 1)
                result = false;
        } else {
            if (problemDataDao.updateProblemData(problemData) != 1)
                result = false;
        }
        return result;
    }
}
