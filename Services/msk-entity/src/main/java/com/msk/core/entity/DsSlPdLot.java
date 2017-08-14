/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表ds_sl_pd_lot对应的DsSlPdLot。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class DsSlPdLot extends BaseEntity{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /** 批次ID */
    private java.lang.Long lotId;
    /** 产品类别01~99 */
    private java.lang.String classesCode;
    /** 产品二级分类编码 */
    private java.lang.String machiningCode;
    /** 产品品种01~99 */
    private java.lang.String breedCode;
    /** 产品特征01~99 */
    private java.lang.String featureCode;
    /** 净重编码 */
    private java.lang.String weightCode;
    /** 2位自动翻番包装规格，01到99，等同NORMS_CODE */
    private java.lang.String pkgCode;
    /** 1：A1，2：A2，3：A3 */
    private java.lang.String gradeCode;
    /** 10位卖家编号，前5位地区编号，后两位00001开始翻番 */
    private java.lang.String slCode;
    /** 卖家显示编码 */
    private java.lang.String slCodeDis;
    /** SL_CODE_MANUFACTURE */
    private java.lang.String slCodeManufacture;
    /** 销售平台，1：神农客（云冻品）；2：美侍客（云冻品B2B） */
    private java.lang.Integer salesPlatform;
    /** 销往地的物流区编号，2位大区编号 */
    private java.lang.String lgcsCode;
    /** 2位年份，2位月份，1位半旬号 */
    private java.lang.String dateCode;
    /** 产品编码 */
    private java.lang.String pdCode;
    /** 产品类别01~99 */
    private java.lang.String classesName;
    /** 产品二级分类名称 */
    private java.lang.String machiningName;
    /** 产品品种01~99 */
    private java.lang.String breedName;
    /** 产品特征01~99 */
    private java.lang.String featureName;
    /** 产品净重名称 */
    private java.lang.String weightName;
    /** 1：A1，2：A2，3：A3 */
    private java.lang.String gradeName;
    /** 卖家名称 */
    private java.lang.String slName;
    /** 物流区名称 */
    private java.lang.String lgcsName;
    /** 产品名称 */
    private java.lang.String pdName;
    /** 产品状态 */
    private java.lang.String pdStatus;
    /** 原产地 */
    private java.lang.String origin;
    /** 产地 */
    private java.lang.String prodcingarea;
    /** 厂家 */
    private java.lang.String manufacturer;
    /** 品牌 */
    private java.lang.String brand;
    /** 净重 */
    private java.lang.String netweight;
    /** 包装规格 */
    private java.lang.String pkgSpec;
    /** 包装方式 */
    private java.lang.String pkgWay;
    /** 生产时间 */
    private java.lang.String pdTime;
    /** 保质期 */
    private java.lang.String shelfLife;
    /** 加工方式 */
    private java.lang.String processingWay;
    /** 储存方式 */
    private java.lang.String storageWay;
    /**
     * <p>默认构造函数。</p>
     */
    public DsSlPdLot() {

    }

    /**
     * <p>批次ID。</p>
     *
     * @return the 批次ID
     */
    public java.lang.Long getLotId() {
        return lotId;
    }

    /**
     * <p>批次ID。</p>
     *
     * @param lotId 批次ID。
     */
    public void setLotId(java.lang.Long lotId) {
        this.lotId = lotId;
    }

    /**
     * <p>产品类别01~99。</p>
     *
     * @return the 产品类别01~99
     */
    public java.lang.String getClassesCode() {
        return classesCode;
    }

    /**
     * <p>产品类别01~99。</p>
     *
     * @param classesCode 产品类别01~99。
     */
    public void setClassesCode(java.lang.String classesCode) {
        this.classesCode = classesCode;
    }

    /**
     * <p>产品二级分类编码。</p>
     *
     * @return the 产品二级分类编码
     */
    public java.lang.String getMachiningCode() {
        return machiningCode;
    }

    /**
     * <p>产品二级分类编码。</p>
     *
     * @param machiningCode 产品二级分类编码。
     */
    public void setMachiningCode(java.lang.String machiningCode) {
        this.machiningCode = machiningCode;
    }

    /**
     * <p>产品品种01~99。</p>
     *
     * @return the 产品品种01~99
     */
    public java.lang.String getBreedCode() {
        return breedCode;
    }

    /**
     * <p>产品品种01~99。</p>
     *
     * @param breedCode 产品品种01~99。
     */
    public void setBreedCode(java.lang.String breedCode) {
        this.breedCode = breedCode;
    }

    /**
     * <p>产品特征01~99。</p>
     *
     * @return the 产品特征01~99
     */
    public java.lang.String getFeatureCode() {
        return featureCode;
    }

    /**
     * <p>产品特征01~99。</p>
     *
     * @param featureCode 产品特征01~99。
     */
    public void setFeatureCode(java.lang.String featureCode) {
        this.featureCode = featureCode;
    }

    /**
     * <p>净重编码。</p>
     *
     * @return the 净重编码
     */
    public java.lang.String getWeightCode() {
        return weightCode;
    }

    /**
     * <p>净重编码。</p>
     *
     * @param weightCode 净重编码。
     */
    public void setWeightCode(java.lang.String weightCode) {
        this.weightCode = weightCode;
    }

    /**
     * <p>2位自动翻番包装规格，01到99，等同NORMS_CODE。</p>
     *
     * @return the 2位自动翻番包装规格，01到99，等同NORMS_CODE
     */
    public java.lang.String getPkgCode() {
        return pkgCode;
    }

    /**
     * <p>2位自动翻番包装规格，01到99，等同NORMS_CODE。</p>
     *
     * @param pkgCode 2位自动翻番包装规格，01到99，等同NORMS_CODE。
     */
    public void setPkgCode(java.lang.String pkgCode) {
        this.pkgCode = pkgCode;
    }

    /**
     * <p>1：A1，2：A2，3：A3。</p>
     *
     * @return the 1：A1，2：A2，3：A3
     */
    public java.lang.String getGradeCode() {
        return gradeCode;
    }

    /**
     * <p>1：A1，2：A2，3：A3。</p>
     *
     * @param gradeCode 1：A1，2：A2，3：A3。
     */
    public void setGradeCode(java.lang.String gradeCode) {
        this.gradeCode = gradeCode;
    }

    /**
     * <p>10位卖家编号，前5位地区编号，后两位00001开始翻番。</p>
     *
     * @return the 10位卖家编号，前5位地区编号，后两位00001开始翻番
     */
    public java.lang.String getSlCode() {
        return slCode;
    }

    /**
     * <p>10位卖家编号，前5位地区编号，后两位00001开始翻番。</p>
     *
     * @param slCode 10位卖家编号，前5位地区编号，后两位00001开始翻番。
     */
    public void setSlCode(java.lang.String slCode) {
        this.slCode = slCode;
    }

    /**
     * <p>卖家显示编码。</p>
     *
     * @return the 卖家显示编码
     */
    public java.lang.String getSlCodeDis() {
        return slCodeDis;
    }

    /**
     * <p>卖家显示编码。</p>
     *
     * @param slCodeDis 卖家显示编码。
     */
    public void setSlCodeDis(java.lang.String slCodeDis) {
        this.slCodeDis = slCodeDis;
    }

    /**
     * <p>SL_CODE_MANUFACTURE。</p>
     *
     * @return the SL_CODE_MANUFACTURE
     */
    public java.lang.String getSlCodeManufacture() {
        return slCodeManufacture;
    }

    /**
     * <p>SL_CODE_MANUFACTURE。</p>
     *
     * @param slCodeManufacture SL_CODE_MANUFACTURE。
     */
    public void setSlCodeManufacture(java.lang.String slCodeManufacture) {
        this.slCodeManufacture = slCodeManufacture;
    }

    /**
     * <p>销售平台，1：神农客（云冻品）；2：美侍客（云冻品B2B）。</p>
     *
     * @return the 销售平台，1：神农客（云冻品）；2：美侍客（云冻品B2B）
     */
    public java.lang.Integer getSalesPlatform() {
        return salesPlatform;
    }

    /**
     * <p>销售平台，1：神农客（云冻品）；2：美侍客（云冻品B2B）。</p>
     *
     * @param salesPlatform 销售平台，1：神农客（云冻品）；2：美侍客（云冻品B2B）。
     */
    public void setSalesPlatform(java.lang.Integer salesPlatform) {
        this.salesPlatform = salesPlatform;
    }

    /**
     * <p>销往地的物流区编号，2位大区编号。</p>
     *
     * @return the 销往地的物流区编号，2位大区编号
     */
    public java.lang.String getLgcsCode() {
        return lgcsCode;
    }

    /**
     * <p>销往地的物流区编号，2位大区编号。</p>
     *
     * @param lgcsCode 销往地的物流区编号，2位大区编号。
     */
    public void setLgcsCode(java.lang.String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    /**
     * <p>2位年份，2位月份，1位半旬号。</p>
     *
     * @return the 2位年份，2位月份，1位半旬号
     */
    public java.lang.String getDateCode() {
        return dateCode;
    }

    /**
     * <p>2位年份，2位月份，1位半旬号。</p>
     *
     * @param dateCode 2位年份，2位月份，1位半旬号。
     */
    public void setDateCode(java.lang.String dateCode) {
        this.dateCode = dateCode;
    }

    /**
     * <p>产品编码。</p>
     *
     * @return the 产品编码
     */
    public java.lang.String getPdCode() {
        return pdCode;
    }

    /**
     * <p>产品编码。</p>
     *
     * @param pdCode 产品编码。
     */
    public void setPdCode(java.lang.String pdCode) {
        this.pdCode = pdCode;
    }

    /**
     * <p>产品类别01~99。</p>
     *
     * @return the 产品类别01~99
     */
    public java.lang.String getClassesName() {
        return classesName;
    }

    /**
     * <p>产品类别01~99。</p>
     *
     * @param classesName 产品类别01~99。
     */
    public void setClassesName(java.lang.String classesName) {
        this.classesName = classesName;
    }

    /**
     * <p>产品二级分类名称。</p>
     *
     * @return the 产品二级分类名称
     */
    public java.lang.String getMachiningName() {
        return machiningName;
    }

    /**
     * <p>产品二级分类名称。</p>
     *
     * @param machiningName 产品二级分类名称。
     */
    public void setMachiningName(java.lang.String machiningName) {
        this.machiningName = machiningName;
    }

    /**
     * <p>产品品种01~99。</p>
     *
     * @return the 产品品种01~99
     */
    public java.lang.String getBreedName() {
        return breedName;
    }

    /**
     * <p>产品品种01~99。</p>
     *
     * @param breedName 产品品种01~99。
     */
    public void setBreedName(java.lang.String breedName) {
        this.breedName = breedName;
    }

    /**
     * <p>产品特征01~99。</p>
     *
     * @return the 产品特征01~99
     */
    public java.lang.String getFeatureName() {
        return featureName;
    }

    /**
     * <p>产品特征01~99。</p>
     *
     * @param featureName 产品特征01~99。
     */
    public void setFeatureName(java.lang.String featureName) {
        this.featureName = featureName;
    }

    /**
     * <p>产品净重名称。</p>
     *
     * @return the 产品净重名称
     */
    public java.lang.String getWeightName() {
        return weightName;
    }

    /**
     * <p>产品净重名称。</p>
     *
     * @param weightName 产品净重名称。
     */
    public void setWeightName(java.lang.String weightName) {
        this.weightName = weightName;
    }

    /**
     * <p>1：A1，2：A2，3：A3。</p>
     *
     * @return the 1：A1，2：A2，3：A3
     */
    public java.lang.String getGradeName() {
        return gradeName;
    }

    /**
     * <p>1：A1，2：A2，3：A3。</p>
     *
     * @param gradeName 1：A1，2：A2，3：A3。
     */
    public void setGradeName(java.lang.String gradeName) {
        this.gradeName = gradeName;
    }

    /**
     * <p>卖家名称。</p>
     *
     * @return the 卖家名称
     */
    public java.lang.String getSlName() {
        return slName;
    }

    /**
     * <p>卖家名称。</p>
     *
     * @param slName 卖家名称。
     */
    public void setSlName(java.lang.String slName) {
        this.slName = slName;
    }

    /**
     * <p>物流区名称。</p>
     *
     * @return the 物流区名称
     */
    public java.lang.String getLgcsName() {
        return lgcsName;
    }

    /**
     * <p>物流区名称。</p>
     *
     * @param lgcsName 物流区名称。
     */
    public void setLgcsName(java.lang.String lgcsName) {
        this.lgcsName = lgcsName;
    }

    /**
     * <p>产品名称。</p>
     *
     * @return the 产品名称
     */
    public java.lang.String getPdName() {
        return pdName;
    }

    /**
     * <p>产品名称。</p>
     *
     * @param pdName 产品名称。
     */
    public void setPdName(java.lang.String pdName) {
        this.pdName = pdName;
    }

    /**
     * <p>产品状态。</p>
     *
     * @return the 产品状态
     */
    public java.lang.String getPdStatus() {
        return pdStatus;
    }

    /**
     * <p>产品状态。</p>
     *
     * @param pdStatus 产品状态。
     */
    public void setPdStatus(java.lang.String pdStatus) {
        this.pdStatus = pdStatus;
    }

    /**
     * <p>原产地。</p>
     *
     * @return the 原产地
     */
    public java.lang.String getOrigin() {
        return origin;
    }

    /**
     * <p>原产地。</p>
     *
     * @param origin 原产地。
     */
    public void setOrigin(java.lang.String origin) {
        this.origin = origin;
    }

    /**
     * <p>产地。</p>
     *
     * @return the 产地
     */
    public java.lang.String getProdcingarea() {
        return prodcingarea;
    }

    /**
     * <p>产地。</p>
     *
     * @param prodcingarea 产地。
     */
    public void setProdcingarea(java.lang.String prodcingarea) {
        this.prodcingarea = prodcingarea;
    }

    /**
     * <p>厂家。</p>
     *
     * @return the 厂家
     */
    public java.lang.String getManufacturer() {
        return manufacturer;
    }

    /**
     * <p>厂家。</p>
     *
     * @param manufacturer 厂家。
     */
    public void setManufacturer(java.lang.String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * <p>品牌。</p>
     *
     * @return the 品牌
     */
    public java.lang.String getBrand() {
        return brand;
    }

    /**
     * <p>品牌。</p>
     *
     * @param brand 品牌。
     */
    public void setBrand(java.lang.String brand) {
        this.brand = brand;
    }

    /**
     * <p>净重。</p>
     *
     * @return the 净重
     */
    public java.lang.String getNetweight() {
        return netweight;
    }

    /**
     * <p>净重。</p>
     *
     * @param netweight 净重。
     */
    public void setNetweight(java.lang.String netweight) {
        this.netweight = netweight;
    }

    /**
     * <p>包装规格。</p>
     *
     * @return the 包装规格
     */
    public java.lang.String getPkgSpec() {
        return pkgSpec;
    }

    /**
     * <p>包装规格。</p>
     *
     * @param pkgSpec 包装规格。
     */
    public void setPkgSpec(java.lang.String pkgSpec) {
        this.pkgSpec = pkgSpec;
    }

    /**
     * <p>包装方式。</p>
     *
     * @return the 包装方式
     */
    public java.lang.String getPkgWay() {
        return pkgWay;
    }

    /**
     * <p>包装方式。</p>
     *
     * @param pkgWay 包装方式。
     */
    public void setPkgWay(java.lang.String pkgWay) {
        this.pkgWay = pkgWay;
    }

    /**
     * <p>生产时间。</p>
     *
     * @return the 生产时间
     */
    public java.lang.String getPdTime() {
        return pdTime;
    }

    /**
     * <p>生产时间。</p>
     *
     * @param pdTime 生产时间。
     */
    public void setPdTime(java.lang.String pdTime) {
        this.pdTime = pdTime;
    }

    /**
     * <p>保质期。</p>
     *
     * @return the 保质期
     */
    public java.lang.String getShelfLife() {
        return shelfLife;
    }

    /**
     * <p>保质期。</p>
     *
     * @param shelfLife 保质期。
     */
    public void setShelfLife(java.lang.String shelfLife) {
        this.shelfLife = shelfLife;
    }

    /**
     * <p>加工方式。</p>
     *
     * @return the 加工方式
     */
    public java.lang.String getProcessingWay() {
        return processingWay;
    }

    /**
     * <p>加工方式。</p>
     *
     * @param processingWay 加工方式。
     */
    public void setProcessingWay(java.lang.String processingWay) {
        this.processingWay = processingWay;
    }

    /**
     * <p>储存方式。</p>
     *
     * @return the 储存方式
     */
    public java.lang.String getStorageWay() {
        return storageWay;
    }

    /**
     * <p>储存方式。</p>
     *
     * @param storageWay 储存方式。
     */
    public void setStorageWay(java.lang.String storageWay) {
        this.storageWay = storageWay;
    }

}
