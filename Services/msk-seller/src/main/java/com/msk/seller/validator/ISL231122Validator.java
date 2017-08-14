package com.msk.seller.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.seller.bean.ISL231122RsParam;

/**
 * Created by cx on 2016/2/17.
 */
public class ISL231122Validator extends BaseValidator<RsRequest<ISL231122RsParam>> {

    @Override
    public void validatorData(RsRequest<ISL231122RsParam> entity) {
        ISL231122RsParam iSL231122RsParam = entity.getParam();
        if (null != iSL231122RsParam) {
            this.validatorRequired("卖家账号", iSL231122RsParam.getSlAreaCode());
            this.validatorRequired("登录手机号码", iSL231122RsParam.getSlTel());
            this.validatorRequired("卖家编码", iSL231122RsParam.getSlCode());
            this.validatorRequired("区划", iSL231122RsParam.getSlAreaCode());
            this.validatorRequired("企业名称", iSL231122RsParam.getEpName());
        }
    }

}
