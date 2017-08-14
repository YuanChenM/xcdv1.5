package com.msk.order.service.impl;

import com.msk.common.constant.CommCodeMasterConst;
import com.msk.common.constant.NumberConstant;
import com.msk.common.constant.SystemConstant;
import com.msk.common.constant.business.CapitalPoolConst;
import com.msk.common.constant.business.CommOrderConst;
import com.msk.common.constant.business.InventoryCodeMasterDef;
import com.msk.common.constant.business.OrderCodeMasterDef;
import com.msk.common.data.jpa.BaseRepository;
import com.msk.common.data.jpa.condition.BaseOperatorEnum;
import com.msk.common.data.jpa.condition.CommonSpecification;
import com.msk.common.data.jpa.condition.Filter;
import com.msk.common.exception.BusinessException;
import com.msk.common.utils.DateTimeUtil;
import com.msk.order.bean.param.*;
import com.msk.order.bean.result.ISO151410RestResult;
import com.msk.order.entity.*;
import com.msk.order.repository.*;
import com.msk.order.service.*;
import com.msk.order.util.SoRestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单整体取消实现
 * zhangqiang1
 */
@Service
public class ISO151410ServiceImpl extends BaseService<SoOrder, Long> implements ISO151410Service {

    @Autowired
    private SoOrderRepository orderRepository;

    @Autowired
    private SoOrderDetailRepository soOrderDetailRepository;

    @Autowired
    private SoSubOrderRepository soSubOrderRepository;

    @Autowired
    private SoSubOrderDetailRepository soSubOrderDetailRepository;

    @Autowired
    private OrderStatusService orderStatusService;

    @Autowired
    private SubOrderStatusService subOrderStatusService;

    @Autowired
    private SoOrderShipRepository soOrderShipRepository;

    @Autowired
    private SoOrderShipDetailRepository soOrderShipDetailRepository;


    @Autowired
    private AsyncPostService asyncPostService;


    @Override
    public BaseRepository getRepository() {
        return orderRepository;
    }

    @Override
    @Transactional
    public ISO151410RestResult modifyOrderCancel(ISO151410RestPram param, String loginId) {
        Long orderId = param.getOrderId();
        param.setUpdId(loginId);
        Filter<SoOrder> filter = new Filter<>();
        filter.add("orderId", BaseOperatorEnum.EQUAL, orderId);
        filter.add("ver", BaseOperatorEnum.EQUAL, param.getVer());
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        SoOrder order = super.findOneForce(filter);
        List<Integer> orderStatusList = new ArrayList<>();
        orderStatusList.add(OrderCodeMasterDef.OrderStatus.NEW);// 新建
        orderStatusList.add(OrderCodeMasterDef.OrderStatus.OBLIGATION);// 待付款
        orderStatusList.add(OrderCodeMasterDef.OrderStatus.PAYMENT);// 已付款
        orderStatusList.add(OrderCodeMasterDef.OrderStatus.PENDING_AUDIT);// 待审核
        orderStatusList.add(OrderCodeMasterDef.OrderStatus.HAVE_AUDITED);// 已审核
        orderStatusList.add(OrderCodeMasterDef.OrderStatus.WAIT_DISTRIBUTION);// 待分销
        orderStatusList.add(OrderCodeMasterDef.OrderStatus.IN_DISTRIBUTION);// 分销中
        orderStatusList.add(OrderCodeMasterDef.OrderStatus.CONFIRM);// 已确认
        orderStatusList.add(OrderCodeMasterDef.OrderStatus.WAIT_SEND); // 待发货
        Integer orderStatus = order.getOrderStatus();
        if (!orderStatusList.contains(orderStatus)) {
            throw new BusinessException("当前订单状态无法进行整单取消操作");
        }
        SoOrder updatedOrder = this.modifyOrderStatusCancel(param);
        ISO151410RestResult result = new ISO151410RestResult();
        result.setOrderId(updatedOrder.getOrderId());
        result.setVer(updatedOrder.getVer());
        this.sendCpTransaction(param, updatedOrder);//资金池
        this.sendOccupyDecrease(param, updatedOrder);// 库存释放
        return result;
    }


