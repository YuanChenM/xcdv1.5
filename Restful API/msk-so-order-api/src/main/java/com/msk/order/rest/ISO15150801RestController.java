package com.msk.order.rest;

import com.msk.common.bean.RestRequest;
import com.msk.order.bean.result.SO15150801ProductStockBean;
import com.msk.order.service.impl.ISO15150801ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.msk.common.bean.RestResponse;
import com.msk.common.base.BaseRestController;
import com.msk.order.bean.param.ISO15150801RestParam;

/**
 * ISO15150801_选择产品画面后台接口
 * Created by wang_jianzhou on 2016/8/2.
 */
@RestController
public class ISO15150801RestController extends BaseRestController {

    private static Logger logger = LoggerFactory.getLogger(ISO15150801RestController.class);

    @Autowired
    private ISO15150801ServiceImpl iso15150801Service;
    /**
     * 批量查询
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/so/seller/product/_search", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponse<SO15150801ProductStockBean> findProductStockPage(@RequestBody RestRequest<ISO15150801RestParam> param) {
        logger.debug("查询卖家产品");
        ISO15150801RestParam iso15150801RestParam = param.getParam();
        iso15150801RestParam.setSlCode(iso15150801RestParam.getFilterMap().get("slCode").toString());
        iso15150801RestParam.setSalePlatform(iso15150801RestParam.getFilterMap().get("salePlatform").toString());
        iso15150801RestParam.setLgcsCode(iso15150801RestParam.getFilterMap().get("lgcsCode").toString());
        if(null != iso15150801RestParam.getFilterMap().get("pdCode")){
            iso15150801RestParam.setPdCode(iso15150801RestParam.getFilterMap().get("pdCode").toString().trim());
        }
        if(null != iso15150801RestParam.getFilterMap().get("pdName")){
            iso15150801RestParam.setPdName(iso15150801RestParam.getFilterMap().get("pdName").toString().trim());
        }
        if(null != iso15150801RestParam.getFilterMap().get("unit")){
            iso15150801RestParam.setUnit(iso15150801RestParam.getFilterMap().get("unit").toString().trim());
        }
        SO15150801ProductStockBean result = this.iso15150801Service.findSellerStockPage(iso15150801RestParam);
        RestResponse<SO15150801ProductStockBean> rsResponse = new RestResponse<>();
        logger.debug("查询卖家产品结束");
        rsResponse.setResult(result);
        return rsResponse;
    }


    /**
     * 批量查询
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/so/supplier/product/_search", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponse<SO15150801ProductStockBean> findSscBidBasicInfoList(@RequestBody RestRequest<ISO15150801RestParam> param) {
        logger.debug("查询供应商产品");
        ISO15150801RestParam iso15150801RestParam = param.getParam();
        iso15150801RestParam.setSlCode(iso15150801RestParam.getFilterMap().get("slCode").toString());
        iso15150801RestParam.setSalePlatform(iso15150801RestParam.getFilterMap().get("salePlatform").toString());
        iso15150801RestParam.setLgcsCode(iso15150801RestParam.getFilterMap().get("lgcsCode").toString());
        if(null != iso15150801RestParam.getFilterMap().get("pdCode")){
            iso15150801RestParam.setPdCode(iso15150801RestParam.getFilterMap().get("pdCode").toString());
        }
        if(null != iso15150801RestParam.getFilterMap().get("pdName")){
            iso15150801RestParam.setPdName(iso15150801RestParam.getFilterMap().get("pdName").toString());
        }
        SO15150801ProductStockBean result = this.iso15150801Service.findSuppStockPage(iso15150801RestParam);
        RestResponse<SO15150801ProductStockBean> rsResponse = new RestResponse<SO15150801ProductStockBean>();
        logger.debug("查询供应商产品结束");
        rsResponse.setResult(result);
        return rsResponse;
    }
}
