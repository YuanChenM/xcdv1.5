package com.msk.product.validator;


import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.product.bean.IPD141145RsParam;

/**
 * IPD141145Validator 校验
 *
 * @author gyh
 */
public class IPD141145Validator extends BaseValidator<RsRequest<IPD141145RsParam>> {

    @Override
    public void validatorData(RsRequest<IPD141145RsParam> entity) {
        IPD141145RsParam param = entity.getParam();
        this.validatorRequired("参数", param);
        if (null != param) {
            this.validatorRequired("卖家Id", param.getSellerCode());
        }
    }
}
