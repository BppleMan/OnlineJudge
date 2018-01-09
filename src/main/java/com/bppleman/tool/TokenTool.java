package com.bppleman.tool;

import org.apache.commons.codec.binary.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Created by BppleMan on 2017/11/20.
 */
public class TokenTool {
    /*
     *单例设计模式（保证类的对象在内存中只有一个）
     *1、把类的构造函数私有
     *2、自己创建一个类的对象
     *3、对外提供一个公共的方法，返回类的对象
     */
    private TokenTool(){}

    private static final TokenTool instance = new TokenTool();

    /**
     * 返回类的对象
     * @return
     */
    public static TokenTool getInstance(){
        return instance;
    }

    /**
     * 生成Token
     * Token：Nv6RRuGEVvmGjB+jimI/gw==
     * @return
     */
    public String makeToken(){  //checkException
        //  7346734837483  834u938493493849384  43434384
        String token = String.valueOf(System.currentTimeMillis() + "|" + new Random().nextLong());
        //数据指纹   128位长   16个字节  md5
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte md5[] =  md.digest(token.getBytes());
            byte[] encodeBase64 = Base64.encodeBase64(md5);
            return new String(encodeBase64);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
