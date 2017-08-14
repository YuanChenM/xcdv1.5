package com.msk.bs.controller;

import com.hoperun.core.utils.StringUtil;
import com.msk.bs.logic.BSHouseLeverLogic;
import com.msk.bs.utils.CommRestUtil;
import com.msk.common.base.BaseController;
import com.msk.core.entity.SlHouseType;
import com.msk.district.bean.CityBean;
import com.msk.district.bean.DistrictParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zhu_kai1 on 2016/8/4.
 */
@Controller
@RequestMapping(value="BS2102109")
public class BS2102109Controller extends BaseController {

    @Autowired
    private BSHouseLeverLogic bsHouseLeverLogic;
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init (Model model){
        // 获取物流区信息
        DistrictParam param = new DistrictParam();
        model.addAttribute("logisticsAreas", CommRestUtil.getLogisticsAreaList(param));
        SlHouseType houseType = new SlHouseType();
        houseType.setTypeLever("0");
        List<SlHouseType> houseTypeList = bsHouseLeverLogic.findSlHouseType(houseType);
        model.addAttribute("houseTypeList",houseTypeList);
        return "bs/BS2102109";
    }

    @RequestMapping(value = "findCity", method = RequestMethod.POST)
    @ResponseBody
    public List<CityBean> findCity(String lgcsAreaCode){
        //获取地区
        if(!StringUtil.isNullOrEmpty(lgcsAreaCode)){
            DistrictParam param = new DistrictParam();
            param.setLgcsAreaCode(lgcsAreaCode);
            param.setIsLoadCity(0);
            List<CityBean> cityBeanList = CommRestUtil.getCityList(param);
            return cityBeanList;
        }
        return null;
    }
}
