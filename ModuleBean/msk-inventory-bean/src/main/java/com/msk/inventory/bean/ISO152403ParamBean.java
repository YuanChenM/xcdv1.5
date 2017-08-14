package com.msk.inventory.bean;

import com.msk.comm.bean.param.BaseRestPageParam;
import com.msk.comm.bean.param.BaseRestParam;

import java.util.List;

/**
 * Created by zheng_xu on 2016/8/22.
 */
public class ISO152403ParamBean extends BaseRestPageParam {
    /** 传入参数对象集合 */
    private List<ISO152403StockParamBean> pdList;

    public List<ISO152403StockParamBean> getPdList() {
        return pdList;
    }

    public void setPdList(List<ISO152403StockParamBean> pdList) {
        this.pdList = pdList;
    }

}
