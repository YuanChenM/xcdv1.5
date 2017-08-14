package com.msk.buyers.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.msk.buyers.logic.BY121305Logic;
import com.msk.buyers.utils.RestCommUtil;
import com.msk.common.base.BaseController;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.consts.BuyersConst;
import com.msk.core.entity.ByMarketTerminal;
import com.msk.core.entity.CommConstant;
import com.msk.district.bean.CityBean;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.LgcsAreaBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 批发市场详细画面编辑
 *
 * @author zhou_ling
 */
@Controller
@RequestMapping("BY121305")
public class BY121305Controller extends BaseController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(BY121305Controller.class);

    @Autowired
    private BY121305Logic by121305Logic;

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init",method = RequestMethod.POST)
    public String init(@RequestParam(value = "terMarketId", required = false) String terMarketId,
                       @RequestParam(value = "type", required = true) String type, Model model) {
        logger.debug("批发市场详细画面编辑");
        ByMarketTerminal byMarketTerminal = new ByMarketTerminal();
        if("edit".equals(type)){
            // 批发市场修改
            byMarketTerminal = by121305Logic.findMarketTerminal(terMarketId);
        }
        model.addAttribute("terMarketId",terMarketId);
        model.addAttribute("byMarketTerminal",byMarketTerminal);
        DistrictParam  districtParam = new DistrictParam();
        //查询所有物流区
        List<LgcsAreaBean> logisticsAreaList = RestCommUtil.getLogisticsAreaList(districtParam).getResult().getLgcsAreaList();
        //查询指定物流区中的城市
        List<CityBean> cityList = new ArrayList<CityBean>();
        if (!StringUtil.isNullOrEmpty(byMarketTerminal.getLgcsAreaCode())){
            districtParam.setLgcsAreaCode(byMarketTerminal.getLgcsAreaCode());
            districtParam.setFlag(0);
            cityList = RestCommUtil.getCityList(districtParam).getResult().getCityList();
        }
        //批发市场级别
        Map<String,String> marketLevelMap = CodeMasterManager.findCodeMasterMap(BuyersConst.MarketLevel.TYPE);
        TreeMap<String,String> treeMap = new TreeMap<>();
        treeMap.putAll(marketLevelMap);
        List <CommConstant> marketLevelList = new ArrayList(treeMap.entrySet());
        model.addAttribute("logisticsAreaList",logisticsAreaList);
        model.addAttribute("cityList",cityList);
        model.addAttribute("marketLevelList", marketLevelList);

        return "buyers/BY121305";
    }

    /**
     * 批发市场详细画面编辑保存
     * @param byMarketTerminal byMarketTerminal
     * @return
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public @ResponseBody
    String byMarketTerminalSave(ByMarketTerminal byMarketTerminal){
        super.setCommonParam(byMarketTerminal);
        Date currentDate = DateTimeUtil.getCustomerDate();
        String terMarketId = byMarketTerminal.getTerMarketId();
        String marketCode=byMarketTerminal.getMarketCode();
        String flg="";

        //通过 marketCode 查询市场id
        BaseParam param =new BaseParam();
        param.getFilterMap().put("marketCode",marketCode);
        List<ByMarketTerminal> marketList= by121305Logic.getTerMarketId(param);

        if(StringUtil.isEmpty(terMarketId)){
            if(CollectionUtils.isEmpty(marketList)){
                terMarketId = UUID.randomUUID().toString();
                byMarketTerminal.setTerMarketId(terMarketId);
                // 批发市场详细画面新增保存
                byMarketTerminal.setUpdTime(currentDate);
                byMarketTerminal.setActTime(currentDate);
                byMarketTerminal.setCrtTime(currentDate);
                by121305Logic.byMarketTerminalAdd(byMarketTerminal);
                return SystemConst.RsStatus.SUCCESS;
            }else{
                logger.info("批发市场编码已存在，新增失败");
                flg="1";
                return flg;
            }
        } else {
            if(!CollectionUtils.isEmpty(marketList)){
                for(ByMarketTerminal market:marketList){
                    if(!market.getTerMarketId().equals(terMarketId)){
                        //编辑，编码存在，且不等于自身数据（说明不止1条）
                        logger.info("批发市场编码已存在，编辑失败");
                        flg="1";
                        break;
                    }else{
                        flg=SystemConst.RsStatus.SUCCESS;
                    }
                }
                if(flg.equals(SystemConst.RsStatus.SUCCESS)){
                    // 批发市场详细画面编辑保存
                    byMarketTerminal.setUpdTime(currentDate);
                    by121305Logic.byMarketTerminalModify(byMarketTerminal);
                }
                return flg;
            }else{
                // 批发市场详细画面编辑保存
                byMarketTerminal.setUpdTime(currentDate);
                by121305Logic.byMarketTerminalModify(byMarketTerminal);
                return SystemConst.RsStatus.SUCCESS;
            }
        }

    }
}
