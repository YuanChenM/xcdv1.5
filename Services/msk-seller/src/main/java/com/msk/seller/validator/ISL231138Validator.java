package com.msk.seller.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.core.entity.SlEpManager;

/**
 * Created by cx on 2016/2/18.
 */
public class ISL231138Validator extends BaseValidator<RsRequest<SlEpManager>> {

    @Override
    public void validatorData(RsRequest<SlEpManager> entity) {
        SlEpManager slEpManager = entity.getParam();
        if (null != slEpManager) {
            this.validatorRequired("企业ID",slEpManager.getEpId());
            this.validatorRequired("管理成员ID",slEpManager.getMemberId());
            this.validatorRequired("职务",slEpManager.getMemberDuties());
            this.validatorRequired("姓名",slEpManager.getMemberName());
            this.validatorRequired("年龄",slEpManager.getMemberAge());
            this.validatorRequired("文化程度",slEpManager.getMemberEduc());
            this.validatorRequired("联系电话",slEpManager.getMemberTel());
            this.validatorRequired("更新者ID",slEpManager.getUpdId());
        }
    }
}
