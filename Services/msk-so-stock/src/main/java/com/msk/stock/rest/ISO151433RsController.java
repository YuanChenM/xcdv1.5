package com.msk.stock.rest;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.stock.bean.ISO151433RsParam;
import com.msk.stock.bean.ISO151433RsResult;
import com.msk.stock.logic.ISO151433Logic;
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

 * 库存查询接口
 * 
 * @author zhang_qiang1
 */
@RestController
public class ISO151433RsController extends BaseRsController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(ISO151433RsController.class);

    @Autowired
    private ISO151433Logic iSO151433Logic;

    /**
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/so/sotckByPdTypeCode/list",method = RequestMethod.POST,  produces = { MediaType.APPLICATION_JSON_VALUE })
    @Validator(validatorClass = "com.msk.stock.validator.ISO151433Validator")
    public RsResponse<ISO151433RsResult> getUsedStock(@RequestBody RsRequest<ISO151433RsParam> param) {
        logger.info("查询可用库存");
        RsResponse<ISO151433RsResult> rs = new RsResponse<ISO151433RsResult>();
             ISO151433RsParam iso151433RsParam=param.getParam();
            String   message="查询成功";
            ISO151433RsResult result= this.iSO151433Logic.queryUsedStock(iso151433RsParam);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setResult(result);
            rs.setMessage(message);
           return rs;
    }
}
