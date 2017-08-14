package com.msk.so.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.hoperun.core.utils.StringUtil;
import com.msk.core.entity.SoCpRunning;
import com.msk.so.logic.TransactionLogic;
import com.msk.so.utils.CheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.msk.common.base.BaseController;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.consts.CapitalPoolConst;
import com.msk.so.bean.SO153106Param;
import com.msk.so.logic.SO153106Logic;

/**
 * 买家付款详细更新页面
 *
 * @author lihuiqian
 * @version 1.0
 */
@Controller
@RequestMapping("SO153106")
public class SO153106Controller extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(SO153106Controller.class);

    @Autowired
    private SO153106Logic so153106Logic;

    @Autowired
    private TransactionLogic transactionLogic;

    /**
     * 处理支付金额初始化
     *
     * @param model
     * @param buyerBillId
     * @return
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(Model model, String runningId, String buyerBillId, Integer ver, String srcPage) {
        // 从redis  获取 支付类型
        Map<String, String> amountMap = CodeMasterManager.findCodeMasterMap("AmountType");
        String valuePaid = amountMap.get(CapitalPoolConst.AmountType.PAID+"");
        String valueRefund = amountMap.get(CapitalPoolConst.AmountType.REFUND+"");
        amountMap.clear();
        amountMap.put(CapitalPoolConst.AmountType.PAID+"",valuePaid);
        amountMap.put(CapitalPoolConst.AmountType.REFUND+"",valueRefund);

        TreeMap<String, String> amounTreeMap = new TreeMap<>();
        amounTreeMap.putAll(amountMap);
        List amountTypeList = new ArrayList(amounTreeMap.entrySet());
        model.addAttribute("amountTypeList", amountTypeList);
        // 从redis  获取 支付方式
        Map<String, String> paidMap = CodeMasterManager.findCodeMasterMap("PaidType");
        TreeMap<String, String> paidTreeMap = new TreeMap<>();
        paidTreeMap.putAll(paidMap);
        List paidTypeList = new ArrayList(paidTreeMap.entrySet());
        model.addAttribute("paidTypeList", paidTypeList);

        model.addAttribute("buyerBillId", buyerBillId);
        model.addAttribute("ver", ver);
        model.addAttribute("runningId", runningId);
        model.addAttribute("srcPage", srcPage);

        // 查询订单金额
        SoCpRunning soCpRunning = transactionLogic.findOneRunning(StringUtil.toLong(runningId));

        model.addAttribute("amountType", soCpRunning.getAmountType());
        model.addAttribute("oldAmountType", soCpRunning.getAmountType());
        model.addAttribute("paidType", soCpRunning.getPaidType());
        model.addAttribute("paidAmount",soCpRunning.getPaidAmount());
        model.addAttribute("oldPaidAmount",soCpRunning.getPaidAmount());
        model.addAttribute("refundCode", soCpRunning.getRefundCode());
        model.addAttribute("paidSeq", soCpRunning.getPaidSeq());
        SimpleDateFormat sdf = new SimpleDateFormat(CheckUtil.FORMAT_YYYYMMDD_HHMMSS);
        model.addAttribute("operateTime", sdf.format(soCpRunning.getPaidTime()));
        model.addAttribute("handler", soCpRunning.getHandler());
        model.addAttribute("remark", soCpRunning.getRemark());

        return "so/SO153106";
    }

    /**
     * 处理支付金额
     * @param so153106Param
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public void update(SO153106Param so153106Param) {
        so153106Param.setEmplId(getLoginUser().getEmplId());
        so153106Logic.updateAmount(so153106Param);
    }

}
