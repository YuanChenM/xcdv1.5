package com.msk.so.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.DecimalUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.consts.CapitalPoolConst;
import com.msk.core.entity.*;
import com.msk.so.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by wu_honglei on 2016/5/4.
 */

@Service
public class SO153112Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SO153112Logic.class);

    /**
     * SqlId. sql定义
     *
     */
    interface SqlId {
        String SQL_ID_FIND_SO_RUNNING = "findSORunning";// 查看支付列表
        String SQL_ID_FIND_SO_RUNNINGBEAN = "findSORunningBean";// 查看支付列表
        String SQL_ID_FIND_SO_REFUND = "findSORefund"; // 查看退款列表
        /** 添加卖家资金池详细页面合计 modify by renyi on 2016/8/12 start */
        String SQL_ID_GET_TOTAL_PAIDAMOUNT_INFO = "getTotalPaidAmountInfo";
        String SQL_ID_GET_TOTAL_REFUNDAMOUNT_INFO = "getTotalRefundAmountInfo";
        /** 添加卖家资金池详细页面合计 modify by renyi on 2016/8/12 end */
        /** 添加卖家资金池详细列表更新删除功能 modify by lihuiqian on 2016/9/2 start */
        String SQL_ID_FIND_ONE_SO_CP_SELLER_BILL_HIS = "findOneSoCpSellerBillHis";
        String SQL_ID_FIND_ONE_SO_CP_BUYER_BILL = "findOneSoCpBuyerBill";
        String SQL_ID_DELETE_SO_CP_RUNNING = "deleteSoCpRunning";
        String SQL_ID_UPDATE_SO_CP_SELLER_BILL_BY_RUNNING = "updateSoCpSellerBillByRunning";
        String SQL_ID_UPDATE_SO_CP_SELLER_BILL = "updateSoCpSellerBill";
        String SQL_ID_DELETE_SO_CP_SELLER_BILL_HIS = "deleteSoCpSellerBillHis";
        String SQL_ID_DELETE_SO_CP_REFUND = "deleteSoCpRefund";
        String SQL_ID_UPDATE_SO_CP_BUYER_BILL_BY_REFUND = "updateSoCpBuyerBillByRefund";
        /** 添加卖家资金池详细列表更新删除功能 modify by lihuiqian on 2016/9/2 end */
    }

    @Autowired
    private TransactionLogic transactionLogic;
    @Autowired
    private SO153111Logic so153111Logic;

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 根据 billId 查询支付记录
     * 
     * @param billId
     * @return
     */
    @Transactional(readOnly = true)
    public List<SoCpRunning> findSORunningByBillId(String billId) {
        logger.info("卖家资金池管理查询支付记录");
        BaseParam param = new BaseParam();
        param.getFilterMap().put("billId", billId);
        param.getFilterMap().put("backType", 2);
        return super.findList(SqlId.SQL_ID_FIND_SO_RUNNING, param);
    }

    /**
     * 根据 billId 查询支付记录
     * 
     * @param billId
     * @return
     */
    @Transactional(readOnly = true)
    public List<SoCpRunningBean> findSORunningBeanByBillId(String billId) {
        logger.info("卖家资金池管理查询支付记录");
        BaseParam param = new BaseParam();
        param.getFilterMap().put("billId", billId);
        param.getFilterMap().put("backType", 2);
        return super.findList(SqlId.SQL_ID_FIND_SO_RUNNINGBEAN, param);
    }

    /** 添加卖家资金池详细列表更新删除功能 modify by lihuiqian on 2016/9/5 start */
    /**
     * 根据条件查询退款
     * 
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<SOCp153112Bean> findSORefund(BaseParam param) {
        logger.info("卖家资金池管理退款列表");
        return super.findList(SqlId.SQL_ID_FIND_SO_REFUND, param);
    }

    /** 添加卖家资金池详细列表更新删除功能 modify by lihuiqian on 2016/9/5 end */

    /** 添加卖家资金池详细页面合计 modify by renyi on 2016/8/12 start */
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
        param.getFilterMap().put("backType", 2);
        return super.findOne(SO153112Logic.SqlId.SQL_ID_GET_TOTAL_PAIDAMOUNT_INFO, param);
    }

    /**
     * 查询支付金额合计
     * 
     * @param pageParam
     * @return
     */
    @Transactional(readOnly = true)
    public SO153102Bean getTotalRefundAmountInfo(BaseParam pageParam) {
        return super.findOne(SO153112Logic.SqlId.SQL_ID_GET_TOTAL_REFUNDAMOUNT_INFO, pageParam);
    }

    /** 添加卖家资金池详细页面合计 modify by renyi on 2016/8/12 end */

    /** 添加卖家资金池详细列表更新删除功能 modify by lihuiqian on 2016/9/2 start */
    /**
     * 删除指定的支付明细行
     *
     * @param
     */
    @Transactional
    public void deleteSoRunning(SOCp153112Param param) {
        // 查询订单金额
        SoCpRunning soCpRunning = transactionLogic.findOneRunning(StringUtil.toLong(param.getRunningId()));

        // 买家交易详细信息
        SOCp153111Bean soSellerBill = this.so153111Logic.findSellerById(param.getSellerBillId());

        // 检索结果检验
        // 如果检索结果不存在或者版本不正确
        if (soSellerBill == null && soSellerBill.getVer().intValue() != param.getVer()) {
            throw new BusinessException("数据已被修改，请重新刷新页面");
        }

        // 删除 so_cp_running
        soCpRunning.setUpdId(param.getEmplId());
        soCpRunning.setUpdTime(DateTimeUtil.getCustomerDate());
        super.modify(SqlId.SQL_ID_DELETE_SO_CP_RUNNING, param);

        // 还原 so_cp_seller_bill
        SoCpSellerBill soCpSellerBill = new SoCpSellerBill();
        soCpSellerBill.setSellerBillId(soSellerBill.getSellerBillId());
        // 原有金额的还原
        BigDecimal result = null;
        if (CapitalPoolConst.AmountType.PAID == soCpRunning.getAmountType()) {
            if (soSellerBill.getReceived() != null) {
                soCpSellerBill
                    .setReceived(DecimalUtil.subtract(soSellerBill.getReceived(), soCpRunning.getPaidAmount()));
            } else {
                throw new BusinessException("实际收款金额数据异常");
            }
            result = DecimalUtil.subtract(soSellerBill.getReceiveable(), soSellerBill.getRefundable());
            result = DecimalUtil.subtract(result, soCpSellerBill.getReceived());
            result = DecimalUtil.add(result, soSellerBill.getRealRefund());
            result = DecimalUtil.subtract(result, soSellerBill.getAjustAmount());
        } else {
            if (soSellerBill.getRealRefund() != null) {
                soCpSellerBill
                    .setRealRefund(DecimalUtil.subtract(soSellerBill.getRealRefund(), soCpRunning.getPaidAmount()));
            } else {
                throw new BusinessException("实际退款金额数据异常");
            }
            result = DecimalUtil.subtract(soSellerBill.getReceiveable(), soSellerBill.getRefundable());
            result = DecimalUtil.subtract(result, soSellerBill.getReceived());
            result = DecimalUtil.add(result, soCpSellerBill.getRealRefund());
            result = DecimalUtil.subtract(result, soSellerBill.getAjustAmount());
        }
        if (DecimalUtil.isGreater(result, BigDecimal.ZERO)) {
            soCpSellerBill.setSettlementStatus(CapitalPoolConst.SettlementStatus.FOR_REFUND);
        } else if (DecimalUtil.isLess(result, BigDecimal.ZERO)) {
            soCpSellerBill.setSettlementStatus(CapitalPoolConst.SettlementStatus.FOR_PAY);
        } else {
            soCpSellerBill.setSettlementStatus(CapitalPoolConst.SettlementStatus.BALANCED);
        }
        soCpSellerBill.setUpdId(param.getEmplId());
        soCpSellerBill.setUpdTime(DateTimeUtil.getCustomerDate());
        super.modify(SqlId.SQL_ID_UPDATE_SO_CP_SELLER_BILL_BY_RUNNING, soCpSellerBill);
    }

    /**
     * 删除指定的应退款明细行
     *
     * @param
     */
    @Transactional
    public void deleteSoRefund(SOCp153112Param param) {
        if ("REFUND".equals(param.getTb())) {
            param.setRefundId(param.getId());
            deleteRefund(param);
        } else if ("SELLER_BILL_HIS".equals(param.getTb())) {
            param.setSellerBillHisId(param.getId());
            deleteSellerBillHis(param);
        }
    }

    /**
     * 删除指定的应退款明细行（表SO_CP_REFUND中的行）
     *
     * @param
     */
    @Transactional
    private void deleteRefund(SOCp153112Param param) {
        // 查询退款退货拒收明细
        SoCpRefund soCpRefund = transactionLogic.findOneRefund(param.getRefundId(), null);

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

        // 删除 so_cp_refund
        SoCpRefund refund = new SoCpRefund();
        refund.setRefundId(param.getRefundId());
        refund.setUpdId(param.getEmplId());
        refund.setUpdTime(DateTimeUtil.getCustomerDate());
        super.modify(SqlId.SQL_ID_DELETE_SO_CP_REFUND, param);

        // 还原 so_cp_buyer_bill
        SoCpBuyerBill soCpBuyerBill = new SoCpBuyerBill();
        soCpBuyerBill.setBuyerBillId(soBuyerBill.getBuyerBillId());
        // 还原金额
        if (soCpRefund.getRefundType().intValue() == CapitalPoolConst.RefundType.RELIEF) {
            // 减免金额
            if (soBuyerBill.getReliefAmount() != null) {
                soBuyerBill
                    .setReliefAmount(DecimalUtil.subtract(soBuyerBill.getReliefAmount(), soCpRefund.getRefundAmount()));
            } else {
                throw new BusinessException("减免金额数据异常");
            }
        } else {
            // 应退金额
            if (soBuyerBill.getRefundable() != null) {
                soBuyerBill
                    .setRefundable(DecimalUtil.subtract(soBuyerBill.getRefundable(), soCpRefund.getRefundAmount()));
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

    /**
     * 删除指定的应退款明细行（表SO_CP_SELLER_BILL_HIS中的行）
     *
     * @param
     */
    @Transactional
    private void deleteSellerBillHis(SOCp153112Param param) {
        // 卖家计费单历史查询
        SoCpSellerBillHis soCpSellerBillHis = super.findOne(SqlId.SQL_ID_FIND_ONE_SO_CP_SELLER_BILL_HIS, param);

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
            soCpSellerBill.setAjustAmount(
                DecimalUtil.subtract(soSellerBill.getAjustAmount(), soCpSellerBillHis.getAjustAmount()));
        }
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
        soCpSellerBill.setUpdTime(DateTimeUtil.getCustomerDate());
        super.modify(SqlId.SQL_ID_UPDATE_SO_CP_SELLER_BILL, soCpSellerBill);

        // 删除 so_cp_seller_bill_his
        soCpSellerBillHis.setUpdId(param.getEmplId());
        soCpSellerBillHis.setUpdTime(DateTimeUtil.getCustomerDate());
        super.modify(SqlId.SQL_ID_DELETE_SO_CP_SELLER_BILL_HIS, soCpSellerBillHis);
    }

    /** 添加卖家资金池详细列表更新删除功能 modify by lihuiqian on 2016/9/2 end */

}
