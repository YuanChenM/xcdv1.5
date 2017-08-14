package com.msk.bs.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.bs.bean.IBS2101103RsParam;
import com.msk.common.bean.RsRequest;

/**
 * Created by cx on 2016/2/29.
 */
public class IBS2101103Validator extends BaseValidator<RsRequest<IBS2101103RsParam>> {

    @Override
    public void validatorData(RsRequest<IBS2101103RsParam> entity) {
        IBS2101103RsParam iBS2101103RsParam = entity.getParam();
//        if (null != iBS2101103RsParam) {
//            String slAccount = iBS2101103RsParam.getSlAccount();
//            String slTel = iBS2101103RsParam.getSlTel();
//            if(StringUtil.isEmpty(slAccount) && StringUtil.isEmpty(slTel)){
//                throw new BusinessException("请输入正确的卖家账号或登录手机号码");
//            }
//        }
    }
}


