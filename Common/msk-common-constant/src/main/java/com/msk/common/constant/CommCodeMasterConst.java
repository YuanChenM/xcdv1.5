package com.msk.common.constant;

/**
 * 共通产量定义
 * @author jiang_nan
 * @version 1.0
 **/
public class CommCodeMasterConst {
    /**
     * YES NO
     */
    public interface YESNO{
        String TYPE = "YESNO";
        /**NO:0*/
        int NO = 0;
        /**YES:1*/
        int YES = 1;
    }

    /**
     * 登录用户类型
     */
    public interface LoginUserType{
        String TYPE = "LoginUserType";
        /**系统用户*/
        String SYSTEM_USER_TYPE = "1";
        /**供应商用户*/
        String SUPPLIER_USER_TYPE = "2";
    }

    /**
     * 系统Code定义
     */
    public interface SystemCode {
        String SYSTEM_CODE_BY = "302";
        String SYSTEM_CODE_PD = "304";
        String SYSTEM_CODE_SL = "303";
        String SYSTEM_CODE_SO = "305";
        String SYSTEM_CODE_MS = "301";
        String SYSTEM_CODE_DS = "307";
        String SYSTEM_CODE_MD = "399";
        String SYSTEM_CODE_BS = "210";
        String SYSTEM_CODE_SNK = "101";
        String SYSTEM_CODE_MSK = "102";
        String SYSTEM_CODE_WSC = "110";
        String SYSTEM_CODE_BYAPP = "207";
    }
}
