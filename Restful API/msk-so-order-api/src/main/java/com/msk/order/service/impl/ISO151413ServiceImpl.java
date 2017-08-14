package com.msk.order.service.impl;


import com.msk.common.constant.NumberConstant;
import com.msk.common.constant.SystemConstant;
import com.msk.common.constant.business.CapitalPoolConst;
import com.msk.common.constant.business.CommOrderConst;
import com.msk.common.constant.business.OrderCodeMasterDef;
import com.msk.common.data.jpa.BaseRepository;
import com.msk.common.data.jpa.condition.BaseOperatorEnum;
import com.msk.common.data.jpa.condition.Filter;
import com.msk.common.exception.BusinessException;
import com.msk.common.utils.DateTimeUtil;
import com.msk.common.utils.DecimalUtil;
import com.msk.order.bean.param.ISO151413RestCpRunningParam;
import com.msk.order.bean.param.ISO151413RestParam;
import com.msk.order.bean.result.ISO151413RestResult;
import com.msk.order.entity.SoOrder;
import com.msk.order.entity.SoPayment;
import com.msk.order.entity.SoSubOrder;
import com.msk.order.repository.SoOrderRepository;
import com.msk.order.repository.SoPaymentRepository;
import com.msk.order.repository.SoSubOrderRepository;
import com.msk.order.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * 已付款接口
 * Created by sun_jiaju on 2016/8/11.
 */
@Service
public class ISO151413ServiceImpl extends BaseService<SoOrder, Long> implements ISO151413Service {
    @Autowired
    private SoOrderRepository soOrderRepository;

    @Autowired
    private SoSubOrderRepository soSubOrderRepository;

    @Autowired
    private OrderStatusService orderStatusService;

    @Autowired
    private SubOrderStatusService subOrderStatusService;

    @Autowired
    private SoPaymentRepository soPaymentRepository;

    @Autowired
    private AsyncPostService asyncPostService;

    @Override
    public BaseRepository getRepository() {
        return soOrderRepository;
    }

    @Override
    @Transactional
    public ISO151413RestResult payOrder(ISO151413RestParam param) {
        Filter<SoOrder> filter = new Filter<>();
        filter.add("orderId", BaseOperatorEnum.EQUAL, param.getOrderId());
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        SoOrder soOrder = super.findOne(filter);
        if (soOrder == null) {
            throw new BusinessException("查询不到该订单ID的订单数据，请确定！");
        }
        // 待付款状态可以付款
        if (soOrder.getOrderStatus() != OrderCodeMasterDef.OrderStatus.OBLIGATION) {
            throw new BusinessException("当前订单订单状态不支持付款！");
        }

        // 计算已付款额
        BigDecimal payAmount = DecimalUtil.add(param.getOrderAmount(), soOrder.getPayAmount());
        // 设置付款状态
        if (payAmount.compareTo(soOrder.getOrderAmount()) > NumberConstant.IntDef.INT_ZERO) {
            throw new BusinessException("付款金额大于该订单金额，请确定！");
        } else if (payAmount.compareTo(soOrder.getOrderAmount()) == NumberConstant.IntDef.INT_ZERO) {
            soOrder.setPayStatus(OrderCodeMasterDef.PayStatus.ALL_PAY);
        } else {
            soOrder.setPayStatus(OrderCodeMasterDef.PayStatus.PART_PAY);
        }
        soOrder.setOrderStatus(OrderCodeMasterDef.OrderStatus.PAYMENT);
        soOrder.setPayAmount(payAmount);
        soOrder.setUpdId(param.getCrtId());
        soOrder.setUpdTime(param.getCrtTime());
        soOrder.setVer(soOrder.getVer() + NumberConstant.IntDef.INT_ONE);

        // 修改订单主表
        soOrder = soOrderRepository.save(soOrder);
        orderStatusService.saveOrderStatusBySoOrder(soOrder, soOrder.getUpdId());

        // 保存订单买家支付信息表
        this.saveSoPayment(param, soOrder);

        // 修改分批订单主表,保存分批订单状态
        List<SoSubOrder> soSubOrders = soOrder.getSoSubOrders();
        for (SoSubOrder soSubOrder : soSubOrders) {
            soSubOrder.setSubOrderStatus(OrderCodeMasterDef.SubOrderStatus.PAYMENT);
            soSubOrder.setSubPayStatus(soOrder.getPayStatus());
            soSubOrder.setUpdId(param.getCrtId());
            soSubOrder.setUpdTime(param.getCrtTime());
            soSubOrder.setVer(soSubOrder.getVer() + NumberConstant.IntDef.INT_ONE);
        }
        soSubOrderRepository.save(soSubOrders);
        for (SoSubOrder soSubOrder : soSubOrders) {
            subOrderStatusService.saveSubStatusBySubOrder(soSubOrder, soSubOrder.getUpdId());
        }

        // 调资金池接口
        this.sendCpRunning(param, soOrder);

        // 返回结果
        ISO151413RestResult result = new ISO151413RestResult();
        result.setOrderId(soOrder.getOrderId());
        result.setOrderStatus(soOrder.getOrderStatus());
        result.setVer(soOrder.getVer());
        return result;
    }

