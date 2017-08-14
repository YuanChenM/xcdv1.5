package com.msk.product.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.product.bean.IPD141144RsParam;
import com.msk.product.bean.IPD141144RsResult;
import com.msk.product.logic.IPD141144Logic;
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
 * 卖家产品库存查询
 * IPD141144RsController.
 *
 * @author xhy
 */
@RestController
@Api(description = "卖家产品库存查询RestController", tags = {"IPD141144RsController"})
public class IPD141144RsController extends BaseRsController {

    /**
     * logger 日志
     */
    private static Logger logger = LoggerFactory.getLogger(IPD141144RsController.class);

    @Autowired
    private IPD141144Logic ipd141144Logic;

    /**
     * 产品信息接口查询
     *
     * @return RsResponse 结果
     * @author xhy
     */
    @ApiOperation(value = "卖家产品库存", notes = "提供卖家产品库存查询接口")
    @RequestMapping(value = "/pd/pd_stock",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.product.validator.IPD141144Validator")
    public RsResponse<IPD141144RsResult> findPdInformation(@RequestBody RsRequest<IPD141144RsParam> param) {
        RsResponse<IPD141144RsResult> rs = new RsResponse<IPD141144RsResult>();
        //查询后台,获取List数据
        IPD141144RsResult result = this.ipd141144Logic.findListSl(param.getParam());
        if (result != null) {
            logger.info("卖家产品库存查询成功！DB连接");
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询卖家产品库存查询接口成功！");
            rs.setResult(result);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("没有查询到数据！");
        return rs;

    }
}
