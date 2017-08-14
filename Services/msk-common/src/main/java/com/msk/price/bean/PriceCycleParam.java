package com.msk.price.bean;

import com.hoperun.core.bean.BaseParam;

import java.util.Date;

/**
 * Created by sun_jiaju on 2016/5/12.
 */
public class PriceCycleParam extends BaseParam {

    //当前日期
    private Date currentDate;
    //价盘code
    private String priceCode;

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    /**
     * Getter method for property <tt>priceCode</tt>.
     *
     * @return property value of priceCode
     */
    public String getPriceCode() {
        return priceCode;
    }

    /**
     * Setter method for property <tt>priceCode</tt>.
     *
     * @param priceCode value to be assigned to property priceCode
     */
    public void setPriceCode(String priceCode) {
        this.priceCode = priceCode;
    }
}
