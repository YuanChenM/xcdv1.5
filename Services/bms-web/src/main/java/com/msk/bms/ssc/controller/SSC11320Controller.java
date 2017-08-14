package com.msk.bms.ssc.controller;

import com.alibaba.fastjson.TypeReference;
import com.msk.bms.ssc.controller.common.ISSCCashPoolingRestUtil;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.ssc.bean.*;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * 资金池详细Controller
 * Created by peng_hao on 2016/8/9.
 */
@Controller
@RequestMapping("SSC11320")
public class SSC11320Controller {

    private static Logger logger = getLogger(SSC11320Controller.class);

    /**
     *查询付款清单和付款记录信息
     */
    @RequestMapping(value = "/init", method = RequestMethod.POST)
    public String init(SSC11320RsBean ssc11320RsBean, Model model) {
        SSC11320RsParam param = new SSC11320RsParam();
        param.setPaymentRequestId(ssc11320RsBean.getPaymentRequestId());
        param.setPaymentId(ssc11320RsBean.getPaymentId());
        SSC11320RsBean result = ISSCCashPoolingRestUtil.findSscCashPoolingDetail(param);
        model.addAttribute("ssc11320Bean", result);
        return "ssc/SSC11320";
    }


}
