package com.bppleman.service;

import com.bppleman.entity.UserSolve;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")

public class CountServiceTest {

    @Resource
    private CountService countService;

    @Before
    public void setUp() throws Exception {
        Logger.getRootLogger().setLevel(Level.DEBUG);
    }

    @Test
    public void getCountByEqual() throws Exception {
        String tableName = "problem_user_solve";
        Map<String, String> args = new HashMap<>();
        args.put("user_id", String.valueOf(10001));
        args.put("solve_state", UserSolve.SOLVED);
        System.out.println(countService.getCount(CountService.EQUAL, tableName, args));
    }

    @Test
    public void getCountByLike() throws Exception {
    }

}