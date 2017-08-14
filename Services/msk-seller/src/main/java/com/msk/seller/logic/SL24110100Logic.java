package com.msk.seller.logic;


import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.common.business.constant.SellerConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.core.entity.*;
import com.msk.core.entity.SlEpHonor;
import com.msk.seller.bean.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fjm on 2016/1/28.
 */
@Service
public class SL24110100Logic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao){
        super.setBaseDao(baseDao);
    }

    @Autowired
    private SL241103Logic sl241103Logic;


    interface SqlId{
        String SQL_ID_SELECT_SELLER = "findSlByAc";
        String SQL_ID_SELECT_EP = "findEpById";
        String SQL_ID_SELECTBRANDLIST = "selectBrandList";
        String SQL_ID_SELECTHONORLIST = "selectHonorList";
        String SQL_ID_SELECTPP = "selectPp";
        String SQL_ID_SELECTMA = "selectMa";
        String SQL_ID_SELECTETEAM = "selectTeam";
        static final String SQL_ID_SELECTEPHONOR="findEpHonor";
        static  final  String SQL_ID_SELECTSLEPCAPONE="findSlEpCapOne";
        static  final  String SQL_ID_SELECTWORKSHOP="findSlEpWorkshop";
        static  final  String SQL_ID_FIND_SL_EP_MANAGER = "findSLEpManager";
        static final String SQL_ID_FIND_PD_BRAND="findPdBrand";
        static final String SQL_ID_FIND_SL_ENTERPRISE="findSlEnterprise";
    }

    /**
     * 根据卖家品牌企业ID，查询企业名称
     * @param brandEpId 品牌企业id
     * @return 企业名称
     */
    @Transactional(readOnly = true)
    public String findSlEnterprise(Long brandEpId) {
        BaseParam base = new BaseParam();
        base.setFilter("brandEpId",brandEpId.toString());
        base.setFilter("delFlg","0");
        SlEnterprise sl=super.findOne(SqlId.SQL_ID_FIND_SL_ENTERPRISE,base);
        if(null!=sl){
            return sl.getEpName();
        }else{
            return null;
        }
    }

    /**
     * 查询卖家代理的所有品牌信息
     * @param slCode，卖家编码
     * @return 返回集合
     */
    @Transactional(readOnly = true)
    public List<SL2411030073Bean> findPdBrand(String slCode) {
        BaseParam base = new BaseParam();
        base.setFilter("slCode",slCode);
        base.setFilter("delFlg","0");
        List<SL2411030073Bean> list = super.findList(SqlId.SQL_ID_FIND_PD_BRAND,base);
        return list;
    }

    @Transactional(readOnly = true)
    public List<SL241103070Bean> findEcTeam(String eslCode){
        List<SL241103070Bean> list = new ArrayList<SL241103070Bean>();
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("eslCode",eslCode);
        map.put("delFlg","0");
        list= super.findList(map,SqlId.SQL_ID_SELECTETEAM);
        if(CollectionUtils.isNotEmpty(list)){
            // 从redis  获取 是否负责人
            Map<String, String> leaderFlgMap = CodeMasterManager.findCodeMasterMap(SellerConstant.LeaderFlg.TYPE);
            for(SL241103070Bean bean : list){
                String  eleaderFlgMe =  leaderFlgMap.get(SellerConstant.LeaderFlg.TEAM_MEMBER+"");
                // 处理 eleaderFlg
                if(!StringUtil.isNullOrEmpty(bean.getEleaderFlg())){
                    String value = leaderFlgMap.get(bean.getEleaderFlg());
                    if(null != value){
                        eleaderFlgMe = value;
                    }
                }
                bean.setEleaderFlg(eleaderFlgMe);
            }
        }
        return list;
    }
    @Transactional(readOnly = true)
    public  List<SL24110306Bean> findMa(String epId){
        List<SL24110306Bean> list = new ArrayList<SL24110306Bean>();
        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("epId",epId);
        map.put("delFlg","0");
        list = super.findList(map,SqlId.SQL_ID_SELECTMA);
        return list;
    }
    /**
     * author cx
     *
     * 查询企业管理团队接口方法
     * @param param
     * @return list
     */
    @Transactional(readOnly = true)
    public  ISL231141RsResult findSLEpManager(RsRequest<ISL231141RsParam> param){
        List<SlEpManager> list = new ArrayList<SlEpManager>();
        BaseParam params = new BaseParam();
        params.setFilter("epId", StringUtil.toSafeString(param.getParam().getEpId()));
        params.setFilter("memberId",StringUtil.toSafeString(param.getParam().getMemberId()));
        params.setFilter("delFlg","0");
        list = super.findList(SqlId.SQL_ID_FIND_SL_EP_MANAGER,params);
        ISL231141RsResult iSL231141RsResult = new ISL231141RsResult();
        iSL231141RsResult.setSlEpManagerList(list);
        return iSL231141RsResult;
    }

    @Transactional(readOnly = true)
    public  List<SL2411030033Bean> findpp(String epId){
        List<SL2411030033Bean> list = new ArrayList<>();
        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("epId",epId);
        map.put("delFlg","0");
        list = super.findList(map,SqlId.SQL_ID_SELECTPP);
        return list;
    }

    @Transactional(readOnly = true)
    public List<SL241103081Bean> findBrand(String epId){
        List<SL241103081Bean> brandList = new ArrayList<SL241103081Bean>();
        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("epId",epId);
        map.put("delFlg","0");
        brandList = super.findList(map,SqlId.SQL_ID_SELECTBRANDLIST);
        return brandList;
    }
    @Transactional(readOnly = true)
    public List<SL241103082Bean> findHonor(String epId){
        List<SL241103082Bean> honorList = new ArrayList<SL241103082Bean>();
        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("epId",epId);
        map.put("delFlg","0");
        honorList = super.findList(map,SqlId.SQL_ID_SELECTHONORLIST);
        return honorList;
    }
    @Transactional(readOnly = true)
    public ISL231181RegionParam findSlByAc(String slAccount) {
        BaseParam param = new BaseParam();
        param.setFilter("slAccount", slAccount);
        param.setFilter("delFlg","0");
        ISL231181RegionParam slSeller = super.findOne(SqlId.SQL_ID_SELECT_SELLER, param);
        return slSeller;
    }

    @Transactional(readOnly = true)
    public SlEnterprise findEpById(String epId) {
        BaseParam param = new BaseParam();
        param.setFilter("epId", epId);
        param.setFilter("delFlg", "0");
        SlEnterprise slEnterprise = super.findOne(SqlId.SQL_ID_SELECT_EP, param);
        return slEnterprise;
        //查询企业专业资质信息
    }
    @Transactional(readOnly = true)
   public List<SL24110302Bean> findSL24110302BeanList(String ep) {
        List<SL24110302Bean> listEpCert = new ArrayList<SL24110302Bean>();
        BaseParam param1 = new BaseParam();
        param1.setFilter("epId", ep);
        param1.setFilter("delFlg","0");
        listEpCert = sl241103Logic.findSL24110302Bean(param1);
        if (listEpCert.size() > 0) {
            List<SL24110302_1Bean> listSlEpCert = new ArrayList<SL24110302_1Bean>();
            for (int i = 0; i < listEpCert.size(); i++) {
                String certId = String.valueOf(listEpCert.get(i).getCertId());
                BaseParam param2 = new BaseParam();
                param2.setFilter("certId", certId);
                param2.setFilter("epId", ep);
                param2.setFilter("delFlg","0");
                listSlEpCert = sl241103Logic.findSL24110302_1Bean(param2);
                listEpCert.get(i).setBeanList(listSlEpCert);
            }
        }
        return listEpCert;
    }


    /*@Transactional(readOnly = true)
    public SL241103003Bean findSl241103003Bean(String epId) {
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("slEpId",epId);
        baseParam.setFilter("delFlg","0");
        List<SlEpCertItem> slEpCertItemList=super.findList(baseParam);
        SL241103003Bean sl241103003Bean=new SL241103003Bean();
        List<SlEpCert> slEpCerts = super.findList(SqlId.SQL_ID_SELECTCERTlIST, baseParam);
        if(null==slEpCertItemList || null==slEpCerts){
            return null;
        }
        for(int i =0;i<slEpCerts.size();i++){
            for(int j=0;j<slEpCertItemList.size();j++) {
                if("动物防疫条件合格证".equals(slEpCerts.get(i).getCertName())){
                    if(slEpCertItemList.get(j).getCertId().equals(slEpCerts.get(i).getCertId()) && "代码编号".equals(slEpCertItemList.get(j).getCertItemName())){
                        sl241103003Bean.setAnimalCode(StringUtil.toSafeString(slEpCertItemList.get(j).getCertItemValue()));
                    }
                    if(slEpCertItemList.get(j).getCertId().equals(slEpCerts.get(i).getCertId()) && "年检日期".equals(slEpCertItemList.get(j).getCertItemName())){
                        sl241103003Bean.setAnimalDate(StringUtil.toSafeString(slEpCertItemList.get(j).getCertItemValue()));
                    }
                }
                if("生猪定点屠宰许可证".equals(slEpCerts.get(i).getCertName())){
                    if(slEpCertItemList.get(j).getCertId().equals(slEpCerts.get(i).getCertId()) && "定点屠宰代码".equals(slEpCertItemList.get(j).getCertItemName())){
                        sl241103003Bean.setSlaughterCode(StringUtil.toSafeString(slEpCertItemList.get(j).getCertItemValue()));
                    }
                    if(slEpCertItemList.get(j).getCertId().equals(slEpCerts.get(i).getCertId()) && "批准号".equals(slEpCertItemList.get(j).getCertItemName())){
                        sl241103003Bean.setSlaughterApproval(StringUtil.toSafeString(slEpCertItemList.get(j).getCertItemValue()));
                    }
                }
                if("食品生产许可证（副本） （全国工业产品生产许可证）".equals(slEpCerts.get(i).getCertName())){
                    if(slEpCertItemList.get(j).getCertId().equals(slEpCerts.get(i).getCertId()) && "证书编号".equals(slEpCertItemList.get(j).getCertItemName())){
                        sl241103003Bean.setFoodCode(StringUtil.toSafeString(slEpCertItemList.get(j).getCertItemValue()));
                    }
                    if(slEpCertItemList.get(j).getCertId().equals(slEpCerts.get(i).getCertId()) && "有效期".equals(slEpCertItemList.get(j).getCertItemName())){
                        sl241103003Bean.setFoodDate(StringUtil.toSafeString(slEpCertItemList.get(j).getCertItemValue()));
                    }
                }
                if("进沪证".equals(slEpCerts.get(i).getCertName())){
                    if(slEpCertItemList.get(j).getCertId().equals(slEpCerts.get(i).getCertId()) && "单位名称".equals(slEpCertItemList.get(j).getCertItemName())){
                        sl241103003Bean.setEnterCompanyName(StringUtil.toSafeString(slEpCertItemList.get(j).getCertItemValue()));
                    }
                    if(slEpCertItemList.get(j).getCertId().equals(slEpCerts.get(i).getCertId()) && "品种".equals(slEpCertItemList.get(j).getCertItemName())){
                        sl241103003Bean.setEnterCompanyType(StringUtil.toSafeString(slEpCertItemList.get(j).getCertItemValue()));
                    }
                    if(slEpCertItemList.get(j).getCertId().equals(slEpCerts.get(i).getCertId()) && "登记日期".equals(slEpCertItemList.get(j).getCertItemName())){
                        sl241103003Bean.setEnterCompanyDate(StringUtil.toSafeString(slEpCertItemList.get(j).getCertItemValue()));
                    }
                }
                if("ISO9001质量管理体系认证证书".equals(slEpCerts.get(i).getCertName())){
                    if(slEpCertItemList.get(j).getCertId().equals(slEpCerts.get(i).getCertId()) && "证书编号".equals(slEpCertItemList.get(j).getCertItemName())){
                        sl241103003Bean.setQualityCode(StringUtil.toSafeString(slEpCertItemList.get(j).getCertItemValue()));
                    }
                    if(slEpCertItemList.get(j).getCertId().equals(slEpCerts.get(i).getCertId()) && "认证标准".equals(slEpCertItemList.get(j).getCertItemName())){
                        sl241103003Bean.setQualityStandard(StringUtil.toSafeString(slEpCertItemList.get(j).getCertItemValue()));
                    }
                    if(slEpCertItemList.get(j).getCertId().equals(slEpCerts.get(i).getCertId()) && "认证范围".equals(slEpCertItemList.get(j).getCertItemName())){
                        sl241103003Bean.setQualityRange(StringUtil.toSafeString(slEpCertItemList.get(j).getCertItemValue()));
                    }
                    if(slEpCertItemList.get(j).getCertId().equals(slEpCerts.get(i).getCertId()) && "发证日期及有效期".equals(slEpCertItemList.get(j).getCertItemName())){
                        sl241103003Bean.setQualityCretDateAndValidityDate(StringUtil.toSafeString(slEpCertItemList.get(j).getCertItemValue()));
                    }
                    if(slEpCertItemList.get(j).getCertId().equals(slEpCerts.get(i).getCertId()) && "认证机构".equals(slEpCertItemList.get(j).getCertItemName())){
                        sl241103003Bean.setQualityAuthentication(StringUtil.toSafeString(slEpCertItemList.get(j).getCertItemValue()));
                    }
                }
                if("ISO22000食品安全管理体系认证证书".equals(slEpCerts.get(i).getCertName())){

                    if(slEpCertItemList.get(j).getCertId().equals(slEpCerts.get(i).getCertId()) && "证书编号".equals(slEpCertItemList.get(j).getCertItemName())){
                        sl241103003Bean.setSafeCode(StringUtil.toSafeString(slEpCertItemList.get(j).getCertItemValue()));
                    }
                    if(slEpCertItemList.get(j).getCertId().equals(slEpCerts.get(i).getCertId()) && "认证标准".equals(slEpCertItemList.get(j).getCertItemName())){
                        sl241103003Bean.setSafeStandard(StringUtil.toSafeString(slEpCertItemList.get(j).getCertItemValue()));
                    }
                    if(slEpCertItemList.get(j).getCertId().equals(slEpCerts.get(i).getCertId()) && "认证范围".equals(slEpCertItemList.get(j).getCertItemName())){
                        sl241103003Bean.setSafeRange(StringUtil.toSafeString(slEpCertItemList.get(j).getCertItemValue()));
                    }
                    if(slEpCertItemList.get(j).getCertId().equals(slEpCerts.get(i).getCertId()) && "发证日期及有效期".equals(slEpCertItemList.get(j).getCertItemName())){
                        sl241103003Bean.setSafeCretDateAndValidityDate(StringUtil.toSafeString(slEpCertItemList.get(j).getCertItemValue()));
                    }
                    if(slEpCertItemList.get(j).getCertId().equals(slEpCerts.get(i).getCertId()) && "认证机构".equals(slEpCertItemList.get(j).getCertItemName())){
                        sl241103003Bean.setSafeAuthentication(StringUtil.toSafeString(slEpCertItemList.get(j).getCertItemValue()));
                    }
                }
                if("ISO14001环境管理体系认证证书".equals(slEpCerts.get(i).getCertName())){
                    if(slEpCertItemList.get(j).getCertId().equals(slEpCerts.get(i).getCertId()) && "注册号".equals(slEpCertItemList.get(j).getCertItemName())){
                        sl241103003Bean.setEnvironmentalCode(StringUtil.toSafeString(slEpCertItemList.get(j).getCertItemValue()));
                    }
                    if(slEpCertItemList.get(j).getCertId().equals(slEpCerts.get(i).getCertId()) && "认证标准".equals(slEpCertItemList.get(j).getCertItemName())){
                        sl241103003Bean.setEnvironmentalStandard(StringUtil.toSafeString(slEpCertItemList.get(j).getCertItemValue()));
                    }
                    if(slEpCertItemList.get(j).getCertId().equals(slEpCerts.get(i).getCertId()) && "认证范围".equals(slEpCertItemList.get(j).getCertItemName())){
                        sl241103003Bean.setEnvironmentalRange(StringUtil.toSafeString(slEpCertItemList.get(j).getCertItemValue()));
                    }
                    if(slEpCertItemList.get(j).getCertId().equals(slEpCerts.get(i).getCertId()) && "发证日期及有效期".equals(slEpCertItemList.get(j).getCertItemName())){
                        sl241103003Bean.setEnvironmentalCretDateAndValidityDate(StringUtil.toSafeString(slEpCertItemList.get(j).getCertItemValue()));
                    }
                    if(slEpCertItemList.get(j).getCertId().equals(slEpCerts.get(i).getCertId()) && "认证机构".equals(slEpCertItemList.get(j).getCertItemName())){
                        sl241103003Bean.setEnvironmentalAuthentication(StringUtil.toSafeString(slEpCertItemList.get(j).getCertItemValue()));
                    }
                }
                if("清真食品生产经营许可证".equals(slEpCerts.get(i).getCertName())){
                    if(slEpCertItemList.get(j).getCertId().equals(slEpCerts.get(i).getCertId()) && "证书编号".equals(slEpCertItemList.get(j).getCertItemName())){
                        sl241103003Bean.setMuslimCode(StringUtil.toSafeString(slEpCertItemList.get(j).getCertItemValue()));
                    }
                    if(slEpCertItemList.get(j).getCertId().equals(slEpCerts.get(i).getCertId()) && "发证日期及有效期".equals(slEpCertItemList.get(j).getCertItemName())){
                        sl241103003Bean.setMuslimCretDateAndValidityDate(StringUtil.toSafeString(slEpCertItemList.get(j).getCertItemValue()));
                    }
                    if(slEpCertItemList.get(j).getCertId().equals(slEpCerts.get(i).getCertId()) && "签发机构".equals(slEpCertItemList.get(j).getCertItemName())){
                        sl241103003Bean.setMuslimAuthentication(StringUtil.toSafeString(slEpCertItemList.get(j).getCertItemValue()));
                    }
                }
            }
        }
        return sl241103003Bean;
    }

    @Transactional(readOnly = true)
    public SL24110306Bean findManagerById(String epId){
        BaseParam param = new BaseParam();
        param.setFilter("epId", epId);
        param.setFilter("delFlg","0");
        SL24110306Bean sL24110306Bean = super.findOne(SqlId.SQL_ID_SELECT_MANAGER,param);
        return sL24110306Bean;
    }
    @Transactional(readOnly = true)
    public SL241103070Bean findEcTeamBySCode(String eslCode){
        BaseParam param = new BaseParam();
        param.setFilter("eslCode",eslCode);
        param.setFilter("delFlg","0");
        SL241103070Bean sl241103070Bean = super.findOne(SqlId.SQL_ID_SELECT_ECTEAM,param);
        return sl241103070Bean;
    }

    @Transactional(readOnly = true)
    public List<SL2411030073Bean> findeBrandType(String slCode){
        List<SL2411030073Bean> list = new ArrayList<SL2411030073Bean>();
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("slCode",slCode);
        map.put("delFlg","0");
        list= super.findList(map,SqlId.SQL_ID_SELECTYPE);
        return list;
    }*/

    //查询企业荣誉
    @Transactional
    public List<SlEpHonor> findSlEpHonor(String epId) {
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("epId",epId);
        baseParam.setFilter("delFlg","0");
        List<SlEpHonor> list = super.findList(SqlId.SQL_ID_SELECTEPHONOR,baseParam);
        return list;
    }

    //查询企业厂房信息
    @Transactional(readOnly = true)
    public SlEpCap findSlEpCap(String epId) {
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("epId",epId);
        baseParam.setFilter("delFlg","0");
        SlEpCap slEpCap = super.findOne(SqlId.SQL_ID_SELECTSLEPCAPONE,baseParam);
        return slEpCap;
    }

    //查询企业车间信息
    @Transactional(readOnly = true)
    public List<SlEpWorkshop> findSlEpWorkshop(String epId) {
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("epId",epId);
        baseParam.setFilter("delFlg","0");
        return super.findList(SqlId.SQL_ID_SELECTWORKSHOP,baseParam);
    }

}
