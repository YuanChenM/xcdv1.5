package com.msk.product.validator;


import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.product.bean.IPD141108RsParam;

/**
 * 
 * IPD141108 Validator 校验
 * 
 * @author xhy
 */
public class IPD141108Validator extends BaseValidator<RsRequest<IPD141108RsParam>> {

    @Override
    public void validatorData(RsRequest<IPD141108RsParam> entity) {
        IPD141108RsParam param = entity.getParam();
        if (null != param) {
        }
    }
}
