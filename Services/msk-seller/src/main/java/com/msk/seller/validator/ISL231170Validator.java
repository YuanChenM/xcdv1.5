package com.msk.seller.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.core.entity.SlProduct;
import com.msk.seller.bean.ISL231169RsParam;

import java.util.List;

/**
 * Created by cx on 2016/2/24.
 */
public class ISL231170Validator extends BaseValidator<RsRequest<ISL231169RsParam>> {

    @Override
    public void validatorData(RsRequest<ISL231169RsParam> entity) {
        ISL231169RsParam iSL231169RsParam = entity.getParam();
        if (null != iSL231169RsParam) {
          List<SlProduct> slProductList = iSL231169RsParam.getSlPdList();
            for(int i = 0;i<slProductList.size();i++){
                SlProduct slProduct = slProductList.get(i);
                this.validatorRequired("卖家编码",slProduct.getSlCode());
                this.validatorRequired("卖家产品ID",slProduct.getSlPdId());
                this.validatorRequired("生产商企业ID",slProduct.getProdEpId());
                this.validatorRequired("品牌商企业ID",slProduct.getBrandEpId());
                this.validatorRequired("产品品牌ID",slProduct.getBrandId());
                this.validatorRequired("产品类别",slProduct.getPdClassesCode());
                this.validatorRequired("产品品种",slProduct.getPdBreedCode());
                this.validatorRequired("产品特征",slProduct.getPdFeatureCode());
                this.validatorRequired("更新者ID",slProduct.getUpdId());
            }
        }
    }
}




