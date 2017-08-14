package com.msk.order.service;

import com.msk.order.bean.param.ISO151428RestParam;
import com.msk.order.bean.result.ISO151428RestResult;

/**
 * ISO151428_购买记录查询接口
 * Created by wang_shuai on 2016/8/24.
 */
public interface ISO151428Service {
    ISO151428RestResult findBuyRecord(ISO151428RestParam iso151428RestParam);
}
