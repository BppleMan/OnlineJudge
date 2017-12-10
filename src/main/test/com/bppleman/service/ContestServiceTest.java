package com.bppleman.service;

import com.bppleman.entity.Contest;
import com.bppleman.entity.Problem;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by BppleMan on 2017/11/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})

public class ContestServiceTest {

    @Resource
    private ContestService contestService = null;

    @Test
    public void getAllContests() throws Exception {
        List<Contest> contests = contestService.getAllContests();
        System.out.println(contests);
    }

    @Test
    public void getProblemsByContestID() throws Exception {
        List<Problem> problems = contestService.getProblemsByContestID(1);
        System.out.println(problems);
    }

    @Test
    public void getProblemsByLabel() throws Exception {
        List<Problem> problems = contestService.getProblemsByLabel("入门");
        System.out.println(problems);
    }

    @Test
    public void insertContest() throws Exception {
        Contest contest = new Contest();
        contest.setName("test");
        contest.setStartTime(new Timestamp(System.currentTimeMillis()));
        long duration = (1 * 24 * 3600) * 1000;
        Date endTime = new Timestamp(contest.getStartTime().getTime() + duration);
        contest.setEndTime(endTime);
        contest.setDuration(duration);
        contest.setStatus(Contest.Status.RUNNING);
        contest.setType(Contest.Type.PUBLIC);
        contestService.insertContest(contest);
    }

    @Test
    public void getContestsByAuthor() throws Exception {
    }

    @Test
    public void getContestsByType() throws Exception {
    }

    @Test
    public void getContestsByStatus() throws Exception {
    }

    @Test
    public void getContestByName() throws Exception {
    }

    @Test
    public void getContestById() throws Exception {
        Contest contest = contestService.getContestById(1);
        System.out.println(contest);
        System.out.println(contest.getProblems());
    }

    @Test
    public void canCreateContest() throws Exception {
    }

    @Test
    public void insertContestProblem() throws Exception {
        List<Integer> problemIds = new ArrayList<>();
        problemIds.add(100011);
        problemIds.add(100012);
        problemIds.add(100013);
        problemIds.add(100014);
        problemIds.add(100015);
        System.out.println(contestService.insertContestProblem(100004, problemIds));
    }

    @Test
    public void deleteUserSolveByContestIdAndProblemIds() throws Exception {
        Logger.getRootLogger().setLevel(Level.DEBUG);
        List<Integer> deleted = new ArrayList<>();
        deleted.add(100002);
    }
}