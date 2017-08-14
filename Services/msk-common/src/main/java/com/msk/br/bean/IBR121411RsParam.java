package com.msk.br.bean;

import com.msk.common.bean.RsPageParam;

import java.util.List;

public class IBR121411RsParam extends RsPageParam {

    private String buyerId;

    private List<String> buyerIdList;

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

    public List<String> getBuyerIdList() {
        return buyerIdList;
    }

    public void setBuyerIdList(List<String> buyerIdList) {
        this.buyerIdList = buyerIdList;
    }
}



