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
import com.msk.order.bean.param.*;
import com.msk.order.bean.result.ISO151802RestResult;
import com.msk.order.entity.*;
import com.msk.order.repository.*;
import com.msk.order.service.*;
import com.msk.order.util.SqlUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * ISO151802_现场退货数据接收接口
 * Created by chu_jian on 2016/8/3.
 */
@Service
public class ISO151802ServiceImpl extends BaseService<SoOrderShip, Long> implements ISO151802Service {
    @Autowired
    private SoOrderShipRepository soOrderShipRepository;
    @Autowired
    SoSubOrderRepository soSubOrderRepository;
    @Autowired
    SoSubOrderDetailRepository soSubOrderDetailRepository;
    @Autowired
    private SoOrderRepository soOrderRepository;
    @Autowired
    private SoOrderDetailRepository soOrderDetailRepository;
    @Autowired
    private SoOrderShipDetailRepository soOrderShipDetailRepository;
    @Autowired
    private SoReturnRepository soReturnRepository;
    @Autowired
    private SoReturnDetailRepository soReturnDetailRepository;
    @Autowired
    private SoDeliverRepository soDeliverRepository;
    @Autowired
    private ReturnOrderStatusService returnOrderStatusService;
    @Autowired
    private OrderStatusService orderStatusService;
    @Autowired
    private SubOrderStatusService subOrderStatusService;
    @Autowired
    private BaseJdbcImpl baseJdbc;
    @Autowired
    private AsyncPostService asyncPostService;

    @Override
    public BaseRepository getRepository() {
        return soOrderShipRepository;
    }

