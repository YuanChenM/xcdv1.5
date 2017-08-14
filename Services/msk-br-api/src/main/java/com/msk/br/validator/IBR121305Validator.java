package com.msk.br.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.core.entity.BrHkGroup;

/**
 * Created by guan_zhongheng on 2016/8/16.
 */
public class IBR121305Validator extends BaseValidator<RsRequest<BrHkGroup>> {
    @Override
    public void validatorData(RsRequest<BrHkGroup> param){
        BrHkGroup brHkGroup = param.getParam();
        if (null != brHkGroup) {
            this.validatorRequired("产品一级分类编码",brHkGroup.getClassesCode());
            this.validatorRequired("买家类型",brHkGroup.getBuyerType());
            this.validatorRequired("地区编码",brHkGroup.getCityCode());
            this.validatorRequired("物流区编码",brHkGroup.getLgcsAreaCode());
            this.validatorRequired("产品二级分类编码",brHkGroup.getMachiningCodeU());
        }
    }
}
