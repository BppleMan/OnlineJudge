package com.bppleman.service;

import com.bppleman.controller.ContestController;
import com.bppleman.entity.Contest;
import com.bppleman.entity.Problem;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by BppleMan on 2017/11/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")

public class ContestServiceTest {
    @Test
    public void getAllContests() throws Exception {
    }

    @Test
    public void getContestsWithPage() throws Exception {
        List<Contest> contests = contestService.getContestsWithPage(
                ContestController.Type.STATUS,
                Contest.Status.END, 1, 5);
        System.out.println(contests);
    }

    @Test
    public void getContestById() throws Exception {
    }

    @Test
    public void getCount() throws Exception {
    }

    @Test
    public void insertContest() throws Exception {
    }

    @Test
    public void canCreateContest() throws Exception {
    }

    @Test
    public void getProblemIdsByContestId() throws Exception {
    }

    @Test
    public void getProblemsByContestId() throws Exception {
    }

    @Test
    public void updateContestProblem() throws Exception {
    }

    @Resource
    private ContestService contestService = null;

    @Before
    public void setUp() throws Exception {
        Logger.getRootLogger().setLevel(Level.DEBUG);
    }


}