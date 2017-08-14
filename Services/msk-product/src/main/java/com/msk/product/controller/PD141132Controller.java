package com.msk.product.controller;

import com.hoperun.core.bean.BaseParam;
import com.msk.common.base.BaseController;
import com.msk.product.logic.PD141132Logic;
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
@RequestMapping(value = "PD141132")
public class PD141132Controller extends BaseController {
    @Autowired
    private PD141132Logic pd141132Logic;

    /**
     * 实例化页面
     *
     * @param model model
     * @return 界面
     * @author pxg
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(Model model,@RequestParam(value="product",required = false)String product,@RequestParam(value="brand",required = false)String brand,@RequestParam(value="local",required = false)String local) {
        BaseParam param = new BaseParam();
        if(null!=product && ""!=product){
            model.addAttribute("product",product);
            model.addAttribute("brand",brand);
            model.addAttribute("local",local);
        }
        return "pd/PD141132";
    }
}
