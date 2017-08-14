package com.msk.ssc.bean;

import com.hoperun.core.utils.StringUtil;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by xia_xiaojie on 2016/9/5.
 */
public class SSC11322ProductValueBean {
    private static final long serialVersionUID = 1L;

    private Long differId;          //差异单ID
    private String differCode;      //差异单编号
    private String intoStoreId;     //入库单ID
    private String intoStoreCode;   //入库单编号
    private Long deliveryId;        //发货订单ID
    private String deliveryCode;    //发货订单编号

    private BigDecimal firstPaid;   //预付款按比例已支付金额
    private BigDecimal valuePaid;   //已付货款
    private BigDecimal valueNeed;   //应付货款
    private BigDecimal valueDiff;   //货款差额

    private String firstPaidStr;
    private String valuePaidStr;
    private String valueNeedStr;
    private String valueDiffStr;

    private BigDecimal totalFirstPaid;  //预付款总额
    private BigDecimal totalValuePaid;  //已付货款总额
    private BigDecimal totalValueNeed;  //应付货款总额
    private BigDecimal totalValueDiff;  //货款总差额

    private String totalFirstPaidStr;
    private String totalValuePaidStr;
    private String totalValueNeedStr;
    private String totalValueDiffStr;


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


    public Long getDifferId() {
        return differId;
    }

    public void setDifferId(Long differId) {
        this.differId = differId;
    }

    public String getDifferCode() {
        return differCode;
    }

    public void setDifferCode(String differCode) {
        this.differCode = differCode;
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

    public BigDecimal getFirstPaid() {
        return firstPaid;
    }

    public void setFirstPaid(BigDecimal firstPaid) {
        this.firstPaid = firstPaid;
    }

    public BigDecimal getValuePaid() {
        return valuePaid;
    }

    public void setValuePaid(BigDecimal valuePaid) {
        this.valuePaid = valuePaid;
    }

    public BigDecimal getValueNeed() {
        return valueNeed;
    }

    public void setValueNeed(BigDecimal valueNeed) {
        this.valueNeed = valueNeed;
    }

    public BigDecimal getValueDiff() {
        return valueDiff;
    }

    public void setValueDiff(BigDecimal valueDiff) {
        this.valueDiff = valueDiff;
    }

    public String getFirstPaidStr() {
        return this.formatMoney(this.firstPaid);
    }

    public void setFirstPaidStr(String firstPaidStr) {
        this.firstPaidStr = firstPaidStr;
    }

    public String getValuePaidStr() {
        return this.formatMoney(this.valuePaid);
    }

    public void setValuePaidStr(String valuePaidStr) {
        this.valuePaid = this.clearComma(valuePaidStr);
    }

    public String getValueNeedStr() {
        return this.formatMoney(this.valueNeed);
    }

    public void setValueNeedStr(String valueNeedStr) {
        this.valueNeed = this.clearComma(valueNeedStr);
    }

    public String getValueDiffStr() {
        return this.formatMoney(this.valueDiff);
    }

    public void setValueDiffStr(String valueDiffStr) {
        this.valueDiff = this.clearComma(valueDiffStr);
    }

    public BigDecimal getTotalFirstPaid() {
        return totalFirstPaid;
    }

    public void setTotalFirstPaid(BigDecimal totalFirstPaid) {
        this.totalFirstPaid = totalFirstPaid;
    }

    public BigDecimal getTotalValuePaid() {
        return totalValuePaid;
    }

    public void setTotalValuePaid(BigDecimal totalValuePaid) {
        this.totalValuePaid = totalValuePaid;
    }

    public BigDecimal getTotalValueNeed() {
        return totalValueNeed;
    }

    public void setTotalValueNeed(BigDecimal totalValueNeed) {
        this.totalValueNeed = totalValueNeed;
    }

    public BigDecimal getTotalValueDiff() {
        return totalValueDiff;
    }

    public void setTotalValueDiff(BigDecimal totalValueDiff) {
        this.totalValueDiff = totalValueDiff;
    }

    public String getTotalFirstPaidStr() {
        return this.formatMoney(this.totalFirstPaid);
    }

    public void setTotalFirstPaidStr(String totalFirstPaidStr) {
        this.totalFirstPaidStr = totalFirstPaidStr;
    }

    public String getTotalValuePaidStr() {
        return this.formatMoney(this.totalValuePaid);
    }

    public void setTotalValuePaidStr(String totalValuePaidStr) {
        this.totalValuePaid = this.clearComma(totalValuePaidStr);
    }

    public String getTotalValueNeedStr() {
        return this.formatMoney(this.totalValueNeed);
    }

    public void setTotalValueNeedStr(String totalValueNeedStr) {
        this.totalValueNeed = this.clearComma(totalValueNeedStr);
    }

    public String getTotalValueDiffStr() {
        return this.formatMoney(this.totalValueDiff);
    }

    public void setTotalValueDiffStr(String totalValueDiffStr) {
        this.totalValueDiff = this.clearComma(totalValueDiffStr);
    }

}
