package com.msk.br.bean;

import com.msk.common.bean.RsPageParam;

import java.util.List;


/**
 * Created by tao_zhifa on 2016/8/19.
 */
public class IBR121308RsParam extends RsPageParam{
    private String buyerId;
    private String provinceCode;
    private String cityCode;
    private String districtCode;
    private String buyerAddr;

    private String employeeName;
    //营业电话
    private String busiTel;
    private String buyerName;
    private String buyerShop;
    private String buyerCode;
    private String buyerType;
    private String marketId;
    //买家电话
    private String buyerTel;
    //老板电话
    private String bossTel;
    //收货人手机
    private String recPerTel;
    private String lgcsAreaCode;
    private String accountName;
    private String buyerTypeName;
    private String marketingsStatusName;
    private String marketingsStatus;
    private String [] marketingsStatusArray;

    private String salesTargetType;
    private String salesTargetName;
    private String classCode;
    private String className;

    private String isAll;
    private List<IBR121308RsParam> byBuyerSalestargetList;
    private List<IBR121308RsParam> byBuyerPdClaList;

    public String getSalesTargetName() {
        return salesTargetName;
    }

    public void setSalesTargetName(String salesTargetName) {
        this.salesTargetName = salesTargetName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<IBR121308RsParam> getByBuyerSalestargetList() {
        return byBuyerSalestargetList;
    }

    public void setByBuyerSalestargetList(List<IBR121308RsParam> byBuyerSalestargetList) {
        this.byBuyerSalestargetList = byBuyerSalestargetList;
    }

    public List<IBR121308RsParam> getByBuyerPdClaList() {
        return byBuyerPdClaList;
    }

    public void setByBuyerPdClaList(List<IBR121308RsParam> byBuyerPdClaList) {
        this.byBuyerPdClaList = byBuyerPdClaList;
    }

    public String[] getMarketingsStatusArray() {
        return marketingsStatusArray;
    }

    public String getIsAll() {
        return isAll;
    }

    public void setIsAll(String isAll) {
        this.isAll = isAll;
    }

    public void setMarketingsStatusArray(String[] marketingsStatusArray) {
        this.marketingsStatusArray = marketingsStatusArray;
    }

    public String getMarketingsStatus() {
        return marketingsStatus;
    }

    public void setMarketingsStatus(String marketingsStatus) {
        this.marketingsStatus = marketingsStatus;
    }

    public String getMarketingsStatusName() {
        return marketingsStatusName;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public void setMarketingsStatusName(String marketingsStatusName) {
        this.marketingsStatusName = marketingsStatusName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBuyerTypeName() {
        return buyerTypeName;
    }

    public void setBuyerTypeName(String buyerTypeName) {
        this.buyerTypeName = buyerTypeName;
    }

    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }


    public String getBuyerTel() {
        return buyerTel;
    }

    public void setBuyerTel(String buyerTel) {
        this.buyerTel = buyerTel;
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

    public String getBuyerAddr() {
        return buyerAddr;
    }

    public void setBuyerAddr(String buyerAddr) {
        this.buyerAddr = buyerAddr;
    }

    public String getSalesTargetType() {
        return salesTargetType;
    }

    public void setSalesTargetType(String salesTargetType) {
        this.salesTargetType = salesTargetType;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getBusiTel() {
        return busiTel;
    }

    public void setBusiTel(String busiTel) {
        this.busiTel = busiTel;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerShop() {
        return buyerShop;
    }

    public void setBuyerShop(String buyerShop) {
        this.buyerShop = buyerShop;
    }

    public String getBuyerCode() {
        return buyerCode;
    }

    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
    }

    public String getBuyerType() {
        return buyerType;
    }

    public void setBuyerType(String buyerType) {
        this.buyerType = buyerType;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getBossTel() {
        return bossTel;
    }

    public void setBossTel(String bossTel) {
        this.bossTel = bossTel;
    }

    public String getRecPerTel() {
        return recPerTel;
    }

    public void setRecPerTel(String recPerTel) {
        this.recPerTel = recPerTel;
    }
}
