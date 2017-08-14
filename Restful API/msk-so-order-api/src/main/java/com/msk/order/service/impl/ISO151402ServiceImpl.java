package com.msk.order.service.impl;

import com.msk.common.bean.RestRequest;
import com.msk.common.constant.SystemConstant;
import com.msk.common.constant.business.OrderCodeMasterDef;
import com.msk.common.data.jpa.condition.CommonSpecification;
import com.msk.common.exception.BusinessException;
import com.msk.common.constant.StringConstant;
import com.msk.common.data.jpa.BaseRepository;
import com.msk.common.utils.DateTimeUtil;
import com.msk.common.utils.DecimalUtil;
import com.msk.common.utils.StringUtil;
import com.msk.order.bean.param.ISO151402RestParam;
import com.msk.order.bean.result.*;
import com.msk.order.entity.*;
import com.msk.order.repository.SoOrderDetailRepository;
import com.msk.order.repository.SoOrderReceiveDemandRepository;
import com.msk.order.repository.SoOrderRepository;
import com.msk.order.repository.SoOrderShipDetailRepository;
import com.msk.order.service.BaseService;
import com.msk.order.service.ISO151402Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.msk.common.data.jpa.condition.BaseOperatorEnum;
import com.msk.common.data.jpa.condition.Filter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * ISO151402_打印查询订单详细信息PDF接口
 * Created by chu_jian on 2016/8/3.
 */

@Service
public class ISO151402ServiceImpl extends BaseService<SoOrder, Long> implements ISO151402Service {

    private static Logger logger = LoggerFactory.getLogger(ISO151402ServiceImpl.class);

    @Autowired
    private SoOrderRepository soOrderRepository;

    @Override
    public BaseRepository getRepository() {
        return this.soOrderRepository;
    }


    /**
     * 打印pdf信息
     *
     * @param param
     * @return
     */
    @Override
    public ISO151402RestResult selectSoProDetail(RestRequest<ISO151402RestParam> param) {
        logger.debug("打印查询订单详细信息PDF接口");
        ISO151402RestResult result = new ISO151402RestResult();
        ISO151402RestParam iso251402RsParam = param.getParam();
        Long orderId = iso251402RsParam.getOrderId();
        Long subOrderId = iso251402RsParam.getSubOrderId();
        Filter<SoOrder> filter = new Filter<>();
        filter.add("orderId", BaseOperatorEnum.EQUAL, orderId);
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        SoOrder soOrder = this.findOne(filter);
        if (soOrder == null) {
            throw new BusinessException("找不到对应的订单！");
        }
        result = this.getOrderRsResult(result, soOrder, subOrderId);
        SoOrderReceiveDemand oderReceiveDemand = soOrder.getSoOrderReceiveDemand();
        ISO151402RestReceiveInfoResult orderReceiveInfo = this.getReceiveInfo(oderReceiveDemand);  //收货信息
        ISO151402RestDeliveryReqResult orderDeliveryInfo = this.getDeliveryReqResult(oderReceiveDemand);//配送信息
        List<ISO151402RestDetailInfoResult> deaList = this.getDetailInfoList(soOrder, subOrderId);
        for (ISO151402RestDetailInfoResult dea : deaList) {
            List<SoOrderShipDetail> soOrderShipDetail = soOrder.getSoOrderShipDetailList();
            List<ISO151402RestAvailabilityInfoResult> avaList = this.getAvailabilityList(soOrderShipDetail);
            dea.setAvailabilityInfo(avaList);
        }
        result.setReceiveInfo(orderReceiveInfo);
        result.setDeliveryReq(orderDeliveryInfo);
        result.setDetailInfo(deaList);
        return result;
    }


