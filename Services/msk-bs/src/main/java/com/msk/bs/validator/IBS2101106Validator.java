package com.msk.bs.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.bs.bean.IBS2101106Bean;
import com.msk.common.bean.RsRequest;

/**
 * Created by cx on 2016/2/29.
 */
public class IBS2101106Validator extends BaseValidator<RsRequest<IBS2101106Bean>> {

    @Override
    public void validatorData(RsRequest<IBS2101106Bean> entity) {
        IBS2101106Bean iBS2101106Bean = entity.getParam();
        if (null != iBS2101106Bean) {
            if(null!=iBS2101106Bean.getDelFlg()&&"1".equals(iBS2101106Bean.getDelFlg())){
                this.validatorRequired("删除操作：关系标志 1:专属买家、2：抢单买家",iBS2101106Bean.getBuyerFlag());
                this.validatorRequired("删除操作：买手ID",iBS2101106Bean.getSlCode());
                this.validatorRequired("删除操作：管家ID",iBS2101106Bean.getHouseCode());
                this.validatorRequired("删除操作：买家ID",iBS2101106Bean.getBuyerId());
            }else{
                this.validatorRequired("新增操作：关系标志 1:专属买家、2：抢单买家",iBS2101106Bean.getBuyerFlag());
                this.validatorRequired("新增操作：买手店ID",iBS2101106Bean.getSlCode());
                this.validatorRequired("新增操作：管家ID",iBS2101106Bean.getHouseCode());
                this.validatorRequired("新增操作：买家ID",iBS2101106Bean.getBuyerId());
            }
        }
    }

}






