package com.msk.inventory.bean;

import com.msk.comm.bean.param.BaseRestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/5.
 */
public class ISO152415ParamBean extends BaseRestParam {

    List<ISO152415InvParamBean> invList = new ArrayList<ISO152415InvParamBean>();

    public List<ISO152415InvParamBean> getInvList() {
        return invList;
    }

    public void setInvList(List<ISO152415InvParamBean> invList) {
        this.invList = invList;
    }
}
