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
import com.msk.common.utils.DecimalUtil;
import com.msk.common.utils.StringUtil;
import com.msk.order.bean.param.ISO151414ByBuyerInfoParam;
import com.msk.order.bean.param.ISO151416OrderSearchParam;
import com.msk.order.bean.param.ISO151416SoldProductParam;
import com.msk.order.bean.result.*;
import com.msk.order.entity.*;
import com.msk.order.repository.SoDeliverRepository;
import com.msk.order.repository.SoOrderDetailRepository;
import com.msk.order.repository.SoOrderReceiveDemandRepository;
import com.msk.order.repository.SoSubOrderDetailRepository;
import com.msk.order.service.BaseService;
import com.msk.order.service.ISO151416Service;
import com.msk.order.util.SoRestUtil;
import com.msk.order.util.SqlUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by liu_tao2 on 2016/8/17.
 */
@Service
public class ISO151416ServiceImpl extends BaseService implements ISO151416Service {

    private static Logger logger = LoggerFactory.getLogger(ISO151416ServiceImpl.class);

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private SoOrderReceiveDemandRepository soOrderReceiveDemandRepository;

    @Autowired
    private SoOrderDetailRepository soOrderDetailRepository;

    @Autowired
    private BaseJdbcImpl baseJdbc;

    @Autowired
    private SoSubOrderDetailRepository soSubOrderDetailRepository;

    @Autowired
    private SoDeliverRepository soDeliverRepository;

    @Override
    public BaseRepository getRepository() {
        return soSubOrderDetailRepository;
    }

    /**
     * 订单列表查询接口
     * 买家订单列表查询接口
     * 买手囤货订单列表查询接口
     *
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public ISO151416OrderSearchResult findOrderDetailList(ISO151416OrderSearchParam param) throws Exception {
        ISO151416OrderSearchResult result = new ISO151416OrderSearchResult();
        param.setStartPos(this.getStartPos(param.getPageNo(), param.getPageCount()));
        Integer totalCount = this.getTotalCount(param);
        List<ISO151414OrderSearchResult> orders = this.getOrders(param);
        if (!CollectionUtils.isEmpty(orders)) {
            getResultByOrderId(orders);
        }
        result.setPageNo(param.getPageNo());
        result.setTotalCount(totalCount);
        result.setTotalPage(totalCount, param.getPageCount());
        result.setOrders(orders);
        return result;
    }

    /**
     * 订单明细查询接口
     * 买家订单明细查询接口
     * 买手囤货订单明细查询接口
     *
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public ISO151416OrderSearchResult findOrderDetail(ISO151416OrderSearchParam param) throws Exception {
        ISO151416OrderSearchResult result = new ISO151416OrderSearchResult();
        List<ISO151414OrderSearchResult> orders = this.getOrders(param);
        if (!CollectionUtils.isEmpty(orders)) {
            getResultByOrderId(orders);
        }
        result.setOrders(orders);
        return result;
    }

    /**
     * 买手销售订单列表查询接口
     * 卖家订单列表查询接口
     *
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public ISO151416OrderSearchResult findOrderSaleDetailList(ISO151416OrderSearchParam param) throws Exception {
        ISO151416OrderSearchResult result = new ISO151416OrderSearchResult();
        param.setStartPos(this.getStartPos(param.getPageNo(), param.getPageCount()));
        Integer totalCount = this.getSaleTotalCount(param);
        List<ISO151414OrderSearchResult> orders = this.getSaleOrders(param);
        if (!CollectionUtils.isEmpty(orders)) {
            getResultBySubOrderId(orders);
        }
        result.setPageNo(param.getPageNo());
        result.setTotalCount(totalCount);
        result.setTotalPage(totalCount, param.getPageCount());
        result.setOrders(orders);
        return result;
    }

    /**
     * 买手销售订单明细查询接口
     * 卖家订单明细接口
     *
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public ISO151416OrderSearchResult findOrderSaleDetail(ISO151416OrderSearchParam param) throws Exception {
        ISO151416OrderSearchResult result = new ISO151416OrderSearchResult();
        List<ISO151414OrderSearchResult> orders = this.getSaleOrders(param);
        if (!CollectionUtils.isEmpty(orders)) {
            getResultBySubOrderId(orders);
        }
        result.setOrders(orders);
        return result;
    }

    /**
     * 通过买家Code找到对应的买家信息
     *
     * @param param
     */
    @Override
    public void getBuyerIdByBuyerCode(ISO151416OrderSearchParam param) {
        if (!StringUtil.isEmpty(param.getBuyersId())) {
            param.setBuyersCode(null);
            return;
        }

        if (!StringUtil.isEmpty(param.getBuyersCode())) {
            ISO151414ByBuyerInfoParam buyerInfoParam = new ISO151414ByBuyerInfoParam();
            buyerInfoParam.setBuyerCode(param.getBuyersCode());
            String buyerId = SoRestUtil.getBuyerIdByBuyerCode(buyerInfoParam);

            if (StringUtil.isEmpty(buyerId)) {
                throw new BusinessException("通过买家编码查询不到买家Id");
            }
            param.setBuyersId(buyerId);
            param.setBuyersCode(null);
        }
    }

