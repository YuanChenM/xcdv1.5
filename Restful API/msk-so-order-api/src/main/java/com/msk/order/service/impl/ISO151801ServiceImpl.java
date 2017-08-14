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
import com.msk.order.bean.param.ISO151801RestParam;
import com.msk.order.bean.param.ISO151801RestProductParam;
import com.msk.order.bean.param.ISO151801RestShipParam;
import com.msk.order.bean.result.ISO151801RestResult;
import com.msk.order.entity.*;
import com.msk.order.repository.*;
import com.msk.order.service.*;
import com.msk.order.util.SqlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * ISO151801_迟收退货数据接收接口
 * Created by chu_jian on 2016/8/2.
 */
@Service
public class ISO151801ServiceImpl extends BaseService<SoOrderShip, Long> implements ISO151801Service {
    private static Logger logger = LoggerFactory.getLogger(ISO151801ServiceImpl.class);
    @Autowired
    SoSubOrderRepository soSubOrderRepository;
    @Autowired
    SoSubOrderDetailRepository soSubOrderDetailRepository;
    @Autowired
    private SoOrderRepository soOrderRepository;
    @Autowired
    private SoOrderDetailRepository soOrderDetailRepository;
    @Autowired
    private SoOrderShipRepository soOrderShipRepository;
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

    @Override
    public BaseRepository getRepository() {
        return soOrderShipRepository;
    }

    /**
     * 迟收业务
     *
     * @param param
     */
    @Override
    @Transactional
    public ISO151801RestResult doReceiverLater(ISO151801RestParam param) {
        Date applyTime = DateTimeUtil.parseDate(param.getApplyTime(), "yyyy-MM-dd HH:mm:ss");
        if (applyTime == null) {
            throw new BusinessException("申请时间传入格式错误！");
        }
        Date receiptDate = DateTimeUtil.parseDate(param.getReceiptDate(), DateTimeUtil.FORMAT_DATE_YYYYMMDD);
        if (receiptDate == null) {
            throw new BusinessException("迟收再发送日期传入格式错误！");
        }

        Filter<SoOrderShip> filter = new Filter<>();
        filter.add("shipId", BaseOperatorEnum.EQUAL, param.getShipId());
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        // 根据shipID，获取发货单信息及OrderID
        SoOrderShip soOrderShip = super.findOne(filter);
        if (null == soOrderShip) {
            throw new BusinessException("没有对应的发货单数据,请检验!!!");
        }

        // 创建退货单主表
        SoReturn soReturn = this.setReturnOrderInfo(param, soOrderShip);
        Integer returnMode = param.getReturnMode();
        if (returnMode == null) {
            throw new BusinessException("迟收类型不能为空");
        } else if (returnMode == NumberConstant.IntDef.INT_ONE) {
            // 全部迟收
            soReturn = allReceiverLater(param, soReturn, soOrderShip);
        } else if (returnMode == NumberConstant.IntDef.INT_TWO) {
            // 部分迟收
            soReturn = partReceiverLater(param, soReturn);
        } else {
            throw new BusinessException("迟收类型填写错误");
        }
        // 记录迟收履历
        returnOrderStatusService.saveReturnStatusByReturnOrder(soReturn, soReturn.getCrtId());
        // 更新order、suborder的状态
        modifyOrderStatus(soReturn);
        ISO151801RestResult iso151801RestResult = new ISO151801RestResult();
        try {
            BeanUtils.copyProperties(soReturn, iso151801RestResult);
            iso151801RestResult.setCreateTime(DateTimeUtil.formatDate("yyyy-MM-dd HH:mm:ss", soReturn.getCrtTime()));
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        return iso151801RestResult;
    }

    /**
     * 全部迟收
     */
    private SoReturn allReceiverLater(ISO151801RestParam param, SoReturn soReturn, SoOrderShip soOrderShip) {
        logger.info("全部迟收开始");
        if (!checkAllLater(param.getShipId())) {
            throw new BusinessException("已经进行过其他操作，不能进行全部迟收操作！！！");
        }
        soReturn.setReturnAmount(this.getReturnAmount(param.getShipId()));
        soReturn = soReturnRepository.save(soReturn);
        // 插入SoReturnDetail
        this.saveSoReturnDetail(soOrderShip, soReturn);
        this.allLaterUpdate(param, soOrderShip);
        return soReturn;
    }

    /**
     * 检验是否能够全部迟收
     *
     * @param shipId
     */
    public boolean checkAllLater(Long shipId) {
        String sql = SqlUtil.getSqlBySqlId("ISO151801.check");
        sql += " AND soda.SHIP_ID = " + shipId;
        List<Map<String, Object>> resultList = this.getResult(sql);
        BigInteger count = (BigInteger) resultList.get(NumberConstant.IntDef.INT_ZERO).get("count");
        return count.compareTo(BigInteger.ZERO) > NumberConstant.IntDef.INT_ZERO;
    }

    /**
     * 全部迟收查询退单金额
     *
     * @param shipId
     */
    private BigDecimal getReturnAmount(Long shipId) {
        String sql = SqlUtil.getSqlBySqlId("ISO151801.getReturnAmount");
        sql += " AND soda.SHIP_ID = " + shipId;
        List<Map<String, Object>> resultList = this.getResult(sql);
        BigDecimal amount = (BigDecimal) resultList.get(NumberConstant.IntDef.INT_ZERO).get("amount");
        return amount;
    }

    /**
     * 全部迟收时插入退货明细表中数据
     *
     * @param soOrderShip
     * @param soReturn
     */
    public void saveSoReturnDetail(SoOrderShip soOrderShip, SoReturn soReturn) {
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
            soReturnDetail.setInboundBatch(soReturn.getReturnCode() + soReturn.getReturnId());
            soReturnDetail.setReturnQty(soOrderShipDetail.getSendQty());
            soReturnDetail.setReturnReason(soReturn.getReturnReason());
            soReturnDetail.setDetailStatus(OrderCodeMasterDef.ReturnOrderStatus.EARN_LATER);
            soReturnDetail.setCrtId(soReturn.getCrtId());
            soReturnDetail.setCrtTime(DateTimeUtil.getCustomerDate());
            soReturnDetail.setDelFlg(SystemConstant.DelFlg.ENABLE);
            soReturnDetail.setVer(NumberConstant.IntDef.INT_ONE);
            soReturnDetails.add(soReturnDetail);
        }
        if (CollectionUtils.isEmpty(soReturnDetails)) {
            throw new BusinessException("全部迟收时没有迟收信息");
        }
        soReturnDetailRepository.save(soReturnDetails);
    }

