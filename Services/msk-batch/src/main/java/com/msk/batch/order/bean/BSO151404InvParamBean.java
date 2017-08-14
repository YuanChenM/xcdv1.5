package com.msk.batch.order.bean;

import java.math.BigDecimal;

/**
 * Created by wang_shuai on 2016/9/12.
 */
public class BSO151404InvParamBean {
    private String salePlatform;
    private String logicCode;
    private String warehouseCode;
    private String inventoryStatus;
    private String outSlCode;
    private String outSlName;
    private String outSlType;
    private String inSlCode;
    private String inSlName;
    private String inSlType;
    private String supplierCode;
    private String pdCode;
    private BigDecimal outboundQty;
    private BigDecimal outboundPrice;
    private Long flowId;
    private String flowNo;

    public String getSalePlatform() {
        return salePlatform;
    }

    public void setSalePlatform(String salePlatform) {
        this.salePlatform = salePlatform;
    }

    public String getLogicCode() {
        return logicCode;
    }

    public void setLogicCode(String logicCode) {
        this.logicCode = logicCode;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getInventoryStatus() {
        return inventoryStatus;
    }

    public void setInventoryStatus(String inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }

    public String getOutSlCode() {
        return outSlCode;
    }

    public void setOutSlCode(String outSlCode) {
        this.outSlCode = outSlCode;
    }

    public String getOutSlName() {
        return outSlName;
    }

    public void setOutSlName(String outSlName) {
        this.outSlName = outSlName;
    }

    public String getOutSlType() {
        return outSlType;
    }

    public void setOutSlType(String outSlType) {
        this.outSlType = outSlType;
    }

    public String getInSlCode() {
        return inSlCode;
    }

    public void setInSlCode(String inSlCode) {
        this.inSlCode = inSlCode;
    }

    public String getInSlName() {
        return inSlName;
    }

    public void setInSlName(String inSlName) {
        this.inSlName = inSlName;
    }

    public String getInSlType() {
        return inSlType;
    }

    public void setInSlType(String inSlType) {
        this.inSlType = inSlType;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public BigDecimal getOutboundQty() {
        return outboundQty;
    }

    public void setOutboundQty(BigDecimal outboundQty) {
        this.outboundQty = outboundQty;
    }

    public BigDecimal getOutboundPrice() {
        return outboundPrice;
    }

    public void setOutboundPrice(BigDecimal outboundPrice) {
        this.outboundPrice = outboundPrice;
    }

    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

    public String getFlowNo() {
        return flowNo;
    }

    public void setFlowNo(String flowNo) {
        this.flowNo = flowNo;
    }
}
