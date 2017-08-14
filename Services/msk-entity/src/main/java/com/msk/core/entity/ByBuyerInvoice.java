/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表by_buyer_invoice对应的ByBuyerInvoice</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class ByBuyerInvoice extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** INVOICE_ID */
    private Long invoiceId;
    /** BUYER_ID */
    private String buyerId;
    /** INVOICE_TYPE */
    private Integer invoiceType;
    /** INVOICE_TITLE */
    private String invoiceTitle;
    /** VAT_INV_COM */
    private String vatInvCom;
    /** VAT_TAXPAYER */
    private String vatTaxpayer;
    /** VAT_ADDR */
    private String vatAddr;
    /** VAT_TEL */
    private String vatTel;
    /** VAT_BANK */
    private String vatBank;
    /** VAT_ACCOUNT */
    private String vatAccount;
    /** INV_RECEIVER_NAME */
    private String invReceiverName;
    /** INV_RECEIVER_TEL */
    private String invReceiverTel;
    /** INV_RECEIVER_EMAIL */
    private String invReceiverEmail;
    /** INV_RECEIVER_PROVINCE */
    private String invReceiverProvince;
    /** INV_RECEIVER_CITY */
    private String invReceiverCity;
    /** INV_RECEIVER_DISTRICT */
    private String invReceiverDistrict;
    /** INV_RECEIVER_ADDR */
    private String invReceiverAddr;
    /** REMARK1 */
    private String remark1;
    /** REMARK2 */
    private String remark2;
    /** REMARK3 */
    private String remark3;
    /**
     * <p>默认构造函数</p>
     */
    public ByBuyerInvoice() {

    }

    /**
     * <p>INVOICE_ID</p>
     *
     * @return the INVOICE_ID
     */
    public Long getInvoiceId() {
        return invoiceId;
    }

    /**
     * <p>INVOICE_ID</p>
     *
     * @param invoiceId INVOICE_ID
     */
    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    /**
     * <p>BUYER_ID</p>
     *
     * @return the BUYER_ID
     */
    public String getBuyerId() {
        return buyerId;
    }

    /**
     * <p>BUYER_ID</p>
     *
     * @param buyerId BUYER_ID
     */
    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    /**
     * <p>INVOICE_TYPE</p>
     *
     * @return the INVOICE_TYPE
     */
    public Integer getInvoiceType() {
        return invoiceType;
    }

    /**
     * <p>INVOICE_TYPE</p>
     *
     * @param invoiceType INVOICE_TYPE
     */
    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    /**
     * <p>INVOICE_TITLE</p>
     *
     * @return the INVOICE_TITLE
     */
    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    /**
     * <p>INVOICE_TITLE</p>
     *
     * @param invoiceTitle INVOICE_TITLE
     */
    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    /**
     * <p>VAT_INV_COM</p>
     *
     * @return the VAT_INV_COM
     */
    public String getVatInvCom() {
        return vatInvCom;
    }

    /**
     * <p>VAT_INV_COM</p>
     *
     * @param vatInvCom VAT_INV_COM
     */
    public void setVatInvCom(String vatInvCom) {
        this.vatInvCom = vatInvCom;
    }

    /**
     * <p>VAT_TAXPAYER</p>
     *
     * @return the VAT_TAXPAYER
     */
    public String getVatTaxpayer() {
        return vatTaxpayer;
    }

    /**
     * <p>VAT_TAXPAYER</p>
     *
     * @param vatTaxpayer VAT_TAXPAYER
     */
    public void setVatTaxpayer(String vatTaxpayer) {
        this.vatTaxpayer = vatTaxpayer;
    }

    /**
     * <p>VAT_ADDR</p>
     *
     * @return the VAT_ADDR
     */
    public String getVatAddr() {
        return vatAddr;
    }

    /**
     * <p>VAT_ADDR</p>
     *
     * @param vatAddr VAT_ADDR
     */
    public void setVatAddr(String vatAddr) {
        this.vatAddr = vatAddr;
    }

    /**
     * <p>VAT_TEL</p>
     *
     * @return the VAT_TEL
     */
    public String getVatTel() {
        return vatTel;
    }

    /**
     * <p>VAT_TEL</p>
     *
     * @param vatTel VAT_TEL
     */
    public void setVatTel(String vatTel) {
        this.vatTel = vatTel;
    }

    /**
     * <p>VAT_BANK</p>
     *
     * @return the VAT_BANK
     */
    public String getVatBank() {
        return vatBank;
    }

    /**
     * <p>VAT_BANK</p>
     *
     * @param vatBank VAT_BANK
     */
    public void setVatBank(String vatBank) {
        this.vatBank = vatBank;
    }

    /**
     * <p>VAT_ACCOUNT</p>
     *
     * @return the VAT_ACCOUNT
     */
    public String getVatAccount() {
        return vatAccount;
    }

    /**
     * <p>VAT_ACCOUNT</p>
     *
     * @param vatAccount VAT_ACCOUNT
     */
    public void setVatAccount(String vatAccount) {
        this.vatAccount = vatAccount;
    }

    /**
     * <p>INV_RECEIVER_NAME</p>
     *
     * @return the INV_RECEIVER_NAME
     */
    public String getInvReceiverName() {
        return invReceiverName;
    }

    /**
     * <p>INV_RECEIVER_NAME</p>
     *
     * @param invReceiverName INV_RECEIVER_NAME
     */
    public void setInvReceiverName(String invReceiverName) {
        this.invReceiverName = invReceiverName;
    }

    /**
     * <p>INV_RECEIVER_TEL</p>
     *
     * @return the INV_RECEIVER_TEL
     */
    public String getInvReceiverTel() {
        return invReceiverTel;
    }

    /**
     * <p>INV_RECEIVER_TEL</p>
     *
     * @param invReceiverTel INV_RECEIVER_TEL
     */
    public void setInvReceiverTel(String invReceiverTel) {
        this.invReceiverTel = invReceiverTel;
    }

    /**
     * <p>INV_RECEIVER_EMAIL</p>
     *
     * @return the INV_RECEIVER_EMAIL
     */
    public String getInvReceiverEmail() {
        return invReceiverEmail;
    }

    /**
     * <p>INV_RECEIVER_EMAIL</p>
     *
     * @param invReceiverEmail INV_RECEIVER_EMAIL
     */
    public void setInvReceiverEmail(String invReceiverEmail) {
        this.invReceiverEmail = invReceiverEmail;
    }

    /**
     * <p>INV_RECEIVER_PROVINCE</p>
     *
     * @return the INV_RECEIVER_PROVINCE
     */
    public String getInvReceiverProvince() {
        return invReceiverProvince;
    }

    /**
     * <p>INV_RECEIVER_PROVINCE</p>
     *
     * @param invReceiverProvince INV_RECEIVER_PROVINCE
     */
    public void setInvReceiverProvince(String invReceiverProvince) {
        this.invReceiverProvince = invReceiverProvince;
    }

    /**
     * <p>INV_RECEIVER_CITY</p>
     *
     * @return the INV_RECEIVER_CITY
     */
    public String getInvReceiverCity() {
        return invReceiverCity;
    }

    /**
     * <p>INV_RECEIVER_CITY</p>
     *
     * @param invReceiverCity INV_RECEIVER_CITY
     */
    public void setInvReceiverCity(String invReceiverCity) {
        this.invReceiverCity = invReceiverCity;
    }

    /**
     * <p>INV_RECEIVER_DISTRICT</p>
     *
     * @return the INV_RECEIVER_DISTRICT
     */
    public String getInvReceiverDistrict() {
        return invReceiverDistrict;
    }

    /**
     * <p>INV_RECEIVER_DISTRICT</p>
     *
     * @param invReceiverDistrict INV_RECEIVER_DISTRICT
     */
    public void setInvReceiverDistrict(String invReceiverDistrict) {
        this.invReceiverDistrict = invReceiverDistrict;
    }

    /**
     * <p>INV_RECEIVER_ADDR</p>
     *
     * @return the INV_RECEIVER_ADDR
     */
    public String getInvReceiverAddr() {
        return invReceiverAddr;
    }

    /**
     * <p>INV_RECEIVER_ADDR</p>
     *
     * @param invReceiverAddr INV_RECEIVER_ADDR
     */
    public void setInvReceiverAddr(String invReceiverAddr) {
        this.invReceiverAddr = invReceiverAddr;
    }

    /**
     * <p>REMARK1</p>
     *
     * @return the REMARK1
     */
    public String getRemark1() {
        return remark1;
    }

    /**
     * <p>REMARK1</p>
     *
     * @param remark1 REMARK1
     */
    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    /**
     * <p>REMARK2</p>
     *
     * @return the REMARK2
     */
    public String getRemark2() {
        return remark2;
    }

    /**
     * <p>REMARK2</p>
     *
     * @param remark2 REMARK2
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    /**
     * <p>REMARK3</p>
     *
     * @return the REMARK3
     */
    public String getRemark3() {
        return remark3;
    }

    /**
     * <p>REMARK3</p>
     *
     * @param remark3 REMARK3
     */
    public void setRemark3(String remark3) {
        this.remark3 = remark3;
    }

}