    /**
     * 全部迟收时更新发货明细，分批订单明细，订单明细
     *
     * @param param
     * @param soOrderShip
     */
    private void allLaterUpdate(ISO151801RestParam param, SoOrderShip soOrderShip) {
        Map<Long, SoOrderDetail> detailMap = new HashMap<>();
        Map<Long, SoSubOrderDetail> subDetailMap = new HashMap<>();
        // 更新发货明细
        for (SoOrderShipDetail soOrderShipDetail : soOrderShip.getSoOrderShipDetailList()) {
            subDetailMap.put(soOrderShipDetail.getSubOrderDetailId(), soOrderShipDetail.getSoSubOrderDetail());
            detailMap.put(soOrderShipDetail.getOrderDetailId(), this.getSoOrderDetail(soOrderShipDetail.getOrderDetailId()));
            soOrderShipDetail.setDetailStatus(OrderCodeMasterDef.OrderDetailAvailabilityStatus.CONFIRM);
            soOrderShipDetail.setShipId(null);
            soOrderShipDetail.setSendQty(null);
            soOrderShipDetail.setReceiveQty(null);
            soOrderShipDetail.setCancelQty(null);
            soOrderShipDetail.setReturnQty(null);
            soOrderShipDetail.setRejectionQty(null);
            soOrderShipDetail.setSendTime(null);
            soOrderShipDetail.setReceivedTime(null);
            soOrderShipDetail.setUpdId(param.getCrtId());
            soOrderShipDetail.setUpdTime(DateTimeUtil.getCustomerDate());
            soOrderShipDetail.setVer(soOrderShipDetail.getVer() + NumberConstant.IntDef.INT_ONE);
        }
        // 更新分批订单明细
        for (SoSubOrderDetail soSubOrderDetail : subDetailMap.values()) {
            soSubOrderDetail.setProDate(DateTimeUtil.parseDate(param.getReceiptDate(), DateTimeUtil.FORMAT_DATE_YYYYMMDD));
            soSubOrderDetail.setDetailStatus(OrderCodeMasterDef.SubOrderDetailStatus.CONFIRM);
            soSubOrderDetail.setSendQty(null);
            soSubOrderDetail.setReceiveQty(null);
            soSubOrderDetail.setCancelQty(null);
            soSubOrderDetail.setReturnQty(null);
            soSubOrderDetail.setRejectionQty(null);
            soSubOrderDetail.setSendTime(null);
            soSubOrderDetail.setReceivedTime(null);
            soSubOrderDetail.setUpdId(param.getCrtId());
            soSubOrderDetail.setUpdTime(DateTimeUtil.getCustomerDate());
            soSubOrderDetail.setVer(soSubOrderDetail.getVer() + NumberConstant.IntDef.INT_ONE);
        }
        soSubOrderDetailRepository.save(subDetailMap.values());
        // 更新订单明细
        for (SoOrderDetail soOrderDetail : detailMap.values()) {
            soOrderDetail.setProDate(DateTimeUtil.parseDate(param.getReceiptDate(), DateTimeUtil.FORMAT_DATE_YYYYMMDD));
            soOrderDetail.setDetailStatus(OrderCodeMasterDef.OrderDetailStatus.CONFIRM);
            soOrderDetail.setSendQty(null);
            soOrderDetail.setReceiveQty(null);
            soOrderDetail.setCancelQty(null);
            soOrderDetail.setReturnQty(null);
            soOrderDetail.setRejectionQty(null);
            soOrderDetail.setSendTime(null);
            soOrderDetail.setReceivedTime(null);
            soOrderDetail.setUpdId(param.getCrtId());
            soOrderDetail.setUpdTime(DateTimeUtil.getCustomerDate());
            soOrderDetail.setVer(soOrderDetail.getVer() + NumberConstant.IntDef.INT_ONE);
        }
        soOrderDetailRepository.save(detailMap.values());
        // 更新发货表
        soOrderShip.setShipStatus(OrderCodeMasterDef.ShipStatus.ALL_LATE_RECEIPT);
        soOrderShip.setUpdId(param.getCrtId());
        soOrderShip.setUpdTime(DateTimeUtil.getCustomerDate());
        soOrderShipRepository.save(soOrderShip);
    }

