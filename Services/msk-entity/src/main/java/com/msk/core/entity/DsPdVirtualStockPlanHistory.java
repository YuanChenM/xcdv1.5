/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表ds_pd_virtual_stock_plan_history对应的DsPdVirtualStockPlanHistory。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class DsPdVirtualStockPlanHistory extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** VIRTUAL_STOCK_PLAN_HISTORY_ID */
    private Long virtualStockPlanHistoryId;
    /** SUPP_DS_ID */
    private Long suppDsId;
    /** VIRTUAL_STOCK_PLAN_ID */
    private Long virtualStockPlanId;
    /** DIST_MONTH */
    private String distMonth;
    /** LGCS_CODE */
    private String lgcsCode;
    /** LGCS_NAME */
    private String lgcsName;
    /** 供应商编号:对应SL_CODE */
    private String suppCode;
    /** 供应商名称:对应EP_NAME */
    private String suppName;
    /** PD_STOCK_TYPE */
    private String pdStockType;
    /** HALF_CODE */
    private String halfCode;
    /** CLASSES_CODE */
    private String classesCode;
    /** CLASSES_NAME */
    private String classesName;
    /** MACHINING_CODE */
    private String machiningCode;
    /** MACHINING_NAME */
    private String machiningName;
    /** BREED_CODE */
    private String breedCode;
    /** BREED_NAME */
    private String breedName;
    /** FEATURE_CODE */
    private String featureCode;
    /** FEATURE_NAME */
    private String featureName;
    /** WEIGHT_CODE */
    private String weightCode;
    /** WEIGHT_NAME */
    private String weightName;
    /** GRADE_CODE */
    private String gradeCode;
    /** GRADE_NAME */
    private String gradeName;
    /** OUT_SPEC */
    private String outSpec;
    /** OUT_NW */
    private java.math.BigDecimal outNw;
    /** PD_CODE */
    private String pdCode;
    /** NORMS_CODE */
    private String normsCode;
    /** NORMS_NAME */
    private String normsName;
    /** ADJUST_DATE */
    private java.util.Date adjustDate;
    /** ORIG_PLAN_NUM */
    private java.math.BigDecimal origPlanNum;
    /** OLD_PLAN_NUM */
    private java.math.BigDecimal oldPlanNum;
    /** NEW_PLAN_NUM */
    private java.math.BigDecimal newPlanNum;
    /**
     * <p>默认构造函数。</p>
     */
    public DsPdVirtualStockPlanHistory() {

    }

    /**
     * <p>VIRTUAL_STOCK_PLAN_HISTORY_ID。</p>
     *
     * @return the VIRTUAL_STOCK_PLAN_HISTORY_ID
     */
    public Long getVirtualStockPlanHistoryId() {
        return virtualStockPlanHistoryId;
    }

    /**
     * <p>VIRTUAL_STOCK_PLAN_HISTORY_ID。</p>
     *
     * @param virtualStockPlanHistoryId VIRTUAL_STOCK_PLAN_HISTORY_ID。
     */
    public void setVirtualStockPlanHistoryId(Long virtualStockPlanHistoryId) {
        this.virtualStockPlanHistoryId = virtualStockPlanHistoryId;
    }

    /**
     * <p>SUPP_DS_ID。</p>
     *
     * @return the SUPP_DS_ID
     */
    public Long getSuppDsId() {
        return suppDsId;
    }

    /**
     * <p>SUPP_DS_ID。</p>
     *
     * @param suppDsId SUPP_DS_ID。
     */
    public void setSuppDsId(Long suppDsId) {
        this.suppDsId = suppDsId;
    }

    /**
     * <p>VIRTUAL_STOCK_PLAN_ID。</p>
     *
     * @return the VIRTUAL_STOCK_PLAN_ID
     */
    public Long getVirtualStockPlanId() {
        return virtualStockPlanId;
    }

    /**
     * <p>VIRTUAL_STOCK_PLAN_ID。</p>
     *
     * @param virtualStockPlanId VIRTUAL_STOCK_PLAN_ID。
     */
    public void setVirtualStockPlanId(Long virtualStockPlanId) {
        this.virtualStockPlanId = virtualStockPlanId;
    }

    /**
     * <p>DIST_MONTH。</p>
     *
     * @return the DIST_MONTH
     */
    public String getDistMonth() {
        return distMonth;
    }

    /**
     * <p>DIST_MONTH。</p>
     *
     * @param distMonth DIST_MONTH。
     */
    public void setDistMonth(String distMonth) {
        this.distMonth = distMonth;
    }

    /**
     * <p>LGCS_CODE。</p>
     *
     * @return the LGCS_CODE
     */
    public String getLgcsCode() {
        return lgcsCode;
    }

    /**
     * <p>LGCS_CODE。</p>
     *
     * @param lgcsCode LGCS_CODE。
     */
    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    /**
     * <p>LGCS_NAME。</p>
     *
     * @return the LGCS_NAME
     */
    public String getLgcsName() {
        return lgcsName;
    }

    /**
     * <p>LGCS_NAME。</p>
     *
     * @param lgcsName LGCS_NAME。
     */
    public void setLgcsName(String lgcsName) {
        this.lgcsName = lgcsName;
    }

    /**
     * <p>供应商编号:对应SL_CODE。</p>
     *
     * @return the 供应商编号:对应SL_CODE
     */
    public String getSuppCode() {
        return suppCode;
    }

    /**
     * <p>供应商编号:对应SL_CODE。</p>
     *
     * @param suppCode 供应商编号:对应SL_CODE。
     */
    public void setSuppCode(String suppCode) {
        this.suppCode = suppCode;
    }

    /**
     * <p>供应商名称:对应EP_NAME。</p>
     *
     * @return the 供应商名称:对应EP_NAME
     */
    public String getSuppName() {
        return suppName;
    }

    /**
     * <p>供应商名称:对应EP_NAME。</p>
     *
     * @param suppName 供应商名称:对应EP_NAME。
     */
    public void setSuppName(String suppName) {
        this.suppName = suppName;
    }

    /**
     * <p>PD_STOCK_TYPE。</p>
     *
     * @return the PD_STOCK_TYPE
     */
    public String getPdStockType() {
        return pdStockType;
    }

    /**
     * <p>PD_STOCK_TYPE。</p>
     *
     * @param pdStockType PD_STOCK_TYPE。
     */
    public void setPdStockType(String pdStockType) {
        this.pdStockType = pdStockType;
    }

    /**
     * <p>HALF_CODE。</p>
     *
     * @return the HALF_CODE
     */
    public String getHalfCode() {
        return halfCode;
    }

    /**
     * <p>HALF_CODE。</p>
     *
     * @param halfCode HALF_CODE。
     */
    public void setHalfCode(String halfCode) {
        this.halfCode = halfCode;
    }

    /**
     * <p>CLASSES_CODE。</p>
     *
     * @return the CLASSES_CODE
     */
    public String getClassesCode() {
        return classesCode;
    }

    /**
     * <p>CLASSES_CODE。</p>
     *
     * @param classesCode CLASSES_CODE。
     */
    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    /**
     * <p>CLASSES_NAME。</p>
     *
     * @return the CLASSES_NAME
     */
    public String getClassesName() {
        return classesName;
    }

    /**
     * <p>CLASSES_NAME。</p>
     *
     * @param classesName CLASSES_NAME。
     */
    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    /**
     * <p>MACHINING_CODE。</p>
     *
     * @return the MACHINING_CODE
     */
    public String getMachiningCode() {
        return machiningCode;
    }

    /**
     * <p>MACHINING_CODE。</p>
     *
     * @param machiningCode MACHINING_CODE。
     */
    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    /**
     * <p>MACHINING_NAME。</p>
     *
     * @return the MACHINING_NAME
     */
    public String getMachiningName() {
        return machiningName;
    }

    /**
     * <p>MACHINING_NAME。</p>
     *
     * @param machiningName MACHINING_NAME。
     */
    public void setMachiningName(String machiningName) {
        this.machiningName = machiningName;
    }

    /**
     * <p>BREED_CODE。</p>
     *
     * @return the BREED_CODE
     */
    public String getBreedCode() {
        return breedCode;
    }

    /**
     * <p>BREED_CODE。</p>
     *
     * @param breedCode BREED_CODE。
     */
    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    /**
     * <p>BREED_NAME。</p>
     *
     * @return the BREED_NAME
     */
    public String getBreedName() {
        return breedName;
    }

    /**
     * <p>BREED_NAME。</p>
     *
     * @param breedName BREED_NAME。
     */
    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    /**
     * <p>FEATURE_CODE。</p>
     *
     * @return the FEATURE_CODE
     */
    public String getFeatureCode() {
        return featureCode;
    }

    /**
     * <p>FEATURE_CODE。</p>
     *
     * @param featureCode FEATURE_CODE。
     */
    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    /**
     * <p>FEATURE_NAME。</p>
     *
     * @return the FEATURE_NAME
     */
    public String getFeatureName() {
        return featureName;
    }

    /**
     * <p>FEATURE_NAME。</p>
     *
     * @param featureName FEATURE_NAME。
     */
    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    /**
     * <p>WEIGHT_CODE。</p>
     *
     * @return the WEIGHT_CODE
     */
    public String getWeightCode() {
        return weightCode;
    }

    /**
     * <p>WEIGHT_CODE。</p>
     *
     * @param weightCode WEIGHT_CODE。
     */
    public void setWeightCode(String weightCode) {
        this.weightCode = weightCode;
    }

    /**
     * <p>WEIGHT_NAME。</p>
     *
     * @return the WEIGHT_NAME
     */
    public String getWeightName() {
        return weightName;
    }

    /**
     * <p>WEIGHT_NAME。</p>
     *
     * @param weightName WEIGHT_NAME。
     */
    public void setWeightName(String weightName) {
        this.weightName = weightName;
    }

    /**
     * <p>GRADE_CODE。</p>
     *
     * @return the GRADE_CODE
     */
    public String getGradeCode() {
        return gradeCode;
    }

    /**
     * <p>GRADE_CODE。</p>
     *
     * @param gradeCode GRADE_CODE。
     */
    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    /**
     * <p>GRADE_NAME。</p>
     *
     * @return the GRADE_NAME
     */
    public String getGradeName() {
        return gradeName;
    }

    /**
     * <p>GRADE_NAME。</p>
     *
     * @param gradeName GRADE_NAME。
     */
    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    /**
     * <p>OUT_SPEC。</p>
     *
     * @return the OUT_SPEC
     */
    public String getOutSpec() {
        return outSpec;
    }

    /**
     * <p>OUT_SPEC。</p>
     *
     * @param outSpec OUT_SPEC。
     */
    public void setOutSpec(String outSpec) {
        this.outSpec = outSpec;
    }

    /**
     * <p>OUT_NW。</p>
     *
     * @return the OUT_NW
     */
    public java.math.BigDecimal getOutNw() {
        return outNw;
    }

    /**
     * <p>OUT_NW。</p>
     *
     * @param outNw OUT_NW。
     */
    public void setOutNw(java.math.BigDecimal outNw) {
        this.outNw = outNw;
    }

    /**
     * <p>PD_CODE。</p>
     *
     * @return the PD_CODE
     */
    public String getPdCode() {
        return pdCode;
    }

    /**
     * <p>PD_CODE。</p>
     *
     * @param pdCode PD_CODE。
     */
    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    /**
     * <p>NORMS_CODE。</p>
     *
     * @return the NORMS_CODE
     */
    public String getNormsCode() {
        return normsCode;
    }

    /**
     * <p>NORMS_CODE。</p>
     *
     * @param normsCode NORMS_CODE。
     */
    public void setNormsCode(String normsCode) {
        this.normsCode = normsCode;
    }

    /**
     * <p>NORMS_NAME。</p>
     *
     * @return the NORMS_NAME
     */
    public String getNormsName() {
        return normsName;
    }

    /**
     * <p>NORMS_NAME。</p>
     *
     * @param normsName NORMS_NAME。
     */
    public void setNormsName(String normsName) {
        this.normsName = normsName;
    }

    /**
     * <p>ADJUST_DATE。</p>
     *
     * @return the ADJUST_DATE
     */
    public java.util.Date getAdjustDate() {
        return adjustDate;
    }

    /**
     * <p>ADJUST_DATE。</p>
     *
     * @param adjustDate ADJUST_DATE。
     */
    public void setAdjustDate(java.util.Date adjustDate) {
        this.adjustDate = adjustDate;
    }

    /**
     * <p>ORIG_PLAN_NUM。</p>
     *
     * @return the ORIG_PLAN_NUM
     */
    public java.math.BigDecimal getOrigPlanNum() {
        return origPlanNum;
    }

    /**
     * <p>ORIG_PLAN_NUM。</p>
     *
     * @param origPlanNum ORIG_PLAN_NUM。
     */
    public void setOrigPlanNum(java.math.BigDecimal origPlanNum) {
        this.origPlanNum = origPlanNum;
    }

    /**
     * <p>OLD_PLAN_NUM。</p>
     *
     * @return the OLD_PLAN_NUM
     */
    public java.math.BigDecimal getOldPlanNum() {
        return oldPlanNum;
    }

    /**
     * <p>OLD_PLAN_NUM。</p>
     *
     * @param oldPlanNum OLD_PLAN_NUM。
     */
    public void setOldPlanNum(java.math.BigDecimal oldPlanNum) {
        this.oldPlanNum = oldPlanNum;
    }

    /**
     * <p>NEW_PLAN_NUM。</p>
     *
     * @return the NEW_PLAN_NUM
     */
    public java.math.BigDecimal getNewPlanNum() {
        return newPlanNum;
    }

    /**
     * <p>NEW_PLAN_NUM。</p>
     *
     * @param newPlanNum NEW_PLAN_NUM。
     */
    public void setNewPlanNum(java.math.BigDecimal newPlanNum) {
        this.newPlanNum = newPlanNum;
    }

}
