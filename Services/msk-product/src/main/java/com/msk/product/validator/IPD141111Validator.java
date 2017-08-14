package com.msk.product.validator;


import com.hoperun.core.consts.NumberConst;
import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.product.bean.IPD141111RsParam;

/**
 * Created by FjM on 2016/3/14.
 */
public class IPD141111Validator extends BaseValidator<RsRequest<IPD141111RsParam>> {
    @Override
    public void validatorData(RsRequest<IPD141111RsParam> entity) {
        IPD141111RsParam param = entity.getParam();
        if (null != param) {
            this.validatorMaxLength(param.getClassesCode(), NumberConst.IntDef.INT_TWO, "产品类别编码", false);
            this.validatorMaxLength(param.getMachiningCode(), NumberConst.IntDef.INT_ONE, "加工类型编码", false);
            this.validatorMaxLength(param.getBreedCode(), NumberConst.IntDef.INT_TWO, "产品品种编码", false);
            this.validatorMaxLength(param.getFeatureCode(), NumberConst.IntDef.INT_TWO, "产品特征编码", false);
            this.validatorMaxLength(param.getWeightCode(), NumberConst.IntDef.INT_TWO, "产品净重编码", false);
            this.validatorMaxLength(param.getGradeCode(), NumberConst.IntDef.INT_TWO, "产品等级编码", false);
            this.validatorMaxLength(param.getLogiAreaCode(), NumberConst.IntDef.INT_TWO, "物流区编码", false);
        }
    }
}
