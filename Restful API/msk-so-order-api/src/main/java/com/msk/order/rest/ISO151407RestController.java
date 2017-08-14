package com.msk.order.rest;


import com.msk.common.annotation.valid.Validation;
import com.msk.common.base.BaseRestController;
import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.common.constant.SystemConstant;
import com.msk.order.bean.param.ISO151407RestParam;
import com.msk.order.bean.result.ISO151407RestOrderResult;
import com.msk.order.bean.result.ISO151407RestResult;
import com.msk.order.service.ISO151407Service;
import io.swagger.annotations.Api;
import org.apache.commons.collections.CollectionUtils;
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
 * ISO151407_买家平台下单数量统计
 * Created by sun_jiaju on 2016/8/24.
 */
@RestController
@Api(value = "Api", description = "买家平台下单数量统计Rest接口", tags = {"ISO151407RestController"})
public class ISO151407RestController extends BaseRestController {
    @Autowired
    private ISO151407Service iso151407Service;

    private static Logger logger = LoggerFactory.getLogger(ISO151407RestController.class);

    /**
     * 买家平台下单数量统计
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/so/orderSource/search", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validation
    public RestResponse<ISO151407RestResult> searchOrderSourceCount(@RequestBody RestRequest<ISO151407RestParam> request) {
        RestResponse<ISO151407RestResult> rs = new RestResponse<ISO151407RestResult>();
        ISO151407RestResult result = new ISO151407RestResult();
        logger.info("买家平台下单数量统计开始");
        List<ISO151407RestOrderResult> orderCountInfoList = this.iso151407Service.searchOrderSourceCount(request.getParam());
        if (CollectionUtils.isNotEmpty(orderCountInfoList)) {
            result.setOrderCountInfoList(orderCountInfoList);
            rs.setResult(result);
            rs.setStatus(SystemConstant.RsStatus.SUCCESS);
            rs.setMessage("查询买家各下单方式订单数量成功");
        } else {
            rs.setStatus(SystemConstant.RsStatus.FAIL);
            rs.setMessage("该买家查询不到下单数量");
        }
        logger.info("买家平台下单数量统计结束");
        return rs;
    }
}
