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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.msk.common.base.BaseController;
import com.msk.common.bean.LoginUser;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.consts.CapitalPoolConst;
import com.msk.so.bean.SOCp153111Bean;
import com.msk.so.bean.SOCp153118Param;
import com.msk.so.logic.SO153111Logic;
import com.msk.so.logic.SO153118Logic;

/**
 * 卖家支付金额详细页面
 *
 * @author li_huiqian
 * @version 1.0
 */
@Controller
@RequestMapping("SO153118")
public class SO153118Controller extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(SO153118Controller.class);

    @Autowired
    private SO153118Logic so153118Logic;

    @Autowired
    private TransactionLogic transactionLogic;

    /**
     * 卖家处理支付金额初始化
     *
     * @param model
     * @param sellerBillId
     * @return
     */
    @RequestMapping(value = "init",
        method = RequestMethod.POST)
    public String init(Model model, String runningId, String sellerBillId, Integer ver, String srcPage) {
        // 从redis 获取 支付类型
        Map<String, String> amountMap = CodeMasterManager.findCodeMasterMap("AmountType");

        String valuePaid = amountMap.get(CapitalPoolConst.AmountType.PAID + "");
        String valueRefund = amountMap.get(CapitalPoolConst.AmountType.REFUND + "");
        amountMap.clear();
        amountMap.put(CapitalPoolConst.AmountType.PAID + "", valuePaid);
        amountMap.put(CapitalPoolConst.AmountType.REFUND + "", valueRefund);

        TreeMap<String, String> amounTreeMap = new TreeMap<>();
        amounTreeMap.putAll(amountMap);
        List amountTypeList = new ArrayList(amounTreeMap.entrySet());
        model.addAttribute("amountTypeList", amountTypeList);
        // 从redis 获取 支付方式
        Map<String, String> paidMap = CodeMasterManager.findCodeMasterMap("PaidType");
        TreeMap<String, String> paidTreeMap = new TreeMap<>();
        paidTreeMap.putAll(paidMap);
        List paidTypeList = new ArrayList(paidTreeMap.entrySet());
        model.addAttribute("paidTypeList", paidTypeList);

        model.addAttribute("sellerBillId", sellerBillId);
        model.addAttribute("ver", ver);
        model.addAttribute("srcPage", srcPage);
        model.addAttribute("runningId", runningId);

        // 查询订单金额
        SoCpRunning soCpRunning = transactionLogic.findOneRunning(StringUtil.toLong(runningId));

        model.addAttribute("amountType", soCpRunning.getAmountType());
        model.addAttribute("oldAmountType", soCpRunning.getAmountType());
        model.addAttribute("paidType", soCpRunning.getPaidType());
        model.addAttribute("paidAmount", soCpRunning.getPaidAmount());
        model.addAttribute("oldPaidAmount", soCpRunning.getPaidAmount());
        model.addAttribute("refundCode", soCpRunning.getRefundCode());
        model.addAttribute("paidSeq", soCpRunning.getPaidSeq());
        SimpleDateFormat sdf = new SimpleDateFormat(CheckUtil.FORMAT_YYYYMMDD_HHMMSS);
        model.addAttribute("operateTime", sdf.format(soCpRunning.getPaidTime()));
        model.addAttribute("handler", soCpRunning.getHandler());
        model.addAttribute("remark", soCpRunning.getRemark());

        return "so/SO153118";
    }

    /**
     * 卖家处理支付金额
     *
     * @param param
     */
    @RequestMapping(value = "update",
        method = RequestMethod.POST)
    @ResponseBody
    public void update(SOCp153118Param param) {
        param.setEmplId(getLoginUser().getEmplId());
        so153118Logic.updateAmount(param);
    }

}
