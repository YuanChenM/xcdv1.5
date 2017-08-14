package com.msk.ssc.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.business.constant.SscConstant;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.SscContractBasic;
import com.msk.ssc.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xia_xiaojie on 2016/8/9.
 */
@Service
public class SSC11322Logic extends BaseLogic {

    @Autowired
    private CommonLogic commonLogic;
    @Autowired
    private SSC11304Logic ssc11304Logic;
    @Autowired
    private SSC11321Logic ssc11321Logic;

    /**
     * SQL ID常量接口
     */
    private interface SqlId {
        String FIND_DELIVERIES_BY_CONTRACT_ID = "findDeliveriesByContractId";
        String FIND_INTO_STORES_BY_DELIVERY_IDS = "findIntoStoresByDeliveryIds";
        String FIND_TRUNK_FREIGHT_BY_DELIVERY_IDS = "findTrunkFreightByDeliveryIds";
        String FIND_ACTUAL_PAID_BY_DELIVERY_IDS = "findActualPaidByDeliveryIds";
        String FIND_FIRST_PAID_BY_DELIVERY_IDS = "findFirstPaidByDeliveryIds";
        String FIND_VERIFICATION_ID_BY_CONTRACT_ID = "findVerificationIdByContractId";

        String UPDATE_VERIFICATION_DETAILS = "updateVerificationDetails";
        String DELETE_VERIFICATION_DETAILS = "deleteVerificationDetails";
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
     * 根据合同ID，查询运费不按到岸价结算的发货订单
     */
    @Transactional(readOnly = true)
    public List<SSC11305RsBean> findDeliveriesByContractId(SSC11305RsParam ssc11305RsParam) {
        return super.findList(SqlId.FIND_DELIVERIES_BY_CONTRACT_ID, ssc11305RsParam);
    }

    /**
     * 根据发货订单ID，批量查询入库单
     */
    @Transactional(readOnly = true)
    public List<SSC11316Bean> findIntoStoresByDeliveryIds(SSC11316Param ssc11316Param) {
        return super.findList(SqlId.FIND_INTO_STORES_BY_DELIVERY_IDS, ssc11316Param);
    }

    /**
     * 根据发货订单ID，批量查询产品的干线运费
     */
    @Transactional(readOnly = true)
    public List<SSC11306RsBean> findTrunkFreightByDeliveryIds(SSC11306RsParam ssc11306RsParam) {
        return super.findList(SqlId.FIND_TRUNK_FREIGHT_BY_DELIVERY_IDS, ssc11306RsParam);
    }

    /**
     * 根据发货订单ID，批量查询实际需要支付的运费
     */
    @Transactional(readOnly = true)
    public List<SSC11322Bean> findActualPaidByDeliveryIds(SSC11322Param ssc11322Param) {
        return super.findList(SqlId.FIND_ACTUAL_PAID_BY_DELIVERY_IDS, ssc11322Param);
    }

    /**
     * 根据发货订单ID，计算预付款按比例已支付金额，即预付款用于抵扣的货款的部分
     */
    @Transactional(readOnly = true)
    public List<SSC11322Bean> findFirstPaidByDeliveryIds(List<Long> deliveryIds) {
        SSC11322Param ssc11322Param = new SSC11322Param();
        ssc11322Param.setDeliveryIds(deliveryIds);
        return super.findList(SqlId.FIND_FIRST_PAID_BY_DELIVERY_IDS, ssc11322Param);
    }

    /**
     * 新增或更新核销单及其详情
     */
    @Transactional
    public int saveOrUpdateVerification(SSC11321RsBean ssc11321RsBean) {
        Long verificationId = ssc11321RsBean.getVerificationId();
        int count = NumberConst.IntDef.INT_ZERO;
        if (null == verificationId) {
            count = this.saveVerification(ssc11321RsBean);
            this.saveVerificationDetails(ssc11321RsBean);
            this.updateContractStatusToPV(ssc11321RsBean);
        }
        else {
            count = this.updateVerification(ssc11321RsBean);
            this.crudVerificationDetails(ssc11321RsBean);
        }
        return count;
    }

