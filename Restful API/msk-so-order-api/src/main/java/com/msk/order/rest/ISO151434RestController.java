package com.msk.order.rest;

import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.common.base.BaseRestController;
import com.msk.common.constant.SystemConstant;
import com.msk.order.bean.param.ISO151434RestParam;
import com.msk.order.bean.result.ISO151434RestResult;
import com.msk.order.service.ISO151434Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ISO151434_获取上半月分销量接口
 * Created by chu_jian on 2016/8/3.
 */
@RestController
public class ISO151434RestController extends BaseRestController {
    private static Logger logger = LoggerFactory.getLogger(ISO151434RestController.class);
    @Autowired
    private ISO151434Service iso151434Service;

    /**
     * 获取上半月分销量
     *
     * @param request 请求参数
     * @return
     */
    @RequestMapping(value = "/so/order/halfmonthcount", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponse<ISO151434RestResult> getHalfMonthCount(@RequestBody RestRequest<ISO151434RestParam> request) {
        logger.debug("-----获取上半月分销量开始-----");
        RestResponse<ISO151434RestResult> response = new RestResponse<>();
        ISO151434RestResult result = iso151434Service.getHalfMonthCount(request.getParam());
        logger.debug("查询上半月份分销量结束");
        response.setStatus(SystemConstant.RsStatus.SUCCESS);
        response.setMessage("查询成功。");
        response.setResult(result);
        return response;
    }
}
