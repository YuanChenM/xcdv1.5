package com.msk.product.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.msk.common.base.BaseController;
import com.msk.core.entity.PdWeight;
import com.msk.product.bean.PD141103Param;
import com.msk.product.bean.PD14112406Param;
import com.msk.product.bean.PD141124Bean;
import com.msk.product.logic.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 产品分类加工信息controller
 *
 * @author yangchunyan
 */
@Controller
@RequestMapping(value = "PD14112407")
public class PD14112407Controller extends BaseController {
    @Autowired
    private PD141124Logic pd141124Logic;
    @Autowired
    private PD14112401Logic pd14112401Logic;
    @Autowired
    private PD14112404Logic pd14112404Logic;
    @Autowired
    private PD14112407Logic pd14112407Logic;
    /**
     * 实例化页面
     *
     * @param bean  参数
     * @param model 参数
     * @return 页面
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String initFast(
            Model model, PD141103Param bean) {
        BaseParam param = new BaseParam();
        //五级分类类目操作
        /** Modfiy: Bug #2442 : 系统长时间未操作后点击菜单报错系统异常   20160905   BY  杨春艳  End */
        param.setFilter("level", String.valueOf(NumberConst.IntDef.INT_ONE));
        //显示一级类目下拉框
        if(bean.getClassestreeName5()!=null){
            if(bean.getClassestreeName5().endsWith(PD14112404Logic.KG)){
                bean.setClassestreeName5(bean.getClassestreeName5().replaceAll((PD14112404Logic.KG),""));
                model.addAttribute("showKg", "(kg)");
            }
        }
        //获取下拉框
//        List<PdWeight> WeightList= this.pd14112404Logic.findListWeights(bean);
//        model.addAttribute("WeightList", WeightList);
        List<PD141124Bean> list = this.pd141124Logic.findList(param);
        model.addAttribute("classestree", bean);
        model.addAttribute("classesList",list);
        return "pd/PD14112407";
    }


    /**
     * 保存产品信息
     *
     * @param bean  参数
     * @return 页面
     */
    @RequestMapping(value = "addClassTree",
            method = RequestMethod.POST)
    public @ResponseBody
    String saveBreed(PD141103Param bean) {
        BaseParam param = new BaseParam();
        super.setCommonParam(param);
        //添加操作 同时添加machining表数据
        int saveClassesTree = NumberConst.IntDef.INT_ZERO;
        switch (bean.getSaveType()){
            case NumberConst.IntDef.INT_TWO : saveClassesTree = pd14112401Logic.saveClassesTree(bean ,param);break;
            case NumberConst.IntDef.INT_THREE : saveClassesTree = pd14112407Logic.saveBreed(bean,param);break;
            case NumberConst.IntDef.INT_FOUR : saveClassesTree = pd14112407Logic.saveFeature(bean,param);break;
            case NumberConst.IntDef.INT_FIVE : saveClassesTree = pd14112407Logic.saveWeight(bean, param);break;
            case NumberConst.IntDef.INT_SIX : saveClassesTree = pd14112407Logic.saveNorms(bean,param);break;
        }

        return saveClassesTree + "";
    }

}
