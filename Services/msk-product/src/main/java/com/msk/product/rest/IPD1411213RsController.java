package com.msk.product.rest;

import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.product.bean.IPD1411213RsParam;
import com.msk.product.bean.IPD1411213RsResult;
import com.msk.product.logic.IPD1411213Logic;
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

import java.util.Date;

/**
 * Created by gao_min on 2016/10/9.
 */
@RestController
@Api(description = "新增修改举报RestController", tags = {"IPD1411213RsController"})
public class IPD1411213RsController extends BaseRsController {
    /**
     * logger 日志
     */
    private static Logger logger = LoggerFactory.getLogger(IPD1411213RsController.class);

    @Autowired
    private IPD1411213Logic ipd1411213Logic;

    /**
     * 新增举报情报接口
     *
     * @return RsResponse 结果
     * @author gao_min
     */
    @ApiOperation(value = "举报信息", notes = "新增修改举报情报接口")
    @RequestMapping(value = "/pd/reportInfo/_update", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.product.validator.IPD1411213Validator")
    public RsResponse<IPD1411213RsResult> insertReportInfo(@RequestBody RsRequest<IPD1411213RsParam> param) {
        RsResponse<IPD1411213RsResult> rsResponse = new RsResponse<IPD1411213RsResult>();
        IPD1411213RsParam rsParam = param.getParam();

        String loginId = param.getLoginId();
        rsParam.setActId(loginId);
        rsParam.setUpdId(loginId);
        rsParam.setCrtId(loginId);
        Date date = DateTimeUtil.getCustomerDate();
        rsParam.setCrtTime(date);
        rsParam.setUpdTime(date);
        rsParam.setActTime(date);
        logger.info("开始新增或者修改举报信息接口！");
        rsResponse = ipd1411213Logic.insertReportInfo(rsParam);

        return rsResponse;

    }
}
