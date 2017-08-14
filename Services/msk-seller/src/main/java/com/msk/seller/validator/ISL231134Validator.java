package com.msk.seller.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.core.entity.SlEpCap;
import com.msk.seller.bean.ISL231134RsParam;
import com.msk.seller.bean.ISL231180SLEpAuth;

/**
 * Created by rwf on 2016/2/23.
 */
public class ISL231134Validator extends BaseValidator<RsRequest<ISL231134RsParam>> {
    @Override
    public void validatorData(RsRequest<ISL231134RsParam> entity) {
        ISL231134RsParam isl231134RsParam = entity.getParam();
        if(null!=isl231134RsParam){
            this.validatorRequired("1：卖家代理及分销授权:2：卖家OEM委托授权标志",isl231134RsParam.getFlag());
            this.validatorRequired("卖家编码",isl231134RsParam.getSlCode());
            this.validatorRequired("生产商_企业ID",isl231134RsParam.getProducerEpId());
            this.validatorRequired("授权经销合同号",isl231134RsParam.getContractNo());
            this.validatorRequired("授权单位",isl231134RsParam.getAuthEpName());
            this.validatorRequired("授权期限开始",isl231134RsParam.getAuthTermBegin());
            this.validatorRequired("授权期限结束",isl231134RsParam.getAuthTermEnd());
            this.validatorRequired("创建者ID",isl231134RsParam.getCrtId());
        }
    }
}
