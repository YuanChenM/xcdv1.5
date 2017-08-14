package com.msk.product.rest;

import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.product.bean.IPD141112RsParam;
import com.msk.product.bean.IPD141112RsResult;
import com.msk.product.logic.IPD141112Logic;
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
 * 产品信息
 * IPD141115RsController.
 *
 * @author xhy
 */
@RestController
@Api(description = "产品等级&卫生质量标准RestController", tags = {"IPD141112RsController"})
public class IPD141112RsController extends BaseRsController {

    /**
     * logger 日志
     */
    private static Logger logger = LoggerFactory.getLogger(IPD141112RsController.class);

    @Autowired
    private IPD141112Logic ipd141112Logic;

    /**
     * 产品等级&卫生质量标准
     *
     * @return RsResponse 结果
     * @author xhy
     */
    @ApiOperation(value = "产品等级&卫生质量", notes = "查询产品等级&卫生质量标准接口")
    @RequestMapping(value = "/pd/pdBidQltTnc",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IPD141112RsResult> findPdGradeHealth(@RequestBody RsRequest<IPD141112RsParam> param) {
        RsResponse<IPD141112RsResult> rs = new RsResponse<IPD141112RsResult>();
        //查询后台,获取List数据
        IPD141112RsResult result = this.ipd141112Logic.findListPdGradeHealth(param.getParam());
        if (result != null) {
            logger.info("查询产品等级&卫生质量标准成功！DB连接");
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询产品等级&卫生质量标准成功！");
            rs.setResult(result);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("产品等级&卫生质量标准,数据错误！");
        return rs;

    }
}
