package com.msk.stock.validator;


import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.stock.bean.StockRsParamList;

/**
 * @author zhang_qiang1
 */
public class ISO151431Validator extends BaseValidator<RsRequest<StockRsParamList>> {
    @Override
    public void validatorData(RsRequest<StockRsParamList> request) {
        StockRsParamList iso151431RsParam=request.getParam();
        this.validatorRequired("参数不能为空",iso151431RsParam);

    }


}


