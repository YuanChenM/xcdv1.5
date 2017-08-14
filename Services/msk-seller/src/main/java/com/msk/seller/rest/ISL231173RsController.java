package com.msk.seller.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.seller.bean.ISL231172RsResult;
import com.msk.seller.bean.ISL231173RsParam;
import com.msk.seller.logic.SL241130Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cx on 2016/3/10.
 */
@RestController
public class ISL231173RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISL231173RsController.class);
    @Autowired
    private SL241130Logic sl241130Logic;
    /**
     * 卖家申请新产品包装
     *
     * @param param param
     * @return rs
     */
    @RequestMapping(value = "/sl/slInfo/slPd/newPdPkg",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.seller.validator.ISL231173Validator")
    public RsResponse<ISL231172RsResult> createSLPdPkg(@RequestBody RsRequest<ISL231173RsParam> param) {
        logger.debug("卖家申请新产品包装");
        RsResponse rs=new RsResponse();
        sl241130Logic.saveNewPd(param.getParam().getNewPdPkgList());
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("卖家申请新产品包装");
        return rs;
    }
}
