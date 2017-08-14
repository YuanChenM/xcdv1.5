/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表ssc_package_material_info对应的SscPackageMaterialInfo。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SscPackageMaterialInfo extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 包材ID */
    private java.lang.Long packageDetailId;
    /** 合同编号 */
    private java.lang.Long contractId;
    /** 产品ID */
    private java.lang.Long detailId;
    /** 纸箱质量标准 */
    private java.lang.String cartonQuaSta;
    /** 纸箱规格标准 */
    private java.lang.String cartonSpecSta;
    /** 内袋质量标准 */
    private java.lang.String innerBagQuaSta;
    /** 内袋规格标准 */
    private java.lang.String innerBagSpecSta;
    /** 胶带标准 */
    private java.lang.String tapeQuaSta;
    /** 标签内容标准 */
    private java.lang.String labelContentSta;
    /** 纸箱质量 */
    private java.lang.String cartonQua;
    /** 纸箱规格 */
    private java.lang.String cartonSpec;
    /** 纸箱使用数量 */
    private java.lang.Integer cartonUseNum;
    /** 内袋质量 */
    private java.lang.String innerBagQua;
    /** 内袋规格 */
    private java.lang.String innerBagSpec;
    /** 内袋使用数量 */
    private java.lang.Integer innerBagUseNum;
    /** 胶带颜色 */
    private java.lang.String tapeColor;
    /** 胶带使用数量 */
    private java.lang.Integer tapeUseNum;
    /** 标签内容 */
    private java.lang.String labelContent;
    /** 纸箱大标签数量 */
    private java.lang.Integer largeLabelNum;
    /** 纸箱小标签数量 */
    private java.lang.Integer smallLabelNum;
    /** 内袋标签数量 */
    private java.lang.Integer innerLabelNum;
    /** 包材提供方式 */
    private java.lang.String supplyMode;
    /** 包材审核方式 */
    private java.lang.String auditMethod;
    /** 包材结算方式 */
    private java.lang.String settlementMethod;
    /** 备注 */
    private java.lang.String remark;
    /**
     * <p>默认构造函数。</p>
     */
    public SscPackageMaterialInfo() {

    }

    /**
     * <p>包材ID。</p>
     *
     * @return the 包材ID
     */
    public java.lang.Long getPackageDetailId() {
        return packageDetailId;
    }

    /**
     * <p>包材ID。</p>
     *
     * @param packageDetailId 包材ID。
     */
    public void setPackageDetailId(java.lang.Long packageDetailId) {
        this.packageDetailId = packageDetailId;
    }

    /**
     * <p>合同编号。</p>
     *
     * @return the 合同编号
     */
    public java.lang.Long getContractId() {
        return contractId;
    }

    /**
     * <p>合同编号。</p>
     *
     * @param contractId 合同编号。
     */
    public void setContractId(java.lang.Long contractId) {
        this.contractId = contractId;
    }

    /**
     * <p>产品ID。</p>
     *
     * @return the 产品ID
     */
    public java.lang.Long getDetailId() {
        return detailId;
    }

    /**
     * <p>产品ID。</p>
     *
     * @param detailId 产品ID。
     */
    public void setDetailId(java.lang.Long detailId) {
        this.detailId = detailId;
    }

    /**
     * <p>纸箱质量标准。</p>
     *
     * @return the 纸箱质量标准
     */
    public java.lang.String getCartonQuaSta() {
        return cartonQuaSta;
    }

    /**
     * <p>纸箱质量标准。</p>
     *
     * @param cartonQuaSta 纸箱质量标准。
     */
    public void setCartonQuaSta(java.lang.String cartonQuaSta) {
        this.cartonQuaSta = cartonQuaSta;
    }

    /**
     * <p>纸箱规格标准。</p>
     *
     * @return the 纸箱规格标准
     */
    public java.lang.String getCartonSpecSta() {
        return cartonSpecSta;
    }

    /**
     * <p>纸箱规格标准。</p>
     *
     * @param cartonSpecSta 纸箱规格标准。
     */
    public void setCartonSpecSta(java.lang.String cartonSpecSta) {
        this.cartonSpecSta = cartonSpecSta;
    }

    /**
     * <p>内袋质量标准。</p>
     *
     * @return the 内袋质量标准
     */
    public java.lang.String getInnerBagQuaSta() {
        return innerBagQuaSta;
    }

    /**
     * <p>内袋质量标准。</p>
     *
     * @param innerBagQuaSta 内袋质量标准。
     */
    public void setInnerBagQuaSta(java.lang.String innerBagQuaSta) {
        this.innerBagQuaSta = innerBagQuaSta;
    }

    /**
     * <p>内袋规格标准。</p>
     *
     * @return the 内袋规格标准
     */
    public java.lang.String getInnerBagSpecSta() {
        return innerBagSpecSta;
    }

    /**
     * <p>内袋规格标准。</p>
     *
     * @param innerBagSpecSta 内袋规格标准。
     */
    public void setInnerBagSpecSta(java.lang.String innerBagSpecSta) {
        this.innerBagSpecSta = innerBagSpecSta;
    }

    /**
     * <p>胶带标准。</p>
     *
     * @return the 胶带标准
     */
    public java.lang.String getTapeQuaSta() {
        return tapeQuaSta;
    }

    /**
     * <p>胶带标准。</p>
     *
     * @param tapeQuaSta 胶带标准。
     */
    public void setTapeQuaSta(java.lang.String tapeQuaSta) {
        this.tapeQuaSta = tapeQuaSta;
    }

    /**
     * <p>标签内容标准。</p>
     *
     * @return the 标签内容标准
     */
    public java.lang.String getLabelContentSta() {
        return labelContentSta;
    }

    /**
     * <p>标签内容标准。</p>
     *
     * @param labelContentSta 标签内容标准。
     */
    public void setLabelContentSta(java.lang.String labelContentSta) {
        this.labelContentSta = labelContentSta;
    }

    /**
     * <p>纸箱质量。</p>
     *
     * @return the 纸箱质量
     */
    public java.lang.String getCartonQua() {
        return cartonQua;
    }

    /**
     * <p>纸箱质量。</p>
     *
     * @param cartonQua 纸箱质量。
     */
    public void setCartonQua(java.lang.String cartonQua) {
        this.cartonQua = cartonQua;
    }

    /**
     * <p>纸箱规格。</p>
     *
     * @return the 纸箱规格
     */
    public java.lang.String getCartonSpec() {
        return cartonSpec;
    }

    /**
     * <p>纸箱规格。</p>
     *
     * @param cartonSpec 纸箱规格。
     */
    public void setCartonSpec(java.lang.String cartonSpec) {
        this.cartonSpec = cartonSpec;
    }

    /**
     * <p>纸箱使用数量。</p>
     *
     * @return the 纸箱使用数量
     */
    public java.lang.Integer getCartonUseNum() {
        return cartonUseNum;
    }

    /**
     * <p>纸箱使用数量。</p>
     *
     * @param cartonUseNum 纸箱使用数量。
     */
    public void setCartonUseNum(java.lang.Integer cartonUseNum) {
        this.cartonUseNum = cartonUseNum;
    }

    /**
     * <p>内袋质量。</p>
     *
     * @return the 内袋质量
     */
    public java.lang.String getInnerBagQua() {
        return innerBagQua;
    }

    /**
     * <p>内袋质量。</p>
     *
     * @param innerBagQua 内袋质量。
     */
    public void setInnerBagQua(java.lang.String innerBagQua) {
        this.innerBagQua = innerBagQua;
    }

    /**
     * <p>内袋规格。</p>
     *
     * @return the 内袋规格
     */
    public java.lang.String getInnerBagSpec() {
        return innerBagSpec;
    }

    /**
     * <p>内袋规格。</p>
     *
     * @param innerBagSpec 内袋规格。
     */
    public void setInnerBagSpec(java.lang.String innerBagSpec) {
        this.innerBagSpec = innerBagSpec;
    }

    /**
     * <p>内袋使用数量。</p>
     *
     * @return the 内袋使用数量
     */
    public java.lang.Integer getInnerBagUseNum() {
        return innerBagUseNum;
    }

    /**
     * <p>内袋使用数量。</p>
     *
     * @param innerBagUseNum 内袋使用数量。
     */
    public void setInnerBagUseNum(java.lang.Integer innerBagUseNum) {
        this.innerBagUseNum = innerBagUseNum;
    }

    /**
     * <p>胶带颜色。</p>
     *
     * @return the 胶带颜色
     */
    public java.lang.String getTapeColor() {
        return tapeColor;
    }

    /**
     * <p>胶带颜色。</p>
     *
     * @param tapeColor 胶带颜色。
     */
    public void setTapeColor(java.lang.String tapeColor) {
        this.tapeColor = tapeColor;
    }

    /**
     * <p>胶带使用数量。</p>
     *
     * @return the 胶带使用数量
     */
    public java.lang.Integer getTapeUseNum() {
        return tapeUseNum;
    }

    /**
     * <p>胶带使用数量。</p>
     *
     * @param tapeUseNum 胶带使用数量。
     */
    public void setTapeUseNum(java.lang.Integer tapeUseNum) {
        this.tapeUseNum = tapeUseNum;
    }

    /**
     * <p>标签内容。</p>
     *
     * @return the 标签内容
     */
    public java.lang.String getLabelContent() {
        return labelContent;
    }

    /**
     * <p>标签内容。</p>
     *
     * @param labelContent 标签内容。
     */
    public void setLabelContent(java.lang.String labelContent) {
        this.labelContent = labelContent;
    }

    /**
     * <p>纸箱大标签数量。</p>
     *
     * @return the 纸箱大标签数量
     */
    public java.lang.Integer getLargeLabelNum() {
        return largeLabelNum;
    }

    /**
     * <p>纸箱大标签数量。</p>
     *
     * @param largeLabelNum 纸箱大标签数量。
     */
    public void setLargeLabelNum(java.lang.Integer largeLabelNum) {
        this.largeLabelNum = largeLabelNum;
    }

    /**
     * <p>纸箱小标签数量。</p>
     *
     * @return the 纸箱小标签数量
     */
    public java.lang.Integer getSmallLabelNum() {
        return smallLabelNum;
    }

    /**
     * <p>纸箱小标签数量。</p>
     *
     * @param smallLabelNum 纸箱小标签数量。
     */
    public void setSmallLabelNum(java.lang.Integer smallLabelNum) {
        this.smallLabelNum = smallLabelNum;
    }

    /**
     * <p>内袋标签数量。</p>
     *
     * @return the 内袋标签数量
     */
    public java.lang.Integer getInnerLabelNum() {
        return innerLabelNum;
    }

    /**
     * <p>内袋标签数量。</p>
     *
     * @param innerLabelNum 内袋标签数量。
     */
    public void setInnerLabelNum(java.lang.Integer innerLabelNum) {
        this.innerLabelNum = innerLabelNum;
    }

    /**
     * <p>包材提供方式。</p>
     *
     * @return the 包材提供方式
     */
    public java.lang.String getSupplyMode() {
        return supplyMode;
    }

    /**
     * <p>包材提供方式。</p>
     *
     * @param supplyMode 包材提供方式。
     */
    public void setSupplyMode(java.lang.String supplyMode) {
        this.supplyMode = supplyMode;
    }

    /**
     * <p>包材审核方式。</p>
     *
     * @return the 包材审核方式
     */
    public java.lang.String getAuditMethod() {
        return auditMethod;
    }

    /**
     * <p>包材审核方式。</p>
     *
     * @param auditMethod 包材审核方式。
     */
    public void setAuditMethod(java.lang.String auditMethod) {
        this.auditMethod = auditMethod;
    }

    /**
     * <p>包材结算方式。</p>
     *
     * @return the 包材结算方式
     */
    public java.lang.String getSettlementMethod() {
        return settlementMethod;
    }

    /**
     * <p>包材结算方式。</p>
     *
     * @param settlementMethod 包材结算方式。
     */
    public void setSettlementMethod(java.lang.String settlementMethod) {
        this.settlementMethod = settlementMethod;
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
