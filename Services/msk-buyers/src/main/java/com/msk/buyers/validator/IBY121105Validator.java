package com.msk.buyers.validator;

import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.plug.spring.base.BaseValidator;
import com.hoperun.core.bean.BaseParam;
import com.msk.buyers.bean.IBY121105Param;
import com.msk.common.bean.RsRequest;

/**
 * Created by guan_zhongheng on 2016/7/29.
 */
public class IBY121105Validator extends BaseValidator<RsRequest<IBY121105Param>> {

    @Override
    public void validatorData(RsRequest<IBY121105Param> request) {
        IBY121105Param baseParam = request.getParam();
        if(baseParam != null){
            String registerSource = StringUtil.toSafeString(baseParam.getRegisterSource());
            String crtTime = StringUtil.toSafeString(baseParam.getCrtTimeInfo());
            this.validatorRequired("来源地",registerSource);
            this.validatorRequired("创建时间",crtTime);
        }else{
            throw new BusinessException("参数不能为空");
        }
    }
}
