package com.msk.inventory.rest;

import java.util.List;

import com.msk.inventory.bean.*;
import com.msk.inventory.service.IISO152418Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.msk.comm.exception.BusinessException;
import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.common.constant.SystemConstant;
import com.msk.inventory.service.IISO152417Service;

/**
 * Created by wang_fan2 on 2016/9/18.
 */
@RestController
@RequestMapping("api")
public class ISO152418RestController {

    @Autowired
    private IISO152418Service iso152418Service;

    @RequestMapping(value = "/inv/inventory/undoDispatch",
        method = RequestMethod.POST)
    public RestResponse<Object> undoDispatch(
        @RequestBody RestRequest<ISO152418ParamBean> requestBody) {
        RestResponse<Object> response = new RestResponse<Object>();
        try {
            List<ISO152418InvParamBean> iso152418InvParamList= requestBody.getParam().getInvList();

            for (int i = 0; i < iso152418InvParamList.size(); i++) {
                iso152418Service.undoDispatch(iso152418InvParamList.get(i));
            }
                
            response.setStatus(SystemConstant.RsStatus.SUCCESS);
            response.setResult("");
            response.setMessage("发货取消成功！");
        } catch (BusinessException be) {
            response.setStatus(SystemConstant.RsStatus.FAIL);
            response.setMessage(be.getMessage());
            response.setReturnCode("F000001");
        } catch (Exception e) {
            response.setStatus(SystemConstant.RsStatus.FAIL);
            response.setMessage("处理中发生了未知错误，请联系管理员！");
            response.setReturnCode("F000001");
        }
        return response;
    }
}
