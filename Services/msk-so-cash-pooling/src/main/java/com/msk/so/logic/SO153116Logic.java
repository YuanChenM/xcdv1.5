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
import com.msk.core.entity.SoCpRunning;
import com.msk.core.entity.SoCpSellerBill;
import com.msk.so.bean.SOCp153111Bean;
import com.msk.so.bean.SOCp153116Param;
import com.msk.so.utils.CheckUtil;

@Service
public class SO153116Logic extends BaseLogic {

    private static Logger logger = LoggerFactory.getLogger(SO153116Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private SO153111Logic so153111Logic;

    @Autowired
    private CommonLogic commonLogic;

    @Autowired
    private TransactionLogic transLogic;

    /**
     * 处理支付金额验证
     *
     * @param so153116Param
     */
    @Transactional
    public void updateAmount(SOCp153116Param so153116Param) {
        // check 支付类型
        Integer amountType = so153116Param.getAmountType();
        if (StringUtil.isEmpty(StringUtil.toSafeString(amountType))) {
            throw new BusinessException("支付类型不能为空");
        }
        // check 支付方式
        Integer paidType = so153116Param.getPaidType();
        if (StringUtil.isEmpty(StringUtil.toSafeString(paidType))) {
            throw new BusinessException("支付方式不能为空");
        }
        // check 退款编码
        String backNo = so153116Param.getBackNo();
        if (amountType.equals(CapitalPoolConst.AmountType.REFUND) && StringUtil.isEmpty(backNo)) {
            throw new BusinessException("退货时退款编码不能为空");
        }
        // check 支付流水号
        // String paidSeq = so153116Param.getPaidSeq();
        // if (StringUtil.isEmpty(paidSeq)) {
        // throw new BusinessException("支付流水号不能为空");
        // }
        // check 日期
        Date operateDate = null;
        String operateTime = so153116Param.getOperateTime();
        if (!StringUtil.isNullOrEmpty(operateTime)) {
            operateDate = DateTimeUtil.parseDate(operateTime, CheckUtil.FORMAT_YYYYMMDD_HHMMSS);
            if (null == operateDate) {
                throw new BusinessException("日期格式不正确");
            }
        } else {
            throw new BusinessException("日期不能为空");
        }
        // 处理支付金额
        String paidAmount = so153116Param.getPaidAmount();
        if (!StringUtil.isEmpty(paidAmount)) {
            BigDecimal paidAmountB = StringUtil.toBigDecimal(paidAmount);
            if (null != paidAmountB && paidAmountB.compareTo(BigDecimal.ZERO) != NumberConst.IntDef.INT_ZERO) {
                dealPaidAmount(paidAmountB, operateDate, so153116Param);
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
     * @param so153116Param
     */
    @Transactional
    private void dealPaidAmount(BigDecimal paidAmountB, Date operateDate, SOCp153116Param so153116Param) {
        // 买家交易详细信息
        String sellerBillId = so153116Param.getSellerBillId();
        // 卖家交易详细信息
        SOCp153111Bean soSellerBill = this.so153111Logic.findSellerById(sellerBillId);
        if (null != soSellerBill && soSellerBill.getVer().equals(so153116Param.getVer())) {
            // 新增 so_cp_running
            SoCpRunning soCpRunning = new SoCpRunning();
            Long runningId = commonLogic.maxId("SO_CP_RUNNING", "RUNNING_ID");
            Date now = DateTimeUtil.getCustomerDate();
            soCpRunning.setRunningId(runningId);
            soCpRunning.setAmountType(so153116Param.getAmountType());
            soCpRunning.setBillId(StringUtil.toLong(sellerBillId));
            /** Bug #3307 Modified by li_huiqian on 2016/10/13 start */
            soCpRunning.setTransCode(soSellerBill.getSellerBillNo());
            /** Bug #3307 Modified by li_huiqian on 2016/10/13 end */
            soCpRunning.setBackType(CapitalPoolConst.BackType.SELLER);
            soCpRunning.setRefundCode(so153116Param.getBackNo());
            soCpRunning.setPaidAmount(paidAmountB);
            soCpRunning.setPaidType(so153116Param.getPaidType());
            soCpRunning.setPaidSeq(so153116Param.getPaidSeq());
            soCpRunning.setPaidTime(operateDate);
            Integer platform = StringUtil.toInteger(soSellerBill.getSupplyPlatform());
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
            soCpRunning.setRemark(so153116Param.getRemark());
            soCpRunning.setCrtId(so153116Param.getEmplId());
            soCpRunning.setCrtTime(now);
            soCpRunning.setUpdId(so153116Param.getEmplId());
            soCpRunning.setUpdTime(now);
            soCpRunning.setActId(so153116Param.getEmplId());
            soCpRunning.setActTime(now);
            soCpRunning.setHandler(so153116Param.getHandler());
            transLogic.saveRunning(soCpRunning);
            // 变更 so_cp_seller_bill
            SoCpSellerBill soCpSellerBill = new SoCpSellerBill();
            soCpSellerBill.setSellerBillId(soSellerBill.getSellerBillId());

            BigDecimal resultB = null;
            if (CapitalPoolConst.AmountType.PAID == so153116Param.getAmountType()) {
                if (null != soSellerBill.getReceived()) {
                    soCpSellerBill.setReceived(soSellerBill.getReceived().add(paidAmountB));
                } else {
                    soCpSellerBill.setReceived(paidAmountB);
                }
                // 计算状态变更
                resultB = soSellerBill.getReceiveable().subtract(soSellerBill.getRefundable())
                    .subtract(soCpSellerBill.getReceived()).add(soSellerBill.getRealRefund())
                    .subtract(soSellerBill.getAjustAmount());
            } else {
                if (null != soSellerBill.getRealRefund()) {
                    soCpSellerBill.setRealRefund(soSellerBill.getRealRefund().add(paidAmountB));
                } else {
                    soCpSellerBill.setRealRefund(paidAmountB);
                }
                // 计算状态变更
                resultB = soSellerBill.getReceiveable().subtract(soSellerBill.getRefundable())
                    .subtract(soSellerBill.getReceived()).add(soCpSellerBill.getRealRefund())
                    .subtract(soSellerBill.getAjustAmount());
            }

            Integer result = resultB.compareTo(BigDecimal.ZERO);
            // 状态变更
            if (result > NumberConst.IntDef.INT_ZERO) {
                soCpSellerBill.setSettlementStatus(CapitalPoolConst.SettlementStatus.FOR_REFUND);
            } else if (result < NumberConst.IntDef.INT_ZERO) {
                soCpSellerBill.setSettlementStatus(CapitalPoolConst.SettlementStatus.FOR_PAY);
            } else {
                soCpSellerBill.setSettlementStatus(CapitalPoolConst.SettlementStatus.BALANCED);
            }
            soCpSellerBill.setUpdId(so153116Param.getEmplId());
            soCpSellerBill.setUpdTime(DateTimeUtil.getCustomerDate());
            super.modify(soCpSellerBill);
        } else {
            throw new BusinessException("数据已被修改，请重新刷新页面");
        }
    }
}
