package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseParam;

/**
 * Created by zhang_qiang1 on 2016/8/19.
 */
public class ISO151409RestReturnListParam extends BaseParam{
    /** 退货单ID*/
    private Integer returnId;
    /** 退货单编码*/
    private String returnCode;

    public Integer getReturnId() {
        return returnId;
    }

    public void setReturnId(Integer returnId) {
        this.returnId = returnId;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }
}
