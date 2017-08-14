package com.msk.seller.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.seller.bean.ISL231153RsParam;

/**
 * Created by cx on 2016/2/23.
 */
public class ISL231153Validator extends BaseValidator<RsRequest<ISL231153RsParam>> {

    @Override
    public void validatorData(RsRequest<ISL231153RsParam> entity) {
        ISL231153RsParam iSL231153RsParam = entity.getParam();
        if (null != iSL231153RsParam) {
            this.validatorRequired("卖家编码",iSL231153RsParam.getSlCode());
        }
    }
}



