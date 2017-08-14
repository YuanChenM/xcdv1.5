package com.msk.product.rest;

import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.product.bean.IPD141146RsParam;
import com.msk.product.bean.IPD141146RsResult;
import com.msk.product.logic.IPD141146Logic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yang_chunyan on 2016/4/29.
 */
@RestController
@Api(description = "查询产品信息RestController", tags = {"IPD141146RsController"})
public class IPD141146RsController extends BaseRsController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(IPD141146RsController.class);

    @Autowired
    private IPD141146Logic ipd141146Logic;
    /**
     * 产品信息查询接口
     *
     * @return RsResponse 结果
     * @author yang_chunyan
     */
    @ApiOperation(value = "产品信息", notes = "查询产品信息")
    @RequestMapping(value = "/pd/pd_pdInfo",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IPD141146RsResult> findPdInfo(@RequestBody RsRequest<IPD141146RsParam> param) {
        logger.info("查询产品信息");
        RsResponse<IPD141146RsResult> rs = new RsResponse<IPD141146RsResult>();

        IPD141146RsResult res = ipd141146Logic.findPdInfo(param.getParam());
        if (res != null) {
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("数据正常！");
            rs.setResult(res);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("产品信息查询异常!");
        return rs;
    }
}
