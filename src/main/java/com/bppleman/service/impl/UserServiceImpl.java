package com.bppleman.service.impl;

import com.bppleman.controller.UserController;
import com.bppleman.dao.UserDao;
import com.bppleman.entity.User;
import com.bppleman.service.UserService;
import com.sun.istack.internal.NotNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BppleMan on 2017/11/9.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao = null;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String registerUser(User user) {
        User temp = null;
        temp = userDao.getUserByUsername(user.getUsername());
        if (temp == null) {
            if (userDao.insertUser(user) == 1)
                return UserController.REGISTER_SUCCESS;
            else return UserController.REGISTER_ERROR;
        }
        return UserController.REGISTER_EXIST;
    }

    @Override
    public User loginUser(User user, @NotNull StringBuffer loginInfo) {
        User loginUser = null;
        loginUser = userDao.getUserByUsername(user.getUsername());
        if (loginUser == null) {
            loginInfo.append(UserController.LOGIN_USERNAME_UN_EXIST);
            return null;
        } else if (!loginUser.getPassword().equals(user.getPassword())) {
            loginInfo.append(UserController.LOGIN_PASSWORD_ERROR);
            return null;
        }
        loginInfo.append(UserController.LOGIN_SUCCESS);
        return loginUser;
    }

    @Override
    public User getUserByName(String username) {
        return userDao.getUserByUsername(username);
    }

    @Override
    public Map<Long, String> getIDUsernameMapByIDs(List<Integer> ids) {
        List<Map<Object, Object>> userMaps = userDao.getUsernameByIDs(ids);
        Map<Long, String> resultMap = new HashMap<>();
        for (Map<Object, Object> map: userMaps) {
            resultMap.put((Long) map.get("id"), (String)map.get("username"));
        }
        return resultMap;
    }
}
