package com.server.sso.controller;

import com.server.common.AccessToken;
import com.server.common.Result;
import com.server.exception.AppException;
import com.server.sso.pojo.Userinfo;
import com.server.sso.service.SsoService;
import com.server.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2020/10/15 17:15
 */
@RestController
@Slf4j
@RequestMapping("/sso")
public class SsoController {

    /**
     * 2H =  2 * 60 * 60s
     */
    @Value("${token.expire}")
    private Long EXPIRE;

    @Autowired
    private SsoService ssoService;

    @PostMapping("/regist")
    public Result regist(@RequestBody @Validated({Insert.class}) Userinfo userinfo) throws AppException {
//        log.info("Registry User: {}", JSONObject.toJSONString(userinfo));
        boolean regist = ssoService.regist(userinfo);
        return Result.ok(regist);
    }

    @PostMapping("/login")
    public Result login(@RequestBody @Validated({Select.class}) Userinfo userinfo, HttpSession session) throws AppException {
        if (ssoService.login(userinfo)) {
            log.info("Login User: {}", userinfo.getUsername());
            // 颁发token
            AccessToken token = getToken();
            session.setAttribute(token.getToken(), userinfo);
            // 过期时间
            session.setMaxInactiveInterval(EXPIRE.intValue());
            return Result.ok(token);
        }
        return Result.ok("Login failed");
    }

    @PostMapping("/logout")
    public Result logout(HttpServletRequest request) throws AppException {
        String token = request.getHeader("TOKEN");
        if (StringUtils.isEmpty(token)) {
            throw new AppException("Current user is not logged in, logout failed", HttpStatus.UNAUTHORIZED.value());
        } else {
            request.getSession().removeAttribute(token);
            return Result.ok("Logout success");
        }
    }

    private AccessToken getToken() {
        String uuid = UUIDUtils.uuid();
        AccessToken accessToken = new AccessToken();
        accessToken.setToken(uuid);
        Date date = new Date(System.currentTimeMillis() + EXPIRE * 1000);
        accessToken.setExpire(date.getTime());
        return accessToken;
    }
}
