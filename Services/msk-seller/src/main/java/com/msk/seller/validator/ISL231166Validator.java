package com.msk.seller.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.seller.bean.ISL231166RsParam;
import com.msk.seller.bean.PdClassesCode;

import java.util.List;

/**
 * Created by cx on 2016/2/24.
 */
public class ISL231166Validator extends BaseValidator<RsRequest<ISL231166RsParam>> {

    @Override
    public void validatorData(RsRequest<ISL231166RsParam> entity){
        ISL231166RsParam iSL231166RsParam = entity.getParam();
        if (null != iSL231166RsParam) {
            this.validatorRequired("卖家编码", iSL231166RsParam.getSlCode());
            List<PdClassesCode> pdClassesCodeList = iSL231166RsParam.getPdClassesCodeList();
            for (int i = 0; i < pdClassesCodeList.size(); i++) {
                this.validatorRequired("产品类别", pdClassesCodeList.get(i).getPdClassesCode());
            }
            this.validatorRequired("创建者ID", iSL231166RsParam.getCrtId());
        }
    }
}


