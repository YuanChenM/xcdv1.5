package com.msk.seller.bean;

import com.msk.core.entity.SlPdPkg;

import java.math.BigDecimal;

/**
 * SL241106Bean
 *
 * @author jiang_nan
 * @version 1.0
 **/
public class SL241106Bean extends SlPdPkg {
    //单个产品净重
    private String normsSuttle;
    //单个产品规格净重误差范围
    private String normsError;
    //内包装净重/个数
    private String normsNumber;
    //内包装尺寸
    private String normsSize;
    //内包装材质及技术标准
    private String normsTexture;
    //外包装规格
    private String normsOut;
    //外包装净重/毛重
    private String normsKg;
    //外包装尺寸
    private String normsOutSize;
    //外包装材质及技术标准
    private String normsOutTexture;
    //长
    private BigDecimal normsLength;
    //宽
    private BigDecimal normsWidth;
    //高
    private BigDecimal normsHeight;
    //体积
    private BigDecimal normsVolume;
    //生产商名称
    private String epName;
    //品牌名称
    private String brandName;
    //产品类别名称
    private String classesName;
    //产品品种名称
    private String breedName;
    //产品特征名称
    private String featureName;
    //产品二级分类
    private String machiningCode;

    private String machiningName;

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
     * 获得epName
     */
    public String getEpName() {
        return epName;
    }

    /**
     * 设置epName
     */
    public void setEpName(String epName) {
        this.epName = epName;
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
     * 获得normsSuttle
     */
    public String getNormsSuttle() {
        return normsSuttle;
    }

    /**
     * 设置normsSuttle
     */
    public void setNormsSuttle(String normsSuttle) {
        this.normsSuttle = normsSuttle;
    }

    /**
     * 获得normsError
     */
    public String getNormsError() {
        return normsError;
    }

    /**
     * 设置normsError
     */
    public void setNormsError(String normsError) {
        this.normsError = normsError;
    }

    /**
     * 获得normsNumber
     */
    public String getNormsNumber() {
        return normsNumber;
    }

    /**
     * 设置normsNumber
     */
    public void setNormsNumber(String normsNumber) {
        this.normsNumber = normsNumber;
    }

    /**
     * 获得normsSize
     */
    public String getNormsSize() {
        return normsSize;
    }

    /**
     * 设置normsSize
     */
    public void setNormsSize(String normsSize) {
        this.normsSize = normsSize;
    }

    /**
     * 获得normsTexture
     */
    public String getNormsTexture() {
        return normsTexture;
    }

    /**
     * 设置normsTexture
     */
    public void setNormsTexture(String normsTexture) {
        this.normsTexture = normsTexture;
    }

    /**
     * 获得normsOut
     */
    public String getNormsOut() {
        return normsOut;
    }

    /**
     * 设置normsOut
     */
    public void setNormsOut(String normsOut) {
        this.normsOut = normsOut;
    }

    /**
     * 获得normsKg
     */
    public String getNormsKg() {
        return normsKg;
    }

    /**
     * 设置normsKg
     */
    public void setNormsKg(String normsKg) {
        this.normsKg = normsKg;
    }

    /**
     * 获得normsOutSize
     */
    public String getNormsOutSize() {
        return normsOutSize;
    }

    /**
     * 设置normsOutSize
     */
    public void setNormsOutSize(String normsOutSize) {
        this.normsOutSize = normsOutSize;
    }

    /**
     * 获得normsOutTexture
     */
    public String getNormsOutTexture() {
        return normsOutTexture;
    }

    /**
     * 设置normsOutTexture
     */
    public void setNormsOutTexture(String normsOutTexture) {
        this.normsOutTexture = normsOutTexture;
    }

    /**
     * 获得normsLength
     */
    public BigDecimal getNormsLength() {
        return normsLength;
    }

    /**
     * 设置normsLength
     */
    public void setNormsLength(BigDecimal normsLength) {
        this.normsLength = normsLength;
    }

    /**
     * 获得normsWidth
     */
    public BigDecimal getNormsWidth() {
        return normsWidth;
    }

    /**
     * 设置normsWidth
     */
    public void setNormsWidth(BigDecimal normsWidth) {
        this.normsWidth = normsWidth;
    }

    /**
     * 获得normsHeight
     */
    public BigDecimal getNormsHeight() {
        return normsHeight;
    }

    /**
     * 设置normsHeight
     */
    public void setNormsHeight(BigDecimal normsHeight) {
        this.normsHeight = normsHeight;
    }

    /**
     * 获得normsVolume
     */
    public BigDecimal getNormsVolume() {
        return normsVolume;
    }

    /**
     * 设置normsVolume
     */
    public void setNormsVolume(BigDecimal normsVolume) {
        this.normsVolume = normsVolume;
    }
}
