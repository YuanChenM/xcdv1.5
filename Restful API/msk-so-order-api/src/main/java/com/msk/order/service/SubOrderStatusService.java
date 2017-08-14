package com.msk.order.service;


import com.msk.order.entity.SoSubOrder;
import com.msk.order.entity.SoSubOrderStatus;

import java.util.List;

/**
 * 更新分批订单状态履历
 * zhangqiang1
 */
public interface SubOrderStatusService {
    /**
     * 根据订单批量更新分批订单履历
     *
     * @param orderId
     * @param subOrderStatus
     * @return
     */
    List<SoSubOrderStatus> saveSubOrderStatusListByOrderId(Long orderId, Integer subOrderStatus, String crtId);



    /**
     * 根据分批订单id，分批订单状态 更新分批订单履历
     *
     * @param subOrderId
     * @param subOrderStatus
     * @return
     */
    SoSubOrderStatus saveSubStatusBySubOrderId(Long subOrderId, Integer subOrderStatus, String crtId);


    /**
     * 根据已经修改过分批订单状态的分批订单  更新履历
     *
     * @param savedOrUpdatedSoSubOrder
     * @param crtId
     * @return
     */
    SoSubOrderStatus saveSubStatusBySubOrder(SoSubOrder savedOrUpdatedSoSubOrder, String crtId);


}
