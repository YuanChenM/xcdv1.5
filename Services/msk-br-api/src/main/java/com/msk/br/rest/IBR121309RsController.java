package com.msk.br.rest;

import com.hoperun.plug.spring.annotation.Validator;
import com.msk.br.bean.IBR121309Bean;
import com.msk.br.bean.IBR121309RsParam;
import com.msk.br.bean.IBR121309RsResult;
import com.msk.br.logic.IBR121309Logic;
import com.msk.br.utils.RestCommUtil;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by guan_zhongheng on 2016/8/23.
 */
@RestController
public class IBR121309RsController extends BaseRsController {

    @Autowired
    private IBR121309Logic ibr121309Logic;

    private static Logger logger = LoggerFactory.getLogger(IBR121309RsController.class);

    /**
     * 获取冻品管家销售期
     * @param request
     * @return
     */
    @RequestMapping(value = "/br/getSaleInfoByBuyerId", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.br.validator.IBR121309Validator")
    public RsResponse<IBR121309RsResult> getSaleInfoByBuyerId(@RequestBody RsRequest<IBR121309RsParam> request){
        // 先通过买手获取冻品管家信息
        RsResponse<IBR121309RsResult> result = RestCommUtil.getSaleMarketControlInfo(request.getParam());
        // 本地获取分类买家池名称
        List<IBR121309Bean> slHouseSaleList = result.getResult().getSlHouseSaleList();
        ibr121309Logic.getBuyerPoolName(slHouseSaleList,request.getParam().getBuyerId());
        result.getResult().setSlHouseSaleList(slHouseSaleList);
        return result;
    }
}
