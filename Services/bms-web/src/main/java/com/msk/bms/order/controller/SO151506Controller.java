package com.msk.bms.order.controller;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BasePageParam;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.bms.order.controller.common.SoRestUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.order.entity.SoReturn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by wang_shuai on 2016/8/3.
 */
@Controller
@RequestMapping("SO151506")
public class SO151506Controller extends BaseController {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(SO151506Controller.class);

    /**
     * 页面初始化
     * @return
     */
    @RequestMapping(value="init",method = RequestMethod.POST)
    public String init(){
        logger.info("退货单列表页面初始化");
        return "order/SO151506";
    }


    /**
     * 退货单列表数据查询
     * @param basePageParam
     * @return
     */
    @RequestMapping(value="search",method = RequestMethod.POST)
    @ResponseBody
    public PageResult<SoReturn> search(BasePageParam basePageParam){
        logger.info("查询退货单列表页面数据");
        RsRequest<BasePageParam> request = new RsRequest<BasePageParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(basePageParam);

        String url =  SystemServerManager.SoOrderApiServerManager.getFindPageReturnOrderList();
        RsResponse<PageResult<SoReturn>> rsResponse= RestClientUtil.post(url, request, new TypeReference<RsResponse<PageResult<SoReturn>>>() {
        });
        return rsResponse.getResult();

    }
}
