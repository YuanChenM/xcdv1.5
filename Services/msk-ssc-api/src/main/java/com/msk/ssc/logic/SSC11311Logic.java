package com.msk.ssc.logic;

import com.alibaba.fastjson.JSON;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.DecimalUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.ssc.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * 业务逻辑类：增删改查生产商入库差异单
 * Created by xia_xiaojie on 2016/7/4.
 */
@Service
public class SSC11311Logic extends BaseLogic {
    private static final Logger logger = LoggerFactory.getLogger(SSC11311Logic.class);

    @Autowired
    private CommonLogic commonLogic;
    @Autowired
    private SSC11306Logic ssc11306Logic;
    @Autowired
    private SSC11312Logic ssc11312Logic;
    @Autowired
    private SSC11317Logic ssc11317Logic;

    /**
     * SQL ID常量接口
     */
    private interface SqlId {
        String FIND_DIFFER_BY_DELIVERY_ID = "findDifferByDeliveryId";
        String FIND_IDS_BY_INTO_STORE_CODE = "findIdsByIntoStoreCode";
        String FIND_MAX_DIFFER_CODE = "findMaxDifferCode";
        String CONFIRM = "confirm";
        String APPEND_INTO_STORE_ID_CODE = "appendIntoStoreIdCode";
    }

    /**
     * 注入DAO
     */
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 根据发货订单ID，查询差异单
     */
    @Transactional(readOnly = true)
    public SSC11311Bean findDifferByDeliveryId(long deliveryId) {
        SSC11311Param queryParam = new SSC11311Param();
        queryParam.setDeliveryId(deliveryId);
        return super.findOne(SqlId.FIND_DIFFER_BY_DELIVERY_ID, queryParam);
    }

    /**
     * 根据入库单编号，查询入库单ID，发货订单ID和CODE，合同ID和CODE
     */
    @Transactional(readOnly = true)
    public SSC11311Bean findIdsByIntoStoreCode(long intoStoreCode) {
        SSC11311Param queryParam = new SSC11311Param();
        queryParam.setIntoStoreCode(intoStoreCode);
        return super.findOne(SqlId.FIND_IDS_BY_INTO_STORE_CODE, queryParam);
    }

    /**
     * 根据差异单ID，将差异单状态更新为已确认
     */
    @Transactional
    public int confirm(SSC11311Bean crudBean) {
        crudBean.setUpdTime(new Date());
        return super.modify(SqlId.CONFIRM, crudBean);
    }

    /**
     * 根据差异单ID，追加入库单ID和Code
     */
    @Transactional
    public int appendIntoStoreIdCode(SSC11311Bean crudBean) {
        crudBean.setUpdTime(DateTimeUtil.getCustomerDate());
        return super.modify(SqlId.APPEND_INTO_STORE_ID_CODE, crudBean);
    }

    public void generateDiffer(SSC11311Bean crudBean) {
        if (!this.tryCreateDiffer(crudBean)) {
            throw new BusinessException("入库单生成成功，差异单生成失败");
        }
    }

    private boolean tryCreateDiffer(SSC11311Bean crudBean) {
        try {
            Thread.sleep(500);  //暂停500毫秒
            this.createDiffer(crudBean);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.info("差异单生成失败");
            return false;
        }
    }

