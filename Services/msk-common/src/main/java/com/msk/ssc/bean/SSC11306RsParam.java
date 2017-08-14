package com.msk.ssc.bean;

import com.hoperun.core.bean.BasePageParam;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by yang_yang
 */
public class SSC11306RsParam extends BasePageParam {

    /** 产品ID */
    private String detailId;
    /** 产品编码 */
    private String pdCode;
    /** 产品重量 */
    private String productQua;
    /** 干线运费 */
    private String trunkFreight;
    /** 到岸价 */
    private String cif;
    /** 产品箱数 */
    private String productBox;
    /** 本次结算标准价 */
    private String settkementStandardPrice;
    /** 货值 */
    private String productValue;
    /** 发货订单ID */
    private String deliveryId;
    /** 发货订单编号 */
    private String deliveryCode;
    /** CONTRACT_ID */
    private String contractId;
    /** 合同编号 */
    private String contractCode;
    /** 关联合同类型 */
    private Integer contractRelationType;
    /** 发货批次 */
    private Integer deliveryBatch;
    /** SUPPLIER_ID */
    private Long supplierId;
    /** 生产商 */
    private String supplierName;
    /** SUPPLIER_CODE */
    private String supplierCode;
    /** PURCHASER_ID */
    private Long purchaserId;
    /** 采购商 */
    private String purchaserName;
    /** 采购商编码 */
    private String purchaserCode;
    /** 发货日期 */
    private String etd;
    /** 到货日期 */
    private String eta;
    /** 发货仓库 */
    private String deliveryWarehouse;
    /** 发货地址 */
    private String deliveryWarehouseAddr;
    /** 到货仓库地址 */
    private String arriveWarehouseAddr;
    /** 发货订单状态 0:新建 1:待确认 2:甲方已审核 3:双方已确认 9:已取消*/
    private String deliveryStatus;
    /** 运费结算方式 */
    private String freightSettleMethod;
    /** 运费单价(元/吨公里) */
    private java.math.BigDecimal freightUnit;
    /** 里程 */
    private java.math.BigDecimal mileage;
    /** 每吨运费 */
    private java.math.BigDecimal transportUnit;
    /** 运费 */
    private java.math.BigDecimal transportCost;
    /** 总金额 */
    private java.math.BigDecimal amount;
    /** 不含运费结算标准价 */
    private String standardPrice;
    /** 备注 */
    private String remark;
    /** 到货仓库 */
    private String arriveWarehouse;
    private String jsonStr;
    /** 发货确认单编号 */
    private String deliveryConfirmCode;
    /**差异单页面跳转ID */
    private Long differId;
    private List<Long> deliveryIds;
    /** 付款记录ID 用于资金池一览跳转）*/
    private String paymentId;
    /** 付款记录ID（用于资金池详细跳转） */
    private Long paymentDetailId;
    /** 付款清单ID 用于付款一览跳转）*/
    private Long  paymentRequestId;
    /** 采购商审核ID */
    private java.lang.String purchaserAuditId;
    /** 采购商审核名称 */
    private java.lang.String purchaserAuditName;
    /** 采购商审核时间 */
    private java.util.Date purchaserAuditTime;
    /** 供应商确认ID */
    private java.lang.String supplierConfirmId;
    /** 供应商确认名称 */
    private java.lang.String supplierConfirmName;
    /** 供应商确认时间 */
    private java.util.Date supplierConfirmTime;
    /** 品牌所属企业ID */
    private java.lang.Long brandEpId;
    /** 品牌ID */
    private java.lang.Long brandId;
    /** 品牌名称 */
    private java.lang.String brandName;
    /** 核销单ID */
    private Long verificationId;

    /**付款申请详情id*/
    private Long paymentRequestDetailId;

    /** 物流区编码 */
    private String lgcsAreaCode;
    /** 物流区名称 */
    private String lgcsAreaName;

    public Long getPaymentRequestId() {
        return paymentRequestId;
    }

    public void setPaymentRequestId(Long paymentRequestId) {
        this.paymentRequestId = paymentRequestId;
    }

    public Long getPaymentDetailId() {
        return paymentDetailId;
    }

    public void setPaymentDetailId(Long paymentDetailId) {
        this.paymentDetailId = paymentDetailId;
    }

    public Long getDifferId() {
        return differId;
    }

    public void setDifferId(Long differId) {
        this.differId = differId;
    }

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getProductQua() {
        return productQua;
    }

    public void setProductQua(String productQua) {
        this.productQua = productQua;
    }

    public String getProductBox() {
        return productBox;
    }

    public void setProductBox(String productBox) {
        this.productBox = productBox;
    }

    public String getTrunkFreight() {
        return trunkFreight;
    }

