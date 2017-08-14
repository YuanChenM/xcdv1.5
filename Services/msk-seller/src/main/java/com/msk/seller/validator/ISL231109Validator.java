package com.msk.seller.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.hoperun.core.utils.StringUtil;
import com.msk.seller.bean.*;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by gyh on 2016/2/29.
 */
public class ISL231109Validator extends BaseValidator<RsRequest<ISL231109RsParam>> {

    @Override
    public void validatorData(RsRequest<ISL231109RsParam> entity) {
        ISL231109RsParam isl231109RsParam = entity.getParam();
        if (null != isl231109RsParam) {
            this.validatorRequired("卖家编码", isl231109RsParam.getSlCode());
        }
    }
}
