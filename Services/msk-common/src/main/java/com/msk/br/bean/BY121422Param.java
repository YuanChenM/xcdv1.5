package com.msk.br.bean;
import com.hoperun.core.bean.BasePageParam;

/**
 * Created by guan_zhongheng on 2016/8/10.
 */
public class BY121422Param extends BasePageParam {
    //买家ID
    private String buyerId;
    //物流区
    private String lgcsAreaName;
    //物流区id
    private String lgcsAreaCode;
    //地区（城市）名称
    private String cityName;
    //地区（城市）id
    private String cityCode;
    //区县
    private String districtName;
    //区县id
    private String districtCode;
    //一级分类名称
    private String classesCode;
    //二级分类名称
    private String machiningCode;
    //报表开始时间
    private String marketStartTime;
    //报表结束时间
    private String marketEndTime;

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getLgcsAreaName() {
        return lgcsAreaName;
    }

    public void setLgcsAreaName(String lgcsAreaName) {
        this.lgcsAreaName = lgcsAreaName;
    }

    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getClassesCode() {
        return classesCode;
    }

    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    public String getMachiningCode() {
        return machiningCode;
    }

    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    public String getMarketStartTime() {
        return marketStartTime;
    }

    public void setMarketStartTime(String marketStartTime) {
        this.marketStartTime = marketStartTime;
    }

    public String getMarketEndTime() {
        return marketEndTime;
    }

    public void setMarketEndTime(String marketEndTime) {
        this.marketEndTime = marketEndTime;
    }
}
