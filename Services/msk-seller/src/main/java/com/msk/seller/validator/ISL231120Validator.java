package com.msk.seller.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.core.entity.SlSeller;

/**
 * Created by cx on 2016/2/16.
 */
public class ISL231120Validator extends BaseValidator<RsRequest<SlSeller>> {

    @Override
    public void validatorData(RsRequest<SlSeller> entity) {
        SlSeller slSeller = entity.getParam();
        if (null != slSeller) {
            this.validatorRequired("卖家编码", slSeller.getSlCode());
            this.validatorRequired("卖家账号", slSeller.getSlAccount());
            //this.validatorRequired("区划", slSeller.getSlAreaCode());
            this.validatorRequired("企业ID", slSeller.getEpId());
            this.validatorRequired("卖家主分类", slSeller.getSlMainClass());
            this.validatorRequired("神农客标志", slSeller.getSnkFlg());
            this.validatorRequired("自产型卖家标志", slSeller.getSelfFlg());
            this.validatorRequired("代理型卖家标志", slSeller.getAgentFlg());
            this.validatorRequired("OEM型卖家标志 ", slSeller.getOemFlg());
            this.validatorRequired("神农客贯标审核", slSeller.getSqaStatus());
            this.validatorRequired("卖家分销资格", slSeller.getDistQua());
            this.validatorRequired("卖家开店资格", slSeller.getShopQua());
            this.validatorRequired("创建者ID", slSeller.getCrtId());
        }
    }

}
