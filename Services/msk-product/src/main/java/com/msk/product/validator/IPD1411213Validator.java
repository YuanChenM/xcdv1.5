package com.msk.product.validator;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.product.bean.IPD1411213RsParam;

/**
 * Created by gao_min on 2016/10/10.
 */
public class IPD1411213Validator extends BaseValidator<RsRequest<IPD1411213RsParam>> {

    @Override
    public void validatorData(RsRequest<IPD1411213RsParam> entity) {
        this.validatorRequired("身份识别码", entity.getAuth());
        this.validatorRequired("平台编码", entity.getSiteCode());
        this.validatorRequired("登陆的用户ID", entity.getLoginId());
        IPD1411213RsParam param = entity.getParam();
        if (null != param) {
            this.validatorRequired("用户类型", param.getUserType());
            this.validatorRequired("用户ID", param.getUserId());
            this.validatorMaxLength(param.getLgcsCode(),NumberConst.IntDef.INT_TWO,"物流区Code",true);
            this.validatorMaxLength(param.getProductCode(), NumberConst.IntDef.INT_TEN, "产品编码",true);
            this.validatorRequired("举报类型", param.getReportType());
            this.validatorRequired("举报内容", param.getReportDescription());
        }
    }
}