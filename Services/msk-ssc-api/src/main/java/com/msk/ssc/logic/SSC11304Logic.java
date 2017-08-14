package com.msk.ssc.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.DecimalUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.common.business.constant.SscConstant;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.*;
import com.msk.ssc.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zhang_qiang1 on 2016/6/30
 */
@Service
public class SSC11304Logic extends BaseLogic {

    /**
     * Logger
     */
    private Logger logger = LoggerFactory.getLogger(SSC11304Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private CommonLogic commonLogic;

    /**
     * 根据合同ID，查询合同
     */
    @Transactional(readOnly = true)
    public SscContractBasic findContractById(SSC11304Param ssc11304Param) {
        return super.findOne(SqlId.SQL_ID_FIND_CONTRACT_BY_ID, ssc11304Param);
    }

    /**
     * 根据合同编号查询合同产品详细信息
     *
     * @param pdDetailParam
     * @return
     */
    @Transactional(readOnly = true)
    public List<SSC11304ProductBean> findContractPdDetailList(SSC11304Param pdDetailParam) {
        List<SSC11304ProductBean> pdDetailList = super.findList(SqlId.SQL_ID_FIND_PD_DETAIL, pdDetailParam);
        return pdDetailList;
    }

    /**
     * 根据合同ID，查询合同订单中的产品明细，排除已有包材信息的产品
     */
    @Transactional(readOnly = true)
    public List<SSC11304ProductBean> findContractProducts(SSC11304Param queryParam) {
        return super.findList(SqlId.SQL_ID_FIND_CONTRACT_PRODUCTS, queryParam);
    }

    /**
     * 查询合同包材信息列表
     *
     * @param ssc11304Param
     * @return
     */
    @Transactional(readOnly = true)
    public List<SSC11304PackageBean> getContractPackingList(SSC11304Param ssc11304Param) {
        List<SSC11304PackageBean> packingList = super.findList(SqlId.SQL_ID_FIND_PACKING, ssc11304Param);
        return packingList;
    }

    /**
     * 修改包材信息
     *
     * @param rsRequestParam
     * @return
     */
    @Transactional
    public int modifyContractPacking(RsRequest<SSC11304PackageBean> rsRequestParam) {
        SscPackageMaterialInfo sscPackageMaterial = rsRequestParam.getParam();
        if (null != sscPackageMaterial.getVer()) {
            String[] primaryKey = {"PACKAGE_DETAIL_ID"};
            Object[] primaryKeyValue = {sscPackageMaterial.getPackageDetailId()};
            super.versionValidator("ssc_package_material_info", primaryKey, primaryKeyValue, sscPackageMaterial.getVer());
        }

        sscPackageMaterial.setUpdId(rsRequestParam.getLoginId());
        sscPackageMaterial.setUpdTime(DateTimeUtil.getCustomerDate());
        int count = super.modify(SqlId.SQL_ID_MODIFY_PACKING, sscPackageMaterial);
        this.updateContractStatusToPendingAudit(sscPackageMaterial.getContractId());
        return count;
    }

    /**
     * 查询合同产品交货计划信息
     *
     * @param ssc11304Param
     * @return
     */
    @Transactional(readOnly = true)
    public List<SSC11304DeliveryPlanBean> findDeliveryPlanList(SSC11304Param ssc11304Param) {
        List<SSC11304DeliveryPlanBean> packingList = super.findList(SqlId.SQL_ID_FIND_DELIVERY_PLAN, ssc11304Param);
        return packingList;
    }


    /**
     * @param pram
     * @return
     */
    @Transactional(readOnly = true)
    public SscBusinessTerms findContractBusiness(SscBusinessTerms pram) {
        SscBusinessTerms sscBusinessTerms = super.findOne(SqlId.SQL_ID_FIND_CONTRACT_BUSSINESS, pram);
        return sscBusinessTerms;
    }


    /**
     * 查询合同产品交货批次
     *
     * @param ssc11304Param
     * @return
     */
    @Transactional(readOnly = true)
    public List<SSC11304DeliveryPlanBean> findDeliveryBatchList(SSC11304Param ssc11304Param) {
        List<SSC11304DeliveryPlanBean> batchList = super.findList(SqlId.SQL_ID_FIND_DELIVERY_BATCH, ssc11304Param);
        return batchList;
    }


    /**
     * 新增合同基本信息
     */
    @Transactional
    public Long saveContract(SscContractBasic contract) {
        long contractId = commonLogic.maxId("ssc_contract_basic", "CONTRACT_ID");
        contract.setContractId(contractId);
        contract.setCrtTime(DateTimeUtil.getCustomerDate());

        int count = super.save(SqlId.SAVE_CONTRACT, contract);
        if (NumberConst.IntDef.INT_ZERO == count) {
            return null;
        }
        return contractId;
    }

    /**
     * 查询合同产品交货计划信息
     *
     * @param deliveryPlanBean
     * @return
     */
    @Transactional
    public int saveDeliveryPlan(SSC11304DeliveryPlanBean deliveryPlanBean) {
        List<String> productCodes = deliveryPlanBean.getProductCodes();
        List<Integer> arrivQus = deliveryPlanBean.getArriveQuts();
        List<BigDecimal> weightS = deliveryPlanBean.getWeihtS();
        if (!CollectionUtils.isEmpty(productCodes) && !CollectionUtils.isEmpty(arrivQus) && !CollectionUtils.isEmpty(weightS) && (productCodes.size() == arrivQus.size()) && (weightS.size() == productCodes.size())) {
            for (int i = 0; i < productCodes.size(); i++) {
                Long maxId = commonLogic.maxId("SSC_DELIVERY_PLAN_BASIC", "LOT_ID");
                deliveryPlanBean.setPdCode(productCodes.get(i));
                Integer productBox = arrivQus.get(i);
                BigDecimal weightVal = deliveryPlanBean.getWeihtS().get(i);// 重量
                deliveryPlanBean.setWeightVal(weightVal);//设置重量
                if (weightVal != null && productBox != null) {
                    BigDecimal weightTemp = DecimalUtil.multiply(weightVal, new BigDecimal(productBox));
                    deliveryPlanBean.setArriveQut(DecimalUtil.divide(weightTemp, new BigDecimal(NumberConst.IntDef.INT_THOUSAND)));
                }

                deliveryPlanBean.setArriveBoxes(productBox);
                deliveryPlanBean.setLotId(maxId);
                deliveryPlanBean.setCrtTime(DateTimeUtil.getCustomerDate());
                super.save(SqlId.SQL_ID_SAVE_DELIVERY_PLAN, deliveryPlanBean);
            }
        }
        return productCodes.size();
    }

    /**
     * 批量新增交货计划
     */
    @Transactional
    public synchronized int saveBatchDps(SSC11304Param ssc11304Param) {
        List<SSC11304DeliveryPlanBean> deliveryPlans = ssc11304Param.getDeliveryPlans();
        long contractId = deliveryPlans.get(0).getContractId();

        //根据合同ID，查询交货计划中最大的批次
        BaseParam baseParam = new BaseParam();
        baseParam.setFilterObject("contractId", contractId);
        Object obj = super.findObject(SqlId.SQL_ID_FIND_MAX_BATCH_CODE, baseParam);

        int batch = NumberConst.IntDef.INT_ONE;
        if (null != obj) {
            batch = Integer.parseInt(String.valueOf(obj)) + 1;
        }

        Date now = new Date();
        for (SSC11304DeliveryPlanBean deliveryPlan : deliveryPlans) {
            deliveryPlan.setLotId(commonLogic.maxId("ssc_delivery_plan_basic", "LOT_ID"));
            deliveryPlan.setBatchCode(String.valueOf(batch));
            deliveryPlan.setCrtTime(now);
        }

        int count = this.batchCreateDps(ssc11304Param);
        this.updateContractStatusToPendingAudit(contractId);
        return count;
    }

    /**
     * 新增或更新交货计划
     */
    @Transactional
    public int saveOrUpdateDP(SSC11304Param ssc11304Param) {
        //旧数据
        List<SSC11304DeliveryPlanBean> dbDeliveryPlans = this.findExistentDps(ssc11304Param);
        Map<String, SSC11304DeliveryPlanBean> dbMap = new HashMap<String, SSC11304DeliveryPlanBean>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (SSC11304DeliveryPlanBean deliveryPlan : dbDeliveryPlans) {
            long contractId = deliveryPlan.getContractId();
            String pdCode = deliveryPlan.getPdCode();
            String arriveDateStr = sdf.format(deliveryPlan.getEta());
            String key = contractId + "-" + pdCode + "-" + arriveDateStr;
            dbMap.put(key, deliveryPlan);
        }

        //新数据
        List<SSC11304DeliveryPlanBean> fpDeliveryPlans = ssc11304Param.getDeliveryPlans();
        Map<String, SSC11304DeliveryPlanBean> fpMap = new HashMap<String, SSC11304DeliveryPlanBean>();

        for (SSC11304DeliveryPlanBean deliveryPlan : fpDeliveryPlans) {
            long contractId = deliveryPlan.getContractId();
            String pdCode = deliveryPlan.getPdCode();
            String arriveDateStr = deliveryPlan.getArriveDateStr();
            String key = contractId + "-" + pdCode + "-" + arriveDateStr;
            fpMap.put(key, deliveryPlan);
        }

        //比对数据，区分哪条数据应新增，哪条应更新
        List<SSC11304DeliveryPlanBean> createdPlans = new ArrayList<SSC11304DeliveryPlanBean>();
        List<SSC11304DeliveryPlanBean> updatedPlans = new ArrayList<SSC11304DeliveryPlanBean>();
        Date now = new Date();

        for (Map.Entry<String, SSC11304DeliveryPlanBean> entry : fpMap.entrySet()) {
            SSC11304DeliveryPlanBean fpPlan = entry.getValue();
            SSC11304DeliveryPlanBean dbPlan = dbMap.get(entry.getKey());

            if (null == dbPlan) {
                fpPlan.setLotId(commonLogic.maxId("ssc_delivery_plan_basic", "LOT_ID"));
                fpPlan.setCrtTime(now);
                createdPlans.add(fpPlan);
            } else {
                dbPlan.setArriveBoxes(dbPlan.getArriveBoxes() + fpPlan.getArriveBoxes());
                dbPlan.setArriveQut(dbPlan.getArriveQut().add(fpPlan.getArriveQut()));
                dbPlan.setRemark(fpPlan.getRemark());
                dbPlan.setUpdId(fpPlan.getCrtId());
                dbPlan.setUpdTime(now);
                dbPlan.setVer(dbPlan.getVer() + 1);
                updatedPlans.add(dbPlan);
            }
        }

        //新增
        int c = NumberConst.IntDef.INT_ZERO;
        if (!CollectionUtils.isEmpty(createdPlans)) {
            SSC11304Param crudParam = new SSC11304Param();
            crudParam.setDeliveryPlans(createdPlans);
            c = this.batchCreateDps(crudParam);
        }

        //更新
        int u = NumberConst.IntDef.INT_ZERO;
        if (!CollectionUtils.isEmpty(updatedPlans)) {
            SSC11304Param crudParam = new SSC11304Param();
            crudParam.setDeliveryPlans(updatedPlans);
            u = this.batchUpdateDps(crudParam);
        }
        return c + u;
    }

    /**
     * 根据合同ID，产品CODE和交货日期，查询交货计划
     */
    @Transactional(readOnly = true)
    public List<SSC11304DeliveryPlanBean> findExistentDps(SSC11304Param ssc11304Param) {
        return super.findList(SqlId.SQL_ID_FIND_EXISTENT_DPS, ssc11304Param);
    }

    /**
     * 批量新增交货计划
     */
    @Transactional
    public int batchCreateDps(SSC11304Param ssc11304Param) {
        return super.save(SqlId.SQL_ID_BATCH_CREATE_DPS, ssc11304Param);
    }

    /**
     * 批量更新交货计划
     */
    @Transactional
    public int batchUpdateDps(SSC11304Param ssc11304Param) {
        return super.modify(SqlId.SQL_ID_BATCH_UPDATE_DPS, ssc11304Param);
    }

    /**
     * 保存  合同包材信息
     *
     * @param packingBean
     * @return
     */
    @Transactional
    public int saveContractPackageM(SSC11304PackageBean packingBean) {
        Long maxId = commonLogic.maxId("ssc_package_material_info", "PACKAGE_DETAIL_ID");
        packingBean.setPackageDetailId(maxId);
        int effect = super.save(SqlId.SQL_ID_SAVE_CONTRACT_PACKAGE_M, packingBean);
        this.updateContractStatusToPendingAudit(packingBean.getContractId());
        return effect;
    }

    /**
     * modify合同产品交货计划信息
     *
     * @param param
     * @return
     */
    @Transactional
    public int modifyDeliveryPlan(RsRequest<SscDeliveryPlanBasic> param) {
        SscDeliveryPlanBasic entity = param.getParam();
        if (null != entity.getVer()) {
            String[] primaryKey = {"LOT_ID"};
            Object[] primaryKeyValue = {entity.getLotId()};
            super.versionValidator("ssc_delivery_plan_basic", primaryKey, primaryKeyValue, entity.getVer());
        }

        entity.setUpdId(entity.getUpdId());
        entity.setUpdTime(DateTimeUtil.getCustomerDate());
        int count = super.modify(SqlId.SQL_ID_MODIFY_DELIVERY_PLAN, entity);
        this.updateContractStatusToPendingAudit(entity.getContractId());
        return count;
    }

    /**
     * 更新合同信息
     */
    @Transactional
    public int updateContractBasic(SscContractBasic contract) {
        if (null != contract.getVer()) {
            String[] primaryKey = {"CONTRACT_ID"};
            Object[] primaryKeyValue = {contract.getContractId()};
            super.versionValidator("ssc_contract_basic", primaryKey, primaryKeyValue, contract.getVer());
        }
        contract.setUpdTime(DateTimeUtil.getCustomerDate());
        return super.modify(SqlId.SQL_ID_UPDATE_CONTRACT_BASIC, contract);
    }

    /**
     * 根据合同ID，更新合同状态为待审核
     */
    @Transactional
    public int updateContractStatusToPendingAudit(long contractId) {
        SscContractBasic contract = new SscContractBasic();
        contract.setContractId(contractId);
        contract.setContractStatus(String.valueOf(SscConstant.SscOrderStatus.PENDING_AUDIT));
        return this.updateContractBasic(contract);
    }

    /**
     * @param packageMaterialInfo
     * @return
     */
    @Transactional(readOnly = false)
    public int delContractPackgeM(SscPackageMaterialInfo packageMaterialInfo) {
        if (null != packageMaterialInfo.getVer()) {
            String[] primaryKey = {"PACKAGE_DETAIL_ID"};
            Object[] primaryKeyValue = {packageMaterialInfo.getPackageDetailId()};
            super.versionValidator("ssc_package_material_info", primaryKey, primaryKeyValue, packageMaterialInfo.getVer());
        }

        packageMaterialInfo.setUpdTime(DateTimeUtil.getCustomerDate());
        int count = super.modify(SqlId.SQL_ID_DEL_PACAKGEM, packageMaterialInfo);
        this.updateContractStatusToPendingAudit(packageMaterialInfo.getContractId());
        return count;
    }

    @Transactional(readOnly = true)
    public Long findPD(SSC11304Param param) {
        return (Long) super.findObject(SqlId.SQL_ID_FIND_PD, param);
    }


    /**
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public Long findPack(BaseParam param) {
        return (Long) super.findObject(SqlId.SQL_ID_FIND_PACK, param);
    }

    /**
     * 从数据看查询最大的 contractCode
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public String findDBContractCode(BaseParam param) {
        return (String) super.findObject(SqlId.SQL_ID_FIND_CONTRACT_CODE, param);
    }

    /**
     * @param businessTerms
     * @return
     */
    @Transactional(readOnly = false)
    public int saveContractBusiness(SscBusinessTerms businessTerms) {
        Long maxId = commonLogic.maxId("ssc_business_terms", "CLAUSE_ID");
        businessTerms.setClauseId(maxId);
        businessTerms.setCrtTime(DateTimeUtil.getCustomerDate());
        int count = super.save(SqlId.SQL_ID_SAVE_CONTRACT_BUSSINESS, businessTerms);
        this.updateContractStatusToPendingAudit(Long.parseLong(businessTerms.getContractId()));
        return count;
    }


    /**
     * @param businessTerms 修改 合同商务条款
     * @return
     */
    @Transactional(readOnly = false)
    public int updateContractBussiness(SscBusinessTerms businessTerms) {
        if (null != businessTerms.getVer()) {
            String[] primaryKey = {"CLAUSE_ID"};
            Object[] primaryKeyValue = {businessTerms.getClauseId()};
            super.versionValidator("ssc_business_terms", primaryKey, primaryKeyValue, businessTerms.getVer());
        }

        businessTerms.setUpdTime(DateTimeUtil.getCustomerDate());
        int count = super.modify(SqlId.SQL_ID_UPDATE_CONTRACT_BUSSINESS, businessTerms);
        this.updateContractStatusToPendingAudit(Long.parseLong(businessTerms.getContractId()));
        return count;
    }

    /**
     * 根据合同编号 查询对应的 有效箱数
     *
     * @param
     * @return
     */
    @Transactional(readOnly = true)
    public SSC11304Result checkEffecBoxNum(SSC11304DeliveryPlanBean plan) {// list  pdBean    one eva时间
        SSC11304Result result = new SSC11304Result();
        BaseParam baseParam = new BaseParam();
        baseParam.getFilterMap().put("contractId", plan.getContractId());
        baseParam.getFilterMap().put("batchCode", plan.getBatchCode());
        String evaDate = (String) super.findObject(SqlId.SQL_ID_FIND_BATCHDATE, baseParam);
        result.setEvaDate(evaDate);
        List<SSC11304DeliveryPlanBean> deliveryPlanBeans = new ArrayList<>();
        for (int i = 0; i < plan.getProductCodes().size(); i++) {
            plan.setPdCode(plan.getProductCodes().get(i));
            plan.setArriveBoxes(plan.getArriveQuts().get(i));
            SSC11304DeliveryPlanBean deliveryPlanBean = super.findOne(SqlId.SQL_ID_SAVE_CHECK_EFFECT_BOX_NUM, plan);
            deliveryPlanBeans.add(deliveryPlanBean);
        }
        result.setDpPageResult(deliveryPlanBeans);

        return result;
    }

    /**
     * 根据合同ID，更新合同状态为待审核
     */
    @Transactional
    public int enableToModify(SscContractBasic contract) {
        contract.setUpdTime(DateTimeUtil.getCustomerDate());
        return super.modify(SqlId.SQL_ID_ENABLE_TO_MODIFY, contract);
    }

    /**
     * 根据合同ID，更新合同总金额
     */
    @Transactional
    public int updateContractAmount(SSC11304Param crudParam) {
        List<SSC11304ProductBean> ssc11304ProductBeans = this.findContractPdDetailList(crudParam);
        BigDecimal totalPayments = BigDecimal.ZERO;
        for (SSC11304ProductBean ssc11304ProductBean : ssc11304ProductBeans) {
            totalPayments = DecimalUtil.add(totalPayments, ssc11304ProductBean.getProductValue());
        }

        BaseParam baseParam = new BaseParam();
        baseParam.setFilterObject("contractId", crudParam.getContractId());
        baseParam.setFilterObject("contractAmount", totalPayments);
        baseParam.setUpdTime(DateTimeUtil.getCustomerDate());
        return super.modify(SqlId.SQL_ID_UPDATE_CONTRACT_AMOUNT, baseParam);
    }

    interface SqlId {
        String SQL_ID_FIND_CONTRACT_BY_ID = "findContractById";//基本信息的查看
        String SQL_ID_UPDATE_CONTRACT_BASIC = "updateContractBasic";    //更新合同信息
        String SAVE_CONTRACT = "saveContract";// 保存合同基础信息

        String SQL_ID_FIND_PD_DETAIL = "findContractPdDetail";//合同产品详细信息的查看
        String SQL_ID_FIND_CONTRACT_PRODUCTS = "findContractProducts";
        String SQL_ID_FIND_PD = "findPD";//check  同一个ocntractId  是否相同的pdCode

        String SQL_ID_FIND_PACKING = "findContractPacking";//合同包材信息查询
        String SQL_ID_MODIFY_PACKING = "modifyPacking";//update包材信息
        String SQL_ID_SAVE_CONTRACT_PACKAGE_M = "saveContractPackageM";//save合同产品交货计划信息
        String SQL_ID_DEL_PACAKGEM = "delContractPackgeM";//删除合同包材
        String SQL_ID_FIND_PACK = "findPack";//check  同一个ocntractId  是否相同的pdCode 在包材中

        String SQL_ID_FIND_DELIVERY_PLAN = "findDeliveryPlan";//合同产品交货计划信息查询
        String SQL_ID_FIND_DELIVERY_BATCH = "findDeliveryBatch";//合同产品交货计划Code查询
        String SQL_ID_SAVE_DELIVERY_PLAN = "saveDeliveryPlan";//save合同产品交货计划信息
        String SQL_ID_MODIFY_DELIVERY_PLAN = "modifyDeliveryPlan";//update合同产品交货计划信息

        String SQL_ID_SAVE_CONTRACT_BUSSINESS = "saveContractBussiness";//添加合同商务条款
        String SQL_ID_UPDATE_CONTRACT_BUSSINESS = "updateContractBussiness";//修改合同商务条款
        String SQL_ID_FIND_CONTRACT_BUSSINESS = "findContractBussiness";//修改合同商务条款

        String SQL_ID_SAVE_CHECK_EFFECT_BOX_NUM = "checkEffecBoxNum";//检查交货发送的箱数
        String SQL_ID_FIND_CONTRACT_CODE = "findDBContractCode";// 查询最大合同编号
        String SQL_ID_FIND_BATCHDATE = "findBatchDate";// 查询交货期计划

        String SQL_ID_FIND_EXISTENT_DPS = "findExistentDps";
        String SQL_ID_BATCH_CREATE_DPS = "batchCreateDps";
        String SQL_ID_BATCH_UPDATE_DPS = "batchUpdateDps";

        String SQL_ID_ENABLE_TO_MODIFY = "enableToModify";
        String SQL_ID_FIND_MAX_BATCH_CODE = "findMaxBatchCode";
        String SQL_ID_UPDATE_CONTRACT_AMOUNT = "updateContractAmount";
    }
}
