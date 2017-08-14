package com.msk.seller.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.core.entity.SlEpCertItem;
import com.msk.seller.bean.ISL231127RsParam;

import java.util.List;

/**
 * Created by rwf on 2016/2/22.
 */
public class ISL231127Validator extends BaseValidator<RsRequest<ISL231127RsParam>> {

    @Override
    public void validatorData(RsRequest<ISL231127RsParam> entity) {
        ISL231127RsParam iSL231127RsParam = entity.getParam();
            for(int i = 0; i<iSL231127RsParam.getCertInfoList().size();i++){
                this.validatorRequired("企业ID",iSL231127RsParam.getCertInfoList().get(i).getEpId());
                this.validatorRequired("证照ID",iSL231127RsParam.getCertInfoList().get(i).getCertId());
                this.validatorRequired("证照名称",iSL231127RsParam.getCertInfoList().get(i).getCertName());
                this.validatorRequired("创建者ID",iSL231127RsParam.getCertInfoList().get(i).getCrtId());
                List<SlEpCertItem> list = iSL231127RsParam.getCertInfoList().get(i).getCertItemList();
                for(int j=0;j<list.size();j++){
                    this.validatorRequired("证照项目ID",list.get(j).getCertItemId());
                    this.validatorRequired("证照项目名称",list.get(j).getCertItemName());
                    this.validatorRequired("证照项目内容",list.get(j).getCertItemValue());
                    this.validatorRequired("创建者ID",list.get(j).getCrtId());
                }
            }
    }
}
