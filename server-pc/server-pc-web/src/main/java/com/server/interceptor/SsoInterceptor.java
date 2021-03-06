package com.server.interceptor;

import com.server.exception.AppException;
import com.server.sso.pojo.Userinfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2020/10/15 16:47
 */
@Component
@Slf4j
public class SsoInterceptor implements HandlerInterceptor {

    private static List<String> ACCESS_URL = new ArrayList<>();
    private final static String TOKEN = "TOKEN";

    @Value("${token.expire}")
    private Long EXPIRE;

    static {
        ACCESS_URL.add("/sso/login");
        ACCESS_URL.add("/sso/regist");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(TOKEN);
        Userinfo user = (Userinfo) request.getSession().getAttribute(token);

        /**
         * 人工配置通过的URI
         */
        for (String s : ACCESS_URL) {
            if (Objects.nonNull(user) && request.getRequestURI().contains("/sso/login")) {
                throw new AppException( "当前用户已登录，请勿重复登录", HttpStatus.OK.value());
            }
            if (request.getRequestURI().contains(s)) {
                return true;
            }
        }

        if (!StringUtils.isEmpty(token)) {
            if (Objects.isNull(user)) {
                // token 过期
                throw new AppException( "token过期，请重新登录", HttpStatus.UNAUTHORIZED.value());
            } else {
                // 刷新token过期时间
                request.getSession().setMaxInactiveInterval(EXPIRE.intValue());
            }
        } else {
            // 未携带token
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
