package com.msk.common.annotation.valid;

/**
 * Created by jackjiang on 16/8/8.
 */
public @interface Number {
    String message() default "";
    int max() default 0;
    int min() default 0;
}
