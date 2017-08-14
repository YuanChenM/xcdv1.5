/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表pd_tc_provider对应的PdTcProvider。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class PdTcProvider extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 总控目录ID */
    private java.lang.Long tcProviderId;
    /** 卖家供应商CODE */
    private java.lang.String providerCode;
    /** 卖家供应商名称 */
    private java.lang.String providerName;
    /** 产品一级分类CODE */
    private java.lang.String classesCode;
    /** 产品一级分类名称 */
    private java.lang.String classesName;
    /** 产品二级分类CODE */
    private java.lang.String machiningCode;
    /** 产品二级分类名称 */
    private java.lang.String machiningName;
    /** 品种编码 */
    private java.lang.String breedCode;
    /** 品种名称 */
    private java.lang.String breedName;
    /** 产品特征编码 */
    private java.lang.String featureCode;
    /** 产品特征名称 */
    private java.lang.String featureName;
    /** 净重编码 */
    private java.lang.String weightCode;
    /** 净重名称 */
    private java.lang.String weightName;
    /** 净重数值 */
    private java.math.BigDecimal weightVal;
    /** 包装编码 */
    private java.lang.String normsCode;
    /** 包装名称 */
    private java.lang.String normsName;
    /** 产品状态 */
    private java.lang.Integer featureFlg;
    /** 产品销售对象 */
    private java.lang.String salesTarget;
    /** 产品加工方向 */
    private java.lang.String machiningWay;
    /** 申请日期 */
    private java.util.Date applyDate;
    /** 审核日期 */
    private java.util.Date auditDate;
    /** 0：申请中；1：审核通过；2：审核拒绝 */
    private java.lang.Integer auditStatus;
    /**
     * <p>默认构造函数。</p>
     */
    public PdTcProvider() {

    }

    /**
     * <p>总控目录ID。</p>
     *
     * @return the 总控目录ID
     */
    public java.lang.Long getTcProviderId() {
        return tcProviderId;
    }

    /**
     * <p>总控目录ID。</p>
     *
     * @param tcProviderId 总控目录ID。
     */
    public void setTcProviderId(java.lang.Long tcProviderId) {
        this.tcProviderId = tcProviderId;
    }

    /**
     * <p>卖家供应商CODE。</p>
     *
     * @return the 卖家供应商CODE
     */
    public java.lang.String getProviderCode() {
        return providerCode;
    }

    /**
     * <p>卖家供应商CODE。</p>
     *
     * @param providerCode 卖家供应商CODE。
     */
    public void setProviderCode(java.lang.String providerCode) {
        this.providerCode = providerCode;
    }

    /**
     * <p>卖家供应商名称。</p>
     *
     * @return the 卖家供应商名称
     */
    public java.lang.String getProviderName() {
        return providerName;
    }

    /**
     * <p>卖家供应商名称。</p>
     *
     * @param providerName 卖家供应商名称。
     */
    public void setProviderName(java.lang.String providerName) {
        this.providerName = providerName;
    }

    /**
     * <p>产品一级分类CODE。</p>
     *
     * @return the 产品一级分类CODE
     */
    public java.lang.String getClassesCode() {
        return classesCode;
    }

    /**
     * <p>产品一级分类CODE。</p>
     *
     * @param classesCode 产品一级分类CODE。
     */
    public void setClassesCode(java.lang.String classesCode) {
        this.classesCode = classesCode;
    }

    /**
     * <p>产品一级分类名称。</p>
     *
     * @return the 产品一级分类名称
     */
    public java.lang.String getClassesName() {
        return classesName;
    }

    /**
     * <p>产品一级分类名称。</p>
     *
     * @param classesName 产品一级分类名称。
     */
    public void setClassesName(java.lang.String classesName) {
        this.classesName = classesName;
    }

    /**
     * <p>产品二级分类CODE。</p>
     *
     * @return the 产品二级分类CODE
     */
    public java.lang.String getMachiningCode() {
        return machiningCode;
    }

    /**
     * <p>产品二级分类CODE。</p>
     *
     * @param machiningCode 产品二级分类CODE。
     */
    public void setMachiningCode(java.lang.String machiningCode) {
        this.machiningCode = machiningCode;
    }

    /**
     * <p>产品二级分类名称。</p>
     *
     * @return the 产品二级分类名称
     */
    public java.lang.String getMachiningName() {
        return machiningName;
    }

    /**
     * <p>产品二级分类名称。</p>
     *
     * @param machiningName 产品二级分类名称。
     */
    public void setMachiningName(java.lang.String machiningName) {
        this.machiningName = machiningName;
    }

    /**
     * <p>品种编码。</p>
     *
     * @return the 品种编码
     */
    public java.lang.String getBreedCode() {
        return breedCode;
    }

    /**
     * <p>品种编码。</p>
     *
     * @param breedCode 品种编码。
     */
    public void setBreedCode(java.lang.String breedCode) {
        this.breedCode = breedCode;
    }

    /**
     * <p>品种名称。</p>
     *
     * @return the 品种名称
     */
    public java.lang.String getBreedName() {
        return breedName;
    }

    /**
     * <p>品种名称。</p>
     *
     * @param breedName 品种名称。
     */
    public void setBreedName(java.lang.String breedName) {
        this.breedName = breedName;
    }

    /**
     * <p>产品特征编码。</p>
     *
     * @return the 产品特征编码
     */
    public java.lang.String getFeatureCode() {
        return featureCode;
    }

    /**
     * <p>产品特征编码。</p>
     *
     * @param featureCode 产品特征编码。
     */
    public void setFeatureCode(java.lang.String featureCode) {
        this.featureCode = featureCode;
    }

    /**
     * <p>产品特征名称。</p>
     *
     * @return the 产品特征名称
     */
    public java.lang.String getFeatureName() {
        return featureName;
    }

    /**
     * <p>产品特征名称。</p>
     *
     * @param featureName 产品特征名称。
     */
    public void setFeatureName(java.lang.String featureName) {
        this.featureName = featureName;
    }

    /**
     * <p>净重编码。</p>
     *
     * @return the 净重编码
     */
    public java.lang.String getWeightCode() {
        return weightCode;
    }

    /**
     * <p>净重编码。</p>
     *
     * @param weightCode 净重编码。
     */
    public void setWeightCode(java.lang.String weightCode) {
        this.weightCode = weightCode;
    }

    /**
     * <p>净重名称。</p>
     *
     * @return the 净重名称
     */
    public java.lang.String getWeightName() {
        return weightName;
    }

    /**
     * <p>净重名称。</p>
     *
     * @param weightName 净重名称。
     */
    public void setWeightName(java.lang.String weightName) {
        this.weightName = weightName;
    }

    /**
     * <p>净重数值。</p>
     *
     * @return the 净重数值
     */
    public java.math.BigDecimal getWeightVal() {
        return weightVal;
    }

    /**
     * <p>净重数值。</p>
     *
     * @param weightVal 净重数值。
     */
    public void setWeightVal(java.math.BigDecimal weightVal) {
        this.weightVal = weightVal;
    }

    /**
     * <p>包装编码。</p>
     *
     * @return the 包装编码
     */
    public java.lang.String getNormsCode() {
        return normsCode;
    }

    /**
     * <p>包装编码。</p>
     *
     * @param normsCode 包装编码。
     */
    public void setNormsCode(java.lang.String normsCode) {
        this.normsCode = normsCode;
    }

    /**
     * <p>包装名称。</p>
     *
     * @return the 包装名称
     */
    public java.lang.String getNormsName() {
        return normsName;
    }

    /**
     * <p>包装名称。</p>
     *
     * @param normsName 包装名称。
     */
    public void setNormsName(java.lang.String normsName) {
        this.normsName = normsName;
    }

    /**
     * <p>产品状态。</p>
     *
     * @return the 产品状态
     */
    public java.lang.Integer getFeatureFlg() {
        return featureFlg;
    }

    /**
     * <p>产品状态。</p>
     *
     * @param featureFlg 产品状态。
     */
    public void setFeatureFlg(java.lang.Integer featureFlg) {
        this.featureFlg = featureFlg;
    }

    /**
     * <p>产品销售对象。</p>
     *
     * @return the 产品销售对象
     */
    public java.lang.String getSalesTarget() {
        return salesTarget;
    }

    /**
     * <p>产品销售对象。</p>
     *
     * @param salesTarget 产品销售对象。
     */
    public void setSalesTarget(java.lang.String salesTarget) {
        this.salesTarget = salesTarget;
    }

    /**
     * <p>产品加工方向。</p>
     *
     * @return the 产品加工方向
     */
    public java.lang.String getMachiningWay() {
        return machiningWay;
    }

    /**
     * <p>产品加工方向。</p>
     *
     * @param machiningWay 产品加工方向。
     */
    public void setMachiningWay(java.lang.String machiningWay) {
        this.machiningWay = machiningWay;
    }

    /**
     * <p>申请日期。</p>
     *
     * @return the 申请日期
     */
    public java.util.Date getApplyDate() {
        return applyDate;
    }

    /**
     * <p>申请日期。</p>
     *
     * @param applyDate 申请日期。
     */
    public void setApplyDate(java.util.Date applyDate) {
        this.applyDate = applyDate;
    }

    /**
     * <p>审核日期。</p>
     *
     * @return the 审核日期
     */
    public java.util.Date getAuditDate() {
        return auditDate;
    }

    /**
     * <p>审核日期。</p>
     *
     * @param auditDate 审核日期。
     */
    public void setAuditDate(java.util.Date auditDate) {
        this.auditDate = auditDate;
    }

    /**
     * <p>0：申请中；1：审核通过；2：审核拒绝。</p>
     *
     * @return the 0：申请中；1：审核通过；2：审核拒绝
     */
    public java.lang.Integer getAuditStatus() {
        return auditStatus;
    }

    /**
     * <p>0：申请中；1：审核通过；2：审核拒绝。</p>
     *
     * @param auditStatus 0：申请中；1：审核通过；2：审核拒绝。
     */
    public void setAuditStatus(java.lang.Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

}
