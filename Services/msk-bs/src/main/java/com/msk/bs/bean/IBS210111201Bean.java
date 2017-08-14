package com.msk.bs.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2016/4/22.
 */
@ApiModel(value = "IBS210111201Bean",description = "买手基本信息")
public class IBS210111201Bean extends BaseEntity{
    @ApiModelProperty(value = "买家ID")
    private java.lang.String buyerId;
    @ApiModelProperty(value = "买家编码")
    private java.lang.String buyerCode;
    @ApiModelProperty(value = "买家名称")
    private java.lang.String buyerName;
    @ApiModelProperty(value = "买家地址")
    private java.lang.String buyerAddr;
    @ApiModelProperty(value = "菜场或批发市场的ID")
    private java.lang.String superiorId;
    @ApiModelProperty(value = "所属分类定性定级描述")
    private java.lang.String superiorQua;
    @ApiModelProperty(value = "参考CONSTANT表")
    private java.lang.String superiorType;
    @ApiModelProperty(value = "买家分类名称")
    private java.lang.String superiorName;
    @ApiModelProperty(value = "省编码")
    private java.lang.String provinceCode;
    @ApiModelProperty(value = "物流区编码")
    private java.lang.String lgcsAreaCode;
    @ApiModelProperty(value = "地区（城市）编码 ")
    private java.lang.String cityCode;
    @ApiModelProperty(value = "区（县）编码")
    private java.lang.String districtCode;
    @ApiModelProperty(value = "买家网站")
    private java.lang.String buyerWebsite;
    @ApiModelProperty(value = "买家微信公众号")
    private java.lang.String buyerWechat;
    @ApiModelProperty(value = "店铺号")
    private java.lang.String storeNo;
    @ApiModelProperty(value = "店铺面积")
    private java.math.BigDecimal storeArea;
    @ApiModelProperty(value = "营业电话")
    private java.lang.String busiTel;
    @ApiModelProperty(value = "员工人数")
    private java.lang.Integer employeesNum;
    @ApiModelProperty(value = "参考CONSTANT表，逗号隔开，多选")
    private java.lang.String paymentType;
    @ApiModelProperty(value = "参考CONSTANT表，逗号隔开，多选")
    private java.lang.String planOrderGap;
    @ApiModelProperty(value = "计划订货量")
    private java.lang.String planOrderNum;
    @ApiModelProperty(value = "实际订货间隙")
    private java.lang.String actualOrderGap;
    @ApiModelProperty(value = "实际订货量")
    private java.lang.String actualOrderNum;
    @ApiModelProperty(value = "营销/ 销售状态")
    private java.lang.String marketingsStatus;
    @ApiModelProperty(value = "注册来源")
    private java.lang.String registerSource;
    @ApiModelProperty(value = "省名")
    private String provinceName;
    @ApiModelProperty(value = "地区名")
    private String cityName;
    @ApiModelProperty(value = "区名")
    private String districtName;
    @ApiModelProperty(value = "区名1")
    private String districtName1;
    @ApiModelProperty(value = "买家电话")
    private String buyerTel;

    /**
     * <p>买家ID。</p>
     *
     * @return the 买家ID
     */
    public java.lang.String getBuyerId() {
        return buyerId;
    }

    /**
     * <p>买家ID。</p>
     *
     * @param buyerId 买家ID。
     */
    public void setBuyerId(java.lang.String buyerId) {
        this.buyerId = buyerId;
    }

    /**
     * <p>买家编码。</p>
     *
     * @return the 买家编码
     */
    public java.lang.String getBuyerCode() {
        return buyerCode;
    }

    /**
     * <p>买家编码。</p>
     *
     * @param buyerCode 买家编码。
     */
    public void setBuyerCode(java.lang.String buyerCode) {
        this.buyerCode = buyerCode;
    }

    /**
     * <p>买家名称。</p>
     *
     * @return the 买家名称
     */
    public java.lang.String getBuyerName() {
        return buyerName;
    }

    /**
     * <p>买家名称。</p>
     *
     * @param buyerName 买家名称。
     */
    public void setBuyerName(java.lang.String buyerName) {
        this.buyerName = buyerName;
    }

    /**
     * <p>买家地址。</p>
     *
     * @return the 买家地址
     */
    public java.lang.String getBuyerAddr() {
        return buyerAddr;
    }

    /**
     * <p>买家地址。</p>
     *
     * @param buyerAddr 买家地址。
     */
    public void setBuyerAddr(java.lang.String buyerAddr) {
        this.buyerAddr = buyerAddr;
    }

