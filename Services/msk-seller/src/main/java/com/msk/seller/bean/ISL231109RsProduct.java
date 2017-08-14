package com.msk.seller.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msk.core.entity.SlProduct;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by gyh on 2016/2/29.
 */
@JsonIgnoreProperties(value = {"delFlg", "crtId", "crtTime", "updId", "updTime", "actId", "actTime","concatInfo","slShowName"})
public class ISL231109RsProduct extends SlProduct {
    /** 产品类别 */
    private String pdClassesName;
    /** 产品二级分类编码 */
    private String machiningName;
    /** 产品品种 */
    private String pdBreedName;
    /** 产品特征 */
    private String pdFeatureName;
    /** 净重编码 */
    private String weightName;
      /**生产商名称*/
    private String prodEpName;
    //品牌
    private String brandEpName;
    //品牌名称
    private String brandName;
    /** 净重编码 */
    private BigDecimal weightVal;
    private String pdStatus;//产品状态
    private List<SL241117Bean> slPdTncStdList;//卖家产品加工质量标准指标值信息List
    private List<SL241118Bean> slPdMctStdList;//卖家产品加工技术标准指标值
    private List<ISL231109RsSlPdPkg> slPdPkgList;//卖家产品包装标准信息
    private List<SL241122Bean> slPdOrgStdList;//卖家产品原种种源标准
    private List<SL241123Bean> slPdFedStdList;//卖家产品饲养标准
    private List<SL241124Bean> slPdGnqStdList;//卖家产品通用质量标准
    private List<SL241125Bean> slPdTspStdList;//卖家产品储存运输标准
    private List<SL241126Bean> slPdSftStdList;//卖家产品安全标准

    /**
     * Getter method for property <tt>prodEpName</tt>.
     *
     * @return property value of prodEpName
     */
    public String getProdEpName() {
        return prodEpName;
    }

    /**
     * Setter method for property <tt>prodEpName</tt>.
     *
     * @param prodEpName value to be assigned to property prodEpName
     */
    public void setProdEpName(String prodEpName) {
        this.prodEpName = prodEpName;
    }

    /**
     * Getter method for property <tt>brandEpName</tt>.
     *
     * @return property value of brandEpName
     */
    public String getBrandEpName() {
        return brandEpName;
    }

