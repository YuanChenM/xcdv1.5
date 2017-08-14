package com.msk.buyers.bean;

import com.msk.core.entity.ByBuyerLicence;

/**
 * Created by zhu_kai1 on 2016/4/28.
 */
public class BuyerLicenceBean extends ByBuyerLicence {

    private static final long serialVersionUID = 1L;
    //证照名称
    private String licDes;
    //法定代表人证件名称
    private String legalLicDes;

    /**
     * Getter method for property <tt>licDes</tt>.
     *
     * @return property value of licDes
     */
    public String getLicDes() {
        return licDes;
    }

    /**
     * Setter method for property <tt>licDes</tt>.
     *
     * @param licDes value to be assigned to property licDes
     */
    public void setLicDes(String licDes) {
        this.licDes = licDes;
    }

    /**
     * Getter method for property <tt>legalLicDes</tt>.
     *
     * @return property value of legalLicDes
     */
    public String getLegalLicDes() {
        return legalLicDes;
    }

    /**
     * Setter method for property <tt>legalLicDes</tt>.
     *
     * @param legalLicDes value to be assigned to property legalLicDes
     */
    public void setLegalLicDes(String legalLicDes) {
        this.legalLicDes = legalLicDes;
    }

}
