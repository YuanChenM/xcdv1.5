package com.msk.inventory.rest;

import com.msk.comm.exception.BusinessException;
import com.msk.common.constant.SystemConstant;
import com.msk.common.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.msk.comm.bean.RestRequest;
import com.msk.comm.bean.RestResponse;
import com.msk.inventory.bean.ISO152407ParamBean;
import com.msk.inventory.service.IISO152407Service;
import com.msk.inventory.service.IOutboundService;

/**
 * Created by wangfan on 16/8/25.
 */
@RestController
@RequestMapping("api")
public class ISO152407RestController {

    @Autowired
    private IISO152407Service iiso152407Service;

    @Autowired
    private IOutboundService outboundIvDetailService;

    @RequestMapping(value = "/so/spInv/orderOccupy",
        method = RequestMethod.POST)
    public RestResponse<ISO152407ParamBean> allocateOwnerInventory(@RequestBody RestRequest<ISO152407ParamBean> requestBody) {
        RestResponse<ISO152407ParamBean> response = new RestResponse<>();
        try{
            iiso152407Service.occupyOrder(requestBody.getParam(), requestBody.getLoginId(), DateTimeUtil.getCustomerDate());
            response.setStatus(SystemConstant.RsStatus.SUCCESS);
            response.setMessage("处理成功");
            response.setReturnCode("");
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
