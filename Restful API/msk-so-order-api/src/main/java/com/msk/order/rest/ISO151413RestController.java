package com.msk.order.rest;


import com.msk.common.annotation.valid.Validation;
import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.common.base.BaseRestController;
import com.msk.common.constant.SystemConstant;
import com.msk.common.utils.DateTimeUtil;
import com.msk.order.bean.param.ISO151413RestParam;
import com.msk.order.bean.result.ISO151413RestResult;
import com.msk.order.service.ISO151413Service;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ISO151413_已付款接口
 * Created by sun_jiaju on 2016/8/9.
 */
@RestController
@Api(value = "Api", description = "已付款Rest接口", tags = {"ISO151413RestController"})
public class ISO151413RestController extends BaseRestController {
    @Autowired
    private ISO151413Service iso151413Service;

    /**
     * 订单已付款接口
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/so/sdo/payment", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validation
    public RestResponse<ISO151413RestResult> payOrder(@RequestBody RestRequest<ISO151413RestParam> request) {
        ISO151413RestParam param = request.getParam();
        param.setCrtId(request.getLoginId());
        param.setCrtTime(DateTimeUtil.getCustomerDate());
        RestResponse<ISO151413RestResult> response = new RestResponse<ISO151413RestResult>();
        try {
            ISO151413RestResult result = iso151413Service.payOrder(param);
            response.setMessage("订单付款成功");
            response.setStatus(SystemConstant.RsStatus.SUCCESS);
            response.setResult(result);
        }catch (Exception e){
            response.setStatus(SystemConstant.RsStatus.FAIL);
            response.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return response;
    }
}
