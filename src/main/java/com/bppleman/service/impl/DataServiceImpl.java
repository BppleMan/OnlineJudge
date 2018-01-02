package com.bppleman.service.impl;

import com.bppleman.dao.DataDao;
import com.bppleman.entity.Data;
import com.bppleman.service.DataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by BppleMan on 2017/11/21.
 */
@Service("dataService")
public class DataServiceImpl implements DataService {

    @Resource
    private DataDao dataDao;

    @Override
    public Data getDataByProblemIDAndShellName(Integer problemId, String shellName) {
        return dataDao.getDataByProblemIDAndShellName(problemId, shellName);
    }
}
