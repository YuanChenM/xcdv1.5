package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseRestParam;

import java.util.Date;
import java.util.List;

/**
 * Created by liu_tao2 on 2016/8/30.
 */
public class ISO151414OccupyStockParam extends BaseRestParam {

    /** 平台ID，CodeMaster有定义 */
    private String plantFormId;

    /** 物流区编码 */
    private String lgcsCode;

    private String warehouseCode;

    private Long orderId;

    private String orderCode;

    private Date orderTime;

    private Integer slType;

    private String slCode;

    private Integer allocateType;

    private List<ISO151414StockProductInfo> pdList;

    public Integer getAllocateType() {
        return allocateType;
    }

    public void setAllocateType(Integer allocateType) {
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

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getSlType() {
        return slType;
    }

    public void setSlType(Integer slType) {
        this.slType = slType;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public List<ISO151414StockProductInfo> getPdList() {
        return pdList;
    }

    public void setPdList(List<ISO151414StockProductInfo> pdList) {
        this.pdList = pdList;
    }
}
