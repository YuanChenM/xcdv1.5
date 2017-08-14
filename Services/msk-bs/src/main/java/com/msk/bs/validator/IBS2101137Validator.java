package com.msk.bs.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.bs.bean.IBS2101137RsParam;
import com.msk.common.bean.RsRequest;

/**
 * Created by ni_shaotang on 2016/10/28.
 */
public class IBS2101137Validator extends BaseValidator<RsRequest<IBS2101137RsParam>> {
    @Override
    public void validatorData(RsRequest<IBS2101137RsParam> paramRsRequest) {

        this.validatorRequired("身份识别码", paramRsRequest.getAuth());
        this.validatorRequired("平台编码", paramRsRequest.getSiteCode());
        this.validatorRequired("登陆的用户ID", paramRsRequest.getLoginId());
        this.validatorRequired("参数param", paramRsRequest.getParam());
        if (null != paramRsRequest.getParam()) {
            this.validatorRequired("查询类型", paramRsRequest.getParam().getSlCodeFlag());
        }
    }
}
