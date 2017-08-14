package com.msk.buyers.bean;

import com.msk.core.entity.ByBuyerPdCla;

/**
 * Created by tao_zhifa on 2016/9/2.
 */
public class IBY121322Bean extends ByBuyerPdCla {

    private String lgcsAreaCode;
    /** LGCS_AREA_NAME */
    private String lgcsAreaName;
    /** CITY_CODE */
    private String cityCode;
    /** CITY_NAME */
    private String cityName;
    /** 参考CONSTANT表 */
    private String superiorType;
    /** SUPERIOR_NAME */
    private String superiorName;

    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    public String getLgcsAreaName() {
        return lgcsAreaName;
    }


    public void setLgcsAreaName(String lgcsAreaName) {
        this.lgcsAreaName = lgcsAreaName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getSuperiorType() {
        return superiorType;
    }

    public void setSuperiorType(String superiorType) {
        this.superiorType = superiorType;
    }

    public String getSuperiorName() {
        return superiorName;
    }

    public void setSuperiorName(String superiorName) {
        this.superiorName = superiorName;
    }
}
