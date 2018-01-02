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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
//        key-value
        Map<Integer, String> test = new HashMap<>();
        String strs[] = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        String english[] = new String[100];
        for (int i = 0; i < strs.length; i++) {
            english[i] = strs[i];
        }

        for (int i = 0; i < 10; i++) {
            if (english[i].equals("six")) {
                int six = i;

            }
        }
//        O (xx)完成一个功能在最坏的情况下所用的时间
//        n = 10
//        O (n)
//        O (1)

        test.put(1, "one");
        test.put(2, "two");
        test.put(3, "three");
        test.put(4, "four");
        test.put(5, "five");
        test.put(6, "six");
        test.put(7, "seven");
        test.put(8, "eight");
        test.put(9, "nine");
        test.put(0, "zero");
        test.put(20, "twenty");


        System.out.println(test.get(0));
//        System.out.println(test.get(0));
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