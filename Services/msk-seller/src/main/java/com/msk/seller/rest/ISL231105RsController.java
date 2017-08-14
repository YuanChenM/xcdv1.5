package com.msk.seller.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.seller.bean.ISL231105RsParam;
import com.msk.seller.bean.ISL231105RsResult;
import com.msk.seller.logic.ISL231105RsLogic;
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
public class ISL231105RsController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISL231105RsController.class);

    @Autowired
    private ISL231105RsLogic isl231105Logic;

    /**
     * 查询卖家账户接口
     *
     * @param param param
     * @return rs
     */
    @RequestMapping(value = "/sl/slInfo/slAccount/search", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.seller.validator.ISL231105Validator")
    public RsResponse<ISL231105RsResult> querySLAccount(@RequestBody RsRequest<ISL231105RsParam> param) {
        logger.debug("查询卖家账户接口");
        RsResponse<ISL231105RsResult> rs = new RsResponse<ISL231105RsResult>();
        ISL231105RsParam iSL231105RsParam = param.getParam();
        ISL231105RsResult slAccountList = isl231105Logic.querySlAccount(iSL231105RsParam);
        if (null == slAccountList) {
            throw new BusinessException("卖家账户不存在!");
        }else{
            rs.setResult(slAccountList);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询卖家账户成功");
        }
        return rs;
    }

}


