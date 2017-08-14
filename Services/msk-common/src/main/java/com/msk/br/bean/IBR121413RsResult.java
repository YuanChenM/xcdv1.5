package com.msk.br.bean;

import com.msk.common.bean.RsPageResult;

import java.util.List;

/**
 * IBR121413RsResult.
 *
 */
public class IBR121413RsResult extends RsPageResult {

    private List<IBR121413RsBean> buyerPdClassesList;

    /**
     * Getter method for property <tt>buyerPdClassesList</tt>.
     *
     * @return property value of buyerPdClassesList
     */
    public List<IBR121413RsBean> getBuyerPdClassesList() {
        return buyerPdClassesList;
    }

    /**
     * Setter method for property <tt>buyerPdClassesList</tt>.
     *
     * @param buyerPdClassesList value to be assigned to property buyerPdClassesList
     */
    public void setBuyerPdClassesList(List<IBR121413RsBean> buyerPdClassesList) {
        this.buyerPdClassesList = buyerPdClassesList;
    }
}
