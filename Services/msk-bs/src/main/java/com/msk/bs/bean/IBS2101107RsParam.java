package com.msk.bs.bean;

import com.msk.common.bean.RsPageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
@ApiModel(value = "IBS2101107RsParam",description = "param")
public class IBS2101107RsParam extends RsPageParam {
    @ApiModelProperty(value = "1:专属买家、2：抢单买家")
    private String buyerFlag;
    @ApiModelProperty(value = "区划(6)+顺序码(4)")
    private java.lang.String slCode;
    @ApiModelProperty(value = "用于登录的卖家账号")
    private java.lang.String houseCode;
    @ApiModelProperty(value = "买家编码")
    private java.lang.String buyerId;
    @ApiModelProperty(value = "开始日时")
    private java.util.Date startTime;
    @ApiModelProperty(value = "结束日时")
    private java.util.Date endTime;
    @ApiModelProperty(value = "1：买家 2：冻品管家")
    private java.lang.String applySide;
    @ApiModelProperty(value = "1：申请中 2：专属会员")
    private java.lang.String applyStatus;
    @ApiModelProperty(value = "申请日时")
    private java.util.Date applyTime;
    @ApiModelProperty(value = "省编码")
    private String provinceCode;
    @ApiModelProperty(value = "地区编码")
    private String cityCode;
    @ApiModelProperty(value = "区编码")
    private String districtCode;
    @ApiModelProperty(value = "买家地址")
    private String buyerAddr;
    @ApiModelProperty(value = "销售对象类型")
    private String salesTargetType;
    @ApiModelProperty(value = "产品类别编码")
    private String classCode;
    @ApiModelProperty(value = "买家联系电话")
    private String busiTel;
    @ApiModelProperty(value = "老板姓名")
    private String employeeName;
    @ApiModelProperty(value = "买家名称")
    private String buyerName;
    @ApiModelProperty(value = "店铺号")
    private String buyerShop;
    @ApiModelProperty(value = "买家主码")
    private String buyerCode;
    @ApiModelProperty(value = "买家类型")
    private String buyerType;
    @ApiModelProperty(value = "菜场ID/批发市场ID")
    private String marketId;

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
     * Getter method for property <tt>salesTargetType</tt>.
     *
     * @return property value of salesTargetType
     */
    public String getSalesTargetType() {
        return salesTargetType;
    }

    /**
     * Setter method for property <tt>salesTargetType</tt>.
     *
     * @param salesTargetType value to be assigned to property salesTargetType
     */
    public void setSalesTargetType(String salesTargetType) {
        this.salesTargetType = salesTargetType;
    }

    /**
     * Getter method for property <tt>classCode</tt>.
     *
     * @return property value of classCode
     */
    public String getClassCode() {
        return classCode;
    }

    /**
     * Setter method for property <tt>classCode</tt>.
     *
     * @param classCode value to be assigned to property classCode
     */
    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    /**
     * Getter method for property <tt>slCode</tt>.
     *
     * @return property value of slCode
     */
    public String getSlCode() {
        return slCode;
    }

    /**
     * Setter method for property <tt>slCode</tt>.
     *
     * @param slCode value to be assigned to property slCode
     */
    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    /**
     * Getter method for property <tt>houseCode</tt>.
     *
     * @return property value of houseCode
     */
    public String getHouseCode() {
        return houseCode;
    }

    /**
     * Setter method for property <tt>houseCode</tt>.
     *
     * @param houseCode value to be assigned to property houseCode
     */
    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    /**
     * Getter method for property <tt>buyerId</tt>.
     *
     * @return property value of buyerId
     */
    public String getBuyerId() {
        return buyerId;
    }

    /**
     * Setter method for property <tt>buyerId</tt>.
     *
     * @param buyerId value to be assigned to property buyerId
     */
    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    /**
     * Getter method for property <tt>startTime</tt>.
     *
     * @return property value of startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * Setter method for property <tt>startTime</tt>.
     *
     * @param startTime value to be assigned to property startTime
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * Getter method for property <tt>endTime</tt>.
     *
     * @return property value of endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * Setter method for property <tt>endTime</tt>.
     *
     * @param endTime value to be assigned to property endTime
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * Getter method for property <tt>applySide</tt>.
     *
     * @return property value of applySide
     */
    public String getApplySide() {
        return applySide;
    }

    /**
     * Setter method for property <tt>applySide</tt>.
     *
     * @param applySide value to be assigned to property applySide
     */
    public void setApplySide(String applySide) {
        this.applySide = applySide;
    }

    /**
     * Getter method for property <tt>applyStatus</tt>.
     *
     * @return property value of applyStatus
     */
    public String getApplyStatus() {
        return applyStatus;
    }

    /**
     * Setter method for property <tt>applyStatus</tt>.
     *
     * @param applyStatus value to be assigned to property applyStatus
     */
    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

    /**
     * Getter method for property <tt>applyTime</tt>.
     *
     * @return property value of applyTime
     */
    public Date getApplyTime() {
        return applyTime;
    }

    /**
     * Setter method for property <tt>applyTime</tt>.
     *
     * @param applyTime value to be assigned to property applyTime
     */
    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
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
}
