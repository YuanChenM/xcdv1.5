package com.msk.order.bean;


import java.io.Serializable;
import java.util.List;

/**
 * Created by zhang_qiang1 on 2016/6/20.
 */
public class ISO151501OrderAndDetailListBean implements Serializable {

    private List<ISO151501OrderAndDetailBean> orderAndDetailResultList;

    public List<ISO151501OrderAndDetailBean> getOrderAndDetailResultList() {
        return orderAndDetailResultList;
    }

    public void setOrderAndDetailResultList(List<ISO151501OrderAndDetailBean> orderAndDetailResultList) {
        this.orderAndDetailResultList = orderAndDetailResultList;
    }
}
