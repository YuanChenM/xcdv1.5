package com.msk.seller.rest;

import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.SlEpBrand;
import com.msk.common.base.BaseRsController;
import com.hoperun.core.consts.SystemConst;
import com.msk.seller.bean.ISL231147RsResult;
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
 * Created by cx on 2016/2/19.
 */
@RestController
public class ISL231147RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISL231147RsController.class);
    @Autowired
    private ISL231146RsLogic iSL231146RsLogic;
    /**
     * 修改企业产品品牌接口
     *
     * @param param param
     * @return rs
     */
    @RequestMapping(value = "/sl/slInfo/slEpBrandcTeam/update",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    //@Validator(validatorClass = "com.msk.seller.validator.ISL231147Validator")
    public RsResponse<ISL231147RsResult> updateSLEpBrandc(@RequestBody RsRequest<SlEpBrand> param) {
        logger.debug("修改企业产品品牌接口");
        RsResponse<ISL231147RsResult> rs = new RsResponse<ISL231147RsResult>();
        SlEpBrand slEpBrand = param.getParam();
        iSL231146RsLogic.updateSlEpBrand(slEpBrand);
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("修改企业产品品牌成功");
        return rs;
    }
}
