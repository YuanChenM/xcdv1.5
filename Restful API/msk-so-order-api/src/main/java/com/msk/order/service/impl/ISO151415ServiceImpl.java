package com.msk.order.service.impl;


import com.msk.common.constant.SystemConstant;
import com.msk.common.constant.business.CapitalPoolConst;
import com.msk.common.constant.business.InventoryCodeMasterDef;
import com.msk.common.data.jpa.condition.BaseOperatorEnum;
import com.msk.common.data.jpa.condition.CommonSpecification;
import com.msk.common.data.jpa.condition.Filter;
import com.msk.common.data.jpa.impl.BaseJdbcImpl;
import com.msk.common.exception.BusinessException;
import com.msk.common.constant.NumberConstant;
import com.msk.common.constant.business.OrderCodeMasterDef;
import com.msk.common.data.jpa.BaseRepository;
import com.msk.common.utils.*;
import com.msk.order.bean.param.*;
import com.msk.order.bean.result.*;
import com.msk.order.entity.*;
import com.msk.order.repository.*;
import com.msk.order.service.*;
import com.msk.order.util.SoRestUtil;
import com.msk.order.util.SqlUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;

/**
 * ISO151415_收发货接口
 * Created by wang_shuai on 2016/8/12.
 */
@Service
public class ISO151415ServiceImpl extends BaseService implements ISO151415Service {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ISO151415ServiceImpl.class);
    @Autowired
    private SoOrderRepository soOrderRepository;
    @Autowired
    private SoOrderShipRepository soOrderShipRepository;
    @Autowired
    private SoDeliverRepository soDeliverRepository;
    @Autowired
    private SoDeliverDetailRepository soDeliverDetailRepository;
    @Autowired
    private SoOrderShipDetailRepository soOrderShipDetailRepository;
    @Autowired
    private SoOrderDetailRepository soOrderDetailRepository;
    @Autowired
    private SoSubOrderRepository soSubOrderRepository;
    @Autowired
    private SoSubOrderDetailRepository soSubOrderDetailRepository;
    @Autowired
    private BaseJdbcImpl baseJdbc;
    @Autowired
    private AsyncPostService asyncPostService;
    @Autowired
    private OrderStatusService orderStatusService;
    @Autowired
    private SubOrderStatusService subOrderStatusService;

    @Override
    @Transactional
    public BaseOrderStatusResult modifyPartReceipt(ISO151415OrderReceiptParam param){
        logger.debug("收货操作");
        List<OrderShipInfo> shipList = param.getShipList();
        if(CollectionUtils.isEmpty(shipList)){
            throw new BusinessException("收货单信息不能为空");

        }
        Filter<SoOrderShip> filter = new Filter<>();
        filter.add("shipId",BaseOperatorEnum.EQUAL,param.getShipId());
        filter.add("delFlg",BaseOperatorEnum.EQUAL,SystemConstant.DelFlg.ENABLE);
        CommonSpecification specification = new CommonSpecification(filter);
        SoOrderShip soOrderShip = soOrderShipRepository.findOne(specification);

        if(null == soOrderShip){
            throw new BusinessException("该收货单ID找不到相关收货单信息，请确认");
        }

        //设置销售平台和物流区
        Filter<SoOrder> orderFilter = new Filter<>();
        orderFilter.add("orderId",BaseOperatorEnum.EQUAL,soOrderShip.getOrderId());
        orderFilter.add("delFlg",BaseOperatorEnum.EQUAL,SystemConstant.DelFlg.ENABLE);
        CommonSpecification orderSpecification = new CommonSpecification(orderFilter);
        SoOrder soOrder = soOrderRepository.findOne(orderSpecification);
        param.setSalePlatform(soOrder.getSalePlatform());
        param.setSaleRegionCode(soOrder.getDistrictCode());

        param.setOrderCode(soOrderShip.getOrderCode());
        param.setOrderId(soOrderShip.getOrderId());
        //部分收货具体处理流程
        dealReceiptListInfo(param);
        //更新订单状态、发货单状态
        modifyOrderReceiptStatus(param);

        return getReceiptOrderResultInfo(param);
    }

    @Override
    @Transactional
    public BaseOrderStatusResult modifyPartDeliver(ISO151415OrderDeliverParam param){
        logger.debug("发货操作");
        //检查数据版本是否正确(现在支持部分发货，会存在版本更新不及时的情况所以这边不好做校验)
        //super.versionValidator("SO_ORDER",new String[]{"ORDER_ID"},new Object[]{param.getOrderId()},param.getVer());
        List<OrderShipInfo> shipList = param.getShipList();
        if(CollectionUtils.isEmpty(shipList)){
            throw new BusinessException("配送单信息不能为空");
        }
        Filter<SoOrderShip> filter = new Filter<>();
        filter.add("shipId",BaseOperatorEnum.EQUAL,param.getShipId());
        filter.add("delFlg",BaseOperatorEnum.EQUAL,SystemConstant.DelFlg.ENABLE);
        CommonSpecification specification = new CommonSpecification(filter);
        SoOrderShip soOrderShip = soOrderShipRepository.findOne(specification);

        if(null == soOrderShip){
            throw new BusinessException("没有对应的发货单信息");
        }

        //设置销售平台和物流区
        Filter<SoOrder> orderFilter = new Filter<>();
        orderFilter.add("orderId",BaseOperatorEnum.EQUAL,soOrderShip.getOrderId());
        orderFilter.add("delFlg",BaseOperatorEnum.EQUAL,SystemConstant.DelFlg.ENABLE);
        CommonSpecification orderSpecification = new CommonSpecification(orderFilter);
        SoOrder soOrder = soOrderRepository.findOne(orderSpecification);
        param.setSalePlatform(soOrder.getSalePlatform());
        param.setSaleRegionCode(soOrder.getDistrictCode());

        soOrderShip.setCrtId(param.getUpdId());
        param.setOrderCode(soOrderShip.getOrderCode());
        param.setOrderId(soOrderShip.getOrderId());
        //部分发货具体处理流程
        dealShipListInfo(param,soOrderShip);
        //更新订单状态
        modifyOrderStatus(param);
        //更新发货单状态
        modifyShipStatus(param);
        //更新分批订单状态
        modifySubStatus(param);
        //调用库存出库接口outBound
        sendOutBound(param,soOrder,soOrderShip);

        return getOrderResultInfo(param);
    }


    /**
     * 得到最终的返回结果
     * @OrderReceiptParam param
     * @return
     */
    public BaseOrderStatusResult getReceiptOrderResultInfo(ISO151415OrderReceiptParam param){
        Filter<SoOrder> filter = new Filter<>();
        filter.add("orderId",BaseOperatorEnum.EQUAL,param.getOrderId());
        filter.add("delFlg",BaseOperatorEnum.EQUAL,SystemConstant.DelFlg.ENABLE);
        CommonSpecification commonSpecification = new CommonSpecification(filter);
        SoOrder soOrder = soOrderRepository.findOne(commonSpecification);

        BaseOrderStatusResult result = new BaseOrderStatusResult();
        result.setOrderId(soOrder.getOrderId());
        result.setShipId(param.getShipId());
        result.setOrderStatus(soOrder.getOrderStatus());
        result.setVer(soOrder.getVer());
        result.setPaymentType(soOrder.getPaymentType());
        return result;
    }


    /**
     * 更新订单状态、发货单状态操作
     * @OrderReceiptParam param
     */
    @Transactional
    public void modifyOrderReceiptStatus(ISO151415OrderReceiptParam param){
        // 更新订单状态
        ISO151415ModifyStatusParam orderStatusParam = new ISO151415ModifyStatusParam();
        orderStatusParam.setOrderId(param.getOrderId());
        orderStatusParam.setUpdId(param.getUpdId());
        orderStatusParam.setShipId(param.getShipId());
        orderStatusParam.setModifyFlag("receipt");
        modifyOrderStatusByOrderId(orderStatusParam);
        // 更新发货单状态
        ISO151415ModifyStatusParam shipStatusParam = new ISO151415ModifyStatusParam();
        shipStatusParam.setShipId(param.getShipId());
        shipStatusParam.setUpdId(param.getUpdId());
        modifyShipStatus(shipStatusParam);
        // 更新分批订单状态
        ISO151415ModifyStatusParam subStatusParam = new ISO151415ModifyStatusParam();
        subStatusParam.setShipId(param.getShipId());
        subStatusParam.setUpdId(param.getUpdId());
        modifySubOrderStatus(subStatusParam);
    }


    /**
     * 根据调用收货接口中的配送单信息处理
     * @param param
     */
    @Transactional
    public void dealReceiptListInfo(ISO151415OrderReceiptParam param){
        List<OrderShipInfo> shipList = param.getShipList();
        // 资金池接口参数
        List<ISO151415SoCpSelChargingParam> cpParam = new ArrayList<>();
        BigDecimal paidAmount = new BigDecimal(NumberConstant.IntDef.INT_ZERO);

        for(OrderShipInfo orderShipInfo : shipList){
            List<OrderShipProductInfo> productList = orderShipInfo.getProductList();
            if(CollectionUtils.isEmpty(productList)){
                throw new BusinessException("产品数据不能为空");
            }
            for(OrderShipProductInfo orderShipProductInfo :productList){
                orderShipProductInfo.setUpdId(param.getUpdId());
                orderShipProductInfo.setUpdTime(DateTimeUtil.parseDate(orderShipInfo.getActualReceiveDate(),DateTimeUtil.FORMAT_DATE_YYYYMMDD  + " HH:mm:ss"));
                //计算总支付金额
                Filter<SoOrderShipDetail> shipDetailFilter = new Filter<>();
                shipDetailFilter.add("shipDetailId", BaseOperatorEnum.EQUAL,orderShipProductInfo.getShipDetailId());
                shipDetailFilter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
                CommonSpecification shipDetailCommonSpecification = new CommonSpecification(shipDetailFilter);
                SoOrderShipDetail shipDetail = soOrderShipDetailRepository.findOne(shipDetailCommonSpecification);
                Filter<SoOrderDetail> detailFilter = new Filter<>();
                detailFilter.add("orderDetailId", BaseOperatorEnum.EQUAL,shipDetail.getOrderDetailId());
                detailFilter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
                CommonSpecification detailCommonSpecification = new CommonSpecification(detailFilter);
                List<SoOrderDetail> soOrderDetails = soOrderDetailRepository.findAll(detailCommonSpecification);
                paidAmount = DecimalUtil.add(paidAmount,DecimalUtil.multiply(orderShipProductInfo.getReceiveQty(),soOrderDetails.get(0).getPdPrice()));
            }
            //得到配送明细数据并且更新订单相关的明细数据
            getReceiptListAndModifyOrderInfo(productList,param);
            //更新实际到货时间
            SoDeliver soDeliver = new SoDeliver();
            soDeliver.setDeliverCode(orderShipInfo.getDeliverCode());
            soDeliver.setActualReceiveDate(DateTimeUtil.parseDate(orderShipInfo.getActualReceiveDate(), DateTimeUtil.FORMAT_DATE_YYYYMMDD  + " HH:mm:ss"));
            //soDeliver.setOrderId(param.getOrderId());
            soDeliver.setShipId(param.getShipId());

            Filter<SoDeliver> filter = new Filter<>();
            filter.add("deliverCode", BaseOperatorEnum.EQUAL,soDeliver.getDeliverCode());
            filter.add("shipId", BaseOperatorEnum.EQUAL,soDeliver.getShipId());
            filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
            CommonSpecification commonSpecification = new CommonSpecification(filter);
            long deliverCount = soDeliverRepository.count(commonSpecification);

            if(deliverCount > 0){
                Filter<SoDeliver> filterDeliver = new Filter<>();
                filterDeliver.add("deliverCode", BaseOperatorEnum.EQUAL,soDeliver.getDeliverCode());
                filterDeliver.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
                CommonSpecification commonSpecificationDeliver = new CommonSpecification(filterDeliver);
                SoDeliver soDeliverBean = soDeliverRepository.findOne(commonSpecificationDeliver);
                soDeliverBean.setActualReceiveDate(DateTimeUtil.parseDate(orderShipInfo.getActualReceiveDate(), DateTimeUtil.FORMAT_DATE_YYYYMMDD + " HH:mm:ss"));
                soDeliverRepository.save(soDeliverBean);

                for(OrderShipProductInfo orderShipProductInfo :productList){
                    Filter<SoDeliverDetail> filterDetail = new Filter<>();
                    filterDetail.add("shipDetailId", BaseOperatorEnum.EQUAL,orderShipProductInfo.getShipDetailId());
                    filterDetail.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
                    CommonSpecification commonSpecificationDetail = new CommonSpecification(filterDetail);
                    List<SoDeliverDetail> soDeliverDetails = soDeliverDetailRepository.findAll(commonSpecificationDetail);
                    List<SoDeliverDetail> deliverDetailList = new ArrayList<>();
                    for (SoDeliverDetail soDeliverDetail : soDeliverDetails){
                        if(soDeliverDetail.getReceiveQty() == null){
                            soDeliverDetail.setReceiveQty(orderShipProductInfo.getReceiveQty());
                        }else {
                            soDeliverDetail.setReceiveQty(DecimalUtil.add(orderShipProductInfo.getReceiveQty(), soDeliverDetail.getReceiveQty()));
                        }
                        soDeliverDetail.setShipDetailId(orderShipProductInfo.getShipDetailId());
                        soDeliverDetail.setUpdTime(DateTimeUtil.getCustomerDate());
                        soDeliverDetail.setUpdId(param.getUpdId());
                        deliverDetailList.add(soDeliverDetail);
                    }
                    soDeliverDetailRepository.save(deliverDetailList);
                }
            }else {
                throw new BusinessException("该配送单编码找不到相关配送单信息，请确认！");
            }

            // 设置资金池接口参数
            for (OrderShipProductInfo shipProductParam : productList){
                cpParam.add(this.getSoCpSelCharging(param, orderShipInfo, shipProductParam));
            }
        }
        //调用支付明细接口
        ISO151415RestCpRunningParam restCpRunningParam = getCpRunningParam(param);
        if (restCpRunningParam != null){
            restCpRunningParam.setUpdId(param.getUpdId());
            restCpRunningParam.setPaidAmount(paidAmount);
            asyncPostService.sendCpRunning(restCpRunningParam);
        }

        // 调用资金池接口
        if (!CollectionUtils.isEmpty(cpParam)) {
            ISO151415RestParam iso151415RestParam = new ISO151415RestParam();
            iso151415RestParam.setSoCpSelChargingList(cpParam);
            iso151415RestParam.setUpdId(param.getUpdId());
            asyncPostService.sendCpSelCharging(iso151415RestParam);
        }
    }

    public ISO151415RestCpRunningParam getCpRunningParam(ISO151415OrderReceiptParam param){
        Filter<SoOrder> filter = new Filter<>();
        filter.add("orderId", BaseOperatorEnum.EQUAL,param.getOrderId());
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification commonSpecification = new CommonSpecification(filter);
        SoOrder order = soOrderRepository.findOne(commonSpecification);

        ISO151415RestCpRunningParam restCpRunningParam = new ISO151415RestCpRunningParam();
        restCpRunningParam.setBackType(CapitalPoolConst.BackType.BUYER);
        restCpRunningParam.setAmountType(NumberConstant.IntDef.INT_ZERO);//设置金额类型为付款
        restCpRunningParam.setTransCode(param.getOrderCode());
        restCpRunningParam.setTransType(NumberConstant.IntDef.INT_ZERO);//设置交易类型为主订单0
        restCpRunningParam.setPaidType(NumberConstant.IntDef.INT_ONE);//设置支付方式为现金1
        restCpRunningParam.setPaidSeq(param.getOrderCode());
        restCpRunningParam.setPaidTime(DateTimeUtil.getCustomerDate());
        restCpRunningParam.setPayeeId(order.getSalePlatform());
        restCpRunningParam.setPayerId(param.getUpdId());
        restCpRunningParam.setPlatform(Integer.parseInt(order.getSalePlatform()));
        restCpRunningParam.setPaymentType(order.getPaymentType());
        restCpRunningParam.setOrderId(param.getOrderId());
        return restCpRunningParam;
    }

    /**
     * 设置资金池接口参数
     * @param param
     * @param orderShipProductInfo
     * @return
     */
    public ISO151415SoCpSelChargingParam getSoCpSelCharging(ISO151415OrderReceiptParam param,OrderShipInfo orderShipInfo,OrderShipProductInfo orderShipProductInfo){
        //查询subOrderId
        Filter<SoOrderShipDetail> filter = new Filter<>();
        filter.add("shipDetailId",BaseOperatorEnum.EQUAL,orderShipProductInfo.getShipDetailId());
        filter.add("delFlg",BaseOperatorEnum.EQUAL,SystemConstant.DelFlg.ENABLE);
        CommonSpecification commonSpecification = new CommonSpecification(filter);
        SoOrderShipDetail soOrderShipDetail = soOrderShipDetailRepository.findOne(commonSpecification);
        //查询Sub_Order表信息
        Filter<SoSubOrder> subOrderFilter = new Filter<>();
        subOrderFilter.add("subOrderId",BaseOperatorEnum.EQUAL,soOrderShipDetail.getSubOrderId());
        subOrderFilter.add("delFlg",BaseOperatorEnum.EQUAL,SystemConstant.DelFlg.ENABLE);
        CommonSpecification subOrderSpecification = new CommonSpecification(subOrderFilter);
        SoSubOrder soSubOrder = soSubOrderRepository.findOne(subOrderSpecification);
        //查询主订单SO_ORDER信息
        Filter<SoOrder> orderFilter = new Filter<>();
        orderFilter.add("orderId",BaseOperatorEnum.EQUAL,soSubOrder.getOrderId());
        orderFilter.add("delFlg",BaseOperatorEnum.EQUAL,SystemConstant.DelFlg.ENABLE);
        CommonSpecification orderSpecification = new CommonSpecification(orderFilter);
        SoOrder soOrder = soOrderRepository.findOne(orderSpecification);
        //查询产品单价
        Filter<SoOrderDetail> orderDetailFilter = new Filter<>();
        orderDetailFilter.add("orderDetailId",BaseOperatorEnum.EQUAL,soOrderShipDetail.getOrderDetailId());
        orderDetailFilter.add("delFlg",BaseOperatorEnum.EQUAL,SystemConstant.DelFlg.ENABLE);
        CommonSpecification detailSpecification = new CommonSpecification(orderDetailFilter);
        SoOrderDetail soOrderDetail = soOrderDetailRepository.findOne(detailSpecification);
        //查询配送数量
        Filter<SoDeliverDetail> deliverDetailFilter = new Filter<>();
        deliverDetailFilter.add("deliverCode",BaseOperatorEnum.EQUAL,orderShipInfo.getDeliverCode());
        deliverDetailFilter.add("shipDetailId",BaseOperatorEnum.EQUAL,orderShipProductInfo.getShipDetailId());
        deliverDetailFilter.add("delFlg",BaseOperatorEnum.EQUAL,SystemConstant.DelFlg.ENABLE);
        CommonSpecification deliverSpecification = new CommonSpecification(deliverDetailFilter);
        SoDeliverDetail soDeliverDetail = soDeliverDetailRepository.findOne(deliverSpecification);


        ISO151415SoCpSelChargingParam soCpSelCharging = new ISO151415SoCpSelChargingParam();
        soCpSelCharging.setOrderId(soOrder.getOrderId());
        soCpSelCharging.setTransCode(soOrder.getOrderCode());
        soCpSelCharging.setTransType(NumberConstant.IntDef.INT_ZERO);
        // 供货明细所属为子订单，传递买手层级信息；供货明细所属为主订单，传递卖家（供应商）层级信息
        if (soSubOrder.getOrderType().equals(NumberConstant.IntDef.INT_FOUR) || soSubOrder.getOrderType().equals(NumberConstant.IntDef.INT_SEVEN)){//4,7
            soCpSelCharging.setBusinessMainId(soSubOrder.getSellerCode());
            soCpSelCharging.setBusinessMainName(soSubOrder.getSellerName());
            soCpSelCharging.setBusinessMainRole(Integer.valueOf(CapitalPoolConst.RoleType.ROLE_BUYERE));
        } else {
            soCpSelCharging.setBusinessMainId(soOrderShipDetail.getSupplierCode());
            soCpSelCharging.setBusinessMainName(soOrderShipDetail.getSupplierName());
            soCpSelCharging.setBusinessMainRole(Integer.valueOf(CapitalPoolConst.RoleType.ROLE_SELLER));
        }

        // 收款方角色
        if (soOrder.getOrderType() == OrderCodeMasterDef.OrderType.THIRD_PARTY_ORDER
                || soOrder.getOrderType() == OrderCodeMasterDef.OrderType.DISTRIBUTION_ORDER) {
            soCpSelCharging.setBusinessAssistantRole(Integer.valueOf(CapitalPoolConst.RoleType.ROLE_BIDDER));
        } else {
            soCpSelCharging.setBusinessAssistantRole(Integer.valueOf(CapitalPoolConst.RoleType.ROLE_BUYERE));
        }
        soCpSelCharging.setBusinessAssistantId(soOrder.getBuyerId());
        soCpSelCharging.setBusinessAssistantCode(soOrder.getBuyerCode());
        soCpSelCharging.setBusinessAssistantName(soOrder.getBuyerName());
        soCpSelCharging.setDeliveryCode(orderShipInfo.getDeliverCode());
        soCpSelCharging.setDeliveryTime(DateTimeUtil.parseDate(orderShipInfo.getActualReceiveDate(), DateTimeUtil.FORMAT_DATE_YYYYMMDD  + " HH:mm:ss"));
        soCpSelCharging.setShippingAmount(DecimalUtil.multiply(soDeliverDetail.getDeliverQty(),soOrderDetail.getPdPrice()));
        soCpSelCharging.setPaidAmount(DecimalUtil.multiply(orderShipProductInfo.getReceiveQty(),soOrderDetail.getPdPrice()));
        return soCpSelCharging;
    }

    /**
     * 根据参数信息得到明细数据和供货明细数据并且更新
     */
    @Transactional
    public void getReceiptListAndModifyOrderInfo(List<OrderShipProductInfo> productList,ISO151415OrderReceiptParam param){
        //通过skuCode调用卖家接口获得pdCode list
        ISO151415RestSkuParam iso151415RestSkuParam = new ISO151415RestSkuParam();
        List<ISO151415SlArtNoResult> list = new ArrayList<>();
        for (OrderShipProductInfo orderShipProductInfo : productList){
            ISO151415SlArtNoResult slArtNo = new ISO151415SlArtNoResult();
            slArtNo.setSkuCode(orderShipProductInfo.getSkuCode());
            list.add(slArtNo);
        }
        iso151415RestSkuParam.setSlList(list);
        iso151415RestSkuParam.setSalePlatform(param.getSalePlatform());
        iso151415RestSkuParam.setSaleRegionCode(param.getSaleRegionCode());
        ISO151415RestPdInfoResult rsPdInfo = SoRestUtil.getPdCodeBySkuCode(iso151415RestSkuParam);
        for (OrderShipProductInfo orderShipProductInfo : productList){
            orderShipProductInfo.setShipId(param.getShipId());
            orderShipProductInfo.setUpdId(param.getUpdId());
//            orderShipProductInfo.setOrderId(param.getOrderId());
            for (ISO151415SlArtNoResult slArtNo : rsPdInfo.getProductList()){
                if (orderShipProductInfo.getSkuCode().equals(slArtNo.getSkuCode())){
                    orderShipProductInfo.setPdCode(slArtNo.getPdCode());
                }
            }
            String sql = SqlUtil.getSqlBySqlId("ISO151415.getReceiptListAndModifyOrderInfo");
            List paramList = new ArrayList();
            paramList.add(orderShipProductInfo.getShipDetailId());
            paramList.add(orderShipProductInfo.getShipId());
            List<Map<String,Object>> mapList = baseJdbc.queryForListNotCount(sql,paramList,null,true);
            OrderDetailAndSuppInfo detailAndSuppInfo = new OrderDetailAndSuppInfo();

            if(null == detailAndSuppInfo){
                throw new BusinessException("收货单号和明细信息对不上");
            }
            if (mapList.size() != 0){
                try {
                    BeanUtils.populate(detailAndSuppInfo,mapList.get(0));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }else {
                throw new BusinessException("查询不到发货明细相关数据");
            }

            //更新订单明细,发货明细,分批订单明细
            modifyReceiptDetailAndSupInfo(orderShipProductInfo, detailAndSuppInfo);
        }

    }

    /**
     * 更新订单明细,发货明细，分批订单明细
     */
    @Transactional
    public void modifyReceiptDetailAndSupInfo(OrderShipProductInfo orderShipProductInfo,OrderDetailAndSuppInfo detailAndSuppInfo){
        if(!checkOrderReceiptStatus(detailAndSuppInfo)){
            throw new BusinessException("订单状态不为可收货状态");
        }
        //设置收货时间
        detailAndSuppInfo.setReceivedTime(DateTimeUtil.getCustomerDate());
        //更新订单发货明细数据
        modifyReceiptSuppDetailInfo(orderShipProductInfo, detailAndSuppInfo);
        //更新订单明细数据
        modifyReceiptDetailInfo(orderShipProductInfo, detailAndSuppInfo);
        //更新分批订单明细数据
        modifyReceiptSubDetailInfo(orderShipProductInfo, detailAndSuppInfo);
    }



    /**
     * 更新发货明细(收货接口)
     */
    @Transactional
    public void modifyReceiptSuppDetailInfo(OrderShipProductInfo orderShipProductInfo,OrderDetailAndSuppInfo detailAndSuppInfo){

        if(!checkSuppReceiptStatus(detailAndSuppInfo)){
            throw new BusinessException("订单供货明细状态不为可收货状态");
        }
        //传入的收货数量
        BigDecimal receiveQty = orderShipProductInfo.getReceiveQty();
        //已收货数量
        BigDecimal suppReceiveQty = detailAndSuppInfo.getSuppReceiveQty();
        //供货数量
        BigDecimal suppQty = detailAndSuppInfo.getSuppOrderQty();
        //取消数量
        BigDecimal suppCancelQty = detailAndSuppInfo.getSuppCancelQty();
        //退货数量
//        BigDecimal suppReturnQty = detailAndSuppInfo.getSuppReturnQty();
        //拒收数量
        BigDecimal suppRejectionQty = detailAndSuppInfo.getSuppRejectionQty();
        //总收货数量
        BigDecimal allReceiveQty = DecimalUtil.add(suppReceiveQty,receiveQty);
        //取消数量+拒收数量
        BigDecimal allOtherQty = DecimalUtil.add(suppCancelQty,suppRejectionQty);
        //实际总数量
        BigDecimal allActualQty = DecimalUtil.add(allReceiveQty,allOtherQty);
        //可收货数量
        BigDecimal canReceiveQty = DecimalUtil.subtract(suppQty, suppReceiveQty);

        if(receiveQty.compareTo(canReceiveQty) > NumberConstant.IntDef.INT_ZERO){
            throw new BusinessException("本次收货数量大于供货明细可收货数量");
        }

        //设置明细状态为全部收货还是部分收货
        if(allActualQty.compareTo(suppQty) == NumberConstant.IntDef.INT_ZERO){
            orderShipProductInfo.setStatus(OrderCodeMasterDef.OrderDetailAvailabilityStatus.ALL_RECEIPT);
        }else{
            orderShipProductInfo.setStatus(OrderCodeMasterDef.OrderDetailAvailabilityStatus.PARTIAL_RECEIPT);
        }
        Filter<SoOrderShipDetail> filter = new Filter<>();
        filter.add("shipDetailId",BaseOperatorEnum.EQUAL,orderShipProductInfo.getShipDetailId());
        filter.add("delFlg",BaseOperatorEnum.EQUAL,SystemConstant.DelFlg.ENABLE);
        CommonSpecification commonSpecification = new CommonSpecification(filter);
        SoOrderShipDetail soOrderShipDetail = soOrderShipDetailRepository.findOne(commonSpecification);

        if (soOrderShipDetail.getReceiveQty() == null){
            soOrderShipDetail.setReceiveQty(orderShipProductInfo.getReceiveQty());
        }else {
            soOrderShipDetail.setReceiveQty(DecimalUtil.add(orderShipProductInfo.getReceiveQty(), soOrderShipDetail.getReceiveQty()));
        }
        soOrderShipDetail.setReceivedTime(orderShipProductInfo.getUpdTime());
        soOrderShipDetail.setDetailStatus(orderShipProductInfo.getStatus());
        soOrderShipDetail.setUpdId(orderShipProductInfo.getUpdId());
        soOrderShipDetail.setUpdTime(orderShipProductInfo.getUpdTime());
        soOrderShipDetail.setVer(soOrderShipDetail.getVer() + 1);
        soOrderShipDetail.setShipDetailId(orderShipProductInfo.getShipDetailId());
        soOrderShipDetail.setReceivedTime(detailAndSuppInfo.getReceivedTime());
        soOrderShipDetailRepository.save(soOrderShipDetail);
    }
    /**
     * 更新订单明细(收货接口)
     */
    @Transactional
    public void modifyReceiptDetailInfo(OrderShipProductInfo orderShipProductInfo,OrderDetailAndSuppInfo detailAndSuppInfo){
        if(!checkReceiptOrderDetailStatus(detailAndSuppInfo)){
            throw new BusinessException("明细状态不为可收货状态");
        }

        //发货数量
        BigDecimal sendQty = detailAndSuppInfo.getDetailSendQty();
        //传入的收货数量
        BigDecimal receiveQty = orderShipProductInfo.getReceiveQty();
        //订单明细订单数量
        BigDecimal orderQty = detailAndSuppInfo.getOrderQty();
        //订单明细已收货数量
        BigDecimal detailReceiveQty = detailAndSuppInfo.getDetailReceiveQty();
        //订单明细取消数量
        BigDecimal detailCancelQty = detailAndSuppInfo.getDetailCancelQty();
        //订单明细拒收数量
        BigDecimal detailRejectionQty = detailAndSuppInfo.getDetailRejectionQty();
        //总收货数量
        BigDecimal allReceiveQty = DecimalUtil.add(receiveQty, detailReceiveQty);
        //实际总数量
        BigDecimal actualQty = DecimalUtil.add(allReceiveQty, DecimalUtil.add(detailCancelQty, detailRejectionQty));
        //订单明细可收货数量
        BigDecimal canReceiveQty = DecimalUtil.subtract(orderQty, detailReceiveQty);

        if(receiveQty.compareTo(canReceiveQty) > NumberConstant.IntDef.INT_ZERO){
            throw new BusinessException("收货数量大于可收货数量");
        }
        //设置供货明细状态为全部收货还是部分收货
        if(actualQty.compareTo(orderQty) == NumberConstant.IntDef.INT_ZERO){
            orderShipProductInfo.setStatus(OrderCodeMasterDef.OrderDetailStatus.ALL_RECEIPT);
        }else {
            orderShipProductInfo.setStatus(OrderCodeMasterDef.OrderDetailStatus.PARTIAL_RECEIPT);
        }
        orderShipProductInfo.setOrderDetailId(detailAndSuppInfo.getOrderDetailId());

        Filter<SoOrderDetail> filter = new Filter<>();
        filter.add("orderDetailId",BaseOperatorEnum.EQUAL,detailAndSuppInfo.getOrderDetailId());
        filter.add("delFlg",BaseOperatorEnum.EQUAL,SystemConstant.DelFlg.ENABLE);
        CommonSpecification specification = new CommonSpecification(filter);
        SoOrderDetail soOrderDetail = soOrderDetailRepository.findOne(specification);
        soOrderDetail.setDetailStatus(orderShipProductInfo.getStatus());
        if(soOrderDetail.getReceiveQty() == null){
            soOrderDetail.setReceiveQty(orderShipProductInfo.getReceiveQty());
        }else{
            soOrderDetail.setReceiveQty(DecimalUtil.add(orderShipProductInfo.getReceiveQty(), soOrderDetail.getReceiveQty()));
        }
        soOrderDetail.setOrderDetailId(orderShipProductInfo.getOrderDetailId());
        soOrderDetail.setReceivedTime(detailAndSuppInfo.getReceivedTime());
        soOrderDetailRepository.save(soOrderDetail);

    }

    /**
     * 更新分批订单明细(收货接口)
     */
    @Transactional
    public void modifyReceiptSubDetailInfo(OrderShipProductInfo orderShipProductInfo,OrderDetailAndSuppInfo detailAndSuppInfo){
        if(!checkReceiptSubOrderDetailStatus(detailAndSuppInfo)){
            throw new BusinessException("分批明细状态不为可收货状态");
        }

        //发货数量
        BigDecimal sendQty = detailAndSuppInfo.getSubDetailSendQty();
        //传入的收货数量
        BigDecimal receiveQty = orderShipProductInfo.getReceiveQty();
        //订单明细订单数量
        BigDecimal subDetailOrderQty = detailAndSuppInfo.getSubDetailOrderQty();
        //订单明细已收货数量
        BigDecimal detailReceiveQty = detailAndSuppInfo.getSubDetailReceiveQty();
        //订单明细取消数量
        BigDecimal detailCancelQty = detailAndSuppInfo.getSubDetailCancelQty();
        //订单明细拒收数量
        BigDecimal detailRejectionQty = detailAndSuppInfo.getSubDetailRejectionQty();
        //总收货数量
        BigDecimal allReceiveQty = DecimalUtil.add(receiveQty,detailReceiveQty);
        //实际总数量
        BigDecimal actualQty = DecimalUtil.add(allReceiveQty, DecimalUtil.add(detailCancelQty, detailRejectionQty));
        //订单明细可收货数量
        BigDecimal canReceiveQty = DecimalUtil.subtract(subDetailOrderQty, detailReceiveQty);

        if(receiveQty.compareTo(canReceiveQty) > NumberConstant.IntDef.INT_ZERO){
            throw new BusinessException("收货数量大于可收货数量");
        }
        if(actualQty.compareTo(subDetailOrderQty) == NumberConstant.IntDef.INT_ZERO){
            orderShipProductInfo.setStatus(OrderCodeMasterDef.SubOrderDetailStatus.ALL_RECEIPT);
        }else {
            orderShipProductInfo.setStatus(OrderCodeMasterDef.SubOrderDetailStatus.PARTIAL_RECEIPT);
        }

        Filter<SoSubOrderDetail> filter = new Filter<>();
        filter.add("subOrderDetailId",BaseOperatorEnum.EQUAL,detailAndSuppInfo.getSubOrderDetailId());
        filter.add("delFlg",BaseOperatorEnum.EQUAL,SystemConstant.DelFlg.ENABLE);
        CommonSpecification specification = new CommonSpecification(filter);
        SoSubOrderDetail soSubOrderDetail = soSubOrderDetailRepository.findOne(specification);
        soSubOrderDetail.setDetailStatus(orderShipProductInfo.getStatus());
        if(soSubOrderDetail.getReceiveQty() == null){
            soSubOrderDetail.setReceiveQty(orderShipProductInfo.getReceiveQty());
        }else{
            soSubOrderDetail.setReceiveQty(DecimalUtil.add(orderShipProductInfo.getReceiveQty(), soSubOrderDetail.getReceiveQty()));
        }
        soSubOrderDetail.setSubOrderDetailId(detailAndSuppInfo.getSubOrderDetailId());
        soSubOrderDetail.setReceivedTime(detailAndSuppInfo.getReceivedTime());
        soSubOrderDetailRepository.save(soSubOrderDetail);

    }

    /**
     * 检查供货明细当前状态
     * @OrderDetailAndSuppInfo detailAndSuppInfo
     */
    public boolean checkSuppReceiptStatus(OrderDetailAndSuppInfo detailAndSuppInfo){
        return !(detailAndSuppInfo.getSuppStatus() != OrderCodeMasterDef.OrderDetailAvailabilityStatus.ALL_SHIPMENT
                && detailAndSuppInfo.getSuppStatus() != OrderCodeMasterDef.OrderDetailAvailabilityStatus.PARTIAL_SHIPMENT
                && detailAndSuppInfo.getSuppStatus() != OrderCodeMasterDef.OrderDetailAvailabilityStatus.PARTIAL_RECEIPT);
    }
    /**
     * 检查订单明细当前状态
     * @OrderDetailAndSuppInfo detailAndSuppInfo
     */
    public boolean checkReceiptOrderDetailStatus(OrderDetailAndSuppInfo detailAndSuppInfo){
        return !(detailAndSuppInfo.getDetailStatus() != OrderCodeMasterDef.OrderDetailStatus.ALL_SHIPMENT
                && detailAndSuppInfo.getDetailStatus() != OrderCodeMasterDef.OrderDetailStatus.PARTIAL_SHIPMENT
                && detailAndSuppInfo.getDetailStatus() != OrderCodeMasterDef.OrderDetailStatus.PARTIAL_RECEIPT);
    }
    /**
     * 检查分批订单明细当前状态
     * @OrderDetailAndSuppInfo detailAndSuppInfo
     */
    public boolean checkReceiptSubOrderDetailStatus(OrderDetailAndSuppInfo detailAndSuppInfo){
        return !(detailAndSuppInfo.getSubDetailStatus() != OrderCodeMasterDef.SubOrderDetailStatus.ALL_SHIPMENT
                && detailAndSuppInfo.getSubDetailStatus() != OrderCodeMasterDef.SubOrderDetailStatus.PARTIAL_SHIPMENT
                && detailAndSuppInfo.getSubDetailStatus() != OrderCodeMasterDef.SubOrderDetailStatus.PARTIAL_RECEIPT);
    }

    /**
     * 检查订单当前状态
     * @OrderDetailAndSuppInfo detailAndSuppInfo
     */
    public boolean checkOrderReceiptStatus(OrderDetailAndSuppInfo detailAndSuppInfo){
        return !(detailAndSuppInfo.getOrderStatus() != OrderCodeMasterDef.OrderStatus.PARTIAL_SHIPMENT
                && detailAndSuppInfo.getOrderStatus() != OrderCodeMasterDef.OrderStatus.ALL_SHIPMENT
                && detailAndSuppInfo.getOrderStatus() != OrderCodeMasterDef.OrderStatus.PARTIAL_RECEIPT);
    }



    /**
     * 得到最终的返回结果
     * @param param
     * @return
     */
    public BaseOrderStatusResult getOrderResultInfo(ISO151415OrderDeliverParam param){
        Filter<SoOrder> filter = new Filter<>();
        filter.add("orderId",BaseOperatorEnum.EQUAL,param.getOrderId());
        filter.add("delFlg",BaseOperatorEnum.EQUAL,SystemConstant.DelFlg.ENABLE);
        CommonSpecification specification = new CommonSpecification(filter);
        SoOrder order = soOrderRepository.findOne(specification);

        BaseOrderStatusResult result = new BaseOrderStatusResult();
        result.setVer(order.getVer());
        result.setOrderId(order.getOrderId());
        result.setOrderStatus(order.getOrderStatus());
        result.setPaymentType(order.getPaymentType());
        result.setShipId(param.getShipId());
        return result;
    }

    /**
     * 更新发货单状态操作
     * @param param
     */
    @Transactional
    public void modifyShipStatus(ISO151415OrderDeliverParam param){
        ISO151415ModifyStatusParam iso151415ModifyStatusParam = new ISO151415ModifyStatusParam();
        iso151415ModifyStatusParam.setShipId(param.getShipId());
        iso151415ModifyStatusParam.setUpdId(param.getUpdId());
        modifyShipStatus(iso151415ModifyStatusParam);
    }

    /**
     * 更新订单状态操作
     * @param param
     */
    @Transactional
    public void modifyOrderStatus(ISO151415OrderDeliverParam param){
        ISO151415ModifyStatusParam modifyStatusParam = new ISO151415ModifyStatusParam();
        modifyStatusParam.setOrderId(param.getOrderId());
        modifyStatusParam.setUpdId(param.getUpdId());
        modifyStatusParam.setModifyFlag("deliver");
        modifyOrderStatusByOrderId(modifyStatusParam);
    }

    /**
     * 更新分批订单状态
     * @param param
     */
    @Transactional
    public void modifySubStatus(ISO151415OrderDeliverParam param){
        ISO151415ModifyStatusParam subModifyStatusParam = new ISO151415ModifyStatusParam();
        subModifyStatusParam.setShipId(param.getShipId());
        subModifyStatusParam.setUpdId(param.getUpdId());
        modifySubOrderStatus(subModifyStatusParam);
    }

    /**
     * 根据调用发货接口中的配送单信息处理
     * @param param
     */
    @Transactional
    public void dealShipListInfo(ISO151415OrderDeliverParam param,SoOrderShip soOrderShip){
        List<OrderShipInfo> shipList = param.getShipList();
        Filter<SoOrderShip> filterShip = new Filter<>();
        filterShip.add("shipId",BaseOperatorEnum.EQUAL,param.getShipId());
        filterShip.add("delFlg",BaseOperatorEnum.EQUAL,SystemConstant.DelFlg.ENABLE);
        CommonSpecification specificationShip = new CommonSpecification(filterShip);
        SoOrderShip orderShip = soOrderShipRepository.findOne(specificationShip);

        for(OrderShipInfo orderShipInfo : shipList){
            List<OrderShipProductInfo> productList = orderShipInfo.getProductList();
            if(CollectionUtils.isEmpty(productList)){
                throw new BusinessException("产品数据不能为空");
            }

            Map<String,Object> map = new HashMap<String, Object>();
            map.put("deliverCode", orderShipInfo.getDeliverCode());
            param.setFilterMap(map);

            Filter<SoDeliver> filter = new Filter<>();
            filter.add("deliverCode",BaseOperatorEnum.EQUAL,orderShipInfo.getDeliverCode());
            filter.add("delFlg",BaseOperatorEnum.EQUAL,SystemConstant.DelFlg.ENABLE);
            CommonSpecification specification = new CommonSpecification(filter);
            long soDeliverCount = soDeliverRepository.count(specification);

            if (soDeliverCount > NumberConstant.IntDef.INT_ZERO){
                throw new BusinessException("配送单编码重复！！");
            }
            //得到配送数据
            SoDeliver soDeliver = dealSoDeliver(orderShipInfo,soOrderShip);
            soDeliver.setDeliverId(this.maxId("so_deliver"));
            soDeliver.setOrderId(orderShip.getOrderId());
            soDeliver.setDelFlg(SystemConstant.DelFlg.ENABLE);
            soDeliver.setSubOrderId(orderShip.getSubOrderId());
            soDeliver.setVer(NumberConstant.IntDef.INT_ONE);
            //得到配送明细数据并且更新订单相关的明细数据
            param.setSubOrderCode(orderShip.getSubOrderCode());
            List<SoDeliverDetail> deliverDetailList = getDeliverListAndModifyOrderInfo(productList,param,soDeliver);

            saveDeliver(soDeliver, deliverDetailList);
        }
    }

    /**
     * 根据调用发货接口信息处理配送信息
     * @param orderShipInfo
     * @return
     */
    public SoDeliver dealSoDeliver(OrderShipInfo orderShipInfo,SoOrderShip soOrderShip)
    {
        SoDeliver soDeliver = new SoDeliver();
        soDeliver.setDeliverId(this.maxId("so_deliver"));
        soDeliver.setShipId(soOrderShip.getShipId());
        soDeliver.setCrtId(soOrderShip.getCrtId());
        soDeliver.setCrtTime(DateTimeUtil.getCustomerDate());

        if(null == orderShipInfo){
            return soDeliver;
        }

        if(!StringUtil.isEmpty(orderShipInfo.getDeliverCode())){
            soDeliver.setDeliverCode(orderShipInfo.getDeliverCode());
        }

        if(!StringUtil.isEmpty(orderShipInfo.getDeliverPerson())){
            soDeliver.setDeliverPerson(orderShipInfo.getDeliverPerson());
        }

        if(!StringUtil.isEmpty(orderShipInfo.getPersonMobile())){
            soDeliver.setPersonMobile(orderShipInfo.getPersonMobile());
        }
        if(!StringUtil.isEmpty(orderShipInfo.getDeliverDate())){
            soDeliver.setDeliverDate(DateTimeUtil.parseDate(orderShipInfo.getDeliverDate(),DateTimeUtil.FORMAT_DATE_YYYYMMDD  + " HH:mm:ss"));
        }

        if(null!=orderShipInfo.getDeliverMode()){
            soDeliver.setDeliverMode(orderShipInfo.getDeliverMode());
        }

        if(!StringUtil.isEmpty(orderShipInfo.getExpectReceiveDate())){
            soDeliver.setExpectReceiveDate(DateTimeUtil.parseDate(orderShipInfo.getExpectReceiveDate(),DateTimeUtil.FORMAT_DATE_YYYYMMDD  + " HH:mm:ss"));
        }

        if(!StringUtil.isEmpty(orderShipInfo.getRemarks())){
            soDeliver.setRemarks(orderShipInfo.getRemarks());
        }

        return soDeliver;
    }

    /**
     * 返回配送明细信息和更新发货数量和状态
     * @param productList
     * @param param
     * @param soDeliver
     * @return
     */
    @Transactional
    public List<SoDeliverDetail> getDeliverListAndModifyOrderInfo(List<OrderShipProductInfo> productList,ISO151415OrderDeliverParam param,SoDeliver soDeliver){
        List<SoDeliverDetail> deliverDetailList = new ArrayList<SoDeliverDetail>();

        //通过skuCode调用卖家接口获得pdCode list
        ISO151415RestSkuParam iso151415RestSkuParam = new ISO151415RestSkuParam();
        List<ISO151415SlArtNoResult> list = new ArrayList<>();
        for (OrderShipProductInfo orderShipProductInfo : productList){
            ISO151415SlArtNoResult slArtNo = new ISO151415SlArtNoResult();
            slArtNo.setSkuCode(orderShipProductInfo.getSkuCode());
            list.add(slArtNo);
        }
        iso151415RestSkuParam.setSlList(list);
        iso151415RestSkuParam.setSalePlatform(param.getSalePlatform());
        iso151415RestSkuParam.setSaleRegionCode(param.getSaleRegionCode());
        ISO151415RestPdInfoResult rsPdInfo = SoRestUtil.getPdCodeBySkuCode(iso151415RestSkuParam);

        for (OrderShipProductInfo orderShipProductInfo : productList){
            orderShipProductInfo.setShipId(param.getShipId());
            orderShipProductInfo.setOrderId(param.getOrderId());
            orderShipProductInfo.setOrderCode(param.getOrderCode());
            orderShipProductInfo.setUpdId(param.getUpdId());
            orderShipProductInfo.setUpdTime(DateTimeUtil.getCustomerDate());
            for (ISO151415SlArtNoResult slArtNo : rsPdInfo.getProductList()){
                if (orderShipProductInfo.getSkuCode().equals(slArtNo.getSkuCode())){
                    orderShipProductInfo.setPdCode(slArtNo.getPdCode());
                }
            }

            String sql = SqlUtil.getSqlBySqlId("ISO151415.getDeliverListAndModifyOrderInfo");
            List paramList = new ArrayList();
            paramList.add(orderShipProductInfo.getShipDetailId());
            paramList.add(orderShipProductInfo.getShipId());
            paramList.add(orderShipProductInfo.getOrderId());
            List<Map<String,Object>> mapList = baseJdbc.queryForListNotCount(sql,paramList,null,true);
            OrderDetailAndSuppInfo detailAndSuppInfo = new OrderDetailAndSuppInfo();
            if(null == detailAndSuppInfo){
                throw new BusinessException("发货单号和明细信息对不上");
            }
            if (mapList.size() != 0){
                try {
                    BeanUtils.populate(detailAndSuppInfo,mapList.get(0));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }else {
                throw new BusinessException("查询不到订单明细相关数据");
            }

            orderShipProductInfo.setPdName(detailAndSuppInfo.getPdName());
            orderShipProductInfo.setOrderDetailId(detailAndSuppInfo.getOrderDetailId());
            //更新订单明细,发货明细,分批订单明细
            modifyDetailAndSupInfo(orderShipProductInfo, detailAndSuppInfo);

            SoDeliverDetail soDeliverDetail = dealSoDeliverDetail(soDeliver, detailAndSuppInfo, orderShipProductInfo);
            soDeliverDetail.setOrderId(soDeliver.getOrderId());
            soDeliverDetail.setSubOrderCode(param.getSubOrderCode());
            soDeliverDetail.setSubOrderId(soDeliver.getSubOrderId());
            soDeliverDetail.setDelFlg(SystemConstant.DelFlg.ENABLE);
            soDeliverDetail.setVer(NumberConstant.IntDef.INT_ONE);
            deliverDetailList.add(soDeliverDetail);
        }

        return deliverDetailList;
    }

    /**
     * 根据发货明细数据更新订单明细表和订单供货明细表中数据
     * @param orderShipProductInfo
     */
    @Transactional
    public void modifyDetailAndSupInfo(OrderShipProductInfo orderShipProductInfo,OrderDetailAndSuppInfo detailAndSuppInfo){
        if(checkOrderStatus(detailAndSuppInfo)){
            throw new BusinessException("订单状态不为可发货状态");
        }
        //设置收货时间
        detailAndSuppInfo.setSendTime(DateTimeUtil.getCustomerDate());
        //更新订单明细数据
        modifyDetailInfo(orderShipProductInfo, detailAndSuppInfo);
        //更新订单供货明细数据
        modifySuppInfo(orderShipProductInfo, detailAndSuppInfo);
        //更新分批订单明细数据
        modifySubDetailInfo(orderShipProductInfo, detailAndSuppInfo);
    }
    /**
     * 发货时判断订单状态是否可以发货
     * @param detailAndSuppInfo
     * @return
     */
    public boolean checkOrderStatus(OrderDetailAndSuppInfo detailAndSuppInfo){
        return detailAndSuppInfo.getOrderStatus() != OrderCodeMasterDef.OrderStatus.WAIT_SEND
                && detailAndSuppInfo.getOrderStatus() != OrderCodeMasterDef.OrderStatus.PARTIAL_SHIPMENT
                && detailAndSuppInfo.getOrderStatus() != OrderCodeMasterDef.OrderStatus.PARTIAL_RECEIPT;
    }
    /**
     * 1.根据已经查到的明细数据和发货要求里面的数量进行判断是否满足要求
     * 2.进行更新订单明细表
     * @param orderShipProductInfo
     * @param detailAndSuppInfo
     */
    @Transactional
    public void modifyDetailInfo(OrderShipProductInfo orderShipProductInfo,OrderDetailAndSuppInfo detailAndSuppInfo){
        if(checkOrderDetailStatus(detailAndSuppInfo)){
            throw new BusinessException("订单明细状态不为可发货状态");
        }
        //发货数量
        BigDecimal sendQty = orderShipProductInfo.getSendQty();
        //明细表中可发货数量
        BigDecimal detailQty = detailAndSuppInfo.getDetailQty();

        if(sendQty.compareTo(detailQty) > NumberConstant.IntDef.INT_ZERO){
            throw new BusinessException("发货数量大于明细可发货数量");
        }

        //设置明细状态为全部发货还是部分发货
        if(detailAndSuppInfo.getDetailStatus() != OrderCodeMasterDef.OrderDetailStatus.PARTIAL_RECEIPT){
            if(sendQty.compareTo(detailQty) == NumberConstant.IntDef.INT_ZERO){
                orderShipProductInfo.setStatus(OrderCodeMasterDef.OrderDetailStatus.ALL_SHIPMENT);
            }else{
                orderShipProductInfo.setStatus(OrderCodeMasterDef.OrderDetailStatus.PARTIAL_SHIPMENT);
            }
        }else {
            orderShipProductInfo.setStatus(detailAndSuppInfo.getDetailStatus());
        }

        Filter<SoOrderDetail> filter = new Filter<>();
        filter.add("orderDetailId",BaseOperatorEnum.EQUAL,orderShipProductInfo.getOrderDetailId());
        filter.add("delFlg",BaseOperatorEnum.EQUAL,SystemConstant.DelFlg.ENABLE);
        CommonSpecification specification = new CommonSpecification(filter);
        SoOrderDetail soOrderDetail = soOrderDetailRepository.findOne(specification);

        if(soOrderDetail.getSendQty() == null){
            soOrderDetail.setSendQty(orderShipProductInfo.getSendQty());
        }else {
            soOrderDetail.setSendQty(DecimalUtil.add(soOrderDetail.getSendQty(), orderShipProductInfo.getSendQty()));
        }
        soOrderDetail.setSendTime(orderShipProductInfo.getUpdTime());
        soOrderDetail.setDetailStatus(orderShipProductInfo.getStatus());
        soOrderDetail.setUpdId(orderShipProductInfo.getUpdId());
        soOrderDetail.setUpdTime(orderShipProductInfo.getUpdTime());
        soOrderDetail.setVer(soOrderDetail.getVer() + 1);
        soOrderDetail.setOrderDetailId(orderShipProductInfo.getOrderDetailId());
        soOrderDetail.setSendTime(detailAndSuppInfo.getSendTime());
        soOrderDetailRepository.save(soOrderDetail);
        //int updateCount = soOrderDetailRepository.modifyDetailStatusAndQty(orderShipProductInfo.getSendQty(),orderShipProductInfo.getUpdTime(),orderShipProductInfo.getStatus(),orderShipProductInfo.getUpdId(),orderShipProductInfo.getUpdTime(),orderShipProductInfo.getOrderDetailId());
    }
    /**
     * 发货时判断订单明细状态是否可以发货
     * @param detailAndSuppInfo
     * @return boolean
     */
    public boolean checkOrderDetailStatus(OrderDetailAndSuppInfo detailAndSuppInfo){
        return detailAndSuppInfo.getDetailStatus() != OrderCodeMasterDef.OrderDetailStatus.WAIT_SEND
                && detailAndSuppInfo.getDetailStatus() != OrderCodeMasterDef.OrderDetailStatus.PARTIAL_SHIPMENT
                && detailAndSuppInfo.getDetailStatus() != OrderCodeMasterDef.OrderDetailStatus.PARTIAL_RECEIPT;
    }

    /**
     * 1.更新分批订单明细表
     * @param orderShipProductInfo
     * @param detailAndSuppInfo
     */
    @Transactional
    public void modifySubDetailInfo(OrderShipProductInfo orderShipProductInfo,OrderDetailAndSuppInfo detailAndSuppInfo){
        if(checkSubOrderDetailStatus(detailAndSuppInfo)){
            throw new BusinessException("订单明细状态不为可发货状态");
        }
        //发货数量
        BigDecimal sendQty = orderShipProductInfo.getSendQty();
        //分批明细表中可发货数量
        BigDecimal subDetailQty = detailAndSuppInfo.getSubDetailQty();

        if(sendQty.compareTo(subDetailQty) > NumberConstant.IntDef.INT_ZERO){
            throw new BusinessException("发货数量大于明细可发货数量");
        }

        //设置明细状态为全部发货还是部分发货
        if(detailAndSuppInfo.getSubDetailStatus() != OrderCodeMasterDef.SubOrderDetailStatus.PARTIAL_RECEIPT){
            if(sendQty.compareTo(subDetailQty) == NumberConstant.IntDef.INT_ZERO){
                orderShipProductInfo.setStatus(OrderCodeMasterDef.SubOrderDetailStatus.ALL_SHIPMENT);
            }else{
                orderShipProductInfo.setStatus(OrderCodeMasterDef.SubOrderDetailStatus.PARTIAL_SHIPMENT);
            }
        }else {
            orderShipProductInfo.setStatus(detailAndSuppInfo.getSubDetailStatus());
        }

        Filter<SoSubOrderDetail> filter = new Filter<>();
        filter.add("subOrderDetailId",BaseOperatorEnum.EQUAL,detailAndSuppInfo.getSubOrderDetailId());
        filter.add("delFlg",BaseOperatorEnum.EQUAL,SystemConstant.DelFlg.ENABLE);
        CommonSpecification specification = new CommonSpecification(filter);
        SoSubOrderDetail soSubOrderDetail = soSubOrderDetailRepository.findOne(specification);

        if(soSubOrderDetail.getSendQty() == null){
            soSubOrderDetail.setSendQty(orderShipProductInfo.getSendQty());
        }else {
            soSubOrderDetail.setSendQty(DecimalUtil.add(soSubOrderDetail.getSendQty(),orderShipProductInfo.getSendQty()));
        }
        soSubOrderDetail.setSendTime(orderShipProductInfo.getUpdTime());
        soSubOrderDetail.setDetailStatus(orderShipProductInfo.getStatus());
        soSubOrderDetail.setUpdId(orderShipProductInfo.getUpdId());
        soSubOrderDetail.setUpdTime(orderShipProductInfo.getUpdTime());
        soSubOrderDetail.setVer(soSubOrderDetail.getVer() + 1);
        soSubOrderDetail.setSubOrderDetailId(detailAndSuppInfo.getSubOrderDetailId());
        soSubOrderDetail.setSendTime(detailAndSuppInfo.getSendTime());
        soSubOrderDetailRepository.save(soSubOrderDetail);
    }
    /**
     * 发货时判断分批订单明细状态是否可以发货
     * @param detailAndSuppInfo
     * @return boolean
     */
    public boolean checkSubOrderDetailStatus(OrderDetailAndSuppInfo detailAndSuppInfo){
        return detailAndSuppInfo.getSubDetailStatus() != OrderCodeMasterDef.SubOrderDetailStatus.WAIT_SEND
                && detailAndSuppInfo.getSubDetailStatus() != OrderCodeMasterDef.SubOrderDetailStatus.PARTIAL_SHIPMENT
                && detailAndSuppInfo.getSubDetailStatus() != OrderCodeMasterDef.SubOrderDetailStatus.PARTIAL_RECEIPT;
    }

    /**
     * 1.根据已经查到的供货明细数据和发货要求里面的数量进行判断是否满足要求
     * 2.进行更新供货明细表
     * @param orderShipProductInfo
     * @param detailAndSuppInfo
     */
    @Transactional
    public void modifySuppInfo(OrderShipProductInfo orderShipProductInfo,OrderDetailAndSuppInfo detailAndSuppInfo){
        if(checkOrderSuppStatus(detailAndSuppInfo)){
            throw new BusinessException("供货明细状态不为可发货状态");
        }

        //发货数量
        BigDecimal sendQty = orderShipProductInfo.getSendQty();
        //供货明细表中可发货数量
        BigDecimal suppQty = detailAndSuppInfo.getSuppQty();

        if(sendQty.compareTo(suppQty) > NumberConstant.IntDef.INT_ZERO){
            throw new BusinessException("发货数量大于供货数量");
        }

        //设置供货明细状态为全部发货还是部分发货
        if(detailAndSuppInfo.getSuppStatus() != OrderCodeMasterDef.OrderDetailAvailabilityStatus.PARTIAL_RECEIPT){
            if(sendQty.compareTo(suppQty) == NumberConstant.IntDef.INT_ZERO){
                orderShipProductInfo.setStatus(OrderCodeMasterDef.OrderDetailAvailabilityStatus.ALL_SHIPMENT);
            }else{
                orderShipProductInfo.setStatus(OrderCodeMasterDef.OrderDetailAvailabilityStatus.PARTIAL_SHIPMENT);
            }
        }else {
            orderShipProductInfo.setStatus(detailAndSuppInfo.getSuppStatus());
        }
        Filter<SoOrderShipDetail> filter = new Filter<>();
        filter.add("orderDetailId",BaseOperatorEnum.EQUAL,orderShipProductInfo.getOrderDetailId());
        filter.add("shipDetailId",BaseOperatorEnum.EQUAL,orderShipProductInfo.getShipDetailId());
        filter.add("delFlg",BaseOperatorEnum.EQUAL,SystemConstant.DelFlg.ENABLE);
        CommonSpecification specification = new CommonSpecification(filter);
        SoOrderShipDetail soOrderShipDetail = soOrderShipDetailRepository.findOne(specification);
        if(soOrderShipDetail.getSendQty() == null){
            soOrderShipDetail.setSendQty(orderShipProductInfo.getSendQty());
        }else {
            soOrderShipDetail.setSendQty(DecimalUtil.add(soOrderShipDetail.getSendQty(), orderShipProductInfo.getSendQty()));
        }
        soOrderShipDetail.setSendTime(orderShipProductInfo.getUpdTime());
        soOrderShipDetail.setDetailStatus(orderShipProductInfo.getStatus());
        soOrderShipDetail.setUpdId(orderShipProductInfo.getUpdId());
        soOrderShipDetail.setUpdTime(orderShipProductInfo.getUpdTime());
        soOrderShipDetail.setVer(soOrderShipDetail.getVer() + 1);
        soOrderShipDetail.setOrderDetailId(orderShipProductInfo.getOrderDetailId());
        soOrderShipDetail.setShipDetailId(orderShipProductInfo.getShipDetailId());
        soOrderShipDetail.setSendTime(detailAndSuppInfo.getSendTime());
        soOrderShipDetailRepository.save(soOrderShipDetail);
//        soOrderShipDetailRepository.modifySuppStatusAndQty(orderShipProductInfo.getSendQty(),orderShipProductInfo.getUpdTime(),orderShipProductInfo.getStatus(),orderShipProductInfo.getUpdId(),orderShipProductInfo.getUpdTime(),orderShipProductInfo.getOrderDetailId(),orderShipProductInfo.getShipDetailId());
    }
    /**
     * 发货时判断订单发货明细状态是否可以发货
     * @param detailAndSuppInfo
     * @return
     */
    public boolean checkOrderSuppStatus(OrderDetailAndSuppInfo detailAndSuppInfo){
        return detailAndSuppInfo.getSuppStatus() != OrderCodeMasterDef.OrderDetailAvailabilityStatus.WAIT_SEND
                && detailAndSuppInfo.getSuppStatus() != OrderCodeMasterDef.OrderDetailAvailabilityStatus.PARTIAL_SHIPMENT
                && detailAndSuppInfo.getSuppStatus() != OrderCodeMasterDef.OrderDetailAvailabilityStatus.PARTIAL_RECEIPT;
    }
    /**
     * 处理配送数据
     * @param soDeliver
     * @param detailAndSuppInfo
     * @param orderShipProductInfo
     * @return
     */
    public SoDeliverDetail dealSoDeliverDetail(SoDeliver soDeliver,OrderDetailAndSuppInfo detailAndSuppInfo,OrderShipProductInfo orderShipProductInfo){
        SoDeliverDetail soDeliverDetail = new SoDeliverDetail();

        soDeliverDetail.setDeliverId(soDeliver.getDeliverId());
        soDeliverDetail.setDeliverCode(soDeliver.getDeliverCode());
        soDeliverDetail.setOrderId(orderShipProductInfo.getOrderId());
        soDeliverDetail.setOrderCode(orderShipProductInfo.getOrderCode());
        soDeliverDetail.setOrderDetailId(detailAndSuppInfo.getOrderDetailId());
        soDeliverDetail.setShipDetailId(detailAndSuppInfo.getShipDetailId());
        soDeliverDetail.setSupplierCode(detailAndSuppInfo.getSupplierCode());
        soDeliverDetail.setPdCode(detailAndSuppInfo.getPdCode());
        soDeliverDetail.setPdName(detailAndSuppInfo.getPdName());
        soDeliverDetail.setDeliverQty(orderShipProductInfo.getSendQty());
        soDeliverDetail.setCrtTime(DateTimeUtil.getCustomerDate());
        soDeliverDetail.setCrtId(orderShipProductInfo.getUpdId());
        soDeliverDetail.setSkuCode(orderShipProductInfo.getSkuCode());

        return soDeliverDetail;
    }


    /**
     * 修改订单状态
     *
     */
    @Transactional
    public void modifyOrderStatusByOrderId(ISO151415ModifyStatusParam param){
        // 查询分批订单是否存在
        Filter<SoSubOrder> filter = new Filter<>();
        filter.add("orderId",BaseOperatorEnum.EQUAL,param.getOrderId());
        filter.add("delFlg",BaseOperatorEnum.EQUAL,SystemConstant.DelFlg.ENABLE);
        CommonSpecification specification = new CommonSpecification(filter);
        List<SoSubOrder> list = soSubOrderRepository.findAll(specification);
        if (!CollectionUtils.isEmpty(list)){
            modifyOrderStatusByDetailStatus(param);
        }
    }
    /**
     * 根据明细状态修改订单状态
     *
     */
    @Transactional
    public void modifyOrderStatusByDetailStatus(ISO151415ModifyStatusParam param){
        String sql = SqlUtil.getSqlBySqlId("ISO151415.getModifyOrderStatusByDetailStatusParam");
        List paramList = new ArrayList();
        //list中循环查询7个相同参数
        for (int i=0;i<NumberConstant.IntDef.INT_EIGHT;i++){
            paramList.add(param.getOrderId());
        }
        List<Map<String,Object>> mapList = baseJdbc.queryForListNotCount(sql,paramList,null,true);
        ISO151415ModifyStatusBeanResult countBean = new ISO151415ModifyStatusBeanResult();
        if (mapList.size() != 0){
            try {
                BeanUtils.populate(countBean,mapList.get(0));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }else {
            throw new BusinessException("查询不到状态数量");
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
            soOrder.setVer(soOrder.getVer() + 1);
            //设置收货时间(最后一次收货时间)
            if (param.getModifyFlag().equals("receipt")){
                soOrder.setOrderReceiveTime(DateTimeUtil.getCustomerDate());
            }

            //设置发货时间(设置首次发货时间)
            if (soOrder.getOrderSendTime() == null && param.getModifyFlag().equals("deliver")){
                soOrder.setOrderSendTime(DateTimeUtil.getCustomerDate());
            }
            soOrderRepository.save(soOrder);
            orderStatusService.saveOrderStatusBySoOrder(soOrder,param.getUpdId());
        }
    }
    /**
     * 修改发货单状态
     *
     */
    @Transactional
    public void modifyShipStatus(ISO151415ModifyStatusParam param){
        Filter<SoOrderShip> filter = new Filter<>();
        filter.add("shipId",BaseOperatorEnum.EQUAL,param.getShipId());
        filter.add("delFlg",BaseOperatorEnum.EQUAL,SystemConstant.DelFlg.ENABLE);
        CommonSpecification specification = new CommonSpecification(filter);
        SoOrderShip sos = soOrderShipRepository.findOne(specification);
        if (sos != null){
            if (sos.getShipStatus() == OrderCodeMasterDef.ShipStatus.ALL_RETURN
                    || sos.getShipStatus() == OrderCodeMasterDef.ShipStatus.ALL_LATE_RECEIPT){
                // 发货单状态为【全部迟收】或【全部退货】，不修改状态
                return;
            }
        }
        String sql = SqlUtil.getSqlBySqlId("ISO151415.getModifyShipStatusParam");
        List paramList = new ArrayList();
        for (int i=0;i<6;i++){
            paramList.add(param.getShipId());
        }
        List<Map<String,Object>> mapList = baseJdbc.queryForListNotCount(sql,paramList,null,true);
        ISO151415ModifyStatusBeanResult countBean = new ISO151415ModifyStatusBeanResult();
        if (mapList.size() != 0){
            try {
                BeanUtils.populate(countBean,mapList.get(0));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }else {
            throw new BusinessException("查询不到状态数量");
        }

        Integer shipStatus = null;
        // 设置订单状态
        if (countBean != null){
            if (countBean.getAllCount() == countBean.getCancelCount()){
                // 所属供货明细状态全为【已取消】，设为【已取消】
                shipStatus = OrderCodeMasterDef.ShipStatus.CANCELED;
            } else if(countBean.getAllCount() == countBean.getCancelCount() + countBean.getAllReceiptCount()){
                // 所属供货明细状态除去【已取消】，全为【全部收货】，设为【全部收货】
                shipStatus = OrderCodeMasterDef.ShipStatus.ALL_RECEIPT;
            } else if (countBean.getAllReceiptCount() > 0 || countBean.getPartialReceiptCount() > 0 ){
                // 所属供货明细状态存在【全部收货】或【部分收货】，设为【部分收货】
                shipStatus = OrderCodeMasterDef.ShipStatus.PARTIAL_RECEIPT;
            } else if(countBean.getAllCount() == countBean.getCancelCount() + countBean.getAllShipmentCount()){
                // 所属供货明细状态除去【已取消】，全为【全部发货】，设为【全部发货】
                shipStatus = OrderCodeMasterDef.ShipStatus.ALL_SHIPMENT;
            } else if (countBean.getAllShipmentCount() > 0 || countBean.getPartialShipmentCount() > 0 ){
                // 所属明细状态存在【全部发货】或【部分发货】，设为【部分发货】
                shipStatus = OrderCodeMasterDef.ShipStatus.PARTIAL_SHIPMENT;
            } else{
                // 以上情况外，设为【待发货】
                shipStatus = OrderCodeMasterDef.ShipStatus.WAIT_SEND;
            }
        }
        param.setShipStatus(shipStatus);
        Filter<SoOrderShip> filterShip = new Filter<>();
        filterShip.add("shipId",BaseOperatorEnum.EQUAL,param.getShipId());
        filterShip.add("delFlg",BaseOperatorEnum.EQUAL,SystemConstant.DelFlg.ENABLE);
        CommonSpecification commonSpecification = new CommonSpecification(filterShip);
        SoOrderShip soOrderShip = soOrderShipRepository.findOne(commonSpecification);
        if (soOrderShip != null){
            soOrderShip.setShipStatus(param.getShipStatus());
            soOrderShip.setUpdId(param.getUpdId());
            soOrderShip.setUpdTime(DateTimeUtil.getCustomerDate());
            soOrderShip.setVer(soOrderShip.getVer() + 1);
            soOrderShipRepository.save(soOrderShip);
        }
    }

    /**
     * 更新分批订单表
     * @param param
     */
    @Transactional
    public void modifySubOrderStatus(ISO151415ModifyStatusParam param){
        Filter<SoOrderShip> filter = new Filter<SoOrderShip>();
        filter.add("delFlg",BaseOperatorEnum.EQUAL,SystemConstant.DelFlg.ENABLE);
        filter.add("shipId",BaseOperatorEnum.EQUAL,param.getShipId());
        CommonSpecification specification = new CommonSpecification(filter);
        SoOrderShip soOrderShip = soOrderShipRepository.findOne(specification);
        Long subOrderId = soOrderShip.getSubOrderId();
        String sql = SqlUtil.getSqlBySqlId("ISO151415.findSubOrderDetailStatusCount");
        List paramList = new ArrayList();
        for (int i=0;i<8;i++){
            paramList.add(subOrderId);
        }
        List<Map<String,Object>> mapList = baseJdbc.queryForListNotCount(sql,paramList,null,true);
        ISO151415ModifyStatusBeanResult countBean = new ISO151415ModifyStatusBeanResult();
        if (mapList.size() != 0){
            try {
                BeanUtils.populate(countBean,mapList.get(0));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }else {
            throw new BusinessException("查询不到状态数量");
        }
        Integer subStatus = null;
        // 设置订单状态
        if (countBean != null){
            if (countBean.getAllCount() == countBean.getCancelCount()){
                // 所属供货明细状态全为【已取消】，设为【已取消】
                subStatus = OrderCodeMasterDef.SubOrderStatus.CANCEL;
            } else if(countBean.getAllCount() == countBean.getCancelCount() + countBean.getAllReceiptCount()){
                // 所属供货明细状态除去【已取消】，全为【全部收货】，设为【全部收货】
                subStatus = OrderCodeMasterDef.SubOrderStatus.ALL_RECEIPT;
            } else if (countBean.getAllReceiptCount() > 0 || countBean.getPartialReceiptCount() > 0 ){
                // 所属供货明细状态存在【全部收货】或【部分收货】，设为【部分收货】
                subStatus = OrderCodeMasterDef.SubOrderStatus.PARTIAL_RECEIPT;
            } else if(countBean.getAllCount() == countBean.getCancelCount() + countBean.getAllShipmentCount()){
                // 所属供货明细状态除去【已取消】，全为【全部发货】，设为【全部发货】
                subStatus = OrderCodeMasterDef.SubOrderStatus.ALL_SHIPMENT;
            } else if (countBean.getAllShipmentCount() > 0 || countBean.getPartialShipmentCount() > 0 ){
                // 所属明细状态存在【全部发货】或【部分发货】，设为【部分发货】
                subStatus = OrderCodeMasterDef.SubOrderStatus.PARTIAL_SHIPMENT;
            } else{
                // 以上情况外，设为【待发货】
                subStatus = OrderCodeMasterDef.SubOrderStatus.WAIT_SEND;
            }
        }
        param.setSubOrderStatus(subStatus);
        Filter<SoSubOrder> filterSub = new Filter<>();
        filterSub.add("subOrderId",BaseOperatorEnum.EQUAL,subOrderId);
        filterSub.add("delFlg",BaseOperatorEnum.EQUAL,SystemConstant.DelFlg.ENABLE);
        CommonSpecification commonSpecification = new CommonSpecification(filterSub);
        SoSubOrder soSubOrder = soSubOrderRepository.findOne(commonSpecification);
        if (soSubOrder != null){
            soSubOrder.setSubOrderStatus(param.getSubOrderStatus());
            soSubOrder.setUpdId(param.getUpdId());
            soSubOrder.setUpdTime(DateTimeUtil.getCustomerDate());
            soSubOrder.setVer(soSubOrder.getVer() + 1);
            soSubOrderRepository.save(soSubOrder);
            subOrderStatusService.saveSubStatusBySubOrder(soSubOrder,param.getUpdId());
        }
    }

    /**
     *保存配送单数据
     * @param deliver 配送单
     * @param deliverDetailList 配送单明细
     */
    @Transactional
    public void saveDeliver(SoDeliver deliver, List<SoDeliverDetail> deliverDetailList){
        long deliverDetailMaxId;
        if (deliver != null){
            soDeliverRepository.save(deliver);
            deliverDetailMaxId = this.maxId("so_deliver_detail",deliverDetailList.size());
            for (SoDeliverDetail soDeliverDetail:deliverDetailList){
                soDeliverDetail.setDeliverDetailId(deliverDetailMaxId);
                soDeliverDetailRepository.save(soDeliverDetail);
                deliverDetailMaxId --;
            }
        }
    }

    /**
     * 调用库存出库接口
     * @param param
     * @param soOrder
     */
    public void sendOutBound(ISO151415OrderDeliverParam param,SoOrder soOrder,SoOrderShip soOrderShip){
        ISO151415RestOutBoundParam iso151415RestOutBoundParam = new ISO151415RestOutBoundParam();
        List<ISO151415OutBoundParam> invList = new ArrayList<>();
        for (OrderShipInfo orderShipInfo : param.getShipList()){
            for (OrderShipProductInfo shipProductInfo : orderShipInfo.getProductList()){
                ISO151415OutBoundParam outBoundParam = new ISO151415OutBoundParam();
                //设置调用出库接口的参数
                outBoundParam.setPlantFormId(soOrder.getSalePlatform());
                outBoundParam.setLgcsCode(soOrder.getDistrictCode());
                outBoundParam.setWarehouseCode(String.valueOf(NumberConstant.IntDef.INT_ZERO) + String.valueOf(NumberConstant.IntDef.INT_TWENTY_ONE));
                outBoundParam.setSlCode(soOrder.getSellerCode());
                if (soOrder.getOrderType()==OrderCodeMasterDef.OrderType.BUYER_SALE_ORDER || soOrder.getOrderType()==OrderCodeMasterDef.OrderType.THIRD_BUYER_SALE_ORDER){
                    outBoundParam.setSlType(String.valueOf(InventoryCodeMasterDef.SlType.ST_BUYER));
                }else {
                    outBoundParam.setSlType(String.valueOf(InventoryCodeMasterDef.SlType.ST_PLATFORM));
                }
                Filter<SoOrderShipDetail> filter = new Filter<>();
                filter.add("shipDetailId",BaseOperatorEnum.EQUAL,shipProductInfo.getShipDetailId());
                filter.add("delFlg",BaseOperatorEnum.EQUAL,SystemConstant.DelFlg.ENABLE);
                CommonSpecification commonSpecification = new CommonSpecification(filter);
                SoOrderShipDetail shipDetail = soOrderShipDetailRepository.findOne(commonSpecification);
                outBoundParam.setSupplierCode(shipDetail.getSupplierCode());
                outBoundParam.setPdCode(shipDetail.getPdCode());
                outBoundParam.setSkuCode(shipProductInfo.getSkuCode());
                outBoundParam.setOutboundQty(shipProductInfo.getSendQty());

                Filter<SoOrderDetail> detailFilter = new Filter<>();
                detailFilter.add("orderDetailId",BaseOperatorEnum.EQUAL,shipDetail.getOrderDetailId());
                detailFilter.add("delFlg",BaseOperatorEnum.EQUAL,SystemConstant.DelFlg.ENABLE);
                CommonSpecification specification = new CommonSpecification(detailFilter);
                SoOrderDetail orderDetail = soOrderDetailRepository.findOne(specification);
                BigDecimal outBoundPrice = DecimalUtil.multiply(shipProductInfo.getSendQty(),orderDetail.getPdPrice());

                outBoundParam.setOutboundPrice(outBoundPrice);
                outBoundParam.setInvOptType(String.valueOf(InventoryCodeMasterDef.OutboundType.OT_SALE));
                outBoundParam.setInventoryStatus(String.valueOf(InventoryCodeMasterDef.GoodType.GT_GOOD));
                outBoundParam.setOutboundId(param.getShipId());
                outBoundParam.setOutboundNo(soOrderShip.getShipCode());
                outBoundParam.setOutboundDetailId(shipProductInfo.getShipDetailId());
                outBoundParam.setDispatchedDate(DateTimeUtil.getCustomerDate());
                outBoundParam.setDispatchedTime(DateTimeUtil.getCustomerDate());

                invList.add(outBoundParam);
            }
        }
        iso151415RestOutBoundParam.setInvList(invList);
        iso151415RestOutBoundParam.setLoginId(param.getUpdId());
        SoRestUtil.transferOutBound(iso151415RestOutBoundParam);

    }



    @Override
    public BaseRepository getRepository() {
        return soOrderShipRepository;
    }
}
