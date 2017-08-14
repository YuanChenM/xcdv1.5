/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表so_cp_account_book对应的SoCpAccountBook。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SoCpAccountBook extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** ACCOUNT_BOOK_ID */
    private Long accountBookId;
    /** ACCOUNT_BOOK_NAME */
    private String accountBookName;
    /** USER_ID */
    private String userId;
    /** USER_NO */
    private String userNo;
    /** 1：平台（神农客） 2：买家  3：卖家  4：买手 */
    private Integer userRole;
    /** USER_NAME */
    private String userName;
    /** 交易收费明细未付款的应付集计 */
    private java.math.BigDecimal unpaid;
    /** 交易收费明细未收款的应收集计 */
    private java.math.BigDecimal unrecieved;
    /** 交易收费明细的待退款集计 */
    private java.math.BigDecimal forRefund;
    /** BALANCE */
    private java.math.BigDecimal balance;
    /**
     * <p>默认构造函数。</p>
     */
    public SoCpAccountBook() {

    }

    /**
     * <p>ACCOUNT_BOOK_ID。</p>
     *
     * @return the ACCOUNT_BOOK_ID
     */
    public Long getAccountBookId() {
        return accountBookId;
    }

    /**
     * <p>ACCOUNT_BOOK_ID。</p>
     *
     * @param accountBookId ACCOUNT_BOOK_ID。
     */
    public void setAccountBookId(Long accountBookId) {
        this.accountBookId = accountBookId;
    }

    /**
     * <p>ACCOUNT_BOOK_NAME。</p>
     *
     * @return the ACCOUNT_BOOK_NAME
     */
    public String getAccountBookName() {
        return accountBookName;
    }

    /**
     * <p>ACCOUNT_BOOK_NAME。</p>
     *
     * @param accountBookName ACCOUNT_BOOK_NAME。
     */
    public void setAccountBookName(String accountBookName) {
        this.accountBookName = accountBookName;
    }

    /**
     * <p>USER_ID。</p>
     *
     * @return the USER_ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * <p>USER_ID。</p>
     *
     * @param userId USER_ID。
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * <p>USER_NO。</p>
     *
     * @return the USER_NO
     */
    public String getUserNo() {
        return userNo;
    }

    /**
     * <p>USER_NO。</p>
     *
     * @param userNo USER_NO。
     */
    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    /**
     * <p>1：平台（神农客） 2：买家  3：卖家  4：买手。</p>
     *
     * @return the 1：平台（神农客） 2：买家  3：卖家  4：买手
     */
    public Integer getUserRole() {
        return userRole;
    }

    /**
     * <p>1：平台（神农客） 2：买家  3：卖家  4：买手。</p>
     *
     * @param userRole 1：平台（神农客） 2：买家  3：卖家  4：买手。
     */
    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    /**
     * <p>USER_NAME。</p>
     *
     * @return the USER_NAME
     */
    public String getUserName() {
        return userName;
    }

    /**
     * <p>USER_NAME。</p>
     *
     * @param userName USER_NAME。
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * <p>交易收费明细未付款的应付集计。</p>
     *
     * @return the 交易收费明细未付款的应付集计
     */
    public java.math.BigDecimal getUnpaid() {
        return unpaid;
    }

    /**
     * <p>交易收费明细未付款的应付集计。</p>
     *
     * @param unpaid 交易收费明细未付款的应付集计。
     */
    public void setUnpaid(java.math.BigDecimal unpaid) {
        this.unpaid = unpaid;
    }

    /**
     * <p>交易收费明细未收款的应收集计。</p>
     *
     * @return the 交易收费明细未收款的应收集计
     */
    public java.math.BigDecimal getUnrecieved() {
        return unrecieved;
    }

    /**
     * <p>交易收费明细未收款的应收集计。</p>
     *
     * @param unrecieved 交易收费明细未收款的应收集计。
     */
    public void setUnrecieved(java.math.BigDecimal unrecieved) {
        this.unrecieved = unrecieved;
    }

    /**
     * <p>交易收费明细的待退款集计。</p>
     *
     * @return the 交易收费明细的待退款集计
     */
    public java.math.BigDecimal getForRefund() {
        return forRefund;
    }

    /**
     * <p>交易收费明细的待退款集计。</p>
     *
     * @param forRefund 交易收费明细的待退款集计。
     */
    public void setForRefund(java.math.BigDecimal forRefund) {
        this.forRefund = forRefund;
    }

    /**
     * <p>BALANCE。</p>
     *
     * @return the BALANCE
     */
    public java.math.BigDecimal getBalance() {
        return balance;
    }

    /**
     * <p>BALANCE。</p>
     *
     * @param balance BALANCE。
     */
    public void setBalance(java.math.BigDecimal balance) {
        this.balance = balance;
    }

}
