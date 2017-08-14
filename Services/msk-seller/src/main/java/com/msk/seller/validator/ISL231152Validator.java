package com.msk.seller.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.core.entity.SlPdBrand;

/**
 * Created by cx on 2016/2/22.
 */
public class ISL231152Validator extends BaseValidator<RsRequest<SlPdBrand>> {

    @Override
    public void validatorData(RsRequest<SlPdBrand> entity) {
        SlPdBrand slPdBrand = entity.getParam();
        if (null != slPdBrand) {
            this.validatorRequired("卖家编码",slPdBrand.getSlCode());
        }
    }
}



