package com.msk.seller.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.seller.bean.ISL231128RsParam;
import com.msk.seller.bean.ISL231129RsParam;
import com.msk.seller.bean.SL24110302_1Bean;

import java.util.List;

/**
 * Created by rwf on 2016/2/23.
 */
public class ISL231129Validator extends BaseValidator<RsRequest<ISL231129RsParam>> {
    @Override
    public void validatorData(RsRequest<ISL231129RsParam> entity) {
        ISL231129RsParam iSL231129RsParam = entity.getParam();
        this.validatorRequired("企业ID",iSL231129RsParam.getEpId());
    }
}