    /**
     * 部分迟收
     *
     * @param param
     * @param soReturn
     */
    public SoReturn partReceiverLater(ISO151801RestParam param, SoReturn soReturn) {
        logger.info("部分迟收开始");
        List<ISO151801RestShipParam> iso151801RestShipParams = param.getShipList();
        if (CollectionUtils.isEmpty(iso151801RestShipParams)) {
            throw new BusinessException("迟收明细不能为空");
        }
        // 统计退货总金额
        BigDecimal allAmount = new BigDecimal(NumberConstant.IntDef.INT_ZERO);
        // 所需插入的退货明细
        List<SoReturnDetail> soReturnDetails = new ArrayList<>();
        // 统计各明细迟收数量，用作check、更新明细用
        Map<Long, BigDecimal> detailMaps = new HashMap<>();
        Map<Long, BigDecimal> subDetailMaps = new HashMap<>();
        Map<Long, BigDecimal> shipDetailMaps = new HashMap<>();

        // 循环参数统计各数据
        for (ISO151801RestShipParam iso151801RestShipParam : iso151801RestShipParams) {
            if (CollectionUtils.isEmpty(iso151801RestShipParam.getProductList())) {
                throw new BusinessException("迟收明细的产品信息不能为空");
            }
            // 根据迟收数量，合并产品参数
            List<ISO151801RestProductParam> products = getProductList(iso151801RestShipParam.getProductList());
            // 生成退货明细数据,计算总金额
            BigDecimal returnAmount = this.partGetReturnDetail(products, soReturn, soReturnDetails, iso151801RestShipParam.getDeliverCode());
            allAmount = DecimalUtil.add(allAmount, returnAmount);
            // 计算各明细的迟收数量
            this.calculate(detailMaps, subDetailMaps, shipDetailMaps, products);
        }
        // check,更新明细数据
        this.resolveDetails(detailMaps, subDetailMaps, shipDetailMaps, param);
        // 插入so_return
        soReturn.setReturnAmount(allAmount);
        soReturnRepository.save(soReturn);
        // 插入so_return_detail
        soReturnDetailRepository.save(soReturnDetails);

        return soReturn;
    }

    /**
     * 部分迟收生成退货明细数据,计算退货金额
     *
     * @param products
     * @param soReturn
     * @param soReturnDetails
     * @param deliverCode
     */
    public BigDecimal partGetReturnDetail(List<ISO151801RestProductParam> products, SoReturn soReturn, List<SoReturnDetail> soReturnDetails, String deliverCode) {
        Long maxId = this.maxId("so_return_detail", products.size() + NumberConstant.IntDef.INT_ONE);
        BigDecimal returnAmount = null;
        for (ISO151801RestProductParam product : products) {
            SoOrderShipDetail soOrderShipDetail = this.getSoOrderShipDetail(Long.valueOf(product.getShipDetailId()));
            SoOrderDetail soOrderDetail = this.getSoOrderDetail(soOrderShipDetail.getOrderDetailId());
            SoDeliver soDeliver = this.getSoDeliver(deliverCode);

            SoReturnDetail soReturnDetail = new SoReturnDetail();
            soReturnDetail.setDetailId(maxId--);
            soReturnDetail.setReturnId(soReturn.getReturnId());
            soReturnDetail.setShipId(soOrderShipDetail.getShipId());
            soReturnDetail.setShipDetailId(soOrderShipDetail.getShipDetailId());
            soReturnDetail.setDeliverId(soDeliver.getDeliverId());
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
            soReturnDetail.setInboundBatch(soReturn.getReturnCode() + soReturn.getReturnId());
            soReturnDetail.setReturnQty(product.getReturnQty());
            soReturnDetail.setReturnReason(product.getDetailReasonID());
            soReturnDetail.setDetailStatus(OrderCodeMasterDef.ReturnOrderStatus.EARN_LATER);
            soReturnDetail.setCrtId(soReturn.getCrtId());
            soReturnDetail.setCrtTime(DateTimeUtil.getCustomerDate());
            soReturnDetail.setDelFlg(SystemConstant.DelFlg.ENABLE);
            soReturnDetail.setVer(NumberConstant.IntDef.INT_ONE);
            soReturnDetails.add(soReturnDetail);
            returnAmount = DecimalUtil.add(returnAmount, DecimalUtil.multiply(product.getReturnQty(), soOrderDetail.getPdPrice()));
        }
        return returnAmount;
    }

