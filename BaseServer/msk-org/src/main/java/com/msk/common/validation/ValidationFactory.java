package com.msk.common.validation;

import com.msk.common.annotation.valid.Mail;
import com.msk.common.annotation.valid.Mobile;
import com.msk.common.annotation.valid.Required;
import com.msk.common.annotation.valid.Size;
import com.msk.common.validation.impl.*;

import java.lang.annotation.Annotation;

public class ValidationFactory {

    public static BaseValidationService getValidationService(Annotation annotation){
        if(annotation instanceof Required){
            return new RequiredValidationService();
        }
        if(annotation instanceof Mail){
            return new MailValidationService();
        }
        if(annotation instanceof Mobile){
            return new MobileValidationService();
        }
        if(annotation instanceof com.msk.common.annotation.valid.Number){
            return new NumberValidationService();
        }
        if(annotation instanceof Size){
            return new SizeValidationService();
        }



        return null;
    }

}
