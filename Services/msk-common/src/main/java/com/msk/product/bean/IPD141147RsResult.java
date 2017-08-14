package com.msk.product.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2016/5/4.
 */
@ApiModel(value = "IPD141147RsResult", description = "result")
@JsonIgnoreProperties(value = { "delFlg", "crtId", "crtTime", "updId", "updTime", "ver", "actId" })
public class IPD141147RsResult extends BaseEntity {

    @ApiModelProperty(value = "生产日期")
    private String manufactureDate;

    @ApiModelProperty(value = "储存条件")
    private String storageCondition;

    @ApiModelProperty(value = "保质期")
    private String shelfLife;

    @ApiModelProperty(value = "品牌名")
    private String brandName;

    @ApiModelProperty(value = "生产商名称")
    private String manufactureName;

    @ApiModelProperty(value = "生产商省份")
    private String provinceName;

    @ApiModelProperty(value = "生产商地区（市县）")
    private String cityName;

    @ApiModelProperty(value = "地址")
    private String addressName;

    @ApiModelProperty(value = "电话")
    private String telephone;

    @ApiModelProperty(value = "ISO9001质量管理体系认证证书认证标准")
    private String qltCertificate;

    @ApiModelProperty(value = "产品等级编码")
    private String pdGrade;

    @ApiModelProperty(value = "企业ID")
    private String epId;

    @ApiModelProperty(value = "卖家ID")
    private String sellerID;

    /**
     * Getter method for property <tt>sellerID</tt>.
     *
     * @return property value of sellerID
     */
    public String getSellerID() {
        return sellerID;
    }

    /**
     * Setter method for property <tt>sellerID</tt>.
     *
     * @param sellerID value to be assigned to property sellerID
     */
    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

    /**
     * Getter method for property <tt>manufactureDate</tt>.
     *
     * @return property value of manufactureDate
     */
    public String getManufactureDate() {
        return manufactureDate;
    }

    /**
     * Setter method for property <tt>manufactureDate</tt>.
     *
     * @param manufactureDate value to be assigned to property manufactureDate
     */
    public void setManufactureDate(String manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    /**
     * Getter method for property <tt>storageCondition</tt>.
     *
     * @return property value of storageCondition
     */
    public String getStorageCondition() {
        return storageCondition;
    }

    /**
     * Setter method for property <tt>storageCondition</tt>.
     *
     * @param storageCondition value to be assigned to property storageCondition
     */
    public void setStorageCondition(String storageCondition) {
        this.storageCondition = storageCondition;
    }

    /**
     * Getter method for property <tt>shelfLife</tt>.
     *
     * @return property value of shelfLife
     */
    public String getShelfLife() {
        return shelfLife;
    }

    /**
     * Setter method for property <tt>shelfLife</tt>.
     *
     * @param shelfLife value to be assigned to property shelfLife
     */
    public void setShelfLife(String shelfLife) {
        this.shelfLife = shelfLife;
    }

    /**
     * Getter method for property <tt>brandName</tt>.
     *
     * @return property value of brandName
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * Setter method for property <tt>brandName</tt>.
     *
     * @param brandName value to be assigned to property brandName
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     * Getter method for property <tt>manufactureName</tt>.
     *
     * @return property value of manufactureName
     */
    public String getManufactureName() {
        return manufactureName;
    }

    /**
     * Setter method for property <tt>manufactureName</tt>.
     *
     * @param manufactureName value to be assigned to property manufactureName
     */
    public void setManufactureName(String manufactureName) {
        this.manufactureName = manufactureName;
    }

    /**
     * Getter method for property <tt>provinceName</tt>.
     *
     * @return property value of provinceName
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * Setter method for property <tt>provinceName</tt>.
     *
     * @param provinceName value to be assigned to property provinceName
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    /**
     * Getter method for property <tt>cityName</tt>.
     *
     * @return property value of cityName
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Setter method for property <tt>cityName</tt>.
     *
     * @param cityName value to be assigned to property cityName
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * Getter method for property <tt>addressName</tt>.
     *
     * @return property value of addressName
     */
    public String getAddressName() {
        return addressName;
    }

    /**
     * Setter method for property <tt>addressName</tt>.
     *
     * @param addressName value to be assigned to property addressName
     */
    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    /**
     * Getter method for property <tt>telephone</tt>.
     *
     * @return property value of telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Setter method for property <tt>telephone</tt>.
     *
     * @param telephone value to be assigned to property telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * Getter method for property <tt>qltCertificate</tt>.
     *
     * @return property value of qltCertificate
     */
    public String getQltCertificate() {
        return qltCertificate;
    }

    /**
     * Setter method for property <tt>qltCertificate</tt>.
     *
     * @param qltCertificate value to be assigned to property qltCertificate
     */
    public void setQltCertificate(String qltCertificate) {
        this.qltCertificate = qltCertificate;
    }

    /**
     * Getter method for property <tt>pdGrade</tt>.
     *
     * @return property value of pdGrade
     */
    public String getPdGrade() {
        return pdGrade;
    }

    /**
     * Setter method for property <tt>pdGrade</tt>.
     *
     * @param pdGrade value to be assigned to property pdGrade
     */
    public void setPdGrade(String pdGrade) {
        this.pdGrade = pdGrade;
    }

    /**
     * Getter method for property <tt>epId</tt>.
     *
     * @return property value of epId
     */
    public String getEpId() {
        return epId;
    }

    /**
     * Setter method for property <tt>epId</tt>.
     *
     * @param epId value to be assigned to property epId
     */
    public void setEpId(String epId) {
        this.epId = epId;
    }
}
