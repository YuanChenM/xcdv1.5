package com.msk.order.validator;

import com.msk.common.bean.ExceptionMessage;
import com.msk.common.utils.DateTimeUtil;
import com.msk.common.utils.StringUtil;
import com.msk.common.validation.BaseCustomValidation;
import com.msk.order.bean.param.ISO151433RestParam;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wang_shuai on 2016/9/13.
 */
@Service
public class ISO151433RestValidator extends BaseCustomValidation {
    @Override
    public List<ExceptionMessage> validation(Object entity) {
        List<ExceptionMessage> exceptionMessages = new ArrayList<>();
        ISO151433RestParam param = (ISO151433RestParam) entity;
        if(!this.validatorRequired(param.getShipId())){
            exceptionMessages.add(new ExceptionMessage("发货单ID不能为空"));
        }

        if(!this.validatorRequired(param.getCancelTime())){
            exceptionMessages.add(new ExceptionMessage("取消时间不能为空"));
        }
        if (!StringUtil.isEmpty(param.getCancelTime())){
            Date cancelTime = DateTimeUtil.parseDate(param.getCancelTime(), DateTimeUtil.FORMAT_DATE_YYYYMMDD  + " HH:mm:ss");
            if(cancelTime == null) {
                exceptionMessages.add(new ExceptionMessage("取消时间必须为yyyy-MM-dd HH:mm:ss格式的正确时间"));
            }
        }

        return exceptionMessages;
    }
}
