package com.msk.ssc.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DecimalUtil;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.SscConstant;
import com.msk.ssc.bean.*;
import com.msk.ssc.logic.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xia_xiaojie on 2016/8/9.
 */
@Api(description = "RestController，核销单明细的管理接口", tags = "ISSC11322RestController")
@RestController
public class ISSC11322RestController extends BaseRsController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(ISSC11312RestController.class);

    @Autowired
    private SSC11308Logic ssc11308Logic;
    @Autowired
    private SSC1130801Logic ssc1130801Logic;
    @Autowired
    private SSC11311Logic ssc11311Logic;
    @Autowired
    private SSC11312Logic ssc11312Logic;
    @Autowired
    private SSC11322Logic ssc11322Logic;


    @ApiOperation(value = "计算货值差异", notes = "根据合同ID，计算货值差异")
    @RequestMapping(value = "/ssc/hx/calcDelyIntoDiff", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<SSC11322Result> calculateProductValueDifference(@RequestBody RsRequest<SSC11322Param> reqParam) {
        List<SSC11311Bean> ssc11311Beans = this.findDiffersByContractId(reqParam.getParam().getContractId());
        List<Long> deliveryIds = new ArrayList<Long>();
        List<Long> differIds = new ArrayList<Long>();
        for (SSC11311Bean ssc11311Bean : ssc11311Beans) {
            deliveryIds.add(ssc11311Bean.getDeliveryId());
            differIds.add(ssc11311Bean.getDifferId());
        }

        Map<Long, BigDecimal> firstAmountMap = this.calculateFirstAmountPaidInProportion(deliveryIds);  ////<发货订单ID, 预付款按比例已支付金额>
        Map<Long, BigDecimal> productValuePaidMap = this.calculateProductValuePaid(deliveryIds);        //<发货订单ID, 已付货款>
        Map<Long, BigDecimal> productValueNeedMap = this.calculateProductValueNeed(differIds);          //<差异单ID, 应付货款>
        List<SSC11322ProductValueBean> productValueBeans = this.calculateProductValues(ssc11311Beans, firstAmountMap, productValuePaidMap, productValueNeedMap);

        SSC11322Result ssc11322Result = new SSC11322Result();
        ssc11322Result.setProductValues(productValueBeans);
        RsResponse<SSC11322Result> respResult = new RsResponse<SSC11322Result>();
        respResult.setResult(ssc11322Result);
        respResult.setStatus(SystemConst.RsStatus.SUCCESS);
        return respResult;
    }

    /**
     * 根据发货订单ID，计算预付款按比例已支付金额，即预付款用于抵扣的货款的部分
     */
    private Map<Long, BigDecimal> calculateFirstAmountPaidInProportion(List<Long> deliveryIds) {
        Map<Long, BigDecimal> firstAmountMap = new HashMap<Long, BigDecimal>(); //<发货订单ID, 预付款按比例已支付金额>
        if (!CollectionUtils.isEmpty(deliveryIds)) {
            List<SSC11322Bean> ssc11322Beans = ssc11322Logic.findFirstPaidByDeliveryIds(deliveryIds);
            Map<Long, BigDecimal> didPaidMap = new HashMap<Long, BigDecimal>(); //<发货订单ID, 预付款按比例已支付金额>
            final BigDecimal hundred = new BigDecimal(NumberConst.IntDef.INT_HUNDRED);

            for (SSC11322Bean ssc11322Bean : ssc11322Beans) {
                long deliveryId = ssc11322Bean.getDeliveryId();
                BigDecimal productValue = ssc11322Bean.getProductValue();
                BigDecimal firstPercent = ssc11322Bean.getFirstPercent();

                BigDecimal amount = DecimalUtil.multiply(productValue, firstPercent);
                amount = DecimalUtil.divide(amount, hundred);
                if (didPaidMap.containsKey(deliveryId)) {
                    BigDecimal value = didPaidMap.get(deliveryId);
                    amount = DecimalUtil.add(value, amount);
                }
                didPaidMap.put(deliveryId, amount);
            }

            for (long deliveryId : deliveryIds) {
                BigDecimal amount = didPaidMap.get(deliveryId);
                firstAmountMap.put(deliveryId, this.scale2(amount));
            }
        }
        return firstAmountMap;
    }

    /**
     * 计算货值金额差和合计
     */
    private List<SSC11322ProductValueBean> calculateProductValues(List<SSC11311Bean> ssc11311Beans, Map<Long, BigDecimal> firstAmountMap, Map<Long, BigDecimal> productValuePaidMap, Map<Long, BigDecimal> productValueNeedMap) {
        List<SSC11322ProductValueBean> productValueBeans = new ArrayList<SSC11322ProductValueBean>();
        BigDecimal totalValueNeed = BigDecimal.ZERO;
        BigDecimal totalValuePaid = BigDecimal.ZERO;
        BigDecimal totalFirstPaid = BigDecimal.ZERO;

        for (SSC11311Bean ssc11311Bean : ssc11311Beans) {
            SSC11322ProductValueBean productValueBean = new SSC11322ProductValueBean();
            productValueBean.setDifferId(ssc11311Bean.getDifferId());
            productValueBean.setDifferCode(ssc11311Bean.getDifferCode());
            productValueBean.setIntoStoreId(ssc11311Bean.getDeliveryPreIntoId());
            productValueBean.setIntoStoreCode(ssc11311Bean.getDeliveryPreIntoCode());
            productValueBean.setDeliveryId(ssc11311Bean.getDeliveryId());
            productValueBean.setDeliveryCode(ssc11311Bean.getDeliveryCode());

            BigDecimal valueNeed = productValueNeedMap.get(ssc11311Bean.getDifferId());
            valueNeed = this.scale2(valueNeed);
            productValueBean.setValueNeed(valueNeed);
            totalValueNeed = DecimalUtil.add(totalValueNeed, valueNeed);

            BigDecimal valuePaid = productValuePaidMap.get(ssc11311Bean.getDeliveryId());
            valuePaid = this.scale2(valuePaid);
            productValueBean.setValuePaid(valuePaid);
            totalValuePaid = DecimalUtil.add(totalValuePaid, valuePaid);

            BigDecimal valueDiff = DecimalUtil.subtract(valueNeed, valuePaid);
            productValueBean.setValueDiff(valueDiff);
            productValueBeans.add(productValueBean);

            BigDecimal firstPaid = firstAmountMap.get(ssc11311Bean.getDeliveryId());
            productValueBean.setFirstPaid(firstPaid);
            totalFirstPaid = DecimalUtil.add(totalFirstPaid, firstPaid);
        }

        //将合计值保存在LIST的第一个元素中
        if (!CollectionUtils.isEmpty(productValueBeans)) {
            SSC11322ProductValueBean productValueBean = productValueBeans.get(NumberConst.IntDef.INT_ZERO);
            productValueBean.setTotalValueNeed(totalValueNeed);
            productValueBean.setTotalValuePaid(totalValuePaid);
            productValueBean.setTotalValueDiff(DecimalUtil.subtract(totalValueNeed, totalValuePaid));
            productValueBean.setTotalFirstPaid(totalFirstPaid);
        }
        return productValueBeans;
    }

    /**
     * 保留两位小数，四舍五入
     */
    private BigDecimal scale2(BigDecimal bd) {
        if (null == bd) {
            return BigDecimal.ZERO.setScale(NumberConst.IntDef.INT_TWO);
        }
        return bd.setScale(NumberConst.IntDef.INT_TWO, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 保留四位小数，四舍五入
     */
    private BigDecimal scale4(BigDecimal bd) {
        if (null == bd) {
            return BigDecimal.ZERO.setScale(NumberConst.IntDef.INT_FOUR);
        }
        return bd.setScale(NumberConst.IntDef.INT_FOUR, BigDecimal.ROUND_HALF_UP);
    }

    @ApiOperation(value = "计算运费差异", notes = "根据合同ID，计算运费差异")
    @RequestMapping(value = "/ssc/hx/calcTranspExpDiff", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<SSC11322Result> calculateTransportCostDifference(@RequestBody RsRequest<SSC11322Param> reqParam) {
        List<SSC11305RsBean> ssc11305RsBeans = this.findDeliveriesByContractId(reqParam.getParam().getContractId());
        List<Long> deliveryIds = new ArrayList<Long>();
        for (SSC11305RsBean ssc11305RsBean : ssc11305RsBeans) {
            deliveryIds.add(ssc11305RsBean.getDeliveryId());
        }

        Map<Long, List<Object>> intoStoreMap = this.mappingDeliveryToIntoStores(deliveryIds);   //<发货订单ID, <入库单ID, 入库单CODE, 入库总重量>>
        Map<Long, BigDecimal> freightPaidMap = this.calculateTransportCostPaid(deliveryIds);    //<发货订单ID, 已付款>
        Map<Long, BigDecimal> freightNeedMap = this.calculateTransportCostNeed(deliveryIds);    //<发货订单ID, 应付款>

        List<SSC11322TransportCostBean> transportCostBeans = this.calculateTransportCosts(ssc11305RsBeans, intoStoreMap, freightPaidMap, freightNeedMap);
        SSC11322Result ssc11322Result = new SSC11322Result();
        ssc11322Result.setTransportCosts(transportCostBeans);

        RsResponse<SSC11322Result> respResult = new RsResponse<SSC11322Result>();
        respResult.setResult(ssc11322Result);
        respResult.setStatus(SystemConst.RsStatus.SUCCESS);
        return respResult;
    }

    /**
     * 根据合同ID，查询运费不按到岸价结算的发货订单
     */
    private List<SSC11305RsBean> findDeliveriesByContractId(long contractId) {
        SSC11305RsParam ssc11305RsParam = new SSC11305RsParam();
        ssc11305RsParam.setContractId(contractId);
        return ssc11322Logic.findDeliveriesByContractId(ssc11305RsParam);
    }

    /**
     * <发货订单ID, <入库单ID, 入库单CODE, 入库总重量>>
     */
    private Map<Long, List<Object>> mappingDeliveryToIntoStores(List<Long> deliveryIds) {
        Map<Long, List<Object>> intoStoreMap = new HashMap<Long, List<Object>>();
        if (!CollectionUtils.isEmpty(deliveryIds)) {
            List<SSC11316Bean> ssc11316Beans = this.findIntoStoresByDeliveryIds(deliveryIds);

            for (SSC11316Bean ssc11316Bean : ssc11316Beans) {
                long deliveryId = ssc11316Bean.getDeliveryId();
                String intoStoreId = StringUtil.toString(ssc11316Bean.getDeliveryPreIntoId());
                String intoStoreCode = StringUtil.toString(ssc11316Bean.getDeliveryPreIntoCode());
                BigDecimal receiveWeight = this.scale4(ssc11316Bean.getWeights());

                if (intoStoreMap.containsKey(deliveryId)) {
                    List<Object> list = intoStoreMap.get(deliveryId);
                    intoStoreId = list.get(NumberConst.IntDef.INT_ZERO) + "," + intoStoreId;
                    intoStoreCode = list.get(NumberConst.IntDef.INT_ONE) + "," + intoStoreCode;
                    receiveWeight = DecimalUtil.add((BigDecimal) list.get(NumberConst.IntDef.INT_TWO), receiveWeight);
                    list.set(NumberConst.IntDef.INT_ZERO, intoStoreId);
                    list.set(NumberConst.IntDef.INT_ONE, intoStoreCode);
                    list.set(NumberConst.IntDef.INT_TWO, receiveWeight);
                }
                else {
                    List<Object> list = new ArrayList<Object>();
                    list.add(intoStoreId);
                    list.add(intoStoreCode);
                    list.add(receiveWeight);
                    intoStoreMap.put(ssc11316Bean.getDeliveryId(), list);
                }
            }
        }
        return intoStoreMap;
    }

    /**
     * 根据发货订单ID，批量查询入库单
     */
    private List<SSC11316Bean> findIntoStoresByDeliveryIds(List<Long> deliveryIds) {
        SSC11316Param ssc11316Param = new SSC11316Param();
        ssc11316Param.setDeliveryIds(deliveryIds);
        return ssc11322Logic.findIntoStoresByDeliveryIds(ssc11316Param);
    }

    /**
     * 计算运费差和合计
     */
    private List<SSC11322TransportCostBean> calculateTransportCosts(List<SSC11305RsBean> ssc11305RsBeans, Map<Long, List<Object>> intoStoreMap, Map<Long, BigDecimal> freightPaidMap, Map<Long, BigDecimal> freightNeedMap) {
        List<SSC11322TransportCostBean> transportCostBeans = new ArrayList<SSC11322TransportCostBean>();
        BigDecimal thousand = new BigDecimal(NumberConst.IntDef.INT_THOUSAND);
        BigDecimal totalDeliveryWeight = BigDecimal.ZERO;
        BigDecimal totalReceiveWeight = BigDecimal.ZERO;
        BigDecimal totalWeightDiff = BigDecimal.ZERO;
        BigDecimal totalFreightNeed = BigDecimal.ZERO;
        BigDecimal totalFreightPaid = BigDecimal.ZERO;
        BigDecimal totalFreightDeal = BigDecimal.ZERO;
        BigDecimal totalFreightDiff = BigDecimal.ZERO;

        for (SSC11305RsBean ssc11305RsBean : ssc11305RsBeans) {
            long deliveryId = ssc11305RsBean.getDeliveryId();
            String deliveryCode = ssc11305RsBean.getDeliveryCode();
            BigDecimal deliveryWeight = this.scale4(ssc11305RsBean.getWeights());
            BigDecimal freightPaid = this.scale2(freightPaidMap.get(deliveryId));
            BigDecimal freightNeed = this.scale2(freightNeedMap.get(deliveryId));
            BigDecimal freightDeal = this.scale2(null);
            BigDecimal freightDiff = DecimalUtil.subtract(freightDeal, freightPaid);

            SSC11322TransportCostBean transportCostBean = new SSC11322TransportCostBean();
            transportCostBean.setDeliveryId(deliveryId);
            transportCostBean.setDeliveryCode(deliveryCode);
            transportCostBean.setDeliveryWeight(deliveryWeight.divide(thousand));
            transportCostBean.setFreightPaid(freightPaid);
            transportCostBean.setFreightNeed(freightNeed);
            transportCostBean.setFreightDeal(freightDeal);
            transportCostBean.setFreightDiff(freightDiff);

            totalDeliveryWeight = DecimalUtil.add(totalDeliveryWeight, deliveryWeight);
            totalFreightNeed = DecimalUtil.add(totalFreightNeed, freightNeed);
            totalFreightPaid = DecimalUtil.add(totalFreightPaid, freightPaid);
            totalFreightDeal = DecimalUtil.add(totalFreightDeal, freightDeal);
            totalFreightDiff = DecimalUtil.add(totalFreightDiff, freightDiff);

            List<Object> intoStoreObjs = intoStoreMap.get(deliveryId);
            BigDecimal receiveWeight = BigDecimal.ZERO;
            BigDecimal weightDiff = BigDecimal.ZERO;
            if (!CollectionUtils.isEmpty(intoStoreObjs)) {
                transportCostBean.setIntoStoreId((String) intoStoreObjs.get(NumberConst.IntDef.INT_ZERO));
                transportCostBean.setIntoStoreCode((String) intoStoreObjs.get(NumberConst.IntDef.INT_ONE));
                receiveWeight = (BigDecimal) intoStoreObjs.get(NumberConst.IntDef.INT_TWO);
                weightDiff = DecimalUtil.subtract(receiveWeight, deliveryWeight);
            }

            totalReceiveWeight = DecimalUtil.add(totalReceiveWeight, receiveWeight);
            totalWeightDiff = DecimalUtil.subtract(totalReceiveWeight, totalDeliveryWeight);
            transportCostBean.setReceiveWeight(receiveWeight.divide(thousand));
            transportCostBean.setWeightDiff(weightDiff.divide(thousand));
            transportCostBeans.add(transportCostBean);
        }

        //将合计值保存在LIST的第一个元素中
        if (!CollectionUtils.isEmpty(transportCostBeans)) {
            SSC11322TransportCostBean transportCostBean = transportCostBeans.get(NumberConst.IntDef.INT_ZERO);
            transportCostBean.setTotalDeliveryWeight(totalDeliveryWeight.divide(thousand));
            transportCostBean.setTotalReceiveWeight(totalReceiveWeight.divide(thousand));
            transportCostBean.setTotalWeightDiff(totalWeightDiff.divide(thousand));
            transportCostBean.setTotalFreightNeed(totalFreightNeed);
            transportCostBean.setTotalFreightPaid(totalFreightPaid);
            transportCostBean.setTotalFreightDeal(totalFreightDeal);
            transportCostBean.setTotalFreightDiff(totalFreightDiff);
        }
        return transportCostBeans;
    }

    @ApiOperation(value = "新增或更新核销单及其详情", notes = "新增或更新核销单及其详情")
    @RequestMapping(value = "/ssc/saveOrUpdateVerification", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<SSC11322Result> saveOrUpdateVerification(@RequestBody RsRequest<SSC11321RsBean> reqBean) {
        int count = ssc11322Logic.saveOrUpdateVerification(reqBean.getParam());
        RsResponse<SSC11322Result> respResult = new RsResponse<SSC11322Result>();
        if (NumberConst.IntDef.INT_ONE == count) {
            respResult.setStatus(SystemConst.RsStatus.SUCCESS);
            respResult.setMessage("操作成功！");
        }
        else {
            respResult.setStatus(SystemConst.RsStatus.FAIL);
            respResult.setMessage("操作失败！");
        }
        return respResult;
    }

    @ApiOperation(value = "批量查询核销单明细", notes = "批量查询核销单明细")
    @RequestMapping(value = "/ssc/findVerificationDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<SSC11322Result> findVerificationDetails(@RequestBody RsRequest<SSC11322Param> reqParam) {
        SSC11322Param queryParam = reqParam.getParam();
        boolean paging = queryParam.isPaging();

        SSC11322Result ssc11322Result = new SSC11322Result();
        int count = NumberConst.IntDef.INT_ZERO;
        if (paging) {
            count = ssc11322Logic.getPageCount(queryParam);
            ssc11322Result.setTotalCount(count);
        }

        List<SSC11322Bean> verificationDetails = new ArrayList<SSC11322Bean>();
        if (!paging || count > NumberConst.IntDef.INT_ZERO) {
            verificationDetails = ssc11322Logic.findPageList(queryParam, SSC11322Bean.class);
        }
        ssc11322Result.setVerificationDetails(verificationDetails);

        RsResponse<SSC11322Result> respResult = new RsResponse<SSC11322Result>();
        respResult.setResult(ssc11322Result);
        respResult.setStatus(SystemConst.RsStatus.SUCCESS);
        return respResult;
    }

    /**
     * 根据合同ID，查询差异单
     */
    private List<SSC11311Bean> findDiffersByContractId(long contractId) {
        SSC11311Param ssc11311Param = new SSC11311Param();
        ssc11311Param.setContractId(contractId);
        return ssc11311Logic.findPageList(ssc11311Param, SSC11311Bean.class);
    }

    /**
     * 计算已付款
     */
    private Map<Long, BigDecimal> calculatePaid(int subject, List<Long> deliveryIds) {
        //<发货订单ID, 已付款>
        Map<Long, BigDecimal> paidMap = new HashMap<Long, BigDecimal>();

        if (!CollectionUtils.isEmpty(deliveryIds)) {
            SSC11308RsParam ssc11308RsParam = new SSC11308RsParam();
            ssc11308RsParam.setDeliveryIds(deliveryIds);
            List<SSC11308RsBean> ssc11308RsBeans = ssc11308Logic.findPageList(ssc11308RsParam, SSC11308RsBean.class);

            //<发货订单ID, 付款申请单ID>
            Map<Long, Long> idMap = new HashMap<Long, Long>();
            List<Long> paymentRequestIds = new ArrayList<Long>();
            for (SSC11308RsBean ssc11308RsBean : ssc11308RsBeans) {
                long paymentRequestId = ssc11308RsBean.getPaymentRequestId();
                paymentRequestIds.add(paymentRequestId);
                idMap.put(ssc11308RsBean.getDeliveryId(), paymentRequestId);
            }

            //<付款申请单ID, 已付款>
            Map<Long, BigDecimal> ridPaidMap = new HashMap<Long, BigDecimal>();
            if (!CollectionUtils.isEmpty(paymentRequestIds)) {
                SSC1130801RsParam ssc1130801RsParam = new SSC1130801RsParam();
                ssc1130801RsParam.setPaymentRequestIds(paymentRequestIds);
                ssc1130801RsParam.setSubject(StringUtil.toString(subject));
                ssc1130801RsParam.setStatus(SscConstant.PaymentStatus.PAID);
                List<SSC1130801RsBean> ssc1130801RsBeans = ssc1130801Logic.findPageList(ssc1130801RsParam, SSC1130801RsBean.class);

                for (SSC1130801RsBean ssc1130801RsBean: ssc1130801RsBeans) {
                    long rid = ssc1130801RsBean.getPaymentRequestId();
                    BigDecimal paid = this.scale2(ssc1130801RsBean.getAmount());

                    if (ridPaidMap.containsKey(rid)) {
                        BigDecimal value = ridPaidMap.get(rid);
                        ridPaidMap.put(rid, DecimalUtil.add(paid, value));
                    }
                    else {
                        ridPaidMap.put(rid, paid);
                    }
                }
            }

            //<发货订单ID, 已付款>
            for (Map.Entry<Long, Long> entry : idMap.entrySet()) {
                BigDecimal paid = ridPaidMap.get(entry.getValue());
                paid = (null == paid) ? BigDecimal.ZERO : paid;
                paidMap.put(entry.getKey(), paid);
            }
        }
        return paidMap;
    }

    /**
     * 货值已付款：付款申请单中的进度款中的货款部分
     */
    private Map<Long, BigDecimal> calculateProductValuePaid(List<Long> deliveryIds) {
        return this.calculatePaid(SscConstant.Subject.GOODS_PAYMENT, deliveryIds);
    }

    /**
     * 运费已付款：付款申请单中的进度款中的运费部分
     */
    private Map<Long, BigDecimal> calculateTransportCostPaid(List<Long> deliveryIds) {
        return this.calculatePaid(SscConstant.Subject.FREIGHT, deliveryIds);
    }

    /**
     * 货值应付款：入库货值
     */
    private Map<Long, BigDecimal> calculateProductValueNeed(List<Long> differIds) {
        Map<Long, BigDecimal> productValueNeedMap = new HashMap<Long, BigDecimal>();    //<差异单ID, 应付款>
        if (!CollectionUtils.isEmpty(differIds)) {
            SSC11312Param ssc11312Param = new SSC11312Param();
            ssc11312Param.setDifferIds(differIds);
            List<SSC11312Bean> ssc11312Beans = ssc11312Logic.findPageList(ssc11312Param, SSC11312Bean.class);

            for (SSC11312Bean ssc11312Bean : ssc11312Beans) {
                long differId = ssc11312Bean.getDifferId();
                BigDecimal need = DecimalUtil.multiply(ssc11312Bean.getReceiveWeight(), ssc11312Bean.getReceivePrice());
                need = this.scale2(need);

                if (productValueNeedMap.containsKey(differId)) {
                    BigDecimal value = productValueNeedMap.get(differId);
                    productValueNeedMap.put(differId, DecimalUtil.add(need, value));
                }
                else {
                    productValueNeedMap.put(differId, need);
                }
            }
        }
        return productValueNeedMap;
    }

    /**
     * 计算应付运费
     */
    private Map<Long, BigDecimal> calculateTransportCostNeed(List<Long> deliveryIds) {
        Map<Long, BigDecimal> freightNeedMap = new HashMap<Long, BigDecimal>();
        if (!CollectionUtils.isEmpty(deliveryIds)) {
            SSC11306RsParam ssc11306RsParam = new SSC11306RsParam();
            ssc11306RsParam.setDeliveryIds(deliveryIds);
            List<SSC11306RsBean> ssc11306RsBeans = ssc11322Logic.findTrunkFreightByDeliveryIds(ssc11306RsParam);

            Map<Long, BigDecimal> transportUnitMap = new HashMap<Long, BigDecimal>();
            for (SSC11306RsBean ssc11306RsBean : ssc11306RsBeans) {
                long deliveryId = ssc11306RsBean.getDeliveryId();
                if (!transportUnitMap.containsKey(deliveryId)) {
                    transportUnitMap.put(deliveryId, ssc11306RsBean.getTransportUnit());
                }
            }

            Map<Long, BigDecimal> receiveWeightMap = this.findDifferDetailsByDeliveryIds(deliveryIds);
            BigDecimal thousand = new BigDecimal(NumberConst.IntDef.INT_THOUSAND);
            for (Map.Entry<Long, BigDecimal> entry : receiveWeightMap.entrySet()) {
                long deliveryId = entry.getKey();
                BigDecimal receiveWeight = entry.getValue();
                receiveWeight = DecimalUtil.divide(receiveWeight, thousand);
                BigDecimal transportUnit = transportUnitMap.get(deliveryId);
                transportUnit = (null == transportUnit) ? BigDecimal.ZERO : transportUnit;
                BigDecimal freightNeed = DecimalUtil.multiply(receiveWeight, transportUnit);
                freightNeedMap.put(deliveryId, this.scale2(freightNeed));
            }
        }
        return freightNeedMap;
    }

    private Map<Long, BigDecimal> findDifferDetailsByDeliveryIds(List<Long> deliveryIds) {
        SSC11312Param ssc11312Param = new SSC11312Param();
        ssc11312Param.setDeliveryIds(deliveryIds);
        List<SSC11312Bean> ssc11312Beans = ssc11312Logic.findPageList(ssc11312Param, SSC11312Bean.class);

        Map<Long, BigDecimal> receiveWeightMap = new HashMap<Long, BigDecimal>();
        for (SSC11312Bean ssc11312Bean : ssc11312Beans) {
            long deliveryId = ssc11312Bean.getDeliveryId();
            BigDecimal receiveWeight = ssc11312Bean.getReceiveWeight();

            if (receiveWeightMap.containsKey(deliveryId)) {
                BigDecimal value = receiveWeightMap.get(deliveryId);
                receiveWeightMap.put(deliveryId, DecimalUtil.add(receiveWeight, value));
            }
            else {
                receiveWeightMap.put(deliveryId, receiveWeight);
            }
        }
        return receiveWeightMap;
    }

    @ApiOperation(value = "关闭合同和核销单", notes = "关闭合同和核销单")
    @RequestMapping(value = "/ssc/closeContract", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<SSC11322Result> closeContract(@RequestBody RsRequest<SSC11322Bean> reqParam) {
        int count = ssc11322Logic.closeContract(reqParam.getParam());
        RsResponse<SSC11322Result> respResult = new RsResponse<SSC11322Result>();
        if (NumberConst.IntDef.INT_ONE == count) {
            respResult.setStatus(SystemConst.RsStatus.SUCCESS);
        }
        else {
            respResult.setStatus(SystemConst.RsStatus.FAIL);
        }
        return respResult;
    }

}
