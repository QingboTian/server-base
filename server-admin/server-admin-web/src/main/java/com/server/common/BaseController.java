package com.server.common;

/**
* @author tianqingbo3
* @version 1.0
* @date 2020/10/21 9:22
*/
public class BaseController {

    public ClientSource getClientSource() {
        ClientSource clientSource = new ClientSource();
        clientSource.setAppId("53783804-523d-49a0-99da-f044a718f8bd");
        clientSource.setAppName("server-admin");
        return clientSource;
    }

}
