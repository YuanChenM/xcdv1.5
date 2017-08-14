package com.msk.price.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.price.bean.*;
import com.msk.price.logic.ISP171185Logic;
import com.msk.price.logic.SP171196Logic;
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
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 根据产品列表信息获取价盘通道和价格列表
 * Created by ni_shaotang on 2016/8/10.
 */
@RestController
@Api(description = "查询神农客价盘通道和价格RestController", tags = {"ISP171185RsController"})
public class ISP171185RsController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISP171185RsController.class);


    @Autowired
    private SP171196Logic SP171196Logic;
    @Autowired
    private ISP171185Logic isp171185Logic;

    //Modif  for 产品价盘信息添加4级订单 at 2016/09/07 by ni_shaotang Start
    @ApiOperation(value = "价盘通道和价格", notes = "查询神农客价盘通道和价格接口")
    @RequestMapping(value = "/pd/getPriceWayList", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    //Modif for Bug #3366 查询神农客价盘通道和价格接口：接口必填字段未做校验 at 2016/10/18 by yang_chunyan Start
    @Validator(validatorClass = "com.msk.price.validator.ISP171185Validator")
    //Modif for Bug #3366 查询神农客价盘通道和价格接口：接口必填字段未做校验 at 2016/10/18 by yang_chunyam Start
    public RsResponse<ISP171185Result> getPriceWayList(@RequestBody RsRequest<ISP171185Param> request) {
        RsResponse<ISP171185Result> response = new RsResponse<ISP171185Result>();
        ISP171185Result result = new ISP171185Result();

        ISP171185Param param = request.getParam();

        //Modif for 参数添加自定义查询价盘 at 2016/10/18 by ni_shaotang Start
        if(StringUtil.isNullOrEmpty(param.getPricePeriod())|| param.getPricePeriod().length() != 5) {
            //获取有效价盘
            List<SP171196Bean> cycleList = SP171196Logic.getPriceCycleCode();
            if (null != cycleList && cycleList.size() > 0) {
                param.setPricePeriod(cycleList.get(0).getPricePeriod());//添加有效价盘周期条件
            }
        }
        //Modif for 参数添加自定义查询价盘 at 2016/10/18 by ni_shaotang End
        List<ISP171185Bean> list = isp171185Logic.getPdPiceWayList(param.getPricePeriod(), param.getProductList());
        if (!CollectionUtils.isEmpty(list)) {
            result.setProductList(list);
        }
        response.setResult(result);
        response.setStatus(SystemConst.RsStatus.SUCCESS);
        response.setMessage("查询成功");
        return response;
    }
    //Modif  for 产品价盘信息添加4级订单 at 2016/09/07 by ni_shaotang End
}
