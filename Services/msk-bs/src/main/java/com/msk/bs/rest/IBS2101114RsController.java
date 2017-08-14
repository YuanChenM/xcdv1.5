package com.msk.bs.rest;

import com.hoperun.core.consts.SystemConst;
import com.msk.bs.bean.IBS2101114Param;
import com.msk.bs.bean.IBS2101114Result;
import com.msk.bs.logic.IBS2101114RsLogic;
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

/**根据买家id获取对应的归属管家信息
 * Created by zhu_kai1 on 2016/7/13.
 */
@RestController
@Api(description = "根据买家ID查询买家归属管家信息RestController", tags = {"IBS2101114RsController"})
public class IBS2101114RsController extends BaseRsController{

    private static Logger logger = LoggerFactory.getLogger(IBS2101114RsController.class);

    @Autowired
    private IBS2101114RsLogic ibs2101114RsLogic;
    @ApiOperation(value = "归属管家信息", notes = "根据买家id获取买家归属管家（现在归属管家、原来归属那些管家）")
    @RequestMapping(value = "/bs/searchHouseInfo", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IBS2101114Result>  searchHouseInfo(@RequestBody RsRequest<IBS2101114Param> request){
        logger.info("开始查询归属管家信息");
        IBS2101114Param param = request.getParam();
        RsResponse<IBS2101114Result> response = new RsResponse<IBS2101114Result>();
        IBS2101114Result ibs2101114Result = ibs2101114RsLogic.searchHouseInfo(param);
        if(null !=ibs2101114Result){
            response.setResult(ibs2101114Result);
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("处理成功");
        }else{
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("没有查询到对应的结果");
        }
        logger.info("查询归属管家信息结束");
        return  response;
    }
}
