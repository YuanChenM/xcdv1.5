package com.msk.seller.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.msk.seller.bean.ISL231105RsParam;
import com.msk.seller.bean.ISL231191RsParam;

/**
 * Created by cx on 2016/2/16.
 */
public class ISL231191Validator extends BaseValidator<RsRequest<ISL231191RsParam>> {

    @Override
    public void validatorData(RsRequest<ISL231191RsParam> entity) {
        ISL231191RsParam isl231191RsParam = entity.getParam();
        if(null != isl231191RsParam){
            this.validatorRequired("卖家ID", isl231191RsParam.getSlCode());
            this.validatorRequired("产品一级分类CODE", isl231191RsParam.getClassesCode());
            this.validatorRequired("产品二级分类CODE", isl231191RsParam.getMachiningCode());
            this.validatorRequired("产品品种编码", isl231191RsParam.getBreedCode());
            this.validatorRequired("产品特征编码", isl231191RsParam.getFeatureCode());
            this.validatorRequired("产品净重编码", isl231191RsParam.getWeightCode());
            this.validatorRequired("产品等级编码", isl231191RsParam.getGradeCode());
            this.validatorRequired("销售平台", isl231191RsParam.getSalesPlatform());
        }
    }
}
