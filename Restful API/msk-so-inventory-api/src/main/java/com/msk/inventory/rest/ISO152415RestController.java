package com.msk.inventory.rest;

import com.msk.comm.bean.RestRequest;
import com.msk.comm.bean.RestResponse;
import com.msk.comm.exception.BusinessException;
import com.msk.common.constant.SystemConstant;
import com.msk.common.utils.DateTimeUtil;
import com.msk.inventory.bean.ISO152415ParamBean;
import com.msk.inventory.service.IISO152415Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by duan_kai on 2016/9/5.
 */
@RestController
@RequestMapping("api")
public class ISO152415RestController {

    @Autowired
    private IISO152415Service iiso152415Service;

    @RequestMapping(value = "/sl/assignInventory", method = RequestMethod.POST)
    public RestResponse<ISO152415ParamBean> assignInventoryForSl(@RequestBody RestRequest<ISO152415ParamBean> requestBody) {
        RestResponse<ISO152415ParamBean> response = new RestResponse<>();
        try {
            int listSize = requestBody.getParam().getInvList().size();
            if (listSize > 0) {
                String loginId = requestBody.getLoginId();
                iiso152415Service.updateBuyerStockpile(requestBody.getParam(),requestBody.getLoginId(), DateTimeUtil.getCustomerDate());
                response.setStatus(SystemConstant.RsStatus.SUCCESS);
                response.setMessage("处理成功！");
            } else {
                response.setStatus(SystemConstant.RsStatus.FAIL);
                response.setMessage("无效的入库明细！");
                response.setReturnCode("F000001");
            }
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
