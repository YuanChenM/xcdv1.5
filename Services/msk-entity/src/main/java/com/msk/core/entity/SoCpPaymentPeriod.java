/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表so_cp_payment_period对应的SoCpPaymentPeriod。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SoCpPaymentPeriod extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** PAYMENT_DAYS_ID */
    private java.lang.Long paymentDaysId;
    /** USER_ID */
    private java.lang.String userId;
    /** USER_NO */
    private java.lang.String userNo;
    /** 1：平台（神农客） 2：买家  3：卖家  4：买手 */
    private java.lang.Integer userRole;
    /** USER_NAME */
    private java.lang.String userName;
    /** COMM_DATE */
    private java.util.Date commDate;
    /** LAST_PERIOD_END */
    private java.util.Date lastPeriodEnd;
    /** PERIOD */
    private java.lang.Integer period;
    /**
     * <p>默认构造函数。</p>
     */
    public SoCpPaymentPeriod() {

    }

    /**
     * <p>PAYMENT_DAYS_ID。</p>
     *
     * @return the PAYMENT_DAYS_ID
     */
    public java.lang.Long getPaymentDaysId() {
        return paymentDaysId;
    }

    /**
     * <p>PAYMENT_DAYS_ID。</p>
     *
     * @param paymentDaysId PAYMENT_DAYS_ID。
     */
    public void setPaymentDaysId(java.lang.Long paymentDaysId) {
        this.paymentDaysId = paymentDaysId;
    }

    /**
     * <p>USER_ID。</p>
     *
     * @return the USER_ID
     */
    public java.lang.String getUserId() {
        return userId;
    }

    /**
     * <p>USER_ID。</p>
     *
     * @param userId USER_ID。
     */
    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }

    /**
     * <p>USER_NO。</p>
     *
     * @return the USER_NO
     */
    public java.lang.String getUserNo() {
        return userNo;
    }

    /**
     * <p>USER_NO。</p>
     *
     * @param userNo USER_NO。
     */
    public void setUserNo(java.lang.String userNo) {
        this.userNo = userNo;
    }

    /**
     * <p>1：平台（神农客） 2：买家  3：卖家  4：买手。</p>
     *
     * @return the 1：平台（神农客） 2：买家  3：卖家  4：买手
     */
    public java.lang.Integer getUserRole() {
        return userRole;
    }

    /**
     * <p>1：平台（神农客） 2：买家  3：卖家  4：买手。</p>
     *
     * @param userRole 1：平台（神农客） 2：买家  3：卖家  4：买手。
     */
    public void setUserRole(java.lang.Integer userRole) {
        this.userRole = userRole;
    }

    /**
     * <p>USER_NAME。</p>
     *
     * @return the USER_NAME
     */
    public java.lang.String getUserName() {
        return userName;
    }

    /**
     * <p>USER_NAME。</p>
     *
     * @param userName USER_NAME。
     */
    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }

    /**
     * <p>COMM_DATE。</p>
     *
     * @return the COMM_DATE
     */
    public java.util.Date getCommDate() {
        return commDate;
    }

    /**
     * <p>COMM_DATE。</p>
     *
     * @param commDate COMM_DATE。
     */
    public void setCommDate(java.util.Date commDate) {
        this.commDate = commDate;
    }

    /**
     * <p>LAST_PERIOD_END。</p>
     *
     * @return the LAST_PERIOD_END
     */
    public java.util.Date getLastPeriodEnd() {
        return lastPeriodEnd;
    }

    /**
     * <p>LAST_PERIOD_END。</p>
     *
     * @param lastPeriodEnd LAST_PERIOD_END。
     */
    public void setLastPeriodEnd(java.util.Date lastPeriodEnd) {
        this.lastPeriodEnd = lastPeriodEnd;
    }

    /**
     * <p>PERIOD。</p>
     *
     * @return the PERIOD
     */
    public java.lang.Integer getPeriod() {
        return period;
    }

    /**
     * <p>PERIOD。</p>
     *
     * @param period PERIOD。
     */
    public void setPeriod(java.lang.Integer period) {
        this.period = period;
    }

}