    /**
     * 设置order 属性
     * 1订单状态  ：分批订单状态，
     * 2订单编码  分批订单
     * 3订单类型  分批订单
     * 4订单总金额  分批订单
     *
     * @param result
     * @param soOrder
     * @return
     */
    private ISO151402RestResult getOrderRsResult(ISO151402RestResult result, SoOrder soOrder, Long subOrderId) {

        BeanUtils.copyProperties(soOrder, result);
        SoSubOrder subOrder = this.getSoSubOrderByOrderId(soOrder, subOrderId);
        if (subOrder != null) {
            result.setOrderCode(subOrder.getSubOrderCode());
            result.setOrderStatus(StringUtil.formatNum(subOrder.getSubOrderStatus()));
            result.setOrderType(StringUtil.formatNum(subOrder.getOrderType()));
        }
        result.setOrderSource(Integer.toString(soOrder.getOrderSource()));// 订单来源
        result.setReturnFlg(soOrder.getReturnFlg());// 是否退货
        result.setSplitDeliveryFlg(soOrder.getSplitDeliveryFlg());// 是否分批发货
        result.setNeedInvoice(soOrder.getNeedInvoice());//是否开发票
        result.setDistrictCode(soOrder.getDistrictName());//districtCode
        result.setOrderSendTime(DateTimeUtil.formatDate("yyyy-MM-dd HH:mm:ss", soOrder.getOrderSendTime()));
        result.setOrderReceiveTime(DateTimeUtil.formatDate("yyyy-MM-dd HH:mm:ss", soOrder.getOrderReceiveTime()));
        result.setSellers(soOrder.getSaName()); //冻品管家sellers
        result.setOrderTime(DateTimeUtil.formatDate("yyyy-MM-dd HH:mm:ss", soOrder.getOrderTime()));  //订单创建时间orderTime
        result.setBuyersId(soOrder.getBuyerId());
        result.setBuyersName(soOrder.getBuyerName());
        result.setBuyersCode(soOrder.getBuyerCode());
        result.setBuyersType(StringUtil.formatNum(soOrder.getBuyerType()));
        result.setPaymentType(StringUtil.formatNum(soOrder.getPaymentType()));
        result.setCancelType(StringUtil.formatNum(soOrder.getCancelType()));
        return result;
    }

    /**
     * 获取订单需求订单信息
     *
     * @param oderReceiveDemand
     * @return
     */
    private ISO151402RestReceiveInfoResult getReceiveInfo(SoOrderReceiveDemand oderReceiveDemand) {
        ISO151402RestReceiveInfoResult orderReceiveInfo = new ISO151402RestReceiveInfoResult();
        BeanUtils.copyProperties(oderReceiveDemand, orderReceiveInfo);
        orderReceiveInfo.setReceiverQq(oderReceiveDemand.getReceiverQq());
        return orderReceiveInfo;
    }

    /**
     * 设置订单配送信息
     *
     * @param oderReceiveDemand
     * @return
     */
    private ISO151402RestDeliveryReqResult getDeliveryReqResult(SoOrderReceiveDemand oderReceiveDemand) {
        ISO151402RestDeliveryReqResult orderDeliveryInfo = new ISO151402RestDeliveryReqResult();
        BeanUtils.copyProperties(oderReceiveDemand, orderDeliveryInfo);
        orderDeliveryInfo.setForecastSendTime(DateTimeUtil.formatDate("yyyy-MM-dd HH:mm:ss", oderReceiveDemand.getForecastSendTime()));
        orderDeliveryInfo.setForecastReceiveTime(DateTimeUtil.formatDate("yyyy-MM-dd HH:mm:ss", oderReceiveDemand.getForecastReceiveTime()));
        if (oderReceiveDemand.getParkingDistance() != null) {
            orderDeliveryInfo.setParkingDistance(Integer.toString(oderReceiveDemand.getParkingDistance()));
        }
        return orderDeliveryInfo;
    }


