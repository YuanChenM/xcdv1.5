package com.msk.order.service;

import com.msk.order.entity.SoReturn;
import com.msk.order.entity.SoReturnStatus;

import java.util.List;

/**
 * 更新退货订单状态履历
 * zhangqiang1
 */
public interface ReturnOrderStatusService {
    /**
     * 根据退货单更新退货单状态
     *
     * @param returnId
     * @param returnStatus
     * @param actId
     * @return
     */
    SoReturnStatus saveReturnStatusByReturnOrderId(Long returnId, Integer returnStatus, String actId);


    /**
     *
     * @param savedOrUpdatedSoReturn 退货单的退货状态已经更改过
     * @param actId
     * @return
     */
    SoReturnStatus saveReturnStatusByReturnOrder(SoReturn savedOrUpdatedSoReturn, String actId);



    /**
     * 根据订单批量更新退货状态
     *
     * @param orderId
     * @param returnStatus
     * @param actId
     * @return
     */
    List<SoReturnStatus> saveReturnStatusByOrderId(Long orderId, Integer returnStatus, String actId);

}
