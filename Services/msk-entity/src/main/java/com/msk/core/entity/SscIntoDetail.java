/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表ssc_into_detail对应的SscIntoDetail。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SscIntoDetail extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 详细信息ID */
    private java.lang.Long id;
    /** 入库单ID */
    private java.lang.Long intoId;
    /** 入库单编号 */
    private java.lang.Long intoCode;
    /** 产品属性码 */
    private java.lang.String pdCode;
    /** 发货产品重量 */
    private java.math.BigDecimal sendWeight;
    /** 入库产品重量 */
    private java.math.BigDecimal receiveWeight;
    /** 产品单价 */
    private java.math.BigDecimal unitPrice;
    /** 发货产品箱数 */
    private java.lang.Integer sendBox;
    /** 入库产品箱数 */
    private java.lang.Integer receiveBox;
    /** 入库产品特征名称 */
    private java.lang.String receiveFeatureName;
    /** 入库产品净重名称 */
    private java.lang.String receiveWeightName;
    /** 备注 */
    private java.lang.String remark;
    /** 生产日期 */
    private java.util.Date productDate;
    /**
     * <p>默认构造函数。</p>
     */
    public SscIntoDetail() {

    }

    /**
     * <p>详细信息ID。</p>
     *
     * @return the 详细信息ID
     */
    public java.lang.Long getId() {
        return id;
    }

    /**
     * <p>详细信息ID。</p>
     *
     * @param id 详细信息ID。
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }

    /**
     * <p>入库单ID。</p>
     *
     * @return the 入库单ID
     */
    public java.lang.Long getIntoId() {
        return intoId;
    }

    /**
     * <p>入库单ID。</p>
     *
     * @param intoId 入库单ID。
     */
    public void setIntoId(java.lang.Long intoId) {
        this.intoId = intoId;
    }

    /**
     * <p>入库单编号。</p>
     *
     * @return the 入库单编号
     */
    public java.lang.Long getIntoCode() {
        return intoCode;
    }

    /**
     * <p>入库单编号。</p>
     *
     * @param intoCode 入库单编号。
     */
    public void setIntoCode(java.lang.Long intoCode) {
        this.intoCode = intoCode;
    }

    /**
     * <p>产品属性码。</p>
     *
     * @return the 产品属性码
     */
    public java.lang.String getPdCode() {
        return pdCode;
    }

    /**
     * <p>产品属性码。</p>
     *
     * @param pdCode 产品属性码。
     */
    public void setPdCode(java.lang.String pdCode) {
        this.pdCode = pdCode;
    }

    /**
     * <p>发货产品重量。</p>
     *
     * @return the 发货产品重量
     */
    public java.math.BigDecimal getSendWeight() {
        return sendWeight;
    }

    /**
     * <p>发货产品重量。</p>
     *
     * @param sendWeight 发货产品重量。
     */
    public void setSendWeight(java.math.BigDecimal sendWeight) {
        this.sendWeight = sendWeight;
    }

    /**
     * <p>入库产品重量。</p>
     *
     * @return the 入库产品重量
     */
    public java.math.BigDecimal getReceiveWeight() {
        return receiveWeight;
    }

    /**
     * <p>入库产品重量。</p>
     *
     * @param receiveWeight 入库产品重量。
     */
    public void setReceiveWeight(java.math.BigDecimal receiveWeight) {
        this.receiveWeight = receiveWeight;
    }

    /**
     * <p>产品单价。</p>
     *
     * @return the 产品单价
     */
    public java.math.BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * <p>产品单价。</p>
     *
     * @param unitPrice 产品单价。
     */
    public void setUnitPrice(java.math.BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * <p>发货产品箱数。</p>
     *
     * @return the 发货产品箱数
     */
    public java.lang.Integer getSendBox() {
        return sendBox;
    }

    /**
     * <p>发货产品箱数。</p>
     *
     * @param sendBox 发货产品箱数。
     */
    public void setSendBox(java.lang.Integer sendBox) {
        this.sendBox = sendBox;
    }

    /**
     * <p>入库产品箱数。</p>
     *
     * @return the 入库产品箱数
     */
    public java.lang.Integer getReceiveBox() {
        return receiveBox;
    }

    /**
     * <p>入库产品箱数。</p>
     *
     * @param receiveBox 入库产品箱数。
     */
    public void setReceiveBox(java.lang.Integer receiveBox) {
        this.receiveBox = receiveBox;
    }

    /**
     * <p>入库产品特征名称。</p>
     *
     * @return the 入库产品特征名称
     */
    public java.lang.String getReceiveFeatureName() {
        return receiveFeatureName;
    }

    /**
     * <p>入库产品特征名称。</p>
     *
     * @param receiveFeatureName 入库产品特征名称。
     */
    public void setReceiveFeatureName(java.lang.String receiveFeatureName) {
        this.receiveFeatureName = receiveFeatureName;
    }

    /**
     * <p>入库产品净重名称。</p>
     *
     * @return the 入库产品净重名称
     */
    public java.lang.String getReceiveWeightName() {
        return receiveWeightName;
    }

    /**
     * <p>入库产品净重名称。</p>
     *
     * @param receiveWeightName 入库产品净重名称。
     */
    public void setReceiveWeightName(java.lang.String receiveWeightName) {
        this.receiveWeightName = receiveWeightName;
    }

    /**
     * <p>备注。</p>
     *
     * @return the 备注
     */
    public java.lang.String getRemark() {
        return remark;
    }

    /**
     * <p>备注。</p>
     *
     * @param remark 备注。
     */
    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }

    /**
     * <p>生产日期。</p>
     *
     * @return the 生产日期
     */
    public java.util.Date getProductDate() {
        return productDate;
    }

    /**
     * <p>生产日期。</p>
     *
     * @param productDate 生产日期。
     */
    public void setProductDate(java.util.Date productDate) {
        this.productDate = productDate;
    }

}
