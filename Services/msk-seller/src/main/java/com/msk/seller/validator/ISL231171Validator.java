package com.msk.seller.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.core.entity.SlProduct;
import com.msk.seller.bean.ISL231169RsParam;

import java.util.List;

/**
 * Created by cx on 2016/2/24.
 */
public class ISL231171Validator extends BaseValidator<RsRequest<ISL231169RsParam>> {

    @Override
    public void validatorData(RsRequest<ISL231169RsParam> entity) {
        ISL231169RsParam iSL231169RsParam = entity.getParam();
        if (null != iSL231169RsParam) {
            List<SlProduct> slPdList =  iSL231169RsParam.getSlPdList();
            for(int i =0;i<slPdList.size();i++){
                SlProduct slProduct = slPdList.get(i);
                this.validatorRequired("卖家编码", slProduct.getSlCode());
                this.validatorRequired("卖家产品ID", slProduct.getSlPdId());
            }
        }
    }
}


