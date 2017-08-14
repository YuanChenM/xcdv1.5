package com.msk.buyers.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.buyers.bean.IBY121104RsParam;
import com.msk.common.bean.RsRequest;

/**
 * IBY121104Validator.
 *
 * @author yang_yang
 */
public class IBY121104Validator extends BaseValidator<RsRequest<IBY121104RsParam>> {
    @Override
    public void validatorData(RsRequest<IBY121104RsParam> request) {
        IBY121104RsParam iby121104RsParam = request.getParam();
        if(null != iby121104RsParam){
            this.validatorRequired("物流区编码", iby121104RsParam.getLgcsCode());
        }else{
            this.validatorRequired("请求参数", request.getParam());
        }
    }
}
