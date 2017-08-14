package com.msk.seller.controller;

import com.hoperun.core.utils.DateTimeUtil;
import com.msk.common.base.BaseController;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.LgcsAreaBean;
import com.msk.seller.bean.SL241132Bean;
import com.msk.seller.logic.SL24113201Logic;
import com.msk.seller.utils.SLControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by fjm on 2016/1/28.
 */
@Controller
@RequestMapping("SL24113201")
public class SL24113201Controller extends BaseController{

    @Autowired
    private SL24113201Logic sl24113201Logic;

    //Modified by xia_xiaojie on 2016/6/21. Modified start.
//    @Autowired
//    private CommonLogic commonLogic;
    //Modified end.

    /**
     * Deprecated by xia_xiaojie on 2016/6/21.
     *
     * 编辑初始化页面
     * @param model model
     * @return String
     */
    /*@RequestMapping(value="init",method = RequestMethod.POST)
    public  String init(SL241132Bean sl241132Bean,Model model){
        List<CommConstant> list=commonLogic.findConstantList("saleStatus");
        model.addAttribute("slPdArtnos",sl241132Bean);
        model.addAttribute("list",list);
        return "sl/SL24113201";
    }*/

    /**
     * 初始化编辑页面。替代旧init()。
     * Created by xia_xiaojie on 2016/6/21.
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(SL241132Bean sl241132Bean, Model model) {
        model.addAttribute("slPdArtnos", sl241132Bean);
        //查询所有物流区
        DistrictParam districtParam = new DistrictParam();
        List<LgcsAreaBean> logisticsAreaList = SLControllerUtil.getLogisticsAreaList(districtParam).getResult().getLgcsAreaList();
        model.addAttribute("logisticsAreaList", logisticsAreaList);
        return "sl/SL24113201";
    }

    /**
     * 编辑初始化页面
     * @param sl241132Bean sl241132Bean
     * @return String
     */

    @RequestMapping(value="save",method = RequestMethod.POST)
    public @ResponseBody int save(SL241132Bean sl241132Bean){
        sl241132Bean.setCrtTime(DateTimeUtil.getCustomerDate());
        sl241132Bean.setUpdTime(DateTimeUtil.getCustomerDate());
        int num=sl24113201Logic.modify(sl241132Bean);
        return num;
    }
}
