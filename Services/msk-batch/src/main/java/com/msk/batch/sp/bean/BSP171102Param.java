package com.msk.batch.sp.bean;


import com.hoperun.core.bean.BaseParam;

/**
 * Created by sun_jiaju
 */
public class BSP171102Param extends BaseParam {
    /** 需求预测年月 */
    private String forecastYm;
    /** 当月 */
    private String thisYm;

    public String getForecastYm() {
        return forecastYm;
    }

    public void setForecastYm(String forecastYm) {
        this.forecastYm = forecastYm;
    }

    public String getThisYm() {
        return thisYm;
    }

    public void setThisYm(String thisYm) {
        this.thisYm = thisYm;
    }
}
