package com.msk.seller.rest;

import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.*;
import com.msk.seller.bean.*;
import com.msk.seller.logic.ISL231109RsLogic;
import com.msk.seller.utils.BusinessConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * ISL231109RsController.
 * 查询卖家质量标准
 *
 * @author gyh
 */
@RestController
public class ISL231109RsController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISL231109RsController.class);

    @Autowired
    private ISL231109RsLogic isl231109Logic;

    /**
     * 查询卖家质量标准
     *
     * @param param param
     * @return 结果
     * @author gyh
     */
    @RequestMapping(value = "/sl/slInfo/slQlt/search",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<ISL231109RsResult> findSlSellerStd(@RequestBody RsRequest<ISL231109RsParam> param) {
        logger.info("查询卖家产品标准");
        ISL231109RsResult results = new ISL231109RsResult();
        results.setSlPdList(this.isl231109Logic.findSlProduct(param.getParam()));
        RsResponse<ISL231109RsResult> rs = new RsResponse<ISL231109RsResult>();
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setResult(results);
        return rs;
    }
}
