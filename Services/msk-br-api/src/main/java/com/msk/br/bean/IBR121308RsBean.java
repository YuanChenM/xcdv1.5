package com.msk.br.bean;

import com.msk.common.base.BaseBean;

import java.util.List;

/**
 * Created by tao_zhifa on 2016/8/19.
 */
public class IBR121308RsBean extends BaseBean {

    private Integer totalCount;
    private Integer totalPage;
    //买家手机号
    private String buyerTel;
    //营业电话号
    private String busiTel;
    private String buyerTypeName;
    private String accountName;
    private String domainName;
    private String buyerId;
    private String buyerCode;
    private String buyerName;
    private String lgcsAreaCode;
    private String lgcsAreaName;
    private String cityCode;
    private String cityName;
    private String districtCode;
    private String districtName;
    private String provinceCode;
    private String provinceName;
    private String buyerAddr;
    //老板手机号
    private String bossTel;
    //收货人手机
    private String recPerTel;
    private String employeeName;
    private String buyerShop;
    private String buyerType;
    private String marketId;
    private String marketName;
    private String marketingsStatusCode;
    private String marketingsStatusName;
    private String salestarget;

    private String salesTargetType;
    private String salesTargetName;
    private String classCode;
    private String className;

    private String isAll;
    private List<IBR121308RsBean> byBuyerSalestargetList;
    private List<IBR121308RsBean> byBuyerPdClaList;

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getSalesTargetType() {
        return salesTargetType;
    }

    public void setSalesTargetType(String salesTargetType) {
        this.salesTargetType = salesTargetType;
    }

    public String getSalesTargetName() {
        return salesTargetName;
    }

    public void setSalesTargetName(String salesTargetName) {
        this.salesTargetName = salesTargetName;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getIsAll() {
        return isAll;
    }

    public void setIsAll(String isAll) {
        this.isAll = isAll;
    }

    public List<IBR121308RsBean> getByBuyerSalestargetList() {
        return byBuyerSalestargetList;
    }

    public void setByBuyerSalestargetList(List<IBR121308RsBean> byBuyerSalestargetList) {
        this.byBuyerSalestargetList = byBuyerSalestargetList;
    }

    public List<IBR121308RsBean> getByBuyerPdClaList() {
        return byBuyerPdClaList;
    }

    public void setByBuyerPdClaList(List<IBR121308RsBean> byBuyerPdClaList) {
        this.byBuyerPdClaList = byBuyerPdClaList;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getBuyerTypeName() {
        return buyerTypeName;
    }

    public void setBuyerTypeName(String buyerTypeName) {
        this.buyerTypeName = buyerTypeName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBuyerTel() {
        return buyerTel;
    }

    public void setBuyerTel(String buyerTel) {
        this.buyerTel = buyerTel;
    }

    public String getBusiTel() {
        return busiTel;
    }

    public void setBusiTel(String busiTel) {
        this.busiTel = busiTel;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
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

    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    public String getLgcsAreaName() {
        return lgcsAreaName;
    }

    public void setLgcsAreaName(String lgcsAreaName) {
        this.lgcsAreaName = lgcsAreaName;
    }


    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getBuyerAddr() {
        return buyerAddr;
    }

    public void setBuyerAddr(String buyerAddr) {
        this.buyerAddr = buyerAddr;
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

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getBuyerShop() {
        return buyerShop;
    }

    public void setBuyerShop(String buyerShop) {
        this.buyerShop = buyerShop;
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

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getMarketingsStatusCode() {
        return marketingsStatusCode;
    }

    public void setMarketingsStatusCode(String marketingsStatusCode) {
        this.marketingsStatusCode = marketingsStatusCode;
    }

    public String getMarketingsStatusName() {
        return marketingsStatusName;
    }

    public void setMarketingsStatusName(String marketingsStatusName) {
        this.marketingsStatusName = marketingsStatusName;
    }

    public String getSalestarget() {
        return salestarget;
    }

    public void setSalestarget(String salestarget) {
        this.salestarget = salestarget;
    }
}
