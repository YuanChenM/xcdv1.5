package com.msk.buyers.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.buyers.bean.BY121409Bean;
import com.msk.buyers.logic.BY121409Logic;
import com.msk.buyers.utils.RestCommUtil;
import com.msk.common.base.BaseController;
import com.msk.common.business.constant.BuyersConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.consts.BuyersConst;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.ByMarketFoodBasic;
import com.msk.core.entity.ByMarketFoodByInfo;
import com.msk.district.bean.CityBean;
import com.msk.district.bean.DistrictBean;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.LgcsAreaBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by zhou_yajun on 2016/7/13.
 */
@Controller
@RequestMapping("BY121409")
public class BY121409Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121409Controller.class);

    @Autowired
    private BY121409Logic by121409Logic;
    @Autowired
    private CommonLogic commonLogic;

    /**
     * 菜场新增编辑画面
     * @param marketId
     * @return
     */
    @RequestMapping(value = "init/{marketId}")
    public String init(@PathVariable("marketId") String marketId,Model model){
        logger.info("菜场新增编辑初始化");

        model.addAttribute("marketId",marketId);
        //根据传参菜场ID获取菜场的基本信息
        BaseParam param = new BaseParam();
        param.setFilter("marketId",marketId);
        ByMarketFoodBasic marketFoodBasic = by121409Logic.findOne(param);
//        model.addAttribute("marketFoodBasic",marketFoodBasic);

        //从redis中获取基础数据
        //辐射范围
        Map<String,String> radistionRange = CodeMasterManager.findCodeMasterMap(BuyersConstant.FoodRadiationRange.TYPE);
        model.addAttribute("radistionRange",radistionRange);
        //市场买家平均定价标准
        Map<String,String> byAveragePriceType = CodeMasterManager.findCodeMasterMap(BuyersConstant.AverageMarketPrice.TYPE);
        model.addAttribute("byAveragePriceType",byAveragePriceType);
        //菜场所属地段类型
        Map<String,String> sectionType = CodeMasterManager.findCodeMasterMap(BuyersConstant.MarketSectionType.TYPE);
        model.addAttribute("sectionType",sectionType);
        //菜场等级
        Map<String,String> marketAssessNature = CodeMasterManager.findCodeMasterMap(BuyersConstant.MarketType.TYPE);
        model.addAttribute("marketAssessNature",marketAssessNature);
        //市场调研阶段
        //Modify for Bug #2513 at 2016/09/07 by zhou_yajun
        Map<String,String> researchPhase = new HashMap<>();
        researchPhase.put(BuyersConst.FoodMarketResearchPhase.MARKET_NET,BuyersConst.FoodMarketResearchPhaseName.MARKET_NET_NAME);
        researchPhase.put(BuyersConst.FoodMarketResearchPhase.MARKET_RESEARCH,BuyersConst.FoodMarketResearchPhaseName.MARKET_RESEARCH_NAME);
        researchPhase.put(BuyersConst.FoodMarketResearchPhase.MARKET_AUDIT,BuyersConst.FoodMarketResearchPhaseName.MARKET_AUDIT_NAME);
        //Modify for Bug #2513 at 2016/09/07 by zhou_yajun
        model.addAttribute("researchPhase",researchPhase);

        //从物流区接口中获取物流区基础数据
        DistrictParam cityParam = new DistrictParam();
        DistrictParam districtParam = new DistrictParam();
        //获取所有的物流区信息
        List<LgcsAreaBean> logisticsAreaList = RestCommUtil.getLogisticsAreaList(cityParam).getResult().getLgcsAreaList();

        if(null != marketFoodBasic){
            //如果是编辑模式,获取菜场所在物流区下的所有城市
            cityParam.setLgcsAreaCode(marketFoodBasic.getLgcsAreaCode());
            cityParam.setIsLoadCity(0);
            cityParam.setFlag(0);
            //获取菜场所在城市下的所有区县
            districtParam.setCityCode(marketFoodBasic.getCityCode());
        }else{
            //如果是新增模式,获取基础物流区下第一条数据下的所有城市
            cityParam.setLgcsAreaCode(logisticsAreaList.get(0).getLgcsAreaCode());
            cityParam.setIsLoadCity(0);
            cityParam.setFlag(0);

        }
        List<CityBean> cityList = RestCommUtil.getCityList(cityParam).getResult().getCityList();
        if(null == marketFoodBasic){
            districtParam.setCityCode(cityList.get(0).getCityCode());
            //Modify for Bug #2736 start at 2016/09/07 by zhou_yajun
            marketFoodBasic = new ByMarketFoodBasic();
            marketFoodBasic.setMerchantTotalNo(0);
            marketFoodBasic.setTargetBuyer(0);
            marketFoodBasic.setTargetAnnualTurnover(new BigDecimal("0.00"));
            marketFoodBasic.setNtargetBuyer(0);
            marketFoodBasic.setNtargetAnnualTurnover(new BigDecimal("0.00"));
            //Modify for Bug #2736 end at 2016/09/07 by zhou_yajun
        }
        List<DistrictBean> districtList = RestCommUtil.getDistrictList(districtParam).getResult().getDistrictList();
        model.addAttribute("marketFoodBasic",marketFoodBasic);

        model.addAttribute("logisticsAreaList",logisticsAreaList);
        model.addAttribute("cityList",cityList);
        model.addAttribute("districtList",districtList);

        return "buyers/BY121409";
    }

    /**
     * 分页查询数据
     *
     * @param param pageParam
     * @return 分页查询数据
     */
    @RequestMapping(value = "search",method = RequestMethod.POST)
    public @ResponseBody
    PageResult<ByMarketFoodByInfo> search(String marketId,BasePageParam param) {
        logger.debug("买家员工信息查询");

        param.getFilterMap().put("marketId", marketId);
        DbUtils.buildLikeCondition(param, "merchantName", DbUtils.LikeMode.PARTIAL);

        String merchantType = StringUtil.toSafeString(param.getFilterMap().get("merchantType"));
        if (!StringUtil.isNullOrEmpty(merchantType)){
            String[] merchantTypes = merchantType.split(",");
            param.getFilterMap().put("merchantTypes", merchantTypes);
        }
        PageResult<ByMarketFoodByInfo> result = by121409Logic.findPage(param, ByMarketFoodByInfo.class);

        return result;
    }

    /**
     * 物流区变更，获取城市下拉框数据
     * @param lgcsAreaCode
     * @return
     */
    @RequestMapping(value = "lgcsAreaChange/{lgcsAreaCode}",
            method = RequestMethod.POST)
    public @ResponseBody List<CityBean> findCityList(@PathVariable("lgcsAreaCode") String lgcsAreaCode){
        List<CityBean> cityList = null;
        if(!StringUtil.isNullOrEmpty(lgcsAreaCode)){
            DistrictParam districtParam = new DistrictParam();
            districtParam.setLgcsAreaCode(lgcsAreaCode);
            districtParam.setIsLoadCity(0);
            districtParam.setFlag(0);
            cityList =  RestCommUtil.getCityList(districtParam).getResult().getCityList();
        }
        return cityList;
    }
    /**
     * 城市变更，获取区县下拉框数据
     * @param cityCode
     * @return
     */
    @RequestMapping(value = "cityChange/{cityCode}",
            method = RequestMethod.POST)
    public @ResponseBody List<DistrictBean> findDistrictList(@PathVariable("cityCode") String cityCode){
        List<DistrictBean> districtList = null;
        if(!StringUtil.isNullOrEmpty(cityCode)){
            DistrictParam districtParam = new DistrictParam();
            districtParam.setCityCode(cityCode);
            districtList = RestCommUtil.getDistrictList(districtParam).getResult().getDistrictList();
        }
        return districtList;
    }

    /**
     * 菜场新增编缉保存
     * @return
     */
    @RequestMapping(value = "save",
            method = RequestMethod.POST)
    public @ResponseBody BY121409Bean save(BY121409Bean by121409Bean){
        //菜场ID
        String marketId = by121409Bean.getMarketId();

        setTypeName(by121409Bean,BuyersConstant.FoodRadiationRange.TYPE);
        setTypeName(by121409Bean,BuyersConstant.AverageMarketPrice.TYPE);
        setTypeName(by121409Bean,BuyersConstant.MarketSectionType.TYPE);
        setTypeName(by121409Bean,BuyersConstant.MarketType.TYPE);
        //Modify for Bug #2513 at 2016/09/07 by zhou_yajun
        //setTypeName(by121409Bean,BuyersConstant.MarketResearchPhase.TYPE);
        if(BuyersConst.FoodMarketResearchPhase.MARKET_NET.equals(by121409Bean.getResearchPhase())){
            by121409Bean.setResearchPhaseName(BuyersConst.FoodMarketResearchPhaseName.MARKET_NET_NAME);
        }else if(BuyersConst.FoodMarketResearchPhase.MARKET_RESEARCH.equals(by121409Bean.getResearchPhase())){
            by121409Bean.setResearchPhaseName(BuyersConst.FoodMarketResearchPhaseName.MARKET_RESEARCH_NAME);
        }else{
            by121409Bean.setResearchPhaseName(BuyersConst.FoodMarketResearchPhaseName.MARKET_AUDIT_NAME);
        }
        //Modify for Bug #2513 at 2016/09/07 by zhou_yajun

        super.setCommonParam(by121409Bean);
        Date currentDate = DateTimeUtil.getCustomerDate();
        by121409Bean.setCrtTime(currentDate);
        by121409Bean.setUpdTime(currentDate);
        by121409Bean.setActTime(currentDate);

        int resultCount;
        //新增模式
        BY121409Bean returnObject = new BY121409Bean();
        if("add".equals(marketId)){
            //如果输入的名称在db中已存在,则不允许输入
            String message = by121409Logic.findMarketByName(by121409Bean);
            if(null != message){
                returnObject.setErrorMessage(message);
                return returnObject;
            }
            marketId = UUID.randomUUID().toString();
            Long maxId = commonLogic.maxId("by_market_food_basic","ID");
            by121409Bean.setMarketId(marketId);
            by121409Bean.setId(maxId);
            resultCount = by121409Logic.save(by121409Bean);
            if(resultCount == NumberConst.IntDef.INT_ONE){
                returnObject.setMarketId(marketId);
                returnObject.setResearchPhase(by121409Bean.getResearchPhase());
                return returnObject;
            }
        }else{
            return by121409Logic.foodBasicModify(by121409Bean,returnObject);
        }
        throw new BusinessException("编辑失败,未找到该菜场!");
    }

    /**
     * 通过redis基础数据设置Name
     * @param by121409Bean
     * @param type
     */
    public void setTypeName(BY121409Bean by121409Bean,String type){
        Map<String,String> typeMap = CodeMasterManager.findCodeMasterMap(type);
        for(String key : typeMap.keySet()){
            if(type.equals(BuyersConstant.FoodRadiationRange.TYPE)){
                if(key.equals(by121409Bean.getRadiationRange())){
                    by121409Bean.setRadiationRangeTypeName(typeMap.get(key));
                }
            }else if(type.equals(BuyersConstant.AverageMarketPrice.TYPE)){
                if(key.equals(by121409Bean.getByAveragePriceType())){
                    by121409Bean.setByAveragePriceTypeName(typeMap.get(key));
                }
            }else if(type.equals(BuyersConstant.MarketSectionType.TYPE)){
                if(key.equals(by121409Bean.getSectionType())){
                    by121409Bean.setSectionTypeName(typeMap.get(key));
                }
            }else if(type.equals(BuyersConstant.MarketType.TYPE)){
                if(key.equals(by121409Bean.getMarketAssessNature())){
                    by121409Bean.setMarketAssessNatureName(typeMap.get(key));
                }
            }else if(type.equals(BuyersConstant.MarketResearchPhase.TYPE)){
                if(key.equals(by121409Bean.getResearchPhase())){
                    by121409Bean.setResearchPhaseName(typeMap.get(key));
                }
            }
        }
    }
}
