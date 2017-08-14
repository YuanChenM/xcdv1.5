package com.msk.inventory.bean;

import java.util.List;

import com.msk.comm.bean.param.BaseRestPageParam;
import com.msk.comm.bean.param.BaseRestParam;

/**
 * Created by wang_fan2 on 2016/8/25.
 */
public class ISO152409ParamBean extends BaseRestParam {

    private String plantFormId;
    private String lgcsCode;
    private String warehouseCode;
    private String orderId;
    private String orderCode;
    private String decreaseTime;
    private String slType;
    private String slCode;
    private String allocateType;
    private List<ISO152409PdParamBean> pdList;

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

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
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

    public List<ISO152409PdParamBean> getPdList() {
        return pdList;
    }

    public void setPdList(List<ISO152409PdParamBean> pdList) {
        this.pdList = pdList;
    }

    public String getAllocateType() {
        return allocateType;
    }

    public void setAllocateType(String allocateType) {
        this.allocateType = allocateType;
    }
}
