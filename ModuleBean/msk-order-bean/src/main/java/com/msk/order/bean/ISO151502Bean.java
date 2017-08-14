package com.msk.order.bean;

import com.msk.order.entity.SoOrderShipDetail;
import com.msk.order.entity.SoSubOrderDetail;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhang_qiang1 on 2016/8/9.
 */

public class ISO151502Bean extends SoSubOrderDetail {

    private String featureName;

    private String normsName;

    private String pdGradeName;

    private java.math.BigDecimal weight;//重量

    List<SoOrderShipDetail> orderShipDetailList;


    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getNormsName() {
        return normsName;
    }

    public void setNormsName(String normsName) {
        this.normsName = normsName;
    }

    public String getPdGradeName() {
        return pdGradeName;
    }

    public void setPdGradeName(String pdGradeName) {
        this.pdGradeName = pdGradeName;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public List<SoOrderShipDetail> getOrderShipDetailList() {
        return orderShipDetailList;
    }

    public void setOrderShipDetailList(List<SoOrderShipDetail> orderShipDetailList) {
        this.orderShipDetailList = orderShipDetailList;
    }
}