    /**
     * 卖家已卖出商品查询接口
     *
     * @param param
     * @return
     */
    @Override
    public ISO151416SoldProductResult searchSoldProductList(ISO151416SoldProductParam param) throws Exception{
        ISO151416SoldProductResult result = new ISO151416SoldProductResult();
        if (param.getPageCount() > NumberConstant.IntDef.INT_ZERO
                && param.getPageNo() > NumberConstant.IntDef.INT_ZERO){
            Integer totalCount = this.getSoldProductTotalCount(param);
            param.setStartPos(this.getStartPos(param.getPageNo(), param.getPageCount()));
            result.setPageNo(param.getPageNo());
            result.setTotalCount(totalCount);
            result.setTotalPage(totalCount, param.getPageCount());
        }

        List<ISO151416SellerProductResult> sellerProductResultList = getSoldProductOrders(param);
        if(CollectionUtils.isEmpty(sellerProductResultList)){
            return null;
        }
        for (ISO151416SellerProductResult sellerProductResult : sellerProductResultList){
            Filter<SoOrderDetail> detailFilter = new Filter();
            detailFilter.add("orderId", BaseOperatorEnum.EQUAL, sellerProductResult.getOrderId());
            detailFilter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
            CommonSpecification comSpec = new CommonSpecification(detailFilter);
            List<SoOrderDetail> soOrderDetailList = soOrderDetailRepository.findAll(comSpec);
            List<ISO151416ProductResult> productResultList = getProductResult(soOrderDetailList,sellerProductResult);
            sellerProductResult.setProductResultList(productResultList);
        }
        result.setSellerProductResult(sellerProductResultList);
        return result;
    }

    public List<ISO151416ProductResult> getProductResult(List<SoOrderDetail> soOrderDetailList,ISO151416SellerProductResult sellerProductResult) throws Exception{
        List<ISO151416ProductResult> productResultList = new ArrayList<>();

        if(CollectionUtils.isEmpty(soOrderDetailList)){
            return null;
        }

        for (SoOrderDetail soOrderDetail : soOrderDetailList){
            ISO151416ProductResult productResult = new ISO151416ProductResult();
            BeanUtils.copyProperties(productResult,soOrderDetail);
            productResult.setSuppQty(soOrderDetail.getOrderQty());
            if(sellerProductResult.getPaymentType().equals(OrderCodeMasterDef.PaymentType.PAYING_ONLINE)){
                //线上处理
                productResult.setActualPay(getActualPayOnline(soOrderDetail, sellerProductResult));
            }else {
                //线下处理
                productResult.setActualPay(getActualPayCashOnDelivery(soOrderDetail, sellerProductResult));
            }
            productResultList.add(productResult);
        }

        return productResultList;
    }

