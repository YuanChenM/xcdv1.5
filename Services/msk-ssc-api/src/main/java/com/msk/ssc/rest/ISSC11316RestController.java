package com.msk.ssc.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.ssc.bean.*;
import com.msk.ssc.logic.SSC11316Logic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 业务控制器：管理预入库通知单
 * Created by xia_xiaojie on 2016/7/8.
 */
@RestController
@Api(description = "预入库通知单RestController",tags = {"ISSC11316RestController"})
public class ISSC11316RestController extends BaseRsController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(ISSC11316RestController.class);

    @Autowired
    private SSC11316Logic SSC11316Logic;

    /**
     * 查询预入库通知单列表
     * 接口URL：SystemServerManager.SellerSupplyChainManage.getQueryPreintoBasics()
     */
    @ApiOperation(value = "查询",notes = "分页查询预入库列表")
    @RequestMapping(value = "/ssc/preinto/query/basics", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<SSC11316Result> queryPreintoBasics(@RequestBody RsRequest<SSC11316Param> reqParam) {
        SSC11316Param queryParam = reqParam.getParam();
        boolean paging = queryParam.isPaging();

        SSC11316Result SSC11316Result = new SSC11316Result();
        int count = NumberConst.IntDef.INT_ZERO;
        if (paging) {
            count = SSC11316Logic.getPageCount(queryParam);
            SSC11316Result.setTotalCount(count);
        }

        List<SSC11316Bean> sscDeliveryPreIntos = new ArrayList<SSC11316Bean>();
        if (!paging || count > NumberConst.IntDef.INT_ZERO) {
            sscDeliveryPreIntos = SSC11316Logic.findPageList(queryParam, SSC11316Bean.class);
        }
        SSC11316Result.setSscDeliveryPreIntos(sscDeliveryPreIntos);

        RsResponse<SSC11316Result> respResult = new RsResponse<SSC11316Result>();
        respResult.setResult(SSC11316Result);
        respResult.setStatus(SystemConst.RsStatus.SUCCESS);
        respResult.setMessage("预入库通知单查询成功");
        return respResult;
    }

    /**
     * 查询预入库通知单
     *
     * @param param
     * @return
     */
    @ApiOperation(value = "查询",notes = "查询预入库通知单")
    @RequestMapping(value = "/ssc/findPreInto",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<SSC11316Bean>> findPreInto(@RequestBody RsRequest<SSC11316Param> param) {
        logger.info("查询预入库通知单");
        RsResponse<List<SSC11316Bean>> response = new RsResponse<>();

        List<SSC11316Bean> list = SSC11316Logic.findList(param.getParam());

        if(!CollectionUtils.isEmpty(list)){
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("查询成功");
            response.setResult(list);
        }else{
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }
        return response;
    }

    /**
     * 删除预入库通知单
     */
    @ApiOperation(value = "删除",notes = "删除预入库通知单")
    @RequestMapping(value = "/ssc/deletePreInto",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RsResponse<SSC11316Result> deletePreInto(@RequestBody RsRequest<SSC11316Param> param){
        logger.info("删除预入库通知单");
        RsResponse<SSC11316Result> resultRsResponse = new RsResponse<SSC11316Result>();

        Integer flag = this.SSC11316Logic.deletePreInto(param.getParam());
        if(flag!= null && flag>0){
            resultRsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
            resultRsResponse.setMessage("删除成功");
        }else {
            resultRsResponse.setStatus(SystemConst.RsStatus.FAIL);
            resultRsResponse.setMessage("删除失败");
        }
        return resultRsResponse;
    }

}
