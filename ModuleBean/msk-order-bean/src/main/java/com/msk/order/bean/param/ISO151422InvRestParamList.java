package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseParam;

import java.util.List;

/**
 * Created by wang_jianzhou on 2016/9/14.
 */
public class ISO151422InvRestParamList extends BaseParam {

    private List<ISO151422InvRestParam> invList;

    public List<ISO151422InvRestParam> getInvList() {
        return invList;
    }

    public void setInvList(List<ISO151422InvRestParam> invList) {
        this.invList = invList;
    }
}
