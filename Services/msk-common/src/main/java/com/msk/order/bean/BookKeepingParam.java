package com.msk.order.bean;


import com.hoperun.core.bean.BaseParam;

import java.math.BigDecimal;

/**
 * Created by zhang_xi on 2016/3/1.
 */
public class BookKeepingParam extends BaseParam {
    //查询编码
    public String searchCode;
    //是否结算（判断是否支付）
    public Integer isPayed;
    //支付流水号
    public String payCode;
    //支付金额
    private BigDecimal payAmount;
    //业务主体编码
    public String payeeCode;
    //业务副体编码
    public String payerCode;
    //业务主体角色
    public String payeeRole;
    //业务副体角色
    public String payerRole;
    //结算方式
    public String clearingForm;
    //查询类型
    public String searchType;
    //金额类型
    public Integer AmountType;

    //平台类型
    public Integer PlateFormType;
    /**
     * @return the searchCode
     */
    public String getSearchCode() {
        return searchCode;
    }
    /**
     * @param searchCode the searchCode to set
     */
    public void setSearchCode(String searchCode) {
        this.searchCode = searchCode;
    }
    /**
     * @return the isPayed
     */
    public Integer getIsPayed() {
        return isPayed;
    }
    /**
     * @param isPayed the isPayed to set
     */
    public void setIsPayed(Integer isPayed) {
        this.isPayed = isPayed;
    }
    /**
     * @return the payCode
     */
    public String getPayCode() {
        return payCode;
    }
    /**
     * @param payCode the payCode to set
     */
    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }
    /**
     * @return the payAmount
     */
    public BigDecimal getPayAmount() {
        return payAmount;
    }
    /**
     * @param payAmount the payAmount to set
     */
    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }
    /**
     * @return the payeeCode
     */
    public String getPayeeCode() {
        return payeeCode;
    }
    /**
     * @param payeeCode the payeeCode to set
     */
    public void setPayeeCode(String payeeCode) {
        this.payeeCode = payeeCode;
    }
    /**
     * @return the payerCode
     */
    public String getPayerCode() {
        return payerCode;
    }
    /**
     * @param payerCode the payerCode to set
     */
    public void setPayerCode(String payerCode) {
        this.payerCode = payerCode;
    }
    /**
     * @return the payeeRole
     */
    public String getPayeeRole() {
        return payeeRole;
    }
    /**
     * @param payeeRole the payeeRole to set
     */
    public void setPayeeRole(String payeeRole) {
        this.payeeRole = payeeRole;
    }
    /**
     * @return the payerRole
     */
    public String getPayerRole() {
        return payerRole;
    }
    /**
     * @param payerRole the payerRole to set
     */
    public void setPayerRole(String payerRole) {
        this.payerRole = payerRole;
    }
    /**
     * @return the clearingForm
     */
    public String getClearingForm() {
        return clearingForm;
    }
    /**
     * @param clearingForm the clearingForm to set
     */
    public void setClearingForm(String clearingForm) {
        this.clearingForm = clearingForm;
    }
    /**
     * @return the searchType
     */
    public String getSearchType() {
        return searchType;
    }
    /**
     * @param searchType the searchType to set
     */
    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }
    /**
     * @return the amountType
     */
    public Integer getAmountType() {
        return AmountType;
    }
    /**
     * @param amountType the amountType to set
     */
    public void setAmountType(Integer amountType) {
        AmountType = amountType;
    }

    /**
     * @return the PlateFormType
     */
    public Integer getPlateFormType() {
        return PlateFormType;
    }

    /**
     * @param PlateFormType the PlateFormType to set
     */
    public void setPlateFormType(Integer plateFormType) {
        PlateFormType = plateFormType;
    }
}
