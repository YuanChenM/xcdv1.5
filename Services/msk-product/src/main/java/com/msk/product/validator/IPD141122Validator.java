package com.msk.product.validator;


import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.product.bean.IPD141122RsParam;

/**
 * Created by fjm on 2016/3/9.
 */
public class IPD141122Validator extends BaseValidator<RsRequest<IPD141122RsParam>> {

    @Override
    public void validatorData(RsRequest<IPD141122RsParam> entity) {
        IPD141122RsParam param = entity.getParam();
        if (null != param) {
            this.validatorRequired("类别编码", param.getClassesCode());
            this.validatorRequired("二级分类编码", param.getMachiningCode());
        }
    }
}
