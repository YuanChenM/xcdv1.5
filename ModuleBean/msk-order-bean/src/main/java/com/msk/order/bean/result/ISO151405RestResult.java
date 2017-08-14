package com.msk.order.bean.result;

import com.msk.common.bean.result.BaseResult;

import java.util.List;

/**
 * ISO151405_产品销量查询接口
 * Created by chu_jian on 2016/8/3.
 */
public class ISO151405RestResult extends BaseResult {
    /** 产品信息集合*/
    private List<ISO151405PdListRestResult> pdList;

    public List<ISO151405PdListRestResult> getPdList() {
        return pdList;
    }

    public void setPdList(List<ISO151405PdListRestResult> pdList) {
        this.pdList = pdList;
    }
}