    /**
     * 根据合同ID，更新合同状态为待审核
     */
    @Transactional
    private int updateContractStatusToPV(SSC11321RsBean ssc11321RsBean) {
        SscContractBasic contract = new SscContractBasic();
        contract.setContractStatus(StringUtil.toString(SscConstant.SscOrderStatus.PENDING_VERIF));
        contract.setContractId(ssc11321RsBean.getContractId());
        return ssc11304Logic.updateContractBasic(contract);
    }

    @Transactional(readOnly = true)
    private Long findVerificationIdByContractId(long contractId) {
        BaseParam baseParam = new BaseParam();
        baseParam.setFilterObject("contractId", contractId);
        Object obj = super.findObject(SqlId.FIND_VERIFICATION_ID_BY_CONTRACT_ID, baseParam);
        if (null == obj) {
            return null;
        }
        return (Long) obj;
    }

    @Transactional
    private synchronized int saveVerification(SSC11321RsBean ssc11321RsBean) {
        if (this.existVerification(ssc11321RsBean)) {
            throw new BusinessException("当前核销单已经被别人添加了，请重新加载数据进行修改！");
        }
        String str = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, new Date());
        Date today = DateTimeUtil.parseDate(str, DateTimeUtil.FORMAT_DATE_YYYYMMDD);

        ssc11321RsBean.setVerificationId(commonLogic.maxId("ssc_verification_for_contract", "VERIFICATION_ID"));
        ssc11321RsBean.setVerificationCode(ssc11321Logic.buildMaxVerificationCode());
        ssc11321RsBean.setStatus(SscConstant.VerificationStatus.UNTREATED);
        ssc11321RsBean.setVerificationDate(today);
        return ssc11321Logic.save(ssc11321RsBean);
    }

    @Transactional(readOnly = true)
    private boolean existVerification(SSC11321RsBean ssc11321RsBean) {
        SSC11321RsParam ssc11321RsParam = new SSC11321RsParam();
        ssc11321RsParam.setContractId(StringUtil.toString(ssc11321RsBean.getContractId()));
        int count = ssc11321Logic.countVerifications(ssc11321RsParam);
        if (count > NumberConst.IntDef.INT_ZERO) {
            return true;
        }
        return false;
    }

    @Transactional
    private int updateVerification(SSC11321RsBean ssc11321RsBean) {
        if (null != ssc11321RsBean.getVer()) {
            String[] primaryKey = {"VERIFICATION_ID"};
            Object[] primaryKeyValue = {ssc11321RsBean.getVerificationId()};
            super.versionValidator("ssc_verification_for_contract", primaryKey, primaryKeyValue, ssc11321RsBean.getVer());
        }

        if (!StringUtil.isEmpty(ssc11321RsBean.getVerificationType())) {
            ssc11321RsBean.setStatus(SscConstant.VerificationStatus.PENDING_CONFIRM);
        }
        ssc11321RsBean.setAuditStatus(SscConstant.VerificationAuditStatus.PENDING_CONFIRM);
        ssc11321RsBean.setUpdTime(DateTimeUtil.getCustomerDate());
        return ssc11321Logic.modify(ssc11321RsBean);
    }

    @Transactional
    private int saveVerificationDetails(SSC11321RsBean ssc11321RsBean) {
        long verificationId = ssc11321RsBean.getVerificationId();
        List<SSC11322Bean> ssc11322Beans = ssc11321RsBean.getVerificationDetails();

        if (CollectionUtils.isEmpty(ssc11322Beans)) {
            return NumberConst.IntDef.INT_ZERO;
        }

        Date now = DateTimeUtil.getCustomerDate();
        for (SSC11322Bean ssc11322Bean : ssc11322Beans) {
            ssc11322Bean.setVerificationDetailId(commonLogic.maxId("ssc_verification_for_contract_detail", "VERIFICATION_DETAIL_ID"));
            ssc11322Bean.setVerificationId(verificationId);
            ssc11322Bean.setCrtTime(now);
        }
        return super.batchSave(ssc11322Beans);
    }

