package com.msk.order.service;

import com.msk.order.bean.param.ISO151802RestParam;
import com.msk.order.bean.result.ISO151802RestResult;

/**
 * ISO151802_现场退货数据接收接口
 * Created by chujian on 16/8/10.
 */
public interface ISO151802Service {
    ISO151802RestResult doReceiverReject(ISO151802RestParam param);
}