    /**
     * <p>菜场或批发市场的ID。</p>
     *
     * @return the 菜场或批发市场的ID
     */
    public java.lang.String getSuperiorId() {
        return superiorId;
    }

    /**
     * <p>菜场或批发市场的ID。</p>
     *
     * @param superiorId 菜场或批发市场的ID。
     */
    public void setSuperiorId(java.lang.String superiorId) {
        this.superiorId = superiorId;
    }

    /**
     * <p>所属分类定性定级描述。</p>
     *
     * @return the 所属分类定性定级描述
     */
    public java.lang.String getSuperiorQua() {
        return superiorQua;
    }

    /**
     * <p>所属分类定性定级描述。</p>
     *
     * @param superiorQua 所属分类定性定级描述。
     */
    public void setSuperiorQua(java.lang.String superiorQua) {
        this.superiorQua = superiorQua;
    }

    /**
     * <p>参考CONSTANT表。</p>
     *
     * @return the 参考CONSTANT表
     */
    public java.lang.String getSuperiorType() {
        return superiorType;
    }

    /**
     * <p>参考CONSTANT表。</p>
     *
     * @param superiorType 参考CONSTANT表。
     */
    public void setSuperiorType(java.lang.String superiorType) {
        this.superiorType = superiorType;
    }

    /**
     * <p>买家分类名称。</p>
     *
     * @return the 买家分类名称
     */
    public java.lang.String getSuperiorName() {
        return superiorName;
    }

    /**
     * <p>买家分类名称。</p>
     *
     * @param superiorName 买家分类名称。
     */
    public void setSuperiorName(java.lang.String superiorName) {
        this.superiorName = superiorName;
    }

    /**
     * <p>省编码。</p>
     *
     * @return the 省编码
     */
    public java.lang.String getProvinceCode() {
        return provinceCode;
    }

    /**
     * <p>省编码。</p>
     *
     * @param provinceCode 省编码。
     */
    public void setProvinceCode(java.lang.String provinceCode) {
        this.provinceCode = provinceCode;
    }

