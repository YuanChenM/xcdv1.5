package com.msk.bs.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.bs.bean.IBS2101105RsResult;
import com.msk.bs.logic.BS2101102Logic;
import com.msk.bs.logic.BSCommLogic;
import com.msk.common.base.BaseController;
import com.msk.core.entity.MdCity;
import com.msk.core.entity.MdDistrict;
import com.msk.core.entity.MdProvince;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 冻品管家列表Controller.
 *
 * @author cx
 */
@Controller
@RequestMapping(value = "BS2101102")
public class BS2101102Controller extends BaseController {
    @Autowired
    private BS2101102Logic bS2101102Logic;

    @Autowired
    private BSCommLogic bsCommLogic;

    /**
     * 实例化页面
     *
     * @return 冻品管家列表页面
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(@RequestParam(value = "slCode", required = false) String slCode,
                       @RequestParam(value = "slCodeDis", required = false) String slCodeDis,
                       @RequestParam(value = "slContact", required = false) String slContact,
                       @RequestParam(value = "provinceCode1", required = false) String provinceCode1,
                       @RequestParam(value = "cityCode1", required = false) String cityCode1,
                       @RequestParam(value = "districtCode1", required = false) String districtCode1,
                       @RequestParam(value = "provinceCode", required = false) String provinceCode,
                       @RequestParam(value = "cityCode", required = false) String cityCode,
                       @RequestParam(value = "districtCode", required = false) String districtCode,
                       @RequestParam(value = "flagNum", required = false) String flagNum,
                       @RequestParam(value = "slAccount", required = false) String slAccount,
                       Model model) throws UnsupportedEncodingException {
        //List<MdProvince> mdProvinces = bsCommLogic.findMdProvinces(new BaseParam());

        BaseParam baseParam = new BaseParam();
        List<MdProvince> mdProvinces = this.bsCommLogic.findMdProvinces(baseParam);
        List<MdCity> cityList = null;
        List<MdDistrict> mdDistrictList = null;
        String provinceId = "";
        String cityId = "";
        for (int i = 0; i < mdProvinces.size(); i++) {
            MdProvince mdProvince = mdProvinces.get(i);
            if (mdProvince.getProvinceCode().equals(provinceCode1)) {
                provinceId = StringUtil.toSafeString(mdProvince.getProvinceId());
            }
        }
        cityList = this.bsCommLogic.findCityList(provinceId);
        for (int i = 0; i < cityList.size(); i++) {
            MdCity mdCity = cityList.get(i);
            if (mdCity.getCityCode().equals(cityCode1)) {
                cityId = StringUtil.toSafeString(mdCity.getCityId());
            }
        }
        mdDistrictList = bsCommLogic.findDistrictList(cityId);
        model.addAttribute("mdProvinces", mdProvinces);
        model.addAttribute("cityList", cityList);
        model.addAttribute("mdDistrictList", mdDistrictList);
        model.addAttribute("slCode", slCode);
        model.addAttribute("slCodeDis", slCodeDis);
        model.addAttribute("slContact", URLDecoder.decode(slContact,"UTF-8"));
        model.addAttribute("provinceCode1", provinceCode1);
        model.addAttribute("cityCode1", cityCode1);
        model.addAttribute("districtCode1", districtCode1);
        model.addAttribute("provinceCode", provinceCode);
        model.addAttribute("cityCode", cityCode);
        model.addAttribute("districtCode", districtCode);
        model.addAttribute("flagNum", flagNum);
        model.addAttribute("slAccount", slAccount);
        return "/bs/BS2101102";
    }

    /**
     * 冻品管家列表
     *
     * @param basePageParam basePageParam
     * @return 信息
     * @author cx
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<IBS2101105RsResult> search(
            @RequestParam(value = "provinceCode1", required = false) String provinceCode1,
            @RequestParam(value = "cityCode1", required = false) String cityCode1,
            @RequestParam(value = "districtCode1", required = false) String districtCode1,
            BasePageParam basePageParam) {
        DbUtils.buildLikeCondition(basePageParam, "houseAccount", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "houseShowName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "slIdcard", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "houseAddress", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "houseTel", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "houseCodeDis", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "vhouseAddress", DbUtils.LikeMode.FRONT);

        if (null == provinceCode1 || provinceCode1.equals("0")) {
            provinceCode1 = "";
        }
        if ( null == cityCode1 ||cityCode1.equals("0")) {
            cityCode1 = "";
        }
        if (null == districtCode1 || districtCode1.equals("0")) {
            districtCode1 = "";
        }
        basePageParam.setFilter("provinceCode1", provinceCode1);
        basePageParam.setFilter("cityCode1", cityCode1);
        basePageParam.setFilter("districtCode1", districtCode1);
        if (isDebug) {
            return null;
        }
        return this.bS2101102Logic.findBS2101102List(basePageParam);
    }

}
