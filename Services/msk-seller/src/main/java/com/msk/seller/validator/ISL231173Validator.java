package com.msk.seller.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.core.entity.PdTcProviderPackage;
import com.msk.seller.bean.ISL231173RsParam;
import com.msk.seller.bean.SL241130Param;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

/**
 * Created by cx on 2016/3/10
 */
public class ISL231173Validator extends BaseValidator<RsRequest<ISL231173RsParam>> {

    @Override
    public void validatorData(RsRequest<ISL231173RsParam> entity) {
        ISL231173RsParam iSL231173RsParam = entity.getParam();
        this.validatorRequired("参数对象", iSL231173RsParam);
        this.validatorRequired("参数内容", iSL231173RsParam.getNewPdPkgList());
        if (null != iSL231173RsParam) {
            List<SL241130Param> list = iSL231173RsParam.getNewPdPkgList();
            if (!CollectionUtils.isEmpty(list) && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    SL241130Param pdTcProviderPackage = list.get(i);
                    this.validatorRequired("产品一级分类CODE", pdTcProviderPackage.getClassesCode());
                    this.validatorRequired("产品一级分类名称", pdTcProviderPackage.getClassesName());
                    this.validatorRequired("产品二级分类CODE", pdTcProviderPackage.getMachiningCode());
                    this.validatorRequired("产品二级分类名称", pdTcProviderPackage.getMachiningName());
                    this.validatorRequired("品种编码", pdTcProviderPackage.getBreedCode());
                    this.validatorRequired("品种名称", pdTcProviderPackage.getBreedName());
                    this.validatorRequired("净重编码", pdTcProviderPackage.getWeightCode());
                    this.validatorRequired("净重名称", pdTcProviderPackage.getWeightName());
                    this.validatorRequired("净重数值", pdTcProviderPackage.getWeightVal());
//                    this.validatorRequired("包装编码", pdTcProviderPackage.getNormsCode());
//                    this.validatorRequired("包装名称", pdTcProviderPackage.getNormsName());
//                    this.validatorRequired("单个产品净重", pdTcProviderPackage.getNormsSuttle());
//                    this.validatorRequired("单个产品规格净重误差范围", pdTcProviderPackage.getNormsError());
//                    this.validatorRequired("内包装净重/个数", pdTcProviderPackage.getNormsNumber());
//                    this.validatorRequired("内包装尺寸", pdTcProviderPackage.getNormsSize());
//                    this.validatorRequired("内包装材质及技术标准", pdTcProviderPackage.getNormsTexture());
//                    this.validatorRequired("外包装规格", pdTcProviderPackage.getNormsOut());
//                    this.validatorRequired("外包装净重/毛重", pdTcProviderPackage.getNormsKg());
//                    this.validatorRequired("外包装尺寸", pdTcProviderPackage.getNormsOutSize());
//                    this.validatorRequired("外包装材质及技术标准", pdTcProviderPackage.getNormsOutTexture());
//                    this.validatorRequired("其他包装信息", pdTcProviderPackage.getNormsTen());
//                    this.validatorRequired("外包装长", pdTcProviderPackage.getNormsLength());
//                    this.validatorRequired("外包装宽", pdTcProviderPackage.getNormsWidth());
//                    this.validatorRequired("外包装高", pdTcProviderPackage.getNormsHeight());
//                    this.validatorRequired("外包装体积", pdTcProviderPackage.getNormsVolume());
//                    this.validatorRequired("内包装净重数值", pdTcProviderPackage.getNetweightInner());
//                    this.validatorRequired("外包装净重数值", pdTcProviderPackage.getNetweightOut());
                    this.validatorRequired("创建者ID/卖家编码", pdTcProviderPackage.getCrtId());
                }
            }
        }
    }
}
