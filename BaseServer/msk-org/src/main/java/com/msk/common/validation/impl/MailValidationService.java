package com.msk.common.validation.impl;

import java.lang.annotation.Annotation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.msk.common.annotation.valid.Mail;
import com.msk.common.validation.BaseValidationService;
import org.springframework.util.StringUtils;

public class MailValidationService implements BaseValidationService{
    @Override
    public String getValidationMessage(Annotation annotation,Object value) {
        Mail mail = (Mail)annotation;
        if(value instanceof String){
            if(!StringUtils.isEmpty(value)){
                if(!checkMail((String) value)){
                    return mail.message();
                }
            }
        }
        return null;
    }

    private boolean checkMail(String mailAddress){
        Pattern pattern = Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$");
        Matcher matcher = pattern.matcher(mailAddress);
        return matcher.matches();
    }
}
