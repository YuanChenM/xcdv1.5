package com.msk.seller.bean;


import com.hoperun.core.bean.BaseParam;

/**
 * Created by zhang_chi on 2016/4/28.
 */
public class ISL231101RsParam extends BaseParam {

    /**时间戳YYYY-MM-DD HH24:mi:ss*/
    private String incrementalTime;

    /**
     * 获得incrementalTime
     */
    public String getIncrementalTime() {
        return incrementalTime;
    }

    /**
     * 设置incrementalTime
     */
    public void setIncrementalTime(String incrementalTime) {
        this.incrementalTime = incrementalTime;
    }
}
