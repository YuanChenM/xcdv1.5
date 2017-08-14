package com.msk.bs.bean;

import com.hoperun.core.bean.BasePageParam;

public class BS2101101Param extends BasePageParam {
    //行政区划
    private String cityName;
    //物流区划
    private String lgcsAreaName;
    //联系电话
    private String slTel;
    //买手身份证号
    private String slIdcard;
    //买手地址
    private String slAddress;
    //买手名称
    private  String slContact;
    //当前管家数
    private  String stewardNum;
    //当前专属买家
    private  String buyerNum;
    //省
    private  String provinceCode;
    //地区
    private  String cityCode;
    //区
    private  String districtCode;

    /**
     * Getter method for property <tt>cityName</tt>.
     *
     * @return property value of cityName
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Setter method for property <tt>cityName</tt>.
     *
     * @param cityName value to be assigned to property cityName
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * Getter method for property <tt>lgcsAreaName</tt>.
     *
     * @return property value of lgcsAreaName
     */
    public String getLgcsAreaName() {
        return lgcsAreaName;
    }

    /**
     * Setter method for property <tt>lgcsAreaName</tt>.
     *
     * @param lgcsAreaName value to be assigned to property lgcsAreaName
     */
    public void setLgcsAreaName(String lgcsAreaName) {
        this.lgcsAreaName = lgcsAreaName;
    }

    /**
     * Getter method for property <tt>slTel</tt>.
     *
     * @return property value of slTel
     */
    public String getSlTel() {
        return slTel;
    }

    /**
     * Setter method for property <tt>slTel</tt>.
     *
     * @param slTel value to be assigned to property slTel
     */
    public void setSlTel(String slTel) {
        this.slTel = slTel;
    }

    /**
     * Getter method for property <tt>slIdcard</tt>.
     *
     * @return property value of slIdcard
     */
    public String getSlIdcard() {
        return slIdcard;
    }

    /**
     * Setter method for property <tt>slIdcard</tt>.
     *
     * @param slIdcard value to be assigned to property slIdcard
     */
    public void setSlIdcard(String slIdcard) {
        this.slIdcard = slIdcard;
    }

    /**
     * Getter method for property <tt>slAddress</tt>.
     *
     * @return property value of slAddress
     */
    public String getSlAddress() {
        return slAddress;
    }

    /**
     * Setter method for property <tt>slAddress</tt>.
     *
     * @param slAddress value to be assigned to property slAddress
     */
    public void setSlAddress(String slAddress) {
        this.slAddress = slAddress;
    }

    /**
     * Getter method for property <tt>slContact</tt>.
     *
     * @return property value of slContact
     */
    public String getSlContact() {
        return slContact;
    }

    /**
     * Setter method for property <tt>slContact</tt>.
     *
     * @param slContact value to be assigned to property slContact
     */
    public void setSlContact(String slContact) {
        this.slContact = slContact;
    }

    /**
     * Getter method for property <tt>stewardNum</tt>.
     *
     * @return property value of stewardNum
     */
    public String getStewardNum() {
        return stewardNum;
    }

    /**
     * Setter method for property <tt>stewardNum</tt>.
     *
     * @param stewardNum value to be assigned to property stewardNum
     */
    public void setStewardNum(String stewardNum) {
        this.stewardNum = stewardNum;
    }

    /**
     * Getter method for property <tt>buyerNum</tt>.
     *
     * @return property value of buyerNum
     */
    public String getBuyerNum() {
        return buyerNum;
    }

    /**
     * Setter method for property <tt>buyerNum</tt>.
     *
     * @param buyerNum value to be assigned to property buyerNum
     */
    public void setBuyerNum(String buyerNum) {
        this.buyerNum = buyerNum;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }
}
