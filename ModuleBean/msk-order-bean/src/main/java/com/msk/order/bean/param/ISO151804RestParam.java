package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseParam;


/**
 * ISO151804_退货原因查询接口
 * Created by sun_jiaju on 2016/8/17.
 */
public class ISO151804RestParam extends BaseParam {
    // 退货类型
    private String returnType;

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }
}
