package com.msk.inventory.bean;

import com.msk.comm.bean.param.BaseParam;
import com.msk.comm.bean.param.BaseRestParam;

import java.util.List;

/**
 * Created by zheng_xu on 2016/8/25.
 */
public class ISO152406ParamBean extends BaseRestParam {
    //平台ID
    private String plantFormId;
    //物流区编码
    private String lgcsCode;
    //订单ID
    private String orderId;
    //订单单号
    private String orderCode;
    //订单创建确认时间(YYYY-MM-DD HH:mm:ss)
    private String orderTime;
    //卖家类型-CodeMaster
    private String slType;
    //卖家编码
    private String slCode;
    //订购产品列表
    private List<ISO152406PdListParamBean> pdList;

    public String getPlantFormId() {
        return plantFormId;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public String getSlType() {
        return slType;
    }

    public String getSlCode() {
        return slCode;
    }

    public List<ISO152406PdListParamBean> getPdList() {
        return pdList;
    }

    public void setPlantFormId(String plantFormid) {
        this.plantFormId = plantFormid;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public void setSlType(String slType) {
        this.slType = slType;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public void setPdList(List<ISO152406PdListParamBean> pdList) {
        this.pdList = pdList;
    }
}
