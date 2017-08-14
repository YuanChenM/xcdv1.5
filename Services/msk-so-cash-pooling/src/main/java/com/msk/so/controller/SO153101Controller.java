package com.msk.so.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.base.BaseController;
import com.msk.so.bean.SO153101Bean;
import com.msk.so.logic.SO153101Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * 买家资金池管理
 *
 * @author yang_yang
 * @version 1.0
 **/
@Controller
@RequestMapping("SO153101")
public class SO153101Controller extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(SO153101Controller.class);

    @Autowired
    private SO153101Logic so153101Logic;

    /**
     * 买家资金池管理
     *
     * @return String
     **/
    @RequestMapping(value = "init",
        method = RequestMethod.POST)
    public String init(BasePageParam param, Model model) {
        model.addAttribute("param", param);
        logger.debug("初始化页面");
        return "so/SO153101";
    }

    /**
     * @param pageParam pageParam
     * @return PageResult<SO153101Bean>
     */
    @RequestMapping(value = "search",
        method = RequestMethod.POST)
    @ResponseBody
    public PageResult<SO153101Bean> search(BasePageParam pageParam) {
        // 买家资金池管理
        logger.info("买家资金池管理");

        return so153101Logic.findSO153101List(pageParam);
    }

}
