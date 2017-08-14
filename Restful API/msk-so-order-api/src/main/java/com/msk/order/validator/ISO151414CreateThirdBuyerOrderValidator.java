package com.msk.order.validator;

import com.msk.common.bean.ExceptionMessage;
import com.msk.common.constant.NumberConstant;
import com.msk.common.validation.BaseCustomValidation;
import com.msk.order.bean.param.ISO151414BaseOrderParam;
import com.msk.order.bean.param.ISO151414OrderDetailParam;
import org.apache.commons.collections.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liutao on 2016/9/18.
 */
public class ISO151414CreateThirdBuyerOrderValidator extends BaseCustomValidation {
    @Override
    public List<ExceptionMessage> validation(Object entity) {
        ISO151414BaseOrderParam param = (ISO151414BaseOrderParam)entity;
        List<ExceptionMessage> exceptionMessages = new ArrayList<>();
        if(!this.validatorRequired(param.getDistrictCode())){
            exceptionMessages.add(new ExceptionMessage("物流区域编码不能为空"));
        }
        if(!this.validatorNumber(String.valueOf(param.getBuyersType()))){
            exceptionMessages.add(new ExceptionMessage("买家类别不能为空"));
        }
        if(param.getBuyersType() <= NumberConstant.IntDef.INT_ZERO){
            exceptionMessages.add(new ExceptionMessage("买家类别传入值错误"));
        }
        if(!this.validatorRequired(param.getSellerCode())){
            exceptionMessages.add(new ExceptionMessage("卖家编码不能为空"));
        }
        if(!this.validatorRequired(param.getSellerName())){
            exceptionMessages.add(new ExceptionMessage("卖家名称不能为空"));
        }
        if(!this.validatorRequired(param.getOrderAmount())){
            exceptionMessages.add(new ExceptionMessage("订单总金额不能为空"));
        }
        if(param.getOrderAmount().compareTo(BigDecimal.ZERO) < NumberConstant.IntDef.INT_ZERO){
            exceptionMessages.add(new ExceptionMessage("订单总金额传入值错误"));
        }
        if(!this.validatorRequired(param.getPaymentType())){
            exceptionMessages.add(new ExceptionMessage("付款类型不能为空"));
        }
        if(!this.validatorRequired(param.getReceiverInfo())){
            exceptionMessages.add(new ExceptionMessage("收货人信息不能为空"));
        }
        if(!this.validatorRequired(param.getReceiverInfo().getReceiverName())){
            exceptionMessages.add(new ExceptionMessage("收货人名称不能为空"));
        }
        if(!this.validatorRequired(param.getReceiverInfo().getReceiverTel())){
            exceptionMessages.add(new ExceptionMessage("收货人电话不能为空"));
        }
        if(!this.validatorRequired(param.getReceiverInfo().getReceiverProvince())){
            exceptionMessages.add(new ExceptionMessage("收货地址省份不能为空"));
        }
        if(!this.validatorRequired(param.getReceiverInfo().getReceiverCity())){
            exceptionMessages.add(new ExceptionMessage("收货地址市不能为空"));
        }
        if(!this.validatorRequired(param.getReceiverInfo().getReceiverDistrict())){
            exceptionMessages.add(new ExceptionMessage("收货地址区不能为空"));
        }
        if(!this.validatorRequired(param.getReceiverInfo().getReceiverAddr())){
            exceptionMessages.add(new ExceptionMessage("收货人详细地址不能为空"));
        }
        if(CollectionUtils.isEmpty(param.getProducts())){
            exceptionMessages.add(new ExceptionMessage("订单详细信息不能为空"));
        }
        for (ISO151414OrderDetailParam detailParam : param.getProducts()){
            if(!this.validatorRequired(detailParam.getPdCode())){
                exceptionMessages.add(new ExceptionMessage("产品编码不能为空"));
            }
            if(!this.validatorRequired(detailParam.getPdName())){
                exceptionMessages.add(new ExceptionMessage("产品名称不能为空"));
            }
            if(!this.validatorRequired(detailParam.getOrderPrice())){
                exceptionMessages.add(new ExceptionMessage("订单价格不能为空"));
            }
            if(detailParam.getOrderPrice().compareTo(BigDecimal.ZERO) < NumberConstant.IntDef.INT_ZERO){
                exceptionMessages.add(new ExceptionMessage("订单价格传入值错误"));
            }
            if(!this.validatorRequired(detailParam.getOrderQty())){
                exceptionMessages.add(new ExceptionMessage("订单数量不能为空"));
            }
            if(detailParam.getOrderQty().compareTo(BigDecimal.ZERO) < NumberConstant.IntDef.INT_ZERO){
                exceptionMessages.add(new ExceptionMessage("订单数量传入值错误"));
            }
        }
        return exceptionMessages;
    }
}
