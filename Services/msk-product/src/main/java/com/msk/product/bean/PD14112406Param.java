package com.msk.product.bean;


import com.hoperun.core.bean.BaseParam;

/**
 * PD14112406 Controller Param
 * @author yangchunyan
 *
 */
public class PD14112406Param extends BaseParam {
    
    private static final long serialVersionUID = 1L;
    /** 产品类别编码 */
    private String classesCode;
    /** 产品类别名称 */
    private String classesName;
    /** 产品二级类别编码 */
    private String machiningCode;
    /** 产品二级类别名称 */
    private String machiningName;
    /** 产品品种编码 */
    private String breedCode;
    /** 产品品种名称 */
    private String breedName;
    /** 产品特征编码 */
    private String featureCode;
    /** 产品特征名称 */
    private String featureName;
    /** 产品净重编码 */
    private String weightCode;
    /** 产品净重编名称 */
    private String weightName;
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

    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
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
