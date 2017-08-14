package com.msk.buyers.bean;

import com.msk.core.entity.BaseEntity;

/**
 * Created by zhu_kai1 on 2016/6/20.
 */
public class IBY121223Result extends BaseEntity{
    private static final long serialVersionUID = 1L;
    /**买家buyerId**/
    private  String buyerId;
    /**买家编码**/
    private  String buyerCode;
    /**买家名称**/
    private  String buyerName;
    /**店铺号**/
    private  String buyerShop;
    /**物流区编码**/
    private  String lgcsAreaCode;
    /**物流区名**/
    private  String lgcsAreaName;
    /**省编码**/
    private  String provinceCode;
    /**省名**/
    private  String provinceName;
    /**地区编码**/
    private  String cityCode;
    /**地区名**/
    private  String cityName;
    /**区编码**/
    private  String districtCode;
    /**区名**/
    private  String  districtName;
    /**买家地址**/
    private  String buyerAddr;
    /**行政区域全名**/
    private  String domainName;
    /**买家分类类型**/
    private  String  superiorType;
    /**买家分类名称**/
    private  String superiorName;
    /**买家账号**/
    private  String accountName;
    /**买家联系电话**/
    private  String busiTel;
    /**注册手机**/
    private  String telNo;
    /**营销/销售类型**/
    private  String marketingsStatusName;
    /**雇员姓名**/
    private  String  employeeName;
    /**销售产品类型**/
    private String classCode;

    private String buyerType;//买家类型
    private String buyerTypeName;//买家类型中文名
    private String marketId;//菜场ID/批发市场ID
    private String marketName;//菜场名称/批发市场名称
    private String marketingsStatusCode;//营销状态编码
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

    public String getBuyerShop() {
        return buyerShop;
    }

    public void setBuyerShop(String buyerShop) {
        this.buyerShop = buyerShop;
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

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getSuperiorType() {
        return superiorType;
    }

    public void setSuperiorType(String superiorType) {
        this.superiorType = superiorType;
    }

    public String getSuperiorName() {
        return superiorName;
    }

    public void setSuperiorName(String superiorName) {
        this.superiorName = superiorName;
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

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getBuyerType() {
        return buyerType;
    }

    public void setBuyerType(String buyerType) {
        this.buyerType = buyerType;
    }

    public String getBuyerTypeName() {
        return buyerTypeName;
    }

    public void setBuyerTypeName(String buyerTypeName) {
        this.buyerTypeName = buyerTypeName;
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
}
