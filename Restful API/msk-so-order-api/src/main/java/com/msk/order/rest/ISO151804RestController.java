package com.msk.order.rest;


import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.common.base.BaseRestController;
import com.msk.common.constant.SystemConstant;
import com.msk.order.bean.param.ISO151804RestParam;
import com.msk.order.bean.result.ISO151804RestReasonResult;
import com.msk.order.bean.result.ISO151804RestResult;
import com.msk.order.service.ISO151804Service;
import io.swagger.annotations.Api;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ISO151804_退货原因查询接口
 * Created by sun_jiaju on 2016/8/17.
 */
@RestController
@Api(value = "Api", description = "退货原因查询Rest接口", tags = {"ISO151804RestController"})
public class ISO151804RestController extends BaseRestController {
    @Autowired
    private ISO151804Service iso151804Service;

    /**
     * 退货原因查询接口
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/so/sro/returnReason/search", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponse<ISO151804RestResult> searchReturnReason(@RequestBody RestRequest<ISO151804RestParam> request) {
        ISO151804RestParam param = request.getParam();
        List<ISO151804RestReasonResult> reasonResults = iso151804Service.searchReturnReason(param);
        RestResponse<ISO151804RestResult> response = new RestResponse<ISO151804RestResult>();
        ISO151804RestResult result = new ISO151804RestResult();
        if (CollectionUtils.isNotEmpty(reasonResults)) {
            result.setReasonList(reasonResults);
            response.setResult(result);
            response.setMessage("退货原因查询成功！");
            response.setStatus(SystemConstant.RsStatus.SUCCESS);
        } else {
            response.setStatus(SystemConstant.RsStatus.FAIL);
            response.setMessage("退货原因查询失败！");
        }
        return response;
    }

    /**
     * 退货原因查询接口
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/so/sro/returnReason/search", method = RequestMethod.POST, produces = {MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_XML_VALUE})
    public RestResponse<ISO151804RestResult> searchReturnReasonXml(@RequestBody RestRequest<ISO151804RestParam> request) {
        ISO151804RestParam param = request.getParam();
        List<ISO151804RestReasonResult> reasonResults = iso151804Service.searchReturnReason(param);
        RestResponse<ISO151804RestResult> response = new RestResponse<ISO151804RestResult>();
        ISO151804RestResult result = new ISO151804RestResult();
        if (CollectionUtils.isNotEmpty(reasonResults)) {
            result.setReasonList(reasonResults);
            response.setResult(result);
            response.setMessage("退货原因查询成功！");
            response.setStatus(SystemConstant.RsStatus.SUCCESS);
        } else {
            response.setStatus(SystemConstant.RsStatus.FAIL);
            response.setMessage("退货原因查询失败！");
        }
        return response;
    }
}
