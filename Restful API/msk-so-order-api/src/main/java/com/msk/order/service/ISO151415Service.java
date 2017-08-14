package com.msk.order.service;

import com.msk.order.bean.param.ISO151415OrderDeliverParam;
import com.msk.order.bean.param.ISO151415OrderReceiptParam;
import com.msk.order.bean.result.BaseOrderStatusResult;

/**
 * Created by wang_shuai on 2016/8/12.
 */
public interface ISO151415Service {

    /**
     * 订单收发货操作
     * @param param
     * @return
     */
    BaseOrderStatusResult modifyPartReceipt(ISO151415OrderReceiptParam param);

    /**
     * 订单发货操作
     * @param param
     * @return
     */
    BaseOrderStatusResult modifyPartDeliver(ISO151415OrderDeliverParam param);
}
