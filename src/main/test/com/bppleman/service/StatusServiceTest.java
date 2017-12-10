package com.bppleman.service;

import com.bppleman.entity.IDParam;
import com.bppleman.entity.Status;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
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

/**
 * Created by BppleMan on 2017/11/20.
 */

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})

public class StatusServiceTest {

    @Resource
    private StatusService statusService = null;

    @Before
    public void setUp() throws Exception {
        Logger.getRootLogger().setLevel(Level.DEBUG);
    }

    @Test
    public void getStatus() throws Exception {
        IDParam idParam = new IDParam(null, null, 100001, null);
        List<Status> statuses = statusService.getStatus(idParam);
        System.out.println(statuses);
    }

    @Test
    public void getStatusByIDs() throws Exception {
        List<Integer> ids = new ArrayList<>();
        ids.add(3);
        ids.add(4);
        ids.add(5);
        List<Status> statuses = statusService.getStatusByIDs(ids);
        System.out.println(statuses);
    }

    @Test
    public void getTheLashStatus() throws Exception {
        Status status = statusService.getTheLastInsertStatus();
        System.out.println(status);
        System.out.println(status.getCode());
    }

    @Test
    public void insertStatus() throws Exception {
        Status status = new Status();
        status.setStatusValue(Status.Judging);
        status.setIdParam(new IDParam(1,2,3,4));
        statusService.insertStatus(status);
        System.out.println(statusService.getTheLastInsertStatus());
    }

    @Test
    public void updateStatus() throws Exception {
        Status status = statusService.getTheLastInsertStatus();
        status.setCompileInfo("");
        status.setStatusValue(Status.Accepted);
        statusService.updateStatus(status);
    }
}