    /**
     * 处理线上付款的实付款金额
     * @param soOrderDetail
     * @param sellerProductResult
     * @return
     */
    public BigDecimal getActualPayOnline(SoOrderDetail soOrderDetail,ISO151416SellerProductResult sellerProductResult){
        BigDecimal actualPay = BigDecimal.ZERO;
        if(sellerProductResult.getOrderStatus() <= OrderCodeMasterDef.OrderStatus.OBLIGATION){
            return BigDecimal.ZERO;
        }else {
            actualPay = DecimalUtil.subtract(DecimalUtil.subtract(DecimalUtil.subtract(soOrderDetail.getOrderQty(), soOrderDetail.getCancelQty()),soOrderDetail.getRejectionQty()),soOrderDetail.getReturnQty());
            if(actualPay.compareTo(BigDecimal.ZERO) < NumberConstant.IntDef.INT_ZERO){
                throw new BusinessException("订单明细的实付款数量小于0");
            }
        }
        return DecimalUtil.multiply(actualPay,soOrderDetail.getPdPrice());
    }

    /**
     * 处理线下付款的实付款金额
     * @param soOrderDetail
     * @param sellerProductResult
     * @return
     */
    public BigDecimal getActualPayCashOnDelivery(SoOrderDetail soOrderDetail,ISO151416SellerProductResult sellerProductResult){
        if(soOrderDetail.getReceiveQty().compareTo(BigDecimal.ZERO) == NumberConstant.IntDef.INT_ZERO){
            return BigDecimal.ZERO;
        }

        BigDecimal actualPay = BigDecimal.ZERO;
        actualPay = DecimalUtil.subtract(soOrderDetail.getReceiveQty(),soOrderDetail.getReturnQty());
        if(actualPay.compareTo(BigDecimal.ZERO) < NumberConstant.IntDef.INT_ZERO){
            throw new BusinessException("订单明细的实付款数量小于0");
        }

        return DecimalUtil.multiply(actualPay,soOrderDetail.getPdPrice());
    }

    /**
     * 根据查询出的订单orderId
     * 得到订单明细信息
     * 得到收货要求信息
     * 得到配送要求信息
     * 遍历结果信息
     *
     * @param orders
     * @throws Exception
     */
    public void getResultByOrderId(List<ISO151414OrderSearchResult> orders) throws Exception {
        for (ISO151414OrderSearchResult iso151414OrderSearchResult : orders) {
            if (!StringUtil.isEmpty(iso151414OrderSearchResult.getOrderTimeStr())) {
                Date orderTime = DateTimeUtil.parseDate(iso151414OrderSearchResult.getOrderTimeStr(), "yyyy-MM-dd HH:mm:ss");
                iso151414OrderSearchResult.setOrderTime(orderTime);
            }

            //通过id得到收货表中信息
            SoOrderReceiveDemand soOrderReceiveDemand = getReceiveDemandInfoByOrderId(iso151414OrderSearchResult.getOrderId());

            ISO151416DeliverInfo deliverInfo = getDeliverInfoByOrderId(iso151414OrderSearchResult.getOrderId());
            iso151414OrderSearchResult.setDeliver(deliverInfo);
            if (null != soOrderReceiveDemand) {
                ISO151414ReceiverInfo receiverInfo = new ISO151414ReceiverInfo();
                ISO151414DeliveryReq deliveryReq = new ISO151414DeliveryReq();
                BeanUtils.copyProperties(receiverInfo, soOrderReceiveDemand);
                receiverInfo.setReceiverQQ(soOrderReceiveDemand.getReceiverQq());
                BeanUtils.copyProperties(deliveryReq, soOrderReceiveDemand);
                iso151414OrderSearchResult.setDeliveryReq(deliveryReq);
                iso151414OrderSearchResult.setReceiveInfo(receiverInfo);
            }
            //通过id得到明细信息
            Filter<SoOrderDetail> detailFilter = new Filter();
            detailFilter.add("orderId", BaseOperatorEnum.EQUAL, iso151414OrderSearchResult.getOrderId());
            detailFilter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
            CommonSpecification comSpec = new CommonSpecification(detailFilter);

            List<SoOrderDetail> soOrderDetails = soOrderDetailRepository.findAll(comSpec);
            iso151414OrderSearchResult.setOrderDetail(soOrderDetails);
        }
    }

