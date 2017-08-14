package com.msk.ssc.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.ssc.bean.SSC11310RsParam;

/**
 * 
 * SSC11310Validator.
 *
 * @author liu_yan2
 */
public class SSC11310Validator extends BaseValidator<RsRequest<SSC11310RsParam>> {

    @Override
    public void validatorData(RsRequest<SSC11310RsParam> request) {
        SSC11310RsParam param = request.getParam();
        this.validatorRequired("业务参数", param);
        if(null!= param){
            this.validatorRequired("入库单ID", param.getIntoId());
        }
    }


}
