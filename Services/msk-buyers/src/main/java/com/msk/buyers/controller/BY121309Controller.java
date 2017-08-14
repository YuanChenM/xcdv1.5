package com.msk.buyers.controller;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.buyers.bean.BY121309Bean;
import com.msk.buyers.bean.BY121309Param;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 买家买家池已购产品目录 controller
 * Created by tao_zhifa on 2016/7/5.
 */
@Controller
@RequestMapping("BY121309")
public class BY121309Controller extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(BY121309Controller.class);

    /**
     * 买家买家池已购产品目录初始化
     * @param buyerId
     * @param model
     * @return
     */
    @RequestMapping(value = "init/{buyerId}", method = RequestMethod.POST)
    public String init(@PathVariable("buyerId") String buyerId, Model model) {
        logger.debug(" 买家买家池已购产品目录初始化");
        model.addAttribute("buyerId", buyerId);
        return "buyers/BY121309";
    }

    /**
     *买家买家池已购产品查询
     * @param buyerId
     * @param param
     * @return
     */
    @RequestMapping(value = "search/{buyerId}", method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<BY121309Bean> search(@PathVariable("buyerId") String buyerId, BY121309Param param) {
        RsRequest<BY121309Param> request = new RsRequest<>();
        PageResult<BY121309Bean> result = new PageResult<>();
        String byId = StringUtil.toString(buyerId);
        param.setBuyerId(byId);
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
//      String url = "http://localhost:8084/msk-buyers-report-api/api/br/findOrderInfoProductCatalog";
        String url = SystemServerManager.BuyersReportServerManager.getfindOrderInfoProductCatalog();
        RsResponse<PageResult<BY121309Bean>> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<PageResult<BY121309Bean>>>() {});
        return response.getResult();
    }
}
