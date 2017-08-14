package com.msk.seller.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.seller.bean.ISL231192Result;
import com.msk.seller.bean.ISL231192RsParam;
import com.msk.seller.logic.ISL231192RsLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pxg on 2016/4/26.
 */
@RestController
public class ISL231192RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISL231192RsController.class);
    @Autowired
    private ISL231192RsLogic isl231192Logic;
    /**
     * 查询卖家产品货号对应的详细信息
     * @param param param
     * @return rs
     */
    @RequestMapping(value = "/sl/slInfo/slArtNoPdInfo/search",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.seller.validator.ISL231192Validator")
    public RsResponse<ISL231192Result> querySlPdArtnoInfo(@RequestBody RsRequest<ISL231192RsParam> param) {
        logger.debug("查询卖家产品货号对应的详细信息接口");
        RsResponse<ISL231192Result> rs = new RsResponse<ISL231192Result>();
        rs.setResult(isl231192Logic.queryData(param));
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("查询卖家产品货号对应的详细信息成功");
        return rs;
    }
}
