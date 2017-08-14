package com.msk.buyers.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.buyers.bean.BY121412Bean;
import com.msk.buyers.logic.BY121412Logic;
import com.msk.common.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 菜场定性定级各阶段一览表 Controller
 * Created by yuan_zhifei on 2016/7/13.
 */
@Controller
@RequestMapping("BY121412")
public class BY121412Controller extends BaseController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121412Controller.class);

    @Autowired
    private BY121412Logic by121412Logic;

    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(Model model) {
        logger.debug("菜场定性定级各阶段一览表初始化");
        return "buyers/BY121412";
    }

   @RequestMapping(value = "search", method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<BY121412Bean> search(BasePageParam param) {
        logger.debug("菜场定性定级各阶段查询");
        PageResult<BY121412Bean> list= this.by121412Logic.findPage(param, BY121412Bean.class);
        return list;
    }
}