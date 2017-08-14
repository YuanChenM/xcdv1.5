package com.msk.price.rest;

import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.price.bean.ISP171183Bean;
import com.msk.price.bean.ISP171183Param;
import com.msk.price.bean.SP171196Bean;
import com.msk.price.logic.ISP171183Logic;
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

import java.util.List;

/**
 * 神农客产品查询价盘接口Controller
 *
 * @author yang_yang
 * @version 1.0
 */
@RestController
@Api(description = "神农客产品查询价盘RestController", tags = {"ISP171183RsController"})
public class ISP171183RsController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISP171183RsController.class);

    @Autowired
    private ISP171183Logic logic;
    @Autowired
    private SP171196Logic SP171196Logic;

    /**
     * 神农客产品查询价盘接口调用
     *
     * @param param
     * @return result
     */
    @ApiOperation(value = "价盘信息", notes = "神农客产品查询价盘接口")
    @RequestMapping(value = "/pd/pd_pricecycle",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<ISP171183Bean>> getPriceCycle(@RequestBody RsRequest<ISP171183Param> param) {
        logger.debug("神农客产品查询价盘接口调用 start");
        //获取有效价盘
        List<SP171196Bean> list = SP171196Logic.getPriceCycleCode();
        if (null != list && list.size() > 0) {
            if (null == param.getParam()) {
                ISP171183Param params = new ISP171183Param();
                param.setParam(params);
            }
            param.getParam().setPricePeriod(list.get(0).getPricePeriod());//添加有效价盘周期条件
        }
        RsResponse<List<ISP171183Bean>> rs = logic.getPriceCycle(param);

        logger.debug("神农客产品查询价盘接口调用 end");
        return rs;
    }

}
