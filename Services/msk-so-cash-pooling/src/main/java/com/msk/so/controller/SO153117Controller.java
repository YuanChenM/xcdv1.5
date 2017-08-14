package com.msk.so.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.hoperun.core.utils.StringUtil;
import com.msk.core.entity.SoCpRefund;
import com.msk.core.entity.SoCpSellerBillHis;
import com.msk.order.bean.SellerListResult;
import com.msk.so.bean.SoCpBuyerBillBean;
import com.msk.so.logic.SO153111Logic;
import com.msk.so.logic.SO153117Logic;
import com.msk.so.logic.TransactionLogic;
import com.msk.so.utils.CheckUtil;
import com.msk.so.utils.SOControllerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.msk.common.base.BaseController;
import com.msk.common.bean.LoginUser;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.consts.CapitalPoolConst;
import com.msk.so.bean.SOCp153117Param;
import com.msk.so.logic.SO153117Logic;

/**
 * 卖家减免金额详细页面
 *
 * @author li_huiqian
 * @version 1.0
 */
@Controller
@RequestMapping("SO153117")
public class SO153117Controller extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(SO153117Controller.class);

    @Autowired
    private SO153117Logic so153117Logic;
    @Autowired
    private TransactionLogic transactionLogic;

    /**
     * 卖家处理减免金额初始化
     *
     * @param model
     * @param sellerBillId
     * @param ver
     * @return
     */
    @RequestMapping(value = "init",
        method = RequestMethod.POST)
    public String init(Model model, String id, String tb, String sellerBillId, String transCode, Integer ver,
        String srcPage) {

        // 查询
        SOCp153117Param param = new SOCp153117Param();
        if ("REFUND".equals(tb)) {
            // 从redis 获取费用调整类型
            Map<String, String> codeMasterMap = CodeMasterManager.findCodeMasterMap("RefundType");
            TreeMap<String, String> treeMap = new TreeMap<>();
            treeMap.putAll(codeMasterMap);
            List refundTypeList = new ArrayList(treeMap.entrySet());
            model.addAttribute("refundTypeList", refundTypeList);

            // 供应商编码
            List<SellerListResult> suppCodeList = SOControllerUtil.getSellerListResult(transCode);
            for (SellerListResult slr : suppCodeList) {
                slr.setUserId(
                    slr.getUserId() + "__" + slr.getUserNo() + "__" + slr.getUserName() + "__" + slr.getUserRole());
            }
            model.addAttribute("suppCodeList", suppCodeList);

            model.addAttribute("id", id);
            model.addAttribute("tb", tb);
            model.addAttribute("sellerBillId", sellerBillId);
            model.addAttribute("ver", ver);
            model.addAttribute("srcPage", srcPage);

            param.setRefundId(StringUtil.toLong(id));
            SoCpRefund soCpRefund = transactionLogic.findOneRefund(param.getRefundId(), null);
            SoCpBuyerBillBean soCpBuyerBill = so153117Logic.findOneSoCpBuyerBill(param);
            model.addAttribute("ver", soCpBuyerBill.getVer());
            model.addAttribute("refundType", soCpRefund.getRefundType());
            model.addAttribute("oldRefundType", soCpRefund.getRefundType());
            model.addAttribute("reliefAmount", soCpRefund.getRefundAmount());
            model.addAttribute("oldReliefAmount", soCpRefund.getRefundAmount());
            String suppCode = soCpRefund.getPayeeId() + "__" + soCpRefund.getPayeeCode() + "__"
                    + soCpRefund.getPayeeName() + "__" + soCpRefund.getPayeeRole();
            model.addAttribute("suppCode", suppCode);
            model.addAttribute("suppCodeName", soCpRefund.getPayeeName());
            model.addAttribute("referenceCode", soCpRefund.getRefundCode());
            SimpleDateFormat sdf = new SimpleDateFormat(CheckUtil.FORMAT_YYYYMMDD_HHMMSS);
            model.addAttribute("operateTime", sdf.format(soCpRefund.getRefundTime()));
            model.addAttribute("remark", soCpRefund.getRemark());
        } else if ("SELLER_BILL_HIS".equals(tb)) {
            // 从redis 获取费用调整类型
            Map<String, String> codeMasterMap = CodeMasterManager.findCodeMasterMap("RefundType");
            String value = codeMasterMap.get(CapitalPoolConst.RefundType.RELIEF + "");
            codeMasterMap.clear();
            codeMasterMap.put(CapitalPoolConst.RefundType.RELIEF + "", value);
            TreeMap<String, String> treeMap = new TreeMap<>();
            treeMap.putAll(codeMasterMap);
            List refundTypeList = new ArrayList(treeMap.entrySet());
            model.addAttribute("refundTypeList", refundTypeList);

            model.addAttribute("id", id);
            model.addAttribute("tb", tb);
            model.addAttribute("sellerBillId", sellerBillId);
            model.addAttribute("ver", ver);
            model.addAttribute("srcPage", srcPage);

            param.setSellerBillHisId(StringUtil.toLong(id));
            SoCpSellerBillHis soCpSellerBillHis = so153117Logic.findOneSoCpSellerBillHis(param);
            model.addAttribute("refundType", soCpSellerBillHis.getAjustType());
            model.addAttribute("reliefAmount", soCpSellerBillHis.getAjustAmount());
            model.addAttribute("oldReliefAmount", soCpSellerBillHis.getAjustAmount());
            model.addAttribute("suppCode", "");
            model.addAttribute("suppCodeName", "");
            model.addAttribute("referenceCode", "");
            SimpleDateFormat sdf = new SimpleDateFormat(CheckUtil.FORMAT_YYYYMMDD_HHMMSS);
            model.addAttribute("operateTime", sdf.format(soCpSellerBillHis.getAjustDate()));
            model.addAttribute("remark", soCpSellerBillHis.getRemark());
        }

        return "so/SO153117";
    }

    /**
     * 卖家处理减免金额
     *
     * @param so153117Param
     */
    @RequestMapping(value = "update",
        method = RequestMethod.POST)
    @ResponseBody
    public void update(SOCp153117Param so153117Param) throws Exception {
        so153117Param.setEmplId(getLoginUser().getEmplId());
        so153117Logic.updateAmount(so153117Param);
    }

}
