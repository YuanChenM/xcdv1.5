package com.msk.order.service.impl;


import com.msk.common.constant.NumberConstant;
import com.msk.common.constant.SystemConstant;
import com.msk.common.constant.business.OrderCodeMasterDef;
import com.msk.common.data.jpa.BaseRepository;
import com.msk.common.data.jpa.condition.BaseOperatorEnum;
import com.msk.common.data.jpa.condition.CommonSpecification;
import com.msk.common.data.jpa.condition.Filter;
import com.msk.common.data.jpa.impl.BaseJdbcImpl;
import com.msk.common.exception.BusinessException;
import com.msk.common.utils.DateTimeUtil;
import com.msk.common.utils.StringUtil;
import com.msk.order.bean.param.ISO151415ModifyStatusParam;
import com.msk.order.bean.param.ISO151433RestParam;
import com.msk.order.bean.result.ISO151415ModifyStatusBeanResult;
import com.msk.order.bean.result.ISO151433RestResult;
import com.msk.order.entity.*;
import com.msk.order.repository.*;
import com.msk.order.service.BaseService;
import com.msk.order.service.ISO151433Service;
import com.msk.order.service.OrderStatusService;
import com.msk.order.service.SubOrderStatusService;
import com.msk.order.util.SqlUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wang_shuai on 2016/8/25.
 */
