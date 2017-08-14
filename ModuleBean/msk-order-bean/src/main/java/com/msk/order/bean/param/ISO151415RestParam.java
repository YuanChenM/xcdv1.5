package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseRestParam;

import java.util.List;

/**
 * Created by wang_shuai on 2016/8/31.
 */
public class ISO151415RestParam extends BaseRestParam {
    private String updId;

    private List<ISO151415SoCpSelChargingParam> soCpSelChargingList;

    public String getUpdId() {
        return updId;
    }

    public void setUpdId(String updId) {
        this.updId = updId;
    }

    public List<ISO151415SoCpSelChargingParam> getSoCpSelChargingList() {
        return soCpSelChargingList;
    }

    public void setSoCpSelChargingList(List<ISO151415SoCpSelChargingParam> soCpSelChargingList) {
        this.soCpSelChargingList = soCpSelChargingList;
    }
}
