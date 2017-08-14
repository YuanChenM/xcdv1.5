package com.msk.order.service;

import com.msk.order.bean.param.ISO151427RestParam;
import com.msk.order.bean.result.ISO151427RestResult;

/**
 * ISO151427_结算详情查询接口
 * Created by wang_shuai on 2016/8/23.
 */
public interface ISO151427Service {
    ISO151427RestResult findSettlementDetail(ISO151427RestParam iso151427RestParam);
}
