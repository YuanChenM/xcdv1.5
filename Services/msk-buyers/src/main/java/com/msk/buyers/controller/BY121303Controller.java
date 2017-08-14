package com.msk.buyers.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.buyers.bean.BY121303Bean;
import com.msk.buyers.bean.IBY121201RsParam;
import com.msk.buyers.logic.BY121301Logic;
import com.msk.buyers.logic.BY121302Logic;
import com.msk.buyers.logic.BY121303Logic;
import com.msk.buyers.logic.IBY121201Logic;
import com.msk.buyers.utils.BuyerTypeUtil;
import com.msk.buyers.utils.RestCommUtil;
import com.msk.common.base.BaseController;
import com.msk.common.business.constant.BuyersConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.core.entity.ByBuyerAccount;
import com.msk.core.entity.ByMarketFood;
import com.msk.core.entity.ByMarketTerminal;
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
 * Created by zhu_kai1 on 2016/5/6.
 */
@Controller
@RequestMapping("BY121303")
public class BY121303Controller extends BaseController{

    private static Logger logger = LoggerFactory.getLogger(BY121303Controller.class);
    @Autowired
    private BY121303Logic by121303Logic;

    @Autowired
    private BY121301Logic by121301Logic;

    @Autowired
    private BY121302Logic by121302Logic;

    @Autowired
    private IBY121201Logic iby121201Logic;
    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init",method = RequestMethod.POST)
    public String init(Model model) {
        logger.debug("买家列表初始化");
        model.addAttribute("superiorId", StringConst.ALL);

        //买家类型
        Map<String,String> buyerTypeRedis = BuyerTypeUtil.getInstance().getBuyerTypeMap();
        TreeMap<String,String> treeMap = new TreeMap<>();
        treeMap.putAll(buyerTypeRedis);
        List  buyerTypeList = new ArrayList(treeMap.entrySet());
        model.addAttribute("buyerTypeList",buyerTypeList);

        //买家状态
        Map<String,String> marketStatus = CodeMasterManager.findCodeMasterMap(BuyersConstant.MarketingsStatus.Type);
        TreeMap<String,String> treeMapMarket = new TreeMap<>();
        treeMapMarket.putAll(marketStatus);
        List  marketStatusList = new ArrayList(treeMapMarket.entrySet());
        model.addAttribute("marketStatusList",marketStatusList);

        //查询所有物流区
        DistrictParam districtParam = new DistrictParam();
        List<LgcsAreaBean> logisticsAreaList = RestCommUtil.getLogisticsAreaList(districtParam).getResult().getLgcsAreaList();
        model.addAttribute("logisticsAreaList", logisticsAreaList);


        return "buyers/BY121303";
    }
    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init/{message}",method = RequestMethod.POST)
    public String init(@PathVariable("message") String message,Model model) {
        logger.debug("买家列表初始化");
        model.addAttribute("superiorId", StringConst.ALL);
        model.addAttribute("message", message);
        return "buyers/BY121303";
    }

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init/{type}/{superiorId}",method = RequestMethod.POST)
    public String init(@PathVariable("type") String type, @PathVariable("superiorId") String superiorId, Model model) {
        logger.debug("买家列表初始化");
        model.addAttribute("type", type);
        model.addAttribute("superiorId", superiorId);
        if (type.equals("1")) {
            ByMarketTerminal market = by121301Logic.findMarketTerminalById(superiorId);
            model.addAttribute("market", market);
        } else if (type.equals("2")) {
            ByMarketFood market = by121302Logic.findMarketFoodById(superiorId);
            model.addAttribute("market", market);
        }
        return "buyers/BY121303";
    }