    /**
     * 支付成功回调
     *
     * @param orderCode
     */
    @Override
    @Transactional
    public void modifyStatus(String orderCode) {
        Filter<SoOrder> filter = new Filter<>();
        filter.add("orderCode", BaseOperatorEnum.EQUAL, orderCode);
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        SoOrder soOrder = super.findOne(filter);
        if (soOrder == null) {
            throw new BusinessException("查询不到该订单CODE的订单数据！");
        }
        // 判断order表状态是待分销还是已确认
        Boolean orderStatusFlg = true;
        // subOrder表及状态表修改插入
        List<SoSubOrder> soSubOrders = soOrder.getSoSubOrders();
        for (SoSubOrder soSubOrder : soSubOrders) {
            // 订单类型是分销订单及买手囤货订单，状态改为待分销；其余改为已确认
            if (soSubOrder.getOrderType() == OrderCodeMasterDef.OrderType.DISTRIBUTION_ORDER ||
                    soSubOrder.getOrderType() == OrderCodeMasterDef.OrderType.BUYER_STOCKPILING_ORDER) {
                soSubOrder.setSubOrderStatus(OrderCodeMasterDef.SubOrderStatus.WAIT_DISTRIBUTION);
            } else {
                soSubOrder.setSubOrderStatus(OrderCodeMasterDef.SubOrderStatus.CONFIRM);
                orderStatusFlg = false;
            }
            soSubOrder.setUpdTime(DateTimeUtil.getCustomerDate());
            soSubOrder.setVer(soSubOrder.getVer() + NumberConstant.IntDef.INT_ONE);
        }
        soSubOrderRepository.save(soSubOrders);
        for (SoSubOrder soSubOrder : soSubOrders) {
            subOrderStatusService.saveSubStatusBySubOrder(soSubOrder, soSubOrder.getUpdId());
        }

        // SoOrder表及状态表修改插入
        if (orderStatusFlg) {
            soOrder.setOrderStatus(OrderCodeMasterDef.OrderStatus.WAIT_DISTRIBUTION);
        } else {
            soOrder.setOrderStatus(OrderCodeMasterDef.OrderStatus.CONFIRM);
        }
        soOrder.setUpdTime(DateTimeUtil.getCustomerDate());
        soOrder.setVer(soOrder.getVer() + NumberConstant.IntDef.INT_ONE);
        soOrderRepository.save(soOrder);
        orderStatusService.saveOrderStatusBySoOrder(soOrder, soOrder.getUpdId());
    }

    /**
     * 保存订单买家支付信息表
     *
     * @param param
     * @param soOrder
     */
    public void saveSoPayment(ISO151413RestParam param, SoOrder soOrder) {
        Long maxPaymentId = this.maxId("so_payment");
        SoPayment soPayment = new SoPayment();
        soPayment.setPaymentId(maxPaymentId);
        soPayment.setOrderId(soOrder.getOrderId());
        soPayment.setOrderCode(soOrder.getOrderCode());
        soPayment.setPayMode(Integer.valueOf(param.getPaymentMode()));
        soPayment.setPayAmount(param.getOrderAmount());
        soPayment.setPayOrderCode(param.getPaymentOrderCode());
        soPayment.setPayTime(DateTimeUtil.parseDate(param.getPaymentTime(), "yyyy-MM-dd HH:mm:ss"));
        soPayment.setDelFlg(SystemConstant.DelFlg.ENABLE);
        soPayment.setCrtId(param.getCrtId());
        soPayment.setCrtTime(param.getCrtTime());
        soPaymentRepository.save(soPayment);
    }

    /**
     * 订单创建发送消息到mq，给资金池调用
     *
     * @param param
     */
    public void sendCpRunning(ISO151413RestParam param, SoOrder soOrder) {
        // 付款
        ISO151413RestCpRunningParam runParam = new ISO151413RestCpRunningParam();
        runParam.setCrtId(param.getCrtId());
        runParam.setBackType(CommOrderConst.BackType.BUYER);
        runParam.setAmountType(CommOrderConst.AmountType.ORDER_CONFIRM);
        runParam.setTransCode(soOrder.getOrderCode());
        runParam.setOrderId(soOrder.getOrderId());
        runParam.setTransType(CommOrderConst.TransType.MAINORDER);
        runParam.setPaidAmount(param.getOrderAmount());
        runParam.setPaidType(Integer.valueOf(param.getPaymentMode()));
        runParam.setPaidSeq(param.getPaymentOrderCode());
        runParam.setPaidTime(DateTimeUtil.getCustomerDate());
        if (Integer.valueOf(soOrder.getSalePlatform()) == NumberConstant.IntDef.INT_TWO) {
            runParam.setPayeeId(CapitalPoolConst.OtherConst.MSK_ID);
        } else {
            runParam.setPayeeId(CapitalPoolConst.OtherConst.SNK_ID);
        }
        runParam.setPayerId(soOrder.getBuyerId());
        // 平台类型
        runParam.setPlatform(Integer.valueOf(soOrder.getSalePlatform()));
        runParam.setPaymentType(soOrder.getPaymentType());
        asyncPostService.sendCpRunning(runParam);
    }
}
