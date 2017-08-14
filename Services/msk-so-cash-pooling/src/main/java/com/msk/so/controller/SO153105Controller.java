package com.msk.so.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.hoperun.core.utils.StringUtil;
import com.msk.core.entity.SoCpRefund;
import com.msk.so.logic.TransactionLogic;
import com.msk.so.utils.CheckUtil;
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
import com.msk.order.bean.SellerListResult;
import com.msk.so.bean.SO153105Param;
import com.msk.so.logic.SO153105Logic;
import com.msk.so.utils.SOControllerUtil;

/**
 * 减免金额详细页面
 *
 * @author li_huiqian
 * @version 1.0
 */
@Controller
@RequestMapping("SO153105")
public class SO153105Controller extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(SO153105Controller.class);

    @Autowired
    private SO153105Logic so153105Logic;
    @Autowired
    private TransactionLogic transactionLogic;

    /**
     * 买家处理减免金额初始化
     *
     * @param model
     * @param buyerBillId
     * @param ver
     * @return
     */
    @RequestMapping(value = "init",
        method = RequestMethod.POST)
    public String init(Model model, String refundId, String buyerBillId, Integer ver, String transCode,
        String srcPage) {
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

        model.addAttribute("buyerBillId", buyerBillId);
        model.addAttribute("ver", ver);
        model.addAttribute("refundId", refundId);
        model.addAttribute("srcPage", srcPage);

        // 查询退款退货拒收明细
        SoCpRefund soCpRefund = transactionLogic.findOneRefund(StringUtil.toLong(refundId), null);
        model.addAttribute("refundType", soCpRefund.getRefundType());
        model.addAttribute("reliefAmount", soCpRefund.getRefundAmount());
        String suppCode = soCpRefund.getPayeeId() + "__" + soCpRefund.getPayeeCode() + "__" + soCpRefund.getPayeeName()
                + "__" + soCpRefund.getPayeeRole();
        model.addAttribute("suppCode", suppCode);
        model.addAttribute("suppCodeName", soCpRefund.getPayeeName());
        model.addAttribute("referenceCode", soCpRefund.getRefundCode());
        SimpleDateFormat sdf = new SimpleDateFormat(CheckUtil.FORMAT_YYYYMMDD_HHMMSS);
        model.addAttribute("operateTime", sdf.format(soCpRefund.getRefundTime()));
        model.addAttribute("remark", soCpRefund.getRemark());
        model.addAttribute("oldReliefAmount", soCpRefund.getRefundAmount());
        model.addAttribute("oldRefundType", soCpRefund.getRefundType());

        return "so/SO153105";
    }

    /**
     * 买家处理减免金额
     *
     * @param so153105Param
     */
    @RequestMapping(value = "update",
        method = RequestMethod.POST)
    @ResponseBody
    public void update(SO153105Param so153105Param) {
        so153105Param.setEmplId(getLoginUser().getEmplId());
        so153105Logic.updateAmount(so153105Param);
    }

}
