package com.msk.price.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.price.bean.PricePlateInfoParam;

/**
 * Created by peng_hao on 2016/6/14.
 */
public class ISP171182Validator extends BaseValidator<RsRequest<PricePlateInfoParam>> {
    @Override
    public void validatorData(RsRequest<PricePlateInfoParam> request) {
        PricePlateInfoParam pricePlateInfoParam=request.getParam();
        if(null!=pricePlateInfoParam){
            this.validatorRequired("物流区Code", pricePlateInfoParam.getLgcsCode());
            //this.validatorRequired("价盘周期", pricePlateInfoParam.getPricePeriod());
            this.validatorRequired("产品编码列表", pricePlateInfoParam.getPdCodes());
        }

    }
}
