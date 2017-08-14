package com.msk.so.rest;

import com.hoperun.core.consts.SystemConst;
import com.msk.cashPooling.bean.ISO153105Param;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.so.bean.ISO153105Result;
import com.msk.so.logic.ISO153105Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 查询买家详情PDF接口Controller
 *
 * @author zhang_chi
 * @version 1.0
 */
@RestController
public class ISO153105RsController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISO153105RsController.class);

    @Autowired
    private ISO153105Logic so153105Logic;

    /**
     * 查询买家详情打印PDF
     *
     * @param param param
     * @return result
     */
    @RequestMapping(value = "/buyer/pdf",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<ISO153105Result> queryBuyerPDF(@RequestBody RsRequest<ISO153105Param> param) {
        logger.debug("查询买家结算列表接口调用 start");
        RsResponse<ISO153105Result> rs = new RsResponse<ISO153105Result>();
        ISO153105Result result  =  so153105Logic.queryBuyerPDF(param);
        if (result != null) {
            rs.setResult(result);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询成功！");
        }else {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("查询没有数据！");
        }
        logger.debug("查询买家结算列表接口调用 end");
        return rs;
    }
}
