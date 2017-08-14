package com.msk.order.service;

import com.msk.common.bean.RestRequest;
import com.msk.order.bean.param.ISO151401RestParam;
import com.msk.order.bean.result.ISO151401RestResult;

/**
 * ISO151401_创建购物需求订单接口
 * Created by chu_jian on 2016/8/3.
 */

public interface ISO151401Service {
    ISO151401RestResult createSoPro(RestRequest<ISO151401RestParam> param);
}
