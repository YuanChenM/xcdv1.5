/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表ds_pd_virtual_stock_actual_history对应的DsPdVirtualStockActualHistory。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class DsPdVirtualStockActualHistory extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** VIRTUAL_STOCK_ACTUAL_HISTORY_ID */
    private Long virtualStockActualHistoryId;
    /** SUPP_DS_ID */
    private Long suppDsId;
    /** VIRTUAL_STOCK_ACTUAL_ID */
    private Long virtualStockActualId;
    /** DIST_MONTH */
    private String distMonth;
    /** LGCS_CODE */
    private String lgcsCode;
    /** SUPP_CODE */
    private String suppCode;
    /** PD_STOCK_TYPE */
    private String pdStockType;
    /** HALF_CODE */
    private String halfCode;
    /** CLASSES_CODE */
    private String classesCode;
    /** BREED_CODE */
    private String breedCode;
    /** FEATURE_CODE */
    private String featureCode;
    /** GRADE_CODE */
    private String gradeCode;
    /** OUT_SPEC */
    private String outSpec;
    /** OUT_NW */
    private java.math.BigDecimal outNw;
    /** MACHINING_CODE */
    private String machiningCode;
    /** MACHINING_NAME */
    private String machiningName;
    /** PD_CODE */
    private String pdCode;
    /** NORMS_CODE */
    private String normsCode;
    /** INPUT_DATE */
    private java.util.Date inputDate;
    /** OLD_ACTUAL_NUM */
    private java.math.BigDecimal oldActualNum;
    /** NEW_ACTUAL_NUM */
    private java.math.BigDecimal newActualNum;
    /**
     * <p>默认构造函数。</p>
     */
    public DsPdVirtualStockActualHistory() {

    }

    /**
     * <p>VIRTUAL_STOCK_ACTUAL_HISTORY_ID。</p>
     *
     * @return the VIRTUAL_STOCK_ACTUAL_HISTORY_ID
     */
    public Long getVirtualStockActualHistoryId() {
        return virtualStockActualHistoryId;
    }

    /**
     * <p>VIRTUAL_STOCK_ACTUAL_HISTORY_ID。</p>
     *
     * @param virtualStockActualHistoryId VIRTUAL_STOCK_ACTUAL_HISTORY_ID。
     */
    public void setVirtualStockActualHistoryId(Long virtualStockActualHistoryId) {
        this.virtualStockActualHistoryId = virtualStockActualHistoryId;
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
     * <p>VIRTUAL_STOCK_ACTUAL_ID。</p>
     *
     * @return the VIRTUAL_STOCK_ACTUAL_ID
     */
    public Long getVirtualStockActualId() {
        return virtualStockActualId;
    }

    /**
     * <p>VIRTUAL_STOCK_ACTUAL_ID。</p>
     *
     * @param virtualStockActualId VIRTUAL_STOCK_ACTUAL_ID。
     */
    public void setVirtualStockActualId(Long virtualStockActualId) {
        this.virtualStockActualId = virtualStockActualId;
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
     * <p>SUPP_CODE。</p>
     *
     * @return the SUPP_CODE
     */
    public String getSuppCode() {
        return suppCode;
    }

    /**
     * <p>SUPP_CODE。</p>
     *
     * @param suppCode SUPP_CODE。
     */
    public void setSuppCode(String suppCode) {
        this.suppCode = suppCode;
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
     * <p>INPUT_DATE。</p>
     *
     * @return the INPUT_DATE
     */
    public java.util.Date getInputDate() {
        return inputDate;
    }

    /**
     * <p>INPUT_DATE。</p>
     *
     * @param inputDate INPUT_DATE。
     */
    public void setInputDate(java.util.Date inputDate) {
        this.inputDate = inputDate;
    }

    /**
     * <p>OLD_ACTUAL_NUM。</p>
     *
     * @return the OLD_ACTUAL_NUM
     */
    public java.math.BigDecimal getOldActualNum() {
        return oldActualNum;
    }

    /**
     * <p>OLD_ACTUAL_NUM。</p>
     *
     * @param oldActualNum OLD_ACTUAL_NUM。
     */
    public void setOldActualNum(java.math.BigDecimal oldActualNum) {
        this.oldActualNum = oldActualNum;
    }

    /**
     * <p>NEW_ACTUAL_NUM。</p>
     *
     * @return the NEW_ACTUAL_NUM
     */
    public java.math.BigDecimal getNewActualNum() {
        return newActualNum;
    }

    /**
     * <p>NEW_ACTUAL_NUM。</p>
     *
     * @param newActualNum NEW_ACTUAL_NUM。
     */
    public void setNewActualNum(java.math.BigDecimal newActualNum) {
        this.newActualNum = newActualNum;
    }

}
