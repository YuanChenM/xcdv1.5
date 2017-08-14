package com.msk.seller.bean;

import com.msk.common.base.BaseBean;

/**
 * SlLogiAreaPdBean.
 *
 * @author yuan_chen
 */
public class SlLogiAreaPdBean extends BaseBean {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** 卖家编码 */
    private String slCode;
    /** 卖家名称 */
    private String slName;
    /** 物流区编码 */
    private String logiAreaCode;
    /** 物流区名称 */
    private String logiAreaName;
    /** 产品编码 */
    private String pdCode;
    /** 产品名称 */
    private String pdName;
    /** 分类编码 */
    private String classesCode;
    /** 品种编码 */
    private String breedCode;
    /** 特征编码 */
    private String featureCode;

    /** 包装等级编码 */
    private String gradeCode;
    /** 包装编码 */
    private String pkgCode;

    /**
     * 获得pkgCode
     */
    public String getPkgCode() {
        return pkgCode;
    }

    /**
     * 设置pkgCode
     */
    public void setPkgCode(String pkgCode) {
        this.pkgCode = pkgCode;
    }

    /**
     * 获得gradeCode
     */
    public String getGradeCode() {
        return gradeCode;
    }

    /**
     * 设置gradeCode
     */
    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }



    /**
     * Getter method for property <tt>slCode</tt>.
     *
     * @return property value of slCode
     */
    public String getSlCode() {
        return slCode;
    }

    /**
     * Setter method for property <tt>slCode</tt>.
     *
     * @param slCode value to be assigned to property slCode
     */
    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    /**
     * Getter method for property <tt>slName</tt>.
     *
     * @return property value of slName
     */
    public String getSlName() {
        return slName;
    }

    /**
     * Setter method for property <tt>slName</tt>.
     *
     * @param slName value to be assigned to property slName
     */
    public void setSlName(String slName) {
        this.slName = slName;
    }

    /**
     * Getter method for property <tt>featureCode</tt>.
     *
     * @return property value of featureCode
     */
    public String getFeatureCode() {
        return featureCode;
    }

    /**
     * Setter method for property <tt>featureCode</tt>.
     *
     * @param featureCode value to be assigned to property featureCode
     */
    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    /**
     * Getter method for property <tt>logiAreaCode</tt>.
     *
     * @return property value of logiAreaCode
     */
    public String getLogiAreaCode() {
        return logiAreaCode;
    }

    /**
     * Setter method for property <tt>logiAreaCode</tt>.
     *
     * @param logiAreaCode value to be assigned to property logiAreaCode
     */
    public void setLogiAreaCode(String logiAreaCode) {
        this.logiAreaCode = logiAreaCode;
    }

    /**
     * Getter method for property <tt>logiAreaName</tt>.
     *
     * @return property value of logiAreaName
     */
    public String getLogiAreaName() {
        return logiAreaName;
    }

    /**
     * Setter method for property <tt>logiAreaName</tt>.
     *
     * @param logiAreaName value to be assigned to property logiAreaName
     */
    public void setLogiAreaName(String logiAreaName) {
        this.logiAreaName = logiAreaName;
    }

    /**
     * Getter method for property <tt>pdCode</tt>.
     *
     * @return property value of pdCode
     */
    public String getPdCode() {
        return pdCode;
    }

    /**
     * Setter method for property <tt>pdCode</tt>.
     *
     * @param pdCode value to be assigned to property pdCode
     */
    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    /**
     * Getter method for property <tt>pdName</tt>.
     *
     * @return property value of pdName
     */
    public String getPdName() {
        return pdName;
    }

    /**
     * Setter method for property <tt>pdName</tt>.
     *
     * @param pdName value to be assigned to property pdName
     */
    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    /**
     * Getter method for property <tt>classesCode</tt>.
     *
     * @return property value of classesCode
     */
    public String getClassesCode() {
        return classesCode;
    }

    /**
     * Setter method for property <tt>classesCode</tt>.
     *
     * @param classesCode value to be assigned to property classesCode
     */
    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    /**
     * Getter method for property <tt>breedCode</tt>.
     *
     * @return property value of breedCode
     */
    public String getBreedCode() {
        return breedCode;
    }

    /**
     * Setter method for property <tt>breedCode</tt>.
     *
     * @param breedCode value to be assigned to property breedCode
     */
    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }
}