    /**
     * <p>物流区编码。</p>
     *
     * @return the 物流区编码
     */
    public java.lang.String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    /**
     * <p>物流区编码。</p>
     *
     * @param lgcsAreaCode 物流区编码。
     */
    public void setLgcsAreaCode(java.lang.String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    /**
     * <p>地区（城市）编码。</p>
     *
     * @return the 地区（城市）编码
     */
    public java.lang.String getCityCode() {
        return cityCode;
    }

    /**
     * <p>地区（城市）编码。</p>
     *
     * @param cityCode 地区（城市）编码。
     */
    public void setCityCode(java.lang.String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * <p>区（县）编码。</p>
     *
     * @return the 区（县）编码
     */
    public java.lang.String getDistrictCode() {
        return districtCode;
    }

    /**
     * <p>区（县）编码。</p>
     *
     * @param districtCode 区（县）编码。
     */
    public void setDistrictCode(java.lang.String districtCode) {
        this.districtCode = districtCode;
    }

    /**
     * <p>买家网站。</p>
     *
     * @return the 买家网站
     */
    public java.lang.String getBuyerWebsite() {
        return buyerWebsite;
    }

    /**
     * <p>买家网站。</p>
     *
     * @param buyerWebsite 买家网站。
     */
    public void setBuyerWebsite(java.lang.String buyerWebsite) {
        this.buyerWebsite = buyerWebsite;
    }

    /**
     * <p>买家微信公众号。</p>
     *
     * @return the 买家微信公众号
     */
    public java.lang.String getBuyerWechat() {
        return buyerWechat;
    }

    /**
     * <p>买家微信公众号。</p>
     *
     * @param buyerWechat 买家微信公众号。
     */
    public void setBuyerWechat(java.lang.String buyerWechat) {
        this.buyerWechat = buyerWechat;
    }

    /**
     * <p>店铺号。</p>
     *
     * @return the 店铺号
     */
    public java.lang.String getStoreNo() {
        return storeNo;
    }

    /**
     * <p>店铺号。</p>
     *
     * @param storeNo 店铺号。
     */
    public void setStoreNo(java.lang.String storeNo) {
        this.storeNo = storeNo;
    }

    /**
     * <p>店铺面积。</p>
     *
     * @return the 店铺面积
     */
    public java.math.BigDecimal getStoreArea() {
        return storeArea;
    }

    /**
     * <p>店铺面积。</p>
     *
     * @param storeArea 店铺面积。
     */
    public void setStoreArea(java.math.BigDecimal storeArea) {
        this.storeArea = storeArea;
    }

    /**
     * <p>营业电话。</p>
     *
     * @return the 营业电话
     */
    public java.lang.String getBusiTel() {
        return busiTel;
    }

    /**
     * <p>营业电话。</p>
     *
     * @param busiTel 营业电话。
     */
    public void setBusiTel(java.lang.String busiTel) {
        this.busiTel = busiTel;
    }

    /**
     * <p>员工人数。</p>
     *
     * @return the 员工人数
     */
    public java.lang.Integer getEmployeesNum() {
        return employeesNum;
    }

    /**
     * <p>员工人数。</p>
     *
     * @param employeesNum 员工人数。
     */
    public void setEmployeesNum(java.lang.Integer employeesNum) {
        this.employeesNum = employeesNum;
    }

    /**
     * <p>参考CONSTANT表，逗号隔开，多选。</p>
     *
     * @return the 参考CONSTANT表，逗号隔开，多选
     */
    public java.lang.String getPaymentType() {
        return paymentType;
    }

    /**
     * <p>参考CONSTANT表，逗号隔开，多选。</p>
     *
     * @param paymentType 参考CONSTANT表，逗号隔开，多选。
     */
    public void setPaymentType(java.lang.String paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * <p>计划订货间隙。</p>
     *
     * @return the 计划订货间隙
     */
    public java.lang.String getPlanOrderGap() {
        return planOrderGap;
    }

    /**
     * <p>计划订货间隙。</p>
     *
     * @param planOrderGap 计划订货间隙。
     */
    public void setPlanOrderGap(java.lang.String planOrderGap) {
        this.planOrderGap = planOrderGap;
    }

    /**
     * <p>计划订货量。</p>
     *
     * @return the 计划订货量
     */
    public java.lang.String getPlanOrderNum() {
        return planOrderNum;
    }

    /**
     * <p>计划订货量。</p>
     *
     * @param planOrderNum 计划订货量。
     */
    public void setPlanOrderNum(java.lang.String planOrderNum) {
        this.planOrderNum = planOrderNum;
    }

    /**
     * <p>实际订货间隙。</p>
     *
     * @return the 实际订货间隙
     */
    public java.lang.String getActualOrderGap() {
        return actualOrderGap;
    }

    /**
     * <p>实际订货间隙。</p>
     *
     * @param actualOrderGap 实际订货间隙。
     */
    public void setActualOrderGap(java.lang.String actualOrderGap) {
        this.actualOrderGap = actualOrderGap;
    }

    /**
     * <p>实际订货量。</p>
     *
     * @return the 实际订货量
     */
    public java.lang.String getActualOrderNum() {
        return actualOrderNum;
    }

    /**
     * <p>实际订货量。</p>
     *
     * @param actualOrderNum 实际订货量。
     */
    public void setActualOrderNum(java.lang.String actualOrderNum) {
        this.actualOrderNum = actualOrderNum;
    }

    /**
     * <p>营销/ 销售状态。</p>
     *
     * @return the 营销/ 销售状态
     */
    public java.lang.String getMarketingsStatus() {
        return marketingsStatus;
    }

    /**
     * <p>营销/ 销售状态。</p>
     *
     * @param marketingsStatus 营销/ 销售状态。
     */
    public void setMarketingsStatus(java.lang.String marketingsStatus) {
        this.marketingsStatus = marketingsStatus;
    }

    /**
     * <p>注册来源。</p>
     *
     * @return the 注册来源
     */
    public java.lang.String getRegisterSource() {
        return registerSource;
    }

    /**
     * <p>注册来源。</p>
     *
     * @param registerSource 注册来源。
     */
    public void setRegisterSource(java.lang.String registerSource) {
        this.registerSource = registerSource;
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
     * Getter method for property <tt>districtName1</tt>.
     *
     * @return property value of districtName1
     */
    public String getDistrictName1() {
        return districtName1;
    }

    /**
     * Setter method for property <tt>districtName1</tt>.
     *
     * @param districtName1 value to be assigned to property districtName1
     */
    public void setDistrictName1(String districtName1) {
        this.districtName1 = districtName1;
    }

    public String getBuyerTel() {
        return buyerTel;
    }

    public void setBuyerTel(String buyerTel) {
        this.buyerTel = buyerTel;
    }
}
