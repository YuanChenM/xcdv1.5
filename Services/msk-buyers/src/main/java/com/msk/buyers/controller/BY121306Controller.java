package com.msk.buyers.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.msk.buyers.logic.BY121306Logic;
import com.msk.buyers.utils.RestCommUtil;
import com.msk.common.base.BaseController;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.consts.BuyersConst;
import com.msk.core.entity.ByMarketFood;
import com.msk.core.entity.CommConstant;
import com.msk.district.bean.CityBean;
import com.msk.district.bean.DistrictBean;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.LgcsAreaBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 菜场详细画面编辑
 *
 * @author zhou_ling
 */
@Controller
@RequestMapping("BY121306")
public class BY121306Controller extends BaseController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121306Controller.class);

    @Autowired
    private BY121306Logic by121306Logic;

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(@RequestParam(value = "fodMarketId", required = false) String fodMarketId,
                       @RequestParam(value = "type", required = true) String type, Model model) {
        logger.debug("菜场详细画面编辑");
        ByMarketFood byMarketFood = new ByMarketFood();
        if ("edit".equals(type)) {
            byMarketFood = by121306Logic.findMarketFood(fodMarketId);
        }
        model.addAttribute("fodMarketId", fodMarketId);
        model.addAttribute("byMarketFood", byMarketFood);
        DistrictParam districtParam = new DistrictParam();
        //查询所有物流区
        List<LgcsAreaBean> logisticsAreaList = RestCommUtil.getLogisticsAreaList(districtParam).getResult().getLgcsAreaList();
        //查询指定物流区中的城市
        List<CityBean> cityList = new ArrayList<CityBean>();
        if (StringUtils.hasLength(byMarketFood.getLgcsAreaCode())) {
            districtParam.setLgcsAreaCode(byMarketFood.getLgcsAreaCode());
            districtParam.setFlag(0);
            cityList = RestCommUtil.getCityList(districtParam).getResult().getCityList();
        }
        //查询指定城市中的区县
        List<DistrictBean> districtList = new ArrayList<DistrictBean>();
        if (StringUtils.hasLength(byMarketFood.getCityCode())) {
            districtParam = new DistrictParam();
            districtParam.setCityCode(byMarketFood.getCityCode());
            districtParam.setFlag(0);
            districtList = RestCommUtil.getDistrictList(districtParam).getResult().getDistrictList();
        }
        //菜场类型
        Map<String, String> marketTypeMap = CodeMasterManager.findCodeMasterMap(BuyersConst.MarketType.TYPE);
        List<CommConstant> marketTypeList = new ArrayList(marketTypeMap.entrySet());
        //菜场地段类型
        Map<String, String> marketSectionTypeMap = CodeMasterManager.findCodeMasterMap(BuyersConst.MarketSectionType.TYPE);
        List<CommConstant> marketSectionTypeList = new ArrayList(marketSectionTypeMap.entrySet());
        model.addAttribute("logisticsAreaList", logisticsAreaList);
        model.addAttribute("cityList", cityList);
        model.addAttribute("districtList", districtList);
        model.addAttribute("marketTypeList", marketTypeList);
        model.addAttribute("marketSectionTypeList", marketSectionTypeList);
        return "buyers/BY121306";
    }

    /**
     * 菜场详细画面编辑保存
     *
     * @param byMarketFood byMarketFood
     * @return
     */
    @RequestMapping(value = "save",
            method = RequestMethod.POST)
    public
    @ResponseBody
    String byMarketTerminalSave(ByMarketFood byMarketFood) {
        super.setCommonParam(byMarketFood);
        Date currentDate = DateTimeUtil.getCustomerDate();
        String fodMarketIdMarketId = byMarketFood.getFodMarketId();
        String marketCode = byMarketFood.getMarketCode();
        String flg = "";
        BaseParam param = new BaseParam();
        param.getFilterMap().put("marketCode", marketCode);
        List<ByMarketFood> fodMarketIdList = by121306Logic.getFodMarketId(param);
        if (StringUtil.isEmpty(fodMarketIdMarketId)) {
            if (CollectionUtils.isEmpty(fodMarketIdList)) {
                fodMarketIdMarketId = UUID.randomUUID().toString();
                byMarketFood.setFodMarketId(fodMarketIdMarketId);
                // 菜场详细画面新增保存
                byMarketFood.setUpdTime(currentDate);
                byMarketFood.setActTime(currentDate);
                byMarketFood.setCrtTime(currentDate);
                by121306Logic.byMarketFodAdd(byMarketFood);
                return SystemConst.RsStatus.SUCCESS;
            } else {
                logger.info("菜场编码已存在,新增失败");
                flg = "1";
                return flg;
            }
        } else {
            if (!CollectionUtils.isEmpty(fodMarketIdList)) {
                for (ByMarketFood fodMarket : fodMarketIdList) {
                    if (!fodMarket.getFodMarketId().equals(fodMarketIdMarketId)) {
                        logger.info("批发市场编码已存在，编辑失败");
                        flg = "1";
                        break;
                    } else {
                        flg = SystemConst.RsStatus.SUCCESS;
                    }
                }
                if(flg.equals(SystemConst.RsStatus.SUCCESS)){
                    byMarketFood.setUpdTime(currentDate);
                    by121306Logic.byMarketFoodModify(byMarketFood);
                }
                return flg;
            } else {
                // 菜场详细画面编辑保存
                byMarketFood.setUpdTime(currentDate);
                by121306Logic.byMarketFoodModify(byMarketFood);
                return SystemConst.RsStatus.SUCCESS;
            }

        }
    }

}
