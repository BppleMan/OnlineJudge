package com.bppleman.service;

import com.bppleman.entity.Code;
import com.bppleman.entity.IDParam;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")

public class CodeServiceTest {

    @Resource
    private CodeService codeService = null;

    @Before
    public void setUp() throws Exception {
        Logger.getRootLogger().setLevel(Level.DEBUG);
    }

    @Test
    public void getCode() throws Exception {
        List<Code> codes = codeService.getCode(new IDParam(100001, 100001, null, null));
        System.out.println(codes);
    }

    @Test
    public void getCodeByID() throws Exception {
    }

    @Test
    public void insertCode() throws Exception {
        Code code = new Code();
        code.setIdParam(new IDParam(1,2,3,4));
        code.setLength(123);
        code.setCodeValue("testCodeValue");
        code.setLanguage("cpp");
        codeService.insertCode(code);
    }

}