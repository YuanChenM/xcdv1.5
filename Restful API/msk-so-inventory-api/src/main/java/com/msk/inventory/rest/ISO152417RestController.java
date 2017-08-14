package com.msk.inventory.rest;

import com.msk.comm.exception.BusinessException;
import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.common.constant.SystemConstant;
import com.msk.inventory.bean.ISO152417ParamBean;
import com.msk.inventory.bean.ISO152417ResultBean;
import com.msk.inventory.bean.ISO152417SupplierBean;
import com.msk.inventory.service.IISO152417Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zheng_xu on 2016/9/12.
 */
@RestController
@RequestMapping("api")
public class ISO152417RestController {

    @Autowired
    private IISO152417Service iso152417Service;

    @RequestMapping(value = "/inv/ownersInHistory",
        method = RequestMethod.POST)
    public RestResponse<ISO152417ResultBean> getOwnersInHistory(
        @RequestBody RestRequest<ISO152417ParamBean> requestBody) {
        RestResponse<ISO152417ResultBean> response = new RestResponse<ISO152417ResultBean>();
        try {
            ISO152417ResultBean iso152417ResultBean = new ISO152417ResultBean();
            // 根据条件查询有过库存的供应商列表
            List<ISO152417SupplierBean> supplierBeanList = iso152417Service.getOwnersInHistory(requestBody.getParam());
            iso152417ResultBean.setSellers(supplierBeanList);
            response.setStatus(SystemConstant.RsStatus.SUCCESS);
            response.setResult(iso152417ResultBean);
            response.setMessage("查询成功！");
        } catch (BusinessException be) {
            response.setStatus(SystemConstant.RsStatus.FAIL);
            response.setMessage(be.getMessage());
            response.setReturnCode("F000001");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(SystemConstant.RsStatus.FAIL);
            response.setMessage("处理中发生了未知错误，请联系管理员！");
            response.setReturnCode("F000001");
        }
        return response;
    }
}
