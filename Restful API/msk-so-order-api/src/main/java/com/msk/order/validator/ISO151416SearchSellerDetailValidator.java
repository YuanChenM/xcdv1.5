package com.msk.order.validator;

import com.msk.common.bean.ExceptionMessage;
import com.msk.common.validation.BaseCustomValidation;
import com.msk.order.bean.param.ISO151416OrderSearchParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liutao on 2016/9/18.
 */
public class ISO151416SearchSellerDetailValidator extends BaseCustomValidation {
    @Override
    public List<ExceptionMessage> validation(Object entity) {
        ISO151416OrderSearchParam param = (ISO151416OrderSearchParam)entity;
        List<ExceptionMessage> exceptionMessages = new ArrayList<>();
        if(!this.validatorRequired(param.getSellerCode())){
            exceptionMessages.add(new ExceptionMessage("卖家编码不能为空"));
        }
        return exceptionMessages;
    }
}
