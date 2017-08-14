package com.msk.inventory.rest;

import com.msk.comm.bean.RestRequest;
import com.msk.comm.bean.RestResponse;
import com.msk.comm.exception.BusinessException;
import com.msk.common.constant.SystemConstant;
import com.msk.common.utils.DateTimeUtil;
import com.msk.inventory.bean.ISO152408ParamBean;
import com.msk.inventory.service.IISO152408Service;
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
public class ISO152408RestController {
    @Autowired
    private IISO152408Service iso152408Service;

    @RequestMapping(value = "/inv/slOrderOccupy/decrease", method = RequestMethod.POST)
    public RestResponse undoAllocateSlInventory(@RequestBody RestRequest<ISO152408ParamBean> requestBody) {
        RestResponse response = new RestResponse();
        try{
            iso152408Service.updateDecreaseQty(requestBody.getParam(), requestBody.getLoginId(), DateTimeUtil.getCustomerDate());
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
