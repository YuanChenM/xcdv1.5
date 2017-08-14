package com.msk.order.rest;

import com.msk.common.annotation.valid.CustomValidation;
import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.common.base.BaseRestController;
import com.msk.common.constant.SystemConstant;
import com.msk.order.bean.param.ISO151426RestParam;
import com.msk.order.bean.result.ISO151426RestResult;
import com.msk.order.service.ISO151426Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


/**
 * ISO151426_管家订单查询接口
 * Created by wang_shuai on 2016/8/22.
 */
@RestController
public class ISO151426RestController extends BaseRestController {
    @Autowired
    private ISO151426Service iso151426Service;

    @RequestMapping(value = "/so/sdo/housekeeping/detail", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @CustomValidation(className = "com.msk.order.validator.ISO151426RestValidator")
    public RestResponse<ISO151426RestResult> detail(@RequestBody RestRequest<ISO151426RestParam> request)
    {
        RestResponse<ISO151426RestResult> response = new RestResponse<ISO151426RestResult>();
        ISO151426RestParam param = request.getParam();
        ISO151426RestResult result = this.iso151426Service.findPageResult(param);
        response.setStatus(SystemConstant.RsStatus.SUCCESS);
        response.setMessage("处理成功");
        response.setResult(result);

        return response;
    }
}
