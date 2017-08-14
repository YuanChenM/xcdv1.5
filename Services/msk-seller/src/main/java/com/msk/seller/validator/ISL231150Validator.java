package com.msk.seller.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.seller.bean.ISlPdBrand;

/**
 * Created by cx on 2016/2/22.
 */
public class ISL231150Validator extends BaseValidator<RsRequest<ISlPdBrand>> {

    @Override
    public void validatorData(RsRequest<ISlPdBrand> entity) {
        ISlPdBrand iSlPdBrand = entity.getParam();
        if (null != iSlPdBrand) {
            this.validatorRequired("卖家编码",iSlPdBrand.getSlCode());
            this.validatorRequired("品牌所属企业ID",iSlPdBrand.getBrandEpId());
            this.validatorRequired("品牌ID",iSlPdBrand.getBrandId());
            this.validatorRequired("品牌名称",iSlPdBrand.getBrandName());
            this.validatorRequired("品牌类型",iSlPdBrand.getBrandType());
            this.validatorRequired("品牌分类",iSlPdBrand.getBrandClass());
            this.validatorRequired("创建者ID",iSlPdBrand.getCrtId());
        }
    }
}



