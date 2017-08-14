package com.msk.seller.bean;

import com.msk.core.entity.BaseEntity;

import java.io.Serializable;

/**
 * Created by zhang_chi on 2016/4/29.
 */
public class ISL231105RsResult extends BaseEntity {

    /** 卖家账号 */
    private String slAccount;
    /** 登录手机号码 */
    private String slTel;
    /** 卖家显示名称 */
    private String slShowName;
    /** 联系人姓名 */
    private String slContact;
    /** 登录密码 */
    private String accountPsd;
    /** 图片路径 */
    private String accountImg;
    /** 认证状态  0:未认证,1:认证中,2:已认证 */
    private Integer authStatus;
    /** 卖家主分类*/
    private Integer slMainClass;

    /** 员工编号 */
    private String emplNo;
    /** 员工名称 */
    private String emplName;
    /** 员工ID */
    private String emplId;
    /** 登录密码 */
    private String loginPwd;
    /** 状态 */
    private int status;

    public String getSlAccount() {
        return slAccount;
    }

    public void setSlAccount(String slAccount) {
        this.slAccount = slAccount;
    }

    public String getSlTel() {
        return slTel;
    }

    public void setSlTel(String slTel) {
        this.slTel = slTel;
    }

    public String getSlShowName() {
        return slShowName;
    }

    public void setSlShowName(String slShowName) {
        this.slShowName = slShowName;
    }

    public String getSlContact() {
        return slContact;
    }

    public void setSlContact(String slContact) {
        this.slContact = slContact;
    }

    public String getAccountPsd() {
        return accountPsd;
    }

    public void setAccountPsd(String accountPsd) {
        this.accountPsd = accountPsd;
    }

    public Integer getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(Integer authStatus) {
        this.authStatus = authStatus;
    }

    public Integer getSlMainClass() {
        return slMainClass;
    }

    public void setSlMainClass(Integer slMainClass) {
        this.slMainClass = slMainClass;
    }

    public String getEmplNo() {
        return emplNo;
    }

    public void setEmplNo(String emplNo) {
        this.emplNo = emplNo;
    }

    public String getEmplName() {
        return emplName;
    }

    public void setEmplName(String emplName) {
        this.emplName = emplName;
    }

    public String getEmplId() {
        return emplId;
    }

    public void setEmplId(String emplId) {
        this.emplId = emplId;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
