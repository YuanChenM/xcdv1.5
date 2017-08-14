package com.msk.order.service.impl;


import com.msk.common.constant.NumberConstant;
import com.msk.common.constant.SystemConstant;
import com.msk.common.data.jpa.BaseRepository;
import com.msk.common.data.jpa.condition.BaseOperatorEnum;
import com.msk.common.data.jpa.condition.Filter;
import com.msk.common.exception.BusinessException;
import com.msk.common.utils.DateTimeUtil;
import com.msk.common.utils.StringUtil;
import com.msk.order.bean.param.ISO151803RestParam;
import com.msk.order.bean.result.ISO151803RestOrderShipResult;
import com.msk.order.bean.result.ISO151803RestProductResult;
import com.msk.order.bean.result.ISO151803RestResult;
import com.msk.order.entity.SoDeliver;
import com.msk.order.entity.SoOrder;
import com.msk.order.entity.SoOrderShip;
import com.msk.order.entity.SoOrderShipDetail;
import com.msk.order.repository.SoOrderRepository;
import com.msk.order.service.BaseService;
import com.msk.order.service.ISO151803Service;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * ISO151803_订单发货明细信息查询接口
 * Created by sun_jiaju on 2016/8/29.
 */
@Service
public class ISO151803ServiceImpl extends BaseService<SoOrder, Long> implements ISO151803Service {
    @Autowired
    private SoOrderRepository soOrderRepository;

