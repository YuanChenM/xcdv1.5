package com.hoperun.core.utils;

import java.lang.reflect.Method;

/**
 * MethodUtils
 * @author jiang_nan
 * @version 1.0
 **/
public final class MethodUtils {
    /**
     * 根据方法名称获得方法对象
     * @param clazz class
     * @param methodName 方法名称
     * @return 方法对象
     */
    public static Method getDeclaredMethod(Class<?> clazz,String methodName){
        Method[] methodList = clazz.getDeclaredMethods();
        Method method = null;
        for (Method declaredMethod : methodList) {
            String declaredMethodName = declaredMethod.getName();
            if (declaredMethodName.equals(methodName)) {
                method = declaredMethod;
                break;
            }
        }
        return method;
    }
}
