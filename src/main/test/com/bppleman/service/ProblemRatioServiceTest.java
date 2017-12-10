package com.bppleman.service;

import com.bppleman.entity.IDParam;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})

public class ProblemRatioServiceTest {

    @Resource
    private ProblemRatioService problemRatioService = null;

    @Resource
    private ProblemService problemService = null;

    @Before
    public void setUp() throws Exception {
        Logger.getRootLogger().setLevel(Level.DEBUG);
    }

    @Test
    public void getProblemRatio() throws Exception {
        List<ProblemRatio> problemRatios = problemRatioService.getProblemRatio(new IDParam(null, null, null, null));
        System.out.println(problemRatios);
    }

    @Test
    public void insertProblemRatio() throws Exception {
        ProblemRatio problemRatio = new ProblemRatio();
        problemRatio.setIdParam(new IDParam(null, 100003, 100001, null));
        problemRatio.setSubmitTime(3);
        problemRatio.setAcTime(2);
        problemRatio.setRatioValue(2.0 / 3.0 * 100.0);
        problemRatioService.insertProblemRatio(problemRatio);
        System.out.println(problemRatio);
    }

    @Test
    public void updateProblemRatio() throws Exception {
        ProblemRatio problemRatio = new ProblemRatio();
        problemRatio.setIdParam(new IDParam(null, 100001, 100001, null));
        problemRatio.setSubmitTime(5);
        problemRatio.setAcTime(4);
        problemRatio.setRatioValue(4.0 / 5.0 * 100.0);
        problemRatioService.insertProblemRatio(problemRatio);
        System.out.println(problemRatio);
    }

    @Test
    public void deleteProblemRatio() throws Exception {
        IDParam idParam = new IDParam(null, 100003, 100001, null);
        List<IDParam> idParams = new ArrayList<>();
        idParams.add(idParam);
        problemRatioService.deleteProblemRatio(idParams);
    }

    @Test
    public void getProblemRatioMap() {
//        ProblemRatio problemRatio = new ProblemRatio(null, null, null);
//        Map<Integer, ProblemRatio> problemRatioMap = problemRatioService.getProblemRatioMap(problemRatio);
//        System.out.println(problemRatioMap);
    }
}