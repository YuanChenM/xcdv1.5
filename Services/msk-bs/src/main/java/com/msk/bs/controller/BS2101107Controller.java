package com.msk.bs.controller;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
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
import com.msk.core.entity.*;
import com.msk.district.bean.CityBean;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.LgcsAreaBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 冻品管家新添页面
 *
 * @author pxg
 */
@Controller
@RequestMapping(value = "BS2101107")
public class BS2101107Controller extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(BS2101107Controller.class);

    @Autowired
    private BSCommLogic bsCommLogic;
    @Autowired
    private BS2101107Logic bs2101107Logic;
    @Autowired
    private IBS2101104RsLogic ibs2101104RsLogic;
    @Autowired
    private BSHouseLeverLogic bsHouseLeverLogic;
    @Autowired
    private BS2102103Logic bs2102103Logic;
    @Autowired
    private BS2102112Logic bs2102112Logic;
    @Autowired
    private BSBasicInfoLogic bsBasicInfoLogic;

    /**
     * 实例化页面
     *
     * @return 加载冻品管家新添页面
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(@RequestParam(value = "slCode", required = false) String slCode,
                       @RequestParam(value = "slContact", required = false) String slContact,
                       @RequestParam(value = "slCodeDis", required = false) String slCodeDis,
                       @RequestParam(value = "houseCode", required = false) String houseCode,
                       Model model, String flagNum, String hkGroupId) throws UnsupportedEncodingException {


        DistrictParam districtParam = new DistrictParam();
        //地区回显
        List<MdProvince> mdProvinces = this.bsCommLogic.findMdProvinces(districtParam);

        //查询管家信息
        SlHouseAccount houseAccount = null;
        if (!StringUtil.isNullOrEmpty(slCode) && !StringUtil.isNullOrEmpty(houseCode)) {
            SlHouseAccount slHouseAccount = new SlHouseAccount();
            slHouseAccount.setSlCode(slCode);
            slHouseAccount.setHouseCode(houseCode);
            houseAccount = bs2101107Logic.findHouseAccount(slHouseAccount);
        }

        if (houseAccount != null) {
            //户籍地址
            if (!StringUtil.isNullOrEmpty(houseAccount.getRprovinceCode())) {
                List<MdCity> rCityList = this.bsCommLogic.findCityList(houseAccount.getRprovinceCode());
                model.addAttribute("rCityList", rCityList);
            }
            if (!StringUtil.isNullOrEmpty(houseAccount.getRcityCode())) {
                List<MdDistrict> rDistrictList = bsCommLogic.findDistrictList(houseAccount.getRcityCode());
                model.addAttribute("rDistrictList", rDistrictList);
            }
            //工作地址
            if (!StringUtil.isNullOrEmpty(houseAccount.getProvinceCode())) {
                List<MdCity> cityList = this.bsCommLogic.findCityList(houseAccount.getProvinceCode());
                model.addAttribute("cityList", cityList);
            }
            if (!StringUtil.isNullOrEmpty(houseAccount.getCityCode())) {
                List<MdDistrict> districtList = bsCommLogic.findDistrictList(houseAccount.getCityCode());
                model.addAttribute("districtList", districtList);
            }
            //虚拟地址
            if (!StringUtil.isNullOrEmpty(houseAccount.getVprovinceCode())) {
                List<MdCity> vCityList = this.bsCommLogic.findCityList(houseAccount.getVprovinceCode());
                model.addAttribute("vCityList", vCityList);
            }
            if (!StringUtil.isNullOrEmpty(houseAccount.getVcityCode())) {
                List<MdDistrict> vDistrictList = bsCommLogic.findDistrictList(houseAccount.getVcityCode());
                model.addAttribute("vDistrictList", vDistrictList);
            }

            //获取物流区
            districtParam.setLgcsAreaCode(houseAccount.getVlgcsAreaCode());
            List<LgcsAreaBean> lgcsAreaBeanList = CommRestUtil.getLogisticsAreaList(districtParam);

            List<BS2102107Bean> houseManageList = new ArrayList<>();
            //选择的管家分类
            if (!CollectionUtils.isEmpty(lgcsAreaBeanList)) {
                BS2102103Param bs2102103Param = new BS2102103Param();
                bs2102103Param.setHouseCode(houseAccount.getHouseCode());
                bs2102103Param.setSlCode(houseAccount.getSlCode());
                bs2102103Param.setLgcsAreaName(lgcsAreaBeanList.get(0).getLgcsAreaName());
                bs2102103Param.setLgcsAreaCode(houseAccount.getVlgcsAreaCode());
                bs2102103Param.setPaging(false);
                PageResult<BS2102107Bean> pageResult = bs2102103Logic.findAllHouseManage(bs2102103Param);
                houseManageList = pageResult.getData();
                if (!CollectionUtils.isEmpty(houseManageList)) {
                    model.addAttribute("houseManage", houseManageList);
                }
            }
            //拼接管家编码
            BsBasicInfo bsBasicInfo = bsBasicInfoLogic.findBsBasicInfo(houseAccount.getSlCode());
            if (bsBasicInfo != null && !StringUtil.isNullOrEmpty(houseAccount.getHouseCodeDis())) {
                houseAccount.setHouseCodeDis(StringUtil.isNullOrEmpty(bsBasicInfo.getSlCodeDis()) == true ? "" : bsBasicInfo.getSlCodeDis() + houseAccount.getHouseCodeDis());
            }
            logger.info("冻品管家分类申请管理区域申报开始");
            if (!StringUtil.isNullOrEmpty(houseAccount.getHouseCodeDis())) {
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
                if (rsResult.getResult() != null) {
                    BS2101107Result rs = rsResult.getResult();
                    if (null != rs && !CollectionUtils.isEmpty(rs.getHkGroupList())) {
                        List<BS2102115Bean> hkGroupList = rs.getHkGroupList();
                       /* StringBuffer groupName = new StringBuffer();
                        for(int i=0;i<hkGroupList.size();i++){
                            if(i == hkGroupList.size() - 1){
                                groupName.append(hkGroupList.get(i).getHkGroupName());
                            }else {
                                groupName.append(hkGroupList.get(i).getHkGroupName()+",");
                            }
                        }*/
                        model.addAttribute("hkGroupList", hkGroupList);
                    }
                }
            }
            logger.info("冻品管家分类申请管理区域申报结束");
        }

        //选择的买手编码
        if (StringUtil.isNullOrEmpty(slCodeDis) && houseAccount != null) {
            BS2102112Bean bs2102112Bean = bs2102112Logic.findHouseAccountBuyer(houseAccount);
            if (null != bs2102112Bean) {
                model.addAttribute("slCodeDis", bs2102112Bean.getSlCodeDis());
                model.addAttribute("slContact", bs2102112Bean.getSlContact());
            }
        } else {
            model.addAttribute("slCodeDis", slCodeDis);
            model.addAttribute("slContact", slContact);
        }

        //获取一级分类
        SlHouseType houseTypeOne = new SlHouseType();
        houseTypeOne.setTypeLever("0");
        List<SlHouseType> houseTypeList = bsHouseLeverLogic.findSlHouseType(houseTypeOne);
        //获取冻品管家二级分类
        LinkedHashMap<SlHouseType, List<SlHouseType>> houseTypeMap = new LinkedHashMap<>();
        if (!CollectionUtils.isEmpty(houseTypeList)) {
            for (SlHouseType slHouseType : houseTypeList) {
                //获取二级分类
                SlHouseType houseTypeTwo = new SlHouseType();
                houseTypeTwo.setTypeLever("1");
                houseTypeTwo.setParentTypeCode(slHouseType.getTypeCode());
                List<SlHouseType> houseTypeListTwo = bsHouseLeverLogic.findSlHouseType(houseTypeTwo);
                houseTypeMap.put(slHouseType, houseTypeListTwo);
            }
        }


        model.addAttribute("slHouseAccount", houseAccount);
        model.addAttribute("houseTypeMap", houseTypeMap);
        model.addAttribute("mdProvinces", mdProvinces);
        model.addAttribute("slCode", slCode);
        model.addAttribute("flagNum", flagNum);
        model.addAttribute("groupId", hkGroupId);
        return "bs/BS2101107";
    }


    /**
     * 根据provinceId查询地市
     *
     * @param provinceCode 省Code
     * @return
     */
    @RequestMapping(value = "findCity", method = RequestMethod.POST)
    public
    @ResponseBody
    List<MdCity> findCity(String provinceCode) {
        MdProvince mdProvince = this.bs2101107Logic.findProvince(provinceCode);
        return this.bsCommLogic.findCityList(provinceCode);
    }

    /**
     * 根据cityId查询地区
     *
     * @param cityCode
     * @return
     */
    @RequestMapping(value = "findDistrict", method = RequestMethod.POST)
    public
    @ResponseBody
    List<MdDistrict> findDistrict(String cityCode) {
        MdCity mdCity = this.bs2101107Logic.findCity(cityCode);
        return this.bsCommLogic.findDistrictList(cityCode);
    }

    /**
     * 数据保存
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public
    @ResponseBody
    RsResponse<SlHouseAccount> save(@RequestBody IBS2101104RsParam ibs2101104RsParam) {
        RsResponse<SlHouseAccount> rs = new RsResponse<SlHouseAccount>();
        rs.setReturnCode("1002");
        rs.setMessage("保存失败");
        if (ibs2101104RsParam.getSlHouseAccount() == null || CollectionUtils.isEmpty(ibs2101104RsParam.getHouseTypeList())) {
            return rs;
        }
        try {
            Long.valueOf(ibs2101104RsParam.getSlHouseAccount().getSlCodeDis());
        } catch (NumberFormatException exception) {
            rs.setReturnCode("1005");
            rs.setMessage("买手编码不存在，不能操作");
            return rs;
        }
        BaseParam baseParam = new BaseParam();
        super.setCommonParam(baseParam);

        List<BS2102107Bean> slHouseTypeList = null;
        if (StringUtil.isNullOrEmpty(ibs2101104RsParam.getSlHouseAccount().getHouseCode())) {
            return ibs2101104RsLogic.editHouseAccount(ibs2101104RsParam, baseParam, slHouseTypeList);

        } else {

            // 获取物流区
            DistrictParam vdistrictParam = new DistrictParam();
            vdistrictParam.setCityName(ibs2101104RsParam.getVcityName());
            vdistrictParam.setCityCodes(new String[]{ibs2101104RsParam.getSlHouseAccount().getVcityCode()});
            vdistrictParam.setFlag(0);
            List<CityBean> lgcsAreaBeanList = CommRestUtil.getProvinceCityList(vdistrictParam);
            //获取物流区

            if (!CollectionUtils.isEmpty(lgcsAreaBeanList)) {
                BS2102103Param bs2102103Param = new BS2102103Param();
                bs2102103Param.setHouseCode(ibs2101104RsParam.getSlHouseAccount().getHouseCode());
                bs2102103Param.setSlCode(ibs2101104RsParam.getSlHouseAccount().getSlCode());
                bs2102103Param.setLgcsAreaName(lgcsAreaBeanList.get(0).getLgcsAreaName());
                bs2102103Param.setLgcsAreaCode(lgcsAreaBeanList.get(0).getLgcsAreaCode());
                bs2102103Param.setPaging(false);
                PageResult<BS2102107Bean> pageResult = bs2102103Logic.findAllHouseManage(bs2102103Param);
                slHouseTypeList = pageResult.getData();
                //获取原来的管家信息
                SlHouseAccount houseAccount = bs2101107Logic.findHouseAccount(ibs2101104RsParam.getSlHouseAccount());
                if (houseAccount == null) {
                    rs.setReturnCode("1004");
                    rs.setMessage("新增出错，请联系管理员");
                    return rs;
                }
                return ibs2101104RsLogic.editHouseAccount(ibs2101104RsParam, baseParam, slHouseTypeList);
            } else {
                return rs;
            }
        }
    }

    /**
     * 根据一级分类获取二级分类列表
     *
     * @param houseCategoryId
     * @return
     */
    @RequestMapping(value = "getHouseTypeList", method = RequestMethod.POST)
    public
    @ResponseBody
    List<SlHouseType> getHouseTypeList(@RequestParam(value = "houseCategoryId", required = false) String houseCategoryId) {
        SlHouseType slHouseType = new SlHouseType();
        slHouseType.setParentTypeCode(houseCategoryId);
        slHouseType.setTypeLever("1");
        List<SlHouseType> typeList = new ArrayList<SlHouseType>();
        if (!StringUtil.isNullOrEmpty(houseCategoryId)) {
            typeList = bs2101107Logic.getHouseTypeList(slHouseType);
        }
        return typeList;
    }
}
