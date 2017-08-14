package com.msk.price.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.base.BaseController;
import com.msk.price.bean.SP171193Result;
import com.msk.price.logic.SP171193Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wang_shuai on 2016/5/17.
 */
@Controller
@RequestMapping("SP171193")
public class SP171193Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SP171193Controller.class);
    @Autowired
    private SP171193Logic sp171193Logic;


    /**
     * 初始化页面
     *
     *
     * @return 页面
     */
    @RequestMapping(value = "init",method = RequestMethod.POST)
    public String init() {
        logger.debug("价盘通道管理页面初始化");
        return "sp/SP171193";
    }


    @RequestMapping(value = "searchDetail",method = RequestMethod.POST)
    @ResponseBody
    public PageResult<SP171193Result>  searchDetail(BasePageParam basePageParam){
        PageResult<SP171193Result> pageResult = sp171193Logic.findWayDetail(basePageParam);
        return pageResult;
    }
}
