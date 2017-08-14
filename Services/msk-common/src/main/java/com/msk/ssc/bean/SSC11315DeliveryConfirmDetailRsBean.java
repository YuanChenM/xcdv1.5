package com.msk.ssc.bean;

import com.msk.core.entity.SscDeliveryConfirmPrDetail;

/**
 * Created by sun_jiaju on 2016/7/5.
 */
public class SSC11315DeliveryConfirmDetailRsBean extends SscDeliveryConfirmPrDetail {
    // 产品名称
    private String pdName;
    //合计总数
    private Integer su;
    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public Integer getSu() {
        return su;
    }

    public void setSu(Integer su) {
        this.su = su;
    }


}
