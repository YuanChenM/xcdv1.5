package com.msk.stock.rest;

import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;

import com.msk.stock.bean.ISO151421RsParam;
import com.msk.stock.bean.ISO151421RsResult;
import com.msk.stock.logic.ISO151421Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 
 * ISO251404RsController.
 * 删除恢复标准分销订单
 * 
 * @author pxg
 */
@RestController
public class ISO151421RsController extends BaseRsController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(ISO151421RsController.class);

    @Autowired
    private ISO151421Logic iSO151421Logic;

    /**
     * 供应商库存列表
     * 
     * @param param param
     * @return 结果
     * @author pxg
     */
    @RequestMapping(value = "/so/supplierstock/list",
        method = RequestMethod.POST,
        produces = { MediaType.APPLICATION_JSON_VALUE })
    @Validator(validatorClass = "com.msk.stock.validator.ISO151421Validator")
    public RsResponse<ISO151421RsResult> modifyDbOrder(@RequestBody RsRequest<ISO151421RsParam> param) {
        String message="供应商库存列表！";
        logger.info(message);
        RsResponse<ISO151421RsResult> rs = new RsResponse<ISO151421RsResult>();
        rs.setMessage(message);
        rs.setResult(this.iSO151421Logic.findAllList(param));
        return rs;
    }
}
