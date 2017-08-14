package com.msk.bms.ssc.controller;

import com.hoperun.jdbc.bean.PageResult;
import com.msk.bms.ssc.controller.common.ISSCPaymentRestUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsResponse;
import com.msk.ssc.bean.*;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * 支付管控
 * @author wu_honglei
 */
@Controller
@RequestMapping("SSC1130801")
public class SSC1130801Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = getLogger(SSC1130801Controller.class);

    /**
     * 支付管控初始化
     * @param model
     * @return
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(Model model,SSC1130801RsBean bean){
        logger.info("支付管控页面初始化");
        model.addAttribute("ssc1130801RsBean", bean);
        return "ssc/SSC1130801";
    }


    /**
     * 分页查询数据
     *
     * @return 分页查询数据
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<SSC1130801RsBean> search(SSC1130801RsParam ssc1130801RsParam){

        RsResponse<PageResult<SSC1130801RsBean>> response = ISSCPaymentRestUtil.findPaymentInfoList(ssc1130801RsParam);

        return response.getResult();
    }





}
