package com.msk.ssc.bean;

import com.msk.core.entity.SscVerificationForContract;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by yang_yang
 */
@ApiModel(value = "SSC11321RsBean", description = "SscVerificationForContract实体的封装对象")
public class SSC11321RsBean extends SscVerificationForContract {
    @ApiModelProperty(value = "核销单明细")
    private List<SSC11322Bean> verificationDetails;

    @ApiModelProperty(value = "导航")
    private String navigation;

    @ApiModelProperty(value = "核销日期 yyyy/MM/dd 格式")
    private String verificationDateStr;

    @ApiModelProperty(value = "合同状态")
    private String contractStatus;

    @ApiModelProperty(value = "合同生效日期")
    private String contractActDate;

    @ApiModelProperty(value = "资金池一览页面跳转用Id")
    private String paymentId;

    @ApiModelProperty(value = "资金池详细页面跳转用Id")
    private Long paymentDetailId;


    @ApiModelProperty(value = "核销金额")
    private String verificationAmountStr;

    @ApiModelProperty(value = "合同总额")
    private BigDecimal contractAmount;
    @ApiModelProperty(value = "合同实际应付款")
    private BigDecimal contractAmountDeal;
    @ApiModelProperty(value = "合同实际已付款")
    private BigDecimal contractAmountPaid;

    @ApiModelProperty(value = "合同总额")
    private String contractAmountStr;
    @ApiModelProperty(value = "合同实际应付款")
    private String contractAmountDealStr;
    @ApiModelProperty(value = "合同实际已付款")
    private String contractAmountPaidStr;

    @ApiModelProperty(value = "应付首付款")
    private BigDecimal firstAmountNeed;
    @ApiModelProperty(value = "已付首付款")
    private BigDecimal firstAmountPaid;

    @ApiModelProperty(value = "应付首付款")
    private String firstAmountNeedStr;
    @ApiModelProperty(value = "已付首付款")
    private String firstAmountPaidStr;

    @ApiModelProperty(value = "应付货款")
    private BigDecimal totalValueNeed;
    @ApiModelProperty(value = "已付货款")
    private BigDecimal totalValuePaid;
    @ApiModelProperty(value = "货款总差额")
    private BigDecimal totalValueDiff;

    @ApiModelProperty(value = "应付货款")
    private String totalValueNeedStr;
    @ApiModelProperty(value = "已付货款")
    private String totalValuePaidStr;
    @ApiModelProperty(value = "货款总差额")
    private String totalValueDiffStr;

    @ApiModelProperty(value = "实需付运费")
    private BigDecimal totalFreightDeal;
    @ApiModelProperty(value = "已付运费")
    private BigDecimal totalFreightPaid;
    @ApiModelProperty(value = "运费总差额")
    private BigDecimal totalFreightDiff;

    @ApiModelProperty(value = "实需付运费")
    private String totalFreightDealStr;
    @ApiModelProperty(value = "已付运费")
    private String totalFreightPaidStr;
    @ApiModelProperty(value = "运费总差额")
    private String totalFreightDiffStr;


    private String formatMoney(BigDecimal money) {
        if (null == money) {
            money = BigDecimal.ZERO;
        }
        return new DecimalFormat("#,##0.00").format(money);
    }


    public List<SSC11322Bean> getVerificationDetails() {
        return verificationDetails;
    }

    public void setVerificationDetails(List<SSC11322Bean> verificationDetails) {
        this.verificationDetails = verificationDetails;
    }

    public String getNavigation() {
        return navigation;
    }

    public void setNavigation(String navigation) {
        this.navigation = navigation;
    }

    public String getVerificationDateStr() {
        return verificationDateStr;
    }

