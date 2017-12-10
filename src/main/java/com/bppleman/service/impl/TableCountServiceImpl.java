package com.bppleman.service.impl;

import com.bppleman.dao.TableCountDao;
import com.bppleman.service.TableCountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("tableCountService")
public class TableCountServiceImpl implements TableCountService {

    @Resource
    private TableCountDao tableCountDao = null;

    @Override
    public Integer getTableCount(String tableName) {
        return tableCountDao.getTableCount(tableName);
    }

    @Override
    public int updateTableCount(String tableName, int count, String option) {
        return tableCountDao.updateTableCount(tableName, count, option);
    }
}
