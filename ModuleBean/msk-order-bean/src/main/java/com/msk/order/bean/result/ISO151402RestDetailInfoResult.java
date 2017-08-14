package com.msk.order.bean.result;

import com.msk.common.bean.result.BaseResult;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * ISO151402_打印查询订单详细信息PDF接口
 * Created by chu_jian on 2016/8/3.
 */
public class ISO151402RestDetailInfoResult implements Serializable {
    private Long subOrderId;
    private Long subOrderDetailId;
    private String supplierCode;
    private String supplierName;
    private String pdCode;
    private String pdName;
    private BigDecimal pdPrice;
    private String priceCycle ;
    private BigDecimal suppQty;
    private BigDecimal sendQty;
    private BigDecimal receiveQty;
    private BigDecimal cancelQty;
    private BigDecimal returnQty;
    private BigDecimal rejectionQty;
    private String detailStatus = "";
    private String proDate = "";
    private BigDecimal amount;
    private List<ISO151402RestAvailabilityInfoResult> availabilityInfo;


    public Long getSubOrderId() {
        return subOrderId;
    }

    public void setSubOrderId(Long subOrderId) {
        this.subOrderId = subOrderId;
    }

    public Long getSubOrderDetailId() {
        return subOrderDetailId;
    }

    public void setSubOrderDetailId(Long subOrderDetailId) {
        this.subOrderDetailId = subOrderDetailId;
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

    public BigDecimal getPdPrice() {
        return pdPrice;
    }

    public void setPdPrice(BigDecimal pdPrice) {
        this.pdPrice = pdPrice;
    }

    public String getPriceCycle() {
        return priceCycle;
    }

    public void setPriceCycle(String priceCycle) {
        this.priceCycle = priceCycle;
    }

    public BigDecimal getSuppQty() {
        return suppQty;
    }

    public void setSuppQty(BigDecimal suppQty) {
        this.suppQty = suppQty;
    }

    public String getProDate() {
        return proDate;
    }

    public void setProDate(String proDate) {
        this.proDate = proDate;
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

    public String getDetailStatus() {
        return detailStatus;
    }

    public void setDetailStatus(String detailStatus) {
        this.detailStatus = detailStatus;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public List<ISO151402RestAvailabilityInfoResult> getAvailabilityInfo() {
        return availabilityInfo;
    }

    public void setAvailabilityInfo(List<ISO151402RestAvailabilityInfoResult> availabilityInfo) {
        this.availabilityInfo = availabilityInfo;
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
}
