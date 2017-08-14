package com.msk.district.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liu_yan2 on 2016/4/27.
 */
public class DistrictResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<AreaBean> areaList;

    private List<LgcsAreaBean> lgcsAreaList;

    private  List<ProvinceBean> provinceList;

    private List<CityBean> cityList;

    private List<DistrictBean> districtList;

    public List<AreaBean> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<AreaBean> areaList) {
        this.areaList = areaList;
    }

    public List<DistrictBean> getDistrictList() {
        return districtList;
    }

    public void setDistrictList(List<DistrictBean> districtList) {
        this.districtList = districtList;
    }

    public List<CityBean> getCityList() {
        return cityList;
    }

    public void setCityList(List<CityBean> cityList) {
        this.cityList = cityList;
    }

    public List<ProvinceBean> getProvinceList() {
        return provinceList;
    }

    public void setProvinceList(List<ProvinceBean> provinceList) {
        this.provinceList = provinceList;
    }

    public List<LgcsAreaBean> getLgcsAreaList() {
        return lgcsAreaList;
    }

    public void setLgcsAreaList(List<LgcsAreaBean> lgcsAreaList) {
        this.lgcsAreaList = lgcsAreaList;
    }
}
