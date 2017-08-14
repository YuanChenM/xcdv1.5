package com.hoperun.web.utils;



import com.hoperun.core.exception.SystemException;
import com.hoperun.core.utils.StringUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie Utils.
 * @author jiang_nan
 * @version 1.0
 **/
public class CookieUtils {
    /**
     * 设置Cookie Value
     * @param response Response
     * @param key Cookie Key
     * @param value Cookie Value.
     */
    public static void setCookieValue(HttpServletResponse response,String key,String value){
        Cookie cookie = new Cookie(key,value);
        response.addCookie(cookie);
    }

    /**
     * 获得Cookie对应的Value
     * @param request Http Servlet Request
     * @param key Cookie Key
     * @return Value
     */
    public static String getCookieValue(HttpServletRequest request,String key){
        if(StringUtil.isEmpty(key)){
            throw new SystemException("Cookie对应的Key不能为空");
        }
        Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组
        for(Cookie cookie : cookies){
            String name = cookie.getName();
            if(key.equals(name)){
                return cookie.getValue();
            }
        }
        // throw new SystemException("没有找到cookie对于的Value");
        return null;
    }

}
