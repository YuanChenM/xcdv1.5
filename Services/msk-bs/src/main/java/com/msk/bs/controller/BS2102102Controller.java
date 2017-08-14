package com.msk.bs.controller;

import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.bs.bean.BS2102102Bean;
import com.msk.bs.bean.BS2102102Param;
import com.msk.bs.logic.BS2102102Logic;
import com.msk.bs.logic.BSHouseLeverLogic;
import com.msk.bs.utils.CommRestUtil;
import com.msk.common.base.BaseController;
import com.msk.core.entity.SlHouseType;
import com.msk.district.bean.CityBean;
import com.msk.district.bean.DistrictParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zhu_kai1 on 2016/8/2.
 */
@Controller
@RequestMapping(value ="BS2102102")
public class BS2102102Controller extends BaseController {

    private Logger logger = LoggerFactory.getLogger(BS2102102Controller.class);

    @Autowired
    private BS2102102Logic bs2102102Logic;
    @Autowired
    private BSHouseLeverLogic bsHouseLeverLogic;
    /**
     * 冻品管家一览
     * @param model
     * @return
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init (Model model){
        // 获取物流区信息
        DistrictParam param = new DistrictParam();
        model.addAttribute("logisticsAreas", CommRestUtil.getLogisticsAreaList(param));
        SlHouseType houseType = new SlHouseType();
        houseType.setTypeLever("0");
        List<SlHouseType> houseTypeList = bsHouseLeverLogic.findSlHouseType(houseType);
        model.addAttribute("houseTypeList",houseTypeList);
        return "bs/BS2102102";
    }

    /**
     * 查询冻品管家信息
     * @param param
     * @return
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public @ResponseBody PageResult<BS2102102Bean> search(BS2102102Param param){
        /**Add: 横展开设置模糊查询条件 2016/09/12   BY  任强  Start */
       String houseShowName =  DbUtils.buildLikeCondition(param.getHouseShowName(), DbUtils.LikeMode.PARTIAL);
        param.setHouseShowName(houseShowName);
        String slContact =DbUtils.buildLikeCondition(param.getSlContact(), DbUtils.LikeMode.PARTIAL);
        param.setSlContact(slContact);
        /**Add: 横展开设置模糊查询条件 2016/09/12   BY  任强  End */
        return  bs2102102Logic.searchHouseInfo(param);
    }


    /**
     * 删除冻品管家信息
     * @param param
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public void delete(BS2102102Param param){
        super.setCommonParam(param);
        param.setUpdTime(DateTimeUtil.getCustomerDate());
        bs2102102Logic.deleteHouseInfo(param);
    }

    /**
     * 查询该物流区下面的地区
     * @param param
     * @return
     */
    @RequestMapping(value = "findCity", method = RequestMethod.POST)
    @ResponseBody
    public List<CityBean> findCityInfo(BS2102102Param param){
        DistrictParam districtParam = new DistrictParam();
        districtParam.setLgcsAreaCode(param.getVlgcsAreaCode());
        districtParam.setIsLoadCity(0);
       return  CommRestUtil.getCityList(districtParam);
    }
}
