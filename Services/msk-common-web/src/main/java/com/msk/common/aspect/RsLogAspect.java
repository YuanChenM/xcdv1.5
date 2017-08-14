package com.msk.common.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.utils.MethodUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * RsLogAspect.
 */
@Aspect
public class RsLogAspect {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(RsLogAspect.class);

    private static Map<String, Method> VALIDATOR_METHOD_MAP = new HashMap<String, Method>();

    /**
     * XML Converter that is defined in spring-mvc-web.xml
     */
    @Autowired
    @Qualifier("xmlConverter")
    MappingJackson2XmlHttpMessageConverter xmlConvert;

    /**
     * XML Converter that is defined in spring-mvc-web.xml
     */
    @Autowired
    @Qualifier("jsonConverter")
    MappingJackson2HttpMessageConverter jsonConvert;

    /**
     * controllerAspect
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void controllerAspect() {}

    /**
     * controllerAspect
     */
    @Pointcut("execution(public * com.msk.*.rest.*RsController.*(..))")
    public void rsAspect() {}

    // /**
    // * doBefore
    // *
    // * @param joinPoint joinPoint
    // */
    // @Before("rsAspect() && controllerAspect()")
    // public void doBefore(JoinPoint joinPoint) {
    //
    // Object[] args = joinPoint.getArgs();
    // if (args != null && args.length > 0) {
    // logger.trace(args[0].toString());
    // }
    // }

    /**
     * doBefore
     *
     * @param returnValue the return value
     */
    @AfterReturning(pointcut = "rsAspect() && controllerAspect()",
        returning = "returnValue")
    public void doAfter(JoinPoint joinPoint, Object returnValue) {
        Signature signature = joinPoint.getSignature();
        Class<?> clazz = signature.getDeclaringType();
        String clazzName = clazz.getName();
        String methodName = signature.getName();
        String key = clazzName + StringConst.COMMA + methodName;
        Method method = VALIDATOR_METHOD_MAP.get(key);
        if (null == method) {
            method = MethodUtils.getDeclaredMethod(clazz,methodName);
            VALIDATOR_METHOD_MAP.put(key, method);
        }
        boolean isXml = false;
        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
        String[] produces = requestMapping.produces();
        if (produces != null && produces.length == 1) {
            if (MediaType.APPLICATION_XML_VALUE.equalsIgnoreCase(produces[0])) {
                isXml = true;
            }
        }
        String rs = "";
        if (returnValue == null) {
            rs = "null";
        } else if (isXml) {
            try {
                rs = xmlConvert.getObjectMapper().writeValueAsString(returnValue);
            } catch (JsonProcessingException e) {
                logger.warn("Exception process fail.{}", e.getMessage());
            }
        } else {
            ObjectMapper mapper = new ObjectMapper();
            try {
                rs = mapper.writeValueAsString(returnValue);
            } catch (JsonProcessingException e) {
                logger.warn("Exception process fail.{}", e.getMessage());
            }
        }
        logger.trace("\n[RsResponse]:{}", rs);
    }

    /**
     * doBefore
     *
     * @param e the exception
     */
    @AfterThrowing(pointcut = "rsAspect() && controllerAspect()",
        throwing = "e")
    public void doThrowing(Exception e) {
        if (e != null) {
            logger.error("\n[Exception]:{}", e.getMessage());
        }
    }
}
