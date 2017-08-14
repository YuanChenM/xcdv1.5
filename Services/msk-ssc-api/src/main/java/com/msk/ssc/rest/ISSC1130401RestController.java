package com.msk.ssc.rest;

import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.*;
import com.msk.ssc.bean.*;
import com.msk.ssc.logic.SSC1130401Logic;
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
 * Created by zhang_qiang1 on 2016/6/30.
 */
@Api(description = "RestController，合同的管理接口", tags = "ISSC1130401RestController")
@RestController
public class ISSC1130401RestController extends BaseRsController {
    private static Logger logger = LoggerFactory.getLogger(ISSC1130401RestController.class);

    @Autowired
    private SSC1130401Logic ssc1130401Logic;

    @ApiOperation(value = "中标生成合同", notes = "中标生成合同")
    @RequestMapping(value = "/ssc/creatContracts", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<SSC11304Result> creatContracts(@RequestBody RsRequest<SscContractBasic> param) {
        logger.info("中标生成合同");
        RsResponse<SSC11304Result> response = new RsResponse<>();
        Long contractId = this.ssc1130401Logic.createContracts(param.getParam());
        if (contractId != null) {
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            SSC11304Result result = new SSC11304Result();
            result.setContractId(contractId);
            response.setResult(result);
            response.setMessage("处理成功");
        } else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }
        return response;
    }

    @ApiOperation(value = "检查该中标书是否已生成合同", notes = "根据中标书ID，检查该中标书是否已生成合同")
    @RequestMapping(value = "/ssc/checkBid", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<SSC11304Result> checkBid(@RequestBody RsRequest<SscBidBasicInfo> param) {
        logger.info("中标生成合同");
        RsResponse<SSC11304Result> response = new RsResponse<>();
        Long count = this.ssc1130401Logic.checkBid(param.getParam());
        if (count != null && count == 0) {//  没有已经从中标生成的 合同
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            SSC11304Result result = new SSC11304Result();
            response.setResult(result);
            response.setMessage("处理成功");
        } else {
            response.setStatus(SystemConst.RsStatus.FAIL);
        }
        return response;
    }

}