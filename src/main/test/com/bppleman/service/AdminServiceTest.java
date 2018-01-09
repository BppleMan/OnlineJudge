package com.bppleman.service;

import com.bppleman.entity.Admin;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")

public class AdminServiceTest {

    @Resource
    private AdminService adminService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getAllAdmins() throws Exception {
    }

    @Test
    public void loginAdmin() throws Exception {
        Admin admin = new Admin();
        admin.setAdminName("bppleman");
        admin.setPassword("123456");
        System.out.println(adminService.loginAdmin(admin));
        System.out.println(admin);
    }

    @Test
    public void insertAdmin() throws Exception {
    }

    @Test
    public void updateAdmin() throws Exception {
    }

}