package com.bppleman.judge;

import com.bppleman.entity.Status;
import com.bppleman.service.DataService;
import com.bppleman.service.StatusService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by BppleMan on 2017/11/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class JudgeThreadProxyTest {

    @Resource
    private StatusService statusService;

    @Resource
    private DataService dataService;

    @Resource
    private JudgeThreadProxy judgeThreadProxy;

    @Test
    public void run() throws Exception {
        Status status = statusService.getTheLastInsertStatus();
        judgeThreadProxy.setStatus(status);
        judgeThreadProxy.run();
    }

}