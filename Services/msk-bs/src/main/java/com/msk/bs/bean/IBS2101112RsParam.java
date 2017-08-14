package com.msk.bs.bean;

import com.msk.common.bean.RsPageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by cx on 2016/4/14.
 */
@ApiModel(value = "IBS2101112RsParam",description = "param")
public class IBS2101112RsParam extends RsPageParam {
    @ApiModelProperty(value = "买手ID")
    private String slCode;
    @ApiModelProperty(value = "买家ID")
    private String buyerId;
    @ApiModelProperty(value = "1:专属买家、2：抢单买家")
    private String buyerFlag;
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
    @ApiModelProperty(value = "管家ID")
    private String houseCode;
    @ApiModelProperty(value = "申请状态")
    private String applyStatus;
    @ApiModelProperty(value = "开始时间")
    private Date startDate;
    @ApiModelProperty(value = "结束时间")
    private Date endDate;
    @ApiModelProperty(value = "1:只查过去的履历、2：包含的现有的冻品管家和买家关系数据")
    private String searchDataFlag;

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
     * Getter method for property <tt>startDate</tt>.
     *
     * @return property value of startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Setter method for property <tt>startDate</tt>.
     *
     * @param startDate value to be assigned to property startDate
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Getter method for property <tt>endDate</tt>.
     *
     * @return property value of endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Setter method for property <tt>endDate</tt>.
     *
     * @param endDate value to be assigned to property endDate
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Getter method for property <tt>searchDataFlag</tt>.
     *
     * @return property value of searchDataFlag
     */
    public String getSearchDataFlag() {
        return searchDataFlag;
    }

    /**
     * Setter method for property <tt>searchDataFlag</tt>.
     *
     * @param searchDataFlag value to be assigned to property searchDataFlag
     */
    public void setSearchDataFlag(String searchDataFlag) {
        this.searchDataFlag = searchDataFlag;
    }
}
