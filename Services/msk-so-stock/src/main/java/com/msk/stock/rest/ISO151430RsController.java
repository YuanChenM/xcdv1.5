package com.msk.stock.rest;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.stock.bean.ISO151430RsResult;
import com.msk.stock.bean.StockRsParam;
import com.msk.stock.bean.StockRsParamList;
import com.msk.stock.logic.ISO151430Logic;
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
 * ISO151423RsController.
 * 库存查询接口
 * 
 * @author zhang_qiang1
 */
@RestController
public class ISO151430RsController extends BaseRsController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(ISO151430RsController.class);

    @Autowired
    private ISO151430Logic iSO151430Logic;

    /**
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/so/queryStockBySeller/list",method = RequestMethod.POST,  produces = { MediaType.APPLICATION_JSON_VALUE })
    @Validator(validatorClass = "com.msk.stock.validator.ISO151430Validator")
    public RsResponse<ISO151430RsResult> getUsedStock(@RequestBody RsRequest<StockRsParamList> param) {
        logger.info("查询可用库存");
        RsResponse<ISO151430RsResult> rs = new RsResponse<ISO151430RsResult>();
        StockRsParamList iso151430RsParam=param.getParam();
            String   message="查询成功";
            ISO151430RsResult result= this.iSO151430Logic.queryUsedStock(iso151430RsParam);
            rs.setResult(result);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage(message);
           return rs;
    }



    /**
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/so/updateStockBySeller",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @Validator(validatorClass = "com.msk.stock.validator.ISO151430Validator")
    public RsResponse<ISO151430RsResult> updateStock(@RequestBody RsRequest<StockRsParamList> param) {
        logger.info("修改库存");
        RsResponse<ISO151430RsResult> rs = new RsResponse<ISO151430RsResult>();
        StockRsParamList iso151430RsParam=param.getParam();
        String   message="修改成功";
        ISO151430RsResult result=new ISO151430RsResult();
        Integer effectCount=  this.iSO151430Logic.updateSLStockQty(iso151430RsParam);
        result.setEffectResultCount(effectCount);
        rs.setResult(result);
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage(message);
        return rs;
    }

    /**
     * 批量取消冻结 卖家库存
     * @param param
     * @return
     */
    @RequestMapping(value = "/so/cancelFrozenStockSL",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @Validator(validatorClass = "com.msk.stock.validator.ISO151432Validator")
    public RsResponse<ISO151430RsResult> cancelFrozenStockSupp(@RequestBody RsRequest<StockRsParamList> param) {
        logger.debug("取消冻结库存");
        RsResponse<ISO151430RsResult> rs = new RsResponse<ISO151430RsResult>();
        StockRsParamList ISO151430RsParam=param.getParam();
        String   message="操作成功";
        ISO151430RsResult result=new ISO151430RsResult();
        Integer effectCount= this.iSO151430Logic.batchCancelFrozenStockSL(ISO151430RsParam.getPdList());
        result.setEffectResultCount(effectCount);
        rs.setResult(result);
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage(message);
        return rs;
    }
    
    /**冻结库存
     *
     * @param param
     * @return
     */
    public RsResponse<ISO151430RsResult> frozenStockSl(@RequestBody StockRsParam param) {
        RsResponse<ISO151430RsResult> rs = new RsResponse<ISO151430RsResult>();
        iSO151430Logic.frozenStock(param);
        return  rs;
    }

    /**取消冻结库存
     *
     * @param param
     * @return
     */
    public RsResponse<ISO151430RsResult> cancelFrozenStockSl(@RequestBody StockRsParam param) {
        RsResponse<ISO151430RsResult> rs = new RsResponse<ISO151430RsResult>();
        iSO151430Logic.cancelFrozenStock(param);
        return  rs;
    }
}
