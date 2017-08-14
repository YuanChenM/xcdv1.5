package com.msk.buyers.bean;

import com.msk.core.entity.ByBuyerPdCla;
import com.msk.core.entity.ByBuyerRecAddr;
import com.msk.core.entity.ByBuyerRecTime;
import com.msk.core.entity.ByBuyerSalestarget;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhu_kai1 on 2016/5/3.
 */
public class BuyerRelationResult implements Serializable{
    private static final long serialVersionUID = 1L;
    private List<BuyerBasicInfoBean> buyerBasicInfoList;
    private List<IBY121207RsParam> employeeList;
    private List<ByBuyerPdCla> productList;
    private List<ByBuyerRecAddr> recAddrList;
    private List<ByBuyerRecTime> recTimeList;
    private List<ByBuyerSalestarget> saleList;
    private List<BuyerLicenceBean> licence;
    private List<IBY121206RsParam> buyerPicture;

    public List<IBY121207RsParam> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<IBY121207RsParam> employeeList) {
        this.employeeList = employeeList;
    }

    public List<ByBuyerPdCla> getProductList() {
        return productList;
    }

    public void setProductList(List<ByBuyerPdCla> productList) {
        this.productList = productList;
    }

    public List<ByBuyerRecTime> getRecTimeList() {
        return recTimeList;
    }

    public void setRecTimeList(List<ByBuyerRecTime> recTimeList) {
        this.recTimeList = recTimeList;
    }

    public List<ByBuyerSalestarget> getSaleList() {
        return saleList;
    }

    public void setSaleList(List<ByBuyerSalestarget> saleList) {
        this.saleList = saleList;
    }

    public List<BuyerBasicInfoBean> getBuyerBasicInfoList() {
        return buyerBasicInfoList;
    }

    public void setBuyerBasicInfoList(List<BuyerBasicInfoBean> buyerBasicInfoList) {
        this.buyerBasicInfoList = buyerBasicInfoList;
    }

    public List<BuyerLicenceBean> getLicence() {
        return licence;
    }

    public void setLicence(List<BuyerLicenceBean> licence) {
        this.licence = licence;
    }

    public List<IBY121206RsParam> getBuyerPicture() {
        return buyerPicture;
    }

    public void setBuyerPicture(List<IBY121206RsParam> buyerPicture) {
        this.buyerPicture = buyerPicture;
    }

    public List<ByBuyerRecAddr> getRecAddrList() {
        return recAddrList;
    }

    public void setRecAddrList(List<ByBuyerRecAddr> recAddrList) {
        this.recAddrList = recAddrList;
    }
}
