package com.msk.bs.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.bs.bean.IBA2141192Param;
import com.msk.common.bean.RsRequest;

/**
 * Created by zhu_kai1 on 2016/10/31.
 */
public class IBA2141192Validator extends BaseValidator<RsRequest<IBA2141192Param>> {
    @Override
    public void validatorData(RsRequest<IBA2141192Param> rsParamRsRequest) {
        IBA2141192Param param = rsParamRsRequest.getParam();
        if (null != param) {
            this.validatorRequired("手机号码不能为空", param.getSlTel());
            this.validatorRequired("密码不能为空",param.getPassword());
            this.validatorRequired("验证码不能为空",param.getImgCode());
            this.validatorRequired("短信验证码不能为空",param.getMessCode());
        }
    }
}
