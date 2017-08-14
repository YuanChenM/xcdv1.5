package com.msk.br.bean;


import com.msk.common.bean.RsPageResult;
import com.msk.core.entity.BrBuyerPool;

import java.util.List;

public class IBR121411RsResult extends RsPageResult {

    private List<BrBuyerPool> brBuyerPoolList;

    /**
     * Getter method for property <tt>brBuyerPoolList</tt>.
     *
     * @return property value of brBuyerPoolList
     */
    public List<BrBuyerPool> getBrBuyerPoolList() {
        return brBuyerPoolList;
    }

    /**
     * Setter method for property <tt>brBuyerPoolList</tt>.
     *
     * @param brBuyerPoolList value to be assigned to property brBuyerPoolList
     */
    public void setBrBuyerPoolList(List<BrBuyerPool> brBuyerPoolList) {
        this.brBuyerPoolList = brBuyerPoolList;
    }
}
