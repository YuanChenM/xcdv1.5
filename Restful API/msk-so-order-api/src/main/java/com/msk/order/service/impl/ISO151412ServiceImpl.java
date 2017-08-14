package com.msk.order.service.impl;

import com.msk.common.data.jpa.condition.BaseOperatorEnum;
import com.msk.common.data.jpa.condition.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.common.exception.BusinessException;
import com.msk.common.constant.NumberConstant;
import com.msk.common.constant.SystemConstant;
import com.msk.common.constant.business.OrderCodeMasterDef;
import com.msk.common.data.jpa.BaseRepository;
import com.msk.common.utils.DateTimeUtil;
import com.msk.common.utils.StringUtil;
import com.msk.order.bean.param.ISO151412RestParam;
import com.msk.order.bean.result.ISO151412RestResult;
import com.msk.order.entity.SoOrder;
import com.msk.order.repository.SoOrderRepository;
import com.msk.order.service.BaseService;
import com.msk.order.service.ISO151412Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 订单删除或恢复
 * zhangqiang1
 */
@Service
public class ISO151412ServiceImpl extends BaseService<SoOrder, Long> implements ISO151412Service {


    @Autowired
    private SoOrderRepository orderRepository;


    @Override
    public BaseRepository getRepository() {
        return this.orderRepository;
    }

    /**
     * 1.订单状态检查，只有完成的订单(状态为全部取消和全部收货)才可以删除与恢复。
     * 其他状态时，returnCode=F000002，message="{0}"状态的订单不能进行删除操作。
     * <p/>
     * 只有删除的订单(DUSTBIN_FLG=1)才可以彻底删除。
     * 其他状态时，returnCode=F000003，message=只有回收站中的订单才能被彻底删除。
     * <p/>
     * 2.操作类型为删除时，
     * 更新订单主表的删除标志为删除
     * <p/>
     * 3.操作类型为恢复时，
     * 更新订单主表的删除标志为未删除
     *
     * @param param
     * @return
     */
    @Override
    @Transactional
    public RestResponse<ISO151412RestResult> setOrderDelOrRecover(RestRequest<ISO151412RestParam> param) {
        String loginId = param.getLoginId();
        RestResponse<ISO151412RestResult> response = new RestResponse<ISO151412RestResult>();
        ISO151412RestResult result = new ISO151412RestResult();
        ISO151412RestParam rsParam = param.getParam();
        Long orderId = rsParam.getOrderId();
        Integer ver = rsParam.getVer();
        Integer operateType = rsParam.getOperateType();
        SoOrder updatedOrder = null;
        if (operateType == NumberConstant.IntDef.INT_ZERO) { // 删除
            Filter<SoOrder> filter = new Filter<>();
            filter.add("orderId", BaseOperatorEnum.EQUAL, orderId);
            filter.add("ver", BaseOperatorEnum.EQUAL, ver);
            filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
            SoOrder soOrder = this.findOne(filter);
            if (soOrder == null) {
                throw new BusinessException("根据条件找不到对应的对应，传入参数为  orderId :" + rsParam.getOrderId() + " ver:" + rsParam.getVer());
            }
            Integer orderStatus = soOrder.getOrderStatus();
            if (orderStatus == OrderCodeMasterDef.OrderStatus.CANCEL || orderStatus == OrderCodeMasterDef.OrderStatus.ALL_RECEIPT) {
                updatedOrder = this.updateSoOrder(soOrder, SystemConstant.DelFlg.ENABLE, loginId, SystemConstant.DelFlg.DISABLE);// dustbinFlg=1
                response.setStatus(SystemConstant.RsStatus.SUCCESS);
            } else {
                String returnCode = "F000002";
                String message = "订单不能进行删除操作";
                response = this.setRestResponse(response, returnCode, message, SystemConstant.RsStatus.FAIL);
            }
        } else if (operateType == NumberConstant.IntDef.INT_ONE) { //恢复
            Filter<SoOrder> delFilter = new Filter<>();
            delFilter.add("orderId", BaseOperatorEnum.EQUAL, orderId);
            delFilter.add("ver", BaseOperatorEnum.EQUAL, ver);
            delFilter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
            SoOrder deletedOrder = this.findOne(delFilter);
            if (!(deletedOrder != null&&SystemConstant.DelFlg.DISABLE.equals(deletedOrder.getDustbinFlg()))) {
                throw new BusinessException("该订单不能恢复！");
            }
            Integer orderStatus = deletedOrder.getOrderStatus();
            if (orderStatus == OrderCodeMasterDef.OrderStatus.CANCEL || orderStatus == OrderCodeMasterDef.OrderStatus.ALL_RECEIPT) {
                updatedOrder = this.updateSoOrder(deletedOrder, SystemConstant.DelFlg.ENABLE, loginId,SystemConstant.DelFlg.ENABLE);// dustbinFlg=0
                response.setStatus(SystemConstant.RsStatus.SUCCESS);
            } else {
                String returnCode = "F000002";
                String message = "订单不能进行恢复";
                response = this.setRestResponse(response, returnCode, message, SystemConstant.RsStatus.FAIL);
            }
        } else if (operateType == NumberConstant.IntDef.INT_TWO) {// 彻底删除
            Filter<SoOrder> delFilter = new Filter<>();
            delFilter.add("orderId", BaseOperatorEnum.EQUAL, orderId);
            delFilter.add("ver", BaseOperatorEnum.EQUAL, ver);
            delFilter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
            SoOrder deletedOrder = this.findOne(delFilter);
            if (deletedOrder == null) {
                throw new BusinessException("该订单不存在！");
            }
            String dustbinFlg = deletedOrder.getDustbinFlg();
            if (SystemConstant.DelFlg.DISABLE.equals(dustbinFlg)) {
                updatedOrder = this.updateSoOrder(deletedOrder, SystemConstant.DelFlg.DISABLE, loginId, SystemConstant.DelFlg.ENABLE);
                response.setStatus(SystemConstant.RsStatus.SUCCESS);
            } else {
                String returnCode = "F000003";
                String message = "只有回收站中的订单才能被彻底删除";
                response = this.setRestResponse(response, returnCode, message, SystemConstant.RsStatus.FAIL);
            }
        } else {
            throw new BusinessException("没有该操作类型 operateType：" + operateType);
        }
        if (updatedOrder != null) {
            result = this.getRsResult(result, updatedOrder);
        }
        response.setResult(result);
        return response;
    }

