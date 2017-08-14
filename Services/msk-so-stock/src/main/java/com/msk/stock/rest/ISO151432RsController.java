package com.msk.stock.rest;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.stock.bean.StockResult;
import com.msk.stock.bean.StockRsParamList;
import com.msk.stock.logic.ISO151432Logic;
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
 *
 * 供应商库存查询接口
 * 
 * @author zhang_qiang1
 */
@RestController
public class ISO151432RsController extends BaseRsController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(ISO151432RsController.class);

    @Autowired
    private ISO151432Logic iSO151432Logic;

    /**
     *
     * @param param
     * @return
     */
        @RequestMapping(value = "/so/queryStockBySupplier/list",
        method = RequestMethod.POST,
        produces = { MediaType.APPLICATION_JSON_VALUE })
    @Validator(validatorClass = "com.msk.stock.validator.ISO151432Validator")
    public RsResponse<StockResult> getUsedStock(@RequestBody RsRequest<StockRsParamList> param) {
        logger.info("查询可用库存");
        RsResponse<StockResult> rs = new RsResponse<StockResult>();
            StockRsParamList iso151432RsParam=param.getParam();
            String   message="查询成功";
            StockResult result= this.iSO151432Logic.queryUsedStock(iso151432RsParam);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setResult(result);
            rs.setMessage(message);
           return rs;
    }

    /**
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/so/updateStockBySupplier",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @Validator(validatorClass = "com.msk.stock.validator.ISO151432Validator")
    public RsResponse<StockResult> updateStock(@RequestBody RsRequest<StockRsParamList> param) {
        logger.info("修改库存");
        RsResponse<StockResult> rs = new RsResponse<StockResult>();
        StockRsParamList iso151432RsParam=param.getParam();
        String   message="修改成功";
        StockResult result=new StockResult();
        Integer effectCount=  this.iSO151432Logic.updateSpStockQty(iso151432RsParam);
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        result.setEffectResultCount(effectCount);
        rs.setResult(result);
        rs.setMessage(message);
        return rs;
    }

    /**
     *
     * @param param
     * @return
     */

    @RequestMapping(value = "/so/queryStockQtyStockBySupplier",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @Validator(validatorClass = "com.msk.stock.validator.ISO151432Validator")
    public RsResponse<StockResult> queryStockQty(@RequestBody RsRequest<StockRsParamList> param) {
        logger.info("查询库存数量");
        RsResponse<StockResult> rs = new RsResponse<StockResult>();
        StockRsParamList iso151432RsParam=param.getParam();
        String   message="查询成功";
        StockResult  result=this.iSO151432Logic.queryStockQty(iso151432RsParam);
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setResult(result);
        rs.setMessage(message);
        return rs;
    }




    /**
     * 添加库存
     * @param param
     * @return
     */
    @RequestMapping(value = "/so/saveStockOfSupplierList",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @Validator(validatorClass = "com.msk.stock.validator.ISO151432SsaveStockOfSupplierListValidator")
    public RsResponse<StockResult> saveStockOfSupplierList(@RequestBody RsRequest<StockRsParamList> param) {
        logger.info("添加库存");
        RsResponse<StockResult> rs = new RsResponse<StockResult>();
        StockRsParamList iso151432RsParam=param.getParam();
        String   message="操作库存成功！";
        StockResult result=new StockResult();
         this.iSO151432Logic.saveStockOfSupplierList(iso151432RsParam.getPdList());
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setResult(result);
        rs.setMessage(message);
        return rs;
    }


    /**
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/so/cancelFrozenStockSupp",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @Validator(validatorClass = "com.msk.stock.validator.ISO151432Validator")
    public RsResponse<StockResult> cancelFrozenStockSupp(@RequestBody RsRequest<StockRsParamList> param) {
        logger.debug("取消冻结库存");
        RsResponse<StockResult> rs = new RsResponse<StockResult>();
        StockRsParamList iso151432RsParam=param.getParam();
        String   message="操作成功";
        StockResult result=new StockResult();
        Integer effectCount= this.iSO151432Logic.batchCancelFrozenStockSupp(iso151432RsParam.getPdList());
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        result.setEffectResultCount(effectCount);
        rs.setResult(result);
        rs.setMessage(message);
        return rs;
    }

}