    /**
     * 新增、更新或删除核销单明细
     */
    @Transactional
    private void crudVerificationDetails(SSC11321RsBean ssc11321RsBean) {
        long verificationId = ssc11321RsBean.getVerificationId();
        List<SSC11322Bean> ssc11322Beans = ssc11321RsBean.getVerificationDetails();
        if (CollectionUtils.isEmpty(ssc11322Beans)) {
            ssc11322Beans = new ArrayList<SSC11322Bean>();
        }

        List<Long> verificationDetailIds = new ArrayList<Long>();
        List<SSC11322Bean> insertList = new ArrayList<SSC11322Bean>();
        List<SSC11322Bean> updateList = new ArrayList<SSC11322Bean>();
        List<SSC11322Bean> deleteList = new ArrayList<SSC11322Bean>();

        for (SSC11322Bean ssc11322Bean : ssc11322Beans) {
            Long verificationDetailId = ssc11322Bean.getVerificationDetailId();
            if (null == verificationDetailId) {
                insertList.add(ssc11322Bean);
            }
            else {
                verificationDetailIds.add(verificationDetailId);
                updateList.add(ssc11322Bean);
            }
        }

        SSC11322Param ssc11322Param = new SSC11322Param();
        ssc11322Param.setVerificationId(verificationId);
        List<SSC11322Bean> db11322Beans = super.findPageList(ssc11322Param, SSC11322Bean.class);
        for (SSC11322Bean db11322Bean : db11322Beans) {
            if (!verificationDetailIds.contains(db11322Bean.getVerificationDetailId())) {
                deleteList.add(db11322Bean);
            }
        }

        if (!CollectionUtils.isEmpty(insertList)) {
            for (SSC11322Bean bean : insertList) {
                bean.setVerificationDetailId(commonLogic.maxId("ssc_verification_for_contract_detail", "VERIFICATION_DETAIL_ID"));
                bean.setVerificationId(verificationId);
            }

            SSC11322Bean crudBean = new SSC11322Bean();
            crudBean.setVerificationDetails(insertList);
            super.batchSave(insertList);
        }

        if (!CollectionUtils.isEmpty(updateList)) {
            SSC11322Bean crudBean = new SSC11322Bean();
            crudBean.setVerificationDetails(updateList);
            this.updateVerificationDetails(crudBean);
        }

        if (!CollectionUtils.isEmpty(deleteList)) {
            SSC11322Bean crudBean = new SSC11322Bean();
            crudBean.setVerificationDetails(deleteList);
            this.deleteVerificationDetails(crudBean);
        }
    }

    @Transactional
    public int updateVerificationDetails(SSC11322Bean ssc11322Bean) {
        List<SSC11322Bean> ssc11322Beans = ssc11322Bean.getVerificationDetails();
        Date now = DateTimeUtil.getCustomerDate();
        for (SSC11322Bean bean : ssc11322Beans) {
            bean.setUpdTime(now);
        }
        return super.modify(SqlId.UPDATE_VERIFICATION_DETAILS, ssc11322Bean);
    }

    @Transactional
    private int deleteVerificationDetails(SSC11322Bean ssc11322Bean) {
        List<SSC11322Bean> ssc11322Beans = ssc11322Bean.getVerificationDetails();
        Date now = DateTimeUtil.getCustomerDate();
        for (SSC11322Bean bean : ssc11322Beans) {
            bean.setUpdTime(now);
        }
        return super.modify(SqlId.DELETE_VERIFICATION_DETAILS, ssc11322Bean);
    }

    /**
     * 关闭合同和核销单
     */
    @Transactional
    public int closeContract(SSC11322Bean ssc11322Bean) {
        SSC11321RsBean ssc11321RsBean = new SSC11321RsBean();
        ssc11321RsBean.setVerificationId(ssc11322Bean.getVerificationId());
        ssc11321RsBean.setStatus(SscConstant.VerificationStatus.CLOSED);
        ssc11321RsBean.setUpdId(ssc11322Bean.getUpdId());
        ssc11321RsBean.setVer(ssc11322Bean.getVer());
        int count = ssc11321Logic.updateVerification(ssc11321RsBean);

        SscContractBasic contract = new SscContractBasic();
        contract.setContractId(ssc11322Bean.getContractId());
        contract.setContractStatus(StringUtil.toString(SscConstant.SscOrderStatus.FINISHED));
        contract.setUpdId(ssc11322Bean.getUpdId());
        contract.setUpdTime(DateTimeUtil.getCustomerDate());
        ssc11304Logic.updateContractBasic(contract);
        return count;
    }

}
