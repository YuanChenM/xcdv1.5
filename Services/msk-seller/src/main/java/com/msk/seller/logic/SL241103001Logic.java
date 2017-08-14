package com.msk.seller.logic;

import com.alibaba.fastjson.JSONObject;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.base.BaseUploadController;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.SellerConstant;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.*;
import com.msk.district.bean.*;
import com.msk.seller.bean.SLUploadFile;
import com.msk.seller.utils.BusinessConst;
import com.msk.seller.utils.SLControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fjm on 2016/1/26.
 */
@Service
public class SL241103001Logic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao){
        super.setBaseDao(baseDao);
    }

    @Autowired
    private SLAccountLogic slAccountLogic;

    @Autowired
    private SLSellerLogic slSellerLogic;

    @Autowired
    private CommonLogic commonLogic;

    interface SqlId{
        String SQL_ID_MODIFY_ONE = "modifyOne";
        String SQL_ID_SELECT_SLACCOUNT = "findOneByAccount";
        String SQL_ID_SELECT_SLACEntity = "findOneEntity";
        String SQL_ID_SAVE_SELLER = "saveSeller";
        String SQL_ID_FINDSLCODE = "findSlcode";
        String SQL_ID_SAVE_EP = "saveEp";


        String SQL_ID_DINDACCOUNTLIST="findAccountList";
        String SQL_ID_FIND_SL_CODE_MANUFACTURE="findSlCodeManufacture";
        String SQL_ID_FIND_SL_CODE_OTHER="findSlCodeOther";


        String SQL_ID_FINDSLSELLER="findSlSeller";
    }
    /**
     * 根据省code 城市Code 区Code 查询该区域是否已经存在卖家 有就去后八位+1，作为顺序号 没有就直接+00000001
     * @param cityCode 市区域code
     * @return
     */
    @Transactional(readOnly = true)
    public String findAccount(String slMainClass, String cityCode,String slConFlg) {
        String slCode=null;
        BaseParam base = new BaseParam();
        base.setFilter("cityCode",cityCode);
        base.setFilter("slMainClass",slMainClass);
        base.setFilter("slConFlg",slConFlg);
        /**查询出所有的卖家账号，查询，如果是以startFlag开头的，截取后八位*/
        int num = super.getCount(SqlId.SQL_ID_DINDACCOUNTLIST, base);
        if(num>0){
            slCode="";
        }else{
            if(slConFlg.equals("1")){
                slCode=slMainClass+cityCode+"001";
            }else{
                slCode=slMainClass+cityCode+"701";
            }
        }
        return slCode;
    }
    /**
     * 根据城市Code 查询该区域是否已经存在卖家生产商编码 有返回空，没有根据国际返回编码
     * @param cityCode 市区域code
     * @return 卖家生产商编码
     */
    @Transactional(readOnly = true)
    public String findSlCodeManufacture(String cityCode,String slConFlg) {
        String slCodeManufacture=null;
        BaseParam base = new BaseParam();
        base.setFilter("cityCode",cityCode);
        base.setFilter("slConFlg",slConFlg);
        base.setFilter("isSlCodeManufacture","1");
        /**根据区域区划和国籍查询是否已经存在编码*/
        SlSeller slSeller = super.findOne(SqlId.SQL_ID_FIND_SL_CODE_MANUFACTURE, base);
        //存在，返回空字符串，在插入时进行
        if(!StringUtil.isNullOrEmpty(slSeller.getSlCodeManufacture())){
            DecimalFormat df = new DecimalFormat("000000");
            Integer number=Integer.parseInt(slSeller.getSlCodeManufacture());
            slCodeManufacture=df.format(number);
        }else{
            //国籍为国内从001开始
            if(slConFlg.equals("1")){
                slCodeManufacture=cityCode+"001";
            }else{
                //国籍为国外从7011开始
                slCodeManufacture=cityCode+"701";
            }
        }
        return slCodeManufacture;
    }

    /**
     * 根据城市Code 查询该区域是否已经存在卖家生产商编码 有返回空，没有根据国际返回编码
     * @param param 参数
     * @return 卖家生产商编码
     */
    @Transactional(readOnly = true)
    public String findSlCodeOther(BaseParam param) {
        /**根据区域区划和国籍查询是否已经存在编码*/
        SlSeller slSeller = super.findOne(SqlId.SQL_ID_FIND_SL_CODE_OTHER, param);
        //判断获取的是哪个编码
        String selfFlg=StringUtil.toSafeString(param.getFilterMap().get("selfFlg"));
        String agentFlg=StringUtil.toSafeString(param.getFilterMap().get("agentFlg"));
        String oemFlg=StringUtil.toSafeString(param.getFilterMap().get("oemFlg"));
        String slConFlg=StringUtil.toSafeString(param.getFilterMap().get("slConFlg"));
        String cityCode=StringUtil.toSafeString(param.getFilterMap().get("cityCode"));
        //
        if("1".equals(selfFlg)){
            //存在，返回空字符串，在插入时进行
            if(!StringUtil.isNullOrEmpty(slSeller.getSlCodeSelf())){
                return StringUtil.toSafeString(Integer.parseInt(slSeller.getSlCodeSelf())+1);
            }else{
                if(slConFlg.equals("1")){
                    return "1"+cityCode+"001";
                }else{
                    return "1"+cityCode+"701";
                }
            }
        }else if("1".equals(agentFlg)){
            //存在，返回空字符串，在插入时进行
            if(!StringUtil.isNullOrEmpty(slSeller.getSlCodeAgent())){
                return StringUtil.toSafeString(Integer.parseInt(slSeller.getSlCodeAgent())+1);
            }else{
                if(slConFlg.equals("1")){
                    return 2+cityCode+"001";
                }else{
                    return 2+cityCode+"701";
                }
            }
        }else if("1".equals(oemFlg)){
            //存在，返回空字符串，在插入时进行
            if(!StringUtil.isNullOrEmpty(slSeller.getSlCodeOem())){
                return StringUtil.toSafeString(Integer.parseInt(slSeller.getSlCodeOem())+1);
            }else{
                if(slConFlg.equals("1")){
                    return 3+cityCode+"001";
                }else{
                    return 3+cityCode+"701";
                }
            }
        }
        return "";
    }

    /**
     * 根据市代码，查询大区、物流区和市。替代旧findCity()。
     * Created by xia_xiaojie on 2016/6/16.
     * @param cityCode 市代码
     * @return 大区、物流区和市
     */
    public CityBean findCity(String cityCode) {
        if (!StringUtils.hasText(cityCode)) {
            return new CityBean();
        }

        DistrictParam param = new DistrictParam();
        param.setCityCodes(new String[] {cityCode});
        param.setFlag(NumberConst.IntDef.INT_ZERO); //查询物流区
        DistrictResult result = SLControllerUtil.getCities(param);
        List<CityBean> cities = result.getCityList();
        return CollectionUtils.isEmpty(cities) ? new CityBean() : cities.get(0);
    }

    /**
     * 根据省代码，查询大区和省。替代旧findProvince()。
     * Created by xia_xiaojie on 2016/6/16.
     * @param provinceCode 省代码
     * @return 大区和省
     */
    public ProvinceBean findProvince(String provinceCode) {
        if (!StringUtils.hasText(provinceCode)) {
            return new ProvinceBean();
        }

        DistrictParam param = new DistrictParam();
        param.setProvinceCode(provinceCode);
        DistrictResult result = SLControllerUtil.getProvinces(param);
        List<ProvinceBean> provinces = result.getProvinceList();
        return CollectionUtils.isEmpty(provinces) ? new ProvinceBean() : provinces.get(0);
    }

    /**
     * 根据市和县代码，查询大区、省、市和县
     * Created by xia_xiaojie on 2016/6/17.
     * @param cityCode 市代码
     * @param districtCode 县代码
     * @return 大区、省、市和县
     */
    @Transactional
    public DistrictBean findDistrict(String cityCode, String districtCode) {
        if (!StringUtils.hasText(cityCode) || !StringUtils.hasText(districtCode)) {
            return new DistrictBean();
        }

        DistrictParam param = new DistrictParam();
        param.setComposeCodes(new String[] {cityCode + districtCode});
        DistrictResult result = SLControllerUtil.getDistricts(param);
        List<DistrictBean> districts = result.getDistrictList();
        return CollectionUtils.isEmpty(districts) ? new DistrictBean() : districts.get(0);
    }

    @Transactional
    public int saveAccount(SlAccount slAccount){
        // 处理 买手情况,放在saveSeller里处理
        slAccount.setCrtTime(DateTimeUtil.getCustomerDate());
        slAccount.setUpdTime(DateTimeUtil.getCustomerDate());
        return super.save(slAccount);
    }

    @Transactional
    public int saveSeller(SlSeller slSeller){
        slSeller.setCrtTime(DateTimeUtil.getCustomerDate());
        int result = super.save(SqlId.SQL_ID_SAVE_SELLER,slSeller);
        //卖家新增成功后处理买手情况
        if (result > NumberConst.IntDef.INT_ZERO) {
            //slAccountLogic.saveSLAccountBs(slSeller);

            slSellerLogic.saveSellerToBs(slSeller);
        }
        return result;
    }
    @Transactional
    public int saveEp(SlEnterprise slEnterprise){
        slEnterprise.setCrtTime(DateTimeUtil.getCustomerDate());
        return super.save(SqlId.SQL_ID_SAVE_EP,slEnterprise);
    }

    @Transactional(readOnly = true)
    public SlAccount slAccountEntity2(String slAccount) {
        BaseParam param = new BaseParam();
        param.setFilter("slAccount", slAccount);
        SlAccount slAccountSql1 = super.findOne(SqlId.SQL_ID_SELECT_SLACEntity, param);
        if(null == slAccountSql1 || "".equals(slAccountSql1)){
            throw  new BusinessException("卖家账户不存在");
        }
        return slAccountSql1;
    }

    @Transactional(readOnly = true)
    public  SlSeller findSlCode(String slAreaCode){
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("slAreaCode",slAreaCode);
        return super.findOne(SqlId.SQL_ID_FINDSLCODE,baseParam);
    }

    @Transactional(readOnly = true)
    public SlAccount slAccountEntity(String slAccount) {
        BaseParam param = new BaseParam();
        param.setFilter("slAccount", slAccount);
        SlAccount slAccountSql2 = super.findOne(SqlId.SQL_ID_SELECT_SLACCOUNT, param);
        return slAccountSql2;
    }


    @Transactional
    public int modifyOne(SlAccount slAccount){
        // 处理 买手情况
        slAccount.setUpdTime(DateTimeUtil.getCustomerDate());
        slAccountLogic.dealSLAccountBs(slAccount);
        return super.modify(SqlId.SQL_ID_MODIFY_ONE, slAccount);
    }

    /**
     * 查询卖家ID
     * @return
     */
    @Transactional(readOnly = true)
    public SlSeller findSlSeller(String slAccount){
        BaseParam param=new BaseParam();
        param.setFilter("slAccount",slAccount);
        SlSeller slSeller= super.findOne(SqlId.SQL_ID_FINDSLSELLER, param);
        return slSeller;
    }

    /**
     * 增加卖家账户
     * Created by zhang_jiaxing on 2016/9/6.
     */
    @Transactional
    public String insertSellerInfo(MultipartFile epthrfile, MultipartFile accountfile, MultipartFile licfile, MultipartFile taxfile,
                                MultipartFile orgfile, MultipartFile balfile, SlEnterprise slEnterprise, SlSeller slSeller, SlAccount slAccount,
                                String slConFlg, String[] slSecondaryClass, String provinceCode, String cityCode, String districtCode,
                                String licType, String licTermUnliimited, HttpServletRequest request, HttpServletResponse response,String loginId){


        // JSON返回参数
        Map<String,Object> jsonMaps = new HashMap<String,Object>();

        // 获取前台提交的账号信息
        String accountWeb = slAccount.getSlAccount();

        // 将account信息放进session里面
        // request.getSession().setAttribute("jsp_slAccount", accountWeb);
        jsonMaps.put("jsp_slAccount",accountWeb);

        // 判断这个账户是否已经存在，拿数据去后台查询数据库查询账号信息
        SlAccount EntitySql = slAccountEntity(accountWeb);
        // 账户已经有，返回告知用户换账户
        if (null != EntitySql) {
            throw new BusinessException("账户已经存在！请重新填写！");
        } else {
            // 不存在账户，那么就插入即可
            // 页面数据插入slAccount表
            if (null != accountfile) {
                if (accountfile.getSize() != 0) {
                    String suffix = new BaseUploadController().getFileSuffix(accountfile);

                    // Modified by xia_xiaojie on 2016/6/22. Modified start.
                    // slAccount.setAccountImg(request.getSession().getServletContext().getAttribute("fileSerUrl").toString()
                    // + "/"
                    // + BusinessConst.SLPath.SLIMAGEPATH + "/" + BusinessConst.SLPath.BASE + "/" +
                    // slAccount.getSlAccount() + StringConst.DOT + suffix);
                    String imgUrl = BusinessConst.SLPath.SERVICEIMAGEPATH + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/"
                            + BusinessConst.SLPath.BASE + "/" + slAccount.getSlAccount() + StringConst.DOT + suffix;
                    slAccount.setAccountImg(imgUrl);
                    // Modified end.
                } else {
                    slAccount.setAccountImg("");
                }
            }
            slAccount.setDelFlg("0");
            slAccount.setSlShowName(slEnterprise.getEpName());
            slAccount.setAuthStatus(2);
            // slAccount.setCrtId(super.getLoginUser().getCrtId());
            // Modified by xia_xiaojie on 2016/6/22. Modified start.
            slAccount.setVer(NumberConst.IntDef.INT_ONE);
            // Modified end.

            // ---------------------------------------------------------------------

            // 处理seller和enterprise数据
            slSeller.setSlAccount(accountWeb);
            if (null == provinceCode || "".equals(provinceCode)) {
                throw new BusinessException("请选择省（含省、直辖市）");
            }

            // Modified by xia_xiaojie on 2016/6/16. Modified start.
            // MdProvince mdProvince = this.sl241103001Logic.findProvince(provinceCode);
            // slSeller.setProvinceCode(provinceCode);
            // String areaCode = this.sl241103001Logic.findAreaCode(mdProvince.getAreaId());
            // slSeller.setAreaCode(areaCode);
            ProvinceBean province = findProvince(provinceCode);
            slSeller.setProvinceCode(provinceCode);
            slSeller.setProvinceName(province.getProvinceName());
            slSeller.setAreaCode(province.getAreaCode());
            slSeller.setAreaName(province.getAreaName());
            // Modified end.

            if (null == cityCode || "".equals(cityCode)) {
                throw new BusinessException("请选择地区（含地级市）");
            }

            // Modified by xia_xiaojie on 2016/6/16. Modified start.
            // MdCity mdCity = this.sl241103001Logic.findCity(cityCode);
            // slSeller.setCityCode(cityCode);
            // String lgcsAreaCode = this.sl241103001Logic.findLgcsAreaCode(mdCity.getLgcsAreaId());
            // slSeller.setLgcsAreaCode(lgcsAreaCode);
            CityBean city = findCity(cityCode);
            slSeller.setCityCode(cityCode);
            slSeller.setCityName(city.getCityName());
            slSeller.setLgcsAreaCode(city.getLgcsAreaCode());
            slSeller.setLgcsAreaName(city.getLgcsAreaName());
            // Modified end.

            if (null == districtCode || "".equals(districtCode)) {
                throw new BusinessException("请选择区（含县级市、县、区）");
            }

            // Modified by xia_xiaojie on 2016/6/17. Modified start.
            // slSeller.setDistrictCode(districtCode);
            // slSeller.setSqaStatus(2);
            DistrictBean district = findDistrict(cityCode, districtCode);
            slSeller.setDistrictCode(districtCode);
            slSeller.setDistrictName(district.getDistrictName());
            slSeller.setSqaStatus(NumberConst.IntDef.INT_TWO);
            // Modified end.

            /**
             * 获取卖家code 先查询该区域是否已经存在卖家注册过，如果存在，取卖家分类码，卖家地区码+三位番号
             */
            String code = this.findAccount(slSeller.getSlMainClass().toString(), cityCode,
                    slSeller.getSlConFlg());
            /* String slCodes=StringUtil.toSafeString(commonLogic.maxId("sl_seller", "SL_CODE")); */
            slSeller.setSlCodeDis(code);

            // 生成卖家生产商编码
            // 根据区划和国际查询生产商编码
            if (slSeller.getSlMainClass() == 0 || slSeller.getSlMainClass() == 1) {
                slSeller.setSlCodeManufacture(
                        this.findSlCodeManufacture(cityCode, slSeller.getSlConFlg()));
            }
            // seller_4.获取ep_id
            Long epid = commonLogic.maxId("SL_ENTERPRISE", "EP_ID");
            slSeller.setEpId(epid);
            //request.getSession().setAttribute("jsp_epId", epid);
            jsonMaps.put("jsp_epId",epid);
            // 二级经营类型
            slSeller.setSelfFlg("0");
            slSeller.setAgentFlg("0");
            slSeller.setOemFlg("0");
            /**
             * 遍历传过来的复选框内容，选择了1，为自产型。。。
             */
            BaseParam base = new BaseParam();
            base.setFilter("cityCode", cityCode);
            base.setFilter("slConFlg", slSeller.getSlConFlg());

            Boolean flag = false;
            //根据卖家主类型确定二级分类必属于哪种类型
            String  slMainClass = StringUtil.toSafeString(slSeller.getSlMainClass());
            if ((SellerConstant.SlMainClass.PRODUCT+"").equals(slMainClass) || (SellerConstant.SlMainClass.SUPPLY+"").equals(slMainClass) ) {
                slSeller.setSelfFlg("1");
                base.setFilter("selfFlg", "1");
                slSeller.setSlCodeSelf(findSlCodeOther(base));
                base.getFilterMap().remove("selfFlg");
                flag = true;
            }
            if ((SellerConstant.SlMainClass.AGENT+"").equals(slMainClass)) {
                slSeller.setAgentFlg("1");
                base.setFilter("agentFlg", "1");
                slSeller.setSlCodeAgent(findSlCodeOther(base));
                base.getFilterMap().remove("agentFlg");
                flag = true;
            }
            if ((SellerConstant.SlMainClass.OEM+"").equals(slMainClass)) {
                slSeller.setOemFlg("1");
                base.setFilter("oemFlg", "1");
                slSeller.setSlCodeOem(findSlCodeOther(base));
                base.getFilterMap().remove("oemFlg");
                flag = true;
            }
            if (null != slSecondaryClass) {
                for (int i = 0; i < slSecondaryClass.length; i++) {
                    if ((SellerConstant.SlMainClass.PRODUCT+"").equals(slSecondaryClass[i])) {
                        slSeller.setSelfFlg("1");
                        base.setFilter("selfFlg", "1");
                        slSeller.setSlCodeSelf(findSlCodeOther(base));
                        base.getFilterMap().remove("selfFlg");
                        flag = true;
                    }
                    if ((SellerConstant.SlMainClass.AGENT+"").equals(slSecondaryClass[i])) {
                        slSeller.setAgentFlg("1");
                        base.setFilter("agentFlg", "1");
                        slSeller.setSlCodeAgent(findSlCodeOther(base));
                        base.getFilterMap().remove("agentFlg");
                        flag = true;
                    }
                    if ((SellerConstant.SlMainClass.OEM+"").equals(slSecondaryClass[i])) {
                        slSeller.setOemFlg("1");
                        base.setFilter("oemFlg", "1");
                        slSeller.setSlCodeOem(findSlCodeOther(base));
                        base.getFilterMap().remove("oemFlg");
                        flag = true;
                    }
                }
            }
            if(!flag){
                throw new BusinessException("二级经营类型不能为空!");
            }
            //slSeller.setCrtId(super.getLoginUser().getCrtId());
            slSeller.setSlConFlg(slConFlg);
            slSeller.setDelFlg("0");

            // Modified by xia_xiaojie on 2016/6/22. Modified start.
            slSeller.setVer(NumberConst.IntDef.INT_ONE);
            // Modified end.

            // Modified by xia_xiaojie on 2016/6/16. Modified start.
            /*
             * MdArea mdArea=sl241103001Logic.areaSelect(provinceCode);
             * if(null!=mdArea){
             * slSeller.setAreaCode(mdArea.getAreaCode());
             * }
             * if(null!=city){
             * MdLogisticsArea
             * mdLogisticsArea=sl241103001Logic.logisticsAreaSelect(StringUtil.toSafeString(city.getLgcsAreaId()));
             * if(null!=mdLogisticsArea){
             * slSeller.setLgcsAreaCode(mdLogisticsArea.getLgcsAreaCode());
             * }
             * }
             */
            // Modified end.

            // 插入 sl_account表
            slAccount.setCrtId(loginId);
            saveAccount(slAccount);

            // 现在将sl_account,ep_id,areacode,slcode和页面传入的主类型main_class一共5个字段值插入数据库
            slSeller.setCrtId(loginId);
            int num = saveSeller(slSeller);
            if (num > 0) {
                SlSeller slSeller1 = findSlSeller(accountWeb);
                if (null != slSeller1) {
                    //request.getSession().setAttribute("jsp_slCode", slSeller1.getSlCode());
                    jsonMaps.put("jsp_slCode",slSeller1.getSlCode());
                }
            }
            // ---------------------------------------------------------------------
            // enterprise_1.将页面传入的数据插入数据库sl_enterprise表里
            // 表的主键ep_id为自增长，现在废弃，使用上述epid代替进行设置
            slEnterprise.setEpId(epid);
            // 下面品牌选择时这里要改。。。WTD
            slEnterprise.setLicType(licType);
            //slEnterprise.setCrtId(super.getLoginUser().getCrtId());
            slEnterprise.setLicTermUnliimited(licTermUnliimited);
            slEnterprise.setDelFlg("0");

            // Modified by xia_xiaojie on 2016/6/22. Modified start.
            slEnterprise.setVer(NumberConst.IntDef.INT_ONE);
            // Modified end.
            slEnterprise.setCrtId(loginId);
            saveEp(slEnterprise);

            if (null != accountfile && 0 != accountfile.getSize()) {
                // 卖家头像上传
                String uploadFilePath = BusinessConst.SLPath.SERVICEIMAGEPATH + "/" + BusinessConst.SLPath.SLIMAGEPATH
                        + "/" + BusinessConst.SLPath.BASE + "/";
                String uploadFileName = slAccount.getSlAccount();
                SLUploadFile slUploadFile = new SLUploadFile();
                slUploadFile.saveUploadFile(accountfile, uploadFilePath, uploadFileName);
            }
            if (null != licfile && 0 != licfile.getSize()) {
                // 图片上传到ftp里面
                String uploadFilePath = BusinessConst.SLPath.SERVICEIMAGEPATH + "/" + BusinessConst.SLPath.SLIMAGEPATH
                        + "/" + epid + "/";
                String uploadFileName = BusinessConst.SLPath.EPBUS;
                SLUploadFile slUploadFile = new SLUploadFile();
                slUploadFile.saveUploadFile(licfile, uploadFilePath, uploadFileName);
            }
            if (null != epthrfile && 0 != epthrfile.getSize()) {
                String uploadFilePath = BusinessConst.SLPath.SERVICEIMAGEPATH + "/" + BusinessConst.SLPath.SLIMAGEPATH
                        + "/" + epid + "/";
                String uploadFileName = BusinessConst.SLPath.EPTHR;
                SLUploadFile slUploadFile = new SLUploadFile();
                slUploadFile.saveUploadFile(epthrfile, uploadFilePath, uploadFileName);
            }
            if (null != taxfile && 0 != taxfile.getSize()) {
                String uploadFilePath = BusinessConst.SLPath.SERVICEIMAGEPATH + "/" + BusinessConst.SLPath.SLIMAGEPATH
                        + "/" + epid + "/";
                String uploadFileName = BusinessConst.SLPath.EPTAX;
                SLUploadFile slUploadFile = new SLUploadFile();
                slUploadFile.saveUploadFile(taxfile, uploadFilePath, uploadFileName);
            }
            if (null != orgfile && 0 != orgfile.getSize()) {
                String uploadFilePath = BusinessConst.SLPath.SERVICEIMAGEPATH + "/" + BusinessConst.SLPath.SLIMAGEPATH
                        + "/" + epid + "/";
                String uploadFileName = BusinessConst.SLPath.EPORG;
                SLUploadFile slUploadFile = new SLUploadFile();
                slUploadFile.saveUploadFile(orgfile, uploadFilePath, uploadFileName);
            }
            if (null != balfile && 0 != balfile.getSize()) {
                String uploadFilePath = BusinessConst.SLPath.SERVICEIMAGEPATH + "/" + BusinessConst.SLPath.SLIMAGEPATH
                        + "/" + epid + "/";
                String uploadFileName = BusinessConst.SLPath.EPBAN;
                SLUploadFile slUploadFile = new SLUploadFile();
                slUploadFile.saveUploadFile(balfile, uploadFilePath, uploadFileName);
            }

            jsonMaps.put("jsp_msg","保存成功");
            JSONObject jsonObject = new JSONObject(jsonMaps);
            String message = jsonObject.toJSONString();

            return message;
        }


    }
}
