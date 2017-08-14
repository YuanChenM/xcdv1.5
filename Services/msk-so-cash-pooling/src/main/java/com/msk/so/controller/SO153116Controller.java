package com.msk.so.controller;

import com.hoperun.jdbc.redis.BaseRedisDao;
import com.msk.common.base.BaseController;
import com.msk.common.bean.LoginUser;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.consts.CapitalPoolConst;
import com.msk.common.consts.RedisDataBaseDef;
import com.msk.so.bean.SOCp153111Bean;
import com.msk.so.bean.SOCp153116Param;
import com.msk.so.logic.SO153111Logic;
import com.msk.so.logic.SO153116Logic;
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
 * 卖家支付金额详细页面
 *
 * @author zhang_chi
 * @version 1.0
 */
@Controller
@RequestMapping("SO153116")
public class SO153116Controller extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(SO153116Controller.class);

    @Autowired
    private SO153116Logic so153116Logic;

    @Autowired
    private SO153111Logic so153111Logic;

    /**
     * 卖家处理支付金额初始化
     *
     * @param model
     * @param sellerBillId
     * @return
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(Model model, String sellerBillId, Integer ver, String srcPage) {
        // 从redis  获取 支付类型
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
        // 从redis  获取 支付方式
        Map<String, String> paidMap = CodeMasterManager.findCodeMasterMap("PaidType");
        TreeMap<String, String> paidTreeMap = new TreeMap<>();
        paidTreeMap.putAll(paidMap);
        List paidTypeList = new ArrayList(paidTreeMap.entrySet());
        model.addAttribute("paidTypeList", paidTypeList);

        model.addAttribute("sellerBillId", sellerBillId);
        model.addAttribute("ver", ver);
        model.addAttribute("srcPage", srcPage);

        // 查询订单金额
        SOCp153111Bean soCp153111Bean = so153111Logic.findSellerById(sellerBillId);
        model.addAttribute("billAmount", soCp153111Bean.getBillAmount());

        return "so/SO153116";
    }

    /**
     * 卖家处理支付金额
     *
     * @param so153116Param
     * @param sellerBillId
     * @param ver
     */
    @RequestMapping(value = "update/{sellerBillId}/{ver}/{srcPage}", method = RequestMethod.POST)
    @ResponseBody
    public void update(SOCp153116Param so153116Param, @PathVariable("sellerBillId") String sellerBillId, @PathVariable("ver") Integer ver) {
        LoginUser user = getLoginUser();
        so153116Param.setSellerBillId(sellerBillId);
        so153116Param.setEmplId(user.getEmplId());
        so153116Param.setVer(ver);
        so153116Logic.updateAmount(so153116Param);
    }

}
