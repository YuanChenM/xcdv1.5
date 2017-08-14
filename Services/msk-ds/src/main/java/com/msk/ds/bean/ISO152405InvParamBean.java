package com.msk.ds.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by wang_fan2 on 2016/8/25.
 */
@ApiModel(value = "ISO152405InvParamBean",
    description = "库存产品入库接口详细信息")
public class ISO152405InvParamBean {
    @ApiModelProperty(value = "平台ID，CodeMaster有定义")
    private String platformId;
    @ApiModelProperty(value = "平台NAME")
    private String platformName;
    @ApiModelProperty(value = "物流区编码")
    private String lgcsCode;
    @ApiModelProperty(value = "仓库编码")
    private String lgcsName;
    @ApiModelProperty(value = "仓库名称")
    private String warehouseCode;
    @ApiModelProperty(value = "业务单对应的ID")
    private String flowId;
    @ApiModelProperty(value = "* 采购批次")
    private String purchaseBatch;
    @ApiModelProperty(value = "内部入库批次，可能是采购批次，也可能是退货批次等")
    private String innerBatch;
    @ApiModelProperty(value = "卖家编码")
    private String slCode;
    @ApiModelProperty(value = "卖家名称")
    private String slName;
    @ApiModelProperty(value = "卖家类型")
    private String slType;
    @ApiModelProperty(value = "供应商编码")
    private String supplierCode;
    @ApiModelProperty(value = "供应商名称")
    private String supplierName;
    @ApiModelProperty(value = "商品编码")
    private String pdCode;
    @ApiModelProperty(value = "商品名称")
    private String pdName;
    @ApiModelProperty(value = "skuCode")
    private String skuCode;
    @ApiModelProperty(value = "入库数量")
    private BigDecimal inboundQty;
    @ApiModelProperty(value = "入库价格，若采购")
    private BigDecimal inboundPrice;
    @ApiModelProperty(value = "入库操作类型")
    private String invOptType;
    @ApiModelProperty(value = "仓库类型")
    private String inventoryStatus;
    @ApiModelProperty(value = "入库作业单id（预入库/发货入库/退货）")
    private String inboundId;
    @ApiModelProperty(value = "入库作业单号（预入库/发货入库/退货）")
    private String inboundNo;
    @ApiModelProperty(value = "入库明细id（预入库/发货入库/退货）")
    private String inboundDetailId;
    @ApiModelProperty(value = "入库指示单id（三方确认）")
    private String riId;
    @ApiModelProperty(value = "入库指示单单号（三方确认）")
    private String riNo;
    @ApiModelProperty(value = "入库指示单明细id（三方确认）")
    private String riDetailId;
    @ApiModelProperty(value = "业务账单id（发货单）")
    private String asnId;
    @ApiModelProperty(value = "业务账单号（发货单）")
    private String asnNo;
    @ApiModelProperty(value = "业务账单明细id（发货单）")
    private String asnDetailId;
    @ApiModelProperty(value = "订单id（合同）")
    private String poId;
    @ApiModelProperty(value = "订单号（合同）")
    private String poNo;
    @ApiModelProperty(value = "订单明细id（合同）")
    private String poDetailId;
    @ApiModelProperty(value = "收货日期")
    private Date recvDate;
    @ApiModelProperty(value = "收货时间")
    private Date recvTime;
    @ApiModelProperty(value = "过期日期")
    private Date expirationDate;
    @ApiModelProperty(value = "单位")
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

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
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

    public String getInboundId() {
        return inboundId;
    }

    public void setInboundId(String inboundId) {
        this.inboundId = inboundId;
    }

    public String getInboundNo() {
        return inboundNo;
    }

    public void setInboundNo(String inboundNo) {
        this.inboundNo = inboundNo;
    }

    public String getInboundDetailId() {
        return inboundDetailId;
    }

    public void setInboundDetailId(String inboundDetailId) {
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

    public String getRiDetailId() {
        return riDetailId;
    }

    public void setRiDetailId(String riDetailId) {
        this.riDetailId = riDetailId;
    }

    public String getAsnId() {
        return asnId;
    }

    public void setAsnId(String asnId) {
        this.asnId = asnId;
    }

    public String getAsnNo() {
        return asnNo;
    }

    public void setAsnNo(String asnNo) {
        this.asnNo = asnNo;
    }

    public String getAsnDetailId() {
        return asnDetailId;
    }

    public void setAsnDetailId(String asnDetailId) {
        this.asnDetailId = asnDetailId;
    }

    public String getPoId() {
        return poId;
    }

    public void setPoId(String poId) {
        this.poId = poId;
    }

    public String getPoNo() {
        return poNo;
    }

    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }

    public String getPoDetailId() {
        return poDetailId;
    }

    public void setPoDetailId(String poDetailId) {
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

    public String getLgcsName() {
        return lgcsName;
    }

    public void setLgcsName(String lgcsName) {
        this.lgcsName = lgcsName;
    }
}
