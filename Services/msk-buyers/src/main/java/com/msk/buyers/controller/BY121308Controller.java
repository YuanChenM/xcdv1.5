package com.msk.buyers.controller;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.br.bean.BY121308RsPageResult;
import com.msk.br.bean.IBY121308RsBean;
import com.msk.br.bean.IBY121308RsParam;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.consts.StatusConst;
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
 * 批发市场列表
 *
 * @author yuan_zhifei
 */
@Controller
@RequestMapping("BY121308")
public class BY121308Controller extends BaseController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121308Controller.class);

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init/{buyerId}",
            method = RequestMethod.POST)
    public String init(@PathVariable("buyerId") String buyerId, Model model) {
        logger.debug("买家订单汇总初始化");
        model.addAttribute("buyerId", buyerId);
        return "buyers/BY121308";
    }

    /**
     * 分页查询数据
     *
     * @param iby121308RsParam IBY121308RsParam
     * @return 分页查询数据
     */
    @RequestMapping(value = "search/{buyerId}",
            method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<IBY121308RsBean> search(@PathVariable("buyerId") String buyerId, IBY121308RsParam iby121308RsParam) {
        logger.info("-----------------订单汇总信息-------------------");
        PageResult<IBY121308RsBean> result = new PageResult<>();
        RsRequest<IBY121308RsParam> request = new RsRequest<>();
        String byerId = StringUtil.toString(buyerId);
        //订单详细全部收货状态7
        String detailStatus = StringUtil.toString(StatusConst.OrderDetailStatusDef.ALL_RECEIPT);
        iby121308RsParam.setBuyerId(byerId);
        iby121308RsParam.setFilter("detailStatus", detailStatus);
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(iby121308RsParam);
        //String url = "http://localhost:8280/api/br/findOrderInfoList";
        String url = SystemServerManager.BuyersReportServerManager.getfindOrderInfoList();
        RsResponse<BY121308RsPageResult> list = RestClientUtil.post(url, request, new TypeReference<RsResponse<BY121308RsPageResult>>() {
        });
        result.setData(list.getResult().getIby121308RsBeanList());
        result.setRecordsTotal(list.getResult().getTotalCount());
        return result;
    }
}
