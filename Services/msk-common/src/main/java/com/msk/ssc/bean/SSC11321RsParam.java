package com.msk.ssc.bean;

import com.hoperun.core.bean.BasePageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by yang_yang
 */
@ApiModel(value = "SSC11321RsParam", description = "核销单的查询参数")
public class SSC11321RsParam extends BasePageParam {
    @ApiModelProperty(value = "合同核销单ID")
    private String verificationId;

    @ApiModelProperty(value = "合同核销单号")
    private String verificationCode;

    @ApiModelProperty(value = "合同ID")
    private String contractId;

    @ApiModelProperty(value = "合同编号")
    private String contractCode;

    @ApiModelProperty(value = "合同名称")
    private String contractName;

    @ApiModelProperty(value = "合同状态集合")
    private String[] contractStatusArr;

    @ApiModelProperty(value = "生产商ID")
    private String supplierId;

    @ApiModelProperty(value = "生产商")
    private String supplierName;

    @ApiModelProperty(value = "供应商编码")
    private String supplierCode;

    @ApiModelProperty(value = "采购商ID")
    private String purchaserId;

    @ApiModelProperty(value = "采购商")
    private String purchaserName;

    @ApiModelProperty(value = "采购商编码")
    private String purchaserCode;

    @ApiModelProperty(value = "核销金额")
    private String verificationAmount;

    @ApiModelProperty(value = "核销方法")
    private String verificationMethord;

    @ApiModelProperty(value = "核销日期")
    private String verificationDate;

    @ApiModelProperty(value = "核销状态")
    private String status;

    @ApiModelProperty(value = "核销状态集合")
    private String[] statusArr;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "核销处理办法")
    private String verificationType;

    @ApiModelProperty(value = "核销人姓名")
    private String chargerName;

    @ApiModelProperty(value = "核销人ID")
    private String chargerId;

    @ApiModelProperty(value = "核销单审核状态")
    private List<Integer> auditStatuses;

    @ApiModelProperty(value = "是否生成付款申请")
    private String isPaymentRequest;

    @ApiModelProperty(value = "支付类型")
    private Integer paymentType;

    @ApiModelProperty(value = "核销单编号 文本框id")
    private String verificationCodeId;


    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getVerificationId() {
        return verificationId;
    }

    public void setVerificationId(String verificationId) {
        this.verificationId = verificationId;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
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

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
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

    public String getPurchaserId() {
        return purchaserId;
    }

    public void setPurchaserId(String purchaserId) {
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

    public String getVerificationAmount() {
        return verificationAmount;
    }

    public void setVerificationAmount(String verificationAmount) {
        this.verificationAmount = verificationAmount;
    }

    public String getVerificationMethord() {
        return verificationMethord;
    }

    public void setVerificationMethord(String verificationMethord) {
        this.verificationMethord = verificationMethord;
    }

    public String getVerificationDate() {
        return verificationDate;
    }

    public void setVerificationDate(String verificationDate) {
        this.verificationDate = verificationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getVerificationType() {
        return verificationType;
    }

    public void setVerificationType(String verificationType) {
        this.verificationType = verificationType;
    }

    public String getChargerName() {
        return chargerName;
    }

    public void setChargerName(String chargerName) {
        this.chargerName = chargerName;
    }

    public String getChargerId() {
        return chargerId;
    }

    public void setChargerId(String chargerId) {
        this.chargerId = chargerId;
    }

    public String[] getContractStatusArr() {
        return contractStatusArr;
    }

    public void setContractStatusArr(String[] contractStatusArr) {
        this.contractStatusArr = contractStatusArr;
    }

    public String[] getStatusArr() {
        return statusArr;
    }

    public void setStatusArr(String[] statusArr) {
        this.statusArr = statusArr;
    }

    public List<Integer> getAuditStatuses() {
        return auditStatuses;
    }

    public void setAuditStatuses(List<Integer> auditStatuses) {
        this.auditStatuses = auditStatuses;
    }

    public String getIsPaymentRequest() {
        return isPaymentRequest;
    }

    public void setIsPaymentRequest(String isPaymentRequest) {
        this.isPaymentRequest = isPaymentRequest;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public String getVerificationCodeId() {
        return verificationCodeId;
    }

    public void setVerificationCodeId(String verificationCodeId) {
        this.verificationCodeId = verificationCodeId;
    }

}