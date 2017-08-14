package com.msk.bms.ssc.controller;

import com.msk.common.base.BaseController;
import com.msk.ssc.bean.*;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.slf4j.LoggerFactory.getLogger;


/**
 * 选择企业信息
 * @author wu_honglei
 */
@Controller
@RequestMapping("SSC1130803")
public class SSC1130803Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = getLogger(SSC1130803Controller.class);

    /**
     * 初始化页面
     *
     * @param model
     * @return 页面
     */
    @RequestMapping(value = "init",method = RequestMethod.POST)
    public String init(Model model, SSC1130803RsBean ssc1130803RsBean) {
        logger.debug("企业信息列表页面初始化");
        model.addAttribute("ssc1130803RsBean",ssc1130803RsBean);
        return "ssc/SSC1130803";
    }



}
