package com.msk.common.validation.impl;

import java.lang.annotation.Annotation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.msk.common.validation.BaseValidationService;

/**
 * Created by jackjiang on 16/8/8.
 */
public class MobileValidationService implements BaseValidationService{
    @Override
    public String getValidationMessage(Annotation annotation,Object value) {
        return null;
    }
    /**
     * 手机号验证
     *
     * @param  str
     * @return 验证通过返回true
     */
    public static boolean isMobile(String str) {
        Pattern pattern = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
