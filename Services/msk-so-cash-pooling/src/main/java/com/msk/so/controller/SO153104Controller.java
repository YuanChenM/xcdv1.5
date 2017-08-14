package com.msk.so.controller;

import com.msk.common.base.BaseController;
import com.msk.common.bean.LoginUser;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.consts.CapitalPoolConst;
import com.msk.so.bean.SO153101Bean;
import com.msk.so.bean.SO153104Param;
import com.msk.so.logic.SO153101Logic;
import com.msk.so.logic.SO153104Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 金额详细页面
 *
 * @author zhang_chi
 * @version 1.0
 */
@Controller
@RequestMapping("SO153104")
public class SO153104Controller extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(SO153104Controller.class);

    @Autowired
    private SO153104Logic so153104Logic;

    @Autowired
    private SO153101Logic so153101Logic;

    /**
     * 处理支付金额初始化
     *
     * @param model
     * @param buyerBillId
     * @param srcPage
     * @return
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(Model model, String buyerBillId, Integer ver,String srcPage) {
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
        model.addAttribute("srcPage", srcPage);

        // 查询订单金额
        SO153101Bean so153101Bean = so153101Logic.findBuyerById(buyerBillId);
        model.addAttribute("orderAmount", so153101Bean.getOrderAmount());

        return "so/SO153104";
    }

    /**
     * 处理支付金额
     * @param so153104Param
     * @param buyerBillId
     * @param ver
     */
    @RequestMapping(value = "update/{buyerBillId}/{ver}/{srcPage}", method = RequestMethod.POST)
    @ResponseBody
    public void update(SO153104Param so153104Param, @PathVariable("buyerBillId") String buyerBillId, @PathVariable("ver") Integer ver) {
        LoginUser user = getLoginUser();
        so153104Param.setBuyerBillId(buyerBillId);
        so153104Param.setEmplId(user.getEmplId());
        so153104Param.setVer(ver);
        so153104Logic.updateAmount(so153104Param);
    }

}
