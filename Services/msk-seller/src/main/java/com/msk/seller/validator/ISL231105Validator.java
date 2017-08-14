package com.msk.seller.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.msk.seller.bean.ISL231105RsParam;

/**
 * Created by cx on 2016/2/16.
 */
public class ISL231105Validator extends BaseValidator<RsRequest<ISL231105RsParam>> {

    @Override
    public void validatorData(RsRequest<ISL231105RsParam> entity) {
        ISL231105RsParam iSL231105RsParam = entity.getParam();
        if(null != iSL231105RsParam){
            if(StringUtil.isNullOrEmpty(iSL231105RsParam.getSlAccount()) && StringUtil.isNullOrEmpty(iSL231105RsParam.getSlTel())){
                this.validatorRequired("卖家账号", iSL231105RsParam.getSlAccount());
            }
        }else{
            throw new BusinessException("输入参数不能为空");
        }
    }

}
