package com.msk.so.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.base.BaseController;
import com.msk.so.bean.SOCp153141Bean;
import com.msk.so.logic.SO153141Logic;
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
 * 账套一览
 *
 * @author yang_yang
 * @version 1.0
 **/
@Controller
@RequestMapping("SO153141")
public class SO153141Controller extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(SO153141Controller.class);
    @Autowired
    private SO153141Logic sO153141Logic;

    /**
     * 账套一览
     *
     * @return String
     **/
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(BasePageParam param, Model model) {
        model.addAttribute("param", param);
        logger.debug("初始化页面");
        return "so/SO153141";
    }

    /**
     * @param pageParam pageParam
     * @return PageResult<SO153141Bean>
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<SOCp153141Bean> search(BasePageParam pageParam) {
        // 账套一览
        logger.info("账套一览");

        return sO153141Logic.findSO153141List(pageParam);
    }

}