    /**
     * 计算各明细需要迟收的数量
     *
     * @param detailMaps
     * @param subDetailMaps
     * @param shipDetailMaps
     * @param products
     */
    public void calculate(Map<Long, BigDecimal> detailMaps, Map<Long, BigDecimal> subDetailMaps, Map<Long, BigDecimal> shipDetailMaps, List<ISO151801RestProductParam> products) {
        for (ISO151801RestProductParam product : products) {
            SoOrderShipDetail soOrderShipDetail = this.getSoOrderShipDetail(Long.valueOf(product.getShipDetailId()));
            SoSubOrderDetail soSubOrderDetail = soOrderShipDetail.getSoSubOrderDetail();
            SoOrderDetail soOrderDetail = this.getSoOrderDetail(soOrderShipDetail.getOrderDetailId());

            BigDecimal returnQty = null;
            // 生成<shipDetailId, 迟收数量>的map
            if (shipDetailMaps.get(soOrderShipDetail.getShipDetailId()) != null) {
                returnQty = DecimalUtil.add(shipDetailMaps.get(soOrderShipDetail.getShipDetailId()), product.getReturnQty());
                shipDetailMaps.put(soOrderShipDetail.getShipDetailId(), returnQty);
            } else {
                shipDetailMaps.put(soOrderShipDetail.getShipDetailId(), product.getReturnQty());
            }
            // 生成<subOrderDetailId, 迟收数量>的map
            if (subDetailMaps.get(soSubOrderDetail.getSubOrderDetailId()) != null) {
                returnQty = DecimalUtil.add(subDetailMaps.get(soSubOrderDetail.getSubOrderDetailId()), product.getReturnQty());
                subDetailMaps.put(soSubOrderDetail.getSubOrderDetailId(), returnQty);
            } else {
                subDetailMaps.put(soSubOrderDetail.getSubOrderDetailId(), product.getReturnQty());
            }
            // 生成<orderDetailId, 迟收数量>的map
            if (detailMaps.get(soOrderDetail.getOrderDetailId()) != null) {
                returnQty = DecimalUtil.add(subDetailMaps.get(soOrderDetail.getOrderDetailId()), product.getReturnQty());
                detailMaps.put(soOrderDetail.getOrderDetailId(), returnQty);
            } else {
                detailMaps.put(soOrderDetail.getOrderDetailId(), product.getReturnQty());
            }
        }
    }

    /**
     * check,更新明细数据
     *
     * @param detailMaps
     * @param subDetailMaps
     * @param shipDetailMaps
     * @param param
     */
    public void resolveDetails(Map<Long, BigDecimal> detailMaps, Map<Long, BigDecimal> subDetailMaps, Map<Long, BigDecimal> shipDetailMaps, ISO151801RestParam param) {
        // 需要更新的订单明细
        List<SoOrderDetail> soOrderDetails = new ArrayList<>();
        for (Long key : detailMaps.keySet()) {
            SoOrderDetail soOrderDetail = this.getSoOrderDetail(key);
            soOrderDetail.setUpdId(param.getCrtId());
            // 可迟收数量 发货数量-收货数量-拒收数量
            BigDecimal canLaterQty = DecimalUtil.subtract(soOrderDetail.getSendQty(), DecimalUtil.add(soOrderDetail.getReceiveQty(), soOrderDetail.getRejectionQty()));
            if (canLaterQty.compareTo(detailMaps.get(key)) < 0) {
                throw new BusinessException("迟收数量大于可迟收数量");
            } else if (canLaterQty.compareTo(detailMaps.get(key)) == 0) {
                soOrderDetails.add(this.backSoOrderDetail(soOrderDetail, param.getReceiptDate()));
            } else {
                soOrderDetails.add(this.updateSoOrderDetail(soOrderDetail, detailMaps.get(key)));
            }
        }

        // 需要更新的分批订单明细
        List<SoSubOrderDetail> soSubOrderDetails = new ArrayList<>();
        // 拆分时存储新旧分批明细id对应
        Map<Long, Long> subDetailIdMap = new HashMap<>();
        Long maxSubDetailId = super.maxId("so_sub_order_detail", subDetailMaps.size() + NumberConstant.IntDef.INT_ONE);
        for (Long key : subDetailMaps.keySet()) {
            SoSubOrderDetail soSubOrderDetail = this.getSoSubOrderDetail(key);
            soSubOrderDetail.setUpdId(param.getCrtId());
            // 可迟收数量 发货数量-收货数量-拒收数量
            BigDecimal canLaterQty = DecimalUtil.subtract(soSubOrderDetail.getSendQty(), DecimalUtil.add(soSubOrderDetail.getReceiveQty(), soSubOrderDetail.getRejectionQty()));
            if (canLaterQty.compareTo(subDetailMaps.get(key)) < 0) {
                throw new BusinessException("迟收数量大于可迟收数量");
            } else if (canLaterQty.compareTo(subDetailMaps.get(key)) == 0) {
                soSubOrderDetails.add(this.backSoSubOrderDetail(soSubOrderDetail, param.getReceiptDate()));
            } else {
                soSubOrderDetails.addAll(this.splitSoSubOrderDetail(soSubOrderDetail, subDetailMaps.get(key), subDetailIdMap, param.getReceiptDate(), maxSubDetailId));
                maxSubDetailId--;
            }
        }

        // 需要更新的发货明细
        List<SoOrderShipDetail> soOrderShipDetails = new ArrayList<>();
        Long maxShipDetailId = super.maxId("so_order_ship_detail", shipDetailMaps.size() + NumberConstant.IntDef.INT_ONE);
        for (Long key : shipDetailMaps.keySet()) {
            SoOrderShipDetail soOrderShipDetail = this.getSoOrderShipDetail(key);
            soOrderShipDetail.setUpdId(param.getCrtId());
            // 可迟收数量 发货数量-收货数量-拒收数量
            BigDecimal canLaterQty = DecimalUtil.subtract(soOrderShipDetail.getSendQty(), DecimalUtil.add(soOrderShipDetail.getReceiveQty(), soOrderShipDetail.getRejectionQty()));
            if (canLaterQty.compareTo(shipDetailMaps.get(key)) < 0) {
                throw new BusinessException("迟收数量大于可迟收数量");
            } else if (canLaterQty.compareTo(shipDetailMaps.get(key)) == 0) {
                soOrderShipDetails.add(this.backSoOrderShipDetail(soOrderShipDetail));
            } else {
                soOrderShipDetails.addAll(this.splitSoOrderShipDetail(soOrderShipDetail, shipDetailMaps.get(key), subDetailIdMap, maxShipDetailId));
                maxShipDetailId--;
            }
        }
        // 更新数据
        if (!CollectionUtils.isEmpty(soOrderDetails)) {
            soOrderDetailRepository.save(soOrderDetails);
        }
        if (!CollectionUtils.isEmpty(soSubOrderDetails)) {
            soSubOrderDetailRepository.save(soSubOrderDetails);
        }
        if (!CollectionUtils.isEmpty(soOrderShipDetails)) {
            soOrderShipDetailRepository.save(soOrderShipDetails);
        }
        // 判断是否全部迟收
        Filter<SoOrderShip> filter = new Filter<>();
        filter.add("shipId", BaseOperatorEnum.EQUAL, param.getShipId());
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        // 根据shipID，获取发货单信息及OrderID
        SoOrderShip soOrderShip = super.findOne(filter);
        if (soOrderShip.getSoOrderShipDetailList().size() == NumberConstant.IntDef.INT_ZERO) {
            soOrderShip.setShipStatus(OrderCodeMasterDef.ShipStatus.ALL_LATE_RECEIPT);
            soOrderShip.setUpdId(param.getCrtId());
            soOrderShip.setUpdTime(DateTimeUtil.getCustomerDate());
            soOrderShipRepository.save(soOrderShip);
        }
    }

