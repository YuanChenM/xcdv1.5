package com.msk.bms.order.controller.common;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.order.bean.result.ISO151507BaseReturnResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by wang_shuai on 2016/8/3.
 */
@Controller
@RequestMapping("/so/returnOrder")
public class SOBaseReturnOrderController extends BaseController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SOBaseReturnOrderController.class);


    /**
     * 退货单列表
     * @param returnId
     * @param model
     * @return
     */
    @RequestMapping(value = "init/{returnId}",method = RequestMethod.POST)
    public String init(@PathVariable("returnId") String returnId, Model model) {
        logger.debug("加载退货单基础信息数据");
        BaseParam param = new BaseParam();
        param.setFilter("returnId", returnId);
        RsRequest<BaseParam> request = new RsRequest<BaseParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        String url = SystemServerManager.SoOrderApiServerManager.getFindPageBaseReturn();
        RsResponse<ISO151507BaseReturnResult> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<ISO151507BaseReturnResult>>() {
        });
        model.addAttribute("returnOrder", rsResponse.getResult());
        return "/order/common/OM_BASE_RETURN_ORDER_INFO";

    }

}
