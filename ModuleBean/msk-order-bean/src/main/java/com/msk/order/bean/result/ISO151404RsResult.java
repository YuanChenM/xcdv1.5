package com.msk.order.bean.result;

import com.msk.common.bean.result.BaseResult;

/**
 * ISO151404_验证退货单号接口
 * Created by chu_jian on 2016/8/3.
 */
public class ISO151404RsResult extends BaseResult {
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
