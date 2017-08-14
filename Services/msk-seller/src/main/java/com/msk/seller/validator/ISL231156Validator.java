package com.msk.seller.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.seller.bean.ISL231155RsParam;

/**
 * Created by cx on 2016/2/23.
 */
public class ISL231156Validator extends BaseValidator<RsRequest<ISL231155RsParam>> {

    @Override
    public void validatorData(RsRequest<ISL231155RsParam> entity) {
        ISL231155RsParam iSL231155RsParam = entity.getParam();
        if (null != iSL231155RsParam) {
            this.validatorRequired("企业ID",iSL231155RsParam.getEpId());
        }
    }
}





