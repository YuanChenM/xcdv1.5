/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表by_access_account对应的ByAccessAccount</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class ByAccessAccount extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** ACCOUNT_ID */
    private Long accountId;
    /** ACCOUNT_NAME */
    private String accountName;
    /** ACCOUNT_PWD */
    private String accountPwd;
    /** TEL_NO */
    private String telNo;
    /**
     * <p>默认构造函数</p>
     */
    public ByAccessAccount() {

    }

    /**
     * <p>ACCOUNT_ID</p>
     *
     * @return the ACCOUNT_ID
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     * <p>ACCOUNT_ID</p>
     *
     * @param accountId ACCOUNT_ID
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * <p>ACCOUNT_NAME</p>
     *
     * @return the ACCOUNT_NAME
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * <p>ACCOUNT_NAME</p>
     *
     * @param accountName ACCOUNT_NAME
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * <p>ACCOUNT_PWD</p>
     *
     * @return the ACCOUNT_PWD
     */
    public String getAccountPwd() {
        return accountPwd;
    }

    /**
     * <p>ACCOUNT_PWD</p>
     *
     * @param accountPwd ACCOUNT_PWD
     */
    public void setAccountPwd(String accountPwd) {
        this.accountPwd = accountPwd;
    }

    /**
     * <p>TEL_NO</p>
     *
     * @return the TEL_NO
     */
    public String getTelNo() {
        return telNo;
    }

    /**
     * <p>TEL_NO</p>
     *
     * @param telNo TEL_NO
     */
    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

}
