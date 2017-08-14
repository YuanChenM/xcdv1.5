package com.msk.seller.rest;

import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.base.BaseRsController;
import com.hoperun.core.consts.SystemConst;
import com.msk.seller.bean.ISL231122RsParam;
import com.msk.seller.bean.ISL231122RsResult;
import com.msk.seller.logic.ISL231122RsLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cx on 2016/2/15.
 */
@RestController
public class ISL231122RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISL231122RsController.class);
    @Autowired
    private ISL231122RsLogic iSL231122RsLogic;
    /**
     * 查询卖家基本信息接口
     *
     * @param param param
     * @return rs
     */
    @RequestMapping(value = "/sl/slInfo/slSeller/search",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
   // @Validator(validatorClass = "com.msk.seller.validator.ISL231122Validator")
    public RsResponse<ISL231122RsResult> querySLSeller(@RequestBody RsRequest<ISL231122RsParam> param) {
        logger.debug("查询卖家基本信息接口");
        RsResponse<ISL231122RsResult> rs=new RsResponse<ISL231122RsResult>();
        ISL231122RsResult result = new ISL231122RsResult();
        result = iSL231122RsLogic.findAllList(param);
        rs.setResult(result);
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("查询卖家基本信息成功");
        return rs;
    }
}
