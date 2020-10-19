package com.server.sso.service;


import com.server.exception.AppException;
import com.server.sso.pojo.Userinfo;

public interface SsoService {

    boolean regist(Userinfo user) throws AppException;

    boolean login(Userinfo user) throws AppException;

    boolean logout(String token);

}