    /**
     * 整单取消整体操作
     *
     * @param orderParam
     */
    public SoOrder modifyOrderStatusCancel(ISO151410RestPram orderParam) {
        Date customerDate = DateTimeUtil.getCustomerDate();
        orderParam.setUpdTime(customerDate);
        orderParam.setCrtTime(customerDate);
        SoOrder updatedOrder = this.updateOrder(orderParam);
        if (updatedOrder.getOrderStatus() != OrderCodeMasterDef.OrderStatus.CANCEL) {
            throw new BusinessException("订单取消失败,没有找到对应的信息");
        } else {
            this.updateBatchSubOrder(updatedOrder, orderParam); // 修改 分批发货订单 和 对应的明细
            this.updateOrderDetailStatusAndCancelQty(updatedOrder, orderParam); // 订单明细状态：8 取消
            this.updateSubOrderDetailStatusAndCancelQty(updatedOrder, orderParam);//修改分批订单明细的状态
            this.updateOrderShipStatusAndCancelQty(updatedOrder, orderParam);//修改发货主表中的取消数量
            this.updateOrderShipDetailStatusAndCancelQty(updatedOrder, orderParam);//修改供货明细中的 取消数量和状态
        }
        return updatedOrder;
    }

    /**
     * 修改订单明细状态表 和取消数量
     *
     * @param orderParam
     * @return
     */

    public void updateOrderDetailStatusAndCancelQty(SoOrder soOrder, ISO151410RestPram orderParam) {
        Integer orderDetailStatus = OrderCodeMasterDef.OrderDetailStatus.CANCEL;
        List<SoOrderDetail> soOrderDetailList = soOrder.getSoOrderDetailList();
        for (SoOrderDetail orderDetail : soOrderDetailList) {
            orderDetail.setUpdId(orderParam.getUpdId());
            orderDetail.setCancelQty(orderDetail.getOrderQty());
            orderDetail.setUpdTime(orderParam.getUpdTime());
            orderDetail.setVer(orderDetail.getVer() + NumberConstant.IntDef.INT_ONE);
            orderDetail.setDetailStatus(orderDetailStatus);
            this.soOrderDetailRepository.save(orderDetail);
        }
    }


    /**
     * 修改 订单状态
     *
     * @param orderParam
     * @return
     */

    public SoOrder updateOrder(ISO151410RestPram orderParam) {
        Long orderId = orderParam.getOrderId();
        Filter<SoOrder> filter = new Filter<>();
        filter.add("orderId", BaseOperatorEnum.EQUAL, orderId);
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        SoOrder order = this.findOne(filter);
        Integer cancelStatus = OrderCodeMasterDef.OrderStatus.CANCEL;
        order.setOrderStatus(cancelStatus);
        order.setCancelReason(orderParam.getCancelReason());
        order.setCancelType(orderParam.getCancelType());
        order.setUpdTime(orderParam.getUpdTime());
        order.setUpdId(orderParam.getUpdId());
        order.setVer(order.getVer() + NumberConstant.IntDef.INT_ONE);
        this.orderStatusService.saveOrderStatusBySoOrder(order, orderParam.getUpdId());
        return orderRepository.save(order);
    }


    /**
     * 修改 分批订单状态
     *
     * @param orderParam
     * @return
     */

    public void updateBatchSubOrder(SoOrder order, ISO151410RestPram orderParam) {
        Integer subOrderStatus = OrderCodeMasterDef.SubOrderStatus.CANCELING;
        List<SoSubOrder> soSubOrderList = order.getSoSubOrders();
        for (SoSubOrder subOrder : soSubOrderList) {
            subOrder.setUpdId(orderParam.getUpdId());
            subOrder.setUpdTime(orderParam.getUpdTime());
            subOrder.setVer(subOrder.getVer() + NumberConstant.IntDef.INT_ONE);
            subOrder.setSubOrderStatus(subOrderStatus);
            SoSubOrder updatedSubOrder = this.soSubOrderRepository.save(subOrder);
            this.subOrderStatusService.saveSubStatusBySubOrder(updatedSubOrder, orderParam.getUpdId());
        }
    }

