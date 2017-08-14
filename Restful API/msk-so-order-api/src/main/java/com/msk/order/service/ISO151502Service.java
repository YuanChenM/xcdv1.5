package com.msk.order.service;

import com.msk.order.entity.SoOrder;

/**
 * zhangqiang1
 */
public interface ISO151502Service {

    SoOrder findByOrderIdAndDelFlg(Long orderId, String delFlg);


}
