package com.msk.seller.rest;

import com.hoperun.plug.spring.annotation.Validator;
import com.hoperun.core.bean.BaseParam;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.base.BaseRsController;
import com.hoperun.core.consts.SystemConst;
import com.msk.seller.bean.ISL231201RsParam;
import com.msk.seller.bean.ISL231201RsResult;
import com.msk.seller.bean.ISL231202RsParam;
import com.msk.seller.logic.ISL231201Logic;
import com.msk.seller.logic.ISL231202Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


/**
 * ISL231203RsController.
 * 创建分销章程卖家意见
 *
 * @author gyh
 */
@RestController
public class ISL231202RsController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISL231203RsController.class);

    @Autowired
    private ISL231202Logic isl231202Logic;

    /**
     * 更新分销章程卖家意见
     *
     * @param param param
     * @return 结果
     * @author gyh
     */
    @RequestMapping(value = "/sl/chapInfo/new",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.seller.validator.ISL231202Validator")
    public RsResponse modifyDbOrder(@RequestBody RsRequest<ISL231202RsParam> param) {
        logger.info("创建分销章程卖家意见");
        RsResponse rs = this.isl231202Logic.addSlDistSug(param);
        return rs;
    }
}
