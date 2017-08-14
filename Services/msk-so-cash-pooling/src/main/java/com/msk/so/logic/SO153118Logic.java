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
import com.hoperun.core.utils.DecimalUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.consts.CapitalPoolConst;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.SoCpRunning;
import com.msk.core.entity.SoCpSellerBill;
import com.msk.so.bean.SOCp153111Bean;
import com.msk.so.bean.SOCp153118Param;
import com.msk.so.utils.CheckUtil;

@Service
public class SO153118Logic extends BaseLogic {

    private static Logger logger = LoggerFactory.getLogger(SO153118Logic.class);

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
     * @param so153118Param
     */
    @Transactional
    public void updateAmount(SOCp153118Param so153118Param) {
        // check 支付类型
        Integer amountType = so153118Param.getAmountType();
        if (StringUtil.isEmpty(StringUtil.toSafeString(amountType))) {
            throw new BusinessException("支付类型不能为空");
        }
        // check 支付方式
        Integer paidType = so153118Param.getPaidType();
        if (StringUtil.isEmpty(StringUtil.toSafeString(paidType))) {
            throw new BusinessException("支付方式不能为空");
        }
        // check 退款编码
        String refundCode = so153118Param.getRefundCode();
        if (amountType.equals(CapitalPoolConst.AmountType.REFUND) && StringUtil.isEmpty(refundCode)) {
            throw new BusinessException("退货时退款编码不能为空");
        }
        // check 支付流水号
        // String paidSeq = so153118Param.getPaidSeq();
        // if (StringUtil.isEmpty(paidSeq)) {
        // throw new BusinessException("支付流水号不能为空");
        // }
        // check 日期
        Date operateDate = null;
        String operateTime = so153118Param.getOperateTime();
        if (!StringUtil.isNullOrEmpty(operateTime)) {
            operateDate = DateTimeUtil.parseDate(operateTime, CheckUtil.FORMAT_YYYYMMDD_HHMMSS);
            if (null == operateDate) {
                throw new BusinessException("日期格式不正确");
            }
        } else {
            throw new BusinessException("日期不能为空");
        }
        // 处理支付金额
        String paidAmount = so153118Param.getPaidAmount();
        if (!StringUtil.isEmpty(paidAmount)) {
            BigDecimal paidAmountB = StringUtil.toBigDecimal(paidAmount);
            if (null != paidAmountB && paidAmountB.compareTo(BigDecimal.ZERO) != NumberConst.IntDef.INT_ZERO) {
                dealPaidAmount(so153118Param);
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
     */
    @Transactional
    private void dealPaidAmount(SOCp153118Param param) {
        // 类型转换
        BigDecimal paidAmount = StringUtil.toBigDecimal(param.getPaidAmount());
        BigDecimal oldPaidAmount = StringUtil.toBigDecimal(param.getOldPaidAmount());
        Date operateDate = DateTimeUtil.parseDate(param.getOperateTime(), CheckUtil.FORMAT_YYYYMMDD_HHMMSS);

        // 卖家交易详细信息
        SOCp153111Bean soSellerBill = this.so153111Logic.findSellerById(param.getSellerBillId());
        // 检索结果检验
        // 如果检索结果不存在或者版本不正确
        if (soSellerBill == null && soSellerBill.getVer().intValue() != param.getVer()) {
            throw new BusinessException("数据已被修改，请重新刷新页面");
        }

        // 更新 so_cp_running
        SoCpRunning soCpRunning = new SoCpRunning();
        soCpRunning.setRunningId(param.getRunningId());
        soCpRunning.setAmountType(param.getAmountType());
        soCpRunning.setBillId(StringUtil.toLong(param.getSellerBillId()));
        /** Bug #3307 Modified by li_huiqian on 2016/10/13 start */
        soCpRunning.setTransCode(soSellerBill.getSellerBillNo());
        /** Bug #3307 Modified by li_huiqian on 2016/10/13 end */
        soCpRunning.setBackType(CapitalPoolConst.BackType.SELLER);
        soCpRunning.setRefundCode(param.getRefundCode());
        soCpRunning.setPaidAmount(paidAmount);
        soCpRunning.setPaidType(param.getPaidType());
        soCpRunning.setPaidSeq(param.getPaidSeq());
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
        soCpRunning.setRemark(param.getRemark());
        soCpRunning.setHandler(param.getHandler());
        soCpRunning.setUpdId(param.getEmplId());
        soCpRunning.setUpdTime(DateTimeUtil.getCustomerDate());
        transLogic.modifyRunning(soCpRunning);

        // 变更 so_cp_seller_bill
        SoCpSellerBill soCpSellerBill = new SoCpSellerBill();
        soCpSellerBill.setSellerBillId(soSellerBill.getSellerBillId());

        // 原有金额的还原
        if (CapitalPoolConst.AmountType.PAID == param.getOldAmountType()) {
            if (soSellerBill.getReceived() != null) {
                soSellerBill.setReceived(DecimalUtil.subtract(soSellerBill.getReceived(), oldPaidAmount));
            } else {
                throw new BusinessException("实际收款金额数据异常");
            }
        } else {
            if (soSellerBill.getRealRefund() != null) {
                soSellerBill.setRealRefund(DecimalUtil.subtract(soSellerBill.getRealRefund(), oldPaidAmount));
            } else {
                throw new BusinessException("实际退款金额数据异常");
            }
        }

        BigDecimal resultB = null;
        if (CapitalPoolConst.AmountType.PAID == param.getAmountType()) {
            if (null != soSellerBill.getReceived()) {
                soCpSellerBill.setReceived(soSellerBill.getReceived().add(paidAmount));
            } else {
                soCpSellerBill.setReceived(paidAmount);
            }
            // 计算状态变更
            resultB = soSellerBill.getReceiveable().subtract(soSellerBill.getRefundable())
                .subtract(soCpSellerBill.getReceived()).add(soSellerBill.getRealRefund())
                .subtract(soSellerBill.getAjustAmount());
        } else {
            if (null != soSellerBill.getRealRefund()) {
                soCpSellerBill.setRealRefund(soSellerBill.getRealRefund().add(paidAmount));
            } else {
                soCpSellerBill.setRealRefund(paidAmount);
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
        soCpSellerBill.setUpdId(param.getEmplId());
        soCpSellerBill.setUpdTime(DateTimeUtil.getCustomerDate());
        super.modify(soCpSellerBill);
    }
}