    /**
     * 根据查询出的拆分订单ID
     * 得到订单明细信息
     * 得到收货要求信息
     * 得到配送要求信息
     * 遍历结果信息
     *
     * @param orders
     * @throws Exception
     */
    public void getResultBySubOrderId(List<ISO151414OrderSearchResult> orders) throws Exception {
        for (ISO151414OrderSearchResult iso151414OrderSearchResult : orders) {
            if (!StringUtil.isEmpty(iso151414OrderSearchResult.getOrderTimeStr())) {
                Date orderTime = DateTimeUtil.parseDate(iso151414OrderSearchResult.getOrderTimeStr(), "yyyy-MM-dd HH:mm:ss");
                iso151414OrderSearchResult.setOrderTime(orderTime);
            }
            //通过id得到收货表中信息
            SoOrderReceiveDemand soOrderReceiveDemand = getReceiveDemandInfoByOrderId(iso151414OrderSearchResult.getOrderId());

            if (null != soOrderReceiveDemand) {
                ISO151414ReceiverInfo receiverInfo = new ISO151414ReceiverInfo();
                ISO151414DeliveryReq deliveryReq = new ISO151414DeliveryReq();
                BeanUtils.copyProperties(receiverInfo, soOrderReceiveDemand);
                receiverInfo.setReceiverQQ(soOrderReceiveDemand.getReceiverQq());
                BeanUtils.copyProperties(deliveryReq, soOrderReceiveDemand);
                iso151414OrderSearchResult.setDeliveryReq(deliveryReq);
                iso151414OrderSearchResult.setReceiveInfo(receiverInfo);
            }
            //通过id得到明细信息
            Filter<SoSubOrderDetail> detailFilter = new Filter();
            detailFilter.add("subOrderId", BaseOperatorEnum.EQUAL, iso151414OrderSearchResult.getSubOrderId());
            detailFilter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
            CommonSpecification comSpec = new CommonSpecification(detailFilter);
            List<SoSubOrderDetail> soSubOrderDetails = soSubOrderDetailRepository.findAll(comSpec);

            List<SoOrderDetail> soOrderDetails = new ArrayList<>();

            if (!CollectionUtils.isEmpty(soSubOrderDetails)) {
                for (SoSubOrderDetail soSubOrderDetail : soSubOrderDetails) {
                    SoOrderDetail soOrderDetail = new SoOrderDetail();
                    BeanUtils.copyProperties(soOrderDetail, soSubOrderDetail);
                    soOrderDetails.add(soOrderDetail);
                }
            }

            iso151414OrderSearchResult.setOrderDetail(soOrderDetails);
        }
    }

    /**
     * 得到收获人信息
     *
     * @param orderId
     * @return
     */
    public SoOrderReceiveDemand getReceiveDemandInfoByOrderId(Long orderId) {
        Filter<SoOrderReceiveDemand> filter = new Filter();
        filter.add("orderId", BaseOperatorEnum.EQUAL, orderId);
        filter.add("delFlg", BaseOperatorEnum.NOTEQUAL, SystemConstant.DelFlg.DISABLE);
        CommonSpecification spec = new CommonSpecification(filter);
        SoOrderReceiveDemand soOrderReceiveDemand = soOrderReceiveDemandRepository.findOne(spec);
        return soOrderReceiveDemand;
    }

    /**
     * 得到配送人信息
     *
     * @param orderId
     * @return
     */
    public ISO151416DeliverInfo getDeliverInfoByOrderId(Long orderId) {
        Filter<SoDeliver> filter = new Filter();
        filter.add("orderId", BaseOperatorEnum.EQUAL, orderId);
        CommonSpecification spec = new CommonSpecification(filter);
        List<SoDeliver> soDeliverList = soDeliverRepository.findAll(spec);
        if (CollectionUtils.isEmpty(soDeliverList)) {
            return null;
        }

        ISO151416DeliverInfo deliverInfo = new ISO151416DeliverInfo();
        SoDeliver soDeliver = soDeliverList.get(NumberConstant.IntDef.INT_ZERO);
        deliverInfo.setOperationDate(soDeliver.getCrtTime());
        String operationDescribe = "";
        if (!StringUtil.isEmpty(soDeliver.getDeliverPerson())) {
            operationDescribe = operationDescribe + "订单已经被配送人员" + soDeliver.getDeliverPerson() + "配送;";
        }
        if (!StringUtil.isEmpty(soDeliver.getPersonMobile())) {
            if(!StringUtil.isEmpty(operationDescribe)){
                operationDescribe = operationDescribe + ",";
            }
            operationDescribe = operationDescribe + "联系电话" + soDeliver.getPersonMobile();
        }
        if (null != soDeliver.getExpectReceiveDate()) {
            if(!StringUtil.isEmpty(operationDescribe)){
                operationDescribe = operationDescribe + ",";
            }
            operationDescribe = operationDescribe + "预计到货时间" + soDeliver.getExpectReceiveDate();
        }
        if (!StringUtil.isEmpty(operationDescribe)) {
            deliverInfo.setOperationDescribe(operationDescribe);
        }
        return deliverInfo;
    }

