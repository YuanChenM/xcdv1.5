package com.msk.order.rest;

import com.msk.common.annotation.valid.CustomValidation;
import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.common.base.BaseRestController;
import com.msk.common.constant.SystemConstant;
import com.msk.order.bean.param.ISO151422RestParam;
import com.msk.order.bean.result.ISO151422RestResult;
import com.msk.order.service.ISO151422Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ISO151422_退货入库
 * Created by wang_jianzhou on 2016/8/17.
 */
@RestController
public class ISO151422RestController extends BaseRestController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISO151422RestController.class);

    @Autowired
    private ISO151422Service iso151422Service;

    /**
     *退货入库
     * @param request
     * @return
     */
    @RequestMapping(value = "/so/ro/inbound", method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_XML_VALUE },consumes = { MediaType.APPLICATION_XML_VALUE })
    @CustomValidation(className = "com.msk.order.validator.ISO151422RestValidator")
    public RestResponse<ISO151422RestResult> inbound(@RequestBody RestRequest<ISO151422RestParam> request){
        logger.info("退货入库");
        RestResponse<ISO151422RestResult> response = new RestResponse<ISO151422RestResult>();
        String message="操作成功！";
        try {
            ISO151422RestParam param = request.getParam();
            param.setUpdId(request.getLoginId());
            ISO151422RestResult result = iso151422Service.doInbound(param);
            if (result != null) {
                response.setStatus(SystemConstant.RsStatus.SUCCESS);
                response.setResult(result);
            } else {
                response.setStatus(SystemConstant.RsStatus.FAIL);
            }
        }catch (Exception e){
            message="操作失败！原因："+e.getMessage();
            response.setStatus(SystemConstant.RsStatus.FAIL);
        }
        response.setMessage(message);
        logger.info("退货入库结束");
        return response;
    }
}