    /**
     * 处理订单明细 回滚
     *
     * @param soOrderDetail
     * @param receiptDate
     */
    public SoOrderDetail backSoOrderDetail(SoOrderDetail soOrderDetail, String receiptDate) {
        soOrderDetail.setDetailStatus(OrderCodeMasterDef.SubOrderDetailStatus.CONFIRM);
        soOrderDetail.setProDate(DateTimeUtil.parseDate(receiptDate, DateTimeUtil.FORMAT_DATE_YYYYMMDD));
        soOrderDetail.setSendQty(null);
        soOrderDetail.setReceiveQty(null);
        soOrderDetail.setCancelQty(null);
        soOrderDetail.setReturnQty(null);
        soOrderDetail.setRejectionQty(null);
        soOrderDetail.setSendTime(null);
        soOrderDetail.setReceivedTime(null);
        soOrderDetail.setUpdTime(DateTimeUtil.getCustomerDate());
        soOrderDetail.setVer(soOrderDetail.getVer() + NumberConstant.IntDef.INT_ONE);
        return soOrderDetail;
    }

    /**
     * 订单明细部分迟收情况
     *
     * @param soOrderDetail
     * @param returnQty
     */
    public SoOrderDetail updateSoOrderDetail(SoOrderDetail soOrderDetail, BigDecimal returnQty) {
        soOrderDetail.setSendQty(DecimalUtil.subtract(soOrderDetail.getSendQty(), returnQty));
        if (soOrderDetail.getReceiveQty().compareTo(soOrderDetail.getReturnQty()) > 0) {
            soOrderDetail.setDetailStatus(OrderCodeMasterDef.OrderDetailStatus.PARTIAL_RECEIPT);
        } else {
            soOrderDetail.setDetailStatus(OrderCodeMasterDef.OrderDetailStatus.PARTIAL_SHIPMENT);
        }
        soOrderDetail.setUpdTime(DateTimeUtil.getCustomerDate());
        soOrderDetail.setVer(soOrderDetail.getVer() + NumberConstant.IntDef.INT_ONE);

        return soOrderDetail;
    }


    /**
     * 处理分批订单明细 回滚
     *
     * @param soSubOrderDetail
     * @param receiptDate
     */
    public SoSubOrderDetail backSoSubOrderDetail(SoSubOrderDetail soSubOrderDetail, String receiptDate) {
        soSubOrderDetail.setDetailStatus(OrderCodeMasterDef.SubOrderDetailStatus.CONFIRM);
        soSubOrderDetail.setProDate(DateTimeUtil.parseDate(receiptDate, DateTimeUtil.FORMAT_DATE_YYYYMMDD));
        soSubOrderDetail.setSendQty(null);
        soSubOrderDetail.setReceiveQty(null);
        soSubOrderDetail.setCancelQty(null);
        soSubOrderDetail.setReturnQty(null);
        soSubOrderDetail.setRejectionQty(null);
        soSubOrderDetail.setSendTime(null);
        soSubOrderDetail.setReceivedTime(null);
        soSubOrderDetail.setUpdTime(DateTimeUtil.getCustomerDate());
        soSubOrderDetail.setVer(soSubOrderDetail.getVer() + NumberConstant.IntDef.INT_ONE);
        return soSubOrderDetail;
    }

