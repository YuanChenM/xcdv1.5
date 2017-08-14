package com.msk.buyers.controller;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.buyers.bean.BY121401Param;
import com.msk.buyers.bean.BY121413Bean;
import com.msk.buyers.logic.BY121401Logic;
import com.msk.buyers.utils.RestCommUtil;
import com.msk.common.base.BaseController;
import com.msk.common.business.constant.BuyersConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.core.entity.ByMarketTerminalBasic;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.LgcsAreaBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;


/**
 * 批发市场定性定级列表 Controller
 * Created by tao_zhifa on 2016/7/7.
 */
@Controller
@RequestMapping("BY121401")
public class BY121401Controller extends BaseController{

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121401Controller.class);

    @Autowired
    private BY121401Logic by121401Logic;

    @RequestMapping(value = "init" ,method = RequestMethod.POST)
    public String init(Model model){
        logger.debug("批发市场定性定级列表初始化");

        //查询所有物流区
        DistrictParam districtParam = new DistrictParam();
        List<LgcsAreaBean> logisticsAreaList = RestCommUtil.getLogisticsAreaList(districtParam).getResult().getLgcsAreaList();
        model.addAttribute("logisticsAreaList", logisticsAreaList);

        //买家状态
        Map<String,String> marketApproveStatus = CodeMasterManager.findCodeMasterMap(BuyersConstant.MarketApproveStatus.TYPE);
        TreeMap<String,String> treeMapMarket = new TreeMap<>();
        treeMapMarket.putAll(marketApproveStatus);
        List  marketApproveStatusList = new ArrayList(treeMapMarket.entrySet());
        model.addAttribute("marketApproveStatusList",marketApproveStatusList);


        return "buyers/BY121401";
    }

    @RequestMapping(value = "search",method = RequestMethod.POST)
    public @ResponseBody PageResult<ByMarketTerminalBasic> search(BY121401Param param){
        logger.debug("批发市场定性定级列表查询");
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
        PageResult<ByMarketTerminalBasic> result = by121401Logic.findPage(param, ByMarketTerminalBasic.class);
        return result;
    }

    @RequestMapping(value = "delete/{marketId}",method = RequestMethod.POST)
    public String  delete(@PathVariable("marketId")String marketId){
        logger.debug("批发市场定性定级列表删除");
        BY121401Param param = new BY121401Param();
        Date currentDate = DateTimeUtil.getCustomerDate();
        param.setMarketId(marketId);
        super.setCommonParam(param);
        param.setUpdTime(currentDate);
        ByMarketTerminalBasic entity = new ByMarketTerminalBasic();
        super.setCommonParam(entity);
        entity.setMarketId(marketId);
        entity.setUpdTime(currentDate);
        int result = by121401Logic.deleteDataBase(entity);
        if (result == NumberConst.IntDef.INT_ZERO) {
            throw new BusinessException("删除失败,未找到该批发市场!");
        }else{
            by121401Logic.deleteStoreByMarketId(entity);
            List<BY121413Bean> storeIdList=  by121401Logic.findStoreByMarketId(param);
            if(!CollectionUtils.isEmpty(storeIdList)){
                param.getFilterMap().put("storeIdList",storeIdList);
                by121401Logic.deleteSalePd(param);
            }
        }
        return "buyers/BY121401";
    }
}
