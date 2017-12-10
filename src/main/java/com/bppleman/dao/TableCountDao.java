package com.bppleman.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface TableCountDao {
    Integer getTableCount(String tableName);
    int updateTableCount(String tableName, int count, String option);
}
