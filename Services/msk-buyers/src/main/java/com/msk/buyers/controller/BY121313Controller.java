package com.msk.buyers.controller;

import com.hoperun.core.exception.BusinessException;
import com.msk.buyers.bean.BY121313Bean;
import com.msk.buyers.logic.IBY121202Logic;
import com.msk.common.base.BaseController;
import com.msk.core.entity.ByBuyerBasicInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 买家信息总控画面
 *
 * @author yuan_chen
 */
@Controller
@RequestMapping("BY121313")
public class BY121313Controller extends BaseController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121313Controller.class);

    @Autowired
    private IBY121202Logic iby121202Logic;

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init/{buyerId}",
            method = RequestMethod.POST)
    public String init(@PathVariable("buyerId") String buyerId, Model model) {
        logger.debug("买家信息总控画面");
        if(buyerId == null){
            throw new BusinessException("参数异常");
        }
        ByBuyerBasicInfo byBasic = iby121202Logic.findBuyerById(buyerId);
        if(null == byBasic){
            throw new BusinessException("买家不存在");
        }
        model.addAttribute("buyerId",byBasic.getBuyerId());
        model.addAttribute("marketingsStatus",byBasic.getMarketingsStatus());
        model.addAttribute("buyerType",byBasic.getSuperiorType());
        return "buyers/BY121313";
    }
}
