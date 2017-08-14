package com.msk.order.service.impl;

import com.msk.common.constant.NumberConstant;
import com.msk.common.constant.SystemConstant;
import com.msk.common.constant.business.CapitalPoolConst;
import com.msk.common.constant.business.CommOrderConst;
import com.msk.common.constant.business.OrderCodeMasterDef;
import com.msk.common.data.jpa.BaseRepository;
import com.msk.common.data.jpa.condition.BaseOperatorEnum;
import com.msk.common.data.jpa.condition.CommonSpecification;
import com.msk.common.data.jpa.condition.Filter;
import com.msk.common.data.jpa.impl.BaseJdbcImpl;
import com.msk.common.exception.BusinessException;
import com.msk.common.exception.SystemException;
import com.msk.common.utils.DateTimeUtil;
import com.msk.common.utils.DecimalUtil;
import com.msk.common.utils.StringUtil;
import com.msk.order.bean.param.*;
import com.msk.order.bean.result.ISO151408RestResult;
import com.msk.order.entity.*;
import com.msk.order.repository.*;
import com.msk.order.service.*;
import com.msk.order.util.SoRestUtil;
import com.msk.order.util.SqlUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * 退货单创建接口
 */
@Service
public class ISO151408ServiceImpl extends BaseService<SoReturn, Long> implements ISO151408Service {

    @Autowired
    private BaseJdbcImpl baseJdbc;

    @Autowired
    private ReturnOrderStatusService returnOrderStatusService;

    @Autowired
    private SubOrderStatusService subOrderStatusService;

    @Autowired
    private OrderStatusService orderStatusService;

    @Autowired
    private SoOrderRepository soOrderRepository;

    @Autowired
    private SoOrderDetailRepository soOrderDetailRepository;

    @Autowired
    private SoOrderShipRepository soOrderShipRepository;

    @Autowired
    private SoOrderShipDetailRepository soOrderShipDetailRepository;

    @Autowired
    private SoSubOrderRepository soSubOrderRepository;

    @Autowired
    private SoSubOrderDetailRepository soSubOrderDetailRepository;

    @Autowired
    private SoReturnRepository returnRepository;

    @Autowired
    private SoReturnDetailRepository soReturnDetailRepository;

    @Autowired
    private AsyncPostService asyncPostService;

    @Override
    public BaseRepository getRepository() {
        return this.returnRepository;
    }

    /**
     * 创建退货单
     *
     * @param rsParam
     * @return
     */
    @Override
    @Transactional
    public ISO151408RestResult createReturnOrder(ISO151408RestParam rsParam) {
        ISO151408RestResult iso151408RsResult = new ISO151408RestResult();
        Integer returnMode = rsParam.getReturnMode();
        Filter<SoOrder> filter = new Filter<>();
        filter.add("orderId", BaseOperatorEnum.EQUAL, rsParam.getOrderId());
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification<SoOrder> spc = new CommonSpecification<>(filter);
        SoOrder soOrder = soOrderRepository.findOne(spc);
        SoReturn soReturn = null;
        if (null == soOrder) {
            throw new BusinessException("订单不存在，不能创建退货单");
        } else if (soOrder.getOrderStatus() != OrderCodeMasterDef.OrderStatus.ALL_RECEIPT && returnMode == OrderCodeMasterDef.ReturnMode.ALL) {
            throw new BusinessException("非全部收货，不能全部退货");
        } else if (!(soOrder.getOrderStatus() == OrderCodeMasterDef.OrderStatus.PARTIAL_SHIPMENT || soOrder.getOrderStatus() == OrderCodeMasterDef.OrderStatus.ALL_SHIPMENT) && returnMode == OrderCodeMasterDef.ReturnMode.APART) {
            throw new BusinessException("非收货状态，不能部分退货");
        } else {
            if (returnMode == OrderCodeMasterDef.ReturnMode.ALL) {// 全退
                soReturn = this.createAllReturnOrder(rsParam, soOrder);
                this.setAllReturnStatus(soOrder, rsParam);//设置所有退货状态
                this.setSoOrderDetailReturnQtyByAllReturn(soOrder, rsParam);// 设置订单明细的退货量
                this.setSubSoOrderDetailReturnQtyByAllReturn(soOrder, rsParam);// 设置分批订单明细的退货量
            } else if ((returnMode == OrderCodeMasterDef.ReturnMode.APART)) {// 部分退货
                soReturn = this.createPartReturnOrder(rsParam, soOrder);
                if (soOrder.getOrderStatus() == OrderCodeMasterDef.OrderStatus.ALL_RECEIPT) {  //  如果全部收货后 部分退货 订单状态修改为 部分收货
                    soOrder.setOrderStatus(OrderCodeMasterDef.OrderStatus.PARTIAL_RECEIPT);// 设置部分收货状态
                    this.orderStatusService.saveOrderStatusBySoOrder(soOrder,rsParam.getUpdId());//更新履历
                }
            } else {
                throw new BusinessException("ReturnMode值 不对！");
            }
            this.createSoReturnDetail(soOrder, soReturn, rsParam.getShipList(), rsParam);
        }
        this.setOrderReturnFlg(soOrder, rsParam);// 设置 订单退货标志
        iso151408RsResult.setReturnId(soReturn.getReturnId());
        iso151408RsResult.setReturnCode(soReturn.getReturnCode());
        iso151408RsResult.setCrtTime(soReturn.getCrtTime());
        iso151408RsResult.setReturnStatus(soReturn.getReturnStatus());
        iso151408RsResult.setVer(soReturn.getVer());

        //this.sendCpRunning(rsParam, soReturn, soOrder);//调用资金池接口 字符明细

        this.sendCpTransaction(rsParam, soReturn, soOrder);// 调用资金池 交易接口
        return iso151408RsResult;
    }

    /**
     * 无论是部分退货还是全部退货 都设置 returnFlg
     *
     * @param soOrder
     * @param rsParam
     */
    public void setOrderReturnFlg(SoOrder soOrder, ISO151408RestParam rsParam) {
        soOrder.setReturnFlg(StringUtil.formatNum(NumberConstant.IntDef.INT_ONE));
        soOrder.setUpdTime(DateTimeUtil.getCustomerDate());
        soOrder.setUpdId(rsParam.getUpdId());
        soOrder.setVer(NumberConstant.IntDef.INT_ONE + soOrder.getVer());
        this.soOrderRepository.save(soOrder);
    }


