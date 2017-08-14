package com.msk.bs.controller;

import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.bs.bean.BS2101101Bean;
import com.msk.bs.bean.BS2101101Param;
import com.msk.bs.bean.IBS2101102RsParam;
import com.msk.bs.bean.IBS210110301Bean;
import com.msk.bs.logic.BS2101101Logic;
import com.msk.bs.logic.BSCommLogic;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.core.entity.*;
import com.msk.district.bean.DistrictParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * 买手店列表Controller.
 *
 * @author cx
 */
@Controller
@RequestMapping(value = "BS2101101")
public class BS2101101Controller extends BaseController {
    @Autowired
    private BS2101101Logic bS2101101Logic;
    @Autowired
    private BSCommLogic bsCommLogic;


    /**
     * 实例化页面
     *
     * @return 买手店列表页面
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    private String init(Model model,
                        @RequestParam(value = "slCode", required = false) String slCode,
                        @RequestParam(value = "applyStatus", required = false) Integer applyStatus,
                        @RequestParam(value = "provinceCode", required = false) String provinceCode,
                        @RequestParam(value = "cityCode", required = false) String cityCode,
                        @RequestParam(value = "districtCode", required = false) String districtCode) {
        DistrictParam districtParam = new DistrictParam();
        List<MdProvince> mdProvinces = this.bsCommLogic.findMdProvinces(districtParam);
        List<MdCity> cityList = null;
        List<MdDistrict> mdDistrictList = null;
        String provinceCodeTemp = "";
        String cityCodeTemp = "";
        for (int i = 0; i < mdProvinces.size(); i++) {
            MdProvince mdProvince = mdProvinces.get(i);
            if (mdProvince.getProvinceCode().equals(provinceCode)) {
                provinceCodeTemp = StringUtil.toSafeString(mdProvince.getProvinceCode());
            }
        }
        cityList = this.bsCommLogic.findCityList(provinceCodeTemp);
        for (int i = 0; i < cityList.size(); i++) {
            MdCity mdCity = cityList.get(i);
            if (mdCity.getCityCode().equals(cityCode)) {
                cityCodeTemp = StringUtil.toSafeString(mdCity.getCityCode());
            }
        }
        mdDistrictList = bsCommLogic.findDistrictList(cityCodeTemp);
        model.addAttribute("mdProvinces", mdProvinces);
        model.addAttribute("cityList", cityList);
        model.addAttribute("mdDistrictList", mdDistrictList);
        model.addAttribute("mdProvinces", mdProvinces);
        model.addAttribute("slCode", slCode);
        model.addAttribute("applyStatus", applyStatus);
        model.addAttribute("provinceCode", provinceCode);
        model.addAttribute("cityCode", cityCode);
        model.addAttribute("districtCode", districtCode);
        return "/bs/BS2101101";
    }

    /**
     * 查询买手店列表
     *
     * @param basePageParam basePageParam
     * @return 信息
     * @author cx
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<IBS210110301Bean> search(@RequestParam(value = "provinceCode", required = false) String provinceCode,
                                        @RequestParam(value = "cityCode", required = false) String cityCode,
                                        @RequestParam(value = "districtCode", required = false) String districtCode,
                                        BS2101101Param basePageParam) {
        DbUtils.buildLikeCondition(basePageParam, "slCodeDis", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(basePageParam, "slContact", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(basePageParam, "slIdcard", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(basePageParam, "slTel", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(basePageParam, "slAddress", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(basePageParam, "stewardNum", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(basePageParam, "buyerNum", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(basePageParam, "shopName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(basePageParam, "lgcsAreaName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(basePageParam,"agentType",DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(basePageParam,"distribution",DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(basePageParam,"demesne",DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(basePageParam,"registerSource",DbUtils.LikeMode.PARTIAL);

        if (provinceCode.equals("0")) {
            provinceCode = "";
        }
        if (cityCode.equals("0")) {
            cityCode = "";
        }
        if (districtCode.equals("0")) {
            districtCode = "";
        }
        basePageParam.setFilter("provinceCode", provinceCode);
        basePageParam.setFilter("cityCode", cityCode);
        basePageParam.setFilter("districtCode", districtCode);
        if (isDebug) {
            return null;
        }
        return this.bS2101101Logic.findBS2101101List(basePageParam);
    }

    /**
     * 删除买手信息
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public void deleteBuyers(BS2101101Bean bs2101101Bean) {
        BsAccount bsAccount = new BsAccount();
        //买手基本信息
        BsBasicInfo bsBasicInfo = null;

        super.setCommonParam(bsAccount);

        bsAccount.setSlAccount(bs2101101Bean.getSlAccount());
        bsAccount.setSlTel(bs2101101Bean.getSlTel());

        if (!StringUtil.isNullOrEmpty(bs2101101Bean.getSlCode())) {
            bsBasicInfo = new BsBasicInfo();
            bsBasicInfo.setSlCode(bs2101101Bean.getSlCode());
            bsBasicInfo.setSlAccount(bs2101101Bean.getSlAccount());
            super.setCommonParam(bsBasicInfo);
        }

        IBS2101102RsParam ibs2101102RsParam = new IBS2101102RsParam();
        ibs2101102RsParam.setSlAccount(bsAccount);
        ibs2101102RsParam.setSlSeller(bsBasicInfo);
        ibs2101102RsParam.setDelFlg("1");

        RsRequest<IBS2101102RsParam> param = new RsRequest<IBS2101102RsParam>();
        param.setParam(ibs2101102RsParam);
        bs2101101Bean.setUpdId(bsAccount.getUpdId());
        bs2101101Bean.setUpdTime(new Date());
        /** Modif for Bug#3498 删除买手时，解除买手下面所有的管家下面的买家的绑定关系，
         * 同时把绑定关系实时同步到买家池  at 2016/10/27 by gao_min start */
        // 1.删除买手信息
        this.bS2101101Logic.deleteSlCode(param);
        // 2.删除管家
        this.bS2101101Logic.deleteHouseAccountBySlCode(bs2101101Bean);
        // 3.解除买手下面所有的管家下面的买家的绑定关系并把绑定关系实时同步到买家池
        this.bS2101101Logic.unbindHkRelation(bs2101101Bean);
        /** Modif for Bug#3498 删除买手时，解除买手下面所有的管家下面的买家的绑定关系，
         * 同时把绑定关系实时同步到买家池 at 2016/10/27 by gao_min end  */
    }
}
