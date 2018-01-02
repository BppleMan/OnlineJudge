package com.bppleman.service;

import com.bppleman.entity.IDParam;
import com.bppleman.entity.Problem;
import com.bppleman.entity.UserSolve;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
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

public class ProblemUserSolveServiceTest {

    private Logger logger = Logger.getRootLogger();

    @Before
    public void setUp() throws Exception {
        logger.setLevel(Level.DEBUG);
    }

    @Resource
    private ProblemUserSolveService problemUserSolveService = null;

    @Test
    public void getUserSolveMap() throws Exception {

    }

    @Test
    public void getUserSolveTotalPageMap() throws Exception {
        Map<String, Integer> userSolveTotalPageMap = problemUserSolveService.getUserSolveTotalPageMap(10001, 5.0);
        System.out.println(userSolveTotalPageMap);
    }

    @Test
    public void getUserSolveProblemsMap() throws Exception {
//        Map<String, List<Problem>> userSolveProblemsMap = problemUserSolveService.getUserSolveProblemsMap(userSolveCurrentPageMap, counterPerPage, user.getId());

    }

    @Test
    public void insertUserSolve() throws Exception {
        UserSolve userSolve = new UserSolve();
        userSolve.setSolveState(UserSolve.SOLVED);
        problemUserSolveService.insertUserSolve(userSolve);
    }

    @Test
    public void updateUserSolve() throws Exception {
        UserSolve userSolve = new UserSolve();
        userSolve.setSolveState(UserSolve.SOLVED);
        problemUserSolveService.updateUserSolve(userSolve);
    }

    @Test
    public void deleteUserSolve() throws Exception {

    }

}