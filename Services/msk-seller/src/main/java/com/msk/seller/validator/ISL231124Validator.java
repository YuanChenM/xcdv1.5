package com.msk.seller.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.core.entity.SlEnterprise;

/**
 * Created by cx on 2016/2/17.
 */
public class ISL231124Validator extends BaseValidator<RsRequest<SlEnterprise>> {

    @Override
    public void validatorData(RsRequest<SlEnterprise> entity) {
        SlEnterprise slEnterprise = entity.getParam();
        if (null != slEnterprise) {
            this.validatorRequired("企业ID", slEnterprise.getEpId());
            this.validatorRequired("企业名称", slEnterprise.getEpName());
            this.validatorRequired("更新者ID", slEnterprise.getUpdId());
            this.validatorRequired("版本号", slEnterprise.getVer());
        }
    }

}
