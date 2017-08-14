package com.msk.bs.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msk.common.consts.BsConst;
import com.msk.core.entity.ByBuyerPdCla;
import com.msk.core.entity.ByBuyerSalestarget;
import com.msk.core.entity.SlBsBuyer;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(value = {"delFlg", "crtId", "crtTime", "updId", "updTime", "actId", "actTime"})
public class IBS2101107Bean extends SlBsBuyer {
    //1:专属买家、2：抢单买家
    private String buyerFlag;
    private String buyerCode;//买家编码
    private String buyerName;//买家名
    private String lgcsAreaCode;//物流区编码
    private String lgcsAreaName;//物流区名
    private String provinceCode;//省编码
    private String provinceName;//省名
    private String cityCode;//地区编码
    private String cityName;//地区名
    private String districtCode;//区编码
    private String districtName;//区名
    private String buyerAddr;//买家地址
    private String busiTel;//买家联系电话
    private String employeeName;//老板姓名
    private String buyerShop;//店铺号
    /** 开始日时 */
    private Date startTime;
    /** 结束日时 */
    private Date endTime;
    /** 申请日时 */
    private Date applyTime;
    //申请状态
    private String applyStatusName;
    //行政区域全名
    private String domainName;
    private List<ByBuyerSalestarget> byBuyerSalestargetList;//买家产品销售对象表
    private List<ByBuyerPdCla> byBuyerPdClaList;//byBuyerPdClaList

    //注册手机
    private String telNo;
    //买家账号
    private String accountName;
    /** 参考CONSTANT表 */
    private String superiorType;
    /** 买家分类名称 */
    private String superiorName;

    private String buyerType;//买家类型
    private String buyerTypeName;//买家类型中文名
    private String marketId;//菜场ID/批发市场ID
    private String marketName;//菜场名称/批发市场名称
    private String marketingsStatusCode;//营销状态编码
    //营销/销售类型
    private String marketingsStatusName;


    /**
     * Getter method for property <tt>buyerTypeName</tt>.
     *
     * @return property value of buyerTypeName
     */
    public String getBuyerTypeName() {
        return buyerTypeName;
    }

    /**
     * Setter method for property <tt>buyerTypeName</tt>.
     *
     * @param buyerTypeName value to be assigned to property buyerTypeName
     */
    public void setBuyerTypeName(String buyerTypeName) {
        this.buyerTypeName = buyerTypeName;
    }

    /**
     * Getter method for property <tt>marketId</tt>.
     *
     * @return property value of marketId
     */
    public String getMarketId() {
        return marketId;
    }

    /**
     * Setter method for property <tt>marketId</tt>.
     *
     * @param marketId value to be assigned to property marketId
     */
    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    /**
     * Getter method for property <tt>marketName</tt>.
     *
     * @return property value of marketName
     */
    public String getMarketName() {
        return marketName;
    }

    /**
     * Setter method for property <tt>marketName</tt>.
     *
     * @param marketName value to be assigned to property marketName
     */
    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    /**
     * Getter method for property <tt>marketingsStatusCode</tt>.
     *
     * @return property value of marketingsStatusCode
     */
    public String getMarketingsStatusCode() {
        return marketingsStatusCode;
    }

    /**
     * Setter method for property <tt>marketingsStatusCode</tt>.
     *
     * @param marketingsStatusCode value to be assigned to property marketingsStatusCode
     */
    public void setMarketingsStatusCode(String marketingsStatusCode) {
        this.marketingsStatusCode = marketingsStatusCode;
    }

    /**
     * Getter method for property <tt>buyerType</tt>.
     *
     * @return property value of buyerType
     */
    public String getBuyerType() {
        return buyerType;
    }

    /**
     * Setter method for property <tt>buyerType</tt>.
     *
     * @param buyerType value to be assigned to property buyerType
     */
    public void setBuyerType(String buyerType) {
        this.buyerType = buyerType;
    }

    /**
     * Getter method for property <tt>buyerShop</tt>.
     *
     * @return property value of buyerShop
     */
    public String getBuyerShop() {
        return buyerShop;
    }

    /**
     * Setter method for property <tt>buyerShop</tt>.
     *
     * @param buyerShop value to be assigned to property buyerShop
     */
    public void setBuyerShop(String buyerShop) {
        this.buyerShop = buyerShop;
    }

    /**
     * Getter method for property <tt>employeeName</tt>.
     *
     * @return property value of employeeName
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * Setter method for property <tt>employeeName</tt>.
     *
     * @param employeeName value to be assigned to property employeeName
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     * Getter method for property <tt>superiorType</tt>.
     *
     * @return property value of superiorType
     */
    public String getSuperiorType() {
        return superiorType;
    }

    /**
     * Setter method for property <tt>superiorType</tt>.
     *
     * @param superiorType value to be assigned to property superiorType
     */
    public void setSuperiorType(String superiorType) {
        this.superiorType = superiorType;
    }

    /**
     * Getter method for property <tt>superiorName</tt>.
     *
     * @return property value of superiorName
     */
    public String getSuperiorName() {
        return superiorName;
    }

    /**
     * Setter method for property <tt>superiorName</tt>.
     *
     * @param superiorName value to be assigned to property superiorName
     */
    public void setSuperiorName(String superiorName) {
        this.superiorName = superiorName;
    }

