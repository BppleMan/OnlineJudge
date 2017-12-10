package com.bppleman.service;

import org.springframework.stereotype.Repository;

@Repository
public interface TableCountService {
    Integer getTableCount(String tableName);
    int updateTableCount(String tableName, int count, String option);
}
