package com.msk.so.utils;

import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by zhang_chi on 2016/6/6.
 */
public class CheckUtil {

    /**
     * 日期格式
     */
    public static final String FORMAT_YYYYMMDD_HHMMSS = "yyyy-MM-dd HH:mm:ss";


    /**
     * JSP日期控件 比较日期大小  格式 yyyy-MM-dd
     *
     * @param startDateStr
     * @param endDateStr
     */
    public static boolean checkDate(String startDateStr, String endDateStr) {
        Date startDate = null;
        Date endDate = null;
        if (!StringUtil.isNullOrEmpty(startDateStr)) {
            startDate = DateTimeUtil.parseDate(startDateStr, DateTimeUtil.FORMAT_DATE_YYYYMMDD);
            if (null == startDate) {
                return false;
            }
        }
        if (!StringUtil.isNullOrEmpty(endDateStr)) {
            endDate = DateTimeUtil.parseDate(endDateStr, DateTimeUtil.FORMAT_DATE_YYYYMMDD);
            if (null == endDate) {
                return false;
            }
        }

        if (startDate != null && endDate != null && startDate.after(endDate)) {
            return false;
        }

        return true;
    }

    /**
     * 比较数字大小
     *
     * @param startNumStr
     * @param endNumStr
     */
    public static boolean checkNum(String startNumStr, String endNumStr) {
        BigDecimal startNum = null;
        BigDecimal endNum = null;
        if (!StringUtil.isNullOrEmpty(startNumStr)) {
            if (!StringUtil.isNumeric(startNumStr)) {
                return false;
            } else {
                startNum = new BigDecimal(startNumStr);
            }
        }
        if (!StringUtil.isNullOrEmpty(endNumStr)) {
            if (!StringUtil.isNumeric(endNumStr)) {
                return false;
            } else {
                endNum = new BigDecimal(endNumStr);
            }
        }

        if (startNum != null && endNum != null && (startNum.compareTo(endNum) > 0)) {
            return false;
        }

        return true;
    }

    /**
     * 将字符串转码
     * ISO-8859-1为国际通用url编码
     *
     * @param target
     * @return
     */
    public static String convert(String target) {
        try {
            if (target.equals(new String(target.getBytes("iso8859-1"), "iso8859-1"))) {
                return new String(target.getBytes("iso8859-1"), "utf-8");
            } else {
                return target;
            }
        } catch (UnsupportedEncodingException e) {
            return target;
        }
    }

}
