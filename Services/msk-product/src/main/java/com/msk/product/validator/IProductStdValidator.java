package com.msk.product.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.product.bean.PDInfoParam;

/**
 * Created by yang_chunyan on 2016/6/30.
 */
public class IProductStdValidator extends BaseValidator<RsRequest<PDInfoParam>> {
    @Override
    public void validatorData(RsRequest<PDInfoParam> entity) {
        PDInfoParam param = entity.getParam();
        if (null != param) {
            this.validatorRequired("档案卡类型", param.getType());
        }
    }
}

