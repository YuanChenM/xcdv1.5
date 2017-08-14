package com.msk.seller.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.core.entity.*;
import com.msk.core.entity.SlAccount;
import com.msk.core.entity.SlEpAgentAuth;
import com.msk.core.entity.SlEpHonor;
import com.msk.seller.bean.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cx on 2016/2/23.
 */
@Service
public class ISL231181Logic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private SL241103001Logic SL241103001Logic;

    @Autowired
    private SL24110100Logic sL24110100Logic;
    @Autowired
    private SL241103Logic sl241103Logic;

    /**
     * SQL ID
     */
    interface SqlId {
        String SQL_ID_FIND_QUERY_EPAGENT_AUTH = "querySlEpAgentAuth";
        String SQL_ID_FIND_QUERY_SLEPMANAGER = "querySlEpManager";
        String SQL_ID_FIND_QUERY_SLPDCLASSES = "querySlPdClasses";
        String SQL_ID_FIND_QUERY_SLEPBRANDHONOR = "queryslEpBrandHonor";
        String SQL_ID_FIND_QUERY_SLEPBRAND = "queryslEpBrand";
        String SQL_ID_FIND_QUERY_SLPDBRAND = "querySlPdBrand";
        String SQL_ID_FIND_QUERY_SLECTEAM = "querySlEcTeam";
        String SQL_ID_FIND_QUERY_SLEPCERT = "querySlEpCert";
        String SQL_ID_FIND_QUERY_SLEPCERT_ITEM = "querySlEpCertItem";
    }

    /**
     * @param param param
     * @return
     * gzh 有待优化
     */
    @Transactional(readOnly = true)
    public ISL231181Result findAllDate(RsRequest<ISL231181RsParam> param) {
        ISL231181Result isl231181Result = new ISL231181Result();
        SlAccount SlAccount = this.SL241103001Logic.slAccountEntity2(param.getParam().getSlAccount());
        if (null != SlAccount) {
            //卖家基本信息
            ISL231181RegionParam slSeller = this.sL24110100Logic.findSlByAc(param.getParam().getSlAccount());
            if (null != slSeller) {
                ISL231181RegionParam slSellerParam = new ISL231181RegionParam();

                // BeanUtils.copyProperties(slSeller, slSellerParam); 相同类型对象的复制  SPRING提供 后期可以用该方法替换

                slSellerParam.setSlCode(slSeller.getSlCode());
                //卖家账号
                slSellerParam.setSlAccount(slSeller.getSlAccount());
                //卖家编码
                slSellerParam.setSlCodeDis(slSeller.getSlCodeDis());
                //生产国籍
                slSellerParam.setSlConFlg(slSeller.getSlConFlg());
                //企业ID
                slSellerParam.setEpId(slSeller.getEpId());
                //卖家主分类
                slSellerParam.setSlMainClass(slSeller.getSlMainClass());
                //神农客标志
                slSellerParam.setSnkFlg(slSeller.getSnkFlg());
                //自产型卖家标志
                slSellerParam.setSelfFlg(slSeller.getSelfFlg());
                //代理型卖家标志
                slSellerParam.setAgentFlg(slSeller.getAgentFlg());
                //OEM型卖家标志
                slSellerParam.setOemFlg(slSeller.getOemFlg());
                //买手型卖家标志
                slSellerParam.setBuyerFlg(slSeller.getBuyerFlg());
                //神农客贯标审核
                slSellerParam.setSqaStatus(slSeller.getSqaStatus());
                //卖家分销资格
                slSellerParam.setDistQua(slSeller.getDistQua());
                //卖家开店资格
                slSellerParam.setShopQua(slSeller.getShopQua());
                //微信号码
                slSellerParam.setMemo1(slSeller.getMemo1());
                //QQ号码
                slSellerParam.setMemo2(slSeller.getMemo2());
                //邮箱
                slSellerParam.setMemo3(slSeller.getMemo3());
                //固定电话
                slSellerParam.setMemo4(slSeller.getMemo4());
                //传真号
                slSellerParam.setMemo5(slSeller.getMemo5());
                //买手类型
                slSellerParam.setMemo6(slSeller.getMemo6());
                //备用字段7
                slSellerParam.setMemo7(slSeller.getMemo7());
                //备用字段8
                slSellerParam.setMemo8(slSeller.getMemo8());
                //备用字段9
                slSellerParam.setMemo9(slSeller.getMemo9());
                //备用字段10
                slSellerParam.setMemo10(slSeller.getMemo10());
                //备用字段11
                slSellerParam.setMemo11(slSeller.getMemo11());
                //备用字段12
                slSellerParam.setMemo12(slSeller.getMemo12());
                //备用字段13
                slSellerParam.setMemo13(slSeller.getMemo13());
                //备用字段14
                slSellerParam.setMemo14(slSeller.getMemo14());
                //备用字段15
                slSellerParam.setMemo15(slSeller.getMemo15());
                //备用字段16
                slSellerParam.setMemo16(slSeller.getMemo16());
                //备用字段17
                slSellerParam.setMemo17(slSeller.getMemo17());
                //备用字段18
                slSellerParam.setMemo18(slSeller.getMemo18());
                //备用字段19
                slSellerParam.setMemo19(slSeller.getMemo19());
                //备用字段20
                slSellerParam.setMemo20(slSeller.getMemo20());
                String ep = StringUtil.toSafeString(slSeller.getEpId());
                String slCode = slSeller.getSlCode();
                //卖家账户信息
                //卖家产品类别
                BaseParam paramForm = new BaseParam();
                paramForm.setFilter("slCode", slCode);
                paramForm.setFilter("delFlg", "0");
                List<SlPdClasses> slPdClassesList = super.findList(SqlId.SQL_ID_FIND_QUERY_SLPDCLASSES, paramForm);
                //企业基本资质
                SlEnterprise slEnterprise = this.sL24110100Logic.findEpById(ep);
                //查询企业荣誉信息
                List<SlEpHonor> slEpHonors = this.sL24110100Logic.findSlEpHonor(ep);
                //企业产品品牌信息
                BaseParam paramnine = new BaseParam();
                paramnine.setFilter("epId", ep);
                paramnine.setFilter("delFlg", "0");
                List<SL2411030033Bean> sl2411030033BeanList = this.findList(SqlId.SQL_ID_FIND_QUERY_SLEPBRAND, paramnine);
                if (!CollectionUtils.isEmpty(sl2411030033BeanList) && sl2411030033BeanList.size() > 0) {
                    for (int i = 0; i < sl2411030033BeanList.size(); i++) {
                        SL2411030033Bean sl2411030033Bean = sl2411030033BeanList.get(i);
                        //企业产品品牌荣誉
                        BaseParam paramFive = new BaseParam();
                        paramFive.setFilter("epId", ep);
                        paramFive.setFilter("brandId", StringUtil.toSafeString(sl2411030033Bean.getBrandId()));
                        paramFive.setFilter("delFlg", "0");
                        List<SlEpBrandHonor> slEpBrandHonorList = super.findList(SqlId.SQL_ID_FIND_QUERY_SLEPBRANDHONOR, paramFive);
                        sl2411030033Bean.setSlEpBrandHonorList(slEpBrandHonorList);
                    }
                }
                //企业生产能力
                SlEpCap slEpCap = this.sL24110100Logic.findSlEpCap(ep);
                //企业车间信息
                List<SlEpWorkshop> slEpWorkshops = this.sL24110100Logic.findSlEpWorkshop(ep);
                //生产商
                BaseParam paramTwo = new BaseParam();
                paramTwo.setFilter("slCode", slCode);
                paramTwo.setFilter("delFlg", "0");
                List<SlEpAgentAuth> slEpAgentAuthList = super.findList(SqlId.SQL_ID_FIND_QUERY_EPAGENT_AUTH, paramTwo);
                //企业团队管理
                BaseParam paramThree = new BaseParam();
                paramThree.setFilter("epId", ep);
                paramThree.setFilter("delFlg", "0");
                List<SlEpManager> slEpManagerList = super.findList(SqlId.SQL_ID_FIND_QUERY_SLEPMANAGER, paramThree);
                //卖家产品品牌
                BaseParam paramsex = new BaseParam();
                paramsex.setFilter("slCode", slCode);
                paramsex.setFilter("delFlg", "0");
                List<SlPdBrand> slPdBrandList = super.findList(SqlId.SQL_ID_FIND_QUERY_SLPDBRAND, paramsex);
                //企业电商团队信息
                BaseParam paramSeven = new BaseParam();
                paramSeven.setFilter("slCode", slCode);
                paramSeven.setFilter("delFlg", "0");
                List<SlEcTeam> slEcTeamList = super.findList(SqlId.SQL_ID_FIND_QUERY_SLECTEAM, paramSeven);
                //企业专业资质
                BaseParam paramEight = new BaseParam();
                paramEight.setFilter("epId", ep);
                paramEight.setFilter("delFlg", "0");
                List<SlEpCertParam> slEpCertListTwo = new ArrayList<SlEpCertParam>();
                List<SlEpCertParam> slEpCertList = super.findList(SqlId.SQL_ID_FIND_QUERY_SLEPCERT, paramEight);
                for (int i = 0; i < slEpCertList.size(); i++) {
                    SlEpCertParam slEpCertParam = slEpCertList.get(i);
                    long epId = slEpCertParam.getEpId();
                    long certId = slEpCertParam.getCertId();
                    //企业证照项目信息
                    BaseParam paramNight = new BaseParam();
                    paramNight.setFilter("epId", StringUtil.toSafeString(epId));
                    paramNight.setFilter("certId", StringUtil.toSafeString(certId));
                    paramNight.setFilter("delFlg", "0");
                    List<SlEpCertItem> slEpCertItemList = super.findList(SqlId.SQL_ID_FIND_QUERY_SLEPCERT_ITEM, paramNight);
                    slEpCertParam.setCertItemList(slEpCertItemList);
                    slEpCertListTwo.add(slEpCertParam);
                }
                //添加卖家产品类别
                isl231181Result.setPdClassesCodeList(slPdClassesList);
                //添加企业专业资质信息
                isl231181Result.setCertInfoList(slEpCertListTwo);
                //添加企业荣誉
                isl231181Result.setSlEpHonorList(slEpHonors);
                //添加企业产品品牌
                isl231181Result.setSlEpBrandList(sl2411030033BeanList);
                //添加企业产品品牌荣誉
                //isl231181Result.setSlEpBrandHonorList(slEpBrandHonorList);
                //添加卖家产品品牌
                isl231181Result.setSlPdBrandList(slPdBrandList);
                //添加企业车间
                isl231181Result.setSlEpWorkshopList(slEpWorkshops);
                //添加生产商
                isl231181Result.setSlEpAuthList(slEpAgentAuthList);
                //添加企业管理团队
                isl231181Result.setSlEpManagerList(slEpManagerList);
                //添加卖家电商团队
                isl231181Result.setSlEcTeamList(slEcTeamList);
                //卖家账号信息
                isl231181Result.setSlAccount(SlAccount);
                //企业基本资质
                isl231181Result.setSlEnterprise(slEnterprise);
                //企业生产能力
                isl231181Result.setSlEpCap(slEpCap);
                //大区编码
                slSellerParam.setAreaCode(slSeller.getAreaCode());
                //大区名称
                slSellerParam.setAreaName(slSeller.getAreaName());
                //物流区编码
                slSellerParam.setLgcsAreaCode(slSeller.getLgcsAreaCode());
                //物流区编码名称
                slSellerParam.setLgcsAreaName(slSeller.getLgcsAreaName());
                //省编码
                slSellerParam.setProvinceCode(slSeller.getProvinceCode());
                //省编码名称
                slSellerParam.setProvinceName(slSeller.getProvinceName());
                //地区编码
                slSellerParam.setCityCode(slSeller.getCityCode());
                //地区编码名称
                slSellerParam.setCityName(slSeller.getCityName());
                //区编码
                slSellerParam.setDistrictCode(slSeller.getDistrictCode());
                //区编码名称
                slSellerParam.setDistrictName(slSeller.getDistrictName());

                //卖家基本信息
                isl231181Result.setSlSeller(slSellerParam);
                //查询企业检测设备
                BaseParam params = new BaseParam();
                params.setFilter("epId", ep);
                params.setFilter("delFlg", "0");
                List<SlEpDdBean> slEpDdBeanList = sl241103Logic.findEpEquipment(params);
                isl231181Result.setSlEpDdList(slEpDdBeanList);
            } else {
                throw new BusinessException("卖家基本信息不存在!");
            }
        } else {
            throw new BusinessException("卖家账户不存在!");
        }
        return isl231181Result;
    }
}
