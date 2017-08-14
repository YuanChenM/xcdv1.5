package com.msk.common.consts;

/**
 * Created by yang_yang on 2016/6/16.
 */
public interface BsConst {

    /**
     * 默认值
     */
    public final static class Default {
        /** 时区 */
        public final static String TIMEZONE = "GMT+8";
        /** 显示日期格式 */
        public final static String FORMAT_DATE = "yyyy-MM-dd";
        /** 显示日期时间格式 */
        public final static String FORMAT_TIME = "yyyy-MM-dd HH:mm:ss";
        /** 字符编码 */
        public final static String ENCODING = Encoding.UTF8;
    }

    /**
     * 字符编码
     */
    public final static class Encoding {
        /** UTF-8 */
        public final static String UTF8 = "UTF-8";
    }

    /**
     * 买手时间周期
     */
    interface BSCycle {
        // 冻品管家卖家关系的时间周期，按天
        int TIMECYCLE = 60;
    }

}
