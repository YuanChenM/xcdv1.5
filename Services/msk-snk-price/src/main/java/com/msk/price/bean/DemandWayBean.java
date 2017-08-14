package com.msk.price.bean;

import com.msk.core.entity.BaseEntity;

/**
 * DemandWayBean
 *
 */
public class DemandWayBean extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer wayType;

    private String passDemand;

    public Integer getWayType() {
        return wayType;
    }

    public void setWayType(Integer wayType) {
        this.wayType = wayType;
    }

    public String getPassDemand() {
        return passDemand;
    }

    public void setPassDemand(String passDemand) {
        this.passDemand = passDemand;
    }
}