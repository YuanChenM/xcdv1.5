package com.msk.buyers.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.buyers.bean.BY121403Bean;
import com.msk.buyers.bean.BY121403TotalBean;
import com.msk.buyers.logic.BY121403Logic;
import com.msk.buyers.utils.RestCommUtil;
import com.msk.common.base.BaseController;
import com.msk.common.business.constant.BuyersConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.BaseEntity;
import com.msk.core.entity.ByMarketTerminalBasic;
import com.msk.core.entity.ByMarketTerminalByInfo;
import com.msk.district.bean.CityBean;
import com.msk.district.bean.DistrictParam;
import org.apache.commons.collections.CollectionUtils;
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
 * Created by zhou_yajun on 2016/7/8.
 */
@Controller
@RequestMapping("BY121403")
public class BY121403Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121403Controller.class);

    @Autowired
    private BY121403Logic by121403Logic;
    @Autowired
    private CommonLogic commonLogic;

    /**
     * 批发市场新增编辑画面
     * @param marketId
     * @return
     */
    @RequestMapping(value = "init/{marketId}")
    public String init(@PathVariable("marketId") String marketId,Model model){
        logger.info("批发市场新增编缉初始化");

        // 获取批发市场ID
        model.addAttribute("marketId",marketId);
        //根据传参批发市场ID获取批发市场的基本信息
        BaseParam param = new BaseParam();
        param.setFilter("marketId",marketId);
        ByMarketTerminalBasic marketTerminalBasic = by121403Logic.findOne(param);
        model.addAttribute("marketTerminalBasic",marketTerminalBasic);
        //市场调研阶段
        Map<String,String> researchPhase = CodeMasterManager.findCodeMasterMap(BuyersConstant.MarketResearchPhase.TYPE);
        model.addAttribute("researchPhase",researchPhase);
        // 校验批发市场买家信息表 是否存在数据  1:新增  2:修改
        String synchButton = "";
        String type = "0";
        if(!"add".equals(marketId) && !StringUtil.isNullOrEmpty(marketTerminalBasic.getMarketId())){
            //编辑状态 修改先判定 先判断是否批发市场买家信息表 是否有数据
            // 当基础表为0表示数据库必定为最新数据信息
            if("0".equals(marketTerminalBasic.getIsMerchantNew())){
                synchButton = "0";
            }else{
                setCommonParam(marketTerminalBasic);
                Date currentDate = DateTimeUtil.getCustomerDate();
                marketTerminalBasic.setMarketId(marketId);
                //modify at Bug #2691 by tao_zhifa start
                marketTerminalBasic.setCrtTime(currentDate);
                marketTerminalBasic.setActTime(currentDate);
                marketTerminalBasic.setUpdTime(currentDate);
                //modify at Bug #2691 by tao_zhifa end
                Map<String,String> map = by121403Logic.doWorkMarketInfo(marketTerminalBasic);
                synchButton = map.get("res");
                if(map.get("maxClassBuyerType") != null && map.get("maxClassBuyerType").length() >0){
                    marketTerminalBasic.setMaxClassBuyerType(map.get("maxClassBuyerType"));
                }
                if(map.get("maxClassBuyerNum") != null){
                    marketTerminalBasic.setMaxClassBuyerNum(Integer.parseInt(map.get("maxClassBuyerNum")));
                }
                if(map.get("targetBuyer") != null){
                    marketTerminalBasic.setTargetBuyer(Integer.parseInt(map.get("targetBuyer")));
                }
            }

            model.addAttribute("type",type);
        }else {
            type = "1";
            model.addAttribute("type",type);
        }
        // 判断是否需要展示同步按钮
        model.addAttribute("synchButton",synchButton);

        return "buyers/BY121403";
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
     * 批发市场新增编缉保存
     * @return
     */
    @RequestMapping(value = "save/{type}",
            method = RequestMethod.POST)
    public @ResponseBody BY121403Bean save(@PathVariable("type")String type,BY121403Bean by121403Bean){
        //批发市场ID
        String marketId = by121403Bean.getMarketId();
        setCommonFiled(by121403Bean);
        //setTypeName(by121403Bean,BuyersConstant.RadiationRange.TYPE);
        //setTypeName(by121403Bean,BuyersConstant.FaceBuyerType.TYPE);
        //setTypeName(by121403Bean,BuyersConstant.MarketLevel.TYPE);
        setTypeName(by121403Bean,BuyersConstant.MarketResearchPhase.TYPE);

        String  existenceFlg =  by121403Bean.getExistenceFlg();

        int resultCount;
        //新增模式
        BY121403Bean returnObject = new BY121403Bean();
        if("1".equals(type)){
            returnObject.setActionType("add");
            //如果输入的名称在db中已存在,则不允许输入
//            String message = by121403Logic.findMarketByName(by121403Bean);
//            if(null != message){
//                returnObject.setErrorMessage(message);
//                return returnObject;
//            }
            //如果输入的名称验证通过,继续进行
//            marketId = UUID.randomUUID().toString();


//Add for Bug #2464号 at 2016/09/06 by zhao_chen Start
            if(existenceFlg.equals("1")){
                by121403Logic.deleteMarketStatus(by121403Bean);
            }

            Long maxId = commonLogic.maxId("by_market_terminal_basic","ID");
            by121403Bean.setMarketId(marketId);
            by121403Bean.setId(maxId);
            by121403Bean.setMarketStatus("0");
            resultCount = by121403Logic.save(by121403Bean);
            if(resultCount == NumberConst.IntDef.INT_ONE){
                returnObject.setMarketId(marketId);
                returnObject.setResearchPhase(by121403Bean.getResearchPhase());
                return returnObject;
            }
        }else{
            return by121403Logic.marketBasicModify(by121403Bean,returnObject);
        }
        throw new BusinessException("编辑失败,未找到该批发市场!");
    }

    /**
     * 设置共通字段
     * @param entity
     */
    public void setCommonFiled(BaseEntity entity){
        super.setCommonParam(entity);
        Date currentDate = DateTimeUtil.getCustomerDate();
        entity.setCrtTime(currentDate);
        entity.setUpdTime(currentDate);
        entity.setActTime(currentDate);
    }

    /**
     * 验证是批发市场是否已经存在
     * @param by121403Bean
     * @return
     */
    @RequestMapping(value = "regExistence", method = RequestMethod.POST)
    public @ResponseBody
    String regExistence (BY121403Bean by121403Bean){
        logger.info("验证是否存在");
        int regData = by121403Logic.regExistence(by121403Bean);
        String returnFlg;
        if (regData > NumberConst.IntDef.INT_ZERO) {
            returnFlg = SystemConst.RsStatus.SUCCESS;
        } else {
            returnFlg =SystemConst.RsStatus.FAIL;
        }
        return returnFlg;
    }
//Add for Bug #2464号 at 2016/09/06 by zhao_chen End

    /**
     * 分页查询数据
     *
     * @param param pageParam
     *     isTargetMerchant:是否目标商户 0是,1不是
     * @return 分页查询数据
     */
    @RequestMapping(value = "search",method = RequestMethod.POST)
    public @ResponseBody
    PageResult<BY121403TotalBean> search(String isTargetMerchant,String marketId,BasePageParam param,Model model) {
        logger.debug("买家员工信息查询");

        param.getFilterMap().put("marketId", marketId);
        param.getFilterMap().put("isTargetMerchant", isTargetMerchant);
        DbUtils.buildLikeCondition(param, "merchantName", DbUtils.LikeMode.PARTIAL);

        String merchantType = StringUtil.toSafeString(param.getFilterMap().get("merchantType"));
        if (!StringUtil.isNullOrEmpty(merchantType)){
            String[] merchantTypes = merchantType.split(",");
            param.getFilterMap().put("merchantTypes", merchantTypes);
        }
        PageResult<BY121403TotalBean> result = by121403Logic.findPage(param, BY121403TotalBean.class);

        BY121403TotalBean findPageTotal = by121403Logic.findPageTotal(param);
        if(result.getData().size() > 0){
            result.getData().get(0).setTotalAmount(findPageTotal.getTotalAmount());
            result.getData().get(0).setTotalNumber(findPageTotal.getTotalNumber());
            result.getData().get(0).setCurrentAmount(findPageTotal.getCurrentAmount());
            result.getData().get(0).setCurrentNumber(findPageTotal.getCurrentNumber());
        }
        return result;
    }

    /**
     * 批发市场新增编缉保存
     * @return
     */
    @RequestMapping(value = "marketSave",method = RequestMethod.POST)
    public @ResponseBody void save(Model model,String jsonStr){
        BaseParam baseParam = new BaseParam();
        super.setCommonParam(baseParam);
        Map<String, ByMarketTerminalByInfo> map = JSON.parseObject(jsonStr, new TypeReference<Map<String, ByMarketTerminalByInfo>>() {});
        List<ByMarketTerminalByInfo> paramList = new ArrayList(map.values());
        if (CollectionUtils.isNotEmpty(paramList)) {
            by121403Logic.batchMarketInfosDo(paramList,this.getLoginUser().getEmplId());
        }else{
            throw new BusinessException("编辑失败,请输入正确的数据信息!");
        }
    }
    /**
     * 信息同步
     * @param marketId
     * @return
     */
    @RequestMapping(value = "synchMarket/{marketId}",
            method = RequestMethod.POST)
    public @ResponseBody Map<String,String> synchMarketInfo(@PathVariable("marketId") String marketId){
        Map<String,String> map = new HashMap<String,String>();
        if(!StringUtil.isNullOrEmpty(marketId)){
            ByMarketTerminalBasic marketTerminalBasic = new ByMarketTerminalBasic();
            super.setCommonParam(marketTerminalBasic);
            Date currentDate = DateTimeUtil.getCustomerDate();
            marketTerminalBasic.setMarketId(marketId);
            marketTerminalBasic.setUpdTime(currentDate);
            setCommonParam(marketTerminalBasic);
            map = by121403Logic.manualSynchMarketInfo(marketTerminalBasic);
        }else{
            throw new BusinessException("数据统计失败!");
        }
        return map;
    }

    /**
     * @param model
     * @return
     */
    @RequestMapping(value = "marketInfoChooseInit" ,method = RequestMethod.POST)
    public String marketChooseInit(Model model){
        return "buyers/BY12140301";
    }

    /**
     * 通过redis基础数据设置Name
     * @param by121403Bean
     * @param type
     */
    public void setTypeName(BY121403Bean by121403Bean,String type){
        Map<String,String> typeMap = CodeMasterManager.findCodeMasterMap(type);
        for(Map.Entry<String, String> key : typeMap.entrySet()){
            if(type.equals(BuyersConstant.MarketResearchPhase.TYPE)){
                if(key.getKey().equals(by121403Bean.getResearchPhase())){
                    by121403Bean.setResearchPhaseName(key.getValue());
                }
            }
        }
    }
}
