package com.msk.seller.rest;

import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.base.BaseRsController;
import com.hoperun.core.consts.SystemConst;
import com.msk.seller.bean.ISL231155RsParam;
import com.msk.seller.bean.ISL231157RsResult;
import com.msk.seller.logic.ISL231154RsLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cx on 2016/2/23.
 */
@RestController
public class ISL231157RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISL231157RsController.class);
    @Autowired
    private ISL231154RsLogic iSL231154RsLogic;
    /**
     * 查询企业产品品牌荣誉
     * @param param param
     * @return rs
     */
    @RequestMapping(value = "/sl/slInfo/slEpBrandHonor/search",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.seller.validator.ISL231157Validator")
    public RsResponse<ISL231157RsResult> querySLEpBrandHonor(@RequestBody RsRequest<ISL231155RsParam> param) {
        logger.debug("查询企业产品品牌荣誉接口");
        RsResponse<ISL231157RsResult> rs = new RsResponse<ISL231157RsResult>();
        ISL231157RsResult result = new ISL231157RsResult();
        result = iSL231154RsLogic.findSLEpBrandHonorAllList(param);
        rs.setResult(result);
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("查询企业产品品牌荣誉成功");
        return rs;
    }
}
