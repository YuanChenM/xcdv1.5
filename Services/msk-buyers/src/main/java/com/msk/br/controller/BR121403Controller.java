package com.msk.br.controller;


import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BasePageParam;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.br.bean.BR121403RsPageResult;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.BrOBuyerInfo;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * 买家标准产品池一览Controller
 *
 * @author zhao_chen1
 */
@Controller
@RequestMapping("BR121403")
public class BR121403Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = getLogger(BR121403Controller.class);

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(Model model) {
        logger.debug("单一买家池列表初始化");
        return "br/BR121403";
    }

    /**
     * 单一买家池列表
     *
     * @param param
     * @return 分页查询数据
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<BrOBuyerInfo> search(BasePageParam param) {
        logger.info("单一买家池列表查询开始");
        PageResult<BrOBuyerInfo> result = new PageResult<>();
        RsRequest<BasePageParam> request = new RsRequest<BasePageParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
//        String url = "http://localhost:8081/api/br/findBrOBuyerInfoList";
        String url = SystemServerManager.BuyersReportServerManager.getFindBrOBuyerInfoList();
        RsResponse<BR121403RsPageResult> list = RestClientUtil.post(url, request, new TypeReference<RsResponse<BR121403RsPageResult>>() {
        });
        result.setData(list.getResult().getBrOBuyerInfoList());
        result.setRecordsTotal(list.getResult().getTotalCount());
        return result;
    }
}
