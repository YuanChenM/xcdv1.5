package com.msk.seller.controller;

import com.msk.common.base.BaseUploadController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 卖家产品池注册总控表列表Controller.
 *
 * @author pxg
 */
@Controller
@RequestMapping(value = "SL241131")
public class SL241131Controller extends BaseUploadController {

    private static Logger logger = LoggerFactory.getLogger(SL241117Controller.class);

    /**
     * 卖家产品池注册总控表列表
     * @return 卖家产品池注册总控表列表
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init() {
        logger.debug("卖家产品池注册总控表列表");
        return "sl/SL241131";
    }
}
