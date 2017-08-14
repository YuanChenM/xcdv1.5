package com.msk.ssc.bean;

import com.msk.core.entity.SscDeliveryOrderBasic;

/**
 *  param
 * <p/>
 * Created by peng_hao 2016/07/12.
 */
public class SSC11305OrderBasicBean extends SscDeliveryOrderBasic {

    /** 到货日期*/
    private String etaStr;

    public String getEtaStr() {
        return etaStr;
    }

    public void setEtaStr(String etaStr) {
        this.etaStr = etaStr;
    }
}

