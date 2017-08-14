/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表by_buyer_account对应的ByBuyerAccount</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class ByBuyerAccount extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** ID */
    private Long id;
    /** BUYER_ID */
    private String buyerId;
    /** TEL_NO */
    private String telNo;
    /** ACCOUNT_NAME */
    private String accountName;
    /** ACCOUNT_PASS */
    private String accountPass;
    /**
     * <p>默认构造函数</p>
     */
    public ByBuyerAccount() {

    }

    /**
     * <p>ID</p>
     *
     * @return the ID
     */
    public Long getId() {
        return id;
    }

    /**
     * <p>ID</p>
     *
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * <p>BUYER_ID</p>
     *
     * @return the BUYER_ID
     */
    public String getBuyerId() {
        return buyerId;
    }

    /**
     * <p>BUYER_ID</p>
     *
     * @param buyerId BUYER_ID
     */
    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
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
     * <p>ACCOUNT_PASS</p>
     *
     * @return the ACCOUNT_PASS
     */
    public String getAccountPass() {
        return accountPass;
    }

    /**
     * <p>ACCOUNT_PASS</p>
     *
     * @param accountPass ACCOUNT_PASS
     */
    public void setAccountPass(String accountPass) {
        this.accountPass = accountPass;
    }

}
