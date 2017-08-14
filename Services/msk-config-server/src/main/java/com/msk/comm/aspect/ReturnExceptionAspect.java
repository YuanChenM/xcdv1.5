package com.msk.comm.aspect;

import com.msk.config.bean.RsResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by shi_yuxi on 2016/8/24.
 */
@Component
@Aspect
public class ReturnExceptionAspect {

    /** 日志对象 */
    private static Logger logger = LoggerFactory.getLogger(ReturnExceptionAspect.class);

    @Pointcut("execution(com.msk.config.bean.RsResponse com.msk.*.rest.*RsController.*(..))")
    public void returnException(){}

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void controllerAspect() {}

     /**
     * 对restController异常处理
     *
     * @param pjp pjp
     * @return Object
     */
    @Around("returnException() && controllerAspect()")
    public Object doAfterThrowing(ProceedingJoinPoint pjp) throws Throwable {
        if (logger.isInfoEnabled()) {
            logger.info("rest接口 处理开始[{}]", pjp.toString());
        }
        Object result = null;
        try {
            Object target = pjp.getTarget();
            long startTime = System.currentTimeMillis();
            result = pjp.proceed();
            long endTime = System.currentTimeMillis();
            logger.info("方法:"+target.getClass().getName()+"."+pjp.getSignature().getName()+"花费时间:"+(endTime-startTime));
        }catch (Exception e){
            RsResponse response = new RsResponse();
            if (logger.isErrorEnabled()) {
                logger.error("参数发生错误:{}", e);
            }
            result = this.ex2msg(e, response);
        }

        return result;
    }
//    @AfterReturning(pointcut = "returnException() && controllerAspect()",
//            returning = "returnValue")
//    public void doAfter(JoinPoint joinPoint, Object returnValue) {
//        System.out.println("返回结果了。。。。。。。。。。。。。。。。。");
//    }

    /**
     * 异常信息转消息
     *
     * @param ex 异常对象
     * @param response 结果对象
     * @return 结果对象
     */
    protected RsResponse ex2msg(Throwable ex, RsResponse response) {
        response.setStatus("F");
        response.setMessage(ex.getMessage());
        response.setReturnCode("F0000001");
        return response;
    }
}