    /**
     * 得到开始行数
     *
     * @param pageNo
     * @param pageCount
     * @return
     */
    public Integer getStartPos(Integer pageNo, Integer pageCount) {
        if (pageNo == NumberConstant.IntDef.INT_ZERO) {
            throw new BusinessException("输入的分页参数有误，请检验！！！！");
        }
        return pageCount * (pageNo - NumberConstant.IntDef.INT_ONE);
    }

    /**
     * 得到返回的订单信息
     *
     * @param param
     * @return
     */
    public List<ISO151414OrderSearchResult> getOrders(ISO151416OrderSearchParam param) throws IOException {
        String sql = SqlUtil.getSqlBySqlId("ISO151416.getOrderList");
        sql = getWhereSql(sql, param);
        if (param.getPageCount() != NumberConstant.IntDef.INT_ZERO) {
            sql = sql + " LIMIT " + param.getStartPos() + "," + param.getPageCount();
        }
        List<ISO151414OrderSearchResult> result = getResult(sql);
        return result;
    }

    /**
     * 得到订单主表信息
     *
     * @param param
     * @return
     */
    public List<ISO151414OrderSearchResult> getSaleOrders(ISO151416OrderSearchParam param) throws IOException {
        String sql = SqlUtil.getSqlBySqlId("ISO151416.getOrderSale");
        sql = getSaleWhereSql(sql, param);
        if (param.getPageCount() != NumberConstant.IntDef.INT_ZERO) {
            sql = sql + " LIMIT " + param.getStartPos() + "," + param.getPageCount();
        }
        List<ISO151414OrderSearchResult> result = getResult(sql);
        return result;
    }


    public List<ISO151416SellerProductResult> getSoldProductOrders(ISO151416SoldProductParam param){
        String sql = SqlUtil.getSqlBySqlId("ISO151416.getSoldProductOrderList");
        sql = getSoldProductWhereSql(sql, param);
        if (param.getPageCount() != NumberConstant.IntDef.INT_ZERO) {
            sql = sql + " LIMIT " + param.getStartPos() + "," + param.getPageCount();
        }
        List<ISO151416SellerProductResult> result = getSoldProductResult(sql);
        return result;
    }

    /**
     *
     * @param param
     * @return
     */
    public Integer getSoldProductTotalCount(ISO151416SoldProductParam param){
        String sql = SqlUtil.getSqlBySqlId("ISO151416.getSoldProductTotalCount");
        sql = getSoldProductWhereSql(sql, param);
        Long count = baseJdbc.count(sql, null, true);
        return Integer.valueOf(count.toString());
    }

    /**
     * 得到分页数据的总数
     *
     * @param param
     * @return
     */
    public Integer getTotalCount(ISO151416OrderSearchParam param) throws IOException {
        String sql = SqlUtil.getSqlBySqlId("ISO151416.getOrderCount");
        sql = getWhereSql(sql, param);
        Long count = baseJdbc.count(sql, null, true);
        return Integer.valueOf(count.toString());
    }

    /**
     * 得到分页数据的总数
     *
     * @param param
     * @return
     */
    public Integer getSaleTotalCount(ISO151416OrderSearchParam param) throws IOException {
        String sql = SqlUtil.getSqlBySqlId("ISO151416.getOrderSaleCount");
        sql = getSaleWhereSql(sql, param);
        Long count = baseJdbc.count(sql, null, true);
        return Integer.valueOf(count.toString());
    }

