package com.bppleman.dao;

import com.bppleman.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by BppleMan on 2017/11/8.
 */
@Repository
public interface UserDao {
    User getUserByParam(String columnName, String value);
    Integer insertUser(User user);
    Integer updateUser(User user);
    List<Map<Object, Object>> getUsernameByIDs(List<Integer> ids);
}
