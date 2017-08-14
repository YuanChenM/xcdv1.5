package com.hoperun.plug.spring.aspect;

import java.lang.reflect.Method;
import java.util.List;


import com.hoperun.core.bean.ExceptionMessage;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.ValidatorException;
import com.hoperun.plug.spring.annotation.Validator;
import com.hoperun.plug.spring.annotation.ValidatorLevel;
import com.hoperun.plug.spring.base.BaseValidator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;


/**
 * ValidatorAspect.
 */
@Aspect
@Component
public class ValidatorAspect {
    /**
     * controllerAspect
     */
    @Pointcut("@annotation(com.hoperun.plug.spring.annotation.Validator)")
    public void controllerAspect() {}

    /**
     * doBefore
     *
     * @param joinPoint joinPoint
     */
    @SuppressWarnings("unchecked")
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        Object[] args = joinPoint.getArgs();
        Validator validator = method.getAnnotation(Validator.class);
        BaseValidator<Object> baseValidator = null;
        try {
            baseValidator = (BaseValidator<Object>) Class.forName(validator.validatorClass()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (baseValidator == null) {
            return;
        }
        ValidatorLevel validatorLevel = validator.level();
        // 验证实体对象
        Object entity = args[NumberConst.IntDef.INT_ZERO];
        if (validatorLevel == ValidatorLevel.WARN) {
            baseValidator.warnValidator(entity);
            args[NumberConst.IntDef.INT_ONE] = baseValidator.getExceptionMessageList();
        } else {
            baseValidator.validator(entity);
            List<ExceptionMessage> msgs = baseValidator.getExceptionMessageList();
            if (!CollectionUtils.isEmpty(msgs)) {
                ValidatorException ex = new ValidatorException(msgs);
                throw ex;
            }
        }
    }
}
