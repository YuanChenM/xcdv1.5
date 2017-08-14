package com.msk.ssc.bean;

import com.hoperun.core.bean.BasePageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by tao_zhifa on 2016/6/29.
 */
@ApiModel(value = "SSC11303RsParam", description = "合同模块的查询参数")
public class SSC11303RsParam extends BasePageParam {
    @ApiModelProperty(value = "合同ID")
    private Long contractId;

    @ApiModelProperty(value = "合同编号")
    private String contractCode;

    @ApiModelProperty(value = "中标确认书ID")
    private Long bidId;

    @ApiModelProperty(value = "中标确认书编号")
    private String bidProjectNo;

    @ApiModelProperty(value = "合同名称")
    private String contractName;

    @ApiModelProperty(value = "合同类型")
    private String contractType;

    @ApiModelProperty(value = "生产商")
    private String supplierName;

    @ApiModelProperty(value = "合同生效日期")
    private String contractActDate;

    @ApiModelProperty(value = "合同状态")
    private String contractStatus;

    @ApiModelProperty(value = "应付金额")
    private java.math.BigDecimal payableAmount;

    @ApiModelProperty(value = "已付金额")
    private java.math.BigDecimal paidAmount;

    @ApiModelProperty(value = "尚余金额")
    private java.math.BigDecimal residualAmount;

    @ApiModelProperty(value = "生产商名称")
    private String purchaserName;

    @ApiModelProperty(value = "合同状态")
    private String contractStatusStr;

    @ApiModelProperty(value = "合同状态")
    private String[] contractStatusArr;

    @ApiModelProperty(value = "中标关联类型")
    private String[] bidRelationTypeArr;

    @ApiModelProperty(value = "判断是否存在付款申请标准")
    private String isPaymentRequest;


    public String getIsPaymentRequest() {
        return isPaymentRequest;
    }

    public void setIsPaymentRequest(String isPaymentRequest) {
        this.isPaymentRequest = isPaymentRequest;
    }

    public String getContractStatusStr() {
        return contractStatusStr;
    }

    public void setContractStatusStr(String contractStatusStr) {
        this.contractStatusStr = contractStatusStr;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public Long getBidId() {
        return bidId;
    }

    public void setBidId(Long bidId) {
        this.bidId = bidId;
    }

    public String getBidProjectNo() {
        return bidProjectNo;
    }

    public void setBidProjectNo(String bidProjectNo) {
        this.bidProjectNo = bidProjectNo;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getContractActDate() {
        return contractActDate;
    }

    public void setContractActDate(String contractActDate) {
        this.contractActDate = contractActDate;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    public BigDecimal getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(BigDecimal payableAmount) {
        this.payableAmount = payableAmount;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public BigDecimal getResidualAmount() {
        return residualAmount;
    }

    public void setResidualAmount(BigDecimal residualAmount) {
        this.residualAmount = residualAmount;
    }

    public String getPurchaserName() {
        return purchaserName;
    }

    public void setPurchaserName(String purchaserName) {
        this.purchaserName = purchaserName;
    }

    public String[] getContractStatusArr() {
        return contractStatusArr;
    }

    public void setContractStatusArr(String[] contractStatusArr) {
        this.contractStatusArr = contractStatusArr;
    }

    public String[] getBidRelationTypeArr() {
        return bidRelationTypeArr;
    }

    public void setBidRelationTypeArr(String[] bidRelationTypeArr) {
        this.bidRelationTypeArr = bidRelationTypeArr;
    }

}