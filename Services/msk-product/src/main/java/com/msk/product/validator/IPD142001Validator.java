package com.msk.product.validator;


import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.product.bean.IPD142001RsParam;

/**
 * IPD142001Validator 校验
 *
 * @author xhy
 */
public class IPD142001Validator extends BaseValidator<RsRequest<IPD142001RsParam>> {

    @Override
    public void validatorData(RsRequest<IPD142001RsParam> entity) {
        IPD142001RsParam param = entity.getParam();
        if (null != param) {
            this.validatorRequired("内容类型", param.getConstantType());
        }
    }
}
