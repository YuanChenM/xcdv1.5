package com.msk.order.service;

import com.msk.order.bean.param.ISO151801RestParam;
import com.msk.order.bean.result.ISO151801RestResult;

/**
 * ISO151801_迟收退货数据接收接口
 * Created by chujian on 16/8/10.
 */
public interface ISO151801Service {
    ISO151801RestResult doReceiverLater(ISO151801RestParam param);
}
