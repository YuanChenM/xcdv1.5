package com.msk.buyers.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.buyers.bean.BY121406Bean;
import com.msk.buyers.logic.BY121406Logic;
import com.msk.buyers.utils.RestCommUtil;
import com.msk.common.base.BaseController;
import com.msk.common.business.constant.BuyersConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.core.entity.BaseEntity;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.LgcsAreaBean;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;


/**
 * 批发市场定性定级各阶段一览表 Controller
 * Created by yuan_zhifei on 2016/7/13.
 */
@Controller
@RequestMapping("BY121406")
public class BY121406Controller extends BaseController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121406Controller.class);

    @Autowired
    private BY121406Logic by121406Logic;

    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(Model model) {
        logger.debug("批发市场定性定级各阶段一览表初始化");
        //买家状态
        Map<String,String> MarketResearchPhase = CodeMasterManager.findCodeMasterMap(BuyersConstant.MarketResearchPhase.TYPE);
        TreeMap<String,String> treeMapMarket = new TreeMap<>();
        treeMapMarket.putAll(MarketResearchPhase);
        List  researchPhaseList = new ArrayList(treeMapMarket.entrySet());
        model.addAttribute("researchPhaseList",researchPhaseList);

        //查询所有物流区
        DistrictParam districtParam = new DistrictParam();
        List<LgcsAreaBean> logisticsAreaList = RestCommUtil.getLogisticsAreaList(districtParam).getResult().getLgcsAreaList();
        model.addAttribute("logisticsAreaList", logisticsAreaList);
        return "buyers/BY121406";
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<BY121406Bean> search(BasePageParam param) {
        logger.debug("批发市场一览查询");
        DbUtils.buildLikeCondition(param, "lgcsAreaName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "marketName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "marketAddr", DbUtils.LikeMode.PARTIAL);

        String researchPhase = StringUtil.toSafeString(param.getFilterMap().get("researchPhase"));
        if (!StringUtil.isNullOrEmpty(researchPhase)){
            String[] researchPhases = researchPhase.split(",");
            param.getFilterMap().put("researchPhases", researchPhases);
        }

        PageResult<BY121406Bean> list= this.by121406Logic.findPage(param, BY121406Bean.class);
        return list;
    }
    /**
     * 批发市场定性定级保存新增操作
     * @return
     */
    @RequestMapping(value = "marketNatureSave",method = RequestMethod.POST)
    public @ResponseBody void save(@RequestParam String jsonStr){
        if(!StringUtil.isNullOrEmpty(jsonStr)){
            Map<String, BY121406Bean> map = JSON.parseObject(jsonStr, new TypeReference<Map<String, BY121406Bean>>() {
            });
            List<BY121406Bean> paramList = new ArrayList(map.values());
            if(CollectionUtils.isNotEmpty(paramList)){
                BaseEntity entity = new BaseEntity();
                super.setCommonParam(entity);
                Date currentDate = DateTimeUtil.getCustomerDate();
                entity.setCrtTime(currentDate);
                entity.setUpdTime(currentDate);
                entity.setActTime(currentDate);

                by121406Logic.netMarketSave(paramList,entity);
            }else{
                throw new BusinessException("编辑失败,请输入正确的数据信息!");
            }
        }
    }
}

