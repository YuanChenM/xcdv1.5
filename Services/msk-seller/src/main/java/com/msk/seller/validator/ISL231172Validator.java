package com.msk.seller.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.core.entity.PdTcProvider;
import com.msk.seller.bean.ISL231172RsParam;
import com.msk.seller.bean.SL241130Param;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by cx on 2016/3/10
 */
public class ISL231172Validator extends BaseValidator<RsRequest<ISL231172RsParam>> {

    @Override
    public void validatorData(RsRequest<ISL231172RsParam> entity) {
        ISL231172RsParam iSL231172RsParam = entity.getParam();
        this.validatorRequired("参数对象", iSL231172RsParam);
        this.validatorRequired("参数内容", iSL231172RsParam.getNewPdBFWList());
        if (null != iSL231172RsParam) {
            List<SL241130Param> list = iSL231172RsParam.getNewPdBFWList();
            if (!CollectionUtils.isEmpty(list) && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    SL241130Param pdTcProvider = list.get(i);
                    this.validatorRequired("1 品种 2 特征 3 净重", pdTcProvider.getNewFlag());
                    this.validatorRequired("产品一级分类CODE", pdTcProvider.getClassesCode());
                    this.validatorRequired("产品一级分类名称", pdTcProvider.getClassesName());
                    this.validatorRequired("产品二级分类CODE", pdTcProvider.getMachiningCode());
                    this.validatorRequired("产品二级分类名称", pdTcProvider.getMachiningName());
                    this.validatorRequired("创建者ID/卖家编码", pdTcProvider.getCrtId());
                    if ("1".equals(pdTcProvider.getNewFlag())) {
                        this.validatorRequired("品种名称", pdTcProvider.getBreedName());
                    } else if ("2".equals(pdTcProvider.getNewFlag())) {
                        this.validatorRequired("品种编码", pdTcProvider.getBreedCode());
                        this.validatorRequired("品种名称", pdTcProvider.getBreedName());
                        this.validatorRequired("产品特征名称", pdTcProvider.getFeatureName());
                    } else if ("3".equals(pdTcProvider.getNewFlag())) {
                        this.validatorRequired("品种编码", pdTcProvider.getBreedCode());
                        this.validatorRequired("品种名称", pdTcProvider.getBreedName());
                        this.validatorRequired("产品特征编码", pdTcProvider.getFeatureCode());
                        this.validatorRequired("产品特征名称", pdTcProvider.getFeatureName());
                        this.validatorRequired("产品净重名称", pdTcProvider.getWeightName());
                        this.validatorRequired("产品净重数值", pdTcProvider.getWeightVal());
                    }
                }
            }
        }

    }
}
