package com.msk.seller.rest;

import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.base.BaseRsController;
import com.hoperun.core.consts.SystemConst;
import com.msk.seller.bean.ISL231141RsParam;
import com.msk.seller.bean.ISL231141RsResult;
import com.msk.seller.logic.SL24110100Logic;
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
public class ISL231141RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISL231141RsController.class);
    @Autowired
    private SL24110100Logic sL24110100Logic;
    /**
     * 查询企业管理团队接口
     *
     * @param param param
     * @return rs
     */
    @RequestMapping(value = "/sl/slInfo/slEpManager/search",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    //@Validator(validatorClass = "com.msk.seller.validator.ISL231141Validator")
    public RsResponse<ISL231141RsResult> querySLEpManager(@RequestBody RsRequest<ISL231141RsParam> param) {
        logger.debug("查询企业管理团队接口");
        RsResponse<ISL231141RsResult> rs = new RsResponse<ISL231141RsResult>();
        ISL231141RsResult result = new ISL231141RsResult();
        result = sL24110100Logic.findSLEpManager(param);
        rs.setResult(result);
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("查询企业管理团队成功");
        return rs;
    }
}
