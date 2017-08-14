package com.msk.product.validator;


import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.product.bean.IPD141105RsParam;

/**
 * IPD141105Validator 校验
 *
 * @author xhy
 */
public class IPD141105Validator extends BaseValidator<RsRequest<IPD141105RsParam>> {

    @Override
    public void validatorData(RsRequest<IPD141105RsParam> entity) {
        IPD141105RsParam param = entity.getParam();
        if (null != param) {
            //this.validatorMax(StringUtil.toBigDecimal(param.getCodeLevel()), StringUtil.toBigDecimal(NumberConst.IntDef.INT_THREE), "产品等级", true);
            this.validatorMaxLength(param.getClassesCode(), NumberConst.IntDef.INT_TWO, "产品类别编码", false);
        }
    }
}
