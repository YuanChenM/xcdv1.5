package com.msk.buyers.controller;

import com.alibaba.fastjson.TypeReference;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.ByBuyerBasicInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
@RequestMapping("BY121320")
public class BY121320Controller extends BaseController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121320Controller.class);

    /**
     * 会员卡管控
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init/{buyerId}", method = RequestMethod.POST)
    public String init(@PathVariable("buyerId") String buyerId, Model model) {
        logger.debug("会员卡管控");
        model.addAttribute("buyerId", buyerId);
        return "buyers/BY121320";
    }


    @RequestMapping(value = "save", method = RequestMethod.POST)
    public
    @ResponseBody
    String save(ByBuyerBasicInfo param, Model model) {
        logger.info("买家营销工具管控表编辑保存");
        String buyerId = param.getBuyerId();
        RsRequest<ByBuyerBasicInfo> request = new RsRequest<ByBuyerBasicInfo>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(param);
        /*String url = "http://localhost:8080/msk-by/api/by/saveTOOLToDataBase";*/
        String url = SystemServerManager.BuyersServerManage.getSaveTOOLToDataBase();
        RsResponse<Integer> basicBean = RestClientUtil.post(url, request, new TypeReference<RsResponse<Integer>>() {
        });
        int updateLine = basicBean.getResult();
        String returnFlg = "保存失败";
        if (updateLine > 0) {
            returnFlg = "保存成功";
        }
        /*this.init(buyerId, model)*/
        return returnFlg;
    }


}
