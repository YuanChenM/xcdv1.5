package com.msk.product.bean;


import com.hoperun.core.bean.BaseParam;

/**
 * PD141105 Controller Param
 * @author jiang_nan
 *
 */
public class PD141105Param extends BaseParam {
    
    private static final long serialVersionUID = 1L;
    /** 产品类别编码 */
    private String classesCode;
    /** 产品类别名称 */
    private String classesName;
    /** 产品品种编码 */
    private String breedCode;
    /** 产品标准ID */
    private String standardId;
    /** 产品品种名称 */
    private String breedName;
    /** 质量标准优良值 */
    private String [] pdQltStdValIdArray;
    /** 质量标准合格值 */
    private String [] pdQltStdExcValArray;
    /** 质量标准标准值 */
    private String [] pdQltStdSuitValArray;
    /** 质量标准不合格值 */
    private String [] pdQltStdUnqualValArray;
    /** 备注 */
    private String [] remarkArray;
    /** checkID */
    private String [] checks;
    /**
     * Get the classesCode.
     *
     * @return classesCode
     *
     * @author Administrator
     */
    public String getClassesCode() {
        return this.classesCode;
    }
    /**
     * Set the classesCode.
     *
     * @param classesCode classesCode
     *
     * @author Administrator
     */
    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }
    /**
     * Get the breedCode.
     *
     * @return breedCode
     *
     * @author Administrator
     */
    public String getBreedCode() {
        return this.breedCode;
    }
    /**
     * Set the breedCode.
     *
     * @param breedCode breedCode
     *
     * @author Administrator
     */
    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }
    /**
     * Get the standardId.
     *
     * @return standardId
     *
     * @author Administrator
     */
    public String getStandardId() {
        return this.standardId;
    }
    /**
     * Set the standardId.
     *
     * @param standardId standardId
     *
     * @author Administrator
     */
    public void setStandardId(String standardId) {
        this.standardId = standardId;
    }
    /**
     * Get the breedName.
     *
     * @return breedName
     *
     * @author Administrator
     */
    public String getBreedName() {
        return this.breedName;
    }
    /**
     * Set the breedName.
     *
     * @param breedName breedName
     *
     * @author Administrator
     */
    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }
    /**
     * Get the pdQltStdValIdArray.
     *
     * @return pdQltStdValIdArray
     *
     * @author Administrator
     */
    public String[] getPdQltStdValIdArray() {
        return this.pdQltStdValIdArray;
    }
    /**
     * Set the pdQltStdValIdArray.
     *
     * @param pdQltStdValIdArray pdQltStdValIdArray
     *
     * @author Administrator
     */
    public void setPdQltStdValIdArray(String[] pdQltStdValIdArray) {
        this.pdQltStdValIdArray = pdQltStdValIdArray;
    }
    /**
     * Get the pdQltStdExcValArray.
     *
     * @return pdQltStdExcValArray
     *
     * @author Administrator
     */
    public String[] getPdQltStdExcValArray() {
        return this.pdQltStdExcValArray;
    }
    /**
     * Set the pdQltStdExcValArray.
     *
     * @param pdQltStdExcValArray pdQltStdExcValArray
     *
     * @author Administrator
     */
    public void setPdQltStdExcValArray(String[] pdQltStdExcValArray) {
        this.pdQltStdExcValArray = pdQltStdExcValArray;
    }
    /**
     * Get the pdQltStdSuitValArray.
     *
     * @return pdQltStdSuitValArray
     *
     * @author Administrator
     */
    public String[] getPdQltStdSuitValArray() {
        return this.pdQltStdSuitValArray;
    }
    /**
     * Set the pdQltStdSuitValArray.
     *
     * @param pdQltStdSuitValArray pdQltStdSuitValArray
     *
     * @author Administrator
     */
    public void setPdQltStdSuitValArray(String[] pdQltStdSuitValArray) {
        this.pdQltStdSuitValArray = pdQltStdSuitValArray;
    }
    /**
     * Get the pdQltStdUnqualValArray.
     *
     * @return pdQltStdUnqualValArray
     *
     * @author Administrator
     */
    public String[] getPdQltStdUnqualValArray() {
        return this.pdQltStdUnqualValArray;
    }
    /**
     * Set the pdQltStdUnqualValArray.
     *
     * @param pdQltStdUnqualValArray pdQltStdUnqualValArray
     *
     * @author Administrator
     */
    public void setPdQltStdUnqualValArray(String[] pdQltStdUnqualValArray) {
        this.pdQltStdUnqualValArray = pdQltStdUnqualValArray;
    }
    /**
     * Get the remarkArray.
     *
     * @return remarkArray
     *
     * @author Administrator
     */
    public String[] getRemarkArray() {
        return this.remarkArray;
    }
    /**
     * Set the remarkArray.
     *
     * @param remarkArray remarkArray
     *
     * @author Administrator
     */
    public void setRemarkArray(String[] remarkArray) {
        this.remarkArray = remarkArray;
    }
    /**
     * Get the checks.
     *
     * @return checks
     *
     * @author Administrator
     */
    public String[] getChecks() {
        return this.checks;
    }
    /**
     * Set the checks.
     *
     * @param checks checks
     *
     * @author Administrator
     */
    public void setChecks(String[] checks) {
        this.checks = checks;
    }
    /**
     * Get the classesName.
     *
     * @return classesName
     *
     * @author Administrator
     */
    public String getClassesName() {
        return this.classesName;
    }
    /**
     * Set the classesName.
     *
     * @param classesName classesName
     *
     * @author Administrator
     */
    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }
    
}
