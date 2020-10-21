package com.server.aop;

import com.alibaba.fastjson.JSONObject;
import com.server.annotation.WebLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
* @author tianqingbo3
* @version 1.0
* @date 2020/10/20 11:36
*/
@Aspect
@Component
@Slf4j
public class WebLogAop {
    /**
     * 切入点
     * 携带改注解都打印接口入参和结果日志信息
     */
    @Pointcut("@annotation(com.server.annotation.WebLog)")
    public void pointCut() {
    }

    @Around("pointCut() && @annotation(webLog)")
    public Object around(ProceedingJoinPoint point, WebLog webLog) {
        long start = System.currentTimeMillis();
        Object target = point.getTarget();// 类
        Object[] args = point.getArgs();// 方法参数
        Method method = null;
        try {
            MethodSignature methodSignature = (MethodSignature)point.getSignature();
            method = target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        String description = webLog.description();// 日志描述信息
        Object proceed = null;
        try {
            proceed = point.proceed();
            long end = System.currentTimeMillis();
            log.info("Request: target: {}#{}, args: {}, description: {} ---- Response: {} ---- Execution time:" +
                            " {}ms", target, method.getName(),
                    JSONObject.toJSON(Arrays.stream(args).collect(Collectors.toList())), description,
                    JSONObject.toJSONString(proceed), end - start);
        } catch (Throwable throwable) {
            log.error("Current method execution error： {}", method.getName());
            throwable.printStackTrace();
        }
        return proceed;
    }


}
