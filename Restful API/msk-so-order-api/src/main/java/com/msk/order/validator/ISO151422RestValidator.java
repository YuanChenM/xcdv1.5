package com.msk.order.validator;

import com.msk.common.bean.ExceptionMessage;
import com.msk.common.validation.BaseCustomValidation;
import com.msk.order.bean.param.ISO151422ProductRestParam;
import com.msk.order.bean.param.ISO151422RestParam;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ISO151422Validator_退货入库接口
 * Created by wang_jianzhou on 2016/9/12.
 */
@Service
public class ISO151422RestValidator extends BaseCustomValidation {

    public List<ExceptionMessage> validation(Object entity) {
        List<ExceptionMessage> exceptionMessages = new ArrayList<>();
        ISO151422RestParam param = (ISO151422RestParam) entity;
        if(!this.validatorRequired(param.getReturnId())){
            exceptionMessages.add(new ExceptionMessage("退货单ID不能为空"));
        }

        if(!this.validatorRequired(param.getInboundTime())){
            exceptionMessages.add(new ExceptionMessage("退货入库时间不能为空"));
        }

        if(!this.validatorRequired(param.getInboundManId())){
            exceptionMessages.add(new ExceptionMessage("入库人ID不能为空"));
        }

        if(!this.validatorRequired(param.getInboundManName())){
            exceptionMessages.add(new ExceptionMessage("入库人名称不能为空"));
        }

        if(CollectionUtils.isNotEmpty(param.getProductList())){
            for(ISO151422ProductRestParam productRestParam : param.getProductList()){
                if(!this.validatorRequired(productRestParam.getDetailId())){
                    exceptionMessages.add(new ExceptionMessage("退货明细ID不能为空"));
                }

                if(!this.validatorRequired(productRestParam.getInboundBatch())){
                    exceptionMessages.add(new ExceptionMessage("退货入库批次号不能为空"));
                }

                if(!this.validatorRequired(productRestParam.getSkuCode())){
                    exceptionMessages.add(new ExceptionMessage("产品货号不能为空"));
                }

                if(!this.validatorRequired(productRestParam.getInboundQty())){
                    exceptionMessages.add(new ExceptionMessage("入库数量不能为空"));
                }
            }
        }else {
            exceptionMessages.add(new ExceptionMessage("退货产品列表不能为空"));
        }
        return exceptionMessages;
    }
}
