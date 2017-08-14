package com.msk.ds.controller;

import com.msk.common.base.BaseController;
import com.msk.ds.bean.ActualInput;
import com.msk.ds.bean.SC183103Param;
import com.msk.ds.logic.SC183103Logic;
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
 * SC183103Controller
 * 
 * @author zhou_yajun
 *
 */
@Controller
@RequestMapping("SC183103")
public class SC183103Controller extends BaseController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(SC183103Controller.class);

    /** SC183103Logic */
    @Autowired
    private SC183103Logic sc183103Logic;

    /**
     * 初始化页面
     * @param sc183103Param sc183103Param
     * @param model model
     * @return 画面
     */
    @RequestMapping(value = "init",
        method = RequestMethod.POST)
    public String init(SC183103Param sc183103Param ,Model model) {
        logger.debug("实际录入画面初始化");

        ActualInput halfParam = sc183103Logic.getHalfName(sc183103Param);
        model.addAttribute("halfParam",halfParam);
        return "ds/SC183103";
    }

    /**
     * 下拉框选择
     * @param sc183103Param sc183103Param
     * @return 画面
     */
    @RequestMapping(value = "selectChange",
            method = RequestMethod.POST)
    public @ResponseBody
    List<ActualInput> selectChange(SC183103Param sc183103Param) {
        logger.info("实际录入画面下拉框选择");
        List<ActualInput> halfParamList = new ArrayList<>();
        ActualInput halfParam = sc183103Logic.getHalfName(sc183103Param);
        halfParamList.add(halfParam);
        return halfParamList;
    }

    /**
     * 保存变更
     * @param param
     * @return
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public @ResponseBody String save(SC183103Param param) throws Exception {
        logger.info("实际录入保存变更");
        this.setCommonParam(param);
//        List<ExceptionMessage> msgs = new ArrayList<>();
//        ExceptionMessage msg = new ExceptionMessage();
        //添加验证
//        if(!ValidatorUtils.checkNumber(param.getChangeOverNum().toString())){
//            msg.setMessage("输入额不是数字类型！");
//            msgs.add(msg);
//            throw new ValidatorException(msgs);
//        }
        sc183103Logic.saveChangeNum(param);
        return param.getFilterMap().get("entryMark").toString();
    }

}