    /**
     * Getter method for property <tt>telNo</tt>.
     *
     * @return property value of telNo
     */
    public String getTelNo() {
        return telNo;
    }

    /**
     * Setter method for property <tt>telNo</tt>.
     *
     * @param telNo value to be assigned to property telNo
     */
    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    /**
     * Getter method for property <tt>marketingsStatusName</tt>.
     *
     * @return property value of marketingsStatusName
     */
    public String getMarketingsStatusName() {
        return marketingsStatusName;
    }

    /**
     * Setter method for property <tt>marketingsStatusName</tt>.
     *
     * @param marketingsStatusName value to be assigned to property marketingsStatusName
     */
    public void setMarketingsStatusName(String marketingsStatusName) {
        this.marketingsStatusName = marketingsStatusName;
    }

    /**
     * Getter method for property <tt>accountName</tt>.
     *
     * @return property value of accountName
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * Setter method for property <tt>accountName</tt>.
     *
     * @param accountName value to be assigned to property accountName
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * Getter method for property <tt>domainName</tt>.
     *
     * @return property value of domainName
     */
    public String getDomainName() {
        return domainName;
    }

    /**
     * Setter method for property <tt>domainName</tt>.
     *
     * @param domainName value to be assigned to property domainName
     */
    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    /**
     * Getter method for property <tt>applyStatusName</tt>.
     *
     * @return property value of applyStatusName
     */
    public String getApplyStatusName() {
        return applyStatusName;
    }

    /**
     * Setter method for property <tt>applyStatusName</tt>.
     *
     * @param applyStatusName value to be assigned to property applyStatusName
     */
    public void setApplyStatusName(String applyStatusName) {
        this.applyStatusName = applyStatusName;
    }

    /**
     * Getter method for property <tt>byBuyerSalestargetList</tt>.
     *
     * @return property value of byBuyerSalestargetList
     */
    public List<ByBuyerSalestarget> getByBuyerSalestargetList() {
        return byBuyerSalestargetList;
    }

    /**
     * Setter method for property <tt>byBuyerSalestargetList</tt>.
     *
     * @param byBuyerSalestargetList value to be assigned to property byBuyerSalestargetList
     */
    public void setByBuyerSalestargetList(List<ByBuyerSalestarget> byBuyerSalestargetList) {
        this.byBuyerSalestargetList = byBuyerSalestargetList;
    }

    /**
     * Getter method for property <tt>byBuyerPdClaList</tt>.
     *
     * @return property value of byBuyerPdClaList
     */
    public List<ByBuyerPdCla> getByBuyerPdClaList() {
        return byBuyerPdClaList;
    }

    /**
     * Setter method for property <tt>byBuyerPdClaList</tt>.
     *
     * @param byBuyerPdClaList value to be assigned to property byBuyerPdClaList
     */
    public void setByBuyerPdClaList(List<ByBuyerPdCla> byBuyerPdClaList) {
        this.byBuyerPdClaList = byBuyerPdClaList;
    }

    /**
     * Getter method for property <tt>buyerCode</tt>.
     *
     * @return property value of buyerCode
     */
    public String getBuyerCode() {
        return buyerCode;
    }

    /**
     * Setter method for property <tt>buyerCode</tt>.
     *
     * @param buyerCode value to be assigned to property buyerCode
     */
    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
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
     * Getter method for property <tt>lgcsAreaCode</tt>.
     *
     * @return property value of lgcsAreaCode
     */
    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    /**
     * Setter method for property <tt>lgcsAreaCode</tt>.
     *
     * @param lgcsAreaCode value to be assigned to property lgcsAreaCode
     */
    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    /**
     * Getter method for property <tt>lgcsAreaName</tt>.
     *
     * @return property value of lgcsAreaName
     */
    public String getLgcsAreaName() {
        return lgcsAreaName;
    }

    /**
     * Setter method for property <tt>lgcsAreaName</tt>.
     *
     * @param lgcsAreaName value to be assigned to property lgcsAreaName
     */
    public void setLgcsAreaName(String lgcsAreaName) {
        this.lgcsAreaName = lgcsAreaName;
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
     * Getter method for property <tt>provinceName</tt>.
     *
     * @return property value of provinceName
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * Setter method for property <tt>provinceName</tt>.
     *
     * @param provinceName value to be assigned to property provinceName
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    /**
     * Getter method for property <tt>cityCode</tt>.
     *
     * @return property value of cityCode
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * Setter method for property <tt>cityCode</tt>.
     *
     * @param cityCode value to be assigned to property cityCode
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
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
     * Getter method for property <tt>districtName</tt>.
     *
     * @return property value of districtName
     */
    public String getDistrictName() {
        return districtName;
    }

    /**
     * Setter method for property <tt>districtName</tt>.
     *
     * @param districtName value to be assigned to property districtName
     */
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
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
     * Getter method for property <tt>buyerFlag</tt>.
     *
     * @return property value of buyerFlag
     */
    public String getBuyerFlag() {
        return buyerFlag;
    }

    /**
     * Setter method for property <tt>buyerFlag</tt>.
     *
     * @param buyerFlag value to be assigned to property buyerFlag
     */
    public void setBuyerFlag(String buyerFlag) {
        this.buyerFlag = buyerFlag;
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
}
