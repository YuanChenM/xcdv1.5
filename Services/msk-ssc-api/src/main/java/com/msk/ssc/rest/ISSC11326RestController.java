package com.msk.ssc.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.ssc.bean.*;
import com.msk.ssc.logic.SSC11326Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by liu_yan2 on 2016/8/9.
 */

@RestController
public class ISSC11326RestController extends BaseRsController {

    Logger logger = LoggerFactory.getLogger(ISSC11326RestController.class);

    @Autowired
    private SSC11326Logic logic;

    /**
     * 查询合同基本信息、生效日及交货期中的最后交货日
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/findContractPlanInfo",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RsResponse<SSC11326RsBean> findContractPlanInfo(@RequestBody RsRequest<SSC11326RsParam> param) {
        logger.info("查询合同生效日及交货期中的最后交货日");
        RsResponse<SSC11326RsBean> response = new RsResponse<SSC11326RsBean>();
        SSC11326RsBean bean = this.logic.findContractPlanInfo(param.getParam());
        if (bean!=null) {
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("处理成功");
            response.setResult(bean);
        } else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }
        return response;
    }

    /**
     * 查询生产期/待运期产品管控
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/findProducePdControl",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RsResponse<SSC11326RsResult> findProducePdControl(@RequestBody RsRequest<SSC11326RsParam> param) {
        logger.info("查询生产期/待运期产品管控");
        RsResponse<SSC11326RsResult> response = new RsResponse<SSC11326RsResult>();
        SSC11326RsResult bean = this.logic.findProducePdControl(param.getParam());
        if (bean!=null) {
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("处理成功");
            response.setResult(bean);
        } else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }
        return response;
    }

    /**
     * 入库产品管控
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/findStockProductDetail",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RsResponse<SSC11317PreIntoBean> findStockProductDetail(@RequestBody RsRequest<SSC11326RsParam> param) {
        logger.info("查询入库产品管控");
        RsResponse<SSC11317PreIntoBean> response = new RsResponse<SSC11317PreIntoBean>();
        SSC11317PreIntoBean bean = this.logic.findStockProductDetail(param.getParam());
        if (bean!=null) {
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("处理成功");
            response.setResult(bean);
        } else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }
        return response;
    }

    /**
     * 批量保存或更新生产期/待运期产品管控
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/batchSaveOrUpdateProducePlan",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RsResponse<Integer> batchSaveOrUpdateProducePlan(@RequestBody RsRequest<SSC11326RsParam> param) {
        logger.info("批量保存或更新生产期/待运期产品管控");
        RsResponse<Integer> response = new RsResponse<Integer>();
        int susCount = this.logic.batchSaveOrUpdateProducePlan(param.getParam());
        if (susCount > NumberConst.IntDef.INT_ZERO) {
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("处理成功");
            response.setResult(susCount);
        } else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("处理失败");
        }
        return response;
    }

    /**
     * 运输期产品管控
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/findDeliveryPdControl",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RsResponse<SSC11326RsResult> findDeliveryPdControl(@RequestBody RsRequest<SSC11326RsParam> param) {
        logger.info("查询运输期产品管控");
        RsResponse<SSC11326RsResult> response = new RsResponse<SSC11326RsResult>();
        SSC11326RsResult bean = this.logic.findDeliveryPdControl(param.getParam());
        if (bean!=null) {
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("处理成功");
            response.setResult(bean);
        } else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }
        return response;
    }

    /**
     * 批量保存或更新运输期产品监控
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/batchSaveOrUpdatePdControl",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RsResponse<Integer> batchSaveOrUpdatePdControl(@RequestBody RsRequest<SSC11326RsParam> param) {
        logger.info("批量保存或更新运输期产品监控");
        RsResponse<Integer> response = new RsResponse<Integer>();
        int susCount = this.logic.batchSaveOrUpdatePdControl(param.getParam());
        if (susCount > NumberConst.IntDef.INT_ZERO) {
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("处理成功");
            response.setResult(susCount);
        } else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("处理失败");
        }
        return response;
    }
}
