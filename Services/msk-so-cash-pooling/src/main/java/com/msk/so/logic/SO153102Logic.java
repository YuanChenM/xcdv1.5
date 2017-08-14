package com.msk.so.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.DecimalUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.consts.CapitalPoolConst;
import com.msk.core.entity.SoCpBuyerBill;
import com.msk.core.entity.SoCpRefund;
import com.msk.core.entity.SoCpRunning;
import com.msk.so.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class SO153102Logic extends BaseLogic {

    private static Logger logger = LoggerFactory.getLogger(SO153102Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private SO153101Logic so153101Logic;

    @Autowired
    private TransactionLogic transactionLogic;

    /**
     * SqlId. sql定义
     */
    interface SqlId {
        String SQL_ID_FIND_SO_RUNNING = "findSORunning";// 查看支付列表
        String SQL_ID_FIND_SO_REFUND = "findSORefund"; // 查看退款列表
        /**
         * 添加买家资金池详细页面合计 modify by renyi on 2016/8/11 start
         */
        String SQL_ID_GET_TOTAL_PAIDAMOUNT_INFO = "getTotalPaidAmountInfo";
        String SQL_ID_GET_TOTAL_REFUNDAMOUNT_INFO = "getTotalRefundAmountInfo";
        /** 添加买家资金池详细页面合计 modify by renyi on 2016/8/11 end */
        /**
         * 添加买家资金池详细列表更新删除功能 modify by lihuiqian on 2016/8/31 start
         */
        String SQL_ID_DELETE_SO_RUNNING = "deleteSoRunning";
        String SQL_ID_DELETE_SO_REFUND = "deleteSoRefund";
        String SQL_ID_UPDATE_SO_CP_BUYER_BILL_BY_RUNNING = "updateSoCpBuyerBillByRunning";
        String SQL_ID_UPDATE_SO_CP_BUYER_BILL_BY_REFUND = "updateSoCpBuyerBillByRefund";
        /** 添加买家资金池详细列表更新删除功能 modify by lihuiqian on 2016/8/31 end */

    }

    /**
     * 根据 billId 查询支付记录
     *
     * @param billId
     * @return
     */
    @Transactional(readOnly = true)
    public List<SoCpRunningBean> findSORunningByBillId(String billId) {
        BaseParam param = new BaseParam();
        param.getFilterMap().put("billId", billId);
        param.getFilterMap().put("backType", 1);
        return super.findList(SO153102Logic.SqlId.SQL_ID_FIND_SO_RUNNING, param);
    }

    /**
     * 根据条件查询退款
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<SoCpRefundBean> findSORefund(BaseParam param) {
        return super.findList(SO153102Logic.SqlId.SQL_ID_FIND_SO_REFUND, param);
    }

    /** 添加买家资金池详细页面合计 modify by renyi on 2016/8/11 start */
    /**
     * 查询支付金额合计
     *
     * @param billId
     * @return
     */
    @Transactional(readOnly = true)
    public SO153102Bean getTotalPaidAmountInfo(String billId) {
        BaseParam param = new BaseParam();
        param.getFilterMap().put("billId", billId);
        param.getFilterMap().put("backType", 1);
        return super.findOne(SO153102Logic.SqlId.SQL_ID_GET_TOTAL_PAIDAMOUNT_INFO, param);
    }

    /**
     * 查询支付金额合计
     *
     * @param transCode
     * @return
     */
    @Transactional(readOnly = true)
    public SO153102Bean getTotalRefundAmountInfo(String transCode) {
        BaseParam param = new BaseParam();
        param.getFilterMap().put("transCode", transCode);
        return super.findOne(SO153102Logic.SqlId.SQL_ID_GET_TOTAL_REFUNDAMOUNT_INFO, param);
    }

    /** 添加买家资金池详细页面合计 modify by renyi on 2016/8/11 end */

    /** 添加买家资金池详细列表更新删除功能 modify by lihuiqian on 2016/8/31 start */
    /**
     * 删除指定的支付明细行
     *
     * @param
     */
    @Transactional
    public void deleteSoRunning(SO153102Param param) {
        // 查询订单金额
        SoCpRunning soCpRunning = transactionLogic.findOneRunning(StringUtil.toLong(param.getRunningId()));

        // 买家交易详细信息
        SO153101Bean soBuyerBill = this.so153101Logic.findBuyerById(param.getBuyerBillId());

        // 检查
        if (soBuyerBill == null || !soBuyerBill.getVer().equals(param.getVer())) {
            throw new BusinessException("数据已被修改，请重新刷新页面");
        }
        if (CapitalPoolConst.TransFlg.CLOSED.equals(soBuyerBill.getTransFlg())) {
            throw new BusinessException("该交易已经关闭，不可修改");
        }

        // 删除 so_cp_running
        soCpRunning.setUpdId(param.getEmplId());
        soCpRunning.setUpdTime(DateTimeUtil.getCustomerDate());
        super.modify(SqlId.SQL_ID_DELETE_SO_RUNNING, param);

        // 还原 so_cp_buyer_bill
        SoCpBuyerBill soCpBuyerBill = new SoCpBuyerBill();
        soCpBuyerBill.setBuyerBillId(StringUtil.toLong(param.getBuyerBillId()));
        BigDecimal result = null;
        if (CapitalPoolConst.AmountType.PAID == soCpRunning.getAmountType()) {
            // 实际付款金额 -= 支付金额
            if (null != soBuyerBill.getPaid()) {
                soCpBuyerBill.setPaid(soBuyerBill.getPaid().subtract(soCpRunning.getPaidAmount()));
            } else {
                throw new BusinessException("实际付款金额数据异常");
            }
            result = DecimalUtil.subtract(soBuyerBill.getDue(), soCpBuyerBill.getPaid());
            result = DecimalUtil.subtract(result, soBuyerBill.getRefundable());
            result = DecimalUtil.add(result, soBuyerBill.getRealRefund());
            result = DecimalUtil.subtract(result, soBuyerBill.getReliefAmount());
        } else {
            // 实际退款金额 -= 支付金额
            if (null != soBuyerBill.getRealRefund()) {
                soCpBuyerBill.setRealRefund(soBuyerBill.getRealRefund().subtract(soCpRunning.getPaidAmount()));
            } else {
                throw new BusinessException("实际退款金额数据异常");
            }
            result = DecimalUtil.subtract(soBuyerBill.getDue(), soBuyerBill.getPaid());
            result = DecimalUtil.subtract(result, soBuyerBill.getRefundable());
            result = DecimalUtil.add(result, soCpBuyerBill.getRealRefund());
            result = DecimalUtil.subtract(result, soBuyerBill.getReliefAmount());
        }
        if (DecimalUtil.isGreater(result, BigDecimal.ZERO)) {
            soCpBuyerBill.setSettlementStatus(CapitalPoolConst.SettlementStatus.FOR_PAY);
        } else if (DecimalUtil.isLess(result, BigDecimal.ZERO)) {
            soCpBuyerBill.setSettlementStatus(CapitalPoolConst.SettlementStatus.FOR_REFUND);
        } else {
            soCpBuyerBill.setSettlementStatus(CapitalPoolConst.SettlementStatus.BALANCED);
        }
        soCpBuyerBill.setUpdId(param.getEmplId());
        soCpBuyerBill.setUpdTime(DateTimeUtil.getCustomerDate());
        super.modify(SqlId.SQL_ID_UPDATE_SO_CP_BUYER_BILL_BY_RUNNING, soCpBuyerBill);
    }

    /**
     * 删除指定的应付款明细行
     *
     * @param
     */
    @Transactional
    public void deleteSoRefund(SO153102Param param) {
        // 查询退款退货拒收明细
        SoCpRefund soCpRefund = transactionLogic.findOneRefund(StringUtil.toLong(param.getRefundId()), null);

        // 获取买家交易详细信息
        SO153101Bean soBuyerBill = this.so153101Logic.findBuyerById(param.getBuyerBillId());

        // 买家交易详细信息验证
        // 1. 如果买家交易详细信息不存在或者版本不正确
        if (soBuyerBill == null || !soBuyerBill.getVer().equals(param.getVer())) {
            throw new BusinessException("数据已被修改，请重新刷新页面");
        }
        // 2. 如果交易已经关闭
        if (CapitalPoolConst.TransFlg.CLOSED.equals(soBuyerBill.getTransFlg())) {
            throw new BusinessException("该交易已经关闭，不可修改");
        }

        // 删除 so_cp_refund
        SoCpRefund refund = new SoCpRefund();
        refund.setRefundId(param.getRefundId());
        refund.setUpdId(param.getEmplId());
        refund.setUpdTime(DateTimeUtil.getCustomerDate());
        super.modify(SqlId.SQL_ID_DELETE_SO_REFUND, param);

        // 还原 so_cp_buyer_bill
        SoCpBuyerBill soCpBuyerBill = new SoCpBuyerBill();
        soCpBuyerBill.setBuyerBillId(StringUtil.toLong(param.getBuyerBillId()));
        // 还原金额
        if (soCpRefund.getRefundType().intValue() == CapitalPoolConst.RefundType.RELIEF) {
            // 减免金额
            if (soBuyerBill.getReliefAmount() != null) {
                soBuyerBill.setReliefAmount(DecimalUtil.subtract(soBuyerBill.getReliefAmount(), soCpRefund.getRefundAmount()));
                /** Bug #3362 modify by renyi on 2016/10/17 start */
                soCpBuyerBill.setReliefAmount(soBuyerBill.getReliefAmount());
                /** Bug #3362 modify by renyi on 2016/10/17 end */
            } else {
                throw new BusinessException("减免金额数据异常");
            }
        } else {
            // 应退金额
            if (soBuyerBill.getRefundable() != null) {
                soBuyerBill.setRefundable(DecimalUtil.subtract(soBuyerBill.getRefundable(), soCpRefund.getRefundAmount()));
                /** Bug #3362 modify by renyi on 2016/10/17 start */
                soCpBuyerBill.setRefundable(soBuyerBill.getRefundable());
                /** Bug #3362 modify by renyi on 2016/10/17 end */
            } else {
                throw new BusinessException("退款金额的集计数据异常");
            }
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
        super.modify(SqlId.SQL_ID_UPDATE_SO_CP_BUYER_BILL_BY_REFUND, soCpBuyerBill);
    }
    /** 添加买家资金池详细列表更新删除功能 modify by lihuiqian on 2016/8/31 end */
}
