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
import com.msk.core.entity.SoCpRefund;
import com.msk.core.entity.SoCpTransaction;
import com.msk.so.bean.SO153101Bean;
import com.msk.so.bean.SO153103Param;
import com.msk.so.utils.CheckUtil;
import com.msk.so.utils.SOControllerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class SO153103Logic extends BaseLogic {

    private static Logger logger = LoggerFactory.getLogger(SO153103Logic.class);

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
        String SQL_ID_SAVE_SOCPBUYERBILL = "saveSoCpBuyerBill";
        String SQL_ID_SAVE_SOCPTRANSACTION = "saveSoCpTransaction";
    }

    /**
     * 处理减免金额验证
     *
     * @param so153103Param
     */
    @Transactional
    public void updateAmount(SO153103Param so153103Param) {
        // check 费用类型
        Integer costsAdjusted = so153103Param.getRefundType();
        if (StringUtil.isEmpty(StringUtil.toSafeString(costsAdjusted))) {
            throw new BusinessException("费用类型不能为空");
        }
        // 费用类型 为 0 退货 1 拒收
        else if ((costsAdjusted.equals(CapitalPoolConst.RefundType.GOODSRETURN)
                || costsAdjusted.equals(CapitalPoolConst.RefundType.REJECT))
                && StringUtil.isEmpty(so153103Param.getBackNo())) {
            throw new BusinessException("退货,拒收时参考号不可为空");
        }
        // 费用类型 为 0 退货 1 拒收 3 减免
        if ((costsAdjusted.equals(CapitalPoolConst.RefundType.GOODSRETURN)
                || costsAdjusted.equals(CapitalPoolConst.RefundType.REJECT)
                || costsAdjusted.equals(CapitalPoolConst.RefundType.RELIEF))
                && StringUtil.isEmpty(so153103Param.getSuppCode())) {
            throw new BusinessException("退货,拒收时供应商不可为空");
        }
        // check 日期
        Date operateDate = null;
        String operateTime = so153103Param.getOperateTime();
        if (!StringUtil.isNullOrEmpty(operateTime)) {
            operateDate = DateTimeUtil.parseDate(operateTime, CheckUtil.FORMAT_YYYYMMDD_HHMMSS);
            if (null == operateDate) {
                throw new BusinessException("发生日期格式不正确");
            }
        } else {
            throw new BusinessException("发生日期不能为空");
        }
        // 处理全部取消金额
        String reliefAmount = so153103Param.getReliefAmount();
        if (costsAdjusted.equals(CapitalPoolConst.RefundType.CANCELORDER)) {
            dealReliefAmount(null, operateDate, so153103Param);
        } else if (!StringUtil.isEmpty(reliefAmount)) {
            BigDecimal reliefAmountB = StringUtil.toBigDecimal(reliefAmount);
            if (null != reliefAmountB && reliefAmountB.compareTo(BigDecimal.ZERO) != 0) {
                dealReliefAmount(reliefAmountB, operateDate, so153103Param);
            } else {
                throw new BusinessException("减免金额格式不正确");
            }
        } else {
            throw new BusinessException("减免金额不能为空");
        }
    }

    /**
     * 处理减免金额
     *
     * @param reliefAmountB
     * @param so153103Param
     */
    @Transactional
    private void dealReliefAmount(BigDecimal reliefAmountB, Date operateDate, SO153103Param so153103Param) {
        // 买家交易详细信息
        SO153101Bean soBuyerBill = this.so153101Logic.findBuyerById(so153103Param.getBuyerBillId());
        if (null != soBuyerBill && soBuyerBill.getVer().equals(so153103Param.getVer())) {
            // 交易关闭
            String transFlg = soBuyerBill.getTransFlg();
            if (transFlg.equals(CapitalPoolConst.TransFlg.CLOSED)) {
                throw new BusinessException("该交易已经关闭，不可修改");
            }
            // 费用类型 为 0 退货 1 拒收
            if (so153103Param.getRefundType().equals(CapitalPoolConst.RefundType.GOODSRETURN)
                    || so153103Param.getRefundType().equals(CapitalPoolConst.RefundType.REJECT)) {
                // 调用接口验证 参考号 是否正确
                Integer count = SOControllerUtil.checkCodeBackNo(so153103Param.getBackNo(), soBuyerBill.getTransCode());
                if (count == 0) {
                    throw new BusinessException("退货,拒收时参考号不存在");
                }
            }
            // 验证 费用类型和费用金额是否合理
            Integer costsAdjusted = so153103Param.getRefundType();
            // 全部取消
            if (costsAdjusted.equals(CapitalPoolConst.RefundType.CANCELORDER)) {
                reliefAmountB = soBuyerBill.getDue();
                // 变更 so_cp_transaction
                SoCpTransaction soCpTransaction = new SoCpTransaction();
                soCpTransaction.setTransId(soBuyerBill.getTransId());
                soCpTransaction.setUpdId(so153103Param.getEmplId());
                soCpTransaction.setTransFlg(CapitalPoolConst.TransFlg.CLOSED);
                soCpTransaction.setUpdTime(DateTimeUtil.getCustomerDate());
                super.modify(SqlId.SQL_ID_SAVE_SOCPTRANSACTION, soCpTransaction);
            }
            // 调整金额 不可大于 应付金额
            // else {
            BigDecimal reliefAmountA = reliefAmountB;
            if (null != soBuyerBill.getReliefAmount()) {
                reliefAmountA = reliefAmountA.add(soBuyerBill.getReliefAmount());
            }
            if (null != soBuyerBill.getRefundable()) {
                reliefAmountA = reliefAmountA.add(soBuyerBill.getRefundable());
            }
            if (reliefAmountA.compareTo(soBuyerBill.getDue()) > NumberConst.IntDef.INT_ZERO) {
                throw new BusinessException("调整金额数据不合理");
            }
            // }
            // 新增 so_cp_refund
            SoCpRefund refund = new SoCpRefund();
            Long refundId = commonLogic.maxId("SO_CP_REFUND", "REFUND_ID");
            Date now = DateTimeUtil.getCustomerDate();
            refund.setRefundId(refundId);
            refund.setTransCode(soBuyerBill.getTransCode());
            refund.setTransType(StringUtil.toInteger(soBuyerBill.getTransType()));
            refund.setOrderId(soBuyerBill.getOrderId());
            refund.setRefundCode(so153103Param.getBackNo());
            refund.setRefundTime(operateDate);
            refund.setRefundType(costsAdjusted);
            refund.setRefundAmount(reliefAmountB);
            refund.setPlatform(StringUtil.toInteger(soBuyerBill.getSupplyPlatform()));

            refund.setPayerId(soBuyerBill.getBusinessAssistantId());
            String businessAssistantRole = soBuyerBill.getBusinessAssistantRole();
            if (!StringUtil.isNullOrEmpty(businessAssistantRole)) {
                refund.setPayerRole(Integer.valueOf(businessAssistantRole));
            }

            // 费用类型 为 0 退货 1 拒收 3 减免
            if ((costsAdjusted.equals(CapitalPoolConst.RefundType.GOODSRETURN)
                    || costsAdjusted.equals(CapitalPoolConst.RefundType.REJECT)
                    || costsAdjusted.equals(CapitalPoolConst.RefundType.RELIEF))) {
                String suppCode = so153103Param.getSuppCode();
                String[] suppCodes = suppCode.split("__");
                refund.setPayeeId(suppCodes[NumberConst.IntDef.INT_ZERO]);
                refund.setPayeeCode(suppCodes[NumberConst.IntDef.INT_ONE]);
                refund.setPayeeName(suppCodes[NumberConst.IntDef.INT_TWO]);
                refund.setPayeeRole(StringUtil.toInteger(suppCodes[NumberConst.IntDef.INT_THREE]));
            }

            refund.setCrtId(so153103Param.getEmplId());
            refund.setCrtTime(now);
            refund.setUpdId(so153103Param.getEmplId());
            refund.setUpdTime(now);
            refund.setActId(so153103Param.getEmplId());
            refund.setActTime(now);
            refund.setRemark(so153103Param.getRemark());
            transLogic.saveRefund(refund);
            // 变更 so_cp_buyer_bill
            SoCpBuyerBill soCpBuyerBill = new SoCpBuyerBill();
            soCpBuyerBill.setBuyerBillId(StringUtil.toLong(so153103Param.getBuyerBillId()));

            BigDecimal resultB = soBuyerBill.getDue().subtract(soBuyerBill.getPaid())
                    .subtract(soBuyerBill.getRefundable()).add(soBuyerBill.getRealRefund())
                    .subtract(soBuyerBill.getReliefAmount()).subtract(reliefAmountB);
            Integer result = resultB.compareTo(BigDecimal.ZERO);
            // 状态变更
            if (result > NumberConst.IntDef.INT_ZERO) {
                soCpBuyerBill.setSettlementStatus(CapitalPoolConst.SettlementStatus.FOR_PAY);
            } else if (result < NumberConst.IntDef.INT_ZERO) {
                soCpBuyerBill.setSettlementStatus(CapitalPoolConst.SettlementStatus.FOR_REFUND);
            } else {
                soCpBuyerBill.setSettlementStatus(CapitalPoolConst.SettlementStatus.BALANCED);
            }

            // 减免的情况
            if (costsAdjusted.equals(CapitalPoolConst.RefundType.RELIEF)) {
                // 减免金额
                if (null != soBuyerBill.getReliefAmount()) {
                    soCpBuyerBill.setReliefAmount(soBuyerBill.getReliefAmount().add(reliefAmountB));
                } else {
                    soCpBuyerBill.setReliefAmount(reliefAmountB);
                }
            } else {
                // 应退金额
                if (null != soBuyerBill.getRefundable()) {
                    soCpBuyerBill.setRefundable(soBuyerBill.getRefundable().add(reliefAmountB));
                } else {
                    soCpBuyerBill.setRefundable(reliefAmountB);
                }
            }

            soCpBuyerBill.setUpdId(so153103Param.getEmplId());
            soCpBuyerBill.setUpdTime(DateTimeUtil.getCustomerDate());
            super.modify(SqlId.SQL_ID_SAVE_SOCPBUYERBILL, soCpBuyerBill);
        } else {
            throw new BusinessException("数据已被修改，请重新刷新页面");
        }
    }
}
