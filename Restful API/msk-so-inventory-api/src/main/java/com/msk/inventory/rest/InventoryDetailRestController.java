package com.msk.inventory.rest;

import com.msk.comm.bean.RestRequest;
import com.msk.comm.bean.RestResponse;
import com.msk.common.constant.business.InventoryCodeMasterDef;
import com.msk.inventory.bean.IvmInventoryDetailBean;
import com.msk.inventory.service.IInventoryDetailService;
import com.msk.inventory.service.IInboundService;
import com.msk.inventory.service.IOutboundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangfan on 16/8/15.
 */
@RestController
@RequestMapping("api")
public class InventoryDetailRestController {
    @Autowired
    private IInventoryDetailService inventoryDetailService;

    @Autowired
    private IInboundService inboundIvDetailService;

    @Autowired
    private IOutboundService outboundIvDetailService;

    /**
     * 保存一条库存明细信息数据
     * 
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/inventory/insertOneInverDetail",
        method = RequestMethod.POST)
    public RestResponse<IvmInventoryDetailBean> insertOneInverDetail(
        @RequestBody RestRequest<IvmInventoryDetailBean> requestBody) {
        inventoryDetailService.insertOneInventoryDetail(requestBody.getParam());
        return null;
    }

    /**
     * 整作业单收货
     *
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/inventory/receiveByInbound",
            method = RequestMethod.POST)
    public RestResponse<IvmInventoryDetailBean> receiveByInbound(
            @RequestBody RestRequest<IvmInventoryDetailBean> requestBody) {
        inventoryDetailService.receiveByInbound(requestBody.getParam());
        return null;
    }

    /**
     * 根据明细数量收货
     *
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/inventory/receiveByInboundDetailQty",
            method = RequestMethod.POST)
    public RestResponse<IvmInventoryDetailBean> receiveByInboundDetailQty(
            @RequestBody RestRequest<IvmInventoryDetailBean> requestBody) {
        IvmInventoryDetailBean param = requestBody.getParam();
        inventoryDetailService.receiveByInboundDetailAndQty(param.getInboundId(), param.getInboundDetailId(), param.getQty());
        return null;
    }

    /**
     * 整作业单上架
     *
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/inventory/putawayByInbound",
            method = RequestMethod.POST)
    public RestResponse<IvmInventoryDetailBean> putawayByInbound(
            @RequestBody RestRequest<IvmInventoryDetailBean> requestBody) {
        inventoryDetailService.putawayByInbound(requestBody.getParam());
        return null;
    }

    /**
     * 查询库存明细列表数据
     * 
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/inventory/select",
        method = RequestMethod.POST)
    public List<IvmInventoryDetailBean> selectInventoryDetailList(
        @RequestBody RestRequest<IvmInventoryDetailBean> requestBody) {
        List<IvmInventoryDetailBean> inventoryDetailList = new ArrayList<IvmInventoryDetailBean>();
        inventoryDetailList=   inventoryDetailService.selectInventoryDetailList(requestBody.getParam());
        return inventoryDetailList;
    }

    /**
     * 查询库存明细列表总数
     * 
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/inventory/getCount",
        method = RequestMethod.POST)
    public int selectInventoryDetailCount(@RequestBody RestRequest<IvmInventoryDetailBean> requestBody){
        int pageCount= inventoryDetailService.selectInventoryDetailCount(requestBody.getParam());
        return pageCount;
    }

    /**
     * 通过货品身份和身份序列修改对应库存货物标识
     * 
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/inventory/updateInvDetailFlag",
        method = RequestMethod.POST)
    public RestResponse<IvmInventoryDetailBean> updateInvDetailFlag(
        @RequestBody RestRequest<IvmInventoryDetailBean> requestBody) {
        inventoryDetailService.updateIvDetailFlag(requestBody.getParam());
        return null;
    }

    /**
     * 通过货物身份和身份序列切分库存数据
     * 
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/inventory/splitIvnDetail",
        method = RequestMethod.POST)
    public RestResponse<IvmInventoryDetailBean> splitIvnDetail(
        @RequestBody RestRequest<IvmInventoryDetailBean> requestBody) {
        inventoryDetailService.splitIvDetail(requestBody.getParam());
        return null;
    }

    /**
     * 通过入库流水单号删除对应库存信息
     * 
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/inventory/delInvDetailByInboundId",
        method = RequestMethod.POST)
    public RestResponse<IvmInventoryDetailBean> delInvDetailByInboundId(
        @RequestBody RestRequest<IvmInventoryDetailBean> requestBody) {
        inventoryDetailService.delIvDetailByInboundId(requestBody.getParam());
        return null;
    }

    /**
     * 根据出库更新发货状态
     *
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/inventory/dispatchByOutbound",
            method = RequestMethod.POST)
    public RestResponse<IvmInventoryDetailBean> dispatchByOutbound(
            @RequestBody RestRequest<IvmInventoryDetailBean> requestBody) {
        inventoryDetailService.dispatchByOutbound(requestBody.getParam());
        return null;
    }

    /**
     * 通过货物身份和身份序列修改对应库存撤销发送信息
     * 
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/inventory/undoDispatchInvDetail",
        method = RequestMethod.POST)
    public RestResponse<IvmInventoryDetailBean> undoDispatchInvDetail(
        @RequestBody RestRequest<IvmInventoryDetailBean> requestBody) {
        inventoryDetailService.undoDispatchIvDetail(requestBody.getParam());
        return null;
    }

    /**
     * 货品入库逻辑处理业务
     * 
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/inventory/inboundIvDetail",
        method = RequestMethod.POST)
    public RestResponse<IvmInventoryDetailBean> inboundIvDetail(
        @RequestBody RestRequest<IvmInventoryDetailBean> requestBody) {
        try {
            inboundIvDetailService.doInboundForDetail(requestBody.getParam());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 货品出庫逻辑处理业务
     * 
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/inventory/outboundIvDetail",
        method = RequestMethod.POST)
    public String outboundIvDetail(
        @RequestBody RestRequest<IvmInventoryDetailBean> requestBody) {
        IvmInventoryDetailBean condition = new IvmInventoryDetailBean();
        condition.setSkuCode(requestBody.getParam().getSkuCode());
        condition.setPucharseBatch(requestBody.getParam().getPucharseBatch());
        try {
            outboundIvDetailService.doOutboundForDetail(condition, requestBody.getParam().getQty(), requestBody.getParam());
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return null;
    }


    /**
     * 根据condition从库存切数据并且改变状态
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/inventory/updateIvFlagByCondition",method = RequestMethod.POST)
    public RestResponse<IvmInventoryDetailBean> updateInventoryDetailIvFlagByCondition(
            @RequestBody RestRequest<IvmInventoryDetailBean> requestBody){
        IvmInventoryDetailBean condition = new IvmInventoryDetailBean();
        condition.setSkuCode(requestBody.getParam().getSkuCode());
        condition.setPucharseBatch(requestBody.getParam().getPucharseBatch());
        condition.setIvFlag(InventoryCodeMasterDef.GoodType.GT_GOOD+"");

        IvmInventoryDetailBean requrstParam = requestBody.getParam();
        inventoryDetailService.updateIvFlagByCondition(condition,requrstParam.getQty(),requrstParam.getIvFlag(),requrstParam.getFlagCReason(), requestBody.getLoginId());
        return null;
    }

    /**
     * 根据明细数量交付
     *
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/inventory/deliverByOutboundDetailQty",
            method = RequestMethod.POST)
    public RestResponse<IvmInventoryDetailBean> deliverByOutboundDetailQty(
            @RequestBody RestRequest<IvmInventoryDetailBean> requestBody) {
        IvmInventoryDetailBean param = requestBody.getParam();
        inventoryDetailService.deliverByOutboundDetailAndQty(param.getOutboundId(), param.getOutboundNo(), param.getOutboundDetailId(), param.getQty(), requestBody.getLoginId());
        return null;
    }
}
