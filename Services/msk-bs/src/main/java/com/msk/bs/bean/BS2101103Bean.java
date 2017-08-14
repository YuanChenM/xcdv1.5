package com.msk.bs.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.msk.common.consts.BsConst;
import com.msk.core.entity.SlBsBuyer;

public class BS2101103Bean extends SlBsBuyer {

    //买家编码
    private String buyerId;
    //买家名
    private String buyerName;
    //所属行政区域码
    private  String lgcsCode;
    //买家地址
    private String buyerAddr;
    //联系电话
    private String busiTel;
    //行政区划
    private String cityName;
    //买手名称
    private  String slContact;
    /** 开始日时 */
    private java.util.Date startTime;
    /** 结束日时 */
    private java.util.Date endTime;
    /** 申请日时 */
    private java.util.Date applyTime;
    //省
    private java.util.Date provinceCode;
    //城市
    private java.util.Date cityCode;
    //地区
    private java.util.Date districtCode;

    /**
     * Getter method for property <tt>buyerId</tt>.
     *
     * @return property value of buyerId
     */
    @Override
    public String getBuyerId() {
        return buyerId;
    }

    /**
     * Setter method for property <tt>buyerId</tt>.
     *
     * @param buyerId value to be assigned to property buyerId
     */
    @Override
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
     * Getter method for property <tt>lgcsCode</tt>.
     *
     * @return property value of lgcsCode
     */
    public String getLgcsCode() {
        return lgcsCode;
    }

    /**
     * Setter method for property <tt>lgcsCode</tt>.
     *
     * @param lgcsCode value to be assigned to property lgcsCode
     */
    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
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
     * Getter method for property <tt>startTime</tt>.
     *
     * @return property value of startTime
     */
    @Override
    @JsonFormat(timezone= BsConst.Default.TIMEZONE, pattern= BsConst.Default.FORMAT_TIME)
    public Date getStartTime() {
        return startTime;
    }

    /**
     * Setter method for property <tt>startTime</tt>.
     *
     * @param startTime value to be assigned to property startTime
     */
    @Override
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * Getter method for property <tt>endTime</tt>.
     *
     * @return property value of endTime
     */
    @Override
    @JsonFormat(timezone= BsConst.Default.TIMEZONE, pattern= BsConst.Default.FORMAT_TIME)
    public Date getEndTime() {
        return endTime;
    }

    /**
     * Setter method for property <tt>endTime</tt>.
     *
     * @param endTime value to be assigned to property endTime
     */
    @Override
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * Getter method for property <tt>applyTime</tt>.
     *
     * @return property value of applyTime
     */
    @Override
    @JsonFormat(timezone= BsConst.Default.TIMEZONE, pattern= BsConst.Default.FORMAT_TIME)
    public Date getApplyTime() {
        return applyTime;
    }

    /**
     * Setter method for property <tt>applyTime</tt>.
     *
     * @param applyTime value to be assigned to property applyTime
     */
    @Override
    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    /**
     * Getter method for property <tt>provinceCode</tt>.
     *
     * @return property value of provinceCode
     */
    public Date getProvinceCode() {
        return provinceCode;
    }

    /**
     * Setter method for property <tt>provinceCode</tt>.
     *
     * @param provinceCode value to be assigned to property provinceCode
     */
    public void setProvinceCode(Date provinceCode) {
        this.provinceCode = provinceCode;
    }

    /**
     * Getter method for property <tt>cityCode</tt>.
     *
     * @return property value of cityCode
     */
    public Date getCityCode() {
        return cityCode;
    }

    /**
     * Setter method for property <tt>cityCode</tt>.
     *
     * @param cityCode value to be assigned to property cityCode
     */
    public void setCityCode(Date cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * Getter method for property <tt>districtCode</tt>.
     *
     * @return property value of districtCode
     */
    public Date getDistrictCode() {
        return districtCode;
    }

    /**
     * Setter method for property <tt>districtCode</tt>.
     *
     * @param districtCode value to be assigned to property districtCode
     */
    public void setDistrictCode(Date districtCode) {
        this.districtCode = districtCode;
    }
}
