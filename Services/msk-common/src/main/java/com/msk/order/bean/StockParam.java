package com.msk.order.bean;


import com.hoperun.core.bean.BaseParam;

import java.math.BigDecimal;

/**
 * 库存Param
 * @author yuan_chen
 */
public class StockParam extends BaseParam {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    /** 物流区编号 */
    private String lgcsCode;
    /** 物流区名称 */
    private String lgcsName;
    /** 仓库编号 */
    private String warehouseCode;
    /** 仓库名称 */
    private String warehouseName;
    /** 供货平台 */
    private Integer supplyPlatform;
    /** 库存类别 */
    private Integer stockType;
    /** 卖家编号 */
    private String slCode;
    /** 卖家名称 */
    private String slName;
    /** 原卖家编号 */
    private String sourceSellerCode;
    /** 原卖家名称 */
    private String sourceSellerName;
    /** 供应商编码 */
    private String supplierCode;
    /** 供应商名称 */
    private String supplierName;
    /** 产品类型 */
    private String classesCode;
    /** 产品类型名称 */
    private String classesName;
    /** 产品品种 */
    private String breedCode;
    /** 产品品种名称 */
    private String breedName;
    /** 特征编码 */
    private String featureCode;
    /** 特征名称 */
    private String featureName;
    /** 产品包装编码 */
    private String normsCode;
    /** 产品包装名称 */
    private String normsName;
    /** 产品编号 */
    private String pdCode;
    /** 产品名称 */
    private String pdName;
    /** 产品等级 */
    private String pdLevel;
    /** 产品单位 */
    private String unit;
    /** 单箱体积 */
    private String packingVolume;
    /** 重量 */
    private String weight;
    /** 体积 */
    private String volume;
    /** 数量 */
    private BigDecimal stockNum;
    /** 业务编码 */
    private String flowId;
    /** 变更类型1：增加，2：减少 */
    private String changeType;
    /** 备份日期 */
    private String historyDate;

    /**PK:stockId*/
    private long stockId;

    //单价
    private BigDecimal pdPrice;

    /**
     * Getter method for property <tt>stockId</tt>.
     *
     * @return property value of stockId
     */
    public long getStockId() {
        return stockId;
    }
    /**
     * Setter method for property <tt>stockId</tt>.
     *
     * @param stockId value to be assigned to property stockId
     */
    public void setStockId(long stockId) {
        this.stockId = stockId;
    }

    /**
     * Getter method for property <tt>flowId</tt>.
     *
     * @return property value of flowId
     */
    public String getFlowId() {
        return flowId;
    }

    /**
     * Setter method for property <tt>flowId</tt>.
     *
     * @param flowId value to be assigned to property flowId
     */
    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    /**
     * Getter method for property <tt>changeType</tt>.
     *
     * @return property value of changeType
     */
    public String getChangeType() {
        return changeType;
    }

    /**
     * Setter method for property <tt>changeType</tt>.
     *
     * @param changeType value to be assigned to property changeType
     */
    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    /**
     * Getter method for property <tt>historyDate</tt>.
     *
     * @return property value of historyDate
     */
    public String getHistoryDate() {
        return historyDate;
    }

