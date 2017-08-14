package com.msk.order.bean.result;

import com.msk.common.bean.result.BaseResult;

import java.util.Date;
import java.util.List;


/**
 * ISO151803_订单发货明细信息查询
 * Created by sun_jiaju on 2016/8/29.
 */
public class ISO151803RestOrderShipResult extends BaseResult{
    private Long shipId;//发货明细
    private String shipCode;//发货单号ID
    private Date proDate;//期望配送日 yyyy-mm-dd
    private Date expectReceiveTime;//预计送达时间
    private Date actualReceiveTime;//实际送达时间，取配送单中最迟配送到的时间
    private Integer canReturn;//从时间判定是否可退货，1-可退货，2-不可退

    private List<ISO151803RestProductResult> productList;

    public List<ISO151803RestProductResult> getProductList() {
        return productList;
    }

    public void setProductList(List<ISO151803RestProductResult> productList) {
        this.productList = productList;
    }


    public Integer getCanReturn() {
        return canReturn;
    }

    public void setCanReturn(Integer canReturn) {
        this.canReturn = canReturn;
    }

    public Long getShipId() {
        return shipId;
    }

    public void setShipId(Long shipId) {
        this.shipId = shipId;
    }

    public String getShipCode() {
        return shipCode;
    }

    public void setShipCode(String shipCode) {
        this.shipCode = shipCode;
    }

    public Date getProDate() {
        return proDate;
    }

    public void setProDate(Date proDate) {
        this.proDate = proDate;
    }

    public Date getExpectReceiveTime() {
        return expectReceiveTime;
    }

    public void setExpectReceiveTime(Date expectReceiveTime) {
        this.expectReceiveTime = expectReceiveTime;
    }

    public Date getActualReceiveTime() {
        return actualReceiveTime;
    }

    public void setActualReceiveTime(Date actualReceiveTime) {
        this.actualReceiveTime = actualReceiveTime;
    }
}
