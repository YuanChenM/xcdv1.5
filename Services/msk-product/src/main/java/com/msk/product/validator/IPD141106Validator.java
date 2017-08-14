package com.msk.product.validator;


import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.product.bean.IPD141106RsParam;

/**
 * 
 * IPD141106 Validator 校验
 * 
 * @author xhy
 */
public class IPD141106Validator extends BaseValidator<RsRequest<IPD141106RsParam>> {

    @Override
    public void validatorData(RsRequest<IPD141106RsParam> entity) {
        IPD141106RsParam param = entity.getParam();
        if (null != param) {
            this.validatorRequired("类别编码", param.getClassesCode());
            this.validatorRequired("品种编码", param.getBreedCode());
        }

    }
}
