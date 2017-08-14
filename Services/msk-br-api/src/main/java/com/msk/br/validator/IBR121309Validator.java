package com.msk.br.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.br.bean.IBR121309RsParam;
import com.msk.common.bean.RsRequest;

/**
 * Created by guan_zhongheng on 2016/8/23.
 */
public class IBR121309Validator extends BaseValidator<RsRequest<IBR121309RsParam>> {

    @Override
    public void validatorData(RsRequest<IBR121309RsParam> param){
        IBR121309RsParam ibr121309RsParam = param.getParam();
        this.validatorRequired("买家ID", ibr121309RsParam.getBuyerId());
        this.validatorRequired("营销/销售期", ibr121309RsParam.getSearchType());
    }
}
