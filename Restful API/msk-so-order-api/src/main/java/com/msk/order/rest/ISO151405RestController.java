package com.msk.order.rest;

import com.msk.common.annotation.valid.CustomValidation;
import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.common.base.BaseRestController;
import com.msk.common.constant.SystemConstant;
import com.msk.order.bean.param.ISO151405RestParam;
import com.msk.order.bean.result.ISO151405PdListRestResult;
import com.msk.order.bean.result.ISO151405RestResult;
import com.msk.order.service.ISO151405Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ISO151405_产品销量查询接口
 * Created by chu_jian on 2016/8/3.
 */
@RestController
public class ISO151405RestController extends BaseRestController {

    private static Logger logger = LoggerFactory.getLogger(ISO151401RestController.class);

    @Autowired
    private ISO151405Service iso151405Service;

    /**
     * 产品销量查询
     *
     * @param param param
     * @return 返回结果
     */
    @RequestMapping(value = "/so/pdSalesVolumn/list", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @CustomValidation(className = "com.msk.order.validator.ISO151405RestValidator")
    public RestResponse<ISO151405RestResult> getVolumn(@RequestBody RestRequest<ISO151405RestParam> param) {
        RestResponse<ISO151405RestResult> response = new RestResponse<ISO151405RestResult>();
        logger.debug("-----产品销量查询接口开始-----");
        // 查询详情
        ISO151405RestParam iso251405RsParam = param.getParam();
        ISO151405RestResult result = new ISO151405RestResult();
        List<ISO151405PdListRestResult> pdList = iso151405Service.getPdSalesVolumn(iso251405RsParam);
        if(!CollectionUtils.isEmpty(pdList)){
            result.setPdList(pdList);
        }
        response.setStatus(SystemConstant.RsStatus.SUCCESS);
        response.setMessage("产品销量查询接口成功。");
        response.setResult(result);
        return response;
    }

}