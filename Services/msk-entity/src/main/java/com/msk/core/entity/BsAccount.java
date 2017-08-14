/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表bs_account对应的BsAccount。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class BsAccount extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 用于登录的卖家账号 */
    private java.lang.String slAccount;
    /** 也可以用于登录 */
    private java.lang.String slTel;
    /** SL_SHOW_NAME */
    private java.lang.String slShowName;
    /** SL_CONTACT */
    private java.lang.String slContact;
    /** 加密后的值 */
    private java.lang.String accountPsd;
    /** 图片路径 */
    private java.lang.String accountImg;
    /** 0:未认证,1:认证中,2:已认证 */
    private java.lang.Integer authStatus;
    /** FROM_FLG */
    private java.lang.String fromFlg;
    /**
     * <p>默认构造函数。</p>
     */
    public BsAccount() {

    }

    /**
     * <p>用于登录的卖家账号。</p>
     *
     * @return the 用于登录的卖家账号
     */
    public java.lang.String getSlAccount() {
        return slAccount;
    }

    /**
     * <p>用于登录的卖家账号。</p>
     *
     * @param slAccount 用于登录的卖家账号。
     */
    public void setSlAccount(java.lang.String slAccount) {
        this.slAccount = slAccount;
    }

    /**
     * <p>也可以用于登录。</p>
     *
     * @return the 也可以用于登录
     */
    public java.lang.String getSlTel() {
        return slTel;
    }

    /**
     * <p>也可以用于登录。</p>
     *
     * @param slTel 也可以用于登录。
     */
    public void setSlTel(java.lang.String slTel) {
        this.slTel = slTel;
    }

    /**
     * <p>SL_SHOW_NAME。</p>
     *
     * @return the SL_SHOW_NAME
     */
    public java.lang.String getSlShowName() {
        return slShowName;
    }

    /**
     * <p>SL_SHOW_NAME。</p>
     *
     * @param slShowName SL_SHOW_NAME。
     */
    public void setSlShowName(java.lang.String slShowName) {
        this.slShowName = slShowName;
    }

    /**
     * <p>SL_CONTACT。</p>
     *
     * @return the SL_CONTACT
     */
    public java.lang.String getSlContact() {
        return slContact;
    }

    /**
     * <p>SL_CONTACT。</p>
     *
     * @param slContact SL_CONTACT。
     */
    public void setSlContact(java.lang.String slContact) {
        this.slContact = slContact;
    }

    /**
     * <p>加密后的值。</p>
     *
     * @return the 加密后的值
     */
    public java.lang.String getAccountPsd() {
        return accountPsd;
    }

    /**
     * <p>加密后的值。</p>
     *
     * @param accountPsd 加密后的值。
     */
    public void setAccountPsd(java.lang.String accountPsd) {
        this.accountPsd = accountPsd;
    }

    /**
     * <p>图片路径。</p>
     *
     * @return the 图片路径
     */
    public java.lang.String getAccountImg() {
        return accountImg;
    }

    /**
     * <p>图片路径。</p>
     *
     * @param accountImg 图片路径。
     */
    public void setAccountImg(java.lang.String accountImg) {
        this.accountImg = accountImg;
    }

    /**
     * <p>0:未认证,1:认证中,2:已认证。</p>
     *
     * @return the 0:未认证,1:认证中,2:已认证
     */
    public java.lang.Integer getAuthStatus() {
        return authStatus;
    }

    /**
     * <p>0:未认证,1:认证中,2:已认证。</p>
     *
     * @param authStatus 0:未认证,1:认证中,2:已认证。
     */
    public void setAuthStatus(java.lang.Integer authStatus) {
        this.authStatus = authStatus;
    }

    /**
     * <p>FROM_FLG。</p>
     *
     * @return the FROM_FLG
     */
    public java.lang.String getFromFlg() {
        return fromFlg;
    }

    /**
     * <p>FROM_FLG。</p>
     *
     * @param fromFlg FROM_FLG。
     */
    public void setFromFlg(java.lang.String fromFlg) {
        this.fromFlg = fromFlg;
    }

}
