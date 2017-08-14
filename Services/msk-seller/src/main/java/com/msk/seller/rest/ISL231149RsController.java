package com.msk.seller.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.seller.bean.ISL231149RsParam;
import com.msk.seller.bean.ISL231149RsResult;
import com.msk.seller.logic.ISL231146RsLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhang_chi on 2016/4/28.
 */
@RestController
public class ISL231149RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISL231149RsController.class);
    @Autowired
    private ISL231146RsLogic isl231146RsLogic;
    /**
     * 查询企业产品品牌接口
     *
     * @param param param
     * @return rs
     */
    @RequestMapping(value = "/sl/slInfo/slEpBrandcTeam/search",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.seller.validator.ISL231149Validator")
    public RsResponse<ISL231149RsResult> querySLEpBrandc(@RequestBody RsRequest<ISL231149RsParam> param) {
        logger.debug("查询企业产品品牌接口");
        RsResponse<ISL231149RsResult> rs = new RsResponse<ISL231149RsResult>();
        ISL231149RsResult result = new ISL231149RsResult();
        result = isl231146RsLogic.findSLEpBrandcAllList(param);
        rs.setResult(result);
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("查询企业产品品牌成功");
        return rs;
    }
}
