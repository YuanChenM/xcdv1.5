package com.msk.product.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.product.bean.IPD141121RsParam;
import com.msk.product.bean.IPD141121RsResult;
import com.msk.product.logic.IPD141121Logic;
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

import java.util.List;

/**
 * 产品技术标准接口
 * IPD141121RsController.
 *
 * @author xhy
 */
@RestController
@Api(description = "加工技术档案卡查询接口RestController", tags = {"IPD141121RsController"})
public class IPD141121RsController extends BaseRsController {

    /**
     * logger 日志
     */
    private static Logger logger = LoggerFactory.getLogger(IPD141121RsController.class);

    @Autowired
    private IPD141121Logic ipd141121Logic;

    /**
     * 产品等级&卫生质量标准
     *
     * @return RsResponse 结果
     * @author xhy
     */
    @ApiOperation(value = "加工技术档案卡", notes = "查询产品的加工技术档案卡信息")
    @RequestMapping(value = "/pd/pd_mct_std",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.pd.validator.IPD141121Validator")
    public RsResponse<List<IPD141121RsResult>> findListMct(@RequestBody RsRequest<IPD141121RsParam> param) {
        RsResponse<List<IPD141121RsResult>> rs = new RsResponse<List<IPD141121RsResult>>();
        //查询后台,获取List数据
        List<IPD141121RsResult> result = this.ipd141121Logic.findListMct(param.getParam());
        rs.setResult(result);
        if (result != null) {
            logger.info("产品技术标准接口成功！DB连接");
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("产品技术标准接口成功！");
            rs.setResult(result);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("产品技术标准接口,数据错误！");
        return rs;
    }


    /**
     * 产品等级&卫生质量标准
     *
     * @return RsResponse 结果
     * @author yangchunyan
     */
    @RequestMapping(value = "/product/findPdMct",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.pd.validator.IPD141121Validator")
    public RsResponse<List<IPD141121RsResult>> findMctList(@RequestBody RsRequest<IPD141121RsParam> param) {
        RsResponse<List<IPD141121RsResult>> rs = new RsResponse<List<IPD141121RsResult>>();
        //查询后台,获取List数据
        List<IPD141121RsResult> result = this.ipd141121Logic.findListMct(param.getParam());
        rs.setResult(result);
        if (result != null) {
            logger.info("产品技术标准接口成功！DB连接");
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("产品技术标准接口成功！");
            rs.setResult(result);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("产品技术标准接口,数据错误！");
        return rs;
    }
}
