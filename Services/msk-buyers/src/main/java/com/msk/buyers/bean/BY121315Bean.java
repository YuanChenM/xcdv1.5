package com.msk.buyers.bean;

import com.msk.core.entity.ByBuyerBasicInfo;

import java.util.TreeMap;

/**
 * Created by zhou_yajun on 2016/7/8.
 */
public class BY121315Bean extends ByBuyerBasicInfo{

    /** 营销期状态*/
    private TreeMap<String,String> marketPeriod;
    /** 销售期状态*/
    private TreeMap<String,String> salesPeriod;
    /** 异常*/
    private TreeMap<String,String> exceptional;

    /**
     * Getter method for property <tt>marketPeriod</tt>.
     *
     * @return property value of marketPeriod
     */
    public TreeMap<String, String> getMarketPeriod() {
        return marketPeriod;
    }

    /**
     * Setter method for property <tt>marketPeriod</tt>.
     *
     * @param marketPeriod value to be assigned to property marketPeriod
     */
    public void setMarketPeriod(TreeMap<String, String> marketPeriod) {
        this.marketPeriod = marketPeriod;
    }

    /**
     * Getter method for property <tt>salesPeriod</tt>.
     *
     * @return property value of salesPeriod
     */
    public TreeMap<String, String> getSalesPeriod() {
        return salesPeriod;
    }

    /**
     * Setter method for property <tt>salesPeriod</tt>.
     *
     * @param salesPeriod value to be assigned to property salesPeriod
     */
    public void setSalesPeriod(TreeMap<String, String> salesPeriod) {
        this.salesPeriod = salesPeriod;
    }

    /**
     * Getter method for property <tt>exceptional</tt>.
     *
     * @return property value of exceptional
     */
    public TreeMap<String, String> getExceptional() {
        return exceptional;
    }

    /**
     * Setter method for property <tt>exceptional</tt>.
     *
     * @param exceptional value to be assigned to property exceptional
     */
    public void setExceptional(TreeMap<String, String> exceptional) {
        this.exceptional = exceptional;
    }
}
