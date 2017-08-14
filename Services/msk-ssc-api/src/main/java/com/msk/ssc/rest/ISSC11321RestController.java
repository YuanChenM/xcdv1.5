package com.msk.ssc.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.ssc.bean.SSC11321RsBean;
import com.msk.ssc.bean.SSC11321RsParam;
import com.msk.ssc.bean.SSC11321RsResult;
import com.msk.ssc.logic.SSC11321Logic;
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

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by yang_yang
 */
@Api(description = "RestController，核销单的管理接口", tags = "ISSC11321RestController")
@RestController
public class ISSC11321RestController extends BaseRsController {
    private static Logger logger = LoggerFactory.getLogger(ISSC11321RestController.class);

    @Autowired
    private SSC11321Logic ssc11321Logic;

    @ApiOperation(value = "查询核销单列表", notes = "查询核销单列表")
    @RequestMapping(value = "/ssc/searchContractVerification", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<PageResult<SSC11321RsBean>> searchContractVerification(@RequestBody RsRequest<SSC11321RsParam> param) {
        logger.info("查询核销单列表");
        RsResponse<PageResult<SSC11321RsBean>> response = new RsResponse<>();
        PageResult<SSC11321RsBean> pageResult = this.ssc11321Logic.findVerifications(param.getParam());
        List<SSC11321RsBean> ssc11321RsBeans = pageResult.getData();

        if (ssc11321RsBeans != null) {
            BigDecimal zero = BigDecimal.ZERO.setScale(NumberConst.IntDef.INT_TWO);
            for (SSC11321RsBean ssc11321RsBean : ssc11321RsBeans) {
                if (ssc11321RsBean.getContractAmountPaid() == null) {
                    ssc11321RsBean.setContractAmountPaid(zero);
                }
                if (ssc11321RsBean.getVerificationAmount() == null) {
                    ssc11321RsBean.setVerificationAmount(zero);
                }
            }
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("查询成功");
            response.setResult(pageResult);
        } else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }
        return response;
    }

    @ApiOperation(value = "更新核销单审核状态", notes = "根据核销单ID，更新核销单审核状态")
    @RequestMapping(value = "/ssc/auditVerification", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<SSC11321RsResult> auditVerification(@RequestBody RsRequest<SSC11321RsBean> reqBean) {
        int row = ssc11321Logic.updateAuditStatus(reqBean.getParam());
        RsResponse<SSC11321RsResult> respResult = new RsResponse<SSC11321RsResult>();
        if (NumberConst.IntDef.INT_ONE == row) {
            respResult.setStatus(SystemConst.RsStatus.SUCCESS);
            respResult.setMessage("更新成功");
        } else {
            respResult.setStatus(SystemConst.RsStatus.FAIL);
            respResult.setMessage("更新失败");
        }
        return respResult;
    }

    @ApiOperation(value = "删除核销单", notes = "根据核销单ID，删除核销单")
    @RequestMapping(value = "/ssc/deleteVerification", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<SSC11321RsResult> deleteVerification(@RequestBody RsRequest<SSC11321RsBean> reqParam) {
        int count = ssc11321Logic.deleteVerification(reqParam.getParam());
        RsResponse<SSC11321RsResult> respResult = new RsResponse<SSC11321RsResult>();
        if (NumberConst.IntDef.INT_ONE == count) {
            respResult.setStatus(SystemConst.RsStatus.SUCCESS);
            respResult.setMessage("删除成功");
        } else {
            respResult.setStatus(SystemConst.RsStatus.FAIL);
            respResult.setMessage("删除失败");
        }
        return respResult;
    }

}