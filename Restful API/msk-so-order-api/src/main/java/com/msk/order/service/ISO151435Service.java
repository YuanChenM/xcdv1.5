package com.msk.order.service;

import com.msk.order.bean.param.ISO151435RestParam;
import com.msk.order.bean.result.ISO151435RestResult;

/**
 * ISO151435_根据订单号(订单编码)获取订单相关信息
 * Created by chu_jian on 2016/8/3.
 */
public interface ISO151435Service {
    ISO151435RestResult getPartOrderDetail(ISO151435RestParam param);
}