    /**
     * 分页查询数据
     *
     * @param param pageParam
     * @return 分页查询数据
     */
    @RequestMapping(value = "search/{superiorId}", method = RequestMethod.POST)
    public @ResponseBody
    PageResult<BY121303Bean> search(@PathVariable("superiorId") String superiorId, BasePageParam param) {
        logger.debug("买家列表查询");
        if (!StringConst.ALL.equals(superiorId)) {
            param.getFilterMap().put("superiorId", superiorId);
        }
        DbUtils.buildLikeCondition(param, "buyerCode", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "buyerName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "buyerAddr", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "superiorName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "lgcsAreaName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "cityName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "districtName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "marketName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "accountName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "telNo", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "storeNo", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "bossName", DbUtils.LikeMode.PARTIAL);

        DbUtils.buildLikeCondition(param, "marketingsStatus", DbUtils.LikeMode.PARTIAL);


        Map<String,String> marketingSatusMap = CodeMasterManager.findCodeMasterMap(BuyersConstant.MarketingsStatus.Type);
        String  marketingsStatusName= String.valueOf(param.getFilterMap().get("marketingsStatusName"));
        boolean exist = false;
        if(!StringUtil.isNullOrEmpty(marketingsStatusName) && marketingsStatusName != "null"){
            for(String key :marketingSatusMap.keySet()){
                if(marketingSatusMap.get(key).indexOf(marketingsStatusName) !=-1){
                    param.getFilterMap().put("marketingsStatus",key);
                    exist = true;
                    break;
                }
            }
            if(!exist)
            param.getFilterMap().put("marketingsStatus", NumberConst.IntDef.INT_N_ONE);
        }
        PageResult<BY121303Bean> beanPageResult = by121303Logic.findPage(param, BY121303Bean.class);
        for (BY121303Bean by121303Bean : beanPageResult.getData()){
            if(null != marketingSatusMap && marketingSatusMap.containsKey(by121303Bean.getMarketingsStatus())){
                by121303Bean.setMarketingsStatusName(marketingSatusMap.get(by121303Bean.getMarketingsStatus()));
            }else{
                // 防止页面上该值为null时，页面报告警
                by121303Bean.setMarketingsStatusName("");
            }
        }
        return beanPageResult;
    }

    /**
     * 注册新买家
     * @param telNo
     * @param model
     * @return
     */
    @RequestMapping(value = "register/{telNo}")
    public String buyerRegister(@PathVariable("telNo") String telNo,Model model){
        IBY121201RsParam inParam = new IBY121201RsParam();
        inParam.setTelNo(telNo);
        super.setCommonParam(inParam);
        Date currentDate = DateTimeUtil.getCustomerDate();
        inParam.setCrtTime(currentDate);
        inParam.setUpdTime(currentDate);
        inParam.setActTime(currentDate);
        String message = iby121201Logic.buyerRegister(inParam);
        ByBuyerAccount buyerAccount = null;
        if(StringUtil.isNullOrEmpty(message)){
            buyerAccount = iby121201Logic.getBuyerAccount(inParam);
            return super.forward("/BY121307/init/"+buyerAccount.getBuyerId()+"");
        }else{
            /** Modif for Bug#Bug #2261 at 2016/09/14 by zhao_chen Start*/
            if(message.indexOf("BJ")>=0){
              String messagInfo  [] = message.split("/");
                String buyerId = messagInfo[1];
                return super.forward("/BY121304/init/"+buyerId+"");
                /** Modif for Bug#Bug #2261 at 2016/09/14 by zhao_chen end*/
            }else{
                return super.forward("/BY121303/init/"+message);
            }

        }
    }

    /**
     * 删除买家信息
     * @param buyerId
     * @return
     */
    @RequestMapping(value = "delete/{buyerId}",method = RequestMethod.POST)
    public String buyerInfoDelete(@PathVariable("buyerId") String buyerId){
        BaseParam param = new BaseParam();
        super.setCommonParam(param);
        Date currentDate = DateTimeUtil.getCustomerDate();
        param.setUpdTime(currentDate);
        param.setCrtTime(currentDate);
        param.setActTime(currentDate);
        param.setFilter("buyerId",buyerId);

        by121303Logic.deleteBuyerInfo(param);
        return "buyers/BY121303";
    }

}
