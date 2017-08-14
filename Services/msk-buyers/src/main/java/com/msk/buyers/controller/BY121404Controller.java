package com.msk.buyers.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.msk.buyers.bean.BY121404Bean;
import com.msk.buyers.logic.BY121404Logic;
import com.msk.common.base.BaseController;
import com.msk.common.business.constant.BuyersConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.ByMarketTerminalByInfo;
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
@RequestMapping("BY121404")
public class BY121404Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121404Controller.class);

    @Autowired
    private BY121404Logic by121404Logic;
    @Autowired
    private CommonLogic commonLogic;

    /**
     * 批发市场新增编辑画面
     * @param marketId
     * @return
     */
    @RequestMapping(value = "init/{marketId}/{id}")
    public String init(@PathVariable("marketId") String marketId,@PathVariable("id") String id,Model model){
        logger.info("批发市场商户新增编缉初始化");
        model.addAttribute("marketId",marketId);
        model.addAttribute("id",id);
        //根据传参批发市场ID获取批发市场的基本信息
        BaseParam param = new BaseParam();
        param.setFilter("marketId",marketId);
        param.setFilter("id",id);
        ByMarketTerminalByInfo marketTerminalBasic = by121404Logic.findOne(param);
        model.addAttribute("marketTerminalBasic",marketTerminalBasic);

        //从redis中获取基础数据
        //商户类型
        Map<String,String> merchantType = CodeMasterManager.findCodeMasterMap(BuyersConstant.MerchantType.TYPE);
        model.addAttribute("merchantType",merchantType);

        return "buyers/BY121404";
    }

    /**
     * 批发市场商户新增编缉保存
     * @return
     */
    @RequestMapping(value = "save",
            method = RequestMethod.POST)
    public @ResponseBody String save(BY121404Bean by121404Bean){
        //ID
        Long id = by121404Bean.getId();

        super.setCommonParam(by121404Bean);
        Date currentDate = DateTimeUtil.getCustomerDate();
        by121404Bean.setCrtTime(currentDate);
        by121404Bean.setUpdTime(currentDate);
        by121404Bean.setActTime(currentDate);

        int resultCount;
        //新增模式
        if(id == 0){
            Long maxId = commonLogic.maxId("by_market_terminal_by_info","ID");
            by121404Bean.setId(maxId);
            resultCount = by121404Logic.save(by121404Bean);
            if(resultCount == NumberConst.IntDef.INT_ONE){
                return "add";
            }
        }else{
            by121404Bean.setDelFlg(String.valueOf(NumberConst.IntDef.INT_ZERO));
            resultCount = by121404Logic.modify(by121404Bean);
            if(resultCount == NumberConst.IntDef.INT_ONE){
                return "modify";
            }
        }
        return "fail";
    }

    /**
     * 批发市场商户信息删除
     * @param id
     * @return
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
    public @ResponseBody String delete(@PathVariable("id") String id){

        if(!StringUtil.isNullOrEmpty(id)){
            BY121404Bean by121404Bean = new BY121404Bean();
            by121404Bean.setId(Long.parseLong(id));
            by121404Bean.setDelFlg(String.valueOf(NumberConst.IntDef.INT_ONE));
            int resultCount = by121404Logic.modify(by121404Bean);
            if(resultCount == NumberConst.IntDef.INT_ONE){
                return "delete";
            }
        }
        return "fail";
    }
}
