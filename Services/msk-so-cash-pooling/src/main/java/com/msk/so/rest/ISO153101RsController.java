package com.msk.so.rest;

import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.cashPooling.bean.BaseCpResult;
import com.msk.cashPooling.bean.CpTransactionParam;
import com.msk.so.logic.ISO153101Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 交易支付接口Controller
 *
 * @author Qiu_wenting
 * @version 1.0
 */
@RestController
public class ISO153101RsController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISO153101RsController.class);

    @Autowired
    private ISO153101Logic logic;

    /**
     * 交易明细接口调用
     *
     * @param param param
     * @return result
     */
    @RequestMapping(value = "/cp/transaction",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<BaseCpResult> transaction(@RequestBody RsRequest<CpTransactionParam> param) {
        logger.debug("交易明细接口调用 start");
        RsResponse<BaseCpResult> rs = new RsResponse<BaseCpResult>();
        BaseCpResult result = new BaseCpResult();

        logic.writeTrans(param);

        if (param.getParam() != null) {
            result.setTransCode(param.getParam().getTransCode());
            rs.setResult(result);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("交易明细接口调用成功！");
        } else {
            rs.setReturnCode("F000001");
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("error.system.error");
        }

        logger.debug("交易明细接口调用 end");
        return rs;
    }
}
