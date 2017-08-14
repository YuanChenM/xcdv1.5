package com.msk.buyers.controller;

import com.msk.common.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhou_yajun on 2016/7/11.
 */
@Controller
@RequestMapping("BY1214051")
public class BY1214051Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY1214051Controller.class);

    /**
     * 批发市场定性定级审批标准
     * @return
     */
    @RequestMapping(value = "init")
    public String init(){
        logger.info("批发市场定性定级审批标准");

        return "buyers/BY1214051";
    }
}