    /**
     * 一个发货订单生成一个差异单
     */
    @Transactional
    private void createDiffer(SSC11311Bean crudBean) {
        long intoStoreCode = crudBean.getIntoStoreCode();
        String userId = crudBean.getCrtId();

        //查询入库单ID和发货订单ID
        SSC11311Bean ssc11311Bean = this.findIdsByIntoStoreCode(intoStoreCode);
        long deliveryId = ssc11311Bean.getDeliveryId();
        long intoStoreId = ssc11311Bean.getIntoStoreId();

        //查询是否已存在对应差异单
        crudBean = this.findDifferByDeliveryId(deliveryId);

        //若差异单不存在，则新增
        if (null == crudBean) {
            synchronized (this) {
                long differId = commonLogic.maxId("ssc_differ_basic", "DIFFER_ID");
                String differCode = this.buildMaxDifferCode();

                //新增差异单
                crudBean = new SSC11311Bean();
                crudBean.setDifferId(differId);
                crudBean.setDifferCode(differCode);
                crudBean.setDeliveryPreIntoId(String.valueOf(intoStoreId));
                crudBean.setDeliveryPreIntoCode(String.valueOf(intoStoreCode));
                crudBean.setDeliveryId(deliveryId);
                crudBean.setDeliveryCode(ssc11311Bean.getDeliveryCode());
                crudBean.setContractId(ssc11311Bean.getContractId());
                crudBean.setContractCode(ssc11311Bean.getContractCode());
                crudBean.setCrtId(userId);
                crudBean.setCrtTime(DateTimeUtil.getCustomerDate());
                super.save(crudBean);

                //新增差异单详情
                List<SSC11312Bean> differDetails = new ArrayList<SSC11312Bean>();
                Map<String, SSC11312Bean> differenceMap = this.compare(deliveryId);
                for (Map.Entry<String, SSC11312Bean> entry : differenceMap.entrySet()) {
                    SSC11312Bean differDetail = entry.getValue();
                    differDetail.setDifferId(differId);
                    differDetail.setDifferCode(differCode);
                    differDetail.setCrtId(userId);
                    differDetails.add(differDetail);
                }
                ssc11312Logic.saveDifferDetails(differDetails);
            }
        }
        else {
            //更新差异单
            crudBean.setDeliveryPreIntoId(crudBean.getDeliveryPreIntoId() + "," + intoStoreId);
            crudBean.setDeliveryPreIntoCode(crudBean.getDeliveryPreIntoCode() + "," + intoStoreCode);
            crudBean.setUpdId(userId);
            this.appendIntoStoreIdCode(crudBean);

            long differId = crudBean.getDifferId();
            String differCode = crudBean.getDifferCode();
            Map<String, List<SSC11312Bean>> resultMap = this.distinguish(differId, deliveryId);

            //新增差异单详情
            List<SSC11312Bean> insertList = resultMap.get("insert");
            if (!CollectionUtils.isEmpty(insertList)) {
                for (SSC11312Bean bean : insertList) {
                    bean.setDifferId(differId);
                    bean.setDifferCode(differCode);
                    bean.setCrtId(userId);
                }
                ssc11312Logic.saveDifferDetails(insertList);
            }

            //更新差异单详情
            List<SSC11312Bean> updateList = resultMap.get("update");
            if (!CollectionUtils.isEmpty(updateList)) {
                for (SSC11312Bean bean : updateList) {
                    bean.setUpdId(userId);
                }
                ssc11312Logic.updateDifferDetails(updateList);
            }

            //删除差异单详情
            List<SSC11312Bean> deleteList = resultMap.get("delete");
            if (!CollectionUtils.isEmpty(deleteList)) {
                for (SSC11312Bean bean : updateList) {
                    bean.setUpdId(userId);
                }
                ssc11312Logic.deleteDifferDetails(deleteList);
            }
        }
    }

    /**
     * 获得最大差异单编号
     */
    private String buildMaxDifferCode() {
        SSC11311Param ssc11311Param = new SSC11311Param();
        String differCode = "CY" + DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YYYYMMDD, DateTimeUtil.getCustomerDate());
        ssc11311Param.setDifferCode(differCode);
        SSC11311Bean ssc11311Bean = super.findOne(SqlId.FIND_MAX_DIFFER_CODE, ssc11311Param);

        String maxDifferCode = null;
        if (null != ssc11311Bean) {
            maxDifferCode = ssc11311Bean.getDifferCode();
        }

