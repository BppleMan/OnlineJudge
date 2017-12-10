package com.bppleman.service;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")

public class TableCountServiceTest {

    @Resource
    private TableCountService tableCountService = null;

    @Before
    public void setUp() throws Exception {
        Logger.getRootLogger().setLevel(Level.DEBUG);
    }

    @Test
    public void getTableCount() throws Exception {
        System.out.println(tableCountService.getTableCount("t_problem"));
    }

    @Test
    public void updateTableCount() throws Exception {
    }

}