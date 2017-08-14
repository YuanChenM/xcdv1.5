package com.msk.product.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;

/**
 * Created by gao_min on 2016/10/11.
 */
public class IPD1411212Validator extends BaseValidator<RsRequest<String>> {

    @Override
    public void validatorData(RsRequest<String> entity) {
        this.validatorRequired("身份识别码", entity.getAuth());
        this.validatorRequired("平台编码", entity.getSiteCode());
    }
}
