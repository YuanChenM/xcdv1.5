package com.msk.so.bean;

import com.hoperun.core.bean.BaseParam;
import com.msk.core.entity.SoCpSelCharging;

import java.util.List;

/**
 * ISO153103Param
 * zhang_chi
 */
public class ISO153103Param extends BaseParam {

    private static final long serialVersionUID = 1L;


    private List<SoCpSelCharging> soCpSelChargingList;

    public List<SoCpSelCharging> getSoCpSelChargingList() {
        return soCpSelChargingList;
    }

    public void setSoCpSelChargingList(List<SoCpSelCharging> soCpSelChargingList) {
        this.soCpSelChargingList = soCpSelChargingList;
    }
}
