package com.msk.seller.bean;


import com.hoperun.core.bean.BaseParam;
import com.msk.core.entity.BaseEntity;

import java.util.List;

/**
 * Created by zhangchi on 2016/5/23.
 */
public class SlPdArtnoBean extends BaseEntity {


    /**卖家货号码*/
    private String slPdArtno;

    /**卖家ID*/
    private String slCode;

    /**卖家编码*/
    private String slCodeDis;

    /**产品一级分类编码*/
    private String classesCode;

    /**产品二级分类编码*/
    private String machiningCode;

    /**产品品种编码*/
    private String breedCode;

    /**产品特征编码*/
    private String featureCode;

    /**净重编码*/
    private String weightCode;

    /**等级编码*/
    private String gradeCode;

    /**销售平台*/
    private String salesPlatform;

    /**销售物流区域*/
    private String saleRegionCode;

    /**保质期*/
    private String shelfLife;

    /**储存方式*/
    private String storageCondition;

    /**产品状态*/
    private String saleStatus;



    /**产地*/
    private String pdPlace;

    /**原产地（国别）*/
    private String pdCountry;

    private List<SlPdArtnoBean>  slPdArtnoBeanList;

    /**根据卖家、销售平台、物流区域、产品查询产品对应的SKU信息*/
    private List<SlPdArtnoBean>  products;

    private String skuCode;

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public List<SlPdArtnoBean> getSlPdArtnoBeanList() {
        return slPdArtnoBeanList;
    }

    public void setSlPdArtnoBeanList(List<SlPdArtnoBean> slPdArtnoBeanList) {
        this.slPdArtnoBeanList = slPdArtnoBeanList;
    }

    public String getSlPdArtno() {
        return slPdArtno;
    }

    public void setSlPdArtno(String slPdArtno) {
        this.slPdArtno = slPdArtno;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getSlCodeDis() {
        return slCodeDis;
    }

    public void setSlCodeDis(String slCodeDis) {
        this.slCodeDis = slCodeDis;
    }

    public String getClassesCode() {
        return classesCode;
    }

    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    public String getMachiningCode() {
        return machiningCode;
    }

    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    public String getBreedCode() {
        return breedCode;
    }

    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    public String getWeightCode() {
        return weightCode;
    }

    public void setWeightCode(String weightCode) {
        this.weightCode = weightCode;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public String getSalesPlatform() {
        return salesPlatform;
    }

    public void setSalesPlatform(String salesPlatform) {
        this.salesPlatform = salesPlatform;
    }

    public String getSaleRegionCode() {
        return saleRegionCode;
    }

    public void setSaleRegionCode(String saleRegionCode) {
        this.saleRegionCode = saleRegionCode;
    }
    public List<SlPdArtnoBean> getProducts() {
        return products;
    }

    public void setProducts(List<SlPdArtnoBean> products) {
        this.products = products;
    }

    public String getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(String shelfLife) {
        this.shelfLife = shelfLife;
    }

    public String getStorageCondition() {
        return storageCondition;
    }

    public void setStorageCondition(String storageCondition) {
        this.storageCondition = storageCondition;
    }

    public String getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(String saleStatus) {
        this.saleStatus = saleStatus;
    }

    public String getPdPlace() {
        return pdPlace;
    }

    public void setPdPlace(String pdPlace) {
        this.pdPlace = pdPlace;
    }

    public String getPdCountry() {
        return pdCountry;
    }

    public void setPdCountry(String pdCountry) {
        this.pdCountry = pdCountry;
    }

}
