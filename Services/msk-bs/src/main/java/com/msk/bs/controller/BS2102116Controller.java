package com.msk.bs.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.bs.bean.*;
import com.msk.bs.logic.*;
import com.msk.bs.utils.CommRestUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.BsBasicInfo;
import com.msk.core.entity.SlHouseAccount;
import com.msk.core.entity.SlHouseIntroduce;
import com.msk.core.entity.SlHouseType;
import com.msk.district.bean.*;

/**
 * Created by wang_haichun on 2016/9/17.
 */
@Controller
@RequestMapping(value = "BS2102116")
public class BS2102116Controller extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(BS2102116Controller.class);

    @Autowired
    private BS2101107Logic bs2101107Logic;
    @Autowired
    private BS2102103Logic bs2102103Logic;
    @Autowired
    private IBS2101104RsLogic ibs2101104RsLogic;
    @Autowired
    private BSHouseLeverLogic bsHouseLeverLogic;
    @Autowired
    private BS2102115Logic bs2102115Logic;
    @Autowired
    private BS2102116Logic bs2102116Logic;
    @Autowired
    private BS2102117Logic bs2102117Logic;
    @Autowired
    private BSBasicInfoLogic bsBasicInfoLogic;
    @Autowired
    private BS2102112Logic bs2102112Logic;

    @RequestMapping(value = "init",method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> init(@RequestParam(value = "slCode",required = true) String slCode,
                                     @RequestParam(value = "houseCode",required = true) String houseCode) {

        Map<String,Object> map = new HashMap<>();
        //查询管家信息
        SlHouseAccount slHouseAccount = new SlHouseAccount();
        slHouseAccount.setSlCode(slCode);
        slHouseAccount.setHouseCode(houseCode);
        SlHouseAccount houseAccount = bs2101107Logic.findHouseAccount(slHouseAccount);

        //拼接管家编码
        BsBasicInfo bsBasicInfo = bsBasicInfoLogic.findBsBasicInfo(houseAccount.getSlCode());
        if(bsBasicInfo != null){
            if(!StringUtil.isNullOrEmpty(houseAccount.getHouseCodeDis())){
                houseAccount.setHouseCodeDis(StringUtil.isNullOrEmpty(bsBasicInfo.getSlCodeDis()) == true ? "" : bsBasicInfo.getSlCodeDis() + houseAccount.getHouseCodeDis());
            }
            if(!StringUtil.isNullOrEmpty(houseAccount.getFlag1())){
                if("1".equals(houseAccount.getFlag1())){
                    houseAccount.setFlag1("男");
                }else if ("2".equals(houseAccount.getFlag1())){
                    houseAccount.setFlag1("女");
                }else {
                    houseAccount.setFlag1("");
                }
            }

            if(!StringUtil.isNullOrEmpty(houseAccount.getVprovinceCode())){
                DistrictParam param = new DistrictParam();
                String vprovince = "";
                String vcity = "";
                String vdistrict = "";
                // 获取省份信息
                param.setProvinceCode(houseAccount.getVprovinceCode());
                List<ProvinceBean> provinceBeanList = CommRestUtil.getProvinceList(param);
                if(!CollectionUtils.isEmpty(provinceBeanList)){
                    vprovince = getPCDName(provinceBeanList,houseAccount.getVprovinceCode());
                }
                if(!StringUtil.isNullOrEmpty(houseAccount.getVcityCode())){
                    //获取城市信息
                    param.setCityCode(houseAccount.getVcityCode());
                    List<CityBean> cityBeanList =CommRestUtil.getProvinceCityList(param);
                    if(!CollectionUtils.isEmpty(cityBeanList)){
                        vcity = getPCDName(cityBeanList,houseAccount.getVcityCode());
                    }
                    if(!StringUtil.isNullOrEmpty(houseAccount.getVdistrictCode())){
                        //获取区县信息
                        param.setDistrictCode(houseAccount.getVdistrictCode());
                        List<DistrictBean> districtBeanList =CommRestUtil.getDistrictList(param);
                        if(!CollectionUtils.isEmpty(districtBeanList)){
                            vdistrict = getPCDName(districtBeanList,houseAccount.getVdistrictCode());
                        }
                    }
                }
                houseAccount.setVhouseAddress(StringUtil.toSafeString(vprovince) + StringUtil.toSafeString(vcity) + StringUtil.toSafeString(vdistrict) + StringUtil.toSafeString(houseAccount.getVhouseAddress()));
            }
            //户籍地址
            DistrictParam rParam = new DistrictParam();
            String rprovince = "";
            String rcity = "";
            String rdistrict = "";
            if(!StringUtil.isNullOrEmpty(houseAccount.getRprovinceCode())){
                // 获取省份信息
                rParam.setProvinceCode(houseAccount.getRprovinceCode());
                List<ProvinceBean> provinceBeanList = CommRestUtil.getProvinceList(rParam);
                if(!CollectionUtils.isEmpty(provinceBeanList)){
                    rprovince = getPCDName(provinceBeanList,houseAccount.getRprovinceCode());
                }
            }
            if(!StringUtil.isNullOrEmpty(houseAccount.getRcityCode())){
                //获取城市信息
                rParam.setCityCode(houseAccount.getRcityCode());
                List<CityBean> cityBeanList =CommRestUtil.getProvinceCityList(rParam);
                if(!CollectionUtils.isEmpty(cityBeanList)){
                    rcity = getPCDName(cityBeanList,houseAccount.getRcityCode());
                }
            }
            if(!StringUtil.isNullOrEmpty(houseAccount.getRdistrictCode())){
                //获取区县信息
                rParam.setDistrictCode(houseAccount.getRdistrictCode());
                List<DistrictBean> districtBeanList =CommRestUtil.getDistrictList(rParam);
                if(!CollectionUtils.isEmpty(districtBeanList)){
                    rdistrict = getPCDName(districtBeanList,houseAccount.getRdistrictCode());
                }
            }
            houseAccount.setRhouseAddress(StringUtil.toSafeString(rprovince) + StringUtil.toSafeString(rcity) + StringUtil.toSafeString(rdistrict) + StringUtil.toSafeString(houseAccount.getRhouseAddress()));


            //工作地址
            String province = "";
            String city = "";
            String district = "";
            DistrictParam param = new DistrictParam();
            if(!StringUtil.isNullOrEmpty(houseAccount.getProvinceCode())){
                // 获取省份信息
                param.setProvinceCode(houseAccount.getProvinceCode());
                List<ProvinceBean> provinceBeanList = CommRestUtil.getProvinceList(param);
                if(!CollectionUtils.isEmpty(provinceBeanList)){
                    province = getPCDName(provinceBeanList,houseAccount.getProvinceCode());
                }
            }
            if(!StringUtil.isNullOrEmpty(houseAccount.getCityCode())){
                //获取城市信息
                param.setCityCode(houseAccount.getCityCode());
                List<CityBean> cityBeanList =CommRestUtil.getProvinceCityList(param);
                if(!CollectionUtils.isEmpty(cityBeanList)){
                    city = getPCDName(cityBeanList,houseAccount.getCityCode());
                }
            }
            if(!StringUtil.isNullOrEmpty(houseAccount.getDistrictCode())){
                //获取区县信息
                param.setDistrictCode(houseAccount.getDistrictCode());
                List<DistrictBean> districtBeanList =CommRestUtil.getDistrictList(param);
                if(!CollectionUtils.isEmpty(districtBeanList)){
                    district = getPCDName(districtBeanList,houseAccount.getDistrictCode());
                }
            }
            houseAccount.setHouseAddress(StringUtil.toSafeString(province)  + StringUtil.toSafeString(city) + StringUtil.toSafeString(district) + StringUtil.toSafeString(houseAccount.getHouseAddress()));

            //选择的买手编码
            BS2102112Bean bs2102112Bean = bs2102112Logic.findHouseAccountBuyer(houseAccount);
            houseAccount.setFlag20(bs2102112Bean.getSlContact() + "  " +bs2102112Bean.getSlCodeDis());
        }



        //获取所以的管家分类
        //获取一级分类
        SlHouseType houseTypeOne = new SlHouseType();
        houseTypeOne.setTypeLever("0");
        List<SlHouseType> houseTypeList = bsHouseLeverLogic.findSlHouseType(houseTypeOne);
        //获取冻品管家二级分类
        LinkedHashMap<SlHouseType, List<SlHouseType>> houseTypeMap = new LinkedHashMap<>();
        if (!CollectionUtils.isEmpty(houseTypeList) && houseTypeList.size() > 0) {
            //获取二级分类
            for (SlHouseType slHouseType : houseTypeList) {
                SlHouseType houseTypeTwo = new SlHouseType();
                houseTypeTwo.setTypeLever("1");
                houseTypeTwo.setParentTypeCode(slHouseType.getTypeCode());
                List<SlHouseType> houseTypeListTwo = bsHouseLeverLogic.findSlHouseType(houseTypeTwo);
                houseTypeMap.put(slHouseType, houseTypeListTwo);
            }
        }

        //获取物流区
        DistrictParam districtParam = new DistrictParam();
        districtParam.setLgcsAreaCode(houseAccount.getVlgcsAreaCode());
        List<LgcsAreaBean> lgcsAreaBeanList = CommRestUtil.getLogisticsAreaList(districtParam);

        List<BS2102107Bean> houseManageList = new ArrayList<>();
        //选择的管家分类
        if (!CollectionUtils.isEmpty(lgcsAreaBeanList) && lgcsAreaBeanList.size() > 0) {
            BS2102103Param bs2102103Param = new BS2102103Param();
            bs2102103Param.setHouseCode(houseAccount.getHouseCode());
            bs2102103Param.setSlCode(houseAccount.getSlCode());
            bs2102103Param.setLgcsAreaName(lgcsAreaBeanList.get(0).getLgcsAreaName());
            bs2102103Param.setLgcsAreaCode(houseAccount.getVlgcsAreaCode());
            bs2102103Param.setPaging(false);
            PageResult<BS2102107Bean> pageResult = bs2102103Logic.findAllHouseManage(bs2102103Param);
            houseManageList = pageResult.getData();
        }

        List<BS2102107Bean> houseManageStr = new ArrayList<>();
        BS2102107Bean bs2102107 = null;
        for (Map.Entry entry : houseTypeMap.entrySet()) {
            SlHouseType slHouseType = (SlHouseType) entry.getKey();
            List<SlHouseType> houseTypes = (List<SlHouseType>) entry.getValue();
            StringBuffer reclassify = new StringBuffer();
            bs2102107 = new BS2102107Bean();
            for(BS2102107Bean bs2102107Bean : houseManageList){
                if(bs2102107Bean.getHouseCategoryCode().equals(slHouseType.getTypeCode())){
                    bs2102107.setHouseCategoryName(slHouseType.getTypeName() + "：");
                }
                for(SlHouseType houseType : houseTypes){
                    if(bs2102107Bean.getHouseReclassifyCode().equals(houseType.getTypeCode())){
                        reclassify.append(houseType.getTypeName()+"   ");
                    }
                }
            }
            bs2102107.setHouseReclassifyName(reclassify.toString());
            houseManageStr.add(bs2102107);
        }
        if(!CollectionUtils.isEmpty(houseManageStr) && houseManageStr.size() > 0){
           /* int len = houseManageStr.size() > 1 ? (houseManageStr.size() / 2 + 1) : 1;
            houseManageStr.get(len -1).setRemark("冻品管家分类申请");*/
            houseManageStr.get(0).setRemark("冻品管家分类申请");
        }


        map.put("houseManage", houseManageStr);

        logger.info("冻品管家分类申请管理区域申报开始");
        StringBuffer groupName = new StringBuffer();
        groupName.append("");
        if(!StringUtil.isNullOrEmpty(houseAccount.getHouseCode())){
            RsRequest<BS2101107Param> request = new RsRequest<BS2101107Param>();
            request.setAuth("MSK00001");
            request.setLoginId("msk01");
            request.setSiteCode("1");
            BS2101107Param bs2101107Param = new BS2101107Param();
            bs2101107Param.setHouseCode(houseAccount.getHouseCode());
            request.setParam(bs2101107Param);
            String url = SystemServerManager.BuyersReportServerManager.getQueryHouseKeeperOfHkGroup();
            RsResponse<BS2101107Result> rsResult =
                    RestClientUtil.post(url, request, new TypeReference<RsResponse<BS2101107Result>>() {
                    });
            if(rsResult.getResult() != null){
                BS2101107Result rs = rsResult.getResult();
                if(null != rs && !CollectionUtils.isEmpty(rs.getHkGroupList())){
                    List<BS2102115Bean> hkGroupList = rs.getHkGroupList();
                        for(int i=0;i<hkGroupList.size();i++){
                            if(i == hkGroupList.size() - 1){
                                groupName.append(hkGroupList.get(i).getHkGroupName());
                            }else {
                                groupName.append(hkGroupList.get(i).getHkGroupName()+"，");
                            }
                        }
                }
            }
        }
        logger.info("冻品管家分类申请管理区域申报结束");
        houseAccount.setFlag19(groupName.toString());
        map.put("houseAccount",houseAccount);


        BS2102116Param bs2102116Param = new BS2102116Param();
        bs2102116Param.setSlCode(slCode);
        bs2102116Param.setHouseCode(houseCode);
        bs2102116Param.setPaging(false);
        //工作经验
        PageResult<BS2102116Bean> workResult =  bs2102115Logic.findWork(bs2102116Param);
        if(workResult.getData() != null){
            //报表上默认空一行
            if(CollectionUtils.isEmpty(workResult.getData())){
                BS2102116Bean bs2102116Bean = new BS2102116Bean();
                workResult.getData().add(bs2102116Bean);
            }else {
                for(int i=0;i<workResult.getData().size();i++){
                    String time = setDateToString(workResult.getData().get(i).getWorkStart(),workResult.getData().get(i).getWorkEnd());
                    workResult.getData().get(i).setWorkTime(time);
                }
            }
        }
        map.put("workList",workResult.getData());
        //教育经历
        PageResult<BS2102117Bean> eduResult =  bs2102116Logic.findEdu(bs2102116Param);
        if(eduResult.getData() != null){
            //报表上默认空一行
            if(CollectionUtils.isEmpty(eduResult.getData())){
                BS2102117Bean bs2102117Bean = new BS2102117Bean();
                eduResult.getData().add(bs2102117Bean);
            }else {
                for(int i=0;i<eduResult.getData().size();i++){
                    String time = setDateToString(eduResult.getData().get(i).getEduStart(),eduResult.getData().get(i).getEduEnd());
                    eduResult.getData().get(i).setEduTime(time);
                }
            }
        }
        map.put("eduList",eduResult.getData());
        //培训经历
        PageResult<BS2102118Bean> trainResult =  bs2102117Logic.findTrain(bs2102116Param);
        if(trainResult.getData() != null){
            //报表上默认空一行
            if(CollectionUtils.isEmpty(trainResult.getData())){
                BS2102118Bean bs2102118Bean = new BS2102118Bean();
                trainResult.getData().add(bs2102118Bean);
            }else {
                for(int i=0;i<trainResult.getData().size();i++){
                    String time = setDateToString(trainResult.getData().get(i).getTrainStart(),trainResult.getData().get(i).getTrainEnd());
                    trainResult.getData().get(i).setTrainTime(time);
                }
            }
        }
        map.put("trainList",trainResult.getData());

        //查询证件照
        SlHouseIntroduce slHouseInfo = new SlHouseIntroduce();
        slHouseInfo.setHouseCode(houseCode);
        SlHouseIntroduce slHouseIntroduce = bs2101107Logic.findIntroduceInfoByHouseCode(slHouseInfo);
        if(null != slHouseIntroduce){
            if(!StringUtil.isNullOrEmpty(slHouseIntroduce.getUploadUrl1())){
                slHouseIntroduce.setUploadUrl1(SystemServerManager.CommonServerManager.getMskFlieDownLoad()+slHouseIntroduce.getUploadUrl1());
            }
            if(!StringUtil.isNullOrEmpty(slHouseIntroduce.getUploadUrl2())){
                slHouseIntroduce.setUploadUrl2(SystemServerManager.CommonServerManager.getMskFlieDownLoad()+slHouseIntroduce.getUploadUrl2());
            }
        }

        map.put("houseIntroduce",slHouseIntroduce);
        return map;
    }


    private String setDateToString(Date startDate,Date endDate){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        StringBuffer str = new StringBuffer();
        if(null != startDate){
            String start =sdf.format(startDate);
            str.append(start);
        }
        if(null != endDate){
            String end =sdf.format(endDate);
            str.append("至"+end);
        }
        if(null == startDate && null == endDate){
            str.append("");
        }
        return str.toString();
    }

    private String getPCDName(List list, String code){
        for (Object obj :list){
            if(obj instanceof ProvinceBean){
                if(((ProvinceBean) obj).getProvinceCode().equals(code)){
                    return ((ProvinceBean) obj).getProvinceName();
                }
            }
            if(obj instanceof CityBean){
                if(((CityBean) obj).getCityCode().equals(code)){
                    return ((CityBean) obj).getCityName();
                }
            }
            if(obj instanceof DistrictBean){
                if(((DistrictBean) obj).getDistrictCode().equals(code)){
                    return ((DistrictBean) obj).getDistrictName();
                }
            }
        }
        return null;
    }
}
