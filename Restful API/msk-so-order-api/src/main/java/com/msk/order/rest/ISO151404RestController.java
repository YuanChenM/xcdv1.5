package com.msk.order.rest;

import com.msk.common.annotation.valid.Validation;
import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.common.base.BaseRestController;
import com.msk.common.constant.SystemConstant;
import com.msk.order.bean.param.ISO151404RsParam;
import com.msk.order.bean.result.ISO151404RsResult;
import com.msk.order.service.ISO151404Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ISO151404_验证退货单号接口
 * Created by chu_jian on 2016/8/3.
 */
@RestController
public class ISO151404RestController extends BaseRestController {

    private static Logger logger = LoggerFactory.getLogger(ISO151404RestController.class);
    @Autowired
    private ISO151404Service iso151404Service;

    @RequestMapping(value = "/so/returnCode/check", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validation
    public RestResponse<ISO151404RsResult> checkReturnCode(@RequestBody RestRequest<ISO151404RsParam> param) {
        RestResponse<ISO151404RsResult> response = new RestResponse<ISO151404RsResult>();
        logger.debug("-----验证退货单号接口 开始-----");
        // 查询详情
        ISO151404RsResult result = iso151404Service.selectCount(param);
        response.setStatus(SystemConstant.RsStatus.SUCCESS);
        response.setMessage("验证退货单号成功。");
        response.setResult(result);
        return response;
    }

}