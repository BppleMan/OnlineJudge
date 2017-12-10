package com.bppleman.service;

import com.bppleman.entity.IDParam;
import com.bppleman.entity.UserSolve;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})

public class UserSolveServiceTest {

    private Logger logger = Logger.getRootLogger();

    @Before
    public void setUp() throws Exception {
        logger.setLevel(Level.DEBUG);
    }

    @Resource
    private UserSolveService userSolveService = null;

    @Test
    public void getUserSolveMap() throws Exception {
        Map<Integer, String> userSolveMap = userSolveService.getUserSolveMap(new IDParam(100001, null, null, null));
        System.out.println(userSolveMap);
    }

    @Test
    public void insertUserSolve() throws Exception {
        UserSolve userSolve = new UserSolve();
        userSolve.setIdParam(new IDParam(100001, 100003, null, null));
        userSolve.setSolveState(UserSolve.SOLVED);
        userSolveService.insertUserSolve(userSolve);
    }

    @Test
    public void updateUserSolve() throws Exception {
        UserSolve userSolve = new UserSolve();
        userSolve.setIdParam(new IDParam(100001, 100002, null, null));
        userSolve.setSolveState(UserSolve.SOLVED);
        userSolveService.updateUserSolve(userSolve);
    }

    @Test
    public void deleteUserSolve() throws Exception {
        List<IDParam> idParams = new ArrayList<>();
        idParams.add(new IDParam(null, 100002, null, null));
        idParams.add(new IDParam(null, 100003, null, null));
        idParams.add(new IDParam(null, 100010, null, null));
        userSolveService.deleteUserSolve(idParams);
    }

}