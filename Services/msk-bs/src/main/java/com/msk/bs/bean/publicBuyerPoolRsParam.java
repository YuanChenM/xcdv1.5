package com.msk.bs.bean;

import com.msk.common.bean.RsPageParam;

public class publicBuyerPoolRsParam extends RsPageParam {

    // 省编码
    private String provinceCode;
    // 物流区编码
    private String lgcsAreaCode;
    // 城市编码
    private String cityCode;
    // 县编码
    private String districtCode;
    // 买家地址
    private String buyerAddr;
    private String salesTargetType;
    private String classCode;
    // 老板姓名
    private String employeeName;
    // 联系电话
    private String busiTel;
    // 买家名称
    private String buyerName;
    // 店铺号
    private String buyerShop;
    // 买家编码
    private String buyerCode;
    // 买家类型
    private String buyerType;
    // 菜场ID/批发市场ID
    private String marketId;
    // 买家账号
    private String accountName;
    // 注册手机
    private String telNo;
    //买家电话
    private String buyerTel;
    // 营销类型
    private  String marketingsStatusName;
    //营销code
    private String marketingsStatus;

    // 买家类型名称
    private String buyerTypeName;
    // “1”的时候全部返回，其他情况不变
    private String isAll;
    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
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

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getMarketingsStatusName() {
        return marketingsStatusName;
    }

    public void setMarketingsStatusName(String marketingsStatusName) {
        this.marketingsStatusName = marketingsStatusName;
    }

    public String getBuyerTypeName() {
        return buyerTypeName;
    }

    public void setBuyerTypeName(String buyerTypeName) {
        this.buyerTypeName = buyerTypeName;
    }

    public String getBuyerTel() {
        return buyerTel;
    }

    public void setBuyerTel(String buyerTel) {
        this.buyerTel = buyerTel;
    }

    public String getMarketingsStatus() {
        return marketingsStatus;
    }

    public void setMarketingsStatus(String marketingsStatus) {
        this.marketingsStatus = marketingsStatus;
    }

    public String getIsAll() {
        return isAll;
    }

    public void setIsAll(String isAll) {
        this.isAll = isAll;
    }
}
