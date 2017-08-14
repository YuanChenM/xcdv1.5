package com.msk.buyers.bean;

import com.msk.common.bean.RsPageResult;

import java.util.List;

/**
 * BY12130302Bean.
 *
 * @author zhou_yajun
 */
public class BY12130302Result extends RsPageResult {

    private List<BY12130302RsParam> slHouseSaleList;

    /**
     * Getter method for property <tt>slHouseSaleList</tt>.
     *
     * @return property value of slHouseSaleList
     */
    public List<BY12130302RsParam> getSlHouseSaleList() {
        return slHouseSaleList;
    }

    /**
     * Setter method for property <tt>slHouseSaleList</tt>.
     *
     * @param slHouseSaleList value to be assigned to property slHouseSaleList
     */
    public void setSlHouseSaleList(List<BY12130302RsParam> slHouseSaleList) {
        this.slHouseSaleList = slHouseSaleList;
    }
}
