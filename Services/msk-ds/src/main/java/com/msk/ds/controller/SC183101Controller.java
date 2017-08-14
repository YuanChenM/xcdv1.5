package com.msk.ds.controller;

import com.msk.common.base.BaseController;
import com.msk.ds.bean.PlanAdjust;
import com.msk.ds.bean.SC183101Param;
import com.msk.ds.logic.SC183101Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * SC183101Controller
 * 
 * @author zhou_yajun
 *
 */
@Controller
@RequestMapping("SC183101")
public class SC183101Controller extends BaseController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(SC183101Controller.class);

    /** SC183101Logic */
    @Autowired
    private SC183101Logic sc183101Logic;

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init",
        method = RequestMethod.POST)
    public String init(SC183101Param sc183101Param,Model model) {
        logger.info("计划调整申请画面初始化");

        PlanAdjust halfParam = sc183101Logic.getHalfName(sc183101Param);
        model.addAttribute("halfParam",halfParam);
        return "ds/SC183101";
    }
    /**
     * 下拉框选择
     * @param sc183103Param sc183103Param
     * @return 画面
     */
    @RequestMapping(value = "selectChange",
            method = RequestMethod.POST)
    public @ResponseBody
    List<PlanAdjust> selectChange(SC183101Param sc183103Param) {
        logger.info("计划调整申请画面下拉框选择");
        List<PlanAdjust> halfParamList = new ArrayList<>();
        PlanAdjust halfParam = sc183101Logic.getHalfName(sc183103Param);
        halfParamList.add(halfParam);
        return halfParamList;
    }
    /**
     * 保存变更
     * @param param
     * @return
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public @ResponseBody String save(SC183101Param param){
        logger.info("计划调整保存变更数据");
        this.setCommonParam(param);
        sc183101Logic.saveChangeNum(param);
        return param.getFilterMap().get("entryMark").toString();
    }

}
