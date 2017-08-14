package com.msk.seller.bean;

import com.hoperun.core.bean.BaseParam;

/**
 * Created by gyh on 2016/3/18.
 */
public class ISL231183RsParam extends BaseParam{
    private String slAccount;//卖家账号
    private String oldAccountPsd;//新密码
    private String newAccountPsd;//旧密码
    private String updId;//新密码

    /**
     * Getter method for property <tt>slAccount</tt>.
     *
     * @return property value of slAccount
     */
    public String getSlAccount() {
        return slAccount;
    }

    /**
     * Setter method for property <tt>slAccount</tt>.
     *
     * @param slAccount value to be assigned to property slAccount
     */
    public void setSlAccount(String slAccount) {
        this.slAccount = slAccount;
    }

    /**
     * Getter method for property <tt>oldAccountPsd</tt>.
     *
     * @return property value of oldAccountPsd
     */
    public String getOldAccountPsd() {
        return oldAccountPsd;
    }

    /**
     * Setter method for property <tt>oldAccountPsd</tt>.
     *
     * @param oldAccountPsd value to be assigned to property oldAccountPsd
     */
    public void setOldAccountPsd(String oldAccountPsd) {
        this.oldAccountPsd = oldAccountPsd;
    }

    /**
     * Getter method for property <tt>newAccountPsd</tt>.
     *
     * @return property value of newAccountPsd
     */
    public String getNewAccountPsd() {
        return newAccountPsd;
    }

    /**
     * Setter method for property <tt>newAccountPsd</tt>.
     *
     * @param newAccountPsd value to be assigned to property newAccountPsd
     */
    public void setNewAccountPsd(String newAccountPsd) {
        this.newAccountPsd = newAccountPsd;
    }

    /**
     * Getter method for property <tt>updId</tt>.
     *
     * @return property value of updId
     */
    public String getUpdId() {
        return updId;
    }

    /**
     * Setter method for property <tt>updId</tt>.
     *
     * @param updId value to be assigned to property updId
     */
    public void setUpdId(String updId) {
        this.updId = updId;
    }
}
