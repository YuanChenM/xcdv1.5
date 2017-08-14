package com.msk.common.consts;

/**
 * SSO常量定义
 */
public interface SSOConstant {
    /**Config 服务中系统模块名称*/
     String SYSTEM_MODULE_NAME = "SSO";
    /**
     * 登录用户类型
     */
    interface LoginUserType{
        /**Code Master Type*/
        String TYPE = "LoginUserType";
        /**员工*/
        int EMPLOYEE = 1;
        /**卖家*/
        int SELLER = 2;
        /**买家*/
        int BUYERS = 3;
    }
}
