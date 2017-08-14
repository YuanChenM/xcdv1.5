package com.msk.ssc.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.ssc.bean.*;
import com.msk.ssc.logic.SSC11315Logic;
import com.msk.ssc.logic.SSC11316Logic;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 发货确认单明细接口
 * Created by sun_jiaju on 2016/7/5.
 */
@RestController
public class ISSC11315RestController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISSC11315RestController.class);


    @Autowired
    private SSC11315Logic ssc11315Logic;

    @Autowired
    private SSC11316Logic ssc11316Logic;

    /**
     * 根据发货确认单编号查询发货确认单信息
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/findDeliveryConfirm",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<SSC11315DeliveryConfirmRsBean> findDeliveryConfirm(@RequestBody RsRequest<SSC11315Param> param) {
        logger.info("根据发货确认单编号查询发货订单基本信息");
        RsResponse<SSC11315DeliveryConfirmRsBean> response = new RsResponse<SSC11315DeliveryConfirmRsBean>();
        SSC11315DeliveryConfirmRsBean result = this.ssc11315Logic.findDeliveryConfirm(param.getParam());

        if(result != null){
            response.setResult(result);
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("处理成功");
        } else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }
        return response;
    }

    /**
     * 根据发货确认单编号查询发货确认产品信息总计
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/findDeliveryConfirmDetailTotal",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<SSC11315DeliveryConfirmDetailRsBean> findDeliveryConfirmDetailTotal(@RequestBody RsRequest<SSC11315Param> param) {
        logger.info("根据发货确认单编号查询发货确认产品信息总计");
        RsResponse<SSC11315DeliveryConfirmDetailRsBean> response = new RsResponse<SSC11315DeliveryConfirmDetailRsBean>();
        SSC11315DeliveryConfirmDetailRsBean result = this.ssc11315Logic.findDeliveryConfirmDetailTotal(param.getParam());
        if(result != null){
            response.setResult(result);
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("处理成功");
        } else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }
        return response;
    }

    /**
     * 更新发货确认单信息
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/modifyDeliveryConfirm",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<SSC11315DeliveryConfirmRsBean> modifyDeliveryConfirm(@RequestBody RsRequest<SSC11315Param> param) {
        logger.info("更新发货确认单信息");
        RsResponse<SSC11315DeliveryConfirmRsBean> response = new RsResponse<SSC11315DeliveryConfirmRsBean>();
        int count = this.ssc11315Logic.modifyDeliveryConfirm(param.getParam());
        if (count == NumberConst.IntDef.INT_ONE) {
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("发货确认单信息更新成功");
        } else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("发货确认单信息更新失败");
        }
        return response;
    }

    /**
     * 更新发货确认产品信息
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/modifyDeliveryConfirmDetail",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<SSC11315DeliveryConfirmRsBean> modifyDeliveryConfirmDetail(@RequestBody RsRequest<SSC11315Param> param) {
        logger.info("更新发货确认产品信息");
        RsResponse<SSC11315DeliveryConfirmRsBean> response = new RsResponse<SSC11315DeliveryConfirmRsBean>();
        int count = this.ssc11315Logic.modifyDeliveryConfirmDetail(param.getParam());
        if (count == NumberConst.IntDef.INT_ONE) {
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("发货确认产品信息更新成功");
        } else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("发货确认产品信息更新失败");
        }
        return response;
    }

    /**
     * 根据发货确认单编号查询发货确认产品信息
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/findDeliveryConfirmDetail",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<PageResult<SSC11315DeliveryConfirmDetailRsBean>> findDeliveryConfirmDetail(@RequestBody RsRequest<SSC11315Param> param) {
        logger.info("根据发货确认单编号查询发货确认产品信息");
        RsResponse<PageResult<SSC11315DeliveryConfirmDetailRsBean>> response=new RsResponse<>();
        PageResult<SSC11315DeliveryConfirmDetailRsBean> res =this.ssc11315Logic.findPage(param.getParam(), SSC11315DeliveryConfirmDetailRsBean.class );
        if(res!=null){
            res.setRecordsTotal(0);
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("处理成功");
            response.setResult(res);
        }else {
            res.setRecordsTotal(0);
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }
        return response;
    }

    /**
     * 查询发货确认单中待装车产品列表
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/chooseConfirmPds",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<SSC11315DeliveryConfirmRsBean> chooseConfirmPds(@RequestBody RsRequest<SSC11315Param> param){
        logger.info("查询发货确认单待装车产品列表");
        RsResponse<SSC11315DeliveryConfirmRsBean> resultRsResponse=new RsResponse<>();
        List<SSC11315DeliveryConfirmDetailRsBean> result =this.ssc11315Logic.findList(param.getParam());
        SSC11315DeliveryConfirmRsBean response = new SSC11315DeliveryConfirmRsBean();
        response.setDeliveryConfirmDetailist(result);
        if(result != null){
            resultRsResponse.setResult(response);
            resultRsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
            resultRsResponse.setMessage("处理成功");
        }else {
            resultRsResponse.setStatus(SystemConst.RsStatus.FAIL);
            resultRsResponse.setMessage("数据不存在");
        }
        return resultRsResponse;
    }

    /**
     * 查询或插入发货预入库单、发货预入库产品信息
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/insertDeliveryPreInto", method = RequestMethod.POST)
    public RsResponse<SSC11315DeliveryConfirmRsBean> insertDeliveryPreInto(@RequestBody RsRequest<SSC11315Param> param){
        logger.info("查询或插入发货预入库单、发货预入库产品信息");
        RsResponse<SSC11315DeliveryConfirmRsBean> resultRsResponse = new RsResponse<SSC11315DeliveryConfirmRsBean>();
        int flag=0;
        List<SSC11315DeliveryConfirmDetailRsBean> rs=this.ssc11315Logic.checkPdPlanBox(param.getParam());
        for(int i=0;i<rs.size();i++){
            for(int j=0;j<param.getParam().getProductPlanBoxes().length;j++){
                logger.info(rs.get(i).getPdCode());
                logger.info(param.getParam().getProductPlanBoxes()[j]);
                if(rs.get(i).getPdCode().equals(param.getParam().getPdCodes()[j])){
                    if(rs.get(i).getSu().intValue()<Integer.parseInt(param.getParam().getProductPlanBoxes()[j])){
                        flag=1;
                        break;
                    }
                }
            }
        }
        if(flag==1){
            throw new BusinessException("当前数据发生冲突,请重新加载数据进行修改");
        }
        SSC11315DeliveryConfirmRsBean result = this.ssc11315Logic.insertDeliveryPreInto(param.getParam());
        if(result != null){
            resultRsResponse.setResult(result);
            resultRsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
            resultRsResponse.setMessage("插入发货预入库数据成功");
        }else {
            resultRsResponse.setStatus(SystemConst.RsStatus.FAIL);
            resultRsResponse.setMessage("插入发货预入库数据失败");
        }
        return resultRsResponse;
    }

    /**
     * 检查确认单产品是否全部装车，从而判断是否可以再生成预入库单
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/checkPdPlanBox",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<Integer> checkPdPlanBox(@RequestBody RsRequest<SSC11315Param> param) {
        logger.info("check确认单产品是否全部装车，从而判断是否可以再生成预入库单");
        RsResponse<Integer> response=new RsResponse<>();
        List<SSC11315DeliveryConfirmDetailRsBean> rs=this.ssc11315Logic.checkPdPlanBox(param.getParam());
        Integer count =0;
        if(rs!=null){
            for(int i=0;i<rs.size();i++){
                count+=rs.get(i).getSu();
            }
        }
        if(count == 0){
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("该确认单的预入库单已经全部生成！");
        }else {
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("处理成功");
        }
        return response;
    }
}


