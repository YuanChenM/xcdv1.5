package com.msk.br.bean;

import com.msk.common.bean.RsPageResult;
import com.msk.core.entity.BrBuyerMarketingStatusHistory;

import java.util.Date;
import java.util.List;

/**
 * IBR121412RsResult.
 *
 */
public class IBR121412RsResult extends RsPageResult {

    private List<IBR121412RsBean> modifyBuyerList;

    /**
     * Getter method for property <tt>modifyBuyerList</tt>.
     *
     * @return property value of modifyBuyerList
     */
    public List<IBR121412RsBean> getModifyBuyerList() {
        return modifyBuyerList;
    }

    /**
     * Setter method for property <tt>modifyBuyerList</tt>.
     *
     * @param modifyBuyerList value to be assigned to property modifyBuyerList
     */
    public void setModifyBuyerList(List<IBR121412RsBean> modifyBuyerList) {
        this.modifyBuyerList = modifyBuyerList;
    }
}
