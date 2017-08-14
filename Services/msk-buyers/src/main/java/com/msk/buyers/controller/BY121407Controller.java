package com.msk.buyers.controller;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.buyers.bean.BY121407Param;
import com.msk.buyers.logic.BY121407Logic;
import com.msk.buyers.utils.RestCommUtil;
import com.msk.common.base.BaseController;
import com.msk.common.business.constant.BuyersConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.core.entity.ByMarketFoodBasic;
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

import java.util.*;

/**
 *菜场定性定级列表
 * Created by tao_zhifa on 2016/7/8.
 */
@Controller
@RequestMapping("BY121407")
public class BY121407Controller extends BaseController{
    /**
     * logger
     */

    private static Logger logger = LoggerFactory.getLogger(BY121407Controller.class);
    @Autowired
    private BY121407Logic by121407Logic;

    @RequestMapping(value = "init" ,method = RequestMethod.POST)
    public String init(Model model){
        logger.debug("菜场定性定级列表初始化");
        //查询所有物流区
        DistrictParam districtParam = new DistrictParam();
        List<LgcsAreaBean> logisticsAreaList = RestCommUtil.getLogisticsAreaList(districtParam).getResult().getLgcsAreaList();
        model.addAttribute("logisticsAreaList", logisticsAreaList);

        //市场类型
        Map<String,String> marketType = CodeMasterManager.findCodeMasterMap(BuyersConstant.MarketType.TYPE);
        TreeMap<String,String> treeMapMarket = new TreeMap<>();
        treeMapMarket.putAll(marketType);
        List  marketTypeList = new ArrayList(treeMapMarket.entrySet());
        model.addAttribute("marketTypeList",marketTypeList);

        //买家状态
        Map<String,String> marketApproveStatus = CodeMasterManager.findCodeMasterMap(BuyersConstant.MarketApproveStatus.TYPE);
        TreeMap<String,String> treeMap = new TreeMap<>();
        treeMap.putAll(marketApproveStatus);
        List  marketApproveStatusList = new ArrayList(treeMap.entrySet());
        model.addAttribute("marketApproveStatusList",marketApproveStatusList);


        return "buyers/BY121407";
    }

    @RequestMapping(value = "search",method = RequestMethod.POST)
    public @ResponseBody
    PageResult<ByMarketFoodBasic> search(BY121407Param param){
        logger.debug("菜场定性定级列表查询");
        DbUtils.buildLikeCondition(param, "lgcsAreaName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "cityName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "marketName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "marketAddr", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "marketCode", DbUtils.LikeMode.PARTIAL);
        String marketStatus = StringUtil.toSafeString(param.getFilterMap().get("marketStatus"));
        if (!StringUtil.isNullOrEmpty(marketStatus)){
            String[] marketStatuss = marketStatus.split(",");
            param.getFilterMap().put("marketStatuss", marketStatuss);
        }
        String marketNature = StringUtil.toSafeString(param.getFilterMap().get("marketNature"));
        if (!StringUtil.isNullOrEmpty(marketNature)){
            String[] marketNatures = marketNature.split(",");
            param.getFilterMap().put("marketNatures", marketNatures);
        }

        PageResult<ByMarketFoodBasic> result = by121407Logic.findPage(param, ByMarketFoodBasic.class);

        return by121407Logic.findPage(param,ByMarketFoodBasic.class);
    }

    @RequestMapping(value = "delete/{marketId}",method = RequestMethod.POST)
    public String  delete(@PathVariable("marketId")String marketId){
        logger.debug("菜场定性定级列表删除");
        /*BY121407Param param = new BY121407Param();*/

        ByMarketFoodBasic entity = new  ByMarketFoodBasic();
        super.setCommonParam(entity);
        Date currentDate = DateTimeUtil.getCustomerDate();
        entity.setUpdTime(currentDate);

        entity.setMarketId(marketId);
        int result = by121407Logic.deleteDataBase(entity);
        if (result == NumberConst.IntDef.INT_ZERO) {
            throw new BusinessException("删除失败,未找到该批发市场!");
        }
        return "buyers/BY121407";
    }
}