    /**
     * 设置RestResponse 返回信息
     *
     * @param response
     * @param returnCode
     * @param message
     * @return
     */
    public RestResponse<ISO151412RestResult> setRestResponse(RestResponse<ISO151412RestResult> response, String returnCode, String message, String rsStatus) {
        response.setReturnCode(returnCode);
        response.setMessage(message);
        response.setStatus(rsStatus);
        return response;
    }

    /**
     * 更新订单主表
     *
     * @return
     */
    public SoOrder updateSoOrder(SoOrder soOrder, String delFlg, String updateId, String dustbinFlg) {
        soOrder.setDelFlg(delFlg);
        soOrder.setVer(soOrder.getVer() + NumberConstant.IntDef.INT_ONE);
        soOrder.setUpdId(updateId);
        soOrder.setDustbinFlg(dustbinFlg);
        soOrder.setUpdTime(DateTimeUtil.getCustomerDate());
        SoOrder updatedOrder = this.orderRepository.save(soOrder);
        return updatedOrder;
    }


    /**
     * 设置ISO151412RsResult 相关参数
     *
     * @param result
     * @param updatedOrder
     * @return
     */
    public ISO151412RestResult getRsResult(ISO151412RestResult result, SoOrder updatedOrder) {
        result.setOrderId(updatedOrder.getOrderId());
        result.setOrderCode(updatedOrder.getOrderCode());
        result.setOrderStatus(updatedOrder.getOrderStatus());
        result.setDelFlg(Integer.parseInt(updatedOrder.getDelFlg()));
        result.setVer(updatedOrder.getVer());
        result.setUpdTime(updatedOrder.getUpdTime());
        return result;
    }


}
