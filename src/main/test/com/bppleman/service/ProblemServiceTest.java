package com.bppleman.service;

import javax.annotation.Resource;

import com.bppleman.controller.ProblemController;
import com.bppleman.entity.Problem;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
    public void getProblemByID() throws Exception {
    }

    @Test
    public void getProblemsByKeyWord() throws Exception {
    }

    @Test
    public void getIDTitleMapByIDs() throws Exception {
    }

    @Test
    public void insertProblem() throws Exception {
        Problem problem = new Problem();
        problem.setTitle("test");
        problem.setInput("test2");
        List<String> labels = new ArrayList<>();
        labels.add("循环");
        labels.add("链表");
        problem.setLabels(labels);
        problemService.insertProblem(problem);
    }

    @Test
    public void deleteProblemByID() throws Exception {
        Problem problem = new Problem();
        problem.setId(100588);
        problemService.deleteProblemByID(100588);
    }
}