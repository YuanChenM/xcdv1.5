package com.msk.order.validator;

import com.msk.common.bean.ExceptionMessage;
import com.msk.common.validation.BaseCustomValidation;
import com.msk.order.bean.param.ISO151801RestParam;
import com.msk.order.bean.param.ISO151801RestProductParam;
import com.msk.order.bean.param.ISO151801RestShipParam;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ISO151801Validator_迟收退货数据接收接口
 * Created by sun_jiaju on 2016/9/12.
 */

@Service
public class ISO151801RestValidator extends BaseCustomValidation {
    public List<ExceptionMessage> validation(Object entity) {
        List<ExceptionMessage> exceptionMessages = new ArrayList<>();
        ISO151801RestParam param = (ISO151801RestParam) entity;
        if (!this.validatorRequired(param.getShipId())) {
            exceptionMessages.add(new ExceptionMessage("发货单ID不能为空"));
        }
        if (!this.validatorRequired(param.getReceiptDate())) {
            exceptionMessages.add(new ExceptionMessage("迟收再发送日不能为空"));
        }
        if (!this.validatorRequired(param.getReturnMode())) {
            exceptionMessages.add(new ExceptionMessage("迟收模式不能为空"));
        }
        if (!this.validatorRequired(param.getApplyMan())) {
            exceptionMessages.add(new ExceptionMessage("申请人名称不能为空"));
        }
        if (!this.validatorRequired(param.getApplyTime())) {
            exceptionMessages.add(new ExceptionMessage("申请时间不能为空"));
        }
        if (!this.validatorRequired(param.getReturnReasonID())) {
            exceptionMessages.add(new ExceptionMessage("迟收原因ID不能为空"));
        }
        if (!this.validatorRequired(param.getReturnReasonName())) {
            exceptionMessages.add(new ExceptionMessage("迟收原因名称不能为空"));
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
            for (ISO151801RestShipParam shipParam : param.getShipList()) {
                if (!this.validatorRequired(shipParam.getDeliverCode())) {
                    exceptionMessages.add(new ExceptionMessage("配送单号不能为空"));
                }
                if (CollectionUtils.isNotEmpty(shipParam.getProductList())) {
                    for (ISO151801RestProductParam productParam : shipParam.getProductList()) {
                        if (!this.validatorRequired(productParam.getShipDetailId())) {
                            exceptionMessages.add(new ExceptionMessage("发货明细ID不能为空"));
                        }
                        if (!this.validatorRequired(productParam.getSkuCode())) {
                            exceptionMessages.add(new ExceptionMessage("SKU编码不能为空"));
                        }
                        if (!this.validatorRequired(productParam.getReturnQty())) {
                            exceptionMessages.add(new ExceptionMessage("迟收数量不能为空"));
                        }
                    }
                } else {
                    exceptionMessages.add(new ExceptionMessage("迟收产品信息不能为空"));
                }
            }
        }
        return exceptionMessages;
    }
}
