package com.msk.order.validator;

import com.msk.common.bean.ExceptionMessage;
import com.msk.common.validation.BaseCustomValidation;
import com.msk.order.bean.param.ISO151428RestParam;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wang_shuai on 2016/9/14.
 */
@Service
public class ISO151428RestValidator extends BaseCustomValidation {
    @Override
    public List<ExceptionMessage> validation(Object entity) {
        List<ExceptionMessage> exceptionMessages = new ArrayList<>();
        ISO151428RestParam param = (ISO151428RestParam) entity;
        if(!this.validatorRequired(param.getPdCode())){
            exceptionMessages.add(new ExceptionMessage("产品编码不能为空"));
        }

        return exceptionMessages;
    }
}
