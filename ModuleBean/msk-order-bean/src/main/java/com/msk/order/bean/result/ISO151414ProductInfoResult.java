package com.msk.order.bean.result;

import com.msk.common.bean.result.BaseResult;

import java.util.List;

/**
 * Created by liu_tao2 on 2016/8/12.
 */
public class ISO151414ProductInfoResult extends BaseResult{

    private List<ISO151414ProductStandardInfo> result;

    public List<ISO151414ProductStandardInfo> getResult() {
        return result;
    }

    public void setResult(List<ISO151414ProductStandardInfo> result) {
        this.result = result;
    }
}
