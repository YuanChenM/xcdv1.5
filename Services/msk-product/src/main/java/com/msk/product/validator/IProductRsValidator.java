package com.msk.product.validator;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.product.bean.IPD141104RsParam;
import com.msk.product.bean.PDInfoParam;

/**
 * Created by yang_chunyan on 2016/6/17.
 */
public class IProductRsValidator extends BaseValidator<RsRequest<PDInfoParam>> {
    @Override
    public void validatorData(RsRequest<PDInfoParam> entity) {
        PDInfoParam param = entity.getParam();
        if (null != param) {
            this.validatorRequired("平台编码", param.getSiteCode());
            this.validatorRequired("身份识别码", param.getAuth());
            this.validatorRequired("登陆的用户ID", param.getLoginId());
        }
    }
}
