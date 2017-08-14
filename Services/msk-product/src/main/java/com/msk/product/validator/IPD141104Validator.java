package com.msk.product.validator;


import com.hoperun.core.consts.NumberConst;
import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.product.bean.IPD141104RsParam;

/**
 * IPD141104Validator 校验
 *
 * @author xhy
 */
public class IPD141104Validator extends BaseValidator<RsRequest<IPD141104RsParam>> {

    @Override
    public void validatorData(RsRequest<IPD141104RsParam> entity) {
        IPD141104RsParam param = entity.getParam();
        if (null != param) {
            this.validatorMaxLength(param.getClassesCode(), NumberConst.IntDef.INT_TWO, "产品类别编码", true);
        }
    }
}
