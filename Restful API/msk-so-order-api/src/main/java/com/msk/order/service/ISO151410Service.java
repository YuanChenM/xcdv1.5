package com.msk.order.service;

import com.msk.order.bean.param.ISO151410RestPram;
import com.msk.order.bean.result.ISO151410RestResult;

/**
 * Created by zhangqiang1 on 16/8/10.
 */
public interface ISO151410Service {

    ISO151410RestResult modifyOrderCancel(ISO151410RestPram param, String loginId);

    void setSubOrderCanceledStatusByOrderId(String orderId);


}
