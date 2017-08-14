package com.msk.so.logic;

import java.math.BigDecimal;
import java.util.Date;

import com.hoperun.core.utils.DecimalUtil;
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
import com.msk.core.entity.SoCpRefund;
import com.msk.core.entity.SoCpTransaction;
import com.msk.so.bean.SO153101Bean;
import com.msk.so.bean.SO153105Param;
import com.msk.so.utils.CheckUtil;
import com.msk.so.utils.SOControllerUtil;

@Service
public class SO153105Logic extends BaseLogic {

    private static Logger logger = LoggerFactory.getLogger(SO153105Logic.class);

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
     * SqlId. sql定义
     */
    interface SqlId {
        String SQL_ID_SAVE_SOCPTRANSACTION = "saveSoCpTransaction";
    }

    /**
     * 处理减免金额验证
     *
     * @param so153105Param
     */
    @Transactional
    public void updateAmount(SO153105Param so153105Param) {
        // check 费用类型
        Integer costsAdjusted = so153105Param.getRefundType();
        if (StringUtil.isEmpty(StringUtil.toSafeString(costsAdjusted))) {
            throw new BusinessException("费用类型不能为空");
        }
        // 费用类型 为 0 退货 1 拒收
        else if ((costsAdjusted.equals(CapitalPoolConst.RefundType.GOODSRETURN)
                || costsAdjusted.equals(CapitalPoolConst.RefundType.REJECT))
                && StringUtil.isEmpty(so153105Param.getReferenceCode())) {
            throw new BusinessException("退货,拒收时参考号不可为空");
        }
        // 费用类型 为 0 退货 1 拒收 3 减免
        if ((costsAdjusted.equals(CapitalPoolConst.RefundType.GOODSRETURN)
                || costsAdjusted.equals(CapitalPoolConst.RefundType.REJECT)
                || costsAdjusted.equals(CapitalPoolConst.RefundType.RELIEF))
                && StringUtil.isEmpty(so153105Param.getSuppCode())) {
            throw new BusinessException("退货,拒收时供应商不可为空");
        }
        // check 日期
        Date operateDate = null;
        String operateTime = so153105Param.getOperateTime();
        if (!StringUtil.isNullOrEmpty(operateTime)) {
            operateDate = DateTimeUtil.parseDate(operateTime, CheckUtil.FORMAT_YYYYMMDD_HHMMSS);
            if (null == operateDate) {
                throw new BusinessException("发生日期格式不正确");
            }
        } else {
            throw new BusinessException("发生日期不能为空");
        }
        // 处理全部取消金额
        String reliefAmount = so153105Param.getReliefAmount();
        if (costsAdjusted.equals(CapitalPoolConst.RefundType.CANCELORDER)) {
            updateReliefAmount(so153105Param);
        } else if (!StringUtil.isEmpty(reliefAmount)) {
            BigDecimal reliefAmountB = StringUtil.toBigDecimal(reliefAmount);
            if (null != reliefAmountB && reliefAmountB.compareTo(BigDecimal.ZERO) != 0) {
                updateReliefAmount(so153105Param);
            } else {
                throw new BusinessException("减免金额格式不正确");
            }
        } else {
            throw new BusinessException("减免金额不能为空");
        }
    }