    /**
     * 拒收业务
     *
     * @param param
     */
    @Override
    @Transactional
    public ISO151802RestResult doReceiverReject(ISO151802RestParam param) {
        Date applyTime = DateTimeUtil.parseDate(param.getApplyTime(),"yyyy-MM-dd HH:mm:ss");
        if (applyTime == null){
            throw new BusinessException("申请时间传入格式错误！");
        }
        Filter<SoOrderShip> filter = new Filter<>();
        filter.add("shipId", BaseOperatorEnum.EQUAL, param.getShipId());
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        // 根据shipID，获取发货单信息及OrderID
        SoOrderShip soOrderShip = super.findOne(filter);
        if (null == soOrderShip) {
            throw new BusinessException("没有对应的发货单数据,请检验!!!");
        }

        // 根据参数设置退货表数据
        SoReturn soReturn = this.setReturnOrderInfo(param, soOrderShip);
        // 获取退货类型
        Integer returnMode = param.getReturnMode();
        if (returnMode == null) {
            throw new BusinessException("现场退货类型不能为空");
        } else if (returnMode == NumberConstant.IntDef.INT_ONE) {
            // 全部现场退货
            soReturn = this.allReceiverReject(param, soReturn, soOrderShip);
        } else if (returnMode == NumberConstant.IntDef.INT_TWO) {
            // 部分现场退货
            soReturn = this.partReceiverReject(param, soReturn);
        } else {
            throw new BusinessException("现场退货类型填写不正确");
        }

        // 记录现场退货履历
        returnOrderStatusService.saveReturnStatusByReturnOrder(soReturn, soReturn.getCrtId());

        // 调资金池接口
        this.sendCpRunning(soReturn);

        ISO151802RestResult iso151802RestResult = new ISO151802RestResult();
        try {
            BeanUtils.copyProperties(iso151802RestResult, soReturn);
            iso151802RestResult.setCreateTime(DateTimeUtil.formatDate("yyyy-MM-dd HH:mm:ss", soReturn.getCrtTime()));
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        return iso151802RestResult;
    }

    /**
     * 全部现场退货
     *
     * @param param
     * @param soReturn
     * @param soOrderShip
     */
    private SoReturn allReceiverReject(ISO151802RestParam param, SoReturn soReturn, SoOrderShip soOrderShip) {
        if (!checkAllReject(param.getShipId())) {
            throw new BusinessException("已经进行过其他操作，不能进行全部现场退货操作！！！");
        }
        // 查询退单金额，插入SoReturn
        soReturn.setReturnAmount(this.getReturnAmount(param.getShipId()));
        soReturn = soReturnRepository.save(soReturn);
        // 插入SoReturnDetail
        this.saveSoReturnDetail(soOrderShip, soReturn);
        // 更新数量，状态
        this.allRejectUpdate(soOrderShip, param);
        return soReturn;
    }

    /**
     * 检验是否能够全部拒收
     *
     * @param shipId
     */
    public boolean checkAllReject(Long shipId) {
        String sql = SqlUtil.getSqlBySqlId("ISO151802.check");
        sql += " AND soda.SHIP_ID = " + shipId;
        List<Map<String, Object>> resultList = this.getResult(sql);
        BigInteger count = (BigInteger) resultList.get(NumberConstant.IntDef.INT_ZERO).get("count");
        return count.compareTo(BigInteger.ZERO) > NumberConstant.IntDef.INT_ZERO;
    }

    /**
     * 全部拒收查询退单金额
     *
     * @param shipId
     */
    private BigDecimal getReturnAmount(Long shipId) {
        String sql = SqlUtil.getSqlBySqlId("ISO151802.getReturnAmount");
        sql += " AND soda.SHIP_ID = " + shipId;
        List<Map<String, Object>> resultList = this.getResult(sql);
        BigDecimal amount = (BigDecimal) resultList.get(NumberConstant.IntDef.INT_ZERO).get("amount");
        return amount;
    }

    /**
     * 全部拒收时保存退货详细
     *
     * @param soOrderShip
     * @param soReturn
     */
    private void saveSoReturnDetail(SoOrderShip soOrderShip, SoReturn soReturn) {
        List<SoReturnDetail> soReturnDetails = new ArrayList<>();
        Long maxId = this.maxId("so_return_detail", soOrderShip.getSoOrderShipDetailList().size() + NumberConstant.IntDef.INT_ONE);
        for (SoOrderShipDetail soOrderShipDetail : soOrderShip.getSoOrderShipDetailList()) {
            SoOrderDetail soOrderDetail = this.getSoOrderDetail(soOrderShipDetail.getOrderDetailId());
            SoReturnDetail soReturnDetail = new SoReturnDetail();
            soReturnDetail.setDetailId(maxId--);
            soReturnDetail.setReturnId(soReturn.getReturnId());
            soReturnDetail.setShipId(soOrderShipDetail.getShipId());
            soReturnDetail.setShipDetailId(soOrderShipDetail.getShipDetailId());
            soReturnDetail.setSupplierCode(soOrderShipDetail.getSupplierCode());
            soReturnDetail.setSupplierName(soOrderShipDetail.getSupplierName());
            soReturnDetail.setManufactureCode(soOrderShipDetail.getManufactureCode());
            soReturnDetail.setClassesCode(soOrderDetail.getClassesCode());
            soReturnDetail.setClassesName(soOrderDetail.getClassesName());
            soReturnDetail.setBreedCode(soOrderDetail.getBreedCode());
            soReturnDetail.setBreedName(soOrderDetail.getBreedName());
            soReturnDetail.setFeatureCode(soOrderDetail.getFeatureCode());
            soReturnDetail.setFeatureName(soOrderDetail.getFeatureName());
            soReturnDetail.setNormsCode(soOrderDetail.getNormsCode());
            soReturnDetail.setNormsName(soOrderDetail.getNormsName());
            soReturnDetail.setPdCode(soOrderDetail.getPdCode());
            soReturnDetail.setPdName(soOrderDetail.getPdName());
            soReturnDetail.setPdGradeCode(soOrderDetail.getPdGradeCode());
            soReturnDetail.setPdGradeName(soOrderDetail.getPdGradeName());
            soReturnDetail.setSkuCode(soOrderShipDetail.getSkuCode());
            soReturnDetail.setUnit(soOrderDetail.getUnit());
            soReturnDetail.setPackingVolume(soOrderDetail.getPackingVolume());
            soReturnDetail.setWeight(soOrderDetail.getWeight());
            soReturnDetail.setVolume(soOrderDetail.getVolume());
            soReturnDetail.setReturnQty(soOrderShipDetail.getSendQty());
            soReturnDetail.setReturnReason(soReturn.getReturnReason());
            soReturnDetail.setInboundBatch(soReturn.getReturnCode() + soReturn.getReturnId());
            soReturnDetail.setDetailStatus(OrderCodeMasterDef.ReturnOrderStatus.WAIT_SALVE);
            soReturnDetail.setCrtId(soReturn.getCrtId());
            soReturnDetail.setCrtTime(DateTimeUtil.getCustomerDate());
            soReturnDetail.setDelFlg(SystemConstant.DelFlg.ENABLE);
            soReturnDetail.setVer(NumberConstant.IntDef.INT_ONE);
            soReturnDetails.add(soReturnDetail);
        }
        soReturnDetailRepository.save(soReturnDetails);
    }

    /**
     * 全部拒收时更新数量，状态
     *
     * @param soOrderShip
     * @param param
     */
    private void allRejectUpdate(SoOrderShip soOrderShip, ISO151802RestParam param) {
        // 更新so_order_ship SHIP_STATUS 全部退货
        soOrderShip.setShipStatus(OrderCodeMasterDef.ShipStatus.ALL_RETURN);
        soOrderShip.setUpdId(param.getCrtId());
        soOrderShip.setUpdTime(DateTimeUtil.getCustomerDate());
        soOrderShip.setVer(soOrderShip.getVer() + NumberConstant.IntDef.INT_ONE);
        soOrderShip = soOrderShipRepository.save(soOrderShip);

        // 更新so_order_ship_detail 更新DETAIL_STATUS 全部退货
        List<SoOrderShipDetail> soOrderShipDetailList = soOrderShip.getSoOrderShipDetailList();
        for (SoOrderShipDetail soOrderShipDetail : soOrderShipDetailList) {
            soOrderShipDetail.setDetailStatus(OrderCodeMasterDef.OrderDetailAvailabilityStatus.ALL_RETURN);
            soOrderShipDetail.setRejectionQty(soOrderShipDetail.getSendQty());
            soOrderShipDetail.setUpdId(param.getCrtId());
            soOrderShipDetail.setUpdTime(DateTimeUtil.getCustomerDate());
            soOrderShipDetail.setVer(soOrderShipDetail.getVer() + NumberConstant.IntDef.INT_ONE);
        }
        soOrderShipDetailList = soOrderShipDetailRepository.save(soOrderShipDetailList);

        // 更新so_sub_order表和so_sub_order_detail
        this.allRejectUpdateSubOrder(soOrderShipDetailList, soOrderShip);
        // 更新so_order表和so_order_detail
        this.allRejectUpdateOrder(soOrderShipDetailList, soOrderShip);
    }

    /**
     * 部分现场退货
     *
     * @param param
     * @param soReturn
     */
    public SoReturn partReceiverReject(ISO151802RestParam param, SoReturn soReturn) {
        List<ISO151802RestShipParam> shipList = param.getShipList();
        if (CollectionUtils.isEmpty(shipList)) {
            throw new BusinessException("现场退货明细不能为空");
        }
        BigDecimal allAmount = null;
        List<SoReturnDetail> soReturnDetails = new ArrayList<>();
        Long maxId = this.maxId("so_return_detail", NumberConstant.IntDef.INT_HUNDRED);
        for (ISO151802RestShipParam iso151802RestShipParam : shipList) {
            if (CollectionUtils.isEmpty(iso151802RestShipParam.getProductList())) {
                throw new BusinessException("现场退货产品明细不能为空");
            }
            for (ISO151802RestShipDetailParam shipDetailParam : iso151802RestShipParam.getProductList()) {
                shipDetailParam.setDeliverCode(iso151802RestShipParam.getDeliverCode());
                BigDecimal returnAmount = dealPartReceiverReject(shipDetailParam, soReturn, soReturnDetails, maxId);
                allAmount = DecimalUtil.add(allAmount, returnAmount);
                maxId--;
            }
        }
        soReturn.setReturnAmount(allAmount);
        /* 插入SoReturn */
        soReturn = soReturnRepository.save(soReturn);
        soReturnDetailRepository.save(soReturnDetails);
        this.partRejectUpdate(param);
        return soReturn;
    }

    /**
     * 更新各明细表，生成退货明细数据，计算退货金额
     */
    private BigDecimal dealPartReceiverReject(ISO151802RestShipDetailParam shipDetailParam, SoReturn soReturn, List<SoReturnDetail> soReturnDetails, Long maxId) {
        Filter<SoOrderShipDetail> filter = new Filter<>();
        filter.add("shipDetailId", BaseOperatorEnum.EQUAL, shipDetailParam.getShipDetailId());
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification<SoOrderShipDetail> condition = new CommonSpecification<>(filter);
        SoOrderShipDetail soOrderShipDetail = soOrderShipDetailRepository.findOne(condition);
        SoOrderDetail soOrderDetail = this.getSoOrderDetail(soOrderShipDetail.getOrderDetailId());
        SoDeliver soDeliver = this.getSoDeliver(shipDetailParam.getDeliverCode());

        if (null == soOrderShipDetail) {
            throw new BusinessException("没有对应的订单发货明细数据,请检验！！！");
        }
        // check可拒收数量 发货数量-收货数量-拒收数量
        BigDecimal canReject = DecimalUtil.subtract(soOrderShipDetail.getSendQty(), DecimalUtil.add(soOrderShipDetail.getRejectionQty(), soOrderShipDetail.getReceiveQty()));
        if (canReject.compareTo(shipDetailParam.getReturnQty()) < 0) {
            throw new BusinessException("拒收数量超过可拒收数量");
        }
        // 更新发货明细表，分批订单明细表，订单明细表
        this.partRejectUpdateDetail(soOrderShipDetail, shipDetailParam.getReturnQty(), soReturn.getCrtId());
        // 生成退货明细数据
        SoReturnDetail soReturnDetail = new SoReturnDetail();
        soReturnDetail.setDetailId(maxId);
        soReturnDetail.setReturnId(soReturn.getReturnId());
        soReturnDetail.setDeliverId(soDeliver.getDeliverId());
        soReturnDetail.setShipId(soOrderShipDetail.getShipId());
        soReturnDetail.setShipDetailId(soOrderShipDetail.getShipDetailId());
        soReturnDetail.setSupplierCode(soOrderShipDetail.getSupplierCode());
        soReturnDetail.setSupplierName(soOrderShipDetail.getSupplierName());
        soReturnDetail.setManufactureCode(soOrderShipDetail.getManufactureCode());
        soReturnDetail.setClassesCode(soOrderDetail.getClassesCode());
        soReturnDetail.setClassesName(soOrderDetail.getClassesName());
        soReturnDetail.setBreedCode(soOrderDetail.getBreedCode());
        soReturnDetail.setBreedName(soOrderDetail.getBreedName());
        soReturnDetail.setFeatureCode(soOrderDetail.getFeatureCode());
        soReturnDetail.setFeatureName(soOrderDetail.getFeatureName());
        soReturnDetail.setNormsCode(soOrderDetail.getNormsCode());
        soReturnDetail.setNormsName(soOrderDetail.getNormsName());
        soReturnDetail.setPdCode(soOrderDetail.getPdCode());
        soReturnDetail.setPdName(soOrderDetail.getPdName());
        soReturnDetail.setPdGradeCode(soOrderDetail.getPdGradeCode());
        soReturnDetail.setPdGradeName(soOrderDetail.getPdGradeName());
        soReturnDetail.setSkuCode(shipDetailParam.getSkuCode());
        soReturnDetail.setUnit(soOrderDetail.getUnit());
        soReturnDetail.setPackingVolume(soOrderDetail.getPackingVolume());
        soReturnDetail.setWeight(soOrderDetail.getWeight());
        soReturnDetail.setVolume(soOrderDetail.getVolume());
        soReturnDetail.setReturnQty(shipDetailParam.getReturnQty());
        soReturnDetail.setReturnReason(shipDetailParam.getDetailReasonID());
        soReturnDetail.setDetailStatus(OrderCodeMasterDef.ReturnOrderStatus.WAIT_SALVE);
        soReturnDetail.setInboundBatch(soReturn.getReturnCode() + soReturn.getReturnId());
        soReturnDetail.setDelFlg(SystemConstant.DelFlg.ENABLE);
        soReturnDetail.setVer(NumberConstant.IntDef.INT_ONE);
        soReturnDetails.add(soReturnDetail);
        return DecimalUtil.multiply(shipDetailParam.getReturnQty(), soOrderDetail.getPdPrice());
    }

    /**
     * 部分拒收更新各明细表
     *
     * @param soOrderShipDetail
     * @param returnQty
     * @param soOrderShipDetail
     */
    public void partRejectUpdateDetail(SoOrderShipDetail soOrderShipDetail, BigDecimal returnQty, String updId) {
        SoSubOrderDetail soSubOrderDetail = soOrderShipDetail.getSoSubOrderDetail();
        SoOrderDetail soOrderDetail = this.getSoOrderDetail(soOrderShipDetail.getOrderDetailId());
        // 更新发货明细表
        soOrderShipDetail.setRejectionQty(DecimalUtil.add(soOrderShipDetail.getRejectionQty(), returnQty));
        if (soOrderShipDetail.getSuppQty().compareTo(soOrderShipDetail.getSendQty()) == 0
                && soOrderShipDetail.getSendQty().compareTo(DecimalUtil.add(soOrderShipDetail.getRejectionQty(), soOrderShipDetail.getReceiveQty())) == 0) {
            soOrderShipDetail.setDetailStatus(OrderCodeMasterDef.OrderDetailAvailabilityStatus.ALL_RETURN);
        }
        soOrderShipDetail.setVer(soOrderShipDetail.getVer() + NumberConstant.IntDef.INT_ONE);
        soOrderShipDetail.setUpdId(updId);
        soOrderShipDetail.setUpdTime(DateTimeUtil.getCustomerDate());
        soOrderShipDetailRepository.save(soOrderShipDetail);

        // 更新分批订单明细表
        soSubOrderDetail.setRejectionQty(DecimalUtil.add(soSubOrderDetail.getRejectionQty(), returnQty));
        if (soSubOrderDetail.getOrderQty().compareTo(soSubOrderDetail.getSendQty()) == 0
                && soSubOrderDetail.getSendQty().compareTo(DecimalUtil.add(soSubOrderDetail.getRejectionQty(), soSubOrderDetail.getReceiveQty())) == 0) {
            soSubOrderDetail.setDetailStatus(OrderCodeMasterDef.SubOrderDetailStatus.ALL_RETURN);
        }
        soSubOrderDetail.setVer(soSubOrderDetail.getVer() + NumberConstant.IntDef.INT_ONE);
        soSubOrderDetail.setUpdId(updId);
        soSubOrderDetail.setUpdTime(DateTimeUtil.getCustomerDate());
        soSubOrderDetailRepository.save(soSubOrderDetail);

        // 更新订单明细表
        soOrderDetail.setRejectionQty(DecimalUtil.add(soOrderDetail.getRejectionQty(), returnQty));
        if (soOrderDetail.getOrderQty().compareTo(soOrderDetail.getSendQty()) == 0
                && soOrderDetail.getSendQty().compareTo(DecimalUtil.add(soOrderDetail.getRejectionQty(), soOrderDetail.getReceiveQty())) == 0) {
            soOrderDetail.setDetailStatus(OrderCodeMasterDef.SubOrderDetailStatus.ALL_RETURN);
        }
        soOrderDetail.setVer(soOrderDetail.getVer() + NumberConstant.IntDef.INT_ONE);
        soOrderDetail.setUpdId(updId);
        soOrderDetail.setUpdTime(DateTimeUtil.getCustomerDate());
        soOrderDetailRepository.save(soOrderDetail);
    }

    /**
     * 部分拒收更新各主表
     *
     * @param param
     */
    private void partRejectUpdate(ISO151802RestParam param) {
        Filter<SoOrderShip> filter = new Filter<>();
        filter.add("shipId", BaseOperatorEnum.EQUAL, param.getShipId());
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        /* 根据shipID，获取发货单信息及OrderID */
        SoOrderShip soOrderShip = super.findOne(filter);

        Integer returnCount = NumberConstant.IntDef.INT_ZERO;
        Integer cancelCount = NumberConstant.IntDef.INT_ZERO;
        for (SoOrderShipDetail soOrderShipDetail : soOrderShip.getSoOrderShipDetailList()) {
            if (soOrderShipDetail.getDetailStatus() == OrderCodeMasterDef.OrderDetailAvailabilityStatus.ALL_RETURN) {
                returnCount++;
            }
            if (soOrderShipDetail.getDetailStatus() == OrderCodeMasterDef.OrderDetailAvailabilityStatus.CANCEL) {
                cancelCount++;
            }
        }
        if (returnCount + cancelCount == soOrderShip.getSoOrderShipDetailList().size()) {
            soOrderShip.setShipStatus(OrderCodeMasterDef.ShipStatus.ALL_RETURN);
            soOrderShip.setUpdId(param.getCrtId());
            soOrderShip.setUpdTime(DateTimeUtil.getCustomerDate());
            soOrderShip.setVer(soOrderShip.getVer() + NumberConstant.IntDef.INT_ONE);
            soOrderShipRepository.save(soOrderShip);
        }

        // 更新so_sub_order表和so_sub_order_detail订单状态
        this.updateSubOrder(soOrderShip);
        // 更新so_order表和so_order_detail订单状态
        this.updateOrder(soOrderShip);
    }

    /**
     * 全部拒收时更新SoSubOrder及detail
     *
     * @param soOrderShipDetailList
     * @param soOrderShip
     */
    private void allRejectUpdateSubOrder(List<SoOrderShipDetail> soOrderShipDetailList, SoOrderShip soOrderShip) {
        // 更新so_sub_order_detail
        Map<Long, SoSubOrderDetail> detailMap = new HashMap<>();
        for (SoOrderShipDetail soOrderShipDetail : soOrderShipDetailList) {
            if (detailMap.get(soOrderShipDetail.getSubOrderDetailId()) == null) {
                SoSubOrderDetail soSubOrderDetail = soOrderShipDetail.getSoSubOrderDetail();
                soSubOrderDetail.setRejectionQty(soSubOrderDetail.getSendQty());
                soSubOrderDetail.setDetailStatus(OrderCodeMasterDef.SubOrderDetailStatus.ALL_RETURN);
                soSubOrderDetail.setUpdId(soOrderShipDetail.getUpdId());
                soSubOrderDetail.setUpdTime(DateTimeUtil.getCustomerDate());
                soSubOrderDetail.setVer(soSubOrderDetail.getVer() + NumberConstant.IntDef.INT_ONE);
                detailMap.put(soSubOrderDetail.getSubOrderDetailId(), soSubOrderDetail);
            }
        }
        soSubOrderDetailRepository.save(detailMap.values());
        this.updateSubOrder(soOrderShip);
    }

    /**
     * 全部拒收时更新SoOrder及detail
     *
     * @param soOrderShipDetailList
     * @param soOrderShip
     */
    private void allRejectUpdateOrder(List<SoOrderShipDetail> soOrderShipDetailList, SoOrderShip soOrderShip) {
        Map<Long, SoOrderDetail> detailMap = new HashMap<>();
        for (SoOrderShipDetail soOrderShipDetail : soOrderShipDetailList) {
            if (detailMap.get(soOrderShipDetail.getSubOrderDetailId()) == null) {
                SoOrderDetail soOrderDetail = this.getSoOrderDetail(soOrderShipDetail.getOrderDetailId());
                soOrderDetail.setRejectionQty(soOrderDetail.getSendQty());
                soOrderDetail.setDetailStatus(OrderCodeMasterDef.SubOrderDetailStatus.ALL_RETURN);
                soOrderDetail.setUpdId(soOrderShipDetail.getUpdId());
                soOrderDetail.setUpdTime(DateTimeUtil.getCustomerDate());
                soOrderDetail.setVer(soOrderDetail.getVer() + NumberConstant.IntDef.INT_ONE);
                detailMap.put(soOrderDetail.getOrderDetailId(), soOrderDetail);
            }
        }
        soOrderDetailRepository.save(detailMap.values());
        this.updateOrder(soOrderShip);
    }

    /**
     * 更新SoSubOrder状态
     *
     * @param soOrderShip
     */
    private void updateSubOrder(SoOrderShip soOrderShip) {
        Filter<SoSubOrder> filter = new Filter<>();
        filter.add("subOrderId", BaseOperatorEnum.EQUAL, soOrderShip.getSubOrderId());
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification<SoSubOrder> condition = new CommonSpecification<>(filter);
        SoSubOrder soSubOrder = soSubOrderRepository.findOne(condition);

        Integer returnCount = NumberConstant.IntDef.INT_ZERO;
        Integer cancelCount = NumberConstant.IntDef.INT_ZERO;
        for (SoSubOrderDetail soSubOrderDetail : soSubOrder.getSoSubOrderDetailList()) {
            if (soSubOrderDetail.getDetailStatus() == OrderCodeMasterDef.SubOrderDetailStatus.ALL_RETURN) {
                returnCount += NumberConstant.IntDef.INT_ONE;
            }
            if (soSubOrderDetail.getDetailStatus() == OrderCodeMasterDef.SubOrderDetailStatus.CANCEL) {
                cancelCount += NumberConstant.IntDef.INT_ONE;
            }
        }
        // 明细状态全部都是全部退货或取消时，更新状态为全部退货
        if (returnCount > NumberConstant.IntDef.INT_ZERO && returnCount + cancelCount == soSubOrder.getSoSubOrderDetailList().size()) {
            soSubOrder.setSubOrderStatus(OrderCodeMasterDef.SubOrderStatus.ALL_RETURN);
            soSubOrder.setUpdId(soOrderShip.getUpdId());
            soSubOrder.setUpdTime(DateTimeUtil.getCustomerDate());
            soSubOrder.setVer(soSubOrder.getVer() + NumberConstant.IntDef.INT_ONE);
            soSubOrderRepository.save(soSubOrder);
            subOrderStatusService.saveSubStatusBySubOrder(soSubOrder, soSubOrder.getUpdId());
        }
    }

    /**
     * 更新SoOrder状态
     *
     * @param soOrderShip
     */
    private void updateOrder(SoOrderShip soOrderShip) {
        Filter<SoOrder> filter = new Filter<>();
        filter.add("orderId", BaseOperatorEnum.EQUAL, soOrderShip.getOrderId());
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification<SoOrder> condition = new CommonSpecification<>(filter);
        SoOrder soOrder = soOrderRepository.findOne(condition);

        Integer returnCount = NumberConstant.IntDef.INT_ZERO;
        Integer cancelCount = NumberConstant.IntDef.INT_ZERO;
        for (SoOrderDetail soOrderDetail : soOrder.getSoOrderDetailList()) {
            if (soOrderDetail.getDetailStatus() == OrderCodeMasterDef.OrderDetailStatus.ALL_RETURN) {
                returnCount++;
            }
            if (soOrderDetail.getDetailStatus() == OrderCodeMasterDef.OrderDetailStatus.CANCEL) {
                cancelCount++;
            }
        }
        // 明细状态全部都是全部退货或取消时，更新状态为全部退货
        if (returnCount > NumberConstant.IntDef.INT_ZERO && returnCount + cancelCount == soOrder.getSoOrderDetailList().size()) {
            soOrder.setOrderStatus(OrderCodeMasterDef.OrderStatus.RETURN_ALL);
            soOrder.setUpdId(soOrderShip.getUpdId());
            soOrder.setUpdTime(DateTimeUtil.getCustomerDate());
            soOrder.setVer(soOrder.getVer() + NumberConstant.IntDef.INT_ONE);
            soOrderRepository.save(soOrder);
            orderStatusService.saveOrderStatusBySoOrder(soOrder, soOrder.getUpdId());
        }
    }

    /**
     * 获取SoOrder
     *
     * @param orderId
     */
    public SoOrder getSoOrder(Long orderId) {
        Filter<SoOrder> filter = new Filter<>();
        filter.add("orderId", BaseOperatorEnum.EQUAL, orderId);
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification<SoOrder> condition = new CommonSpecification<>(filter);
        return soOrderRepository.findOne(condition);
    }

    /**
     * 获取SoOrderDetail
     *
     * @param orderDetailId
     */
    public SoOrderDetail getSoOrderDetail(Long orderDetailId) {
        Filter<SoOrderDetail> filter = new Filter<>();
        filter.add("orderDetailId", BaseOperatorEnum.EQUAL, orderDetailId);
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification<SoOrderDetail> condition = new CommonSpecification<>(filter);
        return soOrderDetailRepository.findOne(condition);
    }

    /**
     * 获取SoDeliver
     *
     * @param deliverCode
     */
    public SoDeliver getSoDeliver(String deliverCode) {
        Filter<SoDeliver> filter = new Filter<>();
        filter.add("deliverCode", BaseOperatorEnum.EQUAL, deliverCode);
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification<SoDeliver> condition = new CommonSpecification<>(filter);
        return soDeliverRepository.findOne(condition);
    }

    /**
     * 设置退货主表信息
     *
     * @param param
     * @param soOrderShip
     */
    private SoReturn setReturnOrderInfo(ISO151802RestParam param, SoOrderShip soOrderShip) {
        Filter<SoOrder> orderFilter = new Filter<>();
        orderFilter.add("orderId", BaseOperatorEnum.EQUAL, soOrderShip.getOrderId());
        orderFilter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification<SoOrder> conditionSoOrder = new CommonSpecification<>(orderFilter);
        SoOrder soOrder = soOrderRepository.findOne(conditionSoOrder);

        SoReturn soReturn = new SoReturn();
        soReturn.setReturnId(super.maxId("so_return"));
        soReturn.setReturnCode(this.getReturnOrderCode());
        soReturn.setOrderId(soOrder.getOrderId());
        soReturn.setOrderCode(soOrder.getOrderCode());
        soReturn.setBuyerCode(soOrder.getBuyerCode());
        soReturn.setBuyerName(soOrder.getBuyerName());
        soReturn.setSellerCode(soOrder.getSellerCode());
        soReturn.setSellerName(soOrder.getSellerName());
        soReturn.setDistrictCode(soOrder.getDistrictCode());
        soReturn.setReturnSource(OrderCodeMasterDef.ReturnSource.DRIVER_PDA);
        soReturn.setReturnType(OrderCodeMasterDef.ReturnType.REJECT);
        soReturn.setReturnMode(param.getReturnMode().toString());
        soReturn.setReturnReason(param.getReturnReasonID());
        soReturn.setApplyMan(param.getApplyMan());
        soReturn.setApplyTime(DateTimeUtil.parseDate(param.getApplyTime(), "yyyy-MM-dd HH:mm:ss"));
        soReturn.setApplyRemark(param.getApplyRemark());
        soReturn.setReceiverName(param.getReceiverName());
        soReturn.setReceiverTel(param.getReceiverTel());
        soReturn.setIsPaid(param.getIsPaid().toString());
        soReturn.setIsInvoice(soOrder.getNeedInvoice());
        soReturn.setReturnStatus(OrderCodeMasterDef.ReturnOrderStatus.WAIT_SALVE);
        soReturn.setSellers(soOrder.getSaId());
        soReturn.setOrderTaker(soOrder.getOrderTaker());
        soReturn.setImage1(param.getImage1());
        soReturn.setImage2(param.getImage2());
        soReturn.setImage3(param.getImage3());
        soReturn.setImage4(param.getImage4());
        soReturn.setImage5(param.getImage5());
        soReturn.setCrtId(param.getCrtId());
        soReturn.setCrtTime(DateTimeUtil.getCustomerDate());
        soReturn.setDelFlg(SystemConstant.DelFlg.ENABLE);
        soReturn.setVer(NumberConstant.IntDef.INT_ONE);
        return soReturn;
    }

    /**
     * 生成退货单订单辅码
     *
     * @return 退货单辅码
     */
    public String getReturnOrderCode() {
        String yyMMdd = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YYYYMMDD, DateTimeUtil.getCustomerDate());
        // 标准时间单元编码
        Calendar getOrderTime = Calendar.getInstance();
        int orderHour = getOrderTime.get(getOrderTime.HOUR_OF_DAY);
        int orderMin = getOrderTime.get(getOrderTime.MINUTE);
        if (orderMin > NumberConstant.IntDef.INT_THIRTY) {
            orderHour = orderHour * NumberConstant.IntDef.INT_TWO + NumberConstant.IntDef.INT_TWO;
        } else {
            orderHour = orderHour * NumberConstant.IntDef.INT_TWO + NumberConstant.IntDef.INT_ONE;
        }
        String returnOrderCode = yyMMdd.substring(NumberConstant.IntDef.INT_TWO, NumberConstant.IntDef.INT_EIGHT)
                + String.valueOf(orderHour);
        return returnOrderCode;
    }

