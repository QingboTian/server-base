package com.server.utils;

import java.util.UUID;

/**
* @author tianqingbo3
* @version 1.0
* @date 2020/10/15 18:56
*/
public class UUIDUtils {

    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
    }

}
