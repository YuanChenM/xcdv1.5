package com.msk.district.bean;

import com.msk.core.entity.MdLogisticsArea;

import java.util.List;

/**
 * Created by liu_yan2 on 2016/4/28.
 */
public class LgcsAreaBean extends MdLogisticsArea{
    /** 大区编码 */
    private String areaCode;
    /** 大区名称 */
    private String areaName;

    private List<CityBean> cityList;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public List<CityBean> getCityList() {
        return cityList;
    }

    public void setCityList(List<CityBean> cityList) {
        this.cityList = cityList;
    }
}
