package com.msk.seller.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.seller.bean.ISL231149RsParam;

/**
 * Created by cx on 2016/2/22.
 */
public class ISL231149Validator extends BaseValidator<RsRequest<ISL231149RsParam>> {

    @Override
    public void validatorData(RsRequest<ISL231149RsParam> entity) {
        ISL231149RsParam iSL231149RsParam = entity.getParam();
        /*if (null != iSL231149RsParam) {
            this.validatorRequired("企业ID",iSL231149RsParam.getEpId());
        }*/
    }
}
