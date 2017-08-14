/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表so_cp_invoice对应的SoCpInvoice。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SoCpInvoice extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** INVOICE_REQ_ID */
    private java.lang.Long invoiceReqId;
    /** ORDER_ID */
    private java.lang.Long orderId;
    /** ORDER_CODE */
    private java.lang.String orderCode;
    /** 1:普通发票,2:增值税普通发票,3:增值税专用发票 */
    private java.lang.Integer invoiceType;
    /** INVOICE_TITLE */
    private java.lang.String invoiceTitle;
    /** VAT_INV_COMP */
    private java.lang.String vatInvComp;
    /** VAT_TAXPAYER */
    private java.lang.String vatTaxpayer;
    /** VAT_ADDR */
    private java.lang.String vatAddr;
    /** VAT_TEL */
    private java.lang.String vatTel;
    /** VAT_BANK */
    private java.lang.String vatBank;
    /** VAT_ACCOUNT */
    private java.lang.String vatAccount;
    /** INV_RECEIVER_TEL */
    private java.lang.String invReceiverTel;
    /** INV_TIME_REQ */
    private java.util.Date invTimeReq;
    /** INV_RECEIVER_EMAIL */
    private java.lang.String invReceiverEmail;
    /** INVOICE_CONTENT */
    private java.lang.String invoiceContent;
    /** INV_RECEIVER_ADDR */
    private java.lang.String invReceiverAddr;
    /** INVOICE_REQ */
    private java.lang.String invoiceReq;
    /** 1:待开票2：部分开票3：已开票9：不开票 */
    private java.lang.Integer invoiceStatus;
    /** REMARK */
    private java.lang.String remark;
    /** REMARK2 */
    private java.lang.String remark2;
    /** REMARK3 */
    private java.lang.String remark3;
    /** INV_TIME */
    private java.util.Date invTime;
    /**
     * <p>默认构造函数。</p>
     */
    public SoCpInvoice() {

    }

    /**
     * <p>INVOICE_REQ_ID。</p>
     *
     * @return the INVOICE_REQ_ID
     */
    public java.lang.Long getInvoiceReqId() {
        return invoiceReqId;
    }

    /**
     * <p>INVOICE_REQ_ID。</p>
     *
     * @param invoiceReqId INVOICE_REQ_ID。
     */
    public void setInvoiceReqId(java.lang.Long invoiceReqId) {
        this.invoiceReqId = invoiceReqId;
    }

    /**
     * <p>ORDER_ID。</p>
     *
     * @return the ORDER_ID
     */
    public java.lang.Long getOrderId() {
        return orderId;
    }

    /**
     * <p>ORDER_ID。</p>
     *
     * @param orderId ORDER_ID。
     */
    public void setOrderId(java.lang.Long orderId) {
        this.orderId = orderId;
    }

    /**
     * <p>ORDER_CODE。</p>
     *
     * @return the ORDER_CODE
     */
    public java.lang.String getOrderCode() {
        return orderCode;
    }

    /**
     * <p>ORDER_CODE。</p>
     *
     * @param orderCode ORDER_CODE。
     */
    public void setOrderCode(java.lang.String orderCode) {
        this.orderCode = orderCode;
    }

    /**
     * <p>1:普通发票,2:增值税普通发票,3:增值税专用发票。</p>
     *
     * @return the 1:普通发票,2:增值税普通发票,3:增值税专用发票
     */
    public java.lang.Integer getInvoiceType() {
        return invoiceType;
    }

    /**
     * <p>1:普通发票,2:增值税普通发票,3:增值税专用发票。</p>
     *
     * @param invoiceType 1:普通发票,2:增值税普通发票,3:增值税专用发票。
     */
    public void setInvoiceType(java.lang.Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    /**
     * <p>INVOICE_TITLE。</p>
     *
     * @return the INVOICE_TITLE
     */
    public java.lang.String getInvoiceTitle() {
        return invoiceTitle;
    }

    /**
     * <p>INVOICE_TITLE。</p>
     *
     * @param invoiceTitle INVOICE_TITLE。
     */
    public void setInvoiceTitle(java.lang.String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    /**
     * <p>VAT_INV_COMP。</p>
     *
     * @return the VAT_INV_COMP
     */
    public java.lang.String getVatInvComp() {
        return vatInvComp;
    }

    /**
     * <p>VAT_INV_COMP。</p>
     *
     * @param vatInvComp VAT_INV_COMP。
     */
    public void setVatInvComp(java.lang.String vatInvComp) {
        this.vatInvComp = vatInvComp;
    }

    /**
     * <p>VAT_TAXPAYER。</p>
     *
     * @return the VAT_TAXPAYER
     */
    public java.lang.String getVatTaxpayer() {
        return vatTaxpayer;
    }

    /**
     * <p>VAT_TAXPAYER。</p>
     *
     * @param vatTaxpayer VAT_TAXPAYER。
     */
    public void setVatTaxpayer(java.lang.String vatTaxpayer) {
        this.vatTaxpayer = vatTaxpayer;
    }

    /**
     * <p>VAT_ADDR。</p>
     *
     * @return the VAT_ADDR
     */
    public java.lang.String getVatAddr() {
        return vatAddr;
    }

    /**
     * <p>VAT_ADDR。</p>
     *
     * @param vatAddr VAT_ADDR。
     */
    public void setVatAddr(java.lang.String vatAddr) {
        this.vatAddr = vatAddr;
    }

    /**
     * <p>VAT_TEL。</p>
     *
     * @return the VAT_TEL
     */
    public java.lang.String getVatTel() {
        return vatTel;
    }

    /**
     * <p>VAT_TEL。</p>
     *
     * @param vatTel VAT_TEL。
     */
    public void setVatTel(java.lang.String vatTel) {
        this.vatTel = vatTel;
    }

    /**
     * <p>VAT_BANK。</p>
     *
     * @return the VAT_BANK
     */
    public java.lang.String getVatBank() {
        return vatBank;
    }

    /**
     * <p>VAT_BANK。</p>
     *
     * @param vatBank VAT_BANK。
     */
    public void setVatBank(java.lang.String vatBank) {
        this.vatBank = vatBank;
    }

    /**
     * <p>VAT_ACCOUNT。</p>
     *
     * @return the VAT_ACCOUNT
     */
    public java.lang.String getVatAccount() {
        return vatAccount;
    }

    /**
     * <p>VAT_ACCOUNT。</p>
     *
     * @param vatAccount VAT_ACCOUNT。
     */
    public void setVatAccount(java.lang.String vatAccount) {
        this.vatAccount = vatAccount;
    }

    /**
     * <p>INV_RECEIVER_TEL。</p>
     *
     * @return the INV_RECEIVER_TEL
     */
    public java.lang.String getInvReceiverTel() {
        return invReceiverTel;
    }

    /**
     * <p>INV_RECEIVER_TEL。</p>
     *
     * @param invReceiverTel INV_RECEIVER_TEL。
     */
    public void setInvReceiverTel(java.lang.String invReceiverTel) {
        this.invReceiverTel = invReceiverTel;
    }

    /**
     * <p>INV_TIME_REQ。</p>
     *
     * @return the INV_TIME_REQ
     */
    public java.util.Date getInvTimeReq() {
        return invTimeReq;
    }

    /**
     * <p>INV_TIME_REQ。</p>
     *
     * @param invTimeReq INV_TIME_REQ。
     */
    public void setInvTimeReq(java.util.Date invTimeReq) {
        this.invTimeReq = invTimeReq;
    }

    /**
     * <p>INV_RECEIVER_EMAIL。</p>
     *
     * @return the INV_RECEIVER_EMAIL
     */
    public java.lang.String getInvReceiverEmail() {
        return invReceiverEmail;
    }

    /**
     * <p>INV_RECEIVER_EMAIL。</p>
     *
     * @param invReceiverEmail INV_RECEIVER_EMAIL。
     */
    public void setInvReceiverEmail(java.lang.String invReceiverEmail) {
        this.invReceiverEmail = invReceiverEmail;
    }

    /**
     * <p>INVOICE_CONTENT。</p>
     *
     * @return the INVOICE_CONTENT
     */
    public java.lang.String getInvoiceContent() {
        return invoiceContent;
    }

    /**
     * <p>INVOICE_CONTENT。</p>
     *
     * @param invoiceContent INVOICE_CONTENT。
     */
    public void setInvoiceContent(java.lang.String invoiceContent) {
        this.invoiceContent = invoiceContent;
    }

    /**
     * <p>INV_RECEIVER_ADDR。</p>
     *
     * @return the INV_RECEIVER_ADDR
     */
    public java.lang.String getInvReceiverAddr() {
        return invReceiverAddr;
    }

    /**
     * <p>INV_RECEIVER_ADDR。</p>
     *
     * @param invReceiverAddr INV_RECEIVER_ADDR。
     */
    public void setInvReceiverAddr(java.lang.String invReceiverAddr) {
        this.invReceiverAddr = invReceiverAddr;
    }

    /**
     * <p>INVOICE_REQ。</p>
     *
     * @return the INVOICE_REQ
     */
    public java.lang.String getInvoiceReq() {
        return invoiceReq;
    }

    /**
     * <p>INVOICE_REQ。</p>
     *
     * @param invoiceReq INVOICE_REQ。
     */
    public void setInvoiceReq(java.lang.String invoiceReq) {
        this.invoiceReq = invoiceReq;
    }

    /**
     * <p>1:待开票2：部分开票3：已开票9：不开票。</p>
     *
     * @return the 1:待开票2：部分开票3：已开票9：不开票
     */
    public java.lang.Integer getInvoiceStatus() {
        return invoiceStatus;
    }

    /**
     * <p>1:待开票2：部分开票3：已开票9：不开票。</p>
     *
     * @param invoiceStatus 1:待开票2：部分开票3：已开票9：不开票。
     */
    public void setInvoiceStatus(java.lang.Integer invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    /**
     * <p>REMARK。</p>
     *
     * @return the REMARK
     */
    public java.lang.String getRemark() {
        return remark;
    }

    /**
     * <p>REMARK。</p>
     *
     * @param remark REMARK。
     */
    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }

    /**
     * <p>REMARK2。</p>
     *
     * @return the REMARK2
     */
    public java.lang.String getRemark2() {
        return remark2;
    }

    /**
     * <p>REMARK2。</p>
     *
     * @param remark2 REMARK2。
     */
    public void setRemark2(java.lang.String remark2) {
        this.remark2 = remark2;
    }

    /**
     * <p>REMARK3。</p>
     *
     * @return the REMARK3
     */
    public java.lang.String getRemark3() {
        return remark3;
    }

    /**
     * <p>REMARK3。</p>
     *
     * @param remark3 REMARK3。
     */
    public void setRemark3(java.lang.String remark3) {
        this.remark3 = remark3;
    }

    /**
     * <p>INV_TIME。</p>
     *
     * @return the INV_TIME
     */
    public java.util.Date getInvTime() {
        return invTime;
    }

    /**
     * <p>INV_TIME。</p>
     *
     * @param invTime INV_TIME。
     */
    public void setInvTime(java.util.Date invTime) {
        this.invTime = invTime;
    }

}