    /**
     * 查询数据库
     *
     * @param sql sql语句
     */
    public List<Map<String, Object>> getResult(String sql) {
        List<Map<String, Object>> mapList = baseJdbc.queryForListNotCount(sql, null, null, true);
        List rows = mapList;
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        for (Object obj : rows) {
            Map<String, Object> row = (Map<String, Object>) obj;
            resultList.add(row);
        }
        return resultList;
    }

    /**
     * 拒收调用资金池
     *
     * @param soReturn
     */
    public void sendCpRunning(SoReturn soReturn) {
        SoOrder soOrder = this.getSoOrder(soReturn.getOrderId());
        ISO151802RestCpRunningParam runParam = new ISO151802RestCpRunningParam();
        runParam.setCrtId(soReturn.getCrtId());
        runParam.setBackType(CommOrderConst.BackType.BUYER);
        runParam.setAmountType(NumberConstant.IntDef.INT_TWO);
        runParam.setTransCode(soOrder.getOrderCode());
        runParam.setOrderId(soOrder.getOrderId());
        runParam.setTransType(CommOrderConst.TransType.MAINORDER);
        runParam.setPaidType(CommOrderConst.PaidType.CASH);
        runParam.setPaidAmount(soOrder.getOrderAmount());
        runParam.setPaidSeq(soOrder.getOrderCode());
        runParam.setPaidTime(DateTimeUtil.getCustomerDate());
        if (Integer.valueOf(soOrder.getSalePlatform()) == NumberConstant.IntDef.INT_TWO) {
            runParam.setPayeeId(CapitalPoolConst.OtherConst.MSK_ID);
        } else {
            runParam.setPayeeId(CapitalPoolConst.OtherConst.SNK_ID);
        }
        runParam.setPayerId(soOrder.getBuyerId());
        runParam.setPlatform(Integer.valueOf(soOrder.getSalePlatform()));
        runParam.setPaymentType(soOrder.getPaymentType());
        runParam.setRefundCode(soReturn.getReturnCode());
        runParam.setRefundTime(soReturn.getApplyTime());
        runParam.setRefundType(CommOrderConst.RefundType.REJECT);
        runParam.setReShipFlg(CommOrderConst.ReShipFlg.RESHIP);
        // 获取退回费用明细列表
        runParam.setRefundDetailList(this.findFundDetail(soReturn.getReturnId()));

        asyncPostService.sendCpRunning(runParam);
    }

    /**
     * 获取退回费用明细列表
     *
     * @param returnId
     */
    private List<ISO151802RestFundDetailParam> findFundDetail(Long returnId) {
        List<ISO151802RestFundDetailParam> fundDetailParams = new ArrayList<>();
        String sql = SqlUtil.getSqlBySqlId("ISO151802.findFundDetail");
        sql += " AND sod.RETURN_ID = " + returnId;
        List<Map<String, Object>> resultList = this.getResult(sql);
        if (CollectionUtils.isNotEmpty(resultList)) {
            for (Map<String, Object> map : resultList) {
                ISO151802RestFundDetailParam fundDetail = new ISO151802RestFundDetailParam();
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
