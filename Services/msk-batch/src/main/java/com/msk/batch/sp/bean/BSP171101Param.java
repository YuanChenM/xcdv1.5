package com.msk.batch.sp.bean;


import com.hoperun.core.bean.BaseParam;

/**
 * Created by sun_jiaju
 */
public class BSP171101Param extends BaseParam {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** 价盘周期 */
    private String pricePeriod;


    public String getPricePeriod() {
        return pricePeriod;
    }

    public void setPricePeriod(String pricePeriod) {
        this.pricePeriod = pricePeriod;
    }
}
