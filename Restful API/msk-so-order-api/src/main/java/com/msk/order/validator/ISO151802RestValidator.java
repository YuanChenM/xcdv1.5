package com.msk.order.validator;

import com.msk.common.bean.ExceptionMessage;
import com.msk.common.validation.BaseCustomValidation;
import com.msk.order.bean.param.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ISO151802Validator_现场退货数据接收接口
 * Created by sun_jiaju on 2016/9/12.
 */

@Service
public class ISO151802RestValidator extends BaseCustomValidation {
    public List<ExceptionMessage> validation(Object entity) {
        List<ExceptionMessage> exceptionMessages = new ArrayList<>();
        ISO151802RestParam param = (ISO151802RestParam) entity;
        if (!this.validatorRequired(param.getShipId())) {
            exceptionMessages.add(new ExceptionMessage("发货单ID不能为空"));
        }
        if (!this.validatorRequired(param.getReturnMode())) {
            exceptionMessages.add(new ExceptionMessage("退货模式不能为空"));
        }
        if (!this.validatorRequired(param.getApplyMan())) {
            exceptionMessages.add(new ExceptionMessage("申请人名称不能为空"));
        }
        if (!this.validatorRequired(param.getApplyTime())) {
            exceptionMessages.add(new ExceptionMessage("申请时间不能为空"));
        }
        if (!this.validatorRequired(param.getReturnReasonID())) {
            exceptionMessages.add(new ExceptionMessage("退货原因ID不能为空"));
        }
        if (!this.validatorRequired(param.getReturnReasonName())) {
            exceptionMessages.add(new ExceptionMessage("退货原因名称不能为空"));
        }
        if (!this.validatorRequired(param.getBuyerId())) {
            exceptionMessages.add(new ExceptionMessage("买家ID不能为空"));
        }
        if (!this.validatorRequired(param.getBuyerCode())) {
            exceptionMessages.add(new ExceptionMessage("买家编码不能为空"));
        }
        if (!this.validatorRequired(param.getIsPaid())) {
            exceptionMessages.add(new ExceptionMessage("已付款标记不能为空"));
        }
        if (CollectionUtils.isNotEmpty(param.getShipList())) {
            for (ISO151802RestShipParam shipParam : param.getShipList()) {
                if (!this.validatorRequired(shipParam.getDeliverCode())) {
                    exceptionMessages.add(new ExceptionMessage("配送单号不能为空"));
                }
                if (CollectionUtils.isNotEmpty(shipParam.getProductList())) {
                    for (ISO151802RestShipDetailParam detailParam : shipParam.getProductList()) {
                        if (!this.validatorRequired(detailParam.getShipDetailId())) {
                            exceptionMessages.add(new ExceptionMessage("发货明细ID不能为空"));
                        }
                        if (!this.validatorRequired(detailParam.getSkuCode())) {
                            ExceptionMessage message = new ExceptionMessage("SKU编码不能为空");
                            exceptionMessages.add(message);
                        }
                        if (!this.validatorRequired(detailParam.getReturnQty())) {
                            exceptionMessages.add(new ExceptionMessage("退货数量不能为空"));
                        }
                    }
                } else {
                    exceptionMessages.add(new ExceptionMessage("退货产品信息不能为空"));
                }
            }
        }
        return exceptionMessages;
    }
}
