package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseParam;

/**
 * ISO151427_结算详情查询接口
 * Created by wang_shuai on 2016/8/23.
 */
public class ISO151427SoReturnParam extends BaseParam{
    //退货单id
    private String returnId;
    //退货单编码
    private String returnCode;

    public String getReturnId() {
        return returnId;
    }

    public void setReturnId(String returnId) {
        this.returnId = returnId;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }
}
