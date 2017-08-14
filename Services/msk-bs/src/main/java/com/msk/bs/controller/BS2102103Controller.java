package com.msk.bs.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.msk.core.entity.SlHouseGrade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.bs.bean.BS2102103Bean;
import com.msk.bs.bean.BS2102103Param;
import com.msk.bs.bean.BS2102107Bean;
import com.msk.bs.logic.BS2102103Logic;
import com.msk.bs.logic.BSHouseLeverLogic;
import com.msk.bs.utils.CommRestUtil;
import com.msk.common.base.BaseController;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.SlHouseManage;
import com.msk.core.entity.SlHouseType;
import com.msk.district.bean.CityBean;
import com.msk.district.bean.DistrictBean;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.ProvinceBean;

/**
 * Created by wang_haichun on 2016/8/2.
 */
@Controller
@RequestMapping(value = "BS2102103")
public class BS2102103Controller extends BaseController{
    private Logger logger = LoggerFactory.getLogger(BS2102103Controller.class);

    @Autowired
    private BS2102103Logic bs2102103Logic;
    @Autowired
    private CommonLogic commonLogic;
    @Autowired
    private BSHouseLeverLogic bsHouseLeverLogic;


    @RequestMapping(value = "init",method = RequestMethod.POST)
    public String init(Model model, BS2102103Bean bs2102103Bean,
                       @RequestParam(value = "flagNum",required = false) String flagNum,
                       @RequestParam(value = "groupId",required = false) String groupId){
        logger.info("冻品管家管理设置画面初始化");
        if(bs2102103Bean != null){
            DistrictParam param = new DistrictParam();
            if(!StringUtil.isNullOrEmpty(bs2102103Bean.getVprovinceCode())){
                // 获取省份信息
                param.setProvinceCode(bs2102103Bean.getVprovinceCode());
                List<ProvinceBean> provinceBeanList = CommRestUtil.getProvinceList(param);
                if(!CollectionUtils.isEmpty(provinceBeanList)){
                    model.addAttribute("provinceName",getPCDName(provinceBeanList,bs2102103Bean.getVprovinceCode()));
                }
                if(!StringUtil.isNullOrEmpty(bs2102103Bean.getVcityCode())){
                    //获取城市信息
                    param.setCityCode(bs2102103Bean.getVcityCode());
                    List<CityBean> cityBeanList =CommRestUtil.getProvinceCityList(param);
                    if(!CollectionUtils.isEmpty(cityBeanList)){
                        model.addAttribute("cityName",getPCDName(cityBeanList,bs2102103Bean.getVcityCode()));
                    }
                    if(!StringUtil.isNullOrEmpty(bs2102103Bean.getVdistrictCode())){
                        //获取区县信息
                        param.setDistrictCode(bs2102103Bean.getVdistrictCode());
                        List<DistrictBean> districtBeanList =CommRestUtil.getDistrictList(param);
                        if(!CollectionUtils.isEmpty(districtBeanList)){
                            model.addAttribute("districtName",getPCDName(districtBeanList,bs2102103Bean.getVdistrictCode()));
                        }
                    }
                }
            }
        }

        model.addAttribute("bs2102103Bean",bs2102103Bean);
        model.addAttribute("flagNum",flagNum);
        model.addAttribute("groupId",groupId);
        logger.info("冻品管家管理设置画面初始化结束");
        return "bs/BS2102103";
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



    /**
     * 获取物流区地区,一级分类
     * @return
     */
    @RequestMapping(value = "findLgcsAreaInfo",method = RequestMethod.POST)
    public String findLgcsAreaInfo(Model model,SlHouseManage slHouseManage,
                                   @RequestParam(value = "houseShowName", required = false) String houseShowName,
                                   @RequestParam(value = "flag1", required = false) Integer flag1,
                                   @RequestParam(value = "houseTel", required = false) String houseTel,
                                   @RequestParam(value = "wechat", required = false) String wechat,
                                   @RequestParam(value = "houseGreade", required = false) String houseGreade,
                                   @RequestParam(value = "houseStar", required = false) String houseStar,
                                   @RequestParam(value = "vhouseAddress", required = false) String vhouseAddress,
                                   @RequestParam(value = "flagNum",required = false) String flagNum,
                                   @RequestParam(value = "groupId",required = false) String groupId){
        //获取地区
        if(!StringUtil.isNullOrEmpty(slHouseManage.getLgcsAreaCode())){
            DistrictParam param = new DistrictParam();
            param.setLgcsAreaCode(slHouseManage.getLgcsAreaCode());
            param.setIsLoadCity(0);
            List<CityBean> cityBeanList = bs2102103Logic.getCityList(param);
            model.addAttribute("cityBeanList",cityBeanList);
            model.addAttribute("slHouseManage",slHouseManage);
        }
        //获取一级分类
        SlHouseType houseType = new SlHouseType();
        houseType.setTypeLever("0");
        List<SlHouseType> houseTypeList = bsHouseLeverLogic.findSlHouseType(houseType);
        model.addAttribute("houseTypeList",houseTypeList);
        model.addAttribute("houseShowName",houseShowName);
        model.addAttribute("flag1",flag1);
        model.addAttribute("houseTel",houseTel);
        model.addAttribute("wechat",wechat);
        model.addAttribute("houseGreade",houseGreade);
        model.addAttribute("houseStar",houseStar);
        model.addAttribute("vhouseAddress",vhouseAddress);
        model.addAttribute("flagNum",flagNum);
        model.addAttribute("groupId",groupId);
        return "bs/BS2102107";
    }

    /**
     * 获取二级分类
     * @param parentTypeCode
     * @return
     */
    @RequestMapping(value = "findLeverTwo",method = RequestMethod.POST)
    public @ResponseBody List<SlHouseType> findLeverTwo(String parentTypeCode){
        List<SlHouseType> houseTypeList = new ArrayList<>();
        if(!StringUtil.isNullOrEmpty(parentTypeCode)){
            //获取二级分类
            SlHouseType houseType = new SlHouseType();
            houseType.setTypeLever("1");
            houseType.setParentTypeCode(parentTypeCode);
            houseTypeList = bsHouseLeverLogic.findSlHouseType(houseType);
        }
        return houseTypeList;
    }


    /**
     *新增数据
     * @param param
     * @return
     */
    @RequestMapping(value = "addHouseManager",method = RequestMethod.POST)
    public @ResponseBody int addHouseManager(SlHouseManage param){
        super.setCommonParam(param);

        if(checkBS2102103Param(param)){
            param.setMid(commonLogic.maxId("SL_HOUSE_MANAGE","MID"));
            param.setCreatetime(new Date());
            param.setPublicBuyers(0);
            param.setVipBuyers(0);
            param.setMarketingDays(0);
            param.setIsChangeBuyers("0");
            param.setDelFlg("0");
            param.setCrtTime(new Date());
            param.setUpdTime(new Date());
            param.setActTime(new Date());
            param.setVer(0);
            /**Add: 创建时间，修改星级在页面显示 2016/09/19   BY  任强  Start */
            SlHouseGrade slHouseGrade = new SlHouseGrade();
            super.setCommonParam(slHouseGrade);
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, +1);
            java.util.Date nextDate = cal.getTime();
            slHouseGrade.setGradeId(commonLogic.maxId("SL_HOUSE_GRADE","GRADEID"));
            slHouseGrade.setSlCode(param.getSlCode());
            slHouseGrade.setHouseCode(param.getHouseCode());
            slHouseGrade.setLgcsAreaCode(param.getLgcsAreaCode());
            slHouseGrade.setLgcsAreaName(param.getLgcsAreaName());
            slHouseGrade.setHouseCategoryCode(param.getHouseCategoryCode());
            slHouseGrade.setHouseReclassifyCode(param.getHouseReclassifyCode());
            slHouseGrade.setValidYearMonth(getTimeStr());
            slHouseGrade.setGradeCode("3");
            slHouseGrade.setDelFlg("0");
            slHouseGrade.setCrtTime(new Date());
            slHouseGrade.setUpdTime(new Date());
            slHouseGrade.setActTime(new Date());
            slHouseGrade.setEndTime(nextDate);
            slHouseGrade.setStatus("0");
            slHouseGrade.setVer(0);
            return bs2102103Logic.addHouseManager(param,slHouseGrade);
            /**Add: 创建时间，修改星级在页面显示 2016/09/19   BY  任强  End */
        }
        return -2;
    }


