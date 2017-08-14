package com.msk.buyers.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msk.core.entity.ByBuyerPictures;

/**
 * IBY121201RsParam.
 *
 * @author zhou_yajun
 */
@JsonIgnoreProperties(value = { "crtTime","updTime"})
public class IBY121206RsParam extends ByBuyerPictures {

    private static final long serialVersionUID = 1L;
    /** 营业执照图片地址 */
    private String picLicensePath;
    /** 组织机构代码证图片 */
    private String picOrgStructurePath;
    /** 税务登记证图片 */
    private String picTaxRegistrationPath;
    /** 食品流通许可证图片 */
    private String picFoodCirculationPath;
    /** 法定代表人证件图片 */
    private String picCertPath;

    /**
     * Getter method for property <tt>picLicensePath</tt>.
     *
     * @return property value of picLicensePath
     */
    public String getPicLicensePath() {
        return picLicensePath;
    }

    /**
     * Setter method for property <tt>picLicensePath</tt>.
     *
     * @param picLicensePath value to be assigned to property picLicensePath
     */
    public void setPicLicensePath(String picLicensePath) {
        this.picLicensePath = picLicensePath;
    }

    /**
     * Getter method for property <tt>picOrgStructurePath</tt>.
     *
     * @return property value of picOrgStructurePath
     */
    public String getPicOrgStructurePath() {
        return picOrgStructurePath;
    }

    /**
     * Setter method for property <tt>picOrgStructurePath</tt>.
     *
     * @param picOrgStructurePath value to be assigned to property picOrgStructurePath
     */
    public void setPicOrgStructurePath(String picOrgStructurePath) {
        this.picOrgStructurePath = picOrgStructurePath;
    }

    /**
     * Getter method for property <tt>picTaxRegistrationPath</tt>.
     *
     * @return property value of picTaxRegistrationPath
     */
    public String getPicTaxRegistrationPath() {
        return picTaxRegistrationPath;
    }

    /**
     * Setter method for property <tt>picTaxRegistrationPath</tt>.
     *
     * @param picTaxRegistrationPath value to be assigned to property picTaxRegistrationPath
     */
    public void setPicTaxRegistrationPath(String picTaxRegistrationPath) {
        this.picTaxRegistrationPath = picTaxRegistrationPath;
    }

    /**
     * Getter method for property <tt>picFoodCirculationPath</tt>.
     *
     * @return property value of picFoodCirculationPath
     */
    public String getPicFoodCirculationPath() {
        return picFoodCirculationPath;
    }

    /**
     * Setter method for property <tt>picFoodCirculationPath</tt>.
     *
     * @param picFoodCirculationPath value to be assigned to property picFoodCirculationPath
     */
    public void setPicFoodCirculationPath(String picFoodCirculationPath) {
        this.picFoodCirculationPath = picFoodCirculationPath;
    }

    /**
     * Getter method for property <tt>picCertPath</tt>.
     *
     * @return property value of picCertPath
     */
    public String getPicCertPath() {
        return picCertPath;
    }

    /**
     * Setter method for property <tt>picCertPath</tt>.
     *
     * @param picCertPath value to be assigned to property picCertPath
     */
    public void setPicCertPath(String picCertPath) {
        this.picCertPath = picCertPath;
    }
}