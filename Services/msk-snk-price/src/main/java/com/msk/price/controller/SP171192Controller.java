package com.msk.price.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.base.BaseController;
import com.msk.price.bean.SP171192Bean;
import com.msk.price.bean.SP171192Result;
import com.msk.price.logic.SP171192Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wang_shuai on 2016/5/20.
 */
@Controller
@RequestMapping("SP171192")
public class SP171192Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SP171192Controller.class);
    @Autowired
    private SP171192Logic sp171192Logic;


    /**
     * 初始化页面
     *
     *
     * @return 页面
     */
    @RequestMapping(value = "init",method = RequestMethod.POST)
    public String init() {
        logger.debug("价盘通道编辑页面初始化");
        return "sp/SP171192";
    }


    @RequestMapping(value = "searchDetail",method = RequestMethod.POST)
    @ResponseBody
    public PageResult<SP171192Result> searchDetail(BasePageParam basePageParam){
        PageResult<SP171192Result> pageResult = sp171192Logic.findWayDetail(basePageParam);
        return pageResult;
    }
    @RequestMapping(value = "del",method = RequestMethod.POST)
    public String delData(SP171192Bean sp171192Bean){
        logger.debug("删除价盘通道标准数据");
        /**Add: 横展开添加共通设置 2016/09/12   BY  任强  Start */
        super.setCommonParam(sp171192Bean);
        sp171192Bean.setUpdTime(DateTimeUtil.getCustomerDate());
        /**Add: 横展开添加共通设置 2016/09/12   BY  任强  End */
        sp171192Logic.delWay(sp171192Bean);
        return this.init();
    }
}

