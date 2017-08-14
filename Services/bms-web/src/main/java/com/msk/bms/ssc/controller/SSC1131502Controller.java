package com.msk.bms.ssc.controller;

import com.alibaba.fastjson.TypeReference;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.ssc.bean.SSC1131502Bean;
import com.msk.ssc.bean.SSC1131502Param;
import com.msk.ssc.bean.SSC1131502Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 业务控制器：三方确认履历
 * Created by xia_xiaojie on 2016/8/22.
 */
@Controller
@RequestMapping("/SSC1131502")
public class SSC1131502Controller extends BaseController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(SSC11322Controller.class);

    /** 平台编号 */
    private static final String SITECODE = "1";
    /** 平台身份识别码 */
    private static final String AUTH = "MSK00001";
    /** 用户登录ID */
    private static final String LOGINID = "msk01";

    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public String view(SSC1131502Param ssc1131502Param, Model model) {
        long deliveryConfirmId = ssc1131502Param.getDeliveryConfirmId();
        List<SSC1131502Bean> productHistories = this.findProductHistoriesByDcId(deliveryConfirmId);
        model.addAttribute("productHistories", productHistories);
        return "ssc/SSC1131502";
    }

    public static RsResponse<SSC1131502Result> findProductHistories(SSC1131502Param ssc1131502Param) {
        RsRequest<SSC1131502Param> reqParam = new RsRequest<SSC1131502Param>();
        reqParam.setSiteCode(SITECODE);
        reqParam.setAuth(AUTH);
        reqParam.setLoginId(LOGINID);
        reqParam.setParam(ssc1131502Param);

        String restUrl = SystemServerManager.SellerSupplyChainManage.getFindProductHistories();
        //String restUrl = "http://localhost:8081/msk-ssc-api/api/ssc/findProductHistories";
        TypeReference ref = new TypeReference<RsResponse<SSC1131502Result>>() {};
        RsResponse<SSC1131502Result> respResult = RestClientUtil.post(restUrl, reqParam, ref);
        return respResult;
    }

    private List<SSC1131502Bean> findProductHistoriesByDcId(long deliveryConfirmId) {
        SSC1131502Param ssc1131502Param = new SSC1131502Param();
        ssc1131502Param.setDeliveryConfirmId(deliveryConfirmId);
        RsResponse<SSC1131502Result> respResult = findProductHistories(ssc1131502Param);
        return respResult.getResult().getProductHistories();
    }

}
