package com.msk.so.controller;

import com.hoperun.jdbc.redis.BaseRedisDao;
import com.msk.common.base.BaseController;
import com.msk.common.bean.LoginUser;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.consts.CapitalPoolConst;
import com.msk.common.consts.RedisDataBaseDef;
import com.msk.so.bean.SOCp153115Param;
import com.msk.so.logic.SO153115Logic;
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
 * 卖家减免金额详细页面
 *
 * @author zhang_chi
 * @version 1.0
 */
@Controller
@RequestMapping("SO153115")
public class SO153115Controller extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(SO153115Controller.class);

    @Autowired
    private SO153115Logic so153115Logic;

    /**
     * 卖家处理减免金额初始化
     *
     * @param model
     * @param sellerBillId
     * @param ver
     * @return
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(Model model, String sellerBillId, Integer ver, String srcPage) {
        //  从redis  获取费用调整类型
        Map<String, String> codeMasterMap = CodeMasterManager.findCodeMasterMap("RefundType");
        String value = codeMasterMap.get(CapitalPoolConst.RefundType.RELIEF+"");
        codeMasterMap.clear();
        codeMasterMap.put(CapitalPoolConst.RefundType.RELIEF+"",value);
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.putAll(codeMasterMap);
        List refundTypeList = new ArrayList(treeMap.entrySet());
        model.addAttribute("refundTypeList", refundTypeList);

        model.addAttribute("sellerBillId", sellerBillId);
        model.addAttribute("ver", ver);
        model.addAttribute("srcPage", srcPage);
        return "so/SO153115";
    }

    /**
     * 卖家处理减免金额
     *
     * @param so153115Param
     * @param sellerBillId
     * @param ver
     */
    @RequestMapping(value = "update/{sellerBillId}/{ver}/{srcPage}", method = RequestMethod.POST)
    @ResponseBody
    public void update(SOCp153115Param so153115Param, @PathVariable("sellerBillId") String sellerBillId, @PathVariable("ver") Integer ver) throws Exception {
        LoginUser user = getLoginUser();
        so153115Param.setSellerBillId(sellerBillId);
        so153115Param.setEmplId(user.getEmplId());
        so153115Param.setVer(ver);
        so153115Logic.updateAmount(so153115Param);
    }

}
