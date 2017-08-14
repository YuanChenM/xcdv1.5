package com.msk.seller.bean;

import com.msk.common.base.BaseBean;

/**
 * BaseSellerBean
 *
 * @author jiang_nan
 * @version 1.0
 **/
public class BaseSellerProductBean extends BaseBean{
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    
    /**卖家编码*/
    private String slCode;
    /**卖家产品ID*/
    private String slPdId;
    /**生产商*/
    private String manufacturer;
    /**品牌*/
    private String brand;
    /**产品类型*/
    private String classesCode;
    /**产品类型名称*/
    private String classesName;
    /**产品品种*/
    private String breedCode;
    /**产品品种名称*/
    private String breedName;
    /**特性*/
    private String feature;
    /**产品编码*/
    private String pdCode;
 
    /**
     * *获得manufacturer
     **/
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * *设置manufacturer
     **/
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * *获得brand
     **/
    public String getBrand() {
        return brand;
    }

    /**
     * *设置brand
     **/
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * *获得classesCode
     **/
    public String getClassesCode() {
        return classesCode;
    }

    /**
     * *设置classesCode
     **/
    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    /**
     * *获得classesName
     **/
    public String getClassesName() {
        return classesName;
    }

    /**
     * *设置classesName
     **/
    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    /**
     * *获得breedCode
     **/
    public String getBreedCode() {
        return breedCode;
    }

    /**
     * *设置breedCode
     **/
    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    /**
     * *获得breedName
     **/
    public String getBreedName() {
        return breedName;
    }

    /**
     * *设置breedName
     **/
    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    /**
     * *获得feature
     **/
    public String getFeature() {
        return feature;
    }

    /**
     * *设置feature
     **/
    public void setFeature(String feature) {
        this.feature = feature;
    }

    /**
     * *获得pdCode
     **/
    public String getPdCode() {
        return pdCode;
    }

    /**
     * *设置pdCode
     **/
    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    /**
     * Get the slCode.
     *
     * @return slCode
     *
     * @author Marshall
     */
    public String getSlCode() {
        return this.slCode;
    }

    /**
     * Set the slCode.
     *
     * @param slCode slCode
     *
     * @author Marshall
     */
    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    /**
     * Get the slPdId.
     *
     * @return slPdId
     *
     * @author Marshall
     */
    public String getSlPdId() {
        return this.slPdId;
    }

    /**
     * Set the slPdId.
     *
     * @param slPdId slPdId
     *
     * @author Marshall
     */
    public void setSlPdId(String slPdId) {
        this.slPdId = slPdId;
    }
}