    /**
     * 全退
     *
     * @param rsParam
     */
    public SoReturn createAllReturnOrder(ISO151408RestParam rsParam, SoOrder soOrder) {
        Filter<SoReturn> filter = new Filter<>();
        filter.add("orderId", BaseOperatorEnum.EQUAL, rsParam.getOrderId());
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        filter.add("returnType", BaseOperatorEnum.EQUAL, OrderCodeMasterDef.ReturnType.RETURNED.toString());
        CommonSpecification<SoReturn> spc = new CommonSpecification<>(filter);
        List<SoReturn> soReturnList = returnRepository.findAll(spc);
        if (!CollectionUtils.isEmpty(soReturnList)) {
            throw new BusinessException("该订单ID已有退货数据，不可全部退货！");
        }
        return this.createSoReturn(soOrder, rsParam);
    }

    /**
     * 部分退货
     *
     * @param rsParam
     */
    public SoReturn createPartReturnOrder(ISO151408RestParam rsParam, SoOrder soOrder) {
        return this.createSoReturn(soOrder, rsParam);

    }

    /**
     * 保存退货单主表
     *
     * @param soOrder
     * @param iso151408RsParam
     * @author zhangqiang1
     */
    public SoReturn createSoReturn(SoOrder soOrder, ISO151408RestParam iso151408RsParam) {
        SoReturn soReturn = new SoReturn(); // 插入退货单主表
        Long returnId = this.maxId("so_return");
        soReturn.setReturnId(returnId);
        soReturn.setIsInvoice(soOrder.getNeedInvoice());
        soReturn.setReturnCode(this.getReturnOrderCode(returnId));
        soReturn.setOrderId(iso151408RsParam.getOrderId());
        soReturn.setOrderCode(soOrder.getOrderCode());
        soReturn.setBuyerCode(soOrder.getBuyerCode());
        soReturn.setBuyerName(soOrder.getBuyerName());
        soReturn.setSellerCode(soOrder.getSellerCode());
        soReturn.setDelFlg(SystemConstant.DelFlg.ENABLE);
        soReturn.setSellerName(soOrder.getSellerName());
        soReturn.setDistrictCode(soOrder.getDistrictCode());
        soReturn.setReturnSource(soOrder.getOrderSource());
        soReturn.setReturnType(OrderCodeMasterDef.ReturnType.RETURNED);// 退货单类型 3-退货
        String returnMode = StringUtil.formatNum(iso151408RsParam.getReturnMode());
        soReturn.setReturnMode(returnMode);
        soReturn.setReturnReason(iso151408RsParam.getReturnReasonID());
        soReturn.setApplyMan(iso151408RsParam.getApplyMan());
        soReturn.setApplyTime(iso151408RsParam.getApplyTime());
        soReturn.setApplyRemark(iso151408RsParam.getApplyRemark());
        soReturn.setReceiverName(iso151408RsParam.getReceiverName());
        soReturn.setReceiverTel(iso151408RsParam.getReceiverTel());
        soReturn.setReturnReason(iso151408RsParam.getReturnReasonID());
        soReturn.setIsPaid(StringUtil.toSafeString(iso151408RsParam.getIsPaid()));
        soReturn.setImage1(iso151408RsParam.getImage1());
        soReturn.setImage2(iso151408RsParam.getImage2());
        soReturn.setImage3(iso151408RsParam.getImage3());
        soReturn.setImage4(iso151408RsParam.getImage4());
        soReturn.setImage5(iso151408RsParam.getImage5());
        soReturn.setCrtId(iso151408RsParam.getUpdId());
        soReturn.setCrtTime(DateTimeUtil.getCustomerDate());
        soReturn.setVer(NumberConstant.IntDef.INT_ONE);
        BigDecimal returnAmountTotal = this.getReturnTotalAmount(iso151408RsParam, soOrder); // 计算退货总金额
        soReturn.setReturnAmount(returnAmountTotal);
        soReturn.setReturnStatus(OrderCodeMasterDef.ReturnOrderStatus.WAIT_SALVE);
        SoReturn savedSoReturn = returnRepository.save(soReturn);
        this.saveReturnStatus(soReturn, iso151408RsParam.getUpdId());// 保存订单状态
        return savedSoReturn;
    }


