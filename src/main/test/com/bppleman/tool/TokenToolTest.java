package com.bppleman.tool;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import static org.junit.Assert.*;

public class TokenToolTest {

    private static TokenTool tokenTool;

    @Test
    public void getInstance() throws Exception {
        tokenTool = TokenTool.getInstance();
    }

    @Test
    public void makeToken() throws Exception {
        String token = tokenTool.makeToken();
        String str = new String(Base64.decodeBase64(token));
        System.out.println(str);
    }

}