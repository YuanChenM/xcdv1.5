package com.msk.bs.controller;


import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.bs.bean.BS2102114Bean;
import com.msk.bs.bean.BS2102114Param;
import com.msk.bs.logic.BS2102114Logic;
import com.msk.common.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 定星管理
 * Created by ren_qiang on 2016/8/18.
 */
@Controller
@RequestMapping(value = "BS2102114")
public class BS2102114Controller extends BaseController {

    @Autowired
    private BS2102114Logic bs2102114Logic;

    @RequestMapping(value = "init" ,method = RequestMethod.POST)
    public String init(Model model,String slCode,String houseCode){
        model.addAttribute("slCode",slCode);
        model.addAttribute("houseCode",houseCode);
        /**初始定星数据--begin 2016/10/10 whc */
        BaseParam param = new BaseParam();
        super.setCommonParam(param);
        param.setFilter("slCode",slCode);
        param.setFilter("houseCode",houseCode);
        /**初始定星数据--end 2016/10/10 whc */
        BS2102114Bean bean = bs2102114Logic.findHouseShowName(param);
        if(bean != null){
            model.addAttribute("bs2102114Bean",bean);
        }


        return "bs/BS2102114";
    }

    /**
     * 修改冻品管家星级
     * @param slCode
     * @param houseCode
     * @param houseStar
     * @param validYearMonth
     * @return
     */
    @RequestMapping(value = "updateStar", method = RequestMethod.POST)
    public @ResponseBody int updateStar(String slCode,String houseCode,String houseStar ,String validYearMonth){
        BS2102114Param param = new BS2102114Param();
        super.setCommonParam(param);
        param.setSlCode(slCode);
        param.setHouseCode(houseCode);
        param.setStarCode(new BigDecimal(houseStar));
        Date date =  DateTimeUtil.getCustomerDate();
        param .setCrtTime(date);
        param.setUpdTime(date);
        param.setActTime(date);

        Integer cot = bs2102114Logic.update(param);
        return cot;
    }

}
