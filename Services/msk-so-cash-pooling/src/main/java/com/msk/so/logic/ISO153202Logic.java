package com.msk.so.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.common.config.CodeMasterManager;
import com.msk.order.bean.ISO151413RsResult;
import com.msk.so.bean.*;
import com.msk.so.utils.CheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by zhang_chi on 2016/8/2.
 */
@Service
public class ISO153202Logic extends BaseLogic {

    private static Logger logger = LoggerFactory.getLogger(ISO153202Logic.class);

    @Autowired
    private SO153111Logic so153111Logic;

    @Autowired
    private SO153112Logic so153112Logic;

    /**
     * 查询买家详情打印PDF
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public ISO153202Result querySellerPDF(RsRequest<ISO153202Param> param) {
        ISO153202Result result = new ISO153202Result();
        ISO153202Param iso153202Param = param.getParam();

        if (iso153202Param == null) {
            return null;
        }
        String buyerBillId = StringUtil.toSafeString(iso153202Param.getSellerBillId());
        if (StringUtil.isEmpty(iso153202Param.getSellerBillId())) {
            return null;
        }

        // 买家计费明细
        SOCp153111Bean soSellerBill = querySellerById(iso153202Param.getSellerBillId());
        ;
        result.setSoSellerBill(soSellerBill);
        // 支付明细
        List<SoCpRunningBean> soCpRunningList = querySoCpRunningList(buyerBillId);
        result.setSoCpRunningList(soCpRunningList);
        // 订单交易信息
        ISO151413RsResult orderInfo = new ISO151413RsResult();

        String srcPage = iso153202Param.getSrcPage();

        if ("SO153111".equals(srcPage)) {
            orderInfo.setSocpTitle("卖家资金池详情");
        } else if ("SO153112".equals(srcPage)) {
            orderInfo.setSocpTitle("买手资金池详情");
        }

        result.setOrder(orderInfo);
        return result;
    }

    /**
     * 买家计费明细
     *
     * @param sellerBillId
     * @return
     */
    @Transactional(readOnly = true)
    private SOCp153111Bean querySellerById(String sellerBillId) {
        SOCp153111Bean soSellerBill = this.so153111Logic.findSellerById(sellerBillId);

        Map<String, String> statementFlgMap = CodeMasterManager.findCodeMasterMap("StatementFlg");
        String statementFlg = soSellerBill.getStatementFlg();
        if (!StringUtil.isEmpty(statementFlg) && !CollectionUtils.isEmpty(statementFlgMap)) {
            String value = statementFlgMap.get(statementFlg);
            if (!StringUtil.isEmpty(value)) {
                soSellerBill.setStatementFlg(value);
            }
        }

        Map<String, String> settlementStatusMap = CodeMasterManager.findCodeMasterMap("SettlementStatus");
        int settlementStatus = soSellerBill.getSettlementStatus();

        if (!StringUtil.isEmpty(settlementStatus + "") && !CollectionUtils.isEmpty(settlementStatusMap)) {
            String value = settlementStatusMap.get(settlementStatus + "");

            if (!StringUtil.isEmpty(value)) {
                soSellerBill.setSettlementStatusStr(value);
            }
        }
        Map<String, String> settlementFlagMap = CodeMasterManager.findCodeMasterMap("SettlementFlg");
        int settlementFlag = soSellerBill.getSettlementFlg();
        if (!StringUtil.isEmpty(settlementFlag + "") && !CollectionUtils.isEmpty(settlementFlagMap)) {

            String value = settlementFlagMap.get(settlementFlag + "");
            if (!StringUtil.isEmpty(value)) {
                soSellerBill.setSettlementFlgStr(value);
            }

        }

        Map<String, String> paymentTypeMap = CodeMasterManager.findCodeMasterMap("PaymentType");
        int paymentType = soSellerBill.getPaymentType();
        if (!StringUtil.isEmpty(paymentType + "") && !CollectionUtils.isEmpty(paymentTypeMap)) {
            String value = paymentTypeMap.get(paymentType + "");
            if (!StringUtil.isEmpty(value)) {
                soSellerBill.setPaymentTypeStr(value);
            }
        }

        Map<String, String> billTypeMap = CodeMasterManager.findCodeMasterMap("BillType");
        int billType = soSellerBill.getBillType();
        if (!StringUtil.isEmpty(billType + "") && !CollectionUtils.isEmpty(billTypeMap)) {
            String value = billTypeMap.get(billType + "");
            if (!StringUtil.isEmpty(value)) {
                soSellerBill.setBillTypeStr(value);
            }
        }

        Map<String, String> matchMap = CodeMasterManager.findCodeMasterMap("MatchVerFlg");
        String matchVerFlag = "";

        if (!StringUtil.isEmpty(soSellerBill.getMatchVerFlg()) && !CollectionUtils.isEmpty(matchMap)) {
            String value = matchMap.get(soSellerBill.getMatchVerFlg());
            if (!StringUtil.isEmpty(value)) {
                soSellerBill.setMatchVerFlg(value);
            }
        } else {
            soSellerBill.setMatchVerFlg(matchVerFlag);
        }

        Map<String, String> transPaidStatusMap = CodeMasterManager.findCodeMasterMap("TransPaidStatus");
        int transPaidStatus = soSellerBill.getTransPaidStatus();
        if (!StringUtil.isEmpty(transPaidStatus + "") && !CollectionUtils.isEmpty(transPaidStatusMap)) {
            String value = transPaidStatusMap.get(transPaidStatus + "");
            if (!StringUtil.isEmpty(value)) {

                soSellerBill.setTransPaidStatusStr(value);
            }
        }

        Map<String, String> chargeStatusMap = CodeMasterManager.findCodeMasterMap("TransPaidStatus");
        int chargeStatus = soSellerBill.getChargeStatus();
        if (!StringUtil.isEmpty(chargeStatus + "") && !CollectionUtils.isEmpty(chargeStatusMap)) {
            String value = chargeStatusMap.get(chargeStatus + "");
            if (!StringUtil.isEmpty(value)) {
                soSellerBill.setChargeStatusStr(value);
            }
        }

        return soSellerBill;
    }

