package com.msk.common.validation.impl;

import java.lang.annotation.Annotation;

import com.msk.common.annotation.valid.Size;
import com.msk.common.validation.BaseValidationService;

/**
 * Created by jackjiang on 16/8/8.
 */
public class SizeValidationService implements BaseValidationService{
    @Override
    public String getValidationMessage(Annotation annotation,Object value) {
        Size size = (Size)annotation;
        if(value == null){
            return null;
        }
        int maxSize = size.maxSize();
        int minSize = size.minSize();
        if(maxSize == 0 && minSize == 0){
            return null;
        }
        if(value instanceof String){
            int valueSize = ((String) value).length();
            if(valueSize < minSize || valueSize > maxSize){
                String message = size.message();
                return message;
            }
        }
        return null;
    }
}
