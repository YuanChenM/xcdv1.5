package com.msk.batch.order.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wang_shuai on 2016/9/12.
 */
public class BSO151404InvParamList implements Serializable {
    private List<BSO151404InvParamBean> invList;

    public List<BSO151404InvParamBean> getInvList() {
        return invList;
    }

    public void setInvList(List<BSO151404InvParamBean> invList) {
        this.invList = invList;
    }
}
