package com.msk.inventory.rest;

import com.msk.comm.bean.RestRequest;
import com.msk.comm.bean.RestResponse;
import com.msk.comm.exception.BusinessException;
import com.msk.common.constant.SystemConstant;
import com.msk.inventory.bean.ISO152403ParamBean;
import com.msk.inventory.bean.ISO152403ResultBean;
import com.msk.inventory.bean.ISO152403StockResultBean;
import com.msk.inventory.service.IISO152403Service;
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
public class ISO152403RestController {
    @Autowired
    private IISO152403Service iso152403RestService;

    /**
     * 根据pdTypeCode查询卖家库存
     * 
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/so/stockByPdTypeCode/list", method = RequestMethod.POST)
    public RestResponse<ISO152403ResultBean> getStockListByPdCode(@RequestBody RestRequest<ISO152403ParamBean> requestBody) {
        RestResponse<ISO152403ResultBean> response = new RestResponse<ISO152403ResultBean>();

        try {
            ISO152403ResultBean iso152403ResultBean = new ISO152403ResultBean();
            // 根据查询条件查询库存
            List<ISO152403StockResultBean> iso152403StockResultBeanList = iso152403RestService.getIso152413StockList(requestBody.getParam());
            iso152403ResultBean.setPdStockList(iso152403StockResultBeanList);
            response.setStatus(SystemConstant.RsStatus.SUCCESS);
            response.setResult(iso152403ResultBean);
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
