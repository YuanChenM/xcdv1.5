package com.msk.product.controller;

import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.base.BaseController;
import com.msk.product.bean.PD141115Bean;
import com.msk.product.bean.PD141115Param;
import com.msk.product.logic.PD141115Logic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("PD141115")
public class PD141115Controller extends BaseController {
    @Autowired
    private PD141115Logic PD141115Logic;
    @RequestMapping(value="init",method=RequestMethod.POST)
    public String init(PD141115Param param,Model model){
        model.addAttribute("param", param);
        return "pd/PD141115";
    }
    @RequestMapping(value="search",method=RequestMethod.POST)
    public @ResponseBody
    PageResult<PD141115Bean> search(PD141115Param param){
        return PD141115Logic.findPage(param, PD141115Bean.class);
    }
    
}
