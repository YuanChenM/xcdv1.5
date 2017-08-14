package com.msk.product.validator;


import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.product.bean.IPD141126RsParam;

/**
 * Created by air on 2016/3/11.
 */
public class IPD141126Validator extends BaseValidator<RsRequest<IPD141126RsParam>> {
    @Override
    public void validatorData(RsRequest<IPD141126RsParam> entity) {
        IPD141126RsParam param = entity.getParam();
        if (null != param) {
            this.validatorRequired("类别编码", param.getClassesCode());
            this.validatorRequired("二级分类编码", param.getMachiningCode());
            this.validatorRequired("品种编码",param.getBreedCode());
        }
    }
}
