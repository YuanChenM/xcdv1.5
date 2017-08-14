/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_ep_cap对应的SlEpCap。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlEpCap extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 企业ID */
    private Long epId;
    /** 厂区_总资产 */
    private java.math.BigDecimal ftyAsset;
    /** 厂区_注册资本 */
    private java.math.BigDecimal ftyRegCapital;
    /** 厂区_占地面积 */
    private java.math.BigDecimal ftyLandArea;
    /** 厂区_厂房面积 */
    private java.math.BigDecimal ftyFloorArea;
    /** 厂区_主要设备 */
    private String ftyEquipment;
    /** 厂区_设计产能 */
    private String ftyDesignCap;
    /** 厂区_实际产能 */
    private String ftyActualCap;
    /** 厂区_外贸销售占比 */
    private java.math.BigDecimal ftyFtRate;
    /** 厂区_直销占比 */
    private java.math.BigDecimal ftyDsRate;
    /** 厂区_代理销售占比 */
    private java.math.BigDecimal ftyAsRate;
    /** 库容概括_原料库容 */
    private java.math.BigDecimal scapMaterial;
    /** 库容概括_成品库容 */
    private java.math.BigDecimal scapProduct;
    /** 实验室_面积 */
    private java.math.BigDecimal labArea;
    /** 实验室_功能 */
    private String labFunction;
    /** 实验室_投资 */
    private java.math.BigDecimal labInvestment;
    /** 实验室_人员 */
    private Integer labMember;
    /** 检测设备_主要设备及用途 */
    private String ddEquipment;
    /**
     * <p>默认构造函数。</p>
     */
    public SlEpCap() {

    }

    /**
     * <p>企业ID。</p>
     *
     * @return the 企业ID
     */
    public Long getEpId() {
        return epId;
    }

    /**
     * <p>企业ID。</p>
     *
     * @param epId 企业ID。
     */
    public void setEpId(Long epId) {
        this.epId = epId;
    }

    /**
     * <p>厂区_总资产。</p>
     *
     * @return the 厂区_总资产
     */
    public java.math.BigDecimal getFtyAsset() {
        return ftyAsset;
    }

    /**
     * <p>厂区_总资产。</p>
     *
     * @param ftyAsset 厂区_总资产。
     */
    public void setFtyAsset(java.math.BigDecimal ftyAsset) {
        this.ftyAsset = ftyAsset;
    }

    /**
     * <p>厂区_注册资本。</p>
     *
     * @return the 厂区_注册资本
     */
    public java.math.BigDecimal getFtyRegCapital() {
        return ftyRegCapital;
    }

    /**
     * <p>厂区_注册资本。</p>
     *
     * @param ftyRegCapital 厂区_注册资本。
     */
    public void setFtyRegCapital(java.math.BigDecimal ftyRegCapital) {
        this.ftyRegCapital = ftyRegCapital;
    }

    /**
     * <p>厂区_占地面积。</p>
     *
     * @return the 厂区_占地面积
     */
    public java.math.BigDecimal getFtyLandArea() {
        return ftyLandArea;
    }

    /**
     * <p>厂区_占地面积。</p>
     *
     * @param ftyLandArea 厂区_占地面积。
     */
    public void setFtyLandArea(java.math.BigDecimal ftyLandArea) {
        this.ftyLandArea = ftyLandArea;
    }

    /**
     * <p>厂区_厂房面积。</p>
     *
     * @return the 厂区_厂房面积
     */
    public java.math.BigDecimal getFtyFloorArea() {
        return ftyFloorArea;
    }

    /**
     * <p>厂区_厂房面积。</p>
     *
     * @param ftyFloorArea 厂区_厂房面积。
     */
    public void setFtyFloorArea(java.math.BigDecimal ftyFloorArea) {
        this.ftyFloorArea = ftyFloorArea;
    }

    /**
     * <p>厂区_主要设备。</p>
     *
     * @return the 厂区_主要设备
     */
    public String getFtyEquipment() {
        return ftyEquipment;
    }

    /**
     * <p>厂区_主要设备。</p>
     *
     * @param ftyEquipment 厂区_主要设备。
     */
    public void setFtyEquipment(String ftyEquipment) {
        this.ftyEquipment = ftyEquipment;
    }

    /**
     * <p>厂区_设计产能。</p>
     *
     * @return the 厂区_设计产能
     */
    public String getFtyDesignCap() {
        return ftyDesignCap;
    }

    /**
     * <p>厂区_设计产能。</p>
     *
     * @param ftyDesignCap 厂区_设计产能。
     */
    public void setFtyDesignCap(String ftyDesignCap) {
        this.ftyDesignCap = ftyDesignCap;
    }

    /**
     * <p>厂区_实际产能。</p>
     *
     * @return the 厂区_实际产能
     */
    public String getFtyActualCap() {
        return ftyActualCap;
    }

    /**
     * <p>厂区_实际产能。</p>
     *
     * @param ftyActualCap 厂区_实际产能。
     */
    public void setFtyActualCap(String ftyActualCap) {
        this.ftyActualCap = ftyActualCap;
    }

    /**
     * <p>厂区_外贸销售占比。</p>
     *
     * @return the 厂区_外贸销售占比
     */
    public java.math.BigDecimal getFtyFtRate() {
        return ftyFtRate;
    }

    /**
     * <p>厂区_外贸销售占比。</p>
     *
     * @param ftyFtRate 厂区_外贸销售占比。
     */
    public void setFtyFtRate(java.math.BigDecimal ftyFtRate) {
        this.ftyFtRate = ftyFtRate;
    }

    /**
     * <p>厂区_直销占比。</p>
     *
     * @return the 厂区_直销占比
     */
    public java.math.BigDecimal getFtyDsRate() {
        return ftyDsRate;
    }

    /**
     * <p>厂区_直销占比。</p>
     *
     * @param ftyDsRate 厂区_直销占比。
     */
    public void setFtyDsRate(java.math.BigDecimal ftyDsRate) {
        this.ftyDsRate = ftyDsRate;
    }

    /**
     * <p>厂区_代理销售占比。</p>
     *
     * @return the 厂区_代理销售占比
     */
    public java.math.BigDecimal getFtyAsRate() {
        return ftyAsRate;
    }

    /**
     * <p>厂区_代理销售占比。</p>
     *
     * @param ftyAsRate 厂区_代理销售占比。
     */
    public void setFtyAsRate(java.math.BigDecimal ftyAsRate) {
        this.ftyAsRate = ftyAsRate;
    }

    /**
     * <p>库容概括_原料库容。</p>
     *
     * @return the 库容概括_原料库容
     */
    public java.math.BigDecimal getScapMaterial() {
        return scapMaterial;
    }

    /**
     * <p>库容概括_原料库容。</p>
     *
     * @param scapMaterial 库容概括_原料库容。
     */
    public void setScapMaterial(java.math.BigDecimal scapMaterial) {
        this.scapMaterial = scapMaterial;
    }

    /**
     * <p>库容概括_成品库容。</p>
     *
     * @return the 库容概括_成品库容
     */
    public java.math.BigDecimal getScapProduct() {
        return scapProduct;
    }

    /**
     * <p>库容概括_成品库容。</p>
     *
     * @param scapProduct 库容概括_成品库容。
     */
    public void setScapProduct(java.math.BigDecimal scapProduct) {
        this.scapProduct = scapProduct;
    }

    /**
     * <p>实验室_面积。</p>
     *
     * @return the 实验室_面积
     */
    public java.math.BigDecimal getLabArea() {
        return labArea;
    }

    /**
     * <p>实验室_面积。</p>
     *
     * @param labArea 实验室_面积。
     */
    public void setLabArea(java.math.BigDecimal labArea) {
        this.labArea = labArea;
    }

    /**
     * <p>实验室_功能。</p>
     *
     * @return the 实验室_功能
     */
    public String getLabFunction() {
        return labFunction;
    }

    /**
     * <p>实验室_功能。</p>
     *
     * @param labFunction 实验室_功能。
     */
    public void setLabFunction(String labFunction) {
        this.labFunction = labFunction;
    }

    /**
     * <p>实验室_投资。</p>
     *
     * @return the 实验室_投资
     */
    public java.math.BigDecimal getLabInvestment() {
        return labInvestment;
    }

    /**
     * <p>实验室_投资。</p>
     *
     * @param labInvestment 实验室_投资。
     */
    public void setLabInvestment(java.math.BigDecimal labInvestment) {
        this.labInvestment = labInvestment;
    }

    /**
     * <p>实验室_人员。</p>
     *
     * @return the 实验室_人员
     */
    public Integer getLabMember() {
        return labMember;
    }

    /**
     * <p>实验室_人员。</p>
     *
     * @param labMember 实验室_人员。
     */
    public void setLabMember(Integer labMember) {
        this.labMember = labMember;
    }

    /**
     * <p>检测设备_主要设备及用途。</p>
     *
     * @return the 检测设备_主要设备及用途
     */
    public String getDdEquipment() {
        return ddEquipment;
    }

    /**
     * <p>检测设备_主要设备及用途。</p>
     *
     * @param ddEquipment 检测设备_主要设备及用途。
     */
    public void setDdEquipment(String ddEquipment) {
        this.ddEquipment = ddEquipment;
    }

}
