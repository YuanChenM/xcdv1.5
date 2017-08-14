package com.msk.seller.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.seller.bean.ISL231101RsParam;
import com.msk.seller.bean.ISL231137RsParam;

/**
 * Created by gyh on 2016/3/17.
 */
public class ISL231137Validator extends BaseValidator<RsRequest<ISL231137RsParam>> {

    @Override
    public void validatorData(RsRequest<ISL231137RsParam> entity) {
        ISL231137RsParam isl231101RsParam=entity.getParam();
        this.validatorRequired("接口参数",isl231101RsParam);
//        this.validatorRequired("生产商类型",isl231101RsParam.getFlag());
        this.validatorRequired("卖家编码",isl231101RsParam.getSlCode());
    }
}
