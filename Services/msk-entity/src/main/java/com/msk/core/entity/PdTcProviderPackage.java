/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表pd_tc_provider_package对应的PdTcProviderPackage。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class PdTcProviderPackage extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 总控目录ID */
    private Long tcProviderId;
    /** 卖家供应商CODE */
    private String providerCode;
    /** 卖家供应商名称 */
    private String providerName;
    /** 产品一级分类CODE */
    private String classesCode;
    /** 产品一级分类名称 */
    private String classesName;
    /** 产品二级分类CODE */
    private String machiningCode;
    /** 产品二级分类名称 */
    private String machiningName;
    /** 品种编码 */
    private String breedCode;
    /** 品种名称 */
    private String breedName;
    /** 产品特征编码 */
    private String featureCode;
    /** 产品特征名称 */
    private String featureName;
    /** 净重编码 */
    private String weightCode;
    /** 净重名称 */
    private String weightName;
    /** 净重数值 */
    private java.math.BigDecimal weightVal;
    /** 销售对象 */
    private String salesTarget;
    /** 加工方向 */
    private String machiningWay;
    /** 0：在线处理；1：已注册 */
    private Integer featureFlg;
    /** 包装编码 */
    private String normsCode;
    /** 包装名称 */
    private String normsName;
    /** 单个产品净重 */
    private String normsSuttle;
    /** 单个产品规格净重误差范围 */
    private String normsError;
    /** 内包装净重/个数 */
    private String normsNumber;
    /** 内包装尺寸 */
    private String normsSize;
    /** 内包装材质及技术标准 */
    private String normsTexture;
    /** 外包装规格 */
    private String normsOut;
    /** 外包装净重/毛重 */
    private String normsKg;
    /** 外包装尺寸 */
    private String normsOutSize;
    /** 外包装材质及技术标准 */
    private String normsOutTexture;
    /** 其他包装信息 */
    private String normsTen;
    /** 外包装长 */
    private java.math.BigDecimal normsLength;
    /** 外包装宽 */
    private java.math.BigDecimal normsWidth;
    /** 外包装高 */
    private java.math.BigDecimal normsHeight;
    /** 外包装体积 */
    private java.math.BigDecimal normsVolume;
    /** 内包装净重数值 */
    private java.math.BigDecimal netweightInner;
    /** 外包装净重数值 */
    private java.math.BigDecimal netweightOut;
    /** GROSSWEIGHT_OUT */
    private java.math.BigDecimal grossweightOut;
    /** 申请日期 */
    private java.util.Date applyDate;
    /** 审核日期 */
    private java.util.Date auditDate;
    /** 审核意见 */
    private String auditMemo;
    /** 审核状态 */
    private Integer auditStatus;
    /**
     * <p>默认构造函数。</p>
     */
    public PdTcProviderPackage() {

    }

    /**
     * <p>总控目录ID。</p>
     *
     * @return the 总控目录ID
     */
    public Long getTcProviderId() {
        return tcProviderId;
    }

    /**
     * <p>总控目录ID。</p>
     *
     * @param tcProviderId 总控目录ID。
     */
    public void setTcProviderId(Long tcProviderId) {
        this.tcProviderId = tcProviderId;
    }

    /**
     * <p>卖家供应商CODE。</p>
     *
     * @return the 卖家供应商CODE
     */
    public String getProviderCode() {
        return providerCode;
    }

    /**
     * <p>卖家供应商CODE。</p>
     *
     * @param providerCode 卖家供应商CODE。
     */
    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    /**
     * <p>卖家供应商名称。</p>
     *
     * @return the 卖家供应商名称
     */
    public String getProviderName() {
        return providerName;
    }

    /**
     * <p>卖家供应商名称。</p>
     *
     * @param providerName 卖家供应商名称。
     */
    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    /**
     * <p>产品一级分类CODE。</p>
     *
     * @return the 产品一级分类CODE
     */
    public String getClassesCode() {
        return classesCode;
    }

    /**
     * <p>产品一级分类CODE。</p>
     *
     * @param classesCode 产品一级分类CODE。
     */
    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    /**
     * <p>产品一级分类名称。</p>
     *
     * @return the 产品一级分类名称
     */
    public String getClassesName() {
        return classesName;
    }

    /**
     * <p>产品一级分类名称。</p>
     *
     * @param classesName 产品一级分类名称。
     */
    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    /**
     * <p>产品二级分类CODE。</p>
     *
     * @return the 产品二级分类CODE
     */
    public String getMachiningCode() {
        return machiningCode;
    }

    /**
     * <p>产品二级分类CODE。</p>
     *
     * @param machiningCode 产品二级分类CODE。
     */
    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    /**
     * <p>产品二级分类名称。</p>
     *
     * @return the 产品二级分类名称
     */
    public String getMachiningName() {
        return machiningName;
    }

    /**
     * <p>产品二级分类名称。</p>
     *
     * @param machiningName 产品二级分类名称。
     */
    public void setMachiningName(String machiningName) {
        this.machiningName = machiningName;
    }

    /**
     * <p>品种编码。</p>
     *
     * @return the 品种编码
     */
    public String getBreedCode() {
        return breedCode;
    }

    /**
     * <p>品种编码。</p>
     *
     * @param breedCode 品种编码。
     */
    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    /**
     * <p>品种名称。</p>
     *
     * @return the 品种名称
     */
    public String getBreedName() {
        return breedName;
    }

    /**
     * <p>品种名称。</p>
     *
     * @param breedName 品种名称。
     */
    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    /**
     * <p>产品特征编码。</p>
     *
     * @return the 产品特征编码
     */
    public String getFeatureCode() {
        return featureCode;
    }

    /**
     * <p>产品特征编码。</p>
     *
     * @param featureCode 产品特征编码。
     */
    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    /**
     * <p>产品特征名称。</p>
     *
     * @return the 产品特征名称
     */
    public String getFeatureName() {
        return featureName;
    }

    /**
     * <p>产品特征名称。</p>
     *
     * @param featureName 产品特征名称。
     */
    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    /**
     * <p>净重编码。</p>
     *
     * @return the 净重编码
     */
    public String getWeightCode() {
        return weightCode;
    }

    /**
     * <p>净重编码。</p>
     *
     * @param weightCode 净重编码。
     */
    public void setWeightCode(String weightCode) {
        this.weightCode = weightCode;
    }

    /**
     * <p>净重名称。</p>
     *
     * @return the 净重名称
     */
    public String getWeightName() {
        return weightName;
    }

    /**
     * <p>净重名称。</p>
     *
     * @param weightName 净重名称。
     */
    public void setWeightName(String weightName) {
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
     * <p>销售对象。</p>
     *
     * @return the 销售对象
     */
    public String getSalesTarget() {
        return salesTarget;
    }

    /**
     * <p>销售对象。</p>
     *
     * @param salesTarget 销售对象。
     */
    public void setSalesTarget(String salesTarget) {
        this.salesTarget = salesTarget;
    }

    /**
     * <p>加工方向。</p>
     *
     * @return the 加工方向
     */
    public String getMachiningWay() {
        return machiningWay;
    }

    /**
     * <p>加工方向。</p>
     *
     * @param machiningWay 加工方向。
     */
    public void setMachiningWay(String machiningWay) {
        this.machiningWay = machiningWay;
    }

    /**
     * <p>0：在线处理；1：已注册。</p>
     *
     * @return the 0：在线处理；1：已注册
     */
    public Integer getFeatureFlg() {
        return featureFlg;
    }

    /**
     * <p>0：在线处理；1：已注册。</p>
     *
     * @param featureFlg 0：在线处理；1：已注册。
     */
    public void setFeatureFlg(Integer featureFlg) {
        this.featureFlg = featureFlg;
    }

    /**
     * <p>包装编码。</p>
     *
     * @return the 包装编码
     */
    public String getNormsCode() {
        return normsCode;
    }

    /**
     * <p>包装编码。</p>
     *
     * @param normsCode 包装编码。
     */
    public void setNormsCode(String normsCode) {
        this.normsCode = normsCode;
    }

    /**
     * <p>包装名称。</p>
     *
     * @return the 包装名称
     */
    public String getNormsName() {
        return normsName;
    }

    /**
     * <p>包装名称。</p>
     *
     * @param normsName 包装名称。
     */
    public void setNormsName(String normsName) {
        this.normsName = normsName;
    }

    /**
     * <p>单个产品净重。</p>
     *
     * @return the 单个产品净重
     */
    public String getNormsSuttle() {
        return normsSuttle;
    }

    /**
     * <p>单个产品净重。</p>
     *
     * @param normsSuttle 单个产品净重。
     */
    public void setNormsSuttle(String normsSuttle) {
        this.normsSuttle = normsSuttle;
    }

    /**
     * <p>单个产品规格净重误差范围。</p>
     *
     * @return the 单个产品规格净重误差范围
     */
    public String getNormsError() {
        return normsError;
    }

    /**
     * <p>单个产品规格净重误差范围。</p>
     *
     * @param normsError 单个产品规格净重误差范围。
     */
    public void setNormsError(String normsError) {
        this.normsError = normsError;
    }

    /**
     * <p>内包装净重/个数。</p>
     *
     * @return the 内包装净重/个数
     */
    public String getNormsNumber() {
        return normsNumber;
    }

    /**
     * <p>内包装净重/个数。</p>
     *
     * @param normsNumber 内包装净重/个数。
     */
    public void setNormsNumber(String normsNumber) {
        this.normsNumber = normsNumber;
    }

    /**
     * <p>内包装尺寸。</p>
     *
     * @return the 内包装尺寸
     */
    public String getNormsSize() {
        return normsSize;
    }

    /**
     * <p>内包装尺寸。</p>
     *
     * @param normsSize 内包装尺寸。
     */
    public void setNormsSize(String normsSize) {
        this.normsSize = normsSize;
    }

    /**
     * <p>内包装材质及技术标准。</p>
     *
     * @return the 内包装材质及技术标准
     */
    public String getNormsTexture() {
        return normsTexture;
    }

    /**
     * <p>内包装材质及技术标准。</p>
     *
     * @param normsTexture 内包装材质及技术标准。
     */
    public void setNormsTexture(String normsTexture) {
        this.normsTexture = normsTexture;
    }

    /**
     * <p>外包装规格。</p>
     *
     * @return the 外包装规格
     */
    public String getNormsOut() {
        return normsOut;
    }

    /**
     * <p>外包装规格。</p>
     *
     * @param normsOut 外包装规格。
     */
    public void setNormsOut(String normsOut) {
        this.normsOut = normsOut;
    }

    /**
     * <p>外包装净重/毛重。</p>
     *
     * @return the 外包装净重/毛重
     */
    public String getNormsKg() {
        return normsKg;
    }

    /**
     * <p>外包装净重/毛重。</p>
     *
     * @param normsKg 外包装净重/毛重。
     */
    public void setNormsKg(String normsKg) {
        this.normsKg = normsKg;
    }

    /**
     * <p>外包装尺寸。</p>
     *
     * @return the 外包装尺寸
     */
    public String getNormsOutSize() {
        return normsOutSize;
    }

    /**
     * <p>外包装尺寸。</p>
     *
     * @param normsOutSize 外包装尺寸。
     */
    public void setNormsOutSize(String normsOutSize) {
        this.normsOutSize = normsOutSize;
    }

    /**
     * <p>外包装材质及技术标准。</p>
     *
     * @return the 外包装材质及技术标准
     */
    public String getNormsOutTexture() {
        return normsOutTexture;
    }

    /**
     * <p>外包装材质及技术标准。</p>
     *
     * @param normsOutTexture 外包装材质及技术标准。
     */
    public void setNormsOutTexture(String normsOutTexture) {
        this.normsOutTexture = normsOutTexture;
    }

    /**
     * <p>其他包装信息。</p>
     *
     * @return the 其他包装信息
     */
    public String getNormsTen() {
        return normsTen;
    }

    /**
     * <p>其他包装信息。</p>
     *
     * @param normsTen 其他包装信息。
     */
    public void setNormsTen(String normsTen) {
        this.normsTen = normsTen;
    }

    /**
     * <p>外包装长。</p>
     *
     * @return the 外包装长
     */
    public java.math.BigDecimal getNormsLength() {
        return normsLength;
    }

    /**
     * <p>外包装长。</p>
     *
     * @param normsLength 外包装长。
     */
    public void setNormsLength(java.math.BigDecimal normsLength) {
        this.normsLength = normsLength;
    }

    /**
     * <p>外包装宽。</p>
     *
     * @return the 外包装宽
     */
    public java.math.BigDecimal getNormsWidth() {
        return normsWidth;
    }

    /**
     * <p>外包装宽。</p>
     *
     * @param normsWidth 外包装宽。
     */
    public void setNormsWidth(java.math.BigDecimal normsWidth) {
        this.normsWidth = normsWidth;
    }

    /**
     * <p>外包装高。</p>
     *
     * @return the 外包装高
     */
    public java.math.BigDecimal getNormsHeight() {
        return normsHeight;
    }

    /**
     * <p>外包装高。</p>
     *
     * @param normsHeight 外包装高。
     */
    public void setNormsHeight(java.math.BigDecimal normsHeight) {
        this.normsHeight = normsHeight;
    }

    /**
     * <p>外包装体积。</p>
     *
     * @return the 外包装体积
     */
    public java.math.BigDecimal getNormsVolume() {
        return normsVolume;
    }

    /**
     * <p>外包装体积。</p>
     *
     * @param normsVolume 外包装体积。
     */
    public void setNormsVolume(java.math.BigDecimal normsVolume) {
        this.normsVolume = normsVolume;
    }

    /**
     * <p>内包装净重数值。</p>
     *
     * @return the 内包装净重数值
     */
    public java.math.BigDecimal getNetweightInner() {
        return netweightInner;
    }

    /**
     * <p>内包装净重数值。</p>
     *
     * @param netweightInner 内包装净重数值。
     */
    public void setNetweightInner(java.math.BigDecimal netweightInner) {
        this.netweightInner = netweightInner;
    }

    /**
     * <p>外包装净重数值。</p>
     *
     * @return the 外包装净重数值
     */
    public java.math.BigDecimal getNetweightOut() {
        return netweightOut;
    }

    /**
     * <p>外包装净重数值。</p>
     *
     * @param netweightOut 外包装净重数值。
     */
    public void setNetweightOut(java.math.BigDecimal netweightOut) {
        this.netweightOut = netweightOut;
    }

    /**
     * <p>GROSSWEIGHT_OUT。</p>
     *
     * @return the GROSSWEIGHT_OUT
     */
    public java.math.BigDecimal getGrossweightOut() {
        return grossweightOut;
    }

    /**
     * <p>GROSSWEIGHT_OUT。</p>
     *
     * @param grossweightOut GROSSWEIGHT_OUT。
     */
    public void setGrossweightOut(java.math.BigDecimal grossweightOut) {
        this.grossweightOut = grossweightOut;
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
     * <p>审核意见。</p>
     *
     * @return the 审核意见
     */
    public String getAuditMemo() {
        return auditMemo;
    }

    /**
     * <p>审核意见。</p>
     *
     * @param auditMemo 审核意见。
     */
    public void setAuditMemo(String auditMemo) {
        this.auditMemo = auditMemo;
    }

    /**
     * <p>审核状态。</p>
     *
     * @return the 审核状态
     */
    public Integer getAuditStatus() {
        return auditStatus;
    }

    /**
     * <p>审核状态。</p>
     *
     * @param auditStatus 审核状态。
     */
    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

}
