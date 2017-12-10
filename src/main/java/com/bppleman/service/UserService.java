package com.bppleman.service;

import com.bppleman.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by BppleMan on 2017/11/9.
 */
@Repository
public interface UserService {
    String registerUser(User user);
    User loginUser(User user, StringBuffer loginInfo);
    User getUserByName(String username);
    Map<Long, String> getIDUsernameMapByIDs(List<Integer> ids);
}
