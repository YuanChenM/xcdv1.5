package com.msk.price.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.price.bean.*;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * Created by yang_chunyan on 2016/10/18.
 */
public class ISP171185Validator extends BaseValidator<RsRequest<ISP171185Param>> {
    @Override
    public void validatorData(RsRequest<ISP171185Param> request) {
        ISP171185Param pricePlateInfoParam=request.getParam();
        if(null!=pricePlateInfoParam){
            if(!CollectionUtils.isEmpty(pricePlateInfoParam.getProductList())){
                int i = 0;
                for (ISP171185Bean param : pricePlateInfoParam.getProductList()) {
                    i++;
                    if(!StringUtils.hasLength(param.getLogiAreaCode())) {
                        this.validatorRequired("第" + i + "组数据的物流区Code", param.getLogiAreaCode());
                        break;
                    }
                    if(!StringUtils.hasLength(param.getPdCode())) {
                        this.validatorRequired("第" + i + "组数据的产品编码", param.getPdCode());
                        break;
                    }
                }
            }else{
                this.validatorRequired("产品信息列表", pricePlateInfoParam.getProductList());
            }
        }else{
            this.validatorRequired("参数", pricePlateInfoParam);
        }
    }
}
