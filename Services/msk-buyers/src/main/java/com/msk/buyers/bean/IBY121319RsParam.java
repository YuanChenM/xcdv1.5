package com.msk.buyers.bean;

import com.hoperun.core.bean.BaseParam;

/**
 *
 *
 * @author yuan_zhifei
 */
public class IBY121319RsParam extends BaseParam {
    //买家ID
    private String buyerId;
    //省名称
    private String provinceName;
    //市名称
    private String cityName;
    //区名称
    private String districtName;
    //配送地址
    private String deliveryAddr;
    //上海订单配送区域
    private String shOdDeliveryArea;
    //买家编码
    private String buyerCode;
    //买家类型
    private String superiorType;
    //买家编码截取长度
    private String subLength;
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

    public String getBuyerCode() {
        return buyerCode;
    }

    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
    }

    public String getSuperiorType() {
        return superiorType;
    }

    public void setSuperiorType(String superiorType) {
        this.superiorType = superiorType;
    }

    public String getSubLength() {
        return subLength;
    }

    public void setSubLength(String subLength) {
        this.subLength = subLength;
    }
}
