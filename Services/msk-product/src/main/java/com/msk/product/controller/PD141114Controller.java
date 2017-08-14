package com.msk.product.controller;

import com.hoperun.core.bean.BaseParam;
import com.msk.common.base.BaseController;
import com.msk.core.entity.PdStandard;
import com.msk.product.bean.TechnicalStdCla;
import com.msk.product.logic.StandardLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 
 * 标准标准档案卡详细信息Controller.
 *
 * @author gyh
 */
@Controller
@RequestMapping("PD141114")
public class PD141114Controller extends BaseController {
    @Autowired
    private StandardLogic standardLogic;
    /**
     * 
     * 实例化页面
     *
     * @param pdStdId
     * @param model
     * @return
     * @author gyh
     */
    @RequestMapping(value="init/{pdStdId}",method=RequestMethod.POST)
    public String init(@PathVariable("pdStdId")String pdStdId,Model model){
        BaseParam param = new BaseParam();
        param.setFilter("pdStdId", pdStdId);
        PdStandard standard = standardLogic.findOne(param);
        List<TechnicalStdCla> tscList =null;
        
        model.addAttribute("standard", standard);
        model.addAttribute("technicalStdClaList", tscList);
        return "pd/PD141114";
    }
}
