package com.msk.bs.controller;

import com.msk.bs.utils.CommRestUtil;
import com.msk.common.base.BaseController;
import com.msk.district.bean.CityBean;
import com.msk.district.bean.DistrictBean;
import com.msk.district.bean.DistrictParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zhu_kai1 on 2016/8/4.
 */
@Controller
@RequestMapping(value = "BS2102110")
public class BS2102110Controller extends BaseController {

    private Logger logger = LoggerFactory.getLogger(BS2102110Controller.class);

    /**
     * 买手初始化页面
     * @param model
     * @return
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(Model model){
        // 获取省份信息
        DistrictParam param = new DistrictParam();
        model.addAttribute("provinceList", CommRestUtil.getProvinceList(param));
        return  "bs/BS2102110";
    }

    /**
     * 查询物流区下面的地区
     * @return
     */
    @RequestMapping(value = "findCity",method = RequestMethod.POST)
    @ResponseBody
        public List<CityBean> findCityList( @RequestParam(value = "provinceCode", required = false) String provinceCode){
        DistrictParam districtParam = new DistrictParam();
        districtParam.setProvinceCode(provinceCode);
        return  CommRestUtil.getProvinceCityList(districtParam);
    }

    /**
     * 查询地区下的区县
     * @return
     */
    @RequestMapping(value = "findDistrict",method = RequestMethod.POST)
    @ResponseBody
    public List<DistrictBean> findDistrictList( @RequestParam(value = "cityCode", required = false) String cityCode){
        DistrictParam districtParam = new DistrictParam();
        districtParam.setCityCode(cityCode);
        return  CommRestUtil.getDistrictList(districtParam);
    }

}
