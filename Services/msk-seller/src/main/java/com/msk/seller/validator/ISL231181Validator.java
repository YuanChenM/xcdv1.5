package com.msk.seller.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.seller.bean.ISL231128RsParam;
import com.msk.seller.bean.ISL231181RsParam;
import com.msk.seller.bean.SL24110302_1Bean;

import java.util.List;

/**
 * Created by rwf on 2016/2/22.
 */
public class ISL231181Validator extends BaseValidator<RsRequest<ISL231181RsParam>> {

    @Override
    public void validatorData(RsRequest<ISL231181RsParam> entity) {
        ISL231181RsParam isl231181RsParam = entity.getParam();
        this.validatorRequired("卖家账号",isl231181RsParam.getSlAccount());
    }
}
