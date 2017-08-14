package com.msk.order.bean.result;

import com.msk.common.bean.result.BaseResult;

import java.util.List;

/**
 * Created by liu_tao2 on 2016/8/12.
 */
public class ISO151414BsBuyerInfoResult extends BaseResult {

    private List<ISO151414HouseSellerRestResult> resultList;

    public List<ISO151414HouseSellerRestResult> getResultList() {
        return resultList;
    }

    public void setResultList(List<ISO151414HouseSellerRestResult> resultList) {
        this.resultList = resultList;
    }
}
