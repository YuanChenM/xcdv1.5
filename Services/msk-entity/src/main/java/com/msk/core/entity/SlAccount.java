/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_account对应的SlAccount。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlAccount extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 用于登录的卖家账号 */
    private String slAccount;
    /** 也可以用于登录 */
    private String slTel;
    /** 卖家显示名称 */
    private String slShowName;
    /** 联系人姓名 */
    private String slContact;
    /** 加密后的值 */
    private String accountPsd;
    /** 图片路径 */
    private String accountImg;
    /** 0:未认证,1:认证中,2:已认证 */
    private Integer authStatus;
    /** 注册来源 */
    private String fromFlg;
    /**
     * <p>默认构造函数。</p>
     */
    public SlAccount() {

    }

    /**
     * <p>用于登录的卖家账号。</p>
     *
     * @return the 用于登录的卖家账号
     */
    public String getSlAccount() {
        return slAccount;
    }

    /**
     * <p>用于登录的卖家账号。</p>
     *
     * @param slAccount 用于登录的卖家账号。
     */
    public void setSlAccount(String slAccount) {
        this.slAccount = slAccount;
    }

    /**
     * <p>也可以用于登录。</p>
     *
     * @return the 也可以用于登录
     */
    public String getSlTel() {
        return slTel;
    }

    /**
     * <p>也可以用于登录。</p>
     *
     * @param slTel 也可以用于登录。
     */
    public void setSlTel(String slTel) {
        this.slTel = slTel;
    }

    /**
     * <p>卖家显示名称。</p>
     *
     * @return the 卖家显示名称
     */
    public String getSlShowName() {
        return slShowName;
    }

    /**
     * <p>卖家显示名称。</p>
     *
     * @param slShowName 卖家显示名称。
     */
    public void setSlShowName(String slShowName) {
        this.slShowName = slShowName;
    }

    /**
     * <p>联系人姓名。</p>
     *
     * @return the 联系人姓名
     */
    public String getSlContact() {
        return slContact;
    }

    /**
     * <p>联系人姓名。</p>
     *
     * @param slContact 联系人姓名。
     */
    public void setSlContact(String slContact) {
        this.slContact = slContact;
    }

    /**
     * <p>加密后的值。</p>
     *
     * @return the 加密后的值
     */
    public String getAccountPsd() {
        return accountPsd;
    }

    /**
     * <p>加密后的值。</p>
     *
     * @param accountPsd 加密后的值。
     */
    public void setAccountPsd(String accountPsd) {
        this.accountPsd = accountPsd;
    }

    /**
     * <p>图片路径。</p>
     *
     * @return the 图片路径
     */
    public String getAccountImg() {
        return accountImg;
    }

    /**
     * <p>图片路径。</p>
     *
     * @param accountImg 图片路径。
     */
    public void setAccountImg(String accountImg) {
        this.accountImg = accountImg;
    }

    /**
     * <p>0:未认证,1:认证中,2:已认证。</p>
     *
     * @return the 0:未认证,1:认证中,2:已认证
     */
    public Integer getAuthStatus() {
        return authStatus;
    }

    /**
     * <p>0:未认证,1:认证中,2:已认证。</p>
     *
     * @param authStatus 0:未认证,1:认证中,2:已认证。
     */
    public void setAuthStatus(Integer authStatus) {
        this.authStatus = authStatus;
    }

    /**
     * <p>注册来源。</p>
     *
     * @return the 注册来源
     */
    public String getFromFlg() {
        return fromFlg;
    }

    /**
     * <p>注册来源。</p>
     *
     * @param fromFlg 注册来源。
     */
    public void setFromFlg(String fromFlg) {
        this.fromFlg = fromFlg;
    }

}
