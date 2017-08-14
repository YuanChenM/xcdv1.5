package com.msk.price.bean;

import com.hoperun.core.bean.BaseParam;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by qi_dianyong
 */
public class SP171110Param extends BaseParam{

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /**
     * 物流区编码
     */
    private String lgcsCode;

    /**
     * 物流区名称
     */
    private String lgcsName;

    /**
     * 供应商code
     */
    private String sellerCode;

    private String slId;

    /**
     * 供应商codeDis
     */
    private String sellerCodeDis;

    /**
     * 供应商名称
     */
    private String sellerName;

    /**
     * 价盘周期
     */
    private String pricePeriod;

    /**
     * 价盘周期开始日期
     */
    private String pricePeriodStart;

    /**
     * 价盘周期结束日期
     */
    private String pricePeriodEnd;

    /**
     * 价盘填报时间
     */
    private String lastPricePeriodTime;

    /**
     * 产品编码
     */
    private String pdCode;

    /**
     * 产品名称
     */
    private String pdName;

    /**
     * 产品一级分类code
     */
    private String classesCode;

    /**
     * 产品一级分类名称
     */
    private String classesName;

    /**
     * 产品二级分类Code
     */
    private String machiningCode;

    /**
     * 产品二级分类名称
     */
    private String machining;

    /**
     * 品种编码
     */
    private String breedCode;

    /**
     * 品种名称
     */
    private String breed;

    /**
     * 产品特征Code
     */
    private String featureCode;

    /**
     * 产品特征
     */
    private String feature;

    /**
     * 净重编码
     */
    private String weightCode;

    /**
     * 净重名称
     */
    private String weight;

    /**
     * 产品特征Code
     */
    private String gradeCode;

    /**
     * 产品特征
     */
    private String grade;

    /**
     * 上个价盘周期
     */
    private String lastPricePeriod;

    /**
     * 报价ID
     */
    private long priceId;

    public String getSlId() {
        return slId;
    }

    public void setSlId(String slId) {
        this.slId = slId;
    }

    /**
     * Getter method for property <tt>lgcsCode</tt>.
     *
     * @return property value of lgcsCode
     */
    public String getLgcsCode() {
        return lgcsCode;
    }

    /**
     * Setter method for property <tt>lgcsCode</tt>.
     *
     * @param lgcsCode value to be assigned to property lgcsCode
     */
    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    /**
     * Getter method for property <tt>lgcsName</tt>.
     *
     * @return property value of lgcsName
     */
    public String getLgcsName() {
        return lgcsName;
    }

    /**
     * Setter method for property <tt>lgcsName</tt>.
     *
     * @param lgcsName value to be assigned to property lgcsName
     */
    public void setLgcsName(String lgcsName) {
        this.lgcsName = lgcsName;
    }

    /**
     * Getter method for property <tt>sellerCode</tt>.
     *
     * @return property value of sellerCode
     */
    public String getSellerCode() {
        return sellerCode;
    }

    public String getSellerCodeDis() {
        return sellerCodeDis;
    }

    public void setSellerCodeDis(String sellerCodeDis) {
        this.sellerCodeDis = sellerCodeDis;
    }

    /**
     * Setter method for property <tt>sellerCode</tt>.
     *
     * @param sellerCode value to be assigned to property sellerCode
     */
    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    /**
     * Getter method for property <tt>sellerName</tt>.
     *
     * @return property value of sellerName
     */
    public String getSellerName() {
        return sellerName;
    }

    /**
     * Setter method for property <tt>sellerName</tt>.
     *
     * @param sellerName value to be assigned to property sellerName
     */
    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    /**
     * Getter method for property <tt>pricePeriod</tt>.
     *
     * @return property value of pricePeriod
     */
    public String getPricePeriod() {
        return pricePeriod;
    }

    /**
     * Setter method for property <tt>pricePeriod</tt>.
     *
     * @param pricePeriod value to be assigned to property pricePeriod
     */
    public void setPricePeriod(String pricePeriod) {
        this.pricePeriod = pricePeriod;
    }

    /**
     * Getter method for property <tt>pricePeriodStart</tt>.
     *
     * @return property value of pricePeriodStart
     */
    public String getPricePeriodStart() {
        return pricePeriodStart;
    }

    /**
     * Setter method for property <tt>pricePeriodStart</tt>.
     *
     * @param pricePeriodStart value to be assigned to property pricePeriodStart
     */
    public void setPricePeriodStart(String pricePeriodStart) {
        this.pricePeriodStart = pricePeriodStart;
    }

