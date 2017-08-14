package com.msk.seller.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.seller.bean.SL24110306Bean;

/**
 * Created by cx on 2016/2/18.
 */
public class ISL231140Validator extends BaseValidator<RsRequest<SL24110306Bean>> {

    @Override
    public void validatorData(RsRequest<SL24110306Bean> entity) {
        SL24110306Bean sL24110306Bean = entity.getParam();
        if (null != sL24110306Bean) {
            this.validatorRequired("企业id",sL24110306Bean.getEpId());
        }
    }
}