    @Override
    public BaseRepository getRepository() {
        return soOrderRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public ISO151803RestResult searchShipDetail(ISO151803RestParam param) throws Exception {
        SoOrder soOrder = super.findOne(this.getFilter(param));
        if (soOrder == null) {
            throw new BusinessException("根据所传参数，查询不到相应数据！");
        }
        // 设置order级别返回值
        ISO151803RestResult result = this.setOrderResult(soOrder);
        for (SoOrderShip soOrderShip : soOrder.getSoOrderShipList()) {
            if (param.getShipId() == null || param.getShipId() == NumberConstant.IntDef.INT_ZERO) {
                result.getShipList().add(this.setOrderShipResult(soOrderShip));
            } else {
                if (soOrderShip.getShipId().equals(param.getShipId())) {
                    result.getShipList().add(this.setOrderShipResult(soOrderShip));
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 设置order级别返回值
     *
     * @param soOrder
     */
    private ISO151803RestResult setOrderResult(SoOrder soOrder) throws Exception {
        ISO151803RestResult result = new ISO151803RestResult();
        result.setShipList(new ArrayList<ISO151803RestOrderShipResult>());
        BeanUtils.copyProperties(result, soOrder);
        result.setBuyersCode(soOrder.getBuyerCode());
        result.setBuyersName(soOrder.getBuyerName());
        result.setPaidAmount(soOrder.getPayAmount());
        // SoPayment最早付款时间
        if (CollectionUtils.isNotEmpty(soOrder.getSoPaymentList())) {
            result.setPaidTime(soOrder.getSoPaymentList().get(0).getPayTime());
        }
        return result;
    }

    /**
     * 设置ship级别返回值
     *
     * @param soOrderShip
     */
    private ISO151803RestOrderShipResult setOrderShipResult(SoOrderShip soOrderShip) throws Exception {
        ISO151803RestOrderShipResult iso151803RestOrderShipResult = new ISO151803RestOrderShipResult();
        iso151803RestOrderShipResult.setProductList(new ArrayList<ISO151803RestProductResult>());
        BeanUtils.copyProperties(iso151803RestOrderShipResult, soOrderShip);
        iso151803RestOrderShipResult.setProDate(soOrderShip.getForecastReceiveTime());
        iso151803RestOrderShipResult.setExpectReceiveTime(soOrderShip.getForecastReceiveTime());
        // SoDeliver最大实际送达时间
        Date maxDate = null;
        for (SoDeliver soDeliver : soOrderShip.getSoDeliverList()) {
            if (soDeliver.getActualReceiveDate() != null && maxDate == null) {
                maxDate = soDeliver.getActualReceiveDate();
            }
            if (soDeliver.getActualReceiveDate() != null && soDeliver.getActualReceiveDate().after(maxDate)) {
                maxDate = soDeliver.getActualReceiveDate();
            }
        }
        iso151803RestOrderShipResult.setActualReceiveTime(maxDate);
        // 通过SoDeliver最大实际送达时间判断是否可以退货
        if (maxDate == null) {
            // 没有实际收货，不可退货
            iso151803RestOrderShipResult.setCanReturn(NumberConstant.IntDef.INT_TWO);
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(maxDate);
            int actualReceiveDate = calendar.get(Calendar.DAY_OF_YEAR);
            calendar.setTime(DateTimeUtil.getCustomerDate());
            int dayNow = calendar.get(Calendar.DAY_OF_YEAR);
            // 超过实际收货时间3天则不可退货
            if (dayNow - actualReceiveDate > NumberConstant.IntDef.INT_THREE) {
                iso151803RestOrderShipResult.setCanReturn(NumberConstant.IntDef.INT_TWO);
            } else {
                iso151803RestOrderShipResult.setCanReturn(NumberConstant.IntDef.INT_ONE);
            }
        }
        for (SoOrderShipDetail soOrderShipDetail : soOrderShip.getSoOrderShipDetailList()) {
            // 设置shipDetail级别返回值
            ISO151803RestProductResult iso151803RestProductResult = this.setShipDetailResult(soOrderShipDetail);
            iso151803RestOrderShipResult.getProductList().add(iso151803RestProductResult);
        }
        return iso151803RestOrderShipResult;
    }

    /**
     * 设置shipDetail级别返回值
     *
     * @param soOrderShipDetail
     */
    private ISO151803RestProductResult setShipDetailResult(SoOrderShipDetail soOrderShipDetail) throws Exception {
        ISO151803RestProductResult iso151803RestProductResult = new ISO151803RestProductResult();
        BeanUtils.copyProperties(iso151803RestProductResult, soOrderShipDetail);
        iso151803RestProductResult.setPdPrice(soOrderShipDetail.getSoSubOrderDetail().getPdPrice());
        if (soOrderShipDetail.getSuppQty() != null) {
            iso151803RestProductResult.setSuppQty(soOrderShipDetail.getSuppQty().intValue());
        }
        if (soOrderShipDetail.getSendQty() != null) {
            iso151803RestProductResult.setSendQty(soOrderShipDetail.getSendQty().intValue());
        }
        if (soOrderShipDetail.getCancelQty() != null) {
            iso151803RestProductResult.setCancelQty(soOrderShipDetail.getCancelQty().intValue());
        }
        if (soOrderShipDetail.getReceiveQty() != null) {
            iso151803RestProductResult.setReceiverQty(soOrderShipDetail.getReceiveQty().intValue());
        }
        if (soOrderShipDetail.getReturnQty() != null) {
            iso151803RestProductResult.setReturnQty(soOrderShipDetail.getReturnQty().intValue());
        }
        return iso151803RestProductResult;
    }

    /**
     * 获取查询条件
     *
     * @param param
     */
    private Filter<SoOrder> getFilter(ISO151803RestParam param) {
        Filter<SoOrder> filter = new Filter<>();
        filter.add("orderId", BaseOperatorEnum.EQUAL, param.getOrderId());
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        filter.add("buyerId", BaseOperatorEnum.EQUAL, param.getBuyerId());
        if (!StringUtil.isNullOrEmpty(param.getOrderCode())) {
            filter.add("orderCode", BaseOperatorEnum.EQUAL, param.getOrderCode());
        }
        if (!StringUtil.isNullOrEmpty(param.getBuyerTel())) {
            filter.add("soOrderReceiveDemand.receiverTel", BaseOperatorEnum.EQUAL, param.getBuyerTel());
            filter.add("soOrderReceiveDemand.delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        }
        if (param.getShipId() != null && param.getShipId() != NumberConstant.IntDef.INT_ZERO) {
            filter.add("soOrderShipList.shipId", BaseOperatorEnum.EQUAL, param.getShipId());
            filter.add("soOrderShipList.delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        }
        if (!StringUtil.isNullOrEmpty(param.getShipCode())) {
            filter.add("soOrderShipList.shipCode", BaseOperatorEnum.EQUAL, param.getShipCode());
            filter.add("soOrderShipList.delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        }
        return filter;
    }
}
