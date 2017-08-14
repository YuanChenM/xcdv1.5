package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseParam;

import java.util.List;

/**
 * 卖家库存占用
 * Created by zhang_qiang1 on 2016/9/12.
 */
public class ISO151410IRestSlInvOccupyDecreaseParam extends BaseParam {

    private String plantFormId;//平台ID
    private String lgcsCode;//物流区编码
    private String orderId;// 订单id
    private String orderCode;// 订单code
    private String decreaseTime;// 订单创建确认时间
    private String slType;// 卖家类型-CodeMaster 1神龙客 2美侍客 3买手
    private String slCode;// 卖家编码
    private String allocateType;// 占用类型：1，先卖家占用，后供应商占用；2，直接供应商占用
    private List<ISO151410IRestSlInvProductParam> pdList;//订购产品列表

    public String getPlantFormId() {
        return plantFormId;
    }

    public void setPlantFormId(String plantFormId) {
        this.plantFormId = plantFormId;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getDecreaseTime() {
        return decreaseTime;
    }

    public void setDecreaseTime(String decreaseTime) {
        this.decreaseTime = decreaseTime;
    }

    public String getSlType() {
        return slType;
    }

    public void setSlType(String slType) {
        this.slType = slType;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getAllocateType() {
        return allocateType;
    }

    public void setAllocateType(String allocateType) {
        this.allocateType = allocateType;
    }

    public List<ISO151410IRestSlInvProductParam> getPdList() {
        return pdList;
    }

    public void setPdList(List<ISO151410IRestSlInvProductParam> pdList) {
        this.pdList = pdList;
    }
}
