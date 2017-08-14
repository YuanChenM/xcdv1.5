package com.msk.product.controller;

import com.alibaba.fastjson.JSONObject;
import com.msk.common.base.BaseController;
import com.msk.product.bean.PD141199Bean;
import com.msk.product.bean.PD141199Param;
import com.msk.product.logic.PD141199Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * PD141199Controller
 *
 * @author zhou_ling
 * @version 1.0
 **/
@Controller
@RequestMapping("PD141199")
public class PD141199Controller extends BaseController {
    /**
     * logger
     *
     * @author zhou_ling
     */
    private static Logger logger = LoggerFactory.getLogger(PD141199Controller.class);

    @Autowired
    private PD141199Logic pd141199Logic;

    /**
     * 加载订单列表界面
     *
     * @param model         model
     * @param pd141199Param pd141199Param
     * @return String
     **/
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(Model model, PD141199Param pd141199Param) {
        List<PD141199Bean> priceCycleList = null;
        logger.debug("初始化页面");
        if (isDebug) {
            logger.info("假数据");
            // 产品类别信息获取假数据
            priceCycleList = pd141199Logic.findPriceCycleList();

        } else {
            // 产品价盘信息db查询
            priceCycleList = pd141199Logic.findPriceCycle(pd141199Param);
        }
        model.addAttribute("priceCycleList", priceCycleList);
        model.addAttribute("pd141199Param", pd141199Param);

        List<PD141199Param> pd141199Params = new ArrayList<PD141199Param>();
        pd141199Params.add(pd141199Param);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pd141199Param", pd141199Params);
        model.addAttribute("paramJson", jsonObject.toString());
        // 返回前台页面
        return "pd/PD141199";
    }

    /**
     * 删除产品价盘
     *
     * @param model         参数
     * @param pd141199Param 参数
     * @return 结果
     */
    @RequestMapping(value = "deletePriceprd", method = RequestMethod.POST)
    public String deletePriceprd(Model model, PD141199Param pd141199Param) {
        pd141199Logic.deletePriceprd(pd141199Param);
        return this.init(model, pd141199Param);
    }

}