    /**
     * 设置分批订单明细信息
     *
     * @param soOrder
     * @param subOrderId
     * @return
     */
    private List<ISO151402RestDetailInfoResult> getDetailInfoList(SoOrder soOrder, Long subOrderId) {
        List<ISO151402RestDetailInfoResult> dataList = new ArrayList<>();
        List<SoSubOrderDetail> subOrderDetailList = soOrder.getSoSubOrderDetailList();
        for (SoSubOrderDetail subOrderDetail : subOrderDetailList) {
            if (subOrderDetail.getSubOrderId().longValue() == subOrderId.longValue()) {
                ISO151402RestDetailInfoResult detailInfo = new ISO151402RestDetailInfoResult();
                SoOrderDetail orderDetail = this.getOrderDetailByOrderDetailId(soOrder, subOrderDetail.getOrderDetailId());
                BeanUtils.copyProperties(subOrderDetail, detailInfo);
                if (orderDetail != null) {
                    detailInfo.setFeatureName(orderDetail.getFeatureName()); //特征 featureName
                }
                SoOrderShipDetail shipDetail = this.getOrderShipDetailBySubOrderDetailId(soOrder, subOrderDetail.getSubOrderDetailId());
                if (shipDetail != null) {
                    detailInfo.setSupplierCode(shipDetail.getSupplierCode()); // 供应商编号supplierCode
                    detailInfo.setSupplierName(shipDetail.getSupplierName()); //供应商名称 supplierName
                }
                BigDecimal pdPrice = subOrderDetail.getPdPrice();
                if (pdPrice != null) {
                    detailInfo.setPdPrice(pdPrice.toString()); // 单价 pdPrice
                }
                BigDecimal suppQty = subOrderDetail.getOrderQty();
                if (suppQty != null) {
                    detailInfo.setSuppQty(suppQty.toString()); // 订购数量 suppQty
                }
                BigDecimal sendQty = subOrderDetail.getSendQty();
                if (sendQty != null) {
                    detailInfo.setSendQty(sendQty.toString()); // 发货数量 sendQty
                }
                BigDecimal receiveQty = subOrderDetail.getReceiveQty();
                if (receiveQty != null) {
                    detailInfo.setReceiveQty(receiveQty.toString());// 收货数量 receiveQty
                }

                BigDecimal cancelQty = subOrderDetail.getCancelQty();
                if (cancelQty != null) {
                    detailInfo.setCancelQty(cancelQty.toString());  //取消数量 cancelQty
                }
                BigDecimal returnQty = subOrderDetail.getReturnQty();
                if (returnQty != null) {
                    detailInfo.setReturnQty(returnQty.toString());      // 退货数量 returnQty
                }
                BigDecimal rejectionQty = subOrderDetail.getRejectionQty();
                if (rejectionQty != null) {
                    detailInfo.setRejectionQty(rejectionQty.toString());          // 拒收数量 rejectionQty
                }
              /*  BigDecimal buyerQty = DecimalUtil.subtract(suppQty, cancelQty);
                buyerQty = DecimalUtil.subtract(buyerQty, returnQty);
                buyerQty = DecimalUtil.subtract(buyerQty, rejectionQty);//suppQty-cancelQty-returnQty-rejectionQty*/
                BigDecimal amount = DecimalUtil.multiply(pdPrice, suppQty);
                detailInfo.setAmount(Double.parseDouble(amount.toString()));
                dataList.add(detailInfo);
            }
        }
        return dataList;
    }


    /**
     * 设置订单供货明细信息
     *
     * @param soOrderShipDetailList
     * @return
     */
    private List<ISO151402RestAvailabilityInfoResult> getAvailabilityList(List<SoOrderShipDetail> soOrderShipDetailList) {
        List<ISO151402RestAvailabilityInfoResult> avaList = new ArrayList<ISO151402RestAvailabilityInfoResult>();
        for (SoOrderShipDetail soOrderShipDetail : soOrderShipDetailList) {
            ISO151402RestAvailabilityInfoResult availabilityInfoResult = new ISO151402RestAvailabilityInfoResult();
            BeanUtils.copyProperties(soOrderShipDetail, availabilityInfoResult);
            availabilityInfoResult.setOrderDetailAvailabilityId(soOrderShipDetail.getShipDetailId() + StringConstant.EMPTY);
            availabilityInfoResult.setStatus(soOrderShipDetail.getDetailStatus() + StringConstant.EMPTY);
            avaList.add(availabilityInfoResult);
        }
        return avaList;
    }

    /**
     * @param order
     * @param orderDetailId
     */
    public SoOrderDetail getOrderDetailByOrderDetailId(SoOrder order, Long orderDetailId) {
        SoOrderDetail result = null;
        List<SoOrderDetail> soOrderDetailList = order.getSoOrderDetailList();
        for (SoOrderDetail orderDetail : soOrderDetailList) {
            if (orderDetail.getOrderDetailId().longValue() == orderDetailId.longValue()) {
                result = orderDetail;
                break;
            }
        }
        return result;
    }


    /**
     * @param order
     * @param subOrderDetailId
     * @return
     */
    public SoOrderShipDetail getOrderShipDetailBySubOrderDetailId(SoOrder order, Long subOrderDetailId) {
        SoOrderShipDetail result = null;
        List<SoOrderShipDetail> soOrderShipDetailList = order.getSoOrderShipDetailList();
        for (SoOrderShipDetail shipDetail1 : soOrderShipDetailList) {
            if (shipDetail1.getSubOrderDetailId().longValue() == subOrderDetailId.longValue()) {
                result = shipDetail1;
            }
        }
        return result;
    }


    /**
     * 根据subOrderId  获取subOrder
     *
     * @param soOrder
     * @param subOrderId
     * @return
     */
    private SoSubOrder getSoSubOrderByOrderId(SoOrder soOrder, Long subOrderId) {
        SoSubOrder subOrder = null;
        List<SoSubOrder> subOrderList = soOrder.getSoSubOrders();
        for (SoSubOrder soSubOrder : subOrderList) {
            if (soSubOrder.getSubOrderId().longValue() == subOrderId.longValue()) {
                subOrder = soSubOrder;
                break;
            }
        }
        return subOrder;
    }


}
