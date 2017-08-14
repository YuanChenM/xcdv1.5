package com.msk.stock.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.stock.bean.ISO151421RsParam;

/**
 * ISO151421Validator.
 *
 * @author pxg
 */
public class ISO151421Validator extends BaseValidator<RsRequest<ISO151421RsParam>> {
    @Override
    public void validatorData(RsRequest<ISO151421RsParam> request) {
        ISO151421RsParam iSO151421RsParam = request.getParam();
        if(null!=iSO151421RsParam){
            this.validatorRequired("供应商Code", iSO151421RsParam.getSupplierCode());
            this.validatorRequired("订单区域编码", iSO151421RsParam.getDistrictCode());
        }
    }
}
