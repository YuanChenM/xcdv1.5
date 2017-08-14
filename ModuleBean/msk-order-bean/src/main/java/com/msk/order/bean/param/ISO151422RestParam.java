package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseParam;

import java.util.List;

/**
 * ISO151422_退货入库
 * Created by wang_jianzhou on 2016/8/17.
 */
public class ISO151422RestParam extends BaseParam{

    /** 退货但id*/
    private Long returnId;
    /** 退货入库时间*/
    private String inboundTime;
    /** 入库人ID*/
    private String inboundManId;
    /** 入库人名称*/
    private String inboundManName;
    /** 退货状态 */
    private java.lang.Integer returnStatus;
    /** 退货产品明细*/
    private List<ISO151422ProductRestParam> productList;

    public Long getReturnId() {
        return returnId;
    }

    public void setReturnId(Long returnId) {
        this.returnId = returnId;
    }

    public String getInboundTime() {
        return inboundTime;
    }

    public void setInboundTime(String inboundTime) {
        this.inboundTime = inboundTime;
    }

    public String getInboundManId() {
        return inboundManId;
    }

    public void setInboundManId(String inboundManId) {
        this.inboundManId = inboundManId;
    }

    public String getInboundManName() {
        return inboundManName;
    }

    public void setInboundManName(String inboundManName) {
        this.inboundManName = inboundManName;
    }

    public Integer getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(Integer returnStatus) {
        this.returnStatus = returnStatus;
    }

    public List<ISO151422ProductRestParam> getProductList() {
        return productList;
    }

    public void setProductList(List<ISO151422ProductRestParam> productList) {
        this.productList = productList;
    }
}
