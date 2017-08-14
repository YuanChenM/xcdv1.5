package com.msk.inventory.bean;

import com.msk.comm.bean.param.BaseRestParam;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by wangfan on 16/8/22.
 */
public class ISO152410ParamBean extends BaseRestParam {

    /** 入库产品列表信息 */
    private List<ISO152410InvParamBean> invList;

    public List<ISO152410InvParamBean> getInvList() {
        return invList;
    }

    public void setInvList(List<ISO152410InvParamBean> invList) {
        this.invList = invList;
    }
}
