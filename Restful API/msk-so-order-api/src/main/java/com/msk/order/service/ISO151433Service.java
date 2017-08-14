package com.msk.order.service;

import com.msk.order.bean.param.ISO151433RestParam;
import com.msk.order.bean.result.ISO151433RestResult;

/**
 * Created by wang_shuai on 2016/8/25.
 */
public interface ISO151433Service {
    ISO151433RestResult cancelShipment(ISO151433RestParam param);
}
