package com.msk.common.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.msk.common.base.BaseParam;
import com.msk.common.bean.RestRequest;
import com.msk.common.validation.BaseValidationService;
import com.msk.common.validation.ValidationFactory;
import org.springframework.util.StringUtils;

@Component
@Aspect
public class ValidationRestControllerAspect {
    @Pointcut(value = "@annotation(com.msk.common.annotation.valid.Validation)")
    public void aspect() {}
    @Before("aspect()")
    public void doBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        BaseParam param = this.getRequestParam(args);
        if(param==null){
            return;
        }
        List<String> messageList = new ArrayList<>();
        this.validationParam(param,messageList);
    }

    private BaseParam getRequestParam(Object [] args){
        if (args != null) {
            Object request = args[0];
            if(request instanceof  RestRequest){
                Object param = this.getRestRequestParam((RestRequest)request);
                return (BaseParam)param;
            }
            if(request instanceof BaseParam){
                return (BaseParam)request;
            }
        }
        return null;
    }
    private BaseParam getRestRequestParam(RestRequest request){
        Object param = request.getParam();
        if(param instanceof BaseParam){
            return (BaseParam) param;
        }
        return null;
    }

    private void validationParam(Object param,List<String> messageList){
        Class<?> paramClass = param.getClass();
        Field[] fieldList = paramClass.getDeclaredFields();
        for (Field field :fieldList){
            Annotation[] annotationList = field.getAnnotations();
            this.validationParam(param,paramClass,field,annotationList,messageList);
        }
    }
    private void validationParam(Object param,Class<?> paramClass,Field field,Annotation [] annotationList,List<String> messageList){
        if(annotationList == null || annotationList.length == 0){
            return;
        }
        for (Annotation annotation : annotationList){
            Object fieldValue = getFieldValue(param,paramClass,field);
            String message = validationParamField(fieldValue,annotation);
            if(!StringUtils.isEmpty(message)){
                messageList.add(message);
            }

        }

    }
    private String validationParamField(Object value,Annotation annotation){
        BaseValidationService validationService = ValidationFactory.getValidationService(annotation);
        if(validationService == null){
            return null;
        }
        return validationService.getValidationMessage(annotation,value);
    }



    private Object getFieldValue(Object param,Class<?> paramClass,Field field){
        String fieldName = field.getName();
        String getMethodName = "get"+toFirstLetterUpperCase(fieldName);
        try {
            Object value = paramClass.getMethod(getMethodName).invoke(param);
            return value;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String toFirstLetterUpperCase(String str) {
        if(str == null || str.length() < 2){
            return str;
        }
        String firstLetter = str.substring(0, 1).toUpperCase();
        return firstLetter + str.substring(1, str.length());
    }
}
