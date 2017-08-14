/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表ssc_differ_detail对应的SscDifferDetail。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SscDifferDetail extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 详细信息ID */
    private java.lang.Long id;
    /** 差异单ID */
    private java.lang.Long differId;
    /** 差异单编号 */
    private java.lang.String differCode;
    /** 产品属性码 */
    private java.lang.String productAttrCode;
    /** 产品重量 */
    private java.math.BigDecimal sendWeight;
    /** 产品重量 */
    private java.math.BigDecimal receiveWeight;
    /** 产品单价 */
    private java.math.BigDecimal unitPrice;
    /** 发货产品箱数 */
    private java.lang.Integer sendBox;
    /** 发货产品金额 */
    private java.math.BigDecimal sendAmount;
    /** 入库产品箱数 */
    private java.lang.Integer receiveBox;
    /** 入库产品价格 */
    private java.math.BigDecimal receivePrice;
    /** 入库产品金额 */
    private java.math.BigDecimal receiveAmount;
    /** 产品差异金额 */
    private java.math.BigDecimal pdDifferAmount;
    /** 产品差异箱数 */
    private java.lang.Integer pdDifferBox;
    /** 发货产品纸箱使用数量 */
    private java.lang.Integer sendCartonUseNum;
    /** 发货产品纸箱金额 */
    private java.math.BigDecimal sendCartonAmount;
    /** 发货产品内袋使用数量 */
    private java.lang.Integer sendInnerBagUseNum;
    /** 发货产品内袋金额 */
    private java.math.BigDecimal sendInnerBagAmount;
    /** 入库产品纸箱使用数量 */
    private java.lang.Integer receiveCartonUseNum;
    /** 入库产品纸箱金额 */
    private java.math.BigDecimal receiveCartonAmount;
    /** 入库产品内袋使用数量 */
    private java.lang.Integer receiveInnerBagUseNum;
    /** 入库产品内袋金额 */
    private java.math.BigDecimal receiveInnerBagAmount;
    /** 包材差异金额 */
    private java.math.BigDecimal packageMaterialDifferAmount;
    /** 是否已核销 */
    private java.lang.String isVerification;
    /** 备注 */
    private java.lang.String remark;
    /**
     * <p>默认构造函数。</p>
     */
    public SscDifferDetail() {

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
     * <p>差异单ID。</p>
     *
     * @return the 差异单ID
     */
    public java.lang.Long getDifferId() {
        return differId;
    }

    /**
     * <p>差异单ID。</p>
     *
     * @param differId 差异单ID。
     */
    public void setDifferId(java.lang.Long differId) {
        this.differId = differId;
    }

    /**
     * <p>差异单编号。</p>
     *
     * @return the 差异单编号
     */
    public java.lang.String getDifferCode() {
        return differCode;
    }

    /**
     * <p>差异单编号。</p>
     *
     * @param differCode 差异单编号。
     */
    public void setDifferCode(java.lang.String differCode) {
        this.differCode = differCode;
    }

    /**
     * <p>产品属性码。</p>
     *
     * @return the 产品属性码
     */
    public java.lang.String getProductAttrCode() {
        return productAttrCode;
    }

    /**
     * <p>产品属性码。</p>
     *
     * @param productAttrCode 产品属性码。
     */
    public void setProductAttrCode(java.lang.String productAttrCode) {
        this.productAttrCode = productAttrCode;
    }

    /**
     * <p>产品重量。</p>
     *
     * @return the 产品重量
     */
    public java.math.BigDecimal getSendWeight() {
        return sendWeight;
    }

    /**
     * <p>产品重量。</p>
     *
     * @param sendWeight 产品重量。
     */
    public void setSendWeight(java.math.BigDecimal sendWeight) {
        this.sendWeight = sendWeight;
    }

    /**
     * <p>产品重量。</p>
     *
     * @return the 产品重量
     */
    public java.math.BigDecimal getReceiveWeight() {
        return receiveWeight;
    }

    /**
     * <p>产品重量。</p>
     *
     * @param receiveWeight 产品重量。
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
     * <p>发货产品金额。</p>
     *
     * @return the 发货产品金额
     */
    public java.math.BigDecimal getSendAmount() {
        return sendAmount;
    }

    /**
     * <p>发货产品金额。</p>
     *
     * @param sendAmount 发货产品金额。
     */
    public void setSendAmount(java.math.BigDecimal sendAmount) {
        this.sendAmount = sendAmount;
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
     * <p>入库产品价格。</p>
     *
     * @return the 入库产品价格
     */
    public java.math.BigDecimal getReceivePrice() {
        return receivePrice;
    }

    /**
     * <p>入库产品价格。</p>
     *
     * @param receivePrice 入库产品价格。
     */
    public void setReceivePrice(java.math.BigDecimal receivePrice) {
        this.receivePrice = receivePrice;
    }

    /**
     * <p>入库产品金额。</p>
     *
     * @return the 入库产品金额
     */
    public java.math.BigDecimal getReceiveAmount() {
        return receiveAmount;
    }

    /**
     * <p>入库产品金额。</p>
     *
     * @param receiveAmount 入库产品金额。
     */
    public void setReceiveAmount(java.math.BigDecimal receiveAmount) {
        this.receiveAmount = receiveAmount;
    }

    /**
     * <p>产品差异金额。</p>
     *
     * @return the 产品差异金额
     */
    public java.math.BigDecimal getPdDifferAmount() {
        return pdDifferAmount;
    }

    /**
     * <p>产品差异金额。</p>
     *
     * @param pdDifferAmount 产品差异金额。
     */
    public void setPdDifferAmount(java.math.BigDecimal pdDifferAmount) {
        this.pdDifferAmount = pdDifferAmount;
    }

    /**
     * <p>产品差异箱数。</p>
     *
     * @return the 产品差异箱数
     */
    public java.lang.Integer getPdDifferBox() {
        return pdDifferBox;
    }

    /**
     * <p>产品差异箱数。</p>
     *
     * @param pdDifferBox 产品差异箱数。
     */
    public void setPdDifferBox(java.lang.Integer pdDifferBox) {
        this.pdDifferBox = pdDifferBox;
    }

    /**
     * <p>发货产品纸箱使用数量。</p>
     *
     * @return the 发货产品纸箱使用数量
     */
    public java.lang.Integer getSendCartonUseNum() {
        return sendCartonUseNum;
    }

    /**
     * <p>发货产品纸箱使用数量。</p>
     *
     * @param sendCartonUseNum 发货产品纸箱使用数量。
     */
    public void setSendCartonUseNum(java.lang.Integer sendCartonUseNum) {
        this.sendCartonUseNum = sendCartonUseNum;
    }

    /**
     * <p>发货产品纸箱金额。</p>
     *
     * @return the 发货产品纸箱金额
     */
    public java.math.BigDecimal getSendCartonAmount() {
        return sendCartonAmount;
    }

    /**
     * <p>发货产品纸箱金额。</p>
     *
     * @param sendCartonAmount 发货产品纸箱金额。
     */
    public void setSendCartonAmount(java.math.BigDecimal sendCartonAmount) {
        this.sendCartonAmount = sendCartonAmount;
    }

    /**
     * <p>发货产品内袋使用数量。</p>
     *
     * @return the 发货产品内袋使用数量
     */
    public java.lang.Integer getSendInnerBagUseNum() {
        return sendInnerBagUseNum;
    }

    /**
     * <p>发货产品内袋使用数量。</p>
     *
     * @param sendInnerBagUseNum 发货产品内袋使用数量。
     */
    public void setSendInnerBagUseNum(java.lang.Integer sendInnerBagUseNum) {
        this.sendInnerBagUseNum = sendInnerBagUseNum;
    }

    /**
     * <p>发货产品内袋金额。</p>
     *
     * @return the 发货产品内袋金额
     */
    public java.math.BigDecimal getSendInnerBagAmount() {
        return sendInnerBagAmount;
    }

    /**
     * <p>发货产品内袋金额。</p>
     *
     * @param sendInnerBagAmount 发货产品内袋金额。
     */
    public void setSendInnerBagAmount(java.math.BigDecimal sendInnerBagAmount) {
        this.sendInnerBagAmount = sendInnerBagAmount;
    }

    /**
     * <p>入库产品纸箱使用数量。</p>
     *
     * @return the 入库产品纸箱使用数量
     */
    public java.lang.Integer getReceiveCartonUseNum() {
        return receiveCartonUseNum;
    }

    /**
     * <p>入库产品纸箱使用数量。</p>
     *
     * @param receiveCartonUseNum 入库产品纸箱使用数量。
     */
    public void setReceiveCartonUseNum(java.lang.Integer receiveCartonUseNum) {
        this.receiveCartonUseNum = receiveCartonUseNum;
    }

    /**
     * <p>入库产品纸箱金额。</p>
     *
     * @return the 入库产品纸箱金额
     */
    public java.math.BigDecimal getReceiveCartonAmount() {
        return receiveCartonAmount;
    }

    /**
     * <p>入库产品纸箱金额。</p>
     *
     * @param receiveCartonAmount 入库产品纸箱金额。
     */
    public void setReceiveCartonAmount(java.math.BigDecimal receiveCartonAmount) {
        this.receiveCartonAmount = receiveCartonAmount;
    }

    /**
     * <p>入库产品内袋使用数量。</p>
     *
     * @return the 入库产品内袋使用数量
     */
    public java.lang.Integer getReceiveInnerBagUseNum() {
        return receiveInnerBagUseNum;
    }

    /**
     * <p>入库产品内袋使用数量。</p>
     *
     * @param receiveInnerBagUseNum 入库产品内袋使用数量。
     */
    public void setReceiveInnerBagUseNum(java.lang.Integer receiveInnerBagUseNum) {
        this.receiveInnerBagUseNum = receiveInnerBagUseNum;
    }

    /**
     * <p>入库产品内袋金额。</p>
     *
     * @return the 入库产品内袋金额
     */
    public java.math.BigDecimal getReceiveInnerBagAmount() {
        return receiveInnerBagAmount;
    }

    /**
     * <p>入库产品内袋金额。</p>
     *
     * @param receiveInnerBagAmount 入库产品内袋金额。
     */
    public void setReceiveInnerBagAmount(java.math.BigDecimal receiveInnerBagAmount) {
        this.receiveInnerBagAmount = receiveInnerBagAmount;
    }

    /**
     * <p>包材差异金额。</p>
     *
     * @return the 包材差异金额
     */
    public java.math.BigDecimal getPackageMaterialDifferAmount() {
        return packageMaterialDifferAmount;
    }

    /**
     * <p>包材差异金额。</p>
     *
     * @param packageMaterialDifferAmount 包材差异金额。
     */
    public void setPackageMaterialDifferAmount(java.math.BigDecimal packageMaterialDifferAmount) {
        this.packageMaterialDifferAmount = packageMaterialDifferAmount;
    }

    /**
     * <p>是否已核销。</p>
     *
     * @return the 是否已核销
     */
    public java.lang.String getIsVerification() {
        return isVerification;
    }

    /**
     * <p>是否已核销。</p>
     *
     * @param isVerification 是否已核销。
     */
    public void setIsVerification(java.lang.String isVerification) {
        this.isVerification = isVerification;
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

}
