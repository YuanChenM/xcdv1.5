package com.msk.bs.controller;

import com.msk.bs.bean.BS2101113Param;
import com.msk.bs.logic.BS2101113Logic;
import com.msk.common.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhu_kai1 on 2016/7/8.
 */
@Controller
@RequestMapping(value="BS2101113")
public class BS2101113Controller extends BaseController{

    private Logger logger = LoggerFactory.getLogger(BS2101113Controller.class);

    @Autowired
    private BS2101113Logic bs2101113Logic;
    /**
     *解除买手和冻品管家之间的关系
     */
    @RequestMapping(value = "relieveRelation", method = RequestMethod.POST)
    public @ResponseBody String relieveRelation(BS2101113Param bs2101113Param){
        super.setCommonParam(bs2101113Param);
        bs2101113Logic.deleteBuyerGet(bs2101113Param);
        return  "S";
    }
}
