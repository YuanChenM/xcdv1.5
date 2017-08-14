package com.msk.so.bean;

import com.msk.common.bean.RsPageResult;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * CpResult
 *
 * @author yang_yang
 * @version 1.0
 **/
public class CpResult<T> extends RsPageResult {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private List<T> sellerBills;


    /**配送单列表*/
    private List<SOCp153111Bean> deliveryList;

    /**退货单编码*/
    private List<SOCp153111Bean> refundList;

    public List<T> getSellerBills() {
        return sellerBills;
    }

    public void setSellerBills(List<T> sellerBills) {
        this.sellerBills = sellerBills;
    }

    public List<SOCp153111Bean> getDeliveryList() {
        return deliveryList;
    }

    public void setDeliveryList(List<SOCp153111Bean> deliveryList) {
        this.deliveryList = deliveryList;
    }

    public List<SOCp153111Bean> getRefundList() {
        return refundList;
    }

    public void setRefundList(List<SOCp153111Bean> refundList) {
        this.refundList = refundList;
    }
}
