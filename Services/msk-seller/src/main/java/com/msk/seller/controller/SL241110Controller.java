package com.msk.seller.controller;

import com.msk.common.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 卖家意见查询
 * SL241110Controller.
 *
 * @author Administrator
 */


@Controller
@RequestMapping(value = "SL241110")
public class SL241110Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SL241110Controller.class);

    /**
     * 实例化页面
     *
     * @param chapId 章节ID
     * @param model  参数
     * @return 页面
     */
    @RequestMapping(value = "init/{chapId}/{chapNo}/{chapClass}", method = RequestMethod.POST)
    public String init(@PathVariable(value = "chapId") String chapId,@PathVariable(value = "chapNo") String chapNo,@PathVariable(value = "chapClass") String chapClass, Model model) {
        logger.info("logger");
        model.addAttribute("chapId", chapId);
        model.addAttribute("chapNo", chapNo);
        model.addAttribute("chapClass", chapClass);
        return "sl/SL241110";
    }

}
