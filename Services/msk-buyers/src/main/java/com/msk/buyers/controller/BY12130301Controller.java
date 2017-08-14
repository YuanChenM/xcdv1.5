package com.msk.buyers.controller;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BasePageParam;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.br.bean.IBR121411RsParam;
import com.msk.br.bean.IBR121411RsResult;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.BrBuyerPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 查看所属买家池信息列表
 */
@Controller
@RequestMapping("BY12130301")
public class BY12130301Controller extends BaseController{

    private static Logger logger = LoggerFactory.getLogger(BY12130301Controller.class);

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init/{buyerId}",method = RequestMethod.POST)
    public String init(@PathVariable("buyerId") String buyerId,Model model) {
        logger.debug("查看所属买家池信息列表");
        model.addAttribute("buyerId", buyerId);
        return "buyers/BY12130301";
    }

    /**
     * 分页查询数据
     *
     * @return 分页查询数据
     */
    @RequestMapping(value = "search/{buyerId}", method = RequestMethod.POST)
    public @ResponseBody
    PageResult<BrBuyerPool> search(@PathVariable("buyerId") String buyerId,BasePageParam param) {

        IBR121411RsParam searchParam = new IBR121411RsParam();
        searchParam.setBuyerId(buyerId);
        searchParam.setPageCount(param.getPageSize());
        searchParam.setPageNo(param.getStartPos() / param.getPageSize() + 1);

        RsRequest<IBR121411RsParam> request = new RsRequest<>();
        request.setAuth("MSK00001");
        request.setLoginId("001");
        request.setSiteCode("001");
        request.setParam(searchParam);

        String url = SystemServerManager.BuyersReportServerManager.getQueryBrBuyerPoolByBuyerId();
        RsResponse<IBR121411RsResult> pageResultList = RestClientUtil.post(url, request, new TypeReference<RsResponse<IBR121411RsResult>>() {
        });

        PageResult<BrBuyerPool> result = new PageResult<>();
        result.setData(pageResultList.getResult().getBrBuyerPoolList());
        result.setRecordsTotal(pageResultList.getResult().getTotalCount());

        return result;
    }
}
