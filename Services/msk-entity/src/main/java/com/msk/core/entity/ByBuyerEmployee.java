/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表by_buyer_employee对应的ByBuyerEmployee</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class ByBuyerEmployee extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** ID */
    private Long id;
    /** BUYER_ID */
    private String buyerId;
    /** 参考CONSTANT表 */
    private String employeeType;
    /** EMPLOYEE_NAME */
    private String employeeName;
    /** EMPLOYEE_TEL */
    private String employeeTel;
    /** EMPLOYEE_QQ */
    private String employeeQq;
    /** EMPLOYEE_WECHAT */
    private String employeeWechat;
    /** 1：有 */
    private String busCardFlg;
    /** BUS_CARD_ID */
    private String busCardId;
    /** BUS_CARD_SUF */
    private String busCardSuf;
    /** 0：否，1：是 */
    private String contactPerson;
    /** 0：否，1：是 */
    private String purchase;
    /** 0：否，1：是 */
    private String receivePerson;
    /**
     * <p>默认构造函数</p>
     */
    public ByBuyerEmployee() {

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
     * <p>参考CONSTANT表</p>
     *
     * @return the 参考CONSTANT表
     */
    public String getEmployeeType() {
        return employeeType;
    }

    /**
     * <p>参考CONSTANT表</p>
     *
     * @param employeeType 参考CONSTANT表
     */
    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    /**
     * <p>EMPLOYEE_NAME</p>
     *
     * @return the EMPLOYEE_NAME
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * <p>EMPLOYEE_NAME</p>
     *
     * @param employeeName EMPLOYEE_NAME
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     * <p>EMPLOYEE_TEL</p>
     *
     * @return the EMPLOYEE_TEL
     */
    public String getEmployeeTel() {
        return employeeTel;
    }

    /**
     * <p>EMPLOYEE_TEL</p>
     *
     * @param employeeTel EMPLOYEE_TEL
     */
    public void setEmployeeTel(String employeeTel) {
        this.employeeTel = employeeTel;
    }

    /**
     * <p>EMPLOYEE_QQ</p>
     *
     * @return the EMPLOYEE_QQ
     */
    public String getEmployeeQq() {
        return employeeQq;
    }

    /**
     * <p>EMPLOYEE_QQ</p>
     *
     * @param employeeQq EMPLOYEE_QQ
     */
    public void setEmployeeQq(String employeeQq) {
        this.employeeQq = employeeQq;
    }

    /**
     * <p>EMPLOYEE_WECHAT</p>
     *
     * @return the EMPLOYEE_WECHAT
     */
    public String getEmployeeWechat() {
        return employeeWechat;
    }

    /**
     * <p>EMPLOYEE_WECHAT</p>
     *
     * @param employeeWechat EMPLOYEE_WECHAT
     */
    public void setEmployeeWechat(String employeeWechat) {
        this.employeeWechat = employeeWechat;
    }

    /**
     * <p>1：有</p>
     *
     * @return the 1：有
     */
    public String getBusCardFlg() {
        return busCardFlg;
    }

    /**
     * <p>1：有</p>
     *
     * @param busCardFlg 1：有
     */
    public void setBusCardFlg(String busCardFlg) {
        this.busCardFlg = busCardFlg;
    }

    /**
     * <p>BUS_CARD_ID</p>
     *
     * @return the BUS_CARD_ID
     */
    public String getBusCardId() {
        return busCardId;
    }

    /**
     * <p>BUS_CARD_ID</p>
     *
     * @param busCardId BUS_CARD_ID
     */
    public void setBusCardId(String busCardId) {
        this.busCardId = busCardId;
    }

    /**
     * <p>BUS_CARD_SUF</p>
     *
     * @return the BUS_CARD_SUF
     */
    public String getBusCardSuf() {
        return busCardSuf;
    }

    /**
     * <p>BUS_CARD_SUF</p>
     *
     * @param busCardSuf BUS_CARD_SUF
     */
    public void setBusCardSuf(String busCardSuf) {
        this.busCardSuf = busCardSuf;
    }

    /**
     * <p>0：否，1：是</p>
     *
     * @return the 0：否，1：是
     */
    public String getContactPerson() {
        return contactPerson;
    }

    /**
     * <p>0：否，1：是</p>
     *
     * @param contactPerson 0：否，1：是
     */
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    /**
     * <p>0：否，1：是</p>
     *
     * @return the 0：否，1：是
     */
    public String getPurchase() {
        return purchase;
    }

    /**
     * <p>0：否，1：是</p>
     *
     * @param purchase 0：否，1：是
     */
    public void setPurchase(String purchase) {
        this.purchase = purchase;
    }

    /**
     * <p>0：否，1：是</p>
     *
     * @return the 0：否，1：是
     */
    public String getReceivePerson() {
        return receivePerson;
    }

    /**
     * <p>0：否，1：是</p>
     *
     * @param receivePerson 0：否，1：是
     */
    public void setReceivePerson(String receivePerson) {
        this.receivePerson = receivePerson;
    }

}
