package com.msk.order.service;

import com.msk.order.bean.param.ISO151413RestParam;
import com.msk.order.bean.result.ISO151413RestResult;

/**
 * Created by jackjiang on 16/8/10.
 */
public interface ISO151413Service {
    ISO151413RestResult payOrder(ISO151413RestParam param);

    void modifyStatus(String orderCode);
}
