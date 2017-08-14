package com.msk.buyers.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.buyers.bean.BY121409Bean;
import com.msk.buyers.bean.BY121411Bean;
import com.msk.buyers.logic.BY121409Logic;
import com.msk.buyers.logic.BY121411Logic;
import com.msk.common.base.BaseController;
import com.msk.common.business.constant.BuyersConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.config.ConfigManager;
import com.msk.core.entity.ByMarketFood;
import com.msk.core.entity.ByMarketFoodBasic;
import com.msk.core.entity.ByMarketFoodByInfo;
import com.msk.core.entity.ByMarketTerminal;
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

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zhou_yajun on 2016/7/11.
 */
@Controller
@RequestMapping("BY121411")
public class BY121411Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121411Controller.class);

    @Autowired
    private BY121411Logic by121411Logic;
    @Autowired
    private BY121409Logic by121409Logic;

    /**
     * 菜场定性定级审批初始化
     * @param marketId
     * @return
     */
    @RequestMapping(value = "init/{marketId}")
    public String init(@PathVariable("marketId") String marketId,Model model){
        logger.info("菜场定性定级审批初始化");
        model.addAttribute("marketId",marketId);
        //根据传参菜场ID获取菜场的基本信息
        BaseParam param = new BaseParam();
        param.setFilter("marketId",marketId);
        ByMarketFoodBasic marketFoodBasic = by121409Logic.findOne(param);
        model.addAttribute("marketFoodBasic",marketFoodBasic);
        //从redis中获取基础数据
        //菜场性质
        Map<String,String> marketNature = CodeMasterManager.findCodeMasterMap(BuyersConstant.MarketType.TYPE);
        model.addAttribute("marketNature",marketNature);

        //根据菜场ID获取菜场图片信息
        List<BY121411Bean> marketFileList = by121411Logic.findList(param);
        //设置图片完整路径
        for (BY121411Bean by121411Bean : marketFileList){
            String picPath = ConfigManager.getMskFileDownLoadServices() + by121411Bean.getFileServerId();
            by121411Bean.setMarketFilePath(picPath);
        }
        model.addAttribute("marketFileList",marketFileList);

        return "buyers/BY121411";
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
     * 菜场定性定级审批保存
     * @return
     */
    @RequestMapping(value = "save",
            method = RequestMethod.POST)
    public @ResponseBody
    String save(BY121409Bean by121409Bean){
        super.setCommonParam(by121409Bean);
        Date currentDate = DateTimeUtil.getCustomerDate();
        by121409Bean.setCrtTime(currentDate);
        by121409Bean.setUpdTime(currentDate);
        by121409Bean.setActTime(currentDate);
        String marketId = by121409Bean.getMarketId();
        BaseParam param= new BaseParam();
        param.getFilterMap().put("marketCode",by121409Bean.getMarketCode());
        List<ByMarketFood> fodMarketList=by121411Logic.getFodMarketId(param);
        String flg="";

        if(!CollectionUtils.isEmpty(fodMarketList)){
            for(ByMarketFood list:fodMarketList){
                flg="";
                if(!list.getFodMarketId().equals(marketId)){
                    logger.info("批发市场编码已存在");
                    flg="1";
                    break;
                }else{
                    flg= SystemConst.RsStatus.SUCCESS;
                }
            }
            if(flg.equals(SystemConst.RsStatus.SUCCESS)){
                //如果当前审批状态是已审批则插入一条数据到表里,如果是未审批则更新这条数据
                if(String.valueOf(BuyersConstant.MarketApproveStatus.MarketApproved).equals(by121409Bean.getMarketStatus())){
                    by121411Logic.marketApproveSave(by121409Bean);
                }else{
                    by121411Logic.modify(by121409Bean);
                }
                //如果点击的是保存并同步按钮,需要同步菜场标准表,买家编码和买家基本信息表中的菜场编码
                if("1".equals(by121409Bean.getSyncFlag())){
                    by121411Logic.synchroBuyerCodeByMarketCode(by121409Bean);
                }
            }
          return flg;
        }else{
            //如果当前审批状态是已审批则插入一条数据到表里,如果是未审批则更新这条数据
            if(String.valueOf(BuyersConstant.MarketApproveStatus.MarketApproved).equals(by121409Bean.getMarketStatus())){
                by121411Logic.marketApproveSave(by121409Bean);
            }else{
                by121411Logic.modify(by121409Bean);
            }
            //如果点击的是保存并同步按钮,需要同步菜场标准表,买家编码和买家基本信息表中的菜场编码
            if("1".equals(by121409Bean.getSyncFlag())){
                by121411Logic.synchroBuyerCodeByMarketCode(by121409Bean);
            }
            return  SystemConst.RsStatus.SUCCESS;
        }


    }
}
