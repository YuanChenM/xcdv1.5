package com.msk.bs.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.bs.bean.IBS2101119RsBean;
import com.msk.bs.bean.IBS2101119RsParam;
import com.msk.bs.bean.IBS2101119RsResult;
import com.msk.bs.logic.IBS2101119RsLogic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ren_qiang on 2016/8/18.
 */
@RestController
@Api(description = "添加通过管家ID列表获得管家信息列表RestController", tags = {"IBS2101119RsController"})
public class IBS2101119RsController extends BaseRsController {

    private  static Logger logger = LoggerFactory.getLogger(IBS2101119RsController.class);

    @Autowired
    private IBS2101119RsLogic ibs2101119RsLogic;

    @ApiOperation(value = "获得管家信息列表", notes = "添加通过管家ID列表获得管家信息列表接口")
    @RequestMapping(value = "/bs/getHouseInfoById", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IBS2101119RsResult> getHouseInfoById(@RequestBody RsRequest<IBS2101119RsParam> request){
        logger.info("开始查询冻品管家信息");
        RsResponse<IBS2101119RsResult> rsResponse = new RsResponse<IBS2101119RsResult>();
        IBS2101119RsResult result = new IBS2101119RsResult();
        /**添加request非空判断 2016/10/13 任强 Start*/
        if(null != request){
            IBS2101119RsParam param = request.getParam();
            if(null != param){
                List<IBS2101119RsBean> list = ibs2101119RsLogic.getHouseInfoById(param);
                if(!CollectionUtils.isEmpty(list)){
                    result.setHouseList(list);
                    rsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
                    rsResponse.setMessage("处理成功");
                    rsResponse.setResult(result);
                }
                else{
                    rsResponse.setStatus(SystemConst.RsStatus.FAIL);
                    rsResponse.setMessage("没有查询到对应的结果");
                }

            }
        }
        /**添加request非空判断 2016/10/13 任强 End*/
        logger.info("结束查询冻品管家信息");
        return  rsResponse;

    }
}
