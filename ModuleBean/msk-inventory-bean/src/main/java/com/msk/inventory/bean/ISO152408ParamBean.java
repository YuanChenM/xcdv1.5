package com.msk.inventory.bean;

import com.msk.comm.bean.param.BaseParam;
import com.msk.comm.bean.param.BaseRestParam;

import java.util.List;

/**
 * Created by zheng_xu on 2016/8/25.
 */
public class ISO152408ParamBean extends BaseRestParam {
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
    private List<ISO152408PdListParamBean> pdList;

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

    public String getDecreaseTime() {
        return decreaseTime;
    }

    public String getSlType() {
        return slType;
    }

    public String getSlCode() {
        return slCode;
    }

    public List<ISO152408PdListParamBean> getPdList() {
        return pdList;
    }

    public void setPlantFormId(String plantFormId) {
        this.plantFormId = plantFormId;
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

    public void setDecreaseTime(String decreaseTime) {
        this.decreaseTime = decreaseTime;
    }

    public void setSlType(String slType) {
        this.slType = slType;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public void setPdList(List<ISO152408PdListParamBean> pdList) {
        this.pdList = pdList;
    }
}
