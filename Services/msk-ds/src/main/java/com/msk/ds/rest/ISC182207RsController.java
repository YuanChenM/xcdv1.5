package com.msk.ds.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.BaseEntity;
import com.msk.ds.bean.ISC182207DetailRsParam;
import com.msk.ds.bean.ISC182207RsParam;
import com.msk.ds.logic.ISC182207RsLogic;
import com.msk.ds.validator.ISC182207Validator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

/**
 * Created by li_kai1 on 2016/7/4.
 */
@Api(description = "生成美迪福接口xml文件接口",tags = {"ISC182207RsController"})
@RestController
public class ISC182207RsController extends BaseRsController {

    private static Logger logger = LoggerFactory.getLogger(ISC182207RsController.class);

    @Autowired
    private ISC182207RsLogic isl182207Logic;

    /**
     * 生成美迪福接口xml文件
     */
    @ApiOperation(value = "生成美迪福接口xml文件",notes = "生成美迪福接口xml文件接口")
    @RequestMapping(value = "/sc/createXML", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.ds.validator.ISC182207Validator")
    public RsResponse<BaseEntity> createXML(@RequestBody RsRequest<ISC182207RsParam> param) {
        logger.debug("调用卖家供应链《生成美迪福xml》接口开始");
        ISC182207RsParam isc182207RsParam = param.getParam();
        RsResponse<BaseEntity> rs = new RsResponse<BaseEntity>();
        BaseEntity baseEntity = new BaseEntity();
        rs.setResult(baseEntity);
        if (isc182207RsParam != null && CollectionUtils.isNotEmpty(isc182207RsParam.getProductList())) {
            boolean result = isl182207Logic.resolveSSCData(isc182207RsParam);
            if (result) {
                rs.setStatus(SystemConst.RsStatus.SUCCESS);
                rs.setMessage("生成美迪福xml文件并上传成功！");
            } else {
                rs.setStatus(SystemConst.RsStatus.FAIL);
                rs.setMessage("文件上传失败！");
            }
        } else {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("要生成xml文件的参数不正确！");
        }
        return rs;
    }

}
