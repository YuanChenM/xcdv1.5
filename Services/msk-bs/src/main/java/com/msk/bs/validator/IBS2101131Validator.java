package com.msk.bs.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.bs.bean.IBS2101131RsParam;
import com.msk.common.bean.RsRequest;

/**
 * Created by gao_min on 2016/10/12.
 */
public class IBS2101131Validator extends BaseValidator<RsRequest<IBS2101131RsParam>> {

    @Override
    public void validatorData(RsRequest<IBS2101131RsParam> paramRsRequest) {

        this.validatorRequired("身份识别码", paramRsRequest.getAuth());
        this.validatorRequired("平台编码", paramRsRequest.getSiteCode());
    }
}
