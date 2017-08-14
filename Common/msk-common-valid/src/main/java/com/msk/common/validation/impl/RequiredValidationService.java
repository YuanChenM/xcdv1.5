package com.msk.common.validation.impl;

import java.lang.annotation.Annotation;

import org.springframework.util.StringUtils;

import com.msk.common.annotation.valid.Required;
import com.msk.common.validation.BaseValidationService;

/**
 * Created by jackjiang on 16/8/8.
 */
public class RequiredValidationService implements BaseValidationService{
    @Override
    public String getValidationMessage(Annotation annotation,Object value) {
        Required requiredAnnotation = (Required)annotation;
        boolean required = requiredAnnotation.required();
        if(!required){
            return null;
        }
        String message = requiredAnnotation.message();
        if(value instanceof String){
            if(StringUtils.isEmpty(value)){
                return message;
            }
        }else{
            if(value == null){
                return message;
            }
        }
        return null;
    }
}
