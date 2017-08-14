package com.msk.order.service.impl;


import com.msk.common.constant.NumberConstant;
import com.msk.common.constant.SystemConstant;
import com.msk.common.data.jpa.BaseRepository;
import com.msk.common.data.jpa.condition.BaseOperatorEnum;
import com.msk.common.data.jpa.condition.CommonSpecification;
import com.msk.common.data.jpa.condition.Filter;
import com.msk.common.exception.BusinessException;
import com.msk.common.utils.DateTimeUtil;
import com.msk.common.utils.StringUtil;
import com.msk.order.entity.SoOrder;
import com.msk.order.entity.SoOrderStatus;
import com.msk.order.repository.SoOrderRepository;
import com.msk.order.repository.SoOrderStatusRepository;
import com.msk.order.service.BaseService;
import com.msk.order.service.OrderStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhang_qiang1 on 2016/9/1.
 */
@Service
public class OrderStatusServiceImpl extends BaseService<SoOrder, Long> implements OrderStatusService {

    private static Logger logger = LoggerFactory.getLogger(OrderStatusServiceImpl.class);

    @Autowired
    private SoOrderRepository orderRepository;

    @Autowired
    private SoOrderStatusRepository orderStatusRepository;

    @Override
    public BaseRepository<SoOrder, Long> getRepository() {
        return this.orderRepository;
    }


    /**
     * @param orderId
     * @param orderStatus
     * @param crtId
     * @return
     */
    @Override
    @Transactional
    public SoOrderStatus saveOrderStatusBySoOrderId(Long orderId, Integer orderStatus, String crtId) {
        logger.info("根据订单id,订单code 更新订单状态履历");
        Filter<SoOrder> orderFilter = new Filter<>();
        orderFilter.add("orderId", BaseOperatorEnum.EQUAL, orderId);
        orderFilter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        SoOrder soOrder = this.findOne(orderFilter);
        if (soOrder == null) {
            throw new BusinessException("根据对应的orderId:" + orderId + "找不到对应的订单");
        }
        soOrder.setOrderStatus(orderStatus);
        return this.saveOrderStatusBySoOrder(soOrder, crtId);
    }


    /**
     * @param savedOrUpdatedOrder 已经更新过的订单状态的订单
     * @param crtId
     * @return
     */
    @Override
    @Transactional
    public SoOrderStatus saveOrderStatusBySoOrder(SoOrder savedOrUpdatedOrder, String crtId) {
        logger.info("根据订单更新订单状态履历");
        Filter<SoOrderStatus> filter = new Filter<>();
        Long orderId = savedOrUpdatedOrder.getOrderId();
        String orderCode = savedOrUpdatedOrder.getOrderCode();
        Integer orderStatus = savedOrUpdatedOrder.getOrderStatus();
        filter.add("orderId", BaseOperatorEnum.EQUAL, orderId);
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification<SoOrderStatus> spec = new CommonSpecification<>(filter);
        List<SoOrderStatus> soOrderStatusList = orderStatusRepository.findAll(spec);
        for (SoOrderStatus status : soOrderStatusList) {
            status.setUpdId(crtId);
            status.setUpdTime(DateTimeUtil.getCustomerDate());
            status.setDelFlg(SystemConstant.DelFlg.DISABLE);
            status.setVer(status.getVer() + NumberConstant.IntDef.INT_ONE);
            this.orderStatusRepository.save(status);
        }
        SoOrderStatus newOrderStatus = new SoOrderStatus();
        Long statusId = this.maxId("so_order_status");
        newOrderStatus.setStatusId(statusId);
        newOrderStatus.setOrderId(orderId);
        newOrderStatus.setOrderCode(orderCode);
        newOrderStatus.setOrderStatus(orderStatus);
        newOrderStatus.setDelFlg(SystemConstant.DelFlg.ENABLE);
        newOrderStatus.setVer(NumberConstant.IntDef.INT_ONE);
        if (StringUtil.isEmpty(crtId)) {
            newOrderStatus.setCrtId(savedOrUpdatedOrder.getUpdId());
        } else {
            newOrderStatus.setCrtId(crtId);
        }
        newOrderStatus.setCrtTime(DateTimeUtil.getCustomerDate());
        this.orderStatusRepository.save(newOrderStatus);
        return newOrderStatus;
    }
}
