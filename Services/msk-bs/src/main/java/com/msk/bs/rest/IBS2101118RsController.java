package com.msk.bs.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.bs.bean.IBS2101118Param;
import com.msk.bs.bean.IBS2101118Result;
import com.msk.bs.logic.IBS2101118RsLogic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhu_kai1 on 2016/8/18.
 */
@RestController
@Api(description = "查询冻品管家销售信息RestController", tags = {"IBS2101118RsController"})
public class IBS2101118RsController extends BaseRsController {

    private  static Logger logger = LoggerFactory.getLogger(IBS2101118RsController.class);

    @Autowired
    private  IBS2101118RsLogic ibs2101118RsLogic;

    /***
     * 查询冻品管家销售信息
     * @param request
     * @return
     */
    @ApiOperation(value = "冻品管家销售信息 ", notes = "查询冻品管家销售信息接口")
    @RequestMapping(value = "/bs/getSaleByBuyerId", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.bs.validator.IBS2101118Validator")
    public RsResponse<IBS2101118Result> getSaleByBuyerId(@RequestBody RsRequest<IBS2101118Param> request){
        logger.info("开始查询冻品管家销售信息");
        IBS2101118Param param = request.getParam();
        IBS2101118Result result = null;
        RsResponse<IBS2101118Result> response = new RsResponse<IBS2101118Result>();
        if(null !=param){
            result = ibs2101118RsLogic.getSaleBuyerInfo(param);
            if(null !=result){
                response.setStatus(SystemConst.RsStatus.SUCCESS);
                response.setMessage("处理成功");
                response.setResult(result);
            }else {
                response.setStatus(SystemConst.RsStatus.FAIL);
                response.setMessage("处理失败");
            }
        }
        logger.info("查询冻品管家销售信息结束");
        return response;
    }
}
