package com.msk.inventory.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by wang_fan2 on 2016/8/23.
 */
public class ISO152413PdStockResultBean implements Serializable {
    /** 仓库编码 */
    private String warehouseCode;
    /** 仓库名称 */
    private String wareHouseName;
    /** 一级分类编码 */
    private String classesCode;
    /** 一级分类名称 */
    private String classesName;
    /** 二级分类编码 */
    private String machiningCode;
    /** 二级分类名称 */
    private String machiningName;
    /** 品种编码 */
    private String breedCode;
    /** 品种名称 */
    private String breedName;
    /** 特征编码 */
    private String featureCode;
    /** 特征名称 */
    private String featureName;
    /** */
    private String weightCode;
    /** */
    private String gradeCode;
    /** 包装编码 */
    private String normsCode;
    /** 包装名称 */
    private String normsName;
    /** 等级编码 */
    private String pdGradeCode;
    /** 等级名称 */
    private String pdGradeName;
    /** 产品编码 */
    private String pdCode;
    /** 产品名称 */
    private String pdName;
    /** 单位 */
    private String unit;
    /** 单箱体积 */
    private String packingVolume;
    /** 良品在库库存 */
    private BigDecimal stockQty;
    /** 良品可用库存 */
    private BigDecimal availableQty;
    /** 供应商编码 */
    private String supplierCode;
    /** 供应商名称 */
    private String supplierName;
    /** SKU编码 */
    private String skuCode;
    /** 采购批次 */
    private String purchaseBatch;
    /** 最近一次入库批次 */
    private String inboundBatch;
    /** 占用数量*/
    private BigDecimal allocatedQty;

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getWareHouseName() {
        return wareHouseName;
    }

    public void setWareHouseName(String wareHouseName) {
        this.wareHouseName = wareHouseName;
    }

    public String getClassesCode() {
        return classesCode;
    }

    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    public String getMachiningCode() {
        return machiningCode;
    }

    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    public String getMachiningName() {
        return machiningName;
    }

    public void setMachiningName(String machiningName) {
        this.machiningName = machiningName;
    }

    public String getBreedCode() {
        return breedCode;
    }

    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
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

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getNormsCode() {
        return normsCode;
    }

    public void setNormsCode(String normsCode) {
        this.normsCode = normsCode;
    }

    public String getNormsName() {
        return normsName;
    }

    public void setNormsName(String normsName) {
        this.normsName = normsName;
    }

    public String getPdGradeCode() {
        return pdGradeCode;
    }

    public void setPdGradeCode(String pdGradeCode) {
        this.pdGradeCode = pdGradeCode;
    }

    public String getPdGradeName() {
        return pdGradeName;
    }

    public void setPdGradeName(String pdGradeName) {
        this.pdGradeName = pdGradeName;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPackingVolume() {
        return packingVolume;
    }

    public void setPackingVolume(String packingVolume) {
        this.packingVolume = packingVolume;
    }

    public BigDecimal getStockQty() {
        return stockQty;
    }

    public void setStockQty(BigDecimal stockQty) {
        this.stockQty = stockQty;
    }

    public BigDecimal getAvailableQty() {
        return availableQty;
    }

    public void setAvailableQty(BigDecimal availableQty) {
        this.availableQty = availableQty;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getPurchaseBatch() {
        return purchaseBatch;
    }

    public void setPurchaseBatch(String purchaseBatch) {
        this.purchaseBatch = purchaseBatch;
    }

    public String getInboundBatch() {
        return inboundBatch;
    }

    public void setInboundBatch(String inboundBatch) {
        this.inboundBatch = inboundBatch;
    }

    public BigDecimal getAllocatedQty() {
        return allocatedQty;
    }

    public void setAllocatedQty(BigDecimal allocatedQty) {
        this.allocatedQty = allocatedQty;
    }
}
