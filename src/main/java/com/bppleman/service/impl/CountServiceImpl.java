package com.bppleman.service.impl;

import com.bppleman.dao.CountDao;
import com.bppleman.service.CountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("countService")
public class CountServiceImpl implements CountService {

    @Resource
    private CountDao countDao;

    @Override
    public Integer getCount(String OPTION, String tableName, String columnName, String value) {
        Map<String, String> args = new HashMap<>();
        args.put(columnName, value);
        if (OPTION == null)
            return countDao.getCountByEqual(tableName, args);
        else if (OPTION.equals(EQUAL))
            return countDao.getCountByEqual(tableName, args);
        else if (OPTION.equals(LIKE))
            return countDao.getCountByLike(tableName, args);
        else
            return null;
    }

    @Override
    public Integer getCount(String OPTION, String tableName, Map<String, String> columnValueMap) {
        if (OPTION == null)
            return countDao.getCountByEqual(tableName, columnValueMap);
        else if (OPTION.equals(EQUAL))
            return countDao.getCountByEqual(tableName, columnValueMap);
        else if (OPTION.equals(LIKE))
            return countDao.getCountByLike(tableName, columnValueMap);
        else
            return null;
    }
}
