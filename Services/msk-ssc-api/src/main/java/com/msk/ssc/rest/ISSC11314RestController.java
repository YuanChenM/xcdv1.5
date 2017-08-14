package com.msk.ssc.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.ssc.bean.SSC11314RsPageResult;
import com.msk.ssc.bean.SSC11314RsParam;
import com.msk.ssc.bean.SSC11314RsResult;
import com.msk.ssc.logic.SSC11314Logic;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by wang_shuai on 2016/7/5.
 */
@RestController
public class ISSC11314RestController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISSC11314RestController.class);

    @Autowired
    private SSC11314Logic ssc11314Logic;

    /**
     * 查询发货确认单一览列表
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/findDeliveryConfirmInfoList",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RsResponse<SSC11314RsPageResult> findDeliveryConfirmInfoList(@RequestBody RsRequest<SSC11314RsParam> param){
        logger.info("查询发货确认单信息");
        RsResponse<SSC11314RsPageResult> resultRsResponse = new RsResponse<SSC11314RsPageResult>();
        SSC11314RsPageResult ssc11314RsPageResult = new SSC11314RsPageResult();
        int count = this.ssc11314Logic.getCount(param.getParam());
        List<SSC11314RsResult> deliveryConfirmList = null;
        if(count != NumberConst.IntDef.INT_ZERO){
            deliveryConfirmList = this.ssc11314Logic.findPageList(param.getParam(), SSC11314RsResult.class);
            ssc11314RsPageResult.setDeliveryConfirmPageResult(deliveryConfirmList);
            ssc11314RsPageResult.setTotalCount(count);
            resultRsResponse.setResult(ssc11314RsPageResult);
            resultRsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
            resultRsResponse.setMessage("处理成功");
        }else {
            resultRsResponse.setStatus(SystemConst.RsStatus.FAIL);
            resultRsResponse.setMessage("数据不存在");
        }
        return resultRsResponse;

    }

    /**
     * 查询弹出框合同信息
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/findChooseContract",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RsResponse<SSC11314RsPageResult> findChooseContract(@RequestBody RsRequest<SSC11314RsParam> param){
        logger.info("查询弹出框合同");
        RsResponse<SSC11314RsPageResult> resultRsResponse = new RsResponse<SSC11314RsPageResult>();
        SSC11314RsPageResult ssc11314RsPageResult = new SSC11314RsPageResult();
        List<SSC11314RsResult> chooseContractList = this.ssc11314Logic.findChooseContract(param.getParam());
        if(CollectionUtils.isNotEmpty(chooseContractList)){
            ssc11314RsPageResult.setDeliveryConfirmPageResult(chooseContractList);
            resultRsResponse.setResult(ssc11314RsPageResult);
            resultRsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
            resultRsResponse.setMessage("处理成功");
        } else {
            resultRsResponse.setStatus(SystemConst.RsStatus.FAIL);
            resultRsResponse.setMessage("数据不存在");
        }
        return resultRsResponse;

    }

    /**
     * 查询弹出框发货单
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/findChooseDelivery",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
     @ResponseBody
     public RsResponse<SSC11314RsPageResult> findChooseDelivery(@RequestBody RsRequest<SSC11314RsParam> param){
        logger.info("查询弹出框发货单");
        RsResponse<SSC11314RsPageResult> resultRsResponse = new RsResponse<SSC11314RsPageResult>();
        SSC11314RsPageResult ssc11314RsPageResult = new SSC11314RsPageResult();
        List<SSC11314RsResult> chooseDeliveryList = this.ssc11314Logic.findChooseDelivery(param.getParam());
        if(CollectionUtils.isNotEmpty(chooseDeliveryList)){
            ssc11314RsPageResult.setDeliveryConfirmPageResult(chooseDeliveryList);
            resultRsResponse.setResult(ssc11314RsPageResult);
            resultRsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
            resultRsResponse.setMessage("处理成功");
        } else {
            resultRsResponse.setStatus(SystemConst.RsStatus.FAIL);
            resultRsResponse.setMessage("数据不存在");
        }
        return resultRsResponse;

    }

    /**
     * 新增发货确认单
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/insertDeliveryConfirm",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RsResponse<SSC11314RsPageResult> insertDeliveryConfirm(@RequestBody RsRequest<SSC11314RsParam> param){
        logger.info("插入发货单");
        RsResponse<SSC11314RsPageResult> resultRsResponse = new RsResponse<SSC11314RsPageResult>();

        String checkFlag = this.ssc11314Logic.checkDeliveryConfirm(param.getParam());
        if(checkFlag.equals(SystemConst.RsStatus.SUCCESS)){
            resultRsResponse.setMessage("已存在该确认单");
        }else {
            String deliveryConfirmId = this.ssc11314Logic.insertDeliveryConfirm(param.getParam());
            if(!StringUtil.isNullOrEmpty(deliveryConfirmId)){
                SSC11314RsPageResult result = new SSC11314RsPageResult();
                result.setDeliveryConfirmId(deliveryConfirmId);
                resultRsResponse.setResult(result);
                resultRsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
                resultRsResponse.setMessage("处理成功");
            }else {
                resultRsResponse.setStatus(SystemConst.RsStatus.FAIL);
                resultRsResponse.setMessage("处理失败");
            }
        }
        return resultRsResponse;

    }

    /**
     * 删除发货确认单
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/deleteConfirmBasic",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RsResponse<SSC11314RsResult> deleteConfirmBasic(@RequestBody RsRequest<SSC11314RsParam> param){
        logger.info("删除发货确认单");
        RsResponse<SSC11314RsResult> resultRsResponse = new RsResponse<SSC11314RsResult>();

        String flag = this.ssc11314Logic.deleteConfirmBasic(param.getParam());
        if(flag.equals(SystemConst.RsStatus.SUCCESS)){
            resultRsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
            resultRsResponse.setMessage("删除成功");
        }else {
            resultRsResponse.setStatus(SystemConst.RsStatus.FAIL);
            resultRsResponse.setMessage("删除失败");
        }
        return resultRsResponse;
    }

}
