package com.msk.product.validator;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.product.bean.IPD141146RsParam;

/**
 * IPD141121Validator 校验
 *
 * @author xhy
 */
public class IPD1411211Validator extends BaseValidator<RsRequest<IPD141146RsParam>> {

    @Override
    public void validatorData(RsRequest<IPD141146RsParam> entity) {
        IPD141146RsParam param = entity.getParam();
        if (null != param) {
            this.validatorMaxLength(param.getType() + "", NumberConst.IntDef.INT_ONE, "档案卡类型",true);
            this.validatorMaxLength(param.getClassesCode(), NumberConst.IntDef.INT_TWO, "产品类别编码", true);
            this.validatorMaxLength(param.getMachiningCode(), NumberConst.IntDef.INT_ONE, "加工类型编码", true);
            this.validatorMaxLength(param.getBreedCode(), NumberConst.IntDef.INT_TWO, "产品品种编码", true);
            this.validatorMaxLength(param.getFeatureCode(), NumberConst.IntDef.INT_TWO, "产品特征编码", false);
        }
    }
}
