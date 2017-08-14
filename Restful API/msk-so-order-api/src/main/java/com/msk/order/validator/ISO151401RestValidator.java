package com.msk.order.validator;

import com.msk.common.bean.ExceptionMessage;
import com.msk.common.validation.BaseCustomValidation;
import com.msk.order.bean.param.ISO151401ProductRestParam;
import com.msk.order.bean.param.ISO151401RestParam;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ISO151401Validator_创建意愿订单接口
 * Created by wang_jianzhou on 2016/9/12.
 */
@Service
public class ISO151401RestValidator extends BaseCustomValidation {
    public List<ExceptionMessage> validation(Object entity) {
        List<ExceptionMessage> exceptionMessages = new ArrayList<>();
        ISO151401RestParam param = (ISO151401RestParam) entity;
        if(!this.validatorRequired(param.getDistrictCode())){
            exceptionMessages.add(new ExceptionMessage("区域不能为空"));
        }

        if(!this.validatorRequired(param.getBuyersId())){
            exceptionMessages.add(new ExceptionMessage("买家ID不能为空"));
        }

        if(!this.validatorRequired(param.getBuyersCode())){
            exceptionMessages.add(new ExceptionMessage("买家编码不能为空"));
        }

        if(!this.validatorRequired(param.getBuyersType())){
            exceptionMessages.add(new ExceptionMessage("买家类型不能为空"));
        }

        if(!this.validatorRequired(param.getBuyersName())){
            exceptionMessages.add(new ExceptionMessage("买家名称不能为空"));
        }

        if(!this.validatorRequired(param.getSellerCode())){
            exceptionMessages.add(new ExceptionMessage("卖家编码不能为空"));
        }

        if(!this.validatorRequired(param.getSellerName())){
            exceptionMessages.add(new ExceptionMessage("买家名称不能为空"));
        }

        if (CollectionUtils.isNotEmpty(param.getProducts())){
            for(ISO151401ProductRestParam productRestParam : param.getProducts()){
                if(!this.validatorRequired(productRestParam.getPdCode())){
                    exceptionMessages.add(new ExceptionMessage("产品编码不能为空"));
                }
                if(!this.validatorRequired(productRestParam.getPdName())){
                    exceptionMessages.add(new ExceptionMessage("产品名称不能为空"));
                }
            }
        }
        return exceptionMessages;
    }
}
