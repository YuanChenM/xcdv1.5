package com.msk.bs.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.bs.bean.IBS2101107RsParam;
import com.msk.common.bean.RsRequest;

/**
 * Created by yangyang on 2016/7/4.
 */
public class IBS2101107Validator extends BaseValidator<RsRequest<IBS2101107RsParam>> {

    @Override
    public void validatorData(RsRequest<IBS2101107RsParam> entity) {
        IBS2101107RsParam iBS2101107RsParam = entity.getParam();
        this.validatorRequired("买家类型",iBS2101107RsParam.getBuyerFlag());
    }

}






