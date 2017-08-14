package com.msk.batch.order.bean;

import com.msk.core.entity.BaseEntity;
import com.msk.core.entity.SoOrder;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

/**
 * BSO151403Param
 * @author wang_jianzhou
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"orderLine","sku","inboundBatch","unit","sellerCode","districtCode","supplierCode","suppQty","pdPrice","orderSource","inventoryStatus"})
public class BSO151403DetailResult extends BaseEntity {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    /** FORECAST_SEND_TIME */
    private long orderLine;
    /** PD_CODE */
    private String pdCode;
    /** PD_NAME */
    private String pdName;
    /** PD_PRICE */
    private BigDecimal pdPrice;
    /** SUPP_QTY */
    private BigDecimal suppQty;
    /** SEND_QTY */
    private BigDecimal sendQty;
    /** SUPPLIER_CODE */
    private String supplierCode;
    /** SUPPLIER_NAME */
    private String supplierName;
    /** UNIT */
    private String unit;
    /** SKU */
    private String sku;
    /** ORDER_QTY*/
    private BigDecimal orderQty;
    /** 发货明细ID*/
    private Long shipDetailId;
    /** 分批订单明细ID*/
    private Long subOrderDetailId;
    /** 入库批次号*/
    private String inboundBatch;
    /** 卖家编码*/
    private String sellerCode;
    /** 物流区编码*/
    private String districtCode;
    /** 订单来源*/
    private Integer orderSource;
    /** 库存类别*/
    private String inventoryStatus;
    /** 产品一级分类编码*/
    private String classesCode;
    /** 产品品种编码*/
    private String breedCode;
    /** 产品特征编码*/
    private String featureCode;
    /** 产品二级分类编码*/
    private String machiningCode;
    /** 净重编码*/
    private String weightCode;
    /** 等级编码*/
    private String gradeCode;

    @XmlTransient
    public String getClassesCode() {
        return classesCode;
    }

    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    @XmlTransient
    public String getBreedCode() {
        return breedCode;
    }

    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    @XmlTransient
    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    @XmlTransient
    public String getMachiningCode() {
        return machiningCode;
    }

    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    @XmlTransient
    public String getWeightCode() {
        return weightCode;
    }

    public void setWeightCode(String weightCode) {
        this.weightCode = weightCode;
    }

    @XmlTransient
    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    @XmlElement(name = "INVENTORYSTATUS",defaultValue = "")
    public String getInventoryStatus() {
        return inventoryStatus;
    }

    public void setInventoryStatus(String inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }

    @XmlElement(name = "ATTRIBUTES",defaultValue = "")
    public Integer getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(Integer orderSource) {
        this.orderSource = orderSource;
    }

    @XmlElement(name = "AREA",defaultValue = "")
    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    @XmlElement(name = "CONSIGNEE",defaultValue = "")
    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    @XmlElement(name = "INBOUNDBATCH",defaultValue = "")
    public String getInboundBatch() {
        return inboundBatch;
    }

    public void setInboundBatch(String inboundBatch) {
        this.inboundBatch = inboundBatch;
    }

    @XmlTransient
    public Long getSubOrderDetailId() {
        return subOrderDetailId;
    }

    public void setSubOrderDetailId(Long subOrderDetailId) {
        this.subOrderDetailId = subOrderDetailId;
    }

    @XmlTransient
    public Long getShipDetailId() {
        return shipDetailId;
    }

    public void setShipDetailId(Long shipDetailId) {
        this.shipDetailId = shipDetailId;
    }

    @XmlElement(name = "ORDERLINE",defaultValue = "")
    public long getOrderLine() {
        return orderLine;
    }

    public void setOrderLine(long orderLine) {
        this.orderLine = orderLine;
    }

    @XmlTransient
    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    @XmlTransient
    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    @XmlElement(name = "INPUTQTY",defaultValue = "")
    public BigDecimal getPdPrice() {
        return pdPrice;
    }

    public void setPdPrice(BigDecimal pdPrice) {
        this.pdPrice = pdPrice;
    }

    @XmlElement(name = "QTYORIGINAL",defaultValue = "")
    public BigDecimal getSuppQty() {
        return suppQty;
    }

    public void setSuppQty(BigDecimal suppQty) {
        this.suppQty = suppQty;
    }

    @XmlTransient
    public BigDecimal getSendQty() {
        return sendQty;
    }

    public void setSendQty(BigDecimal sendQty) {
        this.sendQty = sendQty;
    }

    @XmlElement(name = "COMPANY",defaultValue = "")
    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    @XmlTransient
    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    @XmlElement(name = "UOM",defaultValue = "")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @XmlTransient
    public BigDecimal getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(BigDecimal orderQty) {
        this.orderQty = orderQty;
    }

    @XmlElement(name = "SKU",defaultValue = "")
    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
}
