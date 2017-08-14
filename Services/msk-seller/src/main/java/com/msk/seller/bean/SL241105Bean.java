package com.msk.seller.bean;

import com.msk.core.entity.SlProduct;

/**
 * Created by gyh on 2016/1/9.
 */
public class SL241105Bean extends SlProduct{
    /**生产商名称*/
    private String prodEpName;
    /**品牌名称*/
    private String brandName;
    /**产品类别*/
    private String classesName;
    /**产品品种*/
    private String breedName;
    /**产品编码*/
    private String productCode;
    /**产品特征*/
    private String featureName;
    //加工质量定级名称
    private String slTncGradeName;
    //加工技术定级名称
    private String slQltGradeName;
    //二级分类名称
    private String machiningName;
    private Integer slTncGradeCodeOld;//产品技术标准定级旧的
    private Integer tncMonitorResultOld;//产品技术标准定级监控人审核意见
    private Integer slQltGradeCodeOld;//产品质量标准定级旧的
    private Integer qltMonitorResultOld;//产品质量标准定级监控人审核意见

    /**
     * Getter method for property <tt>slQltGradeCodeOld</tt>.
     *
     * @return property value of slQltGradeCodeOld
     */
    public Integer getSlQltGradeCodeOld() {
        return slQltGradeCodeOld;
    }

    /**
     * Setter method for property <tt>slQltGradeCodeOld</tt>.
     *
     * @param slQltGradeCodeOld value to be assigned to property slQltGradeCodeOld
     */
    public void setSlQltGradeCodeOld(Integer slQltGradeCodeOld) {
        this.slQltGradeCodeOld = slQltGradeCodeOld;
    }

    /**
     * Getter method for property <tt>qltMonitorResultOld</tt>.
     *
     * @return property value of qltMonitorResultOld
     */
    public Integer getQltMonitorResultOld() {
        return qltMonitorResultOld;
    }

    /**
     * Setter method for property <tt>qltMonitorResultOld</tt>.
     *
     * @param qltMonitorResultOld value to be assigned to property qltMonitorResultOld
     */
    public void setQltMonitorResultOld(Integer qltMonitorResultOld) {
        this.qltMonitorResultOld = qltMonitorResultOld;
    }

    /**
     * Getter method for property <tt>slTncGradeCodeOld</tt>.
     *
     * @return property value of slTncGradeCodeOld
     */
    public Integer getSlTncGradeCodeOld() {
        return slTncGradeCodeOld;
    }

    /**
     * Setter method for property <tt>slTncGradeCodeOld</tt>.
     *
     * @param slTncGradeCodeOld value to be assigned to property slTncGradeCodeOld
     */
    public void setSlTncGradeCodeOld(Integer slTncGradeCodeOld) {
        this.slTncGradeCodeOld = slTncGradeCodeOld;
    }

    /**
     * Getter method for property <tt>tncMonitorResultOld</tt>.
     *
     * @return property value of tncMonitorResultOld
     */
    public Integer getTncMonitorResultOld() {
        return tncMonitorResultOld;
    }

    /**
     * Setter method for property <tt>tncMonitorResultOld</tt>.
     *
     * @param tncMonitorResultOld value to be assigned to property tncMonitorResultOld
     */
    public void setTncMonitorResultOld(Integer tncMonitorResultOld) {
        this.tncMonitorResultOld = tncMonitorResultOld;
    }

    /**
     * Getter method for property <tt>machiningName</tt>.
     *
     * @return property value of machiningName
     */
    public String getMachiningName() {
        return machiningName;
    }

    /**
     * Setter method for property <tt>machiningName</tt>.
     *
     * @param machiningName value to be assigned to property machiningName
     */
    public void setMachiningName(String machiningName) {
        this.machiningName = machiningName;
    }

    /**
     * Getter method for property <tt>slTncGradeName</tt>.
     *
     * @return property value of slTncGradeName
     */
    public String getSlTncGradeName() {
        return slTncGradeName;
    }

    /**
     * Setter method for property <tt>slTncGradeName</tt>.
     *
     * @param slTncGradeName value to be assigned to property slTncGradeName
     */
    public void setSlTncGradeName(String slTncGradeName) {
        this.slTncGradeName = slTncGradeName;
    }

    /**
     * Getter method for property <tt>slQltGradeName</tt>.
     *
     * @return property value of slQltGradeName
     */
    public String getSlQltGradeName() {
        return slQltGradeName;
    }

    /**
     * Setter method for property <tt>slQltGradeName</tt>.
     *
     * @param slQltGradeName value to be assigned to property slQltGradeName
     */
    public void setSlQltGradeName(String slQltGradeName) {
        this.slQltGradeName = slQltGradeName;
    }

    /**
     * 获得featureName
     */
    public String getFeatureName() {
        return featureName;
    }

    /**
     * 设置featureName
     */
    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    /**
     * 获得prodEpName
     */
    public String getProdEpName() {
        return prodEpName;
    }

    /**
     * 设置prodEpName
     */
    public void setProdEpName(String prodEpName) {
        this.prodEpName = prodEpName;
    }

    /**
     * 获得brandName
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * 设置brandName
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     * 获得classesName
     */
    public String getClassesName() {
        return classesName;
    }

    /**
     * 设置classesName
     */
    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    /**
     * 获得breedName
     */
    public String getBreedName() {
        return breedName;
    }

    /**
     * 设置breedName
     */
    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    /**
     * 获得productCode
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * 设置productCode
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
}
