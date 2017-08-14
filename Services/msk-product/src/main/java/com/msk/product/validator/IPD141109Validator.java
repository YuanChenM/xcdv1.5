package com.msk.product.validator;


import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.product.bean.IPD141109RsParam;

/**
 * 
 * IPD141109Validator 校验
 * 
 * @author xhy
 */
public class IPD141109Validator extends BaseValidator<RsRequest<IPD141109RsParam>> {

    @Override
    public void validatorData(RsRequest<IPD141109RsParam> entity) {
        IPD141109RsParam param = entity.getParam();
        if (null != param) {
            this.validatorRequired("类别编码", param.getClassesCode());
            this.validatorRequired("品种编码", param.getBreedCode());
            this.validatorRequired("特征编码", param.getFeatureCode());
            this.validatorRequired("产品等级编码", param.getGradeCode());
            this.validatorRequired("单个产品净重", param.getNormsSuttle());
            this.validatorRequired("单个产品规格净重误差范围", param.getNormsError());
            this.validatorRequired("内包装净重/个数", param.getNormsNumber());
            this.validatorRequired("内包装尺寸", param.getNormsSize());
            this.validatorRequired("内包装材质及技术标准", param.getNormsTexture());
            this.validatorRequired("外包装规格", param.getNormsOut());
            this.validatorRequired("外包装净重/毛重", param.getNormsKg());
            this.validatorRequired("外包装尺寸", param.getNormsOutSize());
            this.validatorRequired("外包装材质及技术标准", param.getNormsOutTexture());
            this.validatorRequired("其他包装信息", param.getNormsTen());
            this.validatorRequired("外包装长", param.getNormsLength());
            this.validatorRequired("外包装宽", param.getNormsWidth());
            this.validatorRequired("外包装高", param.getNormsHeight());
            this.validatorRequired("外包装体积", param.getNormsVolume());
            this.validatorRequired("内包装净重数值", param.getNetWeightInner());
            this.validatorRequired("外包装净重数值", param.getNetWeightOut());
        }
    }
}
