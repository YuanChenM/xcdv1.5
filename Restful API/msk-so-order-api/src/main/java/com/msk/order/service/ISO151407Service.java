package com.msk.order.service;

import com.msk.order.bean.param.ISO151407RestParam;
import com.msk.order.bean.result.ISO151407RestOrderResult;

import java.util.List;

/**
 * ISO151407_买家平台下单数量统计
 * Created by sunjiaju on 16/8/24.
 */
public interface ISO151407Service {
    public List<ISO151407RestOrderResult> searchOrderSourceCount(ISO151407RestParam param);
}
