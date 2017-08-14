package com.msk.buyers.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.buyers.bean.BuyerRelationParam;
import com.msk.common.bean.RsRequest;
import org.springframework.util.CollectionUtils;

/**
 * Created by zhu_kai1 on 2016/6/13.
 */
public class BuyerBasicInfoValidator extends BaseValidator<RsRequest<BuyerRelationParam>> {
    @Override
    public void validatorData(RsRequest<BuyerRelationParam> request) {
        BuyerRelationParam buyerRelationParam = request.getParam();
        if(null!=buyerRelationParam){
            if(!CollectionUtils.isEmpty(buyerRelationParam.getBuyerIdList())){
                for (String buyerId : buyerRelationParam.getBuyerIdList()){
                    this.validatorRequired("买家id", buyerId);
                }
            }
        }
    }
}
