package com.msk.so.bean;

import com.msk.common.base.BaseBean;
import com.msk.order.bean.ISO151413RsResult;

import java.util.List;

/**
 * Created by zhang_chi on 2016/8/1.
 */
public class ISO153202Result extends BaseBean {

    // 订单信息
    private ISO151413RsResult order;

    // 买家交易详细信息
    private SOCp153111Bean soSellerBill;

    // 交易流水
    private List<SoCpRunningBean> soCpRunningList;

    /** 添加卖家资金池详细列表更新删除功能 modify by lihuiqian on 2016/9/5 start */
    // 退款明细
    private List<SOCp153112Bean> soCpRefundList;

    /** 添加卖家资金池详细列表更新删除功能 modify by lihuiqian on 2016/9/5 end */

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

    /** 添加卖家资金池详细列表更新删除功能 modify by lihuiqian on 2016/9/5 start */
    public List<SoCpRunningBean> getSoCpRunningList() {
        return soCpRunningList;
    }

    public void setSoCpRunningList(List<SoCpRunningBean> soCpRunningList) {
        this.soCpRunningList = soCpRunningList;
    }

    public List<SOCp153112Bean> getSoCpRefundList() {
        return soCpRefundList;
    }

    public void setSoCpRefundList(List<SOCp153112Bean> soCpRefundList) {
        this.soCpRefundList = soCpRefundList;
    }
    /** 添加卖家资金池详细列表更新删除功能 modify by lihuiqian on 2016/9/5 end */

}
