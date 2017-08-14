package com.msk.seller.rest;

import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.SlEcTeam;
import com.msk.common.base.BaseRsController;
import com.hoperun.core.consts.SystemConst;
import com.msk.seller.bean.ISL231143RsResult;
import com.msk.seller.logic.SL24110109Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cx on 2016/2/18.
 */
@RestController
public class ISL231143RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISL231143RsController.class);
    @Autowired
    private SL24110109Logic sL24110109Logic;
    /**
     * 修改卖家电商团队接口
     *
     * @param param param
     * @return rs
     */
    @RequestMapping(value = "/sl/slInfo/slEcTeam/update",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.seller.validator.ISL231143Validator")
    public RsResponse<ISL231143RsResult> updateSLEcTeam(@RequestBody RsRequest<SlEcTeam> param) {
        logger.debug("修改卖家电商团队接口");
        RsResponse<ISL231143RsResult> rs = new RsResponse<ISL231143RsResult>();
        SlEcTeam slEcTeam = param.getParam();
        sL24110109Logic.updateSLEcTeamPort(slEcTeam);
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("修改卖家电商团队成功");
        return rs;
    }
}
