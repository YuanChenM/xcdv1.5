package com.msk.order.bean;

import com.msk.common.bean.RsPageResult;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sun_jiaju on 2016/6/8.
 */
public class WayResult implements Serializable {
    private Integer wayType;

    public Integer getWayType() {
        return wayType;
    }

    public void setWayType(Integer wayType) {
        this.wayType = wayType;
    }
}
