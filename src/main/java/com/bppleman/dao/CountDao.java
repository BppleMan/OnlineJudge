package com.bppleman.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CountDao {
    Integer getCountByEqual(@Param("tableName") String tableName, @Param("args") Map<String, String> args);
    Integer getCountByLike(@Param("tableName") String tableName, @Param("args") Map<String, String> args);
}