    /**
     * 更新减免金额
     *
     * @param so153105Param
     */
    @Transactional
    private void updateReliefAmount(SO153105Param so153105Param) {
        // 类型转换
        BigDecimal reliefAmount = StringUtil.toBigDecimal(so153105Param.getReliefAmount());
        BigDecimal oldReliefAmount = StringUtil.toBigDecimal(so153105Param.getOldReliefAmount());
        Date operateDate = DateTimeUtil.parseDate(so153105Param.getOperateTime(), CheckUtil.FORMAT_YYYYMMDD_HHMMSS);

        // 获取买家交易详细信息
        SO153101Bean soBuyerBill = this.so153101Logic.findBuyerById(so153105Param.getBuyerBillId());

        // 买家交易详细信息验证
        // 1. 如果买家交易详细信息不存在或者版本不正确
        if (soBuyerBill == null || !soBuyerBill.getVer().equals(so153105Param.getVer())) {
            throw new BusinessException("数据已被修改，请重新刷新页面");
        }
        // 2. 如果交易已经关闭
        if (CapitalPoolConst.TransFlg.CLOSED.equals(soBuyerBill.getTransFlg())) {
            throw new BusinessException("该交易已经关闭，不可修改");
        }
        // 3. 费用类型为 0：退货；1：拒收 时，验证参考号是否正确
        if (CapitalPoolConst.RefundType.GOODSRETURN == so153105Param.getRefundType().intValue()
                || CapitalPoolConst.RefundType.REJECT == so153105Param.getRefundType().intValue()) {
            int count = SOControllerUtil.checkCodeBackNo(so153105Param.getReferenceCode(), soBuyerBill.getTransCode());
            if (count == 0) {
                throw new BusinessException("退货,拒收时参考号不存在");
            }
        }
/*        // 4. 调整金额、减免金额、退款金额的合计 之和 不能大于 应付金额
        BigDecimal calculateDue = reliefAmount;
        if (soBuyerBill.getReliefAmount() != null) {
            calculateDue = DecimalUtil.add(calculateDue, soBuyerBill.getReliefAmount());
        }
        if (soBuyerBill.getRefundable() != null) {
            calculateDue = DecimalUtil.add(calculateDue, soBuyerBill.getRefundable());
        }
        if (DecimalUtil.isGreater(calculateDue, soBuyerBill.getDue())) {
            throw new BusinessException("调整金额数据不合理");
        }*/

        // 费用调整类型为 2：全部取消 时，关闭交易
        if (CapitalPoolConst.RefundType.CANCELORDER == so153105Param.getRefundType().intValue()) {
            reliefAmount = soBuyerBill.getDue();
            // 变更 so_cp_transaction
            SoCpTransaction soCpTransaction = new SoCpTransaction();
            soCpTransaction.setTransId(soBuyerBill.getTransId());
            soCpTransaction.setTransFlg(CapitalPoolConst.TransFlg.CLOSED);
            soCpTransaction.setUpdId(so153105Param.getEmplId());
            soCpTransaction.setUpdTime(DateTimeUtil.getCustomerDate());
            super.modify(SqlId.SQL_ID_SAVE_SOCPTRANSACTION, soCpTransaction);
        }

        // 更新 so_cp_refund
        int refundType = so153105Param.getRefundType();
        SoCpRefund refund = new SoCpRefund();
        refund.setRefundId(so153105Param.getRefundId());
        refund.setTransCode(soBuyerBill.getTransCode());
        refund.setTransType(StringUtil.toInteger(soBuyerBill.getTransType()));
        refund.setOrderId(soBuyerBill.getOrderId());
        refund.setRefundCode(so153105Param.getReferenceCode());
        refund.setRefundTime(operateDate);
        refund.setRefundType(refundType);
        refund.setRefundAmount(reliefAmount);
        refund.setPlatform(StringUtil.toInteger(soBuyerBill.getSupplyPlatform()));
        refund.setPayerId(soBuyerBill.getBusinessAssistantId());
        String businessAssistantRole = soBuyerBill.getBusinessAssistantRole();
        if (!StringUtil.isNullOrEmpty(businessAssistantRole)) {
            refund.setPayerRole(Integer.valueOf(businessAssistantRole));
        }

        // 费用类型 为 0 退货 1 拒收 3 减免
        if (refundType == CapitalPoolConst.RefundType.GOODSRETURN || refundType == CapitalPoolConst.RefundType.REJECT
                || refundType == CapitalPoolConst.RefundType.RELIEF) {
            String suppCode = so153105Param.getSuppCode();
            String[] suppCodes = suppCode.split("__");
            refund.setPayeeId(suppCodes[NumberConst.IntDef.INT_ZERO]);
            refund.setPayeeCode(suppCodes[NumberConst.IntDef.INT_ONE]);
            refund.setPayeeName(suppCodes[NumberConst.IntDef.INT_TWO]);
            refund.setPayeeRole(StringUtil.toInteger(suppCodes[NumberConst.IntDef.INT_THREE]));
        }
        refund.setRemark(so153105Param.getRemark());
        refund.setUpdId(so153105Param.getEmplId());
        refund.setUpdTime(DateTimeUtil.getCustomerDate());
        transLogic.modifyRefund(refund);

        // 变更 so_cp_buyer_bill
        SoCpBuyerBill soCpBuyerBill = new SoCpBuyerBill();
        soCpBuyerBill.setBuyerBillId(StringUtil.toLong(so153105Param.getBuyerBillId()));
        /** Bug #3234 modify by renyi on 2016/10/18 start */
        BigDecimal calculateDue = reliefAmount;
        /** Bug #3234 modify by renyi on 2016/10/18 end */
        // 还原金额
        if (so153105Param.getOldRefundType().intValue() == CapitalPoolConst.RefundType.RELIEF) {
            // 减免金额
            if (soBuyerBill.getReliefAmount() != null) {
                // 4. 调整金额、减免金额、退款金额的合计 之和 不能大于 应付金额
                calculateDue = DecimalUtil.add(calculateDue,DecimalUtil.subtract(soBuyerBill.getReliefAmount(), oldReliefAmount));
                if (soBuyerBill.getRefundable() != null) {
                    calculateDue = DecimalUtil.add(calculateDue, soBuyerBill.getRefundable());
                }
                if (DecimalUtil.isGreater(calculateDue, soBuyerBill.getDue())) {
                    throw new BusinessException("调整金额数据不合理");
                }

                // 计算金额
                if (refundType == CapitalPoolConst.RefundType.RELIEF) {
                    // 减免金额
                    soCpBuyerBill.setReliefAmount(DecimalUtil.add(soBuyerBill.getReliefAmount(), DecimalUtil.subtract(reliefAmount,oldReliefAmount)));
                }else {
                    // 减免金额
                    soCpBuyerBill.setReliefAmount(DecimalUtil.subtract(soBuyerBill.getReliefAmount(), oldReliefAmount));
                    // 应退金额
                    soCpBuyerBill.setRefundable(DecimalUtil.add(soBuyerBill.getRefundable(), reliefAmount));
                }
            } else {
                throw new BusinessException("减免金额数据异常");
            }
        } else {
            // 应退金额
            if (soBuyerBill.getRefundable() != null) {
                // 计算金额
                if (refundType == CapitalPoolConst.RefundType.RELIEF) {
                    // 减免金额
                    soCpBuyerBill.setReliefAmount(DecimalUtil.add(soBuyerBill.getReliefAmount(), reliefAmount));
                    // 应退金额
                    soCpBuyerBill.setRefundable(DecimalUtil.subtract(soBuyerBill.getRefundable(), oldReliefAmount));
                }else {
                    // 应退金额
                    soCpBuyerBill.setRefundable(DecimalUtil.subtract(soBuyerBill.getRefundable(), oldReliefAmount));
                    soCpBuyerBill.setRefundable(DecimalUtil.add(soCpBuyerBill.getRefundable(), reliefAmount));
                }
            } else {
                throw new BusinessException("退款金额的集计数据异常");
            }
        }

        // 应付金额 - 实际付款金额 - 退款金额的集计 + 实际退款金额 - 减免金额 和 0 比较
        BigDecimal result = DecimalUtil.subtract(soBuyerBill.getDue(), soBuyerBill.getPaid());
        result = DecimalUtil.subtract(result, soCpBuyerBill.getRefundable());
        result = DecimalUtil.add(result, soBuyerBill.getRealRefund());
        result = DecimalUtil.subtract(result, soCpBuyerBill.getReliefAmount());
        if (DecimalUtil.isGreater(result, BigDecimal.ZERO)) {
            soCpBuyerBill.setSettlementStatus(CapitalPoolConst.SettlementStatus.FOR_PAY);
        } else if (DecimalUtil.isLess(result, BigDecimal.ZERO)) {
            soCpBuyerBill.setSettlementStatus(CapitalPoolConst.SettlementStatus.FOR_REFUND);
        } else {
            soCpBuyerBill.setSettlementStatus(CapitalPoolConst.SettlementStatus.BALANCED);
        }
        soCpBuyerBill.setUpdId(so153105Param.getEmplId());
        soCpBuyerBill.setUpdTime(DateTimeUtil.getCustomerDate());
        transLogic.modifyBuyerBill(soCpBuyerBill);
    }
}
