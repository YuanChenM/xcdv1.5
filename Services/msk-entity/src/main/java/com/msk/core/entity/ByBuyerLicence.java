/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表by_buyer_licence对应的ByBuyerLicence</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class ByBuyerLicence extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** ID */
    private Long id;
    /** BUYER_ID */
    private String buyerId;
    /** 参考CONSTANT表 */
    private String licName;
    /** LIC_NUMBER */
    private String licNumber;
    /** LEGAL_NAME */
    private String legalName;
    /** LEGAL_LIC_TYPE */
    private String legalLicType;
    /** LEGAL_LIC_NUMBER */
    private String legalLicNumber;
    /**
     * <p>默认构造函数</p>
     */
    public ByBuyerLicence() {

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
    public String getLicName() {
        return licName;
    }

    /**
     * <p>参考CONSTANT表</p>
     *
     * @param licName 参考CONSTANT表
     */
    public void setLicName(String licName) {
        this.licName = licName;
    }

    /**
     * <p>LIC_NUMBER</p>
     *
     * @return the LIC_NUMBER
     */
    public String getLicNumber() {
        return licNumber;
    }

    /**
     * <p>LIC_NUMBER</p>
     *
     * @param licNumber LIC_NUMBER
     */
    public void setLicNumber(String licNumber) {
        this.licNumber = licNumber;
    }

    /**
     * <p>LEGAL_NAME</p>
     *
     * @return the LEGAL_NAME
     */
    public String getLegalName() {
        return legalName;
    }

    /**
     * <p>LEGAL_NAME</p>
     *
     * @param legalName LEGAL_NAME
     */
    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    /**
     * <p>LEGAL_LIC_TYPE</p>
     *
     * @return the LEGAL_LIC_TYPE
     */
    public String getLegalLicType() {
        return legalLicType;
    }

    /**
     * <p>LEGAL_LIC_TYPE</p>
     *
     * @param legalLicType LEGAL_LIC_TYPE
     */
    public void setLegalLicType(String legalLicType) {
        this.legalLicType = legalLicType;
    }

    /**
     * <p>LEGAL_LIC_NUMBER</p>
     *
     * @return the LEGAL_LIC_NUMBER
     */
    public String getLegalLicNumber() {
        return legalLicNumber;
    }

    /**
     * <p>LEGAL_LIC_NUMBER</p>
     *
     * @param legalLicNumber LEGAL_LIC_NUMBER
     */
    public void setLegalLicNumber(String legalLicNumber) {
        this.legalLicNumber = legalLicNumber;
    }

}
