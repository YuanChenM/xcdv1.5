package com.msk.seller.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.seller.bean.ISL231167RsParam;

/**
 * Created by cx on 2016/2/24.
 */
public class ISL231167Validator extends BaseValidator<RsRequest<ISL231167RsParam>> {

    @Override
    public void validatorData(RsRequest<ISL231167RsParam> entity) {
        ISL231167RsParam iSL231167RsParam = entity.getParam();
        if (null != iSL231167RsParam) {
            this.validatorRequired("卖家编码", iSL231167RsParam.getSlCode());
        }
    }
}


