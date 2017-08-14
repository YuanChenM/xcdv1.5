package com.msk.so.logic;

import com.hoperun.core.bean.ExceptionMessage;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.core.utils.ValidatorUtils;
import com.msk.bs.bean.IBS2101116Bean;
import com.msk.cashPooling.bean.CpTransactionParam;
import com.msk.cashPooling.bean.FundDetail;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.common.consts.CapitalPoolConst;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.SlSeller;
import com.msk.core.entity.SoCpBuyerBill;
import com.msk.core.entity.SoCpRefund;
import com.msk.core.entity.SoCpTransaction;
import com.msk.so.utils.SOControllerUtil;
import com.msk.so.utils.SORestUtil;
import org.apache.commons.collections.CollectionUtils;
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
 * 交易明细接口
 *
 * @author Qiu_wenting
 * @version 1.0
 */
@Service
public class ISO153101Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISO153101Logic.class);

    /**
     * msg
     */
    ExceptionMessage msg = new ExceptionMessage();

    @Autowired
    private CommonLogic commonLogic;

    @Autowired
    private TransactionLogic transLogic;

    /**
     * 必须项check
     *
     * @param rsParam 退款参数
     */
    private void RequiredCheck(CpTransactionParam rsParam) {
        // 创建必须项校验的对象
        HashMap<String, Object> checkObj = new HashMap<String, Object>();

        checkObj.put("是否新增标识", rsParam.getInsertFlg());
        checkObj.put("交易编码", rsParam.getTransCode());
        checkObj.put("交易类型", rsParam.getTransType());
        checkObj.put("订单ID", rsParam.getOrderId());
        checkObj.put("操作时间", rsParam.getOperateDate());
        checkObj.put("平台类型", rsParam.getSupplyPlatform());
        checkObj.put("交易标志", rsParam.getTransFlg());

        if (CapitalPoolConst.InsertFlg.YES.equals(rsParam.getInsertFlg())) {
            checkObj.put("收款方ID", rsParam.getBusinessMainId());
            checkObj.put("付款方ID", rsParam.getBusinessAssistantId());
            checkObj.put("收款方名称", rsParam.getBusinessMainName());
            checkObj.put("收款方角色", rsParam.getBusinessMainRole());
            checkObj.put("付款方编码", rsParam.getBusinessAssistantCode());
            checkObj.put("付款方名称", rsParam.getBusinessAssistantName());
            checkObj.put("付款方角色", rsParam.getBusinessAssistantRole());
            checkObj.put("订单金额", rsParam.getOrderAmount());
            checkObj.put("交易发生日期", rsParam.getTranTime());
            checkObj.put("支付类型", rsParam.getPaymentType());
        } else {
            if (!CapitalPoolConst.TransFlg.CLOSED.equals(rsParam.getTransFlg())) {
                checkObj.put("退款编码", rsParam.getRefundCode());
                checkObj.put("退款日期", rsParam.getRefundTime());
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

        // 必须项校验
        ValidatorUtils.validatorRequired(checkObj);
    }

    /**
     * 交易明细接口的具体实现
     * <p/>
     * check param
     *
     * @param request 支付明细
     */
    @Transactional
    public void writeTrans(RsRequest<CpTransactionParam> request) {
        logger.debug("记录交易明细", request);
        CpTransactionParam rsParam = request.getParam();

        if (rsParam != null) {

            RequiredCheck(rsParam);

            // 新增
            if (CapitalPoolConst.InsertFlg.YES.equals(rsParam.getInsertFlg())) {

                // 神农客 设置收款方编码
                if (CapitalPoolConst.SupplyPlatform.SNK == rsParam.getSupplyPlatform()) {
                    rsParam.setBusinessMainCode(CapitalPoolConst.OtherConst.SNK_ID);
                }

                // 美食客 设置收款方编码
                if (CapitalPoolConst.SupplyPlatform.MSK == rsParam.getSupplyPlatform()) {
                    // 卖家角色
                    Integer businessMainRole = rsParam.getBusinessMainRole();
                    if (CapitalPoolConst.RoleType.ROLE_BUYERE.equals(businessMainRole + "")) {
                        // 调买手接口查询买手显示编码
                        String businessMainCode = SORestUtil.getSlCodeDis(rsParam.getBusinessMainId(), CapitalPoolConst.RoleType.ROLE_BUYERE);
                        rsParam.setBusinessMainCode(businessMainCode);
                    } else {
                        // 调卖家接口查询卖家显示编码
                        String businessMainCode = SORestUtil.getSlCodeDis(rsParam.getBusinessMainId(), null);
                        rsParam.setBusinessMainCode(businessMainCode);
                    }
                }

                createTrans(rsParam, request.getLoginId());

            } else {

                SoCpBuyerBill buyerBill = transLogic.findOneBuyerBill(null, rsParam.getTransCode(),
                        rsParam.getTransType());

                if (buyerBill == null) {
                    msg.setMessage("交易编码为" + rsParam.getTransCode() + "的买家计费不存在！");
                    logger.debug("交易编码为" + rsParam.getTransCode() + "的买家计费不存在！");
                    throw new BusinessException(msg);
                }

                SoCpTransaction trans = transLogic.findOneTrans(null, rsParam.getTransCode(), rsParam.getTransType());

                if (trans == null) {
                    msg.setMessage("交易编码为" + rsParam.getTransCode() + "的买家计费记录不存在！");
                    logger.debug("交易编码为" + rsParam.getTransCode() + "的买家计费记录不存在！");
                    throw new BusinessException(msg);
                }

                rsParam.setBusinessMainId(trans.getBusinessMainId());
                rsParam.setBusinessAssistantId(trans.getBusinessAssistantId());
                rsParam.setBusinessMainCode(trans.getBusinessMainCode());
                rsParam.setBusinessMainName(trans.getBusinessMainName());
                rsParam.setBusinessMainRole(trans.getBusinessMainRole());

                // 取消订单、交易关闭
                if (CapitalPoolConst.TransFlg.CLOSED.equals(rsParam.getTransFlg())) {

                    // 设定交易标志交易关闭
                    trans.setTransFlg(rsParam.getTransFlg());

                    // 更新者
                    trans.setUpdId(request.getLoginId());
                    trans.setUpdTime(DateTimeUtil.getCustomerDate());

                    // 更新买家交易信息
                    int count = transLogic.modifyTrans(trans);

                    // 线上并且已付款
                    // if (CapitalPoolConst.PaymentType.ONLINE == trans.getPaymentType() &&
                    // buyerBill.getDue().compareTo(buyerBill.getPaid()) == 0) {
                    // 退款金额
                    buyerBill.setRefundable(trans.getDue());
                    // 退款标志
                    buyerBill.setRefundFlg(CapitalPoolConst.RefundFlg.WITHREFUND);

                    rsParam.setOrderAmount(trans.getOrderAmount());

                    // 退款明细
                    createRefund(rsParam, request.getLoginId(), false);
                    // }

                } else {
                    // 申请退货退款
                    // 退款明细
                    createRefund(rsParam, request.getLoginId(), true);

                    // 退款金额
                    buyerBill.setRefundable(buyerBill.getRefundable().add(rsParam.getRefundAmount()));
                    // 退款标志
                    buyerBill.setRefundFlg(CapitalPoolConst.RefundFlg.WITHREFUND);

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

                buyerBill.setUpdId(request.getLoginId());
                buyerBill.setUpdTime(DateTimeUtil.getCustomerDate());
                // 更新买家交易信息
                int count = transLogic.modifyBuyerBill(buyerBill);

                if (count != 1) {
                    msg.setMessage("交易编码为" + rsParam.getTransCode() + "的买家计费记录更新失败！");
                    logger.debug("交易编码为" + rsParam.getTransCode() + "的买家计费记录更新失败！");
                    throw new BusinessException(msg);
                }

            }
        }
    }

    /**
     * 新增交易
     *
     * @param rsParam 交易参数
     * @param loginId 操作者ID
     */
    @Transactional
    private void createTrans(CpTransactionParam rsParam, String loginId) {
        // 计费基础
        SoCpTransaction trans = rsParam;

        // 主键
        Long transId = commonLogic.maxId("SO_CP_TRANSACTION", "TRANS_ID");
        trans.setTransId(transId);

        // 应付金额
        trans.setDue(rsParam.getOrderAmount());

        // 应收金额
        trans.setReceiveable(rsParam.getOrderAmount());

        // 收款方角色
        trans.setBusinessMainRole(rsParam.getBusinessMainRole());

        // 获取买手信息
        String businessAssistantId = rsParam.getBusinessAssistantId();
        if (!StringUtil.isNullOrEmpty(businessAssistantId)) {
            List<String> stringList = new ArrayList<String>();
            stringList.add(businessAssistantId);
            // 调接口 查询 买家对应的买手信息
            List<IBS2101116Bean> resultList = SOControllerUtil.searchBuyerList(stringList);
            if (CollectionUtils.isNotEmpty(resultList)) {
                IBS2101116Bean ibs2101116Bean = resultList.get(0);
                trans.setBsId(ibs2101116Bean.getSlId());
                trans.setBsCode(ibs2101116Bean.getSlCode());
                trans.setBsName(ibs2101116Bean.getSlName());
            }
        }

        // 创建者、更新者、生效者
        Date now = DateTimeUtil.getCustomerDate();
        trans.setCrtId(loginId);
        trans.setCrtTime(now);
        trans.setUpdId(loginId);
        trans.setUpdTime(now);
        trans.setActId(loginId);
        trans.setActTime(now);

        trans.setRemark(rsParam.getRemark());

        // 插入
        int transCount = transLogic.saveTrans(trans);

        if (transCount != 1) {
            msg.setMessage("交易编码为" + rsParam.getTransCode() + "的交易记录新增失败！");
            logger.debug("交易编码为" + rsParam.getTransCode() + "的交易记录新增失败！");
            throw new BusinessException(msg);
        }

        // 买家计费单
        SoCpBuyerBill buyerBill = new SoCpBuyerBill();

        // 主键
        Long buyerBillId = commonLogic.maxId("SO_CP_BUYER_BILL", "BUYER_BILL_ID");
        buyerBill.setBuyerBillId(buyerBillId);

        // 交易编码
        buyerBill.setTransCode(rsParam.getTransCode());

        // 计费基础ID
        buyerBill.setTransId(transId);

        // 订单ID
        buyerBill.setOrderId(rsParam.getOrderId());

        // 交易类型
        buyerBill.setTransType(rsParam.getTransType());

        // 应付金额
        buyerBill.setDue(rsParam.getOrderAmount());

        // 创建者、更新者、生效者
        now = DateTimeUtil.getCustomerDate();
        buyerBill.setCrtId(loginId);
        buyerBill.setCrtTime(now);
        buyerBill.setUpdId(loginId);
        buyerBill.setUpdTime(now);
        buyerBill.setActId(loginId);
        buyerBill.setActTime(now);

        // 插入
        int buyerBillCount = transLogic.saveBuyerBill(buyerBill);

        if (buyerBillCount != 1) {
            msg.setMessage("交易编码为" + rsParam.getTransCode() + "的买家计费记录新增失败！");
            logger.debug("交易编码为" + rsParam.getTransCode() + "的买家计费记录新增失败！");
            throw new BusinessException(msg);
        }
    }

    /**
     * 新增退款记录
     *
     * @param rsParam 退款参数
     * @param loginId 操作者ID
     */
    @Transactional
    private void createRefund(CpTransactionParam rsParam, String loginId, Boolean flg) {
        // 插入退回费用明细
        SoCpRefund refund = new SoCpRefund();
        int count = 0;

        // 交易编码
        refund.setTransCode(rsParam.getTransCode());

        // 交易类型
        refund.setTransType(rsParam.getTransType());

        // 订单ID
        refund.setOrderId(rsParam.getOrderId());

        // 付款人ID
        refund.setPayerId(rsParam.getBusinessAssistantId());

        // 付款人角色
        refund.setPayerRole(rsParam.getBusinessAssistantRole());

        // 平台类型
        refund.setPlatform(rsParam.getSupplyPlatform());

        // 创建者、更新者、生效者
        Date now = DateTimeUtil.getCustomerDate();
        refund.setCrtId(loginId);
        refund.setCrtTime(now);
        refund.setUpdId(loginId);
        refund.setUpdTime(now);
        refund.setActId(loginId);
        refund.setActTime(now);

        if (flg) {

            // 退款编码
            refund.setRefundCode(rsParam.getRefundCode());

            // 发生日期
            refund.setRefundTime(rsParam.getRefundTime());

            // 退回费用类型(0：退货退款 1：拒收退款 2：关闭订单)
            refund.setRefundType(CapitalPoolConst.RefundType.GOODSRETURN);

            // 退货单、拒收单列表
            List<FundDetail> fundDetailList = rsParam.getRefundDetailList();

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

                // 收款人ID
                refund.setPayeeId(fundDetail.getPayeeId());

                // 收款人编码
                refund.setPayeeCode(slCodeDisMap.get(fundDetail.getPayeeId()));

                // 收款人名称
                refund.setPayeeName(fundDetail.getPayeeName());

                // 收款人角色
                refund.setPayeeRole(fundDetail.getPayeeRole());

                // 退款金额
                refund.setRefundAmount(fundDetail.getFundAmount());

                // 累计每项的退款金额
                if (rsParam.getRefundAmount() == null) {
                    rsParam.setRefundAmount(BigDecimal.ZERO.add(fundDetail.getFundAmount()));
                } else {
                    rsParam.setRefundAmount(rsParam.getRefundAmount().add(fundDetail.getFundAmount()));
                }

                count = transLogic.saveRefund(refund);

                if (count != 1) {
                    msg.setMessage("退款编码为" + rsParam.getRefundCode() + "的退回费用新增失败！");
                    logger.debug("退款编码为" + rsParam.getRefundCode() + "的退回费用新增失败！");
                    throw new BusinessException(msg);
                }
            }

        } else {

            // 主键
            Long refundId = commonLogic.maxId("SO_CP_REFUND", "REFUND_ID");
            refund.setRefundId(refundId);

            // 退款金额
            refund.setRefundAmount(rsParam.getOrderAmount());

            // 发生日期
            refund.setRefundTime(rsParam.getOperateDate());

            // 退回费用类型(0：退货退款 1：拒收退款 2：关闭订单)
            refund.setRefundType(CapitalPoolConst.RefundType.CANCELORDER);

            // 收款人ID
            refund.setPayeeId(rsParam.getBusinessMainId());

            // 收款人编码
            refund.setPayeeCode(rsParam.getBusinessMainCode());

            // 收款人名称
            refund.setPayeeName(rsParam.getBusinessMainName());

            // 收款人角色
            refund.setPayeeRole(rsParam.getBusinessMainRole());

            count = transLogic.saveRefund(refund);

            if (count != 1) {
                msg.setMessage("退款编码为" + rsParam.getRefundCode() + "的退回费用新增失败！");
                logger.debug("退款编码为" + rsParam.getRefundCode() + "的退回费用新增失败！");
                throw new BusinessException(msg);
            }
        }

    }

}
