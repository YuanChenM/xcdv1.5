package com.msk.stock.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by zhang_qiang1 on 2016/4/21.
 */
public class Stock implements Serializable {

    private Long  stockId;// 库存id

    private  String lgcsCode;// 物流区编码

    private String lgcsName;//物流区名称

    private  String warehouseCode ;// 仓库编码

    private String warehouseName;//仓库名称

    private String  supplierCode;// 供应商id

    private  String supplierName;// 供应商名称

    private  String slCode;// 卖家编码

    private String slName;// 卖家名称

    private String pdTypeCode;// 产品类型编码

    private String  warehoseCode;// 仓库编码

    private  String    salePlatform;// 销售平台

    private String   supplyPlatform;// 供应商供货平台

    private  String pdCode;// 产品编码

    private String pdName;//产品名称

    private  String constitutedPdName;// 由classCode,breedCode 组成

    private  String unit;// 单位

    private String classesCode;//产品类别

    private String classesName;//产品类别名称

    private String breedCode;//产品品种

    private String breedName;//产品品种名称

   private  String featureCode;//特征编码

    private String featureName;//特征名称

   private String normsCode;//产品包装编码

    private String normsName;//产品包装名称

    private String pdLevel;//产品等级

    private BigDecimal packingVolume;//单箱体积

   private  BigDecimal weight;// 重量

    private BigDecimal volume;// 体积

    private  BigDecimal  orderStock;// 订单 已发货库存

    private  BigDecimal   returnStock;// 退货入库库存

    private String stockType;// 1  正常仓别，非正常仓别

    private BigDecimal stockQty;//商品库存

    private  BigDecimal enabledStockQty=new BigDecimal(998);// 该商品的有用库存

    private BigDecimal pdTypeSumStock;// pdTypcode  PD:10位   pdTypeCode 9位      pdTypSumstock ：前9位相同的 pdCOde的总库存量

    private BigDecimal  sumStock;// 总库存  当前使用环境就是 供应商分组查询库存


    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getSlName() {
        return slName;
    }

    public void setSlName(String slName) {
        this.slName = slName;
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

    public BigDecimal getStockQty() {
        return stockQty;
    }

    public void setStockQty(BigDecimal stockQty) {
        this.stockQty = stockQty;
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

    public String getBreedCode() {
        return breedCode;
    }

    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public String getLgcsName() {
        return lgcsName;
    }

    public void setLgcsName(String lgcsName) {
        this.lgcsName = lgcsName;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }


    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getOrderStock() {
        return orderStock;
    }

    public void setOrderStock(BigDecimal orderStock) {
        this.orderStock = orderStock;
    }

    public BigDecimal getReturnStock() {
        return returnStock;
    }

    public void setReturnStock(BigDecimal returnStock) {
        this.returnStock = returnStock;
    }

    public BigDecimal getEnabledStockQty() {
        return enabledStockQty;
    }


    public BigDecimal getPackingVolume() {
        return packingVolume;
    }

    public void setPackingVolume(BigDecimal packingVolume) {
        this.packingVolume = packingVolume;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public void setEnabledStockQty(BigDecimal enabledStockQty) {
        this.enabledStockQty = enabledStockQty;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public String getWarehoseCode() {
        return warehoseCode;
    }

    public void setWarehoseCode(String warehoseCode) {
        this.warehoseCode = warehoseCode;
    }

    public String getSalePlatform() {
        return salePlatform;
    }

    public void setSalePlatform(String salePlatform) {
        this.salePlatform = salePlatform;
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

    public String getSupplyPlatform() {
        return supplyPlatform;
    }

    public void setSupplyPlatform(String supplyPlatform) {
        this.supplyPlatform = supplyPlatform;
    }

    public BigDecimal getPdTypeSumStock() {
        return pdTypeSumStock;
    }

    public void setPdTypeSumStock(BigDecimal pdTypeSumStock) {
        this.pdTypeSumStock = pdTypeSumStock;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getPdTypeCode() {
        return pdTypeCode;
    }

    public void setPdTypeCode(String pdTypeCode) {
        this.pdTypeCode = pdTypeCode;
    }

    public String getConstitutedPdName() {
        return constitutedPdName;
    }

    public void setConstitutedPdName(String constitutedPdName) {
        this.constitutedPdName = constitutedPdName;
    }

    public BigDecimal getSumStock() {
        return sumStock;
    }

    public void setSumStock(BigDecimal sumStock) {
        this.sumStock = sumStock;
    }

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType;
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

    public String getPdLevel() {
        return pdLevel;
    }

    public void setPdLevel(String pdLevel) {
        this.pdLevel = pdLevel;
    }


}