    /**
     * 查询条件的判断
     *
     * @param getOrdersSql
     * @param param
     * @return
     */
    public String getWhereSql(String getOrdersSql, ISO151416OrderSearchParam param) {
        if (!StringUtil.isEmpty(param.getBuyersId())) {
            getOrdersSql = getOrdersSql + " AND so.BUYER_ID = '" + param.getBuyersId() + "'";
        }

        if (!StringUtil.isEmpty(param.getBuyersCode())) {
            getOrdersSql = getOrdersSql + " AND so.BUYER_CODE = '" + param.getBuyersCode() + "'";
        }

        if (!StringUtil.isEmpty(param.getSellerCode())) {
            getOrdersSql = getOrdersSql + " AND soo.SELLER_CODE = '" + param.getSellerCode() + "'";
        }

        if (!StringUtil.isEmpty(param.getOrderType())) {
            getOrdersSql = getOrdersSql + " AND so.ORDER_TYPE IN (" + param.getOrderType() + ")";
        }

        //TODO
        if (!StringUtil.isEmpty(param.getOrderStatus())) {
            getOrdersSql = getOrdersSql + " AND so.ORDER_STATUS IN (" + param.getOrderStatus() + ")";
        }

        if (null != param.getOrderTimeFrom()) {
            getOrdersSql = getOrdersSql + " AND so.ORDER_TIME >= '" + DateTimeUtil.formatDate("yyyy-MM-dd HH:mm:ss", param.getOrderTimeFrom()) + "'";
        }

        if (null != param.getOrderTimeTo()) {
            getOrdersSql = getOrdersSql + " AND so.ORDER_TIME <= '" + DateTimeUtil.formatDate("yyyy-MM-dd HH:mm:ss",param.getOrderTimeTo()) + "'";
        }

        if (null != param.getDelFlg()) {
            getOrdersSql = getOrdersSql + " AND so.DEL_FLG = " + param.getDelFlg();
        }

        if (!StringUtil.isEmpty(param.getOrderSource())) {
            getOrdersSql = getOrdersSql + " AND so.ORDER_SOURCE = " + param.getOrderSource();
        }

        if (!StringUtil.isEmpty(param.getDistrictCode())) {
            getOrdersSql = getOrdersSql + " AND so.DISTRICT_CODE = " + param.getDistrictCode();
        }

        if (null != param.getPaymentType()) {
            getOrdersSql = getOrdersSql + " AND so.PAYMENT_TYPE = " + param.getPaymentType();
        }

        if (null != param.getOrderAmountMin()) {
            getOrdersSql = getOrdersSql + " AND so.ORDER_AMOUNT >= " + param.getOrderAmountMin();
        }

        if (null != param.getOrderAmountMax()) {
            getOrdersSql = getOrdersSql + " AND so.ORDER_AMOUNT <= " + param.getOrderAmountMax();
        }

        if (!StringUtil.isEmpty(param.getOrderLevel())) {
            getOrdersSql = getOrdersSql + " AND sod.ORDER_DETAIL_LEVEL IN(" + param.getOrderLevel() + ")";
        }

        if (!StringUtil.isEmpty(param.getReturnFlg())) {
            getOrdersSql = getOrdersSql + " AND so.RETURN_FLG = " + param.getReturnFlg();
        }

        if (null != param.getSelfDeliveryFlg()) {
            getOrdersSql = getOrdersSql + " AND so.SELF_DELIVERY_FLG = " + param.getSelfDeliveryFlg();
        }

        if (null != param.getSplitDeliveryFlg()) {
            getOrdersSql = getOrdersSql + " AND so.SPLIT_DELIVERY_FLG = " + param.getSplitDeliveryFlg();
        }

        if (!StringUtil.isEmpty(param.getSellers())) {
            getOrdersSql = getOrdersSql + " AND so.SA_ID = '" + param.getSellers() + "'";
        }

        if (!StringUtil.isEmpty(param.getOrderTaker())) {
            getOrdersSql = getOrdersSql + " AND so.ORDER_TAKER = '" + param.getOrderTaker() + "'";
        }

        if (!CollectionUtils.isEmpty(param.getOrders())) {
            String orderIdSql = "";
            String orderCodeSql = "";
            for (SoOrder soOrder : param.getOrders()) {
                if (null != soOrder.getOrderId()) {
                    orderIdSql = orderIdSql + "," + "'" + soOrder.getOrderId() + "'";
                }
                if (null != soOrder.getOrderCode()) {
                    orderCodeSql = orderCodeSql + "," + "'" + soOrder.getOrderCode() + "'";
                }
            }
            if (!StringUtil.isEmpty(orderIdSql)) {
                getOrdersSql = getOrdersSql + " AND so.ORDER_ID IN (" + orderIdSql.substring(NumberConstant.IntDef.INT_ONE, orderIdSql.length()) + ")";
            }
            if (!StringUtil.isEmpty(orderCodeSql)) {
                getOrdersSql = getOrdersSql + " AND so.ORDER_CODE IN (" + orderCodeSql.substring(NumberConstant.IntDef.INT_ONE, orderIdSql.length()) + ")";
            }
        }

        return getOrdersSql;
    }