    /**
     * Getter method for property <tt>pricePeriodEnd</tt>.
     *
     * @return property value of pricePeriodEnd
     */
    public String getPricePeriodEnd() {
        return pricePeriodEnd;
    }

    /**
     * Setter method for property <tt>pricePeriodEnd</tt>.
     *
     * @param pricePeriodEnd value to be assigned to property pricePeriodEnd
     */
    public void setPricePeriodEnd(String pricePeriodEnd) {
        this.pricePeriodEnd = pricePeriodEnd;
    }

    /**
     * Getter method for property <tt>lastPricePeriodTime</tt>.
     *
     * @return property value of lastPricePeriodTime
     */
    public String getLastPricePeriodTime() {
        return lastPricePeriodTime;
    }

    /**
     * Setter method for property <tt>lastPricePeriodTime</tt>.
     *
     * @param lastPricePeriodTime value to be assigned to property lastPricePeriodTime
     */
    public void setLastPricePeriodTime(String lastPricePeriodTime) {
        this.lastPricePeriodTime = lastPricePeriodTime;
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
     * Getter method for property <tt>classesName</tt>.
     *
     * @return property value of classesName
     */
    public String getClassesName() {
        return classesName;
    }

    /**
     * Setter method for property <tt>classesName</tt>.
     *
     * @param classesName value to be assigned to property classesName
     */
    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    /**
     * Getter method for property <tt>machiningCode</tt>.
     *
     * @return property value of machiningCode
     */
    public String getMachiningCode() {
        return machiningCode;
    }

    /**
     * Setter method for property <tt>machiningCode</tt>.
     *
     * @param machiningCode value to be assigned to property machiningCode
     */
    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    /**
     * Getter method for property <tt>machining</tt>.
     *
     * @return property value of machining
     */
    public String getMachining() {
        return machining;
    }

    /**
     * Setter method for property <tt>machining</tt>.
     *
     * @param machining value to be assigned to property machining
     */
    public void setMachining(String machining) {
        this.machining = machining;
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

    /**
     * Getter method for property <tt>breed</tt>.
     *
     * @return property value of breed
     */
    public String getBreed() {
        return breed;
    }

    /**
     * Setter method for property <tt>breed</tt>.
     *
     * @param breed value to be assigned to property breed
     */
    public void setBreed(String breed) {
        this.breed = breed;
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
     * Getter method for property <tt>feature</tt>.
     *
     * @return property value of feature
     */
    public String getFeature() {
        return feature;
    }

    /**
     * Setter method for property <tt>feature</tt>.
     *
     * @param feature value to be assigned to property feature
     */
    public void setFeature(String feature) {
        this.feature = feature;
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
     * Getter method for property <tt>weight</tt>.
     *
     * @return property value of weight
     */
    public String getWeight() {
        return weight;
    }

    /**
     * Setter method for property <tt>weight</tt>.
     *
     * @param weight value to be assigned to property weight
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }

    /**
     * Getter method for property <tt>gradeCode</tt>.
     *
     * @return property value of gradeCode
     */
    public String getGradeCode() {
        return gradeCode;
    }

    /**
     * Setter method for property <tt>gradeCode</tt>.
     *
     * @param gradeCode value to be assigned to property gradeCode
     */
    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    /**
     * Getter method for property <tt>grade</tt>.
     *
     * @return property value of grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * Setter method for property <tt>grade</tt>.
     *
     * @param grade value to be assigned to property grade
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * Getter method for property <tt>lastPricePeriod</tt>.
     *
     * @return property value of lastPricePeriod
     */
    public String getLastPricePeriod() {
        return lastPricePeriod;
    }

    /**
     * Setter method for property <tt>lastPricePeriod</tt>.
     *
     * @param lastPricePeriod value to be assigned to property lastPricePeriod
     */
    public void setLastPricePeriod(String lastPricePeriod) {
        this.lastPricePeriod = lastPricePeriod;
    }

    /**
     * Getter method for property <tt>priceId</tt>.
     *
     * @return property value of priceId
     */
    public long getPriceId() {
        return priceId;
    }

    /**
     * Setter method for property <tt>priceId</tt>.
     *
     * @param priceId value to be assigned to property priceId
     */
    public void setPriceId(long priceId) {
        this.priceId = priceId;
    }

}

