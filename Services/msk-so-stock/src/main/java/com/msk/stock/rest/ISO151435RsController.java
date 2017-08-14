package com.msk.stock.rest;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.stock.bean.ISO151435RsParam;
import com.msk.stock.bean.ISO151435RsResult;
import com.msk.stock.logic.ISO151435Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * 
 * 库存变更接口
 * 
 * @author zhang_qiang1
 */
@RestController
public class ISO151435RsController extends BaseRsController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(ISO151435RsController.class);

    @Autowired
    private ISO151435Logic iSO151435Logic;

    /**
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/so/sotckNumber/update",method = RequestMethod.POST,  produces = { MediaType.APPLICATION_JSON_VALUE })
    @Validator(validatorClass = "com.msk.stock.validator.ISO151435Validator")
    public RsResponse<ISO151435RsResult> getUsedStock(@RequestBody RsRequest<ISO151435RsParam> param) {
        logger.info("根据订单修改库存");
        RsResponse<ISO151435RsResult> rs = new RsResponse<ISO151435RsResult>();
             ISO151435RsParam iso151435RsParam=param.getParam();
              String   message="库存修改成功";
          ISO151435RsResult result= this.iSO151435Logic.updateStock(iso151435RsParam);
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setResult(result);
            rs.setMessage(message);
           return rs;
    }

}
