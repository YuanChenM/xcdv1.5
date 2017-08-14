/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.product.bean;


import com.hoperun.core.bean.BaseParam;

/**
 * 价盘Param
 * @author zhou_ling
 */
public class PD141199Param extends BaseParam {
    
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** 产品类别编码 */
    private String classesCode;
    
    /** 产品种类编码 */
    private String breedCode;
    
    /** 产品特征编码 */
    private String featureCode;
    
    /** 加工类型编码 */
    private String machiningCode;
    
    /** 包装编码 */
    private String pkgCode;
    /**净重编码*/
    private String weightCode;
    
    /** 产品等级编码 */
    private String gradeCode;
    
    /** 物流区编码 */
    private String logiareaCode;

    /** 价盘周期的年月 */
    private String pricecycleDate;
    
    /** 价盘周期旬*/
    private String pricecycle;
    
    /** 价盘周期*/
    private String pricecyclePeriod;
    //价盘ID
    private Long pricecycleId;


    /**
     * Getter method for property <tt>pricecycleId</tt>.
     *
     * @return property value of pricecycleId
     */
    public Long getPricecycleId() {
        return pricecycleId;
    }

    /**
     * Setter method for property <tt>pricecycleId</tt>.
     *
     * @param pricecycleId value to be assigned to property pricecycleId
     */
    public void setPricecycleId(Long pricecycleId) {
        this.pricecycleId = pricecycleId;
    }

    /**
     * Getter method for property <tt>weightCode</tt>.
     *
     * @return property value of weightCode
     */
    public String getWeightCode() {
        return weightCode;
    }

    /**
     * Setter method for property <tt>weightCode</tt>.
     *
     * @param weightCode value to be assigned to property weightCode
     */
    public void setWeightCode(String weightCode) {
        this.weightCode = weightCode;
    }

    /**
     * Get the classesCode.
     *
     * @return classesCode
     *
     * @author zhou_ling
     */
    public String getClassesCode() {
        return this.classesCode;
    }

    /**
     * Set the classesCode.
     *
     * @param classesCode classesCode
     *
     * @author zhou_ling
     */
    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    /**
     * Get the breedCode.
     *
     * @return breedCode
     *
     * @author zhou_ling
     */
    public String getBreedCode() {
        return this.breedCode;
    }

    /**
     * Set the breedCode.
     *
     * @param breedCode breedCode
     *
     * @author zhou_ling
     */
    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    /**
     * Get the featureCode.
     *
     * @return featureCode
     *
     * @author zhou_ling
     */
    public String getFeatureCode() {
        return this.featureCode;
    }

    /**
     * Set the featureCode.
     *
     * @param featureCode featureCode
     *
     * @author zhou_ling
     */
    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    /**
     * Get the machiningCode.
     *
     * @return machiningCode
     *
     * @author zhou_ling
     */
    public String getMachiningCode() {
        return this.machiningCode;
    }

    /**
     * Set the machiningCode.
     *
     * @param machiningCode machiningCode
     *
     * @author zhou_ling
     */
    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    /**
     * Get the pkgCode.
     *
     * @return pkgCode
     *
     * @author zhou_ling
     */
    public String getPkgCode() {
        return this.pkgCode;
    }

    /**
     * Set the pkgCode.
     *
     * @param pkgCode pkgCode
     *
     * @author zhou_ling
     */
    public void setPkgCode(String pkgCode) {
        this.pkgCode = pkgCode;
    }

    /**
     * Get the gradeCode.
     *
     * @return gradeCode
     *
     * @author zhou_ling
     */
    public String getGradeCode() {
        return this.gradeCode;
    }

    /**
     * Set the gradeCode.
     *
     * @param gradeCode gradeCode
     *
     * @author zhou_ling
     */
    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    /**
     * Get the logiareaCode.
     *
     * @return logiareaCode
     *
     * @author zhou_ling
     */
    public String getLogiareaCode() {
        return this.logiareaCode;
    }

    /**
     * Set the logiareaCode.
     *
     * @param logiareaCode logiareaCode
     *
     * @author zhou_ling
     */
    public void setLogiareaCode(String logiareaCode) {
        this.logiareaCode = logiareaCode;
    }

    /**
     * Get the pricecycleDate.
     *
     * @return pricecycleDate
     *
     * @author zhou_ling
     */
    public String getPricecycleDate() {
        return this.pricecycleDate;
    }

    /**
     * Set the pricecycleDate.
     *
     * @param pricecycleDate pricecycleDate
     *
     * @author zhou_ling
     */
    public void setPricecycleDate(String pricecycleDate) {
        this.pricecycleDate = pricecycleDate;
    }

    /**
     * Get the pricecycle.
     *
     * @return pricecycle
     *
     * @author zhou_ling
     */
    public String getPricecycle() {
        return this.pricecycle;
    }

    /**
     * Set the pricecycle.
     *
     * @param pricecycle pricecycle
     *
     * @author zhou_ling
     */
    public void setPricecycle(String pricecycle) {
        this.pricecycle = pricecycle;
    }

    /**
     * Get the pricecyclePeriod.
     *
     * @return pricecyclePeriod
     *
     * @author zhou_ling
     */
    public String getPricecyclePeriod() {
        return this.pricecyclePeriod;
    }

    /**
     * Set the pricecyclePeriod.
     *
     * @param pricecyclePeriod pricecyclePeriod
     *
     * @author zhou_ling
     */
    public void setPricecyclePeriod(String pricecyclePeriod) {
        this.pricecyclePeriod = pricecyclePeriod;
    }
    
}
