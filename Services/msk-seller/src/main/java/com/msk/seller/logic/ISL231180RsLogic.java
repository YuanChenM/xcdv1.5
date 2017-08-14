package com.msk.seller.logic;

import com.hoperun.core.utils.DateTimeUtil;
import com.msk.common.logic.CommonLogic;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.msk.core.entity.SlAccount;
import com.msk.core.entity.*;
import com.msk.core.entity.SlEpAgentAuth;
import com.msk.core.entity.SlEpHonor;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.msk.district.bean.CityBean;
import com.msk.district.bean.DistrictBean;
import com.msk.district.bean.ProvinceBean;
import com.msk.seller.bean.*;
import com.msk.core.entity.SlEpOemAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 编辑all
 */
@Service
public class ISL231180RsLogic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISL231180RsLogic.class);
    // 用户账号
    private static String sellerAccount = "";
    // 用户登录ID
    private static String loginId = "";
    // 全体用卖家CODE
    private static String selCode = "";
    // 全体用卖家品牌ID
    private static long epId = 0L;
    // 0.生产商1.自产型,2:代理型,3:OEM型
    private static String mainClass = "";

    /**
     * SQL Id
     */
    interface SqlId {
        static final String SQL_ID_DELETE_SLACCOUNT = "deleteSlAccount";
        static final String SQL_ID_UPDATE_SLSELLER = "updateSlSeller";
        static final String SQL_ID_UPDATE_SLEPBRAND = "updateSlEpBrand";
        static final String SQL_ID_UPDATE_SLEPBRANDHONOR = "updateSlEpBrandHonor";
        static final String SQL_ID_UPDATE_SLEPCERTITEM = "updateSlEpCertItem";
        static final String SQL_ID_UPDATE_SLACCOUNT = "updateSlAccount";
        static final String SQL_ID_UPDATE_SLEPCAP = "updateSlEpCap";
        static final String SQL_ID_UPDATE_SLPDBRAND = "updateSlPdBrand";
        static final String SQL_ID_UPDATE_SLSELFPDBRAND = "updateSlSelfPdBrand";
        static final String SQL_ID_UPDATE_SLEPAGENTAUTH = "updateSlEpAgentAuth";
        static final String SQL_ID_UPDATE_SLEPOEMAUTH = "updateSlEpOemAuth";
        static final String SQL_ID_FIND_SLACCOUNTIFEXIST = "findSlAccountIfExist";
        static final String SQL_ID_FIND_SLENTERPRISE = "findSlEnterprise";
        static final String SQL_ID_SAVE_SLPDCLASSES = "saveSlPdClasses";
        static final String SQL_ID_QUERY_SLTEL = "querySlTel";
        static final String SQL_ID_SAVE_SLEPWORKSHOP = "saveSlEpWorkshop";
        static final String SQL_ID_SAVE_SLEPAGENTAUTH = "saveSlEpAgentAuth";
        static final String SQL_ID_SAVE_SLEPOEMAUTH = "saveSlEpOemAuth";
        static final String SQL_ID_SAVE_SLEPMANAGER = "saveSlEpManager";
        static final String SQL_ID_SAVE_SLECTEAM = "saveSlEcTeam";
        static final String SQL_ID_SAVE_SL_EPDD = "saveSlEpDd";
        static final String SQL_ID_UPDATE_SL_EPDD = "updateSlEpDd";
        static final String SQL_ID_FIND_SLPDBRANDIFEXIST = "findSlPdBrandIfExist";
        static final String SQL_ID_FIND_SLSELLERLIST = "findSlSellerList";
        static final String SQL_ID_FIND_SLSELLEREPID = "findSlSellerEpId";
        static final String SQL_ID_FIND_SLCODE = "findSlCode";
        static final String SQL_ID_FIND_CERTID = "findCertId";
        static final String SQL_ID_FIND_SLEPBRAND = "findSlEpBrandByEpIdAndBrandId";
        static final String SQL_ID_FIND_UPDATESLSELLER = "findUpdateSlseller";
        static final String SQL_ID_FIND_QUERYONESLCODE = "queryOneSlCode";
        static final String SQL_ID_FIND_QUERYLICNODATA = "queryLicNoData";
        static final String SQL_ID_FIND_QUERYSLPDCLASS = "querySlPdClass";
        static final String SQL_ID_DELETE_SLPDCLASS = "deleteSlPdClass";
        static final String SQL_ID_DELETE_SLEPCERT = "deleteSlEpCert";
        static final String SQL_ID_DELETE_SLEPHONOR = "deleteSlEpHonor";
        static final String SQL_ID_DELETE_SLEPBRAND = "deleteSlEpBrand";
        static final String SQL_ID_DELETE_SLEPBRANDHONOR = "deleteSlEpBrandHonor";
        static final String SQL_ID_DELETE_SLPDBRAND = "deleteSlPdBrand";
        static final String SQL_ID_DELETE_WORKSHOPID = "deleteWorkShopId";
        static final String SQL_ID_DELETE_SLEPAGENTAUTH = "deleteSLEpAgentAuth";
        static final String SQL_ID_DELETE_SLEPOEMAUTH = "deleteSLEpOemAuth";
        static final String SQL_ID_DELETE_SLEPMANAGER = "deleteSLEpManager";
        static final String SQL_ID_DELETE_SLECTEAM = "deleteSLEcTeam";
        static final String SQL_ID_DELETE_SLEPDD = "deleteSlEpDd";
        static final String SQL_ID_QUERY_SLEPCERT = "querySlEpCert";
        static final String SQL_ID_QUERY_SLPDBREED = "querySlPdBreed";
        static final String SQL_ID_QUERY_SLEPAGENTAUTH = "querySlEpAgentAuth";
        static final String SQL_ID_QUERY_SLEPOEMAUTH = "querySlEpOemAuth";
        static final String SQL_ID_FIND_MANUFACTUREACCOUNT = "findManufactureAccount";
    }

    /**
     * @see BaseLogic#setBaseDao(BaseDao)
     */
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private SL241103001Logic sL241103001Logic;
    @Autowired
    private SL24110300301Logic sL24110300301Logic;
    @Autowired
    private CommonLogic commonLogic;
    @Autowired
    private SL24110300401Logic sL24110300401Logic;
    @Autowired
    private ISL231146RsLogic iSL231146RsLogic;
    @Autowired
    private SL24110102Logic sl24110102Logic;
    @Autowired
    private SL2411010401Logic sl2411010401Logic;
    @Autowired
    private ISL231154RsLogic isl231154RsLogic;
    @Autowired
    private SL2411010501Logic sl2411010501Logic;
    @Autowired
    private SL24110300402Logic sl24110300402Logic;
    @Autowired
    private ISL231150RsLogic isl231150RsLogic;
    @Autowired
    private SL24110108Logic sl24110108Logic;
    @Autowired
    private SL24110109Logic sl24110109Logic;
    @Autowired
    private SL24110102Logic sL24110102Logic;
    @Autowired
    private SL2411030071Logic sL2411030071Logic;
    @Autowired
    private SLAccountLogic slAccountLogic;
    @Autowired
    private SLSellerLogic slSellerLogic;

    /**
     * 逻辑删除操作
     *
     * @param isl231180RsParam
     *        录入参数信息===================================================================================================
     */
    @Transactional
    public void deleteSlAccount(ISL231180RsParam isl231180RsParam) {
        SlAccount slAccount = isl231180RsParam.getSlAccount();
        if (null != slAccount) {
            slAccount.setUpdId(isl231180RsParam.getLoginId());
            slAccount.setVer(isl231180RsParam.getVer() + 1);
            slAccount.setDelFlg("1");
            slAccount.setUpdTime(DateTimeUtil.getCustomerDate());
            super.remove(SqlId.SQL_ID_DELETE_SLACCOUNT, slAccount);
            // 处理 买手情况
            slAccountLogic.dealSLAccountBs(slAccount);
        } else {
            throw new BusinessException("不存在的账号信息");
        }
    }

    /**
     * 卖家新增.更新
     *
     * @param isl231180RsParam 接口传入参数
     */
    @Transactional
    public ISL231180RsResult saveSlData(ISL231180RsParam isl231180RsParam) {
        ISL231180RsResult isl231180RsResult = new ISL231180RsResult();
        // 判断全体新增
        loginId = isl231180RsParam.getLoginId();
        // 生成商新增操作
        SlAccount slAccount = isl231180RsParam.getSlAccount();
        if (null != isl231180RsParam.getInsertFlag() && isl231180RsParam.getInsertFlag() == NumberConst.IntDef.INT_ZERO
                && null != isl231180RsParam.getManufactureFlag()
                && NumberConst.IntDef.INT_ONE == isl231180RsParam.getManufactureFlag()) {
            // 生产商新增
            SlAccount slAccountTwo = super.findOne(SqlId.SQL_ID_FIND_MANUFACTUREACCOUNT, new BaseParam());
            Integer manufactureAccount;
            DecimalFormat df = new DecimalFormat("0000000");
            if (null != slAccountTwo) {
                manufactureAccount = Integer.parseInt(slAccountTwo.getSlAccount()) + 1;
            } else {
                manufactureAccount = NumberConst.IntDef.INT_ONE;
            }
            sellerAccount = df.format(manufactureAccount);
            slAccount.setSlAccount(sellerAccount);
            slAccount.setAccountPsd("123456");
            slAccount.setSlTel("00000000000");
            slAccount.setAuthStatus(NumberConst.IntDef.INT_ONE);
            slAccount.setCrtId(isl231180RsParam.getLoginId());
            slAccount.setDelFlg("0");
            slAccount.setVer(NumberConst.IntDef.INT_ONE);
            this.sL241103001Logic.saveAccount(slAccount);
        } else {
            // 卖家账号信息新增和修改
            if (null != slAccount) {
                this.registerForSlAccount(slAccount, isl231180RsParam.getInsertFlag());
            }
        }
        /** 卖家基本信息处理 */
        SlSeller slSeller = isl231180RsParam.getSlSeller();
        if (null != slSeller) {
            slSeller = this.saveOrUpdateForSlSeller(slSeller, isl231180RsParam.getInsertFlag());
            selCode = slSeller.getSlCode();
            isl231180RsResult.setSlCode(slSeller.getSlCode());
        }
        /** 卖家产品类别信息 */
        List<SlPdClasses> slPdClassesLists = isl231180RsParam.getPdClassesCodeList();
        if (!CollectionUtils.isEmpty(slPdClassesLists)) {
            /** 搜集用户传入的所有的资质证照ID */
            List<Long> certIdList = new ArrayList<Long>();
            if (null != isl231180RsParam.getCertInfoList()) {
                for (ISL231127CertInfoList isl231127CertInfoList : isl231180RsParam.getCertInfoList()) {
                    Long certId = isl231127CertInfoList.getCertId();
                    certIdList.add(certId);
                }
            }
            this.saveOrUpdateForPdClasse(slPdClassesLists, certIdList);
        }
        /** 企业基本资质 */
        SlEnterpriseBean slEnterprise = isl231180RsParam.getSlEnterprise();
        if (null != slEnterprise) {
            this.saveOrUpdateForSlEnterprise(slEnterprise, isl231180RsParam.getInsertFlag());
            isl231180RsResult.setEpId(epId);
        }
        /** 企业专业资质 */
        List<ISL231127CertInfoList> certInfoList = isl231180RsParam.getCertInfoList();
        if (!CollectionUtils.isEmpty(certInfoList)) {
            this.saveOrUpdateForslEpCert(certInfoList, isl231180RsParam.getInsertFlag());
        }
        /** 企业荣誉 */
        List<SlEpHonor> slEpHonorList = isl231180RsParam.getSlEpHonorList();
        if (!CollectionUtils.isEmpty(slEpHonorList)) {
            this.saveOrUpdateForslEpHonor(slEpHonorList, isl231180RsParam.getInsertFlag());
        }

        /** 企业产品品牌 */
        List<ISL231180SlEpBrandList> slEpBrandList = isl231180RsParam.getSlEpBrandList();
        if (!CollectionUtils.isEmpty(slEpBrandList)) {
            if (null != isl231180RsParam.getInsertFlag() && "".equals(sellerAccount)) {
                this.saveOrUpdateForslEpBrand(slEpBrandList);
            } else {
                this.saveOrUpdateForslEpBrand(slEpBrandList, isl231180RsParam.getInsertFlag());
            }
        }
        /** 卖家品牌 */
        List<ISlPdBrand> slPdBrandList = isl231180RsParam.getSlPdBrandList();
        if (!CollectionUtils.isEmpty(slPdBrandList)) {
            if (null != isl231180RsParam.getInsertFlag() && "".equals(sellerAccount)) {
                this.saveOrUpdateForSlPdBrand(slPdBrandList);
            } else {
                this.saveOrUpdateForSlPdBrand(slPdBrandList, isl231180RsParam.getInsertFlag());
            }
        }
        /** 企业车间 */
        List<SlEpWorkshop> slEpWorkshopList = isl231180RsParam.getSlEpWorkshopList();
        if (!CollectionUtils.isEmpty(slEpWorkshopList)) {
            this.saveOrUpdateForSlEpWorkshop(slEpWorkshopList, isl231180RsParam.getInsertFlag());
        }
        /** 企业生产能力 */
        SlEpCap slEpCap = isl231180RsParam.getSlEpCap();
        if (slEpCap != null) {
            this.saveOrUpdateForSlEpCap(slEpCap, isl231180RsParam.getInsertFlag());
        }
        /** 生产商 */
        List<ISL231180SLEpAuth> isl231180SLEpAuthList = isl231180RsParam.getSlEpAuthList();
        if (!CollectionUtils.isEmpty(isl231180SLEpAuthList)) {
            if (null != isl231180RsParam.getInsertFlag() && "".equals(sellerAccount)) {
                // 单个新增处理逻辑
                this.saveOrUpdateForSlEpAuth(isl231180SLEpAuthList);
            } else {
                // 全体新增 + 修改逻辑
                this.saveOrUpdateForSlEpAuth(isl231180SLEpAuthList, isl231180RsParam.getInsertFlag());
            }
        }
        /** 企业管理团队 */
        List<SlEpManager> slEpManagerList = isl231180RsParam.getSlEpManagerList();
        if (!CollectionUtils.isEmpty(slEpManagerList)) {
            this.saveOrUpdateForSlEpManager(slEpManagerList, isl231180RsParam.getInsertFlag());
        }
        /** 企业电商团队 */
        List<SlEcTeam> slEcTeamList = isl231180RsParam.getSlEcTeamList();
        if (!CollectionUtils.isEmpty(slEcTeamList)) {
            this.saveOrUpdateForSlEcTeam(slEcTeamList, isl231180RsParam.getInsertFlag());
        }
        /** 企业检测设备 */
        List<SlEpDd> slEpDdList = isl231180RsParam.getSlEpDdList();
        if (!CollectionUtils.isEmpty(slEpDdList)) {
            this.saveOrUpdateForSlEpDd(slEpDdList, isl231180RsParam.getInsertFlag());
        }
        return isl231180RsResult;
    }

    /**
     * 卖家\生产商账号注册
     */
    @Transactional
    public void registerForSlAccount(SlAccount slAccount, Integer insertflag) {
        // 判定是否生产商
        BaseParam base = new BaseParam();
        base.setFilter("slAccount", slAccount.getSlAccount());
        SlAccount slAccountCheck = super.findOne(SqlId.SQL_ID_FIND_SLACCOUNTIFEXIST, base);
        if (null != insertflag) {
            // 新增操作
            if (StringUtil.isNullOrEmpty(slAccount.getSlAccount())
                    && StringUtil.isNullOrEmpty(slAccount.getAccountPsd())) {
                throw new BusinessException("卖家账号和密码不能为空!");
            } else {
                if (!StringUtil.isNullOrEmpty(slAccount.getSlTel())) {
                    BaseParam params = new BaseParam();
                    params.setFilter("slTel", slAccount.getSlTel());
                    int num = super.getCount(SqlId.SQL_ID_QUERY_SLTEL, params);
                    if (0 < num) {
                        throw new BusinessException("手机账户已注册!");
                    }
                }
            }
            if (slAccountCheck == null) {
                // 1.1如果账号不存在，则新增
                slAccount.setCrtId(loginId);
                slAccount.setCrtTime(DateTimeUtil.getCustomerDate());
                this.sL241103001Logic.saveAccount(slAccount);
            } else {
                throw new BusinessException("该账号已经被注册!");
            }
        } else {
            if (slAccountCheck != null) {
                /** 更新操作 */
                slAccount.setUpdId(loginId);
                slAccount.setUpdTime(DateTimeUtil.getCustomerDate());
                // 处理 买手情况
                slAccountLogic.dealSLAccountBs(slAccount);
                super.modify(SqlId.SQL_ID_UPDATE_SLACCOUNT, slAccount);
            } else {
                throw new BusinessException("该账号信息不存在!");
            }
        }
    }

    /**
     * 卖家基本信息新增or修改
     * flag
     */
    @Transactional
    public SlSeller saveOrUpdateForSlSeller(SlSeller slSeller, Integer insertflag) {
        mainClass = StringUtil.toSafeString(slSeller.getSlMainClass());
        /** 增加前先查询下这个卖家账号是否已经注册了，如果已经注册了，报错提示，如果没有注册过，就新增 */
        BaseParam base = new BaseParam();
        if (!"".equals(sellerAccount)) {
            base.setFilter("slAccount", sellerAccount);
            slSeller.setSlAccount(sellerAccount);
        } else {
            base.setFilter("slAccount", slSeller.getSlAccount());
            //补充逻辑  需要校验账号表中账号是否存在
            SlAccount slAccountCheck = super.findOne(SqlId.SQL_ID_FIND_SLACCOUNTIFEXIST, base);
            if(slAccountCheck == null){
                throw new BusinessException("卖家账号信息在账号表不存在,请先录入账号信息!");
            }
        }
        SlSeller slSellerList = super.findOne(SqlId.SQL_ID_FIND_SLSELLERLIST, base);
        if (null != slSellerList && insertflag != null) {
            // 新增查想不为0
            throw new BusinessException("该账号卖家基本信息已经存在，无法重复录入");
        } else if (null == slSellerList && null == insertflag) {
            // 修改
            throw new BusinessException("该账号卖家基本信息不存在，无法更新!");
        }

        /** 查询slcode,第一次新建的时候slcode不知道是多少，采用拼接方式 provinceCode+cityCode+districtCode+后八位番号 */
        String slCodeDis = this.sL241103001Logic.findAccount(slSeller.getSlMainClass().toString(),
            slSeller.getCityCode(), slSeller.getSlConFlg());
        // 生成卖家生产商编码
        // 根据区划和国际查询生产商编码
        slSeller.setSlCodeManufacture(
            this.sL241103001Logic.findSlCodeManufacture(slSeller.getCityCode(), slSeller.getSlConFlg()));
        // 二级经营类型
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("cityCode", slSeller.getCityCode());
        baseParam.setFilter("slConFlg", slSeller.getSlConFlg());

        // 获取大区编码
        ProvinceBean mdProvince = this.sL241103001Logic.findProvince(slSeller.getProvinceCode());
        slSeller.setAreaCode(mdProvince.getAreaCode());
        slSeller.setAreaName(mdProvince.getAreaName());
        slSeller.setProvinceName(mdProvince.getProvinceName());

        // 获取物流区编码
        CityBean mdCity = this.sL241103001Logic.findCity(slSeller.getCityCode());
        slSeller.setLgcsAreaCode(mdCity.getLgcsAreaCode());
        slSeller.setLgcsAreaName(mdCity.getLgcsAreaName());
        slSeller.setCityName(mdCity.getCityName());

        // 获取区编码
        DistrictBean district = this.sL241103001Logic.findDistrict(slSeller.getCityCode(), slSeller.getDistrictCode());
        slSeller.setDistrictName(district.getDistrictName());

        if ("1".equals(slSeller.getSelfFlg())) {
            baseParam.setFilter("selfFlg", "1");
            slSeller.setSlCodeSelf(this.sL241103001Logic.findSlCodeOther(baseParam));
            baseParam.getFilterMap().remove("selfFlg");
        }
        if ("1".equals(slSeller.getAgentFlg())) {
            baseParam.setFilter("agentFlg", "1");
            slSeller.setSlCodeAgent(this.sL241103001Logic.findSlCodeOther(baseParam));
            baseParam.getFilterMap().remove("agentFlg");
        }
        if ("1".equals(slSeller.getOemFlg())) {
            baseParam.setFilter("oemFlg", "1");
            slSeller.setSlCodeOem(this.sL241103001Logic.findSlCodeOther(baseParam));
            baseParam.getFilterMap().remove("oemFlg");
        }
        // 获取SL_CODE_DIS 新增则直接前逻辑获取编码   修改通过该出获取最新的编码
        if(slCodeDis == null || "".equals(slCodeDis)){
            base.setFilter("cityCode", slSeller.getCityCode());
            base.setFilter("slMainClass", StringUtil.toSafeString(slSeller.getSlMainClass()));
            base.setFilter("slConFlg", slSeller.getSlConFlg());
            SlSeller seller = sL24110102Logic.findOne("queryMax", base);
            if(seller != null){
                slCodeDis = seller.getSlCodeDis();
            }
        }
        slSeller.setSlCodeDis(slCodeDis);
        slSeller.setCrtId(loginId);
        slSeller.setCrtTime(DateTimeUtil.getCustomerDate());
        slSeller.setUpdId(loginId);
        slSeller.setUpdTime(DateTimeUtil.getCustomerDate());

        if (null == insertflag) {
            /** 查询该卖家编码下的slseller信息，判断用户的市区编码是否发生变化，发生变化 将其存储到履历表中并且更新，没有发生变化 直接更新操作 */
            SlSellerHis slSellerHis = this.sL24110102Logic.findSlSellerBySlCode(slSeller.getSlCode());
            if (!slSeller.getCityCode().equals(slSellerHis.getCityCode())) {
                Long hisId = this.commonLogic.maxId("SL_SELLER_HIS", "HIS_ID");
                slSellerHis.setHisId(hisId);
                this.sL24110102Logic.saveHis(slSellerHis);
            }
            /** 更新seller到数据库 */
            super.modify(SqlId.SQL_ID_UPDATE_SLSELLER, slSeller);
            /** 调用接口 同步卖家模块买手信息 create by likai1 start 2016/8/3 */
            slSellerLogic.updSellerToBs(slSeller);
            /** 调用接口 同步卖家模块买手信息 create by likai1 end 2016/8/3 */
        } else {

            slSeller.setVer(NumberConst.IntDef.INT_ONE);
            int num = this.sL241103001Logic.saveSeller(slSeller);
            if (num <= 0) {
                throw new BusinessException("卖家基本信息注册失败!");
            }
            SlSeller slSellerR = super.findOne(SqlId.SQL_ID_FIND_SLSELLERLIST, base);
            slSeller.setSlCode(slSellerR.getSlCode());
        }
        return slSeller;
    }

    /**
     * 产品类别新增or修改
     */
    @Transactional
    public void saveOrUpdateForPdClasse(List<SlPdClasses> slPdClassesLists, List<Long> certIdList) {
        int i = 0;
        for (SlPdClasses slPdClasse : slPdClassesLists) {
            i++;
            /** 根据主键查询该信息是否已经存在，如果已经存在，提示存在该信息，没有存在插入数据 */
            BaseParam base = new BaseParam();
            base.setFilter("slCode", slPdClasse.getSlCode());
            base.setFilter("pdClassesCode", slPdClasse.getPdClassesCode());
            base.setFilter("machiningCode", slPdClasse.getMachiningCode());
            BaseParam param = new BaseParam();
            if (!"".equals(sellerAccount)) {
                param.setFilter("slCode", selCode);
            } else {
                param.setFilter("slCode", slPdClasse.getSlCode());
            }
            int count = super.getCount(SqlId.SQL_ID_FIND_QUERYONESLCODE, param);
            if (count > 0) {
                /** 根据产品类型和加工程度编码，查询用户必须具备的certID,看用户传入的certId是否包含所有的必须具备资质，包含正常执行，不包含，提示用户  */
                // 2016/10/13 同蒲喜贵确认后 对方说该出是基础数据  但是的确没有其他的入库操作  所以暂时不用 以后再商讨
//                List<SlMstPdCert> slMstPdCerts = super.findList(SqlId.SQL_ID_FIND_CERTID, base);
//                /** 根据产品类型及加工程度。必须的资质 */
//                List<Long> certIds = new ArrayList<Long>();
//                if (null != slMstPdCerts) {
//                    for (SlMstPdCert slMstPdCert : slMstPdCerts) {
//                        Long certId = slMstPdCert.getCertId();
//                        certIds.add(certId);
//                    }
//                }
//                if (!certIdList.containsAll(certIds)) {
//                    throw new BusinessException("对不起，您输入的证照不全，无法完成注册");
//                }
                int counts = super.getCount(SqlId.SQL_ID_FIND_QUERYSLPDCLASS, param);
                // 先删除 后新增
                if (counts > 0 && i == 1) {
                    super.remove(SqlId.SQL_ID_DELETE_SLPDCLASS, param);
                }
                if (!"".equals(sellerAccount)) {
                    slPdClasse.setSlCode(selCode);
                } else {
                    slPdClasse.setSlCode(slPdClasse.getSlCode());
                }
                slPdClasse.setCrtId(loginId);
                slPdClasse.setCrtTime(DateTimeUtil.getCustomerDate());
                super.save(SqlId.SQL_ID_SAVE_SLPDCLASSES, slPdClasse);
            } else {
                throw new BusinessException("卖家ID为:" + slPdClasse.getSlCode() + "的不存在");
            }
        }
    }

    /**
     * 企业基本信息新增or修改
     */
    @Transactional
    public void saveOrUpdateForSlEnterprise(SlEnterpriseBean slEnterprise, Integer insertFlag) {
        if (null != insertFlag) {
            epId = commonLogic.maxId("SL_ENTERPRISE", "EP_ID");
            slEnterprise.setEpId(epId);
            slEnterprise.setCrtId(loginId);
            BaseParam params = new BaseParam();
            if(slEnterprise.getLicNo() != null){
                params.setFilter("licNo", slEnterprise.getLicNo());
                int count = super.getCount(SqlId.SQL_ID_FIND_QUERYLICNODATA, params);
                if (count > 0) {
                    throw new BusinessException("营业执照号已注册!");
                }
            }else if("0".equals(slEnterprise.getLicType())){
                //当营业执照号为空且不是三证合一的时候报错
                throw new BusinessException("三证合一的时候为空的时候必须要有营业执照号！");
            }
            int num = this.sL241103001Logic.saveEp(slEnterprise);
            if (num >= 1) {
                SlSeller param = new SlSeller();
                param.setEpId(slEnterprise.getEpId());
                if (!"".equals(sellerAccount)) {
                    param.setSlCode(selCode);
                } else {
                    param.setSlCode(slEnterprise.getSlCode());
                }
                param.setUpdId(loginId);
                param.setUpdTime(DateTimeUtil.getCustomerDate());
                super.modify(SqlId.SQL_ID_FIND_UPDATESLSELLER, param);
                SlSeller modifySeller = new SlSeller();
                modifySeller.setEpId(slEnterprise.getEpId());
                modifySeller.setSlCode(param.getSlCode());
                slSellerLogic.updSellerToBs(modifySeller);
            }
        } else {
            BaseParam baseParam = new BaseParam();
            baseParam.setFilter("epId", StringUtil.toSafeString(slEnterprise.getEpId()));
            List<SlEnterprise> slEnterpriseList = super.findList(SqlId.SQL_ID_FIND_SLENTERPRISE, baseParam);
            if (slEnterpriseList.size() <= 0) {
                throw new BusinessException("企业基本资质不存在,无法添加");
            }
            /** 更新操作 */
            slEnterprise.setUpdId(loginId);
            this.sl24110102Logic.updateEp(slEnterprise);
            epId = slEnterprise.getEpId();
        }
    }

    /**
     * 企业专业资质新增or修改
     */
    @Transactional
    public void saveOrUpdateForslEpCert(List<ISL231127CertInfoList> certInfoList, Integer insertFlag) {
        if (null != insertFlag) {
            // 新增
            for (ISL231127CertInfoList certInfo : certInfoList) {
                // 判断标示是删除
                if ("1".equals(certInfo.getDelFlg())) {
                    certInfo.setUpdTime(DateTimeUtil.getCustomerDate());
                    // 删除操作
                    super.modify(SqlId.SQL_ID_DELETE_SLEPCERT, certInfo);
                    // 判断标示是新增
                } else if (StringUtil.isNullOrEmpty(certInfo.getDelFlg()) || "0".equals(certInfo.getDelFlg())) {
                    // 查询数据是否存在
                    BaseParam params = new BaseParam();
                    params.setFilter("epId", StringUtil.toSafeString(certInfo.getEpId()));
                    params.setFilter("certId", StringUtil.toSafeString(certInfo.getCertId()));
                    SlEpCert slEpCert2 = super.findOne(SqlId.SQL_ID_QUERY_SLEPCERT, params);
                    if (null != slEpCert2 && "".equals(sellerAccount)) {
                        String delFlg = slEpCert2.getDelFlg();
                        // 数据存在且删除标示为1，表示数据已删除。进行更新操作
                        if ("1".equals(delFlg)) {
                            // 将删除数据，更新删除标示为0
                            certInfo.setUpdTime(DateTimeUtil.getCustomerDate());
                            super.modify(SqlId.SQL_ID_DELETE_SLEPCERT, certInfo);
                            // 更新企业证照项目信息
                            List<SlEpCertItem> itemList = certInfo.getCertItemList();
                            if (null != itemList && itemList.size() > 0) {
                                for (SlEpCertItem slEpCertItem : itemList) {
                                    slEpCertItem.setEpId(certInfo.getEpId());
                                    slEpCertItem.setCertId(certInfo.getCertId());
                                    slEpCertItem.setUpdTime(DateTimeUtil.getCustomerDate());
                                    slEpCertItem.setCrtTime(DateTimeUtil.getCustomerDate());
                                    slEpCertItem.setUpdId(loginId);
                                    slEpCertItem.setUpdTime(DateTimeUtil.getCustomerDate());
                                    super.modify(SqlId.SQL_ID_UPDATE_SLEPCERTITEM, slEpCertItem);
                                }
                            } else {
                                throw new BusinessException("证照项目信息为空");
                            }
                        } else {
                            throw new BusinessException("企业ID为:" + StringUtil.toSafeString(certInfo.getEpId())
                                    + ",证照ID为:" + StringUtil.toSafeString(certInfo.getCertId()) + "，记录已存在");
                        }
                    } else {
                        SlEpCert slEpCert1 = new SlEpCert();
                        if (!"".equals(sellerAccount)) {
                            slEpCert1.setEpId(epId);
                        } else {
                            slEpCert1.setEpId(certInfo.getEpId());
                        }
                        slEpCert1.setCertId(certInfo.getCertId());
                        slEpCert1.setCertName(certInfo.getCertName());
                        slEpCert1.setCrtId(loginId);
                        /** 根据用户企业Id查询最大的certseq */
                        Long maxCertSeq = this.sL24110300301Logic.findMaxCertSeq(certInfo.getEpId());
                        slEpCert1.setCertSeq(maxCertSeq);
                        this.sL24110300301Logic.saveSlEpCert(slEpCert1);
                        List<SlEpCertItem> slEpCertItemList = new ArrayList<SlEpCertItem>();
                        if (null != certInfo.getCertItemList() && certInfo.getCertItemList().size() > 0) {
                            for (SlEpCertItem slEpCertItem : certInfo.getCertItemList()) {
                                if (!"".equals(sellerAccount)) {
                                    slEpCert1.setEpId(epId);
                                } else {
                                    slEpCertItem.setEpId(certInfo.getEpId());
                                }
                                slEpCertItem.setCertId(certInfo.getCertId());
                                slEpCertItem.setCertSeq(maxCertSeq);
                                slEpCertItem.setCrtId(loginId);
                                slEpCertItemList.add(slEpCertItem);
                            }
                            this.sL24110300301Logic.saveSlEpCertItem(slEpCertItemList);
                        }
                    }
                }
            }
        } else {
            // 修改
            for (ISL231127CertInfoList CertInfo : certInfoList) {
                List<SlEpCertItem> itemList = CertInfo.getCertItemList();
                if (null != itemList && itemList.size() > 0) {
                    for (SlEpCertItem slEpCertItem : itemList) {
                        slEpCertItem.setEpId(CertInfo.getEpId());
                        slEpCertItem.setCertId(CertInfo.getCertId());
                        slEpCertItem.setUpdId(loginId);
                        slEpCertItem.setUpdTime(DateTimeUtil.getCustomerDate());
                        super.modify(SqlId.SQL_ID_UPDATE_SLEPCERTITEM, slEpCertItem);
                    }
                } else {
                    throw new BusinessException("更新操作失败，证照项目信息为空");
                }
            }
        }
    }

    /**
     * 企业荣誉新增or修改
     */
    @Transactional
    public void saveOrUpdateForslEpHonor(List<SlEpHonor> slEpHonorList, Integer insertFlag) {
        for (SlEpHonor slEpHonor : slEpHonorList) {
            if ("1".equals(slEpHonor.getDelFlg())) {
                BaseParam param = new BaseParam();
//                param.setFilter("epId", StringUtil.toSafeString(slEpHonor.getEpId()));
//                param.setFilter("honorId", StringUtil.toSafeString(slEpHonor.getHonorId()));
//                param.setFilter("delFlg", "1");
                slEpHonor.setUpdTime(DateTimeUtil.getCustomerDate());
                super.modify(SqlId.SQL_ID_DELETE_SLEPHONOR, slEpHonor);
            } else if (StringUtil.isNullOrEmpty(slEpHonor.getDelFlg()) || "0".equals(slEpHonor.getDelFlg())) {
                if (null != insertFlag) {
                    if (!"".equals(sellerAccount)) {
                        slEpHonor.setEpId(epId);
                    } else {
                        this.sL24110300401Logic.findIfExistHonor(slEpHonor.getEpId(), slEpHonor.getHonorDesc());
                        slEpHonor.setEpId(slEpHonor.getEpId());
                    }
                    Long honorId = this.commonLogic.maxId("SL_EP_HONOR", "HONOR_ID");
                    slEpHonor.setHonorId(honorId);
                    slEpHonor.setCrtId(loginId);
                    this.sL24110300401Logic.saveSlEpHonor(slEpHonor, slEpHonor.getEpId());
                } else {
                    slEpHonor.setUpdId(loginId);
                    this.sl2411010401Logic.updateEpHonor(slEpHonor);
                }
            }
        }
    }

    /**
     * 企业产品品牌新增or修改（单个新增）
     */
    @Transactional
    public void saveOrUpdateForslEpBrand(List<ISL231180SlEpBrandList> slEpBrandList) {
        for (ISL231180SlEpBrandList isl231180SlEpBrandList : slEpBrandList) {
            if (!StringUtil.isNullOrEmpty(StringUtil.toSafeString(isl231180SlEpBrandList.getEpId()))) {
                // 根据epid查询brandid，进行设置
                SlEpBrand brand = this.sL2411030071Logic.maxBrandByEpId(isl231180SlEpBrandList.getEpId());
                Long brandId = 1L;
                if (brand != null && brand.getBrandId() + 1L > 9) {
                    throw new BusinessException("企业产品品牌不能超过9个");
                } else if (null != brand) {
                    brandId = brand.getBrandId() + 1L;
                }
                if ("1".equals(isl231180SlEpBrandList.getDelFlg())) {
//                    BaseParam param = new BaseParam();
//                    param.setFilter("epId", StringUtil.toSafeString(isl231180SlEpBrandList.getEpId()));
//                    param.setFilter("brandId", StringUtil.toSafeString(isl231180SlEpBrandList.getBrandId()));
//                    param.setFilter("delFlg", "1");
                    isl231180SlEpBrandList.setUpdTime(DateTimeUtil.getCustomerDate());
                    super.modify(SqlId.SQL_ID_DELETE_SLEPBRAND, isl231180SlEpBrandList);
                } else if (StringUtil.isNullOrEmpty(isl231180SlEpBrandList.getDelFlg())
                        || "0".equals(isl231180SlEpBrandList.getDelFlg())) {
                    /** 保存企业产品品牌 以及保存品牌到卖家产品品牌中 */
                    SlEpBrand slEpBrand = new SlEpBrand();
                    slEpBrand.setEpId(isl231180SlEpBrandList.getEpId());
                    slEpBrand.setBrandId(brandId);
                    slEpBrand.setBrandName(isl231180SlEpBrandList.getBrandName());
                    slEpBrand.setBrandNo(isl231180SlEpBrandList.getBrandNo());
                    slEpBrand.setBrandTermBegin(isl231180SlEpBrandList.getBrandTermBegin());
                    slEpBrand.setBrandTermEnd(isl231180SlEpBrandList.getBrandTermEnd());
                    slEpBrand.setBrandClass(isl231180SlEpBrandList.getBrandClass());
                    slEpBrand.setCrtId(loginId);
                    // 保存企业产品品牌
                    this.iSL231146RsLogic.saveSLEpBrandc(slEpBrand);
                    BaseParam base = new BaseParam();
                    base.setFilter("epId", StringUtil.toSafeString(isl231180SlEpBrandList.getEpId()));
                    SlSeller slSellerList = super.findOne(SqlId.SQL_ID_FIND_SLSELLEREPID, base);
                    if (null != slSellerList) {
                        if (NumberConst.IntDef.INT_ZERO == slSellerList.getSlMainClass()
                                || NumberConst.IntDef.INT_ONE == slSellerList.getSlMainClass()
                                || NumberConst.IntDef.INT_THREE == slSellerList.getSlMainClass()) {
                            // 设置卖家产品品牌，自有品牌添加
                            ISlPdBrand slPdBrand = new ISlPdBrand();
                            slPdBrand.setSlCode(slSellerList.getSlCode());
                            slPdBrand.setBrandEpId(isl231180SlEpBrandList.getEpId());
                            slPdBrand.setBrandId(slEpBrand.getBrandId());
                            slPdBrand.setBrandName(isl231180SlEpBrandList.getBrandName());
                            slPdBrand.setTermBegin(isl231180SlEpBrandList.getBrandTermBegin());
                            slPdBrand.setTermEnd(isl231180SlEpBrandList.getBrandTermEnd());
                            slPdBrand.setBrandClass(isl231180SlEpBrandList.getBrandClass());
                            slPdBrand.setBrandType(1);
                            slPdBrand.setCrtId(loginId);
                            slPdBrand.setCrtTime(DateTimeUtil.getCustomerDate());
                            // 保存卖家产品品牌，添加自有品牌
                            this.isl231150RsLogic.saveSLPdBrand(slPdBrand);
                            List<SlEpBrandHonor> slEpBrandHonorList = isl231180SlEpBrandList.getSlEpBrandHonorList();
                            if (null != slEpBrandHonorList && slEpBrandHonorList.size() > 0) {
                                for (SlEpBrandHonor slEpBrandHonor : slEpBrandHonorList) {
                                    if ("1".equals(slEpBrandHonor.getDelFlg())) {
//                                        BaseParam param = new BaseParam();
//                                        param.setFilter("epId", StringUtil.toSafeString(slEpBrandHonor.getEpId()));
//                                        param.setFilter("brandId",
//                                            StringUtil.toSafeString(slEpBrandHonor.getBrandId()));
//                                        param.setFilter("honorId",
//                                            StringUtil.toSafeString(slEpBrandHonor.getHonorId()));
//                                        param.setFilter("delFlg", "1");
                                        slEpBrandHonor.setUpdTime(DateTimeUtil.getCustomerDate());
                                        super.modify(SqlId.SQL_ID_DELETE_SLEPBRANDHONOR, slEpBrandHonor);
                                    } else if (StringUtil.isNullOrEmpty(slEpBrandHonor.getDelFlg())
                                            || "0".equals(slEpBrandHonor.getDelFlg())) {
                                        slEpBrandHonor.setEpId(slEpBrandHonor.getEpId());
                                        slEpBrandHonor.setBrandId(brandId);
                                        Long honorId = this.commonLogic.maxId("SL_EP_BRAND_HONOR", "HONOR_ID");
                                        slEpBrandHonor.setHonorId(honorId);
                                        slEpBrandHonor.setCrtId(loginId);
                                        this.isl231154RsLogic.saveSLEpBrandHonor(slEpBrandHonor);
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                List<SlEpBrandHonor> slEpBrandHonorList = isl231180SlEpBrandList.getSlEpBrandHonorList();
                if (null != slEpBrandHonorList && slEpBrandHonorList.size() > 0) {
                    for (SlEpBrandHonor slEpBrandHonor : slEpBrandHonorList) {
                        if ("1".equals(slEpBrandHonor.getDelFlg())) {
//                            BaseParam param = new BaseParam();
//                            param.setFilter("epId", StringUtil.toSafeString(slEpBrandHonor.getEpId()));
//                            param.setFilter("brandId", StringUtil.toSafeString(slEpBrandHonor.getBrandId()));
//                            param.setFilter("honorId", StringUtil.toSafeString(slEpBrandHonor.getHonorId()));
//                            param.setFilter("delFlg", "1");
                            slEpBrandHonor.setUpdTime(DateTimeUtil.getCustomerDate());
                            super.modify(SqlId.SQL_ID_DELETE_SLEPBRANDHONOR, slEpBrandHonor);
                        } else if (StringUtil.isNullOrEmpty(slEpBrandHonor.getDelFlg())
                                || "0".equals(slEpBrandHonor.getDelFlg())) {
                            slEpBrandHonor.setEpId(slEpBrandHonor.getEpId());
                            slEpBrandHonor.setBrandId(slEpBrandHonor.getBrandId());
                            Long honorId = this.commonLogic.maxId("SL_EP_BRAND_HONOR", "HONOR_ID");
                            slEpBrandHonor.setHonorId(honorId);
                            slEpBrandHonor.setCrtId(loginId);
                            this.isl231154RsLogic.saveSLEpBrandHonor(slEpBrandHonor);
                        }
                    }
                }
            }
        }
    }

    /**
     * 企业产品品牌新增or修改（全体 + 修改操作）
     */
    @Transactional
    public void saveOrUpdateForslEpBrand(List<ISL231180SlEpBrandList> slEpBrandList, Integer insertFlag) {
        for (ISL231180SlEpBrandList isl231180SlEpBrandList : slEpBrandList) {
            SlEpBrand slEpBrand = new SlEpBrand();
            slEpBrand.setBrandName(isl231180SlEpBrandList.getBrandName());
            slEpBrand.setBrandNo(isl231180SlEpBrandList.getBrandNo());
            slEpBrand.setBrandTermBegin(isl231180SlEpBrandList.getBrandTermBegin());
            slEpBrand.setBrandTermEnd(isl231180SlEpBrandList.getBrandTermEnd());
            slEpBrand.setBrandClass(isl231180SlEpBrandList.getBrandClass());
            slEpBrand.setCrtId(loginId);
            slEpBrand.setCrtTime(DateTimeUtil.getCustomerDate());
            slEpBrand.setUpdId(loginId);
            slEpBrand.setUpdTime(DateTimeUtil.getCustomerDate());
            if (null != insertFlag) {
                slEpBrand.setBrandId(1L);
                if (slEpBrandList.size() > 9) {
                    throw new BusinessException("企业产品品牌不能超过9个");
                } else {
                    // 根据epid查询brandid，进行设置
                    SlEpBrand brand = this.sL2411030071Logic.maxBrandByEpId(epId);
                    if (brand != null && brand.getBrandId() + 1L > 9) {
                        throw new BusinessException("企业产品品牌不能超过9个");
                    } else if (null != brand) {
                        slEpBrand.setBrandId(brand.getBrandId() + 1L);
                    }
                }
                /** 保存企业产品品牌 以及保存品牌到卖家产品品牌中 */
                slEpBrand.setEpId(epId);
                // 保存企业产品品牌
                this.iSL231146RsLogic.saveSLEpBrandc(slEpBrand);
                if (("0").equals(mainClass) || ("1").equals(mainClass) || ("3").equals(mainClass)) {
                    ISlPdBrand slPdBrand = new ISlPdBrand();
                    slPdBrand.setSlCode(selCode);
                    slPdBrand.setBrandEpId(epId);
                    slPdBrand.setBrandId(slEpBrand.getBrandId());
                    slPdBrand.setBrandName(isl231180SlEpBrandList.getBrandName());
                    slPdBrand.setTermBegin(isl231180SlEpBrandList.getBrandTermBegin());
                    slPdBrand.setTermEnd(isl231180SlEpBrandList.getBrandTermEnd());
                    slPdBrand.setBrandClass(isl231180SlEpBrandList.getBrandClass());
                    slPdBrand.setBrandType(1);
                    slPdBrand.setCrtId(loginId);
                    // 保存卖家产品品牌，添加自有品牌
                    this.isl231150RsLogic.saveSLPdBrand(slPdBrand);

                    List<SlEpBrandHonor> slEpBrandHonorList = isl231180SlEpBrandList.getSlEpBrandHonorList();
                    if (null != slEpBrandHonorList && slEpBrandHonorList.size() > 0) {
                        for (SlEpBrandHonor slEpBrandHonor : slEpBrandHonorList) {
                            // 描述不为空
                            if (!StringUtil.isNullOrEmpty(slEpBrandHonor.getHonorDes())) {
                                slEpBrandHonor.setEpId(epId);
                                slEpBrandHonor.setBrandId(slEpBrand.getBrandId());
                                Long honorId = this.commonLogic.maxId("SL_EP_BRAND_HONOR", "HONOR_ID");
                                slEpBrandHonor.setHonorId(honorId);
                                slEpBrandHonor.setCrtId(loginId);
                                this.isl231154RsLogic.saveSLEpBrandHonor(slEpBrandHonor);
                            }
                        }
                    }
                }
            } else {
                slEpBrand.setEpId(isl231180SlEpBrandList.getEpId());
                slEpBrand.setBrandId(isl231180SlEpBrandList.getBrandId().longValue());
                // 更新企业产品品牌
                super.modify(SqlId.SQL_ID_UPDATE_SLEPBRAND, slEpBrand);
                BaseParam params = new BaseParam();
                params.setFilter("epId", isl231180SlEpBrandList.getEpId().toString());
                SlSeller slTemp = super.findOne(SqlId.SQL_ID_FIND_SLCODE, params);
                if (null != slTemp) {
                    SlPdBrand slPdBrand = new SlPdBrand();
                    slPdBrand.setSlCode(slTemp.getSlCode());
                    slPdBrand.setBrandId(isl231180SlEpBrandList.getBrandId().longValue());
                    slPdBrand.setBrandEpId(isl231180SlEpBrandList.getEpId().longValue());
                    slPdBrand.setBrandName(isl231180SlEpBrandList.getBrandName());
                    slPdBrand.setBrandType(NumberConst.IntDef.INT_ONE);
                    slPdBrand.setTermBegin(isl231180SlEpBrandList.getBrandTermBegin());
                    slPdBrand.setTermEnd(isl231180SlEpBrandList.getBrandTermEnd());
                    slPdBrand.setBrandClass(isl231180SlEpBrandList.getBrandClass());
                    slPdBrand.setUpdId(loginId);
                    slPdBrand.setUpdTime(DateTimeUtil.getCustomerDate());
                    super.modify(SqlId.SQL_ID_UPDATE_SLSELFPDBRAND, slPdBrand);
                }
                // 企业产品品牌荣誉
                List<SlEpBrandHonor> slEpBrandHonorList = isl231180SlEpBrandList.getSlEpBrandHonorList();
                if (null != slEpBrandHonorList && slEpBrandHonorList.size() > 0) {
                    for (SlEpBrandHonor slEpBrandHonor : slEpBrandHonorList) {
                        /** 更新企业产品品牌荣誉 */
                        slEpBrandHonor.setUpdId(loginId);
                        slEpBrandHonor.setUpdTime(DateTimeUtil.getCustomerDate());
                        super.modify(SqlId.SQL_ID_UPDATE_SLEPBRANDHONOR, slEpBrandHonor);
                    }
                }
            }
        }
    }

    /**
     * 单个新增卖家品牌业务处理
     */
    @Transactional
    public void saveOrUpdateForSlPdBrand(List<ISlPdBrand> slPdBrandList) {
        for (ISlPdBrand slPdBrandTwo : slPdBrandList) {
            if ("1".equals(slPdBrandTwo.getDelFlg())) {
//                BaseParam param = new BaseParam();
//                param.setFilter("slCode", StringUtil.toSafeString(slPdBrandTwo.getSlCode()));
//                param.setFilter("brandEpId", StringUtil.toSafeString(slPdBrandTwo.getBrandEpId()));
//                param.setFilter("brandId", StringUtil.toSafeString(slPdBrandTwo.getBrandId()));
//                param.setFilter("delFlg", "1");
                slPdBrandTwo.setUpdTime(DateTimeUtil.getCustomerDate());
                super.modify(SqlId.SQL_ID_DELETE_SLPDBRAND, slPdBrandTwo);
            } else if (StringUtil.isNullOrEmpty(slPdBrandTwo.getDelFlg()) || "0".equals(slPdBrandTwo.getDelFlg())) {
                // 查询卖家品牌是否存在
                BaseParam param = new BaseParam();
                param.setFilter("slCode", StringUtil.toSafeString(slPdBrandTwo.getSlCode()));
                param.setFilter("brandEpId", StringUtil.toSafeString(slPdBrandTwo.getBrandEpId()));
                param.setFilter("brandId", StringUtil.toSafeString(slPdBrandTwo.getBrandId()));
                SlPdBrand slPdBrandThree = super.findOne(SqlId.SQL_ID_QUERY_SLPDBREED, param);
                if (null != slPdBrandThree) {
                    if ("1".equals(slPdBrandThree.getDelFlg())) {
//                        BaseParam params = new BaseParam();
//                        params.setFilter("slCode", StringUtil.toSafeString(slPdBrandTwo.getSlCode()));
//                        params.setFilter("brandEpId", StringUtil.toSafeString(slPdBrandTwo.getBrandEpId()));
//                        params.setFilter("brandId", StringUtil.toSafeString(slPdBrandTwo.getBrandId()));
//                        params.setFilter("delFlg", "0");
                        slPdBrandTwo.setUpdTime(DateTimeUtil.getCustomerDate());
                        super.modify(SqlId.SQL_ID_DELETE_SLPDBRAND, slPdBrandTwo);
                        // 在保存企业产品品牌的时候已经保存了该企业的所有品牌到卖家产品品牌，现在添加的是别的企业的产品品牌,brandId必须存在
                        if (null != slPdBrandTwo.getBrandEpId() && null != slPdBrandTwo.getBrandId()) {
                            BaseParam baseParams = new BaseParam();
                            baseParams.setFilter("brandEpId", slPdBrandTwo.getBrandEpId().toString());
                            baseParams.setFilter("brandId", slPdBrandTwo.getBrandId().toString());
                            // 查询对应企业下的企业品牌ID是否存在，不存在就报错
                            List<SlEpBrand> slEpBrands = super.findList(SqlId.SQL_ID_FIND_SLEPBRAND, baseParams);
                            if (null == slEpBrands || slEpBrands.size() == 0) {
                                throw new BusinessException("在卖家产品品牌增加别的企业的品牌时，录入了不存在的企业产品品牌");
                            }
                            // 查询是否传入了slCode,如果传入了，更新操作，没有传入，新增操作
                            if (!StringUtil.isNullOrEmpty(slPdBrandTwo.getSlCode())) {
                                BaseParam paramTwo = new BaseParam();
                                paramTwo.setFilter("slCode", slPdBrandTwo.getSlCode());
                                paramTwo.setFilter("brandEpId", StringUtil.toSafeString(slPdBrandTwo.getBrandEpId()));
                                paramTwo.setFilter("brandId", StringUtil.toSafeString(slPdBrandTwo.getBrandId()));
                                // 根据传入的数据查询数据库中是否已经存在，存在更新，不存在报错
                                SlPdBrand slPdBrand1 = super.findOne(SqlId.SQL_ID_FIND_SLPDBRANDIFEXIST, paramTwo);
                                if (null != slPdBrand1) {
                                    /** 更新操作 */
                                    slPdBrandTwo.setUpdId(loginId);
                                    slPdBrandTwo.setUpdTime(DateTimeUtil.getCustomerDate());
                                    super.modify(SqlId.SQL_ID_UPDATE_SLPDBRAND, slPdBrandTwo);
                                } else {
                                    throw new BusinessException("没有查询到您输入的卖家产品品牌信息");
                                }
                            }
                        }
                    } else {
                        throw new BusinessException("卖家ID为:" + slPdBrandTwo.getSlCode() + ",企业ID为:"
                                + StringUtil.toSafeString(slPdBrandTwo.getBrandEpId()) + ",品牌ID为:"
                                + StringUtil.toSafeString(slPdBrandTwo.getBrandId()) + "，记录已存在");
                    }
                } else {
                    // 设置卖家产品品牌，自有品牌添加
                    ISlPdBrand slPdBrand = new ISlPdBrand();
                    slPdBrand.setSlCode(slPdBrandTwo.getSlCode());
                    slPdBrand.setBrandEpId(slPdBrandTwo.getBrandEpId());
                    slPdBrand.setBrandId(slPdBrandTwo.getBrandId());
                    slPdBrand.setBrandName(slPdBrandTwo.getBrandName());
                    slPdBrand.setTermBegin(slPdBrandTwo.getTermBegin());
                    slPdBrand.setTermEnd(slPdBrandTwo.getTermBegin());
                    slPdBrand.setBrandClass(slPdBrandTwo.getBrandClass());
                    slPdBrand.setBrandType(1);
                    slPdBrand.setCrtId(loginId);
                    // 保存卖家产品品牌，添加自有品牌
                    this.isl231150RsLogic.saveSLPdBrand(slPdBrand);
                }
            }
        }
    }

    /**
     * 全体新增卖家品牌业务处理(针对新增用于 非自己企业品牌时)
     */
    @Transactional
    public void saveOrUpdateForSlPdBrand(List<ISlPdBrand> slPdBrandList, Integer insertFlag) {
        for (ISlPdBrand slPdBrand : slPdBrandList) {
            slPdBrand.setCrtId(loginId);
            slPdBrand.setUpdId(loginId);
            slPdBrand.setUpdTime(DateTimeUtil.getCustomerDate());
            slPdBrand.setCrtTime(DateTimeUtil.getCustomerDate());
            // 在保存企业产品品牌的时候已经保存了该企业的所有品牌到卖家产品品牌，现在添加的是别的企业的产品品牌,brandId必须存在
            if ((null != slPdBrand.getBrandEpId() && null != slPdBrand.getBrandId())
                    || (slPdBrand.getBrandEpId() != null && slPdBrand.getBrandEpId().equals(epId))) {
                BaseParam baseParams = new BaseParam();
                baseParams.setFilter("brandEpId", slPdBrand.getBrandEpId().toString());
                baseParams.setFilter("brandId", slPdBrand.getBrandId().toString());
                // 查询对应企业下的企业品牌ID是否存在，不存在就报错
                List<SlEpBrand> slEpBrands = super.findList(SqlId.SQL_ID_FIND_SLEPBRAND, baseParams);
                if (null == slEpBrands || slEpBrands.size() == 0) {
                    throw new BusinessException("在卖家产品品牌增加别的企业的品牌时，录入了不存在的企业产品品牌");
                }
                // 查询是否传入了slCode,如果传入了，更新操作，没有传入，新增操作
                if (!StringUtil.isNullOrEmpty(slPdBrand.getSlCode()) && null == insertFlag) {
                    BaseParam paramTwo = new BaseParam();
                    paramTwo.setFilter("slCode", slPdBrand.getSlCode());
                    paramTwo.setFilter("brandEpId", slPdBrand.getBrandEpId().toString());
                    paramTwo.setFilter("brandId", slPdBrand.getBrandId().toString());
                    // 根据传入的数据查询数据库中是否已经存在，存在更新，不存在报错
                    SlPdBrand slPdBrand1 = super.findOne(SqlId.SQL_ID_FIND_SLPDBRANDIFEXIST, paramTwo);
                    if (null != slPdBrand1) {
                        /** 更新操作 */
                        super.modify(SqlId.SQL_ID_UPDATE_SLPDBRAND, slPdBrand);
                    } else {
                        throw new BusinessException("没有查询到您输入的卖家产品品牌信息");
                    }
                } else {
                    /** 检查企业品牌是否存在 */
                    BaseParam base = new BaseParam();
                    baseParams.setFilter("brandEpId", slPdBrand.getBrandEpId().toString());
                    baseParams.setFilter("brandId", slPdBrand.getBrandId().toString());
                    // 查询对应企业下的企业品牌ID是否存在，不存在就报错
                    List<SlEpBrand> slEpBrandList = super.findList(SqlId.SQL_ID_FIND_SLEPBRAND, baseParams);
                    if (null == slEpBrandList || slEpBrandList.size() == 0) {
                        throw new BusinessException("新增卖家品牌的时候,对应企业品牌不存在");
                    }
                    /** 卖家产品品牌新增 */
                    this.isl231150RsLogic.saveSLPdBrand(slPdBrand);
                }
            }
        }
    }

    /**
     * 企业车间新增or修改
     */
    @Transactional
    public void saveOrUpdateForSlEpWorkshop(List<SlEpWorkshop> slEpWorkshopList, Integer insertFlag) {
        for (SlEpWorkshop slEpWorkshop : slEpWorkshopList) {
            if ("1".equals(slEpWorkshop.getDelFlg())) {
//                BaseParam param = new BaseParam();
//                param.setFilter("epId", StringUtil.toSafeString(slEpWorkshop.getEpId()));
//                param.setFilter("workShopId", StringUtil.toSafeString(slEpWorkshop.getWorkshopId()));
//                param.setFilter("delFlg", "1");
                slEpWorkshop.setUpdTime(DateTimeUtil.getCustomerDate());
                super.modify(SqlId.SQL_ID_DELETE_WORKSHOPID, slEpWorkshop);
            } else if (StringUtil.isNullOrEmpty(slEpWorkshop.getDelFlg()) || "0".equals(slEpWorkshop.getDelFlg())) {
                if (null != insertFlag) {
                    if (!"".equals(sellerAccount)) {
                        slEpWorkshop.setEpId(epId);
                    } else {
                        slEpWorkshop.setEpId(slEpWorkshop.getEpId());
                    }
                    Long maxId = this.commonLogic.maxId("SL_EP_WORKSHOP", "WORKSHOP_ID");
                    slEpWorkshop.setWorkshopId(maxId);
                    slEpWorkshop.setCrtId(loginId);
                    slEpWorkshop.setCrtTime(DateTimeUtil.getCustomerDate());
                    super.save(SqlId.SQL_ID_SAVE_SLEPWORKSHOP, slEpWorkshop);
                } else {
                    slEpWorkshop.setUpdId(loginId);
                    this.sl2411010501Logic.updateSlEpWorkshop(slEpWorkshop);
                }
            }
        }
    }

    /**
     * 企业生产能力新增or修改
     */
    @Transactional
    public void saveOrUpdateForSlEpCap(SlEpCap slEpCap, Integer insertFlag) {
        if (null != insertFlag) {
            SlEpCap slEpCap1 = this.sl24110300402Logic.findSlEpCapIfExist(slEpCap.getEpId());
            if (null != slEpCap1 && "".equals(sellerAccount)) {
                throw new BusinessException("企业ID为:" + slEpCap.getEpId() + ",的基本能力已存在");
            }
            /** 新增操作 */
            if (!"".equals(sellerAccount)) {
                slEpCap.setEpId(epId);
            } else {
                slEpCap.setEpId(slEpCap.getEpId());
            }
            slEpCap.setCrtId(loginId);
            this.sl24110300402Logic.saveSlEpCap(slEpCap);
        } else {
            slEpCap.setUpdId(loginId);
            slEpCap.setUpdTime(DateTimeUtil.getCustomerDate());
            super.modify(SqlId.SQL_ID_UPDATE_SLEPCAP, slEpCap);
        }
    }

    /**
     * 单个生产商新增处理
     */
    @Transactional
    public void saveOrUpdateForSlEpAuth(List<ISL231180SLEpAuth> isl231180SLEpAuthList) {
        for (ISL231180SLEpAuth isl231180SLEpAuth : isl231180SLEpAuthList) {
            /** 新增操作 */
            if ("1".equals(isl231180SLEpAuth.getFlag())) {
                if ("1".equals(isl231180SLEpAuth.getDelFlg())) {
//                    BaseParam param = new BaseParam();
//                    param.setFilter("slCode", StringUtil.toSafeString(isl231180SLEpAuth.getSlCode()));
//                    param.setFilter("epId", StringUtil.toSafeString(isl231180SLEpAuth.getProducerEpId()));
//                    param.setFilter("delFlg", "1");
                    isl231180SLEpAuth.setUpdTime(DateTimeUtil.getCustomerDate());
                    super.modify(SqlId.SQL_ID_DELETE_SLEPAGENTAUTH, isl231180SLEpAuth);
                } else if (StringUtil.isNullOrEmpty(isl231180SLEpAuth.getDelFlg())
                        || "0".equals(isl231180SLEpAuth.getDelFlg())) {
                    // 查询记录是否存在
                    BaseParam param = new BaseParam();
                    param.setFilter("slCode", StringUtil.toSafeString(isl231180SLEpAuth.getSlCode()));
                    param.setFilter("epId", StringUtil.toSafeString(isl231180SLEpAuth.getProducerEpId()));
                    SlEpAgentAuth slEpAgentAuthTwo = super.findOne(SqlId.SQL_ID_QUERY_SLEPAGENTAUTH, param);
                    if (null != slEpAgentAuthTwo) {
                        String delFlg = slEpAgentAuthTwo.getDelFlg();
                        if ("1".equals(delFlg)) {
                            BaseParam basepa = new BaseParam();
                            basepa.setFilter("epId", isl231180SLEpAuth.getProducerEpId().toString());
                            SlEnterprise slEnterprise1 = super.findOne(SqlId.SQL_ID_FIND_SLENTERPRISE, basepa);
                            if (null == slEnterprise1) {
                                throw new BusinessException("你录入的生产商_企业ID不存在");
                            }
                            /** 更新操作 */
                            if (isl231180SLEpAuth.getFlag().equals("1")) {
                                /** 更新卖家代理及分销授权 */
                                SlEpAgentAuth slEpAgentAuth = new SlEpAgentAuth();
                                slEpAgentAuth.setSlCode(isl231180SLEpAuth.getSlCode());
                                slEpAgentAuth.setProducerEpId(isl231180SLEpAuth.getProducerEpId());
                                slEpAgentAuth.setContractNo(isl231180SLEpAuth.getContractNo());
                                slEpAgentAuth.setAuthEpName(isl231180SLEpAuth.getAuthEpName());
                                slEpAgentAuth.setAuthTermBegin(isl231180SLEpAuth.getAuthTermBegin());
                                slEpAgentAuth.setAuthTermEnd(isl231180SLEpAuth.getAuthTermEnd());
                                slEpAgentAuth.setAuthTermUnliimited(isl231180SLEpAuth.getAuthTermUnliimited());
                                slEpAgentAuth.setUpdId(loginId);
                                slEpAgentAuth.setUpdTime(DateTimeUtil.getCustomerDate());
                                super.modify(SqlId.SQL_ID_UPDATE_SLEPAGENTAUTH, slEpAgentAuth);
                            }
                        } else {
                            throw new BusinessException("卖家ID为:" + isl231180SLEpAuth.getSlCode() + ",企业ID为:"
                                    + StringUtil.toSafeString(isl231180SLEpAuth.getProducerEpId()) + ",记录已存在");
                        }
                    } else {
                        /** 新增代理及分销授权 */
                        SlEpAgentAuth slEpAgentAuth = new SlEpAgentAuth();
                        slEpAgentAuth.setSlCode(isl231180SLEpAuth.getSlCode());
                        slEpAgentAuth.setProducerEpId(isl231180SLEpAuth.getProducerEpId());
                        slEpAgentAuth.setContractNo(isl231180SLEpAuth.getContractNo());
                        slEpAgentAuth.setAuthEpName(isl231180SLEpAuth.getAuthEpName());
                        slEpAgentAuth.setAuthTermBegin(isl231180SLEpAuth.getAuthTermBegin());
                        slEpAgentAuth.setAuthTermEnd(isl231180SLEpAuth.getAuthTermEnd());
                        slEpAgentAuth.setAuthTermUnliimited(isl231180SLEpAuth.getAuthTermUnliimited());
                        slEpAgentAuth.setCrtId(loginId);
                        super.save(SqlId.SQL_ID_SAVE_SLEPAGENTAUTH, slEpAgentAuth);
                    }
                }
            }
            if ("2".equals(isl231180SLEpAuth.getFlag())) {
                if ("1".equals(isl231180SLEpAuth.getDelFlg())) {
//                    BaseParam param = new BaseParam();
//                    param.setFilter("slCode", StringUtil.toSafeString(isl231180SLEpAuth.getSlCode()));
//                    param.setFilter("epId", StringUtil.toSafeString(isl231180SLEpAuth.getProducerEpId()));
//                    param.setFilter("delFlg", "1");
                    isl231180SLEpAuth.setUpdTime(DateTimeUtil.getCustomerDate());
                    super.modify(SqlId.SQL_ID_DELETE_SLEPOEMAUTH, isl231180SLEpAuth);
                } else if (StringUtil.isNullOrEmpty(isl231180SLEpAuth.getDelFlg())
                        || "0".equals(isl231180SLEpAuth.getDelFlg())) {
                    // 查询记录是否存在
                    BaseParam param = new BaseParam();
                    param.setFilter("slCode", StringUtil.toSafeString(isl231180SLEpAuth.getSlCode()));
                    param.setFilter("epId", StringUtil.toSafeString(isl231180SLEpAuth.getProducerEpId()));
                    SlEpOemAuth slEpOemAuthTwo = super.findOne(SqlId.SQL_ID_QUERY_SLEPOEMAUTH, param);
                    if (null != slEpOemAuthTwo) {
                        String delFlg = slEpOemAuthTwo.getDelFlg();
                        if ("1".equals(delFlg)) {
                            /** 更新卖家代理及分销授权 */
                            SlEpOemAuth slEpOemAuth = new SlEpOemAuth();
                            slEpOemAuth.setSlCode(isl231180SLEpAuth.getSlCode());
                            slEpOemAuth.setProducerEpId(isl231180SLEpAuth.getProducerEpId());
                            slEpOemAuth.setContractNo(isl231180SLEpAuth.getContractNo());
                            slEpOemAuth.setAuthEpName(isl231180SLEpAuth.getAuthEpName());
                            slEpOemAuth.setAuthTermBegin(isl231180SLEpAuth.getAuthTermBegin());
                            slEpOemAuth.setAuthTermEnd(isl231180SLEpAuth.getAuthTermEnd());
                            slEpOemAuth.setAuthTermUnliimited(isl231180SLEpAuth.getAuthTermUnliimited());
                            slEpOemAuth.setUpdId(loginId);
                            slEpOemAuth.setUpdTime(DateTimeUtil.getCustomerDate());
                            super.modify(SqlId.SQL_ID_UPDATE_SLEPOEMAUTH, slEpOemAuth);
                        } else {
                            throw new BusinessException("卖家ID为:" + isl231180SLEpAuth.getSlCode() + ",企业ID为:"
                                    + StringUtil.toSafeString(isl231180SLEpAuth.getProducerEpId()) + ",记录已存在");
                        }
                    } else {
                        /** 新增oem委托授权 */
                        SlEpOemAuth slEpOemAuth = new SlEpOemAuth();
                        slEpOemAuth.setSlCode(isl231180SLEpAuth.getSlCode());
                        slEpOemAuth.setProducerEpId(isl231180SLEpAuth.getProducerEpId());
                        slEpOemAuth.setContractNo(isl231180SLEpAuth.getContractNo());
                        slEpOemAuth.setAuthEpName(isl231180SLEpAuth.getAuthEpName());
                        slEpOemAuth.setAuthTermBegin(isl231180SLEpAuth.getAuthTermBegin());
                        slEpOemAuth.setAuthTermEnd(isl231180SLEpAuth.getAuthTermEnd());
                        slEpOemAuth.setAuthTermUnliimited(isl231180SLEpAuth.getAuthTermUnliimited());
                        slEpOemAuth.setCrtId(loginId);
                        slEpOemAuth.setCrtTime(DateTimeUtil.getCustomerDate());
                        super.save(SqlId.SQL_ID_SAVE_SLEPOEMAUTH, slEpOemAuth);
                    }
                }
            }
        }
    }

    /**
     * 全体生产商新增 + 修改
     */
    @Transactional
    public void saveOrUpdateForSlEpAuth(List<ISL231180SLEpAuth> isl231180SLEpAuthList, Integer insertFlag) {
        for (ISL231180SLEpAuth isl231180SLEpAuth : isl231180SLEpAuthList) {
            if (insertFlag == null) {
                BaseParam basepa = new BaseParam();
                basepa.setFilter("epId", isl231180SLEpAuth.getProducerEpId().toString());
                SlEnterprise slEnterprise1 = super.findOne(SqlId.SQL_ID_FIND_SLENTERPRISE, basepa);
                if (null == slEnterprise1) {
                    throw new BusinessException("你录入的生产商_企业ID不存在");
                }
            }
            /** 新增操作 */
            if ("1".equals(isl231180SLEpAuth.getFlag())) {
                /** 新增\更新代理及分销授权 */
                SlEpAgentAuth slEpAgentAuth = new SlEpAgentAuth();
                if (!"".equals(sellerAccount)) {
                    slEpAgentAuth.setSlCode(selCode);
                    slEpAgentAuth.setProducerEpId(epId);
                } else {
                    slEpAgentAuth.setSlCode(isl231180SLEpAuth.getSlCode());
                    slEpAgentAuth.setProducerEpId(isl231180SLEpAuth.getProducerEpId());
                }
                slEpAgentAuth.setContractNo(isl231180SLEpAuth.getContractNo());
                slEpAgentAuth.setAuthEpName(isl231180SLEpAuth.getAuthEpName());
                slEpAgentAuth.setAuthTermBegin(isl231180SLEpAuth.getAuthTermBegin());
                slEpAgentAuth.setAuthTermEnd(isl231180SLEpAuth.getAuthTermEnd());
                slEpAgentAuth.setAuthTermUnliimited(isl231180SLEpAuth.getAuthTermUnliimited());
                slEpAgentAuth.setCrtId(loginId);
                slEpAgentAuth.setCrtTime(DateTimeUtil.getCustomerDate());
                slEpAgentAuth.setUpdId(loginId);
                slEpAgentAuth.setUpdTime(DateTimeUtil.getCustomerDate());
                if (insertFlag != null) {
                    super.save(SqlId.SQL_ID_SAVE_SLEPAGENTAUTH, slEpAgentAuth);
                } else {
                    super.modify(SqlId.SQL_ID_UPDATE_SLEPAGENTAUTH, slEpAgentAuth);
                }
            }
            if ("2".equals(isl231180SLEpAuth.getFlag())) {
                /** 新增\更新oem委托授权 */
                SlEpOemAuth slEpOemAuth = new SlEpOemAuth();
                if (!"".equals(sellerAccount)) {
                    slEpOemAuth.setSlCode(selCode);
                    slEpOemAuth.setProducerEpId(epId);
                } else {
                    slEpOemAuth.setSlCode(isl231180SLEpAuth.getSlCode());
                    slEpOemAuth.setProducerEpId(isl231180SLEpAuth.getProducerEpId());
                }
                slEpOemAuth.setContractNo(isl231180SLEpAuth.getContractNo());
                slEpOemAuth.setAuthEpName(isl231180SLEpAuth.getAuthEpName());
                slEpOemAuth.setAuthTermBegin(isl231180SLEpAuth.getAuthTermBegin());
                slEpOemAuth.setAuthTermEnd(isl231180SLEpAuth.getAuthTermEnd());
                slEpOemAuth.setAuthTermUnliimited(isl231180SLEpAuth.getAuthTermUnliimited());
                slEpOemAuth.setCrtId(loginId);
                slEpOemAuth.setCrtTime(DateTimeUtil.getCustomerDate());
                slEpOemAuth.setUpdId(loginId);
                slEpOemAuth.setUpdTime(DateTimeUtil.getCustomerDate());
                if (null != insertFlag) {
                    super.save(SqlId.SQL_ID_SAVE_SLEPOEMAUTH, slEpOemAuth);
                } else {
                    super.modify(SqlId.SQL_ID_UPDATE_SLEPOEMAUTH, slEpOemAuth);
                }
            }
        }
    }

    /**
     * 企业管理新增or修改
     */
    @Transactional
    public void saveOrUpdateForSlEpManager(List<SlEpManager> slEpManagerList, Integer insertFlag) {
        for (SlEpManager slEpManager : slEpManagerList) {
            if ("1".equals(slEpManager.getDelFlg())) {
//                BaseParam param = new BaseParam();
//                param.setFilter("memberId", StringUtil.toSafeString(slEpManager.getMemberId()));
//                param.setFilter("epId", StringUtil.toSafeString(slEpManager.getEpId()));
//                param.setFilter("delFlg", "1");
                slEpManager.setUpdTime(DateTimeUtil.getCustomerDate());
                super.modify(SqlId.SQL_ID_DELETE_SLEPMANAGER, slEpManager);
            } else if (StringUtil.isNullOrEmpty(slEpManager.getDelFlg()) || "0".equals(slEpManager.getDelFlg())) {
                if (null != insertFlag) {
                    /** 新增企业管理团队信息 */
                    if (!"".equals(sellerAccount)) {
                        slEpManager.setEpId(epId);
                    } else {
                        slEpManager.setEpId(slEpManager.getEpId());
                    }
                    Long memberId = this.commonLogic.maxId("SL_EP_MANAGER", "MEMBER_ID");
                    slEpManager.setMemberId(memberId);
                    slEpManager.setCrtId(loginId);
                    slEpManager.setCrtTime(DateTimeUtil.getCustomerDate());
                    super.save(SqlId.SQL_ID_SAVE_SLEPMANAGER, slEpManager);
                } else {
                    slEpManager.setUpdId(loginId);
                    this.sl24110108Logic.updateSlEpManagerPort(slEpManager);
                }
            }
        }
    }

    /**
     * 企业电商新增or修改
     */
    @Transactional
    public void saveOrUpdateForSlEcTeam(List<SlEcTeam> slEcTeamList, Integer insertFlag) {
        for (SlEcTeam slEcTeam : slEcTeamList) {
            if ("1".equals(slEcTeam.getDelFlg())) {
//                BaseParam param = new BaseParam();
//                param.setFilter("memberId", StringUtil.toSafeString(slEcTeam.getMemberId()));
//                param.setFilter("slCode", StringUtil.toSafeString(slEcTeam.getSlCode()));
//                param.setFilter("delFlg", "1");
                slEcTeam.setUpdTime(DateTimeUtil.getCustomerDate());
                super.modify(SqlId.SQL_ID_DELETE_SLECTEAM, slEcTeam);
            } else if (StringUtil.isNullOrEmpty(slEcTeam.getDelFlg()) || "0".equals(slEcTeam.getDelFlg())) {
                if (null != insertFlag) {
                    /** 新增操作 */
                    if (!"".equals(sellerAccount)) {
                        slEcTeam.setSlCode(selCode);
                    } else {
                        slEcTeam.setSlCode(slEcTeam.getSlCode());
                    }
                    Long memberId = this.commonLogic.maxId("SL_EC_TEAM", "MEMBER_ID");
                    slEcTeam.setMemberId(memberId.intValue());
                    slEcTeam.setCrtId(loginId);
                    slEcTeam.setCrtTime(DateTimeUtil.getCustomerDate());
                    super.save(SqlId.SQL_ID_SAVE_SLECTEAM, slEcTeam);
                } else {
                    slEcTeam.setUpdId(loginId);
                    this.sl24110109Logic.updateSLEcTeamPort(slEcTeam);
                }

            }
        }
    }

    /**
     * 企业监测设备新增or修改
     */
    @Transactional
    public void saveOrUpdateForSlEpDd(List<SlEpDd> slEpDdList, Integer insertFlag) {
        for (SlEpDd slEpDd : slEpDdList) {
            if ("1".equals(slEpDd.getDelFlg())) {
//                BaseParam param = new BaseParam();
//                param.setFilter("ddId", StringUtil.toSafeString(slEpDd.getDdId()));
//                param.setFilter("epId", StringUtil.toSafeString(slEpDd.getEpId()));
//                param.setFilter("delFlg", "1");
                slEpDd.setUpdTime(DateTimeUtil.getCustomerDate());
                super.modify(SqlId.SQL_ID_DELETE_SLEPDD, slEpDd);
            } else if (StringUtil.isNullOrEmpty(slEpDd.getDelFlg()) || "0".equals(slEpDd.getDelFlg())) {
                if (null != insertFlag) {
                    /** 新增操作 */
                    if (!"".equals(sellerAccount)) {
                        slEpDd.setEpId(epId);
                    } else {
                        slEpDd.setEpId(slEpDd.getEpId());
                    }
                    Long ddId = this.commonLogic.maxId("SL_EP_DD", "DD_ID");
                    slEpDd.setDdId(ddId);
                    slEpDd.setCrtId(loginId);
                    slEpDd.setCrtTime(DateTimeUtil.getCustomerDate());
                    super.save(SqlId.SQL_ID_SAVE_SL_EPDD, slEpDd);
                } else {
                    slEpDd.setUpdId(loginId);
                    slEpDd.setUpdTime(DateTimeUtil.getCustomerDate());
                    super.modify(SqlId.SQL_ID_UPDATE_SL_EPDD, slEpDd);
                }
            }
        }
    }
}
