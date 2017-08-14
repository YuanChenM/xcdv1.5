package com.msk.print.bean;

import com.msk.comm.bean.BaseBean;

import java.math.BigDecimal;


/**
 * OrderPrintAvailabilityBean
 *
 * @author sunjiaju
 * @version 1.0
 **/
public class OrderPrintAvailabilityBean extends BaseBean {
    /** 订单明细供货ID */
    private String orderDetailAvailabilityId;
    /** 供应商编码 */
    private String supplierCode;
    /** 供应商名称 */
    private String supplierName;
    /** 产品类型 */
    private String classesCode;
    /** 产品类型名称 */
    private String classesName;
    /** 产品品种 */
    private String breedCode;
    /** 产品品种名称 */
    private String breedName;
    /** 特征编码 */
    private String featureCode;
    /** 特征名称 */
    private String featureName;
    /** 产品包装编码 */
    private String normsCode;
    /** 产品包装名称 */
    private String normsName;
    /** 产品编码 */
    private String pdCode;
    /** 产品名称 */
    private String pdName;
    /** 产品等级 */
    private String pdLevel;
    /** 供应数量 */
    private BigDecimal suppQty;
    /** 发货数量 */
    private BigDecimal sendQty;
    /** 收货数量 */
    private BigDecimal receiveQty;
    /** 取消数量 */
    private BigDecimal cancelQty;
    /** 退货数量 */
    private BigDecimal returnQty;
    /** 拒收数量 */
    private BigDecimal rejectionQty;
    /** 单价 */
    private BigDecimal pdPrice;
    /** 金额 */
    private BigDecimal amount;
    /** 发货时间 */
    private String sendTime;
    /** 收货时间 */
    private String receivedTime;
    /** 状态 */
    private String status;

    public String getOrderDetailAvailabilityId() {
        return orderDetailAvailabilityId;
    }

    public void setOrderDetailAvailabilityId(String orderDetailAvailabilityId) {
        this.orderDetailAvailabilityId = orderDetailAvailabilityId;
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

    public String getPdLevel() {
        return pdLevel;
    }

    public void setPdLevel(String pdLevel) {
        this.pdLevel = pdLevel;
    }

    public BigDecimal getSuppQty() {
        return suppQty;
    }

    public void setSuppQty(BigDecimal suppQty) {
        this.suppQty = suppQty;
    }

    public BigDecimal getSendQty() {
        return sendQty;
    }

    public void setSendQty(BigDecimal sendQty) {
        this.sendQty = sendQty;
    }

    public BigDecimal getReceiveQty() {
        return receiveQty;
    }

    public void setReceiveQty(BigDecimal receiveQty) {
        this.receiveQty = receiveQty;
    }

    public BigDecimal getCancelQty() {
        return cancelQty;
    }

    public void setCancelQty(BigDecimal cancelQty) {
        this.cancelQty = cancelQty;
    }

    public BigDecimal getReturnQty() {
        return returnQty;
    }

    public void setReturnQty(BigDecimal returnQty) {
        this.returnQty = returnQty;
    }

    public BigDecimal getRejectionQty() {
        return rejectionQty;
    }

    public void setRejectionQty(BigDecimal rejectionQty) {
        this.rejectionQty = rejectionQty;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(String receivedTime) {
        this.receivedTime = receivedTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getPdPrice() {
        return pdPrice;
    }

    public void setPdPrice(BigDecimal pdPrice) {
        this.pdPrice = pdPrice;
    }
}
