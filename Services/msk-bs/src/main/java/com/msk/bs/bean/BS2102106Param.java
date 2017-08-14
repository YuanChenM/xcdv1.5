package com.msk.bs.bean;

import com.hoperun.core.bean.BaseParam;

/**
 * Created by yang_chunyan on 2016/8/1.
 */
public class BS2102106Param extends BaseParam {
    /**
     * 区域编码
     */
    private String cityCode;
    /**
     * 地区编码
     */
    private String districCode;
    /**
     * 一级分类
     */
    private String classesName;
    /**
     * 二级分类
     */
    private String machiningName;

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getDistricCode() {
        return districCode;
    }

    public void setDistricCode(String districCode) {
        this.districCode = districCode;
    }

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    public String getMachiningName() {
        return machiningName;
    }

    public void setMachiningName(String machiningName) {
        this.machiningName = machiningName;
    }
}
