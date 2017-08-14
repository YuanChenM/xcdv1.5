package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseRestPageParam;
import com.msk.common.annotation.valid.Required;

/**
 * ISO151428_购买记录查询接口
 * Created by wang_shuai on 2016/8/24.
 */
public class ISO151428RestParam extends BaseRestPageParam {
    //产品编码
    @Required(required = true, message = "产品编码不能为空")
    private String pdCode;

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }
}
