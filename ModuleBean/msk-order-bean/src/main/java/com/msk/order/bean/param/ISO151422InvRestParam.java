package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseParam;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by wang_jianzhou on 2016/9/14.
 */
public class ISO151422InvRestParam extends BaseParam {

    private String platformId;

    private String platformName;

    private String lgcsCode;

    private String warehouseCode;

    private Long flowId;

    private String purchaseBatch;

    private String innerBatch;

    private String slCode;

    private String slName;

    private String supplierCode;

    private String supplierName;

    private String pdCode;

    private String pdName;

    private String skuCode;

    private BigDecimal inboundQty;

    private BigDecimal inboundPrice;

    private String invOptType;

    private String inventoryStatus;

    private String whCode;

    private String uom;

    private Long inboundId;

    private String inboundNo;

    private Long inboundDetailId;

    private String riId;

    private String riNo;

    private Long riDetailId;

    private Long asnId;

    private String asnNo;

    private Long asnDetailId;

    private Long poId;

    private String poNo;

    private Long poDetailId;

    private Date recvDate;

    private Date recvTime;

    private Date expirationDate;

    private Integer slType;

    private String lgcsName;

    public String getLgcsName() {
        return lgcsName;
    }

    public void setLgcsName(String lgcsName) {
        this.lgcsName = lgcsName;
    }

    public Integer getSlType() {
        return slType;
    }

    public void setSlType(Integer slType) {
        this.slType = slType;
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

    public String getSlName() {
        return slName;
    }

    public void setSlName(String slName) {
        this.slName = slName;
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

    public String getWhCode() {
        return whCode;
    }

    public void setWhCode(String whCode) {
        this.whCode = whCode;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
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

    public String getRiId() {
        return riId;
    }

    public void setRiId(String riId) {
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
}
