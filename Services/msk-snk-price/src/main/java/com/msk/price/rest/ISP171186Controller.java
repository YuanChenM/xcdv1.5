package com.msk.price.rest;

import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.price.bean.ISP171186Bean;
import com.msk.price.bean.ISP171186Param;
import com.msk.price.logic.ISP171186Logic;
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
 * Created by ren_qiang on 2016/9/14.
 */
@RestController
@Api(description ="查询参与分销的供应商信息RestController",tags ={"ISP171186Controller"})
public class ISP171186Controller extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISP171186Controller.class);

    @Autowired
    private ISP171186Logic isp17119202Logic;
    @ApiOperation(value = "供应商信息",notes = "查询参与分销的供应商信息")
    @RequestMapping(value = "/sp/getSupplierList",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<ISP171186Bean>> getSupplierListByPricePeriod(@RequestBody RsRequest<ISP171186Param> rsRequest){

        RsResponse<List<ISP171186Bean>> rsResponse = new RsResponse<List<ISP171186Bean>>();
        ISP171186Param param = rsRequest.getParam();
        if(param != null){
            rsResponse = this.isp17119202Logic.getSupplierListByPricePeriod(param);
            if(rsResponse != null  ){
                if(!CollectionUtils.isEmpty(rsResponse.getResult())){
                    rsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
                    rsResponse.setMessage("查询成功");
                }
                else {
                    rsResponse.setStatus(SystemConst.RsStatus.FAIL);
                    rsResponse.setMessage("未查询到数据");
                }

            }
            else {
                rsResponse.setStatus(SystemConst.RsStatus.FAIL);
                rsResponse.setMessage("查询失败");
            }
        }
        else {
            rsResponse.setStatus(SystemConst.RsStatus.FAIL);
            rsResponse.setMessage("请传入数值");
        }

        return rsResponse;
    }
}
