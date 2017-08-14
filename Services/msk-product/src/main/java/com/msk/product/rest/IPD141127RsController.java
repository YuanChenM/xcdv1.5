package com.msk.product.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.product.bean.IPD141127RsParam;
import com.msk.product.bean.IPD141127RsResult;
import com.msk.product.logic.IPD141127Logic;
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
 * 产品卫生标准
 * IPD141127RsController.
 *
 * @author xhy
 */
@RestController
@Api(description = "安全卫生指标档案卡查询接口RestController", tags = {"IPD141127RsController"})
public class IPD141127RsController extends BaseRsController {

    /**
     * logger 日志
     */
    private static Logger logger = LoggerFactory.getLogger(IPD141127RsController.class);

    @Autowired
    private IPD141127Logic ipd141127Logic;

    /**
     * 产品等级&卫生质量标准
     *
     * @return RsResponse 结果
     * @author xhy
     */
    @ApiOperation(value = "安全卫生指标档案卡", notes = "查询产品的安全卫生指标档案卡信息")
    @RequestMapping(value = "/pd/pd_sft_std",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.product.validator.IPD141127Validator")
    public RsResponse<List<IPD141127RsResult>> findPdGradeHealth(@RequestBody RsRequest<IPD141127RsParam> param) {
        RsResponse<List<IPD141127RsResult>> rs = new RsResponse<List<IPD141127RsResult>>();
        //查询后台,获取List数据
        List<IPD141127RsResult> result = this.ipd141127Logic.findListPdGradeHealth(param.getParam());
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
