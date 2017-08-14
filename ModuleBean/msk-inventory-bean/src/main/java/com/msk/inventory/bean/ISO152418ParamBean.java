package com.msk.inventory.bean;

import java.io.Serializable;
import java.util.List;

import com.msk.comm.bean.param.BaseRestParam;

/**
 * Created by wang_fan2 on 2016/9/18.
 */
public class ISO152418ParamBean extends BaseRestParam implements Serializable {

    private List<ISO152418InvParamBean> invList;

    public List<ISO152418InvParamBean> getInvList() {
        return invList;
    }

    public void setInvList(List<ISO152418InvParamBean> invList) {
        this.invList = invList;
    }
}
