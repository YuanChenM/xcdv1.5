package com.msk.so.controller;

import com.hoperun.jdbc.redis.BaseRedisDao;
import com.msk.common.base.BaseController;
import com.msk.common.bean.LoginUser;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.consts.RedisDataBaseDef;
import com.msk.so.bean.SOCp153142Param;
import com.msk.so.logic.SO153142Logic;
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
 * 帐套 期初金额调整
 *
 * @author zhang_chi
 * @version 1.0
 */
@Controller
@RequestMapping("SO153142")
public class SO153142Controller extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(SO153142Controller.class);

    @Autowired
    private SO153142Logic so153142Logic;


    /**
     * 帐套 期初金额调整  初始化
     *
     * @param model
     * @param accountBookId
     * @param ver
     * @return
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(Model model, String accountBookId, String userNo, Integer ver) {
        // 从redis  获取 支付方式
        Map<String, String> paidMap = CodeMasterManager.findCodeMasterMap("PaidType");
        TreeMap<String, String> paidTreeMap = new TreeMap<>();
        paidTreeMap.putAll(paidMap);
        List paidTypeList = new ArrayList(paidTreeMap.entrySet());
        model.addAttribute("paidTypeList", paidTypeList);

        model.addAttribute("accountBookId", accountBookId);
        model.addAttribute("userNo", userNo);
        model.addAttribute("ver", ver);
        return "so/SO153142";
    }

    /**
     * 处理 帐套 期始金额调整
     *
     * @param so153142Param
     * @param accountBookId
     * @param ver
     */
    @RequestMapping(value = "update/{accountBookId}/{ver}", method = RequestMethod.POST)
    @ResponseBody
    public void update(SOCp153142Param so153142Param, @PathVariable("accountBookId") String accountBookId, @PathVariable("ver") Integer ver) throws Exception {
        LoginUser user = getLoginUser();
        so153142Param.setAccountBookId(accountBookId);
        so153142Param.setEmplId(user.getEmplId());
        so153142Param.setVer(ver);
        so153142Logic.updateAmount(so153142Param);
    }

}
