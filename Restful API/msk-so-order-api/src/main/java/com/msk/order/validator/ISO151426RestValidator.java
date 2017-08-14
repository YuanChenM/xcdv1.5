package com.msk.order.validator;

import com.msk.common.bean.ExceptionMessage;
import com.msk.common.validation.BaseCustomValidation;
import com.msk.order.bean.param.ISO151426RestParam;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wang_shuai on 2016/9/13.
 */
@Service
public class ISO151426RestValidator extends BaseCustomValidation {
    @Override
    public List<ExceptionMessage> validation(Object entity) {
        List<ExceptionMessage> exceptionMessages = new ArrayList<>();
        ISO151426RestParam param = (ISO151426RestParam) entity;
        if(!this.validatorRequired(param.getHousekeepingId())){
            exceptionMessages.add(new ExceptionMessage("管家ID不能为空"));
        }

        return exceptionMessages;
    }
}
