package com.msk.seller.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.seller.bean.ISL231101RsParam;

/**
 * Created by cx on 2016/3/31.
 */
public class IBYS2101104Validator extends BaseValidator<RsRequest<ISL231101RsParam>> {

    @Override
    public void validatorData(RsRequest<ISL231101RsParam> entity) {
        ISL231101RsParam isl231101RsParam=entity.getParam();
        this.validatorRequired("接口参数",isl231101RsParam);
        this.validatorRequired("时间戳",isl231101RsParam.getIncrementalTime());

    }
}
