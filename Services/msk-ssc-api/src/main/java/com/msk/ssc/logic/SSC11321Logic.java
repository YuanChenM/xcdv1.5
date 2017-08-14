package com.msk.ssc.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.business.constant.SscConstant;
import com.msk.core.entity.SscContractBasic;
import com.msk.ssc.bean.SSC11321RsBean;
import com.msk.ssc.bean.SSC11321RsParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 发货订单一览Logic
 *
 */
@Service
public class SSC11321Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SSC11321Logic.class);

    interface SqlId {
        String FIND_MAX_VERIFICATION_CODE = "findMaxVerificationCode";
        String UPDATE_AUDIT_STATUS = "updateAuditStatus";
        String DELETE_VERIFICATION = "deleteVerification";
    }

    @Autowired
    private SSC11304Logic ssc11304Logic;

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 批量查询核销单
     */
    @Transactional(readOnly = true)
    public PageResult<SSC11321RsBean> findVerifications(SSC11321RsParam ssc11321RsParam) {
        this.setDelFlg(ssc11321RsParam);
        return super.findPage(ssc11321RsParam, SSC11321RsBean.class);
    }

    /**
     * 计数核销单
     */
    @Transactional(readOnly = true)
    public int countVerifications(SSC11321RsParam ssc11321RsParam) {
        this.setDelFlg(ssc11321RsParam);
        return super.getPageCount(ssc11321RsParam);
    }

    /**
     * 设置删除标识的值
     */
    private void setDelFlg(SSC11321RsParam ssc11321RsParam) {
        String status = ssc11321RsParam.getStatus();
        String[] statusArr = ssc11321RsParam.getStatusArr();
        String[] contractStatusArr = ssc11321RsParam.getContractStatusArr();
        String verificationId = ssc11321RsParam.getVerificationId();
        String verificationCode = ssc11321RsParam.getVerificationCode();

        boolean statusEmpty = StringUtil.isEmpty(status);
        boolean statusArrEmpty = (null == statusArr || NumberConst.IntDef.INT_ZERO == statusArr.length);
        boolean contractStatusArrEmpty = (null == contractStatusArr || NumberConst.IntDef.INT_ZERO == contractStatusArr.length);
        boolean verificationIdCodeEmpty = (StringUtil.isEmpty(verificationId) && StringUtil.isEmpty(verificationCode));

        //若核销单状态不为已删除
        if (!statusEmpty) {
            final String str = StringUtil.toString(SscConstant.VerificationStatus.DELETED);
            if (!status.equals(str)) {
                ssc11321RsParam.setDelFlg(SystemConst.DelFlg.ENABLE);
            }
        }
        //若核销单状态不包含已删除
        else if (!statusArrEmpty) {
            final String str = StringUtil.toString(SscConstant.VerificationStatus.DELETED);
            List<String> statusList = Arrays.asList(statusArr);
            if (!statusList.contains(str)) {
                ssc11321RsParam.setDelFlg(SystemConst.DelFlg.ENABLE);
            }
        }

        //若合同状态不包含废弃
        if (!contractStatusArrEmpty) {
            final String str = StringUtil.toString(SscConstant.SscOrderStatus.ABANDON);
            List<String> contractStatusList = Arrays.asList(str);
            if (!contractStatusList.contains(str)) {
                ssc11321RsParam.setDelFlg(SystemConst.DelFlg.ENABLE);
            }
            else {
                ssc11321RsParam.setDelFlg(null);
            }
        }

        if (statusEmpty && statusArrEmpty && contractStatusArrEmpty && verificationIdCodeEmpty) {
            ssc11321RsParam.setDelFlg(SystemConst.DelFlg.ENABLE);
        }
    }

    /**
     * 生成最大的核销单编号
     */
    @Transactional(readOnly = true)
    public String buildMaxVerificationCode() {
        String maxCode = "HX" + new SimpleDateFormat("yyyyMMdd").format(new Date());
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("verificationCode", maxCode + "%");
        Object obj = super.findObject(SqlId.FIND_MAX_VERIFICATION_CODE, baseParam);

        if (null == obj) {
            maxCode += "0001";
        }
        else {
            String code = String.valueOf(obj);
            int len = code.length();
            String last4 = code.substring(len-4, len);
            int max = Integer.parseInt(last4) + 1;
            maxCode += String.format("%04d", max);
        }
        return maxCode;
    }

    /**
     * 根据核销单ID，更新审核状态
     */
    @Transactional
    public int updateAuditStatus(SSC11321RsBean ssc11321RsBean) {
        if (null != ssc11321RsBean.getVer()) {
            String[] primaryKey = {"VERIFICATION_ID"};
            Object[] primaryKeyValue = {ssc11321RsBean.getVerificationId()};
            super.versionValidator("ssc_verification_for_contract", primaryKey, primaryKeyValue, ssc11321RsBean.getVer());
        }
        ssc11321RsBean.setUpdTime(DateTimeUtil.getCustomerDate());
        return super.modify(SqlId.UPDATE_AUDIT_STATUS, ssc11321RsBean);
    }

    @Transactional
    public int updateVerification(SSC11321RsBean ssc11321RsBean) {
        if (null != ssc11321RsBean.getVer()) {
            String[] primaryKey = {"VERIFICATION_ID"};
            Object[] primaryKeyValue = {ssc11321RsBean.getVerificationId()};
            super.versionValidator("ssc_verification_for_contract", primaryKey, primaryKeyValue, ssc11321RsBean.getVer());
        }
        ssc11321RsBean.setUpdTime(DateTimeUtil.getCustomerDate());
        return super.modify(ssc11321RsBean);
    }

    @Transactional
    public int deleteVerification(SSC11321RsBean ssc11321RsBean) {
        if (null != ssc11321RsBean.getVer()) {
            String[] primaryKey = {"VERIFICATION_ID"};
            Object[] primaryKeyValue = {ssc11321RsBean.getVerificationId()};
            super.versionValidator("ssc_verification_for_contract", primaryKey, primaryKeyValue, ssc11321RsBean.getVer());
        }
        ssc11321RsBean.setUpdTime(DateTimeUtil.getCustomerDate());
        int count = super.modify(SqlId.DELETE_VERIFICATION, ssc11321RsBean);

        //若合同状态不为废弃，则将合同状态从待核销更新为已生效
        final String abandon = StringUtil.toString(SscConstant.SscOrderStatus.ABANDON);
        if (!ssc11321RsBean.getContractStatus().equals(abandon)) {
            SscContractBasic contract = new SscContractBasic();
            contract.setContractId(ssc11321RsBean.getContractId());
            contract.setContractStatus(StringUtil.toString(SscConstant.SscOrderStatus.EFFECTIVE));
            contract.setUpdId(ssc11321RsBean.getUpdId());
            ssc11304Logic.updateContractBasic(contract);
        }
        return count;
    }

}


