package com.msk.order.bean.param;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by wang_shuai on 2016/9/23.
 */
public class ISO151415OutBoundParam {
    /** 平台ID，CodeMaster有定义 */
    private String plantFormId;
    /** 物流区编码 */
    private String lgcsCode;
    /** 仓库编码 */
    private String warehouseCode;
    /** 采购批次 */
    private String purchaseBatch;
    /** 内部入库批次，可能是采购批次，也可能是退货批次等 */
    private String innerBatch;
    /** 卖家编码 */
    private String slCode;
    private String slType;
    /** 供应商编码 */
    private String supplierCode;
    /** 商品编码 */
    private String pdCode;
    /** skuCode */
    private String skuCode;
    /** 入库数量 */
    private java.math.BigDecimal outboundQty;
    /** 入库价格，若采购 */
    private java.math.BigDecimal outboundPrice;
    /** 入库操作类型，CodeMaster */
    private String invOptType;
    /** 仓库类型 */
    private String inventoryStatus;
    /** 出库作业单id（订单：发货单ID） */
    private Long outboundId;
    /** 出库作业单号（订单：发货单单号） */
    private String outboundNo;
    /** 出库作业明细id（订单：发货明细ID） */
    private Long outboundDetailId;
    /** 出库指示单id（订单：发货单ID） */
    private Long diId;
    /** 出库指示单号 */
    private String diNo;
    /** 出库指示单明细id */
    private Long diDetailId;
    /** 业务账单id（订单：主订单ID） */
    private Long soId;
    /** 业务账单号（订单：主订单编码） */
    private String soNo;
    /** 业务账单明细id（订单：主订单明细ID） */
    private Long soDetailId;
    /** 客户单id（订单：主订单ID） */
    private Long coId;
    /** 客户单号 */
    private String coNo;
    /** 客户单明细id */
    private Long coDetailId;
    /** 发货日期 */
    private java.util.Date dispatchedDate;
    /** 发货时间 */
    private java.util.Date dispatchedTime;

    public String getPlantFormId() {
        return plantFormId;
    }

    public void setPlantFormId(String plantFormId) {
        this.plantFormId = plantFormId;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getPurchaseBatch() {
        return purchaseBatch;
    }

    public void setPurchaseBatch(String purchaseBatch) {
        this.purchaseBatch = purchaseBatch;
    }

    public String getInnerBatch() {
        return innerBatch;
    }

    public void setInnerBatch(String innerBatch) {
        this.innerBatch = innerBatch;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getSlType() {
        return slType;
    }

    public void setSlType(String slType) {
        this.slType = slType;
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

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
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

    public String getInvOptType() {
        return invOptType;
    }

    public void setInvOptType(String invOptType) {
        this.invOptType = invOptType;
    }

    public String getInventoryStatus() {
        return inventoryStatus;
    }

    public void setInventoryStatus(String inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }

    public Long getOutboundId() {
        return outboundId;
    }

    public void setOutboundId(Long outboundId) {
        this.outboundId = outboundId;
    }

    public String getOutboundNo() {
        return outboundNo;
    }

    public void setOutboundNo(String outboundNo) {
        this.outboundNo = outboundNo;
    }

    public Long getOutboundDetailId() {
        return outboundDetailId;
    }

    public void setOutboundDetailId(Long outboundDetailId) {
        this.outboundDetailId = outboundDetailId;
    }

    public Long getDiId() {
        return diId;
    }

    public void setDiId(Long diId) {
        this.diId = diId;
    }

    public String getDiNo() {
        return diNo;
    }

    public void setDiNo(String diNo) {
        this.diNo = diNo;
    }

    public Long getDiDetailId() {
        return diDetailId;
    }

    public void setDiDetailId(Long diDetailId) {
        this.diDetailId = diDetailId;
    }

    public Long getSoId() {
        return soId;
    }

    public void setSoId(Long soId) {
        this.soId = soId;
    }

    public String getSoNo() {
        return soNo;
    }

    public void setSoNo(String soNo) {
        this.soNo = soNo;
    }

    public Long getSoDetailId() {
        return soDetailId;
    }

    public void setSoDetailId(Long soDetailId) {
        this.soDetailId = soDetailId;
    }

    public Long getCoId() {
        return coId;
    }

    public void setCoId(Long coId) {
        this.coId = coId;
    }

    public String getCoNo() {
        return coNo;
    }

    public void setCoNo(String coNo) {
        this.coNo = coNo;
    }

    public Long getCoDetailId() {
        return coDetailId;
    }

    public void setCoDetailId(Long coDetailId) {
        this.coDetailId = coDetailId;
    }

    public Date getDispatchedDate() {
        return dispatchedDate;
    }

    public void setDispatchedDate(Date dispatchedDate) {
        this.dispatchedDate = dispatchedDate;
    }

    public Date getDispatchedTime() {
        return dispatchedTime;
    }

    public void setDispatchedTime(Date dispatchedTime) {
        this.dispatchedTime = dispatchedTime;
    }
}
