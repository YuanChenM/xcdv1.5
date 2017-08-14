package com.msk.seller.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.service.ExcelAsyncPrintFasterService;
import com.msk.common.service.ExcelAsyncPrintService;
import com.msk.core.entity.*;
import com.msk.core.entity.SlAccount;
import com.msk.core.entity.SlEpHonor;
import com.msk.district.bean.CityBean;
import com.msk.district.bean.DistrictBean;
import com.msk.district.bean.ProvinceBean;
import com.msk.seller.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.text.DateFormat;
import java.util.*;

/**
 * Created by guan_zhongheng on 2016/10/8.
 */
@Component("sl24111501Logic")
public class SL24111501Logic extends ExcelAsyncPrintFasterService {
    @Autowired
    private SL241103001Logic SL241103001Logic;

    @Autowired
    private SL24110100Logic sL24110100Logic;

    @Autowired
    private SL241103000Logic sl241103000Logic;

    @Autowired
    private SL241103010Logic sl241103010Logic;

    @Autowired
    private SL241103Logic sl241103Logic;

    @Autowired
    private Sl241116Logic sl241116Logic;

    @Override
    public List<Map<String, ?>> getDataSourceFaster(Object param) {
        Map<String, Object> paramMap = new HashMap<>();
        BaseParam base = new BaseParam();
        base.setFilterMap((Map<String, Object>) param);
        String slAccount = (String) base.getFilterMap().get("slAccount");
        String slSellerCode = (String) base.getFilterMap().get("slCode");
        String slChoosed = (String) base.getFilterMap().get("slChoosed"); // 用于判定下载的组合
        SlSeller slSeller = this.sL24110100Logic.findSlByAc(slAccount);
        List<ProvinceBean> mdProvinces = sl241103000Logic.getProvinces(null);
        String slCode = "";
        String ep = "";
        if (null != slSeller) {
            slCode = slSeller.getSlCode();
            ep = StringUtil.toSafeString(slSeller.getEpId());
            if (slSeller.getSlMainClass() == NumberConst.IntDef.INT_ZERO) {
                slSeller.setMemo8("生产商");
            } else if (slSeller.getSlMainClass() == NumberConst.IntDef.INT_ONE) {
                slSeller.setMemo8("自产型");
            } else if (slSeller.getSlMainClass() == NumberConst.IntDef.INT_TWO) {
                slSeller.setMemo8("代理型");
            } else if (slSeller.getSlMainClass() == NumberConst.IntDef.INT_THREE) {
                slSeller.setMemo8("OEM型");
            }
            String secMem = "";
            if ("1".equals(slSeller.getSelfFlg())) {
                secMem += "自产型,";
            }
            if ("1".equals(slSeller.getAgentFlg())) {
                secMem += "代理型,";
            }
            if ("1".equals(slSeller.getOemFlg())) {
                secMem += "OEM型,";
            }
            if(secMem.length() > 1){
                slSeller.setMemo9(secMem.substring(0,secMem.length()-1));
            }else{
                slSeller.setMemo9("");
            }
            if ("1".equals(slSeller.getSlConFlg())) {
                slSeller.setSlConFlg("国产");
            } else if ("1".equals(slSeller.getSlConFlg())) {
                slSeller.setSlConFlg("进口");
            }
        }
        SlEnterprise slEnterprise = this.sL24110100Logic.findEpById(ep);
        SlAccount SlAccountsql = this.SL241103001Logic.slAccountEntity2(slAccount);
        List<SL24110302Bean> slEpCertList = new ArrayList<SL24110302Bean>();
        if (slChoosed.contains("13_")) {
            // 查询企业资质信息
            slEpCertList = this.sL24110100Logic.findSL24110302BeanList(ep);
        }
        if(slEnterprise.getLicTermBegin() != null){

        }
        if("0".equals(slEnterprise.getLicType())){
            slEnterprise.setLicType("否");
        }else if("1".equals(slEnterprise.getLicType())){
            slEnterprise.setLicType("是");
        }
        if("0".equals(slEnterprise.getLicTermUnliimited())){
            slEnterprise.setLicTermUnliimited("否");
        }else if("1".equals(slEnterprise.getLicTermUnliimited())){
            slEnterprise.setLicTermUnliimited("是");
        }
        paramMap.put("slEpCertList", slEpCertList);

        // 查询企业荣誉信息
        List<SlEpHonor> slEpHonors = new ArrayList<SlEpHonor>();
        if (slChoosed.contains("14_")) {
            slEpHonors = this.sL24110100Logic.findSlEpHonor(ep);
        }
        paramMap.put("slEpHonors", slEpHonors);

        // 查询厂区信息 //查询库容信息 //查询实验室、品控等信息
        SlEpCap slEpCap = new SlEpCap();
        if (slChoosed.contains("15_") || slChoosed.contains("17_") || slChoosed.contains("18_")) {
            slEpCap = this.sL24110100Logic.findSlEpCap(ep);
        }
        paramMap.put("slEpCap", slEpCap);
        // 查询车间信息
        List<SlEpWorkshop> slEpWorkshops = new ArrayList<SlEpWorkshop>();
        if (slChoosed.contains("16_")) {
            slEpWorkshops = this.sL24110100Logic.findSlEpWorkshop(ep);
        }
        paramMap.put("slEpWorkshops", slEpWorkshops);

        // 拿SL_CODE查询出企业电商信息
        List<SL241103070Bean> ecTeam = new ArrayList<SL241103070Bean>();
        if (slChoosed.contains("21_")) {
            ecTeam = this.sL24110100Logic.findEcTeam(slCode);
        }
        paramMap.put("ecTeam", ecTeam);

        // 品牌信息
        List<SL2411030033Bean> list = new ArrayList<SL2411030033Bean>();
        if (slChoosed.contains("22_")) {
            list = this.sL24110100Logic.findpp(ep);
            for(SL2411030033Bean bean:list){
                if("0".equals(bean.getBrandClass())){
                    bean.setBrandClass("卖家独立品牌");
                }else if("1".equals(bean.getBrandClass())){
                    bean.setBrandClass("神农先生联合");
                }else if("2".equals(bean.getBrandClass())){
                    bean.setBrandClass("神农客联合");
                }else if("3".equals(bean.getBrandClass())){
                    bean.setBrandClass("神农人家联合");
                }
            }
        }
        paramMap.put("list", list);
//        paramMap.put("listBack", list);

        /** 代理品牌信息 */
        List<SL2411030073Bean> sl2411030073Beans = this.sL24110100Logic.findPdBrand(slCode);
        if (!CollectionUtils.isEmpty(sl2411030073Beans)) {
            for (SL2411030073Bean sl2411030073Bean : sl2411030073Beans) {
                if (null != this.sL24110100Logic.findSlEnterprise(sl2411030073Bean.getBrandEpId())) {
                    sl2411030073Bean.setEpName(this.sL24110100Logic.findSlEnterprise(sl2411030073Bean.getBrandEpId()));
                }
            }
        }
        // 团队信息
        List<SL24110306Bean> ma = new ArrayList<SL24110306Bean>();
        if (slChoosed.contains("20_")) {
            ma = this.sL24110100Logic.findMa(ep);
        }
        paramMap.put("slEpManager", ma);

        // 生产商信息
        BaseParam params = new BaseParam();
        params.setFilter("slCode", slCode);
        params.setFilter("delFlg", "0");
        List<SlEpAgentAuthBean> slEpAgentAuthAndSlEpOem = new ArrayList<>();
        if (slChoosed.contains("23_")) {
            if (null != slSeller && "1".equals(slSeller.getAgentFlg())) {
                List<SlEpAgentAuthBean> slEpAgentAuth = sl241103010Logic.queryAgentData(params);
                if (!CollectionUtils.isEmpty(slEpAgentAuth)) {
                    for (int i = 0; i < slEpAgentAuth.size(); i++) {
                        SlEpAgentAuthBean slEpAgent = slEpAgentAuth.get(i);
                        slEpAgent.setSlCode(slCode);
                        slEpAgent.setEpName(this.sL24110100Logic.findSlEnterprise(slEpAgent.getProducerEpId()));
                        slEpAgent.setMarkFlg(NumberConst.IntDef.INT_ONE);
                        slEpAgentAuthAndSlEpOem.add(slEpAgent);
                    }
                }
            }
            if (null != slSeller && "1".equals(slSeller.getOemFlg())) {
                List<SlEpAgentAuthBean> slEpOemAuth = sl241103010Logic.queryOemData(params);
                if (!CollectionUtils.isEmpty(slEpOemAuth)) {
                    for (int i = 0; i < slEpOemAuth.size(); i++) {
                        SlEpAgentAuthBean slEpOem = slEpOemAuth.get(i);
                        slEpOem.setSlCode(slCode);
                        slEpOem.setEpName(this.sL24110100Logic.findSlEnterprise(slEpOem.getProducerEpId()));
                        slEpOem.setMarkFlg(NumberConst.IntDef.INT_TWO);
                        slEpAgentAuthAndSlEpOem.add(slEpOem);
                    }
                }
            }
        }
        paramMap.put("slEpAgentAuthAndSlEpOem", slEpAgentAuthAndSlEpOem);
        // 地区回显
        List<CityBean> cityList = null;
        List<DistrictBean> mdDistrictList = null;

        if (null != slSeller) {
            String provinceCode = null;
            for (ProvinceBean mdProvince : mdProvinces) {
                if (mdProvince.getProvinceCode().equals(slSeller.getProvinceCode())) {
                    provinceCode = mdProvince.getProvinceCode();
                    slSeller.setProvinceName(mdProvince.getProvinceName());
                    break;
                }
            }
            cityList = sl241103000Logic.findCityByProvinceCode(provinceCode);
            String cityCode = null;
            for (CityBean mdCity : cityList) {
                if (mdCity.getCityCode().equals(slSeller.getCityCode())) {
                    cityCode = mdCity.getCityCode();
                    slSeller.setCityName(mdCity.getCityName());
                    break;
                }
            }
            mdDistrictList = sl241103000Logic.findDistrictByCityCode(cityCode);
            for (DistrictBean mdDis : mdDistrictList) {
                if (mdDis.getDistrictCode().equals(slSeller.getDistrictCode())) {
                    slSeller.setDistrictName(mdDis.getDistrictName());
                    break;
                }
            }
        }
        // 检测设备数据查询
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("epId", ep);
        baseParam.setFilter("delFlg", "0");
        List<SlEpDdBean> slEpDdBeanList = new ArrayList<SlEpDdBean>();
        if (slChoosed.contains("19_")) {
            slEpDdBeanList = sl241103Logic.findEpEquipment(baseParam);
        }
        // 产品信息
        List<SL241116Bean> productList = new ArrayList<SL241116Bean>();
        if (slChoosed.contains("24_")) {
            BaseParam baseProd = new BaseParam();
            baseProd.getFilterMap().put("slCode",slSellerCode);
            productList = sl241116Logic.findList(baseProd);
        }
        paramMap.put("productList", productList);
        paramMap.put("productListBack", productList);
        paramMap.put("slEpDdBeanList", slEpDdBeanList);
        // 基础表
        paramMap.put("slSeller", slSeller);
        paramMap.put("slEnterprise", slEnterprise);
        paramMap.put("slAccount", SlAccountsql);
        paramMap.put("sheetName","卖家企业详细信息");
        List<Map<String, ?>> listParam = new ArrayList<>();
        listParam.add(paramMap);
        return listParam;
    }

}
