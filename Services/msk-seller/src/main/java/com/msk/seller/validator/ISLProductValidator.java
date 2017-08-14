package com.msk.seller.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.core.entity.SlPdArtno;
import com.msk.seller.bean.ISLProductRsParam;

/**
 * Created by guan_zhongheng on 2016/9/20.
 */
public class ISLProductValidator extends BaseValidator<RsRequest<ISLProductRsParam>> {
    @Override
    public void validatorData(RsRequest<ISLProductRsParam> request) {
        ISLProductRsParam param = request.getParam();
        if (null != param) {
            this.validatorRequired("销售平台", param.getSalePlatform());
            for(SlPdArtno list:param.getSlList()){
                this.validatorRequired("卖家编码", list.getSlCodeDis());
                this.validatorRequired("货号", list.getSlPdArtno());
            }
        }
    }
}
