package com.msk.bs.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.bs.bean.IBS2101118Param;
import com.msk.common.bean.RsRequest;

/**
 * Created by zhu_kai1 on 2016/8/18.
 */
public class IBS2101118Validator extends BaseValidator<RsRequest<IBS2101118Param>> {
    @Override
    public void validatorData(RsRequest<IBS2101118Param> paramRsRequest) {
        IBS2101118Param ibs2101118Param = paramRsRequest.getParam();
        this.validatorRequired("买家ID", ibs2101118Param.getBuyerId());

    }
}
