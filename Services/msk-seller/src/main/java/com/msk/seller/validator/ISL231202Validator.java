package com.msk.seller.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.core.entity.SlDistReguSug;
import com.msk.seller.bean.ISL231201RsParam;
import com.msk.seller.bean.ISL231202RsParam;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * ISL231202Validator.
 *
 * @author gyh
 */
public class ISL231202Validator extends BaseValidator<RsRequest<ISL231202RsParam>> {

    @Override
    public void validatorData(RsRequest<ISL231202RsParam> request) {
        ISL231202RsParam param = request.getParam();
        if (null != param) {
            List<SlDistReguSug> slSugs=param.getSlSugs();
            if(!CollectionUtils.isEmpty(slSugs)){
                for(SlDistReguSug sug:slSugs){
                    this.validatorRequired("卖家编码", sug.getSlCode());
                    this.validatorRequired("类别", sug.getChapClass());
                    this.validatorRequired("章节ID", sug.getChapId());
                    this.validatorRequired("同意标志", sug.getAgreeFlg());
                }
            }
        }
    }
}
