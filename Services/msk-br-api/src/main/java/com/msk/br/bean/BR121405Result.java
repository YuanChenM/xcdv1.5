package com.msk.br.bean;


import com.msk.core.entity.BrOBuyerInfo;

public class BR121405Result extends BrOBuyerInfo {

    private String bossNameAndContact;

    private String buyerNameAndMainCode;

    private String manageAddr;

    private String marketingClass;

    /**
     * Getter method for property <tt>bossNameAndContact</tt>.
     *
     * @return property value of bossNameAndContact
     */
    public String getBossNameAndContact() {
        return bossNameAndContact;
    }

    /**
     * Setter method for property <tt>bossNameAndContact</tt>.
     *
     * @param bossNameAndContact value to be assigned to property bossNameAndContact
     */
    public void setBossNameAndContact(String bossNameAndContact) {
        this.bossNameAndContact = bossNameAndContact;
    }

    /**
     * Getter method for property <tt>buyerNameAndMainCode</tt>.
     *
     * @return property value of buyerNameAndMainCode
     */
    public String getBuyerNameAndMainCode() {
        return buyerNameAndMainCode;
    }

    /**
     * Setter method for property <tt>buyerNameAndMainCode</tt>.
     *
     * @param buyerNameAndMainCode value to be assigned to property buyerNameAndMainCode
     */
    public void setBuyerNameAndMainCode(String buyerNameAndMainCode) {
        this.buyerNameAndMainCode = buyerNameAndMainCode;
    }

    /**
     * Getter method for property <tt>manageAddr</tt>.
     *
     * @return property value of manageAddr
     */
    @Override
    public String getManageAddr() {
        return manageAddr;
    }

    /**
     * Setter method for property <tt>manageAddr</tt>.
     *
     * @param manageAddr value to be assigned to property manageAddr
     */
    @Override
    public void setManageAddr(String manageAddr) {
        this.manageAddr = manageAddr;
    }

    /**
     * Getter method for property <tt>marketingClass</tt>.
     *
     * @return property value of marketingClass
     */
    public String getMarketingClass() {
        return marketingClass;
    }

    /**
     * Setter method for property <tt>marketingClass</tt>.
     *
     * @param marketingClass value to be assigned to property marketingClass
     */
    public void setMarketingClass(String marketingClass) {
        this.marketingClass = marketingClass;
    }
}
