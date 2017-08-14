package com.msk.common.aop;

import com.alibaba.fastjson.JSON;
import com.msk.common.bean.RestRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RestControllerAspect {
    private static Logger logger = LoggerFactory.getLogger(RestControllerAspect.class);

    @Pointcut(value = "@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void aspect() {}

    @Before("aspect()")
    public void doBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length>0) {
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
