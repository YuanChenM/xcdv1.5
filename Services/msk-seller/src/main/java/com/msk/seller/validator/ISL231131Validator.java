package com.msk.seller.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.core.entity.SlEpCap;
import com.msk.seller.bean.ISL231127CertInfoList;
import com.msk.seller.bean.ISL231130RsParam;
import com.msk.seller.bean.ISL231131RsParam;

import java.util.List;

/**
 * Created by rwf on 2016/2/23.
 */
public class ISL231131Validator extends BaseValidator<RsRequest<SlEpCap>> {
    @Override
    public void validatorData(RsRequest<SlEpCap> entity) {
        SlEpCap slEpCap = entity.getParam();
        this.validatorRequired("企业ID",slEpCap.getEpId());
        this.validatorRequired("厂区_总资产",slEpCap.getFtyAsset());
        this.validatorRequired("厂区_注册资本",slEpCap.getFtyRegCapital());
        this.validatorRequired("厂区_占地面积",slEpCap.getFtyLandArea());
        this.validatorRequired("厂区_厂房面积",slEpCap.getFtyFloorArea());
        this.validatorRequired("厂区_主要设备",slEpCap.getFtyEquipment());
        this.validatorRequired("厂区_设计产能",slEpCap.getFtyDesignCap());
        this.validatorRequired("厂区_实际产能",slEpCap.getFtyActualCap());
        this.validatorRequired("厂区_外贸销售占比",slEpCap.getFtyFtRate());
        this.validatorRequired("厂区_直销占比",slEpCap.getFtyDsRate());
        this.validatorRequired("厂区_代理销售占比",slEpCap.getFtyAsRate());
        this.validatorRequired("库容概括_原料库容",slEpCap.getScapMaterial());
        this.validatorRequired("库容概括_成品库容", slEpCap.getScapProduct());
        this.validatorRequired("创建者ID",slEpCap.getCrtId());
    }
}
