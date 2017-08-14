package com.msk.product.controller;

import com.msk.common.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 物流区选择
 *
 * @author yuan_chen
 */
@Controller
@RequestMapping("PD141122")
public class PD141122Controller extends BaseController {

    /**
     * 实例化页面
     *
     * @param model 参数
     * @return 页面
     */
    @RequestMapping(value = "init",
        method = RequestMethod.POST)
    public String init(Model model) {
        return "pd/PD141122";
    }

}