    /**
     * 处理分批订单明细 拆分
     *
     * @param soSubOrderDetail
     * @param returnQty
     * @param subDetailIdMap
     */
    public List<SoSubOrderDetail> splitSoSubOrderDetail(SoSubOrderDetail soSubOrderDetail, BigDecimal returnQty, Map<Long, Long> subDetailIdMap, String receiptDate, Long maxId) {
        List<SoSubOrderDetail> soSubOrderDetails = new ArrayList<>();
        SoSubOrderDetail newSoSubOrderDetail = new SoSubOrderDetail();
        BeanUtils.copyProperties(soSubOrderDetail, newSoSubOrderDetail);
        newSoSubOrderDetail.setDetailStatus(OrderCodeMasterDef.SubOrderDetailStatus.CONFIRM);
        newSoSubOrderDetail.setSubOrderDetailId(maxId);
        newSoSubOrderDetail.setOrderQty(returnQty);
        newSoSubOrderDetail.setSendQty(null);
        newSoSubOrderDetail.setReceiveQty(null);
        newSoSubOrderDetail.setCancelQty(null);
        newSoSubOrderDetail.setReturnQty(null);
        newSoSubOrderDetail.setRejectionQty(null);
        newSoSubOrderDetail.setSendTime(null);
        newSoSubOrderDetail.setReceivedTime(null);
        newSoSubOrderDetail.setProDate(DateTimeUtil.parseDate(receiptDate, DateTimeUtil.FORMAT_DATE_YYYYMMDD));
        newSoSubOrderDetail.setCrtId(soSubOrderDetail.getUpdId());
        newSoSubOrderDetail.setCrtTime(DateTimeUtil.getCustomerDate());
        newSoSubOrderDetail.setUpdId(null);
        newSoSubOrderDetail.setUpdTime(null);
        newSoSubOrderDetail.setActId(null);
        newSoSubOrderDetail.setActTime(null);
        newSoSubOrderDetail.setVer(NumberConstant.IntDef.INT_ONE);
        soSubOrderDetails.add(newSoSubOrderDetail);

        soSubOrderDetail.setOrderQty(DecimalUtil.subtract(soSubOrderDetail.getOrderQty(), returnQty));
        soSubOrderDetail.setSendQty(DecimalUtil.subtract(soSubOrderDetail.getSendQty(), returnQty));
        if (DecimalUtil.subtract(soSubOrderDetail.getSendQty(), soSubOrderDetail.getReceiveQty()).compareTo(soSubOrderDetail.getRejectionQty()) == 0
                && soSubOrderDetail.getOrderQty().compareTo(soSubOrderDetail.getSendQty()) == 0) {
            soSubOrderDetail.setDetailStatus(OrderCodeMasterDef.SubOrderDetailStatus.ALL_RECEIPT);
        } else if (soSubOrderDetail.getReceiveQty().compareTo(soSubOrderDetail.getReturnQty()) > 0) {
            soSubOrderDetail.setDetailStatus(OrderCodeMasterDef.SubOrderDetailStatus.PARTIAL_RECEIPT);
        } else if (soSubOrderDetail.getOrderQty().compareTo(soSubOrderDetail.getSendQty()) == 0) {
            soSubOrderDetail.setDetailStatus(OrderCodeMasterDef.SubOrderDetailStatus.ALL_SHIPMENT);
        } else {
            soSubOrderDetail.setDetailStatus(OrderCodeMasterDef.SubOrderDetailStatus.PARTIAL_SHIPMENT);
        }
        soSubOrderDetail.setUpdTime(DateTimeUtil.getCustomerDate());
        soSubOrderDetail.setVer(soSubOrderDetail.getVer() + NumberConstant.IntDef.INT_ONE);
        soSubOrderDetails.add(soSubOrderDetail);
        subDetailIdMap.put(soSubOrderDetail.getSubOrderDetailId(), newSoSubOrderDetail.getSubOrderDetailId());
        return soSubOrderDetails;
    }

    /**
     * 处理发货明细 回滚
     *
     * @param soOrderShipDetail
     */
    public SoOrderShipDetail backSoOrderShipDetail(SoOrderShipDetail soOrderShipDetail) {
        soOrderShipDetail.setDetailStatus(OrderCodeMasterDef.OrderDetailAvailabilityStatus.CONFIRM);
        soOrderShipDetail.setShipId(null);
        soOrderShipDetail.setSendQty(null);
        soOrderShipDetail.setReceiveQty(null);
        soOrderShipDetail.setCancelQty(null);
        soOrderShipDetail.setReturnQty(null);
        soOrderShipDetail.setRejectionQty(null);
        soOrderShipDetail.setSendTime(null);
        soOrderShipDetail.setReceivedTime(null);
        soOrderShipDetail.setUpdTime(DateTimeUtil.getCustomerDate());
        soOrderShipDetail.setVer(soOrderShipDetail.getVer() + NumberConstant.IntDef.INT_ONE);
        return soOrderShipDetail;
    }

