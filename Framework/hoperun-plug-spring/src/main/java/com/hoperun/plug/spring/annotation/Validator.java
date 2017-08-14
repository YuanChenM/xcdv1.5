package com.hoperun.plug.spring.annotation;

import java.lang.annotation.*;

/**
 * Validator 注解
 * @author jiang_nan
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Validator {
	 String validatorClass();
	 ValidatorLevel level() default ValidatorLevel.EXCEPTION;
}
