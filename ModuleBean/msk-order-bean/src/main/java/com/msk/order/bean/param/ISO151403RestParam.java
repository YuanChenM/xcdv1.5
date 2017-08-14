package com.msk.order.bean.param;


import com.msk.common.annotation.valid.Required;
import com.msk.common.bean.param.BaseParam;

/**
 * ISO151403_查询供应商列表接口
 * Created by chu_jian on 2016/8/3.
 */
public class ISO151403RestParam extends BaseParam {
    /**
     * orderCode
     */
    @Required(required = true, message = "订单号不能为空")
    private String transCode;

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }
}
