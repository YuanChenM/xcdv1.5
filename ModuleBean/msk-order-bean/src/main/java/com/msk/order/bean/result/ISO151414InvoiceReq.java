package com.msk.order.bean.result;


import com.msk.common.bean.param.BaseParam;

import java.util.Date;

/**
 * Created by liu_tao2 on 2016/8/1.
 */
public class ISO151414InvoiceReq extends BaseParam {
    /** 发票类型,1:普通发票,2:增值税普通发票,3:增值税专用发票 */
    private Integer invoiceType;
    /** 发票抬头 */
    private String invoiceTitle;
    /** 发票内容 */
    private String invoiceContent;
    /** 收发票人手机号码 */
    private String invReceiverTel;
    /** 开票时间要求 */
    private Date invTimeReq;
    /** 收发票人邮箱 */
    private String invReceiverEmail;
    /** 收发票人地址 */
    private String invReceiverAddr;
    /** 发票要求 */
    private String invoiceReq;
    /** 增票单位名称 */
    private String vatInvComp;
    /** 增票纳税人识别码 */
    private String vatTaxpayer;
    /** 增票注册地址 */
    private String vatAddr;
    /** 增票注册电话 */
    private String vatTel;
    /** 增票开户银行 */
    private String vatBank;
    /** 增票银行账号 */
    private String vatAccount;

    public Integer getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public String getInvoiceContent() {
        return invoiceContent;
    }

    public void setInvoiceContent(String invoiceContent) {
        this.invoiceContent = invoiceContent;
    }

    public String getInvReceiverTel() {
        return invReceiverTel;
    }

    public void setInvReceiverTel(String invReceiverTel) {
        this.invReceiverTel = invReceiverTel;
    }

    public Date getInvTimeReq() {
        return invTimeReq;
    }

    public void setInvTimeReq(Date invTimeReq) {
        this.invTimeReq = invTimeReq;
    }

    public String getInvReceiverEmail() {
        return invReceiverEmail;
    }

    public void setInvReceiverEmail(String invReceiverEmail) {
        this.invReceiverEmail = invReceiverEmail;
    }

    public String getInvReceiverAddr() {
        return invReceiverAddr;
    }

    public void setInvReceiverAddr(String invReceiverAddr) {
        this.invReceiverAddr = invReceiverAddr;
    }

    public String getInvoiceReq() {
        return invoiceReq;
    }

    public void setInvoiceReq(String invoiceReq) {
        this.invoiceReq = invoiceReq;
    }

    public String getVatInvComp() {
        return vatInvComp;
    }

    public void setVatInvComp(String vatInvComp) {
        this.vatInvComp = vatInvComp;
    }

    public String getVatTaxpayer() {
        return vatTaxpayer;
    }

    public void setVatTaxpayer(String vatTaxpayer) {
        this.vatTaxpayer = vatTaxpayer;
    }

    public String getVatAddr() {
        return vatAddr;
    }

    public void setVatAddr(String vatAddr) {
        this.vatAddr = vatAddr;
    }

    public String getVatTel() {
        return vatTel;
    }

    public void setVatTel(String vatTel) {
        this.vatTel = vatTel;
    }

    public String getVatBank() {
        return vatBank;
    }

    public void setVatBank(String vatBank) {
        this.vatBank = vatBank;
    }

    public String getVatAccount() {
        return vatAccount;
    }

    public void setVatAccount(String vatAccount) {
        this.vatAccount = vatAccount;
    }
}
