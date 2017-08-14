package com.msk.inventory.bean;

import java.util.List;

import com.msk.comm.bean.param.BaseRestParam;

/**
 * Created by wang_fan2 on 2016/8/25.
 */
public class ISO152407ParamBean extends BaseRestParam {

    private String plantFormId;
    private String lgcsCode;
    private String orderId;
    private String orderCode;
    private String orderTime;
    private String slType;
    private String slCode;
    private List<ISO152407PdParamBean> pdList;
    private String allocateType;

    public String getAllocateType() {
        return allocateType;
    }

    public void setAllocateType(String allocateType) {
        this.allocateType = allocateType;
    }

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

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
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

    public List<ISO152407PdParamBean> getPdList() {
        return pdList;
    }

    public void setPdList(List<ISO152407PdParamBean> pdList) {
        this.pdList = pdList;
    }
}
