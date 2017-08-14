package com.msk.product.bean;

/**
 * PD14210101Bean
 *
 * @author xhy
 */
public class PD14210201Bean extends PD14210101Bean {

    private static final long serialVersionUID = 1L;

    private String gradeName;

    private String featureName;

    private String attributeCode;

    private String weightName;
    //单箱净重编码
    private String weightCode;
    //包装编码
    private String normsCode;
    private String normsOut;

    /**
     * Getter method for property <tt>serialVersionUID</tt>.
     *
     * @return property value of serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
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
     * Getter method for property <tt>normsCode</tt>.
     *
     * @return property value of normsCode
     */
    public String getNormsCode() {
        return normsCode;
    }

    /**
     * Setter method for property <tt>normsCode</tt>.
     *
     * @param normsCode value to be assigned to property normsCode
     */
    public void setNormsCode(String normsCode) {
        this.normsCode = normsCode;
    }

    /**
     * Getter method for property <tt>gradeName</tt>.
     *
     * @return property value of gradeName
     */
    public String getGradeName() {
        return gradeName;
    }

    /**
     * Setter method for property <tt>gradeName</tt>.
     *
     * @param gradeName value to be assigned to property gradeName
     */
    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    /**
     * Getter method for property <tt>featureName</tt>.
     *
     * @return property value of featureName
     */
    public String getFeatureName() {
        return featureName;
    }

    /**
     * Setter method for property <tt>featureName</tt>.
     *
     * @param featureName value to be assigned to property featureName
     */
    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    /**
     * Getter method for property <tt>attributeCode</tt>.
     *
     * @return property value of attributeCode
     */
    public String getAttributeCode() {
        return attributeCode;
    }

    /**
     * Setter method for property <tt>attributeCode</tt>.
     *
     * @param attributeCode value to be assigned to property attributeCode
     */
    public void setAttributeCode(String attributeCode) {
        this.attributeCode = attributeCode;
    }

    /**
     * Getter method for property <tt>weightName</tt>.
     *
     * @return property value of weightName
     */
    public String getWeightName() {
        return weightName;
    }

    /**
     * Setter method for property <tt>weightName</tt>.
     *
     * @param weightName value to be assigned to property weightName
     */
    public void setWeightName(String weightName) {
        this.weightName = weightName;
    }

    /**
     * Getter method for property <tt>normsOut</tt>.
     *
     * @return property value of normsOut
     */
    public String getNormsOut() {
        return normsOut;
    }

    /**
     * Setter method for property <tt>normsOut</tt>.
     *
     * @param normsOut value to be assigned to property normsOut
     */
    public void setNormsOut(String normsOut) {
        this.normsOut = normsOut;
    }
}
