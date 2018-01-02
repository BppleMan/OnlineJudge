package com.bppleman.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CountService {
    String EQUAL = "EQUAL";
    String LIKE = "LIKE";
    Integer getCount(String OPTION, String tableName, String columnName, String value);
    Integer getCount(String OPTION, String tableName, Map<String, String> columnValueMap);
}
