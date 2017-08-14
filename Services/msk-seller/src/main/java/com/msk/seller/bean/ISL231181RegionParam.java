package com.msk.seller.bean;

import com.msk.core.entity.SlSeller;

/**
 * Created by Administrator on 2016/3/17.
 */
public class ISL231181RegionParam extends SlSeller {
    /** 大区名称 */
    private String areaName;
    /** 物流区名称 */
    private String lgcsAreaName;
    /** 省编码名称 */
    private String provinceName;
    /** 地区编码名称 */
    private String cityName;
    /** 区编码名称 */
    private String districtName;

    /**
     * Getter method for property <tt>areaName</tt>.
     *
     * @return property value of areaName
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * Setter method for property <tt>areaName</tt>.
     *
     * @param areaName value to be assigned to property areaName
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName;
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
}
