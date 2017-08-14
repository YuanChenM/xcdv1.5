package com.msk.ds.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.msk.core.entity.BaseEntity;



import java.util.Date;

/**
 * Created by FJM on 2016/4/28.
 */

public class SC182212Bean extends BaseEntity{

    private Long    lotId;//'批次ID',

    private String  classesCode;//'产品类别01~99',
    private String  classesName; //'产品类别01~99',

    private String  machiningCode; //'产品二级分类编码',
    private String  machiningName;  // '产品二级分类名称',

    private String  breedCode; //'产品品种01~99',
    private String  breedName;  //产品品种01~99',

    private String  featureCode; //'产品特征01~99',
    private String  featureName;  //'产品特征01~99',

    private String  weightCode; //'净重编码',
    private String  weightName; // '产品净重名称',

    private String  gradeCode;  //'1：A1，2：A2，3：A3',
    private String  gradeName;  // '1：A1，2：A2，3：A3',

    private String  slCode; //'卖家ID',
    private String  slName;  // '卖家名称',

    private String  pdCode; //'产品编码',
    private String  pdName;    // '产品名称',

    private String  pkgCode;  //'2位自动翻番包装规格，01到99，等同NORMS_CODE',
    private String  slCodeDis;  //'7位卖家编号，前3位地区编号，后两位001开始翻番',
    private String  slCodeManufacture;  //'6',
    private Integer salesPlatform;  //'1:神农客（云冻品）2：美侍客（云冻品B2B）',
    private String  lgcsCode;  // '销往地的物流区编号，2位大区编号',
    private String  dateCode;  // '2位年份，2位月份，1位半旬号',

    private String  lgcsName;  // '物流区名称',

    private String  pdStatus; //'产品状态',
    private String  origin;    // '原产地',
    private String  prodcingArea;  // '产地',
    private String  manufacturer;   // '厂家',
    private String  brand;   // '品牌',
    private String  netWeight;  // '净重',
    private String  pkgSpec;   // '包装规格',
    private String  pkgWay;   // '包装方式',
    private String  pdTime;    // '生产时间',
    private String  shelfLife;   // '保质期',
    private String  processingWay;   // '加工方式',
    private String  storageWay;  // '储存方式',
    private Date crtTime;

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
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

    public String getPkgCode() {
        return pkgCode;
    }

    public void setPkgCode(String pkgCode) {
        this.pkgCode = pkgCode;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
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

    public String getSlCodeManufacture() {
        return slCodeManufacture;
    }

    public void setSlCodeManufacture(String slCodeManufacture) {
        this.slCodeManufacture = slCodeManufacture;
    }

    public Integer getSalesPlatform() {
        return salesPlatform;
    }

    public void setSalesPlatform(Integer salesPlatform) {
        this.salesPlatform = salesPlatform;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public String getDateCode() {
        return dateCode;
    }

    public void setDateCode(String dateCode) {
        this.dateCode = dateCode;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    public String getMachiningName() {
        return machiningName;
    }

    public void setMachiningName(String machiningName) {
        this.machiningName = machiningName;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getWeightName() {
        return weightName;
    }

    public void setWeightName(String weightName) {
        this.weightName = weightName;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getSlName() {
        return slName;
    }

    public void setSlName(String slName) {
        this.slName = slName;
    }

    public String getLgcsName() {
        return lgcsName;
    }

    public void setLgcsName(String lgcsName) {
        this.lgcsName = lgcsName;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public String getPdStatus() {
        return pdStatus;
    }

    public void setPdStatus(String pdStatus) {
        this.pdStatus = pdStatus;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getProdcingArea() {
        return prodcingArea;
    }

    public void setProdcingArea(String prodcingArea) {
        this.prodcingArea = prodcingArea;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(String netWeight) {
        this.netWeight = netWeight;
    }

    public String getPkgSpec() {
        return pkgSpec;
    }

    public void setPkgSpec(String pkgSpec) {
        this.pkgSpec = pkgSpec;
    }

    public String getPkgWay() {
        return pkgWay;
    }

    public void setPkgWay(String pkgWay) {
        this.pkgWay = pkgWay;
    }

    public String getPdTime() {
        return pdTime;
    }

    public void setPdTime(String pdTime) {
        this.pdTime = pdTime;
    }

    public String getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(String shelfLife) {
        this.shelfLife = shelfLife;
    }

    public String getProcessingWay() {
        return processingWay;
    }

    public void setProcessingWay(String processingWay) {
        this.processingWay = processingWay;
    }

    public String getStorageWay() {
        return storageWay;
    }

    public void setStorageWay(String storageWay) {
        this.storageWay = storageWay;
    }

    @Override
    @JsonFormat(timezone="GMT+8", pattern="yyyy-MM-dd")
    public Date getCrtTime() {
        return crtTime;
    }

    @Override
    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }
}