    public void setTrunkFreight(String trunkFreight) {
        this.trunkFreight = trunkFreight;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getSettkementStandardPrice() {
        return settkementStandardPrice;
    }

    public void setSettkementStandardPrice(String settkementStandardPrice) {
        this.settkementStandardPrice = settkementStandardPrice;
    }

    public String getProductValue() {
        return productValue;
    }

    public void setProductValue(String productValue) {
        this.productValue = productValue;
    }


    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public Integer getContractRelationType() {
        return contractRelationType;
    }

    public void setContractRelationType(Integer contractRelationType) {
        this.contractRelationType = contractRelationType;
    }

    public Integer getDeliveryBatch() {
        return deliveryBatch;
    }

    public void setDeliveryBatch(Integer deliveryBatch) {
        this.deliveryBatch = deliveryBatch;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public Long getPurchaserId() {
        return purchaserId;
    }

    public void setPurchaserId(Long purchaserId) {
        this.purchaserId = purchaserId;
    }

    public String getPurchaserName() {
        return purchaserName;
    }

    public void setPurchaserName(String purchaserName) {
        this.purchaserName = purchaserName;
    }

    public String getPurchaserCode() {
        return purchaserCode;
    }

    public void setPurchaserCode(String purchaserCode) {
        this.purchaserCode = purchaserCode;
    }

    public String getEtd() {
        return etd;
    }

    public void setEtd(String etd) {
        this.etd = etd;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public String getDeliveryWarehouse() {
        return deliveryWarehouse;
    }

    public void setDeliveryWarehouse(String deliveryWarehouse) {
        this.deliveryWarehouse = deliveryWarehouse;
    }

    public String getDeliveryWarehouseAddr() {
        return deliveryWarehouseAddr;
    }

    public void setDeliveryWarehouseAddr(String deliveryWarehouseAddr) {
        this.deliveryWarehouseAddr = deliveryWarehouseAddr;
    }

    public String getArriveWarehouseAddr() {
        return arriveWarehouseAddr;
    }

    public void setArriveWarehouseAddr(String arriveWarehouseAddr) {
        this.arriveWarehouseAddr = arriveWarehouseAddr;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getFreightSettleMethod() {
        return freightSettleMethod;
    }

    public void setFreightSettleMethod(String freightSettleMethod) {
        this.freightSettleMethod = freightSettleMethod;
    }

    public BigDecimal getFreightUnit() {
        return freightUnit;
    }

    public void setFreightUnit(BigDecimal freightUnit) {
        this.freightUnit = freightUnit;
    }

    public BigDecimal getMileage() {
        return mileage;
    }

    public void setMileage(BigDecimal mileage) {
        this.mileage = mileage;
    }

    public BigDecimal getTransportUnit() {
        return transportUnit;
    }

    public void setTransportUnit(BigDecimal transportUnit) {
        this.transportUnit = transportUnit;
    }

    public BigDecimal getTransportCost() {
        return transportCost;
    }

    public void setTransportCost(BigDecimal transportCost) {
        this.transportCost = transportCost;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStandardPrice() {
        return standardPrice;
    }

    public void setStandardPrice(String standardPrice) {
        this.standardPrice = standardPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getArriveWarehouse() {
        return arriveWarehouse;
    }

    public void setArriveWarehouse(String arriveWarehouse) {
        this.arriveWarehouse = arriveWarehouse;
    }

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }

    public String getDeliveryConfirmCode() {
        return deliveryConfirmCode;
    }

    public void setDeliveryConfirmCode(String deliveryConfirmCode) {
        this.deliveryConfirmCode = deliveryConfirmCode;
    }

    public List<Long> getDeliveryIds() {
        return deliveryIds;
    }

    public void setDeliveryIds(List<Long> deliveryIds) {
        this.deliveryIds = deliveryIds;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPurchaserAuditId() {
        return purchaserAuditId;
    }

    public void setPurchaserAuditId(String purchaserAuditId) {
        this.purchaserAuditId = purchaserAuditId;
    }

    public String getPurchaserAuditName() {
        return purchaserAuditName;
    }

    public void setPurchaserAuditName(String purchaserAuditName) {
        this.purchaserAuditName = purchaserAuditName;
    }

    public Date getPurchaserAuditTime() {
        return purchaserAuditTime;
    }

    public void setPurchaserAuditTime(Date purchaserAuditTime) {
        this.purchaserAuditTime = purchaserAuditTime;
    }

    public String getSupplierConfirmId() {
        return supplierConfirmId;
    }

    public void setSupplierConfirmId(String supplierConfirmId) {
        this.supplierConfirmId = supplierConfirmId;
    }

    public String getSupplierConfirmName() {
        return supplierConfirmName;
    }

    public void setSupplierConfirmName(String supplierConfirmName) {
        this.supplierConfirmName = supplierConfirmName;
    }

    public Date getSupplierConfirmTime() {
        return supplierConfirmTime;
    }

    public void setSupplierConfirmTime(Date supplierConfirmTime) {
        this.supplierConfirmTime = supplierConfirmTime;
    }

    public Long getBrandEpId() {
        return brandEpId;
    }

    public void setBrandEpId(Long brandEpId) {
        this.brandEpId = brandEpId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Long getVerificationId() {
        return verificationId;
    }

    public void setVerificationId(Long verificationId) {
        this.verificationId = verificationId;
    }

    public Long getPaymentRequestDetailId() {
        return paymentRequestDetailId;
    }

    public void setPaymentRequestDetailId(Long paymentRequestDetailId) {
        this.paymentRequestDetailId = paymentRequestDetailId;
    }

    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    public String getLgcsAreaName() {
        return lgcsAreaName;
    }

    public void setLgcsAreaName(String lgcsAreaName) {
        this.lgcsAreaName = lgcsAreaName;
    }
}
