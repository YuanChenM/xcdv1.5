package com.msk.product.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.product.bean.IPD1411214RsParam;

/**
 * Created by gao_min on 2016/10/11.
 */
public class IPD1411214Validator extends BaseValidator<RsRequest<IPD1411214RsParam>> {

    @Override
    public void validatorData(RsRequest<IPD1411214RsParam> entity) {
        this.validatorRequired("身份识别码", entity.getAuth());
        this.validatorRequired("平台编码", entity.getSiteCode());
        IPD1411214RsParam param = entity.getParam();
        if (null != param) {
            this.validatorRequired("用户类型", param.getUserType());
            this.validatorRequired("用户ID", param.getUserId());
        }
    }

}
