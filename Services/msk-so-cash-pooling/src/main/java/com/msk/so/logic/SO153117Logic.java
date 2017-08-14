package com.msk.so.logic;

import java.math.BigDecimal;
import java.util.Date;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DecimalUtil;
import com.msk.core.entity.SoCpBuyerBill;
import com.msk.core.entity.SoCpRefund;
import com.msk.so.bean.SoCpBuyerBillBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.consts.CapitalPoolConst;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.SoCpSellerBill;
import com.msk.core.entity.SoCpSellerBillHis;
import com.msk.so.bean.SOCp153111Bean;
import com.msk.so.bean.SOCp153117Param;
import com.msk.so.utils.CheckUtil;

@Service
public class SO153117Logic extends BaseLogic {

    private static Logger logger = LoggerFactory.getLogger(SO153117Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private SO153111Logic so153111Logic;

    @Autowired
    private TransactionLogic transactionLogic;

    /**
     * SqlId. sql定义
     */
    interface SqlId {
        String SQL_ID_UPDATE_SO_CP_SELLER_BILL_HIS = "updateSoCpSellerBillHis";
        String SQL_ID_FIND_ONE_SO_CP_SELLER_BILL_HIS = "findOneSoCpSellerBillHis";
        String SQL_ID_UPDATE_SO_CP_SELLER_BILL = "updateSoCpSellerBill";
        String SQL_ID_FIND_ONE_SO_CP_BUYER_BILL = "findOneSoCpBuyerBill";
        String SQL_ID_UPDATE_SO_CP_BUYER_BILL = "updateSoCpBuyerBill";
    }

    /**
     * 处理减免金额验证
     *
     * @param so153117Param
     */
    @Transactional
    public void updateAmount(SOCp153117Param so153117Param) throws Exception {
        // check 费用类型
        Integer costsAdjusted = so153117Param.getRefundType();
        if (StringUtil.isEmpty(StringUtil.toSafeString(costsAdjusted))) {
            throw new BusinessException("费用类型不能为空");
        }
        // check 日期
        Date operateDate = null;
        String operateTime = so153117Param.getOperateTime();
        if (!StringUtil.isNullOrEmpty(operateTime)) {
            operateDate = DateTimeUtil.parseDate(operateTime, CheckUtil.FORMAT_YYYYMMDD_HHMMSS);
            if (null == operateDate) {
                throw new BusinessException("发生日期格式不正确");
            }
        } else {
            throw new BusinessException("发生日期不能为空");
        }
        // 处理全部取消金额
        String reliefAmount = so153117Param.getReliefAmount();
        if (!StringUtil.isEmpty(reliefAmount)) {
            BigDecimal reliefAmountB = StringUtil.toBigDecimal(reliefAmount);
            if (null != reliefAmountB && reliefAmountB.compareTo(BigDecimal.ZERO) != 0) {
                dealReliefAmount(so153117Param);
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
     * @param param
     */
    @Transactional
    private void dealReliefAmount(SOCp153117Param param) throws Exception {
        if ("REFUND".equals(param.getTb())) {
            param.setRefundId(param.getId());
            dealRefund(param);
        } else if ("SELLER_BILL_HIS".equals(param.getTb())) {
            param.setSellerBillHisId(param.getId());
            dealSellerBillHis(param);
        }
    }

    /**
     * 处理减免金额(表名为 SO_CP_REFUND)
     *
     * @param param
     */
    @Transactional
    private void dealRefund(SOCp153117Param param) throws Exception {
        // 数据类型转换
        BigDecimal reliefAmount = StringUtil.toBigDecimal(param.getReliefAmount());
        BigDecimal oldReliefAmount = StringUtil.toBigDecimal(param.getOldReliefAmount());
        Date operateDate = DateTimeUtil.parseDate(param.getOperateTime(), CheckUtil.FORMAT_YYYYMMDD_HHMMSS);

        // 获取买家交易详细信息
        SoCpBuyerBillBean soBuyerBill = super.findOne(SqlId.SQL_ID_FIND_ONE_SO_CP_BUYER_BILL, param);

        // 买家交易详细信息验证
        // 1. 如果买家交易详细信息不存在
        if (soBuyerBill == null) {
            throw new BusinessException("数据已被修改，请重新刷新页面");
        }
        // 2. 如果交易已经关闭
        if (CapitalPoolConst.TransFlg.CLOSED.equals(soBuyerBill.getTransFlg())) {
            throw new BusinessException("该交易已经关闭，不可修改");
        }

        // 变更 so_cp_refund
        SoCpRefund soCpRefund = transactionLogic.findOneRefund(param.getRefundId(), null);
        soCpRefund.setPayeeId(null);
        soCpRefund.setPayeeCode(null);
        soCpRefund.setPayeeName(null);
        soCpRefund.setPayeeRole(null);
        int refundType = param.getRefundType();
        soCpRefund.setRefundCode(param.getReferenceCode());
        soCpRefund.setRefundType(param.getRefundType());
        soCpRefund.setRefundAmount(StringUtil.toBigDecimal((param.getReliefAmount())));
        soCpRefund.setRefundType(refundType);
        soCpRefund.setRefundTime(DateTimeUtil.parseDate(param.getOperateTime(), CheckUtil.FORMAT_YYYYMMDD_HHMMSS));
        // 费用类型 为 0 退货 1 拒收 3 减免
        if (refundType == CapitalPoolConst.RefundType.GOODSRETURN || refundType == CapitalPoolConst.RefundType.REJECT
                || refundType == CapitalPoolConst.RefundType.RELIEF) {
            String suppCode = param.getSuppCode();
            String[] suppCodes = suppCode.split("__");
            soCpRefund.setPayeeId(suppCodes[NumberConst.IntDef.INT_ZERO]);
            soCpRefund.setPayeeCode(suppCodes[NumberConst.IntDef.INT_ONE]);
            soCpRefund.setPayeeName(suppCodes[NumberConst.IntDef.INT_TWO]);
            soCpRefund.setPayeeRole(StringUtil.toInteger(suppCodes[NumberConst.IntDef.INT_THREE]));
        }
        soCpRefund.setRemark(param.getRemark());
        soCpRefund.setUpdId(param.getEmplId());
        soCpRefund.setUpdTime(DateTimeUtil.getCustomerDate());
        transactionLogic.modifyRefund(soCpRefund);

        // 变更 so_cp_buyer_bill
        SoCpBuyerBill soCpBuyerBill = new SoCpBuyerBill();
        soCpBuyerBill.setBuyerBillId(StringUtil.toLong(soBuyerBill.getBuyerBillId()));
        // 还原金额
        if (param.getOldRefundType().intValue() == CapitalPoolConst.RefundType.RELIEF) {
            // 减免金额
            if (soBuyerBill.getReliefAmount() != null) {
                soBuyerBill.setReliefAmount(DecimalUtil.subtract(soBuyerBill.getReliefAmount(), oldReliefAmount));
            } else {
                throw new BusinessException("减免金额数据异常");
            }
        } else {
            // 应退金额
            if (soBuyerBill.getRefundable() != null) {
                soBuyerBill.setRefundable(DecimalUtil.subtract(soBuyerBill.getRefundable(), oldReliefAmount));
            } else {
                throw new BusinessException("退款金额的集计数据异常");
            }
        }
        // 计算金额
        if (refundType == CapitalPoolConst.RefundType.RELIEF) {
            // 减免金额
            soCpBuyerBill.setReliefAmount(DecimalUtil.add(soBuyerBill.getReliefAmount(), reliefAmount));
        } else {
            // 应退金额
            soCpBuyerBill.setRefundable(DecimalUtil.add(soBuyerBill.getRefundable(), reliefAmount));
        }
        // 应付金额 - 实际付款金额 - 退款金额的集计 + 实际退款金额 - 减免金额 和 0 比较
        BigDecimal result = DecimalUtil.subtract(soBuyerBill.getDue(), soBuyerBill.getPaid());
        result = DecimalUtil.subtract(result, soBuyerBill.getRefundable());
        result = DecimalUtil.add(result, soBuyerBill.getRealRefund());
        result = DecimalUtil.subtract(result, soBuyerBill.getReliefAmount());
        if (DecimalUtil.isGreater(result, BigDecimal.ZERO)) {
            soCpBuyerBill.setSettlementStatus(CapitalPoolConst.SettlementStatus.FOR_PAY);
        } else if (DecimalUtil.isLess(result, BigDecimal.ZERO)) {
            soCpBuyerBill.setSettlementStatus(CapitalPoolConst.SettlementStatus.FOR_REFUND);
        } else {
            soCpBuyerBill.setSettlementStatus(CapitalPoolConst.SettlementStatus.BALANCED);
        }
        soCpBuyerBill.setUpdId(param.getEmplId());
        soCpBuyerBill.setUpdTime(DateTimeUtil.getCustomerDate());
        super.modify(SqlId.SQL_ID_UPDATE_SO_CP_BUYER_BILL, soCpBuyerBill);
    }

    /**
     * 处理减免金额(表名为 SO_CP_SELLER_BILL_HIS)
     *
     * @param param
     */
    @Transactional
    private void dealSellerBillHis(SOCp153117Param param) throws Exception {
        // 数据类型转换
        BigDecimal reliefAmount = StringUtil.toBigDecimal(param.getReliefAmount());
        BigDecimal oldReliefAmount = StringUtil.toBigDecimal(param.getOldReliefAmount());
        Date operateDate = DateTimeUtil.parseDate(param.getOperateTime(), CheckUtil.FORMAT_YYYYMMDD_HHMMSS);

        // 卖家交易详细信息
        SOCp153111Bean soSellerBill = this.so153111Logic.findSellerById(param.getSellerBillId());
        // 检索结果检测
        // 如果检索结果不存在或者版本与当前版本不一致
        if (soSellerBill == null || soSellerBill.getVer().intValue() != param.getVer()) {
            throw new BusinessException("数据已被修改，请重新刷新页面");
        }

        // 变更 so_cp_seller_bill
        SoCpSellerBill soCpSellerBill = new SoCpSellerBill();
        soCpSellerBill.setSellerBillId(soSellerBill.getSellerBillId());
        // 还原金额
        if (soSellerBill.getAjustAmount() == null) {
            throw new BusinessException("减免金额数据异常");
        } else {
            soSellerBill.setAjustAmount(DecimalUtil.subtract(soSellerBill.getAjustAmount(), oldReliefAmount));
        }
        // 减免金额
        soCpSellerBill.setAjustAmount(DecimalUtil.add(soSellerBill.getAjustAmount(), reliefAmount));
        // 状态变更
        BigDecimal result = DecimalUtil.subtract(soSellerBill.getReceiveable(), soSellerBill.getRefundable());
        result = DecimalUtil.subtract(result, soSellerBill.getReceived());
        result = DecimalUtil.add(result, soSellerBill.getRealRefund());
        result = DecimalUtil.subtract(result, soCpSellerBill.getAjustAmount());
        // 状态变更
        if (DecimalUtil.isGreater(result, BigDecimal.ZERO)) {
            soCpSellerBill.setSettlementStatus(CapitalPoolConst.SettlementStatus.FOR_REFUND);
        } else if (DecimalUtil.isLess(result, BigDecimal.ZERO)) {
            soCpSellerBill.setSettlementStatus(CapitalPoolConst.SettlementStatus.FOR_PAY);
        } else {
            soCpSellerBill.setSettlementStatus(CapitalPoolConst.SettlementStatus.BALANCED);
        }
        soCpSellerBill.setUpdId(param.getEmplId());
        super.modify(SqlId.SQL_ID_UPDATE_SO_CP_SELLER_BILL, soCpSellerBill);

        // 更新 so_cp_seller_bill_his
        SoCpSellerBillHis soCpSellerBillHis = new SoCpSellerBillHis();
        BeanUtils.copyProperties(soSellerBill, soCpSellerBillHis);
        soCpSellerBillHis.setSellerBillHisId(param.getSellerBillHisId());
        soCpSellerBillHis.setAjustDate(operateDate);
        soCpSellerBillHis.setAjustAmount(reliefAmount);
        soCpSellerBillHis.setAjustType(3);
        soCpSellerBillHis.setOperateId(param.getEmplId());
        soCpSellerBillHis.setOperateDate(DateTimeUtil.getCustomerDate());
        soCpSellerBillHis.setRemark(param.getRemark());
        soCpSellerBillHis.setUpdId(param.getEmplId());
        soCpSellerBillHis.setUpdTime(DateTimeUtil.getCustomerDate());
        super.modify(SqlId.SQL_ID_UPDATE_SO_CP_SELLER_BILL_HIS, soCpSellerBillHis);
    }

    /**
     * 根据seller_bill_his_id查找so_cp_seller_bill_his
     *
     * @param param
     */
    @Transactional(readOnly = true)
    public SoCpSellerBillHis findOneSoCpSellerBillHis(SOCp153117Param param) {
        return super.findOne(SqlId.SQL_ID_FIND_ONE_SO_CP_SELLER_BILL_HIS, param);
    }

    /**
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public SoCpBuyerBillBean findOneSoCpBuyerBill(SOCp153117Param param) {
        return super.findOne(SqlId.SQL_ID_FIND_ONE_SO_CP_BUYER_BILL, param);
    }
}
