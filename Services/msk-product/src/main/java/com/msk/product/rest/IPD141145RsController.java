package com.msk.product.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.jdbc.utils.DbUtils;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.PdTcProviderPackage;
import com.msk.product.bean.IPD141145RsParam;
import com.msk.product.bean.IPD141145RsResult;
import com.msk.product.logic.IPD141145Logic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 卖家申请产品审核状态查询
 * IPD141145RsController.
 *
 * @author gyh
 */
@RestController
@Api(description = "卖家申请产品审核状态查询RestController", tags = {"IPD141145RsController"})
public class IPD141145RsController extends BaseRsController {

    /**
     * logger 日志
     */
    private static Logger logger = LoggerFactory.getLogger(IPD141145RsController.class);

    @Autowired
    private IPD141145Logic ipd141145Logic;

    /**
     * 卖家申请产品审核状态查询
     *
     * @return RsResponse 结果
     * @author gyh
     */
    @ApiOperation(value = "审核状态", notes = "提供卖家申请产品审核状态查询接口")
    @RequestMapping(value = "/pd/slpd_audit_status",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.product.validator.IPD141145Validator")
    public RsResponse<IPD141145RsResult> findPdInformation(@RequestBody RsRequest<IPD141145RsParam> param) {
        RsResponse<IPD141145RsResult> rs = new RsResponse<IPD141145RsResult>();
        /**Add: 横展开设置模糊查询条件 2016/09/12   BY  任强  Start */
        if(param != null){
            if(param.getParam() != null){
                param.getParam().setSellerCode(DbUtils.buildLikeCondition(param.getParam().getSellerCode(), DbUtils.LikeMode.PARTIAL));
            }
        }
        /**Add: 横展开设置模糊查询条件 2016/09/12   BY  任强  End */
        //查询后台,获取List数据
        IPD141145RsResult result = new IPD141145RsResult();
        result.setSellerCode(param.getParam().getSellerCode());
        List<PdTcProviderPackage> pdList = this.ipd141145Logic.findPageList(param.getParam(), result);
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        if (!CollectionUtils.isEmpty(pdList) && pdList.size() > 0) {
            logger.info("卖家申请产品审核状态查询成功");
            result.setPdList(pdList);
            rs.setMessage("卖家申请产品审核状态查询成功！");
            rs.setResult(result);
        }
        else {
            rs.setMessage("没有查询到数据！");
        }

        return rs;

    }
}
