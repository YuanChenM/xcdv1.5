package com.msk.price.bean;

import com.hoperun.core.bean.BaseParam;

/**
 * SP171195Param
 */
public class SP171195Param extends BaseParam{
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    /**
     * 需求预测年月
     */
    private String forecastYm;
    /**
     * 物流区CODE
     */
    private String lgcsCode;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getForecastYm() {
        return forecastYm;
    }

    public void setForecastYm(String forecastYm) {
        this.forecastYm = forecastYm;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }
}
