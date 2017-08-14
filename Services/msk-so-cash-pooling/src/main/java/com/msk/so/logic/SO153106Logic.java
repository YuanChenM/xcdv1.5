package com.msk.so.logic;

import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import com.msk.so.bean.SO153106Param;
import com.msk.so.utils.CheckUtil;

/**
 * 买家付款详细
 */
@Service
public class SO153106Logic extends BaseLogic {

    private static Logger logger = LoggerFactory.getLogger(SO153106Logic.class);

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
     * @param so153106Param
     */
    @Transactional
    public void updateAmount(SO153106Param so153106Param) {
        // check 支付类型
        Integer amountType = so153106Param.getAmountType();
        if (StringUtil.isEmpty(StringUtil.toSafeString(amountType))) {
            throw new BusinessException("支付类型不能为空");
        }
        // check 支付方式
        Integer paidType = so153106Param.getPaidType();
        if (StringUtil.isEmpty(StringUtil.toSafeString(paidType))) {
            throw new BusinessException("支付方式不能为空");
        }
        // check 退款编码
        String refundCode = so153106Param.getRefundCode();
        if (amountType.equals(CapitalPoolConst.AmountType.REFUND) && StringUtil.isEmpty(refundCode)) {
            throw new BusinessException("退货时退款编码不能为空");
        }
        // check 支付流水号
        // String paidSeq = so153106Param.getPaidSeq();
        // if (StringUtil.isEmpty(paidSeq)) {
        // throw new BusinessException("支付流水号不能为空");
        // }
        // check 日期
        Date operateDate = null;
        String operateTime = so153106Param.getOperateTime();
        if (!StringUtil.isNullOrEmpty(operateTime)) {
            operateDate = DateTimeUtil.parseDate(operateTime, CheckUtil.FORMAT_YYYYMMDD_HHMMSS);
            if (null == operateDate) {
                throw new BusinessException("日期格式不正确");
            }
        } else {
            throw new BusinessException("日期不能为空");
        }
        // 处理支付金额
        String paidAmount = so153106Param.getPaidAmount();
        if (!StringUtil.isEmpty(paidAmount)) {
            BigDecimal paidAmountB = StringUtil.toBigDecimal(paidAmount);
            if (null != paidAmountB && paidAmountB.compareTo(BigDecimal.ZERO) != NumberConst.IntDef.INT_ZERO) {
                updatePaidAmount(so153106Param);
            } else {
                throw new BusinessException("金额数据不合理");
            }
        } else {
            throw new BusinessException("金额不能为空");
        }
    }

