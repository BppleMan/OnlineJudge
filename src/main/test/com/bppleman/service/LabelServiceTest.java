package com.bppleman.service;

import com.bppleman.dao.LabelDao;
import com.bppleman.entity.Label;
import com.bppleman.entity.Problem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by BppleMan on 2017/11/24.
 */

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})

public class LabelServiceTest {

    @Resource
    private LabelDao labelDao = null;

    @Test
    public void getAllTypes() throws Exception {
    }

    @Test
    public void getAllLabels() throws Exception {
    }

    @Test
    public void getLabelsByType() throws Exception {
        List<String> types = labelDao.getAllTypes();
        System.out.println(types);
        Map<String, List<Label>> labelsMap = new HashMap<>();
        for (String type : types) {
            List<Label> labels = labelDao.getLabelsByType(type);
            labelsMap.put(type, labels);
        }
        System.out.println(labelsMap);
    }

}