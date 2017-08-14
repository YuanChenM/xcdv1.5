package com.msk.product.controller;

import com.hoperun.core.bean.BaseParam;
import com.msk.common.base.BaseController;
import com.msk.product.logic.PD141133Logic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 市场需求审核注册修改添加controller
 *
 * @author pxg
 */
@Controller
@RequestMapping(value = "PD141133")
public class PD141133Controller extends BaseController {
    @Autowired
    private PD141133Logic pd141133Logic;

    /**
     * 实例化页面
     *
     * @param model model
     * @return 界面
     * @author pxg
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(Model model,@RequestParam(value="standard",required = false)String standard,@RequestParam(value="survey",required = false)String survey) {
        BaseParam param = new BaseParam();
        if(null!=standard && ""!=standard){
            model.addAttribute("standard",standard);
            model.addAttribute("survey",survey);
        }
        return "pd/PD141133";
    }
}