    /**
     * 更新支付金额
     *
     * @param so153106Param
     */
    @Transactional
    private void updatePaidAmount(SO153106Param so153106Param) {
        // 类型转换
        BigDecimal paidAmount = StringUtil.toBigDecimal(so153106Param.getPaidAmount());
        BigDecimal oldPaidAmount = StringUtil.toBigDecimal(so153106Param.getOldPaidAmount());
        Date operateDate = DateTimeUtil.parseDate(so153106Param.getOperateTime(), CheckUtil.FORMAT_YYYYMMDD_HHMMSS);

        // 买家交易详细信息
        String buyerBillId = so153106Param.getBuyerBillId();
        SO153101Bean soBuyerBill = this.so153101Logic.findBuyerById(buyerBillId);
        if (null != soBuyerBill && soBuyerBill.getVer().equals(so153106Param.getVer())) {
            // 交易关闭
            String transFlg = soBuyerBill.getTransFlg();
            if (transFlg.equals(CapitalPoolConst.TransFlg.CLOSED)) {
                throw new BusinessException("该交易已经关闭，不可修改");
            }
            // 更新 so_cp_running
            SoCpRunning soCpRunning = new SoCpRunning();
            soCpRunning.setRunningId(so153106Param.getRunningId());
            soCpRunning.setAmountType(so153106Param.getAmountType());
            soCpRunning.setBillId(StringUtil.toLong(buyerBillId));
            soCpRunning.setTransCode(soBuyerBill.getTransCode());
            soCpRunning.setTransType(StringUtil.toInteger(soBuyerBill.getTransType()));
            soCpRunning.setOrderId(soBuyerBill.getOrderId());
            soCpRunning.setBackType(CapitalPoolConst.BackType.BUYER);
            soCpRunning.setRefundCode(so153106Param.getRefundCode());
            soCpRunning.setPaidAmount(paidAmount);
            soCpRunning.setPaidType(so153106Param.getPaidType());
            soCpRunning.setPaidSeq(so153106Param.getPaidSeq());
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
            soCpRunning.setRemark(so153106Param.getRemark());
            soCpRunning.setHandler(so153106Param.getHandler());
            soCpRunning.setUpdId(so153106Param.getEmplId());
            soCpRunning.setUpdTime(DateTimeUtil.getCustomerDate());
            transLogic.modifyRunning(soCpRunning);
            // 变更 so_cp_buyer_bill
            SoCpBuyerBill soCpBuyerBill = new SoCpBuyerBill();
            soCpBuyerBill.setBuyerBillId(StringUtil.toLong(so153106Param.getBuyerBillId()));

            // 原有金额的还原
            if (CapitalPoolConst.AmountType.PAID == so153106Param.getOldAmountType()) {
                // 实际付款金额 -= 支付金额
                if (null != soBuyerBill.getPaid()) {
                    soBuyerBill.setPaid(soBuyerBill.getPaid().subtract(oldPaidAmount));
                } else {
                    throw new BusinessException("实际付款金额数据异常");
                }
            } else {
                // 实际退款金额 -= 支付金额
                if (null != soBuyerBill.getRealRefund()) {
				/* Modify for Bug #3719 by li_huiqian on 20161124 start */
                    soBuyerBill.setRealRefund(soBuyerBill.getRealRefund().subtract(oldPaidAmount));
				/* Modify for Bug #3719 by li_huiqian on 20161124 end */
                } else {
                    throw new BusinessException("实际退款金额数据异常");
                }
            }

            BigDecimal resultB = null;
            if (CapitalPoolConst.AmountType.PAID == so153106Param.getAmountType()) {
                // 实际付款金额 += 支付金额
                if (null != soBuyerBill.getPaid()) {
                    soCpBuyerBill.setPaid(soBuyerBill.getPaid().add(paidAmount));
                } else {
                    soCpBuyerBill.setPaid(paidAmount);
                }
				/* Modify for Bug #3719 by li_huiqian on 20161124 start */
                soCpBuyerBill.setRealRefund(soBuyerBill.getRealRefund());
				/* Modify for Bug #3719 by li_huiqian on 20161124 end */
                // 计算状态变更
                // 买家应付金额 - 实际付款金额 - 退款金额集计 + 实际退款金额 - 减免金额
                resultB = soBuyerBill.getDue().subtract(soCpBuyerBill.getPaid()).subtract(soBuyerBill.getRefundable())
                    .add(soBuyerBill.getRealRefund()).subtract(soBuyerBill.getReliefAmount());
            } else {
                // 实际退款金额 += 支付金额
                if (null != soBuyerBill.getRealRefund()) {
                    soCpBuyerBill.setRealRefund(soBuyerBill.getRealRefund().add(paidAmount));
                } else {
                    soCpBuyerBill.setRealRefund(paidAmount);
                }
				/* Modify for Bug #3719 by li_huiqian on 20161124 start */
                soCpBuyerBill.setPaid(soBuyerBill.getPaid());
				/* Modify for Bug #3719 by li_huiqian on 20161124 end */
                // 计算状态变更
                // 买家应付金额 - 实际付款金额 - 退款金额集计 + 实际退款金额 - 减免金额
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

            soCpBuyerBill.setUpdId(so153106Param.getEmplId());
            soCpBuyerBill.setUpdTime(DateTimeUtil.getCustomerDate());
            super.modify(soCpBuyerBill);
        } else {
            throw new BusinessException("数据已被修改，请重新刷新页面");
        }
    }
}
