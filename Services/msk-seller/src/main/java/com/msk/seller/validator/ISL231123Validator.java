package com.msk.seller.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.core.entity.SlEnterprise;

/**
 * Created by cx on 2016/2/17.
 */
public class ISL231123Validator extends BaseValidator<RsRequest<SlEnterprise>> {

    @Override
    public void validatorData(RsRequest<SlEnterprise> entity) {
        SlEnterprise slEnterprise = entity.getParam();
        if (null != slEnterprise) {
            this.validatorRequired("企业ID", slEnterprise.getEpId());
            this.validatorRequired("企业名称", slEnterprise.getEpName());
            this.validatorRequired("三证合一营业执照标志",slEnterprise.getLicType());
            this.validatorRequired("营业执照_名称", slEnterprise.getLicName());
            this.validatorRequired("营业执照_注册号", slEnterprise.getLicNo());
            this.validatorRequired("营业执照_住所", slEnterprise.getLicAddr());
            this.validatorRequired("营业执照_经营类型", slEnterprise.getLicBusiType());
            this.validatorRequired("营业执照_经营范围", slEnterprise.getLicBusiScope());
            this.validatorRequired("营业执照_法人代表", slEnterprise.getLicLegalPerson());
            this.validatorRequired("营业执照_注册资本", slEnterprise.getLicRegCapital());
            this.validatorRequired("营业执照_实收资本", slEnterprise.getLicPaidinCapital());
            this.validatorRequired("营业执照_成立日期", slEnterprise.getLicCrtDate());
            this.validatorRequired("营业执照_营业期限开始", slEnterprise.getLicTermBegin());
            this.validatorRequired("营业执照_营业期限截止", slEnterprise.getLicTermEnd());
            this.validatorRequired("营业执照_营业期限长期标志", slEnterprise.getLicTermUnliimited());
            this.validatorRequired("税务登记证_税务登记证号", slEnterprise.getTaxNo());
            this.validatorRequired("税务登记证_一般增值税纳税人资格认定编号", slEnterprise.getTaxVatNo());
            this.validatorRequired("组织机构代码证_代码", slEnterprise.getOrgNo());
            this.validatorRequired("组织机构代码证_有效期开始", slEnterprise.getOrgTermBegin());
            this.validatorRequired("组织机构代码证_有效期截止", slEnterprise.getOrgTermEnd());
            this.validatorRequired("银行开户许可证_法定代表人", slEnterprise.getBalLegalPerson());
            this.validatorRequired("银行开户许可证_开户银行", slEnterprise.getBalBank());
            this.validatorRequired("银行开户许可证_帐号", slEnterprise.getBalAccount());
            this.validatorRequired("食品流通许可证_许可证编号", slEnterprise.getFdlNo());
            this.validatorRequired("食品流通许可证_有效期开始", slEnterprise.getFdlTermBegin());
            this.validatorRequired("食品流通许可证_有效期截止", slEnterprise.getFdlTermEnd());
            this.validatorRequired("创建者ID", slEnterprise.getCrtId());
        }
    }

}
