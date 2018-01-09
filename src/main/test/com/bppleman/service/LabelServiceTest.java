package com.bppleman.service;

import com.bppleman.entity.Label;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by BppleMan on 2017/11/24.
 */

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis2.xml"})

public class LabelServiceTest {
    @Resource
    private LabelService labelService = null;

    @Before
    public void setUp() throws Exception {
        Logger.getRootLogger().setLevel(Level.DEBUG);
    }

    @Test
    public void getLabels() throws Exception {
        List<String> values = labelService.getValuesByType("基础入门");
        System.out.println(values);
        List<Label> labels = labelService.getLabels();
        System.out.println(labels);
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection conn = DriverManager.getConnection("jdbc:mysql://bppleman.top:3306/oj?useSSL=false&allowMultiQueries=true&user=root&password=123456&characterEncoding=utf8");
//        String sql = "select * from label where type = '基础入门'";
//        String sql = "select * from label";
//        PreparedStatement ps = conn.prepareStatement(sql);
//        ResultSet rs = ps.executeQuery();
//        while (rs.next()) {
//            System.out.println(rs.getString(1) + ":" + rs.getString(2) + ":" + rs.getString(3));
//        }
    }

}