package com.msk.inventory.rest;

import java.util.List;

import com.msk.comm.exception.BusinessException;
import com.msk.common.constant.SystemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.msk.comm.bean.RestRequest;
import com.msk.comm.bean.RestResponse;
import com.msk.inventory.bean.ISO152413PdStockResultBean;
import com.msk.inventory.bean.ISO152413ParamBean;
import com.msk.inventory.bean.ISO152413ResultBean;
import com.msk.inventory.service.IISO152413Service;

/**
 * Created by wangfan on 16/8/23.
 */
@RestController
@RequestMapping("api")
public class ISO152413RestController {

    @Autowired
    private IISO152413Service iso152413Service;

    @RequestMapping(value = "/inv/spProductInv/list",
        method = RequestMethod.POST)
    public RestResponse<ISO152413ResultBean> findSpProductIvList(
        @RequestBody RestRequest<ISO152413ParamBean> requestBody) {
        RestResponse<ISO152413ResultBean> response = new RestResponse<ISO152413ResultBean>();
        try{
            ISO152413ResultBean iso152413ResultBean = new ISO152413ResultBean();
            // 查询根据条件查询产品库存集合
            List<ISO152413PdStockResultBean> iso151413PdStockResultBeanList = iso152413Service
                .findSlProductIvList(requestBody.getParam());
        iso152413ResultBean.setPdStockList(iso151413PdStockResultBeanList);
            response.setStatus(SystemConstant.RsStatus.SUCCESS);
            response.setResult(iso152413ResultBean);
            response.setMessage("查询成功！");
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
