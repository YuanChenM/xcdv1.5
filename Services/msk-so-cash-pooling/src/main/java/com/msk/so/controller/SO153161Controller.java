package com.msk.so.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.base.BaseController;
import com.msk.so.bean.SOCp153161Bean;
import com.msk.so.logic.SO153161Logic;
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
 * 退款一览
 *
 * @author yang_yang
 * @version 1.0
 **/
@Controller
@RequestMapping("SO153161")
public class SO153161Controller extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(SO153161Controller.class);
    @Autowired
    private SO153161Logic sO153161Logic;

    /**
     * 退款一览
     *
     * @return String
     **/
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(BasePageParam param, Model model) {
        model.addAttribute("param", param);
        logger.debug("初始化页面");
        return "so/SO153161";
    }

    /**
     * @param pageParam pageParam
     * @return PageResult<SO153161Bean>
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<SOCp153161Bean> search(BasePageParam pageParam) {
        // 退款一览
        logger.info("退款一览");

        return sO153161Logic.findSO153161List(pageParam);
    }

}
