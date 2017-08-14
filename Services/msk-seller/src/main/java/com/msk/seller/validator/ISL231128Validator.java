package com.msk.seller.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.core.entity.SlEpCertItem;
import com.msk.seller.bean.ISL231127RsParam;
import com.msk.seller.bean.ISL231128RsParam;
import com.msk.seller.bean.SL24110302_1Bean;

import java.util.List;

/**
 * Created by rwf on 2016/2/22.
 */
public class ISL231128Validator extends BaseValidator<RsRequest<ISL231128RsParam>> {

    @Override
    public void validatorData(RsRequest<ISL231128RsParam> entity) {
        ISL231128RsParam iSL231128RsParam = entity.getParam();
            for(int i = 0; i<iSL231128RsParam.getCertInfoList().size();i++){
                this.validatorRequired("企业ID",iSL231128RsParam.getCertInfoList().get(i).getEpId());
                this.validatorRequired("证照ID",iSL231128RsParam.getCertInfoList().get(i).getCertId());
                this.validatorRequired("证照顺序号",iSL231128RsParam.getCertInfoList().get(i).getCertSeq());
                List<SL24110302_1Bean> list = iSL231128RsParam.getCertInfoList().get(i).getCertItemList();
                for(int j=0;j<list.size();j++){
                    this.validatorRequired("证照项目ID",list.get(j).getCertItemId());
                    this.validatorRequired("证照项目内容",list.get(j).getCertItemValue());
                    this.validatorRequired("证照顺序号",list.get(j).getCertSeq());
                    this.validatorRequired("企业证照项目顺序号",list.get(j).getCertItemSeq());
                    this.validatorRequired("证照ID",list.get(j).getCertId());
                    this.validatorRequired("更新者ID",list.get(j).getUpdId());
                    this.validatorRequired("版本号",list.get(j).getVer());
                }
            }
    }
}