    /**
     * 支付明细
     *
     * @param sellerBillId
     * @return
     */
    @Transactional(readOnly = true)
    private List<SoCpRunningBean> querySoCpRunningList(String sellerBillId) {
        List<SoCpRunningBean> soCpRunningList = this.so153112Logic.findSORunningBeanByBillId(sellerBillId);

        if (org.apache.commons.collections.CollectionUtils.isNotEmpty(soCpRunningList)) {
            // 设置时间
            SimpleDateFormat smd = new SimpleDateFormat(CheckUtil.FORMAT_YYYYMMDD_HHMMSS);
            // 支付方式
            Map<String, String> paidTypeMap = CodeMasterManager.findCodeMasterMap("PaidType");
            for (SoCpRunningBean bean : soCpRunningList) {
                // 设置支付方式
                String paidType = bean.getPaidType() + "";
                if (!StringUtil.isEmpty(paidType)) {
                    String value = paidTypeMap.get(paidType);
                    if (!StringUtil.isEmpty(value)) {
                        bean.setPaidTypeStr(value);
                    }
                }
                // 设置 退款日期
                if (null != bean.getPaidTime()) {
                    bean.setPaidTimeStr(smd.format(bean.getPaidTime()));
                }
            }
        }

        return soCpRunningList;
    }

    /**
     * 查询买家详情打印PDF
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public ISO153202Result querySellerPDFB(RsRequest<ISO153202Param> param) {
        ISO153202Result result = new ISO153202Result();
        ISO153202Param iso153202Param = param.getParam();

        if (iso153202Param == null) {
            return null;
        }
        if (StringUtil.isEmpty(iso153202Param.getSellerBillId())) {
            return null;
        }

        BaseParam baseParam = new BaseParam();
        baseParam.getFilterMap().put("sellerBillId", iso153202Param.getSellerBillId());

        // 应退款明细
        /** 添加卖家资金池详细列表更新删除功能 modify by lihuiqian on 2016/9/5 start */
        List<SOCp153112Bean> soCpRefundList = this.so153112Logic.findSORefund(baseParam);
        /** 添加卖家资金池详细列表更新删除功能 modify by lihuiqian on 2016/9/5 end */
        if (!CollectionUtils.isEmpty(soCpRefundList)) {
            // 设置时间
            SimpleDateFormat smd = new SimpleDateFormat(CheckUtil.FORMAT_YYYYMMDD_HHMMSS);
            // 退回费用类型
            Map<String, String> refundTypeMap = CodeMasterManager.findCodeMasterMap("RefundType");
            /** 添加卖家资金池详细列表更新删除功能 modify by lihuiqian on 2016/9/5 start */
            for (SOCp153112Bean bean : soCpRefundList) {
                /** 添加卖家资金池详细列表更新删除功能 modify by lihuiqian on 2016/9/5 end */
                // 设置 退回费用类型
                String refundType = bean.getRefundType() + "";
                if (!StringUtil.isEmpty(refundType)) {
                    String value = refundTypeMap.get(refundType);
                    if (!StringUtil.isEmpty(value)) {
                        bean.setRefundTypeStr(value);
                    }
                }
                // 设置 退款日期
                if (null != bean.getRefundTime()) {
                    bean.setRefundTimeStr(smd.format(bean.getRefundTime()));
                }
            }
        }

        result.setSoCpRefundList(soCpRefundList);
        return result;
    }

}
