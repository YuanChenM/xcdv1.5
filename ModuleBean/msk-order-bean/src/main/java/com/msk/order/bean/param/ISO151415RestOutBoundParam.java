package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseRestParam;

import java.util.List;

/**
 * Created by wang_shuai on 2016/9/23.
 */
public class ISO151415RestOutBoundParam extends BaseRestParam {
    private String loginId;
    /** 入库产品列表信息 */
    private List<ISO151415OutBoundParam> invList;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public List<ISO151415OutBoundParam> getInvList() {
        return invList;
    }

    public void setInvList(List<ISO151415OutBoundParam> invList) {
        this.invList = invList;
    }
}
