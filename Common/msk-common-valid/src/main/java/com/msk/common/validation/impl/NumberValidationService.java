package com.msk.common.validation.impl;

import java.lang.annotation.Annotation;
import java.math.BigDecimal;

import com.msk.common.validation.BaseValidationService;

public class NumberValidationService implements BaseValidationService{
    @Override
    public String getValidationMessage(Annotation annotation,Object value) {
        com.msk.common.annotation.valid.Number number = (com.msk.common.annotation.valid.Number)annotation;
        int min = number.min();
        int max = number.max();
        if(value == null){
            return null;
        }
        if(min == 0 && max == 0){
            return null;
        }
        String message = number.message();
        if(value instanceof Integer){
            Integer numberValue = (Integer)value;
            if(numberValue<min || numberValue>max){
                return message;
            }

        }else if(value instanceof Float){
            Float numberValue = (Float)value;
            if(numberValue<min || numberValue>max){
                return message;
            }
        }else if(value instanceof Double){
            Double numberValue = (Double)value;
            if(numberValue<min || numberValue>max){
                return message;
            }
        }else if(value instanceof BigDecimal){
            BigDecimal numberValue = (BigDecimal)value;
            if(numberValue.doubleValue()<min || numberValue.doubleValue()>max){
                return message;
            }
        }
        return null;
    }
}
