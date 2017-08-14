package com.msk.inventory.rest;

import com.msk.comm.bean.RestRequest;
import com.msk.comm.bean.RestResponse;
import com.msk.comm.exception.BusinessException;
import com.msk.common.constant.SystemConstant;
import com.msk.common.utils.DateTimeUtil;
import com.msk.inventory.bean.ISO152406ParamBean;
import com.msk.inventory.service.IISO152406Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zheng_xu on 2016/8/25.
 */
@RestController
@RequestMapping("api")
public class ISO152406RestController {

    @Autowired
    private IISO152406Service iso152406Service;

    @RequestMapping(value = "/so/slInv/orderOccupy", method = RequestMethod.POST)
    public RestResponse allocateSlInventory(@RequestBody RestRequest<ISO152406ParamBean> requestBody) {
        RestResponse response = new RestResponse();
        try {
            iso152406Service.getOrderIdOfOccupy(requestBody.getParam(), requestBody.getLoginId(), DateTimeUtil.getCustomerDate());
            response.setStatus(SystemConstant.RsStatus.SUCCESS);
            response.setMessage("更改成功");
        }catch(BusinessException be){
            response.setStatus(SystemConstant.RsStatus.FAIL);
            response.setMessage(be.getMessage());
            response.setReturnCode("F000001");
        }catch(Exception e){
            e.printStackTrace();
            response.setStatus(SystemConstant.RsStatus.FAIL);
            response.setMessage("处理中发生了未知错误，请联系管理员！");
            response.setReturnCode("F000001");
        }
        return response;
    }
}
