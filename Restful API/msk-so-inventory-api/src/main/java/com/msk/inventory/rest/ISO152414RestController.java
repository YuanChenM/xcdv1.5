package com.msk.inventory.rest;

import com.msk.comm.bean.RestRequest;
import com.msk.comm.bean.RestResponse;
import com.msk.comm.exception.BusinessException;
import com.msk.common.constant.SystemConstant;
import com.msk.inventory.bean.ISO152414ParamBean;
import com.msk.inventory.bean.ISO152414ProductResultBean;
import com.msk.inventory.bean.ISO152414ResultBean;
import com.msk.inventory.service.IISO152414Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by duan_kai on 2016/9/5.
 */
@RestController
@RequestMapping("api")
public class ISO152414RestController {

    @Autowired
    private IISO152414Service iso152414Service;

    @RequestMapping(value = "/sl/pd_stock", method = RequestMethod.POST)
    public RestResponse<ISO152414ResultBean> findSlProductList(
            @RequestBody RestRequest<ISO152414ParamBean> requestBody) {
        RestResponse<ISO152414ResultBean> response = new RestResponse<ISO152414ResultBean>();
        try{
            ISO152414ResultBean iso152413ResultBean = new ISO152414ResultBean();
            // 查询根据条件查询产品库存集合
            List<ISO152414ProductResultBean> iso151413PdStockResultBeanList = iso152414Service
                    .findSlProductIvList(requestBody.getParam());
            iso152413ResultBean.setProducts(iso151413PdStockResultBeanList);
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
