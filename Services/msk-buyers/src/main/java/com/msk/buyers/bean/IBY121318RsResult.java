package com.msk.buyers.bean;

import com.msk.common.bean.RsPageResult;
import com.msk.core.entity.ByBuyerReportManager;

import java.util.List;

/**
 *
 *
 * @author zhou_yajun
 */
public class IBY121318RsResult extends RsPageResult {

    private List<BY121318Bean> buyerReportManagerList;

    /**
     * Getter method for property <tt>buyerReportManagerList</tt>.
     *
     * @return property value of buyerReportManagerList
     */
    public List<BY121318Bean> getBuyerReportManagerList() {
        return buyerReportManagerList;
    }

    /**
     * Setter method for property <tt>buyerReportManagerList</tt>.
     *
     * @param buyerReportManagerList value to be assigned to property buyerReportManagerList
     */
    public void setBuyerReportManagerList(List<BY121318Bean> buyerReportManagerList) {
        this.buyerReportManagerList = buyerReportManagerList;
    }
}
