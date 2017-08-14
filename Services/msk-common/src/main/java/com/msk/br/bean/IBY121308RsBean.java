package com.msk.br.bean;

import com.msk.core.entity.BaseEntity;

import java.util.List;

/**
 * BY121308Bean.
 *
 * @author yuan_zhifei
 */
public class IBY121308RsBean extends BaseEntity {
    //产品
    private String pdName;
    //单箱净重
    private String weightName;
    //包装规格
    private String normsName;
    //产品等级名称
    private String gradeName;
    //产品编码
    private String pdCode;
    //订单笔数
    private String orderCount;
    //收货数量
    private String receiveQty;
    //吨数
    private String weight;
    //订单创建时间
    private String orderTime;
    private List<IBY121308RsBean> by121308RsBeanList;

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public String getWeightName() {
        return weightName;
    }

    public void setWeightName(String weightName) {
        this.weightName = weightName;
    }

    public String getNormsName() {
        return normsName;
    }

    public void setNormsName(String normsName) {
        this.normsName = normsName;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(String orderCount) {
        this.orderCount = orderCount;
    }

    public String getReceiveQty() {
        return receiveQty;
    }

    public void setReceiveQty(String receiveQty) {
        this.receiveQty = receiveQty;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public List<IBY121308RsBean> getBy121308RsBeanList() {
        return by121308RsBeanList;
    }

    public void setBy121308RsBeanList(List<IBY121308RsBean> by121308RsBeanList) {
        this.by121308RsBeanList = by121308RsBeanList;
    }
}
