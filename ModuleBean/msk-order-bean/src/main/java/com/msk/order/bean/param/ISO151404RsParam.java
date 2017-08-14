package com.msk.order.bean.param;

import com.msk.common.annotation.valid.Required;
import com.msk.common.bean.param.BaseParam;

/**
 * ISO151404_验证退货单号接口
 * Created by chu_jian on 2016/8/3.
 */
public class ISO151404RsParam extends BaseParam {

    @Required(required = true, message = " 退货单号不能为空")
    private String backNo;

    @Required(required = true, message = " 订单号不能为空")
    private String transCode;

    public String getBackNo() {
        return backNo;
    }

    public void setBackNo(String backNo) {
        this.backNo = backNo;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }
}