@Service
public class ISO151433ServiceImpl extends BaseService implements ISO151433Service {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ISO151433ServiceImpl.class);
    @Autowired
    private SoOrderShipRepository soOrderShipRepository;
    @Autowired
    private SoOrderShipDetailRepository soOrderShipDetailRepository;
    @Autowired
    private SoOrderDetailRepository soOrderDetailRepository;
    @Autowired
    private SoSubOrderRepository soSubOrderRepository;
    @Autowired
    private SoSubOrderDetailRepository soSubOrderDetailRepository;
    @Autowired
    private SoOrderRepository soOrderRepository;
    @Autowired
    private BaseJdbcImpl baseJdbc;
    @Autowired
    private OrderStatusService orderStatusService;
    @Autowired
    private SubOrderStatusService subOrderStatusService;

    @Override
    @Transactional
    public ISO151433RestResult cancelShipment(ISO151433RestParam param){
        logger.debug("开始更新各表状态！");
        // 更新订单发货主表
        Filter<SoOrderShip> filter = new Filter<>();
        filter.add("shipId", BaseOperatorEnum.EQUAL,param.getShipId());
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        filter.add("shipStatus", BaseOperatorEnum.EQUAL,OrderCodeMasterDef.ShipStatus.CANCELING);
        CommonSpecification commonSpecification = new CommonSpecification(filter);
        SoOrderShip soOrderShip = soOrderShipRepository.findOne(commonSpecification);
        if (soOrderShip == null) {
            throw new BusinessException("根据传入参数查询不到取消中的发货单数据！");
        }
        soOrderShip.setShipStatus(OrderCodeMasterDef.ShipStatus.CANCELED);
        if (!StringUtil.isEmpty(param.getCancelManId())){
            soOrderShip.setCancelManId(param.getCancelManId());
        }
        if (!StringUtil.isEmpty(param.getCancelManName())){
            soOrderShip.setCancelManName(param.getCancelManName());
        }
        if (param.getCancelTime() != null){
            Date cancelTime = DateTimeUtil.parseDate(param.getCancelTime(), DateTimeUtil.FORMAT_DATE_YYYYMMDD  + " HH:mm:ss");
            soOrderShip.setCancelTime(cancelTime);

        }
        if (param.getCancelReason() != null){
            soOrderShip.setCancelReason(param.getCancelReason());
        }
        if (!StringUtil.isEmpty(param.getRemark())){
            soOrderShip.setCancelRemark(param.getRemark());
        }
        param.setOrderId(soOrderShip.getOrderId());
        soOrderShip.setUpdId(param.getUpdId());
        soOrderShip.setVer(soOrderShip.getVer() + 1);
        soOrderShipRepository.save(soOrderShip);
        //更新订单供货明细信息表
        modifyShipDetailStatus(param);
        //更新订单明细表状态
        modifyOrderDetailStatus(param);
        //更新分批明细表状态
        modifySubOrderDetailStatus(param);


        //更新订单状态
        Filter<SoOrderShip> filterResult = new Filter<>();
        filterResult.add("shipId", BaseOperatorEnum.EQUAL,param.getShipId());
        filterResult.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification specification = new CommonSpecification(filterResult);
        SoOrderShip result = soOrderShipRepository.findOne(specification);
        ISO151415ModifyStatusParam modifyStatusParam = new ISO151415ModifyStatusParam();
        modifyStatusParam.setOrderId(result.getOrderId());
        modifyStatusParam.setUpdId(param.getUpdId());
        modifyStatusParam.setSubOrderId(result.getSubOrderId());

        modifyOrderStatusAndSubOrderStatusByOrderId(modifyStatusParam);

        ISO151433RestResult iso151433RestResult = new ISO151433RestResult();
        iso151433RestResult.setVer(result.getVer());
        iso151433RestResult.setOrderId(result.getOrderId());
        iso151433RestResult.setShipId(result.getShipId());
        return iso151433RestResult;
    }

    /**
     * 更新订单供货明细信息表
     * @param param
     */
    @Transactional
    public void modifyShipDetailStatus(ISO151433RestParam param){
        Filter<SoOrderShipDetail> filter = new Filter<>();
        List list = new ArrayList();
        list.add(param.getShipId());
        filter.setParamList(list);
        List<SoOrderShipDetail> orderShipDetailList = this.findAll(filter);
        List<SoOrderShipDetail> orderShipDetailParamList = new ArrayList<>();
        for (SoOrderShipDetail soOrderShipDetail : orderShipDetailList){
            soOrderShipDetail.setDetailStatus(OrderCodeMasterDef.OrderDetailAvailabilityStatus.CANCEL);
            soOrderShipDetail.setUpdId(param.getUpdId());
            soOrderShipDetail.setVer(soOrderShipDetail.getVer() + 1);
            soOrderShipDetail.setUpdTime(DateTimeUtil.getCustomerDate());
            orderShipDetailParamList.add(soOrderShipDetail);
        }
        this.save(orderShipDetailParamList);
    }

    /**
     * 更新订单明细表状态为已取消
     * @param param
     */
    @Transactional
    public void modifyOrderDetailStatus(ISO151433RestParam param){
        String sql = SqlUtil.getSqlBySqlId("ISO151433.findSoDetailList");
        List list = new ArrayList();
        list.add(param.getShipId());
        List<SoOrderDetail> soOrderDetailList = baseJdbc.queryForList(sql, list, SoOrderDetail.class, true);
        List<SoOrderDetail> soOrderDetailListParam = new ArrayList<>();
        for (SoOrderDetail soOrderDetail : soOrderDetailList){
            soOrderDetail.setDetailStatus(OrderCodeMasterDef.OrderDetailStatus.CANCEL);
            soOrderDetail.setUpdId(param.getUpdId());
            soOrderDetail.setUpdTime(DateTimeUtil.getCustomerDate());
            soOrderDetail.setVer(soOrderDetail.getVer() + 1);
            soOrderDetailListParam.add(soOrderDetail);
        }
        soOrderDetailRepository.save(soOrderDetailListParam);
    }
    /**
     * 更新分批订单明细表状态为已取消
     * @param param
     */
    @Transactional
    public void modifySubOrderDetailStatus(ISO151433RestParam param){
        String sql = SqlUtil.getSqlBySqlId("ISO151433.findSoSubDetailList");
        List list = new ArrayList();
        list.add(param.getShipId());
        List<SoSubOrderDetail> soSubOrderDetailList = baseJdbc.queryForList(sql, list, SoSubOrderDetail.class, true);
        List<SoSubOrderDetail> soSubOrderDetailListParam = new ArrayList<>();
        for (SoSubOrderDetail soSubOrderDetail : soSubOrderDetailList){
            soSubOrderDetail.setDetailStatus(OrderCodeMasterDef.SubOrderStatus.CANCEL);
            soSubOrderDetail.setUpdId(param.getUpdId());
            soSubOrderDetail.setUpdTime(DateTimeUtil.getCustomerDate());
            soSubOrderDetail.setVer(soSubOrderDetail.getVer() + 1);
            soSubOrderDetailListParam.add(soSubOrderDetail);
        }
        soSubOrderDetailRepository.save(soSubOrderDetailListParam);
    }

    /**
     * 修改订单状态
     *
     */
    @Transactional
    public void modifyOrderStatusAndSubOrderStatusByOrderId(ISO151415ModifyStatusParam param){
        // 查询订单并更新状态
        Filter<SoOrder> filterOrder = new Filter<>();
        filterOrder.add("orderId",BaseOperatorEnum.EQUAL,param.getOrderId());
        filterOrder.add("delFlg",BaseOperatorEnum.EQUAL,SystemConstant.DelFlg.ENABLE);
        CommonSpecification specificationOrder = new CommonSpecification(filterOrder);
        List<SoOrder> orderList = soOrderRepository.findAll(specificationOrder);
        if (orderList.size() != NumberConstant.IntDef.INT_ZERO){
            modifyOrderStatusByDetailStatus(param);
        }
        //查询分批订单并更新状态
        Filter<SoSubOrder> filter = new Filter<>();
        filter.add("subOrderId",BaseOperatorEnum.EQUAL,param.getSubOrderId());
        filter.add("delFlg",BaseOperatorEnum.EQUAL,SystemConstant.DelFlg.ENABLE);
        CommonSpecification specification = new CommonSpecification(filter);
        List<SoSubOrder> list = soSubOrderRepository.findAll(specification);
        if (list.size() != NumberConstant.IntDef.INT_ZERO){
            modifySubOrderStatusBySubDetailStatus(param);
        }
    }

    /**
     * 根据明细状态修改订单状态
     *
     */
    @Transactional
    public void modifyOrderStatusByDetailStatus(ISO151415ModifyStatusParam param){
        String sql = SqlUtil.getSqlBySqlId("ISO151433.findStatusCount");
        List paramList = new ArrayList();
        //list中循环查询7个相同参数
        for (int i=0;i<NumberConstant.IntDef.INT_EIGHT;i++){
            paramList.add(param.getOrderId());
        }
        List<Map<String,Object>> mapList = baseJdbc.queryForListNotCount(sql,paramList,null,true);
        ISO151415ModifyStatusBeanResult countBean = new ISO151415ModifyStatusBeanResult();
        if (mapList.size() != 0){
            try {
                BeanUtils.populate(countBean, mapList.get(0));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }else {
            throw new com.msk.common.exception.BusinessException("查询不到状态数量");
        }

        Integer orderStatus = null;
        // 设置订单状态
        if (countBean != null){
            if (countBean.getAllCount() == countBean.getCancelCount()){
                // 所属明细状态全为【已取消】，设为【已取消】
                orderStatus = OrderCodeMasterDef.OrderStatus.CANCEL;
            } else if(countBean.getAllCount() == countBean.getCancelCount() + countBean.getAllReceiptCount()){
                // 所属明细状态除去【已取消】，全为【全部收货】，设为【全部收货】
                orderStatus = OrderCodeMasterDef.OrderStatus.ALL_RECEIPT;
            } else if (countBean.getAllReceiptCount() > 0 || countBean.getPartialReceiptCount() > 0 ){
                // 所属明细状态存在【全部收货】或【部分收货】，设为【部分收货】
                orderStatus = OrderCodeMasterDef.OrderStatus.PARTIAL_RECEIPT;
            } else if(countBean.getAllCount() == countBean.getCancelCount() + countBean.getAllShipmentCount()){
                // 所属明细状态除去【已取消】，全为【全部发货】，设为【全部发货】
                orderStatus = OrderCodeMasterDef.OrderStatus.ALL_SHIPMENT;
            } else if (countBean.getAllShipmentCount() > 0 || countBean.getPartialShipmentCount() > 0 ){
                // 所属明细状态存在【全部发货】或【部分发货】，设为【部分发货】
                orderStatus = OrderCodeMasterDef.OrderStatus.PARTIAL_SHIPMENT;
            } else if (countBean.getWaitDisCount() > 0){
                // 所属明细状态存在【待分销】，设为【待分销】
                orderStatus = OrderCodeMasterDef.OrderStatus.WAIT_DISTRIBUTION;
            } else if (countBean.getWaitSendCount() > 0){
                // 所属明细状态存在【待发货】，设为【待发货】
                orderStatus = OrderCodeMasterDef.OrderStatus.WAIT_SEND;
            } else{
                // 以上情况外，设为【已确认】
                orderStatus = OrderCodeMasterDef.OrderStatus.CONFIRM;
            }
        }
        param.setOrderStatus(orderStatus);

        Filter<SoOrder> filter = new Filter<>();
        filter.add("orderId",BaseOperatorEnum.EQUAL,param.getOrderId());
        filter.add("delFlg",BaseOperatorEnum.EQUAL,SystemConstant.DelFlg.ENABLE);
        CommonSpecification specification = new CommonSpecification(filter);
        SoOrder soOrder = soOrderRepository.findOne(specification);
        if (soOrder != null){
            soOrder.setOrderStatus(param.getOrderStatus());
            soOrder.setUpdId(param.getUpdId());
            soOrder.setUpdTime(DateTimeUtil.getCustomerDate());
            soOrder.setVer(soOrder.getVer()+1);
            soOrderRepository.save(soOrder);
            orderStatusService.saveOrderStatusBySoOrder(soOrder,param.getUpdId());
        }
    }


    /**
     * 根据分批明细状态修改分批主订单状态
     *
     */
    @Transactional
    public void modifySubOrderStatusBySubDetailStatus(ISO151415ModifyStatusParam param){
        String sql = SqlUtil.getSqlBySqlId("ISO151433.findSubOrderDetailStatusCount");
        List paramList = new ArrayList();
        //list中循环查询7个相同参数
        for (int i=0;i<NumberConstant.IntDef.INT_EIGHT;i++){
            paramList.add(param.getSubOrderId());
        }
        List<Map<String,Object>> mapList = baseJdbc.queryForListNotCount(sql,paramList,null,true);
        ISO151415ModifyStatusBeanResult countBean = new ISO151415ModifyStatusBeanResult();
        if (mapList.size() != 0){
            try {
                BeanUtils.populate(countBean, mapList.get(0));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }else {
            throw new com.msk.common.exception.BusinessException("查询不到分批订单状态数量");
        }

        Integer subOrderStatus = null;
        // 设置订单状态
        if (countBean != null){
            if (countBean.getAllCount() == countBean.getCancelCount()){
                // 所属明细状态全为【已取消】，设为【已取消】
                subOrderStatus = OrderCodeMasterDef.SubOrderStatus.CANCEL;
            } else if(countBean.getAllCount() == countBean.getCancelCount() + countBean.getAllReceiptCount()){
                // 所属明细状态除去【已取消】，全为【全部收货】，设为【全部收货】
                subOrderStatus = OrderCodeMasterDef.SubOrderStatus.ALL_RECEIPT;
            } else if (countBean.getAllReceiptCount() > 0 || countBean.getPartialReceiptCount() > 0 ){
                // 所属明细状态存在【全部收货】或【部分收货】，设为【部分收货】
                subOrderStatus = OrderCodeMasterDef.SubOrderStatus.PARTIAL_RECEIPT;
            } else if(countBean.getAllCount() == countBean.getCancelCount() + countBean.getAllShipmentCount()){
                // 所属明细状态除去【已取消】，全为【全部发货】，设为【全部发货】
                subOrderStatus = OrderCodeMasterDef.SubOrderStatus.ALL_SHIPMENT;
            } else if (countBean.getAllShipmentCount() > 0 || countBean.getPartialShipmentCount() > 0 ){
                // 所属明细状态存在【全部发货】或【部分发货】，设为【部分发货】
                subOrderStatus = OrderCodeMasterDef.SubOrderStatus.PARTIAL_SHIPMENT;
            } else if (countBean.getWaitDisCount() > 0){
                // 所属明细状态存在【待分销】，设为【待分销】
                subOrderStatus = OrderCodeMasterDef.SubOrderStatus.WAIT_DISTRIBUTION;
            } else if (countBean.getWaitSendCount() > 0){
                // 所属明细状态存在【待发货】，设为【待发货】
                subOrderStatus = OrderCodeMasterDef.SubOrderStatus.WAIT_SEND;
            } else{
                // 以上情况外，设为【已确认】
                subOrderStatus = OrderCodeMasterDef.SubOrderStatus.CONFIRM;
            }
        }
        param.setSubOrderStatus(subOrderStatus);

        Filter<SoSubOrder> filter = new Filter<>();
        filter.add("subOrderId",BaseOperatorEnum.EQUAL,param.getSubOrderId());
        filter.add("delFlg",BaseOperatorEnum.EQUAL,SystemConstant.DelFlg.ENABLE);
        CommonSpecification specification = new CommonSpecification(filter);
        SoSubOrder soSubOrder = soSubOrderRepository.findOne(specification);
        if (soSubOrder != null){
            soSubOrder.setSubOrderStatus(param.getSubOrderStatus());
            soSubOrder.setUpdId(param.getUpdId());
            soSubOrder.setUpdTime(DateTimeUtil.getCustomerDate());
            soSubOrder.setVer(soSubOrder.getVer()+1);
            soSubOrderRepository.save(soSubOrder);
            subOrderStatusService.saveSubStatusBySubOrder(soSubOrder,param.getUpdId());
        }
    }



    @Override
    public BaseRepository getRepository() {
        return soOrderShipDetailRepository;
    }
}
