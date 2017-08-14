package com.msk.order.bean.result;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wang_jianzhou on 2016/8/10.
 */
public class DistrictResult implements Serializable {

    private List<LgcsAreaBean> lgcsAreaList;

    public List<LgcsAreaBean> getLgcsAreaList() {
        return lgcsAreaList;
    }

    public void setLgcsAreaList(List<LgcsAreaBean> lgcsAreaList) {
        this.lgcsAreaList = lgcsAreaList;
    }
}
