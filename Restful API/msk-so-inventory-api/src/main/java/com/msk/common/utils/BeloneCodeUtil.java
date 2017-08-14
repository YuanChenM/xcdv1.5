package com.msk.common.utils;

import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * Created by duan_kai on 2016/9/6.
 */
public class BeloneCodeUtil {

    private static String LOGIC_AREA = "logicArea";
    private static String PLATFORM = "platform";
    private static String SL_TYPE = "slType";
    private static String SL_ID = "slId";

    public static String getBeloneXml(Object targetObject) throws Exception{
        String logicArea = (String)getFieldValueByName(BeloneCodeUtil.LOGIC_AREA, targetObject);
        String platform = (String)getFieldValueByName(BeloneCodeUtil.PLATFORM, targetObject);
        String slType = (String)getFieldValueByName(BeloneCodeUtil.SL_TYPE, targetObject);
        String slId = (String)getFieldValueByName(BeloneCodeUtil.SL_ID, targetObject);

        String tempStr = (StringUtils.isEmpty(logicArea) ? "" : "<AREA>" + logicArea + "</AREA>")
                + (StringUtils.isEmpty(platform) ? "" : "<PLT>" + platform + "</PLT>")
                + (StringUtils.isEmpty(slType) ? "" : "<SLT>" + slType + "</SLT>")
                + (StringUtils.isEmpty(slId) ? "" : "<SLID>" + slId + "</SLID>");
        return tempStr;
    }

    private static Object getFieldValueByName(String fieldName, Object o) throws Exception{
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});
            return value;
        } catch (Exception e) {
            throw e;
        }
    }
}
