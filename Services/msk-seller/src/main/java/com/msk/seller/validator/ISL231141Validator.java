package com.msk.seller.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.seller.bean.ISL231141RsParam;

/**
 * Created by cx on 2016/2/18.
 */
public class ISL231141Validator extends BaseValidator<RsRequest<ISL231141RsParam>> {

    @Override
    public void validatorData(RsRequest<ISL231141RsParam> entity) {
        ISL231141RsParam iSL231141RsParam = entity.getParam();
        if (null != iSL231141RsParam) {
            this.validatorRequired("企业id",iSL231141RsParam.getEpId());
        }
    }
}
