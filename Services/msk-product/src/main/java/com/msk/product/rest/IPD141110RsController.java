package com.msk.product.rest;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsResponse;
import com.msk.product.bean.IPD141110RsParam;
import com.msk.product.logic.IPD141110Logic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 产品分类
 * IPD141110RsController.
 *
 * @author xhy
 */
@RestController
@Api(description = "产品分类RestController", tags = {"IPD141110RsController"})
public class IPD141110RsController extends BaseRsController {

    /**
     * logger 日志
     */
    private static Logger logger = LoggerFactory.getLogger(IPD141110RsController.class);

    @Autowired
    private IPD141110Logic ipd141110Logic;

    /**
     * 产品分类接口查询
     *
     * @return RsResponse 结果
     * @author xhy
     */
    @ApiOperation(value = "产品分类", notes = "产品分类接口")
    @RequestMapping(value = "/pd/pdBidType",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<IPD141110RsParam>> findPdClassesAndList() {

        RsResponse<List<IPD141110RsParam>> rs = new RsResponse<List<IPD141110RsParam>>();
        //查询后台,获取List数据
        BaseParam param = new BaseParam();
        List<IPD141110RsParam> result = this.ipd141110Logic.findList(param);
        if (result != null) {
            logger.info("查询产品分类成功！DB连接");
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询产品分类成功！");
            rs.setResult(result);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("产品标准包装档案卡查询接口,数据错误！");
        return rs;

    }
}
