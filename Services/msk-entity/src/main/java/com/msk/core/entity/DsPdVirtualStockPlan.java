/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表ds_pd_virtual_stock_plan对应的DsPdVirtualStockPlan。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class DsPdVirtualStockPlan extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 供应分销流水ID */
    private Long suppDsId;
    /** 虚拟库存计划ID */
    private Long virtualStockPlanId;
    /** 分销月度 */
    private String distMonth;
    /** 物流区编号 */
    private String lgcsCode;
    /** 物流区名称 */
    private String lgcsName;
    /** 供应商编号 */
    private String suppCode;
    /** 供应商名称 */
    private String suppName;
    /** 产品库存类型 */
    private String pdStockType;
    /** 半旬码 */
    private String halfCode;
    /** 产品类别编码 */
    private String classesCode;
    /** 产品类别名称 */
    private String classesName;
    /** 产品加工程度编码 */
    private String machiningCode;
    /** 产品加工程度名称 */
    private String machiningName;
    /** 产品品种编码 */
    private String breedCode;
    /** 产品品种名称 */
    private String breedName;
    /** 产品特征编码 */
    private String featureCode;
    /** 产品特征名称 */
    private String featureName;
    /** 净重编码 */
    private String weightCode;
    /** 净重名称 */
    private String weightName;
    /** 产品等级编码 */
    private String gradeCode;
    /** 产品等级名称 */
    private String gradeName;
    /** 外包装规格 */
    private String outSpec;
    /** 外包装净重 */
    private java.math.BigDecimal outNw;
    /** 产品编码 */
    private String pdCode;
    /** 包装编码 */
    private String normsCode;
    /** 包装名称 */
    private String normsName;
    /** 调整日期 */
    private java.util.Date adjustDate;
    /** 原始计划数量 */
    private java.math.BigDecimal origPlanNum;
    /** 原计划数量 */
    private java.math.BigDecimal oldPlanNum;
    /** 新计划数量 */
    private java.math.BigDecimal newPlanNum;
    /**
     * <p>默认构造函数。</p>
     */
    public DsPdVirtualStockPlan() {

    }

    /**
     * <p>供应分销流水ID。</p>
     *
     * @return the 供应分销流水ID
     */
    public Long getSuppDsId() {
        return suppDsId;
    }

    /**
     * <p>供应分销流水ID。</p>
     *
     * @param suppDsId 供应分销流水ID。
     */
    public void setSuppDsId(Long suppDsId) {
        this.suppDsId = suppDsId;
    }

    /**
     * <p>虚拟库存计划ID。</p>
     *
     * @return the 虚拟库存计划ID
     */
    public Long getVirtualStockPlanId() {
        return virtualStockPlanId;
    }

    /**
     * <p>虚拟库存计划ID。</p>
     *
     * @param virtualStockPlanId 虚拟库存计划ID。
     */
    public void setVirtualStockPlanId(Long virtualStockPlanId) {
        this.virtualStockPlanId = virtualStockPlanId;
    }

    /**
     * <p>分销月度。</p>
     *
     * @return the 分销月度
     */
    public String getDistMonth() {
        return distMonth;
    }

    /**
     * <p>分销月度。</p>
     *
     * @param distMonth 分销月度。
     */
    public void setDistMonth(String distMonth) {
        this.distMonth = distMonth;
    }

    /**
     * <p>物流区编号。</p>
     *
     * @return the 物流区编号
     */
    public String getLgcsCode() {
        return lgcsCode;
    }

    /**
     * <p>物流区编号。</p>
     *
     * @param lgcsCode 物流区编号。
     */
    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    /**
     * <p>供应商编号。</p>
     *
     * @return the 供应商编号
     */
    public String getSuppCode() {
        return suppCode;
    }

    /**
     * <p>供应商编号。</p>
     *
     * @param suppCode 供应商编号。
     */
    public void setSuppCode(String suppCode) {
        this.suppCode = suppCode;
    }

    /**
     * <p>产品库存类型。</p>
     *
     * @return the 产品库存类型
     */
    public String getPdStockType() {
        return pdStockType;
    }

    /**
     * <p>产品库存类型。</p>
     *
     * @param pdStockType 产品库存类型。
     */
    public void setPdStockType(String pdStockType) {
        this.pdStockType = pdStockType;
    }

    /**
     * <p>半旬码。</p>
     *
     * @return the 半旬码
     */
    public String getHalfCode() {
        return halfCode;
    }

    /**
     * <p>半旬码。</p>
     *
     * @param halfCode 半旬码。
     */
    public void setHalfCode(String halfCode) {
        this.halfCode = halfCode;
    }

    /**
     * <p>产品类别编码。</p>
     *
     * @return the 产品类别编码
     */
    public String getClassesCode() {
        return classesCode;
    }

    /**
     * <p>产品类别编码。</p>
     *
     * @param classesCode 产品类别编码。
     */
    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    /**
     * <p>产品品种编码。</p>
     *
     * @return the 产品品种编码
     */
    public String getBreedCode() {
        return breedCode;
    }

    /**
     * <p>产品品种编码。</p>
     *
     * @param breedCode 产品品种编码。
     */
    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
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
     * <p>产品等级编码。</p>
     *
     * @return the 产品等级编码
     */
    public String getGradeCode() {
        return gradeCode;
    }

    /**
     * <p>产品等级编码。</p>
     *
     * @param gradeCode 产品等级编码。
     */
    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    /**
     * <p>外包装规格。</p>
     *
     * @return the 外包装规格
     */
    public String getOutSpec() {
        return outSpec;
    }

    /**
     * <p>外包装规格。</p>
     *
     * @param outSpec 外包装规格。
     */
    public void setOutSpec(String outSpec) {
        this.outSpec = outSpec;
    }

    /**
     * <p>外包装净重。</p>
     *
     * @return the 外包装净重
     */
    public java.math.BigDecimal getOutNw() {
        return outNw;
    }

    /**
     * <p>外包装净重。</p>
     *
     * @param outNw 外包装净重。
     */
    public void setOutNw(java.math.BigDecimal outNw) {
        this.outNw = outNw;
    }

    /**
     * <p>产品编码。</p>
     *
     * @return the 产品编码
     */
    public String getPdCode() {
        return pdCode;
    }

    /**
     * <p>产品编码。</p>
     *
     * @param pdCode 产品编码。
     */
    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
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
     * <p>调整日期。</p>
     *
     * @return the 调整日期
     */
    public java.util.Date getAdjustDate() {
        return adjustDate;
    }

    /**
     * <p>调整日期。</p>
     *
     * @param adjustDate 调整日期。
     */
    public void setAdjustDate(java.util.Date adjustDate) {
        this.adjustDate = adjustDate;
    }

    /**
     * <p>原始计划数量。</p>
     *
     * @return the 原始计划数量
     */
    public java.math.BigDecimal getOrigPlanNum() {
        return origPlanNum;
    }

    /**
     * <p>原始计划数量。</p>
     *
     * @param origPlanNum 原始计划数量。
     */
    public void setOrigPlanNum(java.math.BigDecimal origPlanNum) {
        this.origPlanNum = origPlanNum;
    }

    /**
     * <p>原计划数量。</p>
     *
     * @return the 原计划数量
     */
    public java.math.BigDecimal getOldPlanNum() {
        return oldPlanNum;
    }

    /**
     * <p>原计划数量。</p>
     *
     * @param oldPlanNum 原计划数量。
     */
    public void setOldPlanNum(java.math.BigDecimal oldPlanNum) {
        this.oldPlanNum = oldPlanNum;
    }

    /**
     * <p>新计划数量。</p>
     *
     * @return the 新计划数量
     */
    public java.math.BigDecimal getNewPlanNum() {
        return newPlanNum;
    }

    /**
     * <p>新计划数量。</p>
     *
     * @param newPlanNum 新计划数量。
     */
    public void setNewPlanNum(java.math.BigDecimal newPlanNum) {
        this.newPlanNum = newPlanNum;
    }

    public String getSuppName() {
        return suppName;
    }

    public void setSuppName(String suppName) {
        this.suppName = suppName;
    }

    public String getLgcsName() {
        return lgcsName;
    }

    public void setLgcsName(String lgcsName) {
        this.lgcsName = lgcsName;
    }

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getNormsName() {
        return normsName;
    }

    public void setNormsName(String normsName) {
        this.normsName = normsName;
    }

    public String getMachiningCode() {
        return machiningCode;
    }

    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    public String getMachiningName() {
        return machiningName;
    }

    public void setMachiningName(String machiningName) {
        this.machiningName = machiningName;
    }

    public String getWeightCode() {
        return weightCode;
    }

    public void setWeightCode(String weightCode) {
        this.weightCode = weightCode;
    }

    public String getWeightName() {
        return weightName;
    }

    public void setWeightName(String weightName) {
        this.weightName = weightName;
    }

}
