package com.msk.order.validator;

import com.msk.common.bean.ExceptionMessage;
import com.msk.common.validation.BaseCustomValidation;
import com.msk.order.bean.param.ISO151415OrderReceiptParam;
import com.msk.order.bean.result.OrderShipInfo;
import com.msk.order.bean.result.OrderShipProductInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wang_shuai on 2016/9/13.
 */
@Service
public class ISO151415RestReceiptValidator extends BaseCustomValidation {
    @Override
    public List<ExceptionMessage> validation(Object entity) {
        List<ExceptionMessage> exceptionMessages = new ArrayList<>();
        ISO151415OrderReceiptParam param = (ISO151415OrderReceiptParam) entity;
        if(!this.validatorRequired(param.getShipId())){
            exceptionMessages.add(new ExceptionMessage("发货单ID不能为空"));
        }

        if(!this.validatorRequired(param.getVer())){
            exceptionMessages.add(new ExceptionMessage("版本号不能为空"));
        }

        if (CollectionUtils.isNotEmpty(param.getShipList())){
            for(OrderShipInfo orderShipInfo : param.getShipList()){
                if(!this.validatorRequired(orderShipInfo.getDeliverCode())){
                    exceptionMessages.add(new ExceptionMessage("配送单号不能为空"));
                }
                if(!this.validatorRequired(orderShipInfo.getActualReceiveDate())){
                    exceptionMessages.add(new ExceptionMessage("实际到货日期不能为空"));
                }
                if (CollectionUtils.isNotEmpty(orderShipInfo.getProductList())){
                    for (OrderShipProductInfo orderShipProductInfo : orderShipInfo.getProductList()){
                        if(!this.validatorRequired(orderShipProductInfo.getShipDetailId())){
                            exceptionMessages.add(new ExceptionMessage("发货明细ID不能为空"));
                        }
                        if(!this.validatorRequired(orderShipProductInfo.getSkuCode())){
                            exceptionMessages.add(new ExceptionMessage("SKU编码不能为空"));
                        }
                        if(!this.validatorRequired(orderShipProductInfo.getReceiveQty())){
                            exceptionMessages.add(new ExceptionMessage("收货数量不能为空"));
                        }
                    }
                }
            }

        }
        return exceptionMessages;
    }
}