    /**
     * 修改 分批订单明细 状态 和 取消数量
     *
     * @param orderParam
     * @return
     */

    public void updateSubOrderDetailStatusAndCancelQty(SoOrder order, ISO151410RestPram orderParam) {
        Integer subOrderDetailStatus = OrderCodeMasterDef.SubOrderDetailStatus.CANCEL;
        List<SoSubOrderDetail> soSubOrderDetailList = order.getSoSubOrderDetailList();
        for (SoSubOrderDetail subOrderDetail : soSubOrderDetailList) {
            subOrderDetail.setUpdId(orderParam.getUpdId());
            subOrderDetail.setUpdTime(orderParam.getUpdTime());
            subOrderDetail.setVer(subOrderDetail.getVer() + NumberConstant.IntDef.INT_ONE);
            subOrderDetail.setDetailStatus(subOrderDetailStatus);
            subOrderDetail.setCancelQty(subOrderDetail.getOrderQty());
            this.soSubOrderDetailRepository.save(subOrderDetail);
        }
    }


    /**
     * 修改发货主表中的取消数量
     *
     * @param order
     * @param orderParam
     */
    public void updateOrderShipStatusAndCancelQty(SoOrder order, ISO151410RestPram orderParam) {
        List<SoOrderShip> shipList = order.getSoOrderShipList();
        for (SoOrderShip ship : shipList) {
            ship.setShipStatus(OrderCodeMasterDef.ShipStatus.CANCELED);
            ship.setVer(NumberConstant.IntDef.INT_ONE + ship.getVer());
            ship.setCancelManId(orderParam.getUpdId());
            ship.setUpdTime(orderParam.getUpdTime());
            ship.setCancelReason(Integer.getInteger(orderParam.getCancelReason()));
            ship.setUpdId(orderParam.getUpdId());
            this.soOrderShipRepository.save(ship);
        }
    }


    /**
     * 修改供货明细中的 取消数量和状态
     *
     * @param order
     * @param orderParam
     */
    public void updateOrderShipDetailStatusAndCancelQty(SoOrder order, ISO151410RestPram orderParam) {
        List<SoOrderShipDetail> shipDetailList = order.getSoOrderShipDetailList();
        for (SoOrderShipDetail shipDetail : shipDetailList) {
            shipDetail.setDetailStatus(OrderCodeMasterDef.OrderDetailAvailabilityStatus.CANCEL);
            shipDetail.setVer(NumberConstant.IntDef.INT_ONE + shipDetail.getVer());
            shipDetail.setUpdTime(orderParam.getUpdTime());
            shipDetail.setUpdId(orderParam.getUpdId());
            shipDetail.setCancelQty(shipDetail.getSuppQty());
            this.soOrderShipDetailRepository.save(shipDetail);
        }
    }


    /**
     * 调用资金池 交易明细接口
     *
     * @param param
     * @param order
     */
    public void sendCpTransaction(ISO151410RestPram param, SoOrder order) {
        ISO151410RestCpTransactionParam cpTransactionParam = new ISO151410RestCpTransactionParam();
        cpTransactionParam.setCrtId(param.getUpdId());
        cpTransactionParam.setInsertFlg(NumberConstant.IntDef.INT_ZERO);//是否新增标识 0：否 1：是
        cpTransactionParam.setTransCode(order.getOrderCode());//交易编码
        cpTransactionParam.setOrderId(order.getOrderId());// 订单id
        cpTransactionParam.setTransType(Integer.valueOf(CommOrderConst.SearchType.ORDER));//交易类型 0 主订单 1管理费用清单
        cpTransactionParam.setOperateDate(order.getUpdTime());//操作时间
        if (order.getSalePlatform().equals(String.valueOf(NumberConstant.IntDef.INT_ONE))) { // 平台类型
            cpTransactionParam.setSupplyPlatform(CapitalPoolConst.SupplyPlatform.SNK);
        } else {
            cpTransactionParam.setSupplyPlatform(CapitalPoolConst.SupplyPlatform.MSK);
        }
        cpTransactionParam.setTransFlg(NumberConstant.IntDef.INT_ONE);//交易标志 0：正常 1：交易关闭  订单关闭的情况下传1，其余情况传0
        cpTransactionParam.setPaymentType(order.getPaymentType());//支付类型 1:在线支付,2:线下支付
        //  cpTransactionParam.setRefundDetailList(this.findFundDetail(soReturn.getReturnId()));//退回费用明细列表
        this.asyncPostService.sendCpTransactionBy151410Cancel(cpTransactionParam);
    }