    /**
     * 处理发货明细 拆分
     *
     * @param soOrderShipDetail
     * @param returnQty
     */
    public List<SoOrderShipDetail> splitSoOrderShipDetail(SoOrderShipDetail soOrderShipDetail, BigDecimal returnQty, Map<Long, Long> subDetailIdMap, Long maxId) {
        List<SoOrderShipDetail> soOrderShipDetails = new ArrayList<>();
        SoOrderShipDetail newSoOrderShipDetail = new SoOrderShipDetail();
        BeanUtils.copyProperties(soOrderShipDetail, newSoOrderShipDetail);
        newSoOrderShipDetail.setDetailStatus(OrderCodeMasterDef.OrderDetailAvailabilityStatus.CONFIRM);
        newSoOrderShipDetail.setShipDetailId(maxId);
        newSoOrderShipDetail.setShipId(null);
        newSoOrderShipDetail.setSubOrderDetailId(subDetailIdMap.get(soOrderShipDetail.getSubOrderDetailId()));
        newSoOrderShipDetail.setSuppQty(returnQty);
        newSoOrderShipDetail.setSendQty(null);
        newSoOrderShipDetail.setReceiveQty(null);
        newSoOrderShipDetail.setCancelQty(null);
        newSoOrderShipDetail.setReturnQty(null);
        newSoOrderShipDetail.setRejectionQty(null);
        newSoOrderShipDetail.setSendTime(null);
        newSoOrderShipDetail.setReceivedTime(null);
        newSoOrderShipDetail.setCrtId(soOrderShipDetail.getUpdId());
        newSoOrderShipDetail.setCrtTime(DateTimeUtil.getCustomerDate());
        newSoOrderShipDetail.setUpdId(null);
        newSoOrderShipDetail.setUpdTime(null);
        newSoOrderShipDetail.setActId(null);
        newSoOrderShipDetail.setActTime(null);
        newSoOrderShipDetail.setVer(NumberConstant.IntDef.INT_ONE);
        soOrderShipDetails.add(newSoOrderShipDetail);

        soOrderShipDetail.setSuppQty(DecimalUtil.subtract(soOrderShipDetail.getSuppQty(), returnQty));
        soOrderShipDetail.setSendQty(DecimalUtil.subtract(soOrderShipDetail.getSendQty(), returnQty));
        soOrderShipDetail.setUpdTime(DateTimeUtil.getCustomerDate());
        soOrderShipDetail.setVer(soOrderShipDetail.getVer() + NumberConstant.IntDef.INT_ONE);
        soOrderShipDetails.add(soOrderShipDetail);
        return soOrderShipDetails;
    }

    /**
     * 合并产品数据中 /只是迟收数量不一样其他都相同的 /迟收产品信息
     *
     * @param
     * @return
     */
    private List<ISO151801RestProductParam> getProductList(List<ISO151801RestProductParam> orderShipProducts) {
        List<ISO151801RestProductParam> products = new ArrayList<>();
        products.add(orderShipProducts.get(NumberConstant.IntDef.INT_ZERO));

        for (int i = NumberConstant.IntDef.INT_ONE; i < orderShipProducts.size(); i++) {
            boolean addFlg = true;
            for (ISO151801RestProductParam productInfo : products) {
                ISO151801RestProductParam orderShipProductInfo = orderShipProducts.get(i);
                if (productInfo.getSkuCode().equals(orderShipProductInfo.getSkuCode())
                        && productInfo.getShipDetailId().equals(orderShipProductInfo.getShipDetailId())) {
                    BigDecimal returnQty = productInfo.getReturnQty();
                    BigDecimal qty = orderShipProductInfo.getReturnQty();
                    productInfo.setReturnQty(DecimalUtil.add(qty, returnQty));
                    addFlg = false;
                }
            }
            if (addFlg) {
                products.add(orderShipProducts.get(i));
            }
        }
        return products;
    }

