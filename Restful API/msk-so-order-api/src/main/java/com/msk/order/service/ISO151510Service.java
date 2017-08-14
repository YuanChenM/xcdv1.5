package com.msk.order.service;

import com.msk.order.bean.param.ISO151510RestParam;
import com.msk.order.bean.result.ISO151510RestBeanList;

/**
 * ISO151510_发货单取消后台SERVICE
 * Created by wang_jianzhou on 2016/8/11.
 */
public interface ISO151510Service {

    ISO151510RestBeanList findOrderShipInfo(ISO151510RestParam param);

    void cancelOrderShip(ISO151510RestParam iso151510RestParam);
}
