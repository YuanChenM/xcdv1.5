package com.msk.common.aop;

import com.msk.common.bean.RestRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

@Component
@Aspect
public class RestControllerAspect {
    private static Logger logger = LoggerFactory.getLogger(RestControllerAspect.class);

    @Pointcut(value = "@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void aspect() {}

    @Before("aspect()")
    public void doBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args != null) {
            if(args[0] instanceof RestRequest){
                RestRequest<?> requestBody = (RestRequest<?>) args[0];
                String paramJson = JSON.toJSONString(requestBody);
                logger.info(paramJson);
            }
        }
    }

    @AfterThrowing(pointcut = "aspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {

    }

}
