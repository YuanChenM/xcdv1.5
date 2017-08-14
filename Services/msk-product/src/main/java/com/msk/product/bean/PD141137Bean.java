package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;

import java.util.List;

/**
 * Created by Administrator on 2016/2/23.
 */
public class PD141137Bean extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String classesCode;

    private String machiningCode;

    private String breedCode;

    private String featureCode;

    private String featureName;

    private String scientificName;
    /**
     * 俗名
     */
    private String localName;
    /**
     * 销售名
     */
    private String salesName;

    private List<PD141137StautsBean> applyAndArgList;

    private List<PD141137StautsBean> forbidList;

    private List<PD141137StautsBean> marketingList;

    private List<PD141137StautsBean> topLineList;

    private List<PD141137StautsBean> breakGoods;

    private List<PD141137StautsBean> downLineList;

    private List<PD141137StautsBean> blacklist;

    private List<PD141137FeatureListBean> treeList;

    private List<PD141137SlNumberBean> allNumber;

    private String brandClass;//品牌类型

    private String brandName;

    private String slCode;

    private String brandEpId;

    private String brandId;

    private String prodEpId;

    private String epName;

    private String cityName;

    private String remark;

    private String classestreeCode;


    /**
     * Getter method for property <tt>breakGoods</tt>.
     *
     * @return property value of breakGoods
     */
    public List<PD141137StautsBean> getBreakGoods() {
        return breakGoods;
    }

    /**
     * Setter method for property <tt>breakGoods</tt>.
     *
     * @param breakGoods value to be assigned to property breakGoods
     */
    public void setBreakGoods(List<PD141137StautsBean> breakGoods) {
        this.breakGoods = breakGoods;
    }

    /**
     * Getter method for property <tt>allNumber</tt>.
     *
     * @return property value of allNumber
     */
    public List<PD141137SlNumberBean> getAllNumber() {
        return allNumber;
    }

    /**
     * Setter method for property <tt>allNumber</tt>.
     *
     * @param allNumber value to be assigned to property allNumber
     */
    public void setAllNumber(List<PD141137SlNumberBean> allNumber) {
        this.allNumber = allNumber;
    }

    /**
     * Getter method for property <tt>classestreeCode</tt>.
     *
     * @return property value of classestreeCode
     */
    public String getClassestreeCode() {
        return classestreeCode;
    }

    /**
     * Setter method for property <tt>classestreeCode</tt>.
     *
     * @param classestreeCode value to be assigned to property classestreeCode
     */
    public void setClassestreeCode(String classestreeCode) {
        this.classestreeCode = classestreeCode;
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
     * Getter method for property <tt>treeList</tt>.
     *
     * @return property value of treeList
     */
    public List<PD141137FeatureListBean> getTreeList() {
        return treeList;
    }

    /**
     * Setter method for property <tt>treeList</tt>.
     *
     * @param treeList value to be assigned to property treeList
     */
    public void setTreeList(List<PD141137FeatureListBean> treeList) {
        this.treeList = treeList;
    }

    /**
     * Getter method for property <tt>remark</tt>.
     *
     * @return property value of remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Setter method for property <tt>remark</tt>.
     *
     * @param remark value to be assigned to property remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
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
     * Getter method for property <tt>epName</tt>.
     *
     * @return property value of epName
     */
    public String getEpName() {
        return epName;
    }

    /**
     * Setter method for property <tt>epName</tt>.
     *
     * @param epName value to be assigned to property epName
     */
    public void setEpName(String epName) {
        this.epName = epName;
    }

    /**
     * Getter method for property <tt>prodEpId</tt>.
     *
     * @return property value of prodEpId
     */
    public String getProdEpId() {
        return prodEpId;
    }

    /**
     * Setter method for property <tt>prodEpId</tt>.
     *
     * @param prodEpId value to be assigned to property prodEpId
     */
    public void setProdEpId(String prodEpId) {
        this.prodEpId = prodEpId;
    }

    /**
     * Getter method for property <tt>brandEpId</tt>.
     *
     * @return property value of brandEpId
     */
    public String getBrandEpId() {
        return brandEpId;
    }

    /**
     * Setter method for property <tt>brandEpId</tt>.
     *
     * @param brandEpId value to be assigned to property brandEpId
     */
    public void setBrandEpId(String brandEpId) {
        this.brandEpId = brandEpId;
    }

    /**
     * Getter method for property <tt>brandId</tt>.
     *
     * @return property value of brandId
     */
    public String getBrandId() {
        return brandId;
    }

    /**
     * Setter method for property <tt>brandId</tt>.
     *
     * @param brandId value to be assigned to property brandId
     */
    public void setBrandId(String brandId) {
        this.brandId = brandId;
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
     * Getter method for property <tt>brandClass</tt>.
     *
     * @return property value of brandClass
     */
    public String getBrandClass() {
        return brandClass;
    }

    /**
     * Setter method for property <tt>brandClass</tt>.
     *
     * @param brandClass value to be assigned to property brandClass
     */
    public void setBrandClass(String brandClass) {
        this.brandClass = brandClass;
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
     * Getter method for property <tt>scientificName</tt>.
     *
     * @return property value of scientificName
     */
    public String getScientificName() {
        return scientificName;
    }

    /**
     * Setter method for property <tt>scientificName</tt>.
     *
     * @param scientificName value to be assigned to property scientificName
     */
    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    /**
     * Getter method for property <tt>localName</tt>.
     *
     * @return property value of localName
     */
    public String getLocalName() {
        return localName;
    }

    /**
     * Setter method for property <tt>localName</tt>.
     *
     * @param localName value to be assigned to property localName
     */
    public void setLocalName(String localName) {
        this.localName = localName;
    }

    /**
     * Getter method for property <tt>salesName</tt>.
     *
     * @return property value of salesName
     */
    public String getSalesName() {
        return salesName;
    }

    /**
     * Setter method for property <tt>salesName</tt>.
     *
     * @param salesName value to be assigned to property salesName
     */
    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    /**
     * Getter method for property <tt>applyAndArgList</tt>.
     *
     * @return property value of applyAndArgList
     */
    public List<PD141137StautsBean> getApplyAndArgList() {
        return applyAndArgList;
    }

    /**
     * Setter method for property <tt>applyAndArgList</tt>.
     *
     * @param applyAndArgList value to be assigned to property applyAndArgList
     */
    public void setApplyAndArgList(List<PD141137StautsBean> applyAndArgList) {
        this.applyAndArgList = applyAndArgList;
    }

    /**
     * Getter method for property <tt>forbidList</tt>.
     *
     * @return property value of forbidList
     */
    public List<PD141137StautsBean> getForbidList() {
        return forbidList;
    }

    /**
     * Setter method for property <tt>forbidList</tt>.
     *
     * @param forbidList value to be assigned to property forbidList
     */
    public void setForbidList(List<PD141137StautsBean> forbidList) {
        this.forbidList = forbidList;
    }

    /**
     * Getter method for property <tt>marketingList</tt>.
     *
     * @return property value of marketingList
     */
    public List<PD141137StautsBean> getMarketingList() {
        return marketingList;
    }

    /**
     * Setter method for property <tt>marketingList</tt>.
     *
     * @param marketingList value to be assigned to property marketingList
     */
    public void setMarketingList(List<PD141137StautsBean> marketingList) {
        this.marketingList = marketingList;
    }

    /**
     * Getter method for property <tt>topLineList</tt>.
     *
     * @return property value of topLineList
     */
    public List<PD141137StautsBean> getTopLineList() {
        return topLineList;
    }

    /**
     * Setter method for property <tt>topLineList</tt>.
     *
     * @param topLineList value to be assigned to property topLineList
     */
    public void setTopLineList(List<PD141137StautsBean> topLineList) {
        this.topLineList = topLineList;
    }

    /**
     * Getter method for property <tt>downLineList</tt>.
     *
     * @return property value of downLineList
     */
    public List<PD141137StautsBean> getDownLineList() {
        return downLineList;
    }

    /**
     * Setter method for property <tt>downLineList</tt>.
     *
     * @param downLineList value to be assigned to property downLineList
     */
    public void setDownLineList(List<PD141137StautsBean> downLineList) {
        this.downLineList = downLineList;
    }

    /**
     * Getter method for property <tt>blacklist</tt>.
     *
     * @return property value of blacklist
     */
    public List<PD141137StautsBean> getBlacklist() {
        return blacklist;
    }

    /**
     * Setter method for property <tt>blacklist</tt>.
     *
     * @param blacklist value to be assigned to property blacklist
     */
    public void setBlacklist(List<PD141137StautsBean> blacklist) {
        this.blacklist = blacklist;
    }
}
