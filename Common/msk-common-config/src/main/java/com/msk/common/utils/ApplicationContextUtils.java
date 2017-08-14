package com.msk.common.utils;

import org.springframework.context.ApplicationContext;

/**
 * Created by jackjiang on 16/8/13.
 */
public class ApplicationContextUtils {
    private static ApplicationContext applicationContext;
    public static void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }
    public static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }
    public static <T> T getBean(Class<T> clazz,String beanName){
        return applicationContext.getBean(clazz,beanName);
    }
}
