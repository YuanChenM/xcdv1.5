package com.msk.order.validator;

import com.msk.common.bean.ExceptionMessage;
import com.msk.common.validation.BaseCustomValidation;
import com.msk.order.bean.param.ISO151427DeliverParam;
import com.msk.order.bean.param.ISO151427RestParam;
import com.msk.order.bean.param.ISO151427SoReturnParam;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wang_shuai on 2016/9/14.
 */
@Service
public class ISO151427RestValidator extends BaseCustomValidation {
    @Override
    public List<ExceptionMessage> validation(Object entity) {
        List<ExceptionMessage> exceptionMessages = new ArrayList<>();
        ISO151427RestParam param = (ISO151427RestParam) entity;
        if(!this.validatorRequired(param.getSettlementStatus())){
            exceptionMessages.add(new ExceptionMessage("结算订单状态不能为空"));
        }

        if(!this.validatorRequired(param.getSellerId())){
            exceptionMessages.add(new ExceptionMessage("卖家ID不能为空"));
        }

        if (param.getSettlementStatus() != null && param.getSettlementStatus().equals("1")){
            if (CollectionUtils.isNotEmpty(param.getDeliverList())){
                for (ISO151427DeliverParam iso151427Deliver : param.getDeliverList()){
                    if(!this.validatorRequired(iso151427Deliver.getDeliverCode())){
                        exceptionMessages.add(new ExceptionMessage("配送单编码不能为空"));
                    }
                }
            }
        }
        if (param.getSettlementStatus() != null && param.getSettlementStatus().equals("2")){
            if (CollectionUtils.isNotEmpty(param.getReturnList())){
                for (ISO151427SoReturnParam iso151427SoReturn : param.getReturnList()){
                    if(!this.validatorRequired(iso151427SoReturn.getReturnCode())){
                        exceptionMessages.add(new ExceptionMessage("退货单编码不能为空"));
                    }
                }
            }
        }
        if (param.getSettlementStatus() != null && param.getSettlementStatus().equals("0")){
            if (CollectionUtils.isNotEmpty(param.getReturnList()) && CollectionUtils.isNotEmpty(param.getDeliverList())){
                for (ISO151427SoReturnParam iso151427SoReturn : param.getReturnList()){
                    if(!this.validatorRequired(iso151427SoReturn.getReturnCode())){
                        exceptionMessages.add(new ExceptionMessage("退货单编码不能为空"));
                    }
                }
                for (ISO151427DeliverParam iso151427Deliver : param.getDeliverList()){
                    if(!this.validatorRequired(iso151427Deliver.getDeliverCode())){
                        exceptionMessages.add(new ExceptionMessage("配送单编码不能为空"));
                    }
                }
            }
        }
        return exceptionMessages;
    }
}
