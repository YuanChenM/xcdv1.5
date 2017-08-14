package com.msk.order.bean;

import com.msk.common.bean.RsPageResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sun_jiaju on 2016/6/8.
 */
public class ProductResult extends RsPageResult {
    private String productCode;

    private List<WayResult> wayList=new ArrayList<>();


    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public List<WayResult> getWayList() {
        return wayList;
    }

    public void setWayList(List<WayResult> wayList) {
        this.wayList = wayList;
    }
}
