package com.msk.price.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.zip.DataFormatException;

/**
 * 供应商显示项目设置
 * SP171196Bean
 */
@ApiModel(value = "ISP171186Param",description = "供应商显示项目设置")
public class SP171196Bean extends BaseEntity {

    @ApiModelProperty(value = "主键ID")
    private Long viewId;

    @ApiModelProperty(value = "物流区")
    private String lgcsCode;

    @ApiModelProperty(value = "物流区名称")
    private String lgcsName;

    @ApiModelProperty(value = "卖家Code")
    private String slCode;

    @ApiModelProperty(value = "卖家名称")
    private String slName;

    @ApiModelProperty(value = "字段名 SUPPLY;NUM;PRICE")
    private String viewKey;

    @ApiModelProperty(value = "字段名 SUPPLY;NUM;PRICE")
    private String[] viewKeys;

    @ApiModelProperty(value = "是否显示供应商")
    private String isSupply;

    @ApiModelProperty(value = "是否显示申报数量")
    private String isNum;

    @ApiModelProperty(value = "是否显示申报价格")
    private String isPrice;

    @ApiModelProperty(value = "是否有显示权限 0：无权限；1：有权限 ")
    private String viewFlg;

    @ApiModelProperty(value = "使用系统")
    private String systemType;

    @ApiModelProperty(value = "开始时间")
    private Integer startTime;

    @ApiModelProperty(value = "结束时间")
    private Integer endTime;

    @ApiModelProperty(value = "价盘周期")
    private String pricePeriod;

    @ApiModelProperty(value = "有效开始时间")
    private Date startDate;

    @ApiModelProperty(value = "有效结束时间")
    private Date endDate;

    @ApiModelProperty(value = "有效开始时间")
    private String startDateStr;

    @ApiModelProperty(value = "有效结束时间")
    private String endDateStr;

    public Long getViewId() {
        return viewId;
    }

    public void setViewId(Long viewId) {
        this.viewId = viewId;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public String getLgcsName() {
        return lgcsName;
    }

    public void setLgcsName(String lgcsName) {
        this.lgcsName = lgcsName;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getSlName() {
        return slName;
    }

    public void setSlName(String slName) {
        this.slName = slName;
    }

    public String getViewKey() {
        return viewKey;
    }

    public void setViewKey(String viewKey) {
        this.viewKey = viewKey;
    }

    public String getIsSupply() {
        return isSupply;
    }

    public void setIsSupply(String isSupply) {
        this.isSupply = isSupply;
    }

    public String getIsNum() {
        return isNum;
    }

    public void setIsNum(String isNum) {
        this.isNum = isNum;
    }

    public String getIsPrice() {
        return isPrice;
    }

    public void setIsPrice(String isPrice) {
        this.isPrice = isPrice;
    }

    public String getViewFlg() {
        return viewFlg;
    }

    public void setViewFlg(String viewFlg) {
        this.viewFlg = viewFlg;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public String[] getViewKeys() {
        return viewKeys;
    }

    public void setViewKeys(String[] viewKeys) {
        this.viewKeys = viewKeys;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public String getPricePeriod() {
        return pricePeriod;
    }

    public void setPricePeriod(String pricePeriod) {
        this.pricePeriod = pricePeriod;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStartDateStr() {
        return startDateStr;
    }

    public void setStartDateStr(String startDateStr) {
        this.startDateStr = startDateStr;
    }

    public String getEndDateStr() {
        return endDateStr;
    }

    public void setEndDateStr(String endDateStr) {
        this.endDateStr = endDateStr;
    }
}
