package com.server.sso.service.impl;

import com.server.exception.AppException;
import com.server.sso.mapper.UserinfoMapper;
import com.server.sso.pojo.Userinfo;
import com.server.sso.pojo.UserinfoExample;
import com.server.sso.service.SsoService;
import com.server.utils.MD5Utils;
import com.server.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
* @author tianqingbo3
* @version 1.0
* @date 2020/10/15 17:16
*/
@Service
@Slf4j
public class SsoServiceImpl implements SsoService {

    @Autowired
    private UserinfoMapper userMapper;

    /**
     * 此处还可以根据邮箱或手机号等进行判断用户是否已经注册
     * @param user
     * @return
     */
    @Override
    public boolean regist(Userinfo user) throws AppException {
        if (Objects.isNull(exist(user))) {
            user.setUnionId(UUIDUtils.uuid());
            user.setStatus(1);
            user.setCreated(new Date());
            // 应该对密码进行md5加密，避免明文存储
            user.setPassword(MD5Utils.md5(user.getPassword()));
            return userMapper.insert(user) != 0;
        } else {
            throw new AppException("The user already exists, please do not repeat registration", HttpStatus.FORBIDDEN.value());
        }
    }

    @Override
    public boolean login(Userinfo user) throws AppException {
        Userinfo userinfo = exist(user);
        if (Objects.isNull(userinfo)) {
            throw new AppException("The user does not exist or is blocked, please go to register", HttpStatus.UNAUTHORIZED.value());
            // 用户不存在或者被拉黑
        }
        // 数据库中应该存储的是md5加密的密码
        String pwd = MD5Utils.md5(user.getPassword());
        if (Objects.equals(pwd, userinfo.getPassword())) {
            return true;
        } else {
            throw new AppException("The password is incorrect. Please re-enter it", HttpStatus.UNAUTHORIZED.value());
        }
    }

    @Override
    public boolean logout(String token) {
        return false;
    }

    private Userinfo exist(Userinfo user) {
        UserinfoExample userExample = new UserinfoExample();
        userExample.createCriteria().andUsernameEqualTo(user.getUsername());
        List<Userinfo> userinfos = userMapper.selectByExample(userExample);
        if (CollectionUtils.isEmpty(userinfos)) {
            return null;
        } else {
            // 用户已经逻辑删除 可以理解为拉黑
            if (userinfos.get(0).getStatus().equals(0)) {
                return null;
            }
        }
        return userinfos.get(0);
    }
}
