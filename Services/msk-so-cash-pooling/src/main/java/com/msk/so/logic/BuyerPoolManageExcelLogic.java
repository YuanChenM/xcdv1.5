package com.msk.so.logic;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.service.ExcelAsyncPrintService;
import com.msk.so.bean.SO153101Bean;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by guan_zhongheng on 2016/8/15.
 */
@Component("BuyerPoolManageExcelLogic")
public class BuyerPoolManageExcelLogic extends ExcelAsyncPrintService{

    @Autowired
    private SO153101Logic so153101Logic;

    @Override
    public Map<String, ?> getDataSource(Object param) {
        BasePageParam baseParam = new BasePageParam();
        baseParam.getFilterMap().put("businessAssistantName",((Map) param).get("businessAssistantName"));
        baseParam.getFilterMap().put("businessAssistantCode",((Map) param).get("businessAssistantCode"));
        baseParam.getFilterMap().put("transCode",((Map) param).get("transCode"));
        baseParam.getFilterMap().put("paymentTypes",((Map) param).get("paymentTypes"));
        baseParam.getFilterMap().put("settlementStatuss",((Map) param).get("settlementStatuss"));
        baseParam.getFilterMap().put("paidTypes",((Map) param).get("paidTypes"));
        baseParam.getFilterMap().put("timeStart",((Map) param).get("orderTimeStart"));
        baseParam.getFilterMap().put("timeEnd",((Map) param).get("orderTimeEnd"));
        baseParam.getFilterMap().put("bsName",((Map) param).get("bsName"));
        baseParam.setPaging(false);
        return this.getBuyerPoolManageInfo(baseParam);
    }

    public Map<String, Object> getBuyerPoolManageInfo(BasePageParam pageParam){
        List<SO153101Bean> dataList = so153101Logic.findPageList(pageParam, SO153101Bean.class);
        BigDecimal currentOrder = BigDecimal.ZERO;
        BigDecimal currentActual = BigDecimal.ZERO;
        BigDecimal currentPaid = BigDecimal.ZERO;
        BigDecimal currentRelief = BigDecimal.ZERO;
        BigDecimal currentBalance = BigDecimal.ZERO;

        if (CollectionUtils.isNotEmpty(dataList)) {
            // 支付方式
            Map<String, String> paymentTypeMap = CodeMasterManager.findCodeMasterMap("PaymentType");
            // 支付方式
            Map<String, String> paidTypeMap = CodeMasterManager.findCodeMasterMap("PaidType");
            // 结算状态
            Map<String, String> settlementStatusMap = CodeMasterManager.findCodeMasterMap("SettlementStatus");

            // 组装数据
            for (SO153101Bean bean : dataList) {
                // 支付类型
                if (!StringUtil.isNullOrEmpty(bean.getPaymentType())) {
                    String paymentType = paymentTypeMap.get(bean.getPaymentType());
                    if (null != paymentType) {
                        bean.setPaymentType(paymentType);
                    }
                }
                // 支付方式
                if (!StringUtil.isNullOrEmpty(bean.getPaidType())) {
                    String paidType = paidTypeMap.get(bean.getPaidType());
                    if (null != paidType) {
                        bean.setPaidType(paidType);
                    }
                }
                // 结算状态
                if (!StringUtil.isNullOrEmpty(bean.getSettlementStatus())) {
                    String settlementStatus = settlementStatusMap.get(bean.getSettlementStatus());
                    if (null != settlementStatus) {
                        bean.setSettlementStatus(settlementStatus);
                    }
                }
            }
            //合计
            SO153101Bean totalBen = so153101Logic.getCurrentInfo(pageParam);
            currentOrder = totalBen.getCurrentOrder();
            currentActual = totalBen.getCurrentActual();
            currentPaid = totalBen.getCurrentPaid();
            currentRelief = totalBen.getCurrentRelief();
            currentBalance = totalBen.getCurrentBalance();
        }

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("list", dataList);
        paramMap.put("currentOrder", currentOrder);
        paramMap.put("currentActual", currentActual);
        paramMap.put("currentPaid", currentPaid);
        paramMap.put("currentRelief", currentRelief);
        paramMap.put("currentBalance", currentBalance);
        return paramMap;
    }

}
