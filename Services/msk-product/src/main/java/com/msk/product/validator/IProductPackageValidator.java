package com.msk.product.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.product.bean.PDInfoParam;
import org.apache.commons.collections.CollectionUtils;

/**
 * Created by yang_chunyan on 2016/7/4.
 */
public class IProductPackageValidator extends BaseValidator<RsRequest<PDInfoParam>> {
    @Override
    public void validatorData(RsRequest<PDInfoParam> entity) {
        PDInfoParam param = entity.getParam();
        if (null != param) {
            if(CollectionUtils.isEmpty(param.getStdParams()) && CollectionUtils.isEmpty(param.getNormsParamses())){
                this.validatorRequired("一级分类编码", param.getClassesCode());
                this.validatorRequired("二级分类", param.getMachiningCode());
                this.validatorRequired("品种编码", param.getBreedCode());
                this.validatorRequired("特征编码", param.getFeatureCode());
                this.validatorRequired("净重编码", param.getWeightCode());
            }
        }
    }
}
