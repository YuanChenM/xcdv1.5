package com.msk.so.logic;

import com.hoperun.core.bean.ExceptionMessage;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.core.utils.ValidatorUtils;
import com.msk.cashPooling.bean.CpRunningParam;
import com.msk.cashPooling.bean.FundDetail;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.common.consts.CapitalPoolConst;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.*;
import com.msk.so.utils.SORestUtil;
import org.apache.commons.collections.CollectionUtils;
import org.omg.CORBA.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 支付接口
 *
 * @author Qiu_wenting
 * @version 1.0
 */
@Service
public class ISO153102Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISO153102Logic.class);

    /**
     * msg
     */
    ExceptionMessage msg = new ExceptionMessage();

    @Autowired
    private TransactionLogic transLogic;

    @Autowired
    private CommonLogic commonLogic;

    /**
     * 必须项check
     * <p/>
     * check rsParam
     *
     * @param rsParam 请求参数
     */
    private void requiredCheck(CpRunningParam rsParam) {
        // 创建必须项校验的对象
        HashMap<String, Object> checkObj = new HashMap<String, Object>();
        // 1：买家 2：卖家
        checkObj.put("参考明细来源", rsParam.getBackType());
        // 0：付款 1：退款
        checkObj.put("金额类型", rsParam.getAmountType());
        // 订单号 管理单号
        checkObj.put("交易编码", rsParam.getTransCode());
        checkObj.put("交易类型", rsParam.getTransType());
        checkObj.put("订单ID", rsParam.getOrderId());
        checkObj.put("支付金额", rsParam.getPaidAmount());
        checkObj.put("支付方式", rsParam.getPaidType());
        checkObj.put("支付流水号", rsParam.getPaidSeq());
        checkObj.put("支付日期", rsParam.getPaidTime());
        checkObj.put("收款人ID", rsParam.getPayeeId());
        checkObj.put("付款人ID", rsParam.getPayerId());
        checkObj.put("平台类型", rsParam.getPlatform());
        checkObj.put("付款方式", rsParam.getPaymentType());

        if (rsParam.getAmountType() != null) {

            if (CapitalPoolConst.AmountType.PAID != rsParam.getAmountType()) {
                checkObj.put("退款编码", rsParam.getRefundCode());

                if (CapitalPoolConst.AmountType.BOTH == rsParam.getAmountType()) {
                    checkObj.put("发生日期", rsParam.getRefundTime());
                    checkObj.put("退回费用类型", rsParam.getRefundType());
                    checkObj.put("重新发货标志", rsParam.getReShipFlg());

                    // 退回费用明细列表
                    if (!CollectionUtils.isEmpty(rsParam.getRefundDetailList())) {
                        for (FundDetail fundDetail : rsParam.getRefundDetailList()) {
                            if (StringUtil.isEmpty(fundDetail.getPayeeId())) {
                                checkObj.put("退回费用明细列表_供应商ID", null);
                                break;
                            }
                            if (fundDetail.getFundAmount() == null) {
                                checkObj.put("退回费用明细列表_金额", null);
                                break;
                            }
                            if (StringUtil.isEmpty(fundDetail.getPayeeName())) {
                                checkObj.put("退回费用明细列表_供应商名称", null);
                                break;
                            }
                            if (fundDetail.getPayeeRole() == null) {
                                checkObj.put("退回费用明细列表_供应商角色", null);
                                break;
                            }
                        }
                    } else {
                        checkObj.put("退回费用明细列表", null);
                    }
                }
            }

        }

        // 必须项校验
        ValidatorUtils.validatorRequired(checkObj);
    }

    /**
     * 支付明细接口的具体实现
     * <p/>
     * check param
     *
     * @param request 支付明细
     */
    @Transactional
    public void writeRunning(RsRequest<CpRunningParam> request) {

        logger.debug("记录支付明细", request);
        CpRunningParam rsParam = request.getParam();
        SoCpRunning running = rsParam;
        Long billId = null;

        if (rsParam != null) {
            // 必须项检查
            requiredCheck(rsParam);

            // 神农客 设置收款方编码
            if (CapitalPoolConst.OtherConst.SNK_ID.equals(rsParam.getPayeeId())) {
                rsParam.setPayeeCode(CapitalPoolConst.OtherConst.SNK_CODE);
            }

            // 美食客 设置收款方编码
            if (CapitalPoolConst.OtherConst.MSK_ID.equals(rsParam.getPayeeId())) {
                rsParam.setPayeeCode(CapitalPoolConst.OtherConst.MSK_CODE);
            }

            // 适用于拒收的情况
            if (CapitalPoolConst.AmountType.BOTH == rsParam.getAmountType()) {
                // 插入退回费用明细
                createRefund(rsParam, request.getLoginId());

            }

            // 买家付款
            if (CapitalPoolConst.BackType.BUYER == rsParam.getBackType()) {
                // 处理买家计费单
                billId = handleBuyerBill(rsParam, request.getLoginId());
            } else if (CapitalPoolConst.BackType.SELLER == rsParam.getBackType()) {
                // 平台付款给卖家
                // 处理卖家计费单
                billId = handleSellerBill(rsParam, request.getLoginId());
            }

            // 处理资金流水表
            // 主键
            Long runningId = commonLogic.maxId("SO_CP_RUNNING", "RUNNING_ID");
            running.setRunningId(runningId);

            // 交易明细ID
            if (CapitalPoolConst.BackType.BUYER == rsParam.getBackType()) {
                running.setBillId(billId);
            } else if (CapitalPoolConst.BackType.SELLER == rsParam.getBackType()) {
                running.setBillId(billId);
            }

            // 创建者、更新者、生效者
            Date now = DateTimeUtil.getCustomerDate();
            running.setCrtId(request.getLoginId());
            running.setCrtTime(now);
            running.setUpdId(request.getLoginId());
            running.setUpdTime(now);
            running.setActId(request.getLoginId());
            running.setActTime(now);

            // 插入支付流水
            int count = 0;

            // 拒收
            if (CapitalPoolConst.AmountType.BOTH == rsParam.getAmountType()) {
                // 指定返回费用编码
                running.setRefundCode(null);
                // 金额类型
                running.setAmountType(CapitalPoolConst.AmountType.PAID);
                // 线下且部分付款
                if (CapitalPoolConst.PaymentType.OFFLINE == rsParam.getPaymentType()
                        && rsParam.getPaidAmount().compareTo(new BigDecimal("0")) != 0) {
                    count = transLogic.saveRunning(running);
                } else {
                    count = 1;
                }
            } else {
                // 拒收以外的其他情况
                count = transLogic.saveRunning(running);
            }

            if (count != 1) {
                msg.setMessage("支付流水号为" + rsParam.getPaidSeq() + "的支付流水新增失败！");
                logger.debug("支付流水号为" + rsParam.getPaidSeq() + "的支付流水新增失败！");
                throw new BusinessException(msg);
            }
        }
    }

    /**
     * 处理买家计费单
     *
     * @param rsParam 请求参数
     * @param loginId 登录者ID
     * @return 计费单ID
     */
    @Transactional
    private Long handleBuyerBill(CpRunningParam rsParam, String loginId) {
        SoCpBuyerBill buyerBill = transLogic.findOneBuyerBill(null, rsParam.getTransCode(), rsParam.getTransType());

        if (buyerBill == null) {
            msg.setMessage("交易编码为" + rsParam.getTransCode() + "的买家计费单为空！");
            logger.debug("交易编码为" + rsParam.getTransCode() + "的买家计费单为空！");
            throw new BusinessException(msg);

        }
        // 判断付款还是退款
        if (CapitalPoolConst.AmountType.PAID == rsParam.getAmountType()) {
            // 实付
            buyerBill.setPaid(buyerBill.getPaid().add(rsParam.getPaidAmount()));
        } else if (CapitalPoolConst.AmountType.REFUND == rsParam.getAmountType()) {
            // 实退
            buyerBill.setRealRefund(buyerBill.getRealRefund().add(rsParam.getPaidAmount()));
        } else if (CapitalPoolConst.AmountType.BOTH == rsParam.getAmountType()) {
            // 线下
            if (CapitalPoolConst.PaymentType.OFFLINE == rsParam.getTransType()) {
                // 实付
                buyerBill.setPaid(buyerBill.getPaid().add(rsParam.getPaidAmount()));
            }

            // 不重新发货
            if (Integer.valueOf(CapitalPoolConst.YESNO.NO).equals(StringUtil.toInteger(rsParam.getReShipFlg()))) {
                // 应退
                buyerBill.setRefundable(buyerBill.getRefundable().add(rsParam.getRefundAmount()));
                buyerBill.setRefundFlg(CapitalPoolConst.RefundFlg.WITHREFUND);
            }
        }

        // 状态变更
        if (buyerBill.getDue().subtract(buyerBill.getRefundable())
                .compareTo(buyerBill.getPaid().subtract(buyerBill.getRealRefund())) > 0) {
            buyerBill.setSettlementStatus(CapitalPoolConst.SettlementStatus.FOR_PAY);
        } else if (buyerBill.getDue().subtract(buyerBill.getRefundable())
                .compareTo(buyerBill.getPaid().subtract(buyerBill.getRealRefund())) < 0) {
            buyerBill.setSettlementStatus(CapitalPoolConst.SettlementStatus.FOR_REFUND);
        } else {
            buyerBill.setSettlementStatus(CapitalPoolConst.SettlementStatus.BALANCED);
        }

        // 更新者
        buyerBill.setUpdId(loginId);
        buyerBill.setUpdTime(DateTimeUtil.getCustomerDate());
        // 更新交易费用明细
        int count = transLogic.modifyBuyerBill(buyerBill);

        if (count != 1) {
            msg.setMessage("交易编码为" + rsParam.getTransCode() + "的买家计费记录更新失败！");
            logger.debug("交易编码为" + rsParam.getTransCode() + "的买家计费记录更新失败！");
            throw new BusinessException(msg);
        }

        return buyerBill.getBuyerBillId();
    }

    /**
     * 处理卖家计费单
     *
     * @param rsParam 请求参数
     * @param loginId 登录者ID
     * @return 计费单ID
     */
    @Transactional
    private Long handleSellerBill(CpRunningParam rsParam, String loginId) {

        HashMap<String, Object> selectMap = new HashMap<String, Object>();
        selectMap.put("sellerBillNo", rsParam.getTransCode());

        SoCpSellerBill sellerBill = transLogic.findOneSellerBill(selectMap);

        if (null == sellerBill) {
            msg.setMessage("没有符合条件的卖家计费单！");
            logger.debug("计费单号为" + rsParam.getTransCode() + "没有符合条件的卖家计费单！");
            throw new BusinessException(msg);
        }

        // 判断付款还是退款
        if (CapitalPoolConst.AmountType.PAID == rsParam.getAmountType()) {
            // 实付
            sellerBill.setReceived(sellerBill.getReceived().add(rsParam.getPaidAmount()));
        } else if (CapitalPoolConst.AmountType.REFUND == rsParam.getAmountType()) {
            // 实退
            sellerBill.setRealRefund(sellerBill.getRealRefund().add(rsParam.getPaidAmount()));
        }
        // 状态变更
        if (sellerBill.getReceiveable().subtract(sellerBill.getRefundable())
                .compareTo(sellerBill.getReceived().subtract(sellerBill.getRealRefund())) > 0) {
            sellerBill.setSettlementStatus(CapitalPoolConst.SettlementStatus.FOR_REFUND);
        } else if (sellerBill.getReceiveable().subtract(sellerBill.getRefundable())
                .compareTo(sellerBill.getReceived().subtract(sellerBill.getRealRefund())) < 0) {
            sellerBill.setSettlementStatus(CapitalPoolConst.SettlementStatus.FOR_PAY);
        } else {
            sellerBill.setSettlementStatus(CapitalPoolConst.SettlementStatus.BALANCED);
        }

        // 更新者
        sellerBill.setUpdId(loginId);
        sellerBill.setUpdTime(DateTimeUtil.getCustomerDate());

        // 更新交易费用明细
        int count = transLogic.modifySellerBill(sellerBill);

        if (count != 1) {
            msg.setMessage("卖家计费记录更新失败！");
            logger.debug("计费单号为" + rsParam.getTransCode() + "的卖家计费记录更新失败！");
            throw new BusinessException(msg);
        }

        return sellerBill.getSellerBillId();
    }

    /**
     * 新增退回费用
     *
     * @param rsParam 请求参数
     * @param loginId 登录者ID
     */
    @Transactional
    private void createRefund(CpRunningParam rsParam, String loginId) {
        SoCpRefund refund = new SoCpRefund();

        // 交易编码
        refund.setTransCode(rsParam.getTransCode());

        // 订单ID
        refund.setOrderId(rsParam.getOrderId());

        // 退款编码
        refund.setRefundCode(rsParam.getRefundCode());

        // 发生日期
        refund.setRefundTime(rsParam.getRefundTime());

        // 退回费用类型(0：退货退款 1：拒收退款)
        refund.setRefundType(rsParam.getRefundType());

        // 重新发货标志
        refund.setReShipFlg(rsParam.getReShipFlg());

        // 付款人ID
        refund.setPayerId(rsParam.getPayerId());

        // 付款人角色
        refund.setPayerRole(rsParam.getPayerRole());

        // 平台类型
        refund.setPlatform(rsParam.getPlatform());

        // 创建者、更新者、生效者
        Date now = DateTimeUtil.getCustomerDate();
        refund.setCrtId(loginId);
        refund.setCrtTime(now);
        refund.setUpdId(loginId);
        refund.setUpdTime(now);
        refund.setActId(loginId);
        refund.setActTime(now);

        // 拒收单列表
        List<FundDetail> fundDetailList = rsParam.getRefundDetailList();

//        List<String> slCodeLsit = new ArrayList<String>();
//        for (FundDetail fundDetail : fundDetailList) {
//            slCodeLsit.add(fundDetail.getPayeeId());
//        }
//        HashMap<String, String> slCodeDisMap = SORestUtil.getSlCodeDisList(slCodeLsit);

        List<String> slCodeSeList = new ArrayList<String>();
        List<SlSeller> slCodeByList = new ArrayList<SlSeller>();
        for (FundDetail fundDetail : fundDetailList) {
            if (CapitalPoolConst.RoleType.ROLE_BUYERE.equals(fundDetail.getPayeeRole() + "")) {
                SlSeller slSeller = new SlSeller();
                slSeller.setSlCode(fundDetail.getPayeeId());
                slCodeByList.add(slSeller);
            } else {
                slCodeSeList.add(fundDetail.getPayeeId());
            }
        }

        HashMap<String, String> slCodeDisMap = new HashMap<String, String>();
        // 查询卖家显示编码
        if (CollectionUtils.isNotEmpty(slCodeSeList)) {
            HashMap<String, String> slCodeDisSeMap = SORestUtil.getSlCodeDisList(slCodeSeList, null, null);
            if (slCodeDisSeMap.size() > NumberConst.IntDef.INT_ZERO) {
                slCodeDisMap.putAll(slCodeDisSeMap);
            }
        }
        // 查询买手显示编码
        if (CollectionUtils.isNotEmpty(slCodeByList)) {
            HashMap<String, String> slCodeDisSeMap = SORestUtil.getSlCodeDisList(null, slCodeByList, CapitalPoolConst.RoleType.ROLE_BUYERE);
            if (slCodeDisSeMap.size() > NumberConst.IntDef.INT_ZERO) {
                slCodeDisMap.putAll(slCodeDisSeMap);
            }
        }


        for (FundDetail fundDetail : fundDetailList) {

            // 主键
            Long refundId = commonLogic.maxId("SO_CP_REFUND", "REFUND_ID");
            refund.setRefundId(refundId);

            // 供应商ID
            refund.setPayeeId(fundDetail.getPayeeId());

            // 供应商编码
            refund.setPayeeCode(slCodeDisMap.get(fundDetail.getPayeeId()));

            // 供应商名称
            refund.setPayeeName(fundDetail.getPayeeName());

            // 供应商角色
            refund.setPayeeRole(fundDetail.getPayeeRole());

            // 退款金额
            refund.setRefundAmount(fundDetail.getFundAmount());

            // 累计每项的退款金额
            if (rsParam.getRefundAmount() == null) {
                rsParam.setRefundAmount(BigDecimal.ZERO.add(fundDetail.getFundAmount()));
            } else {
                rsParam.setRefundAmount(rsParam.getRefundAmount().add(fundDetail.getFundAmount()));
            }

            int count = transLogic.saveRefund(refund);

            if (count != 1) {
                msg.setMessage("退款编码为" + rsParam.getRefundCode() + "的退回费用新增失败！");
                logger.debug("退款编码为" + rsParam.getRefundCode() + "的退回费用新增失败！");
                throw new BusinessException(msg);
            }
        }
    }

}
