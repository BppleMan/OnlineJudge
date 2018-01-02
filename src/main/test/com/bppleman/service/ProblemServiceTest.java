package com.bppleman.service;

import javax.annotation.Resource;

import com.bppleman.controller.ProblemController;
import com.bppleman.entity.Problem;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.ArrayList;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})

public class ProblemServiceTest {

    @Resource
    private ProblemService problemService = null;

    @Before
    public void before() {
        Logger.getRootLogger().setLevel(Level.DEBUG);
    }

    @Test
    public void getAllProblems() throws Exception {
    }

    @Test
    public void getProblemsWithPage() throws Exception {
        int pageNumber = 2;
        int countPerPage = 3;
        String type = ProblemController.Type.LABEL;
        String keyWord = "循环, 链表";
        List<Problem> problems = problemService.getProblemsWithPage(type, keyWord, pageNumber, countPerPage);
//        System.out.println(problems);

        System.out.println(problemService.getCount(type, keyWord));
    }

    @Test
    public void getProblemByProblemId() throws Exception {
        Problem problem = problemService.getProblemByProblemId(10002);
        System.out.println(problem);
    }

    @Test
    public void getProblemsByKeyWord() throws Exception {
    }

    @Test
    public void getIDTitleMapByProblemIds() throws Exception {
    }

    @Test
    public void insertProblem() throws Exception {

    }

    @Test
    public void deleteProblemByID() throws Exception {
        Problem problem = new Problem();
        problem.setId(100588);
        problemService.deleteProblemByProblemId(100588);
    }
}