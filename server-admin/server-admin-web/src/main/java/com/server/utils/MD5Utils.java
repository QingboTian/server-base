package com.server.utils;

import org.springframework.util.DigestUtils;

/**
* @author tianqingbo3
* @version 1.0
* @date 2020/10/16 10:26
*/
public class MD5Utils {

    public static String md5(String msg) {
        return DigestUtils.md5DigestAsHex(msg.getBytes());
    }


    public static void main(String[] args) {
        System.out.println(md5("tianqingbo3"));
    }
}
