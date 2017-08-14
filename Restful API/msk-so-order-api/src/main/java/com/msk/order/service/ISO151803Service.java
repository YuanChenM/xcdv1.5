package com.msk.order.service;

import com.msk.order.bean.param.ISO151803RestParam;
import com.msk.order.bean.result.ISO151803RestResult;

/**
 * ISO151803_订单发货明细信息查询
 * Created by sun_jiaju on 2016/8/29.
 */
public interface ISO151803Service {
    ISO151803RestResult searchShipDetail(ISO151803RestParam param)throws Exception;
}
