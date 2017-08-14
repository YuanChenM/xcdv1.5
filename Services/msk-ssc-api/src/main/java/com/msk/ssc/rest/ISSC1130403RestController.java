package com.msk.ssc.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.ssc.logic.SSC1130403Logic;
import com.msk.ssc.bean.SSC11304Param;
import com.msk.ssc.bean.SSC11304ProductBean;
import com.msk.ssc.bean.SSC11304Result;
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
 * Created by xia_xiaojie on 2016/8/31.
 */
@Api(description = "RestController，合同产品的管理接口", tags = "ISSC1130403RestController")
@RestController
public class ISSC1130403RestController extends BaseRsController {
    private static Logger logger = LoggerFactory.getLogger(ISSC1130403RestController.class);

    @Autowired
    private SSC1130403Logic ssc1130403Logic;

    /**
     * 单表查询，REDIS中尚未配置该接口
     */
    //@RequestMapping(value = "/ssc/contract/product/get", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<SSC11304Result> getProducts(@RequestBody RsRequest<SSC11304Param> reqParam) {
        List<SSC11304ProductBean> productBeans = ssc1130403Logic.getProducts(reqParam.getParam());
        SSC11304Result ssc11304Result = new SSC11304Result();
        ssc11304Result.setProducts(productBeans);

        RsResponse<SSC11304Result> respResult = new RsResponse<SSC11304Result>();
        respResult.setResult(ssc11304Result);
        respResult.setStatus(SystemConst.RsStatus.SUCCESS);
        return respResult;
    }

    @ApiOperation(value = "查询合同的产品明细", notes = "根据合同ID，查询合同中的产品，及其交货计划")
    @RequestMapping(value = "/ssc/findContractPdDetailList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<SSC11304Result> findProductsByContractId(@RequestBody RsRequest<SSC11304Param> reqParam) {
        List<SSC11304ProductBean> productBeans = ssc1130403Logic.findProductsByContractId(reqParam.getParam());
        SSC11304Result ssc11304Result = new SSC11304Result();
        ssc11304Result.setProducts(productBeans);

        RsResponse<SSC11304Result> respResult = new RsResponse<SSC11304Result>();
        respResult.setResult(ssc11304Result);
        if (null != productBeans) {
            respResult.setStatus(SystemConst.RsStatus.SUCCESS);
            respResult.setMessage("查询成功");
        }
        else {
            respResult.setStatus(SystemConst.RsStatus.FAIL);
            respResult.setMessage("查询失败");
        }
        return respResult;
    }

    @ApiOperation(value = "删除产品", notes = "根据合同产品ID，删除产品、包装和交货计划，更新合同金额和状态")
    @RequestMapping(value = "/ssc/delContractPd", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<SSC11304Result> deleteProduct(@RequestBody RsRequest<SSC11304ProductBean> reqParam) {
        int count = ssc1130403Logic.deleteProduct(reqParam.getParam());
        RsResponse<SSC11304Result> respResult = new RsResponse<SSC11304Result>();
        if (NumberConst.IntDef.INT_ONE == count) {
            respResult.setStatus(SystemConst.RsStatus.SUCCESS);
            respResult.setMessage("删除成功！");
        }
        else {
            respResult.setStatus(SystemConst.RsStatus.FAIL);
            respResult.setMessage("当前数据已经被别人修改了,请重新加载数据进行修改");
        }
        return respResult;
    }

    @ApiOperation(value = "更新产品", notes = "根据合同产品ID，更新产品，更新合同金额和状态")
    @RequestMapping(value = "/ssc/updateContractOrder", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<SSC11304Result> updateProduct(@RequestBody RsRequest<SSC11304ProductBean> reqParam) {
        int count = ssc1130403Logic.updateProduct(reqParam.getParam());
        RsResponse<SSC11304Result> respResult = new RsResponse<SSC11304Result>();
        if (NumberConst.IntDef.INT_ONE == count) {
            respResult.setStatus(SystemConst.RsStatus.SUCCESS);
            respResult.setMessage("更新成功！");
        }
        else {
            respResult.setStatus(SystemConst.RsStatus.FAIL);
            respResult.setMessage("更新失败！");
        }
        return respResult;
    }

    @ApiOperation(value = "新增产品", notes = "新增产品，更新合同金额和状态")
    @RequestMapping(value = "/ssc/saveContractOrder", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<SSC11304Result> saveProduct(@RequestBody RsRequest<SSC11304ProductBean> reqParam) {
        long productId = ssc1130403Logic.saveProduct(reqParam.getParam());
        SSC11304Result ssc11304Result = new SSC11304Result();
        ssc11304Result.setProductId(productId);

        RsResponse<SSC11304Result> respResult = new RsResponse<SSC11304Result>();
        respResult.setStatus(SystemConst.RsStatus.SUCCESS);
        return respResult;
    }

}
