package com.msk.seller.rest;

import com.hoperun.plug.spring.annotation.Validator;
import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.base.BaseRsController;
import com.hoperun.core.consts.SystemConst;
import com.msk.seller.bean.ISL231201RsParam;
import com.msk.seller.bean.ISL231201RsResult;
import com.msk.seller.logic.ISL231201Logic;
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
 * ISL231201RsController.
 * 取得神农客分销章程信息
 *
 * @author gyh
 */
@RestController
public class ISL231201RsController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISL231201RsController.class);

    @Autowired
    private ISL231201Logic isl231201Logic;

    /**
     * 取得神农客分销章程信息
     *
     * @param param param
     * @return 结果
     * @author gyh
     */
    @RequestMapping(value = "/sl/chapInfo",
        method = RequestMethod.POST,
        produces = { MediaType.APPLICATION_JSON_VALUE })
    @Validator(validatorClass = "com.msk.seller.validator.ISL231201Validator")
    public RsResponse<List<ISL231201RsResult>> findDbOrderInfo(@RequestBody RsRequest<ISL231201RsParam> param) {
        logger.info("取得神农客分销章程信息");
        RsResponse<List<ISL231201RsResult>> rs = new RsResponse<List<ISL231201RsResult>>();
        List<ISL231201RsResult> list = new ArrayList<ISL231201RsResult>();
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("chapClass", param.getParam().getChapClass());
        list = isl231201Logic.findSlDistReguChap(baseParam);
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setResult(list);
        return rs;
    }
}
