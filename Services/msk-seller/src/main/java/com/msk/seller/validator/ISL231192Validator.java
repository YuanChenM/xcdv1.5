package com.msk.seller.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.seller.bean.ISL231191RsParam;
import com.msk.seller.bean.ISL231192RsParam;

/**
 * Created by cx on 2016/2/16.
 */
public class ISL231192Validator extends BaseValidator<RsRequest<ISL231192RsParam>> {

    @Override
    public void validatorData(RsRequest<ISL231192RsParam> entity) {
        ISL231192RsParam isl231191RsParam = entity.getParam();
        if(null != isl231191RsParam){
            this.validatorRequired("卖家ID", isl231191RsParam.getSlCode());
            this.validatorRequired("卖家产品货号", isl231191RsParam.getSlPdArtNo());
        }
    }
}