    /**
     * 插叙条件的判断
     *
     * @param orderSaleSql
     * @param param
     * @return
     */
    public String getSaleWhereSql(String orderSaleSql, ISO151416OrderSearchParam param) {
        if (!StringUtil.isEmpty(param.getBuyersId())) {
            orderSaleSql = orderSaleSql + " AND so.BUYER_ID = '" + param.getBuyersId() + "'";
        }

        if (!StringUtil.isEmpty(param.getBuyersCode())) {
            orderSaleSql = orderSaleSql + " AND so.BUYER_CODE = '" + param.getBuyersCode() + "'";
        }

        if (!StringUtil.isEmpty(param.getSellerCode())) {
            orderSaleSql = orderSaleSql + " AND sso.SELLER_CODE = '" + param.getSellerCode() + "'";
        }

        if (!StringUtil.isEmpty(param.getOrderType())) {
            orderSaleSql = orderSaleSql + " AND sso.ORDER_TYPE IN (" + param.getOrderType() + ")";
        }

        if (!StringUtil.isEmpty(param.getOrderStatus())) {
            orderSaleSql = orderSaleSql + " AND sso.SUB_ORDER_STATUS IN (" + param.getOrderStatus() + ")";
        }

        if (null != param.getOrderTimeFrom()) {
            orderSaleSql = orderSaleSql + " AND so.ORDER_TIME >= '" + DateTimeUtil.formatDate("yyyy-MM-dd HH:mm:ss", param.getOrderTimeFrom()) + "'";
        }

        if (null != param.getOrderTimeTo()) {
            orderSaleSql = orderSaleSql + " AND so.ORDER_TIME <= '" + DateTimeUtil.formatDate("yyyy-MM-dd HH:mm:ss", param.getOrderTimeTo()) + "'";
        }

        if (null != param.getDelFlg()) {
            orderSaleSql = orderSaleSql + " AND so.DEL_FLG = " + param.getDelFlg();
        }

        if (!StringUtil.isEmpty(param.getOrderSource())) {
            orderSaleSql = orderSaleSql + " AND so.ORDER_SOURCE = " + param.getOrderSource();
        }

        if (!StringUtil.isEmpty(param.getDistrictCode())) {
            orderSaleSql = orderSaleSql + " AND so.DISTRICT_CODE IN( " + param.getDistrictCode() + ")";
        }

        if (null != param.getPaymentType()) {
            orderSaleSql = orderSaleSql + " AND so.PAYMENT_TYPE = " + param.getPaymentType();
        }

        if (null != param.getOrderAmountMin()) {
            orderSaleSql = orderSaleSql + " AND a.ORDER_AMOUNT >= " + param.getOrderAmountMin();
        }

        if (null != param.getOrderAmountMax()) {
            orderSaleSql = orderSaleSql + " AND a.ORDER_AMOUNT <= " + param.getOrderAmountMax();
        }

        if (!StringUtil.isEmpty(param.getOrderLevel())) {
            orderSaleSql = orderSaleSql + " AND sod.ORDER_DETAIL_LEVEL IN(" + param.getOrderLevel() + ")";
        }

        if (!StringUtil.isEmpty(param.getReturnFlg())) {
            orderSaleSql = orderSaleSql + " AND so.RETURN_FLG = " + param.getReturnFlg();
        }

        if (null != param.getSelfDeliveryFlg()) {
            orderSaleSql = orderSaleSql + " AND so.SELF_DELIVERY_FLG = " + param.getSelfDeliveryFlg();
        }

        if (null != param.getSplitDeliveryFlg()) {
            orderSaleSql = orderSaleSql + " AND so.SPLIT_DELIVERY_FLG = " + param.getSplitDeliveryFlg();
        }

        if (!StringUtil.isEmpty(param.getSellers())) {
            orderSaleSql = orderSaleSql + " AND so.SA_ID = '" + param.getSellers() + "'";
        }

        if (!StringUtil.isEmpty(param.getOrderTaker())) {
            orderSaleSql = orderSaleSql + " AND so.ORDER_TAKER = '" + param.getOrderTaker() + "'";
        }

        if (!CollectionUtils.isEmpty(param.getOrders())) {
            String orderIdSql = "";
            String orderCodeSql = "";
            for (SoOrder soOrder : param.getOrders()) {
                if (null != soOrder.getOrderId()) {
                    orderIdSql = orderIdSql + "," + "'" + soOrder.getOrderId() + "'";
                }
                if (null != soOrder.getOrderCode()) {
                    orderCodeSql = orderCodeSql + "," + "'" + soOrder.getOrderCode() + "'";
                }
            }

            if (!StringUtil.isEmpty(orderIdSql)) {
                orderSaleSql = orderSaleSql + " AND so.ORDER_ID IN (" + orderIdSql.substring(NumberConstant.IntDef.INT_ONE, orderIdSql.length()) + ")";
            }

            if (!StringUtil.isEmpty(orderCodeSql)) {
                orderSaleSql = orderSaleSql + " AND so.ORDER_CODE IN (" + orderCodeSql.substring(NumberConstant.IntDef.INT_ONE, orderIdSql.length()) + ")";
            }
        }

        return orderSaleSql;
    }

