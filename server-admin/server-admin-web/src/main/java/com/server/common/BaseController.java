package com.server.common;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

/**
* @author tianqingbo3
* @version 1.0
* @date 2020/10/21 9:22
*/
@Slf4j
public class BaseController {

    private static ClientSource clientSource = new ClientSource();

    static {
        clientSource.setAppId("53783804-523d-49a0-99da-f044a718f8bd");
        clientSource.setAppName("server-admin");
        log.info("Application clientSource init: {}", JSONObject.toJSONString(clientSource));
    }

    public static ClientSource getClientSource() {
        return clientSource;
    }

}