    /**
     * Setter method for property <tt>brandEpName</tt>.
     *
     * @param brandEpName value to be assigned to property brandEpName
     */
    public void setBrandEpName(String brandEpName) {
        this.brandEpName = brandEpName;
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
     * Getter method for property <tt>weightVal</tt>.
     *
     * @return property value of weightVal
     */
    public BigDecimal getWeightVal() {
        return weightVal;
    }

    /**
     * Setter method for property <tt>weightVal</tt>.
     *
     * @param weightVal value to be assigned to property weightVal
     */
    public void setWeightVal(BigDecimal weightVal) {
        this.weightVal = weightVal;
    }

    /**
     * Getter method for property <tt>pdClassesName</tt>.
     *
     * @return property value of pdClassesName
     */
    public String getPdClassesName() {
        return pdClassesName;
    }

    /**
     * Setter method for property <tt>pdClassesName</tt>.
     *
     * @param pdClassesName value to be assigned to property pdClassesName
     */
    public void setPdClassesName(String pdClassesName) {
        this.pdClassesName = pdClassesName;
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
     * Getter method for property <tt>pdBreedName</tt>.
     *
     * @return property value of pdBreedName
     */
    public String getPdBreedName() {
        return pdBreedName;
    }

    /**
     * Setter method for property <tt>pdBreedName</tt>.
     *
     * @param pdBreedName value to be assigned to property pdBreedName
     */
    public void setPdBreedName(String pdBreedName) {
        this.pdBreedName = pdBreedName;
    }

    /**
     * Getter method for property <tt>pdFeatureName</tt>.
     *
     * @return property value of pdFeatureName
     */
    public String getPdFeatureName() {
        return pdFeatureName;
    }

    /**
     * Setter method for property <tt>pdFeatureName</tt>.
     *
     * @param pdFeatureName value to be assigned to property pdFeatureName
     */
    public void setPdFeatureName(String pdFeatureName) {
        this.pdFeatureName = pdFeatureName;
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
     * Getter method for property <tt>slPdTncStdList</tt>.
     *
     * @return property value of slPdTncStdList
     */
    public List<SL241117Bean> getSlPdTncStdList() {
        return slPdTncStdList;
    }

    /**
     * Setter method for property <tt>slPdTncStdList</tt>.
     *
     * @param slPdTncStdList value to be assigned to property slPdTncStdList
     */
    public void setSlPdTncStdList(List<SL241117Bean> slPdTncStdList) {
        this.slPdTncStdList = slPdTncStdList;
    }

    /**
     * Getter method for property <tt>slPdMctStdList</tt>.
     *
     * @return property value of slPdMctStdList
     */
    public List<SL241118Bean> getSlPdMctStdList() {
        return slPdMctStdList;
    }

    /**
     * Setter method for property <tt>slPdMctStdList</tt>.
     *
     * @param slPdMctStdList value to be assigned to property slPdMctStdList
     */
    public void setSlPdMctStdList(List<SL241118Bean> slPdMctStdList) {
        this.slPdMctStdList = slPdMctStdList;
    }

    /**
     * Getter method for property <tt>slPdPkgList</tt>.
     *
     * @return property value of slPdPkgList
     */
    public List<ISL231109RsSlPdPkg> getSlPdPkgList() {
        return slPdPkgList;
    }

    /**
     * Setter method for property <tt>slPdPkgList</tt>.
     *
     * @param slPdPkgList value to be assigned to property slPdPkgList
     */
    public void setSlPdPkgList(List<ISL231109RsSlPdPkg> slPdPkgList) {
        this.slPdPkgList = slPdPkgList;
    }

    /**
     * Getter method for property <tt>slPdOrgStdList</tt>.
     *
     * @return property value of slPdOrgStdList
     */
    public List<SL241122Bean> getSlPdOrgStdList() {
        return slPdOrgStdList;
    }

    /**
     * Setter method for property <tt>slPdOrgStdList</tt>.
     *
     * @param slPdOrgStdList value to be assigned to property slPdOrgStdList
     */
    public void setSlPdOrgStdList(List<SL241122Bean> slPdOrgStdList) {
        this.slPdOrgStdList = slPdOrgStdList;
    }

    /**
     * Getter method for property <tt>slPdFedStdList</tt>.
     *
     * @return property value of slPdFedStdList
     */
    public List<SL241123Bean> getSlPdFedStdList() {
        return slPdFedStdList;
    }

    /**
     * Setter method for property <tt>slPdFedStdList</tt>.
     *
     * @param slPdFedStdList value to be assigned to property slPdFedStdList
     */
    public void setSlPdFedStdList(List<SL241123Bean> slPdFedStdList) {
        this.slPdFedStdList = slPdFedStdList;
    }

    /**
     * Getter method for property <tt>slPdGnqStdList</tt>.
     *
     * @return property value of slPdGnqStdList
     */
    public List<SL241124Bean> getSlPdGnqStdList() {
        return slPdGnqStdList;
    }

    /**
     * Setter method for property <tt>slPdGnqStdList</tt>.
     *
     * @param slPdGnqStdList value to be assigned to property slPdGnqStdList
     */
    public void setSlPdGnqStdList(List<SL241124Bean> slPdGnqStdList) {
        this.slPdGnqStdList = slPdGnqStdList;
    }

    /**
     * Getter method for property <tt>slPdTspStdList</tt>.
     *
     * @return property value of slPdTspStdList
     */
    public List<SL241125Bean> getSlPdTspStdList() {
        return slPdTspStdList;
    }

    /**
     * Setter method for property <tt>slPdTspStdList</tt>.
     *
     * @param slPdTspStdList value to be assigned to property slPdTspStdList
     */
    public void setSlPdTspStdList(List<SL241125Bean> slPdTspStdList) {
        this.slPdTspStdList = slPdTspStdList;
    }

    /**
     * Getter method for property <tt>slPdSftStdList</tt>.
     *
     * @return property value of slPdSftStdList
     */
    public List<SL241126Bean> getSlPdSftStdList() {
        return slPdSftStdList;
    }

    /**
     * Setter method for property <tt>slPdSftStdList</tt>.
     *
     * @param slPdSftStdList value to be assigned to property slPdSftStdList
     */
    public void setSlPdSftStdList(List<SL241126Bean> slPdSftStdList) {
        this.slPdSftStdList = slPdSftStdList;
    }

    /**
     * Getter method for property <tt>pdStatus</tt>.
     *
     * @return property value of pdStatus
     */
    public String getPdStatus() {
        return pdStatus;
    }

    /**
     * Setter method for property <tt>pdStatus</tt>.
     *
     * @param pdStatus value to be assigned to property pdStatus
     */
    public void setPdStatus(String pdStatus) {
        this.pdStatus = pdStatus;
    }
}
