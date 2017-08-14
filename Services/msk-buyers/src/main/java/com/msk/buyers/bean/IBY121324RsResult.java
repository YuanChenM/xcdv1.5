package com.msk.buyers.bean;

import com.msk.common.bean.RsPageResult;

import java.util.List;

/**
 * Created by tao_zhifa on 2016/9/13.
 */
public class IBY121324RsResult extends RsPageResult {

    private List<IBY121324RsBean> invoiceList;

    public List<IBY121324RsBean> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<IBY121324RsBean> invoiceList) {
        this.invoiceList = invoiceList;
    }
}
