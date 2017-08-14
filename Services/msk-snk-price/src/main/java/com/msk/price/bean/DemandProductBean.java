package com.msk.price.bean;


import com.msk.core.entity.BaseEntity;

import java.util.List;

/**
 * DemandProductBean
 *
 */
public class DemandProductBean extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String productCode;

    private Integer campType;

    private List<DemandWayBean> wayList;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getCampType() {
        return campType;
    }

    public void setCampType(Integer campType) {
        this.campType = campType;
    }

    public List<DemandWayBean> getWayList() {
        return wayList;
    }

    public void setWayList(List<DemandWayBean> wayList) {
        this.wayList = wayList;
    }
}