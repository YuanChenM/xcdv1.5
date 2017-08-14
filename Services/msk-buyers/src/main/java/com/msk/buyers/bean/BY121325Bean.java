package com.msk.buyers.bean;

import com.msk.common.base.BaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * BY121325Bean.
 *
 * @author tao_zhifa
 */
public class BY121325Bean extends BaseBean {

    private String earliestRecTime ;

    private String latestRecTime ;

    private String recTimeValue;
    private List<IBY121314RsBean> paymentList = new ArrayList<>();

    private IBY121314RsBean iby121314RsBean;

    public String getRecTimeValue() {
        return recTimeValue;
    }

    public void setRecTimeValue(String recTimeValue) {
        this.recTimeValue = recTimeValue;
    }

    public String getEarliestRecTime() {
        return earliestRecTime;
    }

    public void setEarliestRecTime(String earliestRecTime) {
        this.earliestRecTime = earliestRecTime;
    }

    public String getLatestRecTime() {
        return latestRecTime;
    }

    public void setLatestRecTime(String latestRecTime) {
        this.latestRecTime = latestRecTime;
    }

    public List<IBY121314RsBean> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<IBY121314RsBean> paymentList) {
        this.paymentList = paymentList;
    }

    public IBY121314RsBean getIby121314RsBean() {
        return iby121314RsBean;
    }

    public void setIby121314RsBean(IBY121314RsBean iby121314RsBean) {
        this.iby121314RsBean = iby121314RsBean;
    }
}
