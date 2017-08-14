package com.msk.buyers.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.msk.buyers.bean.BY121404Bean;
import com.msk.buyers.bean.BY121410Bean;
import com.msk.buyers.logic.BY121410Logic;
import com.msk.common.base.BaseController;
import com.msk.common.business.constant.BuyersConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.ByMarketFoodByInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

/**
 * Created by zhou_yajun on 2016/7/8.
 */
@Controller
@RequestMapping("BY121410")
public class BY121410Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121410Controller.class);

    @Autowired
    private BY121410Logic by121410Logic;
    @Autowired
    private CommonLogic commonLogic;

    /**
     * 菜场新增编辑画面
     * @param marketId
     * @return
     */
    @RequestMapping(value = "init/{marketId}/{id}")
    public String init(@PathVariable("marketId") String marketId,@PathVariable("id") String id,Model model){
        logger.info("菜场商户新增编缉初始化");
        model.addAttribute("marketId",marketId);
        model.addAttribute("id",id);
        //根据传参菜场ID获取菜场的基本信息
        BaseParam param = new BaseParam();
        param.setFilter("marketId",marketId);
        param.setFilter("id",id);
        ByMarketFoodByInfo marketFoodByInfo = by121410Logic.findOne(param);
        model.addAttribute("marketFoodByInfo",marketFoodByInfo);

        //从redis中获取基础数据
        //商户类型
        Map<String,String> merchantType = CodeMasterManager.findCodeMasterMap(BuyersConstant.MerchantType.TYPE);
        model.addAttribute("merchantType",merchantType);

        return "buyers/BY121410";
    }

    /**
     * 菜场商户新增编缉保存
     * @return
     */
    @RequestMapping(value = "save",
            method = RequestMethod.POST)
    public @ResponseBody String save(BY121410Bean by121410Bean){

        //ID
        Long id = by121410Bean.getId();

        super.setCommonParam(by121410Bean);
        Date currentDate = DateTimeUtil.getCustomerDate();
        by121410Bean.setCrtTime(currentDate);
        by121410Bean.setUpdTime(currentDate);
        by121410Bean.setActTime(currentDate);

        int resultCount;
        //新增模式
        if(id == 0){
            Long maxId = commonLogic.maxId("by_market_food_by_info","ID");
            by121410Bean.setId(maxId);
            resultCount = by121410Logic.save(by121410Bean);
            if(resultCount == NumberConst.IntDef.INT_ONE){
                return "add";
            }
        }else{
            by121410Bean.setDelFlg(String.valueOf(NumberConst.IntDef.INT_ZERO));
            resultCount = by121410Logic.modify(by121410Bean);
            if(resultCount == NumberConst.IntDef.INT_ONE){
                return "modify";
            }
        }
        return "fail";
    }

    /**
     * 菜场商户信息删除
     * @param id
     * @return
     */
   /** Modif for Bug #2774 at 2016/09/19 by zhao_chen Start*/
    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
    public @ResponseBody String delete(@PathVariable("id") String id,BY121404Bean by121404Bean ){
        logger.info("菜场商户信息删除");
        if(!StringUtil.isNullOrEmpty(id)){
       /* BY121404Bean by121404Bean = new BY121404Bean();*/
            by121404Bean.setId(Long.parseLong(id));
            /*by121404Bean.setMarketId();*/
            by121404Bean.setDelFlg(String.valueOf(NumberConst.IntDef.INT_ONE));
            super.setCommonParam(by121404Bean);
            by121404Bean.setUpdTime(DateTimeUtil.getCustomerDate());
            int resultCount = by121410Logic.modify(by121404Bean);
            if(resultCount == NumberConst.IntDef.INT_ONE){
                return "delete";
            }
        }
        return "fail";
    }
    /** Modif for Bug #2774 at 2016/09/19 by zhao_chen end*/

}
