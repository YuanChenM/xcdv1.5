package com.msk.product.validator;


import com.hoperun.core.consts.NumberConst;
import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.product.bean.IPD141138RsParam;

/**
 * IPD141128Validator 校验
 *
 * @author xhy
 */
public class IPD141138Validator extends BaseValidator<RsRequest<IPD141138RsParam>> {

    @Override
    public void validatorData(RsRequest<IPD141138RsParam> entity) {
        IPD141138RsParam param = entity.getParam();
        if (null != param) {
            this.validatorMaxLength(param.getClassesCode(), NumberConst.IntDef.INT_TWO, "产品类别编码", true);
            this.validatorMaxLength(param.getMachiningCode(), NumberConst.IntDef.INT_ONE, "加工类型编码", true);
            this.validatorMaxLength(param.getBreedCode(), NumberConst.IntDef.INT_TWO, "产品品种类型编码", true);
            this.validatorMaxLength(param.getFeatureCode(), NumberConst.IntDef.INT_TWO, "产品特征类型编码", true);
        }
    }
}
