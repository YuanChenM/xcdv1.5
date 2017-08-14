/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_bs_bankaccount对应的SlBsBankaccount。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlBsBankaccount extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 账号ID */
    private java.lang.Long accountId;
    /** 买手ID */
    private java.lang.String slCode;
    /** 开户名 */
    private java.lang.String accountName;
    /** 开户行 */
    private java.lang.String bankName;
    /** 账号 */
    private java.lang.String bankNo;
    /** 0：储蓄卡；1：信用卡 */
    private java.lang.String cardType;
    /** 开户行手机号 */
    private java.lang.String bankTel;
    /**
     * <p>默认构造函数。</p>
     */
    public SlBsBankaccount() {

    }

    /**
     * <p>账号ID。</p>
     *
     * @return the 账号ID
     */
    public java.lang.Long getAccountId() {
        return accountId;
    }

    /**
     * <p>账号ID。</p>
     *
     * @param accountId 账号ID。
     */
    public void setAccountId(java.lang.Long accountId) {
        this.accountId = accountId;
    }

    /**
     * <p>买手ID。</p>
     *
     * @return the 买手ID
     */
    public java.lang.String getSlCode() {
        return slCode;
    }

    /**
     * <p>买手ID。</p>
     *
     * @param slCode 买手ID。
     */
    public void setSlCode(java.lang.String slCode) {
        this.slCode = slCode;
    }

    /**
     * <p>开户名。</p>
     *
     * @return the 开户名
     */
    public java.lang.String getAccountName() {
        return accountName;
    }

    /**
     * <p>开户名。</p>
     *
     * @param accountName 开户名。
     */
    public void setAccountName(java.lang.String accountName) {
        this.accountName = accountName;
    }

    /**
     * <p>开户行。</p>
     *
     * @return the 开户行
     */
    public java.lang.String getBankName() {
        return bankName;
    }

    /**
     * <p>开户行。</p>
     *
     * @param bankName 开户行。
     */
    public void setBankName(java.lang.String bankName) {
        this.bankName = bankName;
    }

    /**
     * <p>账号。</p>
     *
     * @return the 账号
     */
    public java.lang.String getBankNo() {
        return bankNo;
    }

    /**
     * <p>账号。</p>
     *
     * @param bankNo 账号。
     */
    public void setBankNo(java.lang.String bankNo) {
        this.bankNo = bankNo;
    }

    /**
     * <p>0：储蓄卡；1：信用卡。</p>
     *
     * @return the 0：储蓄卡；1：信用卡
     */
    public java.lang.String getCardType() {
        return cardType;
    }

    /**
     * <p>0：储蓄卡；1：信用卡。</p>
     *
     * @param cardType 0：储蓄卡；1：信用卡。
     */
    public void setCardType(java.lang.String cardType) {
        this.cardType = cardType;
    }

    /**
     * <p>开户行手机号。</p>
     *
     * @return the 开户行手机号
     */
    public java.lang.String getBankTel() {
        return bankTel;
    }

    /**
     * <p>开户行手机号。</p>
     *
     * @param bankTel 开户行手机号。
     */
    public void setBankTel(java.lang.String bankTel) {
        this.bankTel = bankTel;
    }

}
