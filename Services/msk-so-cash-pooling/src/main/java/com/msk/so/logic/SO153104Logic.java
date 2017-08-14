package com.msk.so.logic;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.consts.CapitalPoolConst;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.SoCpBuyerBill;
import com.msk.core.entity.SoCpRunning;
import com.msk.so.bean.SO153101Bean;
import com.msk.so.bean.SO153104Param;
import com.msk.so.utils.CheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class SO153104Logic extends BaseLogic {

    private static Logger logger = LoggerFactory.getLogger(SO153104Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private SO153101Logic so153101Logic;

    @Autowired
    private CommonLogic commonLogic;

    @Autowired
    private TransactionLogic transLogic;

    /**
     * 处理支付金额验证
     *
     * @param so153104Param
     */
    @Transactional
    public void updateAmount(SO153104Param so153104Param) {
        // check 支付类型
        Integer amountType = so153104Param.getAmountType();
        if (StringUtil.isEmpty(StringUtil.toSafeString(amountType))) {
            throw new BusinessException("支付类型不能为空");
        }
        // check 支付方式
        Integer paidType = so153104Param.getPaidType();
        if (StringUtil.isEmpty(StringUtil.toSafeString(paidType))) {
            throw new BusinessException("支付方式不能为空");
        }
        // check 退款编码
        String backNo = so153104Param.getBackNo();
        if (amountType.equals(CapitalPoolConst.AmountType.REFUND) && StringUtil.isEmpty(backNo)) {
            throw new BusinessException("退货时退款编码不能为空");
        }
        // check 支付流水号
        // String paidSeq = so153104Param.getPaidSeq();
        // if (StringUtil.isEmpty(paidSeq)) {
        // throw new BusinessException("支付流水号不能为空");
        // }
        // check 日期
        Date operateDate = null;
        String operateTime = so153104Param.getOperateTime();
        if (!StringUtil.isNullOrEmpty(operateTime)) {
            operateDate = DateTimeUtil.parseDate(operateTime, CheckUtil.FORMAT_YYYYMMDD_HHMMSS);
            if (null == operateDate) {
                throw new BusinessException("日期格式不正确");
            }
        } else {
            throw new BusinessException("日期不能为空");
        }
        // 处理支付金额
        String paidAmount = so153104Param.getPaidAmount();
        if (!StringUtil.isEmpty(paidAmount)) {
            BigDecimal paidAmountB = StringUtil.toBigDecimal(paidAmount);
            if (null != paidAmountB && paidAmountB.compareTo(BigDecimal.ZERO) != NumberConst.IntDef.INT_ZERO) {
                dealPaidAmount(paidAmountB, operateDate, so153104Param);
            } else {
                throw new BusinessException("金额数据不合理");
            }
        } else {
            throw new BusinessException("金额不能为空");
        }
    }

    /**
     * 处理支付金额
     *
     * @param paidAmountB
     * @param operateDate
     * @param so153104Param
     */
    @Transactional
    private void dealPaidAmount(BigDecimal paidAmountB, Date operateDate, SO153104Param so153104Param) {
        // 买家交易详细信息
        String buyerBillId = so153104Param.getBuyerBillId();
        SO153101Bean soBuyerBill = this.so153101Logic.findBuyerById(buyerBillId);
        if (null != soBuyerBill && soBuyerBill.getVer().equals(so153104Param.getVer())) {
            // 交易关闭
            String transFlg = soBuyerBill.getTransFlg();
            if (transFlg.equals(CapitalPoolConst.TransFlg.CLOSED)) {
                throw new BusinessException("该交易已经关闭，不可修改");
            }
            // 新增 so_cp_running
            SoCpRunning soCpRunning = new SoCpRunning();
            Long runningId = commonLogic.maxId("SO_CP_RUNNING", "RUNNING_ID");
            Date now = DateTimeUtil.getCustomerDate();
            soCpRunning.setRunningId(runningId);
            soCpRunning.setAmountType(so153104Param.getAmountType());
            soCpRunning.setBillId(StringUtil.toLong(buyerBillId));
            soCpRunning.setTransCode(soBuyerBill.getTransCode());
            soCpRunning.setTransType(StringUtil.toInteger(soBuyerBill.getTransType()));
            soCpRunning.setOrderId(soBuyerBill.getOrderId());
            soCpRunning.setBackType(CapitalPoolConst.BackType.BUYER);
            soCpRunning.setRefundCode(so153104Param.getBackNo());
            soCpRunning.setPaidAmount(paidAmountB);
            soCpRunning.setPaidType(so153104Param.getPaidType());
            soCpRunning.setPaidSeq(so153104Param.getPaidSeq());
            soCpRunning.setPaidTime(operateDate);

            soCpRunning.setPayerId(soBuyerBill.getBusinessAssistantId());
            String businessAssistantRole = soBuyerBill.getBusinessAssistantRole();
            if (!StringUtil.isNullOrEmpty(businessAssistantRole)) {
                soCpRunning.setPayerRole(Integer.valueOf(businessAssistantRole));
            }

            Integer platform = StringUtil.toInteger(soBuyerBill.getSupplyPlatform());
            if (platform.equals(CapitalPoolConst.SupplyPlatform.SNK)) {
                soCpRunning.setPayeeId(CapitalPoolConst.OtherConst.SNK_ID + "");
                soCpRunning.setPayeeCode(CapitalPoolConst.OtherConst.SNK_ID);
                soCpRunning.setPayeeRole(Integer.valueOf(CapitalPoolConst.RoleType.ROLE_PLATFORM));
            } else if (platform.equals(CapitalPoolConst.SupplyPlatform.MSK)) {
                soCpRunning.setPayeeId(CapitalPoolConst.OtherConst.MSK_ID + "");
                soCpRunning.setPayeeCode(CapitalPoolConst.OtherConst.MSK_ID);
                soCpRunning.setPayeeRole(Integer.valueOf(CapitalPoolConst.RoleType.ROLE_PLATFORM));
            }
            soCpRunning.setPlatform(platform);
            soCpRunning.setRemark(so153104Param.getRemark());
            soCpRunning.setCrtId(so153104Param.getEmplId());
            soCpRunning.setCrtTime(now);
            soCpRunning.setUpdId(so153104Param.getEmplId());
            soCpRunning.setUpdTime(now);
            soCpRunning.setActId(so153104Param.getEmplId());
            soCpRunning.setActTime(now);
            soCpRunning.setHandler(so153104Param.getHandler());
            transLogic.saveRunning(soCpRunning);
            // 变更 so_cp_buyer_bill
            SoCpBuyerBill soCpBuyerBill = new SoCpBuyerBill();
            soCpBuyerBill.setBuyerBillId(StringUtil.toLong(so153104Param.getBuyerBillId()));

            BigDecimal resultB = null;
            if (CapitalPoolConst.AmountType.PAID == so153104Param.getAmountType()) {
                if (null != soBuyerBill.getPaid()) {
                    soCpBuyerBill.setPaid(soBuyerBill.getPaid().add(paidAmountB));
                } else {
                    soCpBuyerBill.setPaid(paidAmountB);
                }
                // 计算状态变更
                resultB = soBuyerBill.getDue().subtract(soCpBuyerBill.getPaid()).subtract(soBuyerBill.getRefundable())
                    .add(soBuyerBill.getRealRefund()).subtract(soBuyerBill.getReliefAmount());
            } else {
                if (null != soBuyerBill.getRealRefund()) {
                    soCpBuyerBill.setRealRefund(soBuyerBill.getRealRefund().add(paidAmountB));
                } else {
                    soCpBuyerBill.setRealRefund(paidAmountB);
                }
                // 计算状态变更
                resultB = soBuyerBill.getDue().subtract(soBuyerBill.getPaid()).subtract(soBuyerBill.getRefundable())
                    .add(soCpBuyerBill.getRealRefund()).subtract(soBuyerBill.getReliefAmount());
            }
            Integer result = resultB.compareTo(BigDecimal.ZERO);
            // 状态变更
            if (result > NumberConst.IntDef.INT_ZERO) {
                soCpBuyerBill.setSettlementStatus(CapitalPoolConst.SettlementStatus.FOR_PAY);
            } else if (result < NumberConst.IntDef.INT_ZERO) {
                soCpBuyerBill.setSettlementStatus(CapitalPoolConst.SettlementStatus.FOR_REFUND);
            } else {
                soCpBuyerBill.setSettlementStatus(CapitalPoolConst.SettlementStatus.BALANCED);
            }

            soCpBuyerBill.setUpdId(so153104Param.getEmplId());
            soCpBuyerBill.setUpdTime(DateTimeUtil.getCustomerDate());
            super.modify(soCpBuyerBill);
        } else {
            throw new BusinessException("数据已被修改，请重新刷新页面");
        }
    }
}
