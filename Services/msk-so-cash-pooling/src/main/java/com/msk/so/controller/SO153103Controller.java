package com.msk.so.controller;

import com.msk.common.base.BaseController;
import com.msk.common.bean.LoginUser;
import com.msk.common.config.CodeMasterManager;
import com.msk.order.bean.SellerListResult;
import com.msk.so.bean.SO153103Param;
import com.msk.so.logic.SO153103Logic;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 减免金额详细页面
 *
 * @author zhang_chi
 * @version 1.0
 */
@Controller
@RequestMapping("SO153103")
public class SO153103Controller extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(SO153103Controller.class);

    @Autowired
    private SO153103Logic so153103Logic;


    /**
     * 买家处理减免金额初始化
     *
     * @param model
     * @param buyerBillId
     * @param ver
     * @return
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(Model model, String buyerBillId, Integer ver, String transCode, String srcPage) {
        //  从redis  获取费用调整类型
        Map<String, String> codeMasterMap = CodeMasterManager.findCodeMasterMap("RefundType");
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.putAll(codeMasterMap);
        List refundTypeList = new ArrayList(treeMap.entrySet());
        model.addAttribute("refundTypeList", refundTypeList);

        // 供应商编码
        List<SellerListResult> suppCodeList = SOControllerUtil.getSellerListResult(transCode);

//        List<SellerListResult> suppCodeList = new ArrayList<SellerListResult>();
//        SellerListResult sellerListResult = new SellerListResult();
//        sellerListResult.setUserId("20");
//        sellerListResult.setUserNo("3001001");
//        sellerListResult.setUserName("神农客实业（上海）有限公司");
//        sellerListResult.setUserRole(3);
//        suppCodeList.add(sellerListResult);

        for (SellerListResult slr : suppCodeList) {
            slr.setUserId(slr.getUserId() + "__" + slr.getUserNo() + "__" + slr.getUserName() + "__" + slr.getUserRole());
        }
        model.addAttribute("suppCodeList", suppCodeList);

        model.addAttribute("buyerBillId", buyerBillId);
        model.addAttribute("ver", ver);
        model.addAttribute("srcPage", srcPage);
        return "so/SO153103";
    }

    /**
     * 买家处理减免金额
     *
     * @param so153103Param
     * @param buyerBillId
     * @param ver
     */
    @RequestMapping(value = "update/{buyerBillId}/{ver}/{srcPage}", method = RequestMethod.POST)
    @ResponseBody
    public void update(SO153103Param so153103Param, @PathVariable("buyerBillId") String buyerBillId, @PathVariable("ver") Integer ver) {
        LoginUser user = getLoginUser();
        so153103Param.setBuyerBillId(buyerBillId);
        so153103Param.setEmplId(user.getEmplId());
        so153103Param.setVer(ver);
        so153103Logic.updateAmount(so153103Param);
    }

}