    public void setVerificationDateStr(String verificationDateStr) {
        this.verificationDateStr = verificationDateStr;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    public String getContractActDate() {
        return contractActDate;
    }

    public void setContractActDate(String contractActDate) {
        this.contractActDate = contractActDate;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Long getPaymentDetailId() {
        return paymentDetailId;
    }

    public void setPaymentDetailId(Long paymentDetailId) {
        this.paymentDetailId = paymentDetailId;
    }

    public String getVerificationAmountStr() {
        return this.formatMoney(super.getVerificationAmount());
    }

    public void setVerificationAmountStr(String verificationAmountStr) {
        this.verificationAmountStr = verificationAmountStr;
    }

    public BigDecimal getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(BigDecimal contractAmount) {
        this.contractAmount = contractAmount;
    }

    public BigDecimal getContractAmountDeal() {
        return contractAmountDeal;
    }

    public void setContractAmountDeal(BigDecimal contractAmountDeal) {
        this.contractAmountDeal = contractAmountDeal;
    }

    public BigDecimal getContractAmountPaid() {
        return contractAmountPaid;
    }

    public void setContractAmountPaid(BigDecimal contractAmountPaid) {
        this.contractAmountPaid = contractAmountPaid;
    }

    public String getContractAmountStr() {
        return this.formatMoney(this.contractAmount);
    }

    public void setContractAmountStr(String contractAmountStr) {
        this.contractAmountStr = contractAmountStr;
    }

    public String getContractAmountDealStr() {
        return this.formatMoney(this.contractAmountDeal);
    }

    public void setContractAmountDealStr(String contractAmountDealStr) {
        this.contractAmountDealStr = contractAmountDealStr;
    }

    public String getContractAmountPaidStr() {
        return this.formatMoney(this.contractAmountPaid);
    }

    public void setContractAmountPaidStr(String contractAmountPaidStr) {
        this.contractAmountPaidStr = contractAmountPaidStr;
    }

    public BigDecimal getFirstAmountNeed() {
        return firstAmountNeed;
    }

    public void setFirstAmountNeed(BigDecimal firstAmountNeed) {
        this.firstAmountNeed = firstAmountNeed;
    }

    public BigDecimal getFirstAmountPaid() {
        return firstAmountPaid;
    }

    public void setFirstAmountPaid(BigDecimal firstAmountPaid) {
        this.firstAmountPaid = firstAmountPaid;
    }

    public String getFirstAmountNeedStr() {
        return this.formatMoney(this.firstAmountNeed);
    }

    public void setFirstAmountNeedStr(String firstAmountNeedStr) {
        this.firstAmountNeedStr = firstAmountNeedStr;
    }

    public String getFirstAmountPaidStr() {
        return this.formatMoney(this.firstAmountPaid);
    }

    public void setFirstAmountPaidStr(String firstAmountPaidStr) {
        this.firstAmountPaidStr = firstAmountPaidStr;
    }

    public BigDecimal getTotalValueNeed() {
        return totalValueNeed;
    }

    public void setTotalValueNeed(BigDecimal totalValueNeed) {
        this.totalValueNeed = totalValueNeed;
    }

    public BigDecimal getTotalValuePaid() {
        return totalValuePaid;
    }

    public void setTotalValuePaid(BigDecimal totalValuePaid) {
        this.totalValuePaid = totalValuePaid;
    }

    public BigDecimal getTotalValueDiff() {
        return totalValueDiff;
    }

    public void setTotalValueDiff(BigDecimal totalValueDiff) {
        this.totalValueDiff = totalValueDiff;
    }

    public String getTotalValueNeedStr() {
        return this.formatMoney(this.totalValueNeed);
    }

    public void setTotalValueNeedStr(String totalValueNeedStr) {
        this.totalValueNeedStr = totalValueNeedStr;
    }

    public String getTotalValuePaidStr() {
        return this.formatMoney(this.totalValuePaid);
    }

    public void setTotalValuePaidStr(String totalValuePaidStr) {
        this.totalValuePaidStr = totalValuePaidStr;
    }

    public String getTotalValueDiffStr() {
        return this.formatMoney(this.totalValueDiff);
    }

    public void setTotalValueDiffStr(String totalValueDiffStr) {
        this.totalValueDiffStr = totalValueDiffStr;
    }

    public BigDecimal getTotalFreightDeal() {
        return totalFreightDeal;
    }

    public void setTotalFreightDeal(BigDecimal totalFreightDeal) {
        this.totalFreightDeal = totalFreightDeal;
    }

    public BigDecimal getTotalFreightPaid() {
        return totalFreightPaid;
    }

    public void setTotalFreightPaid(BigDecimal totalFreightPaid) {
        this.totalFreightPaid = totalFreightPaid;
    }

    public BigDecimal getTotalFreightDiff() {
        return totalFreightDiff;
    }

    public void setTotalFreightDiff(BigDecimal totalFreightDiff) {
        this.totalFreightDiff = totalFreightDiff;
    }

    public String getTotalFreightDealStr() {
        return this.formatMoney(this.totalFreightDeal);
    }

    public void setTotalFreightDealStr(String totalFreightDealStr) {
        this.totalFreightDealStr = totalFreightDealStr;
    }

    public String getTotalFreightPaidStr() {
        return this.formatMoney(this.totalFreightPaid);
    }

    public void setTotalFreightPaidStr(String totalFreightPaidStr) {
        this.totalFreightPaidStr = totalFreightPaidStr;
    }

    public String getTotalFreightDiffStr() {
        return this.formatMoney(this.totalFreightDiff);
    }

    public void setTotalFreightDiffStr(String totalFreightDiffStr) {
        this.totalFreightDiffStr = totalFreightDiffStr;
    }

}