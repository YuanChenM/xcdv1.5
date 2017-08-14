package com.msk.bs.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.bs.bean.IBS2101102RsParam;
import com.msk.common.bean.RsRequest;

/**
 * Created by cx on 2016/2/25.
 */
public class IBS2101102Validator extends BaseValidator<RsRequest<IBS2101102RsParam>> {

    @Override
    public void validatorData(RsRequest<IBS2101102RsParam> entity) {
        IBS2101102RsParam iBS2101102RsParam = entity.getParam();
        if (null != iBS2101102RsParam) {
            if (null != iBS2101102RsParam.getSlAccount()) {
                this.validatorRequired("买手账号", iBS2101102RsParam.getSlAccount().getSlAccount());
                this.validatorRequired("手机号码", iBS2101102RsParam.getSlAccount().getSlTel());
                this.validatorRequired("联系人姓名", iBS2101102RsParam.getSlAccount().getSlContact());
                this.validatorRequired("登录密码",iBS2101102RsParam.getSlAccount().getAccountPsd());
                this.validatorRequired("注册来源",iBS2101102RsParam.getSlAccount().getFromFlg());
            }
            if (null != iBS2101102RsParam.getSlSeller()) {
                this.validatorRequired("生产国籍", iBS2101102RsParam.getSlSeller().getSlConFlg());
                this.validatorRequired("省编码", iBS2101102RsParam.getSlSeller().getProvinceCode());
                this.validatorRequired("地区编码", iBS2101102RsParam.getSlSeller().getCityCode());
                this.validatorRequired("区编码", iBS2101102RsParam.getSlSeller().getDistrictCode());
                this.validatorRequired("卖家主分类", iBS2101102RsParam.getSlSeller().getSlMainClass());
                this.validatorRequired("神农客标志", iBS2101102RsParam.getSlSeller().getSnkFlg());
                this.validatorRequired("自产型卖家标志", iBS2101102RsParam.getSlSeller().getSelfFlg());
                this.validatorRequired("代理型卖家标志", iBS2101102RsParam.getSlSeller().getAgentFlg());
                this.validatorRequired("OEM型卖家标志", iBS2101102RsParam.getSlSeller().getOemFlg());
                this.validatorRequired("买手型卖家标志", iBS2101102RsParam.getSlSeller().getBuyerFlg());
                if (iBS2101102RsParam.getSlSeller().getSlCode() == null || "".equals(iBS2101102RsParam.getSlSeller().getSlCode())) {
                    this.validatorRequired("买手类型", iBS2101102RsParam.getSlSeller().getMemo6());
                }
            }
            if (null != iBS2101102RsParam.getSlBuyerShop()) {
                this.validatorRequired("买手身份证号", iBS2101102RsParam.getSlBuyerShop().getSlIdcard());
                this.validatorRequired("合营优先顺方式", iBS2101102RsParam.getSlBuyerShop().getSlSort());
                this.validatorRequired("买手地址", iBS2101102RsParam.getSlBuyerShop().getSlAddress());
            }
            if (null != iBS2101102RsParam.getSlShopInfo()) {
                this.validatorRequired("店铺Logo",iBS2101102RsParam.getSlShopInfo().getShopLogo());
                this.validatorRequired("店铺名称",iBS2101102RsParam.getSlShopInfo().getShopName());
            }
        }
    }

}
