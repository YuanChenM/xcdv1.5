package com.msk.seller.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.core.entity.SlDistReguChap;
import com.msk.core.entity.SlDistReguSug;
import com.msk.core.entity.SlDistSugHis;
import com.msk.seller.bean.ISL231201RsParam;
import com.msk.seller.bean.ISL231202RsParam;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * ISL231203Validator.
 *
 * @author gyh
 */
public class ISL231203Validator extends BaseValidator<RsRequest<SlDistReguSug>> {

    @Override
    public void validatorData(RsRequest<SlDistReguSug> request) {
        SlDistReguSug param = request.getParam();
        if (null != param) {
            this.validatorRequired("卖家编码", param.getSlCode());
            this.validatorRequired("类别", param.getChapClass());
            this.validatorRequired("章节ID", param.getChapId());
            this.validatorRequired("同意标志", param.getAgreeFlg());
            this.validatorRequired("更新者ID", param.getUpdId());
            this.validatorRequired("版本号", param.getVer());
        }
    }
}
