package com.msk.order.rest;

import com.msk.common.annotation.valid.CustomValidation;
import com.msk.common.base.BaseRestController;
import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.common.constant.SystemConstant;
import com.msk.common.utils.DateTimeUtil;
import com.msk.order.bean.param.ISO151801RestParam;
import com.msk.order.bean.result.ISO151801RestResult;
import com.msk.order.service.ISO151801Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ISO151801_迟收退货数据接收接口
 * Created by chu_jian on 2016/8/3.
 */
@RestController
public class ISO151801RestController extends BaseRestController {

    @Autowired
    private ISO151801Service iso151801Service;
    private static Logger logger = LoggerFactory.getLogger(ISO151401RestController.class);

    @RequestMapping(value = "/so/sdo/receive/later", method = RequestMethod.POST, produces = {MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_XML_VALUE})
    @CustomValidation(className = "com.msk.order.validator.ISO151801RestValidator")
    public RestResponse<ISO151801RestResult> doReceiverLater(@RequestBody RestRequest<ISO151801RestParam> request) {
        RestResponse<ISO151801RestResult> response = new RestResponse<ISO151801RestResult>();
        ISO151801RestParam param = request.getParam();
        param.setCrtId(request.getLoginId());
        param.setCrtTime(DateTimeUtil.getCustomerDate());
        logger.info("迟收退货数据接收接口");
        try {
            ISO151801RestResult result = iso151801Service.doReceiverLater(param);
            response.setStatus(SystemConstant.RsStatus.SUCCESS);
            response.setMessage("迟收退货数据成功。");
            response.setResult(result);
        } catch (Exception e) {
            response.setStatus(SystemConstant.RsStatus.FAIL);
            response.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

}