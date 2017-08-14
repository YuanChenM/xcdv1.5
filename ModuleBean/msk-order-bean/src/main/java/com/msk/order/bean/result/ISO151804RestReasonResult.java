package com.msk.order.bean.result;


import com.msk.common.entity.BaseEntity;

/**
 * ISO151804_退货原因查询接口
 * Created by sun_jiaju on 2016/8/17.
 */
public class ISO151804RestReasonResult extends BaseEntity {

    private Integer reasonId;//原因ID

    private String reasonName;//原因名称

    public Integer getReasonId() {
        return reasonId;
    }

    public void setReasonId(Integer reasonId) {
        this.reasonId = reasonId;
    }

    public String getReasonName() {
        return reasonName;
    }

    public void setReasonName(String reasonName) {
        this.reasonName = reasonName;
    }
}