    /**
     * 遍历迟收到退货表中的数据
     *
     * @param param
     * @param soOrderShip
     * @return
     */
    private SoReturn setReturnOrderInfo(ISO151801RestParam param, SoOrderShip soOrderShip) {
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
        soReturn.setReturnType(OrderCodeMasterDef.ReturnType.LATER);
        soReturn.setReturnMode(param.getReturnMode().toString());
        soReturn.setReturnReason(param.getReturnReasonID());
        soReturn.setApplyMan(param.getApplyMan());
        soReturn.setApplyTime(DateTimeUtil.parseDate(param.getApplyTime(), "yyyy-MM-dd HH:mm:ss"));
        soReturn.setApplyRemark(param.getApplyRemark());
        soReturn.setReceiverName(param.getReceiverName());
        soReturn.setReceiverTel(param.getReceiverTel());
        soReturn.setIsPaid(param.getIsPaid().toString());
        soReturn.setIsInvoice(soOrder.getNeedInvoice());
        soReturn.setReturnStatus(OrderCodeMasterDef.ReturnOrderStatus.EARN_LATER);
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
     * 更新order的状态
     *
     * @param
     * @return
     */
    private void modifyOrderStatus(SoReturn soReturn) {
        Filter<SoOrder> orderFilter = new Filter<>();
        orderFilter.add("orderId", BaseOperatorEnum.EQUAL, soReturn.getOrderId());
        orderFilter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification<SoOrder> conditionSoOrder = new CommonSpecification<>(orderFilter);
        SoOrder soOrder = soOrderRepository.findOne(conditionSoOrder);
        Integer allReceiptCount = NumberConstant.IntDef.INT_ZERO;
        Integer allShipmentCount = NumberConstant.IntDef.INT_ZERO;
        Integer partialReceiptCount = NumberConstant.IntDef.INT_ZERO;
        Integer partialShipmentCount = NumberConstant.IntDef.INT_ZERO;
        for (SoOrderDetail soOrderDetail : soOrder.getSoOrderDetailList()) {
            if (soOrderDetail.getDetailStatus().equals(OrderCodeMasterDef.OrderDetailStatus.ALL_RECEIPT)) {
                allReceiptCount++;
            }
            if (soOrderDetail.getDetailStatus().equals(OrderCodeMasterDef.OrderDetailStatus.ALL_SHIPMENT)) {
                allShipmentCount++;
            }
            if (soOrderDetail.getDetailStatus().equals(OrderCodeMasterDef.OrderDetailStatus.PARTIAL_RECEIPT)) {
                partialReceiptCount++;
            }
            if (soOrderDetail.getDetailStatus().equals(OrderCodeMasterDef.OrderDetailStatus.PARTIAL_SHIPMENT)) {
                partialShipmentCount++;
            }
        }
        Integer status = null;
        if (partialReceiptCount > NumberConstant.IntDef.INT_ZERO || allReceiptCount > NumberConstant.IntDef.INT_ZERO) {
            status = OrderCodeMasterDef.OrderStatus.PARTIAL_RECEIPT;
        } else if (allShipmentCount > NumberConstant.IntDef.INT_ZERO || partialShipmentCount > NumberConstant.IntDef.INT_ZERO) {
            status = OrderCodeMasterDef.OrderStatus.PARTIAL_SHIPMENT;
        } else {
            status = OrderCodeMasterDef.OrderStatus.CONFIRM;
        }
        soOrder.setOrderStatus(status);
        soOrder.setUpdId(soReturn.getCrtId());
        soOrder.setUpdTime(DateTimeUtil.getCustomerDate());
        modifySubOrderStatus(soOrder);
        soOrderRepository.save(soOrder);
        orderStatusService.saveOrderStatusBySoOrder(soOrder, soOrder.getUpdId());
    }

    /**
     * 更新suborder的状态
     *
     * @param
     * @return
     */
    private void modifySubOrderStatus(SoOrder soOrder) {
        List<SoSubOrder> soSubOrders = soOrder.getSoSubOrders();
        for (SoSubOrder soSubOrder : soSubOrders) {
            Integer allReceiptCount = NumberConstant.IntDef.INT_ZERO;
            Integer allShipmentCount = NumberConstant.IntDef.INT_ZERO;
            Integer partialReceiptCount = NumberConstant.IntDef.INT_ZERO;
            Integer partialShipmentCount = NumberConstant.IntDef.INT_ZERO;
            for (SoSubOrderDetail soSubOrderDetail : soSubOrder.getSoSubOrderDetailList()) {
                if (soSubOrderDetail.getDetailStatus().equals(OrderCodeMasterDef.SubOrderDetailStatus.ALL_RECEIPT)) {
                    allReceiptCount++;
                }
                if (soSubOrderDetail.getDetailStatus().equals(OrderCodeMasterDef.SubOrderDetailStatus.ALL_SHIPMENT)) {
                    allShipmentCount++;
                }
                if (soSubOrderDetail.getDetailStatus().equals(OrderCodeMasterDef.SubOrderDetailStatus.PARTIAL_RECEIPT)) {
                    partialReceiptCount++;
                }
                if (soSubOrderDetail.getDetailStatus().equals(OrderCodeMasterDef.SubOrderDetailStatus.PARTIAL_SHIPMENT)) {
                    partialShipmentCount++;
                }
            }
            Integer status = null;
            if (partialReceiptCount > NumberConstant.IntDef.INT_ZERO || allReceiptCount > NumberConstant.IntDef.INT_ZERO) {
                status = OrderCodeMasterDef.SubOrderStatus.PARTIAL_RECEIPT;
            } else if (allShipmentCount > NumberConstant.IntDef.INT_ZERO || partialShipmentCount > NumberConstant.IntDef.INT_ZERO) {
                status = OrderCodeMasterDef.SubOrderStatus.PARTIAL_SHIPMENT;
            } else {
                status = OrderCodeMasterDef.SubOrderStatus.CONFIRM;
            }
            soSubOrder.setSubOrderStatus(status);
            soSubOrder.setUpdId(soOrder.getUpdId());
            soSubOrder.setUpdTime(DateTimeUtil.getCustomerDate());
        }
        soSubOrderRepository.save(soSubOrders);
        for (SoSubOrder soSubOrder : soSubOrders) {
            subOrderStatusService.saveSubStatusBySubOrder(soSubOrder, soSubOrder.getUpdId());
        }
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
     * 获取SoOrderShipDetail
     *
     * @param shipDetailId
     */
    public SoOrderShipDetail getSoOrderShipDetail(Long shipDetailId) {
        Filter<SoOrderShipDetail> filter = new Filter<>();
        filter.add("shipDetailId", BaseOperatorEnum.EQUAL, shipDetailId);
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification<SoOrderShipDetail> condition = new CommonSpecification<>(filter);
        return soOrderShipDetailRepository.findOne(condition);
    }

    /**
     * 获取SoSubOrderDetail
     *
     * @param subOrderDetailId
     */
    public SoSubOrderDetail getSoSubOrderDetail(Long subOrderDetailId) {
        Filter<SoSubOrderDetail> filter = new Filter<>();
        filter.add("subOrderDetailId", BaseOperatorEnum.EQUAL, subOrderDetailId);
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification<SoSubOrderDetail> condition = new CommonSpecification<>(filter);
        return soSubOrderDetailRepository.findOne(condition);
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
}