    /**
     * 调用库存释放
     *
     * @param param
     * @param order
     */
    public void sendOccupyDecrease(ISO151410RestPram param, SoOrder order) {
        List<SoOrderShipDetail> soOrderShipDetailList = order.getSoOrderShipDetailList();
        if (CollectionUtils.isEmpty(soOrderShipDetailList)) {// 没到供应商
            this.sendSlOccupyDecrease(param, order);
        } else {// 到供应商
            this.sendSpOccupyDecrease(param, order);
        }
    }

    /**
     * 卖家库存占用减少
     *
     * @param param
     * @param order
     */
    public void sendSlOccupyDecrease(ISO151410RestPram param, SoOrder order) {
        ISO151410IRestSlInvOccupyDecreaseParam slParam = new ISO151410IRestSlInvOccupyDecreaseParam();
        slParam.setUpdId(param.getUpdId());
        if (order.getSalePlatform().equals(OrderCodeMasterDef.SalePlatform.YDP)) {
            slParam.setPlantFormId(CommCodeMasterConst.SystemCode.SYSTEM_CODE_SNK);
        } else {
            slParam.setPlantFormId(CommCodeMasterConst.SystemCode.SYSTEM_CODE_MSK);
        }
        slParam.setLgcsCode(order.getDistrictCode());//物流区编码
        slParam.setOrderId(Long.toString(order.getOrderId()));//订单id
        slParam.setOrderCode(order.getOrderCode());//订单code
        slParam.setDecreaseTime(DateTimeUtil.formatDate("yyyy-MM-dd HH:mm:ss", DateTimeUtil.getCustomerDate()));//占用减少时间(YYYY-MM-DD HH:mm:ss),当前时间即可
        slParam.setSlType(this.getSlType(order));//卖家类型-CodeMaster 1神龙客 2美侍客 3买手
        slParam.setSlCode(order.getSellerCode());// 卖家编码
        slParam.setAllocateType(Integer.toString(NumberConstant.IntDef.INT_ONE));// 占用类型：1，先卖家占用，后供应商占用；2，直接供应商占用
        slParam.setPdList(this.getInvProductList(order));
        this.asyncPostService.SlOccupyDecreaseBy151410Cancel(slParam);
    }

    /**
     * 供应商库存减少
     *
     * @param param
     * @param order
     */
    public void sendSpOccupyDecrease(ISO151410RestPram param, SoOrder order) {
        ISO151410IRestSpInvOccupyDecreaseParam spParam = new ISO151410IRestSpInvOccupyDecreaseParam();
        spParam.setUpdId(param.getUpdId());
        if (order.getSalePlatform().equals(OrderCodeMasterDef.SalePlatform.YDP)) {
            spParam.setPlantFormId(CommCodeMasterConst.SystemCode.SYSTEM_CODE_SNK);
        } else {
            spParam.setPlantFormId(CommCodeMasterConst.SystemCode.SYSTEM_CODE_MSK);
        }
        spParam.setLgcsCode(order.getDistrictCode());//物流区编码
        spParam.setOrderId(Long.toString(order.getOrderId()));//订单id
        spParam.setOrderCode(order.getOrderCode());//订单code
        spParam.setDecreaseTime(DateTimeUtil.formatDate("yyyy-MM-dd HH:mm:ss", DateTimeUtil.getCustomerDate()));//占用减少时间(YYYY-MM-DD HH:mm:ss),当前时间即可
        spParam.setSlType(this.getSlType(order));//卖家类型-CodeMaster 1平台 2买手
        spParam.setSlCode(order.getSellerCode());// 卖家编码
        spParam.setAllocateType(Integer.toString(NumberConstant.IntDef.INT_ONE));// 占用类型：1，先卖家占用，后供应商占用；2，直接供应商占用
        spParam.setPdList(this.getInvProductList(order));
        this.asyncPostService.SpOccupyDecreaseBy151410Cancel(spParam);
    }


