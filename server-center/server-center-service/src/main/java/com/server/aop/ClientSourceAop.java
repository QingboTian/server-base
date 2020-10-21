package com.server.aop;

import com.alibaba.fastjson.JSONObject;
import com.server.client.service.ClientSoruceService;
import com.server.common.ClientSource;
import com.server.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
* @author tianqingbo3
* @version 1.0
* @date 2020/10/20 20:52
*/
@Aspect
@Component
@Slf4j
public class ClientSourceAop {

    @Autowired
    private ClientSoruceService clientSoruceService;

    @Pointcut("execution(public * com.server.service.impl..*.*(..)))")
    public void point(){
        log.info("clientSource pointCut: [{}]", "public * com.server.service.impl..*.*(..)))");
    }

    @Around("point()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Object res = null;

        for (Object obj : args) {
            if (obj instanceof ClientSource) {
                if (validate(obj)) {
                    try {
                        res = joinPoint.proceed();
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                } else {
                    log.info("The current application is not authorized. Please authorize its use");
                    return JSONObject.toJSONString(Result.build(HttpStatus.UNAUTHORIZED.value(), "The current application is not authorized. Please authorize its use"));
                }
            }
        }
        return res;
    }

    private boolean validate(Object obj) {
        ClientSource clientSource = (ClientSource) obj;
        return clientSoruceService.validate(clientSource);
    }

}
