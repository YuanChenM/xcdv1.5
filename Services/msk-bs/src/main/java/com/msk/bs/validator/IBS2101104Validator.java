package com.msk.bs.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.bs.bean.IBS2101104RsParam;
import com.msk.bs.bean.IBS2101104SlHouseAccount;
import com.msk.bs.bean.IBS2101104SlHouseArea;
import com.msk.bs.bean.IBS2101104SlHouseProduct;
import com.msk.common.bean.RsRequest;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by cx on 2016/2/29.
 */
public class IBS2101104Validator extends BaseValidator<RsRequest<IBS2101104RsParam>> {

    @Override
    public void validatorData(RsRequest<IBS2101104RsParam> entity) {
        IBS2101104RsParam param = entity.getParam();
        IBS2101104SlHouseAccount slHouseAccount = param.getSlHouseAccount();
        //经营区域List
        List<IBS2101104SlHouseArea> slAreaList = param.getSlAreaList();
        //冻品管家产品分类list
        List<IBS2101104SlHouseProduct> housePdList = param.getHousePdList();
        if(slHouseAccount!=null){
            if(slHouseAccount.getHouseCode()==null||"".equals(slHouseAccount.getHouseCode())){
                this.validatorRequired("买手店id", slHouseAccount.getSlCode());
                this.validatorRequired("管家账号", slHouseAccount.getHouseAccount());
                this.validatorRequired("登录手机号码", slHouseAccount.getHouseTel());
                this.validatorRequired("登录密码", slHouseAccount.getAccountPsd());
                this.validatorRequired("管家身份证号", slHouseAccount.getSlIdcard());
                /*this.validatorRequired("国籍", slHouseAccount.getSlConFlg());
                this.validatorRequired("管家级别", slHouseAccount.getHouseClass());
                this.validatorRequired("管家分类", slHouseAccount.getHouseCategory());*/
            }
        }
        //更新经营区域
//        if (!CollectionUtils.isEmpty(slAreaList) && slAreaList.size() > 0) {
//            for (IBS2101104SlHouseArea area : slAreaList) {
//                if(area.getSlAreaId()==null||"".equals(area.getSlAreaId())){
//                    this.validatorRequired("买手店编码", area.getSlCode());
//                    this.validatorRequired("物流区编码", area.getLgcsAreaCode());
//                    this.validatorRequired("省编码", area.getProvinceCode());
//                    this.validatorRequired("地区编码", area.getCityCode());
//                    this.validatorRequired("区编码", area.getDistrictCode());
//                    this.validatorRequired("经营地址", area.getAddress());
//                }
//            }
//        }
        //更新冻品管家产品分类
        if (!CollectionUtils.isEmpty(housePdList) && housePdList.size() > 0) {
            for (IBS2101104SlHouseProduct product : housePdList) {
                if(product.getPdId()==null){
                    this.validatorRequired("买手店编码", product.getSlCode());
                    this.validatorRequired("产品类别", product.getPdClassesCode());
                    this.validatorRequired("产品二级分类编码", product.getMachiningCode());
                    this.validatorRequired("产品品种", product.getPdBreedCode());
                    this.validatorRequired("产品特征", product.getPdFeatureCode());
                }else{
                    this.validatorRequired("管家管理产品ID", product.getPdId());
                }
            }
        }
    }
}