    public String getSoldProductWhereSql(String sql , ISO151416SoldProductParam param){
        if(!StringUtil.isEmpty(param.getSellerCode())){
            sql = sql + " AND sso.SELLER_CODE = " + param.getSellerCode();
        }

        if(null != param.getOrderStatus()){
            sql = sql + " AND so.ORDER_STATUS = " + param.getOrderStatus();
        }

        if(!StringUtil.isEmpty(param.getInputParam())){
            sql = sql + " AND ( ssod.PD_NAME = '" + param.getInputParam() + "' OR so.ORDER_CODE = '" + param.getInputParam() + "')";
        }

        if(null != param.getOrderStartTime()){
            sql = sql + "so.ORDER_TIME >= '" + DateTimeUtil.formatDate("yyyy-MM-dd HH:mm:ss", param.getOrderStartTime()) + "'";
        }

        if(null != param.getOrderEndTime()){
            sql = sql + "so.ORDER_TIME <= '" + DateTimeUtil.formatDate("yyyy-MM-dd HH:mm:ss", param.getOrderEndTime()) + "'";
        }

        return sql;
    }

    public List<ISO151416SellerProductResult> getSoldProductResult(String sql){
        List<Map<String, Object>> ordersList = baseJdbc.queryForListNotCount(sql, null, null, true);
        List<ISO151416SellerProductResult> result = new ArrayList<>();
        for (Map<String, Object> map : ordersList) {
            ISO151416SellerProductResult bean = new ISO151416SellerProductResult();
            try {
                BeanUtils.copyProperties(bean, map);
            } catch (Exception e) {
                e.printStackTrace();
            }
            result.add(bean);
        }
        return result;
    }

    /**
     * 得到结果集共通
     *
     * @param sql
     * @return
     */
    public List<ISO151414OrderSearchResult> getResult(String sql) {
        List<Map<String, Object>> ordersList = baseJdbc.queryForListNotCount(sql, null, null, true);
        List<ISO151414OrderSearchResult> result = new ArrayList<>();
        for (Map<String, Object> map : ordersList) {
            ISO151414OrderSearchResult bean = new ISO151414OrderSearchResult();
            try {
                BeanUtils.copyProperties(bean, map);
            } catch (Exception e) {
                e.printStackTrace();
            }
            result.add(bean);
        }
        return result;
    }
}