    /**
     * 查询列表
     * @param param
     * @return
     */
    @RequestMapping(value = "search",method = RequestMethod.POST)
    public @ResponseBody PageResult<BS2102107Bean> search(BS2102103Param param){
        PageResult<BS2102107Bean> pageResult = bs2102103Logic.findAllHouseManage(param);
        List<BS2102107Bean> bs2102107BeanList = pageResult.getData();
        if(!CollectionUtils.isEmpty(bs2102107BeanList)){
            for(int i=0;i<bs2102107BeanList.size();i++){
                if("0".equals(bs2102107BeanList.get(i).getIsChangeBuyers())){
                    bs2102107BeanList.get(i).setIsChangeBuyersText("否");
                }else if("1".equals(bs2102107BeanList.get(i).getIsChangeBuyers())){
                    bs2102107BeanList.get(i).setIsChangeBuyersText("是");
                }
            }
            pageResult.setData(bs2102107BeanList);
        }
        return pageResult;
    }

    /**
     * 修改数据
     * @param houseManages
     * @return
     */
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public @ResponseBody int update(@RequestBody ArrayList<SlHouseManage> houseManages){
        BaseParam param = new BaseParam();
        super.setCommonParam(param);
        return  bs2102103Logic.updateAndSave(houseManages,param);
    }



    //检查参数非空
    private boolean checkBS2102103Param(SlHouseManage param){
        if(StringUtil.isNullOrEmpty(param.getSlCode()) ||
                StringUtil.isNullOrEmpty(param.getHouseCode()) ||
                StringUtil.isNullOrEmpty(param.getCityCode()) ||
                StringUtil.isNullOrEmpty(param.getCityName()) ||
                StringUtil.isNullOrEmpty(param.getHouseCategoryCode()) ||
                StringUtil.isNullOrEmpty(param.getHouseReclassifyCode())){
            return false;
        }
        return true;
    }

    private String getTimeStr(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        String str = sdf.format(new java.util.Date());
        return  str;
    }





}
