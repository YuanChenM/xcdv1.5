package com.msk.product.validator;


import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.product.bean.IPD141107RsParam;

/**
 * 
 * IPD141107 Validator 校验
 * 
 * @author xhy
 */
public class IPD141107Validator extends BaseValidator<RsRequest<IPD141107RsParam>> {

    @Override
    public void validatorData(RsRequest<IPD141107RsParam> entity) {
        IPD141107RsParam param = entity.getParam();
        if (null != param) {
            this.validatorRequired("类别编码", param.getClassesCode());
            this.validatorRequired("品种编码", param.getBreedCode());
        }
    }
}
