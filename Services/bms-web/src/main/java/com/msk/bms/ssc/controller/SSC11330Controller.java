package com.msk.bms.ssc.controller;
import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.msk.bms.ssc.bean.seller.ISL231109RsParam;
import com.msk.bms.ssc.bean.seller.ISL231109RsProduct;
import com.msk.bms.ssc.bean.seller.ISL231109RsResult;
import com.msk.bms.ssc.controller.common.ISSCRestUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.product.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
* SSC11330Controller
* 其他标准录入
*
* @author gyh
*/
@Controller
@RequestMapping("SSC11330")
public class SSC11330Controller extends BaseController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SSC11330Controller.class);


        /**
         * 企业产品 8个档案卡
         * 加工质量标准指标 tnc 加工技术标准指标 mct 原种种源标准指标 原种饲养标准指标
         * 通用质量标准指标 安全标准指标 储存运输标准指标 包装标准指标
         * @param model
         * @return
         */
    @RequestMapping(value = "getSlPdStandards", method = RequestMethod.POST)
    public String getSlPdStandards(Model model, ISL231109RsParam param) {
        logger.debug("获取产品质量标准");
        ISL231109RsResult result = ISSCRestUtil.getSlPdStandards(param);
        ISL231109RsProduct slPdBean = null;
        if (!result.getSlPdList().isEmpty()) {
            slPdBean = result.getSlPdList().get(NumberConst.IntDef.INT_ZERO);
        }
        model.addAttribute("slPdBean", slPdBean);
        return "ssc/SSC11330";
    }

}
