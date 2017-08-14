package com.msk.stock.rest;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.stock.bean.StockResult;
import com.msk.stock.bean.StockRsParamList;
import com.msk.stock.logic.ISO151431Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 应用场景  平台 通过一个pdCode  查询各个物流区的 卖家的存
 * ISO151431RsController.
 * 库存查询接口
 * 
 * @author zhang_qiang1
 */
@RestController
public class ISO151431RsController extends BaseRsController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(ISO151431RsController.class);

    @Autowired
    private ISO151431Logic iso151431Logic;

    /**
     *
     * @param param
     * @return
     */
        @RequestMapping(value = "/so/queryStockByPD/list",
        method = RequestMethod.POST,
        produces = { MediaType.APPLICATION_JSON_VALUE })
    @Validator(validatorClass = "com.msk.stock.validator.ISO151431Validator")
    public RsResponse<StockResult> getUsedStock(@RequestBody RsRequest<StockRsParamList> param) {
        logger.info("查询可用库存");
        RsResponse<StockResult> rs = new RsResponse<StockResult>();
            StockRsParamList iso151431RsParam=param.getParam();
            String   message="查询成功";
            StockResult result= this.iso151431Logic.queryUsedStock(iso151431RsParam);
            rs.setResult(result);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage(message);
           return rs;
    }
}
