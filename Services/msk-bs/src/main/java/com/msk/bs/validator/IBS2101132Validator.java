package com.msk.bs.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.bs.bean.IBS2101132RsParam;
import com.msk.common.bean.RsRequest;

/**
 * Created by gao_min on 2016/10/13.
 */
public class IBS2101132Validator extends BaseValidator<RsRequest<IBS2101132RsParam>> {

    @Override
    public void validatorData(RsRequest<IBS2101132RsParam> paramRsRequest) {

        this.validatorRequired("身份识别码", paramRsRequest.getAuth());
        this.validatorRequired("平台编码", paramRsRequest.getSiteCode());
        this.validatorRequired("登陆的用户ID", paramRsRequest.getLoginId());
    }
}
