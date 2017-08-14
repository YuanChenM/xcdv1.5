package com.msk.so.rest;

import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.so.bean.CpResult;
import com.msk.so.bean.ISO153104Param;
import com.msk.so.logic.ISO153104Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 查询卖家结算列表接口Controller
 *
 * @author yang_yang
 * @version 1.0
 */
@RestController
public class ISO153104RsController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISO153104RsController.class);

    @Autowired
    private ISO153104Logic logic;

    /**
     * 查询卖家结算列表接口调用
     *
     * @param param param
     * @return result
     */
    @RequestMapping(value = "/cp/seller/list",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<CpResult> queryBuyerList(@RequestBody RsRequest<ISO153104Param> param) {
        logger.debug("查询卖家结算列表接口调用 start");
        RsResponse<CpResult> rs = new RsResponse<CpResult>();
        CpResult result = logic.findSO153202List(param);
        if (param.getParam() != null) {
            rs.setResult(result);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询卖家结算列表接口调用成功！");
        }
        logger.debug("查询卖家结算列表接口调用 end");
        return rs;
    }
}
