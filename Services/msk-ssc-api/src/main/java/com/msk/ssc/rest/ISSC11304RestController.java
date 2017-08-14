package com.msk.ssc.rest;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.SscConstant;
import com.msk.core.entity.*;
import com.msk.ssc.bean.*;
import com.msk.ssc.logic.SSC11302Logic;
import com.msk.ssc.logic.SSC11304Logic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
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
 * Created by zhang_qiang1 on 2016/6/30.
 */
@Api(description = "RestController，合同、产品、包材和商务条款的管理接口", tags = "ISSC11304RestController")
@RestController
public class ISSC11304RestController extends BaseRsController {
    private static Logger logger = LoggerFactory.getLogger(ISSC11304RestController.class);

    @Autowired
    private SSC11302Logic ssc11302Logic;
    @Autowired
    private SSC11304Logic ssc11304Logic;

    @ApiOperation(value = "查询合同基本信息", notes = "根据合同ID，查询合同基本信息")
    @RequestMapping(value = "/ssc/findSscContractBasic", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<SscContractBasic> findSscBidBasic(@RequestBody RsRequest<SSC11304Param> param) {
        SscContractBasic contract = ssc11304Logic.findContractById(param.getParam());
        RsResponse<SscContractBasic> respResult = new RsResponse<>();
        respResult.setResult(contract);
        if (null != contract) {
            respResult.setStatus(SystemConst.RsStatus.SUCCESS);
            respResult.setMessage("查询成功");
        } else {
            respResult.setStatus(SystemConst.RsStatus.FAIL);
            respResult.setMessage("查询失败");
        }
        return respResult;
    }

    @ApiOperation(value = "查询合同的产品明细", notes = "根据合同ID，查询合同的产品明细，排除已有包材信息的产品")
    @RequestMapping(value = "/ssc/findContractProducts", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<SSC11304Result> findContractProducts(@RequestBody RsRequest<SSC11304Param> reqParam) {
        List<SSC11304ProductBean> products = ssc11304Logic.findContractProducts(reqParam.getParam());
        SSC11304Result result = new SSC11304Result();
        result.setPdPageResult(products);
        RsResponse<SSC11304Result> respResult = new RsResponse<SSC11304Result>();
        respResult.setResult(result);
        if (null != products) {
            respResult.setStatus(SystemConst.RsStatus.SUCCESS);
            respResult.setMessage("查询成功");
        } else {
            respResult.setStatus(SystemConst.RsStatus.FAIL);
            respResult.setMessage("查询失败");
        }
        return respResult;
    }

    @ApiOperation(value = "查询合同的产品信息", notes = "根据合同ID和产品CODE，查询合同的产品信息")
    @RequestMapping(value = "/ssc/findPd", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<SSC11304Result> findPd(@RequestBody RsRequest<SSC11304Param> param) {
        logger.info("根据合同编号查询合同产品详细信息");
        RsResponse<SSC11304Result> response = new RsResponse<SSC11304Result>();
        SSC11304Result result = new SSC11304Result();
        Long id = this.ssc11304Logic.findPD(param.getParam());
        if (id != null && id > NumberConst.IntDef.INT_ZERO) {
            result.setDetailId(id);
            response.setResult(result);
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("处理成功");
        } else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }
        return response;
    }

    @ApiOperation(value = "查询合同的包材明细", notes = "根据合同ID，查询合同的包材明细")
    @RequestMapping(value = "/ssc/findContractPackingList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<SSC11304Result> findContractPackingList(@RequestBody RsRequest<SSC11304Param> param) {
        logger.info("查询包材信息");
        RsResponse<SSC11304Result> resultRsResponse = new RsResponse<SSC11304Result>();
        SSC11304Result ssc11304Result = new SSC11304Result();
        List<SSC11304PackageBean> packingBeanList = this.ssc11304Logic.getContractPackingList(param.getParam());
        if (CollectionUtils.isNotEmpty(packingBeanList)) {
            ssc11304Result.setPackingPageResult(packingBeanList);
            resultRsResponse.setResult(ssc11304Result);
            resultRsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
            resultRsResponse.setMessage("处理成功");
        } else {
            resultRsResponse.setStatus(SystemConst.RsStatus.FAIL);
            resultRsResponse.setMessage("数据不存在");
        }
        return resultRsResponse;
    }

    @ApiOperation(value = "修改包材信息", notes = "根据包材ID，更新包材信息")
    @RequestMapping(value = "/ssc/modifyContractPacking", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<SSC11304Result> modifyContractPacking(@RequestBody RsRequest<SSC11304PackageBean> param) {
        logger.info("修改合同包材信息");
        RsResponse<SSC11304Result> resultRsResponse = new RsResponse<SSC11304Result>();
        int updateCount = this.ssc11304Logic.modifyContractPacking(param);
        if (updateCount == NumberConst.IntDef.INT_ONE) {
            resultRsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
            resultRsResponse.setMessage("处理成功");
        } else {
            resultRsResponse.setStatus(SystemConst.RsStatus.FAIL);
            resultRsResponse.setMessage("处理失败");
        }
        return resultRsResponse;
    }

    @ApiOperation(value = "查询合同的交货期计划明细", notes = "根据合同ID，查询合同的交货期计划明细")
    @RequestMapping(value = "/ssc/findDeliveryPlanList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<SSC11304Result> findDeliveryPlanList(@RequestBody RsRequest<SSC11304Param> param) {
        logger.info("根据合同编号查询合同产品交货计划信息");
        List<SSC11304DeliveryPlanBean> deliveryPlanList = this.ssc11304Logic.findDeliveryPlanList(param.getParam());
        SSC11304Result ssc11304Result = new SSC11304Result();
        ssc11304Result.setDpPageResult(deliveryPlanList);
        RsResponse<SSC11304Result> resultRsResponse = new RsResponse<SSC11304Result>();
        resultRsResponse.setResult(ssc11304Result);
        if (null != deliveryPlanList) {
            resultRsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
            resultRsResponse.setMessage("查询成功");
        } else {
            resultRsResponse.setStatus(SystemConst.RsStatus.FAIL);
            resultRsResponse.setMessage("查询失败");
        }
        return resultRsResponse;
    }

    @ApiOperation(value = "新增合同的交货期计划", notes = "新增合同的交货期计划")
    @RequestMapping(value = "/ssc/saveDeliveryPlan", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<SSC11304Result> saveDeliveryPlan(@RequestBody RsRequest<SSC11304DeliveryPlanBean> param) {
        logger.info("save合同产品交货计划信息");
        RsResponse<SSC11304Result> resultRsResponse = new RsResponse<SSC11304Result>();
        int count = this.ssc11304Logic.saveDeliveryPlan(param.getParam());
        if (count > NumberConst.IntDef.INT_ZERO) {
            resultRsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
            resultRsResponse.setMessage("save处理成功");
        } else {
            resultRsResponse.setStatus(SystemConst.RsStatus.FAIL);
            resultRsResponse.setMessage("save处理失败");
        }
        return resultRsResponse;
    }

    @ApiOperation(value = "批量新增合同的交货期计划", notes = "批量新增合同的交货期计划")
    @RequestMapping(value = "/ssc/deliveryPlan/saveBatch", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<SSC11304Result> saveBatchDps(@RequestBody RsRequest<SSC11304Param> reqParam) {
        logger.info("批量新增交货计划");
        int count = ssc11304Logic.saveBatchDps(reqParam.getParam());
        RsResponse<SSC11304Result> respResult = new RsResponse<SSC11304Result>();
        if (count > NumberConst.IntDef.INT_ZERO) {
            respResult.setStatus(SystemConst.RsStatus.SUCCESS);
            respResult.setMessage("批量新增交货计划成功");
        } else {
            respResult.setStatus(SystemConst.RsStatus.FAIL);
            respResult.setMessage("批量新增交货计划失败");
        }
        return respResult;
    }

    @ApiOperation(value = "新增或更新合同的交货期计划", notes = "新增或更新合同的交货期计划")
    @RequestMapping(value = "/ssc/deliveryPlan/saveOrUpdate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<SSC11304Result> saveOrUpdateDP(@RequestBody RsRequest<SSC11304Param> reqParam) {
        logger.info("新增或更新交货计划");
        int count = ssc11304Logic.saveOrUpdateDP(reqParam.getParam());
        RsResponse<SSC11304Result> respResult = new RsResponse<SSC11304Result>();
        if (count > NumberConst.IntDef.INT_ZERO) {
            respResult.setStatus(SystemConst.RsStatus.SUCCESS);
            respResult.setMessage("新增或更新交货计划成功");
        } else {
            respResult.setStatus(SystemConst.RsStatus.FAIL);
            respResult.setMessage("新增或更新交货计划失败");
        }
        return respResult;
    }

    @ApiOperation(value = "查询合同产品的交货批次", notes = "根据合同ID，查询合同产品的交货批次")
    @RequestMapping(value = "/ssc/findDeliveryBatchList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<SSC11304Result> findDeliveryBatchList(@RequestBody RsRequest<SSC11304Param> param) {
        logger.info("根据合同编号查询合同产品交货批次");
        RsResponse<SSC11304Result> resultRsResponse = new RsResponse<SSC11304Result>();
        SSC11304Result ssc11304Result = new SSC11304Result();
        List<SSC11304DeliveryPlanBean> deliveryPlanList = this.ssc11304Logic.findDeliveryBatchList(param.getParam());
        if (CollectionUtils.isNotEmpty(deliveryPlanList)) {
            ssc11304Result.setDpPageResult(deliveryPlanList);
            resultRsResponse.setResult(ssc11304Result);
            resultRsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
            resultRsResponse.setMessage("处理成功");
        } else {
            resultRsResponse.setStatus(SystemConst.RsStatus.FAIL);
            resultRsResponse.setMessage("数据不存在");
        }
        return resultRsResponse;
    }

    @ApiOperation(value = "新增合同的包材信息", notes = "新增合同的包材信息")
    @RequestMapping(value = "/ssc/saveContractPackageM", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<SSC11304Result> saveContractPackageM(@RequestBody RsRequest<SSC11304PackageBean> param) {
        logger.info("save合同订单包材");
        RsResponse<SSC11304Result> resultRsResponse = new RsResponse<SSC11304Result>();
        int count = this.ssc11304Logic.saveContractPackageM(param.getParam());
        if (count == NumberConst.IntDef.INT_ONE) {
            resultRsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
            resultRsResponse.setMessage("save处理成功");
        } else {
            resultRsResponse.setStatus(SystemConst.RsStatus.FAIL);
            resultRsResponse.setMessage("save处理失败");
        }
        return resultRsResponse;
    }

    @ApiOperation(value = "更新合同的交货期计划", notes = "根据交货期计划ID，更新合同的交货期计划")
    @RequestMapping(value = "/ssc/modifyDeliveryPlan", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<SSC11304Result> modifyDeliveryPlan(@RequestBody RsRequest<SscDeliveryPlanBasic> param) {
        logger.info("save合同产品交货计划信息");
        RsResponse<SSC11304Result> resultRsResponse = new RsResponse<SSC11304Result>();
        int count = this.ssc11304Logic.modifyDeliveryPlan(param);
        if (count == NumberConst.IntDef.INT_ONE) {
            resultRsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
            resultRsResponse.setMessage("save处理成功");
        } else {
            resultRsResponse.setStatus(SystemConst.RsStatus.FAIL);
            resultRsResponse.setMessage("save处理失败");
        }
        return resultRsResponse;
    }

    @ApiOperation(value = "更新合同基本信息", notes = "根据合同ID，更新合同基本信息")
    @RequestMapping(value = "/ssc/updateContractBasic", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<SSC11304Result> updateContractBasic(@RequestBody RsRequest<SscContractBasic> param) {
        logger.info("修改合同信息");
        RsResponse<SSC11304Result> resultRsResponse = new RsResponse<SSC11304Result>();
        int updateCount = this.ssc11304Logic.updateContractBasic(param.getParam());
        if (updateCount == NumberConst.IntDef.INT_ONE) {
            resultRsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
            resultRsResponse.setMessage("处理成功");
        } else {
            resultRsResponse.setStatus(SystemConst.RsStatus.FAIL);
            resultRsResponse.setMessage("处理失败");
        }
        return resultRsResponse;
    }

    @ApiOperation(value = "删除合同的包材明细", notes = "根据合同ID，删除合同的包材明细")
    @RequestMapping(value = "/ssc/delContractPackgeM", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<SSC11304Result> delContractPackgeM(@RequestBody RsRequest<SscPackageMaterialInfo> param) {
        logger.info("根据合同包材id 删除包材");
        RsResponse<SSC11304Result> response = new RsResponse<SSC11304Result>();
        int effect = this.ssc11304Logic.delContractPackgeM(param.getParam());
        if (effect == NumberConst.IntDef.INT_ONE) {
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("处理成功");
        } else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }
        return response;
    }

    @ApiOperation(value = "新增合同基本信息", notes = "新增合同基本信息")
    @RequestMapping(value = "/ssc/saveContractBasci", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<SSC11304Result> saveContract(@RequestBody RsRequest<SscContractBasic> reqParam) {
        SscContractBasic contractParam = reqParam.getParam();
        //新增时，根据中标编号查询中标id
        if (!StringUtil.isNullOrEmpty(contractParam.getBidProjectNo())
                && contractParam.getBidId() == null) {
            SSC11302Param ssc11302Param = new SSC11302Param();
            ssc11302Param.setBidProjectNo(contractParam.getBidProjectNo());
            Long bidId = ssc11302Logic.findBidIdByBidProjectNo(ssc11302Param);
            contractParam.setBidId(bidId);
            contractParam.setBidRelationType(String.valueOf(SscConstant.RelationType.RELATED));
        }

        Long contractId = ssc11304Logic.saveContract(contractParam);
        RsResponse<SSC11304Result> respResult = new RsResponse<SSC11304Result>();
        if (null != contractId) {
            SSC11304Result result = new SSC11304Result();
            result.setContractId(contractId);
            respResult.setResult(result);
            respResult.setStatus(SystemConst.RsStatus.SUCCESS);
            respResult.setMessage("新增合同成功");
        } else {
            respResult.setStatus(SystemConst.RsStatus.FAIL);
            respResult.setMessage("新增合同失败");
        }
        return respResult;
    }

    @ApiOperation(value = "更新合同基本信息", notes = "根据合同ID，更新合同基本信息")
    @RequestMapping(value = "/ssc/updateContractBasci", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<SSC11304Result> updateContractBasci(@RequestBody RsRequest<SscContractBasic> param) {
        logger.info("根据合同包材id 删除包材");
        RsResponse<SSC11304Result> response = new RsResponse<SSC11304Result>();
        int effect = this.ssc11304Logic.updateContractBasic(param.getParam());
        if (effect > NumberConst.IntDef.INT_ZERO) {
            SSC11304Result result = new SSC11304Result();
            response.setResult(result);
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("处理成功");
        } else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }
        return response;
    }

    @ApiOperation(value = "查询有效箱数", notes = "根据合同ID，查询有效箱数")
    @RequestMapping(value = "/ssc/checkEffecBoxNum", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<SSC11304Result> checkEffecBoxNum(@RequestBody RsRequest<SSC11304DeliveryPlanBean> param) {
        logger.info("根据合同编号 查询对应的 有效箱数");
        RsResponse<SSC11304Result> response = new RsResponse<SSC11304Result>();
        SSC11304Result result = this.ssc11304Logic.checkEffecBoxNum(param.getParam());
        if (result != null) {
            response.setResult(result);
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("处理成功");
        } else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }
        return response;
    }

    @ApiOperation(value = "新增合同的商务条款", notes = "新增合同的商务条款")
    @RequestMapping(value = "/ssc/saveContractBusiness", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<SSC11304Result> saveContractBusiness(@RequestBody RsRequest<SscBusinessTerms> param) {
        logger.info("保存合同商务条款");
        RsResponse<SSC11304Result> response = new RsResponse<SSC11304Result>();
        int effect = this.ssc11304Logic.saveContractBusiness(param.getParam());
        if (effect > NumberConst.IntDef.INT_ZERO) {
            SSC11304Result result = new SSC11304Result();
            response.setResult(result);
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("处理成功");
        } else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }
        return response;
    }

    @ApiOperation(value = "更新合同的商务条款", notes = "根据商务条款ID，更新合同的商务条款")
    @RequestMapping(value = "/ssc/updateContractBusiness", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<SSC11304Result> updateContractBusiness(@RequestBody RsRequest<SscBusinessTerms> param) {
        logger.info("修改合同商务条款");
        RsResponse<SSC11304Result> response = new RsResponse<SSC11304Result>();
        int effect = this.ssc11304Logic.updateContractBussiness(param.getParam());
        if (effect > NumberConst.IntDef.INT_ZERO) {
            SSC11304Result result = new SSC11304Result();
            response.setResult(result);
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("处理成功");
        } else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }
        return response;
    }

    @ApiOperation(value = "查询合同的商务条款明细", notes = "根据合同ID，查询合同的商务条款明细")
    @RequestMapping(value = "/ssc/findSscContractBusiness", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<SscBusinessTerms> findSscContractBusiness(@RequestBody RsRequest<SscBusinessTerms> param) {
        RsResponse<SscBusinessTerms> response = new RsResponse<>();
        SscBusinessTerms res = this.ssc11304Logic.findContractBusiness(param.getParam());
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

    @ApiOperation(value = "查询最大合同编号", notes = "查询最大合同编号")
    @RequestMapping(value = "/ssc/findDBContractCode", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<String> findDBContractCode(@RequestBody RsRequest<BaseParam> param) {
        RsResponse<String> response = new RsResponse<>();
        String contactCode = this.ssc11304Logic.findDBContractCode(param.getParam());
        if (contactCode != null) {
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("处理成功");
            response.setResult(contactCode);
        } else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }
        return response;
    }

    @ApiOperation(value = "检查同一个合同中包材相同的产品", notes = "检查同一个合同中包材相同的产品")
    @RequestMapping(value = "/ssc/findPack", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<Long> findPack(@RequestBody RsRequest<BaseParam> param) {
        logger.info("check 同一个合同中包材相同的产品");
        RsResponse<Long> response = new RsResponse<>();
        Long checkNum = this.ssc11304Logic.findPack(param.getParam());
        if (checkNum != null) {
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("处理成功");
            response.setResult(checkNum);
        } else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }
        return response;
    }

    @ApiOperation(value = "更新合同状态为待审核", notes = "根据合同ID，更新合同状态为待审核，使合同相关信息可以再次修改")
    @RequestMapping(value = "/ssc/enableToModify", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<Integer> enableToModify(@RequestBody RsRequest<SscContractBasic> reqParam) {
        int row = ssc11304Logic.enableToModify(reqParam.getParam());
        RsResponse<Integer> respResult = new RsResponse<Integer>();
        if (NumberConst.IntDef.INT_ONE == row) {
            respResult.setResult(row);
            respResult.setStatus(SystemConst.RsStatus.SUCCESS);
            respResult.setMessage("合同状态更新为待审核。");
        } else {
            respResult.setStatus(SystemConst.RsStatus.FAIL);
            respResult.setMessage("合同状态更新失败！");
        }
        return respResult;
    }

}