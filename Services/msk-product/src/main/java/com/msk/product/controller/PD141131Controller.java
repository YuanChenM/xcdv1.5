package com.msk.product.controller;

import com.hoperun.core.bean.BaseParam;
import com.msk.common.base.BaseController;
import com.msk.core.entity.PdClassestree;
import com.msk.product.logic.PD141131Logic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 产品分类加工信息controller
 *
 * @author pxg
 */
@Controller
@RequestMapping(value = "PD141131")
public class PD141131Controller extends BaseController {
    @Autowired
    private PD141131Logic pd141131Logic;

    /**
     * 实例化页面
     *
     * @param model model
     * @return 界面
     * @author pxg
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(Model model,@RequestParam(value ="tcBuyinvestListId",required =false) String tcBuyinvestListId) {
        BaseParam param = new BaseParam();
        List<String> list=new ArrayList<String>();
        String str1="130-150g";
        String str2="120-150g";
        String str3="110-150g";
        String str4="100-150g";
        String str5="90-150g";
        list.add(str1);
        list.add(str2);
        list.add(str3);
        list.add(str4);
        list.add(str5);
        model.addAttribute("list",list);
        model.addAttribute("tcBuyinvestListId",tcBuyinvestListId);
        //List<PdClassestree> listOne=new ArrayList<>();
        //listOne=pd141131Logic.queryOneClassify();
        //model.addAttribute("listOne",listOne);
        return "pd/PD141131";
    }

    /**
     * 实例化页面
     *
     * @param level level
     * @param classestreeCode classestreeCode
     * @return 界面
     * @author pxg
     */
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public @ResponseBody List<PdClassestree> queryTwo(String classestreeCode,String level) {
        List<PdClassestree> listTwo=new ArrayList<>();
        listTwo=pd141131Logic.queryTwoClassify(level,classestreeCode);
        return listTwo;
    }
}
