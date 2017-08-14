package com.msk.seller.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.core.entity.SlEcTeam;

/**
 * Created by cx on 2016/2/18.
 */
public class ISL231143Validator extends BaseValidator<RsRequest<SlEcTeam>> {

    @Override
    public void validatorData(RsRequest<SlEcTeam> entity) {
        SlEcTeam slEcTeam = entity.getParam();
        if (null != slEcTeam) {
            this.validatorRequired("卖家编码",slEcTeam.getSlCode());
            this.validatorRequired("是否负责人",slEcTeam.getLeaderFlg());
            this.validatorRequired("姓名",slEcTeam.getMemberName());
            this.validatorRequired("年龄",slEcTeam.getMemberAge());
            this.validatorRequired("文化程度",slEcTeam.getMemberEduc());
            this.validatorRequired("联系电话",slEcTeam.getMemberTel());
            this.validatorRequired("创建者ID",slEcTeam.getUpdId());
        }
    }
}
