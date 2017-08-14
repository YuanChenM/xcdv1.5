package com.msk.br.bean;

import com.msk.core.entity.BaseEntity;

/**
 * 买家标准产品池bean
 * <p/>
 * Created by yuan_zhifei on 2016/07/08.
 */
public class BR121404RsBean extends BaseEntity {
    //文件状态
    private String fileStatus;
    //标注市场销售名
    private String salesName;
    //学名
    private String scientificName;
    //俗名
    private String localName;
    //产品特征
    private String featureName;
    //产品等级
    private String gradeName;
    //单箱净重
    private String weightName;
    //包装规格
    private String normsName;
    //产品编码
    private String pdCode;
    //本月订单笔
    private String orderCount;
    //本月订单数量箱
    private String orderQty;
    //上月订单笔
    private String lastOrderCount;
    //上月订单数量箱
    private String lastOrderQty;
    //总订单笔
    private String sumOrderCount;
    //总订单数量
    private String sumOrderQty;
    public String getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(String fileStatus) {
        this.fileStatus = fileStatus;
    }

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
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

    public String getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(String orderQty) {
        this.orderQty = orderQty;
    }

    public String getLastOrderCount() {
        return lastOrderCount;
    }

    public void setLastOrderCount(String lastOrderCount) {
        this.lastOrderCount = lastOrderCount;
    }

    public String getLastOrderQty() {
        return lastOrderQty;
    }

    public void setLastOrderQty(String lastOrderQty) {
        this.lastOrderQty = lastOrderQty;
    }

    public String getSumOrderCount() {
        return sumOrderCount;
    }

    public void setSumOrderCount(String sumOrderCount) {
        this.sumOrderCount = sumOrderCount;
    }

    public String getSumOrderQty() {
        return sumOrderQty;
    }

    public void setSumOrderQty(String sumOrderQty) {
        this.sumOrderQty = sumOrderQty;
    }
}
