package com.msk.ssc.bean;

import com.hoperun.core.utils.StringUtil;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by xia_xiaojie on 2016/9/5.
 */
public class SSC11322TransportCostBean {
    private static final long serialVersionUID = 1L;

    private Long deliveryId;            //发货订单ID
    private String deliveryCode;        //发货订单编号
    private String intoStoreId;         //入库单ID
    private String intoStoreCode;       //入库单编号
    private Long verificationDetailId;  //核销单明细ID

    private BigDecimal deliveryWeight;  //发货重量
    private BigDecimal receiveWeight;   //入库重量
    private BigDecimal weightDiff;      //重量差

    private BigDecimal freightPaid; //已付运费
    private BigDecimal freightNeed; //应付运费
    private BigDecimal freightDeal; //实需运费
    private BigDecimal freightDiff; //运费差

    private String freightPaidStr;
    private String freightNeedStr;
    private String freightDealStr;
    private String freightDiffStr;

    private BigDecimal totalDeliveryWeight; //发货总重量
    private BigDecimal totalReceiveWeight;  //入库总重量
    private BigDecimal totalWeightDiff;     //重量总差额

    private BigDecimal totalFreightPaid;    //已付运费总额
    private BigDecimal totalFreightNeed;    //应付运费总额
    private BigDecimal totalFreightDeal;    //实需运费总额
    private BigDecimal totalFreightDiff;    //运费总差额

    private String totalFreightPaidStr;
    private String totalFreightNeedStr;
    private String totalFreightDealStr;
    private String totalFreightDiffStr;


    private String formatMoney(BigDecimal money) {
        if (null == money) {
            money = BigDecimal.ZERO;
        }
        return new DecimalFormat("#,##0.00").format(money);
    }

    private BigDecimal clearComma(String moneyStr) {
        if (StringUtil.isEmpty(moneyStr)) {
            moneyStr = "0.00";
        }
        else {
            moneyStr = moneyStr.replaceAll(",", "");
        }
        return new BigDecimal(moneyStr);
    }


    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    public String getIntoStoreId() {
        return intoStoreId;
    }

    public void setIntoStoreId(String intoStoreId) {
        this.intoStoreId = intoStoreId;
    }

    public String getIntoStoreCode() {
        return intoStoreCode;
    }

    public void setIntoStoreCode(String intoStoreCode) {
        this.intoStoreCode = intoStoreCode;
    }

    public Long getVerificationDetailId() {
        return verificationDetailId;
    }

    public void setVerificationDetailId(Long verificationDetailId) {
        this.verificationDetailId = verificationDetailId;
    }

    public BigDecimal getDeliveryWeight() {
        return deliveryWeight;
    }

    public void setDeliveryWeight(BigDecimal deliveryWeight) {
        this.deliveryWeight = deliveryWeight;
    }

    public BigDecimal getReceiveWeight() {
        return receiveWeight;
    }

    public void setReceiveWeight(BigDecimal receiveWeight) {
        this.receiveWeight = receiveWeight;
    }

    public BigDecimal getWeightDiff() {
        return weightDiff;
    }

    public void setWeightDiff(BigDecimal weightDiff) {
        this.weightDiff = weightDiff;
    }

    public BigDecimal getFreightPaid() {
        return freightPaid;
    }

    public void setFreightPaid(BigDecimal freightPaid) {
        this.freightPaid = freightPaid;
    }

    public BigDecimal getFreightNeed() {
        return freightNeed;
    }

    public void setFreightNeed(BigDecimal freightNeed) {
        this.freightNeed = freightNeed;
    }

    public BigDecimal getFreightDeal() {
        return freightDeal;
    }

    public void setFreightDeal(BigDecimal freightDeal) {
        this.freightDeal = freightDeal;
    }

    public BigDecimal getFreightDiff() {
        return freightDiff;
    }

    public void setFreightDiff(BigDecimal freightDiff) {
        this.freightDiff = freightDiff;
    }

    public String getFreightPaidStr() {
        return this.formatMoney(this.freightPaid);
    }

    public void setFreightPaidStr(String freightPaidStr) {
        this.freightPaid = this.clearComma(freightPaidStr);
    }

    public String getFreightNeedStr() {
        return this.formatMoney(this.freightNeed);
    }

    public void setFreightNeedStr(String freightNeedStr) {
        this.freightNeed = this.clearComma(freightNeedStr);
    }

    public String getFreightDealStr() {
        return this.formatMoney(this.freightDeal);
    }

    public void setFreightDealStr(String freightDealStr) {
        this.freightDeal = this.clearComma(freightDealStr);
    }

    public String getFreightDiffStr() {
        return this.formatMoney(this.freightDiff);
    }

    public void setFreightDiffStr(String freightDiffStr) {
        this.freightDiff = this.clearComma(freightDiffStr);
    }

    public BigDecimal getTotalDeliveryWeight() {
        return totalDeliveryWeight;
    }

    public void setTotalDeliveryWeight(BigDecimal totalDeliveryWeight) {
        this.totalDeliveryWeight = totalDeliveryWeight;
    }

    public BigDecimal getTotalReceiveWeight() {
        return totalReceiveWeight;
    }

    public void setTotalReceiveWeight(BigDecimal totalReceiveWeight) {
        this.totalReceiveWeight = totalReceiveWeight;
    }

    public BigDecimal getTotalWeightDiff() {
        return totalWeightDiff;
    }

    public void setTotalWeightDiff(BigDecimal totalWeightDiff) {
        this.totalWeightDiff = totalWeightDiff;
    }

    public BigDecimal getTotalFreightPaid() {
        return totalFreightPaid;
    }

    public void setTotalFreightPaid(BigDecimal totalFreightPaid) {
        this.totalFreightPaid = totalFreightPaid;
    }

    public BigDecimal getTotalFreightNeed() {
        return totalFreightNeed;
    }

    public void setTotalFreightNeed(BigDecimal totalFreightNeed) {
        this.totalFreightNeed = totalFreightNeed;
    }

    public BigDecimal getTotalFreightDeal() {
        return totalFreightDeal;
    }

    public void setTotalFreightDeal(BigDecimal totalFreightDeal) {
        this.totalFreightDeal = totalFreightDeal;
    }

    public BigDecimal getTotalFreightDiff() {
        return totalFreightDiff;
    }

    public void setTotalFreightDiff(BigDecimal totalFreightDiff) {
        this.totalFreightDiff = totalFreightDiff;
    }

    public String getTotalFreightPaidStr() {
        return this.formatMoney(this.totalFreightPaid);
    }

    public void setTotalFreightPaidStr(String totalFreightPaidStr) {
        this.totalFreightPaid = this.clearComma(totalFreightPaidStr);
    }

    public String getTotalFreightNeedStr() {
        return this.formatMoney(this.totalFreightNeed);
    }

    public void setTotalFreightNeedStr(String totalFreightNeedStr) {
        this.totalFreightNeed = this.clearComma(totalFreightNeedStr);
    }

    public String getTotalFreightDealStr() {
        return this.formatMoney(this.totalFreightDeal);
    }

    public void setTotalFreightDealStr(String totalFreightDealStr) {
        this.totalFreightDeal = this.clearComma(totalFreightDealStr);
    }

    public String getTotalFreightDiffStr() {
        return this.formatMoney(this.totalFreightDiff);
    }

    public void setTotalFreightDiffStr(String totalFreightDiffStr) {
        this.totalFreightDiff = this.clearComma(totalFreightDiffStr);
    }

}
