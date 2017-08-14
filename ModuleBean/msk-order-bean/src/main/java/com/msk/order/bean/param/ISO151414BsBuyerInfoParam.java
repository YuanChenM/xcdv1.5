package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseParam;

import java.util.List;

/**
 * Created by liu_tao2 on 2016/8/12.
 */
public class ISO151414BsBuyerInfoParam extends BaseParam{
    /** 买家ID列表 */
    private List<String> buyerIdList;

    public List<String> getBuyerIdList() {
        return buyerIdList;
    }

    public void setBuyerIdList(List<String> buyerIdList) {
        this.buyerIdList = buyerIdList;
    }
}
