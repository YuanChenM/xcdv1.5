package com.msk.seller.rest;

import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.base.BaseRsController;
import com.hoperun.core.consts.SystemConst;
import com.msk.seller.bean.ISL231140RsResult;
import com.msk.seller.bean.SL24110306Bean;
import com.msk.seller.logic.SL24110108Logic;
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
public class ISL231140RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISL231140RsController.class);
    @Autowired
    private SL24110108Logic sL24110108Logic;
    /**
     * 删除企业管理团队接口
     *
     * @param param param
     * @return rs
     */
    @RequestMapping(value = "/sl/slInfo/slEpManager/delete",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    //@Validator(validatorClass = "com.msk.seller.validator.ISL231140Validator")
    public RsResponse<ISL231140RsResult> deleteSLEpManager(@RequestBody RsRequest<SL24110306Bean> param) {
        logger.debug("删除企业管理团队接口");
        RsResponse<ISL231140RsResult> rs = new RsResponse<ISL231140RsResult>();
        SL24110306Bean sL24110306Bean = param.getParam();
        sL24110108Logic.removeSlEpManagerPort(sL24110306Bean);
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("删除企业管理团队成功");
        return rs;
    }
}
