package com.msk.price.bean;

import com.hoperun.core.bean.BaseParam;

import java.util.Date;

/**
 * 供应商显示项目设置
 * SP171196Param
 */
public class SP171196Param extends BaseParam{
    /**物流区*/
    private String lgcsCode;
    /**卖家Code*/
    private String slCode;
    /**字段名 SUPPLY;NUM;PRICE*/
    private String viewKey;
    /*价盘周期*/
    private String pricePeriod;

    //招标数量发布明细表
    /*表ID*/
    private Long settingId;
    /*价盘周期编码（16081）*/
    private String pricecycleCode;
    /*设置有效开始时间*/
    private String startDateStr;
    /*设置有效结束时间*/
    private String endDateStr;
    /*设置有效开始时间*/
    private Date validTimeStart;
    /*设置有效结束时间*/
    private Date validTimeEnd;
    /*使用系统*/
    private String systemType;


    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getViewKey() {
        return viewKey;
    }

    public void setViewKey(String viewKey) {
        this.viewKey = viewKey;
    }

    public String getPricePeriod() {
        return pricePeriod;
    }

    public void setPricePeriod(String pricePeriod) {
        this.pricePeriod = pricePeriod;
    }

    public Long getSettingId() {
        return settingId;
    }

    public void setSettingId(Long settingId) {
        this.settingId = settingId;
    }

    public String getPricecycleCode() {
        return pricecycleCode;
    }

    public void setPricecycleCode(String pricecycleCode) {
        this.pricecycleCode = pricecycleCode;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
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

    public Date getValidTimeStart() {
        return validTimeStart;
    }

    public void setValidTimeStart(Date validTimeStart) {
        this.validTimeStart = validTimeStart;
    }

    public Date getValidTimeEnd() {
        return validTimeEnd;
    }

    public void setValidTimeEnd(Date validTimeEnd) {
        this.validTimeEnd = validTimeEnd;
    }
}
