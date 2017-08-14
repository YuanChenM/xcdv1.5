package com.msk.seller.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.hoperun.core.consts.NumberConst;
import com.msk.core.entity.*;
import com.msk.core.entity.SlEpHonor;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.msk.seller.bean.*;

/**
 * Created by cx on 2016/2/23.
 */
public class ISL231180Validator extends BaseValidator<RsRequest<ISL231180RsParam>> {

    @Override
    public void validatorData(RsRequest<ISL231180RsParam> entity) {
        ISL231180RsParam isl231180RsParam =entity.getParam();
        this.validatorRequired("创建者ID/更新者ID",isl231180RsParam.getLoginId());
        if("1".equals(isl231180RsParam.getDelFlg())){
            if (null != isl231180RsParam.getSlAccount()) {
                this.validatorRequired("卖家账号", isl231180RsParam.getSlAccount().getSlAccount());
            }
        }
        if(null!=isl231180RsParam.getInsertFlag()) {
            if (null!=isl231180RsParam.getInsertFlag() && isl231180RsParam.getInsertFlag() == NumberConst.IntDef.INT_ZERO) {
                if (null != isl231180RsParam.getSlAccount()) {
                    if(null!=isl231180RsParam.getManufactureFlag() && NumberConst.IntDef.INT_ONE==isl231180RsParam.getManufactureFlag()){
                        this.validatorRequired("注册来源", isl231180RsParam.getSlAccount().getFromFlg());
                    }else{
                        this.validatorRequired("卖家账号", isl231180RsParam.getSlAccount().getSlAccount());
                        this.validatorRequired("登录密码", isl231180RsParam.getSlAccount().getAccountPsd());
                        this.validatorRequired("登录手机号码", isl231180RsParam.getSlAccount().getSlTel());
                        this.validatorRequired("卖家显示名称", isl231180RsParam.getSlAccount().getSlShowName());
                        this.validatorRequired("联系人姓名", isl231180RsParam.getSlAccount().getSlContact());
                        this.validatorRequired("认证状态", isl231180RsParam.getSlAccount().getAuthStatus());
                        this.validatorRequired("注册来源", isl231180RsParam.getSlAccount().getFromFlg());
                    }
                }
                if (null != isl231180RsParam.getSlSeller()) {
                    if(null!=isl231180RsParam.getManufactureFlag() && NumberConst.IntDef.INT_ONE==isl231180RsParam.getManufactureFlag()){
                        this.validatorRequired("生产国籍", isl231180RsParam.getSlSeller().getSlConFlg());
                        this.validatorRequired("省编码", isl231180RsParam.getSlSeller().getProvinceCode());
                        this.validatorRequired("地区编码", isl231180RsParam.getSlSeller().getCityCode());
                        this.validatorRequired("区编码", isl231180RsParam.getSlSeller().getDistrictCode());
                        this.validatorRequired("卖家主分类", isl231180RsParam.getSlSeller().getSlMainClass());
                        this.validatorRequired("神农客标志", isl231180RsParam.getSlSeller().getSnkFlg());
                        this.validatorRequired("自产型卖家标志", isl231180RsParam.getSlSeller().getSelfFlg());
                        this.validatorRequired("代理型卖家标志", isl231180RsParam.getSlSeller().getAgentFlg());
                        this.validatorRequired("OEM型卖家标志", isl231180RsParam.getSlSeller().getOemFlg());
                        this.validatorRequired("买手型卖家标志", isl231180RsParam.getSlSeller().getBuyerFlg());
                    }else{
                        this.validatorRequired("卖家账号", isl231180RsParam.getSlSeller().getSlAccount());
                        this.validatorRequired("生产国籍", isl231180RsParam.getSlSeller().getSlConFlg());
                        this.validatorRequired("省编码", isl231180RsParam.getSlSeller().getProvinceCode());
                        this.validatorRequired("地区编码", isl231180RsParam.getSlSeller().getCityCode());
                        this.validatorRequired("区编码", isl231180RsParam.getSlSeller().getDistrictCode());
                        this.validatorRequired("卖家主分类", isl231180RsParam.getSlSeller().getSlMainClass());
                        this.validatorRequired("神农客标志", isl231180RsParam.getSlSeller().getSnkFlg());
                        this.validatorRequired("自产型卖家标志", isl231180RsParam.getSlSeller().getSelfFlg());
                        this.validatorRequired("代理型卖家标志", isl231180RsParam.getSlSeller().getAgentFlg());
                        this.validatorRequired("OEM型卖家标志", isl231180RsParam.getSlSeller().getOemFlg());
                        this.validatorRequired("买手型卖家标志", isl231180RsParam.getSlSeller().getBuyerFlg());
                    }
                }
                if (null != isl231180RsParam.getPdClassesCodeList()) {
                    for (SlPdClasses slPdClasses : isl231180RsParam.getPdClassesCodeList()) {
                        this.validatorRequired("产品类别", slPdClasses.getPdClassesCode());
                        this.validatorRequired("产品加工程度编码", slPdClasses.getMachiningCode());
                    }
                }
                if (null != isl231180RsParam.getSlEnterprise()) {
                    this.validatorRequired("企业名称", isl231180RsParam.getSlEnterprise().getEpName());
                    this.validatorRequired("三证合一营业执照标志", isl231180RsParam.getSlEnterprise().getLicType());
                    this.validatorRequired("营业执照_名称", isl231180RsParam.getSlEnterprise().getLicName());
                    this.validatorRequired("税务登记证_税务登记证号", isl231180RsParam.getSlEnterprise().getTaxNo());
                    this.validatorRequired("组织机构代码证_代码", isl231180RsParam.getSlEnterprise().getOrgNo());
                }
                if (null != isl231180RsParam.getCertInfoList()) {
                    for (ISL231127CertInfoList isl231127CertInfoList : isl231180RsParam.getCertInfoList()) {
                        this.validatorRequired("证照名称", isl231127CertInfoList.getCertName());
                        if (null != isl231127CertInfoList.getCertItemList()) {
                            for (SlEpCertItem slEpCertItem : isl231127CertInfoList.getCertItemList()) {
                                this.validatorRequired("证照项目名称", slEpCertItem.getCertItemName());
                                this.validatorRequired("证照项目内容", slEpCertItem.getCertItemValue());
                            }
                        }
                    }
                }
                if (null != isl231180RsParam.getSlEpHonorList()) {
                    for (SlEpHonor slEpHonor : isl231180RsParam.getSlEpHonorList()) {
                        this.validatorRequired("荣誉描述", slEpHonor.getHonorDesc());
                    }
                }
                if (null != isl231180RsParam.getSlEpBrandList()) {
                    for (ISL231180SlEpBrandList isl231180SlEpBrandList : isl231180RsParam.getSlEpBrandList()) {
                        this.validatorRequired("品牌名称", isl231180SlEpBrandList.getBrandName());
                        this.validatorRequired("品牌分类", isl231180SlEpBrandList.getBrandClass());
                        this.validatorRequired("商标注册证", isl231180SlEpBrandList.getBrandNo());
                        if (null != isl231180SlEpBrandList.getSlEpBrandHonorList()) {
                            for (SlEpBrandHonor slEpBrandHonor : isl231180SlEpBrandList.getSlEpBrandHonorList()) {
                                this.validatorRequired("企业产品品牌荣誉描述", slEpBrandHonor.getHonorDes());
                            }
                        }
                    }
                }

                if (null != isl231180RsParam.getSlPdBrandList()) {
                    for (ISlPdBrand slPdBrand : isl231180RsParam.getSlPdBrandList()) {
                        //this.validatorRequired("品牌所属企业ID", slPdBrand.getBrandEpId());
                        //this.validatorRequired("品牌ID", slPdBrand.getBrandId());
                        this.validatorRequired("品牌分类", slPdBrand.getBrandClass());
                    }
                }
                if (null != isl231180RsParam.getSlEpWorkshopList()) {
                    for (SlEpWorkshop slEpWorkshop : isl231180RsParam.getSlEpWorkshopList()) {
                        this.validatorRequired("车间名称", slEpWorkshop.getWorkshopName());
                    }
                }
                if (null != isl231180RsParam.getSlEpCap()) {
                    this.validatorRequired("厂区_总资产", isl231180RsParam.getSlEpCap().getFtyAsset());
                    this.validatorRequired("厂区_注册资本", isl231180RsParam.getSlEpCap().getFtyRegCapital());
                    this.validatorRequired("厂区_占地面积", isl231180RsParam.getSlEpCap().getFtyLandArea());
                    this.validatorRequired("厂区_厂房面积", isl231180RsParam.getSlEpCap().getFtyFloorArea());
                    this.validatorRequired("厂区_主要设备", isl231180RsParam.getSlEpCap().getFtyEquipment());
                    this.validatorRequired("厂区_设计产能", isl231180RsParam.getSlEpCap().getFtyDesignCap());
                    this.validatorRequired("厂区_实际产能", isl231180RsParam.getSlEpCap().getFtyActualCap());
                    this.validatorRequired("厂区_外贸销售占比", isl231180RsParam.getSlEpCap().getFtyFtRate());
                    this.validatorRequired("厂区_直销占比", isl231180RsParam.getSlEpCap().getFtyDsRate());
                    this.validatorRequired("厂区_代理销售占比", isl231180RsParam.getSlEpCap().getFtyAsRate());
                    this.validatorRequired("库容概括_原料库容", isl231180RsParam.getSlEpCap().getScapMaterial());
                    this.validatorRequired("库容概括_成品库容", isl231180RsParam.getSlEpCap().getScapProduct());
                }
                if (null != isl231180RsParam.getSlEpAuthList()) {
                    for (ISL231180SLEpAuth isl231180SLEpAuth : isl231180RsParam.getSlEpAuthList()) {
                        this.validatorRequired("1：卖家代理及分销授权:2：卖家OEM委托授权标志", isl231180SLEpAuth.getFlag());
                        /*this.validatorRequired("生产商_企业ID", isl231180SLEpAuth.getProducerEpId());*/
                    }
                }
                if (null != isl231180RsParam.getSlEpManagerList()) {
                    for (SlEpManager slEpManager : isl231180RsParam.getSlEpManagerList()) {
                        this.validatorRequired("职务", slEpManager.getMemberDuties());
                        this.validatorRequired("姓名", slEpManager.getMemberName());
                    }
                }
                if (null != isl231180RsParam.getSlEcTeamList()) {
                    for (SlEcTeam slEcTeam : isl231180RsParam.getSlEcTeamList()) {
                        this.validatorRequired("是否负责人", slEcTeam.getLeaderFlg());
                        this.validatorRequired("姓名", slEcTeam.getMemberName());
                    }
                }
                if (null != isl231180RsParam.getSlEpDdList()) {
                    for (SlEpDd slEpDd : isl231180RsParam.getSlEpDdList()) {
                        this.validatorRequired("设备名称", slEpDd.getDdName());
                    }
                }
            } else if (null!=isl231180RsParam.getInsertFlag() && isl231180RsParam.getInsertFlag() == NumberConst.IntDef.INT_ONE) {
                if (null != isl231180RsParam.getSlAccount()) {
                        this.validatorRequired("卖家账号", isl231180RsParam.getSlAccount().getSlAccount());
                        this.validatorRequired("登录密码", isl231180RsParam.getSlAccount().getAccountPsd());
                        this.validatorRequired("登录手机号码", isl231180RsParam.getSlAccount().getSlTel());
                        this.validatorRequired("卖家显示名称", isl231180RsParam.getSlAccount().getSlShowName());
                        this.validatorRequired("联系人姓名", isl231180RsParam.getSlAccount().getSlContact());
                        this.validatorRequired("认证状态", isl231180RsParam.getSlAccount().getAuthStatus());
                        this.validatorRequired("注册来源", isl231180RsParam.getSlAccount().getFromFlg());
                }
                if (null != isl231180RsParam.getSlSeller()) {
                    this.validatorRequired("卖家账号", isl231180RsParam.getSlSeller().getSlAccount());
                    this.validatorRequired("生产国籍", isl231180RsParam.getSlSeller().getSlConFlg());
                    this.validatorRequired("省编码", isl231180RsParam.getSlSeller().getProvinceCode());
                    this.validatorRequired("地区编码", isl231180RsParam.getSlSeller().getCityCode());
                    this.validatorRequired("区编码", isl231180RsParam.getSlSeller().getDistrictCode());
                    this.validatorRequired("卖家主分类", isl231180RsParam.getSlSeller().getSlMainClass());
                    this.validatorRequired("神农客标志", isl231180RsParam.getSlSeller().getSnkFlg());
                    this.validatorRequired("自产型卖家标志", isl231180RsParam.getSlSeller().getSelfFlg());
                    this.validatorRequired("代理型卖家标志", isl231180RsParam.getSlSeller().getAgentFlg());
                    this.validatorRequired("OEM型卖家标志", isl231180RsParam.getSlSeller().getOemFlg());
                    this.validatorRequired("买手型卖家标志", isl231180RsParam.getSlSeller().getBuyerFlg());
                }
                if (null != isl231180RsParam.getPdClassesCodeList()) {
                    for (SlPdClasses slPdClasses : isl231180RsParam.getPdClassesCodeList()) {
                        this.validatorRequired("卖家ID", slPdClasses.getSlCode());
                        this.validatorRequired("产品类别", slPdClasses.getPdClassesCode());
                        this.validatorRequired("产品加工程度编码", slPdClasses.getMachiningCode());
                    }
                }
                if (null != isl231180RsParam.getSlEnterprise()) {
                    this.validatorRequired("卖家ID", isl231180RsParam.getSlEnterprise().getSlCode());
                    this.validatorRequired("企业名称", isl231180RsParam.getSlEnterprise().getEpName());
                    this.validatorRequired("三证合一营业执照标志", isl231180RsParam.getSlEnterprise().getLicType());
                    this.validatorRequired("营业执照_名称", isl231180RsParam.getSlEnterprise().getLicName());
                    this.validatorRequired("税务登记证_税务登记证号", isl231180RsParam.getSlEnterprise().getTaxNo());
                    this.validatorRequired("组织机构代码证_代码", isl231180RsParam.getSlEnterprise().getOrgNo());
                }
                if (null != isl231180RsParam.getCertInfoList()) {
                    for (ISL231127CertInfoList isl231127CertInfoList : isl231180RsParam.getCertInfoList()) {
                        this.validatorRequired("企业ID", isl231127CertInfoList.getEpId());
                        this.validatorRequired("证照名称", isl231127CertInfoList.getCertName());
                        if (null != isl231127CertInfoList.getCertItemList()) {
                            for (SlEpCertItem slEpCertItem : isl231127CertInfoList.getCertItemList()) {
                                this.validatorRequired("证照项目名称", slEpCertItem.getCertItemName());
                                this.validatorRequired("证照项目内容", slEpCertItem.getCertItemValue());
                            }
                        }
                    }
                }
                if (null != isl231180RsParam.getSlEpHonorList()) {
                    for (SlEpHonor slEpHonor : isl231180RsParam.getSlEpHonorList()) {
                        this.validatorRequired("企业ID", slEpHonor.getEpId());
                        this.validatorRequired("荣誉描述", slEpHonor.getHonorDesc());
                    }
                }
                if (null != isl231180RsParam.getSlEpBrandList()) {
                    for (ISL231180SlEpBrandList isl231180SlEpBrandList : isl231180RsParam.getSlEpBrandList()) {
                        if(StringUtil.isNullOrEmpty(StringUtil.toSafeString(isl231180SlEpBrandList.getEpId()))){
                            if (null != isl231180SlEpBrandList.getSlEpBrandHonorList()) {
                                for (SlEpBrandHonor slEpBrandHonor : isl231180SlEpBrandList.getSlEpBrandHonorList()) {
                                    this.validatorRequired("企业ID", slEpBrandHonor.getEpId());
                                    this.validatorRequired("企业产品品牌荣誉描述", slEpBrandHonor.getHonorDes());
                                }
                            }
                        }else {
                            this.validatorRequired("企业ID", isl231180SlEpBrandList.getEpId());
                            this.validatorRequired("品牌名称", isl231180SlEpBrandList.getBrandName());
                            this.validatorRequired("品牌分类", isl231180SlEpBrandList.getBrandClass());
                            this.validatorRequired("商标注册证", isl231180SlEpBrandList.getBrandNo());
                            if (null != isl231180SlEpBrandList.getSlEpBrandHonorList()) {
                                for (SlEpBrandHonor slEpBrandHonor : isl231180SlEpBrandList.getSlEpBrandHonorList()) {
                                    this.validatorRequired("企业ID", slEpBrandHonor.getEpId());
                                    this.validatorRequired("企业产品品牌荣誉描述", slEpBrandHonor.getHonorDes());
                                }
                            }
                        }
                    }
                }
                if (null != isl231180RsParam.getSlPdBrandList()) {
                    for (ISlPdBrand slPdBrand : isl231180RsParam.getSlPdBrandList()) {
                        this.validatorRequired("卖家ID", slPdBrand.getSlCode());
                        this.validatorRequired("品牌所属企业ID", slPdBrand.getBrandEpId());
                        this.validatorRequired("品牌分类", slPdBrand.getBrandClass());
                    }
                }
                if (null != isl231180RsParam.getSlEpWorkshopList()) {
                    for (SlEpWorkshop slEpWorkshop : isl231180RsParam.getSlEpWorkshopList()) {
                        this.validatorRequired("企业ID", slEpWorkshop.getEpId());
                        this.validatorRequired("车间名称", slEpWorkshop.getWorkshopName());
                    }
                }
                if (null != isl231180RsParam.getSlEpCap()) {
                    this.validatorRequired("企业ID", isl231180RsParam.getSlEpCap().getEpId());
                    this.validatorRequired("厂区_总资产", isl231180RsParam.getSlEpCap().getFtyAsset());
                    this.validatorRequired("厂区_注册资本", isl231180RsParam.getSlEpCap().getFtyRegCapital());
                    this.validatorRequired("厂区_占地面积", isl231180RsParam.getSlEpCap().getFtyLandArea());
                    this.validatorRequired("厂区_厂房面积", isl231180RsParam.getSlEpCap().getFtyFloorArea());
                    this.validatorRequired("厂区_主要设备", isl231180RsParam.getSlEpCap().getFtyEquipment());
                    this.validatorRequired("厂区_设计产能", isl231180RsParam.getSlEpCap().getFtyDesignCap());
                    this.validatorRequired("厂区_实际产能", isl231180RsParam.getSlEpCap().getFtyActualCap());
                    this.validatorRequired("厂区_外贸销售占比", isl231180RsParam.getSlEpCap().getFtyFtRate());
                    this.validatorRequired("厂区_直销占比", isl231180RsParam.getSlEpCap().getFtyDsRate());
                    this.validatorRequired("厂区_代理销售占比", isl231180RsParam.getSlEpCap().getFtyAsRate());
                    this.validatorRequired("库容概括_原料库容", isl231180RsParam.getSlEpCap().getScapMaterial());
                    this.validatorRequired("库容概括_成品库容", isl231180RsParam.getSlEpCap().getScapProduct());
                }
                if (null != isl231180RsParam.getSlEpAuthList()) {
                    for (ISL231180SLEpAuth isl231180SLEpAuth : isl231180RsParam.getSlEpAuthList()) {
                        this.validatorRequired("卖家ID", isl231180SLEpAuth.getSlCode());
                        this.validatorRequired("1：卖家代理及分销授权:2：卖家OEM委托授权标志", isl231180SLEpAuth.getFlag());
                        this.validatorRequired("生产商_企业ID", isl231180SLEpAuth.getProducerEpId());
                        this.validatorRequired("删除标志", isl231180SLEpAuth.getDelFlg());
                    }
                }
                if (null != isl231180RsParam.getSlEpManagerList()) {
                    for (SlEpManager slEpManager : isl231180RsParam.getSlEpManagerList()) {
                        this.validatorRequired("企业ID", slEpManager.getEpId());
                        this.validatorRequired("职务", slEpManager.getMemberDuties());
                        this.validatorRequired("姓名", slEpManager.getMemberName());
                    }
                }
                if (null != isl231180RsParam.getSlEcTeamList()) {
                    for (SlEcTeam slEcTeam : isl231180RsParam.getSlEcTeamList()) {
                        this.validatorRequired("卖家ID", slEcTeam.getSlCode());
                        this.validatorRequired("是否负责人", slEcTeam.getLeaderFlg());
                        this.validatorRequired("姓名", slEcTeam.getMemberName());
                    }
                }
                if (null != isl231180RsParam.getSlEpDdList()) {
                    for (SlEpDd slEpDd : isl231180RsParam.getSlEpDdList()) {
                        this.validatorRequired("企业ID", slEpDd.getEpId());
                        this.validatorRequired("设备名称", slEpDd.getDdName());
                    }
                }
            }else{
                throw new BusinessException("insertFlag超出范围");
            }
        }
        else {
            if (null != isl231180RsParam.getSlAccount()) {
                this.validatorRequired("卖家账号", isl231180RsParam.getSlAccount().getSlAccount());
                this.validatorRequired("登录密码", isl231180RsParam.getSlAccount().getAccountPsd());
                this.validatorRequired("登录手机号码", isl231180RsParam.getSlAccount().getSlTel());
                this.validatorRequired("卖家显示名称", isl231180RsParam.getSlAccount().getSlShowName());
                this.validatorRequired("联系人姓名", isl231180RsParam.getSlAccount().getSlContact());
                this.validatorRequired("认证状态", isl231180RsParam.getSlAccount().getAuthStatus());
                this.validatorRequired("注册来源", isl231180RsParam.getSlAccount().getFromFlg());
            }
            if (null != isl231180RsParam.getSlSeller()) {
                this.validatorRequired("卖家ID", isl231180RsParam.getSlSeller().getSlCode());
                this.validatorRequired("生产国籍", isl231180RsParam.getSlSeller().getSlConFlg());
                this.validatorRequired("省编码", isl231180RsParam.getSlSeller().getProvinceCode());
                this.validatorRequired("地区编码", isl231180RsParam.getSlSeller().getCityCode());
                this.validatorRequired("区编码", isl231180RsParam.getSlSeller().getDistrictCode());
                this.validatorRequired("卖家主分类", isl231180RsParam.getSlSeller().getSlMainClass());
                this.validatorRequired("神农客标志", isl231180RsParam.getSlSeller().getSnkFlg());
                this.validatorRequired("自产型卖家标志", isl231180RsParam.getSlSeller().getSelfFlg());
                this.validatorRequired("代理型卖家标志", isl231180RsParam.getSlSeller().getAgentFlg());
                this.validatorRequired("OEM型卖家标志", isl231180RsParam.getSlSeller().getOemFlg());
                this.validatorRequired("买手型卖家标志", isl231180RsParam.getSlSeller().getBuyerFlg());
            }
            if (null != isl231180RsParam.getPdClassesCodeList()) {
                for (SlPdClasses slPdClasses : isl231180RsParam.getPdClassesCodeList()) {
                    this.validatorRequired("卖家ID", slPdClasses.getSlCode());
                    this.validatorRequired("产品类别", slPdClasses.getPdClassesCode());
                    this.validatorRequired("产品加工程度编码", slPdClasses.getMachiningCode());
                }
            }
            if (null != isl231180RsParam.getSlEnterprise()) {
                this.validatorRequired("企业ID", isl231180RsParam.getSlEnterprise().getEpId());
                this.validatorRequired("企业名称", isl231180RsParam.getSlEnterprise().getEpName());
                this.validatorRequired("三证合一营业执照标志", isl231180RsParam.getSlEnterprise().getLicType());
                this.validatorRequired("营业执照_名称", isl231180RsParam.getSlEnterprise().getLicName());
                this.validatorRequired("税务登记证_税务登记证号", isl231180RsParam.getSlEnterprise().getTaxNo());
                this.validatorRequired("组织机构代码证_代码", isl231180RsParam.getSlEnterprise().getOrgNo());
            }
            if (null != isl231180RsParam.getCertInfoList()) {
                for (ISL231127CertInfoList isl231127CertInfoList : isl231180RsParam.getCertInfoList()) {
                    this.validatorRequired("企业ID", isl231127CertInfoList.getEpId());
                    this.validatorRequired("证照ID", isl231127CertInfoList.getCertId());
                    this.validatorRequired("证照名称", isl231127CertInfoList.getCertName());
                    if (null != isl231127CertInfoList.getCertItemList()) {
                        for (SlEpCertItem slEpCertItem : isl231127CertInfoList.getCertItemList()) {
                            this.validatorRequired("证照项目ID", slEpCertItem.getCertItemId());
                            this.validatorRequired("证照项目名称", slEpCertItem.getCertItemName());
                            this.validatorRequired("证照项目内容", slEpCertItem.getCertItemValue());
                        }
                    }
                }
            }
            if (null != isl231180RsParam.getSlEpHonorList()) {
                for (SlEpHonor slEpHonor : isl231180RsParam.getSlEpHonorList()) {
                    this.validatorRequired("企业ID", slEpHonor.getEpId());
                    this.validatorRequired("荣誉ID", slEpHonor.getHonorId());
                    this.validatorRequired("荣誉描述", slEpHonor.getHonorDesc());
                }
            }
            if (null != isl231180RsParam.getSlEpBrandList()) {
                for (ISL231180SlEpBrandList isl231180SlEpBrandList : isl231180RsParam.getSlEpBrandList()) {
                    this.validatorRequired("企业ID", isl231180SlEpBrandList.getEpId());
                    this.validatorRequired("品牌ID", isl231180SlEpBrandList.getBrandId());
                    this.validatorRequired("品牌名称", isl231180SlEpBrandList.getBrandName());
                    this.validatorRequired("品牌分类", isl231180SlEpBrandList.getBrandClass());
                    this.validatorRequired("商标注册证", isl231180SlEpBrandList.getBrandNo());
                    if (null != isl231180SlEpBrandList.getSlEpBrandHonorList()) {
                        for (SlEpBrandHonor slEpBrandHonor : isl231180SlEpBrandList.getSlEpBrandHonorList()) {
                            this.validatorRequired("企业ID", slEpBrandHonor.getEpId());
                            this.validatorRequired("品牌ID", slEpBrandHonor.getBrandId());
                            this.validatorRequired("荣誉ID", slEpBrandHonor.getHonorId());
                            this.validatorRequired("企业产品品牌荣誉描述", slEpBrandHonor.getHonorDes());
                        }
                    }
                }
            }
            if (null != isl231180RsParam.getSlPdBrandList()) {
                for (ISlPdBrand slPdBrand : isl231180RsParam.getSlPdBrandList()) {
                    this.validatorRequired("卖家ID", slPdBrand.getSlCode());
                    this.validatorRequired("品牌所属企业ID", slPdBrand.getBrandEpId());
                    this.validatorRequired("品牌ID", slPdBrand.getBrandId());
                    this.validatorRequired("品牌分类", slPdBrand.getBrandClass());
                }
            }
            if (null != isl231180RsParam.getSlEpWorkshopList()) {
                for (SlEpWorkshop slEpWorkshop : isl231180RsParam.getSlEpWorkshopList()) {
                    this.validatorRequired("企业ID", slEpWorkshop.getEpId());
                    this.validatorRequired("车间ID", slEpWorkshop.getWorkshopId());
                    this.validatorRequired("车间名称", slEpWorkshop.getWorkshopName());
                }
            }
            if (null != isl231180RsParam.getSlEpCap()) {
                this.validatorRequired("企业ID", isl231180RsParam.getSlEpCap().getEpId());
                this.validatorRequired("厂区_总资产", isl231180RsParam.getSlEpCap().getFtyAsset());
                this.validatorRequired("厂区_注册资本", isl231180RsParam.getSlEpCap().getFtyRegCapital());
                this.validatorRequired("厂区_占地面积", isl231180RsParam.getSlEpCap().getFtyLandArea());
                this.validatorRequired("厂区_厂房面积", isl231180RsParam.getSlEpCap().getFtyFloorArea());
                this.validatorRequired("厂区_主要设备", isl231180RsParam.getSlEpCap().getFtyEquipment());
                this.validatorRequired("厂区_设计产能", isl231180RsParam.getSlEpCap().getFtyDesignCap());
                this.validatorRequired("厂区_实际产能", isl231180RsParam.getSlEpCap().getFtyActualCap());
                this.validatorRequired("厂区_外贸销售占比", isl231180RsParam.getSlEpCap().getFtyFtRate());
                this.validatorRequired("厂区_直销占比", isl231180RsParam.getSlEpCap().getFtyDsRate());
                this.validatorRequired("厂区_代理销售占比", isl231180RsParam.getSlEpCap().getFtyAsRate());
                this.validatorRequired("库容概括_原料库容", isl231180RsParam.getSlEpCap().getScapMaterial());
                this.validatorRequired("库容概括_成品库容", isl231180RsParam.getSlEpCap().getScapProduct());
            }
            if (null != isl231180RsParam.getSlEpAuthList()) {
                for (ISL231180SLEpAuth isl231180SLEpAuth : isl231180RsParam.getSlEpAuthList()) {
                    this.validatorRequired("卖家ID", isl231180SLEpAuth.getSlCode());
                    this.validatorRequired("1：卖家代理及分销授权:2：卖家OEM委托授权标志", isl231180SLEpAuth.getFlag());
                    this.validatorRequired("生产商_企业ID", isl231180SLEpAuth.getProducerEpId());
                }
            }
            if (null != isl231180RsParam.getSlEpManagerList()) {
                for (SlEpManager slEpManager : isl231180RsParam.getSlEpManagerList()) {
                    this.validatorRequired("企业ID", slEpManager.getEpId());
                    this.validatorRequired("管理成员ID", slEpManager.getMemberId());
                    this.validatorRequired("职务", slEpManager.getMemberDuties());
                    this.validatorRequired("姓名", slEpManager.getMemberName());
                }
            }
            if (null != isl231180RsParam.getSlEcTeamList()) {
                for (SlEcTeam slEcTeam : isl231180RsParam.getSlEcTeamList()) {
                    this.validatorRequired("卖家ID", slEcTeam.getSlCode());
                    this.validatorRequired("成员ID", slEcTeam.getMemberId());
                    this.validatorRequired("是否负责人", slEcTeam.getLeaderFlg());
                    this.validatorRequired("姓名", slEcTeam.getMemberName());
                }
            }
            if (null != isl231180RsParam.getSlEpDdList()) {
                for (SlEpDd slEpDd : isl231180RsParam.getSlEpDdList()) {
                    this.validatorRequired("企业ID", slEpDd.getEpId());
                    this.validatorRequired("设备ID", slEpDd.getDdId());
                    this.validatorRequired("设备名称", slEpDd.getDdName());
                }
            }
            this.validatorRequired("版本号",isl231180RsParam.getVer());
        }
    }
}