    /**
     * 获取returnOrderCode
     *
     * @return
     */
    public String getReturnOrderCode(Long returnId) {
        String yyMMdd = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YYYYMMDD, DateTimeUtil.getCustomerDate());
        Calendar getOrderTime = Calendar.getInstance(); // 标准时间单元编码
        int orderHour = getOrderTime.get(Calendar.HOUR_OF_DAY);
        int orderMin = getOrderTime.get(Calendar.MINUTE);
        if (orderMin > NumberConstant.IntDef.INT_THIRTY) {
            orderHour = orderHour * NumberConstant.IntDef.INT_TWO + NumberConstant.IntDef.INT_TWO;
        } else {
            orderHour = orderHour * NumberConstant.IntDef.INT_TWO + NumberConstant.IntDef.INT_ONE;
        }
        String returnOrderCode = yyMMdd.substring(NumberConstant.IntDef.INT_TWO, NumberConstant.IntDef.INT_EIGHT)
                + String.valueOf(orderHour) + "_" + returnId;
        return returnOrderCode;
    }

    /**
     * 保存退货单状态
     *
     * @param soReturn
     */
    public void saveReturnStatus(SoReturn soReturn, String updateId) {// 先删除之前 添加新的
        returnOrderStatusService.saveReturnStatusByReturnOrder(soReturn, updateId);

    }

    /**
     * 全部退货  设置各个表的状态
     *
     * @param soOrder
     * @param rsParam
     */
    public void setAllReturnStatus(SoOrder soOrder, ISO151408RestParam rsParam) {
        this.updateOrder(soOrder.getOrderId(), OrderCodeMasterDef.OrderStatus.RETURN_ALL, rsParam); // order
        this.updateOrderStatus(soOrder, OrderCodeMasterDef.OrderStatus.RETURN_ALL, rsParam); // orderStatus
        this.updateOrderDetail(soOrder, rsParam);    //orderDetail
        this.updateSubOrder(soOrder, rsParam);//subOrder
        this.updateOrderShipDetailStatusByAllReturn(soOrder, rsParam);//orderShip
        this.updateOrderShipStatusByAllReturn(soOrder, rsParam);//orderShipDetail
        this.subOrderStatusService.saveSubOrderStatusListByOrderId(soOrder.getOrderId(), OrderCodeMasterDef.SubOrderStatus.ALL_RETURN, rsParam.getUpdId());//更新subOrderStatus履历
        this.updateSubOrderDetail(soOrder, OrderCodeMasterDef.SubOrderDetailStatus.ALL_RETURN, rsParam); //subOrderDetail
    }

    /*
        /**
         * 修改订单明细退货数量
         *
         * @param orderDetailId
         * @param productReturnQty
         * @param rsParam
         */
    public void updateOrderDetailReturnQty(Long orderDetailId, BigDecimal productReturnQty, ISO151408RestParam rsParam) {
        Filter<SoOrderDetail> filter = new Filter<>();
        filter.add("orderDetailId", BaseOperatorEnum.EQUAL, orderDetailId);
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification<SoOrderDetail> spec = new CommonSpecification<>(filter);
        SoOrderDetail orderDetail = this.soOrderDetailRepository.findOne(spec);
        BigDecimal orderQty = orderDetail.getOrderQty();// 收货量
        BigDecimal returnedQty = orderDetail.getReturnQty();// 已退量
        BigDecimal rejectionQty = orderDetail.getRejectionQty();// 拒收量
        BigDecimal cancelQty = orderDetail.getCancelQty();// 取消量
        BigDecimal canReturnQty = DecimalUtil.subtract(orderQty, returnedQty);
        canReturnQty = DecimalUtil.subtract(canReturnQty, rejectionQty);
        canReturnQty = DecimalUtil.subtract(canReturnQty, cancelQty);
        Integer returnMode = rsParam.getReturnMode().intValue();
        if (returnMode == OrderCodeMasterDef.ReturnMode.APART) {// 部分退货
            if (DecimalUtil.isLess(canReturnQty, productReturnQty)) {
                throw new BusinessException("在订单明细中退货数量超过可退货数量（订单量-已退货数量-拒收数量-取消数量）！");
            }
            if (DecimalUtil.isEquals(canReturnQty, productReturnQty)) {// 修改订单明细状态
                this.updateOrderDetailStatusByAllReturn(orderDetail.getOrderDetailId(), rsParam);
            }
        }
        BigDecimal returnQty = orderDetail.getReturnQty();// 已经退过的退货数量
        returnQty = DecimalUtil.add(returnQty, productReturnQty);// 现在的推货量=已经退过的退货数量+productReturnQty
        orderDetail.setReturnQty(returnQty);
        orderDetail.setVer(NumberConstant.IntDef.INT_ONE + orderDetail.getVer());
        orderDetail.setUpdId(rsParam.getCrtId());
        orderDetail.setUpdTime(DateTimeUtil.getCustomerDate());
        this.soOrderDetailRepository.save(orderDetail);
        if (returnMode == OrderCodeMasterDef.ReturnMode.APART) {// 部分退货
            if (this.checkOrderDetailIsAllReturn(orderDetail.getOrderId(), rsParam)) {
                this.updateOrder(orderDetail.getOrderId(), OrderCodeMasterDef.OrderStatus.RETURN_ALL, rsParam); // order
            }
        }
    }


    /**
     * 修改分批订单明细退货数量
     *
     * @param subOrderDetailId
     * @param productReturnQty
     * @param rsParam
     */
    public void updateSubOrderDetailReturnQty(Long subOrderDetailId, BigDecimal productReturnQty, ISO151408RestParam rsParam) {
        Filter<SoSubOrderDetail> filter = new Filter<>();
        filter.add("subOrderDetailId", BaseOperatorEnum.EQUAL, subOrderDetailId);
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        Integer returnMode = rsParam.getReturnMode().intValue();
        CommonSpecification<SoSubOrderDetail> spec = new CommonSpecification<>(filter);
        SoSubOrderDetail subOrderDetail = this.soSubOrderDetailRepository.findOne(spec);
        if (subOrderDetail != null) {
            BigDecimal returnedQty = subOrderDetail.getReturnQty();// 已经退过的退货数量
            BigDecimal orderQty = subOrderDetail.getOrderQty();//收货量
            BigDecimal rejectionQty = subOrderDetail.getRejectionQty();// 拒绝量
            BigDecimal cancelQty = subOrderDetail.getCancelQty();//取消量
            subOrderDetail.setVer(NumberConstant.IntDef.INT_ONE + subOrderDetail.getVer());
            subOrderDetail.setUpdId(rsParam.getCrtId());
            subOrderDetail.setUpdTime(DateTimeUtil.getCustomerDate());
            if (returnMode == OrderCodeMasterDef.ReturnMode.APART) {//  部分退货
                BigDecimal canReturnQty = DecimalUtil.subtract(orderQty, rejectionQty);
                canReturnQty = DecimalUtil.subtract(canReturnQty, cancelQty);
                canReturnQty = DecimalUtil.subtract(canReturnQty, returnedQty);
                if (DecimalUtil.isLess(canReturnQty, productReturnQty)) {
                    throw new BusinessException("在分批订单明细中退货数量超过收货数量（订单量-已退货数量-拒收数量-取消数量）！");
                }
                if (DecimalUtil.isEquals(canReturnQty, productReturnQty)) {
                    subOrderDetail.setDetailStatus(OrderCodeMasterDef.SubOrderDetailStatus.ALL_RETURN);
                }
            }
            subOrderDetail.setReturnQty(DecimalUtil.add(productReturnQty, returnedQty));
            this.soSubOrderDetailRepository.save(subOrderDetail);
        }
        if (returnMode == OrderCodeMasterDef.ReturnMode.APART) {//  部分退货
            if (this.checkSubOrderDetailStatusIsAllReturn(subOrderDetail.getSubOrderId())) {
                this.updateSubOrderStatusByAllReturn(subOrderDetail.getSubOrderId(), rsParam);// 全退状态
            }
        }
    }

    /**
     * 获取退货金额
     *
     * @return
     */
    public List<ISO151408RestReturnAmountParam> getReturnAmountList(Long orderId) {
        List<ISO151408RestReturnAmountParam> list = new ArrayList<>();
        String sql = SqlUtil.getSqlBySqlId("ISO151408.Search");
        List<Map<String, Object>> mapList = this.getMaps(sql, orderId);
        for (Map<String, Object> map : mapList) {
            ISO151408RestReturnAmountParam returnAmountRsParam = new ISO151408RestReturnAmountParam();
            try {
                BeanUtils.populate(returnAmountRsParam, map);
                list.add(returnAmountRsParam);
            } catch (Exception e) {
                e.printStackTrace();
                throw new BusinessException(e.getMessage());
            }
        }
        return list;
    }

    /**
     * 根据 部分退货或全部退货 生成退货明细
     *
     * @param soOrderShipDetail
     * @param returnDetailId
     * @param soReturn
     * @param crtId
     * @param product
     * @return
     */
    public SoReturnDetail getSoReturnDetail(SoOrderShipDetail soOrderShipDetail, Long returnDetailId, SoReturn soReturn,
                                            String crtId, ISO151408RestProductListParam product) {
        Long returnId = soReturn.getReturnId();
        String returnCode = soReturn.getReturnCode();
        Filter<SoOrderDetail> filter = new Filter<>();
        filter.add("orderDetailId", BaseOperatorEnum.EQUAL, soOrderShipDetail.getOrderDetailId());
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification spec = new CommonSpecification(filter);
        SoOrderDetail soOrderDetail = soOrderDetailRepository.findOne(spec);
        SoReturnDetail soReturnDetail = new SoReturnDetail();
        soReturnDetail.setDetailId(returnDetailId);
        soReturnDetail.setShipDetailId(soOrderShipDetail.getShipDetailId());
        soReturnDetail.setShipId(soOrderShipDetail.getShipId());
        soReturnDetail.setPdGradeName(soOrderDetail.getPdGradeName());
        soReturnDetail.setPdGradeCode(soOrderDetail.getPdGradeCode());
        soReturnDetail.setPdLevel(soOrderDetail.getPdLevel());
        soReturnDetail.setPdName(soOrderDetail.getPdName());
        soReturnDetail.setPdCode(soOrderDetail.getPdCode());
        soReturnDetail.setVolume(soOrderDetail.getVolume());
        soReturnDetail.setPackingVolume(soOrderDetail.getPackingVolume());
        soReturnDetail.setWeight(soOrderDetail.getWeight());
        soReturnDetail.setUnit(soOrderDetail.getUnit());
        soReturnDetail.setNormsName(soOrderDetail.getNormsName());
        soReturnDetail.setNormsCode(soOrderDetail.getNormsCode());
        soReturnDetail.setManufactureName(soOrderDetail.getMachiningName());
        soReturnDetail.setManufactureCode(soOrderDetail.getMachiningCode());
        soReturnDetail.setFeatureName(soOrderDetail.getFeatureName());
        soReturnDetail.setFeatureCode(soOrderDetail.getFeatureCode());
        soReturnDetail.setClassesName(soOrderDetail.getClassesName());
        soReturnDetail.setClassesCode(soOrderDetail.getClassesCode());
        soReturnDetail.setBreedName(soOrderDetail.getBreedName());
        soReturnDetail.setBreedCode(soOrderDetail.getBreedCode());
        soReturnDetail.setDelFlg(SystemConstant.DelFlg.ENABLE);
        soReturnDetail.setSkuCode(soOrderShipDetail.getSkuCode());
        soReturnDetail.setSupplierName(soOrderShipDetail.getSupplierName());
        soReturnDetail.setSupplierCode(soOrderShipDetail.getSupplierCode());
        soReturnDetail.setShipDetailId(soOrderShipDetail.getShipDetailId());
        soReturnDetail.setReturnId(returnId);
        soReturnDetail.setInboundBatch(returnCode + returnId.toString());// 入库批次
        soReturnDetail.setShipId(soOrderShipDetail.getShipId());
        soReturnDetail.setCrtId(crtId);
        soReturnDetail.setCrtTime(DateTimeUtil.getCustomerDate());
        soReturnDetail.setVer(NumberConstant.IntDef.INT_ONE);
        String returnMode = soReturn.getReturnMode();
        if (StringUtil.formatNum(OrderCodeMasterDef.ReturnMode.ALL).equals(returnMode)) {// 全退
            soReturnDetail.setReturnReason(soReturn.getReturnReason());
            soReturnDetail.setReturnQty(soOrderShipDetail.getReceiveQty());
        } else if (StringUtil.formatNum(OrderCodeMasterDef.ReturnMode.APART).equals(returnMode)) {// 部分退
            soReturnDetail.setReturnQty(product.getReturnQty());
            soReturnDetail.setReturnReason(product.getDetailReasonID());
        }
        soReturnDetail.setDetailStatus(OrderCodeMasterDef.ReturnOrderStatus.WAIT_SALVE);
        return soReturnDetail;
    }

    /**
     * 修改退货数量
     *
     * @param shipDetail
     * @param returnQty
     * @param rsParam
     */
    public void updateSoOrderShipDetailReturnQty(SoOrderShipDetail shipDetail, BigDecimal returnQty, ISO151408RestParam rsParam) {
        BigDecimal returnedQty = shipDetail.getReturnQty();// 已经退过货的
        Integer returnMode = rsParam.getReturnMode();
        shipDetail.setVer(NumberConstant.IntDef.INT_ONE + shipDetail.getVer());
        shipDetail.setUpdId(rsParam.getUpdId());
        shipDetail.setUpdTime(DateTimeUtil.getCustomerDate());
        if (returnMode == OrderCodeMasterDef.ReturnMode.ALL) {
            shipDetail.setDetailStatus(OrderCodeMasterDef.OrderDetailAvailabilityStatus.ALL_RETURN);
        } else if (returnMode == OrderCodeMasterDef.ReturnMode.APART) {
            BigDecimal receiveQty = shipDetail.getReceiveQty();// 收货
            BigDecimal rejectionQty = shipDetail.getRejectionQty();// 收货
            BigDecimal canReturnQty = DecimalUtil.subtract(receiveQty, returnedQty);
            canReturnQty = DecimalUtil.subtract(canReturnQty, rejectionQty);
            if (DecimalUtil.isEquals(canReturnQty, returnQty)) {// 当退货数量等于收货数量
                shipDetail.setDetailStatus(OrderCodeMasterDef.OrderDetailAvailabilityStatus.ALL_RETURN);
            }
        }
        shipDetail.setReturnQty(DecimalUtil.add(returnedQty, returnQty));
        this.soOrderShipDetailRepository.save(shipDetail);
        if (returnMode == OrderCodeMasterDef.ReturnMode.APART) {//  部分发货检测  是否是全部收货
            if (this.checkOrderShipStatusIsAllReturn(shipDetail.getShipId())) {
                this.updateOrderShipStatus(shipDetail.getShipId(), rsParam);
            }
        }

    }

    /**
     * 判断ship 下shipDetail是否都全部退货
     *
     * @param shipId
     * @return
     */
    public boolean checkOrderShipStatusIsAllReturn(Long shipId) {
        boolean flag = false;
        Filter<SoOrderShipDetail> filter = new Filter<>();
        filter.add("shipId", BaseOperatorEnum.EQUAL, shipId);
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification<SoOrderShipDetail> spec = new CommonSpecification<>(filter);
        List<SoOrderShipDetail> orderShipDetailList = this.soOrderShipDetailRepository.findAll(spec);
        int i = NumberConstant.IntDef.INT_ONE;
        for (SoOrderShipDetail orderShipDetail : orderShipDetailList) {
            if (orderShipDetail.getDetailStatus().equals(OrderCodeMasterDef.OrderDetailAvailabilityStatus.ALL_RETURN)) {
                i = i * NumberConstant.IntDef.INT_ONE;
            } else {
                i = i * NumberConstant.IntDef.INT_ZERO;
            }
        }
        if (i == NumberConstant.IntDef.INT_ONE) {
            flag = true;
        }
        return flag;
    }

    /**
     * 判断 subOrder  下的subOrderDetail  是否全部退货
     *
     * @param subOrderId
     * @return
     */
    public boolean checkSubOrderDetailStatusIsAllReturn(Long subOrderId) {
        boolean flag = false;
        Filter<SoSubOrderDetail> filter = new Filter<>();
        filter.add("subOrderId", BaseOperatorEnum.EQUAL, subOrderId);
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification<SoSubOrderDetail> spec = new CommonSpecification<>(filter);
        List<SoSubOrderDetail> soSubOrderDetailList = this.soSubOrderDetailRepository.findAll(spec);
        int i = NumberConstant.IntDef.INT_ONE;
        for (SoSubOrderDetail subOrderDetail : soSubOrderDetailList) {
            if (subOrderDetail.getDetailStatus().equals(OrderCodeMasterDef.SubOrderDetailStatus.ALL_RETURN)) {
                i = i * NumberConstant.IntDef.INT_ONE;
            } else {
                i = i * NumberConstant.IntDef.INT_ZERO;
            }
        }
        if (i == NumberConstant.IntDef.INT_ONE) {
            flag = true;
        }
        return flag;
    }

    /**
     * 批量保存 退货明细
     *
     * @param soReturnDetailList
     */

    public void saveReturnDetails(List<SoReturnDetail> soReturnDetailList) {
        for (SoReturnDetail returnDetail : soReturnDetailList) {
            this.soReturnDetailRepository.save(returnDetail);
        }
    }

    /**
     * 全部退货  收货量就是退货量
     *
     * @param soOrder
     * @param rsParam
     */
    public void setSoOrderDetailReturnQtyByAllReturn(SoOrder soOrder, ISO151408RestParam rsParam) {
        List<SoOrderDetail> soOrderDetailList = soOrder.getSoOrderDetailList();
        for (SoOrderDetail soOrderDetail : soOrderDetailList) {
            BigDecimal returnQty = soOrderDetail.getReceiveQty();
            soOrderDetail.setReturnQty(returnQty);
            soOrderDetail.setUpdId(rsParam.getUpdId());
            soOrderDetail.setUpdTime(DateTimeUtil.getCustomerDate());
            soOrderDetail.setDetailStatus(OrderCodeMasterDef.OrderDetailStatus.ALL_RETURN);
            soOrderDetail.setVer(soOrderDetail.getVer() + NumberConstant.IntDef.INT_ONE);
            soOrderDetailRepository.save(soOrderDetail);
        }
    }


    /**
     * 全部退货  收货量就是退货量
     *
     * @param soOrder
     * @param rsParam
     */
    public void setSubSoOrderDetailReturnQtyByAllReturn(SoOrder soOrder, ISO151408RestParam rsParam) {
        List<SoSubOrderDetail> subOrderDetailList = soOrder.getSoSubOrderDetailList();
        for (SoSubOrderDetail subOrderDetail : subOrderDetailList) {
            BigDecimal returnQty = subOrderDetail.getReceiveQty();
            subOrderDetail.setReturnQty(returnQty);
            subOrderDetail.setUpdId(rsParam.getUpdId());
            subOrderDetail.setUpdTime(DateTimeUtil.getCustomerDate());
            subOrderDetail.setVer(subOrderDetail.getVer() + NumberConstant.IntDef.INT_ONE);
            this.soSubOrderDetailRepository.save(subOrderDetail);
        }
    }

    /**
     * 修改订单明细
     *
     * @param rsParam
     */
    public void updateOrderDetail(SoOrder order, ISO151408RestParam rsParam) {
        List<SoOrderDetail> soOrderDetailList = order.getSoOrderDetailList();
        for (SoOrderDetail soOrderDetail : soOrderDetailList) {
            this.updateSubOrderStatusByAllReturn(soOrderDetail.getOrderDetailId(), rsParam);
        }
    }


    /**
     * @param orderDetailId
     * @param rsParam
     */
    public void updateOrderDetailStatusByAllReturn(Long orderDetailId, ISO151408RestParam rsParam) {
        Filter<SoOrderDetail> filter = new Filter<>();
        filter.add("orderDetailId", BaseOperatorEnum.EQUAL, orderDetailId);
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification<SoOrderDetail> spec = new CommonSpecification<>(filter);
        SoOrderDetail soOrderDetail = this.soOrderDetailRepository.findOne(spec);
        soOrderDetail.setVer(NumberConstant.IntDef.INT_ONE + soOrderDetail.getVer());
        soOrderDetail.setDetailStatus(OrderCodeMasterDef.OrderDetailStatus.ALL_RETURN);
        soOrderDetail.setUpdTime(DateTimeUtil.getCustomerDate());
        soOrderDetail.setUpdId(rsParam.getUpdId());
        this.soOrderDetailRepository.save(soOrderDetail);
    }


    /**
     * 通过sql 查询退货金额
     *
     * @param sql
     * @return
     */
    public List<Map<String, Object>> getMaps(String sql, Long orderId) {
        List<Long> parameters = new ArrayList<>();
        sql += "  AND soda.ORDER_ID =   ?" + parameters.size();
        parameters.add(orderId);
        List<Map<String, Object>> mapList = baseJdbc.queryForListNotCount(sql, parameters, null, true);
        return mapList;
    }

    /**
     * 创建退货明细
     *
     * @param soReturn
     * @param param
     */
    public void createSoReturnDetail(SoOrder soOrder, SoReturn soReturn, List<ISO151408RestShipListParam> param, ISO151408RestParam rsParam) {
        String crtId = rsParam.getUpdId();
        String returnMode = soReturn.getReturnMode();
        List<SoReturnDetail> returnDetailList = new ArrayList<>();
        if (StringUtil.formatNum(OrderCodeMasterDef.ReturnMode.ALL).equals(returnMode)) {// 全退
            List<SoOrderShipDetail> shipDetailList = soOrder.getSoOrderShipDetailList();
            Integer returnDetailLength = shipDetailList.size();
            Long maxId = this.maxId("so_return_detail", returnDetailLength);
            Long begin = maxId - returnDetailLength;
            for (SoOrderShipDetail soOrderShipDetail : shipDetailList) {
                SoReturnDetail soReturnDetail = this.getSoReturnDetail(soOrderShipDetail, begin, soReturn, crtId, null);
                returnDetailList.add(soReturnDetail);
                begin++;
            }
            this.saveReturnDetails(returnDetailList);
        } else {// 部分退
            for (ISO151408RestShipListParam ship : param) {
                Long shipId = ship.getShipId();
                List<ISO151408RestProductListParam> productListParams = ship.getProductList();
                for (ISO151408RestProductListParam product : productListParams) {
                    Integer returnDetailLength = productListParams.size();
                    Long maxId = this.maxId("so_return_detail", returnDetailLength);
                    Long begin = maxId - returnDetailLength;
                    SoOrderShipDetail orderShipDetail = null;
                    Filter<SoOrderShipDetail> filter = new Filter<>();
                    filter.add("shipId", BaseOperatorEnum.EQUAL, shipId);//shipId
                    filter.add("shipDetailId", BaseOperatorEnum.EQUAL, product.getShipDetailId());
                    filter.add("supplierCode", BaseOperatorEnum.EQUAL, product.getSupplierCode());
                    filter.add("pdCode", BaseOperatorEnum.EQUAL, product.getPdCode());
                    filter.add("skuCode", BaseOperatorEnum.EQUAL, product.getSkuCode());
                    filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
                    CommonSpecification spec = new CommonSpecification(filter);
                    orderShipDetail = this.soOrderShipDetailRepository.findOne(spec);
                    if (orderShipDetail == null) {
                        throw new BusinessException("输入的参数找不到对应的明细，参数为 " + "shipId:" + shipId + " ,shipDetailId :" + product.getShipDetailId() + " supplierCode:" + product.getSupplierCode() + "pdCode" + product.getPdCode() + " skuCode :" + product.getSkuCode());
                    }
                    BigDecimal receiveQty = orderShipDetail.getReceiveQty();// 收货
                    BigDecimal returnedQty = orderShipDetail.getReturnQty();//已退的
                    BigDecimal canReturnQty = DecimalUtil.subtract(receiveQty, returnedQty);// 可退的
                    BigDecimal returnQty = product.getReturnQty();// 将要退的
                    if (DecimalUtil.isLess(canReturnQty, returnQty)) {
                        throw new BusinessException("退货数量超过可退货数量（收货数量-已退货数量）！");
                    }
                    SoReturnDetail soReturnDetail = this.getSoReturnDetail(orderShipDetail, begin, soReturn, crtId, product);
                    this.updateSoOrderShipDetailReturnQty(orderShipDetail, returnQty, rsParam);//shipOrderDetail  修改退货数量   和退货状态
                    this.updateSubOrderDetailReturnQty(orderShipDetail.getSubOrderDetailId(), returnQty, rsParam);//soSubOrderDetail  修改退货数量   和退货状态
                    this.updateOrderDetailReturnQty(orderShipDetail.getOrderDetailId(), returnQty, rsParam);// OrderDetail  修改退货数量   和退货状态
                    returnDetailList.add(soReturnDetail);
                    begin++;
                }
                this.saveReturnDetails(returnDetailList);
            }
        }
    }

    /**
     * 判断分批退货量累加 是否达到订单量-拒收量-取消量-已经退货量
     * 达到就是整单取消
     *
     * @param orderId
     * @return
     */
    public boolean checkOrderDetailIsAllReturn(Long orderId, ISO151408RestParam rsParam) {
        Filter<SoOrder> filter = new Filter<>();
        filter.add("orderId", BaseOperatorEnum.EQUAL, orderId);
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification<SoOrder> spec = new CommonSpecification<>(filter);
        SoOrder order = this.soOrderRepository.findOne(spec);
        boolean result = false;
        int i = NumberConstant.IntDef.INT_ZERO;
        List<SoOrderDetail> soOrderDetailList = order.getSoOrderDetailList();
        if (!CollectionUtils.isEmpty(soOrderDetailList)) {
            i = NumberConstant.IntDef.INT_ONE;
            for (SoOrderDetail soOrderDetail : soOrderDetailList) {
                if (soOrderDetail.getDetailStatus().equals(OrderCodeMasterDef.OrderDetailStatus.ALL_RETURN)) {
                    this.updateOrderDetail(order, rsParam);    //orderDetail
                    i = i * NumberConstant.IntDef.INT_ONE;
                } else {
                    i = i * NumberConstant.IntDef.INT_ZERO;
                }
            }
        }
        if (i == NumberConstant.IntDef.INT_ONE) {
            result = true;
        }
        return result;
    }

    /**
     * 根据 部分退货 或全部退货 获取退款总额
     *
     * @param iso151408RsParam
     * @param soOrder
     * @return
     */
    public BigDecimal getReturnTotalAmount(ISO151408RestParam iso151408RsParam, SoOrder soOrder) {
        BigDecimal returnAmountTotal = new BigDecimal(NumberConstant.IntDef.INT_ZERO);
        if (iso151408RsParam.getReturnMode() == OrderCodeMasterDef.ReturnMode.ALL) {// 全部退货
            List<ISO151408RestReturnAmountParam> iso151408Beans = this.getReturnAmountList(soOrder.getOrderId());
            for (ISO151408RestReturnAmountParam iso151408Bean : iso151408Beans) {
                returnAmountTotal = DecimalUtil.add(returnAmountTotal, iso151408Bean.getReturnAmount()); // 全部退货计算退货总金额
            }
        } else { // 部分退货计算退货总金额
            for (ISO151408RestShipListParam shipParam : iso151408RsParam.getShipList()) {
                for (ISO151408RestProductListParam productParam : shipParam.getProductList()) {
                    BigDecimal pdPrice = new BigDecimal(NumberConstant.IntDef.INT_ZERO);
                    Filter<SoOrderShipDetail> filter = new Filter<>();
                    filter.add("shipDetailId", BaseOperatorEnum.EQUAL, productParam.getShipDetailId());
                    filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
                    CommonSpecification spec = new CommonSpecification(filter);
                    SoOrderShipDetail soOrderShipDetail = soOrderShipDetailRepository.findOne(spec);
                    if (soOrderShipDetail == null) {
                        throw new BusinessException("根据shipDetailId： " + productParam.getShipDetailId() + "没有找到对应的发货明细");
                    }
                    Long orderDetailId = soOrderShipDetail.getOrderDetailId();
                    if (orderDetailId != null) {
                        Filter<SoOrderDetail> soOrderDetailFilter = new Filter<>();
                        soOrderDetailFilter.add("orderDetailId", BaseOperatorEnum.EQUAL, orderDetailId);
                        soOrderDetailFilter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
                        CommonSpecification<SoOrderDetail> soOrderDetailCommonSpecification = new CommonSpecification(soOrderDetailFilter);
                        SoOrderDetail soOrderDetail = soOrderDetailRepository.findOne(soOrderDetailCommonSpecification);
                        if (soOrderDetail != null) {
                            pdPrice = soOrderDetail.getPdPrice();
                        }
                    }
                    returnAmountTotal = DecimalUtil.add(returnAmountTotal,
                            DecimalUtil.multiply(pdPrice, productParam.getReturnQty()));
                }
            }
        }
        return returnAmountTotal;
    }


    /**
     * 修改订单表状态
     *
     * @param status
     */
    public void updateOrder(Long orderId, Integer status, ISO151408RestParam rsParam) {// 修改订单表
        Filter<SoOrder> filter = new Filter<>();
        filter.add("orderId", BaseOperatorEnum.EQUAL, orderId);
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification<SoOrder> spc = new CommonSpecification<>(filter);
        SoOrder order = soOrderRepository.findOne(spc);
        order.setUpdId(rsParam.getUpdId());
        order.setUpdTime(DateTimeUtil.getCustomerDate());
        order.setVer(order.getVer() + NumberConstant.IntDef.INT_ONE);
        order.setOrderStatus(status);
        this.soOrderRepository.save(order);
    }

    /**
     * 添加订单状态记录
     *
     * @param soOrder
     * @param status
     */
    public void updateOrderStatus(SoOrder soOrder, Integer status, ISO151408RestParam rsParam) {
        this.orderStatusService.saveOrderStatusBySoOrderId(soOrder.getOrderId(), status, rsParam.getUpdId());

    }


    /**
     * 修改分批发货主表状态
     *
     * @param order
     * @param rsParam
     */
    public void updateSubOrder(SoOrder order, ISO151408RestParam rsParam) {
        List<SoSubOrder> soSubOrderList = order.getSoSubOrders();
        for (SoSubOrder subOrder : soSubOrderList) {
            this.updateSubOrderStatusByAllReturn(subOrder.getSubOrderId(), rsParam);
        }
    }

    /**
     * @param rsParam
     */
    public void updateSubOrderStatusByAllReturn(Long subOrderId, ISO151408RestParam rsParam) {
        Filter<SoSubOrder> filter = new Filter<>();
        filter.add("subOrderId", BaseOperatorEnum.EQUAL, subOrderId);
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification<SoSubOrder> spec = new CommonSpecification<>(filter);
        SoSubOrder subOrder = this.soSubOrderRepository.findOne(spec);
        if (subOrder != null) {
            subOrder.setVer(NumberConstant.IntDef.INT_ONE + subOrder.getVer());
            subOrder.setSubOrderStatus(OrderCodeMasterDef.SubOrderStatus.ALL_RETURN);
            subOrder.setUpdId(rsParam.getUpdId());
            subOrder.setUpdTime(DateTimeUtil.getCustomerDate());
            this.soSubOrderRepository.save(subOrder);
        }
    }

    /**
     * 修改分批发货明细状态
     *
     * @param order
     * @param status
     * @param rsParam
     */
    public void updateSubOrderDetail(SoOrder order, Integer status, ISO151408RestParam rsParam) {
        List<SoSubOrderDetail> soSubOrderDetailList = order.getSoSubOrderDetailList();
        for (SoSubOrderDetail subOrderDetail : soSubOrderDetailList) {
            subOrderDetail.setVer(NumberConstant.IntDef.INT_ONE + subOrderDetail.getVer());
            subOrderDetail.setDetailStatus(status);
            subOrderDetail.setUpdTime(DateTimeUtil.getCustomerDate());
            subOrderDetail.setUpdId(rsParam.getUpdId());
            this.soSubOrderDetailRepository.save(subOrderDetail);
        }
    }


    /**
     * 修改发货单状态
     *
     * @param order
     * @param rsParam
     */
    public void updateOrderShipStatusByAllReturn(SoOrder order, ISO151408RestParam rsParam) {
        List<SoOrderShip> shipList = order.getSoOrderShipList();
        for (SoOrderShip ship : shipList) {
            this.updateOrderShipStatus(ship.getShipId(), rsParam);
        }
    }


    /**
     * 修改发货明细状态
     *
     * @param order
     * @param rsParam
     */
    public void updateOrderShipDetailStatusByAllReturn(SoOrder order, ISO151408RestParam rsParam) {
        List<SoOrderShipDetail> shipDetailList = order.getSoOrderShipDetailList();
        for (SoOrderShipDetail shipDetail : shipDetailList) {
            this.updateOrderShipDetailStatus(shipDetail.getShipDetailId(), rsParam);
        }
    }


    /**
     * OrderShip 更新状态
     *
     * @param shipId
     * @param rsParam
     */
    public void updateOrderShipStatus(Long shipId, ISO151408RestParam rsParam) {
        Filter<SoOrderShip> filter = new Filter<>();
        filter.add("shipId", BaseOperatorEnum.EQUAL, shipId);
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification<SoOrderShip> spec = new CommonSpecification<>(filter);
        SoOrderShip orderShip = this.soOrderShipRepository.findOne(spec);
        orderShip.setShipStatus(OrderCodeMasterDef.ShipStatus.ALL_RETURN);
        orderShip.setUpdId(rsParam.getUpdId());
        orderShip.setVer(NumberConstant.IntDef.INT_ONE + orderShip.getVer());
        orderShip.setUpdTime(DateTimeUtil.getCustomerDate());
        this.soOrderShipRepository.save(orderShip);
    }

    /**
     * OrderShipDetail  更新状态
     *
     * @param shipDetailId
     * @param rsParam
     */
    public void updateOrderShipDetailStatus(Long shipDetailId, ISO151408RestParam rsParam) {
        Filter<SoOrderShipDetail> filter = new Filter<>();
        filter.add("shipDetailId", BaseOperatorEnum.EQUAL, shipDetailId);
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification<SoOrderShipDetail> spec = new CommonSpecification<>(filter);
        SoOrderShipDetail orderShipDetail = this.soOrderShipDetailRepository.findOne(spec);
        orderShipDetail.setDetailStatus(OrderCodeMasterDef.OrderDetailAvailabilityStatus.ALL_RETURN);
        orderShipDetail.setUpdId(rsParam.getUpdId());
        orderShipDetail.setVer(NumberConstant.IntDef.INT_ONE + orderShipDetail.getVer());
        orderShipDetail.setUpdTime(DateTimeUtil.getCustomerDate());
        this.soOrderShipDetailRepository.save(orderShipDetail);
    }


    /**
     * 退货时，给资金池调用     付款明细
     *
     * @param param
     */
    /*public void sendCpRunning(ISO151408RestParam param, SoReturn soReturn, SoOrder order) {
        ISO151408RestCpRunningParam cpTransactionParam = new ISO151408RestCpRunningParam();
        cpTransactionParam.setCrtId(param.getUpdId());
        cpTransactionParam.setBackType(CommOrderConst.BackType.BUYER);//参考明细来源 1：买家　2：卖家
        cpTransactionParam.setAmountType(CommOrderConst.AmountType.SELLER_RECEIVABLES);//退款
        cpTransactionParam.setTransCode(soReturn.getOrderCode());//交易编码（订单号或者卖家计费单号）
        cpTransactionParam.setOrderId(soReturn.getOrderId());//订单ID
        cpTransactionParam.setTransType(CommOrderConst.TransType.MAINORDER);//交易类型 0 主订单 1管理费用清单
        cpTransactionParam.setPaidAmount(soReturn.getReturnAmount());//支付金额
        cpTransactionParam.setPaidType(CommOrderConst.PaidType.TRANSFER);//支付方式 1：现金 2：转账 3：支票 4：冲抵核销
        cpTransactionParam.setPaidSeq(soReturn.getReturnId().toString());//支付流水号
        cpTransactionParam.setPaidTime(DateTimeUtil.getCustomerDate());//支付日期
        cpTransactionParam.setRefundCode(soReturn.getReturnCode());//退款编码（支付退货、拒收的退款时均为必填）
        cpTransactionParam.setRefundTime(soReturn.getApplyTime());//退款日期
        cpTransactionParam.setRefundType(NumberConstant.IntDef.INT_ZERO);//退回费用类型 0：退货退款 1：拒收退款 2：关闭订单
        if (Integer.valueOf(order.getSalePlatform()) == NumberConstant.IntDef.INT_TWO) {
            cpTransactionParam.setPayeeId(CapitalPoolConst.OtherConst.MSK_ID);//收款人ID(目前约定传递平台编码)
        } else {
            cpTransactionParam.setPayeeId(CapitalPoolConst.OtherConst.SNK_ID);//收款人ID(目前约定传递平台编码)
        }
        cpTransactionParam.setPayerId(order.getBuyerId());//付款人ID
        cpTransactionParam.setPlatform(Integer.valueOf(order.getSalePlatform()));//平台  1:神农客 2:美侍客 3:大促会
        cpTransactionParam.setPaymentType(order.getPaymentType());//付款方式
        cpTransactionParam.setRefundDetailList(this.findFundDetail(soReturn.getReturnId()));//退回费用明细列表
        SoRestUtil.sendCpRunningBy151408Return(cpTransactionParam);
    }*/

    /**
     * 资金池交易明细接口
     *
     * @param param
     * @param soReturn
     * @param order
     */
    public void sendCpTransaction(ISO151408RestParam param, SoReturn soReturn, SoOrder order) {
        ISO151408RestCpTransactionParam cpTransactionParam = new ISO151408RestCpTransactionParam();
        cpTransactionParam.setCrtId(param.getUpdId());
        cpTransactionParam.setInsertFlg(NumberConstant.IntDef.INT_ZERO);//是否新增标识 0：否 1：是
        cpTransactionParam.setTransCode(order.getOrderCode());//交易编码
        cpTransactionParam.setTransType(Integer.valueOf(CommOrderConst.SearchType.ORDER));//交易类型 0 主订单 1管理费用清单
        cpTransactionParam.setOperateDate(soReturn.getApplyTime());//操作时间
        if (order.getSalePlatform().equals(String.valueOf(NumberConstant.IntDef.INT_ONE))) { // 平台类型
            cpTransactionParam.setSupplyPlatform(CapitalPoolConst.SupplyPlatform.SNK);
        } else {
            cpTransactionParam.setSupplyPlatform(CapitalPoolConst.SupplyPlatform.MSK);
        }
        cpTransactionParam.setTransFlg(NumberConstant.IntDef.INT_ZERO);//交易标志 0：正常 1：交易关闭  订单关闭的情况下传1，其余情况传0
        cpTransactionParam.setOrderId(order.getOrderId());// 订单id
        cpTransactionParam.setRefundCode(soReturn.getReturnCode());//退款编码（支付退货、拒收的退款时均为必填）
        cpTransactionParam.setRefundTime(soReturn.getApplyTime());//退款日期
        cpTransactionParam.setRefundType(NumberConstant.IntDef.INT_ZERO);//退回费用类型 0：退货退款 1：拒收退款 2：关闭订单
        cpTransactionParam.setReShipFlg(NumberConstant.IntDef.INT_ZERO);//重新发货标志 0：不重新发货 1：重新发货
        cpTransactionParam.setRefundDetailList(this.findFundDetail(soReturn.getReturnId()));//退回费用明细列表
        cpTransactionParam.setRemark("退货调用资金池交易明细接口！");
        this.asyncPostService.sendCpTransactionBy151408Return(cpTransactionParam);
    }


    /**
     * 获取退回费用明细列表
     *
     * @param returnId
     */
    private List<ISO151408RestFundDetailParam> findFundDetail(Long returnId) {
        List<ISO151408RestFundDetailParam> fundDetailParams = new ArrayList<>();
        String sql = SqlUtil.getSqlBySqlId("ISO151408.findFundDetail");
        sql += " AND sod.RETURN_ID = " + returnId;
        List<Map<String, Object>> resultList = this.baseJdbc.queryForListNotCount(sql, null, null, true);
        if (org.apache.commons.collections.CollectionUtils.isNotEmpty(resultList)) {
            for (Map<String, Object> map : resultList) {
                ISO151408RestFundDetailParam fundDetail = new ISO151408RestFundDetailParam();
                try {
                    BeanUtils.populate(fundDetail, map);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new SystemException(e.getMessage());
                }
                fundDetailParams.add(fundDetail);
            }
        }
        return fundDetailParams;
    }


}
