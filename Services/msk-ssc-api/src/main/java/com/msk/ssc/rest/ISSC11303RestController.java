package com.msk.ssc.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.ssc.bean.SSC11303RsBean;
import com.msk.ssc.bean.SSC11303RsParam;
import com.msk.ssc.logic.SSC11303Logic;
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
 * Created by tao_zhifa on 2016/6/29.
 */
@Api(description = "RestController，合同的管理接口", tags = "ISSC11303RestController")
@RestController
public class ISSC11303RestController extends BaseRsController {
    private static Logger logger = LoggerFactory.getLogger(ISSC11303RestController.class);

    @Autowired
    private SSC11303Logic ssc11303Logic;

    @ApiOperation(value = "批量查询合同", notes = "批量查询合同")
    @RequestMapping(value = "/ssc/findContractBasic", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<PageResult<SSC11303RsBean>> findContractBasic(@RequestBody RsRequest<SSC11303RsParam> param) {
        logger.info("合同信息一览接口开始查询");
        RsResponse<PageResult<SSC11303RsBean>> response = new RsResponse<PageResult<SSC11303RsBean>>();
        PageResult<SSC11303RsBean> res = this.ssc11303Logic.findPage(param.getParam(), SSC11303RsBean.class);

        if (res != null) {
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("处理成功");
            response.setResult(res);
        } else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }
        return response;
    }

    @ApiOperation(value = "删除合同", notes = "根据合同ID，删除合同")
    @RequestMapping(value = "/ssc/deleteContractBasic", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<Integer> deleteContractBasic(@RequestBody RsRequest<SSC11303RsParam> param) {
        logger.info("接口删除合同");
        RsResponse<Integer> response = new RsResponse<Integer>();
        int count = this.ssc11303Logic.deleteContractBasic(param.getParam());
        if (count == 0) {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("删除失败");
        } else {
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("删除成功");
        }
        response.setResult(count);
        return response;
    }

}