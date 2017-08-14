package com.msk.order.rest;

import com.msk.common.annotation.valid.CustomValidation;
import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.common.base.BaseRestController;
import com.msk.common.constant.SystemConstant;
import com.msk.order.bean.param.ISO151802RestParam;
import com.msk.order.bean.result.ISO151802RestResult;
import com.msk.order.service.ISO151802Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ISO151802_现场退货数据接收接口
 * Created by chu_jian on 2016/8/3.
 */
@RestController
public class ISO151802RestController extends BaseRestController {
    private static Logger logger = LoggerFactory.getLogger(ISO151401RestController.class);
    @Autowired
    private ISO151802Service iso151802Service;

    @RequestMapping(value = "/so/sdo/receive/reject", method = RequestMethod.POST, produces = {MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_XML_VALUE})
    @CustomValidation(className = "com.msk.order.validator.ISO151802RestValidator")
    public RestResponse<ISO151802RestResult> doReceiverReject(@RequestBody RestRequest<ISO151802RestParam> request) {
        RestResponse<ISO151802RestResult> response = new RestResponse<ISO151802RestResult>();
        logger.debug("现场退货数据接收");
        ISO151802RestParam param= request.getParam();
        param.setCrtId(request.getLoginId());
        try {
            // 现场退货
            ISO151802RestResult result = iso151802Service.doReceiverReject(param);
            response.setStatus(SystemConstant.RsStatus.SUCCESS);
            response.setMessage("现场退货成功!");
            response.setResult(result);
        }catch (Exception e){
            response.setStatus(SystemConstant.RsStatus.FAIL);
            response.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

}