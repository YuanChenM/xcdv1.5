package com.msk.district.bean;

import com.msk.core.entity.MdArea;

import java.util.List;

/**
 * Created by liu_yan2 on 2016/4/28.
 */
public class AreaBean extends MdArea{
    //TO DO
    private List<LgcsAreaBean> lgcsAreaList;

    private  List<ProvinceBean> provinceList;

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
