package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseRestParam;

import java.util.Date;
import java.util.List;

/**
 * Created by wang_shuai on 2016/9/26.
 */
public class ISO151433RestCancelOccupancy extends BaseRestParam {
    //平台ID
    private String plantFormId;
    //物流区编码
    private String lgcsCode;
    //订单ID
    private String orderId;
    //订单单号
    private String orderCode;
    //占用减少时间(YYYY-MM-DD HH:mm:ss),当前时间即可
    private String decreaseTime;
    //卖家类型-CodeMaster
    private String slType;
    //卖家编码
    private String slCode;
    //订购产品列表
    private List<ISO151433PdParam> pdList;
    //占用类型：1，先卖家占用，后供应商占用；2，直接供应商占用
    private String allocateType;

    private String crtId;
    private Date crtTime;
    private String updId;
    private Date updTime;
    private Integer ver;

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

    public List<ISO151433PdParam> getPdList() {
        return pdList;
    }

    public void setPdList(List<ISO151433PdParam> pdList) {
        this.pdList = pdList;
    }

    public String getAllocateType() {
        return allocateType;
    }

    public void setAllocateType(String allocateType) {
        this.allocateType = allocateType;
    }

    public String getCrtId() {
        return crtId;
    }

    public void setCrtId(String crtId) {
        this.crtId = crtId;
    }

    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    public String getUpdId() {
        return updId;
    }

    public void setUpdId(String updId) {
        this.updId = updId;
    }

    public Date getUpdTime() {
        return updTime;
    }

    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }

    public Integer getVer() {
        return ver;
    }

    public void setVer(Integer ver) {
        this.ver = ver;
    }
}
