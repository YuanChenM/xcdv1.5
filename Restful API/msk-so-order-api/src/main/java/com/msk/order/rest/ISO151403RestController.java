package com.msk.order.rest;

import com.msk.common.annotation.valid.Validation;
import com.msk.common.base.BaseRestController;
import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.common.constant.SystemConstant;
import com.msk.order.bean.param.ISO151403RestParam;
import com.msk.order.bean.result.ISO151403RestResult;
import com.msk.order.bean.result.ISO151403SupplierRestResult;
import com.msk.order.service.ISO151403Service;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ISO151403_查询供应商列表接口
 * Created by chu_jian on 2016/8/3.
 */
@RestController
public class ISO151403RestController extends BaseRestController {
    private static Logger logger = LoggerFactory.getLogger(ISO151403RestController.class);
    @Autowired
    private ISO151403Service iso151403Service;

    /**
     * 查询供应商列表接口
     *
     * @param request 请求参数
     * @return
     */
    @RequestMapping(value = "/so/seller/search", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validation
    public synchronized RestResponse<ISO151403RestResult> findSellers(@RequestBody RestRequest<ISO151403RestParam> request) {
        RestResponse<ISO151403RestResult> response = new RestResponse<>();
        ISO151403RestResult rsResult = new ISO151403RestResult();
        logger.debug("查询供应商列表 开始");
        try {
            // 查询详情
            List<ISO151403SupplierRestResult> results = iso151403Service.findSellers(request.getParam());
            if (CollectionUtils.isNotEmpty(results)) {
                rsResult.setSupplierList(results);
                response.setResult(rsResult);
                response.setStatus(SystemConstant.RsStatus.SUCCESS);
                response.setMessage("查询供应商细信息成功!");
            } else {
                response.setStatus(SystemConstant.RsStatus.FAIL);
                response.setMessage("查询无供应商细信息!");
            }
        }catch (Exception e){
            logger.debug(e.getMessage());
            response.setStatus(SystemConstant.RsStatus.FAIL);
            response.setMessage(e.getMessage());
            e.printStackTrace();
        }

        return response;
    }
}
