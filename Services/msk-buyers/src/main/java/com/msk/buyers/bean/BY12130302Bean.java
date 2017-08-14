package com.msk.buyers.bean;

import com.hoperun.core.bean.BasePageParam;

import java.util.Date;

/**
 * BY12130302Bean.
 *
 * @author zhou_yajun
 */
public class BY12130302Bean extends BasePageParam {

    private String buyerId;
    private String searchType;

    /**
     * Getter method for property <tt>buyerId</tt>.
     *
     * @return property value of buyerId
     */
    public String getBuyerId() {
        return buyerId;
    }

    /**
     * Setter method for property <tt>buyerId</tt>.
     *
     * @param buyerId value to be assigned to property buyerId
     */
    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    /**
     * Getter method for property <tt>searchType</tt>.
     *
     * @return property value of searchType
     */
    public String getSearchType() {
        return searchType;
    }

    /**
     * Setter method for property <tt>searchType</tt>.
     *
     * @param searchType value to be assigned to property searchType
     */
    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }
}
