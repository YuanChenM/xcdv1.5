package com.msk.inventory.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by wang_fan2 on 2016/8/25.
 */
public class ISO152405InvParamBean {
    /** 平台ID，CodeMaster有定义 */
    private String platformId;
    private String platformName;
    /** 物流区编码 */
    private String lgcsCode;
    /** 仓库编码 */
    private String warehouseCode;
    /** 业务单对应的ID */
    private Long flowId;
    /** 采购批次 */
    private String purchaseBatch;
    /** 内部入库批次，可能是采购批次，也可能是退货批次等 */
    private String innerBatch;
    /** 卖家编码 */
    private String slCode;
    private String slName;
    /** 卖家类型 */
    private String  slType;
    /** 供应商编码 */
    private String supplierCode;
    private String supplierName;
    /** 商品编码 */
    private String pdCode;
    private String pdName;
    /** skuCode */
    private String skuCode;
    /** 入库数量 */
    private java.math.BigDecimal inboundQty;
    /** 入库价格，若采购 */
    private java.math.BigDecimal inboundPrice;
    /** 入库操作类型，CodeMaster */
    private String invOptType;
    /** 仓库类型 */
    private String inventoryStatus;
    /** 入库作业单id（预入库/发货入库/退货） */
    private Long inboundId;
    /** 入库作业单号（预入库/发货入库/退货） */
    private String inboundNo;
    /** 入库明细id（预入库/发货入库/退货） */
    private Long inboundDetailId;
    /** 入库指示单id（三方确认） */
    private Long riId;
    /** 入库指示单单号（三方确认） */
    private String riNo;
    /** 入库指示单明细id（三方确认） */
    private Long riDetailId;
    /** 业务账单id（发货单） */
    private Long asnId;
    /** 业务账单号（发货单） */
    private String asnNo;
    /** 业务账单明细id（发货单） */
    private Long asnDetailId;
    /** 订单id（合同） */
    private Long poId;
    /** 订单号（合同） */
    private String poNo;
    /** 订单明细id（合同） */
    private Long poDetailId;
    /** 收货日期 */
    private java.util.Date recvDate;
    /** 收货时间 */
    private java.util.Date recvTime;
    /** 过期日期 */
    private java.util.Date expirationDate;
    /** 单位 */
    private String uom;

    public String getSlType() {
        return slType;
    }

    public void setSlType(String slType) {
        this.slType = slType;
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

    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
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

    public BigDecimal getInboundQty() {
        return inboundQty;
    }

    public void setInboundQty(BigDecimal inboundQty) {
        this.inboundQty = inboundQty;
    }

    public BigDecimal getInboundPrice() {
        return inboundPrice;
    }

    public void setInboundPrice(BigDecimal inboundPrice) {
        this.inboundPrice = inboundPrice;
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

    public Long getInboundId() {
        return inboundId;
    }

    public void setInboundId(Long inboundId) {
        this.inboundId = inboundId;
    }

    public String getInboundNo() {
        return inboundNo;
    }

    public void setInboundNo(String inboundNo) {
        this.inboundNo = inboundNo;
    }

    public Long getInboundDetailId() {
        return inboundDetailId;
    }

    public void setInboundDetailId(Long inboundDetailId) {
        this.inboundDetailId = inboundDetailId;
    }

    public Long getRiId() {
        return riId;
    }

    public void setRiId(Long riId) {
        this.riId = riId;
    }

    public String getRiNo() {
        return riNo;
    }

    public void setRiNo(String riNo) {
        this.riNo = riNo;
    }

    public Long getRiDetailId() {
        return riDetailId;
    }

    public void setRiDetailId(Long riDetailId) {
        this.riDetailId = riDetailId;
    }

    public Long getAsnId() {
        return asnId;
    }

    public void setAsnId(Long asnId) {
        this.asnId = asnId;
    }

    public String getAsnNo() {
        return asnNo;
    }

    public void setAsnNo(String asnNo) {
        this.asnNo = asnNo;
    }

    public Long getAsnDetailId() {
        return asnDetailId;
    }

    public void setAsnDetailId(Long asnDetailId) {
        this.asnDetailId = asnDetailId;
    }

    public Long getPoId() {
        return poId;
    }

    public void setPoId(Long poId) {
        this.poId = poId;
    }

    public String getPoNo() {
        return poNo;
    }

    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }

    public Long getPoDetailId() {
        return poDetailId;
    }

    public void setPoDetailId(Long poDetailId) {
        this.poDetailId = poDetailId;
    }

    public Date getRecvDate() {
        return recvDate;
    }

    public void setRecvDate(Date recvDate) {
        this.recvDate = recvDate;
    }

    public Date getRecvTime() {
        return recvTime;
    }

    public void setRecvTime(Date recvTime) {
        this.recvTime = recvTime;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getSlName() {
        return slName;
    }

    public void setSlName(String slName) {
        this.slName = slName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }
}
