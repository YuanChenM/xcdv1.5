package com.msk.bs.bean;

import com.msk.core.entity.ByBuyerBasicInfo;

public class BS2101104Bean extends ByBuyerBasicInfo {

    //卖家编码
    private String slCode;
    //冻品管家账号
    private String houseAccount;
    //买家编码
    private String buyerId;
    //买家名
    private String buyerName;
    //买家地址
    private String buyerAddr;
    //联系电话
    private String busiTel;
    //行政区划
    private String cityName;
    //'省编码'
    private String provinceCode;
    //'物流区编码'
    private String lgcsAreaAode;
    //'地区（城市）编码'
    private String cityCode;
    //'区（县）编码'
    private  String districtCode;
    //按钮标志
    private  String flge;
    //冻品管家code
    private String houseCode;
    /**
     * Getter method for property <tt>slCode</tt>.
     *
     * @return property value of slCode
     */
    public String getSlCode() {
        return slCode;
    }

    /**
     * Setter method for property <tt>slCode</tt>.
     *
     * @param slCode value to be assigned to property slCode
     */
    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    /**
     * Getter method for property <tt>buyerId</tt>.
     *
     * @return property value of buyerId
     */
    public String getBuyerId() {
        return buyerId;
    }

    /**
     * Setter method for property <tt>buyerId</tt>.
     *
     * @param buyerId value to be assigned to property buyerId
     */
    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    /**
     * Getter method for property <tt>buyerName</tt>.
     *
     * @return property value of buyerName
     */
    public String getBuyerName() {
        return buyerName;
    }

    /**
     * Setter method for property <tt>buyerName</tt>.
     *
     * @param buyerName value to be assigned to property buyerName
     */
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    /**
     * Getter method for property <tt>buyerAddr</tt>.
     *
     * @return property value of buyerAddr
     */
    public String getBuyerAddr() {
        return buyerAddr;
    }

    /**
     * Setter method for property <tt>buyerAddr</tt>.
     *
     * @param buyerAddr value to be assigned to property buyerAddr
     */
    public void setBuyerAddr(String buyerAddr) {
        this.buyerAddr = buyerAddr;
    }

    /**
     * Getter method for property <tt>busiTel</tt>.
     *
     * @return property value of busiTel
     */
    public String getBusiTel() {
        return busiTel;
    }

    /**
     * Setter method for property <tt>busiTel</tt>.
     *
     * @param busiTel value to be assigned to property busiTel
     */
    public void setBusiTel(String busiTel) {
        this.busiTel = busiTel;
    }

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
     * Getter method for property <tt>provinceCode</tt>.
     *
     * @return property value of provinceCode
     */
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     * Setter method for property <tt>provinceCode</tt>.
     *
     * @param provinceCode value to be assigned to property provinceCode
     */
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    /**
     * Getter method for property <tt>lgcsAreaAode</tt>.
     *
     * @return property value of lgcsAreaAode
     */
    public String getLgcsAreaAode() {
        return lgcsAreaAode;
    }

    /**
     * Setter method for property <tt>lgcsAreaAode</tt>.
     *
     * @param lgcsAreaAode value to be assigned to property lgcsAreaAode
     */
    public void setLgcsAreaAode(String lgcsAreaAode) {
        this.lgcsAreaAode = lgcsAreaAode;
    }

    /**
     * Getter method for property <tt>cityCode</tt>.
     *
     * @return property value of cityCode
     */
    @Override
    public String getCityCode() {
        return cityCode;
    }

    /**
     * Setter method for property <tt>cityCode</tt>.
     *
     * @param cityCode value to be assigned to property cityCode
     */
    @Override
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * Getter method for property <tt>flge</tt>.
     *
     * @return property value of flge
     */
    public String getFlge() {
        return flge;
    }

    /**
     * Setter method for property <tt>flge</tt>.
     *
     * @param flge value to be assigned to property flge
     */
    public void setFlge(String flge) {
        this.flge = flge;
    }

    /**
     * Getter method for property <tt>districtCode</tt>.
     *
     * @return property value of districtCode
     */
    public String getDistrictCode() {
        return districtCode;
    }

    /**
     * Setter method for property <tt>districtCode</tt>.
     *
     * @param districtCode value to be assigned to property districtCode
     */
    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    /**
     * Getter method for property <tt>houseAccount</tt>.
     *
     * @return property value of houseAccount
     */
    public String getHouseAccount() {
        return houseAccount;
    }

    /**
     * Setter method for property <tt>houseAccount</tt>.
     *
     * @param houseAccount value to be assigned to property houseAccount
     */
    public void setHouseAccount(String houseAccount) {
        this.houseAccount = houseAccount;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }
}
