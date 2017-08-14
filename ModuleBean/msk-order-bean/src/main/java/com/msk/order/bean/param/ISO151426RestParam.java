package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseRestPageParam;

/**
 * ISO151426_管家订单查询接口
 * Created by wang_shuai on 2016/8/22.
 */
public class ISO151426RestParam extends BaseRestPageParam {
    /**
     * 管家ID
     */
    private String housekeepingId;
    /**
     * 订单号
     */
    private String orderId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 订单状态
     */
    private String orderStatus;

    /**
     * 开始时间
     */
    private String beginDate;

    /**
     * 结束时间
     */
    private String endDate;

    /**订单号/产品名称*/
    private String inputParam;

    public String getHousekeepingId() {
        return housekeepingId;
    }

    public void setHousekeepingId(String housekeepingId) {
        this.housekeepingId = housekeepingId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getInputParam() {
        return inputParam;
    }

    public void setInputParam(String inputParam) {
        this.inputParam = inputParam;
    }
}
