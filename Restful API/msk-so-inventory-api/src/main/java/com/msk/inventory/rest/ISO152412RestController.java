package com.msk.inventory.rest;

import com.msk.comm.bean.RestRequest;
import com.msk.comm.bean.RestResponse;
import com.msk.comm.exception.BusinessException;
import com.msk.common.constant.SystemConstant;
import com.msk.inventory.bean.ISO152412ParamBean;
import com.msk.inventory.bean.ISO152412PdStockResultBean;
import com.msk.inventory.bean.ISO152412ResultBean;
import com.msk.inventory.service.IISO152412Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by duan_kai on 2016/8/25.
 */
@RestController
@RequestMapping("api")
public class ISO152412RestController {

    @Autowired
    private IISO152412Service iso152412Service;

    @RequestMapping(value = "/inv/slProductInv/list",
            method = RequestMethod.POST)
    public RestResponse<ISO152412ResultBean> findSlProductIvList(
            @RequestBody RestRequest<ISO152412ParamBean> requestBody) {

        RestResponse<ISO152412ResultBean> response = new RestResponse<ISO152412ResultBean>();
        try{
            ISO152412ResultBean iso152412ResultBean = new ISO152412ResultBean();
            // 查询根据条件查询产品库存集合
            List<ISO152412PdStockResultBean> iso151412PdStockResultBeanList = iso152412Service.findSlProductIvList(requestBody.getParam());
            iso152412ResultBean.setPdStockList(iso151412PdStockResultBeanList);
            response.setStatus(SystemConstant.RsStatus.SUCCESS);
            response.setResult(iso152412ResultBean);
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
