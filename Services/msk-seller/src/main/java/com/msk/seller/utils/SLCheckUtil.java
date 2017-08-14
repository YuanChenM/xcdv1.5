package com.msk.seller.utils;

import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;

import java.util.Date;

/**
 * Created by zhang_chi on 2016/9/13.
 */
public class SLCheckUtil {

    /**
     * 日期格式
     */
    public static final String FORMAT_YYYYMMDD_HHMMSS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 单个验证日期格式   格式 yyyyMMdd
     *
     * @param dateStr
     */
    public static boolean checkDate(String dateStr) {
        Date date = DateTimeUtil.parseDate(dateStr, DateTimeUtil.FORMAT_YYYYMMDD);
        if (null == date) {
            return false;
        } else {
            return true;
        }
    }

}
