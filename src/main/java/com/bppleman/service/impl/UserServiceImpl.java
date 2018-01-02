package com.bppleman.service.impl;

import com.bppleman.controller.UserController;
import com.bppleman.dao.UserDao;
import com.bppleman.entity.User;
import com.bppleman.service.UserService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
    public User getUserByTelephone(String telephone) {
        String columnName = "telephone";
        return userDao.getUserByParam(columnName, telephone);
    }

    @Override
    public User getUserByEmail(String email) {
        String columnName = "email";
        return userDao.getUserByParam(columnName, email);
    }

    @Override
    public User getUserByName(String username) {
        String columnName = "username";
        return userDao.getUserByParam(columnName, username);
    }

    @Override
    @Transactional
    public String registerUser(User user) {
        String columnName = "username";
        User temp = null;
        temp = userDao.getUserByParam(columnName, user.getUsername());
        if (temp == null) {
            if (userDao.insertUser(user) == 1)
                return UserController.REGISTER_SUCCESS;
            else return UserController.REGISTER_ERROR;
        }
        return UserController.REGISTER_EXIST;
    }

    @Override
    public User loginUser(User user,StringBuffer loginInfo) {
        String columnName = "username";
        User loginUser = null;
        loginUser = userDao.getUserByParam(columnName, user.getUsername());
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
    public Map<Long, String> getIDUsernameMapByIDs(List<Integer> ids) {
        List<Map<Object, Object>> userMaps = userDao.getUsernameByIDs(ids);
        Map<Long, String> resultMap = new HashMap<>();
        for (Map<Object, Object> map: userMaps) {
            resultMap.put((Long) map.get("id"), (String)map.get("username"));
        }
        return resultMap;
    }

    @Override
    @Transactional
    public boolean updateUser(User user) {
        boolean result = true;
        if (userDao.updateUser(user) != 1)
            result =false;
        return result;
    }
}
