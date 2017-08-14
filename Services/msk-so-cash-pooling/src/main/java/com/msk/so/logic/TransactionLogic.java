package com.msk.so.logic;

import com.hoperun.core.bean.ExceptionMessage;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

/**
 * 交易基础信息、计费项、计费单共通logic
 *
 * @author Qiu_wenting
 * @version 1.0
 */
@Service
public class TransactionLogic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(TransactionLogic.class);

    /**
     * msg
     */
    ExceptionMessage msg = new ExceptionMessage();

    /**
     * SqlId. sql定义
     *
     * @author pxg
     */
    interface SqlId {
        String SQL_ID_SAVE_TRANS = "saveTrans";
        String SQL_ID_SAVE_REFUND = "saveRefund";
        String SQL_ID_SAVE_BUYER_BILL = "saveBuyerBill";
        String SQL_ID_SAVE_RUNNING = "saveRunning";
        String SQL_ID_SAVE_SEL_CHARGING = "saveSelCharging";

        String SQL_ID_MODIFY_TRANS = "modifyTrans";
        String SQL_ID_MODIFY_BUYER_BILL = "modifyBuyerBill";
        String SQL_ID_MODIFY_SELLER_BILL = "modifySellerBill";
        String SQL_ID_MODIFY_RUNNING = "modifyRunning";
        String SQL_ID_MODIFY_REFUND = "modifyRefund";

        String SQL_ID_FIND_ONE_TRANS = "findOneTrans";
        String SQL_ID_FIND_ONE_REFUND = "findOneRefund";
        String SQL_ID_FIND_ONE_BUYER_BILL = "findOneBuyerBill";
        String SQL_ID_FIND_ONE_SELLER_BILL = "findOneSellerBill";
        String SQL_ID_FIND_ONE_RUNNING = "findOneRunning";

    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 根据查询条件检索一条交易记录
     *
     * @param transId 交易明细
     * @param transCode 交易编码（订单号等唯一标识）
     * @param transType 交易类型（订单、管理费单）
     * @return buyerTrans
     */
    @Transactional(readOnly = true)
    public SoCpTransaction findOneTrans(Long transId, String transCode, Integer transType) {
        SoCpTransaction trans = new SoCpTransaction();
        if (transId != null) {
            trans.setTransId(transId);
        }
        if (transCode != null) {
            trans.setTransCode(transCode);
        }
        if (transType != null) {
            trans.setTransType(transType);
        }
        return findOne(SqlId.SQL_ID_FIND_ONE_TRANS, trans);
    }

    /**
     * 根据查询条件检索一条买家计费单
     *
     * @param buyerBillId 买家计费单ID
     * @param transCode 交易编码
     * @param transType 交易类型
     * @return refund
     */
    @Transactional(readOnly = true)
    public SoCpBuyerBill findOneBuyerBill(Long buyerBillId, String transCode, Integer transType) {
        SoCpBuyerBill buyerBill = new SoCpBuyerBill();
        if (buyerBillId != null) {
            buyerBill.setBuyerBillId(buyerBillId);
        }
        if (transCode != null) {
            buyerBill.setTransCode(transCode);
        }
        if (transType != null) {
            buyerBill.setTransType(transType);
        }
        return findOne(SqlId.SQL_ID_FIND_ONE_BUYER_BILL, buyerBill);
    }

    /**
     * 根据查询条件(指定退回费用明细ID或者退款编码)检索一条退回退款记录
     *
     * @param refundId 退回费用明细ID
     * @param refundCode 退回费用编码（退货单、拒收单等）
     * @return refund
     */
    @Transactional(readOnly = true)
    public SoCpRefund findOneRefund(Long refundId, String refundCode) {
        SoCpRefund refund = new SoCpRefund();
        if (refundId != null) {
            refund.setRefundId(refundId);
        }
        if (refundCode != null) {
            refund.setRefundCode(refundCode);
        }
        return findOne(SqlId.SQL_ID_FIND_ONE_REFUND, refund);
    }

    /**
     * 新增退回费用明细
     *
     * @param refund 退回费用明细
     * @return 插入行数
     */
    @Transactional
    public int saveRefund(SoCpRefund refund) {
        return save(SqlId.SQL_ID_SAVE_REFUND, refund);
    }

    /**
     * 新增交易记录
     *
     * @param running 交易记录
     * @return 插入行数
     */
    @Transactional
    public int saveRunning(SoCpRunning running) {
        return save(SqlId.SQL_ID_SAVE_RUNNING, running);
    }

    /**
     * 更新交易明细
     *
     * @param trans 交易明细
     * @return 更新行数
     */
    @Transactional
    public int modifyTrans(SoCpTransaction trans) {
        return modify(SqlId.SQL_ID_MODIFY_TRANS, trans);
    }

    /**
     * 新增交易明细
     *
     * @param trans 交易明细
     * @return 更新行数
     */
    @Transactional
    public int saveTrans(SoCpTransaction trans) {
        return save(SqlId.SQL_ID_SAVE_TRANS, trans);
    }

    /**
     * 新增买家计费单
     *
     * @param buyerBill 买家计费单
     * @return 更新行数
     */
    @Transactional
    public int saveBuyerBill(SoCpBuyerBill buyerBill) {
        return save(SqlId.SQL_ID_SAVE_BUYER_BILL, buyerBill);
    }

    /**
     * 更新买家计费单
     *
     * @param buyerBill 买家计费单
     * @return 更新行数
     */
    @Transactional
    public int modifyBuyerBill(SoCpBuyerBill buyerBill) {
        return modify(SqlId.SQL_ID_MODIFY_BUYER_BILL, buyerBill);
    }

    /**
     * 根据查询条件检索一条卖家计费单
     *
     * @param selectMap 检索条件
     * @return SoSellerBill
     */
    @Transactional(readOnly = true)
    public SoCpSellerBill findOneSellerBill(HashMap<String, Object> selectMap) {
        return findOne(SqlId.SQL_ID_FIND_ONE_SELLER_BILL, selectMap);
    }

    /**
     * 新增卖家计费项
     *
     * @param selCharging 卖家计费项
     * @return 插入行数
     */
    @Transactional
    public int saveSelCharging(SoCpSelCharging selCharging) {
        return save(SqlId.SQL_ID_SAVE_SEL_CHARGING, selCharging);
    }

    /**
     * 更新卖家计费单
     *
     * @param sellerBill 卖家计费单
     * @return 执行结果
     */
    @Transactional
    public int modifySellerBill(SoCpSellerBill sellerBill) {
        return modify(SqlId.SQL_ID_MODIFY_SELLER_BILL, sellerBill);
    }

    /**
     * 更新 资金池 支付流水
     *
     * @param soCpRunning
     * @return 执行结果
     */
    @Transactional
    public int modifyRunning(SoCpRunning soCpRunning) {
        return modify(SqlId.SQL_ID_MODIFY_RUNNING, soCpRunning);
    }

    /**
     *
     * @param runningId
     * @return
     */
    @Transactional(readOnly = true)
    public SoCpRunning findOneRunning(Long runningId) {
        SoCpRunning soCpRunning = new SoCpRunning();
        soCpRunning.setRunningId(runningId);
        return findOne(SqlId.SQL_ID_FIND_ONE_RUNNING, soCpRunning);
    }

    /**
     * 更新 资金池 退货、退款、拒收费用明细
     *
     * @param soCpRefund
     * @return 执行结果
     */
    @Transactional
    public int modifyRefund(SoCpRefund soCpRefund) {
        return modify(SqlId.SQL_ID_MODIFY_REFUND, soCpRefund);
    }

}
