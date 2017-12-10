package com.bppleman.service;

import com.bppleman.entity.Contest;
import com.bppleman.entity.Problem;
import com.bppleman.entity.User;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by BppleMan on 2017/11/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class UserServiceTest {

    private static Logger logger = Logger.getLogger(UserServiceTest.class);

    @Resource
    private UserService userService = null;

    @Resource
    private ContestService contestService = null;

    @Test
    public void registerUser() throws Exception {
        User user = new User();
        user.setUsername("user2");
        user.setPassword("123456");
        user.setType(User.Normal);
        System.out.println(userService.registerUser(user));
    }

    @Test
    public void loginUser() throws Exception {
        User user = new User();
        user.setUsername("abcd");
        user.setPassword("1234");
    }

    @Test
    public void getUserByName() throws Exception {
        User user = userService.getUserByName("bppleman");
        System.out.println(user);
        Contest contest = contestService.getContestById(1);
    }

    @Test
    public void getUsernameByIDs() throws Exception {
        List<Integer> ids = new ArrayList<>();
        ids.add(100001);
        ids.add(100001);
        ids.add(100001);
        ids.add(100002);
        ids.add(100002);
        ids.add(100003);

        Map<Long, String> resultMap = userService.getIDUsernameMapByIDs(ids);
        System.out.println(resultMap);
    }
}