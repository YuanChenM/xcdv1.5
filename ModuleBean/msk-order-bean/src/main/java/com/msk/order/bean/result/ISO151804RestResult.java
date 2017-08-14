package com.msk.order.bean.result;


import com.msk.common.bean.result.BaseResult;

import java.util.List;

/**
 * ISO151804_退货原因查询接口
 * Created by sun_jiaju on 2016/8/17.
 */
public class ISO151804RestResult extends BaseResult {

    private List<ISO151804RestReasonResult> reasonList;

    public List<ISO151804RestReasonResult> getReasonList() {
        return reasonList;
    }

    public void setReasonList(List<ISO151804RestReasonResult> reasonList) {
        this.reasonList = reasonList;
    }
}
