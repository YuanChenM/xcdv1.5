package com.msk.order.validator;

import com.msk.common.bean.ExceptionMessage;
import com.msk.common.validation.BaseCustomValidation;
import com.msk.order.bean.param.ISO151405PdCodeListRestParam;
import com.msk.order.bean.param.ISO151405RestParam;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ISO151405RestValidator_产品销量查询接口
 * Created by wang_jianzhou on 2016/9/12.
 */
@Service
public class ISO151405RestValidator extends BaseCustomValidation {
    public List<ExceptionMessage> validation(Object entity) {
        List<ExceptionMessage> exceptionMessages = new ArrayList<>();
        ISO151405RestParam param = (ISO151405RestParam) entity;
        if(CollectionUtils.isNotEmpty(param.getPdCodeList())){
            for(ISO151405PdCodeListRestParam pdCodeListRestParam : param.getPdCodeList()){
                if(!this.validatorRequired(pdCodeListRestParam.getPdCode())){
                    exceptionMessages.add(new ExceptionMessage("产品编码不能为空"));
                }
            }
        }else {
            exceptionMessages.add(new ExceptionMessage("产品编码列表不能为空"));
        }
        return exceptionMessages;
    }
}
