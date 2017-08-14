package com.msk.product.validator;


import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.product.bean.IPD141144RsParam;

/**
 * IPD141139Validator 校验
 *
 * @author xhy
 */
public class IPD141144Validator extends BaseValidator<RsRequest<IPD141144RsParam>> {

    @Override
    public void validatorData(RsRequest<IPD141144RsParam> entity) {
        IPD141144RsParam param = entity.getParam();
        if (null != param) {
            this.validatorRequired("卖家编码", param.getSellerCode());
            this.validatorBetween(StringUtil.toBigDecimal(param.getPlatformType()),StringUtil.toBigDecimal(NumberConst.IntDef.INT_TWO),StringUtil.toBigDecimal(NumberConst.IntDef.INT_ONE),"电商类型",true);
            this.validatorMaxLength(StringUtil.toSafeString(param.getDistrictCode()), NumberConst.IntDef.INT_TWO, "物流区域编码",true);
            this.validatorBetween(StringUtil.toBigDecimal(param.getSellerType()),StringUtil.toBigDecimal(NumberConst.IntDef.INT_TWO),StringUtil.toBigDecimal(NumberConst.IntDef.INT_ONE),"卖家类型",true);
            this.validatorMaxLength(param.getPdCode(),NumberConst.IntDef.INT_TEN, "产品编码",false);
        }
    }
}
