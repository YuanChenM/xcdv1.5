package com.msk.seller.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.seller.bean.ISL231181RegionParam;
import com.msk.seller.bean.ISL231181Result;
import com.msk.seller.bean.ISL231181RsParam;
import com.msk.seller.logic.ISL231181Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cx on 2016/2/17.
 */
@RestController
public class ISL231181RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISL231181RsController.class);
    @Autowired
    private ISL231181Logic isl231181RsLogic;
    /**
     * 查询卖家所有信息接口
     *
     * @param param param
     * @return rs
     */
    @RequestMapping(value = "/sl/slInfo/searchAll",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.seller.validator.ISL231181Validator")
    public RsResponse<ISL231181Result> findSLEpManagerInfo(@RequestBody RsRequest<ISL231181RsParam> param) {
        logger.debug("查询卖家所有信息接口");
        RsResponse<ISL231181Result> rs = new RsResponse<ISL231181Result>();
        ISL231181Result result=new ISL231181Result();
        result=isl231181RsLogic.findAllDate(param);
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("查询卖家信息成功");
        rs.setResult(result);
        return rs;
    }
}
