package com.msk.order.bean;

import com.msk.common.bean.RsPageResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sun_jiaju on 2016/6/8.
 */
public class BaikuResult extends RsPageResult {
    private String areaCode;

    private List<SupResult> supList=new ArrayList<>();

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public List<SupResult> getSupList() {
        return supList;
    }

    public void setSupList(List<SupResult> supList) {
        this.supList = supList;
    }

}
