package com.bppleman.service;

import com.bppleman.entity.Problem;
import com.bppleman.entity.SmartProblem;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")

public class SmartProblemServiceTest {

    @Resource
    private SmartProblemService smartProblemService;

    @Before
    public void setUp() throws Exception {
        Logger.getRootLogger().setLevel(Level.DEBUG);
    }

    @Test
    public void getProblemsBySmartProblem() throws Exception {
        SmartProblem smartProblem = new SmartProblem();
        smartProblem.setType("基础入门");
        smartProblem.setLabel("循环");
        smartProblem.setStartLevel(0);
        smartProblem.setEndLevel(25);
        smartProblem.setProblemCount(5);
        List<Problem> problems = smartProblemService.getProblemsBySmartProblem(smartProblem, 10001);
        System.out.println(problems);
    }

}