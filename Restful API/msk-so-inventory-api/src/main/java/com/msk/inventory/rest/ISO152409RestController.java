package com.msk.inventory.rest;

import com.msk.comm.exception.BusinessException;
import com.msk.common.constant.SystemConstant;
import com.msk.common.utils.DateTimeUtil;
import com.msk.inventory.bean.ISO152409ParamBean;
import com.msk.inventory.service.IISO152409Service;
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
public class ISO152409RestController {

    @Autowired
    private IISO152409Service iiso152409Service;

    @Autowired
    private IOutboundService outboundIvDetailService;

    @RequestMapping(value = "/inv/spOrderOccupy/decrease",
        method = RequestMethod.POST)
    public RestResponse<ISO152409ParamBean> undoAllocateOwnerInventory(@RequestBody RestRequest<ISO152409ParamBean> requestBody) {
        RestResponse<ISO152409ParamBean> response = new RestResponse<>();
        try{
            iiso152409Service.occupyOrder(requestBody.getParam(), requestBody.getLoginId(), DateTimeUtil.getCustomerDate());
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
