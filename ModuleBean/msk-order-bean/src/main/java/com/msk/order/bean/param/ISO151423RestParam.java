package com.msk.order.bean.param;

import com.msk.common.annotation.valid.Required;
import com.msk.common.bean.param.BaseParam;

/**
 * 卖家，买手，管家快捷信息查询接口传入参数
 * Created by wang_jianzhou on 2016/8/22.
 */
public class ISO151423RestParam extends BaseParam{

    public ISO151423RestParam() {
    }

    @Required(required = true,message = "sellers不能为空")
    private String sellers ;

    public String getSellers() {
        return sellers;
    }

    public void setSellers(String sellers) {
        this.sellers = sellers;
    }
}