    /**
     * Setter method for property <tt>historyDate</tt>.
     *
     * @param historyDate value to be assigned to property historyDate
     */
    public void setHistoryDate(String historyDate) {
        this.historyDate = historyDate;
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
     * Getter method for property <tt>warehouseCode</tt>.
     *
     * @return property value of warehouseCode
     */
    public String getWarehouseCode() {
        return warehouseCode;
    }

    /**
     * Setter method for property <tt>warehouseCode</tt>.
     *
     * @param warehouseCode value to be assigned to property warehouseCode
     */
    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    /**
     * Getter method for property <tt>warehouseName</tt>.
     *
     * @return property value of warehouseName
     */
    public String getWarehouseName() {
        return warehouseName;
    }

    /**
     * Setter method for property <tt>warehouseName</tt>.
     *
     * @param warehouseName value to be assigned to property warehouseName
     */
    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    /**
     * Getter method for property <tt>supplyPlatform</tt>.
     *
     * @return property value of supplyPlatform
     */
    public Integer getSupplyPlatform() {
        return supplyPlatform;
    }

    /**
     * Setter method for property <tt>supplyPlatform</tt>.
     *
     * @param supplyPlatform value to be assigned to property supplyPlatform
     */
    public void setSupplyPlatform(Integer supplyPlatform) {
        this.supplyPlatform = supplyPlatform;
    }

    /**
     * Getter method for property <tt>stockType</tt>.
     *
     * @return property value of stockType
     */
    public Integer getStockType() {
        return stockType;
    }

    /**
     * Setter method for property <tt>stockType</tt>.
     *
     * @param stockType value to be assigned to property stockType
     */
    public void setStockType(Integer stockType) {
        this.stockType = stockType;
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
     * Getter method for property <tt>slName</tt>.
     *
     * @return property value of slName
     */
    public String getSlName() {
        return slName;
    }

    /**
     * Setter method for property <tt>slName</tt>.
     *
     * @param slName value to be assigned to property slName
     */
    public void setSlName(String slName) {
        this.slName = slName;
    }
    /**
     * <p>SOURCE_SELLER_CODE。</p>
     *
     * @return the SOURCE_SELLER_CODE
     */
    public String getSourceSellerCode() {
        return sourceSellerCode;
    }

    /**
     * <p>SOURCE_SELLER_CODE。</p>
     *
     * @param sourceSellerCode SOURCE_SELLER_CODE。
     */
    public void setSourceSellerCode(String sourceSellerCode) {
        this.sourceSellerCode = sourceSellerCode;
    }

    /**
     * <p>SOURCE_SELLER_NAME。</p>
     *
     * @return the SOURCE_SELLER_NAME
     */
    public String getSourceSellerName() {
        return sourceSellerName;
    }

    /**
     * <p>SOURCE_SELLER_NAME。</p>
     *
     * @param sourceSellerName SOURCE_SELLER_NAME。
     */
    public void setSourceSellerName(String sourceSellerName) {
        this.sourceSellerName = sourceSellerName;
    }

    /**
     * Getter method for property <tt>supplierCode</tt>.
     *
     * @return property value of supplierCode
     */
    public String getSupplierCode() {
        return supplierCode;
    }

    /**
     * Setter method for property <tt>supplierCode</tt>.
     *
     * @param supplierCode value to be assigned to property supplierCode
     */
    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    /**
     * Getter method for property <tt>supplierName</tt>.
     *
     * @return property value of supplierName
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * Setter method for property <tt>supplierName</tt>.
     *
     * @param supplierName value to be assigned to property supplierName
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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
     * Getter method for property <tt>breedName</tt>.
     *
     * @return property value of breedName
     */
    public String getBreedName() {
        return breedName;
    }

    /**
     * Setter method for property <tt>breedName</tt>.
     *
     * @param breedName value to be assigned to property breedName
     */
    public void setBreedName(String breedName) {
        this.breedName = breedName;
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
     * Getter method for property <tt>normsCode</tt>.
     *
     * @return property value of normsCode
     */
    public String getNormsCode() {
        return normsCode;
    }

    /**
     * Setter method for property <tt>normsCode</tt>.
     *
     * @param normsCode value to be assigned to property normsCode
     */
    public void setNormsCode(String normsCode) {
        this.normsCode = normsCode;
    }

    /**
     * Getter method for property <tt>normsName</tt>.
     *
     * @return property value of normsName
     */
    public String getNormsName() {
        return normsName;
    }

    /**
     * Setter method for property <tt>normsName</tt>.
     *
     * @param normsName value to be assigned to property normsName
     */
    public void setNormsName(String normsName) {
        this.normsName = normsName;
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
     * Getter method for property <tt>pdLevel</tt>.
     *
     * @return property value of pdLevel
     */
    public String getPdLevel() {
        return pdLevel;
    }

    /**
     * Setter method for property <tt>pdLevel</tt>.
     *
     * @param pdLevel value to be assigned to property pdLevel
     */
    public void setPdLevel(String pdLevel) {
        this.pdLevel = pdLevel;
    }

    /**
     * Getter method for property <tt>unit</tt>.
     *
     * @return property value of unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Setter method for property <tt>unit</tt>.
     *
     * @param unit value to be assigned to property unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * Getter method for property <tt>packingVolume</tt>.
     *
     * @return property value of packingVolume
     */
    public String getPackingVolume() {
        return packingVolume;
    }

    /**
     * Setter method for property <tt>packingVolume</tt>.
     *
     * @param packingVolume value to be assigned to property packingVolume
     */
    public void setPackingVolume(String packingVolume) {
        this.packingVolume = packingVolume;
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
     * Getter method for property <tt>volume</tt>.
     *
     * @return property value of volume
     */
    public String getVolume() {
        return volume;
    }

    /**
     * Setter method for property <tt>volume</tt>.
     *
     * @param volume value to be assigned to property volume
     */
    public void setVolume(String volume) {
        this.volume = volume;
    }

    /**
     * Getter method for property <tt>stockNum</tt>.
     *
     * @return property value of stockNum
     */
    public BigDecimal getStockNum() {
        return stockNum;
    }

    /**
     * Setter method for property <tt>stockNum</tt>.
     *
     * @param stockNum value to be assigned to property stockNum
     */
    public void setStockNum(BigDecimal stockNum) {
        this.stockNum = stockNum;
    }

    public BigDecimal getPdPrice() {
        return pdPrice;
    }

    public void setPdPrice(BigDecimal pdPrice) {
        this.pdPrice = pdPrice;
    }
}
