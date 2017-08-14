package com.msk.ds.bean;

import com.msk.common.base.BaseBean;
import com.msk.stock.bean.StockRsParam;

import java.math.BigDecimal;

/**
 * zhou_yajun on 2016/1/28.
 */
public class SC182102Param extends BaseBean{
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /**产品名称(全称) */
    private String productName;
    /** 产品一级分类名称 */
    private String classesName;
    /**产品sku*/
    private String sku;
    /**采购批次*/
    private String sscDeliveryStockId;
    /** 产品一级分类编码 */
    private String classesCode;
    /**产品编码 */
    private String productCode;
    /**产品品种编码 */
    private String breedCode;
    /**产品品种名称 */
    private String productTypeName;
    /**产品特征名称 */
    private String productFeature;
    /**产品特征编码 */
    private String featureCode;
    /**产品等级 */
    private String productLevel;
    /**产品等级编码 */
    private String gradeCode;
    /**产品规格 */
    private String productSpecifical;
    /**净重 */
    private BigDecimal netWeight;
    /**产品包装编码 */
    private String packageCode;
    /** 产品包装名称 */
    private String normsName;
    /**计划发货箱数 */
    private BigDecimal sendNum;
    /**实际发货箱数 */
    private BigDecimal sendActualNum;
    /**实际收货箱数 */
    private BigDecimal receiveNum;
    /**发货数量 */
    private BigDecimal sendQty;
    /**备注 */
    private String remark;
    /**录入时间 */
    private String inputDate;

    /** 计划发货重量（KG）*/
    private BigDecimal sendPlanQty;
    /** 实际收货重量（KG）*/
    private BigDecimal recriveQty;
    /** 差异箱数 */
    private BigDecimal differNum;
    /** 差异重量（KG）*/
    private BigDecimal differQty;

    /** 物流区名称 */
    private String lgcsName;
    /** 供应商名称 */
    private String supplierName;
    /** 产品单位 */
    private String unit;
    /** 产品单箱体积 */
    private BigDecimal packingVolume;
    /** 产品体积 */
    private BigDecimal volume;

    /**计划发货箱数合计*/
    private BigDecimal currentSendPlanNum;

    /**实际发货箱数合计 */
    private BigDecimal currentSendActualNum;


    /**实际收货箱数合计 */
    private BigDecimal currentReceiveNum;
    /** 计划发货重量（KG）合计*/
    private BigDecimal currentSendPlanQty;


    /** 实际发货重量（KG）合计*/
    private BigDecimal currentSendActualQty;
    /** 实际收货重量（KG）合计*/
    private BigDecimal currentRecriveQty;
    /** 差异箱数 合计*/
    private BigDecimal currentDifferNum;
    /** 差异重量（KG）合计*/
    private BigDecimal currentDifferQty;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public BigDecimal getCurrentSendPlanNum() {
        return currentSendPlanNum;
    }

    public void setCurrentSendPlanNum(BigDecimal currentSendPlanNum) {
        this.currentSendPlanNum = currentSendPlanNum;
    }

    public BigDecimal getCurrentSendActualNum() {
        return currentSendActualNum;
    }

    public void setCurrentSendActualNum(BigDecimal currentSendActualNum) {
        this.currentSendActualNum = currentSendActualNum;
    }

    public BigDecimal getCurrentReceiveNum() {
        return currentReceiveNum;
    }

    public void setCurrentReceiveNum(BigDecimal currentReceiveNum) {
        this.currentReceiveNum = currentReceiveNum;
    }

    public BigDecimal getCurrentSendPlanQty() {
        return currentSendPlanQty;
    }

    public void setCurrentSendPlanQty(BigDecimal currentSendPlanQty) {
        this.currentSendPlanQty = currentSendPlanQty;
    }

    public BigDecimal getCurrentSendActualQty() {
        return currentSendActualQty;
    }

    public void setCurrentSendActualQty(BigDecimal currentSendActualQty) {
        this.currentSendActualQty = currentSendActualQty;
    }

    public BigDecimal getCurrentRecriveQty() {
        return currentRecriveQty;
    }

    public void setCurrentRecriveQty(BigDecimal currentRecriveQty) {
        this.currentRecriveQty = currentRecriveQty;
    }

    public BigDecimal getCurrentDifferNum() {
        return currentDifferNum;
    }

    public void setCurrentDifferNum(BigDecimal currentDifferNum) {
        this.currentDifferNum = currentDifferNum;
    }

    public BigDecimal getCurrentDifferQty() {
        return currentDifferQty;
    }

    public void setCurrentDifferQty(BigDecimal currentDifferQty) {
        this.currentDifferQty = currentDifferQty;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public String getProductFeature() {
        return productFeature;
    }

    public void setProductFeature(String productFeature) {
        this.productFeature = productFeature;
    }

    public String getProductLevel() {
        return productLevel;
    }

    public void setProductLevel(String productLevel) {
        this.productLevel = productLevel;
    }

    public String getProductSpecifical() {
        return productSpecifical;
    }

    public void setProductSpecifical(String productSpecifical) {
        this.productSpecifical = productSpecifical;
    }

    public BigDecimal getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(BigDecimal netWeight) {
        this.netWeight = netWeight;
    }

    public String getPackageCode() {
        return packageCode;
    }

    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode;
    }

    public BigDecimal getSendNum() {
        return sendNum;
    }

    public void setSendNum(BigDecimal sendNum) {
        this.sendNum = sendNum;
    }

    public BigDecimal getSendActualNum() {
        return sendActualNum;
    }

    public BigDecimal getReceiveNum() {
        return receiveNum;
    }

    public void setReceiveNum(BigDecimal receiveNum) {
        this.receiveNum = receiveNum;
    }

    public void setSendActualNum(BigDecimal sendActualNum) {
        this.sendActualNum = sendActualNum;
    }

    public BigDecimal getSendQty() {
        return sendQty;
    }

    public void setSendQty(BigDecimal sendQty) {
        this.sendQty = sendQty;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getInputDate() {
        return inputDate;
    }

    public void setInputDate(String inputDate) {
        this.inputDate = inputDate;
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

    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public String getLgcsName() {
        return lgcsName;
    }

    public void setLgcsName(String lgcsName) {
        this.lgcsName = lgcsName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getClassesCode() {
        return classesCode;
    }

    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    public String getNormsName() {
        return normsName;
    }

    public void setNormsName(String normsName) {
        this.normsName = normsName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getPackingVolume() {
        return packingVolume;
    }

    public void setPackingVolume(BigDecimal packingVolume) {
        this.packingVolume = packingVolume;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getSendPlanQty() {
        return sendPlanQty;
    }

    public void setSendPlanQty(BigDecimal sendPlanQty) {
        this.sendPlanQty = sendPlanQty;
    }

    public BigDecimal getRecriveQty() {
        return recriveQty;
    }

    public void setRecriveQty(BigDecimal recriveQty) {
        this.recriveQty = recriveQty;
    }

    public BigDecimal getDifferQty() {
        return differQty;
    }

    public void setDifferQty(BigDecimal differQty) {
        this.differQty = differQty;
    }

    public BigDecimal getDifferNum() {
        return differNum;
    }

    public void setDifferNum(BigDecimal differNum) {
        this.differNum = differNum;
    }

    public String getSscDeliveryStockId() {
        return sscDeliveryStockId;
    }

    public void setSscDeliveryStockId(String sscDeliveryStockId) {
        this.sscDeliveryStockId = sscDeliveryStockId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
}
