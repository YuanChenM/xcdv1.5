package com.msk.print.bean;



import com.msk.comm.bean.BaseBean;

import java.util.List;

/**
 * Created by zhang_chi on 2016/8/1.
 */
public class ISO153202Result extends BaseBean {

    // 订单信息
    private ISO151413RsResult order;
    
    //买家交易详细信息
    private SOCp153111Bean soSellerBill;

    //交易流水
    private List<SoCpRunningBean> soCpRunningList;

    //应退款明细
    private List<SoCpRefundBean> soCpRefundList;

    public ISO151413RsResult getOrder() {
        return order;
    }

    public void setOrder(ISO151413RsResult order) {
        this.order = order;
    }

    public SOCp153111Bean getSoSellerBill() {
        return soSellerBill;
    }

    public void setSoSellerBill(SOCp153111Bean soSellerBill) {
        this.soSellerBill = soSellerBill;
    }

    public List<SoCpRunningBean> getSoCpRunningList() {
        return soCpRunningList;
    }

    public void setSoCpRunningList(List<SoCpRunningBean> soCpRunningList) {
        this.soCpRunningList = soCpRunningList;
    }

    public List<SoCpRefundBean> getSoCpRefundList() {
        return soCpRefundList;
    }

    public void setSoCpRefundList(List<SoCpRefundBean> soCpRefundList) {
        this.soCpRefundList = soCpRefundList;
    }
}
