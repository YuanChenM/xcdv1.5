package com.msk.order.bean.result;

import com.msk.common.bean.result.BaseResult;

import java.util.List;

/**
 * Created by liu_tao2 on 2016/8/5.
 */
public class ISO151414ByBuyerInfoResult extends BaseResult {
    /** 买家Id */
    private String buyerId;
    /** 买家编码 */
    private String buyerCode;
    /** 买家名称 */
    private String buyerName;
    /** 买家类型 */
    private String superiorType;

    private ISO151414BuyerResultInfo iby121319RsBean;

    private List<ISO151414BuyerShop> buyershopList;

    public List<ISO151414BuyerShop> getBuyershopList() {
        return buyershopList;
    }

    public void setBuyershopList(List<ISO151414BuyerShop> buyershopList) {
        this.buyershopList = buyershopList;
    }

    public ISO151414BuyerResultInfo getIby121319RsBean() {
        return iby121319RsBean;
    }

    public void setIby121319RsBean(ISO151414BuyerResultInfo iby121319RsBean) {
        this.iby121319RsBean = iby121319RsBean;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerCode() {
        return buyerCode;
    }

    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getSuperiorType() {
        return superiorType;
    }

    public void setSuperiorType(String superiorType) {
        this.superiorType = superiorType;
    }
}
