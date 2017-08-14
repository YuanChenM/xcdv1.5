package com.msk.inventory.rest;

import com.msk.comm.bean.RestRequest;
import com.msk.comm.bean.RestResponse;
import com.msk.comm.exception.BusinessException;
import com.msk.common.constant.SystemConstant;
import com.msk.inventory.bean.ISO152411ParamBean;
import com.msk.inventory.bean.ISO152411ResultBean;
import com.msk.inventory.service.IISO152411Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zheng_xu on 2016/8/22.
 */
@RestController
@RequestMapping("api")
public class ISO152411RestController {
    @Autowired
    private IISO152411Service iso152411Service;

    /**
     * 产品库存查询
     *
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/pd/pd_stock", method = RequestMethod.POST)
    public RestResponse<ISO152411ResultBean> findProductStock(@RequestBody RestRequest<ISO152411ParamBean> requestBody) {
        RestResponse<ISO152411ResultBean> response = new RestResponse<>();
        try{
            List<ISO152411ResultBean> resultlist = iso152411Service.getProdBySlType(requestBody.getParam());
            if (resultlist.size() != 0) {
                for (int i = 0; i < resultlist.size(); i++) {
                    ISO152411ResultBean resultBean = resultlist.get(i);
                    response.setResult(resultBean);
                }
            }
            response.setStatus(SystemConstant.RsStatus.SUCCESS);
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
