package com.msk.cashPooling.bean;

import com.msk.common.bean.RsPageParam;
import com.msk.core.entity.SoCpSelCharging;

import java.util.List;

/**
 * ISO153103Param
 * zhang_chi
 */
public class ISO153103Param extends RsPageParam{

    private static final long serialVersionUID = 1L;


    private List<SoCpSelCharging> soCpSelChargingList;

    public List<SoCpSelCharging> getSoCpSelChargingList() {
        return soCpSelChargingList;
    }

    public void setSoCpSelChargingList(List<SoCpSelCharging> soCpSelChargingList) {
        this.soCpSelChargingList = soCpSelChargingList;
    }
}