        if (StringUtils.hasText(maxDifferCode)) {
            differCode = "CY" + DecimalUtil.add(DecimalUtil.getBigDecimal(maxDifferCode), BigDecimal.ONE);
        }
        else {
            differCode += "0001";
        }
        return differCode;
    }

    /**
     * 根据发货订单ID，查询发货订单产品详情
     */
    private Map<String, SSC1130601RsBean> queryDeliveredProducts(long deliveryId) {
        SSC11306RsParam ssc11306RsParam = new SSC11306RsParam();
        ssc11306RsParam.setPaging(false);
        ssc11306RsParam.setDeliveryId(String.valueOf(deliveryId));
        List<SSC1130601RsBean> sscDeliveryOrderPds = ssc11306Logic.findPageList(ssc11306RsParam, SSC1130601RsBean.class);

        Map<String, SSC1130601RsBean> deliveredMap = new HashMap<String, SSC1130601RsBean>();
        for (SSC1130601RsBean sscDeliveryOrderPd : sscDeliveryOrderPds) {
            deliveredMap.put(sscDeliveryOrderPd.getPdCode(), sscDeliveryOrderPd);
        }
        return deliveredMap;
    }

    /**
     * 根据发货订单ID，查询对应入库单产品详情
     */
    private Map<String, SSC11317PrePdBean> queryReceivedProducts(long deliveryId) {
        SSC11317RsParam ssc11317RsParam = new SSC11317RsParam();
        ssc11317RsParam.setPaging(false);
        ssc11317RsParam.setDeliveryId(deliveryId);
        List<SSC11317PrePdBean> sscDeliveryPrePds = ssc11317Logic.findPageList(ssc11317RsParam, SSC11317PrePdBean.class);
        logger.info("queryReceivedProducts: " + JSON.toJSONString(sscDeliveryPrePds));

        Map<String, SSC11317PrePdBean> receivedMap = new HashMap<String, SSC11317PrePdBean>();
        for (SSC11317PrePdBean sscDeliveryPrePd : sscDeliveryPrePds) {
            String productCode = sscDeliveryPrePd.getPdCode();

            //合计入库产品箱数和重量
            if (receivedMap.containsKey(productCode)) {
                SSC11317PrePdBean value = receivedMap.get(productCode);
                Integer oldBox = value.getProductRecvBox();
                oldBox = (null == oldBox) ? NumberConst.IntDef.INT_ZERO : oldBox;
                Integer newBox = sscDeliveryPrePd.getProductRecvBox();
                newBox = (null == newBox) ? NumberConst.IntDef.INT_ZERO : newBox;
                value.setProductRecvBox(oldBox + newBox);

                BigDecimal oldWeight = value.getProductRecvWeight();
                oldWeight = (null == oldWeight) ? BigDecimal.ZERO : oldWeight;
                BigDecimal newWeight = sscDeliveryPrePd.getProductRecvWeight();
                newWeight = (null == newWeight) ? BigDecimal.ZERO : newWeight;
                value.setProductRecvWeight(DecimalUtil.add(oldWeight, newWeight));
            }
            else {
                receivedMap.put(productCode, sscDeliveryPrePd);
            }
        }
        return receivedMap;
    }

    /**
     * 根据差异单ID，查询已有的差异详情
     */
    private Map<String, SSC11312Bean> queryDifferedProducts(long differId) {
        SSC11312Param ssc11312Param = new SSC11312Param();
        ssc11312Param.setPaging(false);
        ssc11312Param.setDifferId(differId);
        List<SSC11312Bean> sscDifferDetails = ssc11312Logic.findPageList(ssc11312Param, SSC11312Bean.class);

        Map<String, SSC11312Bean> differedMap = new HashMap<String, SSC11312Bean>();
        for (SSC11312Bean sscDifferDetail : sscDifferDetails) {
            differedMap.put(sscDifferDetail.getProductAttrCode(), sscDifferDetail);
        }
        return differedMap;
    }

    /**
     * 比对发货订单和入库单中产品的重量、箱数和单价
     */
    private Map<String, SSC11312Bean> compare(long deliveryId) {
        Map<String, SSC11312Bean> differenceMap = new HashMap<String, SSC11312Bean>();
        Map<String, SSC1130601RsBean> deliveredMap = this.queryDeliveredProducts(deliveryId);   //发货
        Map<String, SSC11317PrePdBean> receivedMap = this.queryReceivedProducts(deliveryId);    //收货

        for (Map.Entry<String, SSC1130601RsBean> entry : deliveredMap.entrySet()) {
            String productCode = entry.getKey();
            SSC1130601RsBean ssc1130601RsBean = entry.getValue();
            int deliveredBox = ssc1130601RsBean.getProductBox();
            BigDecimal deliveredWeight = ssc1130601RsBean.getProductQua();
            BigDecimal deliveredPrice = ssc1130601RsBean.getSettkementStandardPrice();

            SSC11317PrePdBean ssc11317PrePdBean = receivedMap.get(productCode);
            int receivedBox = NumberConst.IntDef.INT_ZERO;
            BigDecimal receivedWeight = BigDecimal.ZERO;
            BigDecimal receivedPrice = BigDecimal.ZERO;
            SSC11312Bean sscDifferDetail = new SSC11312Bean();

            if (null != ssc11317PrePdBean) {
                receivedBox = ssc11317PrePdBean.getProductRecvBox();
                receivedWeight = ssc11317PrePdBean.getProductRecvWeight();
                receivedPrice = ssc11317PrePdBean.getSettkementStandardPrice();
            }

            sscDifferDetail.setProductAttrCode(productCode);
            sscDifferDetail.setSendBox(deliveredBox);
            sscDifferDetail.setSendWeight(deliveredWeight);
            sscDifferDetail.setUnitPrice(deliveredPrice);
            sscDifferDetail.setReceiveBox(receivedBox);
            sscDifferDetail.setReceiveWeight(receivedWeight);
            sscDifferDetail.setReceivePrice(receivedPrice);
            differenceMap.put(productCode, sscDifferDetail);
        }
        return differenceMap;
    }

    /**
     * 区分哪些差异详情需要新增，哪些需要更新，哪些需要删除
     */
    private Map<String, List<SSC11312Bean>> distinguish(long differId, long deliveryId) {
        Map<String, SSC11312Bean> differenceMap = this.compare(deliveryId);
        Map<String, SSC11312Bean> differedMap = this.queryDifferedProducts(differId);

        List<SSC11312Bean> insertList = new ArrayList<SSC11312Bean>();
        List<SSC11312Bean> updateList = new ArrayList<SSC11312Bean>();
        List<SSC11312Bean> deleteList = new ArrayList<SSC11312Bean>();

        for (Map.Entry<String, SSC11312Bean> entry : differenceMap.entrySet()) {
            String productCode = entry.getKey();
            SSC11312Bean newDetail = entry.getValue();
            SSC11312Bean oldDetail = differedMap.get(productCode);

            if (null == oldDetail) {
                insertList.add(newDetail);
            }
            else {
                int oldReceivedBox = oldDetail.getReceiveBox();
                int oldDeliveredBox = oldDetail.getSendBox();
                BigDecimal oldReceivedWeight = oldDetail.getReceiveWeight();
                BigDecimal oldDeliveredWeight = oldDetail.getSendWeight();

                int newReceivedBox = newDetail.getReceiveBox();
                int newDeliveredBox = newDetail.getSendBox();
                BigDecimal newReceivedWeight = newDetail.getReceiveWeight();
                BigDecimal newDeliveredWeight = newDetail.getSendWeight();

                if (oldReceivedBox != newReceivedBox
                        || oldDeliveredBox != newDeliveredBox
                        || !DecimalUtil.isEquals(oldReceivedWeight, newReceivedWeight)
                        || !DecimalUtil.isEquals(oldDeliveredWeight, newDeliveredWeight)) {
                    oldDetail.setReceiveBox(newReceivedBox);
                    oldDetail.setSendBox(newDeliveredBox);
                    oldDetail.setReceiveWeight(newReceivedWeight);
                    oldDetail.setSendWeight(newDeliveredWeight);
                    updateList.add(oldDetail);
                }
            }
        }

        for (Map.Entry<String, SSC11312Bean> entry : differedMap.entrySet()) {
            SSC11312Bean value = entry.getValue();
            SSC11312Bean sscDifferDetail = differenceMap.get(entry.getKey());
            if (null == sscDifferDetail) {
                deleteList.add(value);
            }
        }

        Map<String, List<SSC11312Bean>> resultMap = new HashMap<String, List<SSC11312Bean>>();
        resultMap.put("insert", insertList);
        resultMap.put("update", updateList);
        resultMap.put("delete", deleteList);
        return resultMap;
    }

}