    /**
     * 库存占用释放后  设置subOrder 状态为整单取消
     *
     * @param orderId
     */

    @Override
    public void setSubOrderCanceledStatusByOrderId(String orderId) {
        Long longOrderId = Long.parseLong(orderId);
        Filter<SoSubOrder> filter = new Filter<>();
        filter.add("orderId", BaseOperatorEnum.EQUAL, longOrderId);
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification<SoSubOrder> spec = new CommonSpecification<>(filter);
        List<SoSubOrder> soSubOrders = this.soSubOrderRepository.findAll(spec);
        for (SoSubOrder subOrder : soSubOrders) {
            subOrder.setSubOrderStatus(OrderCodeMasterDef.SubOrderStatus.CANCEL);// 整单取消
            subOrder.setVer(subOrder.getVer() + NumberConstant.IntDef.INT_ONE);
            subOrder.setUpdTime(DateTimeUtil.getCustomerDate());
            SoSubOrder updatedSubOrder = this.soSubOrderRepository.save(subOrder);
            subOrderStatusService.saveSubStatusBySubOrder(updatedSubOrder, subOrder.getUpdId());
        }
    }


    /**
     * 根据订单类型 获取卖家类型
     * 卖家类型-CodeMaster 1平台 2买手
     *
     * @param soOrder
     * @return
     */

    public String getSlType(SoOrder soOrder) {
        String slType = "";
        if (soOrder.getOrderType().equals(OrderCodeMasterDef.OrderType.BUYER_SALE_ORDER)
                || soOrder.getOrderType().equals(OrderCodeMasterDef.OrderType.THIRD_BUYER_SALE_ORDER)) {
            slType = Integer.toString(OrderCodeMasterDef.SellerType.SALE);
        } else {
            slType = Integer.toString(OrderCodeMasterDef.SellerType.PLATFORM);
        }
        return slType;
    }

    /**
     * 根据 订单获取 占用释放的明细
     *
     * @param order
     * @return
     */
    public List<ISO151410IRestSlInvProductParam> getInvProductList(SoOrder order) {
        List<ISO151410IRestSlInvProductParam> pdList = new ArrayList<>();
        List<SoOrderShipDetail> soOrderShipDetailList = order.getSoOrderShipDetailList();
        if (CollectionUtils.isEmpty(soOrderShipDetailList)) {//没有到供应商   查询订单明细
            List<SoOrderDetail> soOrderDetailList = order.getSoOrderDetailList();
            for (SoOrderDetail orderDetail : soOrderDetailList) {
                ISO151410IRestSlInvProductParam productParam = new ISO151410IRestSlInvProductParam();
                productParam.setPdCode(orderDetail.getPdCode());
                productParam.setInventoryStatus(Integer.toString(InventoryCodeMasterDef.GoodType.GT_GOOD));// 库存类型 良品
                productParam.setDecreaseQty(orderDetail.getOrderQty().intValue());// 全部取消量 就是订单量
                pdList.add(productParam);
            }
        } else {// 到供应商   查询供货明细
            for (SoOrderShipDetail shipDetail : soOrderShipDetailList) {
                ISO151410IRestSlInvProductParam productParam = new ISO151410IRestSlInvProductParam();
                productParam.setPdCode(shipDetail.getPdCode());
                productParam.setSupplierCode(shipDetail.getSupplierCode());// 供应商编码
                productParam.setInventoryStatus(Integer.toString(InventoryCodeMasterDef.GoodType.GT_GOOD));// 库存类型 良品
                Integer decreaseQty = null;
                if (shipDetail.getSuppQty() != null) {
                    decreaseQty = shipDetail.getSuppQty().intValue();
                }
                productParam.setDecreaseQty(decreaseQty);// 全部取消量  供应量
                pdList.add(productParam);
            }
        }
        return pdList;
    }

}
