package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseParam;

/**
 * Created by liu_tao2 on 2016/8/12.
 */
public class ISO151414ByBuyerInfoParam extends BaseParam {
    private String telNo;

    private String buyerId;

    private String provinceName;

    private String cityName;

    private String districtName;

    private String deliveryAddr;

    private String shOdDeliveryArea;

    private String buyerCode;

    private String slCode;

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getBuyerCode() {
        return buyerCode;
    }

    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDeliveryAddr() {
        return deliveryAddr;
    }

    public void setDeliveryAddr(String deliveryAddr) {
        this.deliveryAddr = deliveryAddr;
    }

    public String getShOdDeliveryArea() {
        return shOdDeliveryArea;
    }

    public void setShOdDeliveryArea(String shOdDeliveryArea) {
        this.shOdDeliveryArea = shOdDeliveryArea;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }
}
