package com.msk.so.rest;

import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.so.bean.ISO153202Param;
import com.msk.so.bean.ISO153202Result;
import com.msk.so.logic.ISO153202Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 查询卖家详情PDF接口Controller
 *
 * @author zhang_jiaxing
 * @version 1.0
 */
@RestController
public class ISO153202RsController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISO153202RsController.class);

    @Autowired
    private ISO153202Logic so153202Logic;

    /**
     * Query seller info of frist
     *
     * @param param param
     * @return result
     */
    @RequestMapping(value = "/seller/pdf",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<ISO153202Result> querySellerPDF(@RequestBody RsRequest<ISO153202Param> param) {
        logger.debug("查询卖家结算列表接口调用 start");
        RsResponse<ISO153202Result> rs = new RsResponse<ISO153202Result>();
        ISO153202Result result  =  so153202Logic.querySellerPDF(param);
        if (result != null) {
            rs.setResult(result);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询成功！");
        }else {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("查询没有数据！");
        }
        logger.debug("查询卖家结算列表接口调用 end");
        return rs;
    }

    /**
     * Query seller info of second
     *
     * @param param param
     * @return result
     */
    @RequestMapping(value = "/seller/pdfB",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<ISO153202Result> querySellerPDFB(@RequestBody RsRequest<ISO153202Param> param) {
        logger.debug("查询卖家退款列表调用 start");
        RsResponse<ISO153202Result> rs = new RsResponse<ISO153202Result>();
        ISO153202Result result  =  so153202Logic.querySellerPDFB(param);
        if (result != null) {
            rs.setResult(result);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询成功！");
        }else {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("查询没有数据！");
        }
        logger.debug("查询卖家结算列表接口调用 end");
        return rs;
    }
}
