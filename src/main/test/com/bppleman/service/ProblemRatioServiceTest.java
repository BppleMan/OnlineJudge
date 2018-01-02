package com.bppleman.service;

import com.bppleman.entity.IDParam;
import com.bppleman.entity.Problem;
import com.bppleman.entity.ProblemRatio;
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
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})

public class ProblemRatioServiceTest {

    @Resource
    private ProblemRatioService problemRatioService = null;

    @Resource
    private ProblemRatioService contestProblemRatioService;

    @Resource
    private ProblemService problemService = null;

    @Before
    public void setUp() throws Exception {
//        Logger.getRootLogger().setLevel(Level.DEBUG);
    }

    @Test
    public void initProblemRatio() throws Exception {
        List<Problem> problems = problemService.getAllProblems();
        for (Problem problem : problems) {
            if (problem.getProblemRatio() == null) {
                ProblemRatio problemRatio = new ProblemRatio(new IDParam(null, problem.getId(), null, null));
                Random random = new Random();
                Integer submitTime = random.nextInt(1000);
                System.out.println(submitTime);
                Integer acTime = random.nextInt(submitTime);
                Double ratioValue = (double) acTime / (double) submitTime * 100;
                problemRatio.setSubmitTime(submitTime);
                problemRatio.setAcTime(acTime);
                problemRatio.setRatioValue(ratioValue);
                problemRatioService.insertProblemRatio(problemRatio);
            }
        }
    }

    @Test
    public void getProblemRatio() throws Exception {
        Map<Integer, ProblemRatio> problemRatioMap = contestProblemRatioService.getProblemRatioMap(new IDParam(-1, -1, 10001, -1));
        System.out.println(problemRatioMap);
    }

    @Test
    public void insertProblemRatio() throws Exception {
        int count = problemService.getCount(null, null);
    }

    @Test
    public void updateProblemRatio() throws Exception {
    }

    @Test
    public void deleteProblemRatio() throws Exception {

    }

    @Test
    public void getProblemRatioMap() {

    }
